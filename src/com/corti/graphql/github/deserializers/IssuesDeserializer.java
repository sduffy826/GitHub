package com.corti.graphql.github.deserializers;

import com.corti.graphql.github.Issues;
import com.fasterxml.jackson.databind.JsonNode;

public class IssuesDeserializer {
  private static final boolean DEBUG = true;

  private IssuesDeserializer() { }
  
  // Helper, this does the work on getting the issues
  private static Issues getIssuesHelper(JsonNode gitHubEdgeJsonNode, Issues issues) {
    if (gitHubEdgeJsonNode != null && gitHubEdgeJsonNode.isArray()) {
      if (DEBUG) System.out.println("gitHubEdgeJsonNode is array, size: " + gitHubEdgeJsonNode.size());
      try {
        // Loop thru each node
        for (JsonNode arrayElementJsonNode : gitHubEdgeJsonNode) {

          // Get the node with the issue attributes
          JsonNode issueNode = arrayElementJsonNode.get("node");

          // Call issue deserializer and add it to the list
          issues.addIssue(IssueDeserializer.getIssueFromJsonNode(issueNode));
        }
      } catch (Exception e) {        
        e.printStackTrace();
      }
    } else {
      if (DEBUG) System.out.println("gitHubEdgeJsonNode is NOT an array");
    }
    return issues;    
  }
  
  // Return a list of issues based on the json node
  public static synchronized Issues getIssueFromJsonNode(JsonNode gitHubEdgeJsonNode) {
    Issues issues = new Issues();
    return getIssuesHelper(gitHubEdgeJsonNode, issues);
  }
  
  // Append the issues from the json node to the issue list passed in
  public static synchronized Issues getIssueFromJsonNode(JsonNode gitHubEdgeJsonNode, Issues issues) {
    return getIssuesHelper(gitHubEdgeJsonNode, issues);
  }  
}
