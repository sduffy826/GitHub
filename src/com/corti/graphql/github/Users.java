package com.corti.graphql.github;

import java.util.ArrayList;
import java.util.List;

public class Users {
  private List<User> users;
  
  // Constructor
  public Users() {
    super();
    users = new ArrayList<User>(); 
  }

  // Get the issue list
  public List<User> geUsers() {
    return users;
  }

  // Add the issue passed in to the list
  public void addUser(User user) {
    users.add(user);
  }

  @Override
  public String toString() {
    String theString = "users = [ ";
    for (User user : users) {
      theString += user.toString();
    }
    theString += " ]";
    return theString;   
  }  
}
 