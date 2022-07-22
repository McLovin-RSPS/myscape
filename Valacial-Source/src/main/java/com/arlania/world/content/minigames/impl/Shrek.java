//package com.arlania.world.content.minigames.impl;
//
//import com.arlania.engine.task.Task;
//import com.arlania.engine.task.TaskManager;
//import com.arlania.model.Locations;
//import com.arlania.model.Locations.Location;
//import com.arlania.model.Position;
//import com.arlania.model.RegionInstance;
//import com.arlania.model.RegionInstance.RegionInstanceType;
//import com.arlania.world.World;
//import com.arlania.world.entity.impl.npc.NPC;
//import com.arlania.world.entity.impl.player.Player;
//
//public class Shrek {
//
//    public static final int Shrek = 1265;
//
//    public static void enterZone(Player player) {// try this.
//        player.setRegionInstance(new RegionInstance(player, RegionInstanceType.SHREK));
//       System.out.println("entering zone 0...");
//        spawnZoneMonsters(player);
//    }
//
//    public static void leaveZone(Player player) { // i
//        player.moveTo(new Position(2652, 3039));
//        player.getCombatBuilder().reset(true);
//        player.getPacketSender().sendMessage("@blu@You have left Bomby zone " + player.getUsername());
//        if (player.getRegionInstance() != null && player.getRegionInstance().getNpcsList() != null) {
//            System.err.println("destroying zone..");
//            player.getRegionInstance().destruct();
//        } else
//            System.out.println("not destroying zone..");
//        player.restart();
//    }
//
//    public static void spawnZoneMonsters(final Player player) {
//        TaskManager.submit(new Task(2, player, false) {
//            @Override
//            public void execute() {
//                if (player.getRegionInstance() == null || !player.isRegistered()) {
//                    stop();
//                    return;
//                }// chill 1 sec ill be quick loading up my eclipse
//                System.out.println("spawned zone monsters 2..");
//                player.getRegionInstance().spawnNPC(new NPC(Shrek, new Position(2652, 3039, player.getPosition().getZ())).setSpawnedFor(player));
//                player.getRegionInstance().spawnNPC(new NPC(Shrek, new Position(2656, 3028, player.getPosition().getZ())).setSpawnedFor(player));
//                player.getRegionInstance().spawnNPC(new NPC(Shrek, new Position(2656, 3034, player.getPosition().getZ())).setSpawnedFor(player));
//
//                stop();
//            }
//        });
//    }
//}
