package com.corti.graphql.github;

import java.util.ArrayList;
import java.util.List;

public class Labels {
  private List<Label> labels;
  
  // Constructor
  public Labels() {
    super();
    labels = new ArrayList<Label>(); 
  }

  // Get the issue list
  public List<Label> getLabels() {
    return labels;
  }

  // Add the issue passed in to the list
  public void addLabel(Label label) {
    labels.add(label);
  }

  @Override
  public String toString() {
    String theString = "labels = [ ";
    for (Label label : labels) {
      theString += label.toString();
    }
    theString += " ]";
    return theString;   
  }  
} 