package com.arlania.world.content.combat.strategy.impl;

import com.arlania.engine.task.Task;
import com.arlania.engine.task.TaskManager;
import com.arlania.model.*;
import com.arlania.util.Misc;
import com.arlania.world.content.combat.CombatContainer;
import com.arlania.world.content.combat.CombatType;
import com.arlania.world.content.combat.HitQueue.CombatHit;
import com.arlania.world.content.combat.strategy.CombatStrategy;
import com.arlania.world.entity.impl.Character;
import com.arlania.world.entity.impl.npc.NPC;
import com.arlania.world.entity.impl.player.Player;


public class musketier implements CombatStrategy {


	private static final Animation anim = new Animation(11665);
	private static final Animation anim2 = new Animation(11665);
	private static final Animation anim3 = new Animation(11665);

	private static final Graphic gfx1 = new Graphic(1935, 3, GraphicHeight.MIDDLE);
	private static final Graphic gfx2 = new Graphic(1834);

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
		NPC ram = (NPC) entity;
		if (victim.getConstitution() <= 0) {
			return true;
		}
		if (ram.isChargingAttack()) {
			return true;
		}
		Player target = (Player) victim;
		boolean stomp = false;
		for (Player t : Misc.getCombinedPlayerList(target)) {
			if (Locations.goodDistance(t.getPosition(), ram.getPosition(), 1)) {
				stomp = true;
				ram.getCombatBuilder().setVictim(t);
				new CombatHit(ram.getCombatBuilder(), new CombatContainer(ram, t, 1, CombatType.MAGIC, true))
						.handleAttack();
			}
		}
		if (stomp) {
			ram.performAnimation(anim);
			victim.performGraphic(gfx1);
			ram.performGraphic(gfx2);

		}

		if (Locations.goodDistance(ram.getPosition().copy(), victim.getPosition().copy(), 1)
				&& Misc.getRandom(6) <= 4) {
			ram.performAnimation(anim);
			victim.performGraphic(gfx1);
			ram.getCombatBuilder().setContainer(new CombatContainer(ram, victim, 1, 2, CombatType.MELEE, true));
		} else if (Misc.getRandom(10) <= 7) {
			ram.performAnimation(anim2);
			ram.setChargingAttack(true);
			ram.getCombatBuilder().setContainer(new CombatContainer(ram, victim, 1, 2, CombatType.MAGIC, true));
			TaskManager.submit(new Task(1, ram, false) {
				@Override
				protected void execute() {
					stop();
					new Projectile(ram, victim, 1901, 44, 3, 43, 31, 0).sendProjectile();
					ram.setChargingAttack(false).getCombatBuilder()
							.setAttackTimer(ram.getDefinition().getAttackSpeed() - 1);
					stop();
				}
			});
		} else {
			ram.performAnimation(anim3);
			victim.performGraphic(gfx2);
			ram.getCombatBuilder().setContainer(new CombatContainer(ram, victim, 1, 2, CombatType.MAGIC, true));
		}

		return true;
	}

	@Override
	public int attackDelay(Character entity) {
		return entity.getAttackSpeed();
	}

	@Override
	public int attackDistance(Character entity) {
		return 10;
	}

	@Override
	public CombatType getCombatType() {
		return CombatType.MIXED;
	}

}