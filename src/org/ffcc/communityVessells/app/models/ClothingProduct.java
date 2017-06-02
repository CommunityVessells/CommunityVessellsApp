package org.ffcc.communityVessells.app.models;

import java.sql.Date;

public class ClothingProduct extends Product{

	private String size;//XL,L,M,S,XS
	private String condition;//Mint,Near-Mint,Very-Good,Good,Poor,Fubar
	
	public ClothingProduct(int prodID, String title, int repoID, int count, Date dateStored, String size,
			String condition) {
		super(prodID, title, repoID, count, dateStored);
		this.size = size;
		this.condition = condition;
	}



	public ClothingProduct(int prodID, String title, int repoID, int count, Date dateStored) {
		super(prodID, title, repoID, count, dateStored);
		// TODO Auto-generated constructor stub
	}

	public ClothingProduct(int prodID, String title, int repoID, int count) {
		super(prodID, title, repoID, count);
		// TODO Auto-generated constructor stub
	}

	public ClothingProduct(String title, int repoID, int count) {
		super(title, repoID, count);
		// TODO Auto-generated constructor stub
	}

	public ClothingProduct(String title, int count) {
		super(title, count);
		// TODO Auto-generated constructor stub
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
		return this.getCondition().equals(threshold);
	}

}
