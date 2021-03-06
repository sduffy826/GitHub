package com.corti.graphql.github.mainlines;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

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
import com.corti.graphql.github.deserializers.IssuesDeserializer;
import com.corti.jsonutils.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.logging.*;
import com.corti.javalogger.LoggerUtils;

public class SearchRepoIssues {
  private static final boolean DEBUGIT = true;

  // Common objects in the class
  private JsonUtils jsonUtils = null;
  private CloseableHttpClient client = null;
  private CloseableHttpResponse response = null;

  private Logger logger = null;

  private int issues2Get = 5;
  private String call2Make = "IRPNewIssues"; // "SearchQuery"; //
                                             // "IRPNewIssues";
  public SearchRepoIssues() {
    jsonUtils = new JsonUtils();

    logger = (new LoggerUtils()).getLogger("GitHubLogger","GitHubTestingLogger");
    logger.info("=================================================================");
    logger.info("Started, using GraphQL input: "
        + GetRequestFromInputFile.getRef().getFilePath().toString());
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    SearchRepoIssues searchRepoIssues = new SearchRepoIssues();
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
    String jsonString = jsonUtils.getJsonStringForKeyAndValue("query",gitHubGraphQLQuery);

    if (DEBUGIT)
      logger.info("jsonValue:" + gitHubGraphQLQuery + "\njsonString:" + jsonString);

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
      
      if (DEBUGIT) {
        logger.info("Response below:");
        logger.info(jsonUtils.prettifyIt(builder.toString()));
      }

      // Convert response body to json node
      JsonNode myNode = jsonUtils.getJsonNodeForJsonString(builder.toString());
      
      if (DEBUGIT) {
        System.out.println("Calling jsonUtils.dumpNode();");
        jsonUtils.dumpNode(myNode);
      }
      
      // Get the search json object, that's where the values we want are
      JsonNode searchNode = jsonUtils.getJsonNode(jsonUtils.getJsonNode(myNode,  "data"),"search");
         
      // ---
      JsonNode issueList = searchNode.get("edges");
      
      // Call method to return the issues deserialized
      Issues issues = IssuesDeserializer.getIssueFromJsonNode(issueList);
      
      // Write the results to screen
      System.out.println("\n\nissues.toString():" + issues.toString());     
      
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (ClientProtocolException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}