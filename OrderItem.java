package com.controller.model;

public class OrderItem {
    private int orderItemId;
    private int orderId;
    private int mid;
    private int quantity;
    private int itemTotal;

    // Constructor without orderItemId (for inserting new items)
    public OrderItem(int orderId, int mid, int quantity, int itemTotal) {
        this.orderId = orderId;
        this.mid = mid;
        this.quantity = quantity;
        this.itemTotal = itemTotal;
    }

    // Constructor with orderItemId (for retrieving existing items)
    public OrderItem(int orderItemId, int orderId, int mid, int quantity, int itemTotal) {
        this.orderItemId = orderItemId;
        this.orderId = orderId;
        this.mid = mid;
        this.quantity = quantity;
        this.itemTotal = itemTotal;
    }

    // Getters and setters
    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(int itemTotal) {
        this.itemTotal = itemTotal;
    }

    @Override
    public String toString() {
        return orderItemId + " " + orderId + " " + mid + " " + quantity + " " + itemTotal;
    }
}

