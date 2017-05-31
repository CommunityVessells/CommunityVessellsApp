package org.ffcc.communityVessells.app.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.ffcc.communityVessells.app.dao.OrganizationDAO;
import org.ffcc.communityVessells.app.models.Organization;

/**
 * Servlet implementation class RegisterOrganizationController
 */
@WebServlet("/registerorganization")
public class RegisterOrganizationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterOrganizationController() {
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
		
		//Upload image using MultipartConfig and Servlet Api 3.0
		
		ServletContext app = request.getServletContext();
		String destinationRealPath=app.getRealPath("/Images/UserImages");
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
			
			avatarPath= avatar.getPath();
		}
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String type = request.getParameter("type");

		OrganizationDAO orgToDb = new OrganizationDAO();
		Organization createOrg = new Organization((String)session.getAttribute("email"),(String)session.getAttribute("hash") , name, avatarPath);
		//optional could use overloaded constructor
		createOrg.setDescription(description);
		createOrg.setType(type);
		//send messages to alert on homepage and redirect.
		try{
			orgToDb.saveOrganization(createOrg);
			request.setAttribute("successmessage", session.getAttribute("email")+" "+name+" has been successfully registered");
			
			RequestDispatcher success = request.getRequestDispatcher("/"); 
			success.forward(request, response);
		}
		catch(Exception e){
			e.printStackTrace();
			request.setAttribute("errormessage", session.getAttribute("email")+" "+name+" could not be registered.Please try again.");
			RequestDispatcher error = request.getRequestDispatcher("/"); 
			error.forward(request, response);
		}
	}

}
