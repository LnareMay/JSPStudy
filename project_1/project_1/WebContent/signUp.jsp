<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<style>
	@import url('https://cdn.rawgit.com/moonspam/NanumSquare/master/nanumsquare.css');
</style>
<style>
*{
    padding: 0; margin: 0;
}
#backBody{
    height: 100vh;
    width: 100%;
    left: 0%;
    top:0%;
    position: fixed;
    background: #e6eaed;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    margin: 0%;
}
#logoImage{
    top:100px;
    left: 500px;
    height: 100px;
}
#logInBox{
    margin-top:52px;
		position: relative;
    background-color: #ffffff;
    height: 550px;
    width: 398px;
    border: 1px;
    border-color: #d7d7d7;
    border-radius: 3px;
    padding: 38px 38px 0px;
    text-align: start;
}
#title {
 	position: relative;
 	font-family: "NanumSquare", sans-serif;
  	font-size: 40px;
  	line-height: 100%;
  	color: #565656;
}
#inputArea {
  	font-family: "NanumSquare", sans-serif;
  	font-size: 12px;
  	margin-top: 26px;
  	width: 100%;
  	position: relative;
}
#inputArea input {
  	width: 100%;
  	padding: 20px 0px 5px;
  	background-color: transparent;
  	border: none;
  	border-bottom: 1px solid #bcbcbc;
  	font-size: 18px;
  	outline: none;
}
#inputArea label {
  	position: absolute;
  	left: 0px;
  	top: 20px;
  	transition: all 0.5s ease;
  	color: #969696;
}
#inputArea input:focus + label,
#inputArea input:valid + label {
  	top: 0%;
  	color: #969696;
}
#btnArea {
  	font-family: "NanumSquare", sans-serif;
  	font-size: 12px;
  	margin-top: 37px;
  	width: 100%;
}
#btnArea input {
  	width: 100%;
  	height: 48px;
  	background-color: #1c7bbf;
  	color: #ffffff;
  	font-size: 12px;
  	font-weight: 800;
  	border: none;
  	cursor: pointer;
  	border-radius: 3px;
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