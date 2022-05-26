package com.penglecode.codeforce.samples;

import com.penglecode.codeforce.mybatistiny.EnableMybatisTiny;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mds.EnableMultiDataSource;
import org.springframework.boot.autoconfigure.mds.NamedDatabase;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 示例项目SpringBoot启动类
 *
 * @author pengpeng
 * @version 1.0
 */
@EnableMybatisTiny
@SpringBootApplication
@EnableMultiDataSource({@NamedDatabase("product")})
@EnableFeignClients(basePackages="com.penglecode.codeforce.samples.news")
public class Sample1Application {

    public static void main(String[] args) {
        SpringApplication.run(Sample1Application.class, args);
    }

}