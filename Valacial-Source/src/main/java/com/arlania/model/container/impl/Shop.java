package com.arlania.model.container.impl;

import java.util.HashMap;
import java.util.Map;

import com.arlania.engine.task.TaskManager;
import com.arlania.engine.task.impl.ShopRestockTask;
import com.arlania.model.GameMode;
import com.arlania.model.Item;
import com.arlania.model.PlayerRights;
import com.arlania.model.Skill;
import com.arlania.model.container.ItemContainer;
import com.arlania.model.container.StackType;
import com.arlania.model.definitions.ItemDefinition;
import com.arlania.model.input.impl.EnterAmountToBuyFromShop;
import com.arlania.model.input.impl.EnterAmountToSellToShop;
import com.arlania.util.JsonLoader;
import com.arlania.util.Misc;
import com.arlania.world.World;
import com.arlania.world.content.minigames.impl.RecipeForDisaster;
import com.arlania.world.entity.impl.player.Player;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Messy but perfect Shop System
 * 
 * @author Gabriel Hannason
 */

public class Shop extends ItemContainer {

	/*
	 * The shop constructor
	 */
	public Shop(Player player, int id, String name, Item currency, Item[] stockItems) {
		super(player);
		if (stockItems.length > 42)
			throw new ArrayIndexOutOfBoundsException(
					"Stock cannot have more than 40 items; check shop[" + id + "]: stockLength: " + stockItems.length);
		this.id = id;
		this.name = name.length() > 0 ? name : "General Store";
		this.currency = currency;
		this.originalStock = new Item[stockItems.length];
		for (int i = 0; i < stockItems.length; i++) {
			Item item = new Item(stockItems[i].getId(), stockItems[i].getAmount());
			add(item, false);
			this.originalStock[i] = item;
		}
	}

	private final int id;

	private String name;

	private Item currency;

	private Item[] originalStock;

	public Item[] getOriginalStock() {
		return this.originalStock;
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return name;
	}

	public Shop setName(String name) {
		this.name = name;
		return this;
	}

	public Item getCurrency() {
		return currency;
	}

	public Shop setCurrency(Item currency) {
		this.currency = currency;
		return this;
	}

	private boolean restockingItems;

	public boolean isRestockingItems() {
		return restockingItems;
	}

	public void setRestockingItems(boolean restockingItems) {
		this.restockingItems = restockingItems;
	}

	/**
	 * Opens a shop for a player
	 * 
	 * @param player
	 *            The player to open the shop for
	 * @return The shop instance
	 */
	public Shop open(Player player) {
		setPlayer(player);
		getPlayer().getPacketSender().sendInterfaceRemoval().sendClientRightClickRemoval();
		getPlayer().setShop(ShopManager.getShops().get(id)).setInterfaceId(INTERFACE_ID).setShopping(true);
		refreshItems();
		if (Misc.getMinutesPlayed(getPlayer()) <= 190)
			getPlayer().getPacketSender()
					.sendMessage("Note: When selling an item to a store, it loses 15% of its original value.");
		return this;
	}

	/**
	 * Refreshes a shop for every player who's viewing it
	 */
	public void publicRefresh() {
		Shop publicShop = ShopManager.getShops().get(id);
		if (publicShop == null)
			return;
		publicShop.setItems(getItems());
		for (Player player : World.getPlayers()) {
			if (player == null)
				continue;
			if (player.getShop() != null && player.getShop().id == id && player.isShopping())
				player.getShop().setItems(publicShop.getItems());
		}
	}

	/**
	 * Checks a value of an item in a shop
	 * 
	 * @param player
	 *            The player who's checking the item's value
	 * @param slot
	 *            The shop item's slot (in the shop!)
	 * @param sellingItem
	 *            Is the player selling the item?
	 */
	public void checkValue(Player player, int slot, boolean sellingItem) {
		this.setPlayer(player);
		Item shopItem = new Item(getItems()[slot].getId());
		if (!player.isShopping()) {
			player.getPacketSender().sendInterfaceRemoval();
			return;
		}
		Item item = sellingItem ? player.getInventory().getItems()[slot] : getItems()[slot];
		if (item.getId() == 995)
			return;
		if (sellingItem) {
			if (!shopBuysItem(id, item)) {
				player.getPacketSender().sendMessage("You cannot sell this item to this store.");
				return;
			}
		}
		int finalValue = 0;
		String finalString = sellingItem ? "" + ItemDefinition.forId(item.getId()).getName() + ": shop will buy for "
				: "" + ItemDefinition.forId(shopItem.getId()).getName() + " currently costs ";
		if (getCurrency().getId() == -1) {
			Object[] obj = ShopManager.getCustomShopData(id, item.getId());
			if (obj == null)
				return;
			finalValue = (int) obj[0];
			if (sellingItem) {
				if (finalValue != 1) {
					finalValue = (int) (finalValue * 0.85);
				}
			}
			finalString += "" + finalValue + " " + (String) obj[1] + ".";
		} else {
			finalValue = ItemDefinition.forId(item.getId()).getValue();
			String s = currency.getDefinition().getName().toLowerCase().endsWith("s")
					? currency.getDefinition().getName().toLowerCase()
					: currency.getDefinition().getName().toLowerCase() + "s";
			/** CUSTOM CURRENCY, CUSTOM SHOP VALUES **/
			if (id == TOKKUL_EXCHANGE_STORE || id == ENERGY_FRAGMENT_STORE || id == DEADPIRATE_SHOP|| id == STARDUST_STORE|| id == AGILITY_TICKET_STORE
					|| id == GRAVEYARD_STORE || id == HOLY_WATER_STORE || id == AFK_SHOP|| id == AFK_SHOP_2) {
				Object[] obj = ShopManager.getCustomShopData(id, item.getId());
				if (obj == null)
					return;
				finalValue = (int) obj[0];
				s = (String) obj[1];
			}
			if (sellingItem) {
				if (finalValue != 1) {
					finalValue = (int) (finalValue * 0.85);
				}
			}
			finalString += "" + (int) finalValue + " " + s + "" + shopPriceEx((int) finalValue) + ".";
		}
		if (player != null && finalValue > 0) {
			player.getPacketSender().sendMessage(finalString);
			return;
		}
	}

