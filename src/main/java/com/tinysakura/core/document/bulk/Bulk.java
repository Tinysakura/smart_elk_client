package com.tinysakura.core.document.bulk;

import com.google.gson.Gson;
import com.tinysakura.bean.document.bulk.BulkOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
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
    public static RequestBody fromBatchJsonFile(File file) {
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, file);

        return body;
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
        public RequestBody build() {
            StringBuilder sb = new StringBuilder();
            Map<String, BulkOperation> operationMap = new HashMap<String, BulkOperation>(1);
            Gson gson = new Gson();

            for (BulkItem bulkItem : bulkItems) {
                operationMap.put(bulkItem.getOperation(), bulkItem.getBulkItem().getBulkOperation());
                String operation = gson.toJson(operationMap);
                String document = gson.toJson(bulkItem.getBulkItem().getDocument());
                sb.append(operation).append("\n");
                operationMap.clear();
            }

            log.info("documents.json content\n{}", sb.toString());
            return fromJsonStr(sb.toString());
        }

        private  RequestBody fromJsonStr(String jsonStr) {
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = RequestBody.create(mediaType, jsonStr);

            return body;
        }
    }
}