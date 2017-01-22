import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import com.google.gson.*;
import javax.net.ssl.HttpsURLConnection;

public class NlpApi {
	private Gson gson;
	private String json;
	private String text;
	private NlpApi response;
	
	private String id;
	private String language;
	private NlpApi nlp;
	
	public NlpApi() {
		
	}
	public NlpApi(String text) {
		nlp = new NlpApi();
		gson = new Gson();
		nlp.setText(text);
		nlp.language = "en";
		nlp.id = UUID.randomUUID().toString();
		
		setToJsonConversion(nlp);
	}
	
	private void setToJsonConversion(NlpApi obj) {
		json = gson.toJson(obj);
	}
	
	private void setText(String text) {
		this.text = text;
	}
	
	private String getText() {
		return text;
	}
	
	public String getJson() {
		return "{\"documents\": ["+json+"]}"; 
	}
	
	public String KeyPhrase(){
		 
	    try {
	      //Create connection
	    	URL url = new URL("https://westus.api.cognitive.microsoft.com/text/analytics/v2.0/keyPhrases");
	      HttpURLConnection connection = (HttpURLConnection)url.openConnection();
	      connection.setRequestMethod("POST");
	      connection.setRequestProperty("Ocp-Apim-Subscription-Key", "981a640d05964de3ae39dbc1209a7b3f");
	      connection.setRequestProperty("Content-Type", "application/json");
	      connection.setRequestProperty("Accept", "application/json");
	      connection.setRequestProperty("Accept-Language", "application/json"); 
	      connection.setRequestProperty("User-Agent","Mozilla/5.0 ( compatible ) ");
				
	      connection.setUseCaches (false);
	      connection.setDoInput(true);
	      connection.setDoOutput(true);

	      //Send request
	      DataOutputStream wr = new DataOutputStream (
	                  connection.getOutputStream ());
	      System.out.println(getJson());
	      wr.writeBytes (getJson());
	      wr.flush ();
	      wr.close ();

	      //Get Response	
	      BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	      StringBuffer response = new StringBuffer(); 
	      String line;
	      while((line = br.readLine()) != null) {
	        response.append(line);
	        response.append('\r');
	      }
	      br.close();
	      return response.toString();

	    } catch (Exception e) {
	    	
	      e.printStackTrace();
	      return null;

	    }
	}
}
