package com.testcy.demo;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.testcy.utils.LoggerUtils;
import netscape.javascript.JSObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import sun.tools.jar.CommandLine;

import java.io.IOException;

/**
 * @Classname TestCookieStore
 * @Description CookieStore测试
 * @Date 2022/3/9 11:32
 * @Created by qitongtong
 * @Version 1.0
 **/
public class TestCookieStore {

    @Test
    public void testCookieStore(){
        BasicCookieStore cookieStore = new BasicCookieStore();
        CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
        HttpPost httpPost = new HttpPost("http://www.testingedu.com.cn:8081/inter/HTTP/auth");
        try {
            HttpEntity entity = client.execute(httpPost).getEntity();
            String result = EntityUtils.toString(entity, "utf-8");
            Object tokenV = JSONPath.read(result, "$.token");
            LoggerUtils.log.info("返回结果为："+result);
            LoggerUtils.log.info("返回结果为："+tokenV);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
