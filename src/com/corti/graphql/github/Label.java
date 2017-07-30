package com.corti.graphql.github;

import java.sql.Timestamp;

public class Label {

  private String labelId;
  private String repositoryId;
  private String name;
  
  // Constructors  
  public Label() {
    super();
    this.labelId = null;
    this.repositoryId = null;
    this.name = null;      
  }
  
  public Label(String labelId, String name) {
    super();
    this.labelId = labelId;
    this.repositoryId = repositoryId;
    this.name = name;
  }
  
  // Getters and setters
  public String getLabelId() {
    return labelId;
  }
  public String getRepositoryId() {
    return repositoryId;
  }
  public String getName() {
    return name;
  }
  public void setLabelId(String labelId) {
    this.labelId = labelId;
  }
  public void setRepositoryId(String repositoryId) {
    this.repositoryId = repositoryId;
  }
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Label [labelId=" + labelId + ", repositoryId=" + repositoryId + ", name=" + name + "]";
  }    
}