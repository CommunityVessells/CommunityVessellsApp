package org.ffcc.communityVessells.app.dao;

import java.sql.*;
import java.util.LinkedList;

import org.ffcc.communityVessells.app.models.Organization;
import org.ffcc.communityVessells.app.models.Repository;
import org.ffcc.communityVessells.app.models.Request;
import org.ffcc.communityVessells.app.models.Volunteer;
import org.ffcc.communityVessells.app.connection.DB;
import org.ffcc.communityVessells.app.helpers.Helpers;
import org.ffcc.communityVessells.app.models.Product;
import org.ffcc.communityVessells.app.models.Promise;

public class PromiseDAO {

	public PromiseDAO() {
		// TODO Auto-generated constructor stub
	}
	
	 public void savePromise(Promise prom) throws Exception{
			Connection con = null;
			String sqlupdate = "INSERT INTO promise (requestID,volunteerID,volunteerEmail,isFulfilled,title,prodType,count,expire,size,clotheCond) VALUES (?,?,?,?,?,?,?,?,?,?);";			
			DB db = new DB();
			
			try{
				db.open();
				con = db.getConnection();
				
				PreparedStatement insertst = con.prepareStatement(sqlupdate);
				
				insertst.setInt(1, prom.getRequestID());
				insertst.setInt(2, prom.getVolunteerID());
				insertst.setString(3, prom.getVolunteerEmail());
				insertst.setInt(4, Helpers.returnIntFromBoolean(prom.isFulfilled()));
				insertst.setString(5, prom.getTitle());
				insertst.setString(6, prom.getProdType());
				insertst.setInt(7, prom.getCount());
				insertst.setDate(8,prom.getExpire());
				insertst.setString(9, prom.getSize());
				insertst.setString(10, prom.getCondition());
				
				insertst.executeUpdate();
				
				insertst.close();
				
			}catch (SQLException e) {
				e.printStackTrace();
			}catch (Exception e){
				throw new Exception("An error occured while inserting promise to database: " + e.getMessage());
			}finally{
				try{
					db.close();
				}catch (Exception e){
					e.printStackTrace();
				}
			}

			
	 }
	 
	 public static LinkedList<Promise> getPromisesByRequest(int requestID) throws Exception{
			Connection con = null;
			String sqlquery = "SELECT * FROM promise WHERE requestID = ?;";
			DB db = new DB();
			
			try{
				db.open();
				con = db.getConnection();
				
				PreparedStatement selectst = con.prepareStatement(sqlquery);
				selectst.setInt(1, requestID);
				ResultSet rs = selectst.executeQuery();
			
				LinkedList<Promise> promiseList = new LinkedList<Promise>();
				while(rs.next()){
					
					promiseList.add(new Promise(rs.getInt("promiseID"), rs.getInt("requestID"), rs.getInt("volunteerID"), rs.getString("volunteerEmail"), Helpers.returnBooleanFromInt(rs.getInt("isFulfilled")),rs.getString("title"),rs.getString("prodType"),rs.getInt("count"),rs.getDate("expire"),rs.getString("size"),rs.getString("clotheCond"))) ;
				}
				rs.close();
				selectst.close();
				
				return promiseList;
			}
			catch(SQLException sqlE){
				throw new Exception("An error occured while getting promises from database: " + sqlE.getMessage());
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
	 
	 public static LinkedList<Promise> getPromisesByVolunteer(int volunteerID) throws Exception{
			Connection con = null;
			String sqlquery = "SELECT * FROM promise WHERE volunteerID = ?;";
			DB db = new DB();
			
			try{
				db.open();
				con = db.getConnection();
				
				PreparedStatement selectst = con.prepareStatement(sqlquery);
				selectst.setInt(1, volunteerID);
				ResultSet rs = selectst.executeQuery();
			
				LinkedList<Promise> promiseList = new LinkedList<Promise>();
				while(rs.next()){
					
					promiseList.add(new Promise(rs.getInt("promiseID"), rs.getInt("requestID"), rs.getInt("volunteerID"), rs.getString("volunteerEmail"), Helpers.returnBooleanFromInt(rs.getInt("isFulfilled")),rs.getString("title"),rs.getString("prodType"),rs.getInt("count"),rs.getDate("expire"),rs.getString("size"),rs.getString("clotheCond"))) ;
				}
				rs.close();
				selectst.close();
				
				return promiseList;
			}
			catch(SQLException sqlE){
				throw new Exception("An error occured while getting promises from database: " + sqlE.getMessage());
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
	 
	 public static LinkedList<Promise> getAllPromises() throws Exception{
			Connection con = null;
			String sqlquery = "SELECT * FROM promise;";
			DB db = new DB();
			
			try{
				db.open();
				con = db.getConnection();
				
				PreparedStatement selectst = con.prepareStatement(sqlquery);
				
				ResultSet rs = selectst.executeQuery();
				LinkedList<Promise> promiseList = new LinkedList<Promise>();
				
				while(rs.next()){
					
					promiseList.add(new Promise(rs.getInt("promiseID"), rs.getInt("requestID"), rs.getInt("volunteerID"), rs.getString("volunteerEmail"), Helpers.returnBooleanFromInt(rs.getInt("isFulfilled")),rs.getString("title"),rs.getString("prodType"),rs.getInt("count"),rs.getDate("expire"),rs.getString("size"),rs.getString("clotheCond"))) ;
				}
				rs.close();
				selectst.close();
				
				return promiseList;
			}
			catch(SQLException sqlE){
				throw new Exception("An error occured while getting promises from database: " + sqlE.getMessage());
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
	 

	 public static Promise getPromiseByID(int promiseID) throws Exception{
			Connection con = null;
			String sqlquery = "SELECT * FROM promise WHERE promiseID = ?;";
			DB db = new DB();
			
			try{
				db.open();
				con = db.getConnection();
				
				PreparedStatement selectst = con.prepareStatement(sqlquery);
				selectst.setInt(1, promiseID);
				ResultSet rs = selectst.executeQuery();
			
				Promise thisPromise=null;
				if(rs.next()){
					
					thisPromise=new Promise(rs.getInt("promiseID"), rs.getInt("requestID"), rs.getInt("volunteerID"), rs.getString("volunteerEmail"), Helpers.returnBooleanFromInt(rs.getInt("isFulfilled")),rs.getString("title"),rs.getString("prodType"),rs.getInt("count"),rs.getDate("expire"),rs.getString("size"),rs.getString("clotheCond")) ;
				}
				rs.close();
				selectst.close();
				
				return thisPromise;
			}
			catch(SQLException sqlE){
				throw new Exception("An error occured while getting promises from database: " + sqlE.getMessage());
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
