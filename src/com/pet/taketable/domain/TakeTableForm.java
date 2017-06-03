package com.pet.taketable.domain;

import org.springframework.web.multipart.MultipartFile;

public class TakeTableForm {
	private int taketable_id;
	private String member_id;
	private int abata;
	private String title;
	private String content;
	private String okday;
	private String regdate;
	private MultipartFile myfile;
	private String location;
	private String loc;
	private String locx;
	private String locy;
	private String nick;

	public int getTaketable_id() {
		return taketable_id;
	}

	public void setTaketable_id(int taketable_id) {
		this.taketable_id = taketable_id;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public int getAbata() {
		return abata;
	}

	public void setAbata(int abata) {
		this.abata = abata;
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

	public String getOkday() {
		return okday;
	}

	public void setOkday(String okday) {
		this.okday = okday;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public MultipartFile getMyfile() {
		return myfile;
	}

	public void setMyfile(MultipartFile myFile) {
		this.myfile = myFile;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public String getLocx() {
		return locx;
	}

	public void setLocx(String locx) {
		this.locx = locx;
	}

	public String getLocy() {
		return locy;
	}

	public void setLocy(String locy) {
		this.locy = locy;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

}
