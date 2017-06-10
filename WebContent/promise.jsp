<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="org.ffcc.communityVessells.app.models.Volunteer"%>
<%@ page import="org.ffcc.communityVessells.app.dao.VolunteerDAO"%>
<%@ page import="org.ffcc.communityVessells.app.models.Promise"%>
<%@ page import="org.ffcc.communityVessells.app.models.Request"%>
<%@ page import="org.ffcc.communityVessells.app.models.Repository"%>
<%@ page import="org.ffcc.communityVessells.app.dao.RepositoryDAO"%>
<%@ page import="org.ffcc.communityVessells.app.dao.PromiseDAO"%>
<%@ page import="org.ffcc.communityVessells.app.dao.RequestDAO"%>
<%@ page import="java.util.LinkedList"%>
<%@ page errorPage="error.jsp" %>
<% if(session.getAttribute("usertype")!=null && session.getAttribute("usertype").equals("volunteer")) { %>
<!DOCTYPE html>
<html lang="en">
<%@ include file="static_resources/head.html" %>

<body id="comVessells" data-spy="scroll" data-target=".navbar" data-offset="60">
<%@ include file="static_resources/signupModal.html" %>

<%@ include file="static_resources/loginModal.html" %>

<!-- Navbar start -->
<%@ include file="navbar_vol.jsp" %>
<!-- Navbar end-->

<div class="container-fluid">
							<%
								RequestDAO requestdao = new RequestDAO();
								Request thisRequest = requestdao.getRequestByID(Integer.parseInt(request.getParameter("requestID")));
								session.setAttribute("request",thisRequest);								
							%>
<!-- Alerts Start -->
		<%
			if (session.getAttribute("successmessage") != null) {
		%>
		<div class="panel panel-success">
			<div class="panel-heading">
				<h3 class="panel-title text-center" id="panelTitle">Success!</h3>
			</div>
			<div class="panel-body">
				<p class="text-center text-success"><%=(String) session.getAttribute("successmessage")%></p>

			</div>
		</div>


		<%
			}
				session.removeAttribute("successmessage");
		%>


		<%
			if (request.getAttribute("errormessage") != null) {
		%>


		<div class="panel panel-danger">
			<div class="panel-heading">
				<h3 class="panel-title text-center" id="panelTitle">Promised failed to save!</h3>
			</div>
			<div class="panel-body">
				<p class="text-center text-danger"><%=(String) request.getAttribute("errormessage")%></p>
			</div>
		</div>





		<%
			}
		%>

<!-- Alerts End -->
					<div class="well container">
					<!--form start-->
					<form class="form-horizontal" action="savepromise" method="post" >
					
						<fieldset>
						<legend class="anton text-right">Promise a Product</legend>
	
						
						<div class="form-group">
						<h3 class="lobster text-accent-color text-center"><%=thisRequest.getTitle() %></h3>
							<label for="inputEmail3" class="col-sm-2 control-label">Email</label>
							<div class="col-sm-10">
								<input type="text" name="email" class="form-control" id="inputEmail3" placeholder="Email" value="<%=session.getAttribute("email") %>" readonly>
							</div>
						</div>
						<div class="form-group"  >
							<label for="title" class="col-sm-2 control-label">Title</label>
							<div class="col-sm-10">
								<input type="text" name="title" class="form-control" id="title" placeholder="Title">
							</div>
						</div>
						
						
						<div class="form-group"  >
							<label for="prodType" class="col-sm-2 control-label">Product Type</label>
							<div class="col-sm-10">
								<input type="text" name="prodType" class="form-control" id="prodType" value="<%=RepositoryDAO.getRepositoryByID(thisRequest.getReposID()).getRepoType() %>" readonly>
							</div>
						</div>
						
						
						<div class="form-group">
						
							<label for="quantity" class="control-label col-md-2">Quantity </label>
							<div class="col-sm-10">
								<input type="number" name="quantity" min="1" class="form-control" id="quantity">
							</div>
						</div>
												<%
						if((RepositoryDAO.getRepositoryByID(thisRequest.getReposID()).getRepoType().equals("Food Products"))||(RepositoryDAO.getRepositoryByID(thisRequest.getReposID()).getRepoType().equals("Pharmaceuticals"))) { 
						%>
						
						<div class="form-group">
						
							<label for="datepicker" class="control-label col-md-2">Expiry Date </label>
							<div class="col-sm-10">
								<input type="text" name="expire" id="datepicker" class="form-control" required>

							</div>
						</div>
						<% } %>
						
						<% if(RepositoryDAO.getRepositoryByID(thisRequest.getReposID()).getRepoType().equals("Clothing")) { %>
						<div class="form-group">
						<label for="size" class="col-md-2 control-label">Select Size<span class="caret"></span></label>
						
						<div class="col-md-10">
							<select name="size" id="size" class="form-control" required>
								
								<option>XL</option>
								<option>L</option>
								<option>M</option>
								<option>S</option>
								<option>XS</option>
							</select>
							
						</div>
							

						</div>
						
						<div class="form-group">
						<label for="condition" class="col-md-2 control-label">Item Condition<span class="caret"></span></label>
						
						<div class="col-md-10">
							<select name="condition" id="condition" class="form-control" required>
								
								<option>Mint</option>
								<option>Near-Mint</option>
								<option>Very-Good</option>
								<option>Good</option>
								<option>Poor</option>
								<option>Fubar</option>
							</select>
							
						</div>
							

						</div>
						<% } %>
							


						<div class="form-group">
							<div class="col-sm-offset-2 col-md-10">
								<button type="submit" class="btn btn-primary">Save</button>
								<button type="reset" class="btn btn-default">Reset</button>
							</div>
						</div>
					</fieldset>
					
					</form>
					<!--form end-->
					</div>

</div>

<%@ include file="static_resources/footer.html" %>

<%@ include file="static_resources/scriptIncludes.html" %>

<%@ include file="static_resources/initScript.html" %>
<%@ include file="static_resources/datepicker.html"%>


</body>
</html>
<% } %>
<% if(session.getAttribute("usertype")==null){ %>
<jsp:forward page="/"></jsp:forward>
<% } %>