	public void sellItem(Player player, int slot, int amountToSell) {
		this.setPlayer(player);
		if (!player.isShopping() || player.isBanking()) {
			player.getPacketSender().sendInterfaceRemoval();
			return;
		}

		if (!player.isShopping() || player.isBanking()) {
			player.getPacketSender().sendInterfaceRemoval();
			return;
		}
		Item itemToSell = player.getInventory().getItems()[slot];
		if (!itemToSell.sellable()) {
			player.getPacketSender().sendMessage("This item cannot be sold.");
			return;
		}
		if (!shopBuysItem(id, itemToSell)) {
			player.getPacketSender().sendMessage("You cannot sell this item to this store.");
			return;
		}
		if (!player.getInventory().contains(itemToSell.getId()) || itemToSell.getId() == 995)
			return;
		if (this.full(itemToSell.getId()))
			return;
		if (player.getInventory().getAmount(itemToSell.getId()) < amountToSell)
			amountToSell = player.getInventory().getAmount(itemToSell.getId());
		if (amountToSell == 0)
			return;
		/*
		 * if(amountToSell > 300) { String s =
		 * ItemDefinition.forId(itemToSell.getId()).getName().endsWith("s") ?
		 * ItemDefinition.forId(itemToSell.getId()).getName() :
		 * ItemDefinition.forId(itemToSell.getId()).getName() + "s";
		 * player.getPacketSender().sendMessage("You can only sell 300 "+s+
		 * " at a time."); return; }
		 */
		int itemId = itemToSell.getId();
		boolean customShop = this.getCurrency().getId() == -1;
		boolean inventorySpace = customShop ? true : false;
		if (!customShop) {
			if (!itemToSell.getDefinition().isStackable()) {
				if (!player.getInventory().contains(this.getCurrency().getId()))
					inventorySpace = true;
			}
			if (player.getInventory().getFreeSlots() <= 0
					&& player.getInventory().getAmount(this.getCurrency().getId()) > 0)
				inventorySpace = true;
			if (player.getInventory().getFreeSlots() > 0
					|| player.getInventory().getAmount(this.getCurrency().getId()) > 0)
				inventorySpace = true;
		}
		int itemValue = 0;
		if (getCurrency().getId() > 0) {
			itemValue = ItemDefinition.forId(itemToSell.getId()).getValue();
		} else {
			Object[] obj = ShopManager.getCustomShopData(id, itemToSell.getId());
			if (obj == null)
				return;
			itemValue = (int) obj[0];
		}
		if (itemValue <= 0)
			return;
		itemValue = (int) (itemValue * 0.85);
		if (itemValue <= 0) {
			itemValue = 1;
		}
		for (int i = amountToSell; i > 0; i--) {
			itemToSell = new Item(itemId);
			if (this.full(itemToSell.getId()) || !player.getInventory().contains(itemToSell.getId())
					|| !player.isShopping())
				break;
			if (!itemToSell.getDefinition().isStackable()) {
				if (inventorySpace) {
					super.switchItem(player.getInventory(), this, itemToSell.getId(), -1);
					if (!customShop) {
						player.getInventory().add(new Item(getCurrency().getId(), itemValue), false);
					} else {
						// Return points here
					}
				} else {
					player.getPacketSender().sendMessage("Please free some inventory space before doing that.");
					break;
				}
			} else {
				if (inventorySpace) {
					super.switchItem(player.getInventory(), this, itemToSell.getId(), amountToSell);
					if (!customShop) {
						player.getInventory().add(new Item(getCurrency().getId(), itemValue * amountToSell), false);
					} else {
						// Return points here
					}
					break;
				} else {
					player.getPacketSender().sendMessage("Please free some inventory space before doing that.");
					break;
				}
			}
			amountToSell--;
		}
		if (customShop) {
			player.getPointsHandler().refreshPanel();
		}
		player.getInventory().refreshItems();
		fireRestockTask();
		refreshItems();
		publicRefresh();
	}

