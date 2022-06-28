package com.test.interfaceTest.testBase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
    public Properties prop;
    public final static int RESPONSE_STATUS_CODE_201 = 201;
    public final static int RESPONSE_STATUS_CODE_404 = 404;
    public final static int RESPONSE_STATUS_CODE_500 = 500;
    public TestBase(){
        try {
            prop = new Properties();
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") +
                    "/src/main/java/com/test/interfaceTest/config/config.properties");
            prop.load(fis);
        } catch (FileNotFoundException e){
            System.out.println("文件找不到！");
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}


