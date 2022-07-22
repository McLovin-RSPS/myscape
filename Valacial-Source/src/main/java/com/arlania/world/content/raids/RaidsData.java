package com.arlania.world.content.raids;

import java.util.HashMap;
import java.util.Map;


/**
 * 
 * @author Adam_#6723
 *
 */

public enum RaidsData {
	
	WAVE_1(0, RaidsWaveType.FIRST_WAVE, 100, "Wave 1", 10,10,0),
	;
	
	
	

	RaidsData(int taskNumber, RaidsWaveType type, int npcid, String text, int x, int y, int z) {
		this.taskNumber = taskNumber;
		this.npcid = npcid;
		this.text = text;
		this.x = x;
		this.y = y;
		this.z = z;
		this.type = type;
	}
	

	private int npcid, taskNumber, x, y, z;
	private String text, name;
	private RaidsWaveType type;

	
	public RaidsWaveType getType() {
		return type;
	}

	public void setType(RaidsWaveType type) {
		this.type = type;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}
	
	
	
	public int getTaskNumber() {
		return taskNumber;
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

	static final Map<Integer, RaidsData> byId = new HashMap<Integer, RaidsData>();
	
	private final static RaidsData data[] = RaidsData.values();
	
	static {
		for (RaidsData e : data) {
			if (byId.put(e.getTaskNumber(), e) != null) {
				  throw new IllegalArgumentException("duplicate id: " + e.getTaskNumber());
			}
		}
	}
	
	public static RaidsData getById(int id) {
		if(byId.get(id) == null) {
			return byId.get(0);
		}
	    return byId.get(id);
	}
}
