package com.arlania.net.packet.impl;

import com.arlania.engine.task.impl.WalkToTask;
import com.arlania.engine.task.impl.WalkToTask.FinalizedMovementTask;
import com.arlania.model.Animation;
import com.arlania.model.Graphic;
import com.arlania.model.Item;
import com.arlania.model.PlayerRights;
import com.arlania.model.Position;
import com.arlania.model.Skill;
import com.arlania.model.container.impl.Shop.ShopManager;
import com.arlania.model.definitions.NpcDefinition;
import com.arlania.model.input.impl.GambleAmount;
import com.arlania.net.packet.Packet;
import com.arlania.net.packet.PacketListener;
import com.arlania.util.Misc;
import com.arlania.world.World;
import com.arlania.world.content.EnergyHandler;
import com.arlania.world.content.LoyaltyProgramme;
import com.arlania.world.content.PlayerPanel;
import com.arlania.world.content.combat.CombatFactory;
import com.arlania.world.content.combat.magic.CombatSpell;
import com.arlania.world.content.combat.magic.CombatSpells;
import com.arlania.world.content.combat.weapon.CombatSpecial;
import com.arlania.world.content.dialogue.DialogueManager;
import com.arlania.world.content.dialogue.impl.Tutorial;
import com.arlania.world.content.minigames.impl.WarriorsGuild;
import com.arlania.world.content.skill.impl.crafting.Tanning;
import com.arlania.world.content.skill.impl.dungeoneering.Dungeoneering;
import com.arlania.world.content.skill.impl.fishing.Fishing;
import com.arlania.world.content.skill.impl.hunter.PuroPuro;
import com.arlania.world.content.skill.impl.runecrafting.DesoSpan;
import com.arlania.world.content.skill.impl.slayer.SlayerDialogues;
import com.arlania.world.content.skill.impl.slayer.SlayerTasks;
import com.arlania.world.content.skill.impl.summoning.BossPets;
import com.arlania.world.content.skill.impl.summoning.Summoning;
import com.arlania.world.content.skill.impl.summoning.SummoningData;
import com.arlania.world.content.transportation.TeleportHandler;
import com.arlania.world.entity.impl.npc.NPC;
import com.arlania.world.entity.impl.player.Player;

public class NPCOptionPacketListener implements PacketListener {


