import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;


public class GitHubRepo {
  private static final boolean DEBUG = false;
  private final String USER_AGENT = "Mozilla/5.0";
  public static void main(String[] args) throws Exception {
    GitHubRepo http = new GitHubRepo();

    System.out.println("Testing 1 - Send Http GET request");
    http.sendGet();
  }
  // dumb line added here :)
  // HTTP GET request
  private void sendGet() throws Exception {
    String url = "https://api.github.com/users/sduffy826/repos";
    URL obj = new URL(url);
    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
    // optional default is GET
    con.setRequestMethod("GET");
    // add request header
    con.setRequestProperty("User-Agent", USER_AGENT);
    int responseCode = con.getResponseCode();

    System.out.println("\nSending 'GET' request to URL : " + url);
    System.out.println("Response Code : " + responseCode);

    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
    String inputLine;
    StringBuffer response = new StringBuffer();
    while ((inputLine = in.readLine()) != null) {
      response.append(inputLine);
      if (DEBUG) System.out.println(inputLine);
    }
    in.close();

    // print result
    System.out.println(response.toString());
  }
}
