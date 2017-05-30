package org.ffcc.communityVessells.app.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		
		ServletContext app = request.getServletContext();
		String destinationRealPath=app.getRealPath("/Images/UserImages");
		Part filePart = request.getPart("file");
		String avatarPath= null;
		if (filePart.getSize()>0) {	
			String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
		
			InputStream fileContent = filePart.getInputStream();
			File upload = new File(destinationRealPath);
			File avatar = new File(upload, fileName);
			try {
				Files.copy(fileContent, avatar.toPath());
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(avatar.getPath());
			avatarPath= avatar.getPath();
		}
		String username = request.getParameter("username");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");

		VolunteerDAO volToDb = new VolunteerDAO();
		Volunteer createVol = new Volunteer((String)session.getAttribute("email"),(String)session.getAttribute("hash") , username, avatarPath);
		System.out.println(session.getAttribute("hash"));
		System.out.println(session.getAttribute("email"));
		createVol.setFirstName(firstname);
		createVol.setLastName(lastname);
		try{
			volToDb.saveVolunteer(createVol);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
