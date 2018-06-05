package com.teks.sdet.validate;

import com.sun.org.apache.xerces.internal.xs.StringList;

import java.util.List;
import java.util.Map;

public class DataValidator {
  
  List<Map<String, Object>> stateList;
  String pattern = "[A-Z]{2}";
  
  public DataValidator() {
  
  }
  
  public DataValidator(List<Map<String, Object>> stateList) {
    this.stateList = stateList;
  }
  
  public int validateState(String state) {
    System.out.println("==============================================");
    System.out.println("For the state: " + state);
    if(state.matches(pattern)) {
      return validateCode(state);
    } else {
      return validateName(state);
    }
  }
  
  private int validateName(String name) {
    int i = 0;
    int id = 0;
    for(Map<String, Object> state : stateList) {
      if(name.equalsIgnoreCase(String.valueOf(state.get("name")))) {
        System.out.println("Largest City is " + state.getOrDefault("largest_city", "<Data doesn't exist>"));
        System.out.println("Capital City is " + state.get("capital"));
        id = Integer.valueOf(state.get("id").toString());
        i++;
        break;
      }
    }
    if(i == 0) {
      System.out.println("State doesn't exist with name: " + name);
    }
    return id;
  }
  
  private int validateCode(String code) {
    int i = 0;
    int id = 0;
    for(Map<String, Object> state : stateList) {
      if(code.equals(String.valueOf(state.get("abbr")))) {
        System.out.println("Largest City is " + state.getOrDefault("largest_city", "<Data doesn't exist>"));
        System.out.println("Capital City is " + state.get("capital"));
        id = Integer.valueOf(state.get("id").toString());
        i++;
        break;
      }
    }
    if(i == 0) {
      System.out.println("State doesn't exist with abbreviation: " + code);
    }
    return id;
  }
}
