<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-expand navbar-dark bg-dark">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="<c:url value="/"/>">Home</a>
		</div>
		<div>
			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><c:choose>
					<c:when test="${empty logInfo}">
						<a class="nav-link"
						   href="<c:url value="/member/login"/>">로그인</a>
					</c:when>
					<c:otherwise>
						<a class="nav-link"
						   href="<c:url value="/member/logout"/>">로그아웃</a>
					</c:otherwise>
				</c:choose>
				</li>
				<li class="nav-item">
					<a class="nav-link"
					   href="<c:url value="/member/join"/>">회원가입</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" 
					href="<c:url value="/book/books"/>">도서 목록</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" 
					href="<c:url value="/book/addBook"/>">도서 등록</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" 
					href="<c:url value="/book/editBook"/>">도서 편집</a>
				</li>
<%--				<li class="nav-item">--%>
<%--					<a class="nav-link" --%>
<%--					href="<c:url value="/book/removeBook"/>">도서 삭제</a>--%>
<%--				</li>--%>
				<!-- <li class="nav-item">
					<a class="nav-link" 
					href="./BoardListAction.do?pageNum=1">게시판</a>
				</li> -->
			</ul>
		</div>
	</div>
</nav>