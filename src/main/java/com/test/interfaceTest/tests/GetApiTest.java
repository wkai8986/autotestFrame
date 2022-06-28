package com.test.interfaceTest.tests;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.test.interfaceTest.restClicent.RestClient;
import com.test.interfaceTest.testBase.TestBase;
import com.test.interfaceTest.utils.JsonParse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.swing.text.Utilities;
import java.io.IOException;

import static com.alibaba.fastjson.JSON.parseObject;

public class GetApiTest extends TestBase {
    TestBase testBase;
    String url;
    String host;
    RestClient restClient;
    CloseableHttpResponse closeableHttpResponse;


    @BeforeClass
    public void setUp(){
        testBase = new TestBase();
        host = prop.getProperty("host");
        url = host + prop.getProperty("api_list_user");
    }
    @Test
    public void getApiTest() throws IOException,ClientProtocolException,AssertionError{
        restClient = new RestClient();
        closeableHttpResponse = restClient.get(url);

        // 测试状态码是否为201
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode,RESPONSE_STATUS_CODE_201,"返回不是200");

        // 返回体
        String responseContent = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
        System.out.println(responseContent);
        JSONObject responseJson = JSON.parseObject(responseContent);

        String resData = JsonParse.getValueByJPath(responseJson,"data[0]/name");
        Assert.assertEquals(resData,"cerulea","数据不一致");
    }

}
