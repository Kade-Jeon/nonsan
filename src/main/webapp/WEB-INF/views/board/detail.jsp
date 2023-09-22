<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../layout/header.jsp"%>
<%--글 상세보기--%>
<div class="container">
    <button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
    <%--로그인한 id와 동일한 유저가 작성한 글일 때만 삭제, 수정 버튼이 보이도록 if 추가
        principal은 PrincipalDetail의 User의 id를 의미 --%>
    <%--<c:if test="${board.user.id == principal.user.id}">--%>
    <c:if test="${board.user.id == user.id}">
        <a href="/board/${board.id}/updateForm" class="btn btn-warning">수정</a>
        <button id="btn-delete" class="btn btn-danger">삭제</button>
    </c:if>
    <br /><br />
    <div>
        글 번호 : <span id="id"><i>${board.id} </i></span>
        작성자 : <span><i>${board.user.username} </i></span>
    </div>
    <br />
    <div>
        <h3>${board.title}</h3>
    </div>
    <hr />
    <div>
        <div>${board.content}</div>
    </div>
    <hr />

    <%--댓글 기능 구현--%>
    <div class="card">
        <form>
            <%--<input type="hidden" id="userId" value="${principal.user.id}" />--%>
            <input type="hidden" id="userId" value="${user.id}" />
            <input type="hidden" id="boardId" value="${board.id}" />
            <div class="card-body">
                <textarea id="reply-content" class="form-control" rows="1"></textarea>
            </div>
            <div class="card-footer">
                <button type="button" id="btn-reply-save" class="btn btn-primary">등록</button>
            </div>
        </form>
    </div>
    <br />

    <%--댓글 리스트 기능 구현--%>
    <div class="card">
        <div class="card-header">댓글 리스트</div>
        <ul id="reply-box" class="list-group">
            <c:forEach var="reply" items="${board.replys}">
            <li id="reply-${reply.id}" class="list-group-item d-flex justify-content-between">
                <div>${reply.content}</div>
                <div class="d-flex">
                    <div class="font-italic">작성자 : ${reply.user.username} &nbsp;</div>
                    <%--<c:if test="${reply.user.id == principal.user.id}">--%>
                    <c:if test="${reply.user.id == user.id}">
                        <button onclick="index.replyDelete(${board.id}, ${reply.id})" class="badge">삭제</button>
                    </c:if>
                </div>
            </li>
            </c:forEach>
        </ul>
    </div>
</div>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>