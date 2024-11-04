<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<link rel="stylesheet" href="../css/mainPage.css" />
<head>
    <meta charset="UTF-8">
    <title>항공권 예약</title>
    <script type="text/javascript">
 		var imgArray=new Array();
  		imgArray[0]="../assets/1.jpg";
  		imgArray[1]="../assets/2.jpg";
  		imgArray[2]="../assets/3.jpg";
  		imgArray[3]="../assets/4.jpg";
  		imgArray[4]="../assets/5.jpg";
  		imgArray[5]="../assets/6.jpg";
  		imgArray[6]="../assets/7.jpg";

  		function showImage(){
  		   var imgNum=Math.round(Math.random()*3);
  		   var objImg=document.getElementById("introimg");
  		   objImg.src=imgArray[imgNum];
  		   setTimeout(showImage,5000);
  		  }

  		 </script>
</head>
<body>
    <div class="container">
        <h1>항공권 예약 시스템</h1>
        <form action="getInit.form" method="post" class="reservation-form">
            <div class="form-group">
                <label for="from">출발지:</label>
                <input type="text" id="from" name="from" required placeholder="출발지를 입력하세요">
            </div>
            <div class="form-group">
                <label for="to">목적지:</label>
                <input type="text" id="to" name="to" required placeholder="목적지를 입력하세요">
            </div>
            <div class="form-group">
                <label for="departure">출발일:</label>
                <input type="date" id="departure" name="departure" required>
            </div>
            <div class="form-group">
                <label for="return">귀국일:</label>
                <input type="date" id="return" name="return">
            </div>
            <button type="submit" class="submit-button">검색</button>
        </form>
    </div>
</body>
</html>