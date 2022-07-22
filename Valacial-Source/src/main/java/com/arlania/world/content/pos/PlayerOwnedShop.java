package com.arlania.world.content.pos;

import com.arlania.GameServer;
import com.arlania.model.definitions.ItemDefinition;
import com.arlania.net.packet.Packet.PacketType;
import com.arlania.net.packet.PacketBuilder;
import com.arlania.world.World;
import com.arlania.world.entity.impl.player.Player;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a single player owned shop. In this
 * we hold and manage all the items that are added or sold
 * using an instance of this class. A single instance of this
 * class shows a single player owned shop in the manager class.
 * 
 * @author Nick Hartskeerl <apachenick@hotmail.com>
 *
 */
public class PlayerOwnedShop {

	/**
	 * The total capacity of items a shop can contain.
	 */
	public static final int SHOP_CAPACITY = 32;
	
	/**
	 * A collection of all the items in this player owned
	 * shop. If an item slot is empty this is represented as
	 * {@code null}, else as an {@link ShopItem} instance.
	 */
	private ShopItem[] shopItems = new ShopItem[SHOP_CAPACITY];
	
	/**
	 * A reference to the player owning this shop. We use this
	 * reference to notify the shop owner of certain events.
	 */
	private Player owner;
	
	/**
	 * The name of the player owning this player owned shop.
	 */
	private String username;

	private long earnings;
	/**
	 * 
	 */
	private long lastUpdated;
	
	public void open(Player player) {
		player.getPacketSender().sendString(32610, "Player Owned Shop - "+username);
		player.getPacketSender().sendString(32611, "Search");
		player.getPlayerOwnedShopManager().updateFilter("");
		resetItems(player);
		refresh(player, false);
	}
	
	public void refresh(Player player, boolean myShop) {
		
		for(int i = 0; i < shopItems.length; i++) {
			
			ShopItem shopItem = shopItems[i];
			
			PacketBuilder out = new PacketBuilder(34, PacketType.SHORT);
			
			out.putShort(myShop ? 33621 : 32621);
			out.put(i);
			out.putShort(shopItem == null ? 0 : shopItem.getId() + 1);
			
			if(shopItem != null && shopItem.getAmount() > 254) {
				out.put(255);
				out.putInt(shopItem.getAmount());
			} else {
				out.put(shopItem == null ? 0 : shopItem.getAmount());
			}
			
			player.getSession().queueMessage(out);
			
		}
		
	}
	
	public void add(int id, int amount) {
		
		ItemDefinition definition = ItemDefinition.forId(id);
		int price = 0;
		
		if(definition != null) {
			price = definition.getValue();
		}
		
		add(id, amount, price);
		
	}
	
	public void add(int id, int amount, int price) {
		add(new ShopItem(id, amount, price));
		refreshAll();
		save();
	}
	
	public void add(ShopItem shopItem) {
		
		for(int i = 0; i < shopItems.length; i++) {
			if(shopItems[i] != null && shopItems[i].getId() == shopItem.getId()) {
				shopItems[i].setAmount(shopItems[i].getAmount() + shopItem.getAmount());
				return;
			}
		}
		
		int index = freeSlot();
		
		if(index != -1) {
			if(shopItems[index] == null) {
				shopItems[index] = shopItem;
			}
		}
		
	}
	
	public int remove(int slot, int amount) {
		
		ShopItem shopItem = getItem(slot);
		int removed = -1;
		
		if(shopItem != null) {
			if(amount >= shopItem.getAmount()) {
				shopItems[slot] = null;
				shift();
				removed = shopItem.getAmount();
			} else {
				shopItem.setAmount(shopItem.getAmount() - amount);
				removed = amount;
			}
			save();
			refreshAll();
		}
		
		return removed;
		
	}
	
	public void shift() {
		
		List<ShopItem> temp = new ArrayList<>();
		
		for(ShopItem shopItem : shopItems) {
			if(shopItem != null) {
				temp.add(shopItem);
			}
		}
		
		shopItems = temp.toArray(new ShopItem[SHOP_CAPACITY]);
		
	}
	
	public int freeSlot() {
		for(int i = 0; i < shopItems.length; i++){
			if(shopItems[i] == null) {
				return i;
			}
		}
		return -1;
	}
	
	public ShopItem getItem(int slot) {
		return shopItems[slot];
	}
	
