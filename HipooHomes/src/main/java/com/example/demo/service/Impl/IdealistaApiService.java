package com.example.demo.service.Impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Geolocalisation;
import com.example.demo.entities.Property;
import com.example.demo.entities.Status;
import com.example.demo.service.GeolocalisationService;
import com.example.demo.service.PropertyService;
import com.google.gson.Gson;

@Service
public class IdealistaApiService implements com.example.demo.service.IdealistaApiService {
	String access_token = null;
	Date expiryDate;

	@Autowired
	GeolocalisationService geolocalisationService;
	
	@Autowired
	PropertyService propertyService;

	@Override
	public String getAuthToken() throws IOException {

		BufferedReader reader = null;
		int expires_in;
		String API_KEY = "yb49umnh9os5wx1ru0fpvb8c182lxklp";
		String SECRET = "bmYzsu0txkIA";
		/*
		 * Idealista Api key and secret base64 encoded
		 */

		String auth = Base64.getEncoder().encodeToString((API_KEY + ":" + SECRET).getBytes());

		/*
		 * Establish connection
		 */
		URL url = new URL("https://api.idealista.com/oauth/token?grant_type=client_credentials&scope=read");
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

		/*
		 * Setting request properties
		 */
		connection.setRequestProperty("Authorization", "Basic " + auth);
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");
		reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line = null;
		StringWriter out = new StringWriter(connection.getContentLength() > 0 ? connection.getContentLength() : 2048);
		while ((line = reader.readLine()) != null) {
			out.append(line);
		}
		String response = out.toString();
		try {
			JSONObject jsonObject = new JSONObject(response);
			access_token = jsonObject.getString("access_token");
			expires_in = jsonObject.getInt("expires_in");
			System.out.println(expires_in);
			Date now = new Date();
			expiryDate = new Date(now.getTime() + expires_in * 1000);
			System.out.println(expiryDate);
		}

		catch (JSONException err) {
			System.out.println(err);
		}
		return access_token;
	}
	
	@Override
	public String getProperties() throws IOException {

		// Check if token is expired
//		if (expiryDate.before(new Date())) {
//			 access_token = getAuthToken();
//		}
		BufferedReader reader = null;
		List<Geolocalisation> listGeo = new ArrayList<>();

		listGeo = geolocalisationService.GetAll();
		for(Geolocalisation g : listGeo)
		{

		URL url = new URL("https://api.idealista.com/3.5/es/search?numPage=1&country=es&maxItems=50&distance=5000&propertyType=homes"
				+ "&operation=sale&k=yb49umnh9os5wx1ru0fpvb8c182lxklp&center="+g.getLatitude()+","+g.getLongitude());


		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		connection.setRequestProperty("Authorization", "Bearer " + access_token);
		connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");
		reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line = null;
		StringWriter out = new StringWriter(connection.getContentLength() > 0 ? connection.getContentLength() : 2048);
		while ((line = reader.readLine()) != null) {
			out.append(line);
		}
		String response = out.toString();
		try {
			JSONObject jsonObject = new JSONObject(response);
			JSONArray propertiesJSON = jsonObject.getJSONArray("elementList");
			
			/*
			 * Iterate over the JSONArray "elementList"
			 */
			for (int i = 0; i < propertiesJSON.length(); i++) {
				JSONObject propertyJson = propertiesJSON.getJSONObject(i);
				Gson gson = new Gson();
				
				/*
				 * Add property
				 */
				 Property property = new Property();
				 property =gson.fromJson(propertyJson.toString(), Property.class);
				 property.setTitle(propertyJson.getJSONObject("suggestedTexts").getString("title"));
				
				 propertyService.AddProperty(property);
		
				
				
				
			}

		}

		catch (JSONException err) {
			System.out.println(err);
		}
		}

		return null;
	}
	
	
	  
}
