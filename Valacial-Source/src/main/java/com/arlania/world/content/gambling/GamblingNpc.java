package com.arlania.world.content.gambling;

import com.arlania.engine.task.Task;
import com.arlania.engine.task.TaskManager;
import com.arlania.model.Position;
import com.arlania.model.definitions.NpcDefinition;
import com.arlania.util.Misc;
import com.arlania.world.World;
import com.arlania.world.entity.impl.npc.NPC;
import com.arlania.world.entity.impl.player.Player;

public class GamblingNpc {


   public static NPC i = new NPC(648,new Position(2508,2506));

    public static void init(Player p, int id,int amount, int npc){

        if(roll(p, npc)) {

                    p.getInventory().add(id,amount);
                    p.sendMessage("Yeah you have won!");
                    i.forceChat("Fuck i lost :(");
                    p.setWinStreak(p.getWinStreak()+1);

        } else {

                    p.getInventory().delete(id,amount);
                    p.sendMessage("Sorry you lost!!");
                    i.forceChat("Whahaha i won!");
                    p.setWinStreak(0);
        }
    }

public static void SpawnNpc(){
    World.register(i);
}

    public static boolean roll(Player p, int npc) {
        int random = Misc.random(1,100);

        if(p.getWinStreak() > 3 && random > 55){
            random = Misc.random(1,54);
        }

        i.forceChat("I have rolled a "+random+" on the dice!");

        p.sendMessage("<shad=0>@yel@The king has rolled a @red@" +random);
        if(random > 55) {
            return true;
        } else {
            return false;
        }
    }
}
