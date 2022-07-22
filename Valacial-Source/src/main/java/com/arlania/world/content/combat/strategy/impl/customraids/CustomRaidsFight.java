package com.arlania.world.content.combat.strategy.impl.customraids;


import com.arlania.model.Position;

/**
 * rune-server kotlincode on 1/5/2021
 **/
public enum CustomRaidsFight {
    MADARA(1, new Position(3162, 4444)),
    SASUKE(2, new Position(3299, 4440)),
    ORO(3, new Position(3293, 4246));

    private int npcId;
    private Position spawnPosition;

    CustomRaidsFight(int npcId, Position spawnPosition) {
        this.npcId = npcId;
        this.spawnPosition = spawnPosition;
    }

    public int getNpcId() {
        return npcId;
    }

    public Position getSpawnPosition() {
        return spawnPosition;
    }
}
