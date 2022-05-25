package com.penglecode.codeforce.samples.news.api.dto;

import com.penglecode.codeforce.common.model.Result;
import org.springframework.http.HttpStatus;

/**
 * 接口结果数据模型
 *
 * @author 	pengpeng
 * @version 1.0
 */
public class OpenApiResult<T> {

	private int code;
	
	private String message;
	
	private T result;

	public OpenApiResult() {
	}

	public OpenApiResult(int code, String message, T result) {
		this.code = code;
		this.message = message;
		this.result = result;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public Result<T> toResult() {
		HttpStatus httpStatus = HttpStatus.resolve(code);
		if(httpStatus != null && httpStatus.is2xxSuccessful()) {
			return Result.success().code(String.valueOf(code)).message(message).data(result).build();
		} else {
			return Result.failure().code(String.valueOf(code)).message(message).data(result).build();
		}
	}
	
}