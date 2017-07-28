
// import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
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

import com.corti.graphql.GetRequestFromInputFile;
import com.corti.graphql.utils.*;
import com.corti.jsonutils.JsonUtils;
//import com.fasterxml.jackson.core.JsonFactory;
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.core.JsonParseException;
//import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.logging.*;
import com.corti.javalogger.LoggerUtils;

public class TestGraphQL {
  private static final boolean DEBUGIT = true;
  
  // Json object mapper
  private ObjectMapper objectMapper = null;
  
  // Common objects in the class
  private JsonUtils jsonUtils = null;
  private CloseableHttpClient client = null;
  private CloseableHttpResponse response = null;
  private String[] graphQLsDefined = null;
  
  private Logger logger = null;
  
  public TestGraphQL() {
    objectMapper = new ObjectMapper();
    jsonUtils = new JsonUtils();
    graphQLsDefined = GetRequestFromInputFile.getRef().getKeys();
    logger = (new LoggerUtils()).getLogger("GitHubLogger", "GitHubTestingLogger");
    logger.info("=================================================================");
    logger.info("Started, using GraphQL input: " + GetRequestFromInputFile.getRef().getFilePath().toString());
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    TestGraphQL testGraphQL = new TestGraphQL();
    
    String call2Make = "GetResiliencyOrgId";
    
    testGraphQL.callingIBMGraph(99,call2Make); //"IRPNewIssues"); //"ResiliencyRepos");
  }

  public void callingGraph() {
    CloseableHttpClient client = null;
    CloseableHttpResponse response = null;

    client = HttpClients.createDefault();
    HttpPost httpPost = new HttpPost("https://api.github.com/graphql");

    httpPost.addHeader("Authorization", "Bearer myToken");
    httpPost.addHeader("Accept", "application/json");

    String jsonString = "{query:{repository(owner: \"wso2-extensions\", name: \"identity-inbound-auth-oauth\") { object(expression: \"83253ce50f189db30c54f13afa5d99021e2d7ece\") { ... on Commit { blame(path: \"components/org.wso2.carbon.identity.oauth.endpoint/src/main/java/org/wso2/carbon/identity/oauth/endpoint/authz/OAuth2AuthzEndpoint.java\") { ranges { startingLine, endingLine, age, commit { message url history(first: 2) { edges { node {  message, url } } } author { name, email } } } } } } } }}";

    // JSONObject jsonObj = new JSONObject();
    // jsonobj.put("query", "{repository(owner: \"wso2-extensions\", name:
    // \"identity-inbound-auth-oauth\") { object(expression:
    // \"83253ce50f189db30c54f13afa5d99021e2d7ece\") { ... on Commit {
    // blame(path:
    // \"components/org.wso2.carbon.identity.oauth.endpoint/src/main/java/org/wso2/carbon/identity/oauth/endpoint/authz/OAuth2AuthzEndpoint.java\")
    // { ranges { startingLine, endingLine, age, commit { message url
    // history(first: 2) { edges { node { message, url } } } author { name,
    // email } } } } } } } }");

    try {
      // Not sure why the example did this
      JsonNode jsonNode = objectMapper.readValue(jsonString, JsonNode.class);
      String jsonOutString = objectMapper.writeValueAsString(jsonNode);
      StringEntity entity = new StringEntity(jsonOutString);

      httpPost.setEntity(entity);
      response = client.execute(httpPost);
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (ClientProtocolException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      BufferedReader reader = new BufferedReader(
          new InputStreamReader(response.getEntity().getContent()));
      String line = null;
      StringBuilder builder = new StringBuilder();
      while ((line = reader.readLine()) != null) {
        builder.append(line);
      }
      logger.info(builder.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void callingIBMGraph(int _calls2Make, String _graphQLKeyword) {
    // Get helper object that's using the ibm logon properties
    GitHubHelper ibmGitHubHelper = new GitHubHelper("ibmLogon.properties");
    
    // Define config
    RequestConfig globalConfig = RequestConfig.custom()
        .setCookieSpec(CookieSpecs.DEFAULT).build();

    // Setup client
    client = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();

    RequestConfig localConfig = RequestConfig.copy(globalConfig)
        .setCookieSpec(CookieSpecs.STANDARD) // CookieSpecs.STANDARD_STRICT)
        .build();

    HttpPost httpPost = new HttpPost(ibmGitHubHelper.getEndPoint());
    logger.info("EndPoint: " + httpPost.toString());
    
    httpPost.addHeader("Authorization", "bearer " + ibmGitHubHelper.getPAT());
    httpPost.addHeader("Accept", "application/json");

    httpPost.setConfig(localConfig);

    // Loop thru each of the graphQL objects and make the call (when method is
    // invoked caller passes in the number to process... this is just for testing :))
    for (int i = 0; i < Math.min(_calls2Make, graphQLsDefined.length); i++) {   
      String graphQLKeyWord = graphQLsDefined[i];
      if (!_graphQLKeyword.isEmpty()) {
        if (!graphQLKeyWord.equalsIgnoreCase(_graphQLKeyword)) {
          continue;  // They gave name and it doesn't match, skip
        }
      }
      
      logger.info("Making call for GraphQL keyword: " + graphQLKeyWord);
      
      // Get the GraphQL statement to satisfy the request
      String jsonValue = GetRequestFromInputFile.getRef()
          .getString4Key(graphQLKeyWord);
      
      // User doesn't define the query : part in the statement so we'll add it; down
      // the road make it more robust... maybe try to create a jsonNode for the 
      // string and if it fails then add the query (if passed user gave valid json
      // statement)
      String jsonString = jsonUtils.getJsonStringForKeyAndValue("query", jsonValue);
      
      if (DEBUGIT) logger.info("jsonValue:" + jsonValue + "\njsonString:" + jsonString);
      
      try {
        StringEntity entity = new StringEntity(jsonString);
        httpPost.setEntity(entity);
        response = client.execute(httpPost);
        
        if (DEBUGIT) logger.info("Response statusCode: " + response.getStatusLine().getStatusCode());
        
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(response.getEntity().getContent()));
        String line = null;
        StringBuilder builder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
          builder.append(line);
        }
        logger.info("Response below:");
        logger.info(jsonUtils.prettifyIt(builder.toString()));        
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      } catch (ClientProtocolException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }      
    }
  }

}