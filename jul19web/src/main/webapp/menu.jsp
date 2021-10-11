<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="./css/main.css" rel="stylesheet">
<link href="./js/menu.js" rel="stylesheet">
<style type="text/css">
#menu{
	margin: 0 auto;
	height: 100vh;
	width: 150px;
	background-color: #F8F8FF;
	float: left; 
}
#logo{
	margin: 0 auto;
	width: 100%;
	height: 130px;
	text-align: center;
}
#logo img{
	margin: 0 auto;
	margin-top: 30px;
	height: 80px;
}
#menuItem{
	margin: 0 auto;
	height: 30px;
	width: 130px;
	background-color: #E6E6FA;
	margin: 10px;
	text-align: center;
	line-height: 30px;
	font-size: 10pt;
}

#loginbox{
	margin: 0 auto;
	width: 150px;
	height: 70px;
	text-align: center;
}
#loginbox input{
	margin:0;
	margin-bottom:3px;
	padding:0;
	width: 130px;
	height: 20px;
	font-size: 10px;
}
#loginbox button{
	margin:0;
	margin-bottom: 10px;
	padding:0;
	width: 132px;
	height: 20px;
	font-size: 10px;
}

#sessionTrue{
	font-size: 12px;
	color: dimgray;
	background-color: #F8F8FF;
}
</style>
	<div id="logo">
		<img alt="star" src="./img/star.png">
	</div>
	
	<div id="loginbox">
		<c:choose>
			<c:when test="${sessionScope.name ne null }">
			<!-- 세션이 있다면 ~~님 반갑습니다라고 출력  -->
				<div style="margin-bottom: 5px;">
				<b><a style="color:#9370DB;">${sessionScope.name }</a></b>
				님<br>반갑습니다<br></div>
				<small id="sessionTrue"><a onclick="location.href='./myInfo'">내정보</a> | 
				<a onclick="return logout()">로그아웃</a></small>
			</c:when>
			
			<c:otherwise>
			<!-- 세션이 없다면 로그인 하도록 입력창 출력 -->
				<form action="./login" method="post">
					<input type="text" name="id" placeholder="아이디를 입력하세요." required="required">
					<input type="password" name="pw" placeholder="비밀번호를 입력하세요" required="required">
					<button>Login</button>
				</form>				
			</c:otherwise>
		</c:choose>
	</div>
	<br>
	<div id="menuItem" onclick="menuClick('index')">게시판</div>
	<div id="menuItem" onclick="menuClick('gallery')">갤러리</div>
	<div id="menuItem" onclick="menuClick('notice')">공지사항</div>
	<div id="menuItem" onclick="menuClick('free')">자유게시판</div>
	<div id="menuItem" onclick="menuClick('menu')">메뉴</div>
	
	<!-- 9등급(세션)인 사람만 볼 수 있는 메뉴 -->
	<c:if test="${sessionScope.grade eq 9 }">
		<div id="menuItem" onclick="menuClick('admin')">관리자</div>
	</c:if>
	
<script>
function menuClick(menu){
	location.href='./'+menu;
}
function logout(){
	if(confirm("로그아웃 하시겠습니까?")){
		location.href="logout";
	} else{
		return false;
	}
}
</script>