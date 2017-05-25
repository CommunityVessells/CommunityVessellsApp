/*
 * @author: FFCC - members
 */

package org.ffcc.communityVessells.app.models;

public class Volunteer extends User {
	private  int userID;
	private String firstName;
	private String lastName;
	private int promises;
	
	public Volunteer(String email, String password) {
		super(email, password);
		
	}

	public Volunteer(String email, String password, String username) {
		super(email, password, username);
	}

	public Volunteer(String email, String password, String username, String firstName, String lastName) {
		super(email, password, username);
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.promises = 0;
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
