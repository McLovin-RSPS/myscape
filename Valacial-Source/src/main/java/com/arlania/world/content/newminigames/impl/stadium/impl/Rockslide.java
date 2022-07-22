package com.arlania.world.content.newminigames.impl.stadium.impl;

import com.arlania.model.Animation;
import com.arlania.model.Graphic;
import com.arlania.world.content.combat.CombatContainer;
import com.arlania.world.content.combat.CombatHitTask;
import com.arlania.world.content.combat.CombatType;
import com.arlania.world.content.newminigames.impl.stadium.PokemonMove;


public class Rockslide extends PokemonMove {

	@Override
	public String getName() {
		return "Rockslide";
	}

	@Override
	public int getMaxPP() {
		return 16;
	}

	@Override
	public void processAttack() {
		// STEP 1 SEND GFX AND ANIMATIONS HERE
		
			// GFX AND ANIMATION ON TARGET
			this.getTarget().performGraphic(new Graphic(2715)); // Target Graphic
			this.getTarget().performAnimation(new Animation(435)); // Target Recoil Animation
			// GFX AND ANIMATION  ON PLAYERS POKEMON
			this.getPokemon().performGraphic(new Graphic(2009)); // Self Graphic
		
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
		return 390; //SINCE LOW PP WE WILL DO HEAVY HIT
	}

}