package com.arlania.world.content.newminigames.restriction.impl;

import java.util.ArrayList;

import com.arlania.model.Item;
import com.arlania.model.definitions.ItemDefinition;
import com.arlania.world.content.newminigames.restriction.Restriction;
import com.arlania.world.entity.impl.player.Player;

public class ItemRestriction extends Restriction {

	private ArrayList<Integer> allowedItems;

	public ItemRestriction() {
		this.allowedItems = new ArrayList<Integer>();
	}

	public void addAllowedItem(int itemId) {
		this.allowedItems.add(itemId);
	}

	@Override
	public boolean check(Player player) {
		boolean hasLegalItems = true;
		for (Item item : player.getInventory().getItems()) {
			if (item.getId() == -1)
				continue;
			if (!this.allowedItems.contains(item.getId())) {
				player.sendMessage("You are not allowed to bring " + ItemDefinition.forId(item.getId()).getName()
						+ " into this minigame.");
				hasLegalItems = false;
			}
		}
		for (Item item : player.getEquipment().getItems()) {
			if (item.getId() == -1)
				continue;
			if (!this.allowedItems.contains(item.getId())) {
				player.sendMessage("You are not allowed to bring " + ItemDefinition.forId(item.getId()).getName()
						+ " into this minigame.");
				hasLegalItems = false;
			}
		}
		return hasLegalItems;
	}

}
