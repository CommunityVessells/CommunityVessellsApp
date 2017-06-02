package org.ffcc.communityVessells.app.models;

import java.sql.Date;

public class FoodProduct extends Product {

	private Date expire;
	

	public FoodProduct(int prodID, String title, int repoID, int count, Date dateStored) {
		super(prodID, title, repoID, count, dateStored);
		// TODO Auto-generated constructor stub
	}

	public FoodProduct(int prodID, String title, int repoID, int count) {
		super(prodID, title, repoID, count);
		// TODO Auto-generated constructor stub
	}

	public FoodProduct(String title, int count) {
		super(title, count);
		// TODO Auto-generated constructor stub
	}

	public FoodProduct(String title, int repoID, int count) {
		super(title, repoID, count);
		// TODO Auto-generated constructor stub
	}

	public Date getExpire() {
		return expire;
	}

	public void setExpire(Date expire) {
		this.expire = expire;
	}
	
	public boolean hasExpired(Date now){
		return now.after(getExpire());
	}
}
