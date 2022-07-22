package com.arlania.world.content.mysteryboxes;


import com.arlania.model.Animation;
import com.arlania.model.Graphic;
import com.arlania.model.Item;
import com.arlania.model.definitions.ItemDefinition;
import com.arlania.model.definitions.NPCDrops;
import com.arlania.util.Misc;
import com.arlania.util.RandomUtility;
import com.arlania.world.World;
import com.arlania.world.entity.impl.player.Player;

/**
 * @author Tibo
 */
public class YodaBox {

	/**
	 * The player object that will be triggering this event
	 */
	private final Player plr;
	private final int BOX = 0;
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
	public YodaBox(Player plr) {
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
		double numGen3 = Math.random();
		int rewardGrade3 = 0;
		int rewardPos = 0;
		Player player = plr;
		
		if (player.getInventory().hasItem(new Item(BOX, 1))) {
			if (plr.getInventory().getFreeSlots() < 2) {
				return;
			}
			int donor[][] = {
					{20086, 20087, 20088, 21020, 21020, 21013, 13196, 13197, 13198, 13206, 13207, 21060}, //Uncommon, 0
					{20095, 20096, 20097, 20098, 20099, 20100, 20256, 20603, 3285, 3286, 3287, 20644, 20645}, //Rare, 1
					{11550, 4056, 3085, 4060}
			};
			numGen3 = Math.random();

			//this is droprate 
			double randomYoda = Misc.random(75);

			int amount = 1;
			if (randomYoda == 0) {
				rewardGrade3 = 2;
				rewardPos = RandomUtility.getRandom(donor[rewardGrade3].length - 1);
				player.setAnimation(new Animation(6382));
				player.setGraphic(new Graphic(127));
				World.sendMessage("<shad=0>@bla@[@gre@Super Donator box@bla@] [@mag@Legendary@bla@] @gre@" + player.getUsername() + "@bla@ Has just received a @gre@ " + ItemDefinition.forId(donor[rewardGrade3][rewardPos]).getName() + " @bla@!");
				double randomDouble = Misc.random(125);

				double ddrBoost = NPCDrops.getDoubleDr(player,true); //let me do this fast
				randomDouble = (int) randomDouble * ((100 - ddrBoost) / 100);
				if (randomDouble == 0) {
					amount *= 2;
				}
				} else if (randomYoda < 25) {
					rewardGrade3 = 1;
					rewardPos = RandomUtility.getRandom(donor[rewardGrade3].length - 1);
					player.setAnimation(new Animation(6382));
					player.setGraphic(new Graphic(127));
					World.sendMessage("<shad=0>@bla@[@gre@Super Donator box@bla@] [@blu@Uncommon@bla@] @gre@" + player.getUsername() + "@bla@ Has just received a @gre@ " + ItemDefinition.forId(donor[rewardGrade3][rewardPos]).getName() + " @bla@!");
					double randomDouble = Misc.random(125);

					double ddrBoost = NPCDrops.getDoubleDr(player,false);
					randomDouble = (int) randomDouble * ((100 - ddrBoost) / 100);
					if (randomDouble == 0) {
						amount *= 2;
					}
				} else {
				rewardGrade3 = 0;
				rewardPos = RandomUtility.getRandom(donor[rewardGrade3].length - 1);
				player.setAnimation(new Animation(6382));
				player.setGraphic(new Graphic(127));
				World.sendMessage("<shad=0>@bla@[@gre@Super Donator box@bla@] [@gre@common@bla@] @gre@" + player.getUsername() + "@bla@ Has just received a @gre@ " + ItemDefinition.forId(donor[rewardGrade3][rewardPos]).getName() + " @bla@!");
				double randomDouble = Misc.random(125);

				double ddrBoost = NPCDrops.getDoubleDr(player,false);
				randomDouble = (int) randomDouble * ((100 - ddrBoost) / 100);
				if (randomDouble == 0) {
					amount *= 2;
				}
			}
			player.getInventory().delete(20900, 1);
			player.getInventory().delete(2717, 1);
					player.getInventory().add(donor[rewardGrade3][rewardPos], amount).refreshItems();

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
		
		int[] common = { -1,-1,-1,-1,-1,11527,11529,11531,3819,3820,3821,3822,3823,3824,3825,11533,11423,4060,12422,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
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