package com.penglecode.codeforce.samples.consumer.news.api.service;

import com.penglecode.codeforce.samples.consumer.news.api.dto.NewsDTO;
import com.penglecode.codeforce.samples.consumer.news.api.dto.OpenApiResult;
import com.penglecode.codeforce.samples.consumer.news.api.service.fallback.NewsApiServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 基于https://api.apiopen.top/api.html上的开放API接口
 * 
 * @author 	pengpeng
 * @version 1.0
 */
@FeignClient(name="openapi", qualifiers="newsApiService", contextId="newsApiService", url="${feign.client.openapi.url}", fallbackFactory=NewsApiServiceFallbackFactory.class)
public interface NewsApiService {

	/**
	 * 根据条件查询新闻列表
	 * @param page
	 * @param count
	 * @return
	 */
	@GetMapping(value="/getWangYiNews", produces=MediaType.APPLICATION_JSON_VALUE)
	OpenApiResult<List<NewsDTO>> getNewsList(
			@RequestParam(name = "page", defaultValue = "1") Integer page,
			@RequestParam(name = "count", defaultValue = "10") Integer count);
	
}
