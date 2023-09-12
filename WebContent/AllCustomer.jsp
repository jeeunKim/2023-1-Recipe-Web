<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix= "c" uri= "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import = "com.sw.dao.*"%>
<%@ page import = "com.sw.dto.*"%>
<%@ page import = "java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="./styles.css?after" rel="stylesheet" />    
</head>
<style>
body{
		margin:0;
		padding: 0;
		
	}
	.icon{
		width:20px;
		margin-top:0px;
	}
	
	.head{
		height: 5vh;
		width: 100%;
		line-height: 0;
		background-color: gray;
	}
	.up{
		background-color:lightgray;
		height: 35vh;
		width: 100%;
		position: relative;
	}
	.down{
		background-color:lightgray;
		height: auto;
		width: 100%;
		position: relative;
		padding:30px;
	}
	.down-mp{
		background-color:#fff2cc;
		height: 100vh;
		width: 100%;
		position: relative;
		padding:30px;
	}
	#menubar{
		width: 100%;
		float: left;
		list-style-type: none;
        justify-content:space-evenly;
        display: flex;
        flex-wrap: nowrap;
        font-weight:bold;
		
	}
	.menu{
		color: black;
		text-decoration: none;
		font-size: 20px;
		margin:15px;
	}
	.footer{
		background-color:black;
		height:200px;
		margin: 0 auto;
	}
	.title{
		margin:0;
		position: absolute;
		top: 20%;
		left: 35%;
		font-size: 120px;
		color: beige;
		z-index: 1;
	}
	.myimg{
		border-radius:50%;
		margin: 0 auto;
		width:200px;
		
	}
	.nickname{
		position:absolute;
		left:340px;
		top:100px;
		font-size:70px;
	}
	.registration{
		position:absolute;
		left:250px;
		top:100px;
		border-radius:50%;
		background-color:white;
		width:300px;
	}
	.registration_write{
		position:absolute;
		left:290px;
		top:400px;
		font-size:40px;
	}
	.management{
		position:absolute;
		left:800px;
		top:100px;
		border-radius:50%;
		background-color:white;
		width:300px;
	}
	.management_write{
		position:absolute;
		left:840px;
		top:400px;
		font-size:40px;
	}
	.favorite{
		position:absolute;
		left:1350px;
		top:100px;
		border-radius:50%;
		background-color:white;
		width:300px;
	}
	.favorite_write{
		position:absolute;
		left:1390px;
		top:400px;
		font-size:40px;
	}
	.delete{
		display:inline-block;
		float:right;
		width:80px;
		height:30px;
		background-color:red;
		border:none;
		border-radius:10%;
		font-size:15px;
		color:black;
		font-weight:bold;
		margin:5px;
		text-decoration:none;
		text-align:center;
		padding-top:5px;
		vertical-align:middle;
	}
	.card{
		display:inline-block;
		margin:0 auto;
		margin-top: 20px;
		margin-bottom:40px;
		margin-left: 100px;
		border: 1px solid lightgray;
		border-radius:20px;
		width:330px;
		background-color:white;
	}
	.card-img-top{
		width: 18rem;
		height:auto;
	}
	.card-body{
		padding:0;
		margin:0;
	}
	.card-title{
		margin-left:7px;
		margin-top:10px;
		margin-bottom:20px;
		font-size:30px;
		color:black;
	}
	.card-text{
		margin-left:7px;
		font-size:20px;
	}
	.btn-primary{
		margin-left:7px;
		vertical-align:bottom;
		width:150px;
		height:30px;
		background-color:#ffd966;
		border:none;
		border-radius:10%;
		font-size:15px;
		color:black;
	}
	.delete{
		display:inline-block;
		float:right;
		width:80px;
		height:30px;
		background-color:red;
		border:none;
		border-radius:10%;
		font-size:15px;
		color:black;
		font-weight:bold;
		margin:5px;
		text-decoration:none;
		text-align:center;
		padding-top:5px;
		vertical-align:middle;
	}
	.fix{
		display:inline-block;
		float:right;
		width:80px;
		height:30px;
		background-color:yellow;
		border:none;
		border-radius:10%;
		font-size:15px;
		color:black;
		font-weight:bold;
		margin:5px;
		text-decoration:none;
		text-align:center;
		padding-top:5px;
		vertical-align:middle;
	}	
</style>
<body>

<header class="head">
		<ul id="menubar">
			<li><a class ="menu" href="main.do">모든 레시피</a></li>
			<li><a class ="menu" href="AllCustomer.do">모든 회원</a></li>
			
			<c:set var = "nickname" value = "${nickname}" />
			
            <c:if test = "${nickname == null}">
				<li><a class ="menu" href="Login.jsp">로그인</a></li>
			</c:if>
			<c:if test = "${nickname != null}">
				<li><span><img class="icon" src="./img/profile2.png"></span>
				<a class ="menu" href="#" style="font-weight:bold;">
				<%= (String) session.getAttribute("nickname")%>
				<span style ="font-size:13px; color:white; text-decoration:under;" 
				onclick="location.href='Logout.jsp'">&nbsp 로그아웃</span></a></li>
			</c:if>
		</ul>
	</header>

	<section class = "up">
	    <c:if test = "${nickname == null}">
			<span class="nickname">로그인 후 이용해주세요</span>
		</c:if>
		<c:if test = "${nickname != null}">
			<img class="myimg" src="./img/profile2.png"><span class="nickname">
			관리자 모드</span>
		</c:if>

	</section>
	
	<section class = 'down'>
	<% ArrayList<CustomerDto> Customerlist = (ArrayList<CustomerDto>)session.getAttribute("Customerlist"); 
		for(int i = 0; i <Customerlist.size(); i++){
	%>
		<div class="card" >
		  <div class="card-body">
		    <h5 class="card-title"><a href ="#" style= "text-decoration:none; color:black;"><%= Customerlist.get(i).getNickname() %></a>
		    <a href ="AllCustomer.do?customer_id=<%= Customerlist.get(i).getId() %>&delete=<%= 1 %>" onClick="alert('회원을 삭제하시겠습니까?\n삭제하면 다시 복구할 수 없습니다.')" class="delete">삭제</a></h5>
		    
		    <p class="card-text">아이디 :<%= Customerlist.get(i).getId() %></p>
		    <p class="card-text">이름 : <%= Customerlist.get(i).getName()%></p>
		    <p class="card-text">전화번호 : 0<%=Customerlist.get(i).getPhone()%></p>
		    <p class="card-text" >비밀번호 : <%= Customerlist.get(i).getPw()%></p>
		    <p class="card-text" >구독자 수  : <%= Customerlist.get(i).getSubscriber()%></p>
				
		  
		  </div>
		  <script>
				var deletesuccess = '<%=(Integer)session.getAttribute("deletesuccess")%>';
				
			    if(deletesuccess==1){ 
			   	 alert("회원이 삭제되었습니다.");
			   	 <% session.removeAttribute("deletesuccess");%>
					 	
			    }
			</script>
	
	</div>
	
	

	<% }%>
	 	
		
	</section>
	
	<footer class="footer">
		<div>s</div>
	</footer>
</body>
</html>