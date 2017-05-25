/*
 * @author: FFCC - members
 */

package org.ffcc.communityVessells.app.models;

import java.io.InputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
public class User {
	
	private String email;
	private String password;
	private String username;
	private InputStream avatar; //store image object as InputStream to get/set to mediumblob in mySql.
	
	
	public User(String email, String password,String username) {
		super();
		this.email = email;
		this.password = password;
		this.username = username;
	}

	public User(String email, String password, String username,InputStream avatar) {
		super();
		
		this.email = email;
		this.password = password;
		this.username = username;
		this.avatar =  avatar;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public InputStream getAvatar() {
		return avatar;
	}

	public void setAvatar(InputStream avatar) {
		this.avatar = avatar;
	}


	
}
