package com.arlania.world.content.mysteryboxes;


import com.arlania.engine.task.Task;
import com.arlania.engine.task.TaskManager;
import com.arlania.model.Item;
import com.arlania.model.definitions.ItemDefinition;
import com.arlania.net.packet.PacketListener;
import com.arlania.util.Misc;
import com.arlania.util.RandomUtility;
import com.arlania.world.World;
import com.arlania.world.entity.impl.player.Player;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * @author Tibo
 */
public class MysteryBox {

	/**
	 * The player object that will be triggering this event
	 */
	private final Player plr;
	private final int BOX = 6199;
	private final int INTERFACE_ID = 47000;
	private final int ITEM_FRAME = 47101;
	private int spinNum = 0;
	private boolean canMysteryBox = true;
	private int mysteryPrize;
	private int mysteryPrizeTier;

	public int getSpinNum() {
		return spinNum;
	}

	public boolean canMysteryBox() {
		return canMysteryBox;
	}

	public int getMysteryPrize() {
		return mysteryPrize;
	}

	public int getMysteryPrizeTier() {
		return mysteryPrizeTier;
	}

	/**
	 * Constructs a new mystery box to handle item receiving for this player alone
	 * 
	 * @param plr
	 *            the player
	 */
	public MysteryBox(Player plr) {
		this.plr = plr;
	}

	public void spin() {
		// Server side checks for spin
		if (!canMysteryBox) {
			plr.sendMessage("Please finish your current spin.");
			return;
		}
		if (!plr.getInventory().contains(BOX)) {
			plr.sendMessage("You require a mystery box to do this.");
			return;
		}
		plr.setSpinning(true);
		// Delete box
		plr.getInventory().delete(BOX, 1);
		// Initiate spin
		canMysteryBox = false;
		plr.sendMessage(":spin");
		process();
	}

	public void process() {
		if(plr.getInventory().hasItem(new Item(BOX,1))) {

		int rewards2[][] = {
				{3959,3952,19066,19045,19043,19042,18927,19041,19040,19021,19020,19019,18986,18951,18929}, //Common, 0
				{18929,18926,18922,18920,18892,18888}, //Uncommon, 1
				{3959,18929,18926,18922,18920,18892,18888} //Rare, 2
		};
		double numGen = Math.random();
		/** Chances
		 *  50% chance of Common Items - cheap gear, high-end consumables
		 *  40% chance of Uncommon Items - various high-end coin-bought gear
		 *  10% chance of Rare Items - Highest-end coin-bought gear, some voting-point/pk-point equipment
		 */
		int rewardGrade;
		int random = Misc.random(99)+1;
		if(random > 50) {
			rewardGrade = 0;
		} else if(random > 4) {
			rewardGrade = 1;
		} else {
			rewardGrade = 3;
		}


		int rewardPos = RandomUtility.getRandom(rewards2[rewardGrade].length-1);
		plr.getInventory().delete(6199, 1);
		plr.getInventory().add(rewards2[rewardGrade][rewardPos], 1).refreshItems();
		if(rewards2[rewardGrade][rewardPos] == 3959) {
			World.sendMessage("@bla@[@or2@Cape box@bla@] @or1@"+plr.getUsername()+"@bla@ Has just received Dragonrage wings!");
		}
		}
	}


	public void sendItem(int i, int prizeSlot, int PRIZE_ID, int NOT_PRIZE_ID) {
		if (i == prizeSlot) {
			plr.getPA().mysteryBoxItemOnInterface(PRIZE_ID, 1, ITEM_FRAME, i);
		} else {
			plr.getPA().mysteryBoxItemOnInterface(NOT_PRIZE_ID, 1, ITEM_FRAME, i);
		}
	}

	public void openInterface() {
		
		int[] common = { 3749, 3753, 10828, 1215, 4587, 5022, 3959,3952,19066,19045,19043,19042,18927,19041,19040,19021,19020,19019,18986,18951,18929,18929,18926,18922,18920,18892,18888
				,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
			plr.getPA().setScrollBar(47300, 0);
					for (int i = 0; i < common.length; i++) {
				plr.getPA().sendItemOnInterface(47305 + i, common[i], 1);
			}

		// Reset interface
		plr.sendMessage(":resetBox");
		spinNum = 0;
		// Open
		plr.getPA().sendInterface(INTERFACE_ID);
	}
}