package com.arlania.world.content.skill.impl.thieving;

import com.arlania.model.Animation;
import com.arlania.model.Item;
import com.arlania.model.PlayerRights;
import com.arlania.model.Position;
import com.arlania.model.Skill;
import com.arlania.model.Locations.Location;
import com.arlania.model.definitions.ItemDefinition;
import com.arlania.util.RandomUtility;
import com.arlania.world.content.Achievements;
import com.arlania.world.content.Achievements.AchievementData;
import com.arlania.world.content.transportation.TeleportHandler;
import com.arlania.world.entity.impl.player.Player;

public class Stalls {

	public static void stealFromStall(Player player, int lvlreq, int xp, int reward, String message) {
		if(player.getInventory().getFreeSlots() < 1) {
			player.getPacketSender().sendMessage("You need some more inventory space to do this.");
			return;
		}
		if (player.getCombatBuilder().isBeingAttacked()) {
			player.getPacketSender().sendMessage("You must wait a few seconds after being out of combat before doing this.");
			return;
		}
		if(!player.getClickDelay().elapsed(2000))
			return;
		if(player.getSkillManager().getMaxLevel(Skill.THIEVING) < lvlreq) {
			player.getPacketSender().sendMessage("You need a Thieving level of at least " + lvlreq + " to steal from this stall.");
			return;
		}
		if (player.getLocation() == Location.DONATOR_ZONE) {
		if (RandomUtility.RANDOM.nextInt(35) == 5) {
			TeleportHandler.teleportPlayer(player, new Position(2338, 9800), player.getSpellbook().getTeleportType());
			player.getPacketSender().sendMessage("You were caught stealing and teleported away from the stall!");
			return;
		} 
	}
		player.performAnimation(new Animation(881));
		player.getPacketSender().sendMessage(message);
		player.getPacketSender().sendInterfaceRemoval();
		player.getSkillManager().addExperience(Skill.THIEVING, xp);
		player.getClickDelay().reset();
		if (player.getRights() == PlayerRights.PLAYER) {
			player.getInventory().add(5022, 5);

		}
		if (player.getRights() == PlayerRights.DONATOR) {
			player.getInventory().add(5022, 30);

		}
		if (player.getRights() == PlayerRights.SUPER_DONATOR || player.getRights() == PlayerRights.SUPPORT) {
			player.getInventory().add(5022, 50);
		}
		if (player.getRights() == PlayerRights.EXTREME_DONATOR || player.getRights() == PlayerRights.MODERATOR) {
			player.getInventory().add(5022, 75);
		}
		if (player.getRights() == PlayerRights.SPONSOR_DONATOR || player.getRights() == PlayerRights.ADMINISTRATOR) {
			player.getInventory().add(5022, 100);
		}
		if (player.getRights() == PlayerRights.EXECUTIVE_DONATOR) {
			player.getInventory().add(5022, 150);
		}
		if (player.getRights() == PlayerRights.DIVINE_DONATOR) {
			player.getInventory().add(5022, 300);
		}
		
		if (player.getLocation() == Location.DONATOR_ZONE) {
			Item item = new Item(reward);
			int value = item.getDefinition().getValue();		
			player.getInventory().add(5022, value);

		} else {
			player.getInventory().add(reward, 1);

		}
		
		player.getSkillManager().stopSkilling();
		if(reward == 15009)
		player.getAchievementTracker().progress(com.arlania.world.content.achievements.AchievementData.STEAL_A_RING, 1);

		else if(reward == 11998) {
			player.getAchievementTracker().progress(com.arlania.world.content.achievements.AchievementData.STEAL_140_SCIMITARS, 1);
			player.getAchievementTracker().progress(com.arlania.world.content.achievements.AchievementData.STEAL_5000_SCIMITARS, 1);

		}
	}
	

}
