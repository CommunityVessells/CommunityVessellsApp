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
								<input type="email" name="email" class="form-control" id="inputEmail3" placeholder="Email" value="<%=request.getParameter("email") %>" readonly>
							</div>
						</div>
						<div class="form-group"  >
							<label for="inputPassword3" class="col-sm-2 control-label">Retype Password</label>
							<div class="col-sm-10">
								<input type="password" name="password" class="form-control" id="inputPassword3" placeholder="Password" value="<%=request.getParameter("password") %>" readonly>
								
								
							</div>
						</div>
						<div class="form-group">
					      <label class="col-md-2 control-label"></label>

					      <div class="col-md-10">
					        <div class="radio radio-primary">
					          <label>
					            <input name="optionsRadios" id="optionsRadios1" value="volunteer" checked="" type="radio">
					            I am a Volunteer
					          </label>
					        </div>
					        <div class="radio radio-primary">
					          <label>
					            <input name="optionsRadios" id="optionsRadios2" value="organization" type="radio">
					            I am an Organization
					          </label>
					        </div>
					      </div>

					    </div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-md-10">
								<button type="submit" class="btn btn-primary">Sign Up</button>
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