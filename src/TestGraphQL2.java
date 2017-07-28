// import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;

import com.corti.graphql.GetRequestFromInputFile;
import com.corti.jsonutils.JsonUtils;
//import com.fasterxml.jackson.core.JsonFactory;
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.core.JsonParseException;
//import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestGraphQL2 {
  private JsonUtils jsonUtils;
  // private static final boolean DEBUGIT = true;
  
  private static final String schema =
      "{ `query` : `{ __schema { types { name fields { name type { name } } } } }`}";
  private static final String repos = 
      "{ `query` : `query { viewer { repositories(first: 10) { edges { node { name description id updatedAt url } } } id name company email isHireable } }` }";
  
  private ObjectMapper objectMapper = null;
  private static final boolean DEBUGIT = true;

  public TestGraphQL2() {
    System.out.println(GetRequestFromInputFile.getRef().getKeys());
        
    objectMapper = new ObjectMapper();
    jsonUtils = new JsonUtils();
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    TestGraphQL2 testGraphQL = new TestGraphQL2();
    
    /*
    String aString = "`this is a test` and how are you";
    System.out.println(aString);
    System.out.println(aString.replaceAll("`","\""));
    System.out.println(aString);
    */
  //  testGraphQL.callingGraph();
  //  testGraphQL.callingIBMGraph();
  }
  public void callingGraph(){ 
    CloseableHttpClient client= null;
    CloseableHttpResponse response= null;

    client= HttpClients.createDefault();
    HttpPost httpPost= new HttpPost("https://api.github.com/graphql");

    httpPost.addHeader("Authorization","bearer xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
    httpPost.addHeader("Accept","application/json");
    
    // Original with escaped quotes
    String jsonString = "{ \"query\" : \"query { viewer { login }}\"}";
    
    // More readable attempt :)
    jsonString = "{ `query` : `{ viewer { login }}`}"; 
    jsonString = jsonString.replaceAll("`","\"");
    // jsonString = schema.replaceAll("`", "\"");
    jsonString = repos.replaceAll("`", "\"");
    
    
    //JSONObject jsonObj = new JSONObject();     
    //jsonobj.put("query", "{repository(owner: \"wso2-extensions\", name: \"identity-inbound-auth-oauth\") { object(expression: \"83253ce50f189db30c54f13afa5d99021e2d7ece\") { ... on Commit { blame(path: \"components/org.wso2.carbon.identity.oauth.endpoint/src/main/java/org/wso2/carbon/identity/oauth/endpoint/authz/OAuth2AuthzEndpoint.java\") { ranges { startingLine, endingLine, age, commit { message url history(first: 2) { edges { node {  message, url } } } author { name, email } } } } } } } }");

    try {
      // Not sure why the example did this
      JsonNode jsonNode = objectMapper.readValue(jsonString, JsonNode.class);
      String jsonOutString = objectMapper.writeValueAsString(jsonNode);
      StringEntity entity= new StringEntity(jsonOutString);

      System.out.println("jsonOutString:"+jsonOutString);
      httpPost.setEntity(entity);
      response= client.execute(httpPost);
      System.out.println("Response from client.execute: " + response.toString());
    }
    catch(UnsupportedEncodingException e){
      e.printStackTrace();
    }
    catch(ClientProtocolException e){
      e.printStackTrace();
    }
    catch(IOException e){
      e.printStackTrace();
    }

    try{
      BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
      String line= null;
      StringBuilder builder= new StringBuilder();
      while((line=reader.readLine())!= null) {
        System.out.println("Line is: " + line);
        builder.append(line);
      }
      System.out.println("Response here: " + builder.toString());
      System.out.println("\nPretty below\n" + jsonUtils.prettifyIt(builder.toString()));
    }
    catch(Exception e){
      e.printStackTrace();
    }
  }

  public void callingIBMGraph(){
    CloseableHttpClient client= null;
    CloseableHttpResponse response= null;

 // BasicClientCookie mycookie = new BasicClientCookie(null, null);
    
    RequestConfig globalConfig = RequestConfig.custom()
        .setCookieSpec(CookieSpecs.DEFAULT)
        .build();
      
    /* Old way
    client= HttpClients.createDefault();
    */
    client = HttpClients.custom()
        .setDefaultRequestConfig(globalConfig)
        .build();
    
    RequestConfig localConfig = RequestConfig.copy(globalConfig)
        .setCookieSpec(CookieSpecs.STANDARD)    // CookieSpecs.STANDARD_STRICT)
        .build();
        
    HttpPost httpPost= new HttpPost("https://github.ibm.com/api/graphql");

    httpPost.addHeader("Authorization","bearer yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy");
    httpPost.addHeader("Accept","application/json");

    httpPost.setConfig(localConfig);;

    String jsonString = "{ \"query\" : \"query { viewer { login }}\"}";
    
    jsonString = "{ `query` : `{ viewer { login }}`}"; 

    jsonString = jsonString.replaceAll("`","\"");
    jsonString = repos.replaceAll("`", "\"");
    jsonString = schema.replaceAll("`", "\"");
    
    
    //JSONObject jsonObj = new JSONObject();     
    //jsonobj.put("query", "{repository(owner: \"wso2-extensions\", name: \"identity-inbound-auth-oauth\") { object(expression: \"83253ce50f189db30c54f13afa5d99021e2d7ece\") { ... on Commit { blame(path: \"components/org.wso2.carbon.identity.oauth.endpoint/src/main/java/org/wso2/carbon/identity/oauth/endpoint/authz/OAuth2AuthzEndpoint.java\") { ranges { startingLine, endingLine, age, commit { message url history(first: 2) { edges { node {  message, url } } } author { name, email } } } } } } } }");

    try {
      // Not sure why the example did this
      JsonNode jsonNode = objectMapper.readValue(jsonString, JsonNode.class);
      String jsonOutString = objectMapper.writeValueAsString(jsonNode);
      StringEntity entity= new StringEntity(jsonOutString);
System.out.println("jsonOutString:"+jsonOutString);
      httpPost.setEntity(entity);
      response= client.execute(httpPost);
    }
    catch(UnsupportedEncodingException e){
      e.printStackTrace();
    }
    catch(ClientProtocolException e){
      e.printStackTrace();
    }
    catch(IOException e){
      e.printStackTrace();
    }

    try{
      BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
      String line= null;
      StringBuilder builder= new StringBuilder();
      while((line=reader.readLine())!= null) {
        builder.append(line);
      }
      System.out.println(builder.toString());
      System.out.println("\n\nPretty below\n" + jsonUtils.prettifyIt(builder.toString()));
    }
    catch(Exception e){
      e.printStackTrace();
    }
  }

  
  
  private String getLoginInfo() {
    return "";
  }
  
  
  
  
}