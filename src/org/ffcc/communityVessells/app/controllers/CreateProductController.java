package org.ffcc.communityVessells.app.controllers;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

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
 * Servlet implementation class CreateProductController
 */
@WebServlet("/createproduct")
public class CreateProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateProductController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
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
		boolean isFull=false;
		
		Repository thisRepo = (Repository) session.getAttribute("thisRepo");
		String title = request.getParameter("prodtitle");
		int count =Integer.parseInt(request.getParameter("quantity"));
		String promised=request.getParameter("promised");

		ProductDAO prodDAO= new ProductDAO();
		RepositoryDAO repoDAO = new RepositoryDAO();
		
		Product thisProduct = new Product(title,thisRepo.getRepoID(),count);
		//convert time of creation from java.util.Date->java.sql.Date
		java.sql.Date sqlDateStored = null;
		
		if(request.getParameter("expire")==null && (thisRepo.getRepoType().equals("Food Products")||thisRepo.getRepoType().equals("Pharmaceuticals"))) {
			request.setAttribute("errormessage", "Storing or promising products without expiring date is not allowed");
			RequestDispatcher error = request.getRequestDispatcher(response.encodeURL ("repository.jsp?repoID="+Integer.toString(thisRepo.getRepoID()))); 
			error.forward(request, response);
		}
		
		//if the product is not promised store into available products and set dateStored.
		if(promised!=null && promised.equals("promised")) {thisProduct.setPromised();}		
		else {
			
			
			thisRepo.setAvailableProducts(thisRepo.getAvailableProducts()+count);
			if(thisRepo.getAvailableProducts()>thisRepo.getCapacity()){
				thisRepo.setAvailableProducts(thisRepo.getAvailableProducts()-count);
				request.setAttribute("errormessage", "Products stored cannot exceed Repository "+thisRepo.getTitle()+" capacity: "+thisRepo.getCapacity());
				isFull=true;
				RequestDispatcher error = request.getRequestDispatcher(response.encodeURL ("repository.jsp?repoID="+Integer.toString(thisRepo.getRepoID()))); 
				error.forward(request, response);
			}
			sqlDateStored = new java.sql.Date(new java.util.Date().getTime());
			thisProduct.setDateStored(sqlDateStored);
			try {
				repoDAO.updateRepository(thisRepo);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//check for repoType -> update product type and set operations accordingly 
		if(thisRepo.getRepoType().equals("Food Products") && request.getParameter("expire")!=null && !isFull){
			String expire=request.getParameter("expire");
			java.util.Date utilDateExpire = null; 
			//convert String date from jQuery UI calendar into SDF->java.util.Date->java.sql.Date
			try {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); //MM is for month.don't use mm
		        format.setLenient(false);
				utilDateExpire = format.parse(expire);
				//System.out.println(utilDateExpire);
			} catch (ParseException e1) {
				
				e1.printStackTrace();
			}
			java.sql.Date sqlDateExpire = new java.sql.Date(utilDateExpire.getTime());

			thisProduct.setProdFood(sqlDateExpire);
			try {
				prodDAO.saveProduct(thisProduct);
				
				//TODO find method in DAO to get product id after saved and update thisProduct.
				LinkedList<Product> prodList=prodDAO.getProducts(thisRepo.getRepoID());
				Product currentProduct=prodList.pollLast();
				
				prodDAO.setProductTypeFood(currentProduct, sqlDateExpire);
				if(sqlDateStored!=null){
					prodDAO.setDateStored(currentProduct, sqlDateStored);
				}
				session.setAttribute("successmessage","Product"+" "+title+" has been successfully saved");
				response.sendRedirect(response.encodeURL ("repository.jsp?repoID="+Integer.toString(thisRepo.getRepoID())));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.setAttribute("errormessage", "Product"+" "+title+" could not be saved.Please try again.");
				RequestDispatcher error = request.getRequestDispatcher(response.encodeURL ("repository.jsp?repoID="+Integer.toString(thisRepo.getRepoID()))); 
				error.forward(request, response);
			}
		}
		
		if(thisRepo.getRepoType().equals("Pharmaceuticals") && request.getParameter("expire")!=null && !isFull){
			String expire=request.getParameter("expire");
			java.util.Date utilDateExpire = null; 
			//convert String date from jQuery UI calendar into SDF->java.util.Date->java.sql.Date
			try {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); //MM is for month.don't use mm
		        format.setLenient(false);
				utilDateExpire = format.parse(expire);
				//System.out.println(utilDateExpire);
			} catch (ParseException e1) {
				
				e1.printStackTrace();
			}
			java.sql.Date sqlDateExpire = new java.sql.Date(utilDateExpire.getTime());

			thisProduct.setProdFood(sqlDateExpire);
			try {
				prodDAO.saveProduct(thisProduct);
				
				//TODO find method in DAO to get product id after saved and update thisProduct.
				LinkedList<Product> prodList=prodDAO.getProducts(thisRepo.getRepoID());
				Product currentProduct=prodList.pollLast();
				
				prodDAO.setProductTypePharm(currentProduct, sqlDateExpire);
				if(sqlDateStored!=null){
					prodDAO.setDateStored(currentProduct, sqlDateStored);
				}
				session.setAttribute("successmessage","Product"+" "+title+" has been successfully saved");
				response.sendRedirect(response.encodeURL ("repository.jsp?repoID="+Integer.toString(thisRepo.getRepoID())));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.setAttribute("errormessage", "Product"+" "+title+" could not be saved.Please try again.");
				RequestDispatcher error = request.getRequestDispatcher(response.encodeURL ("repository.jsp?repoID="+Integer.toString(thisRepo.getRepoID()))); 
				error.forward(request, response);
			}
		}
		
		if(thisRepo.getRepoType().equals("Clothing") && !isFull){
			String condition=request.getParameter("condition");
			String size=request.getParameter("size");
			

			
			try {
				prodDAO.saveProduct(thisProduct);
				
				//TODO find method in DAO to get product id after saved and update thisProduct.
				LinkedList<Product> prodList=prodDAO.getProducts(thisRepo.getRepoID());
				Product currentProduct=prodList.pollLast();
				
				prodDAO.setProductTypeClothing(currentProduct,condition,size);
				if(sqlDateStored!=null){
					prodDAO.setDateStored(currentProduct, sqlDateStored);
				}
				session.setAttribute("successmessage","Product"+" "+title+" has been successfully saved");
				response.sendRedirect(response.encodeURL ("repository.jsp?repoID="+Integer.toString(thisRepo.getRepoID())));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.setAttribute("errormessage", "Product"+" "+title+" could not be saved.Please try again.");
				RequestDispatcher error = request.getRequestDispatcher(response.encodeURL ("repository.jsp?repoID="+Integer.toString(thisRepo.getRepoID()))); 
				error.forward(request, response);
			}
		}
		
	}

}
