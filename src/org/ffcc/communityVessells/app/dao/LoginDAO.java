package org.ffcc.communityVessells.app.dao;

import java.sql.*;

import org.ffcc.communityVessells.app.models.Organization;
import org.ffcc.communityVessells.app.models.Volunteer;
import org.ffcc.communityVessells.app.connection.DB;
import org.ffcc.communityVessells.app.encryption.EncryptMD5;


public class LoginDAO {
	public LoginDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public String authenticateUser(String email,String password) throws Exception{
		 Connection con = null;
		 String sqlquery1 = "SELECT * FROM volunteer WHERE email=?;";	
		 String sqlquery2 = "SELECT * FROM organization WHERE email=?;";
		 DB db = new DB();
		 String type;
		 try{
				db.open();
				con = db.getConnection();
				//Search in volunteer
				PreparedStatement selectstVol = con.prepareStatement(sqlquery1);
				
				selectstVol.setString(1, email);
				
				ResultSet rsVol=selectstVol.executeQuery();
				if(rsVol.next()){
					Volunteer checkVol = new Volunteer(rsVol.getString("email"),rsVol.getString("password"),rsVol.getString("username"),rsVol.getString("avatar"),rsVol.getInt("userID"),rsVol.getString("firstName"),rsVol.getString("lastName"));
					type = "volunteer";
					//check password match
					if (EncryptMD5.authenticateUser(password, checkVol.getPassword())) return type;
				}
				selectstVol.close();
				//Search in orgs
				
				PreparedStatement selectstOrg = con.prepareStatement(sqlquery2);
				
				selectstOrg.setString(1, email);
				
				ResultSet rsOrg=selectstOrg.executeQuery();
				if(rsOrg.next()){
					Organization checkOrg = new Organization(rsOrg.getString("email"),rsOrg.getString("password"),rsOrg.getString("name"),rsOrg.getString("avatar"),rsOrg.getInt("orgID"),rsOrg.getString("description"),rsOrg.getString("type"));
					type = "organization";
					//check password match
					if (EncryptMD5.authenticateUser(password, checkOrg.getPassword())) return type;
				}
				selectstOrg.close();
				
				
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
		 return null;
	}
}
