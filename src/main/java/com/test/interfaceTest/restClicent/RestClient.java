package com.test.interfaceTest.restClicent;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import static com.alibaba.fastjson.JSON.parseObject;

public class RestClient {
    //get请求，无请求头
    public CloseableHttpResponse get(String url) throws IOException, ClientProtocolException {

        // 创建一个HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // HttpGet请求对象,传入请求地址
        HttpGet httpGet = new HttpGet(url);
        // 执行请求，并得到返回对象
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
        return httpResponse;
    }
    // get请求，带请求头
    public CloseableHttpResponse get(String url,HashMap<String,String> headMap) throws IOException, ClientProtocolException {
        // 创建一个HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // HttpGet请求对象,传入请求地址
        HttpGet httpGet = new HttpGet(url);
        if(!headMap.isEmpty() || headMap!=null){
            for(Map.Entry<String,String> entry : headMap.entrySet()){
                httpGet.addHeader(entry.getKey(),entry.getValue());
            }
        }
        // 执行请求，并得到返回对象
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
        return httpResponse;
    }
    // post请求
    public CloseableHttpResponse post(String url, String queryEntity, HashMap<String,String> headMap) throws ClientProtocolException,IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        //HttpPost请求对象
        HttpPost httpPost = new HttpPost(url);
        //加载请求头
        if(!headMap.isEmpty() || headMap!=null){
            for(Map.Entry<String,String> entry : headMap.entrySet()){
                httpPost.addHeader(entry.getKey(),entry.getValue());
            }
        }
        //设置请求体
        httpPost.setEntity(new StringEntity(queryEntity));
        //发送post请求
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        return httpResponse;
    }



































}
