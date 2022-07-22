package com.arlania.world.content.bossEvent;

import java.util.HashMap;
import java.util.Map;

import com.arlania.model.Position;


/**
 * 
 * @author Adam_#6723
 *
 */

public enum BossEventData {
	
	Bandos_AVATAR(0, 3053, "Kill Bandos Avatar 70 Times", 70, 7112, 70, "Bandos Avatar", 2867, 9946, 0, 299, 200, "Mage/Range", 300),
	ABBADON(1, 6303, "Kill Abbadon 50 Times", 50, 7114, 40, "Abbadon", 2516, 5173, 0, 666, 80000, "Melee/Range", 699),
	INFERNAL(2, 7567, "Kill Infernal Groudon 40 Times", 40, 7116, 30, "Infernal Groudon", 1240, 1235, 0, 15, 250000, "Melee/Mage", 550),
	BAPHOMET(3, 2236, "Kill Baphomet 30 Times", 30, 7124, 20, "Baphomet", 2461, 10156, 0, 667, 325000, "Melee/Range", 500),
	Shadow_LORD(4, 2518, "Kill Shadow Lord 15 Times", 15, 7118, 2, "Shadow Lord", 2900, 3617, 0, 1000, 3500000, "Melee/Range", 1000),

	;
	
	
	
	BossEventData(int taskNumber, int npcid, String text, int endamount, int rewards,int amount, String name, int x, int y, int z, int combatLevel,
			int health, String weakness, int maxhit) {
		this.taskNumber = taskNumber;
		this.npcid = npcid;
		this.text = text;
		this.endamount = endamount;
		this.rewards = rewards;
		this.amount = amount;
		this.name = name;
		this.combatLevel = combatLevel;
		this.health = health;
		this.weakness = weakness;
		this.maxhit = maxhit;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	

	private int npcid, endamount, taskNumber, combatLevel, health, maxhit, x, y, z;
	private int rewards, amount;


	private String text, name, weakness;
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}
	
	public int getMaxhit() {
		return maxhit;
	}
	public int getAmount() {
		return amount;
	}
	public int getCombatLevel() {
		return combatLevel;
	}
	public void setCombatLevel(int combatLevel) {
		this.combatLevel = combatLevel;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public String getWeakness() {
		return weakness;
	}
	public void setWeakness(String weakness) {
		this.weakness = weakness;
	}
	
	public int getTaskNumber() {
		return taskNumber;
	}
	public int getEndamount() {
		return endamount;
	}
	public void setEndamount(int endamount) {
		this.endamount = endamount;
	}

	public String getName() {
		return name;
	}
	
	public int getNpcid() {
		return npcid;
	}
	public String getText() {
		return text;
	}
	public int getRewards() {
		return rewards;
	}

	static final Map<Integer, BossEventData> byId = new HashMap<Integer, BossEventData>();
	
	private final static BossEventData data[] = BossEventData.values();
	
	static {
		for (BossEventData e : data) {
			if (byId.put(e.getTaskNumber(), e) != null) {
				  throw new IllegalArgumentException("duplicate id: " + e.getTaskNumber());
			}
		}
	}
	
	public static BossEventData getById(int id) {
		if(byId.get(id) == null) {
			return byId.get(0);
		}
	    return byId.get(id);
	}
}
