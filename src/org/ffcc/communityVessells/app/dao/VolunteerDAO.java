/*
 * @author: FFCC - members
 */

package org.ffcc.communityVessells.app.dao;

import java.io.InputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

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
				volunteerList.add(new  Volunteer(rs.getString("email"),rs.getString("password"),rs.getString("username"),rs.getBlob("avatar").getBinaryStream(),rs.getInt("userID"),rs.getString("firstName"),rs.getString("lastName"),rs.getInt("promises")));
			}
			rs.close();
			selectst.close();
			
			return volunteerList;
		}
		catch(SQLException sqlE){
			throw new Exception("An error occured while getting volunteers from database: " + sqlE.getMessage());
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
		 
	 }
	
	
}
