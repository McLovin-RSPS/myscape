package com.arlania.world.content;

import com.arlania.model.Item;
import com.arlania.model.definitions.NPCDrops;
import com.arlania.model.definitions.NpcDefinition;
import com.arlania.world.entity.impl.player.Player;

public class TaskHandlerNew {


    public static void openInterface(Player player, int tier) {
        resetInterface(player);
        buildTeleports(tier, player);
        player.teleportIndexPanel = tier;


        if (player.teleports.size() == 0) {
            player.getPacketSender().sendMessage("This tab doesnt have any teleports yet!");
        } else {
            buildInterface(0, player,tier);
        }
        player.getPacketSender().sendInterface(45800);
    }

    public static void resetInterface(Player player) {
        for (int j = 0; j < 60; j++) {
            player.getPacketSender().sendString(45962 + j, "");
        }
    }

    public static void buildTeleports(int tier, Player player) {
        player.teleports = TeleportEnum.dataByTier(tier);
        player.tier = tier;

        for (int j = 0; j < player.teleports.size(); j++) {
            player.getPacketSender().sendString(45962 + j, "" + player.teleports.get(j).getTeleportName());
        }
    }

    public static int getPlayersInLocation(int index, int tier){
//    if(tier == 1) {
//        switch(index) {
//            case 0:
//                return Locations.PLAYERS_IN_TZHAAR;
//            case 1:
//                return Locations.PLAYERS_IN_TOXICKNIGHT;
//            case 2:
//                return Locations.PLAYERS_IN_ARCHERAZ;
//                case 3:
//                return Locations.PLAYERS_IN_ALIEN;
//                case 4:
//                return Locations.PLAYERS_IN_DONKEY;
//        }
//    }
//        if(tier == 2) {
//            switch(index) {
//                case 0:
//                    return Locations.PLAYERS_IN_DONKEYKING;
//            }
//        }
//        if(tier == 4) {
//            switch(index) {
//                case 0:
//                    return Locations.PLAYERS_IN_AFK;
//            }
//        }
    return 0;
    }

    public static void buildInterface(int index, Player player, int tier) {
        /*int childId = 45701;

        for (int i = 0; i < 9; i++) {
            player.getPacketSender().sendItemOnInterface(childId, 0, 0);
            childId++;
        }*/
    	

        player.teleportIndex = index;
        TeleportEnum teleport = player.teleports.get(index);
        NpcDefinition npcdef = NpcDefinition.forId(teleport.getNpcId());

//        player.getPacketSender().sendNpcHeadOnInterface(teleport.getNpcId(),0);

        
        
      /*  Item[] items = new Item[NPCDrops.forId(teleport.getNpcId()).getDropList().length];
        for (int i = 0; i < items.length; ++i) {
        	items[i] = new Item(NPCDrops.forId(teleport.getNpcId()).getDropList()[i].getId(), NPCDrops.forId(teleport.getNpcId()).getDropList()[i].getItem().getAmount());
        }
        player.getPacketSender().sendInterfaceItems(45701, items);*/
        
        

        player.getPacketSender().sendString(45803, "" + getTitle(player.tier));

        player.getPacketSender().sendString(45807, "Style:" + teleport.getAttackWith());
        player.getPacketSender().sendString(45808, "Weakness: " + teleport.getWeakness());
        player.getPacketSender().sendString(45809, "Health: " + npcdef.getHitpoints());
        player.getPacketSender().sendString(45810, ""+teleport.getDifficulty());
        player.getPacketSender().sendString(45840, "Maxhit: "+npcdef.getMaxHit());
        player.getPacketSender().sendString(45841, "Players here: "+ getPlayersInLocation(index,tier));
        //player.getPacketSender().sendString(45843, "Difficulty: " + teleport.getDifficulty());

        /* childId = 45701;
        if (NPCDrops.forId(teleport.getNpcId()) != null) {
            if (NPCDrops.forId(teleport.getNpcId()).getDropList().length > 0) {
                if(NPCDrops.forId(teleport.getNpcId()).getDropList().length < 9) {
                    for (int i = 0; i < NPCDrops.forId(teleport.getNpcId()).getDropList().length; i++) {

                        player.getPacketSender().sendItemOnInterface(childId, NPCDrops.forId(teleport.getNpcId()).getDropList()[i].getId(), NPCDrops.forId(teleport.getNpcId()).getDropList()[i].getItem().getAmount());
                        childId++;
                    }
                } else {
                    for (int i = 0; i < 9; i++) {

                        player.getPacketSender().sendItemOnInterface(childId, NPCDrops.forId(teleport.getNpcId()).getDropList()[8-i].getId(), NPCDrops.forId(teleport.getNpcId()).getDropList()[8-i].getItem().getAmount());
                        childId++;
                    }
                }
            }

        }*/
    }

    public static void buildInterface(int index, Player player, int tier, int npcId) {
        /*int childId = 45701;

        for (int i = 0; i < 9; i++) {
            player.getPacketSender().sendItemOnInterface(childId, 0, 0);
            childId++;
        }*/

        player.teleportIndex = index;
        TeleportEnum teleport = player.teleports.get(index);
        NpcDefinition npcdef = NpcDefinition.forId(npcId);
//        player.getPacketSender().sendNpcHeadOnInterface(npcId, 0);

        player.getPacketSender().sendString(45803, "" + getTitle(player.tier));

        player.getPacketSender().sendString(45807, "Attacks With: " + teleport.getAttackWith());
        player.getPacketSender().sendString(45808, "Weakness: " + teleport.getWeakness());
        player.getPacketSender().sendString(45809, "Health: " + npcdef.getHitpoints());
        player.getPacketSender().sendString(45810, ""+teleport.getDifficulty());
        player.getPacketSender().sendString(45840, "Maxhit: "+npcdef.getMaxHit());
        player.getPacketSender().sendString(45841, "Players here: "+ getPlayersInLocation(index,tier));
        //player.getPacketSender().sendString(45843, "Difficulty: " + teleport.getDifficulty());

        
        Item[] items = new Item[NPCDrops.forId(npcId).getDropList().length];
        for (int i = 0; i < items.length; ++i) {
        	items[i] = new Item(NPCDrops.forId(npcId).getDropList()[i].getId(), NPCDrops.forId(npcId).getDropList()[i].getItem().getAmount());
        }
        player.getPacketSender().sendInterfaceItems(45701, items);
        
        
         /*childId = 45701;
        if (NPCDrops.forId(npcId) != null) {
            if (NPCDrops.forId(npcId).getDropList().length > 0) {
                if(NPCDrops.forId(npcId).getDropList().length < 9) {
                    for (int i = 0; i < NPCDrops.forId(npcId).getDropList().length; i++) {

                        player.getPacketSender().sendItemOnInterface(childId, NPCDrops.forId(npcId).getDropList()[i].getId(), NPCDrops.forId(npcId).getDropList()[i].getItem().getAmount());
                        childId++;
                    }
                } else {
                    for (int i = 0; i < 9; i++) {

                        player.getPacketSender().sendItemOnInterface(childId, NPCDrops.forId(npcId).getDropList()[8-i].getId(), NPCDrops.forId(npcId).getDropList()[8-i].getItem().getAmount());
                        childId++;
                    }
                }
            }

        }*/
    }


    public static String getTitle(int tier) {
        switch (tier) {
            case 1:
                return "Zones";
            case 2:
                return "Bosses";
            case 3:
                return "Minigame";
            case 4:
                return "Other";
            default:
                return "Error";
        }
    }

}
