package com.arlania.world.content;

import com.arlania.model.Position;
import com.arlania.util.Misc;
import com.arlania.world.World;
import com.arlania.world.entity.impl.npc.NPC;

public class AfkOreSystem {

    private static final int AMOUNT_NEEDED = 80_000;

    public static int minedCount = 0;

    public static int getLeft() {
        return AMOUNT_NEEDED - minedCount;
    }

    public static String getFormattedOresLeft() {
        return Misc.insertCommasToNumber(String.valueOf(AMOUNT_NEEDED - minedCount));
    }

    public static void spawncommand() {
        NPC npc = new NPC(3779, new Position(2912, 5472));
        World.register(npc);
        World.sendMessage("<shad=1>@blu@[EVENT]<img=368>THE WORLD<img=368>@red@ has awoken! teleport to ::deadpool to fight him");
        minedCount = 0;
    }

    public static void spawnBoss() {
        if(minedCount < AMOUNT_NEEDED) {
            return;
        }
        NPC npc = new NPC(3779, new Position(2529, 4067));
        World.register(npc);
        World.sendMessage("@blu@<img=368>THE WORLD<img=368>@red@ has awoken! teleport to ::deadpool to fight him");
        minedCount = 0;
    }
}
