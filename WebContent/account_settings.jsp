<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="includes/pageRedirect.jsp"%>
<html>
<head>
<%@include file="includes/head.html"%>
</head>
<body>
	<div class="container-fluid">
		<%@include file="includes/header.jsp"%>

		<div class="row">
			<div class="col-sm-12">
				<div class="page-header">
					<h1>Account Settings</h1>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-2"></div>
			<div class="col-sm-8">
				<form class="form" id="form" action="AccountSetting" method="post"
					id="account_settings">
					<label for="fname" class="form-field">Change First Name</label> <input
						type="text" name="fname" placeholder="${first}" value="${first}"
						id="fname" class="form-field" required><br> <label
						for="lname" class="form-field">Change Last Name</label> <input
						type="text" name="lname" placeholder="${last}" value="${last}"
						id="lname" class="form-field" required><br> <label
						for="password" class="form-field">Change Account Password</label>
					<input type=password name="password" placeholder="Password"
						value="${password}" id="password" class="form-field"><br>
					<label for="c_password" class="form-field">Confirm Password</label>
					<input type=password name="c_password"
						placeholder="Confirm Password" class="form-field"><br>
					<input type="submit" name="login" value="Modify" id="submit"
						class="form-field btn btn-primary"><br>
					<button type="reset"
						onclick="document.getElementById('account_settings').reset();"
						class="form-field btn btn-primary" class="btn-warning">Clear</button>
					<br>
				</form>
			</div>
			<div class="col-sm-2"></div>
		</div>
		<br>
		<br>
		<div class="row height">
			<div class="col-sm-12 height">
				${result}
				<% session.setAttribute("result", ""); %>
			</div>
		</div>
	</div>
</body>
<%@ include file="includes/foot.html"%>
</html>