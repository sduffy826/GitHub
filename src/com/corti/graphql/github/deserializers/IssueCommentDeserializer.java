package com.corti.graphql.github.deserializers;

import com.fasterxml.jackson.databind.JsonNode;

import java.sql.Timestamp;

import com.corti.graphql.github.IssueComment;
import com.corti.jsonutils.JsonUtils;

public class IssueCommentDeserializer {
  private static final boolean DEBUG = true;

  // Create jsonUtils when the class is loaded
  private static final JsonUtils jsonUtils = new JsonUtils();

  private IssueCommentDeserializer() { }

  public static synchronized IssueComment getIssueCommentFromJsonNode(
      JsonNode issueCommentNode) {

    // Create object to store the values
    IssueComment issueComment = new IssueComment(); // Sets it as null

    if (issueCommentNode != null) {
      try {
        issueComment.setIssueCommentId(
            jsonUtils.getText(issueCommentNode, "issueCommentId"));
        
        // Get issue node, then it's id
        issueComment.setIssueId(
            jsonUtils.getText(jsonUtils.getJsonNode(issueCommentNode, "issue"),"issueId"));
        
        issueComment.setAuthor(UserDeserializer.getUserFromJsonNode(
            jsonUtils.getJsonNode(issueCommentNode, "author")));
        issueComment
            .setBodyText(jsonUtils.getText(issueCommentNode, "bodyText"));
        issueComment.setCreatedAt(
            jsonUtils.getTimestamp(issueCommentNode, "createdAt"));
        issueComment.setLastEditedAt(
            jsonUtils.getTimestamp(issueCommentNode, "lastEditedAt"));
      } catch (Exception e) {
        e.printStackTrace();
      }
    } else {
      if (DEBUG)
        System.out.println("issueCommentNode (argument passed in) is null");
    }
    return issueComment;
  }
}