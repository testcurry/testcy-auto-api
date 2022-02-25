package com.testcy.demo;

import com.testcy.utils.LoggerUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @Classname TestGetIP
 * @Description 测试查询ip的接口
 * @Date 2022/2/22 9:34
 * @Created by qitongtong
 * @Version 1.0
 **/
public class TestGetIP {

    @Test
    public void getIP() {
        //拼接请求报文url，请求方式method
        HttpGet getIP = new HttpGet("https://sp1.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?query=10.89.7.88&co=&resource_id=5809&t=1645493561212&ie=utf8&oe=gbk&cb=op_aladdin_callback&format=json&tn=baidu&cb=jQuery11020644429622963157_1645493398048&_=1645493398054");
        //HttpGet getIP = new HttpGet("https://sp1.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?query=10.89.7.88&co=&resource_id=5809&t=1645493561212&ie=utf8&oe=gbk&cb=op_aladdin_callback&format=json&tn=baidu");
        CloseableHttpClient client = HttpClients.createDefault();
        try {
            //发送请求
            CloseableHttpResponse response = client.execute(getIP);
            LoggerUtils.log.info("返回对象为："+response);
            //获取返回行
            StatusLine line = response.getStatusLine();
            LoggerUtils.log.info("返回状态行为："+line);
            //获取状态码
            int statusCode = response.getStatusLine().getStatusCode();
            //获取返回协议版本
            ProtocolVersion protocolVersion = response.getStatusLine().getProtocolVersion();
            LoggerUtils.log.info("协议版本："+protocolVersion);
            LoggerUtils.log.info("状态码位："+statusCode);
            //获取返回实体
            HttpEntity entity = response.getEntity();
            LoggerUtils.log.info("返回实体对象为："+entity);
            //获取实体字符串内容
            String result = EntityUtils.toString(entity, "utf-8");
            LoggerUtils.log.info("返回实体内容为："+result);
            //获取所有的返回头信息
            Header[] allHeaders = response.getAllHeaders();
            for (Header header : allHeaders) {
                String name = header.getName();
                String value = header.getValue();
                LoggerUtils.log.info("头信息："+name+": "+value);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
