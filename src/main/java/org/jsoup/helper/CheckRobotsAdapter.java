package org.jsoup.helper;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.robotstxt.*;

public class CheckRobotsAdapter implements CheckRobotsAllowed {

	String user_agent = "Any";
	private RobotsTxt bots;
	InputStream in;
	
	String root;
	String rest;

	@Override
	public void set_userAgent(String name) {

		this.user_agent = name;

	}
	
	public InputStream makeInputStream(URL url) throws Exception{
		
		this.in = url.openStream();
		return this.in;
		
	}
	
	public InputStream set_inputStream(InputStream input) {
		
		this.in = input;
		return this.in;
		
	}

	@Override
	public boolean check(URL url){

		
		ChangeURL(url);
		
		String txtPath = this.root + "/" + "robots.txt";
			
		try (InputStream inputStream = makeInputStream(new URL(txtPath))) {
			RobotsTxtReader reader = new RobotsTxtReader(MatchingStrategy.DEFAULT, WinningStrategy.DEFAULT);
			bots = reader.readRobotsTxt(inputStream);
			
		} catch (MalformedURLException e) {
			return true;
		} catch (IOException e) {
			return true;
		} catch (Exception e) {
			return true;
		}
		
		
		return bots.query(user_agent, this.rest);

	}
	
	private boolean ChangeURL(URL url){
		
		String url_str = url.toString();
		
		String[] words = url_str.split("/");
		
		if ( words.length < 3) {
			
		}
		else if ( words.length == 3) {
			
			this.root = words[0] + "//" + words[2];
			this.rest = "/";
			
		}
		else if ( words.length > 3) {
			
			
			this.root = words[0] + "//" + words[2];
			
			this.rest = "";
			for(int i = 3; i < words.length; i++) {		
				
				this.rest = this.rest + "/" + words[i];
				
			}
			
			
		}
		
		return true;		
		
	}

}
