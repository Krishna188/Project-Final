<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
    <head>
        <%@include file="includes/head.html" %>
    </head>
    <body>
     <% if(session.getAttribute("username") == null ) {
    	response.sendRedirect("index.jsp");    		
    }  %>
        <div class="container-fluid">
        <%@include file="includes/header.jsp" %>
            <div class="row">
                <div class="panel-group" id="accordion">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title"> <a data-toggle="collapse" data-parent="#accordion" href="#collapse1"> View Scheduled Exam</a></h4>
                        </div>
                        <div id="collapse1" class="panel-collapse collapse in">
                            <div class="panel-body">${data}</div>
                        </div>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title"> <a data-toggle="collapse" data-parent="#accordion" href="#collapse2"> Schedule Exam</a></h4>
                        </div>
                        <div id="collapse2" class="panel-collapse collapse out">
                            <div class="panel-body">
      							  <%@include file="includes/new_exam.jsp" %>
        					</div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </body>
  
        <%@include file="includes/foot.html" %>
</html>