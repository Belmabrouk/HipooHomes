package com.example.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.net.ssl.HttpsURLConnection;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.entities.User;
import com.example.demo.service.PropertyService;
import com.example.demo.service.UserService;
import com.example.demo.service.WebScrappingService;
import com.example.demo.service.Impl.GeolocalisationImpl;
import com.example.demo.service.Impl.IdealistaApiService;

@SpringBootApplication
@EnableScheduling
public class HipooHomesApplication {

	@Autowired
	GeolocalisationImpl geolocalisationService;
	
	@Autowired
	WebScrappingService webScrapingService;
	
	@Autowired
	IdealistaApiService idealistaAPI;
	
	@Autowired
	UserService userService;
	
	@Autowired
	PropertyService propertyService;
	
	

	public static void main(String[] args) throws IOException, InterruptedException {

		SpringApplication.run(HipooHomesApplication.class, args);

		

	}
	
	
	

	  @Bean
	    BCryptPasswordEncoder getBCPE(){
	        return new BCryptPasswordEncoder();
	    }
	@PostConstruct
	private void init() throws IOException {

		/*
		 * Idealista API 
		 */
		
//		idealistaAPI.getAuthToken();
//		idealistaAPI.getProperties();
		
		
	
	}

}
