/*
 * @author: FFCC - members
 */

package org.ffcc.communityVessells.app.dao;


import java.sql.*;
import java.util.LinkedList;

import org.ffcc.communityVessells.app.models.Volunteer;
import org.ffcc.communityVessells.app.connection.DB;

public class VolunteerDAO {

	public VolunteerDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public LinkedList<Volunteer> getVolunteers() throws Exception{
		Connection con = null;
		String sqlquery = "SELECT * FROM volunteer;";
		DB db = new DB();
		
		try{
			db.open();
			con = db.getConnection();
			
			PreparedStatement selectst = con.prepareStatement(sqlquery);
			ResultSet rs = selectst.executeQuery();
		
			LinkedList<Volunteer> volunteerList = new LinkedList<Volunteer>();
			while(rs.next()){
				volunteerList.add(new  Volunteer(rs.getString("email"),rs.getString("password"),rs.getString("username"),rs.getString("avatar"),rs.getString("firstName"),rs.getString("lastName")));
			}
			rs.close();
			selectst.close();
			
			return volunteerList;
		}
		catch(SQLException sqlE){
			throw new Exception("An error occured while getting users from database: " + sqlE.getMessage());
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
	 public void saveVolunteer(Volunteer volunteer) throws Exception{
			Connection con = null;
			String sqlcreate = "INSERT INTO  volunteer (email, password,username,firstName,lastName,avatar) VALUES (? , ? , ? , ? , ? , ?);";
			DB db = new DB();
			
			try{
				db.open();
				con = db.getConnection();
				
				PreparedStatement insertst = con.prepareStatement(sqlcreate);
				
				
				insertst.setString(1, volunteer.getEmail());
				insertst.setString(2, volunteer.getPassword());
				insertst.setString(3, volunteer.getUsername());
				insertst.setString(4, volunteer.getFirstName());
				insertst.setString(5, volunteer.getLastName());
				insertst.setString(6, volunteer.getAvatar());
				
				insertst.executeUpdate();
				insertst.close();
				
			}catch (SQLException e) {
				e.printStackTrace();
				//throw new Exception("Volunteer with ID: " + volunteer.getUserID() + " already exists");
			}catch (Exception e){
				throw new Exception("An error occured while inserting user to database: " + e.getMessage());
			}finally{
				try{
					db.close();
				}catch (Exception e){
					e.printStackTrace();
				}
			}
	 }
	 
	 public static Volunteer findVolunteerByEmail(String email) throws Exception{
		 Connection con = null;
		 String sqlquery = "SELECT * FROM volunteer WHERE email=?;";
		 DB db = new DB();
		 
		 try{
				db.open();
				con = db.getConnection();
				
				PreparedStatement selectst = con.prepareStatement(sqlquery);
				selectst.setString(1, email);
				ResultSet rs=selectst.executeQuery();
				if(rs.next()){
					return new Volunteer(rs.getString("email"),rs.getString("password"),rs.getString("username"),rs.getString("avatar"),rs.getInt("userID"),rs.getString("firstName"),rs.getString("lastName"));
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
