package com.penglecode.codeforce.samples.news.api.service.fallback;

import com.penglecode.codeforce.samples.news.api.dto.NewsDTO;
import com.penglecode.codeforce.samples.news.api.dto.OpenApiResult;
import com.penglecode.codeforce.samples.news.api.service.NewsApiService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * {@link NewsApiService}的FallbackFactory
 *
 * @author pengpeng
 * @version 1.0
 */
@Component
public class NewsApiServiceFallbackFactory implements FallbackFactory<NewsApiService> {

    @Override
    public NewsApiService create(Throwable cause) {
        return new NewsApiServiceFallbackImpl(cause);
    }

    public static class NewsApiServiceFallbackImpl extends AsbtractFallbackApiService implements NewsApiService {

        public NewsApiServiceFallbackImpl(Throwable cause) {
            super(cause);
        }

        @Override
        public OpenApiResult<List<NewsDTO>> getNewsList(Integer page, Integer count) {
            return commonFallbackResult();
        }

    }

}
