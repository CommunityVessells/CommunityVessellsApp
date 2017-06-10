package org.ffcc.communityVessells.app.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ffcc.communityVessells.app.dao.PromiseDAO;
import org.ffcc.communityVessells.app.models.Promise;
import org.ffcc.communityVessells.app.models.Request;
import org.ffcc.communityVessells.app.models.Volunteer;

/**
 * Servlet implementation class CreatePromiseController
 */
@WebServlet("/savepromise")
public class CreatePromiseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreatePromiseController() {
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
		Volunteer vol=(Volunteer) session.getAttribute("volunteer");
		Request req= (Request) session.getAttribute("request");

		String title= request.getParameter("title");
		int count =Integer.parseInt(request.getParameter("quantity"));
		String prodType=request.getParameter("prodType");
		java.sql.Date sqlDateExpire=null;
		String condition=null;
		String size=null;
		
		if(request.getParameter("expire")==null && (prodType.equals("Food Products")||prodType.equals("Pharmaceuticals"))) {
			request.setAttribute("errormessage", "Storing or promising products without expiring date is not allowed");
			RequestDispatcher error = request.getRequestDispatcher(response.encodeURL ("promise.jsp?requestID="+Integer.toString(req.getRequestID()))); 
			error.forward(request, response);
		}
		
		if(request.getParameter("expire")!=null){
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
			sqlDateExpire = new java.sql.Date(utilDateExpire.getTime());
		}
		if(prodType.equals("Clothing")){
			condition=request.getParameter("condition");
			size=request.getParameter("size");
		}
		
		Promise thisPromise=null;
		
			//thisPromise = new Promise(req.getRequestID(),vol.getUserID(),vol.getEmail(),false,title,prodType,count,sqlDateExpire,size,condition);

			if(sqlDateExpire==null){
				thisPromise = new Promise(req.getRequestID(),vol.getUserID(),vol.getEmail(),false,title,prodType,count,size,condition);
			}
			else if(size==null || condition==null){
				thisPromise = new Promise(req.getRequestID(),vol.getUserID(),vol.getEmail(),false,title,prodType,count,sqlDateExpire);
			}
		
		PromiseDAO promiseDAO = new PromiseDAO();
		try {
			promiseDAO.savePromise(thisPromise);
			session.setAttribute("successmessage","Promise"+" "+title+" by "+ vol.getEmail() +" has been successfully saved");
			response.sendRedirect(response.encodeURL ("promise.jsp?requestID="+Integer.toString(req.getRequestID())));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("errormessage", "Promise"+" "+title+" by "+ vol.getEmail() +" could not be saved.Please try again.");
			RequestDispatcher error = request.getRequestDispatcher(response.encodeURL ("promise.jsp?requestID="+Integer.toString(req.getRequestID()))); 
			error.forward(request, response);
		}
	}

}
