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
	.up{
		background-color:#ffd966;
		height: 40vh;
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
		left:340px;
		top:100px;
		font-size:70px;
	}
	.stitle{
		position:absolute;
		left:870px;
		top:10px;
		font-size:80px;
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
	.search-box {
		position:relative;
		dispaly:inline-block;
		top: 140px;
		left: 750px;
			
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
		color: gray;
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
		<c:if test = "${nickname != null}">
			<span class="stitle">검색 결과</span>
		</c:if>
		<div class="search-box">
			<form action="Search.do" method="post">
			  <input class="search-txt" type="text" name = "ingredient" placeholder="여러 재료를 빈 칸을 두어 검색할 수 있습니다!">
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

	</section>
	
	<section class = 'down'>
	
		
	<% int many=0; %>
	<% int count = Integer.parseInt( String.valueOf(session.getAttribute("count"))); %>
	<% String[] recipe_name =  (String[])session.getAttribute("recipe_name"); %>
	<% int[] recipe_id =  (int[])session.getAttribute("recipe_id"); %>
	<% String[] img =  (String[])session.getAttribute("img"); %>
	<% String[] category =  (String[])session.getAttribute("category"); %>
	<% String[] recipe_content =  (String[])session.getAttribute("recipe_content"); %>
	<% int[] level =  (int[])session.getAttribute("level"); %>
	<% int[] cooking_time = (int[])session.getAttribute("cooking_time"); %>
	<% int[] heart = (int[])session.getAttribute("heart"); %>
	
	<script>
		var searchsuccess = '<%=(Integer)session.getAttribute("searchsuccess")%>';
		
	    if(searchsuccess==0){ 
	   	 alert("검색된 결과가 없습니다.");
	   	 <% session.removeAttribute("searchsuccess");%>
	   	 history.back();	 	
	    }
	</script>
	
	
	<c:forEach var="cnt" begin="1" end="${count}" step="1" varStatus="status">
		<% String star=""; %>
			<c:set var="st" value="<%= level[many] %>"/>
			<c:forEach var="stt" begin="1" end="${st }" step="1" varStatus="status">
				<% star += "★"; %>
		    </c:forEach>		
		<div class="card" >
		  <a href ="View.do?recipe_id=<%= recipe_id[many]%>"><img src=<%= img[many]%> class="card-img-top" alt="..."></a>
		  <div class="card-body">
		    <h5 class="card-title"><a href ="ViewMine.do?recipe_id=<%= recipe_id[many]%>" 
		    style= "text-decoration:none; color:black;"><%= recipe_name[many]%></a> 
		    <a href ="jjim2.do?recipe_id=<%= recipe_id[many] %>" class="jjim">찜하기</a>
		    <a href ="Gosub.do?recipe_id=<%= recipe_id[many] %>" class="jjim">구독하기</a></h5>
		    
		    <p class="card-text">카테고리 : <%= category[many]%></p>
		    <p class="card-text">난이도 : <%= star%></p>
		    <p class="card-text" >조리시간 : <%= cooking_time[many]%></p>
		    <p class="card-text" >👍🏻 : <%= heart[many]%></p>
		    <p class="card-text" style = "width: 700px; white-space: nowrap;overflow: hidden;
		    text-overflow:ellipsis; "><%= recipe_content[many]%></p>

		  
		  </div>
		</div>
		<% many++; %>
	</c:forEach>
	 	
		
	</section>
	
	<footer class="footer">
		<div>s</div>
	</footer>
</body>
</html>