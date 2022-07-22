package com.arlania.world.content.skill.impl.prayer;

public enum BonesData {
	 BONES(526, 1355),
	 BAT_BONES(530, 1455),
	 WOLF_BONES(2859, 1555),
	 BIG_BONES(532, 2680),
	 FEMUR_BONES(15182, 3501),
	 BABYDRAGON_BONES(534, 4865),
	 JOGRE_BONE(3125, 5130),
	 ZOGRE_BONES(4812, 5344),
	 LONG_BONES(10976, 5551),
	 CURVED_BONE(10977, 5651),
	 SHAIKAHAN_BONES(3123, 5857),
	 DRAGON_BONES(536, 8750),
	 FAYRG_BONES(4830, 9981),
	 RAURG_BONES(4832, 9957),
	 DAGANNOTH_BONES(6729, 10890),
	 OURG_BONES(14793, 10985),
	 FROSTDRAGON_BONES(18830, 14870),
	 YODA(20200, 10870),
	 GODZILLA(20201, 14870),
	 DONKEY(20202, 16870),
	 DEADLY(20203, 17870),
	 OBLIVION(20204, 19870),
	ABB(20205, 20070),
	NEOCORTEX(20208, 20070),
	BANDOSAVA(20206, 22870),
	 INFERNALGROUDON(20207, 25870),
	 BAP(20210, 26000),
	 CHAOS(4062, 34300),
	 KRT(4063, 36000),
	 CORP(4064, 31000),
	 THANOS(4065, 30900),
	 INFI(4066, 30000),
	 SEPH(3271, 29000),
	 LUCI(3272, 28500),
	 MADARA(3260, 27000),
	 KUNA(1666, 20500),
	 BARREL(3063, 32300);

	BonesData(int boneId, int buryXP) {
		this.boneId = boneId;
		this.buryXP = buryXP;
	}

	private int boneId;
	private int buryXP;
	
	public int getBoneID() {
		return this.boneId;
	}
	
	public int getBuryingXP() {
		return this.buryXP;
	}
	
	public static BonesData forId(int bone) {
		for(BonesData prayerData : BonesData.values()) {
			if(prayerData.getBoneID() == bone) {
				return prayerData;
			}
		}
		return null;
	}
	
}
