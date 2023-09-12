package com.sw.command;


import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;

import com.sw.dao.*;
import com.sw.dto.*;

public class Command implements Service {
	
	public boolean SignUp(CustomerDto cdto) {
		Dao cdao = new DaoImpl();
		boolean IdCheck = cdao.SignUP(cdto);
		
		return IdCheck;
	}
	public int Login(CustomerDto cdto) {
		// TODO Auto-generated method stub
		int ret = 0;
		Dao cdao = new DaoImpl();
		String id = cdto.getId();
		String pw = cdto.getPw();
		
		String dbpw = cdao.Login(cdto, id);
		if(pw.equals(dbpw)) {
			ret = 1;
		}else {
			ret = 0;
		}
		
		return ret;
	}
	public int NumberOfSubscriber(String id) {
		int subscriber=0;
		Dao cdao = new DaoImpl();
		subscriber = cdao.NumberOfSubscriber(id);
		
		
		return subscriber;
	}
	public ArrayList<RecipeDto> MyRecipe(String id) {
		ArrayList<RecipeDto> Rdtolist = new ArrayList<RecipeDto>();
		Dao cdao = new DaoImpl();
		Rdtolist=cdao.MyRecipe(id);
		
		return Rdtolist;
	}
	public void getContent(RecipeDto rdto, int recipe_id) {
		Dao cdao = new DaoImpl();
		cdao.getContent(rdto, recipe_id);
	}
	public ArrayList<String> MySubscribers(String id){
		ArrayList<String> mysub = new ArrayList<String>();
		Dao cdao = new DaoImpl();
		mysub=cdao.MySubscribers(id);
		
		return mysub;
	}
	public ArrayList<String> MySubscribers_nickname(String id){
		ArrayList<String> mysub = new ArrayList<String>();
		Dao cdao = new DaoImpl();
		mysub=cdao.MySubscribers_nickname(id);
		
		return mysub;
	}
	public void pressheart(int recipe_id) {
		Dao cdao = new DaoImpl();
		cdao.pressheart(recipe_id);
	}
	public ArrayList<RecipeDto> Wishlist(String id) {
		ArrayList<RecipeDto> Rdtolist = new ArrayList<RecipeDto>();
		Dao cdao = new DaoImpl();
		Rdtolist=cdao.Wishlist(id);
		
		return Rdtolist;
	}
	public String getNickname(String owner_id) {
		String ownernick;
		Dao cdao = new DaoImpl();
		ownernick=cdao.getNickname(owner_id);
		
		return ownernick;
	}
	public int Gosub(String id, String owner_id) {
		int success;
		Dao cdao = new DaoImpl();
		success=cdao.Gosub(id, owner_id);
		
		return success;
	}
	public int jjim(String id, int recipe_id) {
		Dao cdao = new DaoImpl();
		int success = cdao.jjim(id, recipe_id);
		return success;
	}
	public ArrayList<RecipeDto> PopularRecipe(){
		ArrayList<RecipeDto> Rdtolist = new ArrayList<RecipeDto>();
		Dao cdao = new DaoImpl();
		Rdtolist=cdao.PopularRecipe();
		
		return Rdtolist;
	}
	public void Delete(int recipe_id) {
		Dao cdao = new DaoImpl();
		cdao.Delete(recipe_id);
	}
	public void Fix(int recipe_id, String recipe_name, String recipe_content, int level, int cooking_time, String category) {
		int fixsuccess;
		Dao cdao = new DaoImpl();
		cdao.Fix(recipe_id, recipe_name, recipe_content, level, cooking_time, category);
		
	}
	public ArrayList<RecipeDto> SearchRecipe(String ingredient,int level,int cooking_time,String category){
		ArrayList<RecipeDto> Rdtolist = new ArrayList<RecipeDto>();
		Dao cdao = new DaoImpl();
		Rdtolist=cdao.SearchRecipe(ingredient, level, cooking_time, category);
		
		return Rdtolist;
	}
	public int Upload(String recipe_name,String recipe_content,String category,int level, int cooking_time,String fileRealName, String id) {
		Dao cdao = new DaoImpl();
		int recipe_id = cdao.Upload(recipe_name,recipe_content,category,level,cooking_time, fileRealName, id);
		
		return recipe_id;
	}
	public void Comment(String id, String comment, int recipe_id ) {
		Dao cdao = new DaoImpl();
		cdao.Comment(id, comment, recipe_id);
	}
	public ArrayList<CommentDto> getComment(int recipe_id) {
		ArrayList<CommentDto> Cdtolist = new ArrayList<CommentDto>();
		Dao cdao = new DaoImpl();
		Cdtolist = cdao.getComment(recipe_id);
		return Cdtolist;
	}
	public void UploadIngre(int recipe_id,ArrayList<String> ingredient) {
		Dao cdao = new DaoImpl();
		cdao.UploadIngre(recipe_id, ingredient);
	}
	public ArrayList<CustomerDto> getCustomer(){
		ArrayList<CustomerDto> Customerlist = new ArrayList<CustomerDto>();
		Dao cdao = new DaoImpl();
		Customerlist = cdao.getCustomer();
		
		return Customerlist;
	}
	public void DeleteCustomer(String customer_id) {
		Dao cdao = new DaoImpl();
		cdao.DeleteCustomer(customer_id);
	}
	public void DeleteComment(String comment_id) {
		Dao cdao = new DaoImpl();
		cdao.DeleteComment(comment_id);
	}
}
