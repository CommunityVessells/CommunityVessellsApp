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
	
	public Volunteer(String email, String password,String username) {
		super(email, password,username);
		
	}

	public Volunteer(String email, String password, String username,String avatar) {
		super(email, password, username,avatar);
	}

	public Volunteer(String email, String password, String username,String avatar,String firstName, String lastName) {
		super(email, password, username,avatar);
		
		
		this.firstName = firstName;
		this.lastName = lastName;		
	}
	
	public Volunteer(String email, String password, String username,String avatar,int userID,String firstName, String lastName) {
		super(email, password, username,avatar);
		
		this.userID = userID;
		this.firstName = firstName;
		this.lastName = lastName;		
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

	

	public int getUserID() {
		return userID;
	}
}
