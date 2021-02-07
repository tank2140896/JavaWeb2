package com.javaweb.config.requestRewrite;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class WebHttpServletRequestWrapper extends HttpServletRequestWrapper {
	
	private String getBodyContent(HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (InputStream inputStream = request.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
 
    private final byte[] body;
 
    public WebHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        body = getBodyContent(request).getBytes(Charset.forName("UTF-8"));
        System.err.println(new String(body,"UTF-8"));
        /**
        User user = new User();
        user.setName("ad34343");
        user.setAge(12);
        user.setAddress("d0929fjfk");
        body = new ObjectMapper().writeValueAsBytes(user);//不匹配的可以throw出去
    	*/
    }
 
    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }
 
    @Override
    public ServletInputStream getInputStream() throws IOException {
 
        final ByteArrayInputStream bais = new ByteArrayInputStream(body);
 
        return new ServletInputStream() {
 
            @Override
            public int read() throws IOException {
                return bais.read();
            }
 
            @Override
            public boolean isFinished() {
                return false;
            }
 
            @Override
            public boolean isReady() {
                return false;
            }
 
            @Override
            public void setReadListener(ReadListener readListener) {
 
            }
        };
    }
    
}
