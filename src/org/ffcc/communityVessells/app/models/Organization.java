/*
 * @author: FFCC - members
 */

package org.ffcc.communityVessells.app.models;

public class Organization extends User{
	
	private int orgID;
	private String description;
	private String type;
	

	public Organization(String email, String password, String name) {
		super(email, password, name);		
	}

	public Organization(String email, String password, String name, String avatar) {
		super(email, password, name, avatar);
	}

	public Organization(String email, String password, String name, String avatar, String description, String type) {
		super(email, password, name, avatar);		
		this.description = description;
		this.type = type;		
	}
	
	public Organization(String email, String password, String name, String avatar, int orgID, String description, String type) {
		super(email, password, name, avatar);		
		this.orgID = orgID;
		this.description = description;
		this.type = type;		
	}

	public int getOrgID() {
		return orgID;
	}


	public void setOrgID(int orgID) {
		this.orgID = orgID;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}
}