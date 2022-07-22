package com.arlania.world.content.collectionlog;

import java.util.HashMap;

public enum CollectionLogPage {

	BOSSES(-2533),
	RAIDS(-2532),
	CLUES(-2531),
	MINIGAMES(-2530),
	OTHER(-2529);
	
	private final int button;
	private CollectionLogPage(int button) {
		this.button = button;
	}
	
	private static HashMap<Integer, CollectionLogPage> pages;
	
	public static CollectionLogPage forButton(int button) {
		return pages.get(button);
	}
	
	static {
		pages = new HashMap<>();
		for (CollectionLogPage page : values()) {
			pages.put(page.getButton(), page);
		}
	}
	
	
	public int getButton() {
		return this.button;
	}
	
}
