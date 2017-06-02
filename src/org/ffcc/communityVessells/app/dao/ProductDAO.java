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
	public LinkedList<Product> getProducts(int repoID) throws Exception{
		Connection con = null;
		String sqlquery = "SELECT * FROM product WHERE repoID = ?;";
		DB db = new DB();
		
		try{
			db.open();
			con = db.getConnection();
			
			PreparedStatement selectst = con.prepareStatement(sqlquery);
			selectst.setInt(1, repoID);
			ResultSet rs = selectst.executeQuery();
		
			LinkedList<Product> productList = new LinkedList<Product>();
			while(rs.next()){
				productList.add(new Product(rs.getInt("prodID"),rs.getString("title"), rs.getInt("repoID"), rs.getInt("count")));
			}
			rs.close();
			selectst.close();
			
			return productList;
		}
		catch(SQLException sqlE){
			throw new Exception("An error occured while getting repositories from database: " + sqlE.getMessage());
		}
		finally {
			try{
				db.close();

			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	 }
	
	 public void saveProduct(Product product) throws Exception{
			Connection con = null;
			String sqlcreate = "INSERT INTO product (repoID, title, prodType, count, isPromised) VALUES (? , ? , ? , ? , ?);";
			DB db = new DB();
			
			try{
				db.open();
				con = db.getConnection();
				
				PreparedStatement insertst = con.prepareStatement(sqlcreate);
				
				insertst.setInt(1,product.getProdID());
				insertst.setString(2, product.getTitle());
				insertst.setString(3, product.getProdType());
				insertst.setInt(4, product.getCount());
				if(product.isPromised()){
					insertst.setInt(5, 1);
				}else insertst.setInt(5, 0);
				
				insertst.executeUpdate();
				insertst.close();
				
			}catch (SQLException e) {
				e.printStackTrace();
				//throw new Exception("Repository with ID: " + repository.getRepoID() + " already exists");
			}catch (Exception e){
				throw new Exception("An error occured while inserting product to database: " + e.getMessage());
			}finally{
				try{
					db.close();
				}catch (Exception e){
					e.printStackTrace();
				}
			}
	 }
}
