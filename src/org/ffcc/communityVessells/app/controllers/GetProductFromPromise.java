package org.ffcc.communityVessells.app.controllers;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ffcc.communityVessells.app.dao.ProductDAO;
import org.ffcc.communityVessells.app.dao.PromiseDAO;
import org.ffcc.communityVessells.app.dao.RepositoryDAO;
import org.ffcc.communityVessells.app.dao.RequestDAO;
import org.ffcc.communityVessells.app.models.Product;
import org.ffcc.communityVessells.app.models.Promise;
import org.ffcc.communityVessells.app.models.Repository;
import org.ffcc.communityVessells.app.models.Request;

/**
 * Servlet implementation class GetProductFromPromise
 */
@WebServlet("/getproductfrompromise")
public class GetProductFromPromise extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetProductFromPromise() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		
		int requestID=Integer.parseInt(request.getParameter("requestID"));
		int repoID=Integer.parseInt(request.getParameter("repoID"));
		int promiseID=Integer.parseInt(request.getParameter("promiseID"));
		Repository thisRepo = null;
		Request thisRequest = null;
		Promise thisPromise = null;
		Product thisProduct = null;
		
		try {
			thisRepo = RepositoryDAO.getRepositoryByID(Integer.parseInt(request.getParameter("repoID")));
		}catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		RequestDAO reqDAO = new RequestDAO();
		try {
			thisRequest = reqDAO.getRequestByID(requestID);
			thisPromise = PromiseDAO.getPromiseByID(promiseID);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		thisProduct = new Product(thisPromise.getTitle(),thisRepo.getRepoID(),thisPromise.getCount());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
