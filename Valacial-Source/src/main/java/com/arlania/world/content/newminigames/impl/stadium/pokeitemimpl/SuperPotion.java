package com.arlania.world.content.newminigames.impl.stadium.pokeitemimpl;

import com.arlania.world.content.newminigames.impl.stadium.PokeItem;
import com.arlania.world.content.newminigames.impl.stadium.Pokemon;

public class SuperPotion extends PokeItem {

	@Override
	public void execute(Pokemon pokemon) {
		int heal = (int)(pokemon.getDefaultConstitution() * 0.65);
		pokemon.heal(heal);
	}

	@Override
	public String getDescription() {
		return "You use a Super Potion and your Pokemon heals!";
	}
	
	

}