	private static void firstClick(Player player, Packet packet) {
		int index = packet.readLEShort();
		if(index < 0 || index > World.getNpcs().capacity())
			return;
		final NPC npc = World.getNpcs().get(index);
		if (npc == null) {
			return;
		}
		player.setEntityInteraction(npc);
		if(player.getRights() == PlayerRights.DEVELOPER)
			player.getPacketSender().sendMessage("First click npc id: "+npc.getId());
		if(BossPets.pickup(player, npc)) {
			player.getMovementQueue().reset();
			return;
		}
		player.setWalkToTask(new WalkToTask(player, npc.getPosition(), npc.getSize(), new FinalizedMovementTask() {
			@Override
			public void execute() {
				if(SummoningData.beastOfBurden(npc.getId())) {
					Summoning summoning = player.getSummoning();
					if(summoning.getBeastOfBurden() != null && summoning.getFamiliar() != null && summoning.getFamiliar().getSummonNpc() != null && summoning.getFamiliar().getSummonNpc().getIndex() == npc.getIndex()) {
						summoning.store();
						player.getMovementQueue().reset();
					} else {
						player.getPacketSender().sendMessage("That familiar is not yours!");
					}
					return;
				}
				switch(npc.getId()) {
//				case 7891:
//					VoteChest.handleChest(player, npc);
//					break;
				case 7856:
					player.getCustomCombiner().open();
					break;
					
					case 212:
					ShopManager.getShops().get(48).open(player);
					break;
					

					case 872://U DONT EVEN KNOW HOW TO COPY
						PvmInterface.showInterface(player);//iam sure there is a pvminterface class ijust saw it
						PvmInterface.reset(player);
                     break;
                        
					case 8562:
						player.performAnimation(new Animation(645));
						player.performGraphic(new Graphic(1194));
						TeleportHandler.teleportPlayer(player, new Position(3052, 2724), player.getSpellbook().getTeleportType());
						break;
                        
					case 9872:
						player.performGraphic(new Graphic(69));
						if(player.getRights() == PlayerRights.EXECUTIVE_DONATOR || player.getRights() == PlayerRights.DEVELOPER || player.getRights() == PlayerRights.DIVINE_DONATOR);
							player.getPacketSender().sendMessage("You have teleported to Dead Pirate Zone.!");
							{
						TeleportHandler.teleportPlayer(player, new Position(2963, 2834), player.getSpellbook().getTeleportType());
						}
	                  	break;

					case 8002:
						ShopManager.getShops().get(52).open(player);
						break;
					case 4559:
						ShopManager.getShops().get(46).open(player);
						break;
					case 3192:
						PlayerPanel.refreshPanel(player);
						player.getPacketSender().sendInterface(3200);
						break;
						// no so where i poot id of npc to get points of that npc
					case 1552:
					DialogueManager.start(player, 138);
					player.setDialogueActionId(77);
					break;
					case 541:
						ShopManager.getShops().get(5).open(player);
						break;
					case 2633:
					ShopManager.getShops().get(50).open(player);
					break;
					case 602:
						ShopManager.getShops().get(72).open(player);
						break;
					case 741:
						player.sendMessage("@red@Temporarily disabled.");
						//ShopManager.getShops().get(49).open(player);
					break;
					case 2998:
						ShopManager.getShops().get(41).open(player);
						break;
					case 5441:
						ShopManager.getShops().get(51).open(player);
						break;
					case 461:
						ShopManager.getShops().get(1).open(player);
						break;
						

					case 278:
						ShopManager.getShops().get(6).open(player);
						break;
					case 4902:
						
						DialogueManager.start(player, 157);
						player.setDialogueActionId(-1);
						break;
					
					case 543:			
						DialogueManager.start(player, 159);
						player.setDialogueActionId(709);
						break;
						
					case 9400:			
						DialogueManager.start(player, 166);
						player.setDialogueActionId(1337);
						break;
					case 4247:
						ShopManager.getShops().get(56).open(player);

						break;
				
				case 8710:
				case 8707:
				case 8706:
				case 8705:
					EnergyHandler.rest(player);
					break;
			
				case 947:
					player.getPlayerOwnedShopManager().options();
					break;
					case 8736:
						DialogueManager.start(player, Tutorial.get(player, 0));
					break;

					case 11226:
					if(Dungeoneering.doingDungeoneering(player)) {
						ShopManager.getShops().get(45).open(player);
					}
					break;
				case 273: //Boss point shop npc id can be anything as long as it opens shop 92
					ShopManager.getShops().get(92).open(player);
					player.sendMessage("<img=0>You currently have @red@" + player.getBossPoints() + " Boss Points!");
					break;
				case 633:
					ShopManager.getShops().get(87).open(player);
					break;
				case 241: 
					ShopManager.getShops().get(77).open(player);
					break;
				case 9713:
					DialogueManager.start(player, 107);
					player.setDialogueActionId(69);
					break;
					
				case 683:
//					ShopManager.getShops().get(3).open(player);
					break;
				case 2622:
					ShopManager.getShops().get(43).open(player);
					break;
				case 3101:
					DialogueManager.start(player, 90);
					player.setDialogueActionId(57);
					break;
				case 7969:
					ShopManager.getShops().get(28).open(player);
					//DialogueManager.start(player, ExplorerJack.getDialogue(player));
					break;
				case 1597:
					
				case 8275:
				case 9085:
				case 7780:
					if(npc.getId() != player.getSlayer().getSlayerMaster().getNpcId()) {
						player.getPacketSender().sendMessage("This is not your current Slayer master.");
						return;
					}
					DialogueManager.start(player, SlayerDialogues.dialogue(player));
					break;
				case 437:
					DialogueManager.start(player, 99);
					player.setDialogueActionId(58);
					break;
				case 5112:
					ShopManager.getShops().get(38).open(player);
					break;
				case 8591:
					//player.nomadQuest[0] = player.nomadQuest[1] = player.nomadQuest[2] = false;
					if(!player.getMinigameAttributes().getNomadAttributes().hasFinishedPart(0)) {
						DialogueManager.start(player, 48);
						player.setDialogueActionId(23);
					} else if(player.getMinigameAttributes().getNomadAttributes().hasFinishedPart(0) && !player.getMinigameAttributes().getNomadAttributes().hasFinishedPart(1)) {
						DialogueManager.start(player, 50);
						player.setDialogueActionId(24);
					} else if(player.getMinigameAttributes().getNomadAttributes().hasFinishedPart(1))
						DialogueManager.start(player, 53);
					break;
				case 3385:
					if(player.getMinigameAttributes().getRecipeForDisasterAttributes().hasFinishedPart(0) && player.getMinigameAttributes().getRecipeForDisasterAttributes().getWavesCompleted() < 6) {
						DialogueManager.start(player, 39);
						return;
					}
					if(player.getMinigameAttributes().getRecipeForDisasterAttributes().getWavesCompleted() == 6) {
						DialogueManager.start(player, 46);
						return;
					}
					DialogueManager.start(player, 38);
					player.setDialogueActionId(20);
					break;
				case 6139:
					DialogueManager.start(player, 29);
					player.setDialogueActionId(17);
					break;
				case 3789:
					ShopManager.getShops().get(99).open(player);
					player.getPacketSender().sendString(18729, "Commendations: "+Integer.toString(player.getPointsHandler().getCommendations()));
					break;
				case 2948:
					DialogueManager.start(player, WarriorsGuild.warriorsGuildDialogue(player));
					break;
				case 650:
					ShopManager.getShops().get(35).open(player);
					break;
				case 6055:
				case 6056:
				case 6057:
				case 6058:
				case 6059:
				case 6060:
				case 6061:
				case 6062:
				case 6063:
				case 6064:
				case 7903:
					
					PuroPuro.catchImpling(player, npc);
					break;
				case 8022:
				case 8028:
					DesoSpan.siphon(player, npc);
					break;
				case 2579:
//					player.setDialogueActionId(13);
//					DialogueManager.start(player, 24);
					ShopManager.getShops().get(46).open(player);
					player.getPacketSender().sendMessage("<col=255>You currently have "+player.getPointsHandler().getPrestigePoints()+" Prestige points!");
					break;
				case 8725:
					player.setDialogueActionId(10);
					DialogueManager.start(player, 19);
					break;
				case 4249:
					player.setDialogueActionId(9);
					DialogueManager.start(player, 64);
					break;
				case 6807:
				case 6994:
				case 6995:
				case 6867:
				case 6868:
				case 6794:
				case 6795:
				case 6815:
				case 6816:
				case 6874:
				case 6873:
				case 3594:
				case 3590:
				case 3596:
					if(player.getSummoning().getFamiliar() == null || player.getSummoning().getFamiliar().getSummonNpc() == null || player.getSummoning().getFamiliar().getSummonNpc().getIndex() != npc.getIndex()) {
						player.getPacketSender().sendMessage("That is not your familiar.");
						return;
					}
					player.getSummoning().store();
					break;
				case 364:
					player.setDialogueActionId(8);
					DialogueManager.start(player, 13);
					break;
				case 6970:
					player.setDialogueActionId(3);
					DialogueManager.start(player, 3);
					break;
				case 4657:
					player.setDialogueActionId(5);
					DialogueManager.start(player, 5);
					break;
				case 318:
				case 316:
				case 313:
				case 312:
				case 233:
					player.setEntityInteraction(npc);
					Fishing.setupFishing(player, Fishing.forSpot(npc.getId(), false));
					break;
				case 805:
					ShopManager.getShops().get(34).open(player);
					break;
				case 462:
					ShopManager.getShops().get(33).open(player);
					break;
				case 1263:
					ShopManager.getShops().get(32).open(player);
					break;
				case 8444:
					
					ShopManager.getShops().get(31).open(player);
					break;
				case 8459:
					ShopManager.getShops().get(30).open(player);
					break;
				case 3299:
					ShopManager.getShops().get(21).open(player);
					break;
				case 548:
					ShopManager.getShops().get(20).open(player);
					break;
				case 1685:
					ShopManager.getShops().get(19).open(player);
					break;
				case 308:
					ShopManager.getShops().get(18).open(player);
					break;
				case 802:
					ShopManager.getShops().get(17).open(player);
					break;
				case 794:
					ShopManager.getShops().get(16).open(player);
					break;
				case 4946:
					ShopManager.getShops().get(15).open(player);
					break;
				case 948:
					ShopManager.getShops().get(13).open(player);
					break;
				case 4906:
					ShopManager.getShops().get(14).open(player);
					break;
				case 520:
				case 521:
					ShopManager.getShops().get(12).open(player);
					break;
				case 2292:
					ShopManager.getShops().get(11).open(player);
					break;
				case 2676:
					player.getPacketSender().sendInterface(3559);
					player.getAppearance().setCanChangeAppearance(true);
					break;
				case 494:
				}
				if(!(npc.getId() >= 8705 && npc.getId() <= 8710)) {
					npc.setPositionToFace(player.getPosition());
				}
				player.setPositionToFace(npc.getPosition());
			}
		}));
	}

