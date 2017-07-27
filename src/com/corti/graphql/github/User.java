package com.corti.graphql.github;

public class User {

  private String id;
  private String login;
  private String name;
  private String email;
  
  // Constructors  
  public User() {
    super();
    this.id = null;
    this.login = null;
    this.name = null;
    this.email = null;  
  }
  
  public User(String id, String login, String name, String email) {
    super();
    this.id = id;
    this.login = login;
    this.name = name;
    this.email = email;
  }
  
  // Getters and setters
  public String getId() {
    return id;
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
  public void setId(String id) {
    this.id = id;
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
    return "User [id=" + id + ", login=" + login + ", name=" + name + ", email="
        + email + "]";
  }    
}