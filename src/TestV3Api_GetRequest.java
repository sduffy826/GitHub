// import org.json.JSONObject;

import java.io.BufferedReader;
// import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
// import java.io.UnsupportedEncodingException;
// import java.net.HttpURLConnection;
// import java.net.URL;
// import javax.net.ssl.HttpsURLConnection;

// import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

//import com.fasterxml.jackson.core.JsonFactory;
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.core.JsonParseException;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;

import com.corti.jsonutils.*;

public class TestV3Api_GetRequest {
  private JsonUtils jsonUtils;
  // private static final boolean DEBUGIT = true;
  
  public TestV3Api_GetRequest() {
    jsonUtils = new JsonUtils();
  }
  
  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    TestV3Api_GetRequest testIt = new TestV3Api_GetRequest();
    testIt.makeCall();
  }
  public void makeCall() {
    CloseableHttpClient client= null;
    CloseableHttpResponse response= null;

    client= HttpClients.createDefault();
        
    HttpGet httpGetRequest = new HttpGet("https://github.ibm.com/api/v3/user");

    httpGetRequest.addHeader("Authorization","token f964e96052ac1c31afe4dbf2882576fea29e5931");
    //String myToken = "f964e96052ac1c31afe4dbf2882576fea29e5931";
    httpGetRequest.addHeader("Accept","application/json");
    //httpGetRequest.addHeader("X-Authentication-Token",myToken);

    try {
      response = client.execute(httpGetRequest);
    
      BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
      String line= null;
      StringBuilder builder= new StringBuilder();
      while((line=reader.readLine())!= null) {
        builder.append(line);
      }
      System.out.println(jsonUtils.prettifyIt(builder.toString()));
    }
    catch (ClientProtocolException cpe) { cpe.printStackTrace(); }
    catch (IOException ioe) { ioe.printStackTrace(); }
    catch(Exception e) { e.printStackTrace(); }
  }
}