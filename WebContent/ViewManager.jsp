<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.sql.Timestamp" %>
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
		background-color: gray;
	}
	
	.sec{
		background-color:lightgray;
		height: auto;
		width: 100%;
		position: relative;
		padding:30px;
		padding-bottom: 50px;
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
		padding:0;
		margin:0;
	}
	.card-title{
		margin-left:50px;
		margin-top:10px;
		margin-bottom:20px;
		font-size:50px;
		font-weight:bolder;
		color:black;
		width:500px;
	}
	.card-text{
		margin-left:20px;
		font-size:25px;
		font-weight:bold;
	}
	.card-text-content{
		margin-left:20px;
		font-size:25px;
		display:block;
		clear:both;
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

	
	
	<section class = 'sec'>
	
	
		<% String star=""; %>
		<c:forEach var="cnt" begin="1" end="${level }" step="1" varStatus="status">
			<% star += "★"; %>
		</c:forEach>
		
		<% int recipe_id = (Integer)session.getAttribute("recipe_id"); %>
		<div class="card" >
		  <a href ="#"><img src= <%= (String)session.getAttribute("img") %> class="card-img-top" alt="..."></a>
		  <div class="card-body">
		    <h5 class="card-title"><%= (String)session.getAttribute("recipe_name") %></h5>
		    

		    </br>
		    <p class="card-text">카테고리 : <%= (String)session.getAttribute("category") %></p>
		    <p class="card-text">난이도 : <%= star %></p>
		    <p class="card-text" >조리시간 : <%= (Integer)session.getAttribute("cooking_time") %>분</p>
		    <p class="card-text" >👍🏻 : <%= (Integer)session.getAttribute("heart") %></p>
			
		  </div>
		</div>
		<div class="card" >
		  <div class="card-body">
		    <p class="card-text-content"><span style = "font-size: 40px; font-weight:bold;">조리법
		    </span></br><%= (String)session.getAttribute("recipe_content") %></p>

		  </div>
		</div>
		
		<h1 style = "font-size:40px; margin-left:500px;">댓글</h1>
			    <c:set var = "co_count" value = "${co_count}" />
			
	            <c:if test = "${co_count >=2}">

			    <% int many=0; %>
				<% int count = (Integer)session.getAttribute("co_count2");%>
				<% int[] comment_id =  (int[])session.getAttribute("comment_id"); %>
				<% String[] comment =  (String[])session.getAttribute("comment"); %>
				<% String[] owner_id =  (String[])session.getAttribute("owner_id"); %>
				<% String[] cnickname =  (String[])session.getAttribute("cnickname"); %>
				<% Timestamp[] date =  (Timestamp[])session.getAttribute("date"); %>
				
				<c:forEach var="cnt" begin="1" end="${ co_count}" step="1" varStatus="status">
					<div style = "width:1000px; margin-left:500px; margin-bottom:30px; heigth:auto; float:left;
					background-color:white; border:none; border-radius:15px;">
			 			<p style="color:blue; font-size:25px; font-weight:bold; margin-right:15px;"><%= cnickname[many] %>
			 			<a href ="ViewManager.do?comment_id=<%= comment_id[many] %>&delete=<%= 1 %>&recipe_id=<%= recipe_id %>" onClick="alert('정말로 삭제하시겠습니까?\n삭제하면 다시 복구할 수 없습니다.')" class="delete">삭제</a></p>
			 			<span><%= date[many] %></span>
			 			<p style="font-size:20px;"><%= comment[many] %></p>
					</div>
					<% many++;%>
				</c:forEach>
				
				</c:if>
				<c:if test = "${co_count ==1}">

			    <% int many=0; %>
				<% int count = Integer.parseInt( String.valueOf(session.getAttribute("co_count"))); %>
				<% String comment =  (String)session.getAttribute("comment"); %>
				<% int comment_id =  (Integer)session.getAttribute("comment_id"); %>
				<% String owner_id =  (String)session.getAttribute("owner_id"); %>
				<% String cnickname =  (String)session.getAttribute("cnickname"); %>
				<% Timestamp date =  (Timestamp)session.getAttribute("date"); %>
				
				
					<div style = "width:1000px; margin-left:500px; margin-bottom:30px; heigth:auto; float:left;
					background-color:white; border:none; border-radius:15px;">
			 			<p style="color:blue; font-size:25px; font-weight:bold; margin-right:15px;"><%= cnickname %>
			 			<a href ="ViewManager.do?comment_id=<%= comment_id %>&delete=<%= 1 %>" onClick="alert('정말로 삭제하시겠습니까?\n삭제하면 다시 복구할 수 없습니다.')" class="delete">삭제</a></p>
			 			<span><%= date %></span>
			 			<p style="font-size:20px;"><%= comment %></p>
					</div>
					<% many++;%>
			
				
				</c:if>
			 	<form action = "comment.do?recipe_id=<%= recipe_id %>&mine=<%= "mine" %>" method="post">
			 		<div style = "width:1000px; margin-left:500px; margin-top:100px;">
			 			
						<textarea rows="8" cols="91" name="comment"placeholder="관리자 모드에서는 댓글을 입력할 수 없습니다." style = "font-size:20px;
						 border: 1px solid lightgray; border-radius:15px;"></textarea></p>
						
						
						
					</div>
				</form>
			<script>
				var deletesuccess = '<%=(Integer)session.getAttribute("deletesuccess")%>';
				
			    if(deletesuccess==1){ 
			   	 alert("댓글이 삭제되었습니다.");
			   	 <% session.removeAttribute("deletesuccess");%>
					 	
			    }
			</script>
	 	
		    
		
	</section>
	
	<footer class="footer">
		<div>s</div>
	</footer>
</body>
</html>