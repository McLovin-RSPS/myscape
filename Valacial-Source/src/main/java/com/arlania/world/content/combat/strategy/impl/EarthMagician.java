package com.arlania.world.content.combat.strategy.impl;

import com.arlania.engine.task.Task;
import com.arlania.engine.task.TaskManager;
import com.arlania.model.Animation;
import com.arlania.model.Projectile;
import com.arlania.util.Misc;
import com.arlania.world.content.combat.CombatContainer;
import com.arlania.world.content.combat.CombatType;
import com.arlania.world.content.combat.strategy.CombatStrategy;
import com.arlania.world.entity.impl.Character;
import com.arlania.world.entity.impl.npc.NPC;

public class EarthMagician implements CombatStrategy {

    @Override
    public boolean canAttack(Character entity, Character victim) {
        return true;
    }

    @Override
    public CombatContainer attack(Character entity, Character victim) {
        return null;
    }

    @Override
    public boolean customContainerAttack(Character entity, Character victim) {
        NPC npc = (NPC)entity;
        if(victim.getConstitution() <= 0) {
            return true;
        }
        if(npc.isChargingAttack()) {
            return true;
        }
        npc.setChargingAttack(true);
        final CombatType attkType = CombatType.MAGIC;
        npc.getCombatBuilder().setContainer(new CombatContainer(npc, victim, 1, 4, attkType, true));
        TaskManager.submit(new Task(1, npc, false) {
            int tick = 0;
            @Override
            public void execute() {
                if(tick == 2) {
                    new Projectile(npc, victim, 971, 44, 3, 43, 43, 0).sendProjectile();
                    npc.setChargingAttack(false);
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
