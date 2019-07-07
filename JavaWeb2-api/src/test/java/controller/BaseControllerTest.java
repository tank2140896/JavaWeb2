package controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseControllerTest {

	public final String URL_PREFIX = "http://localhost/javaweb-web";
	
	public final ObjectMapper objectMapper = new ObjectMapper();
	
	public final List<Header> getHeaders(){
		Header header1 = new BasicHeader("userId","admin123456");
		Header header2 = new BasicHeader("token","6E9D84FE9C487151BD40318E9699907E8");
		Header header3 = new BasicHeader("type","0");
		List<Header> list = new ArrayList<>();
		list.add(header1);
		list.add(header2);
		list.add(header3);
		return list;
	}
	
}
