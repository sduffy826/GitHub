package com.corti.graphql.github.deserializers;

import com.fasterxml.jackson.databind.JsonNode;
import com.corti.graphql.github.Label;
import com.corti.jsonutils.JsonUtils;

public class LabelDeserializer {
  private static final boolean DEBUG = true;

  // Create jsonUtils when the class is loaded
  private static final JsonUtils jsonUtils = new JsonUtils();

  private LabelDeserializer() { }

  public static synchronized Label getLabelFromJsonNode(JsonNode labelNode) {
    // Create an user object to store the values
    Label label = new Label(); // Sets it as null

    if (labelNode != null) {
      try {
        label.setLabelId(jsonUtils.getText(labelNode, "labelId"));
        label.setName(jsonUtils.getText(labelNode, "name"));
        label.setRepositoryId(
            jsonUtils.getText(jsonUtils.getJsonNode(labelNode, "repository"),"repositoryId"));
      } catch (Exception e) {
        e.printStackTrace();
      }
    } else {
      if (DEBUG) System.out.println("labelNode (argument passed in) is null");
    }
    return label;
  }
}