	private static void attackNPC(Player player, Packet packet) {
		int index = packet.readShortA();
		player.sendElementalMessage = true;
		if(index < 0 || index > World.getNpcs().capacity())
			return;
		final NPC interact = World.getNpcs().get(index);
		if (interact == null) {
			System.out.println("[NPC-OPTION-PACKET]: did not register click because of nulled npc. index["+index+"]");
			return;
		}

		if(player.getRights() == PlayerRights.DEVELOPER)
			player.getPacketSender().sendMessage("attack npc id: "+interact.getId());

		
		if (player.getSlayer().getSlayerTask().getNpcId() == interact.getId()) {
		} else {
		if(ZonesEnum.getZonesEnumByNpcId(interact.getId()) != null) {
			ZonesEnum zonesEnum = ZonesEnum.getZonesEnumByNpcId(interact.getId());

			//dev dont need requirements
			if(player.getNpcKillCount(zonesEnum.getKill_count_npcId()) < zonesEnum.getKill_count() && player.getRights() != PlayerRights.DEVELOPER) {
				player.sendMessage("<shad=1>@red@You need "+zonesEnum.getKill_count()+" kills of @blu@"+NpcDefinition.forId(zonesEnum.getKill_count_npcId()).getName()+"@red@ to attack "+NpcDefinition.forId(zonesEnum.getNpcId()).getName()+"!"); //the requirements needed and npc amounts of that requirement *abbadon in this case and 100 npc kills of abbadon to atk baphomet+
				return;
			}
		}
		
		}
	





		if(player.getCombatBuilder().getStrategy() == null) {
			player.getCombatBuilder().determineStrategy();
		}
		if (CombatFactory.checkAttackDistance(player, interact)) {
			player.getMovementQueue().reset();
		}

		player.getCombatBuilder().attack(interact);
	}

