<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link rel="stylesheet" href="./css/css.css" />
<style>
#logInBox{
    height: 550px;
}
</style>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body id="backBody">
	<img src="./assets/logo.png" id="logoImage">
	<form method="post" action="signup.do">
    	<div id="logInBox">
			<label id="title">회원 가입</label>
			<div id = "inputArea">
				<input type="text" name="id" id="id"
					autocomplete="off" required>
				<label for="id">아이디</label>
			</div>
			<div id = "inputArea">
				<input type="password" name="pw" id="pw"
					autocomplete="off" required>
				<label for="pw">패스워드</label>
			</div>
			<div id = "inputArea">
				<input type="text" name="name" id="name"
					autocomplete="off" required>
				<label for="name">이름</label>
			</div>
			<div id = "inputArea">
				<input type="email" name="email" id="email"
					autocomplete="off" required>
				<label for="email">이메일</label>
			</div>
			<div id = "inputArea">
				<input type="text" name="tel" id="tel"
					pattern="[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}"
					autocomplete="off" required>
				<label for="tel">연락처</label>
			</div>
			<div id="btnArea">
				<input type="submit" value="회원 가입">
			</div>
		</div>
	</form>
</body>
</html>