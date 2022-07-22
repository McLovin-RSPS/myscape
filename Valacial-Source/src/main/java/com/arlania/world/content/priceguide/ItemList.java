package com.arlania.world.content.priceguide;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.Stream;

import com.arlania.GameSettings;
import com.arlania.model.container.impl.Equipment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class ItemList {

    /**
     * Item List
     **/

    private static final ItemList ITEM_DEFINITIONS[] = new ItemList[GameSettings.ITEM_LIMIT];

    private final int itemId;
    public final String name;
    public final String description;
    public double shopValue;
    public final double lowAlch;
    public final double highAlch;
    private boolean stackable;
    private boolean noted;
    private int equipSlot = 3;
    private int relativeId;
    private int[] bonuses = new int[12];
    private int[] requirements = new int[25];
    private long priceGuideValue;

    public ItemList(int id, String name, String description, double shopValue, double lowAlch, double highAlch,
	    int[] bonuses) {
	itemId = id;
	this.name = name;
	this.description = description;
	this.shopValue = shopValue;
	this.lowAlch = lowAlch;
	this.highAlch = highAlch;
	this.bonuses = bonuses;
    }


    public ItemList(int id) {
	this.itemId = id;
	this.name = "Null";
	this.description = "Null";
	this.shopValue = 0;
	this.lowAlch = 0;
	this.highAlch = 0;
	this.stackable = false;
	this.noted = false;
	this.equipSlot = 3;
	this.relativeId = 0;
	this.bonuses = new int[12];
	this.requirements = new int[25];
    }
    
    private static final String[] ITEM_BONUS_NAMES = { "STAB_BONUS", "SLASH_BONUS", "CRUSH_BONUS", "MAGIC_BONUS",
	    "RANGED_BONUS", "STAB_DEFENSE_BONUS", "SLASH_DEFENSE_BONUS", "CRUSH_DEFENSE_BONUS", "MAGIC_DEFENSE_BONUS",
	    "RANGE_DEFENSE_BONUSE", "STRENGTH_BONUS", "PRAYER_BONUS" };

    private static class ItemDefinitionObject {
	private final String name;
	private final int id;
	private final int amount;

	public ItemDefinitionObject(String name, int id, int amount) {
	    this.id = id;
	    this.name = name;
	    this.amount = amount;
	}
    }

    public int getId() {
	return itemId;
    }

    public String getName() {
	return name;
    }

    public String getDescription() {
	return description;
    }

    public double getShopValue() {
	return shopValue;
    }

    public double getLowAlchValue() {
	return lowAlch;
    }

    public double getHighAlchValue() {
	return highAlch;
    }

    public int[] getBonuses() {
	return bonuses;
    }

    public int getBonus(int index) {
	if (index < 0 || index >= bonuses.length) {
	    return 0;
	}
	return bonuses[index];
    }

    public void setShopValue(int value) {
	this.shopValue = value;
    }

    public boolean isStackable() {
	return stackable;
    }

    public boolean isNoted() {
	return noted;
    }

    public int getEquipSlot() {
	return equipSlot;
    }

    public int getRelativeId() {
	return relativeId;
    }

    public int[] getRequirements() {
	return requirements;
    }

    public int getRequirement(int index) {
	if (index < 0 || index >= requirements.length) {
	    return 0;
	}
	return requirements[index];
    }


    public static ItemList get(int id) {
	if (id < 0 || id >= ITEM_DEFINITIONS.length || ITEM_DEFINITIONS[id] == null) {
	    return new ItemList(id);
	}
	return ITEM_DEFINITIONS[id];
    }

    public long getPriceGuideValue() {
	return priceGuideValue;
    }

    public static ItemList[] get() {
	return ITEM_DEFINITIONS;
    }
    
}
