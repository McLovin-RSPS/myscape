package com.arlania.world.content.newminigames.impl.stadium.impl;

import com.arlania.world.content.combat.CombatContainer;
import com.arlania.world.content.combat.CombatHitTask;
import com.arlania.world.content.combat.CombatType;
import com.arlania.world.content.combat.magic.CombatSpell;
import com.arlania.world.content.combat.magic.CombatSpells;
import com.arlania.world.content.newminigames.impl.stadium.PokemonMove;

public class AirSlash extends PokemonMove {

	@Override
	public String getName() {
		return "Air Slash";
	}

	@Override
	public int getMaxPP() {
		return 10;
	}

	@Override
	public void processAttack() {
		
		//STEP 1- SEND SPELL
				//START OF SPELL PROJECTILE
				CombatSpell spell = CombatSpells.WIND_WAVE.getSpell();
				spell.startCast(this.getPokemon(), this.getTarget());
				//END OF SPELL PROJECTILE
		
		//STEP 2- APPLY VICTIM
				this.getPokemon().getCombatBuilder().setVictim(this.getTarget());
		
		//STEP 3- CREATE COMBAT CONTAINER
				//START OF COMBAT CONTAINER TO APPLY DAMAGE
				CombatContainer container = new CombatContainer(this.getPokemon(), this.getTarget(), 1, CombatType.MAGIC, true);
				int damage = this.getDamage();
				container.setModifiedDamage(damage);
				this.sendCriticalHit(damage);
				//this.getDamage returns a random number from 0 - getMaxDamage() 
		
		//STEP 4- APPLY DAMAGE
				//START OF HIT TASK TO APPLY DAMAGE
				new CombatHitTask(this.getPokemon().getCombatBuilder(), container, 100, false).handleAttack();
				
		
	}

	@Override
	public int getMaxDamage() {
		return 350; //SINCE LOW PP WE WILL DO HEAVY HIT
	}

}