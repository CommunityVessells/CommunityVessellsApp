/*
 * @author: FFCC - members
 */

package org.ffcc.communityVessells.app.models;

public class User {
	
	private String email;
	private String password;
	private String username;
	private String avatarFilePath; //We store image mediumblob object by reference to url.
	
	
	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public User(String email, String password, String username) {
		super();
		
		this.email = email;
		this.password = password;
		this.username = username;
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
	public String getAvatarFilePath() {
		return avatarFilePath;
	}
	public void setAvatarFilePath(String avatarFilePath) {
		this.avatarFilePath = avatarFilePath;
	}
	
	
}
