<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" 
	integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ"
	crossorigin="anonymous">  
<link href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="./css/button_style.css" />
<title>회원 상세 조회</title>
</head>
<body>
	<div class="container mt-sm-5" align="center">
		<div class="jumbotron">
			<h3>회원 정보 조회</h3>
		</div>
		
		<div class="form-group input-group">
			<div class="input-group-prepend"><span class="input-group-text"><i class="fas fa-user"></i></span></div>
			<input type="text" class="form-control" name="id" id="id" value="${ member.getId() }" readonly/>
		</div>	
			
		<div class="form-group input-group">
			<div class="input-group-prepend"><span class="input-group-text"><i class="fas fa-font"></i></span></div>
			<input type="text" class="form-control" name="name" id="name" value="${ member.getName() }" readonly/>
		</div>		
		
		<div class="form-group input-group">
			<div class="input-group-prepend"><span class="input-group-text"><i class="fas fa-comment-dots"></i></span></div>
			<input type="number" class="form-control" name="age" id="age" value="${ member.getAge() }" readonly/>
		</div>		
		
		<div class="form-group input-group">
			<div class="input-group-prepend"><span class="input-group-text"><i class="fas fa-venus-mars"></i></span></div>
			<input type="text" class="form-control" name="age" id="age" value="${ member.getGender() == 'M' ? '남자' : '여자'}" readonly/>
		</div>	
			
		<div class="form-group input-group">
			<div class="input-group-prepend"><span class="input-group-text"><i class="fas fa-mail-bulk"></i></span></div>
			<input type="email" class="form-control" name="email" id="email" value="${ member.getEmail() }" readonly/>
		</div>		
		<br />
		
		<div class="form-group input-group">
	
			<a href="memberModifyForm.do?id=${ member.getId() }" class="btn btn-success mr-sm-3 login-btn">수정</a>
			<a href="memberDeleteForm.do?id=${ member.getId() }" class="btn btn-success mr-sm-3 login-btn">삭제</a>
			<a href="viewMemberList.do" class="btn btn-success mr-sm-3 login-btn">회원 목록</a>
		</div>
	</div>
</body>
</html>