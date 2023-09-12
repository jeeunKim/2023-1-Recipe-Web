package com.sw.dto;
import java.sql.Timestamp;

public class CommentDto {
	int comment_id;
	public CommentDto(int comment_id, String owner_id, String comment_content, Timestamp date, int recipe_id) {
		super();
		this.comment_id = comment_id;
		this.owner_id = owner_id;
		this.comment_content = comment_content;
		this.date = date;
		this.recipe_id = recipe_id;
	}
	public int getComment_id() {
		return comment_id;
	}
	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}
	public String getOwner_id() {
		return owner_id;
	}
	public void setOwner_id(String owner_id) {
		this.owner_id = owner_id;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public int getRecipe_id() {
		return recipe_id;
	}
	public void setRecipe_id(int recipe_id) {
		this.recipe_id = recipe_id;
	}
	String owner_id;
	String comment_content;
	Timestamp date;
	int recipe_id;
	
}
