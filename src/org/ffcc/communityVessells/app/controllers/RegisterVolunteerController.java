package org.ffcc.communityVessells.app.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ffcc.communityVessells.app.encryption.EncryptMD5;

/**
 * Servlet implementation class RegisterVolunteerController
 */
@WebServlet("/registervolunteer")
public class RegisterVolunteerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterVolunteerController() {
        super();
        
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
		
		
		HttpSession session = request.getSession(true);
		System.out.println((String)session.getAttribute("hash"));
		
		System.out.println(request.getParameter("email"));
		
		if(EncryptMD5.authenticateUser((String)request.getParameter("password"), (String)session.getAttribute("hash"))){
			System.out.println((String)request.getParameter("password"));
		}
	}

}
