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

public class SponsorZone {

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

	public static void enterZone(Player player) {// try this.
		player.setRegionInstance(new RegionInstance(player, RegionInstanceType.SPONSOR_ZONE));
		System.out.println("entering zone 0...");
		spawnZoneMonsters(player);
	}

	public static void leaveZone(Player player) { // i
		System.out.println("coming from="+new Throwable().getStackTrace()[1].toString());
		System.out.println("leaving zone 3...");
			player.moveTo(new Position(3817, 3484));
			player.getCombatBuilder().reset(true);
			player.getPacketSender().sendMessage("@blu@You have left the Sponsor Zone " + player.getUsername());			
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
				}
				System.out.println("spawned zone monsters 2..");
			    player.getRegionInstance().spawnNPC(new NPC(DIGLET, new Position(2714, 2993, player.getPosition().getZ())).setSpawnedFor(player));
			    player.getRegionInstance().spawnNPC(new NPC(ABBADON, new Position(2701, 2992, player.getPosition().getZ())).setSpawnedFor(player));
			    player.getRegionInstance().spawnNPC(new NPC(GROUDON, new Position(2702, 2980, player.getPosition().getZ())).setSpawnedFor(player));
			    player.getRegionInstance().spawnNPC(new NPC(BAPHOMET, new Position(2701, 2967, player.getPosition().getZ())).setSpawnedFor(player));
			    player.getRegionInstance().spawnNPC(new NPC(LUCIFER, new Position(2713, 2966, player.getPosition().getZ())).setSpawnedFor(player));
			    player.getRegionInstance().spawnNPC(new NPC(MADARA, new Position(2726, 2967, player.getPosition().getZ())).setSpawnedFor(player));
			    player.getRegionInstance().spawnNPC(new NPC(INFINITY, new Position(2726, 2979, player.getPosition().getZ())).setSpawnedFor(player));
			    player.getRegionInstance().spawnNPC(new NPC(THANOS, new Position(2727, 2992, player.getPosition().getZ())).setSpawnedFor(player));
			    player.getRegionInstance().spawnNPC(new NPC(BARREL, new Position(2719, 2967, player.getPosition().getZ())).setSpawnedFor(player));
			    player.getRegionInstance().spawnNPC(new NPC(CHAOS, new Position(2707, 2966, player.getPosition().getZ())).setSpawnedFor(player));
				stop();
			}
		});
	}
}
