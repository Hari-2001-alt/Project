package com.tap;

public class CartItem {
	private int itemId;
	private int rid;
	private String name;
	private int price;
	private int quantity;
	
	public CartItem() {}
	
	public CartItem(int itemId, int rid, String name, int price, int quantity) {
		super();
		this.itemId = itemId;
		this.rid = rid;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
	

}
