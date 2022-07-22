package com.arlania.world.content.collectionlog;

import java.util.ArrayList;

public class CollectionLogItem {
	
	private CollectionLogData data;
	private int counter;
	private ArrayList<NewItem> items;
	
	public CollectionLogItem(CollectionLogData data) {
		this.data = data;
		this.counter = 0;
		this.items = new ArrayList<>();
	}
	
	public void addItem(int item, int amount) {
		for (int i = 0; i < items.size(); ++i) {
			if (items.get(i).getId() == item) {
				items.get(i).setAmount(items.get(i).getAmount() + amount);
				return;
			}
		}
		items.add(new NewItem(item, amount));
	}
	
	public CollectionLogData getData() {
		return this.data;
	}
	
	public ArrayList<NewItem> getItems() {
		return this.items;
	}
	
	public int getCounter() {
		return this.counter;
	}
	
	public void setCounter(int count) {
		this.counter = count;
	}

}
