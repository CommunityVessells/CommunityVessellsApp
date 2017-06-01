package org.ffcc.communityVessells.app.models;

import java.util.Date;

public class Request {

	private int requestID;
	private int counter;
	private Date startdate;
	private Date closedate;
	private String region;
	private int duration;
	private int promises;
	private int organID;
	private int reposID;
	
	public Request(int requestID, int counter, Date startdate, String region, int duration){
		this.requestID=requestID;
		this.counter=counter;
		this.startdate=startdate;		
		this.region=region;
		this.duration=duration;
	}

	public int getRequestID() {
		return requestID;
	}

	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
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

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getPromises() {
		return promises;
	}

	public void setPromises(int promises) {
		this.promises = promises;
	}

	public int getOrganID() {
		return organID;
	}

	public void setOrganID(int organID) {
		this.organID = organID;
	}

	public int getReposID() {
		return reposID;
	}

	public void setReposID(int reposID) {
		this.reposID = reposID;
	}	
}