package org.ffcc.communityVessells.app.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.ffcc.communityVessells.app.dao.RequestDAO;
import org.ffcc.communityVessells.app.models.Repository;
import org.ffcc.communityVessells.app.models.Request;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateRequestController
 */
@WebServlet("/createrequest")
@MultipartConfig
public class CreateRequestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateRequestController() {
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
		Repository thisRepo = (Repository) session.getAttribute("thisRepo");
		boolean imgError=false;
		boolean dateError=false;
		//Upload image using MultipartConfig and Servlet Api 3.0
		
		String destinationRealPath="C:/Users/user/eclipseWorkspace/CommunityVessellsApp/WebContent/Images/UserImages";

		//read file name and content from form
		Part filePart = request.getPart("file");
		String avatarPath= null;
		if (filePart.getSize()>0) {	
			String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
		
			InputStream fileContent = filePart.getInputStream();
			File upload = new File(destinationRealPath);
			File avatar = new File(upload, fileName);
			try {
				//store file into destination path
				Files.copy(fileContent, avatar.toPath());
			} catch (Exception e) {
				request.setAttribute("errormessage", "Image could not be saved.Please try again.");
				RequestDispatcher error = request.getRequestDispatcher(response.encodeURL ("repository.jsp?repoID="+Integer.toString(thisRepo.getRepoID()))); 
				error.forward(request, response);
				imgError=true;
			}
			
			avatarPath= "Images/UserImages/"+avatar.getName();
		}
		
		String title=request.getParameter("reqtitle");
		String startDate=request.getParameter("startdate");
		String closeDate=request.getParameter("closedate");
		String address=request.getParameter("address");
		
		if(startDate==null || closeDate==null){
			request.setAttribute("errormessage", "You cannot create a Request without starting and closing dates.Please Try Again");
			RequestDispatcher error = request.getRequestDispatcher(response.encodeURL ("repository.jsp?repoID="+Integer.toString(thisRepo.getRepoID()))); 
			error.forward(request, response);
			dateError=true;
		}
		
		//convert String date from jQuery UI calendar into SDF->java.util.Date->java.sql.Date
        java.util.Date utilDateStart = null;
        java.util.Date utilDateClose = null;
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); //MM is for month.don't use mm
	        format.setLenient(false);

	        utilDateStart = format.parse(startDate);
	        utilDateClose = format.parse(closeDate);
		} catch (ParseException e1) {
			
			e1.printStackTrace();
		}
		java.sql.Date sqlDateStart = new java.sql.Date(utilDateStart.getTime());
		java.sql.Date sqlDateClose = new java.sql.Date(utilDateClose.getTime());
		
		//check if close date is after start date
		if(sqlDateStart.after(sqlDateClose)){
			request.setAttribute("errormessage", "Request Closing Date is before Starting Date.Please Try Again");
			RequestDispatcher error = request.getRequestDispatcher(response.encodeURL ("repository.jsp?repoID="+Integer.toString(thisRepo.getRepoID()))); 
			error.forward(request, response);
			dateError=true;
		}
		RequestDAO reqDAO=new RequestDAO();
		Request thisReq = new Request(title,sqlDateStart,sqlDateClose,address,avatarPath,false,thisRepo.getRepoID());
		if(!dateError && !imgError){
			try {
				reqDAO.createRequest(thisReq);
				session.setAttribute("successmessage","Request"+" "+title+" has been successfully saved");
				response.sendRedirect(response.encodeURL ("repository.jsp?repoID="+Integer.toString(thisRepo.getRepoID())));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.setAttribute("errormessage", "Request"+ title +"could not be saved.Please Try Again");
				RequestDispatcher error = request.getRequestDispatcher(response.encodeURL ("repository.jsp?repoID="+Integer.toString(thisRepo.getRepoID()))); 
				error.forward(request, response);
			}
		}
	}

}
