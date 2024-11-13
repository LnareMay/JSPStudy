<%@page import="DBModule.member"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>GooD Flight</title>
<link rel="stylesheet" href="../css/mainPage.css">
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
 %>
<title></title>
</head>
<body onload="showImage()" id="bodyImg">
<header>
        <div class="logo">
            <img src="../assets/logo.png" alt="Skyscanner Logo">
        </div>
        <nav>
            <ul>
                <li><a href="myPage.jsp">마이 페이지</a></li>
                <li><a href="reservationhis.form">예약 내역</a></li>
                <li><%= member.getName() %> 님</li>
                <li><a href="../login.jsp">로그아웃</a></li>
            </ul>
        </nav>
</header>
	<main>
        <section class="search-section">
            <div class="search-bar">
                <form action="./mainPage.jsp" method="post">
                	<div class="input-group">
                        <label for="seatsClass">예약이 성공적으로 완료되었습니다.</label>
                    </div>
                    <button type="submit" class="search-button">확인</button>
                </form>
            </div>
        </section>
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