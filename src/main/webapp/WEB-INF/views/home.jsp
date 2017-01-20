<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DBA page</title>
</head>
<body>
	Dear
	<strong>${user}</strong>, Welcome to Home Page.
	<br />
	<br />
	<br /> Please click any of the below links
	<br />
	<a href="loginPage.do">login</a> |
	<a href="home.do">home</a>|
	<a href="db.do">dba</a>|
	<a href="admin.do">admin</a>
	<br /> or click on
	<a href="<c:url value="/logout.do" />">Logout</a>
</body>
</html>