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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
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
                <li><a href="myPage.jsp">관리자 정보</a></li>
                <li><a href="viewAllMember.form">회원 목록</a></li>
                <li><%= member.getName() %> 님</li>
                <li><a href="../login.jsp">로그아웃</a></li>
            </ul>
        </nav>
    </header>

    <main>
    <section class="search-section">
            <div class="search-bar">
        	<form action="updateUser.do" method="post">
                    <div class="input-group">
                        <label for="ID">아이디</label>
                        <input type="text" id="ID" name="ID" value="<%= memInfo.getID() %>" readonly="readonly">
                    </div>
                    <div class="input-group">
                        <label for="name">이름</label>
                        <input type="text" id="name" name="name" value="<%= memInfo.getName() %>" readonly="readonly">
                    </div>
                    <div class="input-group">
                        <label for="email">이메일</label>
                        <input type="text" id="email" name="email" value="<%= memInfo.getEmail() %>" readonly="readonly">
                    </div>
                    <div class="input-group">
                        <label for="tel">전화번호</label>
                        <input type="text" id="tel" name="tel" value="<%= tel %>" readonly="readonly">
                    </div>
                    <div class="input-group">
                        <label for="createDate">가입 일자</label>
                        <input type="text" id="createDate" name="createDate" value="<%= memInfo.getCreatedate() %>" readonly="readonly">
                    </div>
                    <div class="input-group">
                        <label for="lastUpdateDate">최근 변경 일자</label>
                        <input type="text" id="lastUpdateDate" name="lastUpdateDate" value="<%= memInfo.getLastupdatedate() %>" readonly="readonly">
                    </div>
                    <div class="input-group">
                        <label for="deleteFlag">삭제 여부</label><input type="checkbox" id="deleteFlag" name="deleteFlag" disabled="disabled" style="color: #1c7bbf;" <% if(memInfo.getDeleteflag() != null && memInfo.getDeleteflag().equalsIgnoreCase("C")){ out.write("checked"); } %>>
                    </div>
                    <div class="input-group">
                        <label for="seatsClass">권한</label>
                    </div>
                    <div class="input-group" style="display: flex;">
                        <label style="display: flex;">기장</label><input type="radio" id="role" name="role" value="driver" <% if(memInfo.getIsdriver() != null && memInfo.getIsdriver().equalsIgnoreCase("C")){ out.write("checked"); } %>> 
                        <label style="display: flex;">관리자</label><input type="radio" id="role" name="role" value="manager" <% if(memInfo.getIsmanager() != null && memInfo.getIsmanager().equalsIgnoreCase("C")){ out.write("checked"); } %>>
                    </div>
                    <div style="display: flex;">
                    	<button type="submit" class="search-button" style="margin-right: 5px;">수정</button>
                    	<button type="button" class="btn btn-danger" style="margin-right: 5px; width: 100%; font-size: 18px" onclick="location.href='deleteOrReviveUser.do?id=<%= memInfo.getID() %>&doDelete=true'">삭제</button>                    
                    	<button type="button" class="btn btn-success" style="margin-right: 5px; width: 100%; font-size: 18px" onclick="location.href='deleteOrReviveUser.do?id=<%= memInfo.getID() %>&doDelete=false'">복원</button>                    
                	</div>
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
