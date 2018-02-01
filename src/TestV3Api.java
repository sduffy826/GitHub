// import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

//import com.fasterxml.jackson.core.JsonFactory;
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.core.JsonParseException;
//import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestV3Api {
  private ObjectMapper objectMapper = null;
  private static final boolean DEBUGIT = true;
  
  public TestV3Api() {
    objectMapper = new ObjectMapper();
  }
  
  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    TestV3Api testIt = new TestV3Api();
    testIt.makeCall();
  }
  public void makeCall(){
        String jsonNode;
        CloseableHttpClient client= null;
        CloseableHttpResponse response= null;

        client= HttpClients.createDefault();
        
        HttpGet http= new HttpGet("https://github.ibm.com/api/v3/user");

        http.addHeader("Authorization","token xxxx DeletedHardcodedToken xxxx");
        http.addHeader("Accept","application/json");

        /*
        //        try {
            // Not sure why the example did this
//           JsonNode jsonNode = objectMapper.readValue(jsonString, JsonNode.class);
//            String jsonOutString = objectMapper.writeValueAsString(jsonNode);
//            StringEntity entity= new StringEntity(jsonOutString);

//            httpPost.setEntity(entity);
//            response= client.execute(httpPost);
//        }
//        catch(UnsupportedEncodingException e){
//            e.printStackTrace();
//        }
//        catch(ClientProtocolException e){
//            e.printStackTrace();
//        }
//        catch(IOException e){
//            e.printStackTrace();
//        }
*/
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line= null;
            StringBuilder builder= new StringBuilder();
            while((line=reader.readLine())!= null) {
                builder.append(line);
            }
            System.out.println(builder.toString());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}