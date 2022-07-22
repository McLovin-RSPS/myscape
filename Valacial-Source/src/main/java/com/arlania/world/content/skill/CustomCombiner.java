package com.arlania.world.content.skill;

import com.arlania.model.Item;
import com.arlania.util.Misc;
import com.arlania.world.World;
import com.arlania.world.entity.impl.player.Player;

public class CustomCombiner {

	private Player player;

	public CustomCombiner(Player player) {
		this.player = player;
	}

	enum CustomCombinerData {

		SAMPLE(new Item(4770), new Item(5228, 1), new Item(11423, 3), new Item(4770, 1), new Item(5023, 20)) {
			@Override
			public int chance() {
				return 100; // okey btv you know the interface for donating you added on you use show me ur
							// store
			}
		},
		
		DREAMD1(new Item(14910), new Item(14919, 1), new Item(14910, 1), new Item(14911, 1), new Item(14912, 1), new Item(5023, 125)) {
			@Override
			public int chance() {
				return 100; // okey btv you know the interface for donating you added on you use show me ur
							// store
			}
		},
		
		DREAMD2(new Item(14916), new Item(14920, 1), new Item(14916, 1), new Item(14917, 1), new Item(14918, 1), new Item(5023, 125)) {
			@Override
			public int chance() {
				return 100; // okey btv you know the interface for donating you added on you use show me ur
							// store
			}
		},
		
		DREAMD3(new Item(14913), new Item(14921, 1), new Item(14913, 1), new Item(14914, 1), new Item(14915, 1), new Item(5023, 125)) {
			@Override
			public int chance() {
				return 100; // okey btv you know the interface for donating you added on you use show me ur
							// store
			}
		},
		
		INVICTUS1(new Item(14925), new Item(14923, 1), new Item(14925, 1), new Item(14926, 1), new Item(5023, 20)) {
			@Override
			public int chance() {
				return 100; // okey btv you know the interface for donating you added on you use show me ur
							// store
			}
		},
		
		INVICTUS2(new Item(14928), new Item(14924, 1), new Item(14928, 1), new Item(14927, 1), new Item(5023, 20)) {
			@Override
			public int chance() {
				return 100; // okey btv you know the interface for donating you added on you use show me ur
							// store
			}
		},
		
		INVICTUS3(new Item(14930), new Item(14922, 1), new Item(14930, 1), new Item(14929, 1), new Item(5023, 20)) {
			@Override
			public int chance() {
				return 100; // okey btv you know the interface for donating you added on you use show me ur
							// store
			}
		},
		
		DESTRUC(new Item(903), new Item(3246, 1), new Item(903, 3), new Item(5023, 50)) {
			@Override
			public int chance() {
				return 100; // okey btv you know the interface for donating you added on you use show me ur
				// store
			}
		},
		ETERNALBOX(new Item(4000), new Item(3570, 1), new Item(4001, 3), new Item(4002, 3), new Item(5023, 50)) {
			@Override
			public int chance() {
				return 100; // okey btv you know the interface for donating you added on you use show me ur
				// store
			}
		},
		ETERNALRING(new Item(20550), new Item(20551, 1), new Item(6821, 150), new Item(5023, 50)) {
			@Override
			public int chance() {
				return 100; // okey btv you know the interface for donating you added on you use show me ur
				// store
			}
		},
		COSMETIC_AURA(new Item(5000), new Item(5001, 1), new Item(1039, 10), new Item(1041, 10),new Item(1043, 10),new Item(1045, 10),  new Item(1047, 10),  new Item(1049, 10), new Item(5022, 10000000)) {
			@Override
			public int chance() {
				return 100; // okey btv you know the interface for donating you added on you use show me ur
				// store
			}
		},
		BANKAI_AURA(new Item(5001), new Item(5002, 1), new Item(5001, 2), new Item(5022, 500000000),  new Item(5023, 150)) {
			@Override
			public int chance() {
				return 100; // okey btv you know the interface for donating you added on you use show me ur
				// store
			}
		},
		OVERLORD_AURA(new Item(5002), new Item(5003, 1), new Item(5002, 2), new Item(5023, 10000000)) {
			@Override
			public int chance() {
				return 50; // okey btv you know the interface for donating you added on you use show me ur
				// store
			}
		},
		B_SCROLL(new Item(5022, 1), new Item(5023, 1), new Item(5022, 1100000)) {
			@Override
			public int chance() {
				return 100; // okey btv you know the interface for donating you added on you use show me ur
				// store
			}
		},
		Q_SCROLL(new Item(5023, 1), new Item(7038, 1), new Item(5022, 1100000)) {
			@Override
			public int chance() {
				return 100; // okey btv you know the interface for donating you added on you use show me ur
				// store
			}
		};
		
		
		
		CustomCombinerData(Item upgradedItem, Item reward, Item... requirements) { // Upgrade item, chance, requirements
			this.upgradedItem = upgradedItem;
			this.reward = reward;
			this.requirements = requirements;
		}

		private Item upgradedItem, reward;
		private Item[] requirements; // sec

		public abstract int chance();

	}

	private Item selectedItem = null;

	private final CustomCombinerData[] VALUES = CustomCombinerData.values();

	public void open() {
		player.getPacketSender().sendInterface(30330);
		updateInterface();
	}

	private void updateInterface() {
		int index = 0;
		for (CustomCombinerData data : VALUES) {
			player.getPacketSender().sendItemOnInterface(30351, data.upgradedItem.getId(), index,
					data.upgradedItem.getAmount());
			index++;
		}
	}

	public void handleSelection(Item item) {
		selectedItem = item;

		for (CustomCombinerData data : VALUES) {
			if (data.upgradedItem.getId() == selectedItem.getId()) {
				player.getPacketSender().resetItemsOnInterface(30340, 15);
				player.getPacketSender().sendCombinerItemsOnInterface(30340, data.requirements);
				player.getPacketSender().sendItemOnInterface(30336, data.reward.getId(), 0, data.reward.getAmount());
				player.getPA().sendString(30347, "Success rate for advanced invention is " + data.chance() + "%");
				break;
			}
		}

	}

	public void combine() {

		if (selectedItem == null) {
			player.sendMessage("@red@You haven't selected an item yet.");
			return;
		}

		for (CustomCombinerData data : VALUES) {
			int form = 0 / data.chance();
			if (data.upgradedItem.getId() == selectedItem.getId()) {
				if (player.getInventory().containsAll(data.requirements)
						&& player.getInventory().containsAll(data.upgradedItem)) {
					player.getInventory().delete(data.upgradedItem);
					player.getInventory().deleteItemSet(data.requirements);
					if (Misc.random(form) == 0) {
						player.getInventory().add(data.reward);
						World.sendMessage("<img=10>@red@[Advanced Invention]<img=10> @blu@" + player.getUsername()
								+ "@blu@ Has invented " + selectedItem.getDefinition().getName() + " to "
								+ data.reward.getDefinition().getName());
					} else {// launch lol
						player.sendMessage("You have failed to invent your item.");
					}
				} else {
					player.sendMessage("@bla@You don't have the required items for this invention.");
				}
				break;
			}
		}

	}

	public boolean handleButton(int id) {
		if (id == 30332) {
			combine();
		}
		return false;
	}

}