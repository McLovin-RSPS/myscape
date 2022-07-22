package com.arlania.world.content.combat.strategy.impl;

import com.arlania.engine.task.Task;
import com.arlania.engine.task.TaskManager;
import com.arlania.model.*;
import com.arlania.util.Misc;
import com.arlania.util.RandomUtility;
import com.arlania.world.World;
import com.arlania.world.content.combat.CombatContainer;
import com.arlania.world.content.combat.CombatType;
import com.arlania.world.content.combat.magic.CombatSpells;
import com.arlania.world.content.combat.strategy.CombatStrategy;
import com.arlania.world.entity.impl.Character;
import com.arlania.world.entity.impl.npc.NPC;
import com.arlania.world.entity.impl.player.Player;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;

public class AinzOoalGown implements CombatStrategy {


    public static final Animation ATTACK = new Animation(12252);
    public static final Animation ATTACK_SPECIAL = new Animation(384);

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
        int ran = Misc.random(0, 5);


        switch (ran) {
            case 0:
                boss.forceChat("Chain Dragon Lightning!");
                TaskManager.submit(new Task(4) {
                    @Override
                    protected void execute() {
                        boss.performAnimation(ATTACK_SPECIAL);
                        for (Player player : playerObjectList) {
                            player.performGraphic(new Graphic(1194));

                            player.dealDamage(new CombatContainer(boss, victim, 1, 2, CombatType.MAGIC, true).getHits()[0].getHit());
                            player.dealDamage(new CombatContainer(boss, victim, 1, 2, CombatType.MAGIC, true).getHits()[0].getHit());
                            player.getPacketSender().sendCameraShake(3, 6, 3, 6);
                        }
                        this.stop();
                    }
                });

                TaskManager.submit(new Task(7) {
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
                    boss.forceChat("Magic Arrow!");
                    boss.performAnimation(new Animation(708));
                    victim.performGraphic(new Graphic(630));
                    p.dealDamage(new CombatContainer(boss, victim, 1, 2, CombatType.RANGED, true).getHits()[0].getHit());
                }
                break;//
            case 2:
                boss.forceChat("Ice Coffin!");
                int amt = Misc.random(15, 20);
                ObjectList<Position> positions = new ObjectArrayList<>();
                for(int i = 0; i < amt; i++)
                    positions.add(boss.getPosition().randomAround(Misc.random(-10, 10), Misc.random(-10, 10)));

                for(Position position : positions) {
                    new Projectile(boss.getPosition(), position, 0, 1022, 60, 3, 43, 31, 0).sendProjectile();
                }
                TaskManager.submit(new Task(1) {
                    @Override
                    protected void execute() {
                        for(Player p : playerObjectList) {
                            p.dealDamage(new Hit(Misc.random(100, 300)));
                            if(positions.contains(p.getPosition()))
                                p.dealDamage(new Hit(Misc.random(200, 600)));
                            if(Misc.random(1, 2) <= 2) {
                                victim.performGraphic(new Graphic(1973));
                                victim.performAnimation(new Animation(11044));
                                victim.getMovementQueue().freeze(10);
                                p.getPA().sendMessage("You have been frozen into of a coffin!");
                            }
                        }
                        this.stop();
                    }
                });

                break;
            case 4:
                boss.forceChat("Nuclear Blast!");
                TaskManager.submit(new Task(4) {
                    @Override
                    protected void execute() {
                        boss.performAnimation(ATTACK_SPECIAL);
                        for (Player player : playerObjectList) {
                            player.performGraphic(new Graphic(188));
                            player.dealDamage(new CombatContainer(boss, victim, 2, 3, CombatType.MAGIC, true).getHits()[0].getHit());
                            player.dealDamage(new CombatContainer(boss, victim, 1, 3, CombatType.MAGIC, true).getHits()[0].getHit());
                            player.getPacketSender().sendCameraShake(6, 3, 6, 3);
                            victim.performAnimation(new Animation(9099));
                            victim.forceChat("EEK!");
                        }
                        this.stop();
                    }
                });

                TaskManager.submit(new Task(7) {
                    @Override
                    protected void execute() {
                        for (Player player : playerObjectList) {
                            player.getPacketSender().sendCameraNeutrality();
                        }
                        this.stop();
                    }
                });
                break;
            case 5:
                boss.forceChat("The Goal of All Life is Death.");
                TaskManager.submit(new Task(4) {
                    @Override
                    protected void execute() {
                        boss.performAnimation(ATTACK_SPECIAL);
                        for (Player player : playerObjectList) {
                            player.performGraphic(new Graphic(1374));
                            player.dealDamage(new CombatContainer(boss, victim, 3, 2, CombatType.MAGIC, true).getHits()[0].getHit());
                            player.dealDamage(new CombatContainer(boss, victim, 3, 3, CombatType.MAGIC, true).getHits()[0].getHit());

                            player.dealDamage(new CombatContainer(boss, victim, 2, 2, CombatType.MAGIC, true).getHits()[0].getHit());

                            victim.performAnimation(new Animation(1331));

                        }
                        this.stop();
                    }
                });

                TaskManager.submit(new Task(7) {
                    @Override
                    protected void execute() {
                        for (Player player : playerObjectList) {
                            player.getPacketSender().sendCameraNeutrality();
                        }
                        this.stop();
                    }
                });
                break;

            default:
                System.out.println(ran);
        }



        return false;
    }

    @Override
    public int attackDelay(Character entity) {
        return 3;
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
