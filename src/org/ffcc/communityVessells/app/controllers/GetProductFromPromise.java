package org.ffcc.communityVessells.app.controllers;

import java.io.IOException;
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
		java.sql.Date sqlDateStored = new java.sql.Date(new java.util.Date().getTime());
		
		if(thisPromise.getProdType().equals("Food Products")){
			
			thisProduct.setProdFood(thisPromise.getExpire());
		}
		if(thisPromise.getProdType().equals("Pharmaceuticals")){
			thisProduct.setProdPharm(thisPromise.getExpire());
		}
		if(thisPromise.getProdType().equals("Clothing")){
			thisProduct.setProdClothing(thisPromise.getCondition(), thisPromise.getSize());
		}
		if(thisRepo.getAvailableProducts()+thisPromise.getCount()>thisRepo.getCapacity()){
			request.setAttribute("errormessage", "Products stored cannot exceed Repository "+thisRepo.getTitle()+" capacity: "+thisRepo.getCapacity());
			RequestDispatcher error = request.getRequestDispatcher(response.encodeURL ("request.jsp?repoID="+Integer.toString(thisRepo.getRepoID()))+"&requestID="+Integer.toString(thisPromise.getRequestID())); 
			error.forward(request, response);
		}
		else{
		
			thisProduct.setDateStored(sqlDateStored);
			thisProduct.setAvailable();
			thisRepo.setAvailableProducts(thisRepo.getAvailableProducts()+thisProduct.getCount());
			ProductDAO prodDAO = new ProductDAO();
			RepositoryDAO repoDAO = new RepositoryDAO();
			try {
				prodDAO.saveProductAllFields(thisProduct);
				repoDAO.updateRepository(thisRepo);
				PromiseDAO.setPromiseFulfilled(promiseID);
				session.setAttribute("successmessage","Promise"+" "+thisPromise.getTitle()+" has been successfully fulfilled");
				response.sendRedirect(response.encodeURL ("request.jsp?repoID="+Integer.toString(thisRepo.getRepoID()))+"&requestID="+Integer.toString(thisPromise.getRequestID()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.setAttribute("errormessage", "There has been an error while saving product from promise.Please try again.");
				RequestDispatcher error = request.getRequestDispatcher(response.encodeURL ("request.jsp?repoID="+Integer.toString(thisRepo.getRepoID()))+"&requestID="+Integer.toString(thisPromise.getRequestID())); 
				error.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
