package com.arlania.world.content;

import java.util.*;
import java.util.Map.Entry;

import com.arlania.model.GroundItem;
import com.arlania.model.Item;
import com.arlania.model.Position;
import com.arlania.util.Misc;
import com.arlania.world.World;
import com.arlania.world.content.combat.CombatBuilder.CombatDamageCache;
import com.arlania.world.content.combat.CombatFactory;
import com.arlania.world.entity.impl.GroundItemManager;
import com.arlania.world.entity.impl.npc.NPC;
import com.arlania.world.entity.impl.player.Player;

public class AfkBossDrop {
    public static int[] COMMONLOOT = {13734, 671, 14415, 14395, 10946, 14405, 672, 673, 681, 676, 22075, 666, 15424, 674, 5095, 989, 18350, 18358, 18354, 18352, 10025, 17600, 17712, 17714, 17686, 12284, 19114,
            //upg pvp armour
            13925, 13931, 13996, 13913, 13919, 13928, 13922, 10946, 13910, 13916, 13956, 13952, 13946, 13949, 13943, 13940, 13934, 13937, 6769, 6769, 6769,
            // Pvp armour
            13887, 13893, 13899, 13905, 13884, 13890, 13896, 13902, 13858, 13861, 13864, 13867, 13870, 13873, 13876,
            10835, 13734, 671, 14415, 14395, 14405, 672, 673, 681, 676, 22075, 666, 15424, 674, 5095, 2575, 18350, 18358, 18354, 18352, 10025, 17600, 17712, 17714, 17686, 12284, 19114,
            18786, 15511, 18817, 13302, 13305, 13734, 671, 14415, 14395, 14405, 672, 673, 681, 676, 22075, 666, 15424, 674, 5095, 989, 18350, 18358, 18354, 18352, 10025, 17600, 17712, 17714, 17686, 12284, 19114,
            //upg pvp armour
            13925, 13931, 13996, 13913, 13919, 13928, 13922, 13910, 13916, 13956, 13952, 13946, 13949, 13943, 13940, 13934, 13937, 6769, 6769, 6769,
            // Pvp armour
            13887, 13893, 13899, 13905, 13884, 13890, 13896, 13902, 13858, 13861, 13864, 13867, 13870, 13873, 13876,
            10835, 13734, 671, 14415, 14395, 14405, 672, 673, 681, 676, 22075, 666, 15424, 674, 5095, 2575, 18350, 18358, 18354, 18352, 10025, 17600, 17712, 17714, 17686, 12284, 19114,
            18786, 15511, 18817, 13302, 13305, 13734, 671, 14415, 14395, 14405, 672, 673, 681, 676, 22075, 666, 15424, 674, 5095, 989, 18350, 18358, 18354, 18352, 10025, 17600, 17712, 17714, 17686, 12284, 19114,
            //upg pvp armour
            13925, 13931, 13996, 13913, 13919, 13928, 13922, 13910, 13916, 13956, 13952, 13946, 13949, 13943, 13940, 13934, 13937, 6769, 6769, 6769,
            // Pvp armour
            13887, 13893, 13899, 13905, 13884, 13890, 13896, 13902, 13858, 13861, 13864, 13867, 13870, 13873, 13876,
            10835, 13734, 671, 14415, 14395, 14405, 672, 673, 681, 676, 22075, 666, 15424, 674, 5095, 2575, 18350, 18358, 18354, 18352, 10025, 17600, 17712, 17714, 17686, 12284, 19114,
            18786, 15511, 18817, 13302, 13305, 13734, 671, 14415, 14395, 14405, 672, 673, 681, 676, 22075, 666, 15424, 674, 5095, 989, 18350, 18358, 18354, 18352, 10025, 17600, 17712, 17714, 17686, 12284, 19114,
            //upg pvp armour
            13925, 13931, 13996, 13913, 13919, 13928, 10946, 13922, 13910, 13916, 13956, 13952, 13946, 13949, 13943, 13940, 13934, 13937, 6769, 6769, 6769,
            // Pvp armour
            13887, 13893, 13899, 13905, 13884, 13890, 13896, 13902, 13858, 13861, 13864, 13867, 13870, 13873, 13876,
            10835, 13734, 671, 14415, 14395, 14405, 672, 673, 681, 676, 22075, 666, 15424, 674, 5095, 2575, 18350, 18358, 18354, 18352, 10025, 17600, 17712, 17714, 17686, 12284, 19114,
            18786, 15511, 18817, 13302, 13305,
            //earthquake
            14068, 14069, 14071, 14072, 14070, 11303, 18784, 18783, 13750, 13748, 13746, 13752, 13736, 13754, 18798, 18795, 18797, 19794, 18790, 18789,
            18787, 18791, 18799, 18835, 18834, 18801, 18800, 18796, 18792, 15900, 15845, 15805, 20534,
            20438, 19905, 19904, 18685, 18684, 14008, 14009, 14010, 14011, 14012, 14013, 14014, 14015,
            14016, 19794, 15920, 16133, 11284, 22092, 17712, 22074, 18719, 5096, 22012, 12603, 4393,
            19160, 19159, 19158, 6570, 18359, 6585, 3909, 11617, 18359, 18363, 18361, 18355, 19780,
            14484, 15018, 15019, 15020, 15220, 10946, 12601, 12603, 12605, 2572, 15444, 20000, 20001, 20002,
            12931, 22008, 19116, 19115, 19114, 10946};

