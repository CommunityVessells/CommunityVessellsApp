package org.ffcc.communityVessells.app.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		String title = request.getParameter("title");		
		String type = request.getParameter("repoType");
		int capacity = Integer.parseInt(request.getParameter("capacity"));
		int availableProducts = Integer.parseInt(request.getParameter("available"));
		
		if(availableProducts>capacity){
			RequestDispatcher error = request.getRequestDispatcher("/organization.jsp");
			request.setAttribute("errormessage", "Available Products cannot exceed Repository Capacity");
			error.forward(request, response);
		}
		//RepositoryDAO repoDAO = new RepositoryDAO();
		//Repository newRepo = new Repository(title,type,capacity,availableProducts);
	}

}
