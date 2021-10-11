<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admin</title>
<link href="./css/main.css" rel="stylesheet">
<link href="./css/menu.css" rel="stylesheet">
<style type="text/css">
#adminMenu{
	width: 100%;
	height: 30px;
	background-color: blue;
}
#adminMain{
	width: 100%;
	height: clac(100% - 30px);
	/* background-color: gray; */
}
table {
	border-collapse: collapse;	
	height: auto;
	width: 100%;
}
th{
	background-color: gray;
	border-bottom: 1px white solid;
}
tr:nth-child(even){
	background-color: #c1c1c1;
}
tr:nth-child(odd){
	background-color:aqua;
}
tr:hover{
	background-color: gray;
}
td{
	border-bottom: 1px white solid;
}
#paging{
	margin-top:5px;
	display: block;
	margin:0 auto;
	width: 100%;
	height: 30px;
	text-align: center;
	/* margin: 5px; */
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
		<div id="admin">
			<div id="adminMenu">
				<!-- 꾸며주세요 -->
				회원관리 | 게시글 관리 | 로그 관리 | ....
			</div>
			<div id="adminMain">
				<!-- 실제 내용 출력 
				로그 전체 불러오기를 하겠습니다.
				한 화면에 20개 출력되게 해주세요  = 게시판
				table로 만드세요.
				--> 
				<h2>전체 글 수 : ${totalCount } 개 / 페이지 : ${page }</h2>
				<form action="admin" method="post">
				<select name="searchname">
					<option value="ip">ip</option>
					<option value="target">target</option>
					<option value="etc">etc</option>
				</select>
				<input type="text" id="search" name="search">
				<button type="submit">검색</button>
				</form>
				
				<c:choose>
					<c:when test="${fn:length(list) > 0 }">
				<table style="width: 100%; font-size: smaller;">
					<tr>
						<th>no</th>
						<th>
						<select onchange="select()" id="ip">
							<option value="">ip를 선택</option>
							<c:forEach items="${ipList }" var="i">
								<c:if test="${i eq ip }">
									<option value="${i }" selected="selected">${i }</option>
								</c:if>
								<c:if test="${i ne ip }">
									<option value="${i }">${i }</option>
								</c:if>
							</c:forEach>
						</select>
						</th>
						<th>date</th>
						<th>
						<select onchange="select()" id="target">
							<option value="">target을 선택</option>
							<c:forEach items="${targetList }" var="t">
								<c:if test="${target eq t }">
									<option value="${t }" selected="selected">${t }</option>
								</c:if>
								<c:if test="${target ne t }">
									<option value="${t }">${t }</option>
								</c:if>
							</c:forEach>
						</select>
						</th>
						<th>etc</th>
					</tr>
					<c:forEach items="${list }" var="l">
					<tr>						
						<td>${l.log_no }</td>
						<td>${l.log_ip }</td>
						<td>${l.log_date }</td>
						<td>${l.log_target }</td>
						<td><c:out value="${fn:substring(l.log_etc, 0, 40) }"/> </td>
					</tr>
					</c:forEach>
				</table>
					<div id="paging">
						<!-- 이동할 페이지명을 변수처리 -->
						<c:set var="pageName" value="admin" scope="request"/>
						<c:set var="PAGENUMBER" value="20" scope="request"/>
						<c:import url="paging.jsp"/>
					</div>
					</c:when>
					<c:otherwise>
						<h2>출력할 데이터가 없습니다</h2>
					</c:otherwise>
				</c:choose>
				
			</div>
		</div>
	</div>
</div>
</div>

<script type="text/javascript">
function select(){
	//value값을 가져오고 싶다면 ?
	//alert("!");
	var ip = document.getElementById("ip").value;
	var target = document.getElementById("target").value;
	//값 오는 것이 확인된다면 서블릿을 보내서 해당 ip것만 받도록 합니다.
	//location.href='admin?ip=' + ip + '&page=' + ${page };
	location.href='admin?ip='+ip+'&target='+target;
}
</script>



</body>
</html>



