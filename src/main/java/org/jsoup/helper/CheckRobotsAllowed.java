package org.jsoup.helper;

import java.net.URL;

public interface CheckRobotsAllowed {
	
	// set user agent name
	public void set_userAgent(String name);
	
	// check URL whether Robots.txt allows
	public boolean check(URL url);
	
}
