package com.corti.graphql.github.deserializers;

import com.fasterxml.jackson.databind.JsonNode;
import com.corti.graphql.github.User;
import com.corti.jsonutils.JsonUtils;

public class UserDeserializer {
  private static final boolean DEBUG = true;

  // Create jsonUtils when the class is loaded
  private static final JsonUtils jsonUtils = new JsonUtils();

  private UserDeserializer() { }

  public static synchronized User getUserFromJsonNode(JsonNode userNode) {
    // Create an user object to store the values
    User user = new User(); // Sets it as null

    if (userNode != null) {
      try {
        user.setUserId(jsonUtils.getText(userNode, "userId"));
        user.setLogin(jsonUtils.getText(userNode, "login"));
        user.setName(jsonUtils.getText(userNode, "name"));
        user.setEmail(jsonUtils.getText(userNode, "email"));
      } catch (Exception e) {
        e.printStackTrace();
      }
    } else {
      if (DEBUG) System.out.println("userNode (argument passed in) is null");
    }
    return user;
  }
}