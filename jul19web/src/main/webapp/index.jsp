<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:if test="${dto eq null }">
	<c:redirect url="./index"/>
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>
<style type="text/css">
ul, li{
	padding: 0;
	margin: 0;
	list-style: none;
	text-align: center;
}

#ulTable{
	/* margin-top: 10px; */
	width: 100%;
	margin: 10px;
	height: auto;
}
#ulTable > li:first-child > ul > li{
	background-color: #E6E6FA;
	font-weight: bold;
	text-align: center;
}
#ulTable > li > ul{
	clear: both;
	padding: 0 auto;
	position: relative;
	min-width: 40px;
}
#ulTable > li > ul > li{
	float: left;
	font-size: 10pt;
	border-bottom: 1px solid white;
	height: 30px;
	line-height: 25px;
}

#ulTable > li > ul > li:first-child{
	width: 10%;
}
#ulTable > li > ul > li:first-child +li{
	width: 45%;
	text-align: left;
}
#ulTable > li > ul > li:first-child +li + li{
	width: 20%;
}
#ulTable > li > ul > li:first-child +li + li + li{
	width: 15%;
}
#ulTable > li > ul > li:first-child +li + li + li + li{
	width: 10%;
}
a:link, a:visited, a:hover,a:active{ color: dimgray; text-decoration: none;}
small{
	height: auto;
	width: auto;
	background-color: #DDA0DD;
	color: white;
	border-radius: 25%;
}
#writeBtn{
	margin: 10px 0 10px 20px;
	text-align: left;
}

#writeBtn img{
	margin-left: 3px;
	height: 14px;
	vertical-align: text-top;
}
#paging{
	text-align: center;
	vertical-align: middle;
	width: 100%;
	line-height: 50px;
}
#pageBtn{
	border: none;
	background-color: #F8F8FF;
	margin: 0;
	padding: 0;
}
</style>
</head>
<body>
<div id="container">
<div id="menu">
	<c:import url="menu.jsp"/>
</div>
<div id="main">
	<h1>boardList</h1>
	<div id="mainWrapper">
	<ul id="ulTable">
		<li>
			<ul>
				<li>No</li>
				<li>Title</li>
				<li>Writer</li>
				<li>Date</li>
				<li>Count</li>
			</ul>
		</li>
		<c:forEach items="${dto }" var="dto">
		<li>
			<ul>
				<li>${dto.fno }</li>
				<li>
				
				<a href="./detail?fno=${dto.fno }">${dto.ftitle }
				<c:if test="${dto.commentcount > 0}"><small>${dto.commentcount }</small></c:if>
				</a>
				
				</li>
				<li>${dto.name }</li>
				<li>${dto.fdate }</li>
				<li>${dto.fcount }</li>
			</ul>
		</li>
		</c:forEach>
	</ul>
	<br>

	<!-- 페이징 -->
	<div id="paging">
		<c:set var="pageName" value="index" scope="request"/> <!-- scope="request"로 index값까지 주소줄에 전송 -->
		<c:set var="PAGENUMBER" value="10" scope="request"/>
		<c:import url="paging.jsp"/>
	</div>
	<c:if test="${sessionScope.id ne null }">
		<div id="writeBtn"><a href="./freeWrite">글쓰기<img alt="write" src="./img/edit.png"> </a></div>
	</c:if>
	</div>
</div>
</div>
</body>
</html>