    public static void handleDrop(NPC npc) {
        //World.getPlayers().forEach(p -> p.getPacketSender().sendString(26707, "@or2@WildyWyrm: @gre@N/A"));
        if (npc.getCombatBuilder().getDamageMap().size() == 0) {
            return;
        }
        Map<Player, Integer> killers = new HashMap<>();
        for (Entry<Player, CombatDamageCache> entry : npc.getCombatBuilder().getDamageMap().entrySet()) {
            if (entry == null) {
                continue;
            }
            long timeout = entry.getValue().getStopwatch().elapsed();
            if (timeout > CombatFactory.DAMAGE_CACHE_TIMEOUT) {
                continue;
            }
            Player player = entry.getKey();
            if (player.getConstitution() <= 0 || !player.isRegistered()) {
                continue;
            }
            killers.put(player, entry.getValue().getDamage());
        }
        npc.getCombatBuilder().getDamageMap().clear();
        List<Entry<Player, Integer>> result = sortEntries(killers);
        int count = 0;
        for (Iterator<Entry<Player, Integer>> iterator = result.iterator(); iterator.hasNext(); ) {
            Entry<Player, Integer> entry = iterator.next();
            Player killer = entry.getKey();
            int damage = entry.getValue();
            if (++count < 10) {
                handleDrop(npc, killer, damage);
                iterator.remove();
            }
            killer.getInventory().add(2023, 2);
            killer.getInventory().add(5020, 5000);
            killer.sendMessage("You were rewarded for your contribution.");
        }

        List<Entry<Player, Integer>> sublist = Misc.randomSubList(result, 3);
        for (Entry<Player, Integer> entry : sublist) {
            handleDrop(npc, entry.getKey(), entry.getValue());
            entry.getKey().sendMessage("You were picked randomly to receive a reward.");
        }
    }

    public static void giveLoot(Player player, NPC npc, Position pos) {
        int chance = Misc.getRandom(100);
        int common = COMMONLOOT[Misc.getRandom(COMMONLOOT.length - 1)];
        @SuppressWarnings("unused")
        int common1 = COMMONLOOT[Misc.getRandom(COMMONLOOT.length - 1)];
        //	int rare = RARELOOT[Misc.getRandom(RARELOOT.length - 1)];
        //int superrare = SUPERRARELOOT[Misc.getRandom(SUPERRARELOOT.length - 1)];
        if (chance >= 0) {
            player.getInventory().add(common, 1);
            player.getInventory().add(2023, 3);
            //	player.getInventory().add(18530, 1);
            player.getInventory().add(5020, 10000);
            player.getInventory().add(20481, 1);
            String itemName = (new Item(common).getDefinition().getName());
            String itemMessage = Misc.anOrA(itemName) + " " + itemName;
            World.sendMessage(
                    "<img=368>Garfield Drop: " + player.getUsername() + " received " + itemMessage + " from Garfield!");
            displayPopup(player, new Item(common, 1));
            return;
        }
    }

    private static void displayPopup(Player player, Item item) {
        for (int i = 8145; i <= 8195; i++) {
            player.getPacketSender().sendString(i, "");
        }
        player.getPacketSender().sendString(8144, "@bla@<img=368>Garfield Reward Log");
        player.getPacketSender().sendString(8146, "@bla@<img=368>Garfield Reward ");
        player.getPacketSender().sendString(8147, "@yel@You have contributed towards killing Garfield");
        player.getPacketSender().sendString(8149, "@dre@You are Awarded Rare 1x " + item.getDefinition().getName());
        player.getPacketSender().sendString(8150, "@bla@You are Awarded uncommon 15000x AFK tickets ");
        player.getPacketSender().sendString(8151, "@bla@You are Awarded uncommon 1x Summoning charm box");
        player.getPacketSender().sendString(8152, "@bla@You are Awarded Common 5x Lazycat Bones");
        player.getPacketSender().sendInterface(8134);
    }

    /**
     * @param npc
     * @param player
     * @param damage
     */
    private static void handleDrop(NPC npc, Player player, int damage) {
        Position pos = npc.getPosition();
        giveLoot(player, npc, pos);
    }

    /**
     * @param map
     * @return
     */
    static <K, V extends Comparable<? super V>> List<Entry<K, V>> sortEntries(Map<K, V> map) {
        List<Entry<K, V>> sortedEntries = new ArrayList<Entry<K, V>>(map.entrySet());
        Collections.sort(sortedEntries, new Comparator<Entry<K, V>>() {
            @Override
            public int compare(Entry<K, V> e1, Entry<K, V> e2) {
                return e2.getValue().compareTo(e1.getValue());
            }
        });
        return sortedEntries;
    }
}
