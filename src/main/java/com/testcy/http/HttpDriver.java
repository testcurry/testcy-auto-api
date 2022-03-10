package com.testcy.http;

import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname HttpDriver
 * @Description 接口测试常用方法封装
 * @Date 2022/3/10 15:01
 * @Created by qitongtong
 * @Version 1.0
 **/
public class HttpDriver {

    private CloseableHttpClient client;
    private boolean useCookie = true;
    private BasicCookieStore cookieStore;
    private boolean useHeader = true;
    private Map<String, String> headerMap;

    public HttpDriver() {
        cookieStore = new BasicCookieStore();
        headerMap=new HashMap<>();
    }

}
