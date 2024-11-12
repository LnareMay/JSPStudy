<%@page import="DBModule.member"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
	@import url('https://cdn.rawgit.com/moonspam/NanumSquare/master/nanumsquare.css');
</style>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>GooD Flight</title>
    <link rel="stylesheet" href="../css/mainPage.css">
    <link rel="stylesheet" href="../css/css.css" />
    <script type="text/javascript">
	  	var imgArray=new Array();
	  	imgArray[0]="../assets/1.jpg";
	  	imgArray[1]="../assets/2.jpg";
	  	imgArray[2]="../assets/3.jpg";
	  	imgArray[3]="../assets/4.jpg";
	
	  	function showImage(){
		   var imgNum=Math.round(Math.random()*3);
		   var objImg=document.getElementById("bodyImg");
		   objImg.background=imgArray[imgNum];
		   setTimeout(showImage,5000);
	 	}

 </script>
 <%
 	member member = (member)session.getAttribute("Member");
 	String tel = member.getTel();
 	if(tel.length() == 10){
 		tel = tel.substring(0, 3) + "-" + tel.substring(3,6) + "-" + tel.substring(7);
 	} else if(tel.length() == 11){
 		tel = tel.substring(0, 3) + "-" + tel.substring(3,7) + "-" + tel.substring(7);
 	}
 %>
</head>
<body onload="showImage()" id="bodyImg">
    <header>
        <div class="logo">
            <img src="../assets/logo.png" alt="Skyscanner Logo">
        </div>
        <nav>
            <ul>
                <li><a href="myPage.jsp">마이 페이지</a></li>
                <li><a href="#">예약 내역</a></li>
                <li><%= member.getName() %> 님</li>
                <li><a href="../login.jsp">로그아웃</a></li>
            </ul>
        </nav>
    </header>

    <main>
        <form method="post" action="updateMember.do" style="display: flex; align-content: center; justify-content: center;">
    	<div id="logInBox" style="margin-top: 0px; height: auto;">
					<input type="hidden" name="id" id="id"
						autocomplete="off" required value="<%= member.getID() %>">
			<label id="title">내 정보</label>
			<div id = "inputArea">
				<input type="text" name="id" id="id"
					autocomplete="off" required value="<%= member.getID() %>" disabled="disabled">
				<label for="id">아이디</label>
			</div>
			<div id = "inputArea">
				<input type="password" name="pw" id="pw"
					autocomplete="off" required value="<%= member.getPassword() %>">
				<label for="pw">패스워드</label>
			</div>
			<div id = "inputArea">
				<input type="text" name="name" id="name"
					autocomplete="off" required value="<%= member.getName() %>">
				<label for="name">이름</label>
			</div>
			<div id = "inputArea">
				<input type="email" name="email" id="email"
					autocomplete="off" required value="<%= member.getEmail() %>">
				<label for="email">이메일</label>
			</div>
			<div id = "inputArea">
				<input type="text" name="tel" id="tel"
					pattern="[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}"
					autocomplete="off" required value="<%= tel %>">
				<label for="tel">연락처</label>
			</div>
			<div id = "inputArea">
				<input type="text" name="createDate" id="createDate"
					autocomplete="off" required value="<%= member.getCreatedate() %>" disabled="disabled">
				<label for="email">가입 날짜</label>
			</div>
			<div id="btnArea" style="margin-bottom: 37px">
				<input type="submit" value="회원 정보 수정">
			</div>
		</div>
	</form>
    </main>
    <footer>
        <div class="footer-content">
            <ul>
                <li><a href="#">개인정보처리방침</a></li>
                <li><a href="#">이용약관</a></li>
                <li><a href="#">고객지원</a></li>
            </ul>
        </div>
    </footer>
</body>
</html>
