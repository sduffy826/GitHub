package com.corti.graphql.github.deserializers;

import com.corti.graphql.github.Users;
import com.fasterxml.jackson.databind.JsonNode;

public class UsersDeserializer {
  private static final boolean DEBUG = true;

  private UsersDeserializer() { }

  public static synchronized Users getUsersFromJsonNode(JsonNode gitHubEdgeJsonNode) {
    Users users = new Users();
    if (gitHubEdgeJsonNode.isArray()) {
      if (DEBUG) System.out.println("gitHubEdgeJsonNode is array, size: " + gitHubEdgeJsonNode.size());
      try {
        // Loop thru each node
        for (JsonNode arrayElementJsonNode : gitHubEdgeJsonNode) {

          // Get the node with the issue attributes
          JsonNode userNode = arrayElementJsonNode.get("node");

          // Call issue deserializer and add it to the list
          users.addUser(UserDeserializer.getUserFromJsonNode(userNode));
        }
      } catch (Exception e) {        
        e.printStackTrace();
      }
    } else {
      if (DEBUG) System.out.println("gitHubEdgeJsonNode is NOT an array");
    }
    return users;
  }
}