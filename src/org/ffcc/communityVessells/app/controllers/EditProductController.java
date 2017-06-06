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
import org.ffcc.communityVessells.app.dao.RepositoryDAO;
import org.ffcc.communityVessells.app.models.Product;
import org.ffcc.communityVessells.app.models.Repository;

/**
 * Servlet implementation class EditProductController
 */
@WebServlet("/editproduct")
public class EditProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProductController() {
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
		//RepositoryDAO repoDAO = new RepositoryDAO();
		
			boolean isFull=false;
			Repository thisRepo=null;
			Product thisProduct=null;
			 
			try {
				thisRepo = RepositoryDAO.getRepositoryByID(Integer.parseInt(request.getParameter("repoID")));
				thisProduct=ProductDAO.getProductByID(Integer.parseInt(request.getParameter("prodID"))); 
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			if(request.getParameter("store").equals("true")){
				
				thisRepo.setAvailableProducts(thisRepo.getAvailableProducts()+thisProduct.getCount());
				RepositoryDAO repoDAO = new RepositoryDAO();
				ProductDAO prodDAO = new ProductDAO();
				if(thisRepo.getAvailableProducts()>thisRepo.getCapacity()){
					thisRepo.setAvailableProducts(thisRepo.getAvailableProducts()-thisProduct.getCount());
					request.setAttribute("errormessage", "Products stored cannot exceed Repository "+thisRepo.getTitle()+" capacity: "+thisRepo.getCapacity());
					isFull=true;
					RequestDispatcher error = request.getRequestDispatcher(response.encodeURL ("repository.jsp?repoID="+Integer.toString(thisRepo.getRepoID()))); 
					error.forward(request, response);
				}
				
				if(!isFull){
					try {
						prodDAO.setAvailable(thisProduct);
						java.sql.Date sqlDateStored = new java.sql.Date(new java.util.Date().getTime());
						thisProduct.setDateStored(sqlDateStored);
						prodDAO.setDateStored(thisProduct, sqlDateStored);
						
						repoDAO.updateRepository(thisRepo);
						
						session.setAttribute("successmessage","Product"+" "+thisProduct.getTitle()+" has been successfully saved");
						response.sendRedirect(response.encodeURL ("repository.jsp?repoID="+Integer.toString(thisRepo.getRepoID())));
					} catch (Exception e) {
						
						e.printStackTrace();
						request.setAttribute("errormessage", "Product"+" "+thisProduct.getTitle()+" could not be saved.Please try again.");
						RequestDispatcher error = request.getRequestDispatcher(response.encodeURL ("repository.jsp?repoID="+Integer.toString(thisRepo.getRepoID()))); 
						error.forward(request, response);
					}
				}
			}
			
			if(request.getParameter("remove").equals("true")){
				thisRepo.setAvailableProducts(thisRepo.getAvailableProducts()-thisProduct.getCount());
				RepositoryDAO repoDAO = new RepositoryDAO();
				
				try {
					ProductDAO.deleteProductByID(thisProduct.getProdID());
					repoDAO.updateRepository(thisRepo);
					session.setAttribute("successmessage","Product"+" "+thisProduct.getTitle()+" has been successfully deleted");
					response.sendRedirect(response.encodeURL ("repository.jsp?repoID="+Integer.toString(thisRepo.getRepoID())));
				} catch (Exception e) {
					
					e.printStackTrace();
					request.setAttribute("errormessage", "Product"+" "+thisProduct.getTitle()+" could not be deleted.Please try again.");
					RequestDispatcher error = request.getRequestDispatcher(response.encodeURL ("repository.jsp?repoID="+Integer.toString(thisRepo.getRepoID()))); 
					error.forward(request, response);
				}
			}
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
		

	}

}
