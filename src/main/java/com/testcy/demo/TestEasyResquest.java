package com.testcy.demo;

import com.alibaba.fastjson.JSONPath;
import com.testcy.http.EasyRequest;
import com.testcy.utils.DateUtils;
import com.testcy.utils.LoggerUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @Classname TestEasyResquest
 * @Description 测试easyRequest
 * @Date 2022/3/3 17:44
 * @Created by qitongtong
 * @Version 1.0
 **/
public class TestEasyResquest {

    @Test
    public void testLogin() {
        //auth接口
        String response0 = EasyRequest.postRequest("http://www.testingedu.com.cn:8081/inter/HTTP/auth", "");
        LoggerUtils.log.info("auth接口返回结果为：" + response0);
        String token = JSONPath.read(response0, "$.token").toString();
        LoggerUtils.log.info("auth返回结果中token的值为：" + token);
        String tokenH = "{" + "\"token\"" + ":\""+token + "\"" + "}";
        LoggerUtils.log.info("json格式的token：" + tokenH);
        //注册接口
        String username = "testcy" + DateUtils.createTime("mmss") ;
        LoggerUtils.log.info("随机生成的注册用户名为:"+username);
        String response1 = EasyRequest.postRequest("http://www.testingedu.com.cn:8081/inter/HTTP//register?username="+username+"&pwd=123456&nickname=库里yyds&describe=傻酷mvp", "", tokenH);
        LoggerUtils.log.info("登录接口返回结果为：" + response1);
        //登录接口
        String response2 = EasyRequest.postRequest("http://www.testingedu.com.cn:8081/inter/HTTP/login", "username="+username+"&password=123456", tokenH);
        LoggerUtils.log.info("登录接口返回结果为：" + response2);
        //查询用户接口
        String userId = JSONPath.read(response2, "$.userid").toString();
        LoggerUtils.log.info("当前用户id为："+userId);
        String response3 = EasyRequest.postRequest("http://testingedu.com.cn:8081/inter/HTTP/getUserInfo", "id="+userId, tokenH);
        LoggerUtils.log.info("查询用户接口返回结果为：" + response3);
        //登出接口
        String response4 = EasyRequest.postRequest("http://testingedu.com.cn:8081/inter/HTTP/logout", "", tokenH);
        LoggerUtils.log.info("注销接口返回结果为：" + response4);
    }

    @Test
    public void testEduInter() {
        String result = null;
        String username = "Curry" + DateUtils.createTime("mmss");
        LoggerUtils.log.info("当前随机注册的用户为：" + username);
        try {
            CloseableHttpClient client1 = HttpClients.custom().build();
            CloseableHttpClient client0 = HttpClients.createDefault();
            //访问auth接口
            HttpPost auth = new HttpPost("http://www.testingedu.com.cn:8081/inter/HTTP/auth");
            CloseableHttpResponse response = client0.execute(auth);
            result = EntityUtils.toString(response.getEntity(), "utf-8");
            LoggerUtils.log.info("auth接口返回的结果为：" + result);
            String tokenV = JSONPath.read(result, "$.token").toString();
            LoggerUtils.log.info("auth接口返回的token：" + tokenV);
            //访问注册接口
//            HttpPost register = new HttpPost("http://www.testingedu.com.cn:8081/inter/HTTP//register?username=curry0&pwd=123456&nickname=%E5%BA%93%E9%87%8Cyyds&describe=mvp%E5%82%BB%E9%85%B7");
            HttpPost register = new HttpPost("http://www.testingedu.com.cn:8081/inter/HTTP//register?username="+username+"&pwd=123456&nickname=库里yyds&describe=傻酷mvp");
            register.addHeader("token", tokenV);
//            register.addHeader("Content-Type","application/x-www-form-urlencoded");
            CloseableHttpResponse response1 = client0.execute(register);
            result = EntityUtils.toString(response1.getEntity(), "utf-8");
            LoggerUtils.log.info("注册接口返回：" + result);
            //访问登录接口
            HttpPost login = new HttpPost("http://www.testingedu.com.cn:8081/inter/HTTP/login");
            //设置登录接口头-->token
            login.addHeader("token", tokenV);
            StringEntity entity = new StringEntity("username=" + username + "&password=123456");
            entity.setContentType("application/x-www-form-urlencoded");
            entity.setContentEncoding("utf-8");
            login.setEntity(entity);
            CloseableHttpResponse loginResponses = client0.execute(login);
            String loginResult = EntityUtils.toString(loginResponses.getEntity(), "utf-8");
            LoggerUtils.log.info("登录接口返回结果：" + loginResult);
            String userId = JSONPath.read(loginResult, "$.userid").toString();
            //访问查询用户信息接口
            HttpPost getUserInfo = new HttpPost("http://testingedu.com.cn:8081/inter/HTTP/getUserInfo");
            getUserInfo.addHeader("Content-Type", "application/x-www-form-urlencoded");
            getUserInfo.addHeader("token", tokenV);
            StringEntity stringEntity = new StringEntity("id=" + userId);
            getUserInfo.setEntity(stringEntity);
            CloseableHttpResponse response3 = client0.execute(getUserInfo);
            result = EntityUtils.toString(response3.getEntity(), "utf-8");
            LoggerUtils.log.info("查询用户信息接口返回结果为:" + result);
            //访问注销接口
            HttpPost logout = new HttpPost("http://testingedu.com.cn:8081/inter/HTTP/logout");
            logout.addHeader("token", tokenV);
            CloseableHttpResponse response2 = client0.execute(logout);
            result = EntityUtils.toString(response2.getEntity(), "utf-8");
            LoggerUtils.log.info("注销接口返回结果为：" + result);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
