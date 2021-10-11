<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>갤러리 글쓰기</title>
<style type="text/css">
#mainWrapper{
	vertical-align: middle;
	text-align: center;
}
#write{
	vertical-align: middle;
	text-align: center;
	height: 500px;
	width: 600px;
	margin: 0 auto;
}
#gtitle{
	width: 100%;
	height: 30px;
	margin: 5px;
	padding-left: 9px;
	padding-right: 9px;
}
#gcontent{
	width: 100%;
	height: 450px;
	margin: 5px;
	padding: 10px;
}
#write button{
	width: 120px;
	height: 40px;
}
#write img{
	height: 12px;
}
</style>
</head>
<body>
<div id="container">
<div id="menu">
	<c:import url="./menu.jsp"/>
</div>

<div id="main">
	<div id="mainWrapper">
		<div id="write">
		<form action="./galleryWrite" method="POST" enctype="multipart/form-data">
			<input type="text" name="gtitle" id="gtitle" required="required" placeholder="제목을 입력하세요.">
			<textarea name="gcontent" id="gcontent" required="required"></textarea>
			<input type="file" name="file" accept=".gif, .png, .jpg, .jpeg">
			<button type="submit">글쓰기 <img alt="" src="./img/edit.png"> </button>
		</form>
		</div>
	</div>
</div>
</div>
</body>
</html>