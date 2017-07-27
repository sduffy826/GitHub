package com.corti.graphql.github;

import java.sql.Timestamp;

public class Label {

  private String id;
  private String repositoryId;
  private String name;
  
  // Constructors  
  public Label() {
    super();
    this.id = null;
    this.repositoryId = null;
    this.name = null;      
  }
  
  public Label(String id, String name) {
    super();
    this.id = id;
    this.repositoryId = repositoryId;
    this.name = name;
  }
  
  // Getters and setters
  public String getId() {
    return id;
  }
  public String getRepositoryId() {
    return repositoryId;
  }
  public String getName() {
    return name;
  }
  public void setId(String id) {
    this.id = id;
  }
  public void setRepositoryId(String repositoryId) {
    this.repositoryId = repositoryId;
  }
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Label [id=" + id + ", repositoryId=" + repositoryId + ", name=" + name + "]";
  }    
}