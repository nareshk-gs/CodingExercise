package com.teks.sdet.input;

import com.teks.sdet.validate.DataValidator;

import java.util.Scanner;

public class ConsoleInput {
  
  private DataValidator dataValidator;
  
  public ConsoleInput() {}
  
  public ConsoleInput(DataValidator dataValidator) {
    this.dataValidator = dataValidator;
  }
  
  public void getDataFromUserInput() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("****************************************");
    System.out.println("State name will be accepted in any case");
    System.out.println("State abbreviation code will be accepted in CAPS only");
    System.out.println("*******************************************************");
    System.out.println("Enter State : ");
    
    // Validate if input contains only alpha
    while(!scanner.hasNext("[a-zA-Z.]*")) {
      System.out.println("Not a valid state name or abbreviation. Try Again!!!");
      scanner.next();
    }
    
    dataValidator.validateState(scanner.nextLine());
    getDataFromUserInput();
  }
}
