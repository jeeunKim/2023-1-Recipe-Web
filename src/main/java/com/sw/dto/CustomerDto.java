package com.sw.dto;

public class CustomerDto {
	
	public CustomerDto(String id, String pw, String name, String nickname, int pnum, int subscriber) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.nickname = nickname;
		this.pnum = pnum;
		this.subscriber = subscriber;
	}
	String id;
	String pw;
	String name;
	String nickname;
	int pnum;
	int subscriber;
	
	public CustomerDto() {}
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getPhone() {
		return pnum;
	}
	public void setPhone(int pnum) {
		this.pnum = pnum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public int getSubscriber() {
		return subscriber;
	}
	public void setSubscriber(int s) {
		this.subscriber = s;
	}

}
