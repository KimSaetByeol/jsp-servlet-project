<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ERROR</title>
<link href="./css/main.css" rel="stylesheet">
<link href="./css/menu.css" rel="stylesheet">
<link href="./css/error.css" rel="stylesheet">
</head>
<body>
<div id="container">
<div id="menu">
	<c:import url="menu.jsp"/>
</div>
<div id="main">
	<div id="mainWrapper">
		<div id="error">
			${param.code }로 인하여 에러가 발생<br>
			<c:choose>
				<c:when test="${param.code eq 400 }">
					<h2>400 에러가 발생했습니다.</h2>
				</c:when>
				<c:when test="${code eq 404 }">
					<h1>페이지를 찾을 수 없습니다.</h1>
					<h2>올바른 경로로 접근하시기 바랍니다.</h2>
				</c:when>
				<c:when test="${param.code eq 500 }">
					<h1>내부 오류가 발생했습니다.</h1>
					<h2>관리자에게 문의하세요.</h2>
				</c:when>
				<c:when test=""></c:when>
				<c:when test=""></c:when>
				<c:otherwise></c:otherwise>
			</c:choose>
		</div>
	</div>
</div>
</div>
</body>
</html>