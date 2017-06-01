package org.ffcc.communityVessells.app.dao;

import java.sql.*;
import java.util.LinkedList;

import org.ffcc.communityVessells.app.models.Organization;
import org.ffcc.communityVessells.app.connection.DB;

public class OrganizationDAO {

	public OrganizationDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public LinkedList<Organization> getOrganizations() throws Exception{
		Connection con = null;
		String sqlquery = "SELECT * FROM organization;";
		DB db = new DB();
		
		try{
			db.open();
			con = db.getConnection();
			
			PreparedStatement selectst = con.prepareStatement(sqlquery);
			ResultSet rs = selectst.executeQuery();
		
			LinkedList<Organization> organizationList = new LinkedList<Organization>();
			while(rs.next()){
				organizationList.add(new Organization(rs.getString("email"),rs.getString("password"),rs.getString("name"),rs.getString("avatar"),rs.getString("description"),rs.getString("type")));
			}
			rs.close();
			selectst.close();
			
			return organizationList;
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
	 public void saveOrganization(Organization organization) throws Exception{
			Connection con = null;
			String sqlcreate = "INSERT INTO organization (email, password, name, description, type, avatar) VALUES (? , ? , ? , ? , ? , ?);";
			DB db = new DB();
			
			try{
				db.open();
				con = db.getConnection();
				
				PreparedStatement insertst = con.prepareStatement(sqlcreate);
				
				
				insertst.setString(1, organization.getEmail());
				insertst.setString(2, organization.getPassword());
				insertst.setString(3, organization.getUsername());
				insertst.setString(4, organization.getDescription());
				insertst.setString(5, organization.getType());
				insertst.setString(6, organization.getAvatar());
				
				insertst.executeUpdate();
				insertst.close();
				
			}catch (SQLException e) {
				e.printStackTrace();
				//throw new Exception("Organization with ID: " + organization.getOrgID() + " already exists");
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
	 
	 public static Organization findOrganizationByEmail(String email) throws Exception{
		 Connection con = null;
		 String sqlquery = "SELECT * FROM organization WHERE email=?;";
		 DB db = new DB();
		 
		 try{
				db.open();
				con = db.getConnection();
				
				PreparedStatement selectst = con.prepareStatement(sqlquery);
				selectst.setString(1, email);
				ResultSet rs=selectst.executeQuery();
				if(rs.next()){
					return new Organization(rs.getString("email"),rs.getString("password"),rs.getString("name"),rs.getString("avatar"),rs.getInt("orgID"),rs.getString("description"),rs.getString("type"));
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