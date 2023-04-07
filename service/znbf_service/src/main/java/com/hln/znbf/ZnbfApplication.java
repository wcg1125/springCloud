package com.hln.znbf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.hln"})
@MapperScan({"com.hln.znbf.mapper"})
@EnableScheduling //表示开启定时任务
public class ZnbfApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZnbfApplication.class, args);
    }

}
