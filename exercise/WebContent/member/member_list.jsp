<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" 
	integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ"
	crossorigin="anonymous">  
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script> 
<link href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="./css/button_style.css" />
<title>회원 목록 조회</title>
</head>
<body>
	<div class="container mt-sm-5" align="center">
		<div class="jumbotron">
			<h3>회원 목록 조회</h3>
			<c:if test="${ memberList.isEmpty() }">
				<h5><p class="bg-danger text-white">등록된 게시글이 없습니다!!!</p></h5>
			</c:if>
		</div>
		
		<%-- <form action="viewmemberlist.do" class="form-line">
			<div class="input-group">
				<select name="f" id="f" class="form-control col-sm-2 mr-sm-2">
					<option ${ param.f == "name" ? "selected" : ""} value="name">이름</option>
					<option ${ param.f == "email" ? "selected" : ""} value="email">이메일</option>
				</select>
				<input type="text" name="q" class="form-control col-sm-8 mr-sm-2" value="${ param.q }"
					placeholder="검색어를 입력하세요!!!"/>
				<button type="submit" class="btn btn-primary col-sm-1 mr-sm-2 login-btn">검색</button>
				<a href="boardWriteForm.do?p=${ param.p }" class="btn btn-success col-sm-1 login-btn">글쓰기</a>
			</div>
		</form>
		<br class="mt-sm-5" /> --%>
		
		<table class="table table-bordered table-striped table-hover">
			<thead class="table-dark" align="center">
				<tr>
					<th width="20%">이름</th>
					<th width="10%">ID</th>
					<th width="10%">나이</th>
					<th width="10%">성별</th>
					<th width="40%">이메일</th>
					<th width="10%">삭제</th>				
				</tr>
			</thead>
			<tbody>
			<c:forEach var="member" items="${ memberList }">
				<tr>
					<td>${ member.getName() }</td>
					<td>
						<a href="memberDetail.do?id=${ member.getId() }">${ member.getId() }</a>
					</td>
					<td>${ member.getAge() }</td>
					<td>${ member.getGender() == "M" ? "남자" : "여자"}</td>
					<td>${ member.getEmail() }</td>	
					<td align="center">
						<a href="boardDeleteForm.do?bno=${ member.getId() }"><i class="fas fa-trash-alt"></i></a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>