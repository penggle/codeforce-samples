package com.penglecode.codeforce.samples.consumer.news.api.service.fallback;

import com.penglecode.codeforce.samples.consumer.news.api.dto.OpenApiResult;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

/**
 * @author pengpeng
 * @version 1.0
 */
public abstract class AsbtractFallbackApiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsbtractFallbackApiService.class);

    protected final Throwable cause;

    public AsbtractFallbackApiService(Throwable cause) {
        this.cause = cause;
    }

    protected <T> OpenApiResult<T> commonFallbackResult() {
        LOGGER.error(cause.getMessage(), cause);
        if(cause instanceof FeignException) {
            FeignException feignException = (FeignException) cause;
            return new OpenApiResult<>(feignException.status(), feignException.getMessage(), null);
        }
        return defaultFallbackResult();
    }

    protected <T> OpenApiResult<T> defaultFallbackResult() {
        return new OpenApiResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), cause.getMessage(), null);
    }

}
