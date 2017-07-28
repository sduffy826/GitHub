package com.corti.graphql;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GetRequestFromInputFile {
  private static boolean DEBUGIT = false;
  private static final String input = "GraphQL.input";
  private Path filePath = null;
  private Map<String, String[]> inputMap;

  private static GetRequestFromInputFile getRequestFromInputFile;

  private GetRequestFromInputFile() {
    inputMap = new HashMap<String, String[]>();
    Charset charset = Charset.forName("US-ASCII");

    String inputFile = "./" + input;

    String line = null;
    String lastKey = "";
    ArrayList<String> dataLines = new ArrayList<String>();

    try {
      filePath = Paths.get(inputFile);
        
      BufferedReader reader = Files.newBufferedReader(filePath, charset);
      while ((line = reader.readLine()) != null) {
        if (DEBUGIT)
          System.out.println("Read: " + line);
        
        // Strip comments out (everything from # to eol)
        line = line.split("#")[0];        
        
        if (line.startsWith("@")) {
          if (lastKey.length() > 0 && dataLines.size() > 0) {
            String theList[] = new String[dataLines.size()];
            theList = dataLines.toArray(theList);
            inputMap.put(lastKey, theList);
            if (DEBUGIT) System.out.println("Added: " + lastKey + " to map");
          }
          lastKey = line.substring(1).trim();
          dataLines.clear();
          if (DEBUGIT) System.out.println("Processing new key: " + lastKey);
        } 
        else 
          if (!line.isEmpty()) {
            dataLines.add(line);
          }
      }
      if (lastKey.length() > 0 && dataLines.size() > 0) {
        String theList[] = new String[dataLines.size()];
        theList = dataLines.toArray(theList);
        inputMap.put(lastKey, theList);
        if (DEBUGIT) System.out.println("Added: " + lastKey + " to map");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static GetRequestFromInputFile getRef() {
    if (getRequestFromInputFile == null) {
      getRequestFromInputFile = new GetRequestFromInputFile();
    }
    return getRequestFromInputFile;
  }

  // Return the values for the given key
  public String[] getArray4Key(String _theKey) {
    return inputMap.get(_theKey);
  }

  // Return the value as a string
  public String getString4Key(String _theKey) {
    String rtnString = "";
    for (String line : getArray4Key(_theKey)) {
      rtnString += line.trim() + " ";
    }
    return rtnString;
  }

  // Return all the keys as a string
  public String[] getKeys() {
    // Convert key set to array of strings
    String[] theKeys = inputMap.keySet().toArray(new String[inputMap.size()]);
    return theKeys;
  }

  // Return all the keys as a string
  public String getListOfKeys() {
    String rtnString = "";
    for (String theKey : inputMap.keySet()) {
      rtnString += ", " + theKey;
    }
    return rtnString.replaceFirst(",", " ").trim();
  }

  public Path getFilePath() {
    return filePath;
  }
}
