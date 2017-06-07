<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.ffcc.communityVessells.app.models.Organization"%>
<%@ page import="org.ffcc.communityVessells.app.models.Repository"%>
<%@ page import="org.ffcc.communityVessells.app.models.Product"%>
<%@ page import="org.ffcc.communityVessells.app.models.Request"%>
<%@ page import="org.ffcc.communityVessells.app.dao.RepositoryDAO"%>
<%@ page import="org.ffcc.communityVessells.app.dao.ProductDAO"%>
<%@ page import="org.ffcc.communityVessells.app.dao.RequestDAO"%>
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
				<div class="container-fluid">
				
					<div class="form-group label-floating">
						<label class="control-label" for="focusedInput1">Search</label>
						<input class="form-control search" type="text" id="focusedInput1"/>
					
					</div>
				</div>
				
				<%if(thisRepo.getRepoType().equals("Clothing")) {%>
					<table class="table table-hover" id="sortingTable">
					<tr class='light-primary-color text-primary-color'>
						<td><button class="sort btn text-primary-color" data-sort="count">Quantity
 						</button></td>
						<td><button class="sort btn text-primary-color" data-sort="title">Title
 						</button></td>
 						<td><button class="sort btn text-primary-color" data-sort="size">Size</button></td>
						<td><button class="sort btn text-primary-color" data-sort="condition">Condition</button></td>
						<td><button class="sort btn text-primary-color" data-sort="promised">Promised
 						</button></td>
						<td><button class="sort btn text-primary-color" data-sort="date">Date Stored</button></td>
						<td><button class="btn text-primary-color">Functions</button></td>
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
							<td class="count"><%=p.getCount() %></td>
							<td class="title"><%=p.getTitle() %></td>
							<td class="size"><%=p.getSize() %></td>
							<td class="condition"><%=p.getCondition() %></td>
							<%if(p.isPromised()) { %>
							<td class="promised">yes</td>
							<%} else { %>
							<td class="promised">no</td>
							<%}
							if(p.getDateStored()!=null) {%>
							<td class="date"> <%=p.getDateStored() %></td>
							<%} else { %>
							<td class="date">Not Available</td>
							<%}%>
							<%if(p.isFubar("Fubar") || p.hasExpired(new java.sql.Date(new java.util.Date().getTime()))) { 
							
							%>
							<td><a class="btn btn-danger btn-sm" href="<%= response.encodeURL ("editproduct?repoID="+Integer.toString(p.getRepoID())+"&prodID="+Integer.toString(p.getProdID()))+"&remove=true&store=false"%>">Remove</a></td>
							<% } 
							else if(p.isPromised()) {
							
							%>
							<td><a class="btn btn-success btn-sm" href="<%= response.encodeURL ("editproduct?repoID="+Integer.toString(p.getRepoID())+"&prodID="+Integer.toString(p.getProdID()))+"&remove=false&store=true"%>">Store</a></td>
							<% } else {%>
							<td></td>
							<%} %>
							</tr>
					<% }%>
					
					
					
					
				  	</tbody>
					</table>
					<% } %>

				<%if(thisRepo.getRepoType().equals("Food Products") || thisRepo.getRepoType().equals("Pharmaceuticals")) {%>
					<table class="table table-hover" id="sortingTable">
					<tr class='light-primary-color text-primary-color'>
						<td><button class="sort btn text-primary-color" data-sort="count">Quantity
 						</button></td>
						<td><button class="sort btn text-primary-color" data-sort="title">Title
 						</button></td>
 						<td><button class="sort btn text-primary-color" data-sort="expire">Expires</button></td>
						
						<td><button class="sort btn text-primary-color" data-sort="promised">Promised
 						</button></td>
						<td><button class="sort btn text-primary-color" data-sort="date">Date Stored</button></td>
						<td><button class="btn text-primary-color">Functions</button></td>
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
							<td class="count"><%=p.getCount() %></td>
							<td class="title"><%=p.getTitle() %></td>
							<td class="expire"><%=p.getExpire() %></td>
							
							<%if(p.isPromised()) { %>
							<td class="promised">yes</td>
							<%} else { %>
							<td class="promised">no</td>
							<%}
							if(p.getDateStored()!=null) {%>
							<td class="date"> <%=p.getDateStored() %></td>
							<%} else { %>
							<td class="date">Not Available</td>
							<%}%>
							<%if(p.isFubar("Fubar") || p.hasExpired(new java.sql.Date(new java.util.Date().getTime()))) { 
							
							%>
							<td><a class="btn btn-danger btn-sm" href="<%= response.encodeURL ("editproduct?repoID="+Integer.toString(p.getRepoID())+"&prodID="+Integer.toString(p.getProdID()))+"&remove=true&store=false"%>">Remove</a></td>
							<% } 
							else if(p.isPromised()) {
							
							%>
							<td><a class="btn btn-success btn-sm" href="<%= response.encodeURL ("editproduct?repoID="+Integer.toString(p.getRepoID())+"&prodID="+Integer.toString(p.getProdID()))+"&remove=false&store=true"%>">Store</a></td>
							<% } else {%>
							<td></td>
							<%} %>
							</tr>
					<% }%>
					
					
					
					
				  	</tbody>
					</table>
					<% } %>
					
				</div>
				
					
			</div>
			

		</div>
		<!-- end table -->
		</div>
		<hr class="divider-color">
					<!-- Issue Request start -->
					<div class="container-fluid text-center">
			<div id="Issue">
			<h4 class="anton">
					<small>Issue New Request</small>
				</h4>
				<button class="btn btn-default btn-raised light-primary-color"
					id="createRequest">
					<span class="glyphicon glyphicon-plus" id="symbolrequest"></span>
				</button>
			</div>
			<div class="well hide" id="newRequest">
	
					<!--form start-->

					<form class="form-horizontal" action="createrequest" method="post" enctype="multipart/form-data">
						<fieldset>
						
						<div class="form-group">
						
							<label for="reqtitle" class="control-label col-md-2">Title  </label>
							<div class="col-sm-10">
								<input type="text" name="reqtitle" class="form-control" id="reqtitle" placeholder="Request Title">
							</div>
						</div>
						
						<div class="form-group">
						
							<label for="startdate" class="control-label col-md-2">Start Date </label>
							<div class="col-sm-10">
								<input type="text" name="startdate" class="form-control" id="startdate">
							</div>
						</div>
						
						<div class="form-group">
						
							<label for="closedate" class="control-label col-md-2">Close Date </label>
							<div class="col-sm-10">
								<input type="text" name="closedate" class="form-control" id="closedate">
							</div>
						</div>
						
						<div class="form-group">
						
							<label for="address" class="control-label col-md-2">Address </label>
							<div class="col-sm-10">
								<input type="text" name="address" class="form-control" id="address" placeholder="Address">
							</div>
						</div>
						<!-- Sarcastic Whitespace -->
						<br><br><br><br>
						<div class="form-group">
      						<label for="inputFile" class="col-md-2 control-label">Upload Image</label>
							<div class="col-md-10">
        						<input readonly="" class="form-control" placeholder="Browse..." type="text">        						
        						<input id="inputFile" name="file" multiple="" type="file" accept="image/x-png,image/gif,image/jpeg">

        						<p class="help-block" id="helper">Only png,jpeg or gif files accepted. File upload limit: 16Mb</p>
      						</div>
    					</div>				
							
						<div class="form-group">
							<div class="col-sm-offset-2 col-md-10">
								<button type="submit" class="btn btn-primary">Save</button>
								<button type="reset" class="btn btn-default">Reset</button>
							</div>	
						</div>
						</fieldset>
						</form>
						</div>
						</div>
			<!-- Issue Request end -->
			<hr class="divider-color">
			
			<!-- Start show requests -->
		<div class="row-content text-center" id="Requests">
		<h3 class="anton"><small><%=thisRepo.getTitle()%> Requests</small></h3>
		<button class="btn btn-info" data-toggle="collapse"
							data-target="#allrequests" id="showAllRequests">
							Show All Requests <span class="glyphicon glyphicon-chevron-down" id="iconR"></span>
		</button>
			

			<div class="collapse" id="allrequests">
			
							<!-- loop div start -->
							<%
								RequestDAO requestdao = new RequestDAO();
								LinkedList<Request> requestsList = requestdao.getRequests(thisRepo.getRepoID());
								
								for (Request r : requestsList) {
							%>
							<div class="well">
							<h4 class="list-group-item-heading anton">
							
								<small><span class="text-info">
										<%=r.getTitle()%>
								</span> Details
								</small>
							</h4>
							
							<div class="container-fluid">
								<div class="center-block">
									<img src="<%=r.getAvatar() %>" alt="Request Image" class="img-reponsive" style="width:60%;height:auto;">
								</div>
							</div>
							<br>
							<h2 class="lobster text-accent-color"><%=r.getTitle()%></h2>
							<p>
								<b>Repository Type: </b><%=thisRepo.getRepoType() %></p>
							<p>
								<b>Start Date: </b><%=r.getStartdate() %></p>
							
							<p><b>Closes on: </b><%=r.getClosedate() %></p>
							
							<p><b>Location: </b><%=r.getAddress() %></p>

    						
    							
							<%-- URI rewrite repoID --%>
							<a class="btn btn-info btn-raised  updateRepo" title="Display Promised Products" href="<%= response.encodeURL ("repository.jsp?repoID="+Integer.toString(r.getReposID())+"&requestID="+Integer.toString(r.getRequestID()))%>">
								<b>Details </b><span class="glyphicon glyphicon-eye-open"
									id="symbolview"> </span>
							</a>
							

						</div>
						
						<%
							}
						%>
						
						</div>
						<!-- loop div end -->
			
			
			</div>
			
			
		<!-- End show requests -->
	</div>
		<%@ include file="static_resources/footer.html"%>

	<%@ include file="static_resources/scriptIncludes.html"%>

	<%@ include file="static_resources/initScript.html"%>

	<%@ include file="static_resources/animateScript.html"%>
	<%@ include file="static_resources/scrollspyScriptOrg.html" %>
	
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