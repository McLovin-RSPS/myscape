package com.arlania.world.content.newminigames;

import java.util.ArrayList;
import java.util.Iterator;

import com.arlania.world.World;
import com.arlania.world.content.newminigames.restriction.Restriction;
import com.arlania.world.entity.impl.player.Player;

/**
 * A template for creating Lobby children
 * 
 * @author Skwishy
 *
 */
public abstract class Lobby {

	/**
	 * The list of players inside the lobby
	 */
	private ArrayList<Player> players;
	/**
	 * The number of minimum required players to start execution
	 */
	private int minimumRequiredPlayers;
	/**
	 * The lobby countdown time
	 */
	private LobbyTime lobbyCountdownTime;

	/**
	 * A class constructor with no countdown required
	 * 
	 * @param minimumRequiredPlayers The minimum amount of required players to start
	 *                               execution
	 */
	public Lobby(int minimumRequiredPlayers) {
		this.minimumRequiredPlayers = minimumRequiredPlayers;
		this.lobbyCountdownTime = LobbyTime.NONE;
		this.players = new ArrayList<Player>();
	}

	/**
	 * A class constructor that takes a lobby countdown time
	 * 
	 * @param lobbyCountdownTime     The time type to be used
	 * @param minimumRequiredPlayers The minimum amount of required players to start
	 *                               execution
	 */
	public Lobby(LobbyTime lobbyCountdownTime, int minimumRequiredPlayers) {
		this.minimumRequiredPlayers = minimumRequiredPlayers;
		this.lobbyCountdownTime = lobbyCountdownTime;
		this.players = new ArrayList<Player>();
	}

	/**
	 * The total wait time for the lobby
	 * 
	 * @return
	 */
	public int getLobbyWaitTime() {
		return this.lobbyCountdownTime.getTimeToWait();
	}

	/**
	 * The current countdown for the lobby
	 * 
	 * @return
	 */
	public int getLobbyCountdown() {
		return this.lobbyCountdownTime.getCurrentWait();
	}

	/**
	 * The list of players for the lobby
	 * 
	 * @return
	 */
	public ArrayList<Player> getPlayers() {
		return this.players;
	}

	/**
	 * Adds players into the lobby
	 * 
	 * @param player The player to be added
	 */
	public void addPlayer(Player player) {
		if (this.canEnterLobby(player)) {
			if (!this.players.contains(player)) {
				this.players.add(player);
				player.sendMessage("You have joined the lobby!");
			} else {
				player.sendMessage("You have already joined the lobby!");
			}
		}
	}

	/**
	 * Removes players from the lobby
	 * 
	 * @param player THe player to be removed
	 */
	public void removePlayer(Player player) {
		this.players.remove(player);
	}

	/**
	 * The main sequence of each Lobby instance
	 */
	public void sequence() {
		int currentSize = this.getPlayers().size();
		if (currentSize >= this.minimumRequiredPlayers) {
			if (this.getLobbyCountdown() == 0) {
				if(!this.initiateGame()) {
					this.getPlayers().forEach(p -> {
						p.sendMessage("The minigame has not started because some players have violated ");
						p.sendMessage("the restrictions for the minigame and there are not enough players");
						p.sendMessage("to continue playing. Please rejoin the lobby to play again.");
					});
				} else
					this.initialize();
				
				this.getPlayers().forEach(p -> {
					if(World.getPlayers().contains(p))
						p.getPacketSender().sendInterfaceRemoval();
				});
				this.getPlayers().clear();
			} else {
				this.lobbyCountdownTime.setCurrentWait(this.getLobbyCountdown() - 1);
			}
		} else if (this.getLobbyCountdown() < this.getLobbyWaitTime())
			this.lobbyCountdownTime.resetWait();

		this.sendLobbyTimer();
	}

	/**
	 * Sends the UI for lobby
	 */
	private void sendLobbyTimer() {
		this.players.forEach(p -> {
			p.getPacketSender().sendInterface(21005);
			p.sendParallellInterfaceVisibility(21005, false);
			p.getPacketSender().sendString(21006, "Lobby");
			p.getPacketSender().sendString(21007, "Next Departure: " + this.lobbyCountdownTime.getCurrentWait());
			p.getPacketSender().sendString(21008,
					"Players Ready: (" + this.players.size() + " / " + this.minimumRequiredPlayers + ")");
			p.getPacketSender().sendString(21009,
					"(Need " + this.minimumRequiredPlayers + " to " + (this.minimumRequiredPlayers * 3) + " players)");
		});
	}

	/**
	 * Abstract initializer depending on implementation of class
	 */
	public abstract void initialize();

	/**
	 * The restriction of the Lobby
	 * 
	 * @return Restriction
	 */
	public abstract Restriction[] getRestrictions();

	private boolean canEnterLobby(Player player) {
		boolean canEnter = true;
		for (Restriction restriction : this.getRestrictions()) {
			if (!restriction.check(player)) {
				canEnter = false;
			}
		}
		return canEnter;
	}
	
	private boolean initiateGame() {
		Iterator<Player> players = this.getPlayers().iterator();
		while(players.hasNext()) {
			Player playerToCheck = players.next();
			if(!this.canEnterLobby(playerToCheck)) {
				playerToCheck.getPacketSender().sendInterfaceRemoval();
				this.getPlayers().remove(playerToCheck);
			}
		}
		return this.getPlayers().size() >= this.minimumRequiredPlayers;
	}

	/**
	 * Predefined lobby times for Lobby object
	 * 
	 * @author Skwishy
	 *
	 */
	public enum LobbyTime {

		NONE(0), QUARTER(15), HALF(30), QUARTER3(45), MINUTE(60);

		LobbyTime(int timeToWait) {
			this.timeToWait = timeToWait;
		}

		private int timeToWait;
		private int currentWait;

		public int getTimeToWait() {
			return this.timeToWait;
		}

		public int getCurrentWait() {
			return this.currentWait;
		}

		public void setCurrentWait(int currentWait) {
			this.currentWait = currentWait;
		}

		public void resetWait() {
			this.currentWait = this.timeToWait;
		}

	}

}
