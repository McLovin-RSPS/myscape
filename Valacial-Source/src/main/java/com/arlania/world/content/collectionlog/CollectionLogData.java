package com.arlania.world.content.collectionlog;

import java.util.ArrayList;

public enum CollectionLogData {

	// Bosses
	Abbadon(0, "Abbadon", new int[] { 3313, 3314, 3315, 3316, 3317 }),
	LINK(0, "Link", new int[] { 3318, 3319, 3320, 3321, 3322 }),
	BAPHOMET(0, "Baphomet", new int[] { 3819, 3820, 3821, 3822, 3823, 3824, 3825 }),
	MADARA(0, "Madara", new int[] { 4056 }),
	ASSASSIN(0, "Assassin", new int[] { 3076, 3077, 3289, 3290, 3291, 3292, 3293, 3294 }),
	DeadBONEGUARDIAN(0, "Bone Guardian", new int[] { 5131, 5132, 5133, 5135, 5136, 5137, 5138, 2717 }),
	BLOODLORD(0, "Blood Lord", new int[] { 3809, 3810, 3811, 3812, 3813, 3814 }),
	SAMURI(0, "Samurai", new int[] { 925, 923, 924, 927, 926, 922}),
	Thanos(0, "Thanos", new int[] { 4060, 4629, 4630, 4631, 4632, 4633, 4634 }),
	KING(0, "King", new int[] { 931, 932, 933, 934, 929 }),
	BARREL(0, "Barrelchest", new int[] { 4057, 4058, 4059, 937 }),
	CHOAS(0, "Chaos Elemental", new int[] { 911, 910, 909, 912, 913, 903 }),
	DEVIL(0, "Devil Beast", new int[] { 4774, 4775, 4776, 4777, 4779 }),
	BLESSED(0, "Blessed God", new int[] { 5083, 5085, 5086, 5087, 5088, 5089, 5084 }),
	ROSE(0, "RoseEater", new int[] { 5140, 5142, 5141, 5143, 5144, 5146, 5147, 5148, }),
	JOKER(0, "Joker", new int[] { 13728, 13729, 13730, 13731, 13732, 13733}),
	DARTH(0, "Darth Vader", new int[] { 3078, 3079, 3080, 3081, 3086, 3087 }),
	CLOUD(0, "Cloud", new int[] { 13265, 13266, 13268, 13269, 13270, 13271}),
	Sora(0, "Sora", new int[] { 4765, 4766, 4767, 4768, 4769, 4771, 4772 }),
	MYSTERIO(0, "Mysterio", new int[] { 5199, 5200, 5201, 5202, 5203, 5206 }),
	WHITEBEARD(0, "Whitebeard", new int[] { 8893, 8894, 8895, 8896, 8897, 8898, 8899}),
	MAGMA(0, "Magma Satanic Devil", new int[] { 14934, 14935, 10905, 10906, 10907 }),
// FIND BOMBY
	// Raids
	RAIDS_EXAMPLE(1, "Raids Example", new int[] { 4151 }),

	// Minigames
	MINIGAMES_EXAMPLE(3, "Minigames Example", new int[] { 4151 }),

	// Other
	OTHER_EXAMPLE(4, "Other Example", new int[] { 4151 }),

	;

	private final int type;
	private final String name;
	private final int[] items;
	private final String counterText;
	private final int npcId;
	
	private CollectionLogData(int pageIndex, String name, int[] items) {
		this.type = pageIndex;
		this.name = name;
		this.items = items;
		this.counterText = null;
		this.npcId = -1;
	}
	
	private CollectionLogData(int pageIndex, String name, int[] items, int npcId) {
		this.type = pageIndex;
		this.name = name;
		this.items = items;
		this.counterText = null;
		this.npcId = npcId;
	}
	
	private CollectionLogData(int pageIndex, String name, String counterText, int[] items) {
		this.type = pageIndex;
		this.name = name;
		this.items = items;
		this.counterText = counterText;
		this.npcId = -1;
	}
	
	private CollectionLogData(int pageIndex, String name, String counterText, int[] items, int npcId) {
		this.type = pageIndex;
		this.name = name;
		this.items = items;
		this.counterText = counterText;
		this.npcId = npcId;
	}
	
	private CollectionLogData(int pageIndex, String name) {
		this.type = pageIndex;
		this.name = name;
		this.items = null;
		this.counterText = null;
		this.npcId = -1;
	}
	
	private CollectionLogData(int pageIndex, String name, String counterText) {
		this.type = pageIndex;
		this.name = name;
		this.items = null;
		this.counterText = counterText;
		this.npcId = -1;
	}

	public static ArrayList<CollectionLogData> getPageList(CollectionLogPage page) {
		ArrayList<CollectionLogData> list = new ArrayList<>();
		for (CollectionLogData data : values()) {
			if (data.getType() == page.ordinal()) {
				list.add(data);
			}
		}
		return list;
	}
	
	public int[] getItems() {
		return this.items;
	}
	
	public int getNpcId() {
		return this.npcId;
	}

	public int getType() {
		return this.type;
	}

	public String getName() {
		return this.name;
	}
	
	public String getCounterText() {
		return this.counterText;
	}
	
}
