package com.corti.graphql.utils;
import java.io.IOException;

/**
 * Helper class, has some common utility methods
 * @author sduffy *
 */
public class LoginProperties {
  private static final boolean DEBUG = false;
  private java.util.Properties properties;
  private String propertiesFile = "logon.properties";  // Default
  
  // Restrict instantiation so can only be done locally
  public LoginProperties() {
    propertiesFile = "logon.properties";
    initIt();
  }
  
  public LoginProperties(String _propertiesFile) {
    propertiesFile = _propertiesFile;
    initIt();
  }
  
  private void initIt() {
    properties = new java.util.Properties();
    try {
      String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
      String propFileAndPath = rootPath + propertiesFile;
      if (DEBUG) System.out.println(rootPath + " <- rootpath");
      
      java.io.FileInputStream fis = new java.io.FileInputStream(propFileAndPath);
      properties.load(fis);
      fis.close();      
    }
    catch (IOException io) { io.printStackTrace();  }   
    if (DEBUG) System.out.println("PAT is: " + getPAT());
  }
  
  // Return the property passed in.
  public String getProperty(String _property) {
    return properties.getProperty(_property);  
  }
  
  // Return the personal access token (also called PAT)
  public String getPAT() { return getProperty("PAT"); }
   
 // Return the URL (also called endpoint)
 public String getEndPoint() { return getProperty("ENDPOINT"); }
 public String getURL() { return getEndPoint(); }  
  
}