	public void handleSecondClick(Player player, Packet packet) {
		int index = packet.readLEShortA();
		if(index < 0 || index > World.getNpcs().capacity())
			return;
		final NPC npc = World.getNpcs().get(index);
		if(npc == null)
			return;
		player.setEntityInteraction(npc);
		final int npcId = npc.getId();
		if(player.getRights() == PlayerRights.DEVELOPER)
			player.getPacketSender().sendMessage("Second click npc id: "+npcId);
		player.setWalkToTask(new WalkToTask(player, npc.getPosition(), npc.getSize(), new FinalizedMovementTask() {
			@Override
			public void execute() {
				switch(npc.getId()) {
				case 9872:
					ShopManager.getShops().get(87).open(player);
//					player.sendMessage("<img=0>You currently have @red@" + player.getDeadPoints() + " Dead Points!");
					break;
					case 8002:
						player.getPacketSender().sendMessage("Coming Soon!");
						// ShopManager.getShops().get(53).open(player);
						break;
				case 6755:
					player.performGraphic(new Graphic(7));
					TeleportHandler.teleportPlayer(player, new Position(3052, 2724), player.getSpellbook().getTeleportType());
					break;
				case 1266:
					case 1360:
					case 494:
					player.getBank(player.getCurrentBankTab()).open();
					break;
				case 2998:
					//DialogueManager.sstart(player, 155);
					player.getPacketSender().sendEnterAmountPrompt("How many coins would you like to gamble?");
					player.setInputHandling(new GambleAmount());
					break;
				case 3192:
					PlayerPanel.refreshPanel(player);
					player.getPacketSender().sendInterface(3200);
					break;
				case 17:
					npc.setForcedChat("I Will Teleport You to Upgrade Machine!");
					TeleportHandler.teleportPlayer(player, new Position(2763, 2952), player.getSpellbook().getTeleportType());
					break;
				case 4559:
					ShopManager.getShops().get(46).open(player);
					break;
				case 961:
					ShopManager.getShops().get(6).open(player);
					break;
				case 2579:
					ShopManager.getShops().get(46).open(player);
					player.getPacketSender().sendMessage("<col=255>You currently have "+player.getPointsHandler().getPrestigePoints()+" Prestige points!");
					break;
				case 212:
					player.sendMessage("@red@Temporarily disabled.");
					//ShopManager.getShops().get(49).open(player);
					break;
				case 947:
					player.getPlayerOwnedShopManager().open();
					break;
				case 2622:
					ShopManager.getShops().get(43).open(player);
					break;
				case 4902:
					ShopManager.getShops().get(55).open(player);
					break;
				case 462:
					npc.performAnimation(CombatSpells.CONFUSE.getSpell().castAnimation().get());
					npc.forceChat("Off you go!");
					TeleportHandler.teleportPlayer(player,new Position(2911, 4832), player.getSpellbook().getTeleportType());
					break;
				case 3101:
					DialogueManager.start(player, 95);
					player.setDialogueActionId(57);
					break;
				case 7969:
					ShopManager.getShops().get(28).open(player);
					break;
				case 364:
					player.getPacketSender().sendMessage("").sendMessage("You currently have "+player.getPointsHandler().getVotingPoints()+" Voting points.").sendMessage("You can earn points and coins by voting. To do so, simply use the ::vote command.");;
					ShopManager.getShops().get(27).open(player);
					break;
				case 1597:
				case 9085:
				case 7780:
					if(npc.getId() != player.getSlayer().getSlayerMaster().getNpcId()) {
						player.getPacketSender().sendMessage("This is not your current Slayer master.");
						return;
					}
					if(player.getSlayer().getSlayerTask() == SlayerTasks.NO_TASK)
						player.getSlayer().assignTask();
					else
						DialogueManager.start(player, SlayerDialogues.findAssignment(player));
					break;
				case 8591:
					if(!player.getMinigameAttributes().getNomadAttributes().hasFinishedPart(1)) {
						player.getPacketSender().sendMessage("You must complete Nomad's quest before being able to use this shop.");
						return;
					}
					ShopManager.getShops().get(37).open(player);
					break;
				case 805:
					Tanning.selectionInterface(player);
					break;
				case 318:
				case 316:
				case 313:
				case 312:
					player.setEntityInteraction(npc);
					Fishing.setupFishing(player, Fishing.forSpot(npc.getId(), true));
					break;
				case 4946:
					ShopManager.getShops().get(15).open(player);
					break;
				case 683:
//					ShopManager.getShops().get(3).open(player);
					break;
				case 705:
					ShopManager.getShops().get(4).open(player);
					break;
				case 2253:
					ShopManager.getShops().get(9).open(player);
					break;
				case 6970:
					player.setDialogueActionId(35);
					DialogueManager.start(player, 63);
					break;
				}
				npc.setPositionToFace(player.getPosition());
				player.setPositionToFace(npc.getPosition());
			}
		}));
	}

