package com.penglecode.codeforce.samples.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 应用codeforce默认配置
 *
 * @author pengpeng
 * @version 1.0
 */
@Configuration
@ComponentScan(basePackages="com.penglecode.codeforce.common.config.defaults")
public class DefaultCodeforceConfiguration {
}
