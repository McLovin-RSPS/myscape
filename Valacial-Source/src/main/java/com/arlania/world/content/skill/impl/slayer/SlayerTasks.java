package com.arlania.world.content.skill.impl.slayer;

import com.arlania.model.Position;
import com.arlania.util.Misc;

/**
 * @author Gabriel Hannason 
 */

public enum SlayerTasks {

	NO_TASK(null, -1, null, -1, null),

	/**
	 * Easy tasks
	 */
	BOMBY(SlayerMaster.VANNAKA, 1265, "Bomby's Minions can be found in the Monster Teleport.", 2100, new Position(2660, 3045, 0)),
	HOMER(SlayerMaster.VANNAKA, 2437, "Donut Homer can be found in the Monster teleport.", 2100, new Position(1827, 4511)),
	ALIEN(SlayerMaster.VANNAKA, 1677, "Alien can be found in the Monster teleport.", 2100, new Position(3561, 9948)),
	Bowser(SlayerMaster.DURADEL, 1880, "Bowser can be found in the Monster teleport.", 6500, new Position(3169, 2982)),
	Mewtwo(SlayerMaster.DURADEL, 1459, "Mewtwo can be found in the Monster teleport.", 6500, new Position(2793, 2773)),
	/**
	 * Medium tasks
	 */
	DONKEY_KONG(SlayerMaster.DURADEL, 6102, "Donkey Kong can be found in the Monster teleport.", 6500, new Position(1747, 5323)),
	CRASH_BANDICOOT(SlayerMaster.DURADEL, 4392, "Crash Bandicoot can be found in the Monster teleport.", 6500, new Position(2704, 9756)),
	SONIC(SlayerMaster.DURADEL, 9786, "Sonic & Knuckles can be found in the Monster teleport.", 6500, new Position(2089, 4455)),
	SPYRO(SlayerMaster.DURADEL, 7843, "Spyro can be found in the Monster teleport.", 6500, new Position(3860, 2831)),
	SEPHIROTH(SlayerMaster.DURADEL, 9236, "Sephiroth can be found in the Monster teleport.", 6500, new Position(3127, 4373)),


	DONKEY_KONG_(SlayerMaster.KURADEL, 6102, "Donkey Kong can be found in the Monster teleport.", 6500, new Position(1747, 5323)),
	CRASH_BANDICOOT_(SlayerMaster.KURADEL, 4392, "Crash Bandicoot can be found in the Monster teleport.", 6500, new Position(2704, 9756)),
	SONIC_(SlayerMaster.KURADEL, 9786, "Sonic & Knuckles can be found in the Monster teleport.", 6500, new Position(2089, 4455)),
	SPYRO_(SlayerMaster.KURADEL, 7843, "Spyro can be found in the Monster teleport.", 6500, new Position(3860, 2831)),
	SEPHIROTH_(SlayerMaster.KURADEL, 9236, "Sephiroth can be found in the Monster teleport.", 6500, new Position(3127, 4373)),
	/**
	 * Hard tasks
	 */
	ABBADON(SlayerMaster.SUMONA, 6303, "Abbadon can be found in the Boss teleport.", 6500, new Position(2516, 5173)),
	INFERNAL_GROUDON(SlayerMaster.SUMONA, 7567, "Links can be found in the Boss teleport.", 6500, new Position(1240, 1233)),
	BAPHOMET(SlayerMaster.SUMONA, 2236, "Baphomet can be found in the Boss teleport.", 6500, new Position(2461, 10156)),
	UCHIHA_MADARA(SlayerMaster.SUMONA, 7532, "Uchiha Madara can be found in the Boss teleport.", 6500, new Position(2892, 2731)),
	Assassin(SlayerMaster.SUMONA, 1999, "Assassin pet can be found in the Boss teleport.", 6500, new Position(2596, 2588)),
	/**	
	 * Elite
	 */
	;

	private SlayerTasks(SlayerMaster taskMaster, int npcId, String npcLocation, int XP, Position taskPosition) {
		this.taskMaster = taskMaster;
		this.npcId = npcId;
		this.npcLocation = npcLocation;
		this.XP = XP;
		this.taskPosition = taskPosition;
	}

	private SlayerMaster taskMaster;
	private int npcId;
	private String npcLocation;
	private int XP;
	private Position taskPosition;

	public SlayerMaster getTaskMaster() {
		return this.taskMaster;
	}

	public int getNpcId() {
		return this.npcId;
	}

	public String getNpcLocation() {
		return this.npcLocation;
	}

	public int getXP() {
		return this.XP;
	}

	public Position getTaskPosition() {
		return this.taskPosition;
	}

	public static SlayerTasks forId(int id) {
		for (SlayerTasks tasks : SlayerTasks.values()) {
			if (tasks.ordinal() == id) {
				return tasks;
			}
		}
		return null;
	}

	public static int[] getNewTaskData(SlayerMaster master) {
		int slayerTaskId = 1, slayerTaskAmount = 20;
		int easyTasks = 0, mediumTasks = 0, hardTasks = 0, eliteTasks = 0;

		/*
		 * Calculating amount of tasks
		 */
		for (SlayerTasks task : SlayerTasks.values()) {
			if (task.getTaskMaster() == SlayerMaster.VANNAKA)
				easyTasks++;
			else if (task.getTaskMaster() == SlayerMaster.DURADEL)
				mediumTasks++;
			else if (task.getTaskMaster() == SlayerMaster.KURADEL)
				hardTasks++;
			else if (task.getTaskMaster() == SlayerMaster.SUMONA)
				eliteTasks++;
		}

		if (master == SlayerMaster.VANNAKA) {
			slayerTaskId = 1 + Misc.getRandom(easyTasks);
			if (slayerTaskId > easyTasks)
				slayerTaskId = easyTasks;
			slayerTaskAmount = 40 + Misc.getRandom(15);
		} else if (master == SlayerMaster.DURADEL) {
			slayerTaskId = easyTasks - 1 + Misc.getRandom(mediumTasks);
			slayerTaskAmount = 28 + Misc.getRandom(13);
		} else if (master == SlayerMaster.KURADEL) {
			slayerTaskId = 1 + easyTasks + mediumTasks + Misc.getRandom(hardTasks - 1);
			slayerTaskAmount = 37 + Misc.getRandom(20);
		} else if (master == SlayerMaster.SUMONA) {
			slayerTaskId = 1 + easyTasks + mediumTasks + hardTasks + Misc.getRandom(eliteTasks - 1);
			slayerTaskAmount = 7 + Misc.getRandom(10);
		}
		return new int[] { slayerTaskId, slayerTaskAmount };
	}
	
	@Override
	public String toString() {
		return Misc.ucFirst(name().toLowerCase().replaceAll("_", " "));
	}
}
