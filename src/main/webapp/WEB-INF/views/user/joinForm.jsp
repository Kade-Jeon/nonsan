<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../layout/header.jsp"%>
<%--회원가입폼--%>
<div class="container">
    <form>
        <div class="form-group">
            <label for="uid">Id</label>
            <input type="text" class="form-control" placeholder="아이디를 입력해주세요" id="uid">
        </div>

        <div class="form-group">
            <label for="nickname">Nickname</label>
            <input type="nickname" class="form-control" placeholder="닉네임을 입력해주세요" id="nickname">
        </div>

        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" placeholder="비밀번호를 입력해주세요" id="password">
        </div>

        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" class="form-control" placeholder="이메일을 입력해주세요" id="email">
        </div>

    </form>
    <button id="btn-save" class="btn btn-primary">회원가입</button>
</div>

<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>
