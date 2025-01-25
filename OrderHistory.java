package com.controller.model;

public class OrderHistory {
	private int orderHistoryId;
	private int orderId;
	private int userId;
	private float totalAmt;
	private String status;
	
	    // Constructors
	    public OrderHistory() {
	    }

	    public OrderHistory( int orderId, int userId, float totalAmt, String status) {
	        
	        this.orderId = orderId;
	        this.userId = userId;
	        this.totalAmt = totalAmt;
	        this.status = status;
	    }
	    public OrderHistory(int orderHistoryId, int orderId, int userId, float totalAmt, String status) {
	        this.orderHistoryId = orderHistoryId;
	        this.orderId = orderId;
	        this.userId = userId;
	        this.totalAmt = totalAmt;
	        this.status = status;
	    }

	    public OrderHistory(int orderId, int userId, float totalAmt) {
			// TODO Auto-generated constructor stub
	    	this.orderId = orderId;
	        this.userId = userId;
	        this.totalAmt = totalAmt;
		}

		// Getters and Setters
	    public int getOrderHistoryId() {
	        return orderHistoryId;
	    }

	    public void setOrderHistoryId(int orderHistoryId) {
	        this.orderHistoryId = orderHistoryId;
	    }

	    public int getOrderId() {
	        return orderId;
	    }

	    public void setOrderId(int orderId) {
	        this.orderId = orderId;
	    }

	    public int getUserId() {
	        return userId;
	    }

	    public void setUserId(int userId) {
	        this.userId = userId;
	    }

	    public float getTotalAmt() {
	        return totalAmt;
	    }

	    public void setTotalAmt(float totalAmt) {
	        this.totalAmt = totalAmt;
	    }

	    public String getStatus() {
	        return status;
	    }

	    public void setStatus(String status) {
	        this.status = status;
	    }
	

}
