package com.arlania.world.content.combat.strategy.impl;

import com.arlania.engine.task.Task;
import com.arlania.engine.task.TaskManager;
import com.arlania.model.Animation;
import com.arlania.model.Projectile;
import com.arlania.model.Skill;
import com.arlania.util.Misc;
import com.arlania.world.content.combat.CombatContainer;
import com.arlania.world.content.combat.CombatType;
import com.arlania.world.content.combat.HitQueue.CombatHit;
import com.arlania.world.content.combat.strategy.CombatStrategy;
import com.arlania.world.entity.impl.Character;
import com.arlania.world.entity.impl.npc.NPC;
import com.arlania.world.entity.impl.player.Player;

public class KaylenRanger implements CombatStrategy {

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
        NPC ranger = (NPC)entity;
        if(victim.getConstitution() <= 0) {
            return true;
        }
        if(ranger.isChargingAttack()) {
            return true;
        }
        ranger.setChargingAttack(true);
        ranger.performAnimation(new Animation(426));
        final CombatType combatType = CombatType.RANGED;
        ranger.getCombatBuilder().setContainer(new CombatContainer(ranger, victim, 1, 2, combatType, true));
        TaskManager.submit(new Task(1, ranger, false) {
            int tick = 0;
            @Override
            protected void execute() {
                if (tick == 0) {
                    tick++;
                    new Projectile(ranger, victim, 1120, 44, 3, 43, 43, 0).sendProjectile();
                } else if (tick == 1) {
                    ranger.setChargingAttack(false);
                    stop();
                }

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
        return CombatType.RANGED;
    }
}
