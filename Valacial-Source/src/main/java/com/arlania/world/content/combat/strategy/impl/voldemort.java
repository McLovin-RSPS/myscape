package com.arlania.world.content.combat.strategy.impl;

import com.arlania.engine.task.Task;
import com.arlania.engine.task.TaskManager;
import com.arlania.model.Animation;
import com.arlania.model.Graphic;
import com.arlania.model.Locations;
import com.arlania.model.Position;
import com.arlania.model.Projectile;
import com.arlania.util.Misc;
import com.arlania.world.content.combat.CombatContainer;
import com.arlania.world.content.combat.CombatHitTask;
import com.arlania.world.content.combat.CombatType;
import com.arlania.world.content.combat.strategy.CombatStrategy;
import com.arlania.world.entity.impl.Character;
import com.arlania.world.entity.impl.npc.NPC;
import com.arlania.world.entity.impl.player.Player;

public class voldemort implements CombatStrategy {

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
		NPC musketier = (NPC)entity;
		if(musketier.isChargingAttack() || victim.getConstitution() <= 0) {
			return true;
		}
		if(Locations.goodDistance(musketier.getPosition().copy(), victim.getPosition().copy(), 1) && Misc.getRandom(5) <= 3) {
			musketier.performAnimation(new Animation(musketier.getDefinition().getAttackAnimation()));
			musketier.getCombatBuilder().setContainer(new CombatContainer(musketier, victim, 1, 1, CombatType.MAGIC, true));
		} else if(!Locations.goodDistance(musketier.getPosition().copy(), victim.getPosition().copy(), 3) && Misc.getRandom(5) == 1) {
			musketier.setChargingAttack(true);
			final Position pos = new Position(victim.getPosition().getX() - 3, victim.getPosition().getY());
			((Player)victim).getPacketSender().sendGlobalGraphic(new Graphic(1898), pos);
			musketier.performAnimation(new Animation(9595));
			musketier.forceChat("I AM REBORN!");
			TaskManager.submit(new Task(2) {
				@Override
				protected void execute() {
					musketier.moveTo(pos);
					musketier.performAnimation(new Animation(musketier.getDefinition().getAttackAnimation()));
					musketier.getCombatBuilder().setContainer(new CombatContainer(musketier, victim, 1, 1, CombatType.MAGIC, false));
					musketier.setChargingAttack(false);
					musketier.getCombatBuilder().setAttackTimer(0);
					stop();
				}
			});
		} else {
			musketier.setChargingAttack(true);
			boolean barrage = Misc.getRandom(4) <= 2;
			musketier.performAnimation(new Animation(barrage ? 451 : 451));
			musketier.forceChat("Fear Me!");
			musketier.getCombatBuilder().setContainer(new CombatContainer(musketier, victim, 1, 3, CombatType.MAGIC, true));
			TaskManager.submit(new Task(1, musketier, false) {
				int tick = 0;
				@Override
				public void execute() {
					if(tick == 0 && !barrage) {
						/*
						 * PROJECTILE ID HERE 1rst #
						 */
					//	new Projectile(musketier, victim, 89, 44, 3, 43, 43, 0).sendProjectile();
					} else if(tick == 1) {
						if(barrage && victim.isPlayer() && Misc.getRandom(10) <= 5) {
							victim.getMovementQueue().freeze(10);
							victim.performGraphic(new Graphic(1176));
						}
						if(barrage && Misc.getRandom(6) <= 3) {
							musketier.performAnimation(new Animation(14223));
							musketier.forceChat("");
							for(Player toAttack : Misc.getCombinedPlayerList((Player)victim)) {
								if(toAttack != null && Locations.goodDistance(musketier.getPosition(), toAttack.getPosition(), 1) && toAttack.getConstitution() > 0) {
									new CombatHitTask(musketier.getCombatBuilder(), new CombatContainer(musketier, toAttack, 2, CombatType.MAGIC, false)).handleAttack();
									toAttack.performGraphic(new Graphic(382));
									musketier.forceChat("MUHAHAHA!!!");
								}
							}
						}
						musketier.setChargingAttack(false).getCombatBuilder().setAttackTimer(attackDelay(musketier) - 2);
						stop();
					}
					tick++;
				}
			});
		}
		return true;
	}


	@Override
	public int attackDelay(Character entity) {
		return entity.getAttackSpeed();
	}

	@Override
	public int attackDistance(Character entity) {
		return 1;
	}

	@Override
	public CombatType getCombatType() {
		return CombatType.RANGED;
	}
}