import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

//import com.fasterxml.jackson.core.JsonFactory;
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.core.JsonParseException;
//import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CopyOfGitHubRepoJsonParse {
  private static final boolean DEBUG = false;
  private final String USER_AGENT = "Mozilla/5.0";
  public static void main(String[] args) throws Exception {
    CopyOfGitHubRepoJsonParse http = new CopyOfGitHubRepoJsonParse();

    System.out.println("Testing 1 - Send Http GET request");
    http.sendGet();
  }

  // HTTP GET request
  private void sendGet() throws Exception {
    String url = "https://api.github.com/users/sduffy826/repos";
    URL obj = new URL(url);
    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
    // optional default is GET
    con.setRequestMethod("GET");
    // add request header
    con.setRequestProperty("User-Agent", USER_AGENT);
    con.addRequestProperty("Accept","application/vnd.github.v3+json");
    
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
        
    ObjectMapper mapper = new ObjectMapper();
    JsonNode rootNode = mapper.readTree(response.toString());
    for (JsonNode aNode : rootNode) {
      System.out.println("Repository name: " + aNode.get("name").toString());
      System.out.println("  id: " + aNode.get("id").toString());
      System.out.println("  created: " + aNode.get("created_at").toString());
      System.out.println("  updated: " + aNode.get("updated_at").toString());
      System.out.println("  pushed: " + aNode.get("pushed_at").toString());
    }    
  }
}
