<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세보기</title>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<!-- <script type="text/javascript" src="./js/freeDetail.js"></script> -->
<script type="text/javascript">
function gallery(gno, code){
	//alert("클릭" + gno + code);
	if(code == 'm'){
		if(confirm("해당 글을 수정하시겠습니까?")){
			location.href='./galleryModify?gno='+gno;
		}
	} else if(code == 'd'){
		if(confirm("해당 글을 삭제하시겠습니까?")){
			location.href='./galleryDelete?gno='+gno;
		}
	}
}
</script>
<style type="text/css">
table {
	margin: 0 auto;
	min-height: 300px;
	height: auto;
	width: 1000px;
	border-collapse: collapse;
}
table td{
	padding-left:10px; 
	height: 30px;
}
table tr{
	border-bottom: 1px #E6E6FA solid;
}
table tr:last-child{
	height: 200px;
}
#detail{
	margin-top: 50px;
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
		<div id="detail">
			<table>
				<tr>
					<th>제목</th>
					<td>${dto.gtitle }
					<c:if test="${sessionScope.id eq dto.id }">
						<button onclick="gallery(${dto.gno }, 'm');"><img alt="modify" src="./img/edit.png" height="10px"> 수정</button>
						<button onclick="gallery(${dto.gno }, 'd');"><img alt="delete" src="./img/delete.png" height="10px">삭제</button>
					</c:if>
					</td>
				</tr>
				<tr>
					<th>글쓴이</th>
					<td>${dto.name }(${dto.id })</td>
				</tr>
				<tr>
					<th>쓴날짜</th>
					<td>${dto.gdate }</td>
				</tr>
				<tr>
					<th>조회수</th>
					<td>${dto.gcount }</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
					<c:if test="${dto.file ne null }">
						<img alt="${dto.file }" src="./upload/${dto.file }" width="40%" style="margin: 10px;"> <br>
					</c:if>
						${dto.gcontent }
					</td>
				</tr>
			</table>
			<a href="./gallery">게시판으로</a>
		</div>
		</div>
		<hr>
	</div>
	<br>
</div>
</div>
</body>
</html>