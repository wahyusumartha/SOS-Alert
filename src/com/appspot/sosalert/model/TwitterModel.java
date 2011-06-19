package com.appspot.sosalert.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class TwitterModel {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	
	@Persistent
	private String token;
	
	@Persistent
	private String tokenSecret;
	
	public TwitterModel(){
		
	}
	
	public void setToken(String token){
		this.token = token;
	}
	
	public void setTokenSecret(String tokenSecret){
		this.tokenSecret = tokenSecret;
	}
	
	public String getToken(){
		return token;
	}
	
	public String getTokenSecret(){
		return tokenSecret;
	}
}
