package com.controller.Dao;

import java.util.List;

import com.controller.model.Restaurant;

public interface RestaurantDao {
	void addRestaurant(Restaurant restaurant);
    Restaurant getRestaurantById(int rid);
    void updateRestaurant(Restaurant restaurant);
    void deleteRestaurant(int rid);
     static List<Restaurant> getAllRestaurants() {
		// TODO Auto-generated method stub
		return null;
	}
}
