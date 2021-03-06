package com.corti.graphql.github;

import java.sql.Timestamp;
import java.util.List;

public class IssueComment {
  private String issueCommentId;
  private String issueId;
  
  private User author;
  private String bodyText;
  private Timestamp createdAt;
  private Timestamp lastEditedAt;
  
  // Constructors
  public IssueComment() {
    super();
    // TODO Auto-generated constructor stub
    this.issueCommentId = null;
    this.issueId = null;
    this.author = null;
    this.bodyText = null;
    this.createdAt = null;
    this.lastEditedAt = null;
  }
  
  public IssueComment(String issueCommentId, String issueId, User author, String bodyText,
      Timestamp createdAt, Timestamp lastEditedAt) {
    super();
    this.issueCommentId = issueCommentId;
    this.issueId = issueId;
    this.author = author;
    this.bodyText = bodyText;
    this.createdAt = createdAt;
    this.lastEditedAt = lastEditedAt;
  }
  
  // Getters and setters
  public String getIssueCommentId() {
    return issueCommentId;
  }
  public String getIssueId() {
    return issueId;
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
  public void setIssueCommentId(String issueCommentId) {
    this.issueCommentId = issueCommentId;
  }
  public void setIssueId(String issueId) {
    this.issueId = issueId;
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
    return "IssueComment [issueCommentId=" + issueCommentId + ", issueId=" + issueId + ", author="
        + author + ", bodyText=" + bodyText + ", createdAt=" + createdAt
        + ", lastEditedAt=" + lastEditedAt + "]";
  }
}
 