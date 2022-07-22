package com.arlania.world.content.combat.strategy.impl;

import com.arlania.engine.task.Task;
import com.arlania.engine.task.TaskManager;
import com.arlania.model.*;
import com.arlania.util.Misc;
import com.arlania.world.World;
import com.arlania.world.content.combat.CombatContainer;
import com.arlania.world.content.combat.CombatHitTask;
import com.arlania.world.content.combat.CombatType;
import com.arlania.world.content.combat.strategy.CombatStrategy;
import com.arlania.world.entity.impl.Character;
import com.arlania.world.entity.impl.npc.NPC;
import com.arlania.world.entity.impl.player.Player;

public class DraxNe implements CombatStrategy {

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
		NPC bandosAvatar = (NPC)entity;
		if(bandosAvatar.isChargingAttack() || victim.getConstitution() <= 0) {
			return true;
		}
		if(Locations.goodDistance(bandosAvatar.getPosition().copy(), victim.getPosition().copy(), 1) && Misc.getRandom(5) <= 3) {
			bandosAvatar.performAnimation(new Animation(bandosAvatar.getDefinition().getAttackAnimation()));
			bandosAvatar.getCombatBuilder().setContainer(new CombatContainer(bandosAvatar, victim, 1, 1, CombatType.MELEE, true));
		} else if(!Locations.goodDistance(bandosAvatar.getPosition().copy(), victim.getPosition().copy(), 3) && Misc.getRandom(5) == 1) {
			bandosAvatar.setChargingAttack(true);
			final Position pos = new Position(victim.getPosition().getX() - 2, victim.getPosition().getY());
			((Player)victim).getPacketSender().sendGlobalGraphic(new Graphic(1549), pos);
			bandosAvatar.forceChat("Die you earthling!");
			TaskManager.submit(new Task(2) {
				@Override
				protected void execute() {
					bandosAvatar.moveTo(pos);
					bandosAvatar.performAnimation(new Animation(10265));
					bandosAvatar.getCombatBuilder().setContainer(new CombatContainer(bandosAvatar, victim, 1, 1, CombatType.MELEE, false));
					bandosAvatar.setChargingAttack(false);
					bandosAvatar.getCombatBuilder().setAttackTimer(0);
					stop();
				}
			});
		} else {
			bandosAvatar.setChargingAttack(true);
			final int x = bandosAvatar.getPosition().getX(), y = bandosAvatar.getPosition().getY();

			bandosAvatar.getCombatBuilder().setContainer(new CombatContainer(bandosAvatar, victim, 1, 3, CombatType.MAGIC, true));
			TaskManager.submit(new Task(1, bandosAvatar, false) {
				@Override
				public void execute() {
					for (Player p : World.getPlayers()) {
						if (p == null)
							continue;
						if (p.getPosition().distanceToPoint(x, y) <= 20) {
							p.dealDamage(new Hit(150, Hitmask.RED, CombatIcon.NONE));
						}
						if (p.getPosition().distanceToPoint(x, y) <= 20) {

							for (int x_ = x - 2; x_ < x + 2; x_++) {
								for (int y_ = y - 2; y_ < y + 2; y_++) {
									p.getPacketSender().sendGraphic(new Graphic(2259), new Position(x_, y));
								}
							}
						}
					}
					this.stop();
				}
			});
		}
		return true;
	}

	public static int getAnimation(int npc) {
		int anim = 12259;
		if(npc == 50)
			anim = 81;
		else if(npc == 5362 || npc == 5363)
			anim = 14246;
		else if(npc == 51)
			anim = 13152;
		return anim;
	}


	@Override
	public int attackDelay(Character entity) {
		return entity.getAttackSpeed();
	}

	@Override
	public int attackDistance(Character entity) {
		return 7;
	}

	@Override
	public CombatType getCombatType() {
		return CombatType.MIXED;
	}
}
