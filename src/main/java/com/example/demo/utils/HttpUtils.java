package com.example.demo.utils;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


/**
 * Created by xuyf on 2018/7/19.
 */
public class HttpUtils {
    public static String doPost(String url,String data){
        String result = null;
        // 创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);
        //添加参数
        StringEntity stringEntity = new StringEntity(data,"UTF-8");
        stringEntity.setContentEncoding("UTF-8");
        httpPost.setEntity(stringEntity);
        CloseableHttpResponse response = null;
        try {
            // 执行请求操作，并拿到结果（同步阻塞）
            response = httpClient.execute(httpPost);
            // 获取结果实体
            // 判断网络连接状态码是否正常(0--200都数正常)
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity(), "utf-8");
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally{   // 释放链接
            try{
                response.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return result;
    }

    public static String doGet(String url){
        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity(), "utf-8");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                response.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return result;
    }
}
