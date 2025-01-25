package com.controller.Dao;


import java.util.List;

import com.controller.model.Menu; // Correct import

public interface MenuDao {

    void addMenu(Menu newMenu);
    Menu getMenuById(int mid);
    void updateMenu(Menu updatedMenu);
    void deleteMenu(int mid);
    
    List<Menu> getAllMenusByRestaurant(int rid);
}


