package com.arlania.world.content.newminigames.impl.stadium;

import java.util.HashMap;

import com.arlania.world.entity.impl.npc.NPC;
import com.arlania.world.entity.impl.player.Player;

public class Pokemon extends NPC {

	private Player pokemonTrainer;
	private PokemonMove[] moveList;
	private HashMap<PokemonMove, PPTracker> moveTracker;
	private HashMap<String, Object> attributes;
	private Pokemon target;

	public Pokemon(int id, Player pokemonTrainer) {
		super(id, pokemonTrainer.getPosition());
		this.pokemonTrainer = pokemonTrainer;
		PokemonData pokemonData = PokemonData.forId(id);
		this.moveList = new PokemonMove[pokemonData.getMoves().length];
		System.arraycopy(pokemonData.getMoves(), 0, this.moveList, 0, pokemonData.getMoves().length);
		this.moveTracker = new HashMap<PokemonMove, PPTracker>(pokemonData.getMoves().length);
		for (PokemonMove move : pokemonData.getMoves())
			this.moveTracker.put(move, new PPTracker(move));
		this.attributes = new HashMap<String, Object>();
		this.getMovementQueue().setLockMovement(true);
	}

	public void assignTarget(Pokemon target) {
		this.target = target;
	}

	public Player getPokemonTrainer() {
		return this.pokemonTrainer;
	}

	public PokemonMove[] getMoves() {
		return this.moveList;
	}

	public int getUniqueId() {
		return (int) this.attributes.get("POKE-ID");
	}

	public void setAttribute(String attribute, Object data) {
		this.attributes.put(attribute, data);
	}

	public void removeAttribute(String attribute) {
		this.attributes.remove(attribute);
	}

	public Object getAttribute(String attribute) {
		return this.attributes.get(attribute);
	}

	public Pokemon getTarget() {
		return this.target;
	}

	public PPTracker getPPTracker(PokemonMove move) {
		return this.moveTracker.get(move);
	}

	public void sendDeath() {
		PokemonMinigame.GAMES_IN_PROGRESS.get(this.getUniqueId()).endGame(this.getTarget());
	}

	public class PPTracker {

		private int currentPP;
		private PokemonMove pokemonMove;

		public PPTracker(PokemonMove pokemonMove) {
			this.currentPP = pokemonMove.getMaxPP();
			this.pokemonMove = pokemonMove;
		}

		public void decrPP() {
			if (!this.outOfPP())
				this.currentPP--;
		}

		public boolean outOfPP() {
			return this.currentPP == 0;
		}

		public int getCurrentPP() {
			return this.currentPP;
		}
		
		public void addPP(int amount) {
			this.currentPP += amount;
			if(this.currentPP > pokemonMove.getMaxPP())
				this.currentPP = pokemonMove.getMaxPP();
		}

	}

}
