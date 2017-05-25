/*
 * @author: FFCC - members
 */

package org.ffcc.communityVessells.app.models;

import java.io.InputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Volunteer extends User {
	private  int userID;
	private String firstName;
	private String lastName;
	private int promises;
	
	public Volunteer(String email, String password,String username) {
		super(email, password,username);
		
	}

	public Volunteer(String email, String password, String username,InputStream avatar) {
		super(email, password, username,avatar);
	}

	public Volunteer(String email, String password, String username,InputStream avatar,int  userID, String firstName, String lastName,int promises) {
		super(email, password, username,avatar);
		
		this.userID = userID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.promises = promises;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getPromises() {
		return promises;
	}

	public void setPromises(int promises) {
		this.promises = promises;
	}

	public int getUserID() {
		return userID;
	}
}
