package com.teks.sdet;

import com.teks.sdet.input.ConsoleInput;
import com.teks.sdet.service.ServiceCall;
import com.teks.sdet.validate.DataValidator;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CodingExerciseApplication {
  
  public static DataValidator dataValidator;
  
  public static void main(String[] args) {
    dataValidator = new DataValidator(new ServiceCall().extractResponse());
    new ConsoleInput(dataValidator).getDataFromUserInput();
  }
}
