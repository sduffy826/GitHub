package com.corti.graphql.github;

import java.sql.Timestamp;
import java.util.List;

public class Issue {
  private String id;
  private String repositoryId;
  private List<User> assignees;
  private User author;
  private String bodyText;
  private boolean closed;
  private List<IssueComment> comments;
  private Timestamp createdAt;
  private User editor;
  private List<Label> labels; 
  private Timestamp lastEditedAt;
  private Milestone mileStone;
  private int number;
  private List<User> participants;
  private String state;
  private String title;
  private String url;
  
  // Constructors
  public Issue() {
    super();

    this.id = null;
    this.repositoryId = null;
    this.assignees = null;
    this.author = null;
    this.bodyText = null;
    this.closed = false;
    this.comments = null;
    this.createdAt = null;
    this.editor = null;
    this.labels = null;
    this.lastEditedAt = null;
    this.mileStone = null;
    this.number = 0;
    this.participants = null;
    this.state = null;
    this.title = null;
    this.url = null; 
  }

  public Issue(String id, String repositoryId, List<User> assignees,
      User author, String bodyText, boolean closed, List<IssueComment> comments,
      Timestamp createdAt, User editor, List<Label> labels, Timestamp lastEditedAt,
      Milestone mileStone, int number, List<User> participants, String state,
      String title, String url) {
    super();
    this.id = id;
    this.repositoryId = repositoryId;
    this.assignees = assignees;
    this.author = author;
    this.bodyText = bodyText;
    this.closed = closed;
    this.comments = comments;
    this.createdAt = createdAt;
    this.editor = editor;
    this.labels = labels;
    this.lastEditedAt = lastEditedAt;
    this.mileStone = mileStone;
    this.number = number;
    this.participants = participants;
    this.state = state;
    this.title = title;
    this.url = url;
  }

  // Setters and getters
  public String getId() {
    return id;
  }

  public String getRepositoryId() {
    return repositoryId;
  }

  public List<User> getAssignees() {
    return assignees;
  }

  public User getAuthor() {
    return author;
  }

  public String getBodyText() {
    return bodyText;
  }

  public boolean isClosed() {
    return closed;
  }

  public List<IssueComment> getComments() {
    return comments;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }
  
  public User getEditor() {
    return editor;
  }

  public List<Label> getLabels() {
    return labels;
  }

  public Timestamp getLastEditedAt() {
    return lastEditedAt;
  }

  public Milestone getMileStone() {
    return mileStone;
  }

  public int getNumber() {
    return number;
  }

  public List<User> getParticipants() {
    return participants;
  }

  public String getState() {
    return state;
  }

  public String getTitle() {
    return title;
  }

  public String getUrl() {
    return url;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setRepositoryId(String repositoryId) {
    this.repositoryId = repositoryId;
  }

  public void setAssignees(List<User> assignees) {
    this.assignees = assignees;
  }

  public void setAuthor(User author) {
    this.author = author;
  }

  public void setBodyText(String bodyText) {
    this.bodyText = bodyText;
  }

  public void setClosed(boolean closed) {
    this.closed = closed;
  }

  public void setComments(List<IssueComment> comments) {
    this.comments = comments;
  }

  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }

  public void setEditor(User editor) {
    this.editor = editor;
  }
  
  public void setLabels(List<Label> labels) {
    this.labels = labels;
  }

  public void setLastEditedAt(Timestamp lastEditedAt) {
    this.lastEditedAt = lastEditedAt;
  }

  public void setMileStone(Milestone mileStone) {
    this.mileStone = mileStone;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public void setParticipants(List<User> participants) {
    this.participants = participants;
  }

  public void setState(String state) {
    this.state = state;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  @Override
  public String toString() {
    return "Issue [id=" + id + ", repositoryId=" + repositoryId + ", assignees="
        + assignees + ", author=" + author + ", bodyText=" + bodyText
        + ", closed=" + closed + ", comments=" + comments + ", createdAt="
        + createdAt + ", editor=" + editor + ", labels=" + labels
        + ", lastEditedAt=" + lastEditedAt + ", mileStone=" + mileStone 
        + ", number=" + number + ", participants=" + participants + ", state=" 
        + state + ", title=" + title + ", url=" + url + "]";
  } 
}
 