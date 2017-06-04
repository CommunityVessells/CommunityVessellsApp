<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.ffcc.communityVessells.app.models.Organization"%>
<%@ page import="org.ffcc.communityVessells.app.models.Repository"%>
<%@ page import="org.ffcc.communityVessells.app.dao.RepositoryDAO"%>
<%@ page import="java.util.LinkedList"%>
<%@ page errorPage="error.jsp"%>
<%
	if (session.getAttribute("usertype") != null && session.getAttribute("usertype").equals("organization")) {
%>
<!DOCTYPE html>
<html lang="en">
<%@ include file="static_resources/head.html"%>

<body id="org" data-spy="scroll" data-target=".navbar"	data-offset="60">
	<%
		Organization temp = (Organization) session.getAttribute("organization");
		Repository thisRepo = RepositoryDAO.getRepositoryByID(Integer.parseInt(request.getParameter("repoID")));
		session.setAttribute("thisRepo",thisRepo);
	%>
	<%@ include file="static_resources/loginModal.html"%>
	<%@ include file="navbar_org.jsp"%>

	<div class="container-fluid">
	
	<!-- Repository title -->
	<div class="row-content text-center">
		<h3 class=""><small class="text-accent-color maintext" style="font-family:'Anton', sans-serif;">Repository Details</small></h3>
		<p class="maintext"><b>Title: </b><%=thisRepo.getTitle()%></p>
		<p class="maintext"><b>Type: </b><%=thisRepo.getRepoType()%></p>
		<p class="maintext"><b>Capacity: </b><%=thisRepo.getCapacity()%></p>
		<p class="maintext"><b>Available Products: </b><%=thisRepo.getAvailableProducts()%></p>
	</div>
	<hr class="divider-color">
	<!-- Store new product -->
			<div class="row-content text-center">
				<h4 class="anton">
					<small>Store New Product</small>
				</h4>
				<button class="btn btn-default btn-raised light-primary-color"
					id="createProd">
					<span class="glyphicon glyphicon-plus" id="symbolrepo"></span>
				</button>

				<div class="well hide" id="newProd">
	
					<!--form start-->

					<form class="form-horizontal" action="createproduct" method="post">
						<fieldset>
						
						<div class="form-group">
						
							<label for="prodtitle" class="control-label col-md-2">Title  </label>
							<div class="col-sm-10">
								<input type="text" name="prodtitle" class="form-control" id="prodtitle" placeholder="Product Title">
							</div>
						</div>
						
						<div class="form-group">
						
							<label for="quantity" class="control-label col-md-2">Quantity </label>
							<div class="col-sm-10">
								<input type="number" name="quantity" min="1" class="form-control" id="quantity">
							</div>
						</div>
						
						
						
							
          				<div class="form-group">	 
						<div class="checkbox">
          					<label>
            					<input type="checkbox" id="promised" name="promised" value="promised"> Promised Product
          					</label>
						</div>
						</div>
						
						
						<%
						if(thisRepo.getRepoType().equals("Food Products") || thisRepo.getRepoType().equals("Pharmaceuticals") ) { 
						%>
						
						<div class="form-group">
						
							<label for="datepicker" class="control-label col-md-2">Expiry Date </label>
							<div class="col-sm-10">
								<input type="text" name="expire" id="datepicker" class="form-control">

							</div>
						</div>
						<% } %>
						
						<% if(thisRepo.getRepoType().equals("Clothing")) { %>
						<div class="form-group">
						<label for="size" class="col-md-2 control-label">Select Size<span class="caret"></span></label>
						
						<div class="col-md-10">
							<select name="size" id="size" class="form-control">
								
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
							<select name="condition" id="condition" class="form-control">
								
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
			<!-- End Store New -->
	
	</div>
		<%@ include file="static_resources/footer.html"%>

	<%@ include file="static_resources/scriptIncludes.html"%>

	<%@ include file="static_resources/initScript.html"%>

	<%@ include file="static_resources/animateScript.html"%>
	<%@ include file="static_resources/scrollspyScriptOrg.html" %>
	<%@ include file="static_resources/onclickCreateScript.html"%>
	<%@ include file="static_resources/datepicker.html"%>
	</body>
	</html>
	<% } %>
	<%
	if (session.getAttribute("usertype") == null) {
	%>
	<jsp:forward page="/"></jsp:forward>
	<%
	}
	%>