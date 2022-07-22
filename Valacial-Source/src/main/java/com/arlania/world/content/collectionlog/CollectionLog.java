package com.arlania.world.content.collectionlog;

import com.arlania.model.Item;
import com.arlania.model.definitions.ItemDefinition;
import com.arlania.world.entity.impl.player.Player;

import java.util.ArrayList;

public class CollectionLog {
	
	private ArrayList<CollectionLogItem> log;
	
	public CollectionLog() {
		log = new ArrayList<>();
	}
	
	public ArrayList<CollectionLogItem> getLog() {
		return this.log;
	}
	
	public static void open(Player player) {
		player.getPA().sendInterface(63000);
		if (player.collectionLogPageOpen != null) {
			loadPage(player, player.collectionLogPageOpen);
		} else {
			loadPage(player, CollectionLogPage.BOSSES);
		}
		if (player.collectionLogView != null) {
			selectLog(player, player.collectionLogView);
		} else {
			selectLog(player, CollectionLogData.Abbadon);
		}
	}
	
	public static void loadPage(Player player, CollectionLogPage page) {
		player.collectionLogPageOpen = page;
		player.getPA().sendConfig(1106, page.ordinal());
		sendButtons(player, page);
	}

	public static void onNpcKill(Player player, int npcId) {
		if (npcId < 1) {
			return;
		}
		for (CollectionLogData data : CollectionLogData.values()) {
			if (data.getNpcId() == npcId) {
				increaseCounter(player, data);
				return;
			}
		}
	}
	
	public static void checkItemDrop(Player player, int npcId, int itemId, int amount) {
		if (npcId < 1) {
			return;
		}
		if (npcId >= 2042 && npcId <= 2044) {
			npcId = 2042;
		}
		if (npcId >= 7144 && npcId <= 7146) {
			npcId = 7144;
		}
		if (npcId >= 7930 && npcId <= 7940) {
			npcId = 7930;
		}
		for (CollectionLogData data : CollectionLogData.values()) {
			if (data.getNpcId() == npcId) {
				logItem(player, data, itemId, amount);
				return;
			}
		}
	}

	public static void increaseCounter(Player player, CollectionLogData data) {
		for (CollectionLogItem cli : player.getCollectionLog().getLog()) {
			if (cli.getData() == data) {
				cli.setCounter(cli.getCounter() + 1);
				return;
			}
		}
		CollectionLogItem addLog = new CollectionLogItem(data);
		addLog.setCounter(1);
		player.getCollectionLog().getLog().add(addLog);
	}

	public static void logItem(Player player, CollectionLogData data, int item, int amount) {
		boolean valid = false;
		for (int i : data.getItems()) {
			if (i == item) {
				valid = true;
				break;
			}
		}
		if (!valid) {
			return;
		}
		for (CollectionLogItem cli : player.getCollectionLog().getLog()) {
			if (cli.getData() == data) {
				cli.addItem(item, amount);
				player.sendMessage("@red@An item was added to your collection log: " + amount + "x " + ItemDefinition.forId(item).getName() + "!");
				CollectionLogSaving.save(player);
				return;
			}
		}
		player.sendMessage("@red@An item was added to your collection log: " + amount + "x " + ItemDefinition.forId(item).getName() + "!");
		CollectionLogItem addLog = new CollectionLogItem(data);
		addLog.addItem(item, amount);
		player.getCollectionLog().getLog().add(addLog);
		CollectionLogSaving.save(player);
	}

	public static void sendButtons(Player player, CollectionLogPage page) {
		ArrayList<CollectionLogData> list = CollectionLogData.getPageList(page);

		for (int i = 0; i < 50; i++) {
			player.getPA().sendString(63151 + i, "");
		}
		for (int i = 0; i < list.size(); i++) {
			player.getPA().sendString(63151 + i, list.get(i).getName());
		}
		player.getPA().sendString(63000, String.valueOf(list.size()));
	}
	
	public static void selectLogButton(Player player, int slot) {
		ArrayList<CollectionLogData> list = CollectionLogData.getPageList(player.collectionLogPageOpen);
		if (slot > list.size() - 1) {
			return;
		}
		CollectionLogData selected = list.get(slot);
		selectLog(player, selected);
	}

	public static void selectLog(Player player, CollectionLogData selected) {
		player.getPA().sendString(63015, selected.getName());

		int[] itemIds = selected.getItems();
		int total = itemIds == null ? 0 : itemIds.length;
		int counter = getCounter(player.getCollectionLog(), selected);
		if (selected.getType() == 0) {
			player.getPA().sendString(63017, selected.getName() + " kills: " + counter);
		} else {
			if (selected.getCounterText() != null) {
				player.getPA().sendString(63017, selected.getCounterText() + ": " + counter);
			} else {
				player.getPA().sendString(63017, "");
			}
		}
		player.collectionLogView = selected;
		ArrayList<Item> items = new ArrayList<>();
		if (itemIds != null) {
			for (int i : itemIds) {
				items.add(new Item(i, getLogItemAmount(player, selected, i)));
			}
		}
		
		int obtained = 0;
		for (Item gi : items) {
			if (gi.getAmount() > 0) {
				obtained++;
			}
		}
		player.getPA().sendString(63016, "Obtained: " + obtained + "/" + total);

		for (int i = 0; i < 100; i++) {
			player.getPA().sendItemOnInterface(63026, -1, i, 0);
		}

		for (int i = 0; i < items.size(); i++) {
			player.getPA().sendItemOnInterface(63026, items.get(i).getId(), i, items.get(i).getAmount());
		}
	}

	public static int getCounter(CollectionLog cl, CollectionLogData data) {
		for (CollectionLogItem i : cl.getLog()) {
			if (i.getData() == data) {
				return i.getCounter();
			}
		}
		return 0;
	}
	
	public static int getLogObtained(CollectionLog cl, CollectionLogData data) {
		for (CollectionLogItem i : cl.getLog()) {
			if (i.getData() == data) {
				return i.getItems().size();
			}
		}
		return 0;
	}

	public static int getLogItemAmount(Player player, CollectionLogData data, int item) {
		for (CollectionLogItem cli : player.getCollectionLog().getLog()) {
			if (cli.getData() == data) {
				for (int i = 0; i < cli.getItems().size(); ++i) {
					if (cli.getItems().get(i).getId() == item) {
						return cli.getItems().get(i).getAmount();
					}
				}
			}
		}
		return 0;
	}

	public static boolean clickButton(Player player, int button) {
		CollectionLogPage page = CollectionLogPage.forButton(button);
		if (page != null) {
			loadPage(player, page);
			return true;
		}
		if (button <= -2460 && button >= -2485) {
			selectLogButton(player, button + 2485);
			return true;
		}
		return false;
	}
	
}
