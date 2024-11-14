<%@page import="DBModule.flight"%>
<%@page import="DBModule.member"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	@import url('https://cdn.rawgit.com/moonspam/NanumSquare/master/nanumsquare.css');
</style>
<!DOCTYPE html>
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
	flight flight = (flight)request.getAttribute("flight");
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
                <li><a href="reservationhis.form">예약 내역</a></li>
                <li><%= member.getName() %> 님</li>
                <li><a href="../login.jsp">로그아웃</a></li>
            </ul>
        </nav>
    </header>

    <main>
        <section class="search-section">
            <div class="search-bar">
                <form action="reservation.do" method="post">
                	<input type="hidden" id="memberID" name="memberID" value="<%= member.getID() %>">
                    <div class="input-group">
                        <label for="flightCode">항공 코드</label>
                        <input type="text" id="flightCode" name="flightCode" value="<%= flight.getFlightcode() %>" readonly="readonly">
                    </div>
                    <div class="input-group">
                        <label for="flightName">항공기 명</label>
                        <input type="text" id="flightName" name="flightName" value="<%= flight.getFlightname() %>" readonly="readonly">
                    </div>
                    <div class="input-group">
                        <label for="from">출발지</label>
                        <input type="text" id="from" name="from" value="<%= flight.getFromairport() %>" readonly="readonly">
                    </div>
                    <div class="input-group">
                        <label for="to">목적지</label>
                        <input type="text" id="to" name="to" value="<%= flight.getToairport() %>" readonly="readonly">
                    </div>
                    <div class="input-group">
                        <label for="startTime">출발 시간</label>
                        <input type="text" id="startTime" name="startTime" value="<%= flight.getStarttime() %>" readonly="readonly">
                    </div>
                    <div class="input-group">
                        <label for="endTime">도착 시간</label>
                        <input type="text" id="endTime" name="endTime" value="<%= flight.getEndtime() %>" readonly="readonly">
                    </div>
                    <div class="input-group">
                        <label for="comment">상세 설명</label>
                        <input type="text" id="comment" name="comment" value="<%= flight.getComment() %>" readonly="readonly">
                    </div>
                    <div class="input-group">
                        <label for="seatsClass">좌석</label>
                    </div>
                    <div class="input-group" style="display: flex;">
                        <label>First</label><input type="radio" id="seatsClass" name="seatsClass" value="First"> 
                        <label>Business</label><input type="radio" id="seatsClass" name="seatsClass" value="Business">
                        <label>Economy</label><input type="radio" id="seatsClass" name="seatsClass" value="Economy">
                    </div>
                    <div class="input-group">
                        <label for="seats">인원 수</label>
                        <input type="number" id="seats" name="seats">
                    </div>
                    <button type="submit" class="search-button">예약</button>
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
