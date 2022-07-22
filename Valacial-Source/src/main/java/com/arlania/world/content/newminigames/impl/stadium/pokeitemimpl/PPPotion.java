package com.arlania.world.content.newminigames.impl.stadium.pokeitemimpl;

import com.arlania.world.content.newminigames.impl.stadium.PokeItem;
import com.arlania.world.content.newminigames.impl.stadium.Pokemon;
import com.arlania.world.content.newminigames.impl.stadium.PokemonMove;

public class PPPotion extends PokeItem {

	@Override
	public void execute(Pokemon pokemon) {
		
		PokemonMove[] moves = pokemon.getMoves();
		for(PokemonMove move: moves) {
			
			pokemon.getPPTracker(move).addPP(5);
			
		}
		
	}

	@Override
	public String getDescription() {
		return "You use a PP Potion and add 5 PP to all your moves!";
	}

}
