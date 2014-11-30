package com.example.artistinformation.structures;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

public class JSONParser {
	static InputStream inputStream = null;
	static JSONObject jsonObject = null;
	static String json = "";

	// constructor
	public JSONParser() {
	}

	public JSONObject getJSONFromUrl(String url) {
		// Making HTTP request
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);

		try {
			// Execute HTTP Post Request
			HttpResponse httpResponse = httpClient.execute(httpGet);
			if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				return null;
			}

			HttpEntity httpEntity = httpResponse.getEntity();
			InputStream inputStream = null;

			String result;

			BufferedReader reader;
			if (httpEntity != null) {
				inputStream = httpEntity.getContent();
			}
			reader = new BufferedReader(new InputStreamReader(inputStream),
					8000);
			StringBuffer builder = new StringBuffer("");
			String line = reader.readLine();
			while (line != null) {
				builder.append(line);
				line = reader.readLine();
			}
			inputStream.close();
			result = builder.toString();
			jsonObject = new JSONObject(result);
		} catch (Exception exception) {

		}
		// return JSON String
		return jsonObject;
	}
}