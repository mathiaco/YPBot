import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import com.google.gson.*;
import javax.net.ssl.HttpsURLConnection;

public class WebApi {
		
		Gson gson = new Gson(); 
		WebApi employee = new WebApi();
		employee.setId(1);
		employee.setFirstName("Lokesh");
		employee.setLastName("Gupta");
		employee.setRoles(Arrays.asList("ADMIN", "MANAGER"));
		 		 
		System.out.println(gson.toJson(employee));

	public WebApi() {
		
	}
	
	private String JsonCon(String text) {
		
	}
	
	// HTTP POST request
	private void sendPost() throws Exception {
		

		String url = "https://westus.api.cognitive.microsoft.com/text/analytics/v2.0/keyPhrases";
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		//add request header
		con.setRequestMethod("POST");
		con.setRequestProperty("Ocp-Apim-Subscription-Key", "981a640d05964de3ae39dbc1209a7b3f");
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("Accept-Language", "application/json");

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());

	}

}