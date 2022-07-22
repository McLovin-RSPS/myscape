package com.arlania.world.content.bossEvent;

import com.arlania.model.Item;
import com.arlania.model.Position;
import com.arlania.world.World;
import com.arlania.world.content.transportation.TeleportHandler;
import com.arlania.world.entity.impl.npc.NPC;
import com.arlania.world.entity.impl.player.Player;



/**
 * 
 * @author Adam_#6723
 *
 */

public class BossEventHandler {
	
	public static boolean isEventRunning = false;
	
	public void AssignTasks(int taskNumber) {
		BossEventData data = BossEventData.byId.get(taskNumber);
		for(Player players : World.getPlayers()) {
			if(players != null) {
				if(taskNumber == data.getTaskNumber()) {
					players.setBossevent(data);
					players.setCurrentBossTask(data.getNpcid());
					players.setCurrentBossTaskAmount(data.getEndamount());
					//players.sendMessage("You have been Assigned to kill: " + players.getBossevent().getName() + " Amount: " + players.getBossevent().getEndamount());
					//players.sendMessage("First one to complete this task will be rewarded handsomely!");
					isEventRunning = true;
				}
			}
		}
		//World.sendMessage("@red@ Do ::events to teleport to the task!");
	}
		
	public void teleport(Player player) {
		if(player.getBossevent() != null) {
			TeleportHandler.teleportPlayer(player, new Position(player.getBossevent().getX(), player.getBossevent().getY(), player.getBossevent().getZ()), player.getSpellbook().getTeleportType());
		} else {
			player.getPA().sendMessage("You do not have a task currently!");
		}
	}

	public void death(Player player, NPC npc, String NpcName) {
		if(npc.getId() != player.getCurrentBossTask()) {
			//System.err.println("Not killing correct npt");
			return;
		}
		if (player.bossevent == null || player.currentBossTask == -1 || player.currentBossTaskAmount == -1) {
			System.err.println("No Task Applied");
			return;
		}
		player.setCurrentBossTaskAmount(player.getCurrentBossTaskAmount() - 1);
		player.getPA().sendMessage("You currently need to kill " + player.getCurrentBossTaskAmount() + " " + NpcName);

		if (player.getCurrentBossTaskAmount() <= 0) {
			player.getPA().sendMessage("You have came first in the daily boss tasks! check your bank for your reward!");
			finish(player);
			return;
		}
	}
	
	public void resetTasks(Player player) {
		if(player.bossevent != null || player.currentBossTask != -1 || player.currentBossTaskAmount != -1) {
		player.setCurrentBossTask(-1);
		player.setCurrentBossTaskAmount(-1);
		player.setBossevent(null);
		System.err.println("Reset Tasks!");
		System.err.println("Current Status: " + player.getCurrentBossTask() + " " + player.getCurrentBossTaskAmount() + " " + player.getBossevent());
		} else {
			System.err.println("They don't got a task");
		}
	}

	public void finish(Player player) {
		World.sendMessage("<img=13><col=0000ff> " + player.getUsername() + " Has just been rewarded for completing the Daily Boss Event Task");
		player.getInventory().add(new Item(player.getBossevent().getRewards(), player.getBossevent().getAmount()));
		player.getPA().sendMessage("You have Received a reward for completing the daily tasks!");
		for(Player players : World.getPlayers()) {
			if (players != null) {
				resetTasks(players);
			}
		}
		isEventRunning = false;
	}
}
//same