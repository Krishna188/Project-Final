<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% 
    	if(session.getAttribute("username") != null ) 
    	{
    		response.sendRedirect(session.getAttribute("role").toString().trim().toLowerCase()+".jsp");    		
    	}
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<%@ include file="includes/head.html"%>
</head>
<body class="h_yellow">
	<div class="container-fluid">

		<div class="row head">
			<div class="col-sm-4 col-sm-push-4">
				<img src="img/logo.png" alt="Humber Schedule" id="logo"
					class="center">
			</div>
		</div>

		<!--
                Checks for Notification
             -->
		<div class="row">
			<div class="col-sm-4"></div>
			<div class="col-sm-4">
				<!--
                        Display Result
                    -->
				${result}
				<% session.setAttribute("result", ""); %>
			</div>
			<div class="col-sm-4"></div>
		</div>
		<!--
                Checks for Notification
             -->

		<!--
                Form placeholder
             -->
		<div class="row">
			<div class="col-sm-4"></div>
			<div class="col-sm-4">
				<div class="container-fluid">
					<div class="row">
						<div class="col-sm-12">
							<form action="Login" method="post" id="login-form" class="form">
								<!--Action File Name here-->
								<!--form data-->
								<br> <label for="username" class="form-field">Username</label>
								<input type="text" name="username" placeholder="Username"
									id="username" class="form-field" required><br> <label
									for="password" class="form-field">Password</label> <input
									type="password" name="password" placeholder="PASSWORD"
									id="password" class="form-field" required
									style="text-transform: none;"><br>
								<!--form data-->
								<input type="submit" name="login" value="Login" id="submit"
									class="form-field btn btn-primary"> <br>
							</form>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-4"></div>
		</div>
		<!--
                Form placeholder
             -->
	</div>
</body>
<%@ include file="includes/foot.html"%>
</html>