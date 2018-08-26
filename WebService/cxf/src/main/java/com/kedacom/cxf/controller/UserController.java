package com.kedacom.cxf.controller;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kedacom.cxf.model.User;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 模拟远程调用
 * @author lihongguang
 * @date 2018年8月23日
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value="/getUser", method=RequestMethod.GET)
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

    @RequestMapping(value="/putUser", method=RequestMethod.PUT)
    public String putUser(@RequestBody User user){
        String result;
        try {
            String params = new ObjectMapper().writeValueAsString(user);
            HttpResponse response = doPut("http://127.0.0.1:8080/cxf/services/rest/userService/putUser/", params);
            result = "Status: "+response.getStatusLine().getStatusCode();
        } catch (Exception e) {
            result = e.getMessage();
        }
        return result;
    }

    @RequestMapping(value="/deleteUser", method=RequestMethod.DELETE)
    public String deleteUser(String id) {
        String result;
        HttpResponse response = doDelete("http://127.0.0.1:8080/cxf/services/rest/userService/deleteUser/" + id);
        try {
            result = "Status: "+response.getStatusLine().getStatusCode();
        } catch (Exception e) {
            result = e.getMessage();
        }
        return result;
    }

    @RequestMapping(value="/getLogs", method=RequestMethod.GET)
    public String getLogs() {
        String result;
        HttpResponse response = doGet("http://127.0.0.1:8080/cxf/services/rest/userService/getLogs/");
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

    HttpResponse doPut(String url, String params) {
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPut put = new HttpPut(url);
        try {
            StringEntity entity = new StringEntity(params);
            entity.setContentEncoding("UTF-8");
			entity.setContentType("application/json");
            put.setEntity(entity);
            response = httpClient.execute(put);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    HttpResponse doDelete(String url) {
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpDelete delete = new HttpDelete(url);
        try {
            response = httpClient.execute(delete);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
