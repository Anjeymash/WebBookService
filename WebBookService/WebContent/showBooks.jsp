<%@ page language="java" contentType="text/html; charset=utf-8"
	import="by.htp.library.bean.Book" pageEncoding="utf-8"%>
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
		</head>
		</br>
	</ul>
<body>
	<ul id="navbar">
		Books are found:
		<br />

		<c:forEach var="book" items="${requestScope.listbook}">
			<form action="Controller" method="get">
				<input type="hidden" name="command" value="delbook" /> <br />
				title:
				<td><c:out value="${book.name} " /></td> <br /> author:
				<td><c:out value="${book.author} " /></td> <br /> published in:
				<td><c:out value="${book.age} " /></td> <br /> ID:
				<td><c:out value="${book.id} " /></td> <input type="hidden"
					name="bookID" value="${book.id}" /> <input type="submit"
					value="Delete book" />
			</form>
		</c:forEach>
		<br />


	</ul>
</body>
</html>