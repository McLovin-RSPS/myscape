package com.arlania.world.content;

import com.arlania.engine.task.Task;
import com.arlania.engine.task.TaskManager;
import com.arlania.model.Animation;
import com.arlania.model.Graphic;
import com.arlania.model.GraphicHeight;
import com.arlania.model.GroundItem;
import com.arlania.model.Item;
import com.arlania.model.Position;
import com.arlania.util.Misc;
import com.arlania.world.World;
import com.arlania.world.content.transportation.TeleportHandler;
import com.arlania.world.entity.impl.GroundItemManager;
import com.arlania.world.entity.impl.player.Player;

/*
 * Special Key Event for 2017 (Promotional Event)
 * @author Cade www.runeunity.org
 * 
 */

public class KeysEvent {
	
	//Item ids that will be dropped
	public static int pvmKey = -1;
	
	//useless, just needed to write down object id
	public static int chest = 6420;

	public static int rareLoots[] = { 21004, 21031, 21030, 21002, 1153, 2572, 1115, 1067, 21011, 21012,21004, 21031, 21030, 21002, 1153, 2572, 1115, 1067, 21011, 21012,21004, 21031, 21030, 21002, 1153, 2572, 1115, 1067, 21011, 21012,21013,20086,20087,20088,20252,21020,21021};
	
	public static int ultraLoots[] = {20102,20103,20104,20105,20106,20253,20107};
	
	public static int amazingLoots[] = {21060, 13196, 13197, 13198, 20254,13206, 13207, 13199};
	
	public static int commonLoots[] = {5022};
	/*
	 * Start methods below
	 */
	
	
	/*
	 * Grabs a random item from aray
	 */
	public static int getRandomItem(int[] array) {
		return array[Misc.getRandom(array.length - 1)];
	}

	
	/*
	 * Opening the chest with suspense, give reward
	 */
	public static void openChest(Player player) {
		/*if(!p.getClickDelay().elapsed(1000))
			return;*/
		if (player.getInventory().contains(-1)) {
		
			TaskManager.submit(new Task(0, player, true) {
			@Override
			public void execute() {
				player.performAnimation(new Animation(6387));
				player.getPacketSender().sendMessage("Opening Chest...");
				player.getInventory().delete(-1, 1);
				giveReward(player);
				this.stop();
			}
		});
      } else {
		  
    	  player.getPacketSender().sendMessage("You require a Red Key to open this chest!");
    	  return;
      }
	 
	}
	public static void craftSword(Player player) {
		player.performGraphic(new Graphic(1194));
		player.performAnimation(new Animation(791));
		if(player.getInventory().contains(6081) && player.getInventory().contains(15596) && player.getInventory().contains(4061)) {
			TaskManager.submit(new Task(2, player, false) {
				@Override
				public void execute() {
					player.getInventory().delete(6081, 1);
					player.getInventory().delete(15596, 1);
					player.getInventory().delete(4060, 1);

					player.getInventory().add(21009, 1);
					World.sendMessage("<img=10> <col=008FB2>[Crafted]"+player.getUsername()+" has just Crafted mythical Sword.");
					this.stop();
				}
			});
		} else {
			player.getPacketSender().sendMessage("You require the infinity gauntlet to craft a Mythical Sword.");
			return;
		}

	}
	
	public static void craftThanosGloves(Player player) {
		player.performGraphic(new Graphic(1194));
		player.performAnimation(new Animation(791));
		if(player.getInventory().contains(4629) && player.getInventory().contains(4630) && player.getInventory().contains(4631)
				 && player.getInventory().contains(4632) && player.getInventory().contains(4060)  && player.getInventory().contains(4633) && player.getInventory().contains(4634)) {
			TaskManager.submit(new Task(2, player, false) {
				@Override
				public void execute() {
					player.getInventory().delete(4629, 1);
					player.getInventory().delete(4630, 1);
					player.getInventory().delete(4631, 1);
					player.getInventory().delete(4632, 1);
					player.getInventory().delete(4633, 1);
					player.getInventory().delete(4634, 1);
					player.getInventory().delete(4060, 1);
					player.getInventory().add(4061, 1);
					World.sendMessage("<img=10> <col=008FB2>[Crafted]"+player.getUsername()+" has just Crafted infinity gauntlet (Charged) GG.");
					this.stop();
				}
			});
		} else {
			player.getPacketSender().sendMessage("You require all the stones and infinity gauntlet to craft infinity gauntlet (charged).");
			return;
		}

	}
	
