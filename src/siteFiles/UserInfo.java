package siteFiles;

import java.util.Calendar;


public class UserInfo {
	private int id;
	private String name;
	private String confirmNumber;
	private Calendar dateOrdered;
	private String address;
	
	public UserInfo(String name, String address, String confirmNumber, Calendar dateOrdered) {
		this.name=name;
		this.confirmNumber=confirmNumber;
		this.dateOrdered=dateOrdered;
		this.address=address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getConfirm() {
		return confirmNumber;
	}

	public void setConfirm(String confirm) {
		this.confirmNumber = confirm;
	}

	public Calendar getDate() {
		return dateOrdered;
	}

	public void setDate(Calendar date)
	{
		this.dateOrdered=date;
	}
	
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String adr)
	{
		this.address=adr;
	}
}
