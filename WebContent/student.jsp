<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="includes/pageRedirect.jsp"%>
<%@page import="Classes.Display" %>
<%@page import="Classes.Logic" %>

<%
	if(!session.getAttribute("role").toString().equals("STUDENT"))
	{
		response.sendRedirect(session.getAttribute("role").toString().toLowerCase().concat(".jsp"));
	}

	try{
			session.setAttribute("exam_timetable", new Logic().get_student_exams(session.getAttribute("username").toString()));
		} catch(Exception ex) {
			session.setAttribute("result", new Display(Display.Type.ERROR).getHtml(ex.getMessage()));
		}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<%@include file="includes/head.html"%>
</head>
<body>
	<div class="container-fluid">
		<%@include file="includes/header.jsp"%>

		<div class="row">
			<div class="col-sm-12">
				<div class="page-header">
					<h1>
						${username} <br>
						<small>Timetable for ${username} </small>
					</h1>
				</div>
			</div>
		</div>

		<div class="row">
			<!-- Student Timetable displayer -->
			<div class="col-sm-1"></div>
			<div class="col-sm-10">
			<div class="panel panel-default">
				<!-- Table -->
				${exam_timetable}
			<!-- Displayer Above -->
		</div>
		</div>
		<div class="col-sm-1"></div>
		
	</div>
	${result}
<% session.setAttribute("result", ""); %>
			
</body>
<%@include file="includes/foot.html"%>

</html>