package org.ffcc.communityVessells.app.models;

public class Promise {
	
	private int promiseID;
	private int requestID;
	private int volunteerID;
	private int productID;

	public Promise(int promiseID, int requestID, int volunteerID, int productID) {
		super();
		this.promiseID = promiseID;
		this.requestID = requestID;
		this.volunteerID = volunteerID;
		this.productID = productID;
	}
	
	public Promise(int requestID, int volunteerID, int productID) {
		super();		
		this.requestID = requestID;
		this.volunteerID = volunteerID;
		this.productID = productID;
	}

	public int getRequestID() {
		return requestID;
	}

	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}

	public int getVolunteerID() {
		return volunteerID;
	}

	public void setVolunteerID(int volunteerID) {
		this.volunteerID = volunteerID;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public int getPromiseID() {
		return promiseID;
	}
	
	
	
	
}
