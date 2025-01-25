package com.controller.model;

public class Restaurant {
	
	private int rid;
	private String rname;
	private String cuisineType;
	private String address;
	private float rattings;
	private boolean isActive;
	private String imagePath;
	
	    
	    public Restaurant() {}

	    
	    public Restaurant( int rid,String rname, String cuisineType,  String address, float rattings, boolean isActive, String imagePath) {
	       
	    	this.rid=rid;
	        this.rname = rname;
	        this.cuisineType = cuisineType;
	        this.address = address;
	        this.rattings = rattings;
	        this.isActive = isActive;
	        this.imagePath = imagePath;
	    }

	    
	    public int getRId() {
	    	return rid;
	    }
	    public void setRId() {
	    	this.rid=rid;
	    }
	    

	    public String getRName() {
	        return rname;
	    }

	    public void setRName(String rname) {
	        this.rname = rname;
	    }

	    public String getCuisineType() {
	        return cuisineType;
	    }

	    public void setCuisineType(String cuisineType) {
	        this.cuisineType = cuisineType;
	    }

	    

	    public String getAddress() {
	        return address;
	    }

	    public void setAddress(String address) {
	        this.address = address;
	    }

	    public float getRattings() {
	        return rattings;
	    }

	    public void setRatings(float rattings) {
	        this.rattings = rattings;
	    }

	    public boolean isActive() {
	        return isActive;
	    }

	    public void setActive(boolean isActive) {
	        this.isActive = isActive;
	    }

	    public String getImagePath() {
	        return imagePath;
	    }

	    public void setImagePath(String imagePath) {
	        this.imagePath = imagePath;
	    }
	}
