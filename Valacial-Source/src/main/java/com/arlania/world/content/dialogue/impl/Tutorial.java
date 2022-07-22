package com.arlania.world.content.dialogue.impl;

import com.arlania.GameSettings;
import com.arlania.engine.task.Task;
import com.arlania.engine.task.TaskManager;
import com.arlania.model.Direction;
import com.arlania.model.GameMode;
import com.arlania.model.Position;
import com.arlania.net.security.ConnectionHandler;
import com.arlania.world.World;
import com.arlania.world.content.clan.ClanChatManager;
import com.arlania.world.content.dialogue.Dialogue;
import com.arlania.world.content.dialogue.DialogueExpression;
import com.arlania.world.content.dialogue.DialogueType;
import com.arlania.world.entity.impl.player.Player;

/**
 * Represents a Dungeoneering party invitation dialogue
 * 
 * @author Gabriel Hannason
 */

public class Tutorial {

	public static Dialogue get(Player p, int stage) {
		Dialogue dialogue = null;
		switch(stage) {
		case 0:
			dialogue = new Dialogue() {

				@Override
				public DialogueType type() {
					return DialogueType.NPC_STATEMENT;
				}

				@Override
				public DialogueExpression animation() {
					return DialogueExpression.NORMAL;
				}

				@Override
				public String[] dialogue() {
					return new String[]{"Ah, a wise choice indeed! So let's get you started out,", "shall we? I'll give you a few tips and once you've finished", "listening to me, I'll give you a @red@$1 Bond@bla@ for your", "patience. Let's start with the most important aspect; money!"};
				};

				@Override
				public int npcId() {
					return 8736;
				}

				@Override
				public Dialogue nextDialogue() {
					return get(p, stage + 1);
				}
			};
			break;
		case 1:
			dialogue = new Dialogue() {

				@Override
				public DialogueType type() {
					return DialogueType.NPC_STATEMENT;
				}

				@Override
				public DialogueExpression animation() {
					return DialogueExpression.NORMAL;
				}

				@Override
				public String[] dialogue() {
					return new String[]{"You can earn money doing many different things in", "ImaginePS. For example, see those AFK rocks infront of ", "you? You can mine from them to get  @red@AFK Tickets@bla@", "trade it to the merchant whose floating over there."};
				};

				@Override
				public int npcId() {
					return 8736;
				}

				@Override
				public void specialAction() {
					p.moveTo(new Position(3828, 3487));
					p.setDirection(Direction.SOUTH);

				}

				@Override
				public Dialogue nextDialogue() {
					return get(p, stage + 1);
				}
			};
			break;
		case 2:
			dialogue = new Dialogue() {

				@Override
				public DialogueType type() {
					return DialogueType.NPC_STATEMENT;
				}

				@Override
				public DialogueExpression animation() {
					return DialogueExpression.NORMAL;
				}

				@Override
				public String[] dialogue() {
					return new String[]{"You can click on your guide book to get some quick tips.", "You got it from your starter."};
				};

				@Override
				public int npcId() {
					return 8736;
				}

				@Override
				public void specialAction() {
					p.moveTo(new Position(3817, 3484));
					p.setDirection(Direction.NORTH);

				}

				@Override
				public Dialogue nextDialogue() {
					return get(p, stage + 1);
				}
			};
			break;
		case 3:
			dialogue = new Dialogue() {

				@Override
				public DialogueType type() {
					return DialogueType.NPC_STATEMENT;
				}

				@Override
				public DialogueExpression animation() {
					return DialogueExpression.CALM;
				}

				@Override
				public String[] dialogue() {
					return new String[]{"`Talk to the Player-Owned Store Manager to access", "other players for-sale items."};
				};

				@Override
				public int npcId() {
					return 8736;
				}

				@Override
				public void specialAction() {
					p.moveTo(new Position(3817, 3498));
					p.setDirection(Direction.EAST);
				}

				@Override
				public Dialogue nextDialogue() {
					return get(p, stage + 1);
				}
			};
			break;
		case 4:
			dialogue = new Dialogue() {

				@Override
				public DialogueType type() {
					return DialogueType.NPC_STATEMENT;
				}

				@Override
				public DialogueExpression animation() {
					return DialogueExpression.NORMAL;
				}

				@Override
				public String[] dialogue() {
					return new String[]{"The next important thing you need to learn is navigating.", "All important teleports can by clicking the", "World-Map. It's under the XP ICON & money pouch icon."};
				};

				@Override
				public int npcId() {
					return 8736;
				}

				@Override
				public void specialAction() {
					p.moveTo(GameSettings.DEFAULT_POSITION.copy());
					p.setDirection(Direction.NORTH);
					p.getPacketSender().sendTab(GameSettings.QUESTS_TAB);
				}

				@Override
				public Dialogue nextDialogue() {
					return get(p, stage + 1);
				}
			};
			break;
		case 5:
			dialogue = new Dialogue() {

				@Override
				public DialogueType type() {
					return DialogueType.NPC_STATEMENT;
				}

				@Override
				public DialogueExpression animation() {
					return DialogueExpression.NORMAL;
				}

				@Override
				public String[] dialogue() {
					return new String[]{"If you wish to navigate to a skill's training location,", "simply press the on the respective skill in the skill tab."};
				};

				@Override
				public int npcId() {
					return 8736;
				}

				@Override
				public void specialAction() {
					p.getPacketSender().sendTab(GameSettings.SKILLS_TAB);
				}

				@Override
				public Dialogue nextDialogue() {
					return get(p, stage + 1);
				}
			};
			break;
		case 6:
			dialogue = new Dialogue() {

				@Override
				public DialogueType type() {
					return DialogueType.NPC_STATEMENT;
				}

				@Override
				public DialogueExpression animation() {
					return DialogueExpression.NORMAL;
				}

				@Override
				public String[] dialogue() {
					return new String[]{"Enough of the boring stuff, let's show you some creatures!", "There are a bunch of @red@custom bosses@bla@ to fight in ImaginePS.", "Every boss drops unique and good gear when killed.", "One example, this is Whitebeard!"};
				};

				@Override
				public int npcId() {
					return 8736;
				}

				@Override
				public void specialAction() {
					p.moveTo(new Position(2449, 2848));
				}

				@Override
				public Dialogue nextDialogue() {
					return get(p, stage + 1);
				}
			};
			break;
		case 7:
			dialogue = new Dialogue() {

				@Override
				public DialogueType type() {
					return DialogueType.NPC_STATEMENT;
				}

				@Override
				public DialogueExpression animation() {
					return DialogueExpression.NORMAL;
				}

				@Override
				public String[] dialogue() {
					return new String[]{"Ah.. Cosmetic Zone", "I love this place.", "Kill the Donkeys to get a Cosmetic box."};
				};

				@Override
				public int npcId() {
					return 8736;
				}

				@Override
				public void specialAction() {
					p.moveTo(new Position(2969, 2579));
				}

				@Override
				public Dialogue nextDialogue() {
					return get(p, stage + 1);
				}
			};
			break;
		case 8:
			dialogue = new Dialogue() {

				@Override
				public DialogueType type() {
					return DialogueType.NPC_STATEMENT;
				}

				@Override
				public DialogueExpression animation() {
					return DialogueExpression.NORMAL;
				}

				@Override
				public String[] dialogue() {
					return new String[]{"ImaginePS offers over 30 tiers", "Slayer is a good way to progress.", "Here are the slayer master you'll come to after each task.", "Your first task will be @red@Bomby's Minions@bla@."};
				};

				@Override
				public int npcId() {
					return 8736;
				}

				@Override
				public void specialAction() {
					p.moveTo(new Position(3830, 3516));
				}

				@Override
				public Dialogue nextDialogue() {
					return get(p, stage + 1);
				}
			};
			break;
		case 9:
			dialogue = new Dialogue() {

				@Override
				public DialogueType type() {
					return DialogueType.NPC_STATEMENT;
				}

				@Override
				public DialogueExpression animation() {
					return DialogueExpression.NORMAL;
				}

				@Override
				public String[] dialogue() {
					return new String[]{"This is the Donator's zone.", "Players who have a Donator rank can teleport here", "and take advantage of the resources that it has." };
				};

				@Override
				public int npcId() {
					return 8736;
				}

				@Override
				public void specialAction() {
					p.moveTo(new Position(3755, 3476));
				}

				@Override
				public Dialogue nextDialogue() {
					return get(p, stage + 1);
				}
			};
			break;
		case 10:
			dialogue = new Dialogue() {

				@Override
				public DialogueType type() {
					return DialogueType.NPC_STATEMENT;
				}

				@Override
				public DialogueExpression animation() {
					return DialogueExpression.NORMAL;
				}

				@Override
				public String[] dialogue() {
					return new String[]{"To receive a Donator rank, you'd need to claim", "$10 worth of @red@Bonds@bla@.", "Upon claiming a bond, you can buy from in-game store.", "You can buy bonds from @red@::store@bla@ or in-game." };
				};

				@Override
				public int npcId() {
					return 8736;
				}

				@Override
				public void specialAction() {
					p.moveTo(new Position(3755, 3476));
				}

				@Override
				public Dialogue nextDialogue() {
					return get(p, stage + 1);
				}
			};
			break;
		case 11:
			dialogue = new Dialogue() {

				@Override
				public DialogueType type() {
					return DialogueType.NPC_STATEMENT;
				}

				@Override
				public DialogueExpression animation() {
					return DialogueExpression.NORMAL;
				}

				@Override
				public String[] dialogue() {
					return new String[]{"ImaginePS is a competitive game. We host a variety of Events", "of which will test your skills."};
				};

				@Override
				public int npcId() {
					return 8736;
				}

				@Override
				public void specialAction() {
					p.moveTo(new Position(3817, 3484));
					p.setDirection(Direction.NORTH);
					p.getPacketSender().sendTabInterface(GameSettings.QUESTS_TAB, 50850)
							.sendTab(GameSettings.QUESTS_TAB);

				}

				@Override
				public Dialogue nextDialogue() {
					return get(p, stage + 1);
				}
			};
			break;
		case 12:
			dialogue = new Dialogue() {

				@Override
				public DialogueType type() {
					return DialogueType.NPC_STATEMENT;
				}

				@Override
				public DialogueExpression animation() {
					return DialogueExpression.NORMAL;
				}

				@Override
				public String[] dialogue() {
					return new String[]{"That was almost all.", "I just want to remind you to vote for us on various", "gaming toplists. To do so, simply use the ::vote command.", "You will be rewarded!"};
				};

				@Override
				public int npcId() {
					return 8736;
				}

				@Override
				public void specialAction() {
					p.moveTo(GameSettings.DEFAULT_POSITION.copy());
					p.setDirection(Direction.SOUTH);
				}

				@Override
				public Dialogue nextDialogue() {
					return get(p, stage + 1);
				}
			};
			break;
		case 13:
			dialogue = new Dialogue() {

				@Override
				public DialogueType type() {
					return DialogueType.NPC_STATEMENT;
				}

				@Override
				public DialogueExpression animation() {
					return DialogueExpression.NORMAL;
				}

				@Override
				public String[] dialogue() {
					return new String[]{"If you have any more questions, simply use the ::help", "command and a staff member should get back to you.", "You can also join the clanchat channel 'help' and ask", "other players for help there too. Have fun playing	ImaginePS!"};
				};

				@Override
				public int npcId() {
					return 8736;
				}

				@Override
				public Dialogue nextDialogue() {
					return get(p, stage + 1);
				}
			};
			break;

		case 14:
			dialogue = new Dialogue() {

				@Override
				public DialogueType type() {
					return DialogueType.NPC_STATEMENT;
				}

				@Override
				public DialogueExpression animation() {
					return DialogueExpression.NORMAL;
				}

				@Override
				public String[] dialogue() {
					return new String[]{"If you have any more questions, simply use the ::help", "command and a staff member should get back to you.", "You can also join the clanchat channel 'help' and ask", "other players for help there too. Have fun playing ImaginePS!"};
				};

				@Override
				public int npcId() {
					return 8736;
				}

				@Override
				public void specialAction() {
					if(ConnectionHandler.getStarters(p.getHostAddress()) <= GameSettings.MAX_STARTERS_PER_IP) {
						if(p.newPlayer() == true ) {
							p.getInventory().add(15354, 1);
						}	else {
							p.getPacketSender().sendMessage("You already received your bond.");

						}
//						if(p.getGameMode() != GameMode.NORMAL) {
//							p.getInventory().add(995, 10000).add(1153, 1).add(1115, 1).add(1067, 1).add(1323, 1).add(1191, 1).add(841, 1).add(882, 50).add(1167, 1).add(1129, 1).add(1095, 1).add(1063, 1).add(579, 1).add(577, 1).add(1011, 1).add(1379, 1).add(556, 50).add(558, 50).add(557, 50).add(555, 50).add(1351, 1).add(1265, 1).add(1712, 1).add(11118, 1).add(1007, 1).add(1061, 1).add(330, 100).add(1419, 1);
//						} else {
//							p.getInventory().add(995, 5000000).add(1153, 1).add(1115, 1).add(1067, 1).add(1323, 1).add(1191, 1).add(841, 1).add(882, 1000).add(1167, 1).add(1129, 1).add(1095, 1).add(1063, 1).add(579, 1).add(577, 1).add(1011, 1).add(1379, 1).add(556, 1000).add(558, 1000).add(557, 1000).add(555, 1000).add(1351, 1).add(1265, 1).add(1712, 1).add(11118, 1).add(1007, 1).add(1061, 1).add(386, 100).add(1419, 1);
//						}
						p.getPacketSender().sendMessage("<col=FF0066>You've been given a $1 Bond! It is untradeable.");
						ConnectionHandler.addStarter(p.getHostAddress(), true);

						p.setReceivedStarter(true); //Incase they want to change game mode, regives starter items
					} else {

						p.getPacketSender().sendMessage("Your connection has received enough starting items.");
					}
					//p.getPacketSender().sendInterface(3559);
					p.getAppearance().setCanChangeAppearance(true);
					p.setPlayerLocked(false);
//					ClanChatManager.join(p, "fantasy");
//					World.sendMessage("<img=10> <col=6600CC>[NEW PLAYER]: "+p.getUsername()+" has logged into ImaginePS for the first time!");

					//reset interface?
					//resetInterface(player);
					TaskManager.submit(new Task(20, p, false) {
						@Override
						protected void execute() {
							if(p != null && p.isRegistered()) {
								p.getPacketSender().sendMessage("<img=10> @blu@Thank you for playing. We hope you enjoy your time here at ImaginePS.");
								p.setNewPlayer(false);
							}
							stop();
						}
					});
					p.save();
				}

				@Override
				public Dialogue nextDialogue() {
					return get(p, stage + 1);
				}
			};
			break;
		}
		return dialogue;
	}


}