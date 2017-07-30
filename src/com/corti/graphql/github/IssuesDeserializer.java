package com.corti.graphql.github;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.corti.dateutils.Convert;
import com.corti.jsonutils.JsonUtils;

public class IssuesDeserializer {
  private static final boolean DEBUG = true;
  private JsonNode gitHubEdgeJsonNode; 
  private JsonUtils jsonUtils;
  
  private List<Issue> issues;
  
  public IssuesDeserializer(JsonNode jsonNode) {
    gitHubEdgeJsonNode = jsonNode;  
    jsonUtils = new JsonUtils();
    issues = new ArrayList<Issue>();
    init();
  }

  public List<Issue> getIssues() {    
    return issues;
  }
  
  private void init() {
    if (gitHubEdgeJsonNode.isArray()) {
      if (DEBUG) System.out.println("gitHubEdgeJsonNode is array, size: " + gitHubEdgeJsonNode.size());
      try {
        // Loop thru each node
        for (JsonNode arrayElementJsonNode : gitHubEdgeJsonNode) {
          
          // Get the node with the issue attributes
          JsonNode issueNode = arrayElementJsonNode.get("node");
          
          issues.add(IssueDeserializer.getIssueFromJsonNode(issueNode));
          
          /*
          // Create an issue object to store the values
          Issue issue = new Issue();  // Sets it as null
          
          try {      
            issue.setIssueId(jsonUtils.getText(issueNode, "issueId"));
            issue.setRepositoryId(
                jsonUtils.getText(jsonUtils.getJsonNode(issueNode,"repository"),"repositoryId"));
            
            issue.setBodyText(jsonUtils.getText(issueNode,"bodyText"));
            issue.setClosed(jsonUtils.getBoolean(issueNode,"closed"));
            issue.setCreatedAt(jsonUtils.getTimestamp(issueNode, "createdAt"));
            issue.setLastEditedAt(jsonUtils.getTimestamp(issueNode, "lastEditedAt"));
            issue.setNumber(jsonUtils.getInt(issueNode,"number"));
            issue.setState(jsonUtils.getText(issueNode,"state"));
            issue.setTitle(jsonUtils.getText(issueNode,"title"));
            issue.setUrl(jsonUtils.getText(issueNode,"url"));
               
            issue.setIssueId(issueNode.get("issueId").asText());
            issue.setRepositoryId(issueNode.get("repository").get("repositoryId").asText());
            issue.setBodyText(issueNode.get("bodyText").asText());
            issue.setClosed(issueNode.get("closed").asBoolean());
            issue.setCreatedAt(  
              Convert.getTimestampFromISO8601String(issueNode.get("createdAt").asText()));
            issue.setLastEditedAt(  
                Convert.getTimestampFromISO8601String(issueNode.get("lastEditedAt").asText()));
            issue.setNumber(issueNode.get("number").asInt());
            issue.setState(issueNode.get("state").asText());
            issue.setTitle(issueNode.get("title").asText());
            issue.setUrl(issueNode.get("url").asText());
                        
          }
          catch (Exception e) { e.printStackTrace(); }
          issues.add(issue); */
        }
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }
    else {
      if (DEBUG) System.out.println("gitHubEdgeJsonNode is NOT an array");
    }
  }
  
  @Override
  public String toString() {
    String theString = "issues = [ ";
    for (Issue anIssue : issues) {
      theString += anIssue.toString();
    }
    theString += " ]";
    return theString;   
  }
  
  
  
  
}
