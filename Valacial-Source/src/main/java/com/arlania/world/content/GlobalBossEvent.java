/**
 *
 */
package com.arlania.world.content;

import java.util.*;
import java.util.concurrent.TimeUnit;

import com.arlania.model.*;
import com.arlania.util.Misc;
import com.arlania.world.World;
import com.arlania.world.content.combat.*;
import com.arlania.world.entity.impl.npc.NPC;
import com.arlania.world.entity.impl.player.Player;



/**
 * @author BOMBY
 *
 *         January 18, 2021 - 8:04:43 PM
 */
public class GlobalBossEvent {

    private static long time;

    public static final int ID = 3782; //41151

    public static boolean spawned;

    public static int donatedSoFar;

    public static Position INSTANCED_LOCATION = new Position(2517, 3985, 0);

    public static Position getBossPosition() {
        return new Position(2517, 3985, 0);
    }

    public static void handleDonationTime(int amount) {
        long baseTime = (amount * 36_000);// 1$ = 36secs - 100$ = 1hour
        long currentTime = time;

        long newTime = currentTime - time;
//
//        if (newTime < 0) {
//            time = 500;
//        } else
//            time -= baseTime;

        donatedSoFar += amount;
        if (donatedSoFar >= 50) {
            donatedSoFar -= 50;
            World.sendMessage(
                    "@red@(Donator Boss)@bla@: A donator has helped further unlock the Donator Boss. ");
           // World.sendMessage("@red@(Donator Boss)@bla@: @cyan@the Donator boss time has been reduced by 1hour and will spawn in:@bla@" + GlobalBossEvent.timeRemaining());
        }

    }

    public static void init() {
        time = System.currentTimeMillis() + TimeUnit.HOURS.toMillis(1);
        //donatedSoFar += amount;
    }

    public static void process() {
        if (donatedSoFar >= 150 && !spawned) {
            World.sendMessage(
                    "@red@(Donator Boss)@bla@:The Donator Boss has spawned! @red@[::donatorboss]@bla@");
//        }
//        if (time < System.currentTimeMillis() && !spawned) {
            spawn();
        }

    }

    private static Item[][] rewards = {

            { new Item(10942, 1), new Item(8800, 1) , new Item(8801, 1), new Item(8802, 1), new Item(8803, 1), new Item(8804,1 ), new Item(8805,1), new Item(8806, 1), new Item(8807, 1), new Item(8808,1 )}, // common

            { new Item(5021, 100), new Item(18750, 1), new Item(18751, 1), new Item(15401), new Item(15452) }, // rare

            { new Item(20552,1 ), new Item(12612,1 ), new Item(8832,1 ), new Item(3906), new Item(3908), new Item(3910), new Item(9254, 1), new Item(9255,1), new Item(9256,1 ), new Item(9257, 1), new Item(9258,1 )},// epic

    };

    public static void handleDeath(NPC npc) {

        if (npc.getCombatBuilder().getDamageMap().size() == 0)
            return;

        final Map<Player, Integer> killers = new HashMap<>();

        for (Map.Entry<Player, CombatBuilder.CombatDamageCache> entry : npc.getCombatBuilder().getDamageMap()
                .entrySet()) {

            if (entry == null)
                continue;

            final long timeout = entry.getValue().getStopwatch().elapsed();

            if (timeout > CombatFactory.DAMAGE_CACHE_TIMEOUT)
                continue;

            final Player player = entry.getKey();

            if (player.getConstitution() <= 0 || !player.isRegistered())
                continue;

            killers.put(player, entry.getValue().getDamage());
        }

        npc.getCombatBuilder().getDamageMap().clear();

        List<Map.Entry<Player, Integer>> result = sortEntries(killers);

        for (Map.Entry<Player, Integer> entry : result) {

            final Player killer = entry.getKey();

            giveReward(killer);
        }
        spawned = false;
        time = System.currentTimeMillis() + TimeUnit.HOURS.toMillis(5);
    }

    public static void spawn() {
        NPC npc = new NPC(3782, new Position(2517, 3985));
        spawned = true;
        World.sendMessage("@red@(DonatorBoss)@bla@: Ainz Ooal Gown has spawned at ;;donatorboss!");
    }

    private static void giveReward(Player p) {
        double chance = Math.random();

        Item[] reward = null;

        if (chance <= 0.001)// 1/1,000
            reward = rewards[2];
        else if (chance <= 0.1)// 1/10
            reward = rewards[1];
        else
            reward = rewards[0];

        if (reward != null) {
            boolean space = p.getInventory().getFreeSlots() >= 1;
            Item toAdd = reward[Misc.random(reward.length - 1)];
            if (space)
                p.getInventory().add(toAdd);
            else
                p.getBank(0).add(toAdd);

            p.sendMessage("Your reward of " + toAdd.getAmount() + "x " + toAdd.getDefinition().getName()
                    + " has been added to your " + (space ? "inventory" : "bank"));
            World.sendMessage("<shad=0>@bla@[@red@Ainz Ooal Gown@bla@] @red@" + p.getUsername()
                    + " has gotten a reward of"+ toAdd.getAmount() + " x" + toAdd.getDefinition().getName()+"");
         //   World.sendMessage("<shad=0>@bla@[@red@Ainz Ooal Gown@bla@] @red@ Donate to fight him again!");

        }
    }

    private static <K, V extends Comparable<? super V>> List<Map.Entry<K, V>> sortEntries(Map<K, V> map) {
        final List<Map.Entry<K, V>> sortedEntries = new ArrayList<>(map.entrySet());
        sortedEntries.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));
        return sortedEntries;
    }

    /**
     * @return
     */

}
