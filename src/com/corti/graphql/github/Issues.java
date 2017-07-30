package com.corti.graphql.github;

import java.util.ArrayList;
import java.util.List;

public class Issues {
  private List<Issue> issues;
  
  // Constructor
  public Issues() {
    super();
    issues = new ArrayList<Issue>(); 
  }

  // Get the issue list
  public List<Issue> getIssues() {
    return issues;
  }

  // Add the issue passed in to the list
  public void addIssue(Issue issue) {
    issues.add(issue);
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
 