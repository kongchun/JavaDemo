package com.kedacom.cxf.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.kedacom.cxf.model.User;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.RequestBody;
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
		String result;
		HttpResponse response = doGet("http://127.0.0.1:8080/cxf/services/rest/userService/getUser/" + id);
		try {
			result = EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			result = e.getMessage();
		}
		return result;
	}

	@RequestMapping("/putUser")
	public String putUser(String id, String name){
		String result;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		params.add(new BasicNameValuePair("name", name));
		HttpResponse response = doPut("http://127.0.0.1:8080/cxf/services/rest/userService/putUser/", params);
		try {
			result = "Status: "+response.getStatusLine().getStatusCode();
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

	HttpResponse doPut(String url, List<NameValuePair> params) {
		CloseableHttpResponse response = null;
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpPut put = new HttpPut(url);
		try {
			put.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			response = httpClient.execute(put);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
}
