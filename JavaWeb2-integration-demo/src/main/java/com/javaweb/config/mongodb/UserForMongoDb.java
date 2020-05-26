package com.javaweb.config.mongodb;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection="user")//相当于表
public class UserForMongoDb implements Serializable {

	private static final long serialVersionUID = -339300585927455739L;

	@Id
	private String mongoDbId;//此ID为mongodb自动生成的
	
	private String id;
	
	private String username;
	
	private String password;
	
	private int age;
	
	private String currentAddress;
	
}
