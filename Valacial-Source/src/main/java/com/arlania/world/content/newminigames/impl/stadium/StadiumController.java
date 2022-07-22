package com.arlania.world.content.newminigames.impl.stadium;

import com.arlania.model.Locations;
import com.arlania.model.Position;
import com.arlania.world.content.dialogue.DialogueManager;
import com.arlania.world.content.newminigames.Controller;
import com.arlania.world.content.newminigames.impl.stadium.PokeItem.PokeItemInfo;
import com.arlania.world.content.newminigames.impl.stadium.impl.NoMove;
import com.arlania.world.entity.impl.npc.NPC;
import com.arlania.world.entity.impl.player.Player;

public class StadiumController extends Controller {

	public StadiumController() {
		
	}
	
	public StadiumController(PokemonMinigame associatedMinigame) {
		super();
	}

	@Override
	public boolean canMove() {
		return true;
	}

	@Override
	public boolean canFightPlayer() {
		/* NOT AVAILABLE */
		return false;
	}

	@Override
	public boolean canFightNPC() {
		/* NOT AVAILABLE */
		return false;
	}

	@Override
	public void login(Player player) {
		/* NOT AVAILABLE */
	}

	@Override
	public boolean logout(Player player) {
		if (this.getAssociatedMinigame() != null)
			this.getAssociatedMinigame().safeMinigame(player);
		Locations.Location.DUEL_ARENA.logout(player);
		return true;
	}

	@Override
	public boolean handleDeath(Player player) {
		/* NOT AVAILABLE */
		return false;
	}

	@Override
	public Position getRespawnLocation() {
		/* NOT AVAILABLE */
		return null;
	}

	@Override
	public boolean teleport(Player player) {
		/*
		 * No teleporting
		 */
		player.sendMessage("You must leave the minigame before teleporting!");
		return true;
	}

	@Override
	public void sendInterface(Player player) {

	}

	@Override
	public boolean handlePlayerDeath(Player player, Player target) {
		return true;
	}

	@Override
	public boolean handleNPCDeath(Player player, NPC target) {
		return false;
	}

	@Override
	public boolean handleNPCClick(Player player, NPC n, int option) {
		if (n instanceof Pokemon) {
			
			Pokemon pokemon = (Pokemon) n;
			if (pokemon.getPokemonTrainer().getUsername().equalsIgnoreCase(player.getUsername())) {
				PokemonMinigame minigame = (PokemonMinigame) this.getAssociatedMinigame();
				if (minigame != null) {
					if (minigame.getCurrentPlayer().getUsername().equalsIgnoreCase(player.getUsername())) {
						DialogueManager.start(minigame.getCurrentPlayer(), PokemonDialogue.get(0, pokemon));
					}
				}
			}
		} else
			player.sendMessage("You cannot interact with that right now.");
		return true;
	}

	@Override
	public boolean handlePlayerClick(Player player, Player other, int option) {
		player.sendMessage("You cannot interact with that right now.");
		return true;
	}

	@Override
	public boolean handleObjectClick(Player player, Object o, int option) {
		player.sendMessage("You cannot interact with that right now.");
		return true;
	}

	@Override
	public boolean handleButtonClick(Player player, int buttonId) {
		if (player.getDialogue() != null) {
			switch (player.getDialogue().id()) {
			case 0:// SELECT OPTION (FIGHT, ITEM RUN)
				if (buttonId == 2471) {
					DialogueManager.start(player, PokemonDialogue.get(1, this.minigame().getPokemonByTrainer(player)));
				} else if (buttonId == 2472) {
					DialogueManager.start(player, PokemonDialogue.get(3, this.minigame().getPokemonByTrainer(player)));
				} else if (buttonId == 2473) {
					this.minigame().leaveMinigame(player);
				}
				break;
			case 1:// PICK MOVE
				Pokemon pokemon = this.minigame().getPokemonByTrainer(player);
				if (buttonId >= 2494 && buttonId <= 2497) {
					PokemonMove move = null;
					int selectedMove = buttonId - 2494;

					try {
						move = pokemon.getMoves()[selectedMove];
					} catch (ArrayIndexOutOfBoundsException | NullPointerException e) {

					} finally {
						if (move == null) {
							move = new NoMove();
							move.setPokemon(pokemon);
						}
						move.setPokemon(pokemon);
						move.execute();
					}
				} else if (buttonId == 2498) {
					DialogueManager.start(player, PokemonDialogue.get(0, pokemon));
				}
				break;
			case 3:
				pokemon = this.minigame().getPokemonByTrainer(player);
				if (buttonId >= 2494 && buttonId <= 2497) {
					int selectedMove = buttonId - 2494;
					PokeItem[] items = new PokeItem[4];
					int itemsCount = 0;

					for (PokeItemInfo pokeItem : PokeItem.PokeItemInfo.values()) {
						if (itemsCount == 3)
							break;
						if (pokemon.getPokemonTrainer().getInventory().contains(pokeItem.getItemId())) {
							items[itemsCount++] = pokeItem.getPokeItem();
						}
					}

					PokeItem selectedItem = items[selectedMove];
					if (selectedItem != null) {
						selectedItem.execute(pokemon);
						((PokemonMinigame) ((Controller) pokemon.getPokemonTrainer().getController()).getAssociatedMinigame())
								.decideTurn();
						if (selectedItem.getDialogue() != null)
							DialogueManager.start(pokemon.getPokemonTrainer(), selectedItem.getDialogue());
					}
				} else if (buttonId == 2498) {
					DialogueManager.start(player, PokemonDialogue.get(0, pokemon));
				}
				break;
			}
		}
		return false;
	}

	@Override
	public boolean handleItemClick(Player player, int itemId, int action) {
		switch (action) {
		case 1:
		case 2:
		case 3:
		case 4:
			player.sendMessage("You cannot do that right now.");
			return true;
		}
		return false;
	}

	public PokemonMinigame minigame() {
		return (PokemonMinigame) this.getAssociatedMinigame();
	}

}
