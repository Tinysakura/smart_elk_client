package com.tinysakura.net.retrofit.service;

import com.tinysakura.bean.document.bulk.BulkResult;
import com.tinysakura.bean.test.Music;
import com.tinysakura.constant.BulkOperationConstants;
import com.tinysakura.core.document.bulk.Bulk;
import com.tinysakura.core.document.bulk.BulkItem;
import com.tinysakura.net.client.RetrofitProxyServiceHolder;
import io.reactivex.functions.Consumer;
import okhttp3.MultipartBody;
import org.junit.Test;

/**
 * DocumentService测试
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/4
 */

public class DocumentServiceTest {

    /**
     * 批量索引文档接口测试
     */
    @Test
    public void batchPostDocumentTest() {
        BulkItem bulkItem1 = new BulkItem.Builder().operation(BulkOperationConstants.Operation.CREATE).document(new Music("Taylor swift", "Style"))
                .documentId("3").index("media").type(BulkOperationConstants.Type.CONCAT).build();

        BulkItem bulkItem2 = new BulkItem.Builder().operation(BulkOperationConstants.Operation.CREATE).document(new Music("Taylor swift", "Love Story"))
                .documentId("4").index("media").type(BulkOperationConstants.Type.CONCAT).build();

        MultipartBody.Part part = new Bulk.Builder().item(bulkItem1).item(bulkItem2).build();

        DocumentService documentService = RetrofitProxyServiceHolder.getInstance().getDocumentServiceProxy();
        documentService.batchPostDocument(part).subscribe(new Consumer<BulkResult>() {
            public void accept(BulkResult bulkResult) throws Exception {
                System.out.println(bulkResult);
            }
        });
    }
}