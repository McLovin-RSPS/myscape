package com.arlania.world.content.newminigames.impl.stadium.impl;

import com.arlania.world.content.dialogue.DialogueManager;
import com.arlania.world.content.newminigames.impl.stadium.PokemonDialogue;
import com.arlania.world.content.newminigames.impl.stadium.PokemonMove;

/*
 * DO NOT MODIFY THIS FILE
 */

public class NoMove extends PokemonMove {

	@Override
	public String getName() {
		return "No move";
	}

	@Override
	public int getMaxPP() {
		return -1;
	}

	@Override
	public void processAttack() {
		
		DialogueManager.start(this.getPokemon().getPokemonTrainer(), PokemonDialogue.get(-69, this.getPokemon()));
		
	}
	
	@Override
	public int getMaxDamage() {
		return -1;
	}

}
