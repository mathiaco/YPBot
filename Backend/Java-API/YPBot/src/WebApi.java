//import java.io.BufferedReader;
//import java.io.DataOutputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.*;
//
//import com.google.gson.*;
//import javax.net.ssl.HttpsURLConnection;
//
//public class WebApi {
//	
//	private Gson gson;
//	private String json;
//	private String text;
//	private WebApi response;
//	
//	//private WebApi spellcheck;
//	private WebApi nlp;
//	
//	public WebApi(){
//		
//	}
//			 		 
//	public WebApi(String text) {
//		String language = "en";
//		String id = UUID.randomUUID().toString();
//		nlp = new WebApi();
//		nlp.setText(text); 
//	}	
//	
//	
//	private String ToJsonConversion(WebApi obj) {
//		gson = new Gson();
//		json = gson.toJson(obj);
//		return json;
//	}
//	
//	private void setText(String text) {
//		this.text = text;
//	}
//	
//	private String getText() {
//		return text;
//	}
//	
//	/*public void SpellCheck() {
//		ToJsonConversion(spellcheck);
//		System.out.print(json);
//	} */
//	
//	public void KeyPhrase(){
//		
//		URL url;
//	    HttpURLConnection connection = null;  
//	    try {
//	      //Create connection
//	      url = new URL("https://westus.api.cognitive.microsoft.com/text/analytics/v2.0/keyPhrases");
//	      connection = (HttpURLConnection)url.openConnection();
//	      connection.setRequestMethod("POST");
//	      connection.setRequestProperty("Ocp-Apim-Subscription-Key", "981a640d05964de3ae39dbc1209a7b3f");
//	      connection.setRequestProperty("Content-Type", "application/json");
//	      connection.setRequestProperty("Accept", "application/json");
//	      connection.setRequestProperty("Accept-Language", "application/json"); 
//				
//	      connection.setUseCaches (false);
//	      connection.setDoInput(true);
//	      connection.setDoOutput(true);
//
//	      //Send request
//	      DataOutputStream wr = new DataOutputStream (
//	                  connection.getOutputStream ());
//	      wr.writeBytes (json);
//	      wr.flush ();
//	      wr.close ();
//
//	      //Get Response	
//	      InputStream is = connection.getInputStream();
//	      BufferedReader rd = new BufferedReader(new InputStreamReader(is));
//	      String line;
//	      StringBuffer response = new StringBuffer(); 
//	      while((line = rd.readLine()) != null) {
//	        response.append(line);
//	        response.append('\r');
//	      }
//	      rd.close();
//	      return response.toString();
//
//	    } catch (Exception e) {
//
//	      e.printStackTrace();
//	      return null;
//
//	    } finally {
//
//	      if(connection != null) {
//	        connection.disconnect(); 
//	      }
//	    }
//		
//		
//		
//		String url = "";
//		URL obj = new URL(url);
//		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
//
//		//add request header
//		con.setRequestMethod("POST");
//		
//
//		// Send post request
//		con.setDoOutput(true);
//		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
//		wr.writeBytes(json);
//		wr.flush();
//		wr.close();
//
//		int responseCode = con.getResponseCode();
//		System.out.println("\nSending 'POST' request to URL : " + url);
//		System.out.println("Response Code : " + responseCode);
//
//		BufferedReader in = new BufferedReader(
//		        new InputStreamReader(con.getInputStream()));
//		String inputLine;
//		StringBuffer response = new StringBuffer();
//
//		while ((inputLine = in.readLine()) != null) {
//			response.append(inputLine);
//		}
//		in.close();
//
//		//print result
//		System.out.println(response.toString());
//		
//	}
//
//}
