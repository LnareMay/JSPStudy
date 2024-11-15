<%@page import="DBModule.flight"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
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
 	List<flight> flightList = (ArrayList)request.getAttribute("flightList");
 	flight fligt = new flight();
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
                <div class="search-section-list">
                	<c:if test="<%= flightList.isEmpty() %>">
        				<h1>검색 조건에 맞는 항공편이 없습니다.</h1>
       				</c:if>
       				<ol>
       					<c:forEach var="flight" items="<%= flightList %>">
       						<div class="input-group">
	       						<label for="memInfo" style="color: black; text-align: left;">${ flight.getFlightcode() }</label>
	                        		<input type="button" id="memInfo" name="memInfo" style="cursor: pointer; background-color: #1c7bbf; color: #ffffff;"
	                        		onclick="location.href='flightDetail.form?flightcode=${ flight.getFlightcode() }'"
	                        		value="${ flight.getFlightname() } 항공편&nbsp출발/도착지  ${ flight.getFromairport() } / ${ flight.getToairport() }">
                        		</div>
       					</c:forEach>
       				</ol>
       				<c:if test='<%= member.getIsmanager().equalsIgnoreCase("C") %>'>
        				<div style="display: flex; flex-direction: row-reverse;">
                    		<button type="button" class="search-button" style="width: 150px; align-content: flex-end;"
                    		onclick="location.href='./registerFlight.jsp'"
                    		>항공편 등록</button>
                    	</div>
       				</c:if>
                </div>
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
