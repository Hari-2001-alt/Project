package com.tap;

import java.util.HashMap;
import java.util.Map;

public class Cart {
	private Map<Integer,CartItem> map;
	
	public Cart() {
		this.map=new HashMap<>();	
	}
	
	public void addItem(CartItem item) {
		int itemId=item.getItemId();
		if(map.containsKey(itemId)) {
			map.get(itemId).setQuantity(map.get(itemId).getQuantity()+item.getQuantity()); //to increase the quantity from the user point of view
			
		}
		else {
			map.put(itemId,item);
		}
	}
	
	public void removeItem(int itemId) {
		map.remove(itemId);
	}
	
	public void clearItem() {
		map.clear();
	}
	
	public Map<Integer,CartItem> getItem() {
		return map;
		
	}
	
	public void updateItem(int itemId,int quantity) {
	
		if(map.containsKey(itemId)) {
			if(quantity<=0) {
				map.remove(itemId);	
			}
			else {
				map.get(itemId).setQuantity(quantity);
			}
		
		}
		
	}
	
	

	public int getRestaurantId() {
		if(map.isEmpty()) {
			return 0;
		}
		return map.values().iterator().next().getRid();
	}

}
