package com.corti.graphql.github.deserializers;

import com.corti.graphql.github.Issues;
import com.fasterxml.jackson.databind.JsonNode;

public class IssuesDeserializer {
  private static final boolean DEBUG = true;

  private IssuesDeserializer() { }

  public static synchronized Issues getIssueFromJsonNode(JsonNode gitHubEdgeJsonNode) {
    Issues issues = new Issues();
    if (gitHubEdgeJsonNode.isArray()) {
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
}
