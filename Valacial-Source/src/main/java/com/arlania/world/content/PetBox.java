package com.arlania.world.content;


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
public class PetBox {

	/**
	 * The player object that will be triggering this event
	 */
	private final Player plr;
	private final int BOX = 19934;
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
	public PetBox(Player plr) {
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
				plr.getPacketSender().sendMessage("You don't have enough free inventory space.");
				return;
			}
			int oblivion[][] = {
					{5022}, //Uncommon, 0
					{3683,3684,3685,21061,20255}, //Rare, 1
					{2104,2758,2095,2096,2097,2098}
			};
			numGen3 = Math.random();

			double randomYoda = Misc.random(150);

			double drBoost = NPCDrops.getDroprate(plr,false);
			randomYoda = (int)randomYoda * ((100-drBoost)/100);
			int amount = 1;
			if(randomYoda == 0) {
				rewardGrade3 = 1;
				rewardPos = RandomUtility.getRandom(oblivion[rewardGrade3].length-1);
				plr.setAnimation(new Animation(6382));
				plr.setGraphic(new Graphic(127));
				World.sendMessage("<shad=0>@bla@[@yel@Pokeball@bla@] @yel@"+plr.getUsername()+"@bla@ Has just received a @yel@ "+ItemDefinition.forId(oblivion[rewardGrade3][rewardPos]).getName()+" @bla@from @yel@Oblivion box!");
				double randomDouble = Misc.random(99);

				double ddrBoost = NPCDrops.getDoubleDr(plr,false);
				randomDouble = (int)randomDouble * ((100-ddrBoost)/100);
				if(randomDouble == 0) {
					amount *= 2;
				}
			} else {
				rewardGrade3 = 0;
				rewardPos = RandomUtility.getRandom(oblivion[rewardGrade3].length-1);
				double randomDouble = Misc.random(99);

				double ddrBoost = NPCDrops.getDoubleDr(plr,false);
				randomDouble = (int)randomDouble * ((100-ddrBoost)/100);
				amount = Misc.random(65);

				if(randomDouble == 0) {
					amount *= 2;
				}
			}
			plr.getInventory().delete(19934, 1);
			plr.getInventory().add(oblivion[rewardGrade3][rewardPos], amount).refreshItems();
		}
	}
}