package com.testcy.http;

import com.alibaba.fastjson.JSONObject;
import com.testcy.utils.LoggerUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


/**
 * @Classname EasyRequest
 * @Description http请求封装
 * @Date 2022/3/3 11:20
 * @Created by qitongtong
 * @Version 1.0
 **/
public class EasyRequest {

    /**
     * Content-Type为application/x-www-form-urlencoded格式的post请求封装
     *
     * @param url     请求url
     * @param entity  正文实体
     * @param headers json格式的请求头
     * @return http响应
     */
    public static String postRequest(String url, String entity, String... headers) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        StringEntity stringEntity = null;
        try {
            stringEntity = new StringEntity(entity);
            stringEntity.setContentType("application/x-www-form-urlencoded");
            stringEntity.setContentEncoding("utf-8");
            httpPost.setEntity(stringEntity);
            if (headers.length > 0) {
                for (String header : headers) {
                    JSONObject jsonObject = JSONObject.parseObject(header);
                    for (String key : jsonObject.keySet()) {
                        httpPost.addHeader(key, jsonObject.getString(key));
                    }
                }
            }
            String response = EntityUtils.toString(stringEntity, "utf-8");
            return response;
        } catch (Exception e) {
            LoggerUtils.log.error("实体内容或实体编码方式有误！请检查...");
            String response = e.fillInStackTrace().toString();
            e.printStackTrace();
            return response;
        }
    }

}
