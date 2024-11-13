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
 	member memInfo = (member)request.getAttribute("member");
 	String tel = memInfo.getTel();
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
                <li><a href="reservationhis.form">예약 내역</a></li>
                <li><%= member.getName() %> 님</li>
                <li><a href="../login.jsp">로그아웃</a></li>
            </ul>
        </nav>
    </header>

    <main>
        <form action="reservation.do" method="post">
                    <div class="input-group">
                        <label for="flightCode">아이디</label>
                        <input type="text" id="flightCode" name="flightCode" value="<%= memInfo.getID() %>" readonly="readonly">
                    </div>
                    <div class="input-group">
                        <label for="flightCode">이름</label>
                        <input type="text" id="flightCode" name="flightCode" value="<%= memInfo.getName() %>" readonly="readonly">
                    </div>
                    <div class="input-group">
                        <label for="flightName">이메일</label>
                        <input type="text" id="flightName" name="flightName" value="<%= memInfo.getEmail() %>" readonly="readonly">
                    </div>
                    <div class="input-group">
                        <label for="from">전화번호</label>
                        <input type="text" id="from" name="from" value="<%= memInfo.getTel() %>" readonly="readonly">
                    </div>
                    <div class="input-group">
                        <label for="to">목적지</label>
                        <input type="text" id="to" name="to" value="<%= memInfo.getToairport() %>" readonly="readonly">
                    </div>
                    <div class="input-group">
                        <label for="startTime">출발 시간</label>
                        <input type="text" id="startTime" name="startTime" value="<%= memInfo.getStarttime() %>" readonly="readonly">
                    </div>
                    <div class="input-group">
                        <label for="endTime">도착 시간</label>
                        <input type="text" id="endTime" name="endTime" value="<%= memInfo.getEndtime() %>" readonly="readonly">
                    </div>
                    <div class="input-group">
                        <label for="comment">상세 설명</label>
                        <input type="text" id="comment" name="comment" value="<%= memInfo.getComment() %>" readonly="readonly">
                    </div>
                    <div class="input-group">
                        <label for="seatsClass">좌석</label>
                    </div>
                    <div class="input-group" style="display: flex;">
                        <label>기장</label><input type="radio" id="isdriver" name="isdriver"> 
                        <label>관리자</label><input type="radio" id="ismanager" name="ismanager">
                    </div>
                    <div class="input-group">
                        <label for="seats">인원 수</label>
                        <input type="number" id="seats" name="seats">
                    </div>
                    <button type="submit" class="search-button">예약</button>
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
