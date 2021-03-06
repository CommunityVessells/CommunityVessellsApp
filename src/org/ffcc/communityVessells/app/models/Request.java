package org.ffcc.communityVessells.app.models;

import java.sql.Date;

public class Request {

	private int requestID;
	private String title;
	private Date startdate;
	private Date closedate;
	private String address;
	private String avatar;
	private boolean isFulfilled=false;
	private int reposID;

	public Request(int requestID, String title,Date startdate, Date closedate, String address, String avatar, boolean isFulfilled,
			int reposID) {
		super();
		this.requestID = requestID;
		this.title=title;
		this.startdate = startdate;
		this.closedate = closedate;
		this.address = address;
		this.avatar = avatar;
		this.isFulfilled = isFulfilled;
		this.reposID = reposID;
	}
	
	public Request(String title,Date startdate, Date closedate, String address, String avatar, boolean isFulfilled,
			int reposID) {
		super();
		this.title=title;
		this.startdate = startdate;
		this.closedate = closedate;
		this.address = address;
		this.avatar = avatar;
		this.isFulfilled = isFulfilled;
		this.reposID = reposID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getClosedate() {
		return closedate;
	}

	public void setClosedate(Date closedate) {
		this.closedate = closedate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public boolean isFulfilled() {
		return isFulfilled;
	}

	public void setFulfilled() {
		this.isFulfilled = true;
	}

	public int getReposID() {
		return reposID;
	}

	public void setReposID(int reposID) {
		this.reposID = reposID;
	}

	public int getRequestID() {
		return requestID;
	}
	
	

}