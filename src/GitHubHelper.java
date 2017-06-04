import java.io.IOException;

/**
 * Helper class, has some common utility methods
 * @author sduffy *
 */
public class GitHubHelper {
  private static final boolean DEBUG = true;
  private java.util.Properties logonProperties;
  private static GitHubHelper gitHubHelper;
  
  // Restrict instantiation so can only be done locally
  private GitHubHelper() {
    logonProperties = new java.util.Properties();
    try {
      String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
      String propertiesFile = rootPath + "logon.properties";
      if (DEBUG) System.out.println(rootPath + " <- rootpath");
      
      java.io.FileInputStream fis = new java.io.FileInputStream(propertiesFile);
      logonProperties.load(fis);
      fis.close();      
    }
    catch (IOException io) { io.printStackTrace();  }   
    if (DEBUG) System.out.println("PAT is: " + getPAT());
  }
  
  // Get reference to the singleton object
  public static GitHubHelper getObj() {
    if (gitHubHelper == null) {
      gitHubHelper = new GitHubHelper();
    }
    return gitHubHelper;
  }
  
  // Return the property passed in.
  public String getProperty(String _property) {
    return logonProperties.getProperty(_property);  
  }
  
  // Return the personal access token (also called PAT)
  public String getPAT() { return getProperty("PAT"); }
  
}
