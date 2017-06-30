<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<ul id="navbar">
		<li><a href="addform.jsp">Add new book</a></li>
		<li><a href="Controller?command=showallbooks">Delete book</a></li>
		<li><a href="fndform.jsp">Find book</a></li>
	</ul>
<body>

	<form action="Controller" method="post">
	<ul id="navbar">
		<input type="hidden" name="command" value="fndbook" /> Enter the book name:<br /> 
		<li><input type="text" name="bookName" value="" /><br /></li> 
		<li><input type="submit" value="find book" /><br/><li/>
		
	</form>

	<c:if test="${not empty requestScope.errorMessage}">
		<c:out value="${requestScope.errorMessage}" />
	</c:if>
</ul>

</body>
</html>