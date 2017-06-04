/*
 * @author: FFCC - members
 */

package org.ffcc.communityVessells.app.dao;

import java.sql.*;
import java.util.LinkedList;

import org.ffcc.communityVessells.app.models.Organization;
import org.ffcc.communityVessells.app.models.Product;
import org.ffcc.communityVessells.app.models.Repository;
import org.ffcc.communityVessells.app.connection.DB;

public class RepositoryDAO {
	
	public RepositoryDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public LinkedList<Repository> getRepositories() throws Exception{
		Connection con = null;
		String sqlquery = "SELECT * FROM repository;";
		DB db = new DB();
		
		try{
			db.open();
			con = db.getConnection();
			
			PreparedStatement selectst = con.prepareStatement(sqlquery);
			ResultSet rs = selectst.executeQuery();
		
			LinkedList<Repository> repositoryList = new LinkedList<Repository>();
			while(rs.next()){
				repositoryList.add(new Repository(rs.getInt("repoID"),rs.getString("title"),rs.getString("repoType"),rs.getInt("capacity"),rs.getInt("availableProducts"),rs.getInt("orgID")));
			}
			rs.close();
			selectst.close();
			
			return repositoryList;
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
	 public void saveRepository(Repository repository) throws Exception{
			Connection con = null;
			String sqlcreate = "INSERT INTO repository (capacity, availableProducts, orgID, title, repoType) VALUES (? , ? , ? , ? , ?);";
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
	 
	 public void updateRepository(Repository repository) throws Exception{
			Connection con = null;
			
			String sqlupdate = "UPDATE repository SET capacity=?,availableProducts=?,title=?,repoType=? WHERE repoID=?;";
			DB db = new DB();
			
			try{
				db.open();
				con = db.getConnection();
				
				PreparedStatement updatest = con.prepareStatement(sqlupdate);
				
				updatest.setInt(1,repository.getCapacity());
				updatest.setInt(2, repository.getAvailableProducts());
				updatest.setString(3, repository.getTitle());
				updatest.setString(4, repository.getRepoType());
				
				updatest.setInt(5, repository.getRepoID());
				
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
	 
	 public static LinkedList<Repository> findRepositoryByTitle(String title) throws Exception{
		 Connection con = null;
		 String sqlquery = "SELECT * FROM repository WHERE title=?;";
		 DB db = new DB();
		 
		 try{
				db.open();
				con = db.getConnection();
				
				LinkedList<Repository> repositoryList = new LinkedList<Repository>();

				PreparedStatement selectst = con.prepareStatement(sqlquery);
				selectst.setString(1, title);

				ResultSet rs=selectst.executeQuery();
				while(rs.next()){
					repositoryList.add(new Repository(rs.getInt("repoID"),rs.getString("title"),rs.getString("repoType"), rs.getInt("capacity"), rs.getInt("availableProducts"),rs.getInt("orgID")));
				}
				
				selectst.close();
				return repositoryList;
		 	}
		 	catch(Exception e){
		 		throw new Exception("An error occured while searching in database: "+e.getMessage());
		 	}
		 	finally{
				try{
					db.close();
				}catch (Exception e){
					e.printStackTrace();					
				}
	
		 	}
		 
	 }
	 
	 public static Repository getRepositoryByID(int repoID) throws Exception{
		 Connection con = null;
		 String sqlquery = "SELECT * FROM repository WHERE repoID=?;";
		 DB db = new DB();
		 
		 try{
				db.open();
				con = db.getConnection();
				

				PreparedStatement selectst = con.prepareStatement(sqlquery);
				selectst.setInt(1, repoID);

				ResultSet rs=selectst.executeQuery();
				if(rs.next()){
					return new Repository(rs.getInt("repoID"),rs.getString("title"),rs.getString("repoType"), rs.getInt("capacity"), rs.getInt("availableProducts"),rs.getInt("orgID"));
				}
				
				selectst.close();
				return null;
		 	}
		 	catch(Exception e){
		 		throw new Exception("An error occured while searching in database: "+e.getMessage());
		 	}
		 	finally{
				try{
					db.close();
				}catch (Exception e){
					e.printStackTrace();					
				}
	
		 	}
		 
	 }

}
