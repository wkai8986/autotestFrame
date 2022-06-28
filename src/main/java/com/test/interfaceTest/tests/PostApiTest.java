package com.test.interfaceTest.tests;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.test.interfaceTest.bean.User;
import com.test.interfaceTest.restClicent.RestClient;
import com.test.interfaceTest.testBase.TestBase;
import com.test.interfaceTest.utils.JsonParse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONString;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PostApiTest extends TestBase {

    TestBase testBase;
    String host;
    String url;
    RestClient restClient;
    CloseableHttpResponse httpResponse;

    @BeforeClass
    public void setUp(){
        testBase = new TestBase();
        host = prop.getProperty("host");
        url = host + prop.getProperty("api_create_user");
    }

    @Test
    public void postApiTest() throws IOException {
        restClient = new RestClient();
        HashMap<String,String> headMap = new HashMap<String,String>();
        //支持json格式
        headMap.put("Content-Type", "application/json");
        User user = new User("Tom","teacher");
        String dataJson = JSON.toJSONString(user);
        httpResponse = restClient.post(url,dataJson,headMap);

        int statusCode = httpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_201,"返回码不是200！");

        //返回体转化为json对象
        String responseData = EntityUtils.toString(httpResponse.getEntity());
        System.out.println(responseData);
       JSONObject responseDataJson = JSON.parseObject(responseData);

        String name = JsonParse.getValueByJPath(responseDataJson,"name");
        String job = JsonParse.getValueByJPath(responseDataJson,"job");

        Assert.assertEquals(name,"Tom","姓名不同！");
        Assert.assertEquals(job,"teacher","job不同！");

    }
}
