<%@ page language="java" contentType="text/html; charset=utf-8"
	import="by.htp.library.bean.Book" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<form action="index.jsp" method="post">
		Book is added<br />
		<h3>
			title:
			<c:out value="${requestScope.book.name}" />
			<br /> author:
			<c:out value="${requestScope.book.author}" />
			<br /> published in:
			<c:out value="${requestScope.book.age}" />

		</h3>
		<input type="submit" value="return to main menue" />
	</form>
</body>
</html>