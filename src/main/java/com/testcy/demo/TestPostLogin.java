package com.testcy.demo;

import com.alibaba.fastjson.JSONPath;
import com.testcy.utils.AutoTools;
import com.testcy.utils.LoggerUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @Classname TestPostLogin
 * @Description post请求接口示例
 * @Date 2022/2/28 13:53
 * @Created by qitongtong
 * @Version 1.0
 **/
public class TestPostLogin {

    @Test
    public void login(){
        HttpPost httpPost = new HttpPost("http://www.testingedu.com.cn:8000/index.php?m=Home&c=User&a=do_login&t=0.48018265463013776");
        //通过请求头设置正文格式
//        httpPost.setHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
        try {
            StringEntity stringEntity = new StringEntity("username=13800138006&password=123456&verify_code=1");
            //设置正文格式，通过实体对象来设置
            stringEntity.setContentType("application/x-www-form-urlencoded");
            //设置正文编码方式
            stringEntity.setContentEncoding("utf-8");
            httpPost.setEntity(stringEntity);
            CloseableHttpClient client = HttpClients.createDefault();
            CloseableHttpResponse response = client.execute(httpPost);
            LoggerUtils.log.info("返回对象为："+response);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "utf-8");
            LoggerUtils.log.info("返回正文为："+result);

            result=AutoTools.decodeUnicode(result);
            LoggerUtils.log.info("Unicode解码之后的返回结果为："+result);
            //json解析
            String msg = JSONPath.read(result, "$.msg").toString();
            LoggerUtils.log.info("unicode解码之后的返回结果中的msg字段信息为："+msg);
            Assertions.assertEquals("登陆成功", msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
