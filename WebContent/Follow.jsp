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
		position:absolute;
		width:200px;
		top:50px;
		left:120px;	
	}
	.nickname{
		position:absolute;
		font-size:70px;
		top:100px;
		left:600px;
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
	.flex-box{
		margin:0 auto;
		display:flex;
		width:100%;
		height:600px;
		align-items:center;
		overflow:auto;
	}
	.card{
		margin-top: 20px;
		margin-bottom:40px;
		margin-right:20px;
		margin-left:20px;
		border: 1px solid lightgray;
		border-radius:20px;
		width:25rem;
		height:550px;
		background-color:white;
	}
	.card-title{
		font-size:30px;
		margin:0;
		margin-bottom:20px;
	}
	.card-text{
		font-size:20px;
	}
	.card-img-top{
		width:25rem;
		height:300px;
	}
	.jjim{
		float:right;
		width:80px;
		height:30px;
		background-color:lightgray;
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
			<span class="nickname">로그인 후 이용해주세요</span>
		</c:if>
		<c:if test = "${nickname != null}"><span class="nickname">내가 구독한 유저들의 레시피</span>
		</c:if>

	</section>
	
	<section class = 'down'>
	<%@page import ="java.util.ArrayList" %>
	<% int many=0; int how=0; %>
	<% String[] subs =  (String[])session.getAttribute("subs"); %>
	<% int[] count =  (int[])session.getAttribute("count"); %>
	<% int howmany =  Integer.parseInt(String.valueOf(session.getAttribute("howmany"))); %>
	
	
	<% ArrayList<String>[] recipe_name =  (ArrayList<String>[])session.getAttribute("recipe_name"); %>
	<% ArrayList<Integer>[] recipe_id = (ArrayList<Integer>[])session.getAttribute("recipe_id"); %>
	<% ArrayList<String>[] img =  (ArrayList<String>[])session.getAttribute("img"); %>
	<% ArrayList<String>[] category =  (ArrayList<String>[])session.getAttribute("category"); %>
	<% ArrayList<String>[] recipe_content = (ArrayList<String>[])session.getAttribute("recipe_content"); %>
	<% ArrayList<Integer>[] level = (ArrayList<Integer>[])session.getAttribute("level"); %>
	<% ArrayList<Integer>[] cooking_time = (ArrayList<Integer>[])session.getAttribute("cooking_time"); %>
	<% ArrayList<Integer>[] heart = (ArrayList<Integer>[])session.getAttribute("heart"); %>
	
	<c:forEach var="cont" begin="1" end="${howmany}" step="1" varStatus="status">
		<h1 style="font-size:50px;">내가 구독한 <span style="color:blue;"><%= subs[how]%></span>  님의 레시피</h1>
		
		<% many=0; int ct =  count[how];%>
		<c:set var="ct" value="<%=ct %>"/>
		
		<div class="flex-box">
		<c:forEach var="cnt" begin="1" end="${ct}" step="1" varStatus="status">
			<% String star=""; %>
			<c:set var="st" value="<%= level[how].get(many) %>"/>
			<c:forEach var="stt" begin="1" end="${st }" step="1" varStatus="status">
				<% star += "★"; %>
			</c:forEach>
			<div class="card" >
			  <a href ="View.do?recipe_id=<%= recipe_id[how].get(many) %>"><img src=<%= img[how].get(many)%> class="card-img-top" alt="..."></a>
			  <div class="card-body">
			    <h5 class="card-title"><a href ="View.do?recipe_id=<%= recipe_id[how].get(many)%>" 
			    style= "text-decoration:none; color:black;"><%= recipe_name[how].get(many)%></a>
			    <a href ="jjim.do?recipe_id=<%= recipe_id[how].get(many) %>" class="jjim">찜하기</a></h5> 
			    <script>
				    var suc = '<%=(Integer)session.getAttribute("success")%>';
	
			         if(suc==-1){ 
			        	 alert("이미 찜한 레시피입니다.");
			        	 <% session.removeAttribute("success");%>
						 
			         }
			         if(suc==1){ 
			        	 alert("찜 목록에 레시피를 추가하였습니다.");
			        	 <% session.removeAttribute("success");%>
			         }
			    </script>
			    
			    
			    <p class="card-text">카테고리 : <%= category[how].get(many)%></p>
			    <p class="card-text">난이도 : <%= star%></p>
			    <p class="card-text" >조리시간 : <%= cooking_time[how].get(many)%></p>
			    <p class="card-text" >👍🏻 : <%= heart[how].get(many)%></p>

			  </div>
			</div>
		 	
			<% many++; %>
		</c:forEach>
		</div>
		<% how++; %>
	</c:forEach>
	</section>
	
	<footer class="footer">
		<div><p style = "color:white;">풋터</p></div>
	</footer>
</body>
</html>