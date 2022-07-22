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

public class Mew implements CombatStrategy {


	//Ground
	public static final Graphic psychic = new Graphic(105);
	//Venom Cloud
	private static final Graphic SHADOW_CLAW = new Graphic(77);
	//Physic
	public static final Animation SUPER_PUNCH = new Animation(9939);
	private static final Graphic STUN = new Graphic(80);

	//2241 - When poisoned by venom cloud - Venom Attacked animation Cloud gfx
//11665 - Hits the ground - Earth Shake - Rocks falling on head
	// 2588 - Jumps into the air - Super Nova - Graphics for super nova: Explosions
// 9939 - Super punch - Mass Destruction - Knockback and stun gfx birds over head
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
				TaskManager.submit(new Task(2) {
					@Override
					protected void execute() {
						boss.performGraphic(psychic);
						for (Player player : playerObjectList) {
							player.dealDamage(new CombatContainer(boss, victim, 2, 1, CombatType.MAGIC, true).getHits()[0].getHit());
							player.getPacketSender().sendCameraShake(1, 1, 1, 1);

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
					//boss.forceChat("Venom!");
					p.performGraphic(SHADOW_CLAW);
					p.dealDamage(new CombatContainer(boss, victim, 1, 1, CombatType.RANGED, true).getHits()[0].getHit());
				}
				break;
			case 2:
				//boss.forceChat("Supernova!");
				//boss.performAnimation(FLY);
				int amt = Misc.random(15, 20);
				ObjectList<Position> positions = new ObjectArrayList<>();
				for(int i = 0; i < amt; i++)
					positions.add(boss.getPosition().randomAround(Misc.random(-10, 10), Misc.random(-10, 10)));

				for(Position position : positions) {
					new Projectile(boss.getPosition(), position, 0, 143, 50, 3, 43, 31, 0).sendProjectile();
				}

				TaskManager.submit(new Task(1) {
					@Override
					protected void execute() {
						for(Player p : playerObjectList) {
							p.dealDamage(new Hit(Misc.random(300, 300)));
							if(positions.contains(p.getPosition()))
								p.dealDamage(new Hit(Misc.random(500, 500)));
							if(Misc.random(1) <= 1) {
								victim.performAnimation(new Animation(9099));
								//boss.forceChat("Think next time before challenging a god.");
							}
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