	public static void craftSpaceCape(Player player) {
		player.performGraphic(new Graphic(247));
		player.performAnimation(new Animation(6702));
		if(player.getInventory().contains(4762) && player.getInventory().contains(4763) && player.getInventory().contains(4764)) {
			TaskManager.submit(new Task(2, player, false) {
				@Override
				public void execute() {
					player.getInventory().delete(4762, 1);
					player.getInventory().delete(4763, 1);
					player.getInventory().delete(4764, 1);
					player.getInventory().add(4787, 1);
					World.sendMessage("<img=10> <col=008FB2>[Crafted]"+player.getUsername()+" has just Crafted Space Cape.");
					this.stop();
				}
			});
		} else {
			player.getPacketSender().sendMessage("You require Aqantium Cape, Grndmaster Cape, Destuction Cape To Craft This! .");
			return;
		}

	}
	
	public static void craftMadarafan(Player player) {
		player.performGraphic(new Graphic(437));
		player.performAnimation(new Animation(792));
		if(player.getInventory().contains(3085) && player.getInventory().contains(4056)) {
			TaskManager.submit(new Task(2, player, false) {
				@Override
				public void execute() {
					player.getInventory().delete(3085, 1);
					player.getInventory().delete(4056, 1);
					player.getInventory().add(3084, 1);
					World.sendMessage("<img=10> <col=008FB2>[Crafted]"+player.getUsername()+" has just Crafted Madara Fan (x) GG.");
					this.stop();
				}
			});
		} else {
			player.getPacketSender().sendMessage("You require Mandara (X) stone and Mandara Fan! .");
			return;
		}

	}
	
	public static void giveReward(Player player) {
		if (Misc.getRandom(40) == 1) { //Rare Item
			int rareDrops = getRandomItem(rareLoots);
			player.getInventory().add(rareDrops, 1);
			World.sendMessage("@or3@[Burning Chest]@bla@ "+player.getUsername()+ " has received a Rare from the chest!");
		} else if (Misc.getRandom(50) == 1) {//Ultra Rare items
			World.sendMessage("@or3@[Burning Chest]@bla@ "+player.getUsername()+ " has received an Ultra Rare from the chest!");
			int ultraDrops = getRandomItem(ultraLoots);
			player.getInventory().add(ultraDrops, 1);
		} else if (Misc.getRandom(100) == 1) {//Amazing items
			World.sendMessage("@or3@[Burning Chest]@bla@ "+player.getUsername()+ " has received a Legendary Reward from the chest!!");
			int amazingDrops = getRandomItem(amazingLoots);
			player.getInventory().add(amazingDrops, 1);
		} else {//Common items
			int commonDrops = getRandomItem(commonLoots);
			player.getInventory().add(commonDrops, Misc.random(250));
		}
			
	}
	
	/*
	 * Handles Skotizo Boss
	 */
	public static void handleSkotizo(Player player, Position pos) {
		if (Misc.getRandom(50) == 1) {
			GroundItemManager.spawnGroundItem(player, new GroundItem(new Item(pvmKey), pos, player.getUsername(), false, 150, true, 200));
		    player.getPacketSender().sendMessage("@red@You have received a key!");
		}
		GroundItemManager.spawnGroundItem(player, new GroundItem(new Item(526), pos, player.getUsername(), false, 150, true, 200));
	}
	
	/*
	 * Handles Phoenix Boss
	 */
	public static void handlePhoenix(Player player, Position pos) {
		if (Misc.getRandom(50) == 1) {
			GroundItemManager.spawnGroundItem(player, new GroundItem(new Item(pvmKey), pos, player.getUsername(), false, 150, true, 200));
		    player.getPacketSender().sendMessage("@red@You have received a key!");
		}
		GroundItemManager.spawnGroundItem(player, new GroundItem(new Item(526), pos, player.getUsername(), false, 150, true, 200));
	}
	
	/*
	 * Handles Thermo Boss
	 */
	public static void handleThermo(Player player, Position pos) {
		if (Misc.getRandom(50) == 1) {
			GroundItemManager.spawnGroundItem(player, new GroundItem(new Item(pvmKey), pos, player.getUsername(), false, 150, true, 200));
		    player.getPacketSender().sendMessage("@red@You have receivedved a key!");
		}
		GroundItemManager.spawnGroundItem(player, new GroundItem(new Item(526), pos, player.getUsername(), false, 150, true, 200));
	}
	
