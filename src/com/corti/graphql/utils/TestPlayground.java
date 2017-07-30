package com.corti.graphql.utils;

import java.io.IOException;
import java.util.concurrent.Callable;

import com.corti.dateutils.Convert;
import com.corti.jsonutils.JsonUtils;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class TestPlayground {
  public TestPlayground() {  }
  public static void main(String[] args) {
    TestPlayground me = new TestPlayground();
    
    me.testLambda();
    me.testJsonNode();
  }
  
  public void testLambda() {
    Callable<String> callMe = () -> "Hi";
    try {
      System.out.println(callMe.call());
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }    
  }
  
  /*
  public void testJsonGetter(JsonNode jNode, String theField) {
    Callable<String> getTxt = (theNode,theField) -> { JsonNode aNode = theNode.get(theField);
                                                      return (aNode == null ? null : aNode.asText()) }
    }
  }
  */
  
  public void testJsonNode() {
    ObjectMapper mapper = new ObjectMapper();
  
    String jsonString = "{\"animal\":{\"type\":\"mammal\",\"species\":\"dog\"}}";
    
    // Get a jsonString representing the object
    try {      
      mapper.enable(SerializationFeature.INDENT_OUTPUT);
      
      JsonNode node = mapper.readValue(jsonString, JsonNode.class);
      System.out.println("The JsonNode");       
      System.out.println(mapper.writeValueAsString(node));
      
      // Show getter
      System.out.println(node.get("animal").get("type").asText());
      
      // Show how to use the utility class I wrote, it doesn't throw exception :) (returns null)
      JsonUtils jsonUtils = new JsonUtils();      
      System.out.println(jsonUtils.getText(jsonUtils.getJsonNode(node,"animal"),"type"));
      System.out.println(jsonUtils.getText(jsonUtils.getJsonNode(node,"nimal"),"type"));
    }
    catch (JsonGenerationException e1) { e1.printStackTrace(); }
    catch (IOException e2) { e2.printStackTrace(); }    
  }
}
