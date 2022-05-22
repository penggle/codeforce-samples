package com.penglecode.codeforce.samples.consumer.news.api.service.fallback;

import com.penglecode.codeforce.samples.consumer.news.api.dto.JokeDTO;
import com.penglecode.codeforce.samples.consumer.news.api.dto.OpenApiResult;
import com.penglecode.codeforce.samples.consumer.news.api.service.JokeApiService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * {@link JokeApiService}的FallbackFactory
 *
 * @author pengpeng
 * @version 1.0
 */
@Component
public class JokeApiServiceFallbackFactory implements FallbackFactory<JokeApiService> {

    @Override
    public JokeApiService create(Throwable cause) {
        return new JokeApiServiceFallbackImpl(cause);
    }

    public static class JokeApiServiceFallbackImpl extends AsbtractFallbackApiService implements JokeApiService {

        public JokeApiServiceFallbackImpl(Throwable cause) {
            super(cause);
        }

        @Override
        public OpenApiResult<JokeDTO> getJokeById(String sid) {
            return commonFallbackResult();
        }

        @Override
        public OpenApiResult<List<JokeDTO>> getJokeList(String type, Integer page, Integer count) {
            return commonFallbackResult();
        }

    }

}
