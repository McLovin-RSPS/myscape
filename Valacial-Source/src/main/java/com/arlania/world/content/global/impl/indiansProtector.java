package com.arlania.world.content.global.impl;

import com.arlania.model.Position;
import com.arlania.model.definitions.NPCDrops;
import com.arlania.world.content.global.GlobalBoss;

import com.arlania.world.entity.impl.player.Player;

public class indiansProtector extends GlobalBoss {

    private final static int NPC_ID = 50;




    public indiansProtector() {
        super(NPC_ID);

    }
    @Override
    public  void spawn() {
        super.spawn();
        this.setConstitution(5000000);
    }

    @Override
    protected void handleDrop(Player player) {
        NPCDrops.dropItems(player, this);
    }

    @Override
    protected Position[] spawnLocations() {
        return new Position[]{
                new Position(3057, 5201, 0)
        };
    }

    @Override
    public int minutesTillRespawn() {
        return 25;
    }

    @Override
    protected int minutesTillDespawn() {
        return 3600000;
    }

    @Override
    public int maximumDrops() {
        return 10;
    }
}
