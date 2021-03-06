package org.ffcc.communityVessells.app.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ffcc.communityVessells.app.dao.OrganizationDAO;
import org.ffcc.communityVessells.app.dao.VolunteerDAO;
import org.ffcc.communityVessells.app.encryption.EncryptMD5;

/**
 * Servlet implementation class RegisterController
 */
@WebServlet("/register")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterController() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		
		String email= request.getParameter("email");
		String password= request.getParameter("password");

		

		//Check for valid email address
		if(email.isEmpty() || !email.contains("@")) {
					RequestDispatcher errorDispatcher = request.getRequestDispatcher("/");
					request.setAttribute("errormessage", "You have not entered a valid e-mail address.");
					
					errorDispatcher.forward(request, response); 
		}
		
		//Check if email exists in volunteer or organization
		try{
			if(VolunteerDAO.findVolunteerByEmail(email)!=null) {
				RequestDispatcher errorDispatcher = request.getRequestDispatcher("/");
				request.setAttribute("errormessage", "This e-mail address already exists.");
			
				errorDispatcher.forward(request, response); 
			}
			if(OrganizationDAO.findOrganizationByEmail(email)!=null) {
				RequestDispatcher errorDispatcher = request.getRequestDispatcher("/");
				request.setAttribute("errormessage", "This e-mail address already exists.");
			
				errorDispatcher.forward(request, response); 
			}
		}
		catch(Exception e){
			e.getMessage();
		}
		//Check for valid password
		
		if(password.isEmpty() || password.length()<8) {
			RequestDispatcher errorDispatcher = request.getRequestDispatcher("/");
			request.setAttribute("errormessage", "The password entered is smaller than 8 characters long.");
			
			errorDispatcher.forward(request, response); 
		}
		
		/*Create MD5 hash of password.
		* TODO  Use OAuth2 and SSL. Keeping a request channel unencrypted ,
		defeats the purpose of hashing passwords into database.*/
		HttpSession session = request.getSession(true);

		session.setAttribute("email", email);
		session.setAttribute("hash", EncryptMD5.encrypt(password));
		
		
		//Send credentials to the next screen based on user=volunteer or user=organization
		if(request.getParameter("optionsRadios").equals("organization")){
			RequestDispatcher rdOrg = request.getRequestDispatcher("/registerOrganization.jsp");
			rdOrg.forward(request, response);
		}
		RequestDispatcher rdVolunteer = request.getRequestDispatcher("/registerVolunteer.jsp");
		rdVolunteer.forward(request, response);
		
	}

}
