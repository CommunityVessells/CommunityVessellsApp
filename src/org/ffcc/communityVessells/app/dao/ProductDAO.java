package org.ffcc.communityVessells.app.dao;

import java.sql.*;
import java.util.LinkedList;

import org.ffcc.communityVessells.app.models.Organization;
import org.ffcc.communityVessells.app.models.Repository;
import org.ffcc.communityVessells.app.connection.DB;
import org.ffcc.communityVessells.app.models.Product;


public class ProductDAO {

	public ProductDAO() {
		// TODO Auto-generated constructor stub
	}

	 public void saveProduct(Product product) throws Exception{
			Connection con = null;
			String sqlcreate = "INSERT INTO product (capacity, availableProducts, orgID, title, repoType) VALUES (? , ? , ? , ? , ?);";
			DB db = new DB();
			
			try{
				db.open();
				con = db.getConnection();
				
				PreparedStatement insertst = con.prepareStatement(sqlcreate);
				
				insertst.setInt(1, repository.getCapacity());
				insertst.setInt(2, repository.getAvailableProducts());
				insertst.setInt(3, repository.getOrgID());
				insertst.setString(4, repository.getTitle());
				insertst.setString(5, repository.getRepoType());
				
				insertst.executeUpdate();
				insertst.close();
				
			}catch (SQLException e) {
				e.printStackTrace();
				//throw new Exception("Repository with ID: " + repository.getRepoID() + " already exists");
			}catch (Exception e){
				throw new Exception("An error occured while inserting repository to database: " + e.getMessage());
			}finally{
				try{
					db.close();
				}catch (Exception e){
					e.printStackTrace();
				}
			}
}
