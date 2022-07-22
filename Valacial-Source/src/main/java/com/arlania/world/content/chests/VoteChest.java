package com.arlania.world.content.chests;

import com.arlania.model.Animation;
import com.arlania.model.GameObject;
import com.arlania.model.Graphic;
import com.arlania.model.Item;
import com.arlania.model.PlayerRights;
import com.arlania.util.Misc;
import com.arlania.util.RandomUtility;
import com.arlania.world.World;
import com.arlania.world.entity.impl.npc.NPC;
import com.arlania.world.entity.impl.player.Player;

public class VoteChest {

	public static void handleChest(final Player p, final GameObject object) {
		if (!p.getClickDelay().elapsed(2000)) {
			return;
		}

		if (!p.getInventory().contains(79)) {
			p.getPacketSender().sendMessage("This chest can only be opened with the vote Chest Key");
			return;
		}
        p.forceChat("I can get vote keys every 12 hours by doing @red@::vote@bla@.");
		p.performAnimation(new Animation(7253));
		p.performGraphic(new Graphic(1898));
		
		int random = 0;
		
		if (p.getRights() == PlayerRights.DONATOR)  {
			random = 85;
		}
		if (p.getRights() == PlayerRights.EXECUTIVE_DONATOR)  {
			random = 85;
		}
		if (p.getRights() == PlayerRights.EXTREME_DONATOR)  {
			random = 85;
		}
		if (p.getRights() == PlayerRights.SPONSOR_DONATOR)  {
			random = 85;
		}
		if (p.getRights() == PlayerRights.SUPER_DONATOR)  {
			random = 85;
		}
		
		if (random == 0 || Misc.getRandom(100) < random) {
			p.getInventory().delete(79, 1);
		} else {
			p.getPacketSender().sendMessage("Your Vote Key has been saved as a donator benefit");
		}
		
		p.getPacketSender().sendMessage("You open the Vote Chest..");

		Item[] loot = itemRewards[Misc.getRandom(itemRewards.length - 1)];
		
		for (Item item : loot) {
			p.getInventory().add(item, "Vote Chest loot");
		}
		
		p.getInventory().add(5022, 500 + RandomUtility.RANDOM.nextInt(100), "Vote Chest");
	}

	private static final Item[][] itemRewards =  {
			 {new Item(5022, 500)},
			 {new Item(5022, 700)},
			 {new Item(5022, 900)},
			 {new Item(5022, 1000)},
			 {new Item(5022, 1200)},
			 {new Item(5022, 1500)},
			 {new Item(2717, 1)},
			 {new Item(20900, 1)},
			 {new Item(11425, 1)},
			 {new Item(2717, 1)},
			 {new Item(20900, 1)},
			 {new Item(2717, 1)},
			 {new Item(20900, 1)},
			 {new Item(2717, 1)},
			 {new Item(20900, 1)},
			 {new Item(11425, 1)},
			 {new Item(11559, 2)},
			 {new Item(11559, 2)},
			 {new Item(12421, 1)},
			{new Item(7118, 1)},
			{new Item(7114, 1)},
			{new Item(11559, 2)},
			{new Item(15355, 1)},

	};
	
}
