package com.arlania.world.content.minigames.impl;

import com.arlania.engine.task.Task;
import com.arlania.engine.task.TaskManager;
import com.arlania.model.Locations;
import com.arlania.model.Locations.Location;
import com.arlania.model.Position;
import com.arlania.model.RegionInstance;
import com.arlania.model.RegionInstance.RegionInstanceType;
import com.arlania.world.World;
import com.arlania.world.entity.impl.npc.NPC;
import com.arlania.world.entity.impl.player.Player;

public class ExecutiveZone {

	public static final int DRAGON = 6433;
	public static final int SKOTIZO = 8754;
	public static final int ABBADON = 6303;
	public static final int GROUDON = 7567;
	public static final int BAPHOMET = 2236;
	public static final int LUCIFER = 1999;
	public static final int MADARA = 7532;
	public static final int INFINITY = 7563;
	public static final int THANOS = 9202;
	public static final int BEAST = 8133;
	public static final int BARREL = 5666;
	public static final int CHAOS = 3200;
	public static final int DIGLET = 6442;
	public static final int AKUNA = 7843;
	public static final int LUNARIAN = 9236;

	public static void enterZone(Player player) {// try this.
		player.setRegionInstance(new RegionInstance(player, RegionInstanceType.EXECUTIVE_ZONE));
		System.out.println("entering zone 0...");
		spawnZoneMonsters(player);
	}

	public static void leaveZone(Player player) { // i
		System.out.println("coming from="+new Throwable().getStackTrace()[1].toString());
		System.out.println("leaving zone 3...");
			player.moveTo(new Position(3817, 3484));
			player.getCombatBuilder().reset(true);
			player.getPacketSender().sendMessage("@blu@You have left the Executive Zone " + player.getUsername());			
			if (player.getRegionInstance() != null && player.getRegionInstance().getNpcsList() != null) {
				System.err.println("destroying zone..");
				player.getRegionInstance().destruct();
		} else
			System.out.println("not destroying zone..");
			player.restart();
	}

	public static void spawnZoneMonsters(final Player player) {
		TaskManager.submit(new Task(2, player, false) {
			@Override
			public void execute() {
				if (player.getRegionInstance() == null || !player.isRegistered()) {
					stop();
					return;
				}// chill 1 sec ill be quick loading up my eclipse
				System.out.println("spawned zone monsters 2..");
				player.getRegionInstance().spawnNPC(new NPC(DRAGON, new Position(2915, 2805, player.getPosition().getZ())).setSpawnedFor(player));
				player.getRegionInstance().spawnNPC(new NPC(SKOTIZO, new Position(2904, 2805, player.getPosition().getZ())).setSpawnedFor(player));
				player.getRegionInstance().spawnNPC(new NPC(ABBADON, new Position(2904, 2796, player.getPosition().getZ())).setSpawnedFor(player));
				player.getRegionInstance().spawnNPC(new NPC(GROUDON, new Position(2904, 2787, player.getPosition().getZ())).setSpawnedFor(player));
				player.getRegionInstance().spawnNPC(new NPC(BAPHOMET, new Position(2904, 2779, player.getPosition().getZ())).setSpawnedFor(player));			player.getRegionInstance().spawnNPC(new NPC(MADARA, new Position(2911, 2779, player.getPosition().getZ())).setSpawnedFor(player));
				player.getRegionInstance().spawnNPC(new NPC(INFINITY, new Position(2920, 2779, player.getPosition().getZ())).setSpawnedFor(player));
				player.getRegionInstance().spawnNPC(new NPC(THANOS, new Position(2927, 2779, player.getPosition().getZ())).setSpawnedFor(player));
				player.getRegionInstance().spawnNPC(new NPC(BEAST, new Position(2927, 2786, player.getPosition().getZ())).setSpawnedFor(player));
				player.getRegionInstance().spawnNPC(new NPC(BARREL, new Position(2927, 2783, player.getPosition().getZ())).setSpawnedFor(player));
				player.getRegionInstance().spawnNPC(new NPC(DIGLET, new Position(2927, 2795, player.getPosition().getZ())).setSpawnedFor(player));
				player.getRegionInstance().spawnNPC(new NPC(LUCIFER, new Position(2927, 2804, player.getPosition().getZ())).setSpawnedFor(player));
				player.getRegionInstance().spawnNPC(new NPC(AKUNA, new Position(2921, 2792, player.getPosition().getZ())).setSpawnedFor(player));
				player.getRegionInstance().spawnNPC(new NPC(AKUNA, new Position(2921, 2791, player.getPosition().getZ())).setSpawnedFor(player));
				player.getRegionInstance().spawnNPC(new NPC(AKUNA, new Position(2921, 2790, player.getPosition().getZ())).setSpawnedFor(player));
				player.getRegionInstance().spawnNPC(new NPC(AKUNA, new Position(2921, 2789, player.getPosition().getZ())).setSpawnedFor(player));
				player.getRegionInstance().spawnNPC(new NPC(AKUNA, new Position(2921, 2788, player.getPosition().getZ())).setSpawnedFor(player));
				player.getRegionInstance().spawnNPC(new NPC(LUNARIAN, new Position(2910, 2788, player.getPosition().getZ())).setSpawnedFor(player));
				player.getRegionInstance().spawnNPC(new NPC(LUNARIAN, new Position(2910, 2789, player.getPosition().getZ())).setSpawnedFor(player));
				player.getRegionInstance().spawnNPC(new NPC(LUNARIAN, new Position(2910, 2790, player.getPosition().getZ())).setSpawnedFor(player));
				player.getRegionInstance().spawnNPC(new NPC(LUNARIAN, new Position(2910, 2791, player.getPosition().getZ())).setSpawnedFor(player));
				player.getRegionInstance().spawnNPC(new NPC(LUNARIAN, new Position(2910, 2792, player.getPosition().getZ())).setSpawnedFor(player));
				
				stop();
			}
		});
	}
}
