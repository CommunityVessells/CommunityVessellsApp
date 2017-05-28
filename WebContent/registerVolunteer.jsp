<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>
<%@ page import="org.ffcc.communityVessells.app.encryption.EncryptMD5" %>

<!DOCTYPE html>
<html lang="en">
<%@ include file="static_resources/head.html" %>

<body id="comVessells" data-spy="scroll" data-target=".navbar" data-offset="60">
<%@ include file="static_resources/signupModal.html" %>

<%@ include file="static_resources/loginModal.html" %>

<!-- Navbar start -->
<%@ include file="static_resources/navbar_generic.html" %>
<!-- Navbar end-->

<div class="container-fluid">

					<div class="well container">
					<!--form start-->
					<form class="form-horizontal" action="registervolunteer" method="post">
					
						<fieldset>
						<legend class="anton text-right">Submit your Details</legend>
	
						
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">Email</label>
							<div class="col-sm-10">
								<input type="text" name="email" class="form-control" id="inputEmail3" placeholder="Email" value="<%=session.getAttribute("email") %>" readonly>
							</div>
						</div>
						<div class="form-group"  >
							<label for="inputUsername" class="col-sm-2 control-label">Username</label>
							<div class="col-sm-10">
								<input type="text" name="username" class="form-control" id="inputUsername" placeholder="Username">
							</div>
						</div>

						<div class="form-group"  >
							<label for="inputFirstname" class="col-sm-2 control-label">First Name</label>
							<div class="col-sm-10">
								<input type="text" name="firstname" class="form-control" id="inputFirstname" placeholder="First Name">
							</div>
						</div>
						
						<div class="form-group"  >
							<label for="inputLastname" class="col-sm-2 control-label">Last Name</label>
							<div class="col-sm-10">
								<input type="text" name="lastname" class="form-control" id="inputLastname" placeholder="Last Name">
							</div>
						</div>
						
						<div class="form-group">
      						<label for="inputFile" class="col-md-2 control-label">Upload Avatar Image</label>
							<div class="col-md-10">
        						<input readonly="" class="form-control" placeholder="Browse..." type="text">        						
        						<input id="inputFile" name="avatar" multiple="" type="file" accept="image/x-png,image/gif,image/jpeg">

        						<p class="help-block" id="helper">Only png,jpeg or gif files accepted.<br>Recommended size 150 x 150. File upload limit: 16Mb</p>
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
					<!--form end-->
					</div>

</div>

<%@ include file="static_resources/footer.html" %>

<%@ include file="static_resources/scriptIncludes.html" %>

<%@ include file="static_resources/initScript.html" %>


</body>
</html>