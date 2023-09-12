package com.sw.dao;

import java.sql.Connection;
import java.util.Date;
import java.util.HashSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.sw.dto.*;

public class DaoImpl implements Dao {
	public int login(CustomerDto dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			con = getConnection();
			String sql = "select customer_id, pw from customer where customer_id = ? and pw = ?";
			pstmt = con.prepareStatement(sql);
			
			String id = dto.getId();
			String pw = dto.getPw();
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String Cid = rs.getString("customer_id");
				String Cpw = rs.getString("pw");
				
				if(Cid == null) {}
				else if(Cid.equals(id)) {
					if(Cpw.equals(pw)) {
						result = 1;
					}
					else {}
				}
				else {}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			closeConnection(rs,pstmt,con);
		}
		
		return result;
	}

	@Override
	public boolean SignUP(CustomerDto cdto) {
		// TODO Auto-generated method stub
		System.out.println("MemberDaoImpl::insertMember");
		Connection conn = getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		int ret=0;
		boolean IdCheck = true;
		String sql = "insert into customer (customer_id, name, password, phone_num, subscriber, nickname) values (?,?,?,?,?,?)";
		String sql2 = "select customer_id from customer where customer_id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);	
			pstmt2 = conn.prepareStatement(sql2);
			
			String id = cdto.getId();
			String pw = cdto.getPw();
			String nickname = cdto.getNickname();
			int phone = cdto.getPhone();
			String name = cdto.getName();
			int subscriber = cdto.getSubscriber();
		
			pstmt2.setString(1, id);
			rs = pstmt2.executeQuery();
			
			while(rs.next()) {
				IdCheck = false;
			}
			if(IdCheck == true) {
				pstmt.setString(1, id);
				pstmt.setString(2, name);
				pstmt.setString(3, pw);
				pstmt.setInt(4, phone);
				pstmt.setInt(5, subscriber);
				pstmt.setString(6, nickname);
				
				ret = pstmt.executeUpdate();
			}
			
			
		} catch(SQLException e) {
			ret = -1;
			System.out.println("access error.");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
		} finally {
			closeConnection(rs, pstmt, conn);
		}
		return IdCheck;
	}
	

	@Override
	public String Login(CustomerDto cdto, String id) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = "select password, nickname from customer where customer_id =?";
		String pwDb = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				pwDb = rs.getString("password");
				String nickname = rs.getString("nickname");
				cdto.setNickname(nickname);
			}
			
		}catch (SQLException e){
			System.out.println("SQLException error.");
			e.printStackTrace();
		}finally {
			closeConnection(rs, pstmt, conn);
			
		}
		return pwDb;
	}
	public int NumberOfSubscriber(String id) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = "select subscriber from customer where customer_id =?";
		int subscriber = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				subscriber = rs.getInt("subscriber");
				
			}
			
		}catch (SQLException e){
			System.out.println("SQLException error.");
			e.printStackTrace();
		}finally {
			closeConnection(rs, pstmt, conn);
		}
		return subscriber;
	}
	public ArrayList<RecipeDto> PopularRecipe(){
		Connection conn = getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = "select * from recipe order by heart desc;";

	    ArrayList<RecipeDto> Rdtolist = new ArrayList<RecipeDto>();
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int recipe_id = rs.getInt("recipe_id");
				String recipe_name = rs.getString("recipe_name");
				int level = rs.getInt("level");
				int cooking_time = rs.getInt("cooking_time");
				String img = rs.getString("image");
				img = "./upload/"+img;
				String recipe_content = rs.getString("recipe_content");
				String category = rs.getString("category");
				String owner_id = rs.getString("owner_id");
				int heart = rs.getInt("heart");
				RecipeDto rdto = new RecipeDto(recipe_id, recipe_name, level, cooking_time, img,
						recipe_content,category,owner_id, heart);
				Rdtolist.add(rdto);
				
				
				
			}
			
		}catch (SQLException e){
			System.out.println("SQLException error.");
			e.printStackTrace();
		}finally {
			closeConnection(rs, pstmt, conn);
		}
		return Rdtolist;
	}
	public ArrayList<RecipeDto> MyRecipe(String id) {
		Connection conn = getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = "select * from recipe where owner_id = ?;";

	    ArrayList<RecipeDto> Rdtolist = new ArrayList<RecipeDto>();
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int recipe_id = rs.getInt("recipe_id");
				String recipe_name = rs.getString("recipe_name");
				int level = rs.getInt("level");
				int cooking_time = rs.getInt("cooking_time");
				String img = rs.getString("image");
				img = "./upload/"+img;
				String recipe_content = rs.getString("recipe_content");
				String category = rs.getString("category");
				String owner_id = rs.getString("owner_id");
				int heart = rs.getInt("heart");
				RecipeDto rdto = new RecipeDto(recipe_id, recipe_name, level, cooking_time, img,
						recipe_content,category,owner_id, heart);
				Rdtolist.add(rdto);
				
				
				
			}
			
		}catch (SQLException e){
			System.out.println("SQLException error.");
			e.printStackTrace();
		}finally {
			closeConnection(rs, pstmt, conn);
		}
		return Rdtolist;
	}
	public ArrayList<RecipeDto> Wishlist(String id){
		Connection conn = getConnection();
		ResultSet rs = null;
		Connection conn2 = getConnection();
		ResultSet rs2 = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		
		String sql = "select recipe_id from pick where customer_id = ?;";
		String sql2 = "select * From recipe where recipe_id = ?";
	    ArrayList<RecipeDto> Rdtolist = new ArrayList<RecipeDto>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				pstmt2 = conn2.prepareStatement(sql2);
				pstmt2.setInt(1, rs.getInt("recipe_id"));
				rs2 = pstmt2.executeQuery();
				
				while(rs2.next()) {
					int recipe_id = rs2.getInt("recipe_id");
					String recipe_name = rs2.getString("recipe_name");
					int level = rs2.getInt("level");
					int cooking_time = rs2.getInt("cooking_time");
					String img = rs2.getString("image");
					img = "./upload/"+img;
					String recipe_content = rs2.getString("recipe_content");
					String category = rs2.getString("category");
					String owner_id = rs2.getString("owner_id");
					int heart = rs2.getInt("heart");
					
					RecipeDto rdto = new RecipeDto(recipe_id, recipe_name, level, cooking_time, img,
							recipe_content,category,owner_id, heart);
					Rdtolist.add(rdto);

				}
			}
			
		}catch (SQLException e){
			System.out.println("SQLException error.");
			e.printStackTrace();
		}finally {
			closeConnection(rs, pstmt, conn);
			closeConnection(rs2, pstmt2, conn2);
		}
		return Rdtolist;
	}
	public String getNickname(String owner_id) {
		Connection conn = getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = "select nickname From customer where customer_id = ?;";
		String nick="";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, owner_id);
			rs = pstmt.executeQuery();
			rs.next();
			nick = rs.getString("nickname");
			
		}catch (SQLException e){
			System.out.println("SQLException error.");
			e.printStackTrace();
		}finally {
			closeConnection(rs, pstmt, conn);
		}
		return nick;
		
	}
	public ArrayList<ArrayList<RecipeDto>> SubscribersRecipes(String id){
		Connection conn = getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = "select * from recipe where owner_id = ?;";
		
		
		ArrayList<RecipeDto> Rdtolist = new ArrayList<RecipeDto>();
		ArrayList<ArrayList<RecipeDto>> sub_Rdtolist = new ArrayList<ArrayList<RecipeDto>>();
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int recipe_id = rs.getInt("recipe_id");
				String recipe_name = rs.getString("recipe_name");
				int level = rs.getInt("level");
				int cooking_time = rs.getInt("cooking_time");
				String img = rs.getString("image");
				img = "./upload/"+img;
				String recipe_content = rs.getString("recipe_content");
				String category = rs.getString("category");
				String owner_id = rs.getString("owner_id");
				int heart = rs.getInt("heart");
				RecipeDto rdto = new RecipeDto(recipe_id, recipe_name, level, cooking_time, img,
						recipe_content,category,owner_id, heart);
				Rdtolist.add(rdto);

			}
			
		}catch (SQLException e){
			System.out.println("SQLException error.");
			e.printStackTrace();
		}finally {
			closeConnection(rs, pstmt, conn);
		}
		return sub_Rdtolist;
		
	}
	public ArrayList<String> MySubscribers(String id){
		Connection conn = getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = "select * from subscribe where customer_id = ?;";

	    ArrayList<String> mysub = new ArrayList<String>();
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String subscriber_id = rs.getString("subscriber_id");
				mysub.add(subscriber_id);	
			}
			
		}catch (SQLException e){
			System.out.println("SQLException error.");
			e.printStackTrace();
		}finally {
			closeConnection(rs, pstmt, conn);
		}
		return mysub;
		
	}
	public ArrayList<String> MySubscribers_nickname(String id){
		Connection conn = getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = "select * from subscribe where customer_id = ?;";
		String sql2 = "select nickname from customer where customer_id = ?;";
	    ArrayList<String> mysub = new ArrayList<String>();
	    ArrayList<String> mysub2 = new ArrayList<String>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String subscriber_id = rs.getString("subscriber_id");
				mysub.add(subscriber_id);	
			}
			
			for(int i = 0; i< mysub.size();i++) {
				closeConnection(rs, pstmt, conn);
				conn = getConnection();
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, mysub.get(i));
				rs = pstmt.executeQuery();
				while(rs.next()) {
					mysub2.add(rs.getString("nickname"));	
				}
			}
			
			
		}catch (SQLException e){
			System.out.println("SQLException error.");
			e.printStackTrace();
		}finally {
			closeConnection(rs, pstmt, conn);
		}
		return mysub2;
		
	}
	public void getContent(RecipeDto rdto, int recipe_id) {
		Connection conn = getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = "select * from recipe where recipe_id =?";
		int recipe_Id=0;
		String recipe_name="";
		String recipe_content="";
		String img="";
		int cooking_time=0;
		int level=0;
		String owner_id="";
		String category="";
		int heart=0;
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, recipe_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				recipe_Id = rs.getInt("recipe_id");
				recipe_name= rs.getString("recipe_name");
				recipe_content= rs.getString("recipe_content");
				img= rs.getString("image");
				img = "./upload/"+img;
				cooking_time = rs.getInt("cooking_time");
				level = rs.getInt("level");
				owner_id= rs.getString("owner_id");
				category= rs.getString("category");
				heart = rs.getInt("heart");
				
			}
			rdto.setRecipe_id(recipe_Id);
			rdto.setRecipe_name(recipe_name);
			rdto.setRecipe_content(recipe_content);
			rdto.setImg(img);
			rdto.setCooking_time(cooking_time);
			rdto.setCooking_time(cooking_time);
			rdto.setLevel(level);
			rdto.setOwner_id(owner_id);
			rdto.setCategory(category);
			rdto.setHeart(heart);
			
		}catch (SQLException e){
			System.out.println("SQLException error.");
			e.printStackTrace();
		}finally {
			closeConnection(rs, pstmt, conn);
		}
		
		
	}
	public int Gosub(String id, String owner_id) {
		Connection conn = getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection conn2 = getConnection();
		PreparedStatement pstmt2 = null;
		String sql = "insert into subscribe values(?,?);";
		String sql2= "select subscriber_id from subscribe where customer_id = ?;";
	    int success=-1;
	    int count =0;
		
		try {
			pstmt2 = conn2.prepareStatement(sql2);
			pstmt2.setString(1, id);
			rs = pstmt2.executeQuery();
			
			while(rs.next()) {
				if(rs.getString("subscriber_id").equals(owner_id)) {
					count++;
				}
			}
			if(count == 0) {
				success=1;
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setString(2, owner_id);
				pstmt.executeUpdate();
			}
			
		}catch (SQLException e){
			System.out.println("SQLException error.");
			e.printStackTrace();
		}finally {
			closeConnection(rs, pstmt, conn);
		}
		return success;
	}
	public void pressheart(int recipe_id) {
		Connection conn = getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE recipe SET heart = heart+1 WHERE recipe_id =?" ;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, recipe_id);
			pstmt.executeUpdate();
			
			
		}catch (SQLException e){
			System.out.println("SQLException error.");
			e.printStackTrace();
		}finally {
			closeConnection(rs, pstmt, conn);
		}
	}
	public int jjim(String id, int recipe_id) {
		Connection conn = getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection conn2 = getConnection();
		PreparedStatement pstmt2 = null;
		String sql = "INSERT INTO pick (customer_id, recipe_id) VALUES (?,?);" ;
		String sql2 = "select recipe_id from pick where recipe_id = ? and customer_id = ?;";
		int success=0;
		
		try {
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setInt(1, recipe_id);
			pstmt2.setString(2, id);
			rs = pstmt2.executeQuery();
			if(rs.next()) {
				success = -1;
				System.out.println(success);
				
			}
			else {
				success = 1;
				System.out.println(success);
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setInt(2, recipe_id);
				pstmt.executeUpdate();
			}
	
		}catch (SQLException e){
			System.out.println("SQLException error.");
			e.printStackTrace();
		}finally {
			closeConnection(rs, pstmt, conn);
		}
		return success;
	}
	public void Delete(int recipe_id) {
		Connection conn = getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		String sql = "delete from recipe where recipe_id = ?" ;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, recipe_id);
			pstmt.executeUpdate();
			
			
	
		}catch (SQLException e){
			System.out.println("SQLException error.");
			e.printStackTrace();
		}finally {
			closeConnection(rs, pstmt, conn);
		}
		
	}
	public void DeleteComment(String comment_id) {
		Connection conn = getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		String sql = "delete from comment where comment_id = ?" ;
		
		try {
			System.out.println(comment_id);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, comment_id);
			pstmt.executeUpdate();
			
			
	
		}catch (SQLException e){
			System.out.println("SQLException error.");
			e.printStackTrace();
		}finally {
			closeConnection(rs, pstmt, conn);
		}
	}
	public void Fix(int recipe_id, String recipe_name, String recipe_content, int level, int cooking_time ,String category) {
		Connection conn = getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		
		String sql = "UPDATE recipe SET recipe_name = ? ,recipe_content = ? ,level = ? ,cooking_time =?, category = ? WHERE recipe_id= ?;";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, recipe_name);
			pstmt.setString(2, recipe_content);
			pstmt.setInt(3, level);
			pstmt.setInt(4, cooking_time);
			pstmt.setString(5, category);
			pstmt.setInt(6, recipe_id);
			
			pstmt.executeUpdate();
			
			
	
		}catch (SQLException e){
			System.out.println("SQLException error.");
			e.printStackTrace();
		}finally {
			closeConnection(rs, pstmt, conn);
		}
		
	}
	public ArrayList<RecipeDto> SearchRecipe(String ingredient,int level,int cooking_time,String category){
		Connection conn = getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		Connection conn2 = getConnection();
		ResultSet rs2 = null;
		PreparedStatement pstmt2 = null;
		
		Connection conn3 = getConnection();
		ResultSet rs3 = null;
		PreparedStatement pstmt3 = null;
		
		Connection conn4 = getConnection();
		ResultSet rs4 = null;
		PreparedStatement pstmt4 = null;
		
		Connection conn5 = getConnection();
		ResultSet rs5 = null;
		PreparedStatement pstmt5 = null;
		
		RecipeDto rdto = new RecipeDto();
		ArrayList<Integer> recipe_ingr = new ArrayList<Integer>();
		ArrayList<Integer> recipe_time = new ArrayList<Integer>();
		ArrayList<Integer> recipe_level = new ArrayList<Integer>();
		ArrayList<Integer> recipe_category = new ArrayList<Integer>();
		ArrayList<Integer> recipe_name = new ArrayList<Integer>();
		ArrayList<RecipeDto> Rdtolist = new ArrayList<RecipeDto>();
		String sql ="select recipe_id from ingredients where ingredients1 =? or ingredients2 =? or ingredients3 =? or ingredients4 =? or ingredients5 =?; "; 
		String sql2 ="select recipe_id from recipe where level = ?;"; 
		String sql3 ="select recipe_id from recipe where category = ?"; 
		String sql4 ="select recipe_id from recipe where cooking_time < ?";
		String sql5 ="select recipe_id from recipe where recipe_name like  ?";
		int nokeyward = 0;
		try {
			if(ingredient.equals("all")) {
				sql = "select recipe_id from ingredients;";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				
			}else {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, ingredient);
				pstmt.setString(2, ingredient);
				pstmt.setString(3, ingredient);
				pstmt.setString(4, ingredient);
				pstmt.setString(5, ingredient);
				rs = pstmt.executeQuery();
				
				pstmt5 = conn5.prepareStatement(sql5);
				pstmt5.setString(1, "%"+ingredient+"%");
				rs5 = pstmt5.executeQuery();
				nokeyward = 1;
				
			}

			if(level == 0) {
				sql2 ="select recipe_id from recipe;"; 
				pstmt2 = conn2.prepareStatement(sql2);
				rs2 = pstmt2.executeQuery();
			}else {
				pstmt2 = conn2.prepareStatement(sql2);
				pstmt2.setInt(1, level);
				rs2 = pstmt2.executeQuery();
			}
			
			if(category.equals("all")) {
				sql3 ="select recipe_id from recipe;"; 
				pstmt3 = conn3.prepareStatement(sql3);
				rs3 = pstmt3.executeQuery();
			}else {
				pstmt3 = conn3.prepareStatement(sql3);
				pstmt3.setString(1, category);
				rs3 = pstmt3.executeQuery();
			}
			
			if(cooking_time==0) {
				sql4 ="select recipe_id from recipe;"; 
				pstmt4 = conn4.prepareStatement(sql4);
				rs4 = pstmt4.executeQuery();
			}else {
				pstmt4 = conn4.prepareStatement(sql4);
				pstmt4.setInt(1, cooking_time);
				rs4 = pstmt4.executeQuery();
			}
			
			while(rs.next()) {
				recipe_ingr.add(rs.getInt("recipe_id"));
			}
			while(rs2.next()) {
				recipe_time.add(rs2.getInt("recipe_id"));
			}
			while(rs3.next()) {
				recipe_level.add(rs3.getInt("recipe_id"));
			}
			while(rs4.next()) {
				recipe_category.add(rs4.getInt("recipe_id"));
			}
			if(nokeyward == 1) {
				while(rs5.next()) {
					recipe_name.add(rs5.getInt("recipe_id"));
				}
			}
			recipe_ingr.retainAll(recipe_time);
			recipe_ingr.retainAll(recipe_level);
			recipe_ingr.retainAll(recipe_category);
			for(int i =0; i < recipe_name.size();i++) {
				recipe_ingr.add(recipe_name.get(i));
				
			}
			HashSet<Integer> hs = new HashSet<Integer>(recipe_ingr);
			recipe_ingr.clear();
			recipe_ingr.addAll(hs);
			
			
			for(int i = 0; i< recipe_ingr.size();i++) {
				getContent(rdto, recipe_ingr.get(i));
				Rdtolist.add(rdto);
				rdto = new RecipeDto();
			}
	
		}catch (SQLException e){
			System.out.println("SQLException error.");
			e.printStackTrace();
		}finally {
			closeConnection(rs, pstmt, conn);
			closeConnection(rs2, pstmt2, conn2);
			closeConnection(rs3, pstmt3, conn3);
			closeConnection(rs4, pstmt4, conn4);
		}
		return Rdtolist;
	}
	public int Upload(String recipe_name,String recipe_content,String category,int level, 
			int cooking_time,String fileRealName, String id) {
		Connection conn = getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		Connection conn2 = getConnection();
		ResultSet rs2 = null;
		PreparedStatement pstmt2 = null;
		
		int recipe_id = 2;
		String sql = "select count(*) from recipe order by recipe_id;";
		String sql2 = "insert into recipe (recipe_id, recipe_name, recipe_content, image, cooking_time, level, owner_id,category,heart ) values (?,?,?,?,?,?,?,?,?);";
		
		try {
			pstmt = conn.prepareStatement(sql);			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				recipe_id += rs.getInt("count(*)");
			}
			pstmt2 = conn2.prepareStatement(sql2);		
			pstmt2.setInt(1, recipe_id);
			pstmt2.setString(2, recipe_name);
			pstmt2.setString(3, recipe_content);
			pstmt2.setString(4, fileRealName);
			pstmt2.setInt(5, cooking_time);
			pstmt2.setInt(6, level);
			pstmt2.setString(7, id);
			pstmt2.setString(8, category);
			pstmt2.setInt(9, 0);
			pstmt2.executeUpdate();
			
	
		}catch (SQLException e){
			System.out.println("SQLException error.");
			e.printStackTrace();
		}finally {
			closeConnection(rs, pstmt, conn);
		}
		return recipe_id;
	}
	public void UploadIngre(int recipe_id,ArrayList<String> ingredient) {
		Connection conn = getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		String sql = "insert into ingredients (recipe_id, ingredients1, ingredients2, ingredients3, ingredients4, ingredients5) values (?,?,?,?,?,?);";
		for(int i = ingredient.size(); i< 5;i++) {
			ingredient.add(null);
		}

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, recipe_id);
			pstmt.setString(2, ingredient.get(0));
			pstmt.setString(3, ingredient.get(1));
			pstmt.setString(4, ingredient.get(2));
			pstmt.setString(5, ingredient.get(3));
			pstmt.setString(6, ingredient.get(4));
			pstmt.executeUpdate();
			
		}catch (SQLException e){
			System.out.println("SQLException error.");
			e.printStackTrace();
		}finally {
			closeConnection(rs, pstmt, conn);
		}
	}
	public void Comment(String id, String comment,int recipe_id ) {
		Connection conn = getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		Connection conn2 = getConnection();
		ResultSet rs2 = null;
		PreparedStatement pstmt2 = null;
		java.util.Date utilDate = new java.util.Date();
	    java.sql.Timestamp  sqlDate = new java.sql.Timestamp (utilDate.getTime());
		String comment_id = String.valueOf((int)(Math.random() * 10000)+100);
		
		String sql2 = "insert into comment (comment_id, owner_id, comment_content, date, recipe_id) values (?,?,?,?,?);";
		
		try {
			pstmt2 = conn2.prepareStatement(sql2);		
			pstmt2.setString(1, comment_id);
			pstmt2.setString(2, id);
			pstmt2.setString(3, comment);
			pstmt2.setTimestamp (4, sqlDate);
			pstmt2.setInt(5, recipe_id);
			
			pstmt2.executeUpdate();
			
	
		}catch (SQLException e){
			System.out.println("SQLException error.");
			e.printStackTrace();
		}finally {
			closeConnection(rs, pstmt, conn);
		}
		
	}
	public ArrayList<CustomerDto> getCustomer(){
		Connection conn = getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		ArrayList<CustomerDto> CustomerList = new ArrayList<CustomerDto>();
		
		String sql = "select * from customer where customer_id != ?;";
		
		try {
			pstmt = conn.prepareStatement(sql);	
			pstmt.setString(1, "manager");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String customer_id = rs.getString("customer_id");
				String name = rs.getString("name");
				String pw = rs.getString("password");
				int phone = rs.getInt("phone_num");
				int subscriber = rs.getInt("subscriber");
				String nickname = rs.getString("nickname");
				CustomerDto cdto= new CustomerDto(customer_id,pw ,name , nickname, phone,subscriber);
				CustomerList.add(cdto);
			}
			
	
		}catch (SQLException e){
			System.out.println("SQLException error.");
			e.printStackTrace();
		}finally {
			closeConnection(rs, pstmt, conn);
		}
		return CustomerList;
	}
	public void DeleteCustomer(String customer_id) {
		Connection conn = getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		String sql = "delete from customer where customer_id = ?;";
		try {
			pstmt = conn.prepareStatement(sql);	
			pstmt.setString(1, customer_id);
			pstmt.executeUpdate();
			
			
	
		}catch (SQLException e){
			System.out.println("SQLException error.");
			e.printStackTrace();
		}finally {
			closeConnection(rs, pstmt, conn);
		}
	}
	public ArrayList<CommentDto> getComment(int recipe_id) {
		Connection conn = getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		java.util.Date utilDate = new java.util.Date();
	    java.sql.Timestamp  sqlDate = new java.sql.Timestamp (utilDate.getTime());
	    ArrayList<CommentDto> Cdtolist = new ArrayList<CommentDto>();
		
		String sql = "SELECT * FROM recipesite_db.comment where recipe_id = ? order by date desc;";
			
		try {
			pstmt = conn.prepareStatement(sql);	
			pstmt.setInt(1, recipe_id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int crecipe_id = rs.getInt("recipe_id");
				int comment_id = rs.getInt("comment_id");
				String comment_content = rs.getString("comment_content");
				String owner_id = rs.getString("owner_id");
				Timestamp date = rs.getTimestamp("date");
				
				CommentDto cdto = new CommentDto(comment_id, owner_id, comment_content, date, crecipe_id);
				Cdtolist.add(cdto);
			}
			
			
	
		}catch (SQLException e){
			System.out.println("SQLException error.");
			e.printStackTrace();
		}finally {
			closeConnection(rs, pstmt, conn);
		}
		return Cdtolist;
	}
	   
	public Connection getConnection() {
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("JDBC driver load success");
		}
		catch(ClassNotFoundException e) {
			 e.printStackTrace();
	         System.out.println("JDBC driver load fail");
		}
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/recipesite_db?useSSL=false", 
			          "root","kje991229");
			System.out.println("DB connect success");
		}
		catch(SQLException e) {
			System.out.println("connect fail");
			e.printStackTrace();
		}
		
		return con;
	}
	
	public void closeConnection(ResultSet set, PreparedStatement pstmt, Connection conn) {
		if(set!=null)
		{
			try {
				set.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		if(pstmt!=null)
		{
			try {
				pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		if(conn!=null)
		{
			try {
				conn.close();
			} catch (Exception e2) {
			e2.printStackTrace();
			}
		}
	}
		

}