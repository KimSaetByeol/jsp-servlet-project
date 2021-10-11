<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- 만드는 변수마다 모두  scope="request" 를 적어주세요.
이것은 변수의 참조 범위를 지정하는 것 입니다. scope="page"가 기본입니다. -->
    
<fmt:parseNumber integerOnly="true" var="totalPage" value="${totalCount / (PAGENUMBER*2) }" scope="request" />

<c:if test="${totalCount % PAGENUMBER ne 0 }">
	<c:set var="totalPage" value="${totalPage + 1 }" scope="request" />
</c:if>

<c:if test="${page % PAGENUMBER ne 0 }">
	<fmt:parseNumber integerOnly="true" var="startPage" value="${page / PAGENUMBER }" scope="request" />
	<c:set value="${startPage * PAGENUMBER + 1 }" var="startPage" scope="request" />
</c:if>

<c:if test="${page % PAGENUMBER eq 0 }">
	<c:set value="${page - (PAGENUMBER - 1) }" var="startPage" scope="request" />
</c:if>

<c:set value="${startPage + PAGENUMBER - 1 }" var="endPage" scope="request" />

<c:if test="${startPage + PAGENUMBER gt totalPage}">
	<c:set var="endPage" value="${totalPage }" scope="request" />
</c:if>

<button onclick="location.href='./${pageName}?page=1'" id="pageBtn">맨앞으로</button>

<c:if test="${page lt PAGENUMBER + 1 }">
	<button disabled="disabled" id="pageBtn">이전</button>
</c:if>

<c:if test="${page gt PAGENUMBER }">
	<button onclick="location.href='./${pageName}?page=${page - PAGENUMBER}'" id="pageBtn">
		이전</button>
</c:if>

<c:if test="${page eq 1 }">
	<button disabled="disabled" id="pageBtn">◀</button>
</c:if>

<c:if test="${page ne 1 }">
	<button onclick="location.href='./${pageName}?page=${page - 1}'" id="pageBtn">◀</button>
</c:if>

<c:forEach begin="${startPage }" end="${endPage }" var="i">
	<c:if test="${i eq page }">
		<a href="./${pageName}?page=${i }" style="color: #9370DB; font-weight: bold;">${i }</a>
	</c:if>
	
	<c:if test="${i ne page }">
		<a href="./${pageName}?page=${i }">${i }</a>
	</c:if>
</c:forEach>

<c:if test="${page eq totalPage }">
	<button disabled="disabled" id="pageBtn">▶</button>
</c:if>

<c:if test="${page ne totalPage }">
	<button onclick="location.href='./${pageName}?page=${page + 1}'" id="pageBtn">▶</button>
</c:if>

<c:if test="${page lt totalPage - PAGENUMBER - 1 }">
	<button onclick="location.href='./${pageName}?page=${page + PAGENUMBER}'" id="pageBtn">다음</button>
</c:if>

<c:if test="${page gt totalPage - PAGENUMBER }">
	<button disabled="disabled" id="pageBtn">다음</button>
</c:if>

<button onclick="location.href='./${pageName}?page=${totalPage }'" id="pageBtn">끝으로</button>
