package com.corti.graphql.github;

import java.sql.Timestamp;
import java.util.List;

public class IssueComment {
  private String id;
  private String issuedId;
  
  private User author;
  private String bodyText;
  private Timestamp createdAt;
  private Timestamp lastEditedAt;
  
  // Constructors
  public IssueComment() {
    super();
    // TODO Auto-generated constructor stub
    this.id = null;
    this.issuedId = null;
    this.author = null;
    this.bodyText = null;
    this.createdAt = null;
    this.lastEditedAt = null;
  }
  
  public IssueComment(String id, String issuedId, User author, String bodyText,
      Timestamp createdAt, Timestamp lastEditedAt) {
    super();
    this.id = id;
    this.issuedId = issuedId;
    this.author = author;
    this.bodyText = bodyText;
    this.createdAt = createdAt;
    this.lastEditedAt = lastEditedAt;
  }
  
  // Getters and setters
  public String getId() {
    return id;
  }
  public String getIssuedId() {
    return issuedId;
  }
  public User getAuthor() {
    return author;
  }
  public String getBodyText() {
    return bodyText;
  }
  public Timestamp getCreatedAt() {
    return createdAt;
  }
  public Timestamp getLastEditedAt() {
    return lastEditedAt;
  }
  public void setId(String id) {
    this.id = id;
  }
  public void setIssuedId(String issuedId) {
    this.issuedId = issuedId;
  }
  public void setAuthor(User author) {
    this.author = author;
  }
  public void setBodyText(String bodyText) {
    this.bodyText = bodyText;
  }
  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }
  public void setLastEditedAt(Timestamp lastEditedAt) {
    this.lastEditedAt = lastEditedAt;
  }
  
  @Override
  public String toString() {
    return "IssueComment [id=" + id + ", issuedId=" + issuedId + ", author="
        + author + ", bodyText=" + bodyText + ", createdAt=" + createdAt
        + ", lastEditedAt=" + lastEditedAt + "]";
  }
}
 