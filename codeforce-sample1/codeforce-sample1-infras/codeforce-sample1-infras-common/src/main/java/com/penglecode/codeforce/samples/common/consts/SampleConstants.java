package com.penglecode.codeforce.samples.common.consts;

import com.penglecode.codeforce.common.consts.SpringEnvConstant;

import java.util.function.Supplier;

/**
 * 示例常量
 *
 * @author pengpeng
 * @version 1.0
 */
public interface SampleConstants {

    /**
     * 当前应用的应用代码
     */
    Supplier<String> APP_CODE = new SpringEnvConstant<String>("spring.application.code") {};

}
