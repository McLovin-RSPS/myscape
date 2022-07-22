package com.arlania.world.content;


import com.arlania.model.Direction;
import com.arlania.world.entity.impl.player.Player;

/**
 * @author Tamatea <tamateea@gmail.com>
 */
public class NpcSpawner {

    private Player player;

    private int npcSpawning = -1;

    private Direction face = Direction.NORTH;

    private int radius = -1;




    public NpcSpawner(Player player) {
        this.player = player;
    }

    public void setDirection(Direction direction) {
        this.face = direction;
    }


    public void setNpcID(int id) {
        this.npcSpawning = id;
    }

    public void setRadius(int rad) {
        this.radius = rad;
    }

    public void printSpawn() {
       System.out.println("\t{\"npc-id\": "+npcSpawning+",\"face\": "+face.name()+",\"position\": {\"x\": "+player.getPosition().getX()+",\"y\": "+player.getPosition().getY()+",\"z\": "+player.getPosition().getZ()+"},\"walking-policy\": {\"coordinate\": "+(radius != -1)+",\"radius\": "+radius+"}},");
    }



}
