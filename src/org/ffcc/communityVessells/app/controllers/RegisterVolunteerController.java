package org.ffcc.communityVessells.app.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

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

import org.ffcc.communityVessells.app.dao.VolunteerDAO;
import org.ffcc.communityVessells.app.models.Volunteer;

/**
 * Servlet implementation class RegisterVolunteerController
 */
@WebServlet("/registervolunteer")
@MultipartConfig
public class RegisterVolunteerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterVolunteerController() {
		super();

	}

	/**
	 * @throws IOException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 
	 * @throws IOException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
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
				request.setAttribute("errormessage", session.getAttribute("email")+" could not be registered.Please try again.");
				RequestDispatcher error = request.getRequestDispatcher("/"); 
				error.forward(request, response);
			}
			
			avatarPath= "/CommunityVessellsApp/Images/UserImages/"+avatar.getName();
			
		}
		String username = request.getParameter("username");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");

		VolunteerDAO volToDb = new VolunteerDAO();
		Volunteer createVol = new Volunteer((String)session.getAttribute("email"),(String)session.getAttribute("hash") , username, avatarPath);
		//optional could use overloaded constructor
		createVol.setFirstName(firstname);
		createVol.setLastName(lastname);
		//send messages to alert on homepage and redirect.
		try{
			volToDb.saveVolunteer(createVol);
			request.setAttribute("successmessage", session.getAttribute("email")+" "+username+" has been successfully registered");
			
			RequestDispatcher success = request.getRequestDispatcher("/"); 
			success.forward(request, response);
		}
		catch(Exception e){
			e.printStackTrace();
			request.setAttribute("errormessage", session.getAttribute("email")+" "+username+" could not be registered.Please try again.");
			RequestDispatcher error = request.getRequestDispatcher("/"); 
			error.forward(request, response);
		}
		
	}
}
