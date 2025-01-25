package com.controller.model;

public class Menu {
	private int mid;
	private int rid;
	private String mname;
	private String description;
	private int price;
	private boolean isAvailable;
	private String imagePath;
	

	    // Constructors
	    public Menu() {
	    	
	    }

	    public Menu(int mid, int rid, String mname, String description, int price, boolean isAvailable, String imagePath) {
	        this.mid = mid;
	        this.rid = rid;
	        this.mname = mname;
	        this.description = description;
	        this.price = price;
	        this.isAvailable = isAvailable;
	        this.imagePath = imagePath;
	    }

	    // Getters and Setters
	    public int getMid() {
	        return mid;
	    }

	    public void setMid(int mid) {
	        this.mid = mid;
	    }

	    public int getRid() {
	        return rid;
	    }

	    public void setRid(int rid) {
	        this.rid = rid;
	    }

	    public String getMname() {
	        return mname;
	    }

	    public void setMname(String mname) {
	        this.mname = mname;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

	    public int getPrice() {
	        return price;
	    }

	    public void setPrice(int price) {
	        this.price = price;
	    }

	    public boolean getIsAvailable() {
	        return isAvailable;
	    }

	    public void setAvailable(boolean isAvailable) {
	        this.isAvailable = isAvailable;
	    }

	    public String getImagePath() {
	        return imagePath;
	    }

	    public void setImagePath(String imagePath) {
	        this.imagePath = imagePath;
	    }


}
