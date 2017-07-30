package com.corti.graphql.github.deserializers;

import com.fasterxml.jackson.databind.JsonNode;
import com.corti.graphql.github.Issue;
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

        // We get the 'edges' node within the assignees node and pass that to the users 
        // deserializer, it returns a Users object
        issue.setAssignees(
            UsersDeserializer.getUsersFromJsonNode(
              jsonUtils.getJsonNode(jsonUtils.getJsonNode(issueNode,"assignees"),"edges")
            ));
        
        issue.setAuthor(UserDeserializer.getUserFromJsonNode(jsonUtils.getJsonNode(issueNode,"author")));
        
        issue.setBodyText(jsonUtils.getText(issueNode, "bodyText"));        
        issue.setClosed(jsonUtils.getBoolean(issueNode, "closed"));
        
        issue.setComments(IssueCommentsDeserializer.getIssueCommentsFromJsonNode(
            jsonUtils.getJsonNode(jsonUtils.getJsonNode(issueNode,"comments"),"edges")
           ));
        
        issue.setCreatedAt(jsonUtils.getTimestamp(issueNode, "createdAt"));
        issue.setEditor(UserDeserializer.getUserFromJsonNode(jsonUtils.getJsonNode(issueNode, "editor")));
        
        issue.setLabels(LabelsDeserializer.getLabelsFromJsonNode(
            jsonUtils.getJsonNode(jsonUtils.getJsonNode(issueNode,"labels"),"edges")
           ));
        
        issue.setLastEditedAt(jsonUtils.getTimestamp(issueNode, "lastEditedAt"));
        issue.setNumber(jsonUtils.getInt(issueNode, "number"));
        
        issue.setParticipants(
            UsersDeserializer.getUsersFromJsonNode(
              jsonUtils.getJsonNode(jsonUtils.getJsonNode(issueNode,"participants"),"edges")
            ));       
        
        issue.setState(jsonUtils.getText(issueNode, "state"));
        issue.setTitle(jsonUtils.getText(issueNode, "title"));
        issue.setUrl(jsonUtils.getText(issueNode, "url"));
      } catch (Exception e) { e.printStackTrace(); }
    } else {
      if (DEBUG) System.out.println("issueNode (argument passed in) is null");
    }
    return issue;
  }
}