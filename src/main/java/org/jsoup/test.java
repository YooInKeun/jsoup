package org.jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * A simple example for checkRobots.
 */

public class test {
    public static void main(String[] args) throws IOException {
   	
    	
    	// not allowed
    	System.out.println("Simple not allowed test. Result should [Robots.txt don't allow you]\n");
    	
        try {
        	String str = Jsoup.connect("https://finance.naver.com/").isAllowed("mybot").request().url().toExternalForm();
        	
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        
    	// allowed
    	System.out.println("\nSimple not allowed test. Result should [Google Áöµµ]\n");
    	
        Document doc = Jsoup.connect("https://www.google.co.kr/maps/").isAllowed("mybot").get();
        log(doc.title());

    }

    
    private static void log(String msg, String... vals) {
        System.out.println(String.format(msg, vals));
    }
}