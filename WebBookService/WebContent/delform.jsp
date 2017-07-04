<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<ul id="navbar">
		<li><a href="addform.jsp">Add new book</a></li>
		<li><a href="Controller?command=showallbooks">Delete book</a></li>
		<li><a href="fndform.jsp">Find book</a></li>
		<li><a href="Controller?command=singout">Sign out</a></li>
		</ul>
	
<ul id="navbar">
	<form action="Controller" method="get">
	
		<input type="hidden" name="command" value="showallbooks" /> 
		
			<li><input type="submit"value="try once more" /></li>
	
	</form>

	<c:if test="${not empty requestScope.errorMessage}">
		<c:out value="${requestScope.errorMessage}" />
	</c:if>
</ul>

</body>
</html>