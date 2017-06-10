package org.ffcc.communityVessells.app.models;

import java.sql.Date;

public class Promise {
	
	private int promiseID;
	private int requestID;
	private int volunteerID;
	
	private String volunteerEmail;
	private boolean isFulfilled=false;

	private String title;
	private String prodType;
	
	private int count;
	
	private Date expire;//only for food or pharm types
	
	private String size;//XL,L,M,S,XS or Null
	private String condition;//Mint,Near-Mint,Very-Good,Good,Poor,Fubar or Null

	

	public Promise(int promiseID, int requestID, int volunteerID, String volunteerEmail, boolean isFulfilled,
			String title, String prodType, int count, Date expire, String size, String condition) {
		super();
		this.promiseID = promiseID;
		this.requestID = requestID;
		this.volunteerID = volunteerID;
		this.volunteerEmail = volunteerEmail;
		this.isFulfilled = isFulfilled;
		this.title = title;
		this.prodType = prodType;
		this.count = count;
		this.expire = expire;
		this.size = size;
		this.condition = condition;
	}

	public Promise(int requestID, int volunteerID, String volunteerEmail, boolean isFulfilled, String title,
			String prodType, int count, Date expire, String size, String condition) {
		super();
		this.requestID = requestID;
		this.volunteerID = volunteerID;
		this.volunteerEmail = volunteerEmail;
		this.isFulfilled = isFulfilled;
		this.title = title;
		this.prodType = prodType;
		this.count = count;
		this.expire = expire;
		this.size = size;
		this.condition = condition;
	}
	
	public Promise(int requestID, int volunteerID, String volunteerEmail, boolean isFulfilled, String title,
			String prodType, int count, Date expire) {
		super();
		this.requestID = requestID;
		this.volunteerID = volunteerID;
		this.volunteerEmail = volunteerEmail;
		this.isFulfilled = isFulfilled;
		this.title = title;
		this.prodType = prodType;
		this.count = count;
		this.expire = expire;

	}

	public Promise(int requestID, int volunteerID, String volunteerEmail, boolean isFulfilled, String title,
			String prodType, int count, String size, String condition) {
		super();
		this.requestID = requestID;
		this.volunteerID = volunteerID;
		this.volunteerEmail = volunteerEmail;
		this.isFulfilled = isFulfilled;
		this.title = title;
		this.prodType = prodType;
		this.count = count;
		this.size = size;
		this.condition = condition;
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


	public int getPromiseID() {
		return promiseID;
	}

	public String getVolunteerEmail() {
		return volunteerEmail;
	}

	public void setVolunteerEmail(String volunteerEmail) {
		this.volunteerEmail = volunteerEmail;
	}

	public boolean isFulfilled() {
		return isFulfilled;
	}

	public void setFulfilled() {
		this.isFulfilled = true;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getProdType() {
		return prodType;
	}

	public void setProdType(String prodType) {
		this.prodType = prodType;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Date getExpire() {
		return expire;
	}

	public void setExpire(Date expire) {
		this.expire = expire;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}
	
	public static Product getProductFromPromise(Promise promise,Request request){
		//return a product that isPromised and has a null StoreDate.
		return new Product(promise.getTitle(),promise.getProdType(),request.getReposID(),promise.getCount(),null,promise.getExpire(),true,promise.getSize(),promise.getCondition());
	}
	
	
}
