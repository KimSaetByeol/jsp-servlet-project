<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:if test="${list eq null }">
	<c:redirect url="./gallery"/>
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>gallery</title>
<style type="text/css">
ul, li{
	padding: 0;
	margin: 0;
	list-style: none;
	text-align: center;
}

#ulTable{
	/* margin-top: 10px; */
	width: 800px;
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
	/* height: 30px; */
	height: 60px;
	line-height: 60px;
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
	margin: 40px 0 10px 20px;
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
<h1>gallery List</h1>
<div id="mainWrapper">
<c:choose>
	<c:when test="${fn:length(list) > 0 }">
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
			<c:forEach items="${list }" var="l">
			<li>
				<ul>
					<li>${l.gno }</li>
					<li>
					<a href="./galleryDetail?gno=${l.gno }">
						<c:choose>
							<c:when test="${l.gthumbnail eq null }">
								<img alt="thumb" src="./img/noimage2.jpg" style="vertical-align: middle;">
							</c:when>
							<c:otherwise>
								<img alt="thumb" src="./thumbnail/${l.gthumbnail }" style="vertical-align: middle;">								
							</c:otherwise>
						</c:choose>
					&nbsp; ${l.gtitle }</a>
					</li>
					<li>${l.name }</li>
					<li>${l.gdate }</li>
					<li>${l.gcount }</li>
				</ul>
			</li>
			</c:forEach>
		</ul> <!-- end of ulTable -->
	</c:when>
	
	<c:otherwise>출력할 글이 없습니다.</c:otherwise>
</c:choose>
<br><br><br>
<div id="paging">
	<c:set var="pageName" value="gallery" scope="request"/> <!-- scope="request"로 index값까지 주소줄에 전송 -->
	<c:set var="PAGENUMBER" value="10" scope="request"/>
	<c:import url="paging.jsp"/>
</div>
<br>
<c:if test="${sessionScope.id ne null }">
	<div id="writeBtn"><a href="./galleryWrite">글쓰기<img alt="write" src="./img/edit.png"> </a></div>
</c:if>
</div> <!-- mainwrapper -->
</div> <!-- main -->
</div> <!-- container -->
</body>
</html>