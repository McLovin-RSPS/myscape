package com.arlania.world.content.newminigames.restriction.impl;

import com.arlania.world.content.newminigames.restriction.Restriction;
import com.arlania.world.entity.impl.player.Player;

public class TotalLevelRestriction extends Restriction {

	private int totalLevelReq;

	public TotalLevelRestriction(int totalLevelReq) {
		this.totalLevelReq = totalLevelReq;
	}

	@Override
	public boolean check(Player player) {
		if (player.getSkillManager().getTotalLevel() < this.totalLevelReq) {
			player.sendMessage("You need a total level of " + this.totalLevelReq + " to join this minigame.");
			return false;
		}
		return true;
	}

}
