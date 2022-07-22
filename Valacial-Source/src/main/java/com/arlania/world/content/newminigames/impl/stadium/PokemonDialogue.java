package com.arlania.world.content.newminigames.impl.stadium;

import com.arlania.model.definitions.ItemDefinition;
import com.arlania.world.content.dialogue.Dialogue;
import com.arlania.world.content.dialogue.DialogueExpression;
import com.arlania.world.content.dialogue.DialogueType;
import com.arlania.world.content.newminigames.impl.stadium.PokeItem.PokeItemInfo;
import com.arlania.world.content.newminigames.impl.stadium.Pokemon.PPTracker;

public class PokemonDialogue {

	public static Dialogue get(int stage, Pokemon pokemon) {
		Dialogue dialogue = null;
		switch (stage) {
		case 0:
			dialogue = new Dialogue() {

				@Override
				public DialogueType type() {
					return DialogueType.OPTION;
				}

				@Override
				public DialogueExpression animation() {
					return DialogueExpression.NO_EXPRESSION;
				}

				@Override
				public String[] dialogue() {
					return new String[] { "Fight", "Item", "Run" };
				}

				@Override
				public int id() {
					return stage;
				}
			};
			break;
		case 1:
			dialogue = new Dialogue() {

				@Override
				public DialogueType type() {
					return DialogueType.OPTION;
				}

				@Override
				public DialogueExpression animation() {
					return DialogueExpression.NO_EXPRESSION;
				}

				@Override
				public String[] dialogue() {
					PokemonMove[] moves = pokemon.getMoves();
					String[] moveNames = new String[5];

					for (int i = 0; i < 4; i++) {
						try {
							PPTracker tracker = pokemon.getPPTracker(moves[i]);
							moveNames[i] = moves[i].getName() + " (" + tracker.getCurrentPP() + " / "
									+ moves[i].getMaxPP() + " PP)";
						} catch (ArrayIndexOutOfBoundsException e) {
							moveNames[i] = "----";
						}
					}
					moveNames[4] = "Back";

					return moveNames;
				}

				@Override
				public int id() {
					return stage;
				}
			};

			break;
		case 2:

			dialogue = new Dialogue() {

				@Override
				public DialogueType type() {
					return DialogueType.STATEMENT;
				}

				@Override
				public DialogueExpression animation() {
					return DialogueExpression.NO_EXPRESSION;
				}

				@Override
				public String[] dialogue() {
					return new String[] { "You do not have enough PP for that move." };
				}

				@Override
				public Dialogue nextDialogue() {
					return PokemonDialogue.get(1, pokemon);
				}

			};

			break;
		case 3:
			dialogue = new Dialogue() {

				@Override
				public DialogueType type() {
					return DialogueType.OPTION;
				}

				@Override
				public DialogueExpression animation() {
					return DialogueExpression.NO_EXPRESSION;
				}

				@Override
				public String[] dialogue() {
					String[] items = new String[5];
					int itemsCount = 0;
					
					for(PokeItemInfo pokeItem: PokeItem.PokeItemInfo.values()) {
						if(itemsCount == 3)
							break;
						if(pokemon.getPokemonTrainer().getInventory().contains(pokeItem.getItemId())) {
							items[itemsCount++] = ItemDefinition.forId(pokeItem.getItemId()).getName();
						}
					}
					items[4] = "Back";
					
					for(int i = 0; i < items.length; i++) {
						if(items[i] == null)
							items[i] = "----";
					}
					
					return items;
				}

				@Override
				public int id() {
					return stage;
				}
			};

			break;
		case -69:

			dialogue = new Dialogue() {

				@Override
				public DialogueType type() {
					return DialogueType.STATEMENT;
				}

				@Override
				public DialogueExpression animation() {
					return DialogueExpression.NO_EXPRESSION;
				}

				@Override
				public String[] dialogue() {
					return new String[] { "You cannot use that move!" };
				}

				@Override
				public Dialogue nextDialogue() {
					return PokemonDialogue.get(1, pokemon);
				}

			};

			break;
		case -70:
			dialogue = new Dialogue() {

				@Override
				public DialogueType type() {
					return DialogueType.STATEMENT;
				}

				@Override
				public DialogueExpression animation() {
					return DialogueExpression.NO_EXPRESSION;
				}

				@Override
				public String[] dialogue() {
					return new String[] { "Your turn has now ended..." };
				}

			};

			break;
		}
		return dialogue;
	}

}
