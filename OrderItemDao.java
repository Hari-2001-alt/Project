package com.controller.Dao;

import java.util.List;

import com.controller.model.OrderItem;

public interface OrderItemDao {
    // Insert a new OrderItem
   boolean insertOrderItem(OrderItem orderItem);

    // Retrieve an OrderItem by its ID
    OrderItem getOrderItemById(int orderItemId);

    // Retrieve all OrderItems
    List<OrderItem> getAllOrderItems();
    
    boolean insertOrderItem1(OrderItem orderItem);

    // Update an existing OrderItem
    boolean updateOrderItem(OrderItem orderItem);

    // Delete an OrderItem by its ID
    boolean deleteOrderItem(int orderItemId);

	boolean addOrderItem(OrderItem orderItem);
}

