
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Classes.Display" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<%@include file="includes/head.html"%>
<%
	
	session = request.getSession();
	if (session.getAttribute("username") == null) {
		RequestDispatcher dd = request.getRequestDispatcher("index.jsp");
		dd.forward(request, response);
	}
	if(session.getAttribute("role") == null)
	{
		session.setAttribute("result",new Display(Display.Type.WARNING).getHtml("Login as admin to use this function!"));
		RequestDispatcher dd = request.getRequestDispatcher("index.jsp");
		dd.forward(request, response);
	}
	else if(!session.getAttribute("role").toString().equals("ADMIN"))
	{
		session.setAttribute("result",new Display(Display.Type.WARNING).getHtml("Action not allowed!"));
		RequestDispatcher dd = request.getRequestDispatcher(session.getAttribute("role").toString().toLowerCase().concat(".jsp"));
		dd.forward(request, response);
	}
	else if(session.getAttribute("USERNAME") == null || session.getAttribute("ROLE") == null || session.getAttribute("FIRSTNAME") == null || session.getAttribute("LASTNAME") == null)
	{
		session.setAttribute("result",new Display(Display.Type.WARNING).getHtml("Action not allowed!"));
		RequestDispatcher dd = request.getRequestDispatcher(session.getAttribute("role").toString().toLowerCase().concat(".jsp"));
		dd.forward(request, response);
	}
%>
</head>
<body>
	<div class="container-fluid">
		<%@include file="includes/header.jsp"%>
		<div class="col-sm-12">
			<form method="post" action="Modify" id="mod_user">
				<section class="col-sm-8"> <legend>
					Edit
					<%=session.getAttribute("USERNAME")%>
				</legend>
				<fieldset>
					<div class="form-group">
						<label class="label label-info form-control">Username</label><br>
						<input type="text" name="username" placeholder="Username"
							style="text-transform: uppercase" required="required"
							value="<%=session.getAttribute("USERNAME")%>" readonly="readonly">
					</div>
					<div class="form-group">
						<label class="label label-info form-control">Password</label><br>
						<input type="password" id="pass-field" name="password"
							placeholder="Password" style="text-transform: none"
							value="<%=session.getAttribute("PASSWORD")%>"
							required="required"> <span
							class="glyphicon glyphicon-eye-open"
							alt="Click here to hide/unhide" id="unhide"></span>
					</div>

					<div class="form-group">
						<label class="label label-info form-control">First Name</label><br>
						<input type="text" name="fname" placeholder="First name"
							style="text-transform: uppercase" required="required"
							value="<%=session.getAttribute("FIRSTNAME")%>">
					</div>
					<div class="form-group">
						<label class="label label-info form-control">Last Name</label><br>
						<input type="text" name="lname" placeholder="Last name"
							style="text-transform: uppercase" required="required"
							value="<%=session.getAttribute("LASTNAME")%>">
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-primary">Modify User</button>
						<button type="reset"
							onclick="document.getElementById('mod_user').reset();"
							class="btn btn-warning">Reset</button>
						<button type="button" name="back" class="btn btn-danger"
							onclick="history.back()">Cancel</button>
						<input type="hidden" name="role"
							value="<%=session.getAttribute("ROLE")%>">
					</div>
				</fieldset>
				</section>
			</form>
		</div>
	</div>
	<%@ include file="includes/foot.html"%>
	<script>
		document.getElementById("unhide").addEventListener("click", function() {
			if (document.getElementById("pass-field").type == 'password') {
				document.getElementById("pass-field").type = 'text';
			} else {
				document.getElementById("pass-field").type = 'password';
			}
		}, false);
	</script>
</body>
</html>
