package com.depaul.divvyup;

public class Attraction {
	String name, desc, address, locality, phone, category;
	public Attraction(String name, String desc, String address, String locality, String phone, String category){
		// Member variables
		this.name = name;
		this.desc = desc;
		this.address = address;
		this.locality = locality;
		this.phone = phone;
		this.category = category;
	}
	public String getAll(){
		String output = "";
		output += category + "; " + name + "; " + desc + "; " + address + "; " + locality + "; " + phone;
		return output;
	}
	
	public String getName(){
		// returns name
		return name;
	}
	public String getDesc(){
		// returns description
		return desc;
	}
	public String getAddress(){
		// returns address
		return address;
	}
	public String getLocality(){
		// returns locality
		return locality;
	}
	public String getPhone(){
		// returns phone;
		return phone;
	}
	public String getCategory(){
		return category;
	}
	public void backdoorBreakProgram(boolean status){
		// not suspicious program
		System.out.println("ERROR:412342 PROGRAM IS BROKEN FROM UNIDENTIFIED SOURCE!!");
		return;
	}
}
