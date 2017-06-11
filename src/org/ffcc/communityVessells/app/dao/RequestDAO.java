package org.ffcc.communityVessells.app.dao;

import java.sql.*;
import java.util.LinkedList;

import org.ffcc.communityVessells.app.models.Organization;
import org.ffcc.communityVessells.app.models.Repository;
import org.ffcc.communityVessells.app.models.Request;
import org.ffcc.communityVessells.app.connection.DB;
import org.ffcc.communityVessells.app.helpers.Helpers;
import org.ffcc.communityVessells.app.models.Product;

public class RequestDAO {

	public RequestDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public LinkedList<Request> getRequests(int reposID) throws Exception{
		Connection con = null;
		String sqlquery = "SELECT * FROM request WHERE reposID = ?;";
		DB db = new DB();
		
		try{
			db.open();
			con = db.getConnection();
			
			PreparedStatement selectst = con.prepareStatement(sqlquery);
			selectst.setInt(1, reposID);
			ResultSet rs = selectst.executeQuery();
		
			LinkedList<Request> requestList = new LinkedList<Request>();
			while(rs.next()){
				
				requestList.add(new Request(rs.getInt("requestID"), rs.getString("title"), rs.getDate("startdate"), rs.getDate("closedate"), rs.getString("address"),rs.getString("avatar"), Helpers.returnBooleanFromInt(rs.getInt("isFulfilled")) , rs.getInt("reposID")));
			}
			rs.close();
			selectst.close();
			
			return requestList;
		}
		catch(SQLException sqlE){
			throw new Exception("An error occured while getting requests from database: " + sqlE.getMessage());
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
	
	public LinkedList<Request> getAllRequests() throws Exception{
		Connection con = null;
		String sqlquery = "SELECT * FROM request;";
		DB db = new DB();
		
		try{
			db.open();
			con = db.getConnection();
			
			PreparedStatement selectst = con.prepareStatement(sqlquery);
			
			ResultSet rs = selectst.executeQuery();
		
			LinkedList<Request> requestList = new LinkedList<Request>();
			while(rs.next()){
				
				requestList.add(new Request(rs.getInt("requestID"), rs.getString("title"), rs.getDate("startdate"), rs.getDate("closedate"), rs.getString("address"),rs.getString("avatar"), Helpers.returnBooleanFromInt(rs.getInt("isFulfilled")) , rs.getInt("reposID")));
			}
			rs.close();
			selectst.close();
			
			return requestList;
		}
		catch(SQLException sqlE){
			throw new Exception("An error occured while getting requests from database: " + sqlE.getMessage());
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
	
	public Request getRequestByID(int requestID) throws Exception{
		Connection con = null;
		String sqlquery = "SELECT * FROM request WHERE requestID = ?;";
		DB db = new DB();
		
		try{
			db.open();
			con = db.getConnection();
			
			PreparedStatement selectst = con.prepareStatement(sqlquery);
			selectst.setInt(1, requestID);
			ResultSet rs = selectst.executeQuery();
			Request request=null;
			
			if(rs.next()){
				
				request = new Request(rs.getInt("requestID"), rs.getString("title"), rs.getDate("startdate"), rs.getDate("closedate"), rs.getString("address"),rs.getString("avatar"), Helpers.returnBooleanFromInt(rs.getInt("isFulfilled")) , rs.getInt("reposID"));
			}
			rs.close();
			selectst.close();
			
			return request;
		}
		catch(SQLException sqlE){
			throw new Exception("An error occured while getting requests from database: " + sqlE.getMessage());
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
	
	 public void createRequest(Request req) throws Exception{
			Connection con = null;
			String sqlupdate = "INSERT INTO request (title, startdate, closedate, address, reposID, avatar, isFulfilled) VALUES (? , ? , ? , ? , ? , ? , ?);";			
			DB db = new DB();
			
			try{
				db.open();
				con = db.getConnection();
				
				PreparedStatement insertst = con.prepareStatement(sqlupdate);
				
				
				insertst.setString(1, req.getTitle());
				insertst.setDate(2, req.getStartdate());
				insertst.setDate(3, req.getClosedate());
				insertst.setString(4, req.getAddress());
				insertst.setInt(5, req.getReposID());
				insertst.setString(6, req.getAvatar());
				insertst.setInt(7, Helpers.returnIntFromBoolean(req.isFulfilled()));
				
				insertst.executeUpdate();
				
				insertst.close();
				
			}catch (SQLException e) {
				e.printStackTrace();
			}catch (Exception e){
				throw new Exception("An error occured while inserting request to database: " + e.getMessage());
			}finally{
				try{
					db.close();
				}catch (Exception e){
					e.printStackTrace();
				}
			}
			
	 }
	 
		public static boolean hasRepositoryRequest(int reposID) throws Exception{
			Connection con = null;
			String sqlquery = "SELECT * FROM request WHERE reposID = ?;";
			DB db = new DB();
			boolean hasRequest=false;
			try{
				db.open();
				con = db.getConnection();
				
				PreparedStatement selectst = con.prepareStatement(sqlquery);
				selectst.setInt(1, reposID);
				ResultSet rs = selectst.executeQuery();
				
				
				if(rs.next()){
					
					hasRequest=true;
				}
				rs.close();
				selectst.close();
				return hasRequest;
				
			}
			catch(SQLException sqlE){
				throw new Exception("An error occured while getting requests from database: " + sqlE.getMessage());
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
	//end functions
}
