package com.arlania.world.content.newminigames.impl.stadium.impl;

import com.arlania.model.Animation;
import com.arlania.model.Graphic;
import com.arlania.world.content.combat.CombatContainer;
import com.arlania.world.content.combat.CombatHitTask;
import com.arlania.world.content.combat.CombatType;
import com.arlania.world.content.combat.magic.CombatSpell;
import com.arlania.world.content.combat.magic.CombatSpells;
import com.arlania.world.content.newminigames.impl.stadium.PokemonMove;


public class FireStorm extends PokemonMove {

	@Override
	public String getName() {
		return "Fire Storm";
	}

	@Override
	public int getMaxPP() {
		return 4;
	}

	@Override
	public void processAttack() {
		// STEP 1 SEND GFX AND ANIMATIONS HERE
		
			// GFX AND ANIMATION ON TARGET
		//START OF SPELL PROJECTILE
			CombatSpell spell = CombatSpells.FIRE_STRIKE.getSpell();
			spell.startCast(this.getPokemon(), this.getTarget());
		//END OF SPELL PROJECTILE
			this.getTarget().performGraphic(new Graphic(2039)); // Target Graphic
			
		
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
		return 650; //SINCE LOW PP WE WILL DO HEAVY HIT
	}

}