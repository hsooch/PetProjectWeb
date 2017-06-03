package com.pet.petowner.domain;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by KwonHyungsub on 2016-06-08.
 */
public class PetOwnerForm {
	/*
	 * ImageView edit_photo; EditText edit_name,
	 * edit_whatKind,edit_registNumber, edit_address,edit_contactPoint; CheckBox
	 * edit_isOperation, edit_isRegularCheck; RadioButton edit_boy, edit_girl;
	 * RadioGroup edit_sex;
	 */

	private int petOwner_id;
	private MultipartFile myFile;
	private String name;
	private String whatKind;
	private String registNumber;
	private String address;
	private String contactPoint;
	private String isOperation;
	private String isRegularCheck;
	private String boy;
	private String girl;
	private String sex;
	private String member_id;
	private String email;
	private String loc;
	private String locx;
	private String locy;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBoy() {
		return boy;
	}

	public void setBoy(String boy) {
		this.boy = boy;
	}

	public String getContactPoint() {
		return contactPoint;
	}

	public void setContactPoint(String contactPoint) {
		this.contactPoint = contactPoint;
	}

	public String getGirl() {
		return girl;
	}

	public void setGirl(String girl) {
		this.girl = girl;
	}

	public String getIsOperation() {
		return isOperation;
	}

	public void setIsOperation(String isOperation) {
		this.isOperation = isOperation;
	}

	public String getIsRegularCheck() {
		return isRegularCheck;
	}

	public void setIsRegularCheck(String isRegularCheck) {
		this.isRegularCheck = isRegularCheck;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPetOwner_id() {
		return petOwner_id;
	}

	public void setPetOwner_id(int petOwner_id) {
		this.petOwner_id = petOwner_id;
	}

	public MultipartFile getMyFile() {
		return myFile;
	}

	public void setMyFile(MultipartFile myFile) {
		this.myFile = myFile;
	}

	public String getRegistNumber() {
		return registNumber;
	}

	public void setRegistNumber(String registNumber) {
		this.registNumber = registNumber;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getWhatKind() {
		return whatKind;
	}

	public void setWhatKind(String whatKind) {
		this.whatKind = whatKind;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

}
