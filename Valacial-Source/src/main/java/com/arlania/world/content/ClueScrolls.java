package com.arlania.world.content;

import com.arlania.model.Item;
import com.arlania.util.Misc;
import com.arlania.util.RandomUtility;
import com.arlania.world.World;
import com.arlania.world.entity.impl.player.Player;

public class ClueScrolls {

	public static int CluesCompleted;
	public static String currentHint;

	public static final int[] ACTIVE_CLUES = { 2677, 2678, 2679, 2680, 2681, 2682, 2683, 2684, 2685 };
	// digging locations or show reward on reading clue??
	// to-do change name of clue scrolls in item def
	
	private static final Item[][] BASIC_STACKS =  {//Always get 1 of the following
//			{new Item(21002, 1)},//Coal
//			{new Item(21003, 1)},//Copper Ore
//			{new Item(21004, 1)},//Tin Ore
//			{new Item(21030, 1)},//Iron Ore
//			{new Item(21031, 1)},//Mithril Ore
//			{new Item(21032, 1)},//Addy Ore
//			{new Item(21033, 1)},//Rune Ore
//			{new Item(7102, 25)},//Bronze Bar
//			{new Item(7116, 25)},//Iron Bar
//			{new Item(7114, 32)},//Steel Bar
//			{new Item(7112, 25)},//Mithril Bar
//			{new Item(7124, 12)},//Adamant Bar
//			{new Item(1063, 1)},//Iron Bar
//			{new Item(1153, 1)},//Steel Bar
//			{new Item(21012, 1)},//Mithril Bar
//			{new Item(21013, 1)},//Adamant Bar
	};
	
	private static final Item[][] LOW_LEVEL_REWARD =  {//Always get 2 of the following
//			{new Item(21002, 1)},//Coal
//			{new Item(21003, 1)},//Copper Ore
//			{new Item(21004, 1)},//Tin Ore
//			{new Item(21030, 1)},//Iron Ore
//			{new Item(21031, 1)},//Mithril Ore
//			{new Item(21032, 1)},//Addy Ore
//			{new Item(21033, 1)},//Rune Ore
//			{new Item(7102, 25)},//Bronze Bar
//			{new Item(7116, 25)},//Iron Bar
//			{new Item(7114, 32)},//Steel Bar
//			{new Item(7112, 25)},//Mithril Bar
//			{new Item(7124, 12)},//Adamant Bar

	};
	
	private static final Item[][] MEDIUM_LEVEL_REWARD =  {//1 in 3 chance to hit this table
			{new Item(12041, 1)},//Red Boater
			{new Item(7100, 1)},//Orange Boater
			{new Item(20695, 1)},//Green Boater
			{new Item(12042, 1)},//Blue Boater

	};
	
	private static final Item[][] HIGH_LEVEL_REWARD =  {//1 in 10 chance to hit the table
			{new Item(12041, 1)},//Red Boater
			{new Item(7100, 1)},//Orange Boater
			{new Item(20695, 1)},//Green Boater
			{new Item(12042, 1)},//Blue Boater
			{new Item(12043, 1)},//Red Boater
			{new Item(7118, 1)},//Orange Boater
			{new Item(7116, 100)},//Green Boater
			{new Item(13664, 3)},//Blue Boater
	};
	private static final Item[][] GOD_LEVEL_REWARD =  {//1 in 800 chance to hit the table
			{new Item(5022, 10000)},//Saradomin Bow
			{new Item(5022, 25000)},//Guthix Bow
			{new Item(7118, 2)},//Zamorak Bow
			{new Item(7100, 2)},//Armadyl Godsword
			{new Item(11531, 1)},//Bandos Godsword
			{new Item(11529, 1)},//Saradomin Godsword
			{new Item(11527, 1)},//Zamorak Godsword
			
	};
	private static final Item[][] EXTREME_LEVEL_REWARD =  {//1 in 1500 chance to hit the table
			{new Item(11423, 1)},//3rd Age 
			{new Item(5023, 1)},//3rd Age 
			{new Item(10334, 1)},//3rd Age 
			{new Item(10336, 1)},//3rd Age 
			{new Item(10338, 1)},//3rd Age 
			{new Item(10340, 1)},//3rd Age 
			{new Item(10342, 1)},//3rd Age 
			{new Item(10344, 1)},//3rd Age 
			{new Item(10346, 1)},//3rd Age 
			{new Item(10348, 1)},//3rd Age 
			{new Item(10350, 1)},//3rd Age 
			{new Item(10352, 1)},//3rd Age 
			{new Item(19311, 1)},//3rd Age 
			{new Item(12926, 1)},//Blowpipe
			{new Item(14044, 1)},//Black Phat
			{new Item(14050, 1)},//Black Santa
			{new Item(4084, 1)},//Sled
			{new Item(19143, 1)},//Saradomin Bow
			{new Item(19146, 1)},//Guthix Bow
			{new Item(19149, 1)},//Zamorak Bow
	};

	private static final String[] HINTS = { "Dig somewhere in the edgeville bank",
			"Dig near the mining guild teleport", "Dig somewhere near the duel arena tele",
			"Dig near one of the slayer masters", "Dig in the area you might see fisherman",
			"Dig near the tele to get chaotics", "Dig near the king of dragons",
			"Dig near the fourth minigame teleport", "Dig where players plant flowers" };

