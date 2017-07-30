package com.corti.graphql.github;

import java.util.ArrayList;
import java.util.List;

public class IssueComments {
  private List<IssueComment> issueComments;
  
  // Constructor
  public IssueComments() {
    super();
    issueComments = new ArrayList<IssueComment>(); 
  }

  // Get the issue list
  public List<IssueComment> getIssueComments() {
    return issueComments;
  }

  // Add the issue passed in to the list
  public void addIssueComment(IssueComment issueComment) {
    issueComments.add(issueComment);
  }

  @Override
  public String toString() {
    String theString = "issueComments = [ ";
    for (IssueComment anIssueComment : issueComments) {
      theString += anIssueComment.toString();
    }
    theString += " ]";
    return theString;   
  }  
}