package com.controller.Dao;

import java.util.ArrayList;

import com.controller.model.User;

public interface UserDao {
	int insert(User u)throws Exception;
    User getUserById(int userId) throws Exception; 
	ArrayList<User> fetchAll();
	User fetchOne(int userid);
	int update(int userid,String password);
	int delete(int userid);
	User fetchUserByEmail(String email);
	
	
}
