package com.tinysakura.core.document.bulk;

import com.alibaba.fastjson.JSON;
import com.tinysakura.bean.document.bulk.BulkOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 批量文档索引文件 构造类
 * @Author: chenfeihao@corp.netease.com
 * @Date: 2019/5/4
 */
@Data
@Slf4j
public class Bulk {
    /**
     * 批量文档索引数据集合
     */
    private List<BulkItem> bulkItems;

    /**
     * file 2 MultipartBody
     * @param file
     * @return
     */
    public MultipartBody.Part fromBatchJsonFile(File file) {
        RequestBody fileRequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);

        MultipartBody.Part part =
                MultipartBody.Part.createFormData(file.getName(), file.getName(), fileRequestBody);

        return part;
    }

    public static final class Builder{
        private List<BulkItem> bulkItems;

        public Builder() {
            this.bulkItems = new ArrayList<BulkItem>();
        }

        public Builder item(BulkItem bulkItem) {
            bulkItems.add(bulkItem);

            return this;
        }

        /**
         * 根据bulkItems生成符合elk要求的request body
         * @return
         */
        public MultipartBody.Part build() {
            StringBuilder sb = new StringBuilder();
            Map<String, BulkOperation> operationMap = new HashMap<String, BulkOperation>(1);

            for (BulkItem bulkItem : bulkItems) {
                operationMap.put(bulkItem.getOperation(), bulkItem.getBulkItem().getBulkOperation());
                sb.append(JSON.toJSONString(operationMap)).append("\n");
                operationMap.clear();

                sb.append(JSON.toJSONString(bulkItem.getBulkItem().getDocument()));
            }

            log.info("documents.json content : {}", sb.toString());
            return fromBytes(sb.toString().getBytes());
        }

        private MultipartBody.Part fromBytes(byte[] bytes) {
            RequestBody fileRequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), bytes);

            MultipartBody.Part part =
                    MultipartBody.Part.createFormData("documents.json", "documents.json", fileRequestBody);

            return part;
        }
    }
}