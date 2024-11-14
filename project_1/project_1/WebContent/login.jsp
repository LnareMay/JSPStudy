<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link rel="stylesheet" href="./css/css.css" />
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<%
	session.removeAttribute("Member");
%>
<body id="backBody">
	<img src="./assets/logo.png" id="logoImage">
	<form method="post" action="login.do">
    	<div id="logInBox">
			<label id="title">LOGIN</label>
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
			<div id="btnArea">
				<input type="submit" value="로그인">
			</div>
			<div id="checkUserArea">
				<a href="">ID/PW 찾기 </a>
				<a> | </a>
				<a href="signUp.jsp" style="color: #1c7bbf"> 회원가입</a>
			</div>
		</div>
	</form>
</body>
</html>