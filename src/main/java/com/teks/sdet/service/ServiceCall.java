package com.teks.sdet.service;

import com.teks.sdet.config.PropertyReader;
import com.teks.sdet.input.ConsoleInput;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class ServiceCall {
  
  private String url;
  private Response response;
  
  public ServiceCall() {
    url = new PropertyReader().getProperties().getProperty("service.url");
  }
  
  public void getResponse() {
    response =  given()
            .get(url)
            .then()
            .extract()
            .response();
  }
  
  public List<Map<String, Object>> extractResponse() {
    getResponse();
    if(response.statusCode() != 200)
      new ConsoleInput().getDataFromUserInput();
    JsonPath json = response.jsonPath();
    List<Map<String, Object>> list = response.jsonPath().getList("RestResponse.result");
    return list;
  }
}
