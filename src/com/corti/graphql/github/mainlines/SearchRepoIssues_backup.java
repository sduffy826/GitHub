package com.corti.graphql.github.mainlines;

//import org.json.JSONObject;

import java.io.BufferedReader;
//import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import javax.net.ssl.HttpsURLConnection;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.corti.graphql.GetRequestFromInputFile;
import com.corti.graphql.utils.GitHubHelper;
import com.corti.graphql.github.Issues;
import com.corti.graphql.github.SearchQuery;
import com.corti.graphql.github.deserializers.IssuesDeserializer;
import com.corti.jsonutils.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.core.JsonFactory;
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.core.JsonParseException;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.logging.*;
import com.corti.javalogger.LoggerUtils;

public class SearchRepoIssues_backup {
  private static final boolean DEBUGIT = true;

  // Json object mapper
  private ObjectMapper objectMapper = null;

  // Common objects in the class
  private JsonUtils jsonUtils = null;
  private CloseableHttpClient client = null;
  private CloseableHttpResponse response = null;

  private Logger logger = null;

  private int issues2Get = 5;
  private String call2Make = "IRPNewIssues"; // "SearchQuery"; //
                                             // "IRPNewIssues";

  public SearchRepoIssues_backup() {
    objectMapper = new ObjectMapper();
    jsonUtils = new JsonUtils();

    logger = (new LoggerUtils()).getLogger("GitHubLogger",
        "GitHubTestingLogger");
    logger.info(
        "=================================================================");
    logger.info("Started, using GraphQL input: "
        + GetRequestFromInputFile.getRef().getFilePath().toString());
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    SearchRepoIssues_backup searchRepoIssues = new SearchRepoIssues_backup();
    searchRepoIssues.callingIBMGraph(searchRepoIssues.issues2Get,
        searchRepoIssues.call2Make);
  }

  /**
   * Make the call to IBM's github enterprise repo
   * 
   * @param _calls2Make
   * @param _call2Make
   */
  public void callingIBMGraph(int _numberToPull, String _call2Make) {
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

    String gitHubGraphQLQuery = GetRequestFromInputFile.getRef()
        .getString4Key(_call2Make);

    logger.info("Making call for GraphQL keyword: " + _call2Make);

    // User doesn't define the query : part in the statement so we'll add it;
    // down
    // the road make it more robust... maybe try to create a jsonNode for the
    // string and if it fails then add the query (if passed user gave valid json
    // statement)
    String jsonString = jsonUtils.getJsonStringForKeyAndValue("query",
        gitHubGraphQLQuery);

    if (DEBUGIT)
      logger.info(
          "jsonValue:" + gitHubGraphQLQuery + "\njsonString:" + jsonString);

    System.out.println(jsonUtils.prettifyIt(jsonString));
    System.out.println(gitHubGraphQLQuery);

    try {
      StringEntity entity = new StringEntity(jsonString); // gitHubGraphQLQuery);
                                                          // // jsonString);
      httpPost.setEntity(entity);
      response = client.execute(httpPost);

      if (DEBUGIT)
        logger.info(
            "Response statusCode: " + response.getStatusLine().getStatusCode());

      BufferedReader reader = new BufferedReader(
          new InputStreamReader(response.getEntity().getContent()));
      String line = null;
      StringBuilder builder = new StringBuilder();
      while ((line = reader.readLine()) != null) {
        builder.append(line);
      }
      logger.info("Response below:");
      logger.info(jsonUtils.prettifyIt(builder.toString()));

      JsonNode myNode = jsonUtils.getJsonNodeForJsonString(builder.toString());
      jsonUtils.dumpNode(myNode);

      JsonNode dataNode = myNode.get("data");
      JsonNode searchNode = dataNode.get("search");
      String searchNodeString = searchNode.toString();
      System.out.println("New: " + searchNodeString);

      // Create a java pojo from object
      // SearchQuery sq = objectMapper.readValue(searchNode, SearchQuery.class;

      SearchQuery sq2 = jsonUtils.getPojoFromJsonNode(searchNode,
          SearchQuery.class);
      System.out.println(sq2);

      System.out.println("DumpInfo");
      jsonUtils.dumpInfo(SearchQuery.class);
      System.out.println("DumpInfo done");
      
      // ---
      JsonNode issueList = searchNode.get("edges");
      
      // Call method to return the issues deserialized
      Issues issues = IssuesDeserializer.getIssueFromJsonNode(issueList);
      
      System.out.println("\n\ntoString:" + issues.toString());      
      
      // SearchQuery sq1 = objectMapper.readValue(searchNode,
      // SearchQuery.class);
      // System.out.println(myNode.asText());

    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (ClientProtocolException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}