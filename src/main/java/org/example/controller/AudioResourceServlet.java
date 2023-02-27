package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

@WebServlet(name = "audio",value="audio")
public class AudioResourceServlet extends HttpServlet{
	
	//Creating internal Memory for Audio
	ConcurrentHashMap<String,Audio> audioDB = new ConcurrentHashMap<>();
	
	@Override
	public void init() throws ServletException{
		Audio song1 = new Audio();
		song1.setArtistName("Ali Seth");
		song1.setTrackTitle("Pasoori");
		song1.setAlbumTitle("Jackson");
		song1.setTrackNumber("track_001");
		song1.setYear(2021);
		song1.setNoOfReviews(4500);
		song1.setNoOfCopiesSold(10000);
		audioDB.put("Pasoori",song1);
		
		Audio song2 = new Audio();
		song2.setArtistName("Sia");
		song2.setTrackTitle("Cheap Thrills");
		song2.setAlbumTitle("Frank Zappa");
		song2.setTrackNumber("track_002");
		song2.setYear(2022);
		song2.setNoOfReviews(6000);
		song2.setNoOfCopiesSold(85000);
		audioDB.put("Cheap Thrills",song2);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String track_title = request.getParameter("track_title");
		Audio song1 = audioDB.get(track_title);
		String artist_name = song1.getArtistName();
	
		
		
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(audioDB);
		
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		out.println("GET RESPONSE IN JSON - all elements " + element.toString());
		out.println("RESPONSE IN JSON - Single Element:"+gson.toJson(artist_name));
		out.flush();
		
		
		//Response in JSON type
		
		out.println("RESPONSE IN JSON - All Elements:" + element.toString());
		out.flush();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
