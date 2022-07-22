package com.arlania.world.content.global;

import java.util.*;
import java.util.concurrent.TimeUnit;

import com.arlania.GameSettings;
import com.arlania.engine.task.Task;
import com.arlania.engine.task.TaskManager;
import com.arlania.model.Position;
import com.arlania.model.definitions.NPCDrops;
import com.arlania.world.World;
import com.arlania.world.content.combat.CombatBuilder;
import com.arlania.world.content.combat.CombatFactory;
//import com.arlania.world.content.global.impl.ShadowLord;
//import com.arlania.world.content.global.impl.ChromeBot;
//import com.arlania.world.content.global.impl.Godzilla;
import com.arlania.world.content.global.impl.DeathReaper;
import com.arlania.world.content.global.impl.indiansProtector;
import com.arlania.world.content.global.impl.vorago;
import com.arlania.world.content.global.impl.ShadowLord;
import com.arlania.world.entity.impl.npc.NPC;
import com.arlania.world.entity.impl.player.Player;

/**
 * Created by Stan van der Bend on 16/12/2017.
 * project: runeworld-game-server
 * package: runeworld.world.entity.combat.strategy.global
 */
public abstract class GlobalBossHandler {

    private static List<GlobalBoss> GLOBAL_BOSSES = new ArrayList<>();

    public static void init(){
    	register(new DeathReaper(),0);
    	register(new indiansProtector(),1);
      	register(new vorago(),2);
    	register(new ShadowLord(),3);
    }

    private static void register(GlobalBoss globalBoss, int id){
        GLOBAL_BOSSES.add(globalBoss);

        final long millisTillRespawn = (globalBoss.minutesTillRespawn()*60000);
        final int cyclesTillRespawn = Math.toIntExact(millisTillRespawn / GameSettings.GAME_PROCESSING_CYCLE_RATE);

        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 0);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);


        System.out.println("A "+globalBoss.getDefinition().getName()+" will spawn in "+cyclesTillRespawn+" cycles.");

        World.bossSpawns[id] = (System.currentTimeMillis()+millisTillRespawn);

        TaskManager.submit(new Task(cyclesTillRespawn, false) {
            @Override
            protected void execute() {
                globalBoss.spawn();
                stop();
            }
        });
    }
    static void deRegister(GlobalBoss globalBoss){
        GLOBAL_BOSSES.remove(globalBoss);
        World.deregister(globalBoss);
    }

    public static int getNPC(int npcId){
        switch (npcId){
            case 8766:
                return 2;
            case 7552:
                return 3;
            case 50:
                return 1;
            case 8453:
                return  0;
        }
        return 0;
    }

    public static void onDeath(GlobalBoss npc) {
        handleDrop(npc);
        deRegister(npc);
        register(npc,getNPC(npc.getId()));
    }
    private static void handleDrop(GlobalBoss npc) {

      //  World.getPlayers().forEach(p -> p.getPacketSender().sendString(26708, "<col=ff7000>Ganodermic Beast: <col=ff0000>N/A"));

        if(npc.getCombatBuilder().getDamageMap().size() == 0)
            return;

        final Map<Player, Integer> killers = new HashMap<>();

        for(Map.Entry<Player, CombatBuilder.CombatDamageCache> entry : npc.getCombatBuilder().getDamageMap().entrySet()) {

            if(entry == null)
                continue;

            final long timeout = entry.getValue().getStopwatch().elapsed();

            if(timeout > CombatFactory.DAMAGE_CACHE_TIMEOUT)
                continue;

            final Player player = entry.getKey();

            if(player.getConstitution() <= 0 || !player.isRegistered())
                continue;

            killers.put(player, entry.getValue().getDamage());
        }

        npc.getCombatBuilder().getDamageMap().clear();

        List<Map.Entry<Player, Integer>> result = sortEntries(killers);
        int count = 0;

        for(Map.Entry<Player, Integer> entry : result) {

            final Player killer = entry.getKey();

            npc.handleDrop(killer);

            if(++count >= 50)
                break;
        }
    }



    private static <K, V extends Comparable<? super V>> List<Map.Entry<K, V>> sortEntries(Map<K, V> map) {
        final List<Map.Entry<K, V>> sortedEntries = new ArrayList<>(map.entrySet());
        sortedEntries.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));
        return sortedEntries;
    }


    public static List<GlobalBoss> getBosses(){
        return GLOBAL_BOSSES;
    }
}