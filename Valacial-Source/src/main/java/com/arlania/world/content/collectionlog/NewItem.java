package com.arlania.world.content.collectionlog;

public class NewItem {
	
	private int id;
	private int amount;
	
	public NewItem(int item, int amount) {
		this.id = item;
		this.amount = amount;
	}
	
	public int getId() {
		return this.id;
	}
	
	public int getAmount() {
		return this.amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}

}
