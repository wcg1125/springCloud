package com.hln.oss.utils;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//交给spring管理， spring加载之后，执行一个方法；
@Component
public class ConstantPropertiesUtil implements InitializingBean {

    //读取配置文件  基于spring的注解Value去对应的配置文件里面基于名读取对应的值存在变量里面

    //地域节点
    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.file.keyid}")
    private String keyId;

    @Value("${aliyun.oss.file.keysecret}")
    private String keysecret;

    @Value("${aliyun.oss.file.bucketname}")
    private String bucketname;


    public static String END_POINT;

    public static String ACCESS_KEY_ID;

    public static String ACCESS_KEY_SECRET;

    public static String BUCKET_NAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT = endpoint;
        ACCESS_KEY_ID = keyId;
        ACCESS_KEY_SECRET = keysecret;
        BUCKET_NAME = bucketname;
    }
}