	public void handleThirdClick(Player player, Packet packet) {
		int index = packet.readShort();
		if(index < 0 || index > World.getNpcs().capacity())
			return;
		final NPC npc = World.getNpcs().get(index);
		if (npc == null)
			return;
		player.setEntityInteraction(npc).setPositionToFace(npc.getPosition().copy());
		npc.setPositionToFace(player.getPosition());
		if(player.getRights() == PlayerRights.DEVELOPER)
			player.getPacketSender().sendMessage("Third click npc id: "+npc.getId());
		player.setWalkToTask(new WalkToTask(player, npc.getPosition(), npc.getSize(), new FinalizedMovementTask() {
			@Override
			public void execute() {
				switch(npc.getId()) {
				case 3101:
					ShopManager.getShops().get(42).open(player);
					break;
					
				case 1266:
					TeleportHandler.teleportPlayer(player, new Position(2407, 3995), player.getSpellbook().getTeleportType());
					break;
					
				case 1597:
				case 8275:

				case 9085:
				case 7780:
					ShopManager.getShops().get(40).open(player);
					break;
				case 364:
					LoyaltyProgramme.open(player);
					break;
				case 4657:
					if(player.getRights() == PlayerRights.PLAYER) {
						player.getPacketSender().sendMessage("You need to be a member to teleport to this zone.").sendMessage("To become a member, visit www.ImaginePS.org and purchase a scroll.");
						return;
					}
					TeleportHandler.teleportPlayer(player, new Position(3363, 9638), player.getSpellbook().getTeleportType());
					break;
				case 947:
					player.getPlayerOwnedShopManager().openEditor();
					break;
				case 946:
					ShopManager.getShops().get(0).open(player);
					break;
				case 1861:
					ShopManager.getShops().get(2).open(player);
					break;
				case 961:
					if(player.getRights() == PlayerRights.PLAYER) {
						player.getPacketSender().sendMessage("This feature is currently only available for members.");
						return;
					}
					boolean restore = player.getSpecialPercentage() < 100;
					if(restore) {
						player.setSpecialPercentage(100);
						CombatSpecial.updateBar(player);
						player.getPacketSender().sendMessage("Your special attack energy has been restored.");
					}
					for(Skill skill : Skill.values()) {
						if(player.getSkillManager().getCurrentLevel(skill) < player.getSkillManager().getMaxLevel(skill)) {
							player.getSkillManager().setCurrentLevel(skill, player.getSkillManager().getMaxLevel(skill));
							restore = true;
						}
					}
					if(restore) {
						player.performGraphic(new Graphic(1302));
						player.getPacketSender().sendMessage("Your stats have been restored.");
					} else
						player.getPacketSender().sendMessage("Your stats do not need to be restored at the moment.");
					break;
				case 705:
					ShopManager.getShops().get(5).open(player);
					break;
				case 2253:
					ShopManager.getShops().get(10).open(player);
					break;
				}
				npc.setPositionToFace(player.getPosition());
				player.setPositionToFace(npc.getPosition());
			}
		}));
	}

