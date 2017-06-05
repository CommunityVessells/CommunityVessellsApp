package org.ffcc.communityVessells.app.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ffcc.communityVessells.app.dao.ProductDAO;
import org.ffcc.communityVessells.app.models.Product;
import org.ffcc.communityVessells.app.models.Repository;

/**
 * Servlet implementation class GetProductsController
 */
@WebServlet("/getproducts")
public class GetProductsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetProductsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		Repository thisRepo = (Repository) session.getAttribute("thisRepo");
		LinkedList<Product> allProducts = null;
		ProductDAO productDAO= new ProductDAO(); 
		try {
			 allProducts = productDAO.getProductsAllFields(thisRepo.getRepoID());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PrintWriter out = new PrintWriter(response.getWriter());
		out.println("						<tr class='default-primary-color text-primary-color well'>");
		out.println("							<td class='sort'><b>Column heading</b></td>");
		out.println("							<td><b>Column heading</b></a></td>");
		out.println("							<td><b>Column heading</b></td>");
		out.println("							<td><b>Condition</b></td>");
		out.println("							<td><b>Column heading</b></td>");
		out.println("							<td><b>Date Stored</b></td>");
		out.println("						</tr>");
		
		for(Product p:allProducts){
			if(p.isPromised()){
				out.println("    <tr class='warning'>");
			}
			else if(!p.isPromised() && p.hasExpired(new java.sql.Date(new java.util.Date().getTime()))) {
				out.println("    <tr class='danger'>");
			}
			else if(!p.isPromised() && p.isFubar("Fubar")) {
				out.println("    <tr class='danger'>");
			}
			else {
				out.println("    <tr class='success'>");
			}
			out.println("    <td>"+p.getProdID()+"</td>");
			out.println("    <td>"+p.getTitle()+"</td>");
			out.println("    <td>"+p.getSize()+"</td>");
			out.println("    <td>"+p.getCondition()+"</td>");
			out.println("    <td>"+(p.isPromised() ? "yes" : "no" )+"</td>");
			out.println("    <td>"+(p.getDateStored()!=null ? p.getDateStored() : "Not Available" )+"</td>");
			out.println("  </tr>");
		}
		

		
	}

}