	/**
	 * Buying an item from a shop
	 */
	@Override
	public Shop switchItem(ItemContainer to, Item item, int slot, boolean sort, boolean refresh) {
		final Player player = getPlayer();
		if (player == null)
			return this;
		if (!player.isShopping() || player.isBanking()) {
			player.getPacketSender().sendInterfaceRemoval();
			return this;
		}
		if (this.id == GENERAL_STORE) {
			if (player.getGameMode() == GameMode.IRONMAN) {
				player.getPacketSender()
						.sendMessage("Ironman-players are not allowed to buy items from the general-store.");
				return this;
			}
			if (player.getRights() == PlayerRights.YOUTUBER) {
				player.getPacketSender()
						.sendMessage("Youtubers are not allowed to buy items from the general-store.");
				return this;
			}
			if (player.getGameMode() == GameMode.HARDCORE_IRONMAN) {
				player.getPacketSender()
						.sendMessage("Hardcore-ironman-players are not allowed to buy items from the general-store.");
				return this;
			}
		}
		if (!shopSellsItem(item))
			return this;

		if (getItems()[slot].getAmount() <= 1 && id != GENERAL_STORE) {

			player.getPacketSender()
					.sendMessage("The shop can't be 1 items and needs to regenerate some items first..");

		}   

		if (item.getAmount() > getItems()[slot].getAmount())
			item.setAmount(getItems()[slot].getAmount());
		int amountBuying = item.getAmount();
		if (id == 21) { //farming cheapfix
			if (getItems()[slot].getAmount() - amountBuying <= 1) {
				amountBuying = getItems()[slot].getAmount() - 1;
				while(getItems()[slot].getAmount() - amountBuying <= 1) {
					if (getItems()[slot].getAmount() - amountBuying == 1) break;
					amountBuying--;
				}
			}
		}
		if (getItems()[slot].getAmount() < amountBuying) {
			amountBuying = getItems()[slot].getAmount() - 101;
		}
		if (amountBuying == 0)
			return this;

		if (amountBuying > 5000) {
			player.getPacketSender().sendMessage(
					"You can only buy 5000 " + ItemDefinition.forId(item.getId()).getName() + "s at a time.");
			return this;
		}
		boolean customShop = getCurrency().getId() == -1;
		boolean usePouch = false;
		int playerCurrencyAmount = 0;
		int value = ItemDefinition.forId(item.getId()).getValue();
		String currencyName = "";
		if (getCurrency().getId() == -1) {
			Object[] obj = ShopManager.getCustomShopData(id, item.getId());
			if (obj == null)
				return this;
			value = (int) obj[0];
			currencyName = (String) obj[1];
			if (id == PKING_REWARDS_STORE) {
				playerCurrencyAmount = player.getPointsHandler().getPkPoints();
			} else if (id == VOTING_REWARDS_STORE) {
				playerCurrencyAmount = player.getPointsHandler().getVotingPoints();
			} else if (id == DUNGEONEERING_STORE) {
				playerCurrencyAmount = player.getPointsHandler().getDungeoneeringTokens();
			} else if (id == DONATOR_STORE_1) {
				playerCurrencyAmount = player.getPointsHandler().getDonationPoints();
			} else if (id == TRIVIA_STORE) {
				playerCurrencyAmount = player.getPointsHandler().getTriviaPoints();
			} else if (id == LOYALTY_STORE) {
				playerCurrencyAmount = player.getPointsHandler().getLoyaltyPoints();
			} else if (id == BOSS_POINT_STORE) {
				playerCurrencyAmount = player.getBossPoints();
			} else if (id == DEADPIRATE_SHOP) {
				playerCurrencyAmount = player.getDeadPoints();
			} else if (id == PEST_CONTROL_STORE) {
				playerCurrencyAmount = player.getPointsHandler().getCommendations();
			} else if (id == DONATOR_STORE_2) {
				playerCurrencyAmount = player.getPointsHandler().getDonationPoints();
			} else if (id == PRESTIGE_STORE) {

				playerCurrencyAmount = player.getPointsHandler().getPrestigePoints();
			} else if (id == SLAYER_STORE) {
				playerCurrencyAmount = player.getPointsHandler().getSlayerPoints();
			}
		} else {
			playerCurrencyAmount = player.getInventory().getAmount(currency.getId());
			currencyName = ItemDefinition.forId(currency.getId()).getName().toLowerCase();
			if (currency.getId() == 995) {
				if (player.getMoneyInPouch() >= value) {
					playerCurrencyAmount = player.getMoneyInPouchAsInt();
					if (!(player.getInventory().getFreeSlots() == 0
							&& player.getInventory().getAmount(currency.getId()) == value)) {
						usePouch = true;
					}
				}
			} else {
				/** CUSTOM CURRENCY, CUSTOM SHOP VALUES **/
				if (id == TOKKUL_EXCHANGE_STORE || id == ENERGY_FRAGMENT_STORE || id == DEADPIRATE_SHOP || id == STARDUST_STORE || id == AGILITY_TICKET_STORE
						|| id == GRAVEYARD_STORE || id == HOLY_WATER_STORE || id == AFK_SHOP|| id == AFK_SHOP_2) {
					value = (int) ShopManager.getCustomShopData(id, item.getId())[0];
				}
			}
		}
		if (value <= 0) {
			return this;
		}
		if (!hasInventorySpace(player, item, getCurrency().getId(), value)) {
			player.getPacketSender().sendMessage("You do not have any free inventory slots.");
			return this;
		}
		if (playerCurrencyAmount <= 0 || playerCurrencyAmount < value) {
			player.getPacketSender()
					.sendMessage("You do not have enough "
							+ ((currencyName.endsWith("s") ? (currencyName) : (currencyName + "s")))
							+ " to purchase this item.");
			return this;
		}
		if (id == SKILLCAPE_STORE_1 || id == SKILLCAPE_STORE_2 || id == SKILLCAPE_STORE_3) {
			for (int i = 0; i < item.getDefinition().getRequirement().length; i++) {
				int req = item.getDefinition().getRequirement()[i];
				if ((i == 3 || i == 5) && req == 99)
					req *= 10;
				if (req > player.getSkillManager().getMaxLevel(i)) {
					player.getPacketSender().sendMessage("You need to have at least level 99 in "
							+ Misc.formatText(Skill.forId(i).toString().toLowerCase()) + " to buy this item.");
					return this;
				}
			}
		} else if (id == GAMBLING_STORE) {
			if (item.getId() == 15084 || item.getId() == 299) {
				if (player.getRights() == PlayerRights.PLAYER) {
					player.getPacketSender().sendMessage("You need to be a member to use these items.");
					return this;
				}
			}
		}

		for (int i = amountBuying; i > 0; i--) {
			if (!shopSellsItem(item)) {
				break;
			}
			if (getItems()[slot].getAmount() < amountBuying) {
				amountBuying = getItems()[slot].getAmount() - 101;

			}

			if (getItems()[slot].getAmount() <= 1 && id != GENERAL_STORE) {

				player.getPacketSender()
						.sendMessage("The shop can't be below 1 items and needs to regenerate some items first...");
				break;
			}
			if (!item.getDefinition().isStackable()) {
				if (playerCurrencyAmount >= value && hasInventorySpace(player, item, getCurrency().getId(), value)) {

					if (!customShop) {
						if (usePouch) {
							player.setMoneyInPouch((player.getMoneyInPouch() - value));
						} else {
							player.getInventory().delete(currency.getId(), value, false);
						}
					} else {
						if (id == PKING_REWARDS_STORE) {
							player.getPointsHandler().setPkPoints(-value, true);
						} else if (id == VOTING_REWARDS_STORE) {
							player.getPointsHandler().setVotingPoints(-value, true);
						} else if (id == DUNGEONEERING_STORE) {
							player.getPointsHandler().setDungeoneeringTokens(-value, true);
						} else if (id == DONATOR_STORE_1) {
							player.getPointsHandler().setDonationPoints(-value, true);
						} else if (id == BOSS_POINT_STORE) {
							player.setBossPoints(player.getBossPoints() - value);
						} else if (id == DEADPIRATE_SHOP) {
							player.setDeadPoints(player.getDeadPoints() - value);
						} else if (id == PEST_CONTROL_STORE) {
							player.getPointsHandler().setCommendations(player.getPointsHandler().getCommendations() - value);
						} else if (id == TRIVIA_STORE) {
							player.getPointsHandler().setTriviaPoints(-value, true);
						} else if (id == LOYALTY_STORE) {
							player.getPointsHandler().setLoyaltyPoints(-value, true);
						} else if (id == DONATOR_STORE_2) {
							player.getPointsHandler().setDonationPoints(-value, true);
						} else if (id == PRESTIGE_STORE) {
							player.getPointsHandler().setPrestigePoints(-value, true);
						} else if (id == SLAYER_STORE) {
							player.getPointsHandler().setSlayerPoints(-value, true);
						}
					}

					super.switchItem(to, new Item(item.getId(), 1), slot, false, false);

					playerCurrencyAmount -= value;
				} else {
					break;
				}
			} else {
				if (playerCurrencyAmount >= value && hasInventorySpace(player, item, getCurrency().getId(), value)) {

					int canBeBought = playerCurrencyAmount / (value);
					if (canBeBought >= amountBuying) {
						canBeBought = amountBuying;
					}
					if (canBeBought == 0)
						break;

					if (!customShop) {
						if (usePouch) {
							player.setMoneyInPouch((player.getMoneyInPouch() - (value * canBeBought)));
						} else {
							player.getInventory().delete(currency.getId(), value * canBeBought, false);
						}
					} else {
						if (id == PKING_REWARDS_STORE) {
							player.getPointsHandler().setPkPoints(-value * canBeBought, true);
						} else if (id == VOTING_REWARDS_STORE) {
							player.getPointsHandler().setVotingPoints(-value * canBeBought, true);
						} else if (id == DUNGEONEERING_STORE) {
							player.getPointsHandler().setDungeoneeringTokens(-value * canBeBought, true);
						} else if (id == DONATOR_STORE_1) {
							player.getPointsHandler().setDonationPoints(-value * canBeBought, true);
						} else if (id == TRIVIA_STORE) {
							player.getPointsHandler().setTriviaPoints(-value * canBeBought, true);
						} else if (id == LOYALTY_STORE) {
							player.getPointsHandler().setLoyaltyPoints(-value * canBeBought, true);
						} else if (id == BOSS_POINT_STORE) {
							player.setBossPoints(player.getBossPoints() - (value * canBeBought));
						} else if (id == DEADPIRATE_SHOP) {
							player.setDeadPoints(player.getDeadPoints() - (value * canBeBought));
						} else if (id == PEST_CONTROL_STORE) {
							player.getPointsHandler().setCommendations(player.getPointsHandler().getCommendations() - (value * canBeBought));
						} else if (id == DONATOR_STORE_2) {
							player.getPointsHandler().setDonationPoints(-value * canBeBought, true);
						} else if (id == PRESTIGE_STORE) {
							player.getPointsHandler().setPrestigePoints(-value * canBeBought, true);
						} else if (id == SLAYER_STORE) {
							player.getPointsHandler().setSlayerPoints(-value * canBeBought, true);
						}
					}
					super.switchItem(to, new Item(item.getId(), canBeBought), slot, false, false);
					playerCurrencyAmount -= value;
					break;
				} else {
					break;
				}
			}
			amountBuying--;
		}
		if (!customShop) {
			if (usePouch) {
				player.getPacketSender().sendString(8135, "" + player.getMoneyInPouch()); // Update
																							// the
																							// money
																							// pouch
			}
		} else {
			player.getPointsHandler().refreshPanel();
		}
		player.getInventory().refreshItems();
		fireRestockTask();
		refreshItems();
		publicRefresh();
		return this;
	}