	public int getAmount(int id) {
		for(int i = 0; i < shopItems.length; i++){
			if(shopItems[i] != null && shopItems[i].getId() == id) {
				return shopItems[i].getAmount();
			}
		}
		return 0;
	}
	
	public boolean contains(int id) {
		for(int i = 0; i < shopItems.length; i++){
			if(shopItems[i] != null && shopItems[i].getId() == id) {
				return true;
			}
		}
		return false;
	}
	
	public boolean contains(String name) {
		
		if(name == null) {
			return false;
		}
		
		for(int i = 0; i < shopItems.length; i++){
			if(shopItems[i] != null && shopItems[i].getDefinition() != null && shopItems[i].getDefinition().getName() != null && shopItems[i].getDefinition().getName().toLowerCase().contains(name.toLowerCase())) {
				return true;
			}
		}
		return false;
		
	}
	
	public int size() {
		int size = 0;
		for(int i = 0; i < shopItems.length; i++){
			if(shopItems[i] != null) {
				size++;
			}
		}
		return size;
	}
	
	public void refreshAll() {
		for(Player player : World.getPlayers()) {
			if(player != null && player.getPlayerOwnedShopManager().getCurrent() == this) {
				refresh(player, player.getPlayerOwnedShopManager().getMyShop() == this);
			}
		}
	}
	
	public static void resetItems(Player player) {
		
		for(int i = 0; i < SHOP_CAPACITY; i++) {
			
			PacketBuilder out = new PacketBuilder(34, PacketType.SHORT);
			
			out.putShort(32621);
			out.put(i);
			out.putShort(0);
			out.put(0);
			
			player.getSession().queueMessage(out);
			
		}
		
	}

	public void save() {
		long saveEarnings = this.earnings;
		ShopItem[] saveItems = getShopItems().clone();
		String saveUsername = getUsername();

		GameServer.getLoader().getEngine().submit(() -> {
			Path path = Paths.get(PlayerOwnedShopManager.DIRECTORY + File.separator, saveUsername + ".txt");

			if (Files.notExists(path)) {
				try {
					Files.createDirectories(path.getParent());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
				writer.write(Long.toString(saveEarnings));
				writer.newLine();
				for(ShopItem item : saveItems) {
					if(item != null) {
						writer.write(item.getId()+" - "+item.getAmount()+" - "+item.getPrice());
						writer.newLine();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Get a reference to a collection of all the items in
	 * this player owned shop. If an item slot is empty it
	 * will be shown as {@code null}, else as a {@link ShopItem}
	 * instance.
	 * @return The array of items in this player owned shop.
	 */
	public ShopItem[] getShopItems() {
		return shopItems;
	}

	/**
	 * Set a new array of items to represent the collection
	 * of all items in this player owned shop. If an item
	 * slot is empty it must be shown as {@code null}, else
	 * as a {@link ShopItem} instance.
	 * @param shopItems The new array of items for this shop.
	 */
	public void setShopItems(ShopItem[] shopItems) {
		this.shopItems = shopItems;
	}

	public long getEarnings() {
		return this.earnings;
	}

	public void setEarnings(long earnings) {
		this.earnings = earnings;
	}

	public void addEarnings(long toAdd) {
		this.earnings += toAdd;
	}

	/**
	 * Get the reference to the player instance of the owner
	 * of this shop. It is important to notice that with this
	 * reference the player instance can refer to an offline
	 * player. If you would like to gain access to the player
	 * owning this shop while this player is online or offline
	 * use the  method instead.
	 * @return A reference to the player owning this shop.
	 */
	public Player getOwner() {
		return owner;
	}

	/**
	 * 
	 * @param owner
	 */
	public void setOwner(Player owner) {
		this.owner = owner;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public long getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(long lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public static class ShopItem
	{
		
		private int id;
		
		private int amount;
		
		private int price;

		public ShopItem(int id, int amount) {
			this.id = id;
			this.amount = amount;
		}
		
		public ShopItem(int id, int amount, int price) {
			this(id, amount);
			this.price = price;
		}

		public int getPrice() {
			return price;
		}

		public void setPrice(int price) {
			this.price = price;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getAmount() {
			return amount;
		}

		public void setAmount(int amount) {
			this.amount = amount;
		}
		
		public ItemDefinition getDefinition() {
			return ItemDefinition.forId(id);
		}

	}

	
}
