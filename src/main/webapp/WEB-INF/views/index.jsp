<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="layout/header.jsp"%>

<div class="container">
<%-- ${boards} : EL 표현식, Request 정보가 넘어올 때 jstl에서 boards를 받을 수 있음
     페이징 구현 후 ${boards.content}로 수정--%>
<%-- 톰켓의 4가지 Scope 영역중에 application, session, request, pageContext가 있다.
     그 중에 pageContext 영역에 보관하고 EL표현식으로 찾아서 사용--%>
<c:forEach var="board" items="${boards.content}">
    <div class="card m-2">
        <div class="card-body">
            <h4 class="card-title">${board.title}</h4>
            <a href="/board/${board.id}" class="btn btn-primary">상세보기</a>
        </div>
    </div>
</c:forEach>
    <ul class="pagination justify-content-center">
        <c:choose>
            <c:when test="${boards.first}">
                <li class="page-item disabled"><a class="page-link" href="?page=${boards.number - 1}">Previous</a></li>
            </c:when>
            <c:otherwise>
                <li class="page-item"><a class="page-link" href="?page=${boards.number - 1}">Previous</a></li>
            </c:otherwise>
        </c:choose>
        <c:forEach var="pageNumber" begin="1" end="${boards.totalPages}">
            <li class="page-item"><a class="page-link" href="?page=${pageNumber-1}">${pageNumber}</a></li>
        </c:forEach>
        <c:choose>
            <c:when test="${boards.last}">
                <li class="page-item disabled"><a class="page-link" href="?page=${boards.number + 1}">Next</a></li>
            </c:when>
            <c:otherwise>
                <li class="page-item"><a class="page-link" href="?page=${boards.number + 1}">Next</a></li>
            </c:otherwise>
        </c:choose>
    </ul>
</div>

<%@ include file="layout/footer.jsp"%>