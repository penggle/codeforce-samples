package com.penglecode.codeforce.samples.consumer.news.api.dto;

import com.penglecode.codeforce.common.model.BaseDTO;

/**
 * 新闻数据模型
 * 
 * @author 	pengpeng
 * @version 1.0
 */
public class NewsDTO implements BaseDTO {

	private static final long serialVersionUID = 1L;

	private String title;
	
	private String path;
	
	private String image;
	
	private String passtime;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getPasstime() {
		return passtime;
	}

	public void setPasstime(String passtime) {
		this.passtime = passtime;
	}
	
}
