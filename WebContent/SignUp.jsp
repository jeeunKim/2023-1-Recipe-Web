<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix= "c" uri= "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign up</title>
</head>
<style>
* {
  margin: 0px;
  padding: 0px;
  text-decoration: none;
  font-family:sans-serif;

}

body {
  background-image:#34495e;
}
.box{
  margin: 0 auto;
  margin-top:50px;
  position:relative;
  background-color:#ffd966;
  width:500px;
  height:800px;
  top:100%;
  border-radius: 15px;
}

.joinForm {

  margin-top:50px;
  position:absolute;
  width:400px;
  height:700px;
  padding: 30px, 20px;
  background-color:#FFFFFF;
  text-align:center;
  top:40%;
  left:50%;
  transform: translate(-50%,-50%);
  border-radius: 15px;
}

.joinForm h2 {
  text-align: center;
  margin: 30px;
}

.textForm {
  border-bottom: 2px solid #adadad;
  margin: 30px;
  padding: 10px 10px;
}


.id {
  width: 100%;
  border:none;
  outline:none;
  color: #636e72;
  font-size:16px;
  height:25px;
  background: none;
}

.pw {
  width: 100%;
  border:none;
  outline:none;
  color: #636e72;
  font-size:16px;
  height:25px;
  background: none;
}

.name {
  width: 100%;
  border:none;
  outline:none;
  color: #636e72;
  font-size:16px;
  height:25px;
  background: none;
}

.email {
  width: 100%;
  border:none;
  outline:none;
  color: #636e72;
  font-size:16px;
  height:25px;
  background: none;
}

.nickname {
  width: 100%;
  border:none;
  outline:none;
  color: #636e72;
  font-size:16px;
  height:25px;
  background: none;
}

.cellphoneNo {
  width: 100%;
  border:none;
  outline:none;
  color: #636e72;
  font-size:16px;
  height:25px;
  background: none;
}
.button{
	margin:10px;
	background-color:#ffd966;
	border-radius:5px;
	border:none;
	padding:10px;
	font-size:larger;
}
.btn {
  position:relative;
  left:40%;
  transform: translateX(-50%);
  margin-bottom: 40px;
  width:80%;
  height:40px;
  background: linear-gradient(125deg,#81ecec,#6c5ce7,#81ecec);
  background-position: left;
  background-size: 200%;
  color:white;
  font-weight: bold;
  border:none;
  cursor:pointer;
  transition: 0.4s;
  display:inline;
}

.btn:hover {
  background-position: right;
}
</style>
<body>

<div class = "box">
<form class="joinForm" action = 'signup.do' >
      <c:set var = "IdCheck" value = "${IdCheck}" />

	  <c:if test = "${IdCheck == false}">
			<script>
				window.alert("이미 존재하는 아이디입니다.");
			</script>
			<% session.removeAttribute("IdCheck"); %>
	  </c:if>
	  <c:if test = "${IdCheck == true}">
			<script>
				window.alert("회원가입이 완료되었습니다.");
			</script>
			<% session.removeAttribute("IdCheck"); %>
	  </c:if>                                                                                        
      <h2>회원가입</h2>
      <div class="textForm">
        <input name="id" type="text" class="id" placeholder="아이디">
      </div>
      <div class="textForm">
        <input name="pw" type="password" class="pw" placeholder="비밀번호" id ="pw" onchange="confirm()">
      </div>
       <div class="textForm">
        <input name="PwConfirm" type="password" class="pw" placeholder="비밀번호 확인" id ="PwConfirm"
        onchange="confirm()">&nbsp;<span id="check"></span>
       	
      </div>
      <div class="textForm">
        <input name="name" type="text" class="name" placeholder="이름">
      </div>
      <div class="textForm">
        <input name="nickname" type="text" class="nickname" placeholder="닉네임">
      </div>
      <div class="textForm">
        <input name="phonenum" type="number" class="cellphoneNo" placeholder="전화번호">
      </div>
      <input class = "button" type ="submit" value ="가입" >
      <input class = "button"  type ="reset" value ="재작성">
      <a style = "display:block; text-decoration: center; margin-top:20px; color:gray;" href="Login.jsp">로그인</a>
    </form>
    </div>
    <script>
    function confirm(){
    	if(document.getElementById('pw').value !='' && document.getElementById('PwConfirm').value!=''){
            if(document.getElementById('pw').value==document.getElementById('PwConfirm').value){
                document.getElementById('check').innerHTML='비밀번호가 일치합니다.'
                document.getElementById('check').style.color='blue';
            }
            else{
                document.getElementById('check').innerHTML='비밀번호가 일치하지 않습니다.';
                document.getElementById('check').style.color='red';
            }
        }
    }
    </script>
</body>
</html>