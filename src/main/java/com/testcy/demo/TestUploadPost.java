package com.testcy.demo;

import com.testcy.utils.AutoTools;
import com.testcy.utils.LoggerUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

/**
 * @Classname TestUploadPost
 * @Description 文件上传接口post请求
 * @Date 2022/3/2 17:08
 * @Created by qitongtong
 * @Version 1.0
 **/
public class TestUploadPost {

    @Test
    public void uploadPic(){
        //设置请求地址和请求方式
        String url="http://www.testingedu.com.cn:8000/index.php/home/Uploadify/imageUp/savepath/head_pic/pictitle/banner/dir/images.html";
        HttpPost httpPost = new HttpPost(url);
        //设置http请求头 Content-Type
        httpPost.setHeader("Content-Type","multipart/form-data");
        //构造请求体
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        multipartEntityBuilder.addBinaryBody("road2", new File("src/main/resources/images/road2.jpg"));
        HttpEntity httpEntity = multipartEntityBuilder.build();
        httpPost.setEntity(httpEntity);
        CloseableHttpClient client = HttpClients.createDefault();
        try {
            CloseableHttpResponse response = client.execute(httpPost);
            LoggerUtils.log.info("上传文件接口返回值："+response);
            LoggerUtils.log.info(EntityUtils.toString(response.getEntity()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
