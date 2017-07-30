package com.corti.graphql.github;

import com.fasterxml.jackson.databind.JsonNode;
import com.corti.jsonutils.JsonUtils;

public class IssueDeserializer {
  private static final boolean DEBUG = true;

  // Create jsonUtils when the class is loaded
  private static final JsonUtils jsonUtils = new JsonUtils();

  private IssueDeserializer() { }

  public static synchronized Issue getIssueFromJsonNode(JsonNode issueNode) {
    // Create an issue object to store the values
    Issue issue = new Issue(); // Sets it as null

    if (issueNode != null) {
      try {
        issue.setIssueId(jsonUtils.getText(issueNode, "issueId"));
        issue.setRepositoryId(jsonUtils.getText(
            jsonUtils.getJsonNode(issueNode, "repository"), "repositoryId"));

        issue.setBodyText(jsonUtils.getText(issueNode, "bodyText"));
        issue.setClosed(jsonUtils.getBoolean(issueNode, "closed"));
        issue.setCreatedAt(jsonUtils.getTimestamp(issueNode, "createdAt"));
        issue
            .setLastEditedAt(jsonUtils.getTimestamp(issueNode, "lastEditedAt"));
        issue.setNumber(jsonUtils.getInt(issueNode, "number"));
        issue.setState(jsonUtils.getText(issueNode, "state"));
        issue.setTitle(jsonUtils.getText(issueNode, "title"));
        issue.setUrl(jsonUtils.getText(issueNode, "url"));
      } catch (Exception e) {
        e.printStackTrace();
      }
    } else {
      if (DEBUG)
        System.out.println("gitHubEdgeJsonNode is NOT an array");
    }
    return issue;
  }
}
