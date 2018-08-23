package com.kedacom.cxf.controller;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 模拟远程调用
 * @author lihongguang
 * @date 2018年8月23日
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@RequestMapping("/getUser")
	public String getUser(String id) {
		HttpResponse response = doGet("http://127.0.0.1:8080/cxf/services/rest/userService/getUser/" + id);
		String result;
		try {
			result = EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			result = e.getMessage();
		}
		return result;
	}

	HttpResponse doGet(String url) {
		CloseableHttpResponse response = null;
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet get = new HttpGet(url);
		try {
			response = httpClient.execute(get);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
}
