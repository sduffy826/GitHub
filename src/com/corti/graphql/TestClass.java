package com.corti.graphql;

import com.corti.jsonutils.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;

public class TestClass {

  public static void main(String[] args) {
    String key2Check = "GetSchema";
    JsonUtils jsonUtils = new JsonUtils();
    
    // Call various method on the utility
    System.out.println("All keys as a string below");
    System.out.println(GetRequestFromInputFile.getRef().getListOfKeys());
    
    // Get all the keys as an array
    System.out.println("Keys returned in array");
    String[] keyList = GetRequestFromInputFile.getRef().getKeys();
    for (String aKey : keyList) {
      System.out.println("aKey: " + aKey);
    }
    
    // Show the data as represented in the input file (the array of strings)    
    System.out.println(key2Check + " as Array");
    for (String aLine : GetRequestFromInputFile.getRef().getArray4Key(key2Check)) {
      System.out.println(aLine);
    }    

    // Show as a string
    System.out.println(key2Check + " as String");
    System.out.println(GetRequestFromInputFile.getRef().getString4Key(key2Check));
   
    // Convert to json object
    String jsonString = GetRequestFromInputFile.getRef().getString4Key(key2Check);
    JsonNode jsonNode = jsonUtils.getJsonNodeForKeyAndValue("query",jsonString);
    // Show it in pretty format
    System.out.println(jsonUtils.prettifyIt(jsonNode));    
  }

}
