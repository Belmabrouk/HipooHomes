package com.example.demo.service;

import java.io.IOException;

public interface IdealistaApiService  {
	
	public String getAuthToken()throws IOException;
	public String getProperties()throws IOException;

}
