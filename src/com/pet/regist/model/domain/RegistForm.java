package com.pet.regist.model.domain;

import org.springframework.web.multipart.MultipartFile;

public class RegistForm {
	private int regist_id;
	private String title;
	private String content;
	private String regdate;
	private MultipartFile myfile;
	private int type;
	private int member_id;
	private int pet_id;

	public int getRegist_id() {
		return regist_id;
	}

	public void setRegist_id(int regist_id) {
		this.regist_id = regist_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getMember_id() {
		return member_id;
	}

	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}

	public MultipartFile getMyfile() {
		return myfile;
	}

	public void setMyfile(MultipartFile myfile) {
		this.myfile = myfile;
	}

	public int getPet_id() {
		return pet_id;
	}

	public void setPet_id(int pet_id) {
		this.pet_id = pet_id;
	}

}
