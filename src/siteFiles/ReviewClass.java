package siteFiles;

import java.util.Calendar;


public class ReviewClass {
	private int id;
	private String userName;
	private String itemName;
	private String type;
	private String brand;
	private String age;
	private String gender;
	private String occupation;
	private String comments;
	private String rating;
	
	
	public ReviewClass(String userName, String itemName, String type, String brand, String age, String gender, String occupation, String comments, String rating) {
		this.userName = userName;
		this.itemName=itemName;
		this.type=type;
		this.brand=brand;
		this.age=age;
		this.gender=gender;
		this.occupation=occupation;
		this.comments=comments;
		this.rating=rating;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}
	public String getUserName() {
		return userName;
	}
	
	


	public String getType() {
		return type;
	}

	public String getBrand() {
		return brand;
	}

	
	public String getAge() {
		return age;
	}
	
	public String getGender() {
		return gender;
	}
	public String getOccupation() {
		return occupation;
	}
	public String getComments() {
		return comments;
	}
	public String getRating() {
		return rating;
	}

	
}
