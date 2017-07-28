package com.corti.graphql.github;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.corti.dateutils.Convert;

public class IssuesDeserializer {
  
  private JsonNode gitHubEdgeJsonNode; 
  
  private List<Issue> issues;
  
  public IssuesDeserializer(JsonNode jsonNode) {
    gitHubEdgeJsonNode = jsonNode;  
    issues = new ArrayList<Issue>();
    init();
  }

  public List<Issue> getIssues() {    
    return issues;
  }
  
  private void init() {
    if (gitHubEdgeJsonNode.isArray()) {
      try {
        // Loop thrue each node
        for (JsonNode arrayElementJsonNode : gitHubEdgeJsonNode) {
          
          // Get the node with the issue attributes
          JsonNode issueNode = arrayElementJsonNode.get("node");
          
          // Create an issue object to store the values
          Issue issue = new Issue();  // Sets it as null
          
          try {       
            issue.setIssueId(issueNode.get("issueId").asText());
            issue.setRepositoryId(issueNode.get("repository").get("repositoryId").asText());
            issue.setBodyText(issueNode.get("bodyText").asText());
            issue.setClosed(issueNode.get("closed").asBoolean());
            issue.setCreatedAt(  
              Convert.getTimestampFromISO8601String(issueNode.get("createdAt").asText()));
          }
          catch (Exception e) { }
          issues.add(issue);
        }
      }
      catch (Exception e) {
        e.printStackTrace();
      }
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
