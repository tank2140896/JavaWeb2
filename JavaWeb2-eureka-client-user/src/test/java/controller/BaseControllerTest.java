package controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseControllerTest {

	public final String URL_PREFIX = "http://client1:2001";
	
	public final ObjectMapper objectMapper = new ObjectMapper();
	
	public final List<Header> getHeaders(){
		Header header1 = new BasicHeader("token","8F686C6F684449C1A1F770E1D61BA2F3931415080");
		List<Header> list = new ArrayList<>();
		list.add(header1);
		return list;
	}
	
}
