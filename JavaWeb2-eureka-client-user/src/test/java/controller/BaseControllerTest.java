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
		Header header1 = new BasicHeader("token","MjNFRjRBN0NFQjcyNEE4M0ExQkZCNDVEQkY4NTdDOEU0NDEzMDk5MzAsYWRtaW4xMjM0NTYsMA==");
		List<Header> list = new ArrayList<>();
		list.add(header1);
		return list;
	}
	
}
