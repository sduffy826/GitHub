public class TestProperties {

  /**
   * This is just a simple mainline program to test using a properties file; the GitHubHelper
   * does the work for us, I updated it after the merge conflict with the origin MOORE
   * @param args - Not used
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    
    System.out.println("Before");

    String personalAccessToken = GitHubHelper.getObj().getPAT();
    String organization = GitHubHelper.getObj().getProperty("ORGANIZATION");
    String userid = GitHubHelper.getObj().getProperty("USER");
    
    System.out.println("After");
    
    System.out.println("The PAT is: " + personalAccessToken + 
        " Organization: " + organization + 
        " User: " + userid);
  }
  // foo - another conflict
}
