package org.ffcc.communityVessells.app.models;

public class Repository {

	private int repoID;
	private String title;
	private String repoType;
	private int capacity;
	private int availableProducts;
	private int orgID;

	public Repository(String title,String repoType, int capacity, int availableProducts) {
		this.title = title;
		this.repoType = repoType;		
		this.capacity = capacity;
		this.availableProducts = availableProducts;
	}
	
	public Repository(String title,String repoType, int capacity, int availableProducts,int orgID) {
		this.title = title;
		this.repoType = repoType;		
		this.capacity = capacity;
		this.availableProducts = availableProducts;
		this.orgID = orgID;
	}
	
	public Repository(int repoID, String title,String repoType, int capacity, int availableProducts) {
		this.title = title;
		this.repoType = repoType;
		this.repoID = repoID;
		this.capacity = capacity;
		this.availableProducts = availableProducts;
	}
	
	public Repository(int repoID, String title,String repoType, int capacity, int availableProducts,int orgID) {
		this.title = title;
		this.repoType = repoType;
		this.repoID = repoID;
		this.capacity = capacity;
		this.availableProducts = availableProducts;
		this.orgID=orgID;
	}

	public String getRepoType() {
		return repoType;
	}

	public void setRepoType(String repoType) {
		this.repoType = repoType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getRepoID() {
		return repoID;
	}

	

	public void setRepoID(int repoID) {
		this.repoID = repoID;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getAvailableProducts() {
		return availableProducts;
	}

	public void setAvailableProducts(int availableProducts) {
		this.availableProducts = availableProducts;
	}

	public int getOrgID() {
		return orgID;
	}
	
	public void setOrgID(int orgID) {
		this.orgID = orgID;
	}
}