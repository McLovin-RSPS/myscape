package com.arlania.world.content.combat.strategy.impl;

import com.arlania.engine.task.Task;
import com.arlania.engine.task.TaskManager;
import com.arlania.model.Animation;
import com.arlania.model.Graphic;
import com.arlania.model.Hit;
import com.arlania.model.Position;
import com.arlania.model.Projectile;
import com.arlania.util.Misc;
import com.arlania.world.World;
import com.arlania.world.content.combat.CombatContainer;
import com.arlania.world.content.combat.CombatType;
import com.arlania.world.content.combat.strategy.CombatStrategy;
import com.arlania.world.entity.impl.Character;
import com.arlania.world.entity.impl.npc.NPC;
import com.arlania.world.entity.impl.npc.NPCMovementCoordinator.Coordinator;

public class CustomBossStrategy1 implements CombatStrategy {

    @Override
    public boolean canAttack(Character entity, Character victim) {
        return true;
    }

    @Override
    public CombatContainer attack(Character entity, Character victim) {
        return null;
    }
    
    private boolean minionsSpawned = false;

    @Override
    public boolean customContainerAttack(Character entity, Character victim) {
        NPC npc = (NPC)entity;
        if(victim.getConstitution() <= 0) {
            return true;
        }
        if(npc.isChargingAttack()) {
            return true;
        }
      //  final CombatType attkType = CombatType.MAGIC;
      //  npc.getCombatBuilder().setContainer(new CombatContainer(npc, victim, 1, 4, attkType, true));
        
       
        
        TaskManager.submit(new Task(1, npc, false) {
            int tick = 0;
            @Override
            public void execute() {
        		victim.dealDamage(new Hit(Misc.exclusiveRandom(100)));
        		 if(entity.getConstitution() < 2000000 && !minionsSpawned) {
        				entity.forceChat("Minions help me! Defeat Them all!");
        				NPC minion1 = new NPC(4538, new Position(entity.getPosition().getX() + Misc.exclusiveRandom(10), entity.getPosition().getY() + Misc.exclusiveRandom(5), entity.getPosition().getZ()));
        				NPC minion2 = new NPC(4538, new Position(entity.getPosition().getX() + Misc.exclusiveRandom(10), entity.getPosition().getY() + Misc.exclusiveRandom(5), entity.getPosition().getZ()));
        				NPC minion3 = new NPC(4538, new Position(entity.getPosition().getX() + Misc.exclusiveRandom(10), entity.getPosition().getY() + Misc.exclusiveRandom(5), entity.getPosition().getZ()));
        				NPC minion4 = new NPC(4538, new Position(entity.getPosition().getX() + Misc.exclusiveRandom(10), entity.getPosition().getY() + Misc.exclusiveRandom(5), entity.getPosition().getZ()));
        				NPC minion5 = new NPC(4538, new Position(entity.getPosition().getX() + Misc.exclusiveRandom(10), entity.getPosition().getY() + Misc.exclusiveRandom(5), entity.getPosition().getZ()));
        				NPC minion6 = new NPC(4538, new Position(entity.getPosition().getX() + Misc.exclusiveRandom(10), entity.getPosition().getY() + Misc.exclusiveRandom(5), entity.getPosition().getZ()));
        				NPC minion7 = new NPC(4538, new Position(entity.getPosition().getX() + Misc.exclusiveRandom(10), entity.getPosition().getY() + Misc.exclusiveRandom(5), entity.getPosition().getZ()));
        				NPC minion8 = new NPC(4538, new Position(entity.getPosition().getX() + Misc.exclusiveRandom(10), entity.getPosition().getY() + Misc.exclusiveRandom(5), entity.getPosition().getZ()));
        				NPC minion9 = new NPC(4538, new Position(entity.getPosition().getX() + Misc.exclusiveRandom(10), entity.getPosition().getY() + Misc.exclusiveRandom(5), entity.getPosition().getZ()));
        				NPC minion10 = new NPC(4538, new Position(entity.getPosition().getX() + Misc.exclusiveRandom(10), entity.getPosition().getY() + Misc.exclusiveRandom(5), entity.getPosition().getZ()));
        				minion1.getMovementCoordinator().setCoordinator(new Coordinator(true, 3));
        				minion2.getMovementCoordinator().setCoordinator(new Coordinator(true, 2));
        				minion3.getMovementCoordinator().setCoordinator(new Coordinator(true, 2));
        				minion4.getMovementCoordinator().setCoordinator(new Coordinator(true, 1));
        				minion5.getMovementCoordinator().setCoordinator(new Coordinator(true, 2));
        				minion6.getMovementCoordinator().setCoordinator(new Coordinator(true, 1));
        				minion7.getMovementCoordinator().setCoordinator(new Coordinator(true, 1));
        				minion8.getMovementCoordinator().setCoordinator(new Coordinator(true, 4));
        				minion9.getMovementCoordinator().setCoordinator(new Coordinator(true, 5));
        				minion10.getMovementCoordinator().setCoordinator(new Coordinator(true, 1));
        				World.register(minion1);
        				World.register(minion2);
        				World.register(minion3);
        				World.register(minion4);
        				World.register(minion5);
        				World.register(minion6);
        				World.register(minion7);
        				World.register(minion8);
        				World.register(minion9);
        				World.register(minion10);
        				minion1.getCombatBuilder().attack(victim);
        				minion2.getCombatBuilder().attack(victim);
        				minion3.getCombatBuilder().attack(victim);
        				minion4.getCombatBuilder().attack(victim);
        				minion5.getCombatBuilder().attack(victim);
        				minion6.getCombatBuilder().attack(victim);
        				minion7.getCombatBuilder().attack(victim);
        				minion8.getCombatBuilder().attack(victim);
        				minion9.getCombatBuilder().attack(victim);
        				minion10.getCombatBuilder().attack(victim);
        				minionsSpawned = true;
        			}
                if(tick == 2) {
                    stop();
                }
                tick++;
            }
        });
        return true;
    }

    @Override
    public int attackDelay(Character entity) {
        return entity.getAttackSpeed();
    }

    @Override
    public int attackDistance(Character entity) {
        return 5;
    }

    @Override
    public CombatType getCombatType() {
        return CombatType.MAGIC;
    }
}
