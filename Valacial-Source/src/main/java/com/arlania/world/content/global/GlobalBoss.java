package com.arlania.world.content.global;

import com.arlania.GameSettings;
import com.arlania.model.Locations;
import com.arlania.model.Locations.Location;
import com.arlania.model.Position;
import com.arlania.util.Misc;
import com.arlania.world.World;
import com.arlania.world.entity.impl.npc.NPC;
import com.arlania.world.entity.impl.npc.NPCMovementCoordinator;
import com.arlania.world.entity.impl.player.Player;


import java.util.concurrent.TimeUnit;
/**
 * Created by Stan van der Bend on 16/12/2017.
 * project: runeworld-game-server
 * package: runeworld.world.entity.combat.strategy
 */
public abstract class GlobalBoss extends NPC {


    private final int cyclesTillDespawn =  Math.toIntExact(TimeUnit.MINUTES.toMillis(minutesTillDespawn()) / GameSettings.GAME_PROCESSING_CYCLE_RATE);

    private final Position spawnPosition = Misc.randomElement(spawnLocations());

    private boolean respawn = true;
    
    private int id;

    public GlobalBoss(int id) {
        super(id, new Position(1, 1, 0));
        setPosition(spawnPosition);
        this.id = id;
    }

    int cyclesOutOfCombat = 0;

    @Override
    public void sequence(){
        super.sequence();

        if(this.getCombatBuilder().isOutOfCombat())
            cyclesOutOfCombat++;
        else cyclesOutOfCombat = 0;

        if(cyclesOutOfCombat >= cyclesTillDespawn){

            cyclesOutOfCombat = 0;
        }
    }


    /**
     * Handles any additional behaviour upon the spawning of this {@link GlobalBoss}.
     */
    protected void spawn() {
        World.sendMessage(constructSpawnMessage());
        this.setDefaultPosition(spawnPosition);
        this.setAttackDistance(20);
        this.getDefinition().setAggressive(true);
        this.getDefinition().setMulti(true);
        this.getDefinition().setRespawnTime(-1);
        this.setLocation(Location.getLocation(this));
        World.register(this);
        System.out.println("registering npcId="+id+" X="+spawnPosition.getX()+" Y="+spawnPosition.getY());
        
    }

    private String constructSpawnMessage(){
        return "<shad=0>@whi@[@mag@Multi Boss@whi@] @mag@"+getDefinition().getName()+"@whi@ Has just respawned! ";
        
    }

    /**
     * Handles the drop after this {@link GlobalBoss} has been killed for the top {@link GlobalBoss#maximumDrops()} players.
     *
     * @param player    a rewarded {@link Player}.
     */
    protected abstract void handleDrop(Player player);

    /**
     * The potential {@link Position}s this {@link GlobalBoss} can spawn at.
     *
     * @return all potential {@link Position}s.
     */
    protected abstract Position[] spawnLocations();

    /**
     * The amount of time it takes for this {@link GlobalBoss} to respawn after it has de-spawned.
     *
     * @return respawn time in minutes.
     */
    public abstract int minutesTillRespawn();

    /**
     * The amount of time after which this {@link GlobalBoss} de-spawns due to lack of combat engagement.
     *
     * @return de-spawn time in minutes.
     */
    protected abstract int minutesTillDespawn();

    /**
     *The maximum amount of players that can receive a drop based on their damage.
     *
     * @return the maximum amount of drops.
     */
    public abstract int maximumDrops();

    private NPCMovementCoordinator movementCoordinator = new NPCMovementCoordinator(this);

    public NPCMovementCoordinator getMovementCoordinator() {
        return movementCoordinator;
    }

}
