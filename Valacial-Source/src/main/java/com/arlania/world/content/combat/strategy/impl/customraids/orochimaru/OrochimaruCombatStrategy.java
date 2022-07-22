package com.arlania.world.content.combat.strategy.impl.customraids.orochimaru;

import com.arlania.engine.task.Task;
import com.arlania.engine.task.TaskManager;
import com.arlania.model.*;
import com.arlania.util.Misc;
import com.arlania.world.World;
import com.arlania.world.content.combat.CombatContainer;
import com.arlania.world.content.combat.CombatType;
import com.arlania.world.content.combat.DesolaceFormulas;
import com.arlania.world.content.combat.prayer.CurseHandler;
import com.arlania.world.content.combat.prayer.PrayerHandler;
import com.arlania.world.content.combat.strategy.CombatStrategy;
import com.arlania.world.entity.impl.Character;
import com.arlania.world.entity.impl.npc.NPC;
import com.arlania.world.entity.impl.player.Player;

import java.util.ArrayList;

/**
 * rune-server kotlincode on 1/5/2021
 **/
public class OrochimaruCombatStrategy implements CombatStrategy {

    private static final Animation attackAnimation = new Animation(10496);
    private static final Animation attackAnimation2 = new Animation(10410);
    private static final Graphic splashGraphic = new Graphic(266);
    public static final int MAGIC_PROJECTILE = 268;
    @Override
    public boolean canAttack(Character entity, Character victim) {
        return victim.isPlayer();
    }

    @Override
    public CombatContainer attack(Character entity, Character victim) {
        return null;
    }

    /**
     * Handles the phase one initial attack
     */
    public void phaseOneInitAttack(Character entity,Character victim){
        entity.performAnimation(attackAnimation);

        NPC spawn = NPC.of(5090, new Position(victim.getPosition().getX()+Misc.random(1),victim.getPosition().getY()+Misc.random(1),victim.getPosition().getZ()));
        World.register(spawn);

        Position spawnPos = spawn.getPosition();

        new Projectile(entity, spawn, MAGIC_PROJECTILE, 85, 1, 20, 25, 50).sendProjectile();
        TaskManager.submit(new Task(3) {

            @Override
            public void execute() {

                if(victim.getPosition()==spawnPos) {
                    victim.dealDamage(new Hit(999, Hitmask.RED, CombatIcon.MAGIC));
                }
                spawn.performGraphic(splashGraphic);
                World.deregister(spawn);
                stop();
            }
        });
    }
    public void phaseTwoInitAttack(){

    }
    public void phaseThreeInitAttack(){

    }
    @Override
    public boolean customContainerAttack(Character entity, Character victim) {
        if(entity.getConstitution() < entity.getAsNpc().getDefaultConstitution() * .70 && entity.getCurrentPhase() == 0){
            entity.setCurrentPhase(1);
            ArrayList<Player> targets = entity.getAsNpc().getPossibleTargets();
            for(Player target : targets){
                phaseOneInitAttack(entity,target);
                phaseOneInitAttack(entity,target);
                phaseOneInitAttack(entity,target);

            }
            return true;
        }



        if(entity.getConstitution() < entity.getAsNpc().getDefaultConstitution() * .50 && entity.getCurrentPhase() == 1){
            entity.setCurrentPhase(2);
            phaseTwoInitAttack();
        }
        if(entity.getCurrentPhase() == 1){
            ArrayList<Player> targets = entity.getAsNpc().getPossibleTargets();
            for(Player target : targets){
                phaseOneInitAttack(entity,target);
                phaseOneInitAttack(entity,target);
                phaseOneInitAttack(entity,target);

            }
            return true;
        }
        if(entity.getConstitution() < entity.getAsNpc().getDefaultConstitution() * .30 && entity.getCurrentPhase() == 2){
            entity.setCurrentPhase(3);
            phaseThreeInitAttack();
        }
                int magicDamage = Misc.random(DesolaceFormulas.getMagicMaxhit(entity,victim));

                if (PrayerHandler.isActivated(victim.asPlayer(), PrayerHandler.getProtectingPrayer(CombatType.MAGIC))
                        || CurseHandler.isActivated(victim.asPlayer(), CurseHandler.getProtectingPrayer(CombatType.MAGIC))) {
                    magicDamage = (magicDamage / 3);
                }
                entity.performAnimation(attackAnimation);

                new Projectile(entity, victim, MAGIC_PROJECTILE, 85, 1, 105, 43, 0).sendProjectile();

                final int finalDamage1 = magicDamage;

                TaskManager.submit(new Task(2) {

                    @Override
                    public void execute() {
                        if(victim!=null) {
                            victim.performGraphic(splashGraphic);
                            victim.dealDamage(new Hit(finalDamage1, Hitmask.RED, CombatIcon.MAGIC));
                        }
                        stop();
                    }
                });
                return true;
    }

    @Override
    public int attackDelay(Character entity) {
        return 3;
    }

    @Override
    public int attackDistance(Character entity) {
        return 9;
    }

    @Override
    public CombatType getCombatType() {
        return CombatType.MIXED;
    }
}
