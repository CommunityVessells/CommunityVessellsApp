package org.ffcc.communityVessells.app.dao;

import java.sql.*;


import org.ffcc.communityVessells.app.models.Volunteer;
import org.ffcc.communityVessells.app.connection.DB;
import org.ffcc.communityVessells.app.encryption.EncryptMD5;


public class LoginDAO {
	public LoginDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean authenticateUser(String email,String password) throws Exception{
		 Connection con = null;
		 String sqlquery = "SELECT * FROM volunteer WHERE email=?;";		 
		 DB db = new DB();
		 String type;
		 try{
				db.open();
				con = db.getConnection();
				//Search in volunteer
				PreparedStatement selectstVol = con.prepareStatement(sqlquery);
				
				selectstVol.setString(1, email);
				
				ResultSet rsVol=selectstVol.executeQuery();
				if(rsVol.next()){
					Volunteer checkVol = new Volunteer(rsVol.getString("email"),rsVol.getString("password"),rsVol.getString("username"),rsVol.getString("avatar"),rsVol.getInt("userID"),rsVol.getString("firstName"),rsVol.getString("lastName"));
					type = "volunteer";
					//check password match
					return EncryptMD5.authenticateUser(password, checkVol.getPassword());
				}
				selectstVol.close();
				//Search in orgs
				/*
				PreparedStatement selectstOrg = con.prepareStatement(sqlquery);
				selectstOrg.setString(1, "organization");
				selectstOrg.setString(2, email);
				
				ResultSet rsOrg=selectstOrg.executeQuery();
				if(rsOrg.next()){
					Volunteer checkOrg = new Volunteer(rsOrg.getString("email"),rsOrg.getString("password"),rsOrg.getString("username"),rsOrg.getString("avatar"),rsOrg.getInt("userID"),rsOrg.getString("firstName"),rsOrg.getString("lastName"));
					type = "organization";
					//check password match
					return EncryptMD5.authenticateUser(password, checkOrg.getPassword());
				}
				selectstOrg.close();
				*/
				
		 }catch(Exception e){
		 		throw new Exception("An error occured while searching in database: "+e.getMessage());
		 	}
		 	finally{
				try{
					db.close();
				}catch (Exception e){
					e.printStackTrace();					
				}
				
		 	}
		 return false;
	}
}
