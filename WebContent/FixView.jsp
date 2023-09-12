<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix= "c" uri= "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
		background-color: beige;
	}
	
	.sec{
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
		width:500px;
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
		margin-bottom:40px;
		border: 1px solid lightgray;
		border-radius:20px;
		width:1000px;
		height:auto;
		background-color:white;
	}
	.card-img-top{
		width: 25rem;
		height:auto;
		float:right;
	}
	.card-body{
		padding-bottom:30px;
		margin:0;
	}
	.card-title{
		margin-left:20px;
		margin-top:10px;
		margin-bottom:20px;
		font-size:30px;
		font-weight:bolder;
		color:black;
		width:450px;
	}
	.title2{
		margin-left:20px;
		margin-top:10px;
		margin-bottom:20px;
		font-size:30px;
		font-weight:bolder;
		color:black;
		width:450px;
		border:1px solid lightgray;
	}
	.card-text{
		margin-left:20px;
		font-size:25px;
		font-weight:bold;
		
	}
	.card-text-content{
		margin-left:20px;
		font-size:30px;
		display:block;
		clear:both;
		width:950px;
		height:700px;
		border:1px solid lightgray;
		border-radius:10px;
	}
	.text{
		font-size:25px;
		width:200px;
		border:1px solid lightgray;
	}
	.text2{
		font-size:25px;
		width:200px;
		border:none;
	}
	.btn{
	 	height:500px; 
	 	margin : 0 auto;
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

	<section class = 'sec'>
		<h1 style ="font-size:60px; position:absolute; left:900px; top:-20px;">레시피 수정</h1>
		<% int recipe_id = (Integer)session.getAttribute("recipe_id"); %>
		<form action ="Fix.do?recipe_id=<%= recipe_id %>" method = post>
			<div class="card" style="margin-top:120px;" >
			  <a href ="#"> <img src= <%= (String)session.getAttribute("img") %> class="card-img-top" alt="..."></a>
			  <div class="card-body">
			    <span class="card-title"> 제목 : <input type = "text" class="title2" name="recipe_name" value= <%= (String)session.getAttribute("recipe_name") %>></span> </h5>
			    
				<p></p>
			    </br>
			    <!--  -->
			    <span class="card-text">카테고리 : </span><input type = "text" class="text" name="category" value= <%= (String)session.getAttribute("category") %>></P>
			    <span class="card-text">난이도 : </span><input type = "number"  class="text" name="level" value= <%= (Integer)session.getAttribute("level") %>></P>
			    <span class="card-text">조리시간 : </span><input type = "number"  class="text" name="cooking_time" value= <%= (Integer)session.getAttribute("cooking_time") %>></P>
				
			  </div>
			</div>
			
			<div class="card" >
			  <div class="card-body" style ="text-align:center;">
			    <span style = "font-size: 40px; font-weight:bold;">조리법</span></br> 
					<textarea name="recipe_content"  class="card-text-content">
					<%= (String)session.getAttribute("recipe_content") %></textarea>
					
					<input type ="submit" style = "width:100px; height:50px; font-size:20px; 
			    	background-color:yellow; border:none; margin-top: 20px;" onClick="alert('이대로 수정하시겠습니까?')"value = "수정 완료"/>
			    	
			    	<a href = "#" onClick="history.back()" 
			    	style = "text-decoration:none; color:black; padding-right:5px;padding-left:5px;
			    	padding-top:11px; padding-bottom:11px; font-size:20px;
			    	background-color:lightgray; margin-top: 20px; margin-left: 10px;">수정 취소</a>
			  </div>
			  
			</div>
		    
		   
		</form>
	</section>
	
	<footer class="footer">
		<div>s</div>
	</footer>
</body>
</html>