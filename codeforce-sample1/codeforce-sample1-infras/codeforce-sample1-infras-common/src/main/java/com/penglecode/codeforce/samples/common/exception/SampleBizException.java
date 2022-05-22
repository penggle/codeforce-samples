package com.penglecode.codeforce.samples.common.exception;

import com.penglecode.codeforce.common.exception.ApplicationException;

/**
 * 示例业务异常
 *
 * @author pengpeng
 * @version 1.0
 */
public class SampleBizException extends ApplicationException {

    public SampleBizException(String code, String message) {
        super(code, message);
    }

    public SampleBizException(Throwable cause) {
        super(cause);
    }

    public SampleBizException(String message, Throwable cause) {
        super(message, cause);
    }

    public SampleBizException(String code, String message, Throwable cause) {
        super(code, message, cause);
    }

}
