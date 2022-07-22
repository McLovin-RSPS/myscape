package com.arlania.world.content;

import com.arlania.model.Item;
import com.arlania.model.definitions.ItemDefinition;
import com.arlania.model.definitions.NPCDrops;
import com.arlania.model.definitions.NpcDefinition;
import com.arlania.util.Misc;
import com.arlania.world.entity.impl.player.Player;

import java.util.*;

/**
 * @author  not Tamatea
 * shocking
 */
public class DropLookup {
    private static final String[] DEFAULT = { "Bandos avatar", "nex", "broly", "Iktomi", "abbadon" };

    public static void open(Player player) {
        resetInterface(player);
        sendNames(player);
        display(player, NPCDrops.forId(100));
        //search(player, Misc.randomElement(DEFAULT), SearchPolicy.NPC);
    }
    public static void openForNpc(Player player, int id) {
        resetInterface(player);
        sendNames(player);
        display(player, NPCDrops.forId(id));
    }
    private static void sendNames(Player player) {
    	player.npcDrops.clear();
    	int[] count = new int[] {0};
    		NPCDrops.getDrops().forEach((npcId, drops) -> {
    			if(count[0] == 169)
    				return;
    			if(npcId > 0 && NpcDefinition.forId(npcId) != null) {
//    				System.out.println(NpcDefinition.forId(npcId).getName());
    			player.getPA().sendString(NpcDefinition.forId(npcId).getName(), 37651 + count[0]);
    			player.npcDrops.add(npcId);
    			count[0]++;
    			}
    		});
    	
    }
    public static void search(Player player, String context, SearchPolicy type) {
        context = context.trim().toLowerCase();
        List<String> npc = new ArrayList<>();
        List<Integer> integer = new ArrayList<>();
        player.getPacketSender().sendFrame126(37602, "NPC Drop Table Checker");

        idLoop:
        for (Map.Entry<Integer, NPCDrops> drop : NPCDrops.getDrops().entrySet()) {
            NPCDrops definition = drop.getValue();

            String name;

            for(int id : definition.getNpcIds()) {
                if(id == -1)
                    continue;
             NpcDefinition def = NpcDefinition.forId(id);
                if (def== null)
                    continue idLoop;
                name = def.getName();
                if(name == null || name.equals(""))
                    continue idLoop;

                if (type == SearchPolicy.NPC) {
                    if (name.toLowerCase().contains(context)) {
                        if (!npc.contains(name)) {
                            npc.add(name);
                            integer.add(id);
                        }
                    }
                } else if (type == SearchPolicy.ITEM) {
                    for (NPCDrops.NpcDropItem item : definition.getDropList()) {
                        String itemName = ItemDefinition.forId(item.getId()).getName();
                        if (itemName.toLowerCase().contains(context)) {
                            if (!npc.contains(name)) {
                                npc.add(name);
                                integer.add(id);
                            }
                        }
                    }
                }
            }
        }

        if (integer.isEmpty()) {
            player.sendMessage("@red@No Results found.");
            display(player, NPCDrops.getDrops().get(1));
            return;
        }
        for (int child = 37651; child < 37821; child++) {
            player.getPacketSender().sendFrame126("", child);
        }
        int index = 37651;
        for(String npcName : npc) {
            player.getPacketSender().sendFrame126(npcName, index);
            index++;
        }
        player.setAttribute("DROP_KEY", integer);
        player.npcDrops = integer;
        display(player, NPCDrops.forId(player.npcDrops.get(0)));
    }


    private static final int ITEM_STRING = 40_499, AMOUNT_STRING = 40_569, RARITY_STRING = 40_639;

    public static void resetInterface(Player player) {
        player.getPacketSender().sendFrame126(37602, "NPC Drop Table Checker");
        for (int child = 37651; child < 37821; child++) {
            player.getPacketSender().sendFrame126("", child);
        }
        for(int i = 0; i < 70; i++) {
            player.getPacketSender().sendString(ITEM_STRING+i, "");
            player.getPacketSender().sendString(AMOUNT_STRING+i, "");
            player.getPacketSender().sendString(RARITY_STRING+i, "");
        }
        player.getPacketSender().resetItemsOnInterface(37915, 70);

    }


    public static void display(Player player, NPCDrops definition) {

        player.getPacketSender().sendFrame126(37602, NpcDefinition.forId(definition.getNpcIds()[0]).getName()+" - Drops");

        player.getPacketSender().sendInterface(37600);
        for(int i = 0; i < 70; i++) {
            player.getPacketSender().sendString(ITEM_STRING+i, "");
            player.getPacketSender().sendString(AMOUNT_STRING+i, "");
            player.getPacketSender().sendString(RARITY_STRING+i, "");
        }
        List<Item> drop = new ArrayList<>();
        int index = 0;

        List<NPCDrops.NpcDropItem> dropList = Arrays.asList(definition.getDropList());

        dropList.sort(Comparator.comparing(NPCDrops.NpcDropItem::getChance));

        for(NPCDrops.NpcDropItem item : dropList) {
            ItemDefinition itemDef = ItemDefinition.forId(item.getId());
            if(index > 69 || itemDef == null|| itemDef.getName().equalsIgnoreCase("none"))
                continue;

            drop.add(item.getItem());
            index++;
            player.getPacketSender().sendString(ITEM_STRING+index, itemDef.getName());
            player.getPacketSender().sendString(AMOUNT_STRING+index, item.getCount()[0]+"");
            player.getPacketSender().sendString(RARITY_STRING+index, item.getChance().getRandom() == 0 ? "Always" : "1/"+item.getChance().getRandom()+"");

        }
        player.getPacketSender().sendDropItemsOnInterface(37915, 70, drop, true);

    }

    public enum SearchPolicy {
        ITEM, NPC
    }




}