	public void handleFourthClick(Player player, Packet packet) {
		int index = packet.readLEShort();
		if(index < 0 || index > World.getNpcs().capacity())
			return;
		final NPC npc = World.getNpcs().get(index);
		if (npc == null)
			return;
		player.setEntityInteraction(npc);
		if(player.getRights() == PlayerRights.DEVELOPER)
			player.getPacketSender().sendMessage("Fourth click npc id: "+npc.getId());
		player.setWalkToTask(new WalkToTask(player, npc.getPosition(), npc.getSize(), new FinalizedMovementTask() {
			@Override
			public void execute() {
				switch(npc.getId()) {
				case 4657:
					if(player.getRights() == PlayerRights.PLAYER) {
						player.getPacketSender().sendMessage("You need to be a member to teleport to this zone.").sendMessage("To become a member, visit www.ImaginePS.org and purchase a scroll.");
						return;
					}
					TeleportHandler.teleportPlayer(player, new Position(3363, 9638), player.getSpellbook().getTeleportType());
					break;
				case 6755:
					if(player.getRights() == PlayerRights.EXECUTIVE_DONATOR || player.getRights() == PlayerRights.DIVINE_DONATOR) {
					TeleportHandler.teleportPlayer(player, new Position(3363, 9638), player.getSpellbook().getTeleportType());
					}
					break;
				case 947:
					player.getPlayerOwnedShopManager().claimEarnings();
					break;
				case 705:
					ShopManager.getShops().get(7).open(player);
					break;
				case 2253:
					ShopManager.getShops().get(8).open(player);
					break;
				case 1597:
				case 9085:
				case 8275:
				case 7780:
					player.getPacketSender().sendString(36030, "Current Points:   "+player.getPointsHandler().getSlayerPoints());
					player.getPacketSender().sendInterface(36000);
					break;
				}
				npc.setPositionToFace(player.getPosition());
				player.setPositionToFace(npc.getPosition());
			}
		}));
	}

