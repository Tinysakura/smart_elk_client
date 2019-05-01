package com.tinysakura.core.proxy;

import com.tinysakura.net.client.RetrofitServiceHolder;
import com.tinysakura.net.retrofit.service.IndexService;
import com.tinysakura.net.retrofit.service.QueryService;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
/**
 * 针对多节点elastic_search的动态代理InvocationHandler，当主节点发生异常时可以通过子节点与elk通信
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/1
 */
@Slf4j
public class MultiNodeInvocationHandler<T> implements InvocationHandler {

    /**
     * 被代理的对象
     */
    T target;

    List<Object> retrofitServices;

    public MultiNodeInvocationHandler(T target) {
        this.target = target;

        selectRetrofitServices(target);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return recurse(retrofitServices, method, args, 0, new Object[1]);
    }

    /**
     * 根据被代理的对象类型选择不同的retrofit service集合
     * @param target
     */
    public void selectRetrofitServices(T target) {
        RetrofitServiceHolder retrofitServiceHolder = RetrofitServiceHolder.getInstance();

        if (target instanceof IndexService) {
            retrofitServices = retrofitServiceHolder.getIndexService();
        }

        if (target instanceof QueryService) {
            retrofitServices = retrofitServiceHolder.getQueryService();
        }
    }

    private Observable<Object> recurse(final List<Object> retrofitServices, final Method method, final Object[] args, final int node, final Object[] result) {
        try {
            ((Observable)method.invoke(retrofitServices.get(node), args)).subscribe(new Observer() {
                public void onSubscribe(Disposable disposable) {

                }

                public void onNext(Object o) {
                    /**
                     * 保存响应结果
                     */
                    log.info("请求成功被node{}节点响应", node);
                    result[0] = o;
                }

                public void onError(Throwable throwable) {
                    if (node == retrofitServices.size() - 1) {
                        log.info("所有节点均不可用，请检查elk集群");
                        return;
                    }

                    log.info("node{}出现异常,选用下一个节点", node);
                    int next = node + 1;
                    recurse(retrofitServices, method, args, next, result);
                }

                public void onComplete() {

                }
            });
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        if (result[0] != null) {
            return Observable.just(result[0]);
        } else {
            /**
             * 当建立重复的索引时会走入该分支
             */
            return Observable.empty();
        }
    }
}