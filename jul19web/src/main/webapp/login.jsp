<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
<style type="text/css">
#login{
	margin: 0 auto;
	margin-top: 100px;
	margin-bottom: 100px;
	width: 400px;
	height: 300px;
	background-color: #E6E6FA;
	text-align: center;
	vertical-align: middle;
}

#login img{
	margin: 0 auto;
	margin-top: 30px;
	height: 160px;
}

#login input{
	margin-top: 10px;
	height: 25px;
}

#login button{
	margin-top: 10px;
	height: 25px;
	width: 100px;
}
</style>
</head>
<body>
<div id="container">
<div id="menu">
	<c:import url="menu.jsp"/>
</div>
<div id="main">
	<div id="mainWrapper">
		<div id="login">
			<form action="./login" method="post">
			<img alt="logo" src="./img/star2.png"><br>
			<input type="text" name="id" 
				placeholder=" 아이디를 입력하세요." required="required">
			<input type="password" name="pw" 
				placeholder=" 비밀번호를 입력하세요" required="required"><br>
			<button>Login</button>
			</form>
		</div>
	</div>
</div>
</div>
</body>
</html>