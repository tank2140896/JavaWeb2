package com.javaweb.util.entity;

import java.security.PrivateKey;
import java.security.PublicKey;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RsaKey {
	
	private PublicKey rsaPublicKey;
	
	private PrivateKey rsaPrivateKey;
	
	private String rsaStringPublicKey;
	
	private String rsaStringPrivateKey;
	
}
