package com.corti.graphql.github.deserializers;

import com.corti.graphql.github.Labels;
import com.fasterxml.jackson.databind.JsonNode;

public class LabelsDeserializer {
  private static final boolean DEBUG = true;

  private LabelsDeserializer() { }

  public static synchronized Labels getLabelsFromJsonNode(JsonNode gitHubEdgeJsonNode) {
    Labels labels = new Labels();
    if (gitHubEdgeJsonNode != null && gitHubEdgeJsonNode.isArray()) {
      if (DEBUG) System.out.println("gitHubEdgeJsonNode is array, size: " + gitHubEdgeJsonNode.size());
      try {
        // Loop thru each node
        for (JsonNode arrayElementJsonNode : gitHubEdgeJsonNode) {

          // Get the node with the issue attributes
          JsonNode labelNode = arrayElementJsonNode.get("node");

          // Call issue deserializer and add it to the list
          labels.addLabel(LabelDeserializer.getLabelFromJsonNode(labelNode));
        }
      } catch (Exception e) {        
        e.printStackTrace();
      }
    } else {
      if (DEBUG) System.out.println("gitHubEdgeJsonNode is NOT an array");
    }
    return labels;
  }
}