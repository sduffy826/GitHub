package com.corti.graphql.github;

import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchQuery {

  // private String __typename;
  private int issueCount;
  private int codeCount;
  private int userCount;
  private int repositoryCount;
  
  public SearchQuery() {
    super();
    // this.__typename = null;
    this.issueCount = 0;
    this.codeCount = 0;
    this.userCount = 0;
    this.repositoryCount = 0;
  }
  
  public SearchQuery(String __typename, int issueCount, int codeCount,
      int userCount, int repositoryCount) {
    super();
    // this.__typename = __typename;
    this.issueCount = issueCount;
    this.codeCount = codeCount;
    this.userCount = userCount;
    this.repositoryCount = repositoryCount;
  }
    
 // public String get__typename() {
    // return __typename;
 // }
  public int getIssueCount() {
    return issueCount;
  }
  public int getCodeCount() {
    return codeCount;
  }
  public int getUserCount() {
    return userCount;
  }
  public int getRepositoryCount() {
    return repositoryCount;
  }
 // public void set__typename(String __typename) {
  //  this.__typename = __typename;
 // }
  public void setIssueCount(int issueCount) {
    this.issueCount = issueCount;
  }
  public void setCodeCount(int codeCount) {
    this.codeCount = codeCount;
  }
  public void setUserCount(int userCount) {
    this.userCount = userCount;
  }
  public void setRepositoryCount(int repositoryCount) {
    this.repositoryCount = repositoryCount;
  }
  
  
  @Override
  public String toString() {
   // return "SearchQuery [__typename=" + __typename + ", issueCount="
   //     + issueCount + ", codeCount=" + codeCount + ", userCount=" + userCount
   //     + ", repositoryCount=" + repositoryCount + "]";
    return "";
  }
  
  
  
  
}
