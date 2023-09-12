<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix= "c" uri= "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="./MainStyles.css" rel="stylesheet" />    

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
		width: 2030px;
		line-height: 0;
		background-color: beige;
	}
	.up{
		background-image: url( "./img/main_img.png" );
		background-repeat: no-repeat;
		background-size: 100% 100%;
		background-color:#ffd966;
		height: 830px;
		width: 2030px;
		position: relative;
		margin-bottom:0;
		padding:0;
	}
	.down{
		margin-top: 0;
		margin-bottom: 0;
		padding:0;
		background-color:beige;
		width: 2030px;
		height: auto;
		position: relative;
		
	}
	.footer{
		background-color:black;
		height:200px;
		margin: 0 auto;
	}
	.rank{
		margin: 0 auto;
		width:1200px;
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
	.img1{
		position: absolute;
		width:100%;
	}
	.img2{
		position: absolute;
		width:480px;
		left: 50%;
		top: 30%;
	}
	.title{
		width:1100px;
		position:relative;
		margin:0; padding:0;
		top: 20px;
		left: 130px;
		font-size: 100px;
		color: beige;
		z-index: 1;
	}
	.subtitle{
		width:1100px;
		position:relative;
		margin:0;
		left: 150px;
		top: 20px;
		font-size: 60px;
		color: beige;
		z-index: 1;
	}
	.search-box {
		position:relative;
		dispaly:inline-block;
		top: 50px;
		left: 1250px;
			
	}
	.search-txt{
		width:500px;
		height:50px;
		border:none;
		border-radius:5px;
		font-size: 20px;
		color:black;
	}
	.search-btn{
		width:60px;
		height:50px;
		background-color: beige;
		border: white;
		border-radius:5px;
		font-size: 20px;
	}
	summary{
		color: beige;
		font-size: 20px;
		margin-top:5px;
		transition-delay: .5s;
		font-weigth:bold;
	}
	details{
		position:absolute;
		transition-delay: .5s;
		
	}
	table{
		width:500px;
		height:150px;
		border-collapse: collapse;
		background-color:white;
		border: 1px solid black;
	}
	th {
		border: 1px solid #ffd966;
		
	}
	td {
		border: 1px solid #ffd966;
	}
	.ranktitle{
		text-align:center; 
		font-size:80px; 
		color:black; 
		margin:0; 
		padding-top:50px;
		font-style: italic;
		
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
		float:left;
		display:flex;
		margin-top: 60px;
		margin-left: 100px;
		margin-bottom:0px;
		border: 1px solid lightgray;
		border-radius:20px;
		width:800px;
		background-color:white;
	}
	.card2{
		display:flex;
		margin-top: 60px;
		margin-left: 1120px;
		margin-bottom:0px;
		border: 1px solid lightgray;
		border-radius:20px;
		width:800px;
		background-color:white;
	}
	.card-img-top{
		width: 20rem;
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
	.nick{
		font-size:20px;
		color:black;
		font-weight:bold;
		color:blue;
	}
	.nickp{
		margin-left:7px;
		font-size:25px;
		color:black;
		font-weight:bold;
	}
</style>
</head>
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
	<div class="search-box">
			<form action="Search.do" method="post">
			  <input class="search-txt" type="text" name = "ingredient" placeholder="요리명! 또는 여러 재료를 빈 칸을 두어 검색!">
			  <button class="search-btn" type="submit">검색</button>
			  
				<details>
			  		<summary>상세검색</summary>
					<table>  
						<tr>
							<th><b>난이도</b> </th>
							<td>
									<input type="radio" name ="level" value = 0 checked >모두
									<input type="radio" name ="level" value = 1 >★
									<input type="radio" name ="level" value = 2 >★★
									<input type="radio" name ="level" value = 3 >★★★
									<input type="radio" name ="level" value = 4 >★★★★
									<input type="radio" name ="level" value = 5 >★★★★★
							</td>
						</tr>
						<tr>
							<th><b>조리시간</b> </th>
							<td>
									<input type="radio" name ="cooking_time" value = 0 checked>모두
									<input type="radio" name ="cooking_time" value = 30 >~30분
									<input type="radio" name ="cooking_time" value = 60 >~1시간
									<input type="radio" name ="cooking_time" value = 120 >~2시간
									<input type="radio" name ="cooking_time" value = 120 >2시간~
							</td>
						</tr>
						<tr>
							<th><b>카테고리</b> </th>
							<td>
									<input type="radio" name ="category" value = "all" checked>모두
									<input type="radio" name ="category" value = "한식" >한식
									<input type="radio" name ="category" value = "일식" >일식
									<input type="radio" name ="category" value = "양식" >양식
							</td>
						</tr>
				
					</table>
				</details>
					
					
			</form>
		</div>
		<h1 class="title">Share Your Recipe</h1></br>
		<h1 class="subtitle">매번 버리는 아까운 식재료, </br>어떤 음식으로 만들 수 있을까?</h1>
		
		
	</section>

	<section class = "down">
		
	<c:if test = "${nickname != null}">
		<div class = "rank">
			<h1 class = "ranktitle">인기 레시피</h1>
		</div>
	<% int many=1; %>
	<% int count = Integer.parseInt( String.valueOf(session.getAttribute("count"))); %>
	<% String[] recipe_name =  (String[])session.getAttribute("recipe_name"); %>
	<% int[] recipe_id =  (int[])session.getAttribute("recipe_id"); %>
	<% String[] img =  (String[])session.getAttribute("img"); %>
	<% String[] category =  (String[])session.getAttribute("category"); %>
	<% String[] recipe_content =  (String[])session.getAttribute("recipe_content"); %>
	<% String[] owner_nickname =  (String[])session.getAttribute("owner_nickname"); %>
	<% String[] owner_id =  (String[])session.getAttribute("owner_id"); %>
	<% int[] level =  (int[])session.getAttribute("level"); %>
	<% int[] cooking_time = (int[])session.getAttribute("cooking_time"); %>
	<% int[] heart = (int[])session.getAttribute("heart"); %>
	
	

	<c:forEach var="cnt" begin="1" end="${count}" step="1" varStatus="status">
		<% String star=""; %>
			<c:set var="st" value="<%= level[many-1] %>"/>
			<c:forEach var="stt" begin="1" end="${st }" step="1" varStatus="status">
				<% star += "★"; %>
		    </c:forEach>	
		<div class="card" >
		  <a href ="View.do?recipe_id=<%= recipe_id[many-1]%>"><img src=<%= img[many-1]%> class="card-img-top" alt="..."></a>
		  <div class="card-body">
		    <h5 class="card-title"><a href ="View.do?recipe_id=<%= recipe_id[many-1]%>" 
		    style= "text-decoration:none; color:black;"><%= recipe_name[many-1]%></a>
		    <a href ="jjim2.do?recipe_id=<%= recipe_id[many-1] %>" class="jjim">찜하기</a>
		    <a href ="Gosub.do?recipe_id=<%= recipe_id[many-1] %>" class="jjim">구독하기</a></h5>
		    <span class="nickp">작성자 : </span><a href ="Viewprofile.do?owner_id=<%= owner_id[many-1] %>" class="nick"><%= owner_nickname[many-1] %></a></h5>
		    
		    <p class="card-text">카테고리 : <%= category[many-1]%></p>
		    <p class="card-text">난이도 : <%= star%></p>
		    <p class="card-text" >조리시간 : <%= cooking_time[many-1]%></p>
		    <p class="card-text" >👍🏻 : <%= heart[many-1]%></p>
		    <p class="card-text" style = "width: 460px; white-space: nowrap;overflow: hidden;
		    text-overflow:ellipsis; "><%= recipe_content[many-1]%></p>
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
			    <script>
				    var success = '<%=(Integer)session.getAttribute("sub_success")%>';
	
			         if(success==-1){ 
			        	 alert("이미 구독중입니다");
			        	 <% session.removeAttribute("sub_success");%>
			         }
			         if(success==1){ 
			        	 alert("구독완료 !");
			        	 <% session.removeAttribute("sub_success");%>
			         }
			         if(success==0){ 
			        	 alert("본인은 구독할 수 없습니다.");
			        	 <% session.removeAttribute("sub_success");%>
			         }
			    </script>
		  
		  </div>
		</div>
		<% star=""; %>
			<c:set var="st" value="<%= level[many] %>"/>
			<c:forEach var="stt" begin="1" end="${st }" step="1" varStatus="status">
				<% star += "★"; %>
		    </c:forEach>	
		<div class="card2" >
		  <a href ="View.do?recipe_id=<%= recipe_id[many]%>"><img src=<%= img[many]%> class="card-img-top" alt="..."></a>
		  <div class="card-body">
		    <h5 class="card-title"><a href ="View.do?recipe_id=<%= recipe_id[many]%>" 
		    style= "text-decoration:none; color:black;"><%= recipe_name[many]%></a>
		    <a href ="jjim2.do?recipe_id=<%= recipe_id[many] %>" class="jjim">찜하기</a>
		    <a href ="Gosub.do?recipe_id=<%= recipe_id[many] %>" class="jjim">구독하기</a></h5>
		    <span class="nickp">작성자 : </span><a href ="Viewprofile.do?owner_id=<%= owner_id[many] %>" class="nick"><%= owner_nickname[many] %></a></h5>
		    
		    <p class="card-text">카테고리 : <%= category[many]%></p>
		    <p class="card-text">난이도 : <%= star%></p>
		    <p class="card-text" >조리시간 : <%= cooking_time[many]%></p>
		    <p class="card-text" >👍🏻 : <%= heart[many]%></p>
		    <p class="card-text" style = "width: 460px; white-space: nowrap;overflow: hidden;
		    text-overflow:ellipsis; "><%= recipe_content[many]%></p>
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
			    <script>
				    var success = '<%=(Integer)session.getAttribute("sub_success")%>';
	
			         if(success==-1){ 
			        	 alert("이미 구독중입니다");
			        	 <% session.removeAttribute("sub_success");%>
			         }
			         if(success==1){ 
			        	 alert("구독완료 !");
			        	 <% session.removeAttribute("sub_success");%>
			         }
			         if(success==0){ 
			        	 alert("본인은 구독할 수 없습니다.");
			        	 <% session.removeAttribute("sub_success");%>
			         }
			    </script>
		  
		  </div>
		</div>
		<% many+=2; %>
	</c:forEach>
	<div style ="background-color:beige; height: 100px;">
	</div>
	</c:if>
	</section>
	<footer class="footer">
		<div>s</div>
	</footer>

</body>
</html>