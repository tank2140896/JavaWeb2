package com.javaweb.help;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonHelp {
	
	public String fromJsonDefault(ResponseResult responseResult){
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return gson.toJson(responseResult);
	}
	
}
