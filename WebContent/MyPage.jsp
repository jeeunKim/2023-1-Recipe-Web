<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix= "c" uri= "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
		background-color: beige;
	}
	.up{
		background-color:#ffd966;
		height: 35vh;
		width: 100%;
		position: relative;
	}
	.down{
		background-color:#fff2cc;
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
		color : black;
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
		position:absolute;
		width:200px;
		top:50px;
		left:120px;	
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
	.card{
		display:flex;
		margin:0 auto;
		margin-top: 20px;
		border: 1px solid lightgray;
		border-radius:20px;
		width:1000px;
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
	}
	.card-text{
		margin-left:7px;
		font-size:15px;
	}
	.btn btn-primary{
		margin-left:7px;
		vertical-align:bottom;
	}
	.aa{
		font-size:15px;
	}
</style>
<body>
<header class="head">
		<ul id="menubar">
			<li><a class ="menu" href="main.do">홈</a></li>
			<li><a class ="menu" href="Follow.do">팔로우</a></li>
			<li><a class ="menu" href="MyPage.do">마이페이지</a></li>
			
			<c:set var = "nickname" value = "${nickname}" />
			
            <c:if test = "${nickname == null}">
				<li><a class ="menu" href="Login.jsp">로그인</a></li>
			</c:if>
			<c:if test = "${nickname != null}">
				<li><span><img class="icon" src="./img/profile2.png"></span>
				<a class ="menu" href="#" style="font-weight:bold;">
				<%= (String) session.getAttribute("nickname")%>
				<span style ="font-size:13px; color:gray; text-decoration:under;" 
				onclick="location.href='Logout.jsp'">&nbsp 로그아웃</span></a></li>
			</c:if>
		</ul>
	</header>

	<section class = "up">
	    <c:if test = "${nickname == null}">
			<img class="myimg" src="./img/profile2.png"><span class="nickname">로그인 후 이용해주세요</span>
		</c:if>
		<c:if test = "${nickname != null}">
			<img class="myimg" src="./img/profile2.png"><span class="nickname"><%= (String) session.getAttribute("nickname")%>&nbsp님&nbsp
			<span style="font-size:20px; color:black;">구독자 : <%= String.valueOf(session.getAttribute("subscriber"))%>명</span>
			<a class= "aa" href ="ProfileChange.do" style="color:gray">회원정보 변경</a></span>
			
			
		</c:if>

	</section>
	<section class = 'down-mp'>
		<a href = "Registration.jsp?id=${ID}">
			<img class = "registration" src="./img/write.png">
		</a>
		<a href = "Registration.jsp?id=${ID}" class ="registration_write" style = "text-decoration:none; color:black;"> 레시피 등록</a>

		<a href = "MyRecipe.do?id=${ID}">
			<img class = "management" src="./img/manage.png">
		</a>
		<a href = "MyRecipe.do?id=${ID}" class ="management_write" style = "text-decoration:none; color:black;"> 레시피 관리</a>
		
		<a href = "Wish.do?id=${ID}">
			<img class = "favorite" src="./img/favorite.png">
		</a>
		<a href = "Wish.do?id=${ID}" class ="favorite_write" style = "text-decoration:none; color:black;"> 찜한 레시피</a>
		
		
	</section>

	

	<footer class="footer">
		<div>s</div>
	</footer>
</body>
</html>