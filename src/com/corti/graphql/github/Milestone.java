package com.corti.graphql.github;

import java.sql.Timestamp;

public class Milestone {
  private String id;
  private String repositoryId;
  private User creator;
  private String description;
  private Timestamp dueOn;
  private String state;
  private String title;  
  
  // Constructors 
  public Milestone() {
    super();
    this.id = null;
    this.repositoryId = null;
    this.creator = null;
    this.description = null;
    this.dueOn = null;
    this.state = null;
    this.title = null;
  }
  
  public Milestone(String id, String repositoryId, User creator, 
      String description, Timestamp dueOn, String state, String title) {
    super();
    this.id = id;
    this.repositoryId = repositoryId;
    this.creator = creator;
    this.description = description;
    this.dueOn = dueOn;
    this.state = state;
    this.title = title;
  }
  
  // Getters and setters
  public String getId() {
    return id;
  }
  public String getRepositoryId() {
    return repositoryId;
  }
  public User getCreator() {
    return creator;
  }
  public String getDescription() {
    return description;
  }
  public Timestamp getDueOn() {
    return dueOn;
  }
  public String getState() {
    return state;
  }
  public String getTitle() {
    return title;
  }
  public void setId(String id) {
    this.id = id;
  }
  public void setRepositoryId(String repositoryId) {
    this.repositoryId = repositoryId;
  }
  public void setCreator(User creator) {
    this.creator = creator;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public void setDueOn(Timestamp dueOn) {
    this.dueOn = dueOn;
  }
  public void setState(String state) {
    this.state = state;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  
  @Override
  public String toString() {
    return "Milestone [id=" + id + ", repositoryId=" + repositoryId 
        + ", creator=" + creator + ", description=" + description + ", dueOn=" 
        + dueOn + ", state=" + state + ", title="   + title + "]";
  }
}

