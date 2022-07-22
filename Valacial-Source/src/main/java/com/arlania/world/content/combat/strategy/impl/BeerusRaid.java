package com.arlania.world.content.combat.strategy.impl;

import com.arlania.engine.task.Task;
import com.arlania.engine.task.TaskManager;
import com.arlania.model.*;
import com.arlania.util.Misc;
import com.arlania.world.World;
import com.arlania.world.content.combat.CombatContainer;
import com.arlania.world.content.combat.CombatType;
import com.arlania.world.content.combat.strategy.CombatStrategy;
import com.arlania.world.entity.impl.Character;
import com.arlania.world.entity.impl.npc.NPC;
import com.arlania.world.entity.impl.player.Player;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;

public class BeerusRaid implements CombatStrategy {


    public static final Animation ATTACK = new Animation(2922);
    public static final Animation ATTACK_SPECIAL = new Animation(9059);
    private static final Graphic MAGIC_GFX = new Graphic(1355, GraphicHeight.MIDDLE);
    private static final Graphic MAGIC_GFX1 = new Graphic(1493, GraphicHeight.MIDDLE);
    public static final Animation FLY = new Animation(8961);



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
        NPC boss;


        boss = (NPC)entity;



        if(victim.getConstitution() <= 0) {
            return true;
        }

        if(boss.isChargingAttack()) {
            return true;
        }
        final int x = boss.getPosition().getX(), y = boss.getPosition().getY();
        ObjectList<Player> playerObjectList = new ObjectArrayList<>();

        for(Player p : World.getPlayers())
            if(p!= null && p.getPosition().distanceToPoint(x, y) <= 40)
                playerObjectList.add(p);
        boss.getCombatBuilder().setVictim(Misc.randomElement(playerObjectList));

        int attack = 0;
        int ran = Misc.random(0, 2);


        switch (ran) {
            case 0:
                boss.forceChat("Sphere of Destruction!.");
                TaskManager.submit(new Task(1) {
                    @Override
                    protected void execute() {
                        boss.performAnimation(ATTACK_SPECIAL);
                        boss.performGraphic(MAGIC_GFX);
                        for (Player player : playerObjectList) {
                            player.dealDamage(new CombatContainer(boss, victim, 1, 1, CombatType.MAGIC, true).getHits()[0].getHit());
                        }
                        this.stop();
                    }
                });
//376

                TaskManager.submit(new Task(1) {
                    @Override
                    protected void execute() {
                        for (Player player : playerObjectList) {
                            player.getPacketSender().sendCameraNeutrality();
                        }
                        this.stop();
                    }
                });
                break;
            case 1:
                for(Player p : playerObjectList) {
                    boss.forceChat("Spheres of Destruction!");
                    boss.performAnimation(ATTACK_SPECIAL);

                    p.performGraphic(MAGIC_GFX1);
                    p.dealDamage(new CombatContainer(boss, victim, 1, 1, CombatType.MAGIC, true).getHits()[0].getHit());
                }
                break;
            case 2:
                boss.forceChat("Balls of Destruction!");
                boss.performAnimation(FLY);
                int amt = Misc.random(15, 20);
                ObjectList<Position> positions = new ObjectArrayList<>();
                for(int i = 0; i < amt; i++)
                    positions.add(boss.getPosition().randomAround(Misc.random(-10, 10), Misc.random(-10, 10)));

                for(Position position : positions) {
                    new Projectile(boss.getPosition(), position, 0, 2804, 50, 3, 43, 31, 0).sendProjectile();
                }

                TaskManager.submit(new Task(3) {
                    @Override
                    protected void execute() {
                        for(Player p : playerObjectList) {
                            p.dealDamage(new Hit(Misc.random(300, 300)));
                            if(positions.contains(p.getPosition()))
                                p.dealDamage(new Hit(Misc.random(500, 500)));
                            if(Misc.random(1) <= 1) {
                                victim.performAnimation(new Animation(9099));
                               boss.forceChat("Think next time before challenging a god.");
                            }
                        }
                        this.stop();
                    }
                });

                break;
//            case 3:
//                for(Player p : playerObjectList) {
//                    boss.forceChat("Haki.");
//
//                }
//                break;
            default:
                System.out.println(ran);
        }



        return false;
    }

    @Override
    public int attackDelay(Character entity) {
        return 5;
    }

    @Override
    public int attackDistance(Character entity) {
        return 40;
    }

    @Override
    public CombatType getCombatType() {
        return CombatType.MIXED;
    }
}
