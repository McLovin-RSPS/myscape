package com.arlania.world.content.newminigames.impl.stadium;

import java.util.Random;

import com.arlania.model.Graphic;
import com.arlania.world.content.dialogue.DialogueManager;
import com.arlania.world.content.newminigames.impl.stadium.impl.NoMove;

public abstract class PokemonMove {

	private Pokemon pokemon;

	public void setPokemon(Pokemon pokemon) {
		this.pokemon = pokemon;
	}

	public Pokemon getPokemon() {
		return this.pokemon;
	}

	public Pokemon getTarget() {
		return this.pokemon.getTarget();
	}

	public abstract String getName();

	public abstract int getMaxPP();

	public abstract void processAttack();

	public abstract int getMaxDamage();

	public int getDamage() {
		return new Random().nextInt(this.getMaxDamage());
	}
	
	public void sendCriticalHit(int damage) {
		double maxDamage = (double) this.getMaxDamage();
		if(damage > (maxDamage * 0.65))
			this.getPokemon().getPokemonTrainer().forceChat("It was a critical hit!");
	}

	public synchronized boolean execute() {
		if (this.pokemon == null || this.getTarget() == null) {
			throw new RuntimeException("No pokemon or target! Pokemon " + this.pokemon + " Target " + this.getTarget());
		} else {
			if (this.canAttack()) {
				this.getPokemon().setPositionToFace(this.getTarget().getPosition());
				this.getTarget().setPositionToFace(this.getPokemon().getPosition());

				this.processAttack();

				if (!(this instanceof NoMove)) {
					// this.getPokemon().getPokemonTrainer().getPacketSender().sendInterfaceRemoval();
					DialogueManager.start(this.getPokemon().getPokemonTrainer(),
							PokemonDialogue.get(-70, this.getPokemon()));
					this.getPokemon().getPPTracker(this).decrPP();
				//	((PokemonMinigame) this.getPokemon().getPokemonTrainer().getController().getAssociatedMinigame())
							//.decideTurn();
				}
				return true;
			} else
				return false;
		}
	}

	private boolean canAttack() {
		if (this instanceof NoMove)
			return true;
		if (this.pokemon.getPPTracker(this).outOfPP()) {
			DialogueManager.start(this.pokemon.getPokemonTrainer(), PokemonDialogue.get(2, this.pokemon));
			return false;
		}
		return true;
	}

	public void performGraphic(Graphic graphic) {
		if (graphic == null)
			return;
		performGraphic(graphic);
	}

}
