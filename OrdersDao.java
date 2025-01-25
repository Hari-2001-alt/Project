package com.controller.Dao;

import java.sql.SQLException;
import java.util.List;

import com.controller.model.Orderss;

public interface OrdersDao {
    int addOrder(Orderss order) throws SQLException;
    Orderss getOrderById(int orderId) throws SQLException;
    List<Orderss> getOrdersByUserId(int userId);
    List<Orderss> getRestaurantById(int rId);
    List<Orderss> getAllOrders() throws SQLException;
    void updateOrder(Orderss order) throws SQLException;
    void deleteOrder(int orderId) throws SQLException;
}
