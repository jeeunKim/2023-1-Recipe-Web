package com.sw.command;
import java.util.ArrayList;
import java.util.Date;
import com.sw.dto.*;
public interface Service {
	public int Login(CustomerDto cdto);
	public boolean SignUp(CustomerDto cdto);
	public int NumberOfSubscriber(String id);
	public ArrayList<RecipeDto> MyRecipe(String id);
	public void getContent(RecipeDto rdto, int recipe_id);
	public void pressheart(int recipe_id);
	public ArrayList<String> MySubscribers(String id);
	public ArrayList<String> MySubscribers_nickname(String id);
	public ArrayList<RecipeDto> Wishlist(String id);
	public String getNickname(String owner_id);
	public int jjim(String id, int recipe_id);
	public ArrayList<RecipeDto> PopularRecipe();
	public int Gosub(String id, String owner_id);
	public void Delete(int recipe_id);
	public void Fix(int recipe_id, String recipe_name, String recipe_content, int level, int cooking_time, String category);
	public ArrayList<RecipeDto> SearchRecipe(String ingredient,int level,int cooking_time,String category);
	public int Upload(String recipe_name,String recipe_content,String category,int level, 
			int cooking_time,String fileRealName, String id);
	public void Comment(String id, String comment,int recipe_id );
	public ArrayList<CommentDto> getComment(int recipe_id);
	public void UploadIngre(int recipe_id,ArrayList<String> ingredient);
	public ArrayList<CustomerDto> getCustomer();
	public void DeleteCustomer(String customer_id);
	public void DeleteComment(String comment_id);
}
