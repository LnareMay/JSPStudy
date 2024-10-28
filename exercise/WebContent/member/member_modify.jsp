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
<title>회원 정보 수정</title>
</head>
<body>
	<div class="container mt-sm-5" align="center">
		<div class="jumbotron">
			<h3>회원 정보 수정하기</h3>
		</div>
		
		<form action="memberModify.do" method="post" 
				name="memberForm">
			<div class="form-group input-group">
				<div class="input-group-prepend"><span class="input-group-text"><i class="fas fa-user"></i></span></div>
				<input type="text" class="form-control" name="id" id="id" value="${ member.getId() }" readonly/>
			</div>
			
			<div class="form-group input-group">
				<div class="input-group-prepend"><span class="input-group-text"><i class="fas fa-key"></i></span></div>
				<input type="password" class="form-control" name="pw" id="pw" required="required" placeholder="올바른 PW를 입력하세요"/>
			</div>
						
			<div class="form-group input-group">
				<div class="input-group-prepend"><span class="input-group-text"><i class="fas fa-font"></i></span></div>
				<input type="text" class="form-control" name="name" id="name" value="${ member.getName() }"/>
			</div>

			<div class="form-group input-group">
				<div class="input-group-prepend"><span class="input-group-text"><i class="fas fa-comment-dots"></i></span></div>
				<input type="number" class="form-control" name="age" id="age" required="required" value="${ member.getAge() }"/>
			</div>
			
			<div class="form-group input-group">
				<div class="input-group-prepend"><span class="input-group-text"><i class="fas fa-venus-mars"></i></span></div>
				<input type="radio" class="form-control" name="gender" id="gender" value="M" checked="checked"/> 남자
				<input type="radio" class="form-control" name="gender" id="gender" value="F"/> 여자
			</div>
			
			<div class="form-group input-group">
				<div class="input-group-prepend"><span class="input-group-text"><i class="fas fa-mail-bulk"></i></span></div>
				<input type="email" class="form-control" name="email" id="email" required="required" value="${ member.getEmail() }"/>
			</div>
			
			<div class="form-group input-group">
				<div class="form-group input-group mt-md-5 justify-content-center">
					<input type="submit" class="btn btn-success float-right login-btn" value="회원 정보 수정"/>
					<input type="reset" class="btn btn-success float-right login-btn ml-sm-2" value="새로고침"/>
					<a href="javascript:history.go(-1)" class="btn btn-success ml-sm-2 float-right">이전</a>
				</div>				
			</div>		
		</form>
	</div>
</body>
</html>