	@Override
	public void handleMessage(Player player, Packet packet) {
		if(player.isTeleporting() || player.isPlayerLocked() || player.getMovementQueue().isLockMovement())
			return;
		switch (packet.getOpcode()) {
		case ATTACK_NPC:
			attackNPC(player, packet);
			break;
		case FIRST_CLICK_OPCODE:
			firstClick(player, packet);
			break;
		case SECOND_CLICK_OPCODE:
			handleSecondClick(player, packet);
			break;
		case THIRD_CLICK_OPCODE:
			handleThirdClick(player, packet);
			break;
		case FOURTH_CLICK_OPCODE:
			handleFourthClick(player, packet);
			break;
		case MAGE_NPC:
			int npcIndex = packet.readLEShortA();
			int spellId = packet.readShortA();

			if (npcIndex < 0 || spellId < 0 || npcIndex > World.getNpcs().capacity()) {
				return;
			}

			NPC n = World.getNpcs().get(npcIndex);
			player.setEntityInteraction(n);

			CombatSpell spell = CombatSpells.getSpell(spellId);

			if (n == null || spell == null) {
				player.getMovementQueue().reset();
				return;
			}

			if (!NpcDefinition.getDefinitions()[n.getId()].isAttackable()) {
				player.getMovementQueue().reset();
				return;
			}

			if(n.getConstitution() <= 0) {
				player.getMovementQueue().reset();
				return;
			}

			player.setPositionToFace(n.getPosition());
			player.setCastSpell(spell);
			if(player.getCombatBuilder().getStrategy() == null) {
				player.getCombatBuilder().determineStrategy();
			}
			if (CombatFactory.checkAttackDistance(player, n)) {
				player.getMovementQueue().reset();
			}
			player.getCombatBuilder().resetCooldown();
			player.getCombatBuilder().attack(n);
			break;
		}
	}

	public static final int ATTACK_NPC = 72, FIRST_CLICK_OPCODE = 155, MAGE_NPC = 131, SECOND_CLICK_OPCODE = 17, THIRD_CLICK_OPCODE = 21, FOURTH_CLICK_OPCODE = 18;
}
