package com.teks.sdet.config;

import java.io.*;
import java.util.Properties;

public class PropertyReader {
  
  private Properties properties = new Properties();;
  
  public PropertyReader() {
    String propertyFile = System.getProperty("user.dir")
                          + File.separator + "src" + File.separator
                          + "main" + File.separator + "resources"
                          + File.separator + "project.properties";
    try {
      InputStream input = new FileInputStream(new File(propertyFile));
      properties.load(input);
    } catch (FileNotFoundException fnfe) {
      fnfe.getMessage();
    } catch (IOException ioe) {
      ioe.getMessage();
    }
  }
  
  public Properties getProperties() {
    return properties;
  }
}
