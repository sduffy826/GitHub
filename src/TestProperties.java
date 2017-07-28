import com.corti.graphql.utils.*;
public class TestProperties {

  /**
   * This is just a simple mainline program to test using a properties file; the GitHubHelper
   * does the work for us.
   * @param args - Not used
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    
    System.out.println("Before");

    GitHubHelper[] gitHubHelper = new GitHubHelper[2];
    gitHubHelper[0]= new GitHubHelper();
    gitHubHelper[1]= new GitHubHelper("ibmLogon.properties");
    
    for (GitHubHelper gHH : gitHubHelper) {
      System.out.println("\n\nENDPOINT " + gHH.getEndPoint());
      System.out.println("PAT " + gHH.getPAT());
      System.out.println("ORGANIZATION " + gHH.getProperty("ORGANIZATION"));
      System.out.println("USER " + gHH.getProperty("USER"));
    }    
    System.out.println("After");
    
  }
  // foo - another conflict
}
