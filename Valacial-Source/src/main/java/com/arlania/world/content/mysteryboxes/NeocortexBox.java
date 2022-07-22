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
public class NeocortexBox {

	/**
	 * The player object that will be triggering this event
	 */
	private final Player plr;
	private final int BOX = 7120;
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
	public NeocortexBox(Player plr) {
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
			int oblivion[][] = {
					{3285, 3286, 3287, 20644, 20645}, //Uncommon, 0
					{4806, 4805, 4804, 925, 923, 924, 927, 926, 922, 919, 3666}, //Rare, 1
					{4761, 11423, 12423}
			};
			numGen3 = Math.random();

			//this is droprate 
			double randomYoda = Misc.random(75);

			int amount = 1;
			if (randomYoda == 0) {
				rewardGrade3 = 2;
				rewardPos = RandomUtility.getRandom(oblivion[rewardGrade3].length - 1);
				player.setAnimation(new Animation(6382));
				player.setGraphic(new Graphic(127));
				World.sendMessage("<shad=0>@bla@[@blu@Extreme Donator box@bla@] [@mag@Legendary@bla@] @blu@" + player.getUsername() + "@bla@ Has just received a @blu@ " + ItemDefinition.forId(oblivion[rewardGrade3][rewardPos]).getName() + " @bla@!");
				double randomDouble = Misc.random(125);

				double ddrBoost = NPCDrops.getDoubleDr(player,false);
				randomDouble = (int) randomDouble * ((100 - ddrBoost) / 100);
				if (randomDouble == 0) {
					amount *= 2;
				}
				} else if (randomYoda < 25) {
					rewardGrade3 = 1;
					rewardPos = RandomUtility.getRandom(oblivion[rewardGrade3].length - 1);
					player.setAnimation(new Animation(6382));
					player.setGraphic(new Graphic(127));
					World.sendMessage("<shad=0>@bla@[@blu@Extreme Donator box@bla@] [@blu@Uncommon@bla@] @blu@" + player.getUsername() + "@bla@ Has just received a @blu@ " + ItemDefinition.forId(oblivion[rewardGrade3][rewardPos]).getName() + " @bla@!");
					double randomDouble = Misc.random(125);

					double ddrBoost = NPCDrops.getDoubleDr(player,false);
					randomDouble = (int) randomDouble * ((100 - ddrBoost) / 100);
					if (randomDouble == 0) {
						amount *= 2;
					}
				} else {
				rewardGrade3 = 0;
				rewardPos = RandomUtility.getRandom(oblivion[rewardGrade3].length - 1);
				player.setAnimation(new Animation(6382));
				player.setGraphic(new Graphic(127));
				World.sendMessage("<shad=0>@bla@[@blu@Extreme Donator box@bla@] [@blu@common@bla@] @blu@" + player.getUsername() + "@bla@ Has just received a @blu@ " + ItemDefinition.forId(oblivion[rewardGrade3][rewardPos]).getName() + " @bla@!");
				double randomDouble = Misc.random(125);

				double ddrBoost = NPCDrops.getDoubleDr(player,false);
				randomDouble = (int) randomDouble * ((100 - ddrBoost) / 100);
				if (randomDouble == 0) {
					amount *= 2;
				}
			}
					player.getInventory().delete(7120, 1);
					player.getInventory().add(oblivion[rewardGrade3][rewardPos], amount).refreshItems();

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
		
		int[] common = { -1,-1,-1,-1,-1,3285,3286,3287,20644,20645,925,923,924,927,926,922,919,3666,4806,4805,4804,4761,11423,12423,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
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