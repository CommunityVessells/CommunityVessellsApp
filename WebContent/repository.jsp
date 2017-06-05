<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.ffcc.communityVessells.app.models.Organization"%>
<%@ page import="org.ffcc.communityVessells.app.models.Repository"%>
<%@ page import="org.ffcc.communityVessells.app.models.Product"%>
<%@ page import="org.ffcc.communityVessells.app.dao.RepositoryDAO"%>
<%@ page import="org.ffcc.communityVessells.app.dao.ProductDAO"%>
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
				<h3 class="panel-title text-center" id="panelTitle">Create new
					Product Failed!</h3>
			</div>
			<div class="panel-body">
				<p class="text-center text-danger"><%=(String) request.getAttribute("errormessage")%></p>
			</div>
		</div>





		<%
			}
		%>

<!-- Alerts End -->


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
								<input type="text" name="expire" id="datepicker" class="form-control" required>

							</div>
						</div>
						<% } %>
						
						<% if(thisRepo.getRepoType().equals("Clothing")) { %>
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
			<!-- End Store New -->
	<hr class="divider-color">
	<!-- Start show products -->
		<div class="row-content text-center" id="Repositories">
		<h3 class="anton"><small><%=thisRepo.getTitle()%></small></h3>
		<button class="btn btn-info" data-toggle="collapse"
							data-target="#products" id="showAllProducts">
							Show All Products <span class="glyphicon glyphicon-chevron-down" id="icon"></span>
		</button>
		<div class="container-fluid">

			<div class="collapse table-responsive" id="products">
				<!-- toolbar start -->
				<!--  
				<div class="btn-toolbar">
		  			<div class="btn-group btn-group-justified">
		    			<a href="javascript:void(0)" class="btn btn-raised text-accent-color">Delete Expired/Fubar Products</a>
		    			<a href="javascript:void(0)" class="btn btn-raised">Store All Promised Products</a>		    					    			
		    		</div>
	  			</div>
	  			-->
	  			<!-- toolbar end -->
	  			<div class="checkbox" id="filters">
					<div class="togglebutton">
						<label> <input type="checkbox"  id="expirefilter" checked>Expired/Fubar 
						</label> 
						&nbsp;
						<label> <input type="checkbox" id="promisefilter" checked>Promised 
						</label>
						&nbsp;
						<label> <input type="checkbox" id="availablefilter" checked>Available 
						</label>
						&nbsp;
					</div>
				</div>
				<br>
				<div id="records">
				<div class="container">
					<div class="form-group label-floating">
						<label class="control-label" for="focusedInput1">Search</label>
						<input class="form-control search" type="text" id="focusedInput1"/>
					</div>
				</div>
					<table class="table table-hover" id="sortingTable">
					<tr class='light-primary-color text-primary-color'>
						<td></td>
						<td><button class="sort btn text-primary-color" data-sort="title">Title
 						</button></td>
 						<td><b>Column heading</b></td>
						<td><b>Condition</b></td>
						<td><b>Column heading</b></td>
						<td><b>Date Stored</b></td>
					</tr>
				  	<tbody id="tableProd" class="list">
					<% LinkedList<Product> allProducts = null;
						ProductDAO productDAO= new ProductDAO(); 
						try {
							 allProducts = productDAO.getProductsAllFields(thisRepo.getRepoID());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}				
					%>

					<%
					for(Product p:allProducts){
						if(p.isPromised()){ %>
							<tr class='warning'>
						<% }
						else if(!p.isPromised() && p.hasExpired(new java.sql.Date(new java.util.Date().getTime()))) { %>
							<tr class='danger'>
						<% }
						else if(!p.isPromised() && p.isFubar("Fubar")) { %>
							<tr class='danger'>
						<% }
						else { %>
							<tr class='success'>
						<% } %>
							<td><%=p.getProdID() %></td>
							<td class="title"><%=p.getTitle() %></td>
							<td><%=p.getSize() %></td>
							<td><%=p.getCondition() %></td>
							<%if(p.isPromised()) { %>
							<td>yes</td>
							<%} else { %>
							<td>no</td>
							<%}
							if(p.getDateStored()!=null) {%>
							<td> <%=p.getDateStored() %></td>
							<%} else { %>
							<td>Not Available</td>
							<%}%>
							</tr>
					<% }
					%>
				  	</tbody>
					</table>
					
					
				</div>	
			</div>
		</div>
		<!-- end table -->
		</div>
	</div>
		<%@ include file="static_resources/footer.html"%>

	<%@ include file="static_resources/scriptIncludes.html"%>

	<%@ include file="static_resources/initScript.html"%>

	<%@ include file="static_resources/animateScript.html"%>
	<%@ include file="static_resources/scrollspyScriptOrg.html" %>
	<%@ include file="static_resources/onclickCreateScript.html"%>
	<%@ include file="static_resources/datepicker.html"%>
	<%@ include file="static_resources/initList.html"%>
	<%@ include file="static_resources/filterScript.html"%>

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