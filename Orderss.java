package com.controller.model;

import java.time.LocalDateTime;

public class Orderss {
    private int orderId;
    private int userId;
    private int rid;
    private LocalDateTime ordDate;
    private float totalAmt;
    private String status;
    private String paymentMode;

    public Orderss(int userId, int rid, float totalAmt, String status, String paymentMode) {
        this.userId = userId;
        this.rid = rid;
        this.totalAmt = totalAmt;
        this.status = status;
        this.paymentMode = paymentMode;
        this.ordDate = LocalDateTime.now();
    }

    // Getters and setters
    public int getOrderId() { 
    	return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public int getRid() { return rid; }
    public void setRid(int rid) { this.rid = rid; }
    public LocalDateTime getOrdDate() { return ordDate; }
    public void setOrdDate(LocalDateTime ordDate) { this.ordDate = ordDate; }
    public float getTotalAmt() { return totalAmt; }
    public void setTotalAmt(float totalAmt) { this.totalAmt = totalAmt; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getPaymentMode() { return paymentMode; }
    public void setPaymentMode(String paymentMode) { this.paymentMode = paymentMode; }
}

