package com.arlania.world.content;

import com.arlania.model.Animation;
import com.arlania.model.Hit;
import com.arlania.util.Misc;
import com.arlania.world.World;
import com.arlania.world.content.combat.CombatContainer;
import com.arlania.world.content.combat.CombatType;
import com.arlania.world.content.combat.strategy.CombatStrategy;
import com.arlania.world.entity.impl.Character;
import com.arlania.world.entity.impl.npc.NPC;
import com.arlania.world.entity.impl.npc.NPCMovementCoordinator.Coordinator;

public class CustomBossStrategy implements CombatStrategy {

	
	private boolean minionsSpawned = false;
	
	NPC npc;
	
	
	@Override
	public boolean canAttack(Character entity, Character victim) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public CombatContainer attack(Character entity, Character victim) {
		
		if(entity.getConstitution() < 450000 && !minionsSpawned) {
			entity.forceChat("Minions help me");
			for(int i = 0; i < 10; i++) {
			npc = new NPC(9326, entity.getPosition());
			npc.getMovementCoordinator().setCoordinator(new Coordinator(true, 3));
			World.register(npc);
			}
			minionsSpawned = true;
		}
		
		entity.performAnimation(new Animation(13053));
		victim.dealDamage(new Hit(Misc.exclusiveRandom(2000)));
		
		System.out.println("Called this method");
		
		return null;
	}

	@Override
	public boolean customContainerAttack(Character entity, Character victim) {
		
		if(entity.getConstitution() < 450000 && !minionsSpawned) {
			entity.forceChat("Minions help me");
			for(int i = 0; i < 10; i++) {
			npc = new NPC(9326, entity.getPosition());
			npc.getMovementCoordinator().setCoordinator(new Coordinator(true, 3));
			World.register(npc);
			}
			minionsSpawned = true;
		}
		
		entity.performAnimation(new Animation(13053));
		victim.dealDamage(new Hit(Misc.exclusiveRandom(2000)));
		
		System.out.println("Called this method");
			
		
		return true;
	}

	@Override
	public int attackDelay(Character entity) {
		// TODO Auto-generated method stub
		return entity.getAttackSpeed();
	}

	@Override
	public int attackDistance(Character entity) {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public CombatType getCombatType() {
		// TODO Auto-generated method stub
		return CombatType.MIXED;
	}

}
