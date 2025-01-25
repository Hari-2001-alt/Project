package com.controller.Dao;

import java.util.List;

import com.controller.model.OrderHistory;



public interface OrderHistoryDao {
	
    boolean addOrderHistory(OrderHistory orderHistory);
    OrderHistory getOrderHistoryById(int id);
    List<OrderHistory> getAllOrderHistories();
    void updateOrderHistory(OrderHistory orderHistory);
    void deleteOrderHistory(int id);
}
