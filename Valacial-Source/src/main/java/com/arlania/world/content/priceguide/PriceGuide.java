package com.arlania.world.content.priceguide;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.arlania.model.definitions.ItemDefinition;
import com.arlania.world.World;
import com.arlania.world.entity.impl.player.Player;

public class PriceGuide {

    /**
     * Items with prices
     */
    public static Map<Integer, Long> prices = new HashMap<>();

    static {
	loadData();
    }
    
    /**
     * Gets price of Item by using itemId
     * 
     * @param itemId
     * @return price of Item name
     */
    public static long getPrice(int itemId) {
	return ItemDefinition.forId(itemId).getPriceGuideValue();
    }

    /**
     * Gets price of Item by using itemId
     * 
     * @param c
     * @param itemId
     * @return price of Item name
     */
    public static long getPrice(Player c, int itemId) {
	return ItemDefinition.forId(itemId).getPriceGuideValue();
    }

    /**
     * Gets price of Item by using itemName
     * 
     * @param c
     * @param itemName
     * @return price of Item name
     */
    
    public static long getPrice(Player c, String itemName) {
	return ItemDefinition.forId(ItemDefinition.getItemId(itemName)).getPriceGuideValue();
    }

    /**
     * Loads data onto HashMap to be used anytime.
     */
    public static void loadData() {
	try {
	    File file = new File("./data/itemPrices.txt");
	    if (!file.exists())
		return;
	    BufferedReader in = new BufferedReader(new FileReader(file));
	    String data = null;
	    try {
		while ((data = in.readLine()) != null) {
		    String args[] = data.split(":");
		    prices.put(Integer.parseInt(args[0]), Long.parseLong(args[1]));
		    System.err.println("ItemID: " + args[0] + " Price: " + args[1] + "B.");
		}
	    } finally {
		in.close();
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    /**
     * Add item with <id> and price <price>
     * 
     * @param id
     *            ID of Item
     * @param price
     *            Price of Item
     */
    public static void priceItem(Player c, int id, long price) {
	if (prices.containsKey(id)) {
	    c.sendMessage("<col=255>Price of " + ItemDefinition.getItemName(id) + " has been changed from @red@"
		    + prices.get(id) + "B<col=255> to @red@" + price + "B <col=255>.");
	} else {
	    World.sendStaffMessage("<col=255>Price of " + ItemDefinition.getItemName(id) + " has been set to @red@" + price + "B<col=255>.");
	}
	prices.put(id, price);
	prices = prices.entrySet().stream()
		.sorted(Map.Entry
			.comparingByValue(/* Collections.reverseOrder() */))
		.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	saveData();
    }

    /**
     * Saves data from HashMap to be used anytime.
     */
    public static void saveData() {
	try {
	    File file = new File("./Data/itemPrices.txt");
	    file.getParentFile().mkdirs();
	    if (!file.exists())
		file.createNewFile();
	    BufferedWriter in = new BufferedWriter(new FileWriter(file));
	    try {
		for (Entry<Integer, Long> price : prices.entrySet()) {
		    in.write(price.getKey() + ":" + price.getValue());
		    in.newLine();
		}
	    } finally {
		in.close();
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

}