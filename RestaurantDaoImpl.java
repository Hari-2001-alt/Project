package com.controller.DaoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.controller.Dao.RestaurantDao;
import com.controller.model.Restaurant;


public  class RestaurantDaoImpl implements RestaurantDao {
	
	 private static final String URL = "jdbc:mysql://localhost:3306/foodapp";
	    private static final String USER = "root";
	    private static final String PASSWORD = "root";

	    static {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	        } 
	        catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	    }

	    public void addRestaurant(Restaurant restaurant) {
	        String sql = "INSERT INTO restaurant (rname, cuisineType, address, ratings, isActive, imagePath) VALUES ( ?, ?, ?, ?, ?, ?)";
	        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
	             PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setString(1, restaurant.getRName());
	            statement.setString(2, restaurant.getCuisineType());
	            
	            statement.setString(3, restaurant.getAddress());
	            statement.setFloat(4, restaurant.getRattings());
	            statement.setBoolean(5, restaurant.isActive());
	            statement.setString(6, restaurant.getImagePath());
	            statement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    @Override
	    public Restaurant getRestaurantById(int rid) {
	        String sql = "SELECT * FROM restaurant WHERE rid = ?";
	        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
	             PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setInt(1, rid);
	            ResultSet resultSet = statement.executeQuery();
	            if (resultSet.next()) {
	                return new Restaurant(
	                		resultSet.getInt("rid"),
	                        resultSet.getString("rname"),
	                        resultSet.getString("cuisineType"),
	                        resultSet.getString("address"),
	                        resultSet.getFloat("rattings"),
	                        resultSet.getBoolean("isActive"),
	                        resultSet.getString("imagePath")
	                );
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

	    @Override
	    public void updateRestaurant(Restaurant restaurant) {
	        String sql = "UPDATE restaurant SET rname = ?, cuisineType = ?, deliveryTime = ?, address = ?, rattings = ?, isActive = ?, imagePath = ? WHERE rid = ?";
	        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
	             PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setString(1, restaurant.getRName());
	            statement.setString(2, restaurant.getCuisineType());
	            
	            statement.setString(4, restaurant.getAddress());
	            statement.setFloat(5, restaurant.getRattings());
	            statement.setBoolean(6, restaurant.isActive());
	            statement.setString(7, restaurant.getImagePath());
	            
	            statement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    @Override
	    public void deleteRestaurant(int rid) {
	        String sql = "DELETE FROM restaurant WHERE rid = ?";
	        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
	             PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setInt(1, rid);
	            statement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public List<Restaurant> getAllRestaurants() {
	        List<Restaurant> restaurants = new ArrayList<>();
	        String sql = "SELECT * FROM restaurant";
	        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
	             Statement statement = connection.createStatement();
	             ResultSet resultSet = statement.executeQuery(sql)) {
	            while (resultSet.next()) {
	                restaurants.add(new Restaurant(
	                		resultSet.getInt("rid"),
	                        resultSet.getString("rname"),
	                        resultSet.getString("cuisineType"),
	                        resultSet.getString("address"),
	                        resultSet.getFloat("rattings"),
	                        resultSet.getBoolean("isActive"),
	                        resultSet.getString("imagePath")
	                ));
	            }
	        } 
	        catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return restaurants;
	    }
	
}

