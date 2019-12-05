package org.jsoup.helper;

import org.jsoup.Jsoup;;
import org.jsoup.Connection;
import org.jsoup.integration.servlets.FileServlet;

import org.junit.Assert;
import org.junit.Test;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

import java.net.URL;

public class CheckRobotsAdapterTest {

	@Test
	public void success() throws Exception {
		
		Connection con = Jsoup.connect("https://www.google.co.kr/maps/");
		
		String user_agent = "Any";
		
		assertEquals("https://www.google.co.kr/maps/", con.isAllowed(user_agent).request().url().toExternalForm());
		
	}
	
	@Test
	public void notAllowed() throws Exception {
		
		String user_agent = "Any";
		
        boolean threw = false;
        try {
        	Connection con = Jsoup.connect("https://finance.naver.com/").isAllowed(user_agent);
        	
        } catch (IllegalArgumentException e) {
            threw = true;
            assertEquals("Robots.txt don't allow you", e.getMessage());
        }
        assertTrue(threw);
    
	}
}