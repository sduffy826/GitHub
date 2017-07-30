package com.corti.graphql.github.deserializers;

import com.corti.graphql.github.IssueComments;
import com.fasterxml.jackson.databind.JsonNode;

public class IssueCommentsDeserializer {
  private static final boolean DEBUG = true;

  private IssueCommentsDeserializer() { }

  public static synchronized IssueComments getIssueCommentsFromJsonNode(JsonNode gitHubEdgeJsonNode) {
    IssueComments issueComments = new IssueComments();
    if (gitHubEdgeJsonNode.isArray()) {
      if (DEBUG) System.out.println("gitHubEdgeJsonNode is array, size: " + gitHubEdgeJsonNode.size());
      try {
        // Loop thru each node
        for (JsonNode arrayElementJsonNode : gitHubEdgeJsonNode) {

          // Get the node with the issue attributes
          JsonNode issueCommentNode = arrayElementJsonNode.get("node");

          // Call issue deserializer and add it to the list
          issueComments.addIssueComment(IssueCommentDeserializer.getIssueCommentFromJsonNode(issueCommentNode));
        }
      } catch (Exception e) {        
        e.printStackTrace();
      }
    } else {
      if (DEBUG) System.out.println("gitHubEdgeJsonNode is NOT an array");
    }    
    return issueComments;
  }
}