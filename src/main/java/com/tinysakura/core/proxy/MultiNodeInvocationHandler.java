package com.tinysakura.core.proxy;

import com.tinysakura.bean.query.result.Hit;
import com.tinysakura.bean.query.result.Hits;
import com.tinysakura.bean.query.result.QueryResponse;
import com.tinysakura.net.client.RetrofitServiceHolder;
import com.tinysakura.net.retrofit.service.DocumentService;
import com.tinysakura.net.retrofit.service.IndexService;
import com.tinysakura.net.retrofit.service.QueryService;
import com.tinysakura.util.ArrayUtil;
import com.tinysakura.util.StringUtil;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

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

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        Class clazz = null;

        if (method.getDeclaringClass().equals(QueryService.class)) {
            /**
             * 针对QueryService做特殊处理
             */
            try {
                clazz = (Class) args[args.length - 1];
                args = ArrayUtil.removeAppointType(Class.class, args);

                method = method.getDeclaringClass().getMethod(method.getName(), ArrayUtil.convert2ClassArray(args));
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }

        return recurse(retrofitServices, method, args, 0, new Object[1], clazz);
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

        if (target instanceof DocumentService) {
            retrofitServices = retrofitServiceHolder.getDocumentService();
        }
    }

    private Observable recurse(final List<Object> retrofitServices, final Method method, final Object[] args, final int node, final Object[] result, final Class clazz) {
        try {
            ((Observable)method.invoke(retrofitServices.get(node), args)).subscribe(new Observer() {
                @Override
                public void onSubscribe(Disposable disposable) {

                }

                @Override
                public void onNext(Object o) {
                    /**
                     * 保存响应结果
                     */
                    log.info("请求成功被node{}节点响应", node);
                    result[0] = o;
                }

                @Override
                public void onError(Throwable throwable) {
                    if (node == retrofitServices.size() - 1) {
                        log.info("所有节点均不可用，请检查elk集群");
                        return;
                    }

                    log.info("node{}出现异常,选用下一个节点", node);
                    int next = node + 1;
                    recurse(retrofitServices, method, args, next, result, clazz);
                }

                @Override
                public void onComplete() {

                }
            });
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        if (result[0] != null) {
            /**
             * 针对QueryService中的方法做特殊处理
             */
            if (target instanceof QueryService) {
                QueryResponse queryResponse = (QueryResponse) result[0];

                /**
                 * 通过反射填充QueryResponse的Result属性
                 */
                Hits hits = queryResponse.getHits();

                List<Object> resultList = new ArrayList<Object>();

                /**
                 * 查询结果为空的情况
                 */
                if (hits.getHits() == null || hits.getHits().length == 0) {
                    queryResponse.setResults(resultList);

                    return Observable.just(queryResponse);
                }

                /**
                 * hits节点下_source字段不为空的情况（查询时指定了返回的fields时该字段会为空）
                 */
                if (hits.getHits()[0].get_source() != null) {
                    for (Hit hit : hits.getHits()) {
                        Map<String, Object> resourceMap = hit.get_source();

                        Object o = null;

                        try {
                            o = clazz.newInstance();
                        } catch (InstantiationException | IllegalAccessException e) {
                            e.printStackTrace();
                        }

                        Method[] methods = clazz.getDeclaredMethods();
                        Map<String, Method> methodMap = Arrays.asList(methods).stream().collect(Collectors.toMap(e -> e.getName(), e -> e));

                        for (String key : resourceMap.keySet()) {
                            /**
                             * 获取到对应属性的set方法，通过反射的方式注入对象
                             */
                            String methodName = "set".concat(StringUtil.upperCaseFirst(key));
                            try {
                                Method setMethod = methodMap.get(methodName);

                                Class<?> setParameter = setMethod.getParameterTypes()[0];
                                Constructor<?> constructor = setParameter.getConstructor(String.class);
                                Object setValue = constructor.newInstance(resourceMap.get(key).toString().split(".")[0]);

                                setMethod.invoke(o, setValue);
                            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException e) {
                                e.printStackTrace();
                            }
                        }

                        resultList.add(o);
                    }
                }
                else if (hits.getHits()[0].getFields() != null) {
                    /**
                     * hits节点下source字段为空时转为使用fields属性
                      */
                    for (Hit hit : hits.getHits()) {
                        Map<String, Object[]> fieldsMap = hit.getFields();

                        Object o = null;

                        try {
                            o = clazz.newInstance();
                        } catch (InstantiationException | IllegalAccessException e) {
                            e.printStackTrace();
                        }

                        Method[] methods = clazz.getDeclaredMethods();
                        Map<String, Method> methodMap = Arrays.asList(methods).stream().collect(Collectors.toMap(e -> e.getName(), e -> e));

                        for (String key : fieldsMap.keySet()) {
                            /**
                             * 获取到对应属性的set方法，通过反射的方式注入对象
                             */
                            String methodName = "set".concat(StringUtil.upperCaseFirst(key));
                            try {
                                Method setMethod = methodMap.get(methodName);
                                setMethod.invoke(o, fieldsMap.get(key)[0]);
                            } catch (IllegalAccessException | InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }

                        resultList.add(o);
                    }
                }

                queryResponse.setResults(resultList);

                return Observable.just(queryResponse);
            }

            return Observable.just(result[0]);
        } else {
            /**
             * 当建立重复的索引时会走入该分支
             */
            return Observable.empty();
        }
    }
}