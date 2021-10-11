$(document).ready(function(){
	//수정하기 기능
	$(".modifyInput").click(function(){
		//var fcontent = $(this).children().first().text();
		//var fcno = $(this).children().last().text();
		//변경
		var fcontent = $(this).children(".fcontent").text();
		var fno = $(this).children(".fno").text();
		var fcno = $(this).children(".fcno").text();
		$(this).parent().html(
				"<form action='./commentModify' method='post'>"
				+"<textarea name='content'>"+fcontent+"</textarea>"
				+"<input type='hidden' name='fcno' value='"+fcno+"'>"
				+"<input type='hidden' name='fno' value='"+fno+"'>"
				+"<button>수정하기</button>"
				+"</form>"
				+"<div class='clear1'>수정취소</div>");
				//content변경 + 댓글번호
	$(".clear1").click(function(){
		//alert(htmlB);
		$(this).parent().html(
				'<div class="modifyInput">수정하기'
		 		+'<div class="fcontent">'+fcontent+'</div>'
		 		+'<div class="fno" style="display: none;">'+fno+'</div>'
		 		+'<div class="fcno" style="display: none;">'+fcno+'</div>'
			 	+'</div>');
	});
	});
	
	
	//댓글쓰는 기능
	var now = 0;
	   $("#commentInput").hide();
	   $(".commentWrite").bind("click focus",function(){
	      var offset = $(".commentWrite").offset();
	      $("html, body").animate({scrollTop:offset.top},900);
	      if (now == 0) {      
	         $("#commentInput").slideDown(1000);
	         $("#commentInput").html('<form action="./commentInput" method="post"><textarea name="fcontent"></textarea><input type="hidden" name="fno" value="${dto.fno }"><button>댓글쓰기</button></form>');
	         $(this).text("닫으려면 클릭하세요.▲");
	         now = 1;
	      } else {
	         $("#commentInput").slideUp(1000);
	         $(this).text("댓글을 쓰려면 클릭하세요.▼");
	         now = 0;
	      }
	   });

});