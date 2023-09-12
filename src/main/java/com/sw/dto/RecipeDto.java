package com.sw.dto;



public class RecipeDto {
   public RecipeDto() {}
   public RecipeDto(int recipe_id, String recipe_name, int level,int cooking_time,String img,
		   String recipe_content, String category,String owner_id, int heart) {
		
	    this.recipe_id = recipe_id;
		this.recipe_name = recipe_name;
		this.level = level;
		this.cooking_time = cooking_time;
		this.recipe_content = recipe_content;
		this.category = category;
		this.owner_id = owner_id;
		this.img = img;
		this.heart = heart;
   }
   
   public int getRecipe_id() {
	return recipe_id;
}
public void setRecipe_id(int recipe_id) {
	this.recipe_id = recipe_id;
}
public String getRecipe_name() {
	return recipe_name;
}
public void setRecipe_name(String recipe_name) {
	this.recipe_name = recipe_name;
}
public int getLevel() {
	return level;
}
public void setLevel(int level) {
	this.level = level;
}
public int getCooking_time() {
	return cooking_time;
}
public void setCooking_time(int cooking_time) {
	this.cooking_time = cooking_time;
}
public String getImg() {
	return img;
}
public void setImg(String img) {
	this.img = img;
}
public String getRecipe_content() {
	return recipe_content;
}
public void setRecipe_content(String recipe_content) {
	this.recipe_content = recipe_content;
}
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}
public String getOwner_id() {
	return owner_id;
}
public void setOwner_id(String owner_id) {
	this.owner_id = owner_id;
}
public int getHeart() {
	return heart;
}
public void setHeart(int heart) {
	this.heart = heart;
}


	public int recipe_id;
   public String recipe_name;
   public int level;
   public int cooking_time;
   public String img;
   public String recipe_content;
   public String category;
   public String owner_id; 
   public int heart;
 
}