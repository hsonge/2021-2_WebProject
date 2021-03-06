<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import = "leehyosong.dao.BoardDao, leehyosong.dto.BoardDto, java.util.List, java.sql.Date" %>
   
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
	<title>게시판</title>
	<link rel="stylesheet" type="text/css" href="css/board.css">
</head>
<body>
<div class="container">
	<h2>상세보기</h2>
	<br/>
	<form action="bupdate.do" method="post">
		<input type="hidden" name="id" value="${dto.title}">
		<table class="table table-striped table-hover">
			<tr>
				<th>title</th><td>${dto.title}</td>
			</tr>
			<tr>
				<th>context</th><td><input type="text" value="${dto.context}" name="context"></td>
			</tr>
			<tr>
				<th>writeDate</th><td colspan="3"><input type="text" value="${dto.writeDate}" name="joinDate"></td>
			</tr>
			<tr>
				<td colspan="4">
					<input type="submit" value ="게시글 수정" >
					<input type="button" value ="게시글 삭제" onclick ="location.href='bdelete.do?title=${dto.title}'">
					<input type="button" value ="게시글 목록" onclick ="location.href='blist.do'">
					<input type="button" value ="게시글 등록" onclick ="location.href='binsertForm.do'">
				</td>
			</tr>
		</table><br><br>
	</form>
</div>
</body>
</html>