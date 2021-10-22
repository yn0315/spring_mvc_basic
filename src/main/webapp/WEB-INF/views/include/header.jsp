<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    #logo {
        color: darkslategrey;
        text-decoration: none;
    }

    #logo:hover {
        color: rgb(101, 117, 117);
        text-decoration: underline;
    }
</style>

<header class="navbar navbar-expand-lg navbar-light bg-light">

    <h1>
        <a id="logo" href="/board/list">My First Website</a>
    </h1>
    <div class="menu-box">
    <c:if test="${loginUser==null}">
        <a class ="btn btn-outline-secondary" href="/member/sign-in">로그인</a>
        <a class="btn btn-dark" href="/member/sign-up">회원가입</a>
    </c:if>

    <c:if test="${loginUser !=null}">
        <span>${loginUser.name}님 안녕하세요</span>
        <a class="btn btn-danger" href="/member/logout">로그아웃</a>
    </c:if>

    </div>
</header>