package com.penglecode.codeforce.samples.news.api.service;

import com.penglecode.codeforce.samples.news.api.dto.JokeDTO;
import com.penglecode.codeforce.samples.news.api.dto.OpenApiResult;
import com.penglecode.codeforce.samples.news.api.service.fallback.JokeApiServiceFallbackFactory;
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
@FeignClient(name="openapi", qualifiers="jokeApiService", contextId="jokeApiService", url="${feign.client.openapi.url}", fallbackFactory=JokeApiServiceFallbackFactory.class)
public interface JokeApiService {

	/**
	 * 根据id获取段子详情
	 * @param sid
	 * @return
	 */
	@GetMapping(value="/getSingleJoke", produces=MediaType.APPLICATION_JSON_VALUE)
	OpenApiResult<JokeDTO> getJokeById(@RequestParam(name="sid") String sid);

	/**
	 * 根据条件查询段子列表
	 * @param type
	 * @param page
	 * @param count
	 * @return
	 */
	@GetMapping(value="/getJoke", produces=MediaType.APPLICATION_JSON_VALUE)
	OpenApiResult<List<JokeDTO>> getJokeList(@RequestParam(name = "type", required = false) String type,
											 @RequestParam(name = "page", defaultValue = "1") Integer page,
											 @RequestParam(name = "count", defaultValue = "10") Integer count);
	
}
