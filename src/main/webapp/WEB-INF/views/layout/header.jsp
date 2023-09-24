<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%
    session = request.getSession();
    String uid = (String) session.getAttribute("uid");
    String nickName = (String) session.getAttribute("nickName");
%>
<%--<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal" var="principal" />
</sec:authorize>--%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Blog</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

    <%--summer note 코드 추가--%>
    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
</head>
<body>
    <nav class="navbar navbar-expand-md bg-dark navbar-dark">
        <%--세션 사용자의 uid 정보를 받아와서 출력--%>
        <a class="navbar-brand" href="/">${uid}</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="/">글목록</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/board/saveForm">글쓰기</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="http://localhost:1888/hgyr/logout">로그아웃</a>
            </li>
        </ul>
        </div>
        </nav>
        <br/>