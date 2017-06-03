package com.pet.reply.model.domain;

public class Reply {
	private int reply_id;
	private int member_id;
	private int regist_id;
	private String content;

	public int getReply_id() {
		return reply_id;
	}

	public void setReply_id(int reply_id) {
		this.reply_id = reply_id;
	}

	public int getMember_id() {
		return member_id;
	}

	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}

	public int getRegist_id() {
		return regist_id;
	}

	public void setRegist_id(int regist_id) {
		this.regist_id = regist_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
