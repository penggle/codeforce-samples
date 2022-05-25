package com.penglecode.codeforce.sample2.codegen;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mybatis.EnableMultiDataSource;
import org.springframework.boot.autoconfigure.mybatis.NamedDatabase;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * 所有示例的代码自动生成入口
 *
 * @author pengpeng
 * @version 1.0
 */
@SpringBootApplication
@EnableMultiDataSource({@NamedDatabase("product")})
public class SamplesCodegenApplication implements ApplicationRunner {

    public static void main(String[] args) {
        //本例以非web方式(Servlet,Reactive)启动
        new SpringApplicationBuilder(SamplesCodegenApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }

    @Override
    public void run(ApplicationArguments args) {
        System.out.println("【ApplicationRunner】===============> args = " + args);
    }

}
