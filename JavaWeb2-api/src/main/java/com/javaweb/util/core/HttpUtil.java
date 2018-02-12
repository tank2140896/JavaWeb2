package com.javaweb.util.core;

import java.net.URL;
import java.net.URLConnection;

public class HttpUtil {
	
	public static URLConnection getURLConnection(String url) throws Exception {
		return new URL(url).openConnection();
	}

}