	/**
	 * Checks if a player has enough inventory space to buy an item
	 * 
	 * @param item
	 *            The item which the player is buying
	 * @return true or false if the player has enough space to buy the item
	 */
	public static boolean hasInventorySpace(Player player, Item item, int currency, int pricePerItem) {
		if (player.getInventory().getFreeSlots() >= 1) {
			return true;
		}
		if (item.getDefinition().isStackable()) {
			if (player.getInventory().contains(item.getId())) {
				return true;
			}
		}
		if (currency != -1) {
			if (player.getInventory().getFreeSlots() == 0
					&& player.getInventory().getAmount(currency) == pricePerItem) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Shop add(Item item, boolean refresh) {
		super.add(item, false);
		if (id != RECIPE_FOR_DISASTER_STORE)
			publicRefresh();
		return this;
	}

	@Override
	public int capacity() {
		return 42;
	}

	@Override
	public StackType stackType() {
		return StackType.STACKS;
	}

	@Override
	public Shop refreshItems() {
		if (id == RECIPE_FOR_DISASTER_STORE) {
			RecipeForDisaster.openRFDShop(getPlayer());
			return this;
		}
		for (Player player : World.getPlayers()) {
			if (player == null || !player.isShopping() || player.getShop() == null || player.getShop().id != id)
				continue;
			player.getPacketSender().sendItemContainer(player.getInventory(), INVENTORY_INTERFACE_ID);
			player.getPacketSender().sendItemContainer(ShopManager.getShops().get(id), ITEM_CHILD_ID);
			player.getPacketSender().sendString(NAME_INTERFACE_CHILD_ID, name);
			if (player.getInputHandling() == null || !(player.getInputHandling() instanceof EnterAmountToSellToShop
					|| player.getInputHandling() instanceof EnterAmountToBuyFromShop))
				player.getPacketSender().sendInterfaceSet(INTERFACE_ID, INVENTORY_INTERFACE_ID - 1);
		}
		return this;
	}

	@Override
	public Shop full() {
		getPlayer().getPacketSender().sendMessage("The shop is currently full. Please come back later.");
		return this;
	}

	public String shopPriceEx(int shopPrice) {
		String ShopAdd = "";
		if (shopPrice >= 1000 && shopPrice < 1000000) {
			ShopAdd = " (" + (shopPrice / 1000) + "K)";
		} else if (shopPrice >= 1000000) {
			ShopAdd = " (" + (shopPrice / 1000000) + " million)";
		}
		return ShopAdd;
	}

	private boolean shopSellsItem(Item item) {
		return contains(item.getId());
	}

	public void fireRestockTask() {
		if (isRestockingItems() || fullyRestocked())
			return;
		setRestockingItems(true);
		TaskManager.submit(new ShopRestockTask(this));
	}

	public void restockShop() {
		for (int shopItemIndex = 0; shopItemIndex < getOriginalStock().length; shopItemIndex++) {
			int currentStockAmount = getItems()[shopItemIndex].getAmount();
			add(getItems()[shopItemIndex].getId(), getOriginalStock()[shopItemIndex].getAmount() - currentStockAmount);
//			publicRefresh();
			refreshItems();
		}

	}

	public boolean fullyRestocked() {
		if (id == GENERAL_STORE) {
			return getValidItems().size() == 0;
		} else if (id == RECIPE_FOR_DISASTER_STORE) {
			return true;
		}

		if (getOriginalStock() != null) {
			for (int shopItemIndex = 0; shopItemIndex < getOriginalStock().length; shopItemIndex++) {
				if (getItems()[shopItemIndex].getAmount() != getOriginalStock()[shopItemIndex].getAmount())
					return false;
			}
		}
		return true;
	}

	public static boolean shopBuysItem(int shopId, Item item) {
		if (shopId == GENERAL_STORE)
			return true;
		if (shopId == DUNGEONEERING_STORE || shopId == DEADPIRATE_SHOP|| shopId == BOSS_POINT_STORE|| shopId == PEST_CONTROL_STORE || shopId == TRIVIA_STORE || shopId == LOYALTY_STORE
				|| shopId == DONATOR_STORE_1 || shopId == DONATOR_STORE_2 || shopId == PKING_REWARDS_STORE
				|| shopId == VOTING_REWARDS_STORE || shopId == RECIPE_FOR_DISASTER_STORE || shopId == HOLY_WATER_STORE
				|| shopId == ENERGY_FRAGMENT_STORE || shopId == AGILITY_TICKET_STORE || shopId == GRAVEYARD_STORE
				|| shopId == TOKKUL_EXCHANGE_STORE || shopId == PRESTIGE_STORE || shopId == STARDUST_STORE || shopId == SLAYER_STORE || shopId == AFK_SHOP|| shopId == AFK_SHOP_2)
			return false;
		Shop shop = ShopManager.getShops().get(shopId);
		if (shop != null && shop.getOriginalStock() != null) {
			for (Item it : shop.getOriginalStock()) {
				if (it != null && it.getId() == item.getId())
					return true;
			}
		}
		return false;
	}

	public static class ShopManager {

		private static Map<Integer, Shop> shops = new HashMap<Integer, Shop>();

		public static Map<Integer, Shop> getShops() {
			return shops;
		}

		public static JsonLoader parseShops() {
			return new JsonLoader() {
				@Override
				public void load(JsonObject reader, Gson builder) {
					int id = reader.get("id").getAsInt();
					String name = reader.get("name").getAsString();
					Item[] items = builder.fromJson(reader.get("items").getAsJsonArray(), Item[].class);
					Item currency = new Item(reader.get("currency").getAsInt());
					shops.put(id, new Shop(null, id, name, currency, items));
				}

				@Override
				public String filePath() {
					return "./data/def/json/world_shops.json";
				}
			};
		}

		public static Object[] getCustomShopData(int shop, int item) {
			if (shop == VOTING_REWARDS_STORE) {
				switch (item) {
				//case 619:
					//return new Object[] { 30, "Voting Points" };
					case 10025:
					case 79:
						return new Object[] { 1, "Voting Points" };
				case 6585:
				case 19669:
				case 20072:
					return new Object[] { 30, "Voting Points" };	
				case 9470:
				case 19747:
					return new Object[] { 70, "Voting Points" };
				case 989:
					return new Object[] { 6, "Voting Points" };	
				case 13663:
				case 2572:
				case 6570:
				case 619:
					return new Object[] { 15, "Voting Points" };	
				case 7610:
				case 7611:
				case 7612:
				case 7613:
					return new Object[] { 125, "Voting Points" };	
				}
				return new Object[] { 25, "Voting Points" };
			} else if (shop == PKING_REWARDS_STORE) {
				switch (item) {
				case 13879:
				case 13883:
				case 15332:
				return new Object[] { 1, "PK Points" };
				case 2581:
				case 2577:
				case 6570:
				case 11235:
				case 4151:
				case 20072:
					return new Object[] { 10, "PK Points" };
				case 10550:
				case 10548:
				case 10551:
				case 11283:
				case 15241:
					return new Object[] { 25, "PK Points" };
				case 13887:
				case 13893:
				case 13896:
				case 13884:
				case 13890:
					return new Object[] { 50, "PK Points" };
				case 20000:
				case 20001:
				case 20002:
					return new Object[] { 60, "PK Points" };
				case 4706:
				case 11696:
				case 11698:
				case 11700:
					return new Object[] { 45, "PK Points" };
				case 11730:
				case 19111:
					return new Object[] { 30, "PK Points" };
				case 15486:
				case 15018:
				case 15020:
				case 15019:
				case 15220:
					return new Object[] { 20, "PK Points" };
				case 12926:
					return new Object[] { 100, "PK Points" };
				case 11694:
				case 19780:
				case 14484:
					return new Object[] { 65, "PK Points" };
				}
			} else if (shop == STARDUST_STORE) {
				switch (item) {
				case 13632:
				case 13633:
				case 13634:
				case 13635:
				case 13636:
				case 13637:
				case 13638:
				case 13639:
				case 13640:
					return new Object[] { 250, "Stardust" };	
				case 13641:
					return new Object[] { 500, "Stardust" };
				case 13642:
					return new Object[] { 750, "Stardust" };	
				}
				return new Object[] { 100, "Stardust Points" };
			} else if (shop == ENERGY_FRAGMENT_STORE) {
				switch (item) {
				case 5509:
					return new Object[] { 400, "energy fragments" };
				case 5510:
					return new Object[] { 750, "energy fragments" };
				case 5512:
					return new Object[] { 1100, "energy fragments" };
				}
			} else if (shop == HOLY_WATER_STORE) {
				switch (item) {
				case 6585:
				case 10551:
					return new Object[] { 500, "Holy Waters" };
				case 18932:
					return new Object[] { 15000, "Holy Waters" };
				}
			} else if(shop == AFK_SHOP) {

				switch (item) {
					case 7104:
						return new Object[] { 5_000, "Afk tickets" };
					case 7118:
						return new Object[] { 20_000, "Afk tickets" };
					case 11425:
						return new Object[] { 30_000, "Afk tickets" };
					case 7114:
						return new Object[] { 40_000, "Afk tickets" };
					case 15355:
						return new Object[] { 50_000, "Afk tickets" };
					case 11533:
						return new Object[] { 200_000, "Afk tickets" };
					case 11423:
						return new Object[] { 300_000, "Afk tickets" };
					case 20450:
					case 20451:
					case 20452:
					case 20453:
						return new Object[] { 250_000, "Afk tickets" };

				}
			} else if(shop == AFK_SHOP_2) {

				switch (item) {
					case 455:
						return new Object[] { 150_000, "Afk tickets" };
					case 5023:
						return new Object[] { 10_000_000, "Afk tickets" };
					case 11527:
						return new Object[] { 5_000, "Afk tickets" };
					case 7104:
						return new Object[] { 50_000, "Afk tickets" };
					case 20450:
					case 20451:
					case 20452:
					case 20453:
						return new Object[] { 250_000, "Afk tickets" };

				}
			} else if (shop == PEST_CONTROL_STORE) {
				switch (item) {
				case 11663:
				case 11665:
				case 11664:
					return new Object[] { 750, "Commendation Points" };
					case 8842:
						return new Object[] { 500, "Commendation Points" };
				case 8839:
				case 8840:
					return new Object[] { 1000, "CommendationCommendation Points" };

				}
			} else if (shop == BOSS_POINT_STORE) {
				switch (item) {
					case 10025:
						return new Object[] { 10, "Boss Points" };
					case 3313:
				case 3314:
				case 3315:
				case 3316:
				case 3317:
					return new Object[] { 300, "Boss Points" };	
				case 4056:
					return new Object[] { 1000, "Boss Points" };	
				case 3085:
					return new Object[] { 1000, "Boss Points" };	
				case 7118:
					return new Object[] { 300, "Boss Points" };
				case 3651:
				case 3652:
				case 3653:
				case 3654:
				case 3655:
				case 20510:
					return new Object[] { 225, "Boss Points" };
				case 3285:
				case 3286:
				case 3287:
				case 20644:
				case 20645:
					return new Object[] { 200, "Boss Points" };
				case 7114:
					return new Object[] { 500, "Boss Points" };
				case 11423:
					return new Object[] { 3500, "Boss Points" };
				case 20054:
					return new Object[] { 5000, "Boss Points" };
				case 15355:
					return new Object[] { 3000, "Boss Points" };
				case 3887:
					return new Object[] { 15000, "Boss Points" };
				}
				
				
				
			} else if (shop == DEADPIRATE_SHOP) {
				switch (item) {
				case 4780:
				case 4781:
				case 4782:
				case 4784:
				case 4785:
					return new Object[] { 1200, "hearths of the sea" };	
				case 4786:
					return new Object[] { 1500, "hearths of the sea" };	
				}
				return new Object[] { 750, "hearths of the sea" };
				
				
				
				
			} else if (shop == DONATOR_STORE_1) {
				switch (item) {
				
				//case id item id
				//return is price
				
				case 7118:
				return new Object[] { 5, "Donation Points" };
				case 7114:
					return new Object[] { 10, "Donation Points" };
				case 7120:
					return new Object[] { 15, "Donation Points" };
				case 7122:
					return new Object[] { 30, "Donation Points" };
				case 7116:
					return new Object[] { 50, "Donation Points" };
				case 11423:
					return new Object[] { 35, "Donation Points" };	
				case 455:
					return new Object[] { 5, "Donation Points" };	
				case 7104:
					return new Object[] { 7, "Donation Points" };	
				case 3576:
					return new Object[] { 50, "Donation Points" };	
				case 3084:
					return new Object[] { 40, "Donation Points" };
				case 898:
					return new Object[] { 175, "Donation Points" };
				case 897:
					return new Object[] { 175, "Donation Points" };	
				case 3246:
					return new Object[] { 175, "Donation Points" };	
				case 1667:
					return new Object[] { 400, "Donation Points" };
				case 4770:
					return new Object[] { 100, "Donation Points" };
				case 3569:
					return new Object[] { 75, "Donation Points" };
				case 3818:
					return new Object[] { 500, "Donation Points" };
				case 5000:
					return new Object[] { 400, "Donation Points" };
				case 3088:
					return new Object[] { 250, "Donation Points" };
				case 14933:
					return new Object[] { 30, "Donation Points" };
				}
				return new Object[] { 100, "Donation Points" };
			} else if (shop == DONATOR_STORE_2) {
				switch (item) {
				case 5111:
				case 5113:
				case 5112:
				case 5115:
				case 5114:
				case 5116:
				return new Object[] { 200, "Donation Points" };
				case 12421:
				return new Object[] { 15, "Donation Points" };
				case 12422:
					return new Object[] { 25, "Donation Points" };
				case 12423:
					return new Object[] { 50, "Donation Points" };
				case 12424:
					return new Object[] { 250, "Donation Points" };
				case 12425:
					return new Object[] { 500, "Donation Points" };
				case 4743:
					return new Object[] { 75, "Donation Points" };
				}
				return new Object[] { 100, "Donation Points" };
			} else if (shop == AGILITY_TICKET_STORE) {
				switch (item) {
				case 14936:
				case 14938:
					return new Object[] { 60, "agility tickets" };
				case 10941:
				case 10939:
				case 10940:
				case 10933:
					return new Object[] { 20, "agility tickets" };
				case 13661:
					return new Object[] { 100, "agility tickets" };
				}
			} else if (shop == GRAVEYARD_STORE) {
				switch (item) {
				case 20010:
				case 20011:
				case 20012:
				case 20009:
				case 20020:
				case 10551:
					return new Object[] { 500, "zombie fragments" };
				case 10548:
				case 10549:
				case 10550:
				case 11846:
				case 11848:
				case 11850:
				case 11852:
				case 11854:
				case 11856:
					return new Object[] { 200, "zombie fragments" };
				case 11842:
				case 11844:
				case 7592:
				case 7593:
				case 7594:
				case 7595:
				case 7596:
					return new Object[] { 150, "zombie fragments" };
				case 15241:
					return new Object[] { 1250, "zombie fragments" };
				case 18889:
				case 18890:
				case 18891:
				case 18965:
				
				case 16137:
				case 13045:
				case 13047:
				case 16403:
				case 16425:
				case 16955:
					return new Object[] { 1500, "zombie fragments" };
				case 1:
				case 15243:
					return new Object[] { 2, "zombie fragments" };
				}
				return new Object[] { 10000, "zombie fragments" };
			} else if (shop == TOKKUL_EXCHANGE_STORE) {
				switch (item) {
				case 11978:
					return new Object[] { 300000, "tokkul" };
				case 438:
				case 436:
					return new Object[] { 10, "tokkul" };
				case 440:
					return new Object[] { 25, "tokkul" };
				case 453:
					return new Object[] { 30, "tokkul" };
				case 442:
					return new Object[] { 30, "tokkul" };
				case 444:
					return new Object[] { 40, "tokkul" };
				case 447:
					return new Object[] { 70, "tokkul" };
				case 449:
					return new Object[] { 120, "tokkul" };
				case 451:
					return new Object[] { 250, "tokkul" };
				case 1623:
					return new Object[] { 20, "tokkul" };
				case 1621:
					return new Object[] { 40, "tokkul" };
				case 1619:
					return new Object[] { 70, "tokkul" };
				case 1617:
					return new Object[] { 150, "tokkul" };
				case 1631:
					return new Object[] { 1600, "tokkul" };
				case 6571:
					return new Object[] { 50000, "tokkul" };
				case 11128:
					return new Object[] { 22000, "tokkul" };
				case 6522:
					return new Object[] { 20, "tokkul" };
				case 6524:
				case 6523:
				case 6526:
					return new Object[] { 5000, "tokkul" };
				case 6528:
				case 6568:
					return new Object[] { 800, "tokkul" };
				}
			} else if (shop == DUNGEONEERING_STORE) {
				switch (item) {
				case 18351:
				case 18349:
				case 18353:
				case 18357:
				case 18355:
				case 18359:
				case 18361:
				case 18363:
					return new Object[] { 40000, "Dungeoneering tokens" };
				case 16955:
				case 16425:
				case 16403:
					return new Object[] { 250000, "Dungeoneering tokens" };
				case 13201:
				case 13202:
				case 13203:
				case 13204:
				case 13205:
				case 13210:
				case 13211:
				case 13212:
				case 13213:
				case 13214:
				case 13215:
					return new Object[] { 400000, "Dungeoneering tokens" };
				case 13208:
					return new Object[] { 868500, "Dungeoneering tokens" };
				case 3642:
				case 3643:
				case 3644:
				case 3645:
				case 3646:
				case 3647:
				case 3648:
				case 3649:
				case 3650:
				case 3652:
				case 3653:
				case 3654:
				case 3655:
				case 3656:
				case 3657:
				case 3658:
				case 3659:
				case 3651:
				case 3660:
				case 3661:
					
					return new Object[] { 500000, "Dungeoneering tokens" };
				case 13209:
					return new Object[] { 6000, "Dungeoneering tokens" };
				case 18335:
					return new Object[] { 75000, "Dungeoneering tokens" };
				}
			} else if (shop == LOYALTY_STORE) {
				switch (item) {
				case 13879:
				case 13883:
									return new Object[] { 300, "Loyalty Points" };
				case 15332:
									return new Object[] { 600, "Loyalty Points" };
				case 989:
									return new Object[] { 6000, "Loyalty Points" };
				case 2572:
									return new Object[] { 12000, "Loyalty Points" };
									
				case 6190:
				case 6191:
				case 6192:
				case 6193:
				case 6194:
				case 6195:
				case 6196:
									return new Object[] { 403200, "Loyalty Points" };
				}
				return new Object[] { 100, "Loyalty Points" };
				
			} else if (shop == TRIVIA_STORE) {
				switch (item) {
				case 621:
					return new Object[] { 10, "Trivia Points" };
				case 10836:
				case 10837:
				case 10838:
				case 10839:
				case 5016:
				case 10840:
					return new Object[] { 50, "Trivia Points" };
				case 19708:
				case 19707:
				case 19706:
					return new Object[] { 75, "Trivia Points" };
				case 2631:
				case 2643:
				case 2641:
				case 2639:
				case 2635:
				case 2633:
				case 2637:
				case 6857:
				case 9857:
				case 6861:
				case 6863:
					return new Object[] { 100, "Trivia Points" };	
				case 13354:
				case 9470:
				case 7673:
				case 7671:
				case 18746:
				case 18744:
				case 18745:
				case 19323:
				case 19331:
				case 19325:
					return new Object[] { 250, "Trivia Points" };
				case 15426:
					return new Object[] { 500, "Trivia Points" };
				case 1053:
				case 1055:
				case 1057:
					return new Object[] { 1000, "Trivia Points" };
				case 7606:
				case 7607:
				case 7608:
				case 7609:
					return new Object[] { 1200, "Trivia Points" };
				}
				return new Object[] { 100, "Trivia Points" };
			} else if (shop == PRESTIGE_STORE) {
				switch (item) {
				case 15501:
					return new Object[] { 20, "Prestige Points" };
				case 14018:
					return new Object[] { 90, "Prestige points" };
				case 19335:
					return new Object[] { 30, "Prestige points" };
				case 15220:
				case 15020:
				case 15019:
				case 15018:
					return new Object[] { 20, "Prestige points" };
				case 20000:
				case 20001:
				case 20002:
					return new Object[] { 50, "Prestige points" };
				case 4084:
					return new Object[] { 170, "Prestige points" };
				case 13855:
				case 13848:
				case 13856:
				case 13854:
				case 13853:
				case 13852:
				case 13851:
					case 13857:

					case 13850:
				case 13849:

					return new Object[] { 5, "Prestige points" };
				case 10400:
				case 10402:
				case 10416:
				case 10418:
				case 10408:
				case 10410:
				case 10412:
				case 10414:
				case 10404:
				case 10406:
					return new Object[] { 2, "Prestige points" };
				case 14595:
				case 14603:
					return new Object[] { 5, "Prestige points" };
				case 14602:
				case 14605:
					return new Object[] { 3, "Prestige points" };
				}
			} else if (shop == SLAYER_STORE) {
				switch (item) {
				case 13263:
					return new Object[] { 250, "Slayer points" };
				case 13281:
					return new Object[] { 5, "Slayer points" };
				case 20054:
					return new Object[] { 1000, "Slayer points" };
				case 18782:
					return new Object[] { 100, "Slayer points" };
				case 15403:
				case 11730:
				case 10887:
				case 15241:
				case 11716:
					return new Object[] { 250, "Slayer points" };
			    case 2717:
				case 20900:
					return new Object[] { 45, "Slayer points" };
				case 11550:
					return new Object[] { 350, "Slayer points" };
				case 7118:
				case 7104:
					return new Object[] { 150, "Slayer points" };
				case 15220:
				case 15020:
				case 15019:
				case 15018:
					return new Object[] { 300, "Slayer points" };
				case 5120:
				case 5121:
				case 5122:
				case 5123:
					return new Object[] { 550, "Slayer points" };

				case 11235:
				case 4151:
				case 15486:
					return new Object[] { 250, "Slayer points" };
				case 15243:
					return new Object[] { 3, "Slayer points" };
				case 10551:
					return new Object[] { 200, "Slayer points" };

				case 2581:
				case 2577:
				case 6585:
				case 11732:
					return new Object[] { 100, "Slayer points" };

				case 2572:
					return new Object[] { 250, "Slayer points" };
				}
			}
			return null;
		}
	}

	/**
	 * The shop interface id.
	 */
	public static final int INTERFACE_ID = 3824;

	/**
	 * The starting interface child id of items.
	 */
	public static final int ITEM_CHILD_ID = 3900;

	/**
	 * The interface child id of the shop's name.
	 */
	public static final int NAME_INTERFACE_CHILD_ID = 3901;

	/**
	 * The inventory interface id, used to set the items right click values to
	 * 'sell'.
	 */
	public static final int INVENTORY_INTERFACE_ID = 3823;

	/*
	 * Declared shops
	 */

	public static final int DONATOR_STORE_1 = 48;
	public static final int DONATOR_STORE_2 = 49;

	public static final int TRIVIA_STORE = 50;
	public static final int LOYALTY_STORE = 72;

	public static final int GENERAL_STORE = 12;
	public static final int RECIPE_FOR_DISASTER_STORE = 36;

	private static final int VOTING_REWARDS_STORE = 27;
	private static final int PKING_REWARDS_STORE = 26;
	private static final int ENERGY_FRAGMENT_STORE = 33;
	private static final int AGILITY_TICKET_STORE = 39;
	private static final int GRAVEYARD_STORE = 42;
	private static final int TOKKUL_EXCHANGE_STORE = 43;
	private static final int HOLY_WATER_STORE = 51;
	private static final int SKILLCAPE_STORE_1 = 8;
	private static final int SKILLCAPE_STORE_2 = 9;
	private static final int SKILLCAPE_STORE_3 = 10;
	private static final int GAMBLING_STORE = 41;
	private static final int DUNGEONEERING_STORE = 44;
	private static final int PRESTIGE_STORE = 46;
	public static final int BOSS_POINT_STORE = 92;
	public static final int DEADPIRATE_SHOP = 87;
	public static final int PEST_CONTROL_STORE = 99;
	private static final int SLAYER_STORE = 47;
	public static final int STARDUST_STORE = 55;
	public static final int AFK_SHOP = 52;
	public static final int AFK_SHOP_2 = 53;
}
