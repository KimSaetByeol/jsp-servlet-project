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
<style type="text/css">
table {
	margin: 0 auto;
	margin-bottom: 30px;
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
#commentInput{
	width: 980px;
	height: 180px;
	background-color: #F8F8FF;
}
#commentInput textarea {
	margin:0;
	padding:0;
	width: 100%;
	height: 120px;
}
#commentInput button {
	margin:0;
	padding:0;
	height: 30px;
	width: 100%;
}
#commIf{
	margin: 10px;
}
#commbox{
	margin-top: 10px;
}
#detail{
	margin-top: 50px;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	//수정하기 기능
	$(".modifyInput").click(function(){
		var fcontent = $(this).children(".fcontent").text();
		var fcno = $(this).children(".fcno").text();
		var fno = $(this).children(".fno").text();
		$(this).parent().html("<form action='./commentModify' method='post'>"
				+"<textarea name='fcontent' style='padding: 3px; margin-top: 5px; width: 760px; height: 90px;'>"+fcontent+"</textarea>"
				+"<input type='hidden' name='fcno' value='"+fcno+"'>"
				+"<input type='hidden' name='fno' value='"+fno+"'>"
				+"<div id='orModi'><button style='border: 0px; background-color: #F8F8FF; float: left; font-size: 14px;'>수정하기 | </button></form>"
				+"<div class='clear' style='float: left; font-size: 14px;'>수정취소</div></div><br><br>");
		//alert('fcontent: ' + fcontent);
		//alert('fno : ' + fno);
		//$(this).html("<form><textarea>"+fcontent+fno+"</textarea><button>수정하기</button></form>");
		$(".clear").click(function(){
			$(this).parent().parent().html(
					"<div class='modifyInput'><p style='font-size: 12px; color:#9370DB;'>수정하려면 댓글을 클릭하세요.</p>"
					+"<div class='fcontent'>"+fcontent+"</div>"
					+"<div class='fno' style='display:none;'>"+fno+"</div>"
					+"<div class='fcno' style='display:none;'>"+fcno+"</div>"
					+"</div>"
					);
		});
	});
	
	//댓글쓰기 기능
	var now = 0;
	   $("#commentInput").hide();
	   $(".commentWrite").bind("click focus",function(){
	      var offset = $(".commentWrite").offset();
	      $("html, body").animate({scrollTop:offset.top},900);
	      if (now == 0) {      
	         $("#commentInput").slideDown(1000);
	         $("#commentInput").html('<form action="./commentInput" method="post">'
	         +'<textarea name="fcontent" style="padding: 5px; margin-top: 5px; height: 90px;"></textarea>'
	         + '<input type="hidden" name="fno" value="${dto.fno }">'
	         + '<button style="margin-top:5px; width: 200px; height: 40px; float: right;">댓글쓰기</button></form>');
	         $(this).text("닫으려면 클릭하세요.▲");
	         now = 1;
	      } else {
	         $("#commentInput").slideUp(1000);
	         $(this).text("댓글을 쓰려면 클릭하세요.▼");
	         now = 0;
	      }
	   });
});
</script>
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
					<td>${dto.ftitle }</td>
				</tr>
				<tr>
					<th>글쓴이</th>
					<td>${dto.name }(${dto.id })</td>
				</tr>
				<tr>
					<th>쓴날짜</th>
					<td>${dto.fdate }</td>
				</tr>
				<tr>
					<th>조회수</th>
					<td>${dto.fcount }</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
					<c:if test="${dto.file ne null }">
						<img alt="${dto.file }" src="./upload/${dto.file }" width="60%" style="margin: 5px;"> <br>
					</c:if>
						${dto.fcontent }
						<br><br>
					</td>
				</tr>
			</table>
		</div>
		<!-- 댓글 유무를 없으면 달아달라고 표시 -->
		<!-- 있으면 그 글의 수 표시 -->
		<div id="commIf">
		<c:choose>
			<c:when test="${dto.commentcount > 0}">			
				${dto.commentcount }개의 댓글이 있습니다 	
			</c:when>
			<c:otherwise>
				댓글이 없습니다. 댓글을 달아주세요.
			</c:otherwise>
		</c:choose>
		</div>
		<hr>
		<!-- 여기에 댓글 찍기 -->
		<c:choose>
			<c:when test="${fn:length(commentList) > 0 }">
				<c:forEach items="${commentList }" var="i">
				<div id="commbox">
				<hr>
					<button>${i.fcno } / ${i.name }(<small>${i.id }</small>)</button>
					${i.fcdate } 
					<button>${i.fclike }</button>
					<div class="modifyBox">
						<div class="modifyInput">
							<p style="font-size: 12px; color:#9370DB;">수정하려면 댓글을 클릭하세요.</p>
							<div class="fcontent"><p>${i.fccontent }</p></div>
							<div class="fcno" style="display: none;">${i.fcno }</div>
							<div class="fno" style="display: none;">${i.fno }</div>
						 </div>
					 </div>
				</div>
				</c:forEach>	
			</c:when>
			<c:otherwise>
				댓글이 없습니다.
			</c:otherwise>
		</c:choose>
		<!-- 여기에 댓글쓰기 창을 연결합니다. -->
		<!-- Jquery로 할겁니다. 준비물이 뭔지 확인해주세요. -->
		<br>
		<!-- 로그인 한 사람만 볼 수 있게 -->
		<c:if test="${sessionScope.id ne null}">
			<div class="commentWrite">댓글을 쓰려면 클릭하세요.▼</div>
			<div id="commentInput"></div>
		</c:if>
	</div>
	<br>
	<a href="./index">게시판으로</a>
</div>
</div>
</body>
</html>