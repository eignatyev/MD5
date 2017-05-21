package test.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class RequestMethods extends RequestDetails{
	public void proceedGETRequest() throws Exception {		
		if (this.getParamText() != null)
			this.setUrl(this.getUrl() + "?text=" + this.getParamText());
		
		URL urlObject = new URL(this.getUrl());
		HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
		connection.setRequestMethod("GET");
		this.setResponseCode(connection.getResponseCode());
		
		try {
			BufferedReader responseStream = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String responseLine;
			StringBuffer response = new StringBuffer();
	
			while ((responseLine = responseStream.readLine()) != null)
				response.append(responseLine);
			responseStream.close();
			this.setResponseJSON(new JSONObject(response.toString()));
			this.setResponseBody(response.toString());
		} catch (Exception e) {}		
	}
}
