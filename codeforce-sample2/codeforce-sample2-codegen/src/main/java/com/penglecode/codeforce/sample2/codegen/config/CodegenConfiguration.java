package com.penglecode.codeforce.sample2.codegen.config;

import com.penglecode.codeforce.common.config.defaults.CommonDefaultConfigBasePackage;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author pengpeng
 * @version 1.0
 */
@Configuration
@ComponentScan(basePackageClasses=CommonDefaultConfigBasePackage.class)
public class CodegenConfiguration {
}
