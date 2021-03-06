package org.ffcc.communityVessells.app.dao;

import java.sql.*;
import java.util.LinkedList;

import org.ffcc.communityVessells.app.models.Organization;
import org.ffcc.communityVessells.app.models.Repository;
import org.ffcc.communityVessells.app.connection.DB;
import org.ffcc.communityVessells.app.helpers.Helpers;
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
				
				productList.add(new Product(rs.getInt("productID"),rs.getString("title"), rs.getInt("repoID"), rs.getInt("count")));
			}
			rs.close();
			selectst.close();
			
			return productList;
		}
		catch(SQLException sqlE){
			throw new Exception("An error occured while getting products from database: " + sqlE.getMessage());
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
			String sqlupdate = "INSERT INTO product (repoID, title, prodType, count, isPromised) VALUES (? , ? , ? , ? , ?);";			
			DB db = new DB();
			
			try{
				db.open();
				con = db.getConnection();
				
				PreparedStatement insertst = con.prepareStatement(sqlupdate);
				
				insertst.setInt(1,product.getRepoID());
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
	 
	 public void saveProductAllFields(Product product) throws Exception{
			Connection con = null;
			String sqlupdate = "INSERT INTO product (repoID, title, prodType, count,dateStored, isPromised,expire,size,clotheCond) VALUES (? , ? , ? , ? , ?, ? ,? ,? ,?);";			
			DB db = new DB();
			
			try{
				db.open();
				con = db.getConnection();
				
				PreparedStatement insertst = con.prepareStatement(sqlupdate);
				
				insertst.setInt(1,product.getRepoID());
				insertst.setString(2, product.getTitle());
				insertst.setString(3, product.getProdType());
				insertst.setInt(4, product.getCount());
				insertst.setDate(5, product.getDateStored());
				insertst.setInt(6, Helpers.returnIntFromBoolean(product.isPromised()));
				insertst.setDate(7, product.getExpire());
				insertst.setString(8, product.getSize());
				insertst.setString(9, product.getCondition());
				
				
				insertst.executeUpdate();
				
				insertst.close();
				
			}catch (SQLException e) {
				e.printStackTrace();
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
	 
	 public void setProductTypeFood(Product product,Date expire) throws Exception{
			Connection con = null;
			product.setProdFood(expire);
			String sqlupdate = "UPDATE product SET prodType=?,expire=? WHERE productID=?;";
			DB db = new DB();
			
			try{
				db.open();
				con = db.getConnection();
				
				PreparedStatement updatest = con.prepareStatement(sqlupdate);
				
				updatest.setString(1,product.getProdType());
				updatest.setDate(2, product.getExpire());				
				//updatest.setString(3, product.getSize());
				//updatest.setString(3, product.getCondition());
				
				updatest.setInt(3, product.getProdID());
				
				updatest.executeUpdate();

				updatest.close();
				
			}catch (SQLException e) {
				e.printStackTrace();
			}catch (Exception e){
				throw new Exception("An error occured while updating product in database: " + e.getMessage());
			}finally{
				try{
					db.close();
				}catch (Exception e){
					e.printStackTrace();
				}
			}
	 }
	 
	 public void setProductTypePharm(Product product,Date expire) throws Exception{
			Connection con = null;
			product.setProdPharm(expire);
			String sqlupdate = "UPDATE product SET prodType=?,expire=? WHERE productID=?;";
			DB db = new DB();
			
			try{
				db.open();
				con = db.getConnection();
				
				PreparedStatement updatest = con.prepareStatement(sqlupdate);
				
				updatest.setString(1,product.getProdType());
				updatest.setDate(2, product.getExpire());
				//updatest.setString(3, product.getSize());
				//updatest.setString(4, product.getCondition());
				
				updatest.setInt(3, product.getProdID());
				
				updatest.executeUpdate();
				updatest.close();
				
			}catch (SQLException e) {
				e.printStackTrace();
			}catch (Exception e){
				throw new Exception("An error occured while updating product in database: " + e.getMessage());
			}finally{
				try{
					db.close();
				}catch (Exception e){
					e.printStackTrace();
				}
			}
	 }
	 
	 public void setProductTypeClothing(Product product,String condition,String size) throws Exception{
			Connection con = null;
			product.setProdClothing(condition, size);
			String sqlupdate = "UPDATE product SET prodType=?,size=?,clotheCond=?  WHERE productID =?;";
			DB db = new DB();
			
			try{
				db.open();
				con = db.getConnection();
				
				PreparedStatement updatest = con.prepareStatement(sqlupdate);
				
				updatest.setString(1,product.getProdType());
				
				updatest.setString(2, product.getSize());
				updatest.setString(3, product.getCondition());
				
				updatest.setInt(4, product.getProdID());
				
				updatest.executeUpdate();
				updatest.close();
				
			}catch (SQLException e) {
				e.printStackTrace();
			}catch (Exception e){
				throw new Exception("An error occured while updating product in database: " + e.getMessage());
			}finally{
				try{
					db.close();
				}catch (Exception e){
					e.printStackTrace();
				}
			}
	 }
	 
	 public void setPromised(Product product) throws Exception {
			Connection con = null;
			product.setPromised();
			String sqlupdate = "UPDATE product SET isPromised=?  WHERE productID = ?;";
			DB db = new DB();
			
			try{
				db.open();
				con = db.getConnection();
				
				PreparedStatement updatest = con.prepareStatement(sqlupdate);
				
				updatest.setInt(1,Helpers.returnIntFromBoolean(product.isPromised()));
				
				updatest.setInt(2, product.getProdID());
				
				updatest.executeUpdate();
				updatest.close();
				
			}catch (SQLException e) {
				e.printStackTrace();
			}catch (Exception e){
				throw new Exception("An error occured while updating product in database: " + e.getMessage());
			}finally{
				try{
					db.close();
				}catch (Exception e){
					e.printStackTrace();
				}
			}
	 }
	 
	 public void setAvailable(Product product) throws Exception {
			Connection con = null;
			product.setAvailable();
			String sqlupdate = "UPDATE product SET isPromised= ?  WHERE productID = ?;";
			DB db = new DB();
			
			try{
				db.open();
				con = db.getConnection();
				
				PreparedStatement updatest = con.prepareStatement(sqlupdate);
				
				updatest.setInt(1,Helpers.returnIntFromBoolean(product.isPromised()));
				
				updatest.setInt(2, product.getProdID());
				
				updatest.executeUpdate();
				updatest.close();
				
			}catch (SQLException e) {
				e.printStackTrace();
			}catch (Exception e){
				throw new Exception("An error occured while updating product in database: " + e.getMessage());
			}finally{
				try{
					db.close();
				}catch (Exception e){
					e.printStackTrace();
				}
			}
	 }
	 
	 public void setDateStored(Product product,Date dateStored) throws Exception {
			Connection con = null;
			product.setDateStored(dateStored);
			String sqlupdate = "UPDATE product SET dateStored=?  WHERE productID =?;";
			DB db = new DB();
			
			try{
				db.open();
				con = db.getConnection();
				
				PreparedStatement updatest = con.prepareStatement(sqlupdate);
				
				updatest.setDate(1,product.getDateStored());
				
				updatest.setInt(2, product.getProdID());
				
				updatest.executeUpdate();
				updatest.close();
				
			}catch (SQLException e) {
				e.printStackTrace();
			}catch (Exception e){
				throw new Exception("An error occured while updating product in database: " + e.getMessage());
			}finally{
				try{
					db.close();
				}catch (Exception e){
					e.printStackTrace();
				}
			}
	 }
	 
	 public LinkedList<Product> getProductsAllFields(int repoID) throws Exception{
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
					
					productList.add(new Product(rs.getInt("productID"),rs.getString("title"),rs.getString("prodType"), rs.getInt("repoID"), rs.getInt("count"),rs.getDate("dateStored") ,rs.getDate("expire"),rs.getBoolean("isPromised"),rs.getString("size"),rs.getString("clotheCond")));
				}
				rs.close();
				selectst.close();
				
				return productList;
			}
			catch(SQLException sqlE){
				throw new Exception("An error occured while getting products from database: " + sqlE.getMessage());
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
	 
	 public static Product getProductByID(int prodID) throws Exception{
			Connection con = null;
			String sqlquery = "SELECT * FROM product WHERE productID = ?;";
			DB db = new DB();
			
			try{
				db.open();
				con = db.getConnection();
				
				PreparedStatement selectst = con.prepareStatement(sqlquery);
				selectst.setInt(1, prodID);
				ResultSet rs = selectst.executeQuery();
			
				
				if(rs.next()){
					
					return new Product(rs.getInt("productID"),rs.getString("title"),rs.getString("prodType"), rs.getInt("repoID"), rs.getInt("count"),rs.getDate("dateStored") ,rs.getDate("expire"),rs.getBoolean("isPromised"),rs.getString("size"),rs.getString("clotheCond"));
				}
				rs.close();
				selectst.close();
				
				return null;
			}
			catch(SQLException sqlE){
				throw new Exception("An error occured while getting products from database: " + sqlE.getMessage());
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
	 
	 public static void deleteProductByID(int prodID) throws Exception{
			Connection con = null;
			String sqlquery = "DELETE FROM product WHERE productID = ?;";
			DB db = new DB();
			
			try{
				db.open();
				con = db.getConnection();
				
				PreparedStatement deletest = con.prepareStatement(sqlquery);
				deletest.setInt(1, prodID);
				deletest.executeUpdate();
			
				
				deletest.close();
				
			}
			catch(SQLException sqlE){
				throw new Exception("An error occured while getting products from database: " + sqlE.getMessage());
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
}
