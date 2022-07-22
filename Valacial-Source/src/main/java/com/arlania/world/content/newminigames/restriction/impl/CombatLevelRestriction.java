package com.arlania.world.content.newminigames.restriction.impl;

import com.arlania.world.content.newminigames.restriction.Restriction;
import com.arlania.world.entity.impl.player.Player;

public class CombatLevelRestriction extends Restriction {

	private int combatLevelReq;

	public CombatLevelRestriction(int combatLevelReq) {
		this.combatLevelReq = combatLevelReq;
	}

	@Override
	public boolean check(Player player) {
		if (player.getSkillManager().getCombatLevel() < this.combatLevelReq) {
			player.sendMessage("You need a combat level of " + this.combatLevelReq + " to join this minigame.");
			return false;
		}
		return true;
	}

}
