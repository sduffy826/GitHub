package com.corti.graphql.github;

public class User {
  private String userId;
  private String login;
  private String name;
  private String email;
  
  // Constructors  
  public User() {
    super();
    this.userId = null;
    this.login = null;
    this.name = null;
    this.email = null;  
  }
  
  public User(String userId, String login, String name, String email) {
    super();
    this.userId = userId;
    this.login = login;
    this.name = name;
    this.email = email;
  }
  
  // Getters and setters
  public String getUserId() {
    return userId;
  }
  public String getLogin() {
    return login;
  }
  public String getName() {
    return name;
  }
  public String getEmail() {
    return email;
  }
  public void setUserId(String userId) {
    this.userId = userId;
  }
  public void setLogin(String login) {
    this.login = login;
  }
  public void setName(String name) {
    this.name = name;
  }
  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return "User [userId=" + userId + ", login=" + login + ", name=" + name + ", email="
        + email + "]";
  }    
}