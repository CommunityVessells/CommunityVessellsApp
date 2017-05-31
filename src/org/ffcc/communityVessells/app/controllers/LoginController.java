package org.ffcc.communityVessells.app.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ffcc.communityVessells.app.dao.LoginDAO;
import org.ffcc.communityVessells.app.dao.OrganizationDAO;
import org.ffcc.communityVessells.app.dao.VolunteerDAO;
import org.ffcc.communityVessells.app.models.Organization;
import org.ffcc.communityVessells.app.models.Volunteer;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		
		
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String auth;
		LoginDAO login = new LoginDAO();
		
		
		try {
			
			auth=login.authenticateUser(email, password);
			
			if(auth==null) {
				RequestDispatcher fail = request.getRequestDispatcher("/");
				request.setAttribute("loginfail", "Your credentials are wrong. Please try again");
				
				fail.forward(request, response);
			}
			if(auth!=null && auth.equals("volunteer")){
				HttpSession session = request.getSession();
				session.setAttribute("usertype", auth);
				

				Volunteer vol = VolunteerDAO.findVolunteerByEmail(email);
				
				session.setAttribute("email", vol.getEmail());
				session.setAttribute("username",vol.getUsername());
				session.setAttribute("firstname", vol.getFirstName());
				session.setAttribute("lastname", vol.getLastName());
				session.setAttribute("avatar", vol.getAvatar());
				
				RequestDispatcher success = request.getRequestDispatcher("/");
				success.forward(request, response);
			}
			if(auth!=null && auth.equals("organization")){
				HttpSession session = request.getSession();
				session.setAttribute("usertype", auth);
				
				Organization org = OrganizationDAO.findOrganizationByEmail(email);
				
				session.setAttribute("email", org.getEmail());
				session.setAttribute("name",org.getUsername());
				session.setAttribute("description", org.getDescription());
				session.setAttribute("orgtype", org.getType());
				session.setAttribute("avatar", org.getAvatar());
				
				RequestDispatcher success = request.getRequestDispatcher("/");
				success.forward(request, response);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
