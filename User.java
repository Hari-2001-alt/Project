package com.controller.model;

public class User {
	private int userId;
    private String email;
    private String password;
    private String username;
    private String address;
   

    // Default constructor
    public User() {}

    // Parameterized constructor
    

    public User(String username, String password, String email, String address) {
		//super();
		this.email = email;
		this.password = password;
		this.username = username;
		this.address = address;
	}

	public User(int userId, String email, String password, String username, String address) {
		super();
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.username = username;
		this.address = address;
	}

	// Getters and Setters
    
   public int getUserId() {
       return userId;
    }

  public void setUserId(int userId) {
       this.userId = userId;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

	@Override
	public String toString() {
		return  userId + " " + email + " " + password + " " + username
				+ " " + address ;
	}

    
}
