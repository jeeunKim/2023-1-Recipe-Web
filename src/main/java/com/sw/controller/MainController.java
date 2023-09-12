package com.sw.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.io.File;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.*;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;
import javax.servlet.ServletContext;
import com.sw.command.*;
import com.sw.dto.*;
import java.util.Random;
import java.sql.Timestamp;
/**
 * Servlet implementation class MainController
 */
@WebServlet("*.do")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
	}
	protected void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("actionDo");
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String command = uri.substring(conPath.length()); //~~.do
		
		String viewPage = null;
		Boolean IdCheck;
	    int pwCheck=0;
	    String id;
	    String pw;
	    String nickname;
	    String name;
	    int phone; 
	    int subscriber;
		
	    CustomerDto cdto = new CustomerDto(); //회원정보 dto
	    RecipeDto rdto = new RecipeDto();  //상품정보 dto
	    Command newcommand = null;
	    
		
		HttpSession session = request.getSession();
		System.out.println("actionDo - "+ command);
		
		//-----------------------------------------------------메인 홈페이지
		if(command.equals("/main.do")) {   	
			newcommand = new Command();
			ArrayList<RecipeDto> Rdtolist = newcommand.PopularRecipe();
			
			int count = Rdtolist.size();
			int[] recipe_id = new int[count];
			String[] recipe_name = new String[count];
			String[] recipe_content = new String[count];
			String[] img= new String[count];
			int[] cooking_time= new int[count];
			int[] level= new int[count];
			String[] category = new String[count];
			String[] owner_id = new String[count];
			String[] owner_nickname = new String[count];
			int[] heart = new int[count];
			
			for(int i = 0; i <40; i++) {
				recipe_name[i] = Rdtolist.get(i).getRecipe_name();
				recipe_content[i] = Rdtolist.get(i).getRecipe_content();
				img[i] = Rdtolist.get(i).getImg();
				cooking_time[i] = Rdtolist.get(i).getCooking_time();
				level[i] = Rdtolist.get(i).getLevel();
				category[i] = Rdtolist.get(i).getCategory();
				recipe_id[i] = Rdtolist.get(i).getRecipe_id();
				heart[i] = Rdtolist.get(i).getHeart();
				owner_id[i] = Rdtolist.get(i).getOwner_id();
				owner_nickname[i] = newcommand.getNickname(owner_id[i]);
				
			}
			session.setAttribute("recipe_name", recipe_name);
			session.setAttribute("recipe_id", recipe_id);
			session.setAttribute("recipe_content", recipe_content);
			session.setAttribute("img", img);
			session.setAttribute("cooking_time", cooking_time);
			session.setAttribute("level", level);
			session.setAttribute("category", category);
			session.setAttribute("count", 20);
			session.setAttribute("heart", heart);
			session.setAttribute("owner_nickname", owner_nickname);
			session.setAttribute("owner_id", owner_id);
			
			viewPage = "Main.jsp";
			if(((String)session.getAttribute("ID")).equals("manager")) {
				viewPage = "ManagerMain.do";
			}
		}
		else if(command.equals("/ManagerMain.do")) { 
			newcommand = new Command();
			String Drecipe_id = (String)request.getParameter("recipe_id");
			int dre;
			String delete = (String)request.getParameter("delete");
			newcommand = new Command();
			int deletesuccess=-1;
			if(delete != null) {
				if(delete.equals("1")) {
					dre = Integer.parseInt(Drecipe_id);
					newcommand.Delete(dre);
					deletesuccess=1;
					session.setAttribute("deletesuccess", deletesuccess);
				}
			}
			
			ArrayList<RecipeDto> Rdtolist = newcommand.PopularRecipe();
			
			int count = Rdtolist.size();
			int[] recipe_id = new int[count];
			String[] recipe_name = new String[count];
			String[] recipe_content = new String[count];
			String[] img= new String[count];
			int[] cooking_time= new int[count];
			int[] level= new int[count];
			String[] category = new String[count];
			String[] owner_id = new String[count];
			String[] owner_nickname = new String[count];
			int[] heart = new int[count];
			
			for(int i = 0; i <count; i++) {
				recipe_name[i] = Rdtolist.get(i).getRecipe_name();
				recipe_content[i] = Rdtolist.get(i).getRecipe_content();
				img[i] = Rdtolist.get(i).getImg();
				cooking_time[i] = Rdtolist.get(i).getCooking_time();
				level[i] = Rdtolist.get(i).getLevel();
				category[i] = Rdtolist.get(i).getCategory();
				recipe_id[i] = Rdtolist.get(i).getRecipe_id();
				heart[i] = Rdtolist.get(i).getHeart();
				owner_id[i] = Rdtolist.get(i).getOwner_id();
				owner_nickname[i] = newcommand.getNickname(owner_id[i]);
				
			}
			session.setAttribute("recipe_name", recipe_name);
			session.setAttribute("recipe_id", recipe_id);
			session.setAttribute("recipe_content", recipe_content);
			session.setAttribute("img", img);
			session.setAttribute("cooking_time", cooking_time);
			session.setAttribute("level", level);
			session.setAttribute("category", category);
			session.setAttribute("count", count);
			session.setAttribute("heart", heart);
			session.setAttribute("owner_nickname", owner_nickname);
			session.setAttribute("owner_id", owner_id);

			viewPage = "ManagerMain.jsp";
		}else if(command.equals("/AllCustomer.do")) {  
	         newcommand=new Command();
	         ArrayList<CustomerDto> Customerlist= new ArrayList<CustomerDto>();
	         String customer_id = (String)request.getParameter("customer_id");
	         String delete = (String)request.getParameter("delete");
	         if(delete != null) {
					if(delete.equals("1")) {
						newcommand.DeleteCustomer(customer_id);
						int deletesuccess=1;
						session.setAttribute("deletesuccess", deletesuccess);
					}
				}
	         Customerlist=newcommand.getCustomer();
	         
		     session.setAttribute("Customerlist", Customerlist);
	         
	     	
	         viewPage="AllCustomer.jsp";
	         

		}
		else if(command.equals("/ViewManager.do")) {  
			session.removeAttribute("co_count");
			id = String.valueOf(session.getAttribute("ID"));
			int recipe_id = Integer.parseInt((String)request.getParameter("recipe_id"));
			newcommand = new Command();
			if(request.getParameter("check")!= null) {
				newcommand.pressheart(recipe_id);
			}
			
			String dcomment_id = (String)request.getParameter("comment_id");
	        String delete = (String)request.getParameter("delete");
	         if(delete != null) {
					if(delete.equals("1")) {
						newcommand.DeleteComment(dcomment_id);
						int deletesuccess=1;
						session.setAttribute("deletesuccess", deletesuccess);
					}
				}
	         
			newcommand.getContent(rdto, recipe_id);
			String recipe_name= rdto.getRecipe_name();
			String recipe_content=rdto.getRecipe_content();
			String img=rdto.getImg();
			int cooking_time=rdto.getCooking_time();
			int level=rdto.getLevel();
			String owner_id=rdto.getOwner_id();
			String category=rdto.getCategory();
			int heart=rdto.getHeart();
			
			session.setAttribute("recipe_name", recipe_name);
			session.setAttribute("recipe_id", recipe_id);
			session.setAttribute("img", img);
			session.setAttribute("cooking_time", cooking_time);
			session.setAttribute("level", level);
			session.setAttribute("owner_id", owner_id);
			session.setAttribute("category", category);
			session.setAttribute("heart", heart);
			
			
	        StringBuilder temp = new StringBuilder();
	        for (int i = 0; i < recipe_content.length(); i++) {
	            char currentChar = recipe_content.charAt(i);
	            if (Character.isDigit(currentChar)) {
	            	if(recipe_content.charAt(i+1) == '.') {
	            		temp.append("<br><br>").append(currentChar);
	            	}else {
	            		temp.append(currentChar);
	            	}
	            } else {
	                temp.append(currentChar);
	            }
	        }
	        String result = temp.toString();
	        session.setAttribute("recipe_content", result);
	        
	        
	        ArrayList<CommentDto> CdtoList = new ArrayList<CommentDto>();
	        CdtoList = newcommand.getComment(recipe_id);
			
			int count = CdtoList.size();
			if(count >=2) {
				int[] crecipe_id = new int[count];
				String[] ccomment = new String[count];
				String[] owner_id2 = new String[count];
				Timestamp[] date= new Timestamp[count];
				int[] comment_id= new int[count];
				String[] cnickname = new String[count];
				
				for(int i = 0; i <count; i++) {
					crecipe_id[i] = CdtoList.get(i).getRecipe_id();
					ccomment[i] = CdtoList.get(i).getComment_content();
					owner_id2[i] = CdtoList.get(i).getOwner_id();
					date[i] = CdtoList.get(i).getDate();
					comment_id[i] = CdtoList.get(i).getComment_id();
					cnickname[i] = newcommand.getNickname(owner_id2[i]);
				}
				session.setAttribute("owner_id", owner_id2);
				session.setAttribute("cnickname", cnickname);
				session.setAttribute("comment", ccomment);
				session.setAttribute("date", date);
				session.setAttribute("comment_id", comment_id);
				session.setAttribute("co_count", count);
				session.setAttribute("co_count2", count);
				session.setAttribute("temp", 1);
			}
	         viewPage= "ViewManager.jsp";

		}
		//-----------------------------------------------------로그인
		else if(command.equals("/Login.do")) {  
	         id = request.getParameter("id");
	         pw = request.getParameter("pw");
	         cdto.setId(id);
	         cdto.setPw(pw);
	         newcommand=new Command();
	         pwCheck=newcommand.Login(cdto);
	     	if(pwCheck==1){	
	        	 nickname = cdto.getNickname();
	        	 session.setAttribute("ID", id);
	        	 session.setAttribute("nickname",nickname);
	        	 viewPage="main.do";
	         }
	         else {
	        	 viewPage="Login.jsp";
	         }

		}
		//-----------------------------------------------------회원가입
		else if(command.equals("/signup.do")) {   
			
			 id = request.getParameter("id");		
	         pw = request.getParameter("pw");
	         name = request.getParameter("name");
	         nickname = request.getParameter("nickname");
	         phone = Integer.parseInt(request.getParameter("phonenum"));
	                  
	         cdto.setId(id);
	         cdto.setPw(pw);
	         cdto.setName(name);
	         cdto.setPhone(phone);
	         cdto.setNickname(nickname);
	         cdto.setId(id);
	         cdto.setNickname(nickname);
	         cdto.setSubscriber(0);
	         
	         newcommand = new Command();
	         IdCheck = newcommand.SignUp(cdto);
	         if (IdCheck == true) {
	        	 session.setAttribute("IdCheck", IdCheck);
	        	
	         }
	         else {
	        	 session.setAttribute("IdCheck", IdCheck);
	        	 
	         }
			 viewPage = "SignUp.jsp";
			 
		}
		
		else if(command.equals("/MyPage.do")) { 
			id = String.valueOf(session.getAttribute("ID"));
			newcommand = new Command();
			subscriber = newcommand.NumberOfSubscriber(id);
			session.setAttribute("subscriber", subscriber);
			
			
			viewPage = "MyPage.jsp";
		}
	
		else if(command.equals("/View.do")) { 
			session.removeAttribute("co_count");
			id = String.valueOf(session.getAttribute("ID"));
			int recipe_id = Integer.parseInt((String)request.getParameter("recipe_id"));
			newcommand = new Command();
			if(request.getParameter("check")!= null) {
				newcommand.pressheart(recipe_id);
			}
			newcommand.getContent(rdto, recipe_id);
			
			String recipe_name= rdto.getRecipe_name();
			String recipe_content=rdto.getRecipe_content();
			String img=rdto.getImg();
			int cooking_time=rdto.getCooking_time();
			int level=rdto.getLevel();
			String category=rdto.getCategory();
			int heart=rdto.getHeart();
			
			session.setAttribute("recipe_name", recipe_name);
			session.setAttribute("recipe_id", recipe_id);
			session.setAttribute("img", img);
			session.setAttribute("cooking_time", cooking_time);
			session.setAttribute("level", level);
			session.setAttribute("category", category);
			session.setAttribute("heart", heart);
			
			
	        StringBuilder temp = new StringBuilder();
	        for (int i = 0; i < recipe_content.length(); i++) {
	            char currentChar = recipe_content.charAt(i);
	            if (Character.isDigit(currentChar)) {
	            	if(recipe_content.charAt(i+1) == '.') {
	            		temp.append("<br><br>").append(currentChar);
	            	}else {
	            		temp.append(currentChar);
	            	}
	            } else {
	                temp.append(currentChar);
	            }
	        }
	        String result = temp.toString();
	        session.setAttribute("recipe_content", result);
	        
	        ArrayList<CommentDto> CdtoList = new ArrayList<CommentDto>();
	        CdtoList = newcommand.getComment(recipe_id);
			
			int count = CdtoList.size();
			if(count >=2) {
				int[] crecipe_id = new int[count];
				String[] ccomment = new String[count];
				String[] owner_id = new String[count];
				Timestamp[] date= new Timestamp[count];
				int[] comment_id= new int[count];
				String[] cnickname = new String[count];
				
				for(int i = 0; i <count; i++) {
					crecipe_id[i] = CdtoList.get(i).getRecipe_id();
					ccomment[i] = CdtoList.get(i).getComment_content();
					owner_id[i] = CdtoList.get(i).getOwner_id();
					date[i] = CdtoList.get(i).getDate();
					comment_id[i] = CdtoList.get(i).getComment_id();
					cnickname[i] = newcommand.getNickname(owner_id[i]);
				}
				session.setAttribute("owner_id", owner_id);
				session.setAttribute("cnickname", cnickname);
				session.setAttribute("comment", ccomment);
				session.setAttribute("date", date);
				session.setAttribute("comment_id", comment_id);
				session.setAttribute("co_count", count);
				session.setAttribute("co_count2", count);
				session.setAttribute("temp", 1);
			}
			else if(count == 1) {
				String ccomment = CdtoList.get(0).getComment_content();
				String owner_id = CdtoList.get(0).getOwner_id();
				Timestamp date = CdtoList.get(0).getDate();
				int comment_id = CdtoList.get(0).getComment_id();
				String cnickname = newcommand.getNickname(owner_id);
				
				session.setAttribute("owner_id", owner_id);
				session.setAttribute("cnickname", cnickname);
				session.setAttribute("comment", ccomment);
				session.setAttribute("date", date);
				session.setAttribute("comment_id", comment_id);
				session.setAttribute("co_count", count);
			}
			viewPage = "View.jsp";
		}
		else if(command.equals("/ViewMine.do")) { 
			session.removeAttribute("co_count");
			id = String.valueOf(session.getAttribute("ID"));
			int recipe_id = Integer.parseInt((String)request.getParameter("recipe_id"));
			newcommand = new Command();
			if(request.getParameter("check")!= null) {
				newcommand.pressheart(recipe_id);
			}
			newcommand.getContent(rdto, recipe_id);
			String recipe_name= rdto.getRecipe_name();
			String recipe_content=rdto.getRecipe_content();
			String img=rdto.getImg();
			int cooking_time=rdto.getCooking_time();
			int level=rdto.getLevel();
			String owner_id=rdto.getOwner_id();
			String category=rdto.getCategory();
			int heart=rdto.getHeart();
			
			session.setAttribute("recipe_name", recipe_name);
			session.setAttribute("recipe_id", recipe_id);
			session.setAttribute("img", img);
			session.setAttribute("cooking_time", cooking_time);
			session.setAttribute("level", level);
			session.setAttribute("owner_id", owner_id);
			session.setAttribute("category", category);
			session.setAttribute("heart", heart);
			
			
	        StringBuilder temp = new StringBuilder();
	        for (int i = 0; i < recipe_content.length(); i++) {
	            char currentChar = recipe_content.charAt(i);
	            if (Character.isDigit(currentChar)) {
	            	if(recipe_content.charAt(i+1) == '.') {
	            		temp.append("<br><br>").append(currentChar);
	            	}else {
	            		temp.append(currentChar);
	            	}
	            } else {
	                temp.append(currentChar);
	            }
	        }
	        String result = temp.toString();
	        session.setAttribute("recipe_content", result);
	        
	        
	        ArrayList<CommentDto> CdtoList = new ArrayList<CommentDto>();
	        CdtoList = newcommand.getComment(recipe_id);
			
			int count = CdtoList.size();
			if(count >=2) {
				int[] crecipe_id = new int[count];
				String[] ccomment = new String[count];
				String[] owner_id2 = new String[count];
				Timestamp[] date= new Timestamp[count];
				int[] comment_id= new int[count];
				String[] cnickname = new String[count];
				
				for(int i = 0; i <count; i++) {
					crecipe_id[i] = CdtoList.get(i).getRecipe_id();
					ccomment[i] = CdtoList.get(i).getComment_content();
					owner_id2[i] = CdtoList.get(i).getOwner_id();
					date[i] = CdtoList.get(i).getDate();
					comment_id[i] = CdtoList.get(i).getComment_id();
					cnickname[i] = newcommand.getNickname(owner_id2[i]);
				}
				session.setAttribute("owner_id", owner_id2);
				session.setAttribute("cnickname", cnickname);
				session.setAttribute("comment", ccomment);
				session.setAttribute("date", date);
				session.setAttribute("comment_id", comment_id);
				session.setAttribute("co_count", count);
				session.setAttribute("co_count2", count);
				session.setAttribute("temp", 1);
			}
			else if(count == 1) {
				String ccomment = CdtoList.get(0).getComment_content();
				Timestamp date = CdtoList.get(0).getDate();
				int comment_id = CdtoList.get(0).getComment_id();
				String cnickname = newcommand.getNickname(owner_id);
				
				session.setAttribute("cnickname", cnickname);
				session.setAttribute("comment", ccomment);
				session.setAttribute("date", date);
				session.setAttribute("comment_id", comment_id);
				session.setAttribute("co_count", count);
			}
	        
			viewPage = "ViewMine.jsp";
		}
		else if(command.equals("/MyRecipe.do")) { 
			id = String.valueOf(session.getAttribute("ID"));
			String Drecipe_id = (String)request.getParameter("recipe_id");
			int dre;
			String delete = (String)request.getParameter("delete");
			newcommand = new Command();
			int deletesuccess=-1;
			if(delete != null) {
				if(delete.equals("1")) {
					dre = Integer.parseInt(Drecipe_id);
					newcommand.Delete(dre);
					deletesuccess=1;
				}
			}
			ArrayList<RecipeDto> Rdtolist = newcommand.MyRecipe(id);
			
			int count = Rdtolist.size();
			int[] recipe_id = new int[count];
			String[] recipe_name = new String[count];
			String[] recipe_content = new String[count];
			String[] img= new String[count];
			int[] cooking_time= new int[count];
			int[] level= new int[count];
			String[] category= new String[count];
			int[] heart = new int[count];
			
			for(int i = 0; i <count; i++) {
				recipe_name[i] = Rdtolist.get(i).getRecipe_name();
				recipe_content[i] = Rdtolist.get(i).getRecipe_content();
				img[i] = Rdtolist.get(i).getImg();
				cooking_time[i] = Rdtolist.get(i).getCooking_time();
				level[i] = Rdtolist.get(i).getLevel();
				category[i] = Rdtolist.get(i).getCategory();
				recipe_id[i] = Rdtolist.get(i).getRecipe_id();
				heart[i] = Rdtolist.get(i).getHeart();
				
			}
			session.setAttribute("recipe_name", recipe_name);
			session.setAttribute("recipe_id", recipe_id);
			session.setAttribute("recipe_content", recipe_content);
			session.setAttribute("img", img);
			session.setAttribute("cooking_time", cooking_time);
			session.setAttribute("level", level);
			session.setAttribute("category", category);
			session.setAttribute("count", count);
			session.setAttribute("heart", heart);
			session.setAttribute("deletesuccess", deletesuccess);

			viewPage = "MyRecipe.jsp";
		}
		else if(command.equals("/Viewprofile.do")) { 
			id = String.valueOf(session.getAttribute("ID"));
			String owner_id =  (String)request.getParameter("owner_id");
			
			if(owner_id.equals(id)) {
				viewPage= "MyPage.jsp";
			}
			else {
				newcommand = new Command();
				String owner_nickname = newcommand.getNickname(owner_id);
				ArrayList<RecipeDto> Rdtolist = newcommand.MyRecipe(owner_id);
				
				int count = Rdtolist.size();
				int[] recipe_id = new int[count];
				String[] recipe_name = new String[count];
				String[] recipe_content = new String[count];
				String[] img= new String[count];
				int[] cooking_time= new int[count];
				int[] level= new int[count];
				String[] category= new String[count];
				int[] heart = new int[count];
				
				for(int i = 0; i <count; i++) {
					recipe_name[i] = Rdtolist.get(i).getRecipe_name();
					recipe_content[i] = Rdtolist.get(i).getRecipe_content();
					img[i] = Rdtolist.get(i).getImg();
					cooking_time[i] = Rdtolist.get(i).getCooking_time();
					level[i] = Rdtolist.get(i).getLevel();
					category[i] = Rdtolist.get(i).getCategory();
					recipe_id[i] = Rdtolist.get(i).getRecipe_id();
					heart[i] = Rdtolist.get(i).getHeart();
					
				}
				session.setAttribute("recipe_name", recipe_name);
				session.setAttribute("recipe_id", recipe_id);
				session.setAttribute("recipe_content", recipe_content);
				session.setAttribute("img", img);
				session.setAttribute("cooking_time", cooking_time);
				session.setAttribute("level", level);
				session.setAttribute("category", category);
				session.setAttribute("count", count);
				session.setAttribute("heart", heart);
				session.setAttribute("owner_nickname", owner_nickname);
				session.setAttribute("owner_id", owner_id);

				viewPage = "Viewprofile.jsp";
			}
			
		}
		
		else if(command.equals("/Wish.do")) { 
			id = String.valueOf(session.getAttribute("ID"));
			newcommand = new Command();
			ArrayList<RecipeDto> Rdtolist = newcommand.Wishlist(id);
			int count = Rdtolist.size();
			
			int[] recipe_id = new int[count];
			String[] recipe_name = new String[count];
			String[] recipe_content = new String[count];
			String[] img= new String[count];
			int[] cooking_time= new int[count];
			int[] level= new int[count];
			String[] category= new String[count];
			int[] heart = new int[count];
			String[] wishnick = new String[count];
			
			for(int i = 0; i <count; i++) {
				recipe_name[i] = Rdtolist.get(i).getRecipe_name();
				recipe_content[i] = Rdtolist.get(i).getRecipe_content();
				img[i] = Rdtolist.get(i).getImg();
				cooking_time[i] = Rdtolist.get(i).getCooking_time();
				level[i] = Rdtolist.get(i).getLevel();
				category[i] = Rdtolist.get(i).getCategory();
				recipe_id[i] = Rdtolist.get(i).getRecipe_id();
				heart[i] = Rdtolist.get(i).getHeart();
				wishnick[i] = newcommand.getNickname(Rdtolist.get(i).getOwner_id());
			}
			session.setAttribute("recipe_name", recipe_name);
			session.setAttribute("recipe_id", recipe_id);
			session.setAttribute("recipe_content", recipe_content);
			session.setAttribute("img", img);
			session.setAttribute("cooking_time", cooking_time);
			session.setAttribute("level", level);
			session.setAttribute("category", category);
			session.setAttribute("count", count);
			session.setAttribute("heart", heart);
			session.setAttribute("wishnick", wishnick);
			
			
			viewPage = "Wish.jsp";
		}
		else if(command.equals("/jjim.do")) { 
			id = String.valueOf(session.getAttribute("ID"));
			int recipe_id = Integer.parseInt((String)request.getParameter("recipe_id"));
			newcommand = new Command();
			int success = newcommand.jjim(id, recipe_id);
			session.setAttribute("success", success);
			
			viewPage = "Follow.jsp";
		}
		else if(command.equals("/jjim2.do")) { 
			id = String.valueOf(session.getAttribute("ID"));
			int recipe_id = Integer.parseInt((String)request.getParameter("recipe_id"));
			newcommand = new Command();
			int success = newcommand.jjim(id, recipe_id);
			session.setAttribute("success", success);
			
			viewPage = "Main.jsp";
		}
		else if(command.equals("/jjim3.do")) { 
			id = String.valueOf(session.getAttribute("ID"));
			int recipe_id = Integer.parseInt((String)request.getParameter("recipe_id"));
			newcommand = new Command();
			int success = newcommand.jjim(id, recipe_id);
			session.setAttribute("success", success);
			
			viewPage = "View.jsp";
		}
		else if(command.equals("/Gosub.do")) { 
			id = String.valueOf(session.getAttribute("ID"));
			int recipe_id = Integer.parseInt((String)request.getParameter("recipe_id"));
			int success=0;
			newcommand = new Command();
			newcommand.getContent(rdto, recipe_id);
			
			String owner_id = rdto.getOwner_id();
			if(id.equals(owner_id)) {
				session.setAttribute("sub_success", success);
			}else {
				success = newcommand.Gosub(id, owner_id);
			session.setAttribute("sub_success", success);
			}

			viewPage = "Main.jsp";
		}
		else if(command.equals("/Gosub2.do")) { 
			id = String.valueOf(session.getAttribute("ID"));
			newcommand = new Command();
			
			String owner_id = request.getParameter("owner_id");
			int success = newcommand.Gosub(id, owner_id);
			
			session.setAttribute("sub_success", success);
			
			
			viewPage = "Viewprofile.jsp";
		}
		
		else if(command.equals("/Follow.do")) { 
			id = String.valueOf(session.getAttribute("ID"));
			newcommand = new Command();
			ArrayList<String> mysub = newcommand.MySubscribers_nickname(id);
			ArrayList<String> mysuber = newcommand.MySubscribers(id);
			ArrayList<RecipeDto> Rdtolist = new ArrayList<RecipeDto>();
			ArrayList<ArrayList<RecipeDto>> sub_Rdtolist = new ArrayList<ArrayList<RecipeDto>>();
			int[] quantity = new int[mysub.size()];
			
			for(int i =0;i<mysub.size();i++) {
				Rdtolist = newcommand.MyRecipe(mysuber.get(i));
				sub_Rdtolist.add(Rdtolist);
				quantity[i] = Rdtolist.size();
			}
			ArrayList<Integer>[] recipe_id = new ArrayList[mysub.size()];
			ArrayList<String>[] recipe_name = new ArrayList[mysub.size()];
			ArrayList<String>[] recipe_content = new ArrayList[mysub.size()];
			ArrayList<String>[] img= new ArrayList[mysub.size()];
			ArrayList<Integer>[] cooking_time= new ArrayList[mysub.size()];
			ArrayList<Integer>[] level= new ArrayList[mysub.size()];
			ArrayList<String>[] category=new ArrayList[mysub.size()];
			ArrayList<Integer>[] heart=new ArrayList[mysub.size()];
			
			for(int i = 0; i< mysub.size();i++) {
				recipe_id[i] = new ArrayList<Integer>();
				recipe_name[i] = new ArrayList<String>();
				recipe_content[i] = new ArrayList<String>();
				img[i] = new ArrayList<String>();
				cooking_time[i] = new ArrayList<Integer>();
				level[i] = new ArrayList<Integer>();
				category[i] = new ArrayList<String>();
				heart[i] = new ArrayList<Integer>();
			}
		
			for(int j = 0; j<mysub.size();j++) {	
				for(int i = 0; i <quantity[j]; i++) {
					recipe_id[j].add( sub_Rdtolist.get(j).get(i).getRecipe_id());
					recipe_name[j].add( sub_Rdtolist.get(j).get(i).getRecipe_name());
					recipe_content[j].add( sub_Rdtolist.get(j).get(i).getRecipe_content());
					img[j].add(sub_Rdtolist.get(j).get(i).getImg());
					cooking_time[j].add( sub_Rdtolist.get(j).get(i).getCooking_time());
					level[j].add(sub_Rdtolist.get(j).get(i).getLevel());
					category[j].add( sub_Rdtolist.get(j).get(i).getCategory());	
					heart[j].add(sub_Rdtolist.get(j).get(i).getHeart());
				}				
			}

			int size=0;
			String[] subs = new String[mysub.size()];
			for(String temp : mysub){
				subs[size++] = temp;
			}
			session.setAttribute("subs", subs);
			session.setAttribute("howmany", mysub.size());
			session.setAttribute("recipe_name", recipe_name);
			session.setAttribute("recipe_id", recipe_id);
			session.setAttribute("recipe_content", recipe_content);
			session.setAttribute("img", img);
			session.setAttribute("cooking_time", cooking_time);
			session.setAttribute("level", level);
			session.setAttribute("category", category);
			session.setAttribute("heart", heart);
			session.setAttribute("count", quantity);
			
			viewPage = "Follow.jsp";
		}
		else if(command.equals("/fixView.do")) { 
			id = String.valueOf(session.getAttribute("ID"));
			int recipe_id = Integer.parseInt((String)request.getParameter("recipe_id"));
			newcommand = new Command();
			newcommand.getContent(rdto, recipe_id);
			
			String recipe_name= rdto.getRecipe_name();
			String recipe_content=rdto.getRecipe_content();
			String img=rdto.getImg();
			int cooking_time=rdto.getCooking_time();
			int level=rdto.getLevel();
			String owner_id=rdto.getOwner_id();
			String category=rdto.getCategory();
			int heart=rdto.getHeart();
			
			session.setAttribute("recipe_name", recipe_name);
			session.setAttribute("recipe_id", recipe_id);
			session.setAttribute("img", img);
			session.setAttribute("cooking_time", cooking_time);
			session.setAttribute("level", level);
			session.setAttribute("owner_id", owner_id);
			session.setAttribute("category", category);
			session.setAttribute("heart", heart);
			
	        
	        session.setAttribute("recipe_content", recipe_content);
	        
			viewPage = "FixView.jsp";
		}
		else if(command.equals("/Fix.do")) { 
			id = String.valueOf(session.getAttribute("ID"));
			newcommand = new Command();
			int fixsuccess = 1;
			
			int recipe_id = Integer.parseInt((String)request.getParameter("recipe_id"));
			String recipe_name = (String)request.getParameter("recipe_name");
			String category = (String)request.getParameter("category");
			String recipe_content = (String)request.getParameter("recipe_content");
			int level = Integer.parseInt((String)request.getParameter("level"));
			int cooking_time = Integer.parseInt((String)request.getParameter("cooking_time"));
			newcommand.Fix(recipe_id, recipe_name, recipe_content, level, cooking_time, category);
			
			session.setAttribute("fixsuccess", fixsuccess);
			session.setAttribute("recipe_id2", recipe_id);
			
			viewPage = "MyRecipe.do";
		}
		else if(command.equals("/Search.do")) { 
			id = String.valueOf(session.getAttribute("ID"));
			newcommand = new Command();
			int Searchsuccess = 0;
			ArrayList<RecipeDto> Rdtolist = new ArrayList<RecipeDto>();
			
			String ingredient = (String)request.getParameter("ingredient");
			int lev = Integer.parseInt((String)request.getParameter("level"));
			int time = Integer.parseInt((String)request.getParameter("cooking_time"));
			String cate = request.getParameter("category");
			
			if(ingredient.equals("")) { ingredient = "all";}
			
			Rdtolist = newcommand.SearchRecipe(ingredient, lev, time, cate);
			
			int count = Rdtolist.size();
			int[] recipe_id = new int[count];
			String[] recipe_name = new String[count];
			String[] recipe_content = new String[count];
			String[] img= new String[count];
			int[] cooking_time= new int[count];
			int[] level= new int[count];
			String[] category= new String[count];
			int[] heart = new int[count];
			
			
			for(int i = 0; i <count; i++) {
				recipe_name[i] = Rdtolist.get(i).getRecipe_name();
				recipe_content[i] = Rdtolist.get(i).getRecipe_content();
				img[i] = Rdtolist.get(i).getImg();
				cooking_time[i] = Rdtolist.get(i).getCooking_time();
				level[i] = Rdtolist.get(i).getLevel();
				category[i] = Rdtolist.get(i).getCategory();
				recipe_id[i] = Rdtolist.get(i).getRecipe_id();
				heart[i] = Rdtolist.get(i).getHeart(); 
				
			}
			session.setAttribute("recipe_name", recipe_name);
			session.setAttribute("recipe_id", recipe_id);
			session.setAttribute("recipe_content", recipe_content);
			session.setAttribute("img", img);
			session.setAttribute("cooking_time", cooking_time);
			session.setAttribute("level", level);
			session.setAttribute("category", category);
			session.setAttribute("count", count);
			session.setAttribute("heart", heart);
			
			if(Rdtolist.size() == 0) {
				int searchsuccess = 0;
				session.setAttribute("searchsuccess", searchsuccess);
			}
			
			viewPage = "Search.jsp";
		}
		else if(command.equals("/upload.do")) { 
			id = String.valueOf(session.getAttribute("ID"));
			newcommand = new Command();
			request.setCharacterEncoding("utf-8");	
			String directory = "C:\\apache-tomcat-9.0.65\\wtpwebapps\\2023_Project_Recipe\\upload";
			int maxSize = 1024 * 1024 * 100;
			String encoding = "UTF-8";
			MultipartRequest multipartRequest = new MultipartRequest(
					request, directory, maxSize, encoding,
					new DefaultFileRenamePolicy());
			

			String fileRealName = multipartRequest.getFilesystemName("file");
			
			String recipe_name = multipartRequest.getParameter("recipe_name");
			String recipe_content = multipartRequest.getParameter("recipe_content");
			String category = multipartRequest.getParameter("category");
			String ingredients = multipartRequest.getParameter("ingredients");
			int level = Integer.parseInt(multipartRequest.getParameter("level"));
			int cooking_time = Integer.parseInt(multipartRequest.getParameter("cooking_time"));
		
			int recipe_id = newcommand.Upload(recipe_name, recipe_content, category, level, cooking_time, fileRealName, id);
			String[] temp = ingredients.split(" ");
			ArrayList<String> ingredient = new ArrayList<String>();
			for(int i = 0; i<temp.length;i++) {
				ingredient.add(temp[i]);
			}
			newcommand.UploadIngre(recipe_id, ingredient);
			
			viewPage = "MyRecipe.do"; 
		}
		else if(command.equals("/comment.do")) { 
			id = String.valueOf(session.getAttribute("ID"));
			newcommand = new Command();
			ArrayList<CommentDto> CdtoList = new ArrayList<CommentDto>();
			
			int recipe_id = Integer.parseInt((String)request.getParameter("recipe_id"));
			String comment =(String)request.getParameter("comment");
		
			newcommand.Comment(id, comment,  recipe_id);
			CdtoList = newcommand.getComment(recipe_id);
			
			int count = CdtoList.size();
			if(count >=2) {
				int[] crecipe_id = new int[count];
				String[] ccomment = new String[count];
				String[] owner_id = new String[count];
				Timestamp[] date= new Timestamp[count];
				int[] comment_id= new int[count];
				String[] cnickname = new String[count];
				
				for(int i = 0; i <count; i++) {
					crecipe_id[i] = CdtoList.get(i).getRecipe_id();
					ccomment[i] = CdtoList.get(i).getComment_content();
					owner_id[i] = CdtoList.get(i).getOwner_id();
					date[i] = CdtoList.get(i).getDate();
					comment_id[i] = CdtoList.get(i).getComment_id();
					cnickname[i] = newcommand.getNickname(owner_id[i]);
				}
				session.setAttribute("owner_id", owner_id);
				session.setAttribute("cnickname", cnickname);
				session.setAttribute("comment", ccomment);
				session.setAttribute("date", date);
				session.setAttribute("comment_id", comment_id);
				session.setAttribute("co_count", count);
				session.setAttribute("co_count2", count);
				session.setAttribute("temp", 1);
			}
			else if(count == 1) {
				String ccomment = CdtoList.get(0).getComment_content();
				String owner_id = CdtoList.get(0).getOwner_id();
				Timestamp date = CdtoList.get(0).getDate();
				int comment_id = CdtoList.get(0).getComment_id();
				String cnickname = newcommand.getNickname(owner_id);
				
				session.setAttribute("owner_id", owner_id);
				session.setAttribute("cnickname", cnickname);
				session.setAttribute("comment", ccomment);
				session.setAttribute("date", date);
				session.setAttribute("comment_id", comment_id);
				session.setAttribute("co_count", count);
			}
			String mine = (String)request.getParameter("mine");
			if(mine.equals("mine")) {
				viewPage = "ViewMine.jsp"; 
			}else {
				viewPage = "View.jsp"; 
			}
			
		}

		
		response.sendRedirect(viewPage);
	}

}
