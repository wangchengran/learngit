<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://cdn.bootcss.com/jquery/2.1.2/jquery.min.js"></script>
<title>欢迎登陆</title>
</head>
<body>
	<center>
	<h1>欢迎登陆</h1>
		<form action="/index" method="post">
			账号:<input type="text" name="username"><br> 
			密码:<input type="password" name="password"><br> 
			<c:choose>
			<c:when test="${message!=null }">
			<div style="color: red" >
				${message }
			</div>
			</c:when><c:otherwise></c:otherwise>
			</c:choose>
			<input type="submit" value="登陆"> 
			<input type="button" value="注册" onclick="window.location.href='/register.jsp'">
		</form>
	</center>
</body>
</html>