	public static void addClueRewards(Player player) {
		if (player.getInventory().getFreeSlots() < 6) {
			player.getPacketSender().sendMessage("You must have atleast 6 free inventory spaces!");
			return;
		}
		player.getInventory().delete(2714, 1);//Deletes Clue Casket
		Item[] basicLoot = BASIC_STACKS[Misc.getRandom(200)];
		for(Item item : basicLoot) {
			player.getInventory().add(item);
		}
		
		if (RandomUtility.RANDOM.nextInt(1) == 1) {
		Item[] lowLoot = LOW_LEVEL_REWARD[Misc.getRandom(200)];
		for(Item item : lowLoot) {
			player.getInventory().add(item);
			player.getInventory().add(item);
		}
		}
		
		else if (RandomUtility.RANDOM.nextInt(3) == 2) {
		Item[] mediumLoot = MEDIUM_LEVEL_REWARD[Misc.getRandom(200)];
		for(Item item : mediumLoot) {
			player.getInventory().add(item);
		}		
		}
		
		else if (RandomUtility.RANDOM.nextInt(10) == 5) {
		Item[] highLoot = HIGH_LEVEL_REWARD[Misc.getRandom(HIGH_LEVEL_REWARD.length - 1)];
		for(Item item : highLoot) {
			player.getInventory().add(item);
		}		
		}
		
		else if (RandomUtility.RANDOM.nextInt(800) == 600) {
		Item[] godLoot = GOD_LEVEL_REWARD[Misc.getRandom(GOD_LEVEL_REWARD.length - 1)];
		for(Item item : godLoot) {
			player.getInventory().add(item);
			World.sendMessage("@or3@[Clue Scroll]@bla@ "+player.getUsername()+ " has recieved a gift from the Gods!");
		}		
		}
		else if (RandomUtility.RANDOM.nextInt(1500) == 1288) {
		Item[] extremeLoot = EXTREME_LEVEL_REWARD[Misc.getRandom(EXTREME_LEVEL_REWARD.length - 1)];
		for(Item item : extremeLoot) {
			player.getInventory().add(item);
			World.sendMessage("@or3@[Clue Scroll]@bla@ "+player.getUsername()+ " has recieved an Extreme Rare!");
		}		
		}

	}

	public static void giveHint(Player player, int itemId) {
		if (itemId == 2677) {
			int index = 0;
			currentHint = HINTS[index];
			player.getPacketSender().sendInterface(47700);
			player.getPacketSender().sendString(47704, currentHint);
			player.getPacketSender().sendString(47703, " " + CluesCompleted);
			player.getInventory().delete(2677, 1);
			player.getInventory().add(2714, 1);
		}
		if (itemId == 2678) {
			int index = 1;
			currentHint = HINTS[index];
			player.getPacketSender().sendInterface(47700);
			player.getPacketSender().sendString(47704, currentHint);
			player.getPacketSender().sendString(47703, " " + CluesCompleted);
			player.getInventory().delete(2678, 1);
			player.getInventory().add(2714, 1);

		}
		if (itemId == 2679) {
			int index = 2;
			currentHint = HINTS[index];
			player.getPacketSender().sendInterface(47700);
			player.getPacketSender().sendString(47704, currentHint);
			player.getPacketSender().sendString(47703, " " + CluesCompleted);
			player.getInventory().delete(2679, 1);
			player.getInventory().add(2714, 1);

		}
		if (itemId == 2680) {
			int index = 3;
			currentHint = HINTS[index];
			player.getPacketSender().sendInterface(47700);
			player.getPacketSender().sendString(47704, currentHint);
			player.getPacketSender().sendString(47703, " " + CluesCompleted);
			player.getInventory().delete(2680, 1);
			player.getInventory().add(2714, 1);

		}
		if (itemId == 2681) {
			int index = 4;
			currentHint = HINTS[index];
			player.getPacketSender().sendInterface(47700);
			player.getPacketSender().sendString(47704, currentHint);
			player.getPacketSender().sendString(47703, " " + CluesCompleted);
			player.getInventory().delete(2681, 1);
			player.getInventory().add(2714, 1);

		}
		if (itemId == 2682) {
			int index = 5;
			currentHint = HINTS[index];
			player.getPacketSender().sendInterface(47700);
			player.getPacketSender().sendString(47704, currentHint);
			player.getPacketSender().sendString(47703, " " + CluesCompleted);
			player.getInventory().delete(2682, 1);
			player.getInventory().add(2714, 1);

		}
		if (itemId == 2683) {
			int index = 6;
			currentHint = HINTS[index];
			player.getPacketSender().sendInterface(47700);
			player.getPacketSender().sendString(47704, currentHint);
			player.getPacketSender().sendString(47703, " " + CluesCompleted);
			player.getInventory().delete(2683, 1);
			player.getInventory().add(2714, 1);

		}
		if (itemId == 2684) {
			int index = 7;
			currentHint = HINTS[index];
			player.getPacketSender().sendInterface(47700);
			player.getPacketSender().sendString(47704, currentHint);
			player.getPacketSender().sendString(47703, " " + CluesCompleted);
			player.getInventory().delete(2684, 1);
			player.getInventory().add(2714, 1);
		}
		if (itemId == 2685) {
			int index = 8;
			currentHint = HINTS[index];
			player.getPacketSender().sendInterface(47700);
			player.getPacketSender().sendString(47704, currentHint);
			player.getPacketSender().sendString(47703, " " + CluesCompleted);
			player.getInventory().delete(2685, 1);
			player.getInventory().add(2714, 1);
		}

	}

	public static void setCluesCompleted(int CluesCompleted, boolean add) {
		if (add)
			CluesCompleted += CluesCompleted;
		else
			ClueScrolls.CluesCompleted = CluesCompleted;
	}

	public static void incrementCluesCompleted(double amount) {
		CluesCompleted += amount;
	}

	public static int getCluesCompleted() {
		return CluesCompleted;
	}

}
