<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<style>
	.login-btn {
/* 		color: black;
		background-color: #FFC312;
		width: 100px; */
	}
	
	.login-btn:hover {
		color: black;
		background-color: white;
	}
	
	.input-group-prepend span {
		color: black;
		border-left-color: #FFC312;
		width: 40px;
		border: 0 !important;
	}
</style>
<title>회원 가입</title>
</head>
<body>
	<div class="container" align="center">
		<div class="jumbotron">
			<h3>회원 가입</h3>
			<p>회원 가입에 필요한 정보들을 입력해주세요</p>
		</div>
		<form action="insertMember.do" method="post" 
		name="memberForm">
			<div class="form-group input-group">
				<div class="input-group-prepend"><span class="input-group-text"><i class="fas fa-user"></i></span></div>
				<input type="text" class="form-control" name=id id="id" required="required" placeholder="아이디 입력"/>
			</div>
			<div class="form-group input-group">
				<div class="input-group-prepend"><span class="input-group-text"><i class="fas fa-key"></i></span></div>
				<input type="password" class="form-control" name="pw" id="pw" required="required" placeholder="비밀 번호 입력"/>
			</div>
			<div class="form-group input-group">
				<div class="input-group-prepend"><span class="input-group-text"><i class="fas fa-font"></i></span></div>
				<input type="text" class="form-control" name="name" id="name" required="required" placeholder="이름 입력"/>
			</div>
			<div class="form-group input-group">
				<div class="input-group-prepend"><span class="input-group-text"><i class="fas fa-comment-dots"></i></span></div>
				<input type="number" class="form-control" name="age" id="age" required="required" placeholder="나이 입력"/>
			</div>
			<div class="form-group input-group">
				<div class="input-group-prepend"><span class="input-group-text"><i class="fas fa-venus-mars"></i></span></div>
				<input type="radio" class="form-control" name="gender" id="gender" value="M" checked="checked"/> 남자
				<input type="radio" class="form-control" name="gender" id="gender" value="F"/> 여자
			</div>
			<div class="form-group input-group">
				<div class="input-group-prepend"><span class="input-group-text"><i class="fas fa-mail-bulk"></i></span></div>
				<input type="email" class="form-control" name="email" id="email" required="required" placeholder="이메일 입력"/>
			</div>
			<div class="form-group input-group">
				<div class="form-group input-group mt-md-5 justify-content-center">
					<input type="submit" class="btn btn-primary float-right login-btn " value="회원가입"/>
					<input type="reset" class="btn btn-primary float-right login-btn ml-sm-2" value="새로고침"/>
				</div>
			</div>
		</form>
	</div>
</body>
</html>