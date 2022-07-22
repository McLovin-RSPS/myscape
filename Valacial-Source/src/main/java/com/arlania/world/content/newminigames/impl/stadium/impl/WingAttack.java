package com.arlania.world.content.newminigames.impl.stadium.impl;

import com.arlania.world.content.combat.CombatContainer;
import com.arlania.world.content.combat.CombatHitTask;
import com.arlania.world.content.combat.CombatType;
import com.arlania.world.content.combat.magic.CombatSpell;
import com.arlania.world.content.combat.magic.CombatSpells;
import com.arlania.world.content.newminigames.impl.stadium.PokemonMove;

public class WingAttack extends PokemonMove {

	@Override
	public String getName() {
		return "Wing Attack";
	}

	@Override
	public int getMaxPP() {
		return 16;
	}

	@Override
	public void processAttack() {
		
		//START OF SPELL PROJECTILE
		CombatSpell spell = CombatSpells.CRUMBLE_UNDEAD.getSpell();
		spell.startCast(this.getPokemon(), this.getTarget());
		//END OF SPELL PROJECTILE
		
		//START OF COMBAT BUILDER - SETTING TARGET
		this.getPokemon().getCombatBuilder().setVictim(this.getTarget());
		
		//START OF COMBAT CONTAINER TO APPLY DAMAGE
		CombatContainer container = new CombatContainer(this.getPokemon(), this.getTarget(), 1, CombatType.MAGIC, true);
		int damage = this.getDamage();
		container.setModifiedDamage(damage);
		this.sendCriticalHit(damage);//SENDS CRITICAL HIT FORCEHCAT
		//this.getDamage returns a random number from 0 - getMaxDamage() 
		
		//START OF HIT TASK TO APPLY DAMAGE
		new CombatHitTask(this.getPokemon().getCombatBuilder(), container, 100, false).handleAttack();
		
		
	}
	
	@Override
	public int getMaxDamage() {
		
		/*
		 * SINCE DAMAGE IS BASED OFF OF X10 MULTIPLIER
		 * 300 DAMAGE IS EQUIVALENT TO 300/10 = 30 DAMAGE
		 * 400 DAMAGE IS EQUIVALENT TO 400/10 = 40 DAMAGE
		 * 10 DAMAGE IS EQUIVALENT TO 10/10 = 1 DAMAGE
		 */
		
		return 200;
	}

}
