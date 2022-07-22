package com.arlania.world.content.newminigames.restriction.impl;

import java.util.ArrayList;

import com.arlania.model.definitions.NpcDefinition;
import com.arlania.world.content.newminigames.restriction.Restriction;
import com.arlania.world.entity.impl.player.Player;

public class PetRestriction extends Restriction {

	private ArrayList<Integer> allowedPets;

	public PetRestriction() {
		this.allowedPets = new ArrayList<Integer>();
	}

	public void addAllowedPet(int id) {
		this.allowedPets.add(id);
	}

	public void addAllowedPets(int... ids) {
		for (int id : ids)
			this.allowedPets.add(id);
	}

	@Override
	public boolean check(Player player) {
		if (player.getSummoning().getFamiliar() == null)
			return true;
		int petId = player.getSummoning().getFamiliar().getSummonNpc().getId();
		if (this.allowedPets.contains(petId)) {
			return true;
		} else {
			String petName = NpcDefinition.forId(petId).getName();
			player.sendMessage("You cannot bring a " + petName + " familiar into this minigame.");
		}
		return false;
	}

}
