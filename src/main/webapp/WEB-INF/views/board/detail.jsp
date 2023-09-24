<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../layout/header.jsp"%>
<%--글 상세보기--%>
<div class="container">
    <button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
    <%--게시글 작성자의 uid와 세션의 uid가 동일한 경우에만 수정, 삭제 버튼이 보임--%>
    <c:if test="${board.uid == uid}">
        <a href="/board/${board.id}/updateForm" class="btn btn-warning">수정</a>
        <button id="btn-delete" class="btn btn-danger">삭제</button>
    </c:if>
    <br /><br />
    <div>
        글 번호 : <span id="id"><i>${board.id} </i></span>
        작성자 : <span><i>${board.nickname} </i></span>
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
            <input type="hidden" id="nickname" value="${board.nickname}" />
            <input type="hidden" id="nickname" value="${reply.nickname}" />
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
                    <div class="font-italic">작성자 : ${reply.nickname} &nbsp;</div>
                    <%--게시글 작성자의 닉네임과 세션의 닉네임이 동일한 경우에만 댓글 삭제 버튼이 보임--%>
                    <c:if test="${reply.nickname == nickName}">
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