	/*
	 * Handles Slash Bash Boss
	 */
	public static void handleSlashBash(Player player, Position pos) {
		if (Misc.getRandom(50) == 1) {
			GroundItemManager.spawnGroundItem(player, new GroundItem(new Item(pvmKey), pos, player.getUsername(), false, 150, true, 200));
		    player.getPacketSender().sendMessage("@red@You have received a key!");
		}
		GroundItemManager.spawnGroundItem(player, new GroundItem(new Item(526), pos, player.getUsername(), false, 150, true, 200));
	}
	
	/*
	 * Handles KBD Boss
	 */
	public static void handleKBD(Player player, Position pos) {
		if (Misc.getRandom(50) == 1) {
			GroundItemManager.spawnGroundItem(player, new GroundItem(new Item(pvmKey), pos, player.getUsername(), false, 150, true, 200));
		    player.getPacketSender().sendMessage("@red@You have received a key!");
		}
		GroundItemManager.spawnGroundItem(player, new GroundItem(new Item(526), pos, player.getUsername(), false, 150, true, 200));
	}
	
	/*
	 * Handles Cerb Boss
	 */
	public static void handleCerb(Player player, Position pos) {
		if (Misc.getRandom(50) == 1) {
			GroundItemManager.spawnGroundItem(player, new GroundItem(new Item(pvmKey), pos, player.getUsername(), false, 150, true, 200));
		    player.getPacketSender().sendMessage("@red@You have received a key!");
		}
		GroundItemManager.spawnGroundItem(player, new GroundItem(new Item(526), pos, player.getUsername(), false, 150, true, 200));
	}
	
	/*
	 * Handles Bork Boss
	 */
	public static void handleBork(Player player, Position pos) {
		if (Misc.getRandom(50) == 1) {
			GroundItemManager.spawnGroundItem(player, new GroundItem(new Item(pvmKey), pos, player.getUsername(), false, 150, true, 200));
		    player.getPacketSender().sendMessage("@red@You have received a key!");
		}
		GroundItemManager.spawnGroundItem(player, new GroundItem(new Item(526), pos, player.getUsername(), false, 150, true, 200));
	}
	
	/*
	 * Handles Glacor Boss
	 */
	public static void handleGlacor(Player player, Position pos) {
		if (Misc.getRandom(50) == 1) {
			GroundItemManager.spawnGroundItem(player, new GroundItem(new Item(pvmKey), pos, player.getUsername(), false, 150, true, 200));
		    player.getPacketSender().sendMessage("@red@You have received a key!");
		}
		GroundItemManager.spawnGroundItem(player, new GroundItem(new Item(526), pos, player.getUsername(), false, 150, true, 200));
	}
	
	/*
	 * Handles Shaman Boss
	 */
	public static void handleShaman(Player player, Position pos) {
		if (Misc.getRandom(50) == 1) {
			GroundItemManager.spawnGroundItem(player, new GroundItem(new Item(pvmKey), pos, player.getUsername(), false, 150, true, 200));
		    player.getPacketSender().sendMessage("@red@You have received a key!");
		}
		GroundItemManager.spawnGroundItem(player, new GroundItem(new Item(526), pos, player.getUsername(), false, 150, true, 200));
	}
	
	/*
	 * Handles Green Dragon
	 */
	public static void handleGreenDragon(Player player, Position pos) {
		if (Misc.getRandom(195) == 25) {
			GroundItemManager.spawnGroundItem(player, new GroundItem(new Item(pvmKey), pos, player.getUsername(), false, 150, true, 200));
		    player.getPacketSender().sendMessage("@red@You have received a key!");
		}
		GroundItemManager.spawnGroundItem(player, new GroundItem(new Item(526), pos, player.getUsername(), false, 150, true, 200));
	}
	
	/*
	 * Handles Blue Dragon
	 */
	public static void handleBlueDragon(Player player, Position pos) {
		if (Misc.getRandom(195) == 25) {
			GroundItemManager.spawnGroundItem(player, new GroundItem(new Item(pvmKey), pos, player.getUsername(), false, 150, true, 200));
		    player.getPacketSender().sendMessage("@red@You have received a key!");
		}
		GroundItemManager.spawnGroundItem(player, new GroundItem(new Item(526), pos, player.getUsername(), false, 150, true, 200));
	}
	
	/*
	 * Handles Abby Demon
	 */
	public static void handleAbbyDemon(Player player, Position pos) {
		if (Misc.getRandom(195) == 25) {
			GroundItemManager.spawnGroundItem(player, new GroundItem(new Item(pvmKey), pos, player.getUsername(), false, 150, true, 200));
		    player.getPacketSender().sendMessage("@red@You have received a key!");
		}
		GroundItemManager.spawnGroundItem(player, new GroundItem(new Item(526), pos, player.getUsername(), false, 150, true, 200));
	}

}
