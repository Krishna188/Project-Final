
<%
	if (session.getAttribute("username") == null) {
		response.sendRedirect("index.jsp");
	}
%>