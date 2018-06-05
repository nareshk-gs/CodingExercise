package com.teks.sdet.validate;

import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

public class DataValidatorTest {
  
  private String url = "http://services.groupkt.com/state/get/USA/all";
  private Response response;
  
  
  @BeforeClass
  public void callService() {
    response = given().get(url).then().extract().response();
  }
  
  @DataProvider
  public Object[] names() {
    List<String> nameList = response.jsonPath().getList("RestResponse.result.name");
    return nameList.toArray();
  }
  
  @DataProvider
  public Object[] abbreviations() {
    List<String> nameList = response.jsonPath().getList("RestResponse.result.abbr");
    return nameList.toArray();
  }
  
  @DataProvider
  public Object[] assorted() {
    List<String> list = new ArrayList<>(
            Arrays.asList("ab", "PP", "1234", "<>?:", "...")
    );
    return list.toArray();
  }
  
  @Test (dataProvider = "names")
  public void validateNameTest(String name) {
  
  int id = new DataValidator(response.jsonPath().getList("RestResponse.result"))
                  .validateState(name);
  assertThat(id, is(greaterThan(0)));
  
  }
  
  @Test (dataProvider = "abbreviations")
  public void validateCodeTest(String code) {
    int id = new DataValidator(response.jsonPath().getList("RestResponse.result"))
            .validateState(code);
    assertThat(id, is(greaterThan(0)));
  }
  
  @Test (dataProvider = "assorted")
  public void validateFailures(String junk) {
    int id = new DataValidator(response.jsonPath().getList("RestResponse.result"))
            .validateState(junk);
    assertThat(id, is(equalTo(0)));
  }
  
}
