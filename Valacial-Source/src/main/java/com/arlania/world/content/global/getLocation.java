package com.arlania.world.content.global;

import com.arlania.model.Position;

/**
 * Created by Stan van der Bend on 16/12/2017.
 * project: runeworld-game-server
 * package: runeworld.world.entity.combat.strategy.global
 */
public class getLocation {

    private final Position spawnPosition;
    private final String spawnMessage;

    public getLocation(Position spawnPosition, String spawnMessage) {
        this.spawnPosition = spawnPosition;
        this.spawnMessage = spawnMessage;
    }

    public Position getSpawnPosition() {
        return spawnPosition;
    }

    public String getString() {
        return spawnMessage;
    }
}