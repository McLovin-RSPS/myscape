package com.arlania.world.content;

import com.arlania.model.Position;
import com.arlania.util.Misc;
import com.arlania.world.World;
import com.arlania.world.entity.impl.npc.NPC;

public class NpcGlobalSystem {

    private static final int AMOUNT_NEEDED = 100_000;

    public static int NpcCount = 0;

    public static int getLeft() {
        return AMOUNT_NEEDED - NpcCount;
    }

    public static String getFormattedNpcsLeft() {
        return Misc.insertCommasToNumber(String.valueOf(AMOUNT_NEEDED - NpcCount));
    }

    public static void spawncommand() {
        NPC npc = new NPC(6766, new Position(2912, 5472));
        World.register(npc);
        World.sendMessage("<shad=1>@blu@[EVENT]<img=368>Mew<img=368>@red@ has awoken! teleport to ::mew to fight him");
        NpcCount = 0;
    }

    public static void spawnBoss() {
        if(NpcCount < AMOUNT_NEEDED) {
            return;
        }
        NPC npc = new NPC(6766, new Position(2529, 4067));
        World.register(npc);
        World.sendMessage("@blu@<img=368>Mew<img=368>@red@ has awoken! teleport to ::mew to fight him");
        NpcCount = 0;
    }
}
