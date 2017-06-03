package org.ffcc.communityVessells.app.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ffcc.communityVessells.app.dao.RepositoryDAO;
import org.ffcc.communityVessells.app.models.Organization;
import org.ffcc.communityVessells.app.models.Repository;

/**
 * Servlet implementation class CreateRepo
 */
@WebServlet("/createrepo")
public class CreateRepo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateRepo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		String title = request.getParameter("title");		
		String type = request.getParameter("repoType");
		
		//send error message if capacity | available are empty
		if(request.getParameter("capacity").isEmpty() ){
			RequestDispatcher error = request.getRequestDispatcher("/organization.jsp");
			request.setAttribute("errormessage", "You have not set Capacity");
			error.forward(request, response);
		}

		int capacity = Integer.parseInt(request.getParameter("capacity"));		
		//check if available>capacity

		Organization org = (Organization) session.getAttribute("organization");
		
		Repository newRepo = new Repository(title,type,capacity,0,org.getOrgID());
		RepositoryDAO repoDAO = new RepositoryDAO();
		try{
			repoDAO.saveRepository(newRepo);
			session.setAttribute("successmessage","Repository"+" "+title+" has been successfully saved");
			
			//RequestDispatcher success = request.getRequestDispatcher("/organization.jsp"); 
			response.sendRedirect("organization.jsp");
			
			
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("errormessage", "Repository"+" "+title+" could not be saved.Please try again.");
			RequestDispatcher error = request.getRequestDispatcher("/organization.jsp"); 
			error.forward(request, response);
		}
	}

}
