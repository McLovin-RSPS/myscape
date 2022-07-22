package com.arlania.world.content.newminigames.restriction.impl;

import java.util.HashMap;

import com.arlania.model.Skill;
import com.arlania.world.content.newminigames.restriction.Restriction;
import com.arlania.world.entity.impl.player.Player;

public class SkillLevelRestriction extends Restriction {

	private HashMap<Skill, Integer> skillLevelReqs;

	public SkillLevelRestriction() {
		this.skillLevelReqs = new HashMap<Skill, Integer>();
	}

	public void addRestriction(Skill skill, int levelReq) {
		this.skillLevelReqs.put(skill, levelReq);
	}

	@Override
	public boolean check(Player player) {
		boolean hasReq = true;
		for (Skill skill : skillLevelReqs.keySet()) {
			if (player.getSkillManager().getCurrentLevel(skill) < skillLevelReqs.get(skill)) {
				player.sendMessage("You need a " + skill.getFormatName() + " level of " + skillLevelReqs.get(skill)
						+ " to join this minigame.");
				hasReq = false;
			}
		}
		return hasReq;
	}

}
