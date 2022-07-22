package com.arlania.world.content.newminigames.impl.stadium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

import com.arlania.model.Position;
import com.arlania.world.World;
import com.arlania.world.content.dialogue.DialogueManager;
import com.arlania.world.content.newminigames.Minigame;
import com.arlania.world.content.newminigames.restriction.Restriction;
import com.arlania.world.entity.impl.npc.NPC;
import com.arlania.world.entity.impl.player.Player;

public class PokemonMinigame extends Minigame {

	public static HashMap<Integer, PokemonMinigame> GAMES_IN_PROGRESS = new HashMap<Integer, PokemonMinigame>();
	public static final Position ENDGAME_POSITION = new Position(3087, 3491, 0);
	public static final Position PLAYER_1_POS = new Position(2721, 2913, 0);
	public static final Position PLAYER_2_POS = new Position(2721, 2916, 0);
	
	public static void main(String[] args) {
		int x = PLAYER_1_POS.getX();
		int y = PLAYER_1_POS.getY();
		int regionX = x >> 3;
		int regionY = y >> 3;
		int regionId = (regionX / 8 << 8) + regionY / 8;
		System.out.println(regionId);
	}
	
	private Pokemon currentPlayer;
	private boolean gameEnded;

	public PokemonMinigame(Pokemon... pokemon) {
		if (pokemon.length > 2)
			throw new RuntimeException("Only 2 pokemon are allowed per minigame!");

		// ADD NPCS AND PLAYERS TO MINIGAME
		this.getNpcs().addAll(Arrays.asList(pokemon));
		this.getNpcs().forEach(n -> {
			Pokemon poke = (Pokemon) n;
			poke.setAttribute("POKE-ID", this.getUniqueId());
			this.enterMinigame(poke.getPokemonTrainer());
			World.register(n);
		});
		// END ADD NPCS AND PLAYERS TO MINIGAME

		this.assignTargets();
		this.movePlayersToStadium();
		this.decideTurn();

		PokemonMinigame.GAMES_IN_PROGRESS.put(this.getUniqueId(), this);
	}

	@Override
	public void enterMinigame(Player player) {
		this.getPlayers().add(player);
		player.setAttribute("POKE-ID", this.getUniqueId());
		player.setController(new StadiumController(this));
		if(player.getSummoning().getFamiliar() != null)
			player.getSummoning().getFamiliar().getSummonNpc().setVisible(false);
	}

	@Override
	public void leaveMinigame(Player player) {

		this.getPlayers().forEach(p -> {
			p.setController(null);
			p.removeAttribute("POKE-ID");
			p.getPacketSender().sendInterfaceRemoval();
			p.getMovementQueue().setLockMovement(false);
			p.moveTo(PokemonMinigame.ENDGAME_POSITION);
			if(p.getSummoning().getFamiliar() != null)
				p.getSummoning().getFamiliar().getSummonNpc().setVisible(true);
		});
		this.getPlayers().remove(player);

		if (this.getPlayers().size() == 1 && !this.gameEnded)
			this.endGame(((Pokemon) this.getNpcs().get(0)));

		// DO NOT REMOVE GAME FROM GAMES IN SESSION TO TRACK PLANE
	}
	
	@Override
	public Restriction[] getRestrictions() {
		return null;
	}

	public void deregisterEntities() {
		this.getNpcs().forEach(npc -> World.deregister(npc));
	}

	private void assignTargets() {
		((Pokemon) this.getNpcs().get(0)).assignTarget((Pokemon) this.getNpcs().get(1));
		((Pokemon) this.getNpcs().get(1)).assignTarget((Pokemon) this.getNpcs().get(0));
	}

	private void movePlayersToStadium() {
		for (int i = 0; i < 2; i++) {
			Position pos = i == 0 ? PokemonMinigame.PLAYER_1_POS : PokemonMinigame.PLAYER_2_POS;
			this.getPlayers().get(i).moveTo(pos);
			this.getPlayers().get(i).getMovementQueue().setLockMovement(true);
			this.getNpcs().get(i).moveTo(new Position(pos.getX() + 1, pos.getY(), pos.getZ()));
		}
		this.getNpcs().get(0).setPositionToFace(this.getNpcs().get(1).getPosition());
		this.getNpcs().get(1).setPositionToFace(this.getNpcs().get(0).getPosition());
		((Pokemon) this.getNpcs().get(0)).getPokemonTrainer()
				.setPositionToFace(((Pokemon) this.getNpcs().get(1)).getPokemonTrainer().getPosition());
		((Pokemon) this.getNpcs().get(1)).getPokemonTrainer()
				.setPositionToFace(((Pokemon) this.getNpcs().get(0)).getPokemonTrainer().getPosition());
	}

	@SuppressWarnings("unused")
	private int getPlane() {
		return PokemonMinigame.GAMES_IN_PROGRESS.size() * 4;
	}

	public void endGame(Pokemon winner) {
		this.gameEnded = true;
		if (winner != null) {
			if (winner.getPokemonTrainer() != null) {
				this.giveReward(winner.getPokemonTrainer());
				this.leaveMinigame(winner.getPokemonTrainer());
			} else {
				throw new RuntimeException("[PokemonMinigame]-" + this.getUniqueId() + ": Null pokemon trainer!");
			}
		}
		this.deregisterEntities();
	}

	private void giveReward(Player winner) {
		winner.sendMessage("You won!");
		if (winner.getDueling().duelingWith != -1) {
			Player duelEnemy = World.getPlayers().get(winner.getDueling().duelingWith);
			winner.getDueling().duelVictory();
			if (duelEnemy != null) {
				duelEnemy.getDueling().reset();
				duelEnemy.sendMessage("You lost to " + winner.getUsername() + " pl3b.");
				World.getPlayers().forEach(p -> p.sendMessage(winner.getUsername() + " just won a Pokemon Battle against " + duelEnemy.getUsername()));
			}
		}
	}

	public void decideTurn() {
		if (this.currentPlayer == null)
			this.currentPlayer = (Pokemon) this.getNpcs().get(new Random().nextInt(2));
		this.currentPlayer = (Pokemon) this.getNpcs().get(1 - this.getNpcs().indexOf(this.currentPlayer));
		this.currentPlayer.forceChat("Your turn!");
		DialogueManager.start(this.getCurrentPlayer(), PokemonDialogue.get(0, this.currentPlayer));
	}

	public Player getCurrentPlayer() {
		return this.currentPlayer.getPokemonTrainer();
	}

	public Pokemon getPokemonByTrainer(Player player) {
		for (NPC n : this.getNpcs()) {
			Pokemon poke = (Pokemon) n;
			if (poke.getPokemonTrainer() == player)
				return poke;
		}
		return null;
	}

}
