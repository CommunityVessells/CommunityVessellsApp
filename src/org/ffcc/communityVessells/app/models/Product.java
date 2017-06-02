package org.ffcc.communityVessells.app.models;

import java.sql.Date;

public class Product {
	private int prodID;
	private String title;
	private String prodType;
	private int repoID;
	private int count;
	private Date dateStored;
	private Date expire;//only for food or pharm types
	private boolean isPromised=false;
	private String size;//XL,L,M,S,XS or N/A
	private String condition;//Mint,Near-Mint,Very-Good,Good,Poor,Fubar or N/A

	public Product(int prodID, String title, int repoID, int count, Date dateStored) {
		super();
		this.prodID = prodID;
		this.title = title;
		this.repoID = repoID;
		this.count = count;
		this.dateStored = dateStored;
	}

	public Product(int prodID, String title, int repoID, int count) {
		super();
		this.prodID = prodID;
		this.title = title;
		this.repoID = repoID;
		this.count = count;
	}

	public Product(String title, int count) {
		super();
		this.title = title;
		this.count = count;
	}

	public Product(String title, int repoID, int count) {
		super();
		this.title = title;
		this.repoID = repoID;
		this.count = count;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	//special setters
	public void setProdFood(Date expire){
		this.prodType="food";
		this.setExpire(expire);
		this.setCondition("N/A");
		this.setSize("N/A");
		
	}


	
	public void setProdPharm(Date expire){
		this.prodType="pharm";
		this.setExpire(expire);
		this.setCondition("N/A");
		this.setSize("N/A");
		
	}
	
	public void setProdClothing(String condition,String size){
		this.prodType="clothing";
		this.setExpire(null);
		this.setCondition(condition);
		this.setSize(size);
	}
	
	public String getProdType(){
		return this.prodType;
	}
	
	public int getRepoID() {
		return repoID;
	}

	public void setRepoID(int repoID) {
		this.repoID = repoID;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getProdID() {
		return prodID;
	}

	public Date getDateStored() {
		return dateStored;
	}

	public void setDateStored(Date dateStored) {
		this.dateStored = dateStored;
	}

	public boolean isPromised() {
		return isPromised;
	}

	//this setter is special
	public void setPromised() {
		this.isPromised = true;
	}
	
	public Date getExpire() {
		return expire;
	}

	public void setExpire(Date expire) {
		this.expire = expire;
	}
	
	public boolean hasExpired(Date now){
		if(this.getExpire()!=null){
			return now.after(this.getExpire());
		}
		
		return false;
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
	
	//user inputs the threshold for acceptable clothes
	public boolean isFubar(String threshold){
		if(!this.getCondition().equals("N/A")){
			return this.getCondition().equals(threshold);
		}
		
		return false;
	}
}
