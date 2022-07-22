package com.arlania.net.packet.impl;

import com.arlania.engine.task.Task;
import com.arlania.engine.task.TaskManager;
import com.arlania.model.*;
import com.arlania.model.Locations.Location;
import com.arlania.model.actions.items.scrolls.MemberScrolls;
import com.arlania.model.definitions.ItemDefinition;
import com.arlania.net.packet.Packet;
import com.arlania.net.packet.PacketListener;
import com.arlania.util.Misc;
import com.arlania.util.RandomUtility;
import com.arlania.world.World;
import com.arlania.world.content.ChristmasPresent;
import com.arlania.world.content.ClueScrolls;
import com.arlania.world.content.Consumables;
import com.arlania.world.content.Digging;
import com.arlania.world.content.Effigies;
import com.arlania.world.content.ExperienceLamps;
import com.arlania.world.content.Gambling;
import com.arlania.world.content.MoneyPouch;
import com.arlania.world.content.PetBox;
import com.arlania.world.content.PlayerPanel;
import com.arlania.world.content.Sounds;
import com.arlania.world.content.Sounds.Sound;
import com.arlania.world.content.TrioBosses;
import com.arlania.world.content.achievements.AchievementData;
import com.arlania.world.content.books.TestBook;
import com.arlania.world.content.combat.range.DwarfMultiCannon;
import com.arlania.world.content.combat.weapon.CombatSpecial;
import com.arlania.world.content.dialogue.DialogueManager;
//import com.arlania.world.content.dissolvesystem.DissolveHandler;
import com.arlania.world.content.itemopening.CharmBox;
import com.arlania.world.content.itemopening.DonationBox;
import com.arlania.world.content.itemopening.RUBoxes;
import com.arlania.world.content.mysteryboxes.AbbadonBox;
import com.arlania.world.content.mysteryboxes.AssasinBox;
import com.arlania.world.content.mysteryboxes.BandosBox;
import com.arlania.world.content.mysteryboxes.DonatorBox;
import com.arlania.world.content.mysteryboxes.DonkeyBox;
import com.arlania.world.content.mysteryboxes.GodzillaBox;
import com.arlania.world.content.mysteryboxes.InfernalGroudonBox;
import com.arlania.world.content.mysteryboxes.MysteryBox;
import com.arlania.world.content.mysteryboxes.NeocortexBox;
import com.arlania.world.content.mysteryboxes.OblivionBox;
import com.arlania.world.content.mysteryboxes.YodaBox;
import com.arlania.world.content.mysteryboxes.spiderBox;
import com.arlania.world.content.scratchcards.ScratchCard;
import com.arlania.world.content.skill.impl.construction.Construction;
import com.arlania.world.content.skill.impl.dungeoneering.ItemBinding;
import com.arlania.world.content.skill.impl.herblore.Herblore;
import com.arlania.world.content.skill.impl.herblore.IngridientsBook;
import com.arlania.world.content.skill.impl.hunter.BoxTrap;
import com.arlania.world.content.skill.impl.hunter.Hunter;
import com.arlania.world.content.skill.impl.hunter.JarData;
import com.arlania.world.content.skill.impl.hunter.PuroPuro;
import com.arlania.world.content.skill.impl.hunter.SnareTrap;
import com.arlania.world.content.skill.impl.hunter.Trap.TrapState;
import com.arlania.world.content.skill.impl.prayer.Prayer;
import com.arlania.world.content.skill.impl.runecrafting.Runecrafting;
import com.arlania.world.content.skill.impl.runecrafting.RunecraftingPouches;
import com.arlania.world.content.skill.impl.runecrafting.RunecraftingPouches.RunecraftingPouch;
import com.arlania.world.content.skill.impl.slayer.SlayerDialogues;
import com.arlania.world.content.skill.impl.slayer.SlayerTasks;
import com.arlania.world.content.skill.impl.summoning.CharmingImp;
import com.arlania.world.content.skill.impl.summoning.SummoningData;
import com.arlania.world.content.skill.impl.woodcutting.BirdNests;
import com.arlania.world.content.transportation.JewelryTeleporting;
import com.arlania.world.content.transportation.TeleportHandler;
import com.arlania.world.content.transportation.TeleportType;
import com.arlania.world.content.treasuretrails.ClueScroll;
import com.arlania.world.content.treasuretrails.CoordinateScrolls;
import com.arlania.world.content.treasuretrails.DiggingScrolls;
import com.arlania.world.content.treasuretrails.MapScrolls;
import com.arlania.world.content.treasuretrails.Puzzle;
import com.arlania.world.content.treasuretrails.SearchScrolls;
import com.arlania.world.entity.impl.npc.NPC;
import com.arlania.world.entity.impl.player.Player;
import com.google.common.collect.Multiset.Entry;



public class ItemActionPacketListener implements PacketListener {
	
	
	
	public static void cancelCurrentActions(Player player) {
		player.getPacketSender().sendInterfaceRemoval();
		player.setTeleporting(false);
		player.setWalkToTask(null);
		player.setInputHandling(null);
		player.getSkillManager().stopSkilling();
		player.setEntityInteraction(null);
		player.getMovementQueue().setFollowCharacter(null);
		player.getCombatBuilder().cooldown(false);
		player.setResting(false);
	}
	public static boolean checkReqs(Player player, Location targetLocation) {
		if(player.getConstitution() <= 0)
			return false;
		if(player.getTeleblockTimer() > 0) {
			player.getPacketSender().sendMessage("A magical spell is blocking you from teleporting.");
			return false;
		}
		if(player.getLocation() != null && !player.getLocation().canTeleport(player))
			return false;
		if(player.isPlayerLocked() || player.isCrossingObstacle()) {
			player.getPacketSender().sendMessage("You cannot teleport right now.");
			return false;
		}
		return true;
	}

	private static void firstAction(final Player player,  Packet packet) {
		if (player.requiresPin())
			return;
		int interfaceId = packet.readUnsignedShort();
		int slot = packet.readShort();
		int itemId = packet.readShort();
		
		Location targetLocation = player.getLocation();
		
		MemberScrolls.claimScroll(player, itemId);

		if(interfaceId == 38274) {
			Construction.handleItemClick(itemId, player);
			return;
		}


        if(interfaceId == 47989) {
            player.getPacketSender().sendItemOnInterface(47900, itemId, 1);
            return;
        }

		if(itemId == 7038) {
			int amount = player.getInventory().getAmount(7038);

			//for(int i = 0; i < player.getInventory().getAmount(5023); i++) {
				//amount++;
			//}
			if(amount >= 20000000) {
				System.err.println("ok");
				player.getPA().sendMessage("fuck off fella");
				return;
			}
			player.getInventory().delete(7038, amount);
			player.getInventory().add(5023, 1000 * amount);
		}

		if(itemId == 5023) {
			int amount = player.getInventory().getAmount(5023);

			//for(int i = 0; i < player.getInventory().getAmount(5023); i++) {
				//amount++;
			//}
			if(amount >= 20000000) {
				System.err.println("ok");
				player.getPA().sendMessage("fuck off fella");
				return;
			}
			player.getInventory().delete(5023, amount);
			player.getInventory().add(5022, 1000000 * amount);
		}
		
		if(slot < 0 || slot > player.getInventory().capacity())
			return;
		if(player.getInventory().getItems()[slot].getId() != itemId)
			return;
		player.setInteractingItem(player.getInventory().getItems()[slot]);
		if (Prayer.isBone(itemId)) {
			Prayer.buryBone(player, itemId);
			return;
		}
		if (Consumables.isFood(player, itemId, slot))
			return;
		if(Consumables.isPotion(itemId)) {
			Consumables.handlePotion(player, itemId, slot);
			return;
		}
		if(BirdNests.isNest(itemId)) {
			BirdNests.searchNest(player, itemId);
			return;
		}
		if (Herblore.cleanHerb(player, itemId))
			return;
		if(ClueScroll.handleCasket(player, itemId) || SearchScrolls.loadClueInterface(player, itemId) || MapScrolls.loadClueInterface(player, itemId) || Puzzle.loadClueInterface(player, itemId) || CoordinateScrolls.loadClueInterface(player, itemId) || DiggingScrolls.loadClueInterface(player, itemId))
			return;
		if(Effigies.isEffigy(itemId)) {
			Effigies.handleEffigy(player, itemId);
			return;
		}
		if(ExperienceLamps.handleLamp(player, itemId)) {
			return;
		}
		if(itemId == 5022) {
			player.getInventory().delete(5022,1);
			player.getInventory().add(995,1000000000);
		}
		
		switch(itemId) {
		case 3576:
			player.getGoodieBagSponsor().open();
			break;
			case 1856:
				player.getPacketSender().sendInterface(48550);
				break;
		case 3569:
			player.getGoodieBagbfg9000().open();
			break;
			case 3570:
				player.getGoodiebagEternal().open();
				break;
		case 8989:
			player.getGoodieBagtrivia().open();
			break;
			
		case 455:
			new ScratchCard(player).display();
			break;
			case 21007:
				if(player.getInventory().hasItem(new Item(21007,100))){
//					World.sendMessage("<shad=0>@bla@[@whi@Mythical@bla@] <shad=0>@whi@"+ player.getUsername()
//							+ "@bla@ has just forged a mythical blade");
				} else {
					player.sendMessage("You need 100 shards to forge a mythical blade");
				}
				break;
		case 13663:
			if(player.getInterfaceId() > 0) {
				player.getPacketSender().sendMessage("Please close the interface you have open before doing this.");
				return;
			}
			player.setUsableObject(new Object[2]).setUsableObject(0, "reset");
			player.getPacketSender().sendString(38006, "Choose stat to reset!").sendMessage("@red@Please select a skill you wish to reset and then click on the 'Confim' button.").sendString(38090, "Which skill would you like to reset?");
			player.getPacketSender().sendInterface(38000);
			break;
		case 19670:
			if(player.busy()) {
				player.getPacketSender().sendMessage("You can not do this right now.");
				return;
			}
			player.setDialogueActionId(70);
			DialogueManager.start(player, player.getGameMode() == GameMode.NORMAL ? 108 : 109);
			break;
		case 8007: //Varrock
			if (player.inFFALobby){
				player.sendMessage("Use the portal to leave the ffa lobby!");
				return;
			}
			if (player.inFFA) {
				player.sendMessage("You can not teleport out of FFA!");
				return;
			}
			if(!player.getClickDelay().elapsed(1200) || player.getMovementQueue().isLockMovement()) {
				return;
			}
			if(player.getLocation() == Location.CONSTRUCTION) {
				player.getPacketSender().sendMessage("Please use the portal to exit your house");
				return;
			}
			
			
			if(!checkReqs(player, targetLocation)) {
				return;
			}
			player.setTeleporting(true).getMovementQueue().setLockMovement(true).reset();
			cancelCurrentActions(player);
			player.performAnimation(new Animation(4731));
			player.performGraphic(new Graphic(678));
			player.getInventory().delete(8007, 1);
			player.getClickDelay().reset();
		
			TaskManager.submit(new Task(2, player, false) {
				@Override
				public void execute() {		
					Position position = new Position(3210, 3424, 0);
					player.moveTo(position);
					player.getMovementQueue().setLockMovement(false).reset();
					this.stop();
				}
			});
			 break;

			case 20450:
				if(player.getTransform() == 6766) {
					player.setTimer(player.getTimer()+(100*30));
					player.getInventory().delete(20450, 1);

				} else {
					if (player.getTransform() < 1) {
						player.setNpcTransformationId(6766);
						player.setTransform(6766);
						player.setTimer(100 * 30);

						player.getInventory().delete(20450, 1);

						player.getUpdateFlag().flag(Flag.APPEARANCE);
						NPC npc = new NPC(6766, new Position(0, 0));
						player.setConstitution(npc.getConstitution());
						player.sendMessage("<col=ff0000>You transformed into Doomsday.");
					} else {
						player.sendMessage("Please wait till your timer is over!");
					}
				}
				break;

			case 20451:
				if(player.getTransform() == 2240) {
					player.setTimer(player.getTimer()+(100*30));
					player.getInventory().delete(20451, 1);

				} else {
					if(player.getTransform() < 1) {
						player.setNpcTransformationId(2240);
						player.setTransform(2240);
						player.setTimer(100 * 30);
						player.getInventory().delete(20451, 1);

						player.getUpdateFlag().flag(Flag.APPEARANCE);

						NPC npc = new NPC(2240, new Position(0, 0));
						player.setConstitution(npc.getConstitution());
						player.sendMessage("<col=ff0000>You transformed into Sasuke(Cursed Mark).");
					} else {
						player.sendMessage("Please wait till your timer is over!");
					}
				}
				break;
					case 20452:
						if(player.getTransform() == 2241) {
							player.setTimer(player.getTimer()+(100*30));
							player.getInventory().delete(20452, 1);

						} else {
							if(player.getTransform() < 1) {
				player.setNpcTransformationId(2241);
				player.setTransform(2241);
				player.setTimer(100*30);

								player.getInventory().delete(20452, 1);

								player.getUpdateFlag().flag(Flag.APPEARANCE);

                 NPC npc = new NPC(2241,new Position(0,0));
                player.setConstitution(npc.getConstitution());
				player.sendMessage("<col=ff0000>You transformed into Broly.");
		} else {
			player.sendMessage("Please wait till your timer is over!");
		}
	}
				break;
				case 20453:
					if(player.getTransform() == 7952) {
						player.setTimer(player.getTimer()+(100*30));
						player.getInventory().delete(20453, 1);

					} else {
						if(player.getTransform() < 1) {
				player.setNpcTransformationId(7952);
				player.setTransform(7952);
							player.getInventory().delete(20453, 1);

							player.setTimer(100*30);
							player.getUpdateFlag().flag(Flag.APPEARANCE);


                 NPC npc = new NPC(7952,new Position(0,0));
                player.setConstitution(npc.getConstitution());
				player.sendMessage("<col=ff0000>You transformed into Mew.");
		} else {
			player.sendMessage("Please wait till your timer is over!");
		}
	}
				break;

			case 5020:
				int random =  Misc.getRandom((10000));
				NPC bandosava = new NPC(4540, new Position(2865, 9954, random*100+8));
				bandosava.getDefinition().setRespawnTime(5);

				player.sendMessage("Bandos Ava instance");
				World.register(bandosava);
				TeleportHandler.teleportPlayer(player, new Position(2867,9946,random*100+8), player.getSpellbook().getTeleportType());
				player.setInstance(true);
				break;
				case 5018:
				 random =  Misc.getRandom((10000));
				 bandosava = new NPC(6303, new Position(2528, 5175, random*100+8));
				 bandosava.getDefinition().setRespawnTime(5);
					World.register(bandosava);
					bandosava = new NPC(6303, new Position(2504, 5174, random*100+8));
					bandosava.getDefinition().setRespawnTime(5);
					World.register(bandosava);
					player.sendMessage("Abbadon instance");
				World.register(bandosava);
				TeleportHandler.teleportPlayer(player, new Position(2516,5173,random*100+8), player.getSpellbook().getTeleportType());
				player.setInstance(true);
				break;
				case 17546:
					player.setHasVengeance(true);
					player.performAnimation(new Animation(509));
					player.performGraphic(new Graphic(1322));
					World.sendMessage("<shad=1>@red@[GODMODE] @yel@"+ player.getUsername() + "@bla@ just drank a God potion! ");
					

					player.getPacketSender().sendMessage("You're a god, and everyone knows it.");
					player.getInventory().delete(17546,1);
					player.getGodMode().init(); // kk it shud be perfect now
					if(player.getGodMode().isActive()) {
						return;
					}
					player.setSpecialPercentage(15000);
					CombatSpecial.updateBar(player);
					break;
				case 5016:
					random =  Misc.getRandom((10000));
					bandosava = new NPC(1234, new Position(1236, 1241, random*100+8));
					bandosava.getDefinition().setRespawnTime(5);
					World.register(bandosava);
					bandosava = new NPC(1234, new Position(1246, 1241, random*100+8));
					bandosava.getDefinition().setRespawnTime(5);
					World.register(bandosava);
					player.sendMessage("Infernal Groudon instance");
					World.register(bandosava);
					TeleportHandler.teleportPlayer(player, new Position(1240,1227,random*100+8), player.getSpellbook().getTeleportType());
					player.setInstance(true);
				break;
		case 8008: //Lumbridge
			if (player.inFFALobby){
				player.sendMessage("Use the portal to leave the ffa lobby!");
				return;
			}
			if (player.inFFA) {
				player.sendMessage("You can not teleport out of FFA!");
				return;
			}
			if(!player.getClickDelay().elapsed(1200) || player.getMovementQueue().isLockMovement()) {
				return;
			}
			if(player.getLocation() == Location.CONSTRUCTION) {
				player.getPacketSender().sendMessage("Please use the portal to exit your house");
				return;
			}
		
			
			if(!checkReqs(player, targetLocation)) {
				return;
			}
			player.setTeleporting(true).getMovementQueue().setLockMovement(true).reset();
			cancelCurrentActions(player);
			player.performAnimation(new Animation(4731));
			player.performGraphic(new Graphic(678));
			player.getInventory().delete(8008, 1);
			player.getClickDelay().reset();
		
			TaskManager.submit(new Task(2, player, false) {
				@Override
				public void execute() {		
					Position position = new Position(3222, 3218, 0);
					player.moveTo(position);
					player.getMovementQueue().setLockMovement(false).reset();
					this.stop();
				}
			});
		
	
	
			
			 break;
		case 8009: //Falador
			if (player.inFFALobby){
				player.sendMessage("Use the portal to leave the ffa lobby!");
				return;
			}
			if (player.inFFA) {
				player.sendMessage("You can not teleport out of FFA!");
				return;
			}
			if(!player.getClickDelay().elapsed(1200) || player.getMovementQueue().isLockMovement()) {
				return;
			}
			if(player.getLocation() == Location.CONSTRUCTION) {
				player.getPacketSender().sendMessage("Please use the portal to exit your house");
				return;
			}
		
			
			if(!checkReqs(player, targetLocation)) {
				return;
			}
			player.setTeleporting(true).getMovementQueue().setLockMovement(true).reset();
			cancelCurrentActions(player);
			player.performAnimation(new Animation(4731));
			player.performGraphic(new Graphic(678));
			player.getInventory().delete(8009, 1);
			player.getClickDelay().reset();
		
			TaskManager.submit(new Task(2, player, false) {
				@Override
				public void execute() {		
					Position position = new Position(2964, 3378, 0);
					player.moveTo(position);
					player.getMovementQueue().setLockMovement(false).reset();
					this.stop();
				}
			});
		
	
	
			
			 break;
		case 8010: //Camelot
			if (player.inFFALobby){
				player.sendMessage("Use the portal to leave the ffa lobby!");
				return;
			}
			if (player.inFFA) {
				player.sendMessage("You can not teleport out of FFA!");
				return;
			}
			if(!player.getClickDelay().elapsed(1200) || player.getMovementQueue().isLockMovement()) {
				return;
			}
			if(player.getLocation() == Location.CONSTRUCTION) {
				player.getPacketSender().sendMessage("Please use the portal to exit your house");
				return;
			}
			
			
			if(!checkReqs(player, targetLocation)) {
				return;
			}
			player.setTeleporting(true).getMovementQueue().setLockMovement(true).reset();
			cancelCurrentActions(player);
			player.performAnimation(new Animation(4731));
			player.performGraphic(new Graphic(678));
			player.getInventory().delete(8010, 1);
			player.getClickDelay().reset();
		
			TaskManager.submit(new Task(2, player, false) {
				@Override
				public void execute() {		
					Position position = new Position(2757, 3477, 0);
					player.moveTo(position);
					player.getMovementQueue().setLockMovement(false).reset();
					this.stop();
				}
			});
		
			
			 break;
		case 8011: //Ardy
			if (player.inFFALobby){
				player.sendMessage("Use the portal to leave the ffa lobby!");
				return;
			}
			if (player.inFFA) {
				player.sendMessage("You can not teleport out of FFA!");
				return;
			}
			if(!player.getClickDelay().elapsed(1200) || player.getMovementQueue().isLockMovement()) {
				return;
			}
			if(player.getLocation() == Location.CONSTRUCTION) {
				player.getPacketSender().sendMessage("Please use the portal to exit your house");
				return;
			}
		
			
			if(!checkReqs(player, targetLocation)) {
				return;
			}
			player.setTeleporting(true).getMovementQueue().setLockMovement(true).reset();
			cancelCurrentActions(player);
			player.performAnimation(new Animation(4731));
			player.performGraphic(new Graphic(678));
			player.getInventory().delete(8011, 1);
			player.getClickDelay().reset();
		
			TaskManager.submit(new Task(2, player, false) {
				@Override
				public void execute() {		
					Position position = new Position(2662, 3305, 0);
					player.moveTo(position);
					player.getMovementQueue().setLockMovement(false).reset();
					this.stop();
				}
			});
		
	
			 break;
		case 8012: //Watchtower Tele
			if (player.inFFALobby){
				player.sendMessage("Use the portal to leave the ffa lobby!");
				return;
			}
			if (player.inFFA) {
				player.sendMessage("You can not teleport out of FFA!");
				return;
			}
			if(!player.getClickDelay().elapsed(1200) || player.getMovementQueue().isLockMovement()) {
				return;
			}
			if(player.getLocation() == Location.CONSTRUCTION) {
				player.getPacketSender().sendMessage("Please use the portal to exit your house");
				return;
			}
		
			
			if(!checkReqs(player, targetLocation)) {
				return;
			}
			player.setTeleporting(true).getMovementQueue().setLockMovement(true).reset();
			cancelCurrentActions(player);
			player.performAnimation(new Animation(4731));
			player.performGraphic(new Graphic(678));
			player.getInventory().delete(8012, 1);
			player.getClickDelay().reset();
		
			TaskManager.submit(new Task(2, player, false) {
				@Override
				public void execute() {		
					Position position = new Position(2728, 3349, 0);
					player.moveTo(position);
					player.getMovementQueue().setLockMovement(false).reset();
					this.stop();
				}
			});
		
	
			 break;
		case 8013: //Home Tele
			 TeleportHandler.teleportPlayer(player, new Position(3087, 3491), TeleportType.NORMAL);
			 break;
		case 13598: //Runecrafting Tele
			 TeleportHandler.teleportPlayer(player, new Position(2595, 4772), TeleportType.NORMAL);
			 break;
		case 13599: //Air Altar Tele
			 TeleportHandler.teleportPlayer(player, new Position(2845, 4832), TeleportType.NORMAL);
			 break;
		case 13600: //Mind Altar Tele
			 TeleportHandler.teleportPlayer(player, new Position(2796, 4818), TeleportType.NORMAL);
			 break;
		case 13601: //Water Altar Tele
			 TeleportHandler.teleportPlayer(player, new Position(2713, 4836), TeleportType.NORMAL);
			 break;
		case 13602: //Earth Altar Tele
			 TeleportHandler.teleportPlayer(player, new Position(2660, 4839), TeleportType.NORMAL);
			 break;
		case 13603: //Fire Altar Tele
			 TeleportHandler.teleportPlayer(player, new Position(2584, 4836), TeleportType.NORMAL);
			 break;
		case 13604: //Body Altar Tele
			 TeleportHandler.teleportPlayer(player, new Position(2527, 4833), TeleportType.NORMAL);
			 break;
		case 13605: //Cosmic Altar Tele
			 TeleportHandler.teleportPlayer(player, new Position(2162, 4833), TeleportType.NORMAL);
			 break;
		case 13606: //Chaos Altar Tele
			 TeleportHandler.teleportPlayer(player, new Position(2269,4843), TeleportType.NORMAL);
			 break;
		case 13607: //Nature Altar Tele
			 TeleportHandler.teleportPlayer(player, new Position(2398, 4841), TeleportType.NORMAL);
			 break;
		case 13608: //Law Altar Tele
			 TeleportHandler.teleportPlayer(player, new Position(2464, 4834), TeleportType.NORMAL);
			 break;
		case 13609: //Death Altar Tele
			 TeleportHandler.teleportPlayer(player, new Position(2207, 4836), TeleportType.NORMAL);
			 break;
		case 18809: //Rimmington Tele
			 TeleportHandler.teleportPlayer(player, new Position(2957, 3214), TeleportType.NORMAL);
			 break;
		case 18811: //Pollnivneach Tele
			 TeleportHandler.teleportPlayer(player, new Position(3359,2910), TeleportType.NORMAL);
			 break;
		case 18812: //Rellekka Tele
			 TeleportHandler.teleportPlayer(player, new Position(2659, 3661), TeleportType.NORMAL);
			 break;
		case 18814: //Yanielle Tele
			 TeleportHandler.teleportPlayer(player, new Position(2606, 3093), TeleportType.NORMAL);
			 break;
		case 6542:
			ChristmasPresent.openBox(player);
			break;
			
			
		case 10025:
			CharmBox.open(player);
			break;
			
			
		case 1959:
			TrioBosses.eatPumpkin(player);
			break;
		case 2677:
		case 2678:
		case 2679:
		case 2680:
		case 2681:
		case 2682:
		case 2683:
		case 2684:
		case 2685:
			ClueScrolls.giveHint(player, itemId);
			break;
		case 7956:
			if (player.getRights() == PlayerRights.DONATOR) {
				if (Misc.getRandom(0) == 4) {
					player.getPacketSender().sendMessage("Casket has been saved as a donator benefit");
				} else {
					player.getInventory().delete(7956, 1);
				}
			}
			if (player.getRights() == PlayerRights.SUPER_DONATOR || player.getRights() == PlayerRights.SUPPORT) {
				if (Misc.getRandom(0) == 5) {
					player.getPacketSender().sendMessage("Casket has been saved as a donator benefit");
				} else {
					player.getInventory().delete(7956, 1);
				}
			}
			if (player.getRights() == PlayerRights.EXTREME_DONATOR || player.getRights() == PlayerRights.MODERATOR) {
				if (Misc.getRandom(0) == 6) {
					player.getPacketSender().sendMessage("Casket has been saved as a donator benefit");
				} else {
					player.getInventory().delete(7956, 1);
				}
			}
			if (player.getRights() == PlayerRights.SPONSOR_DONATOR  || player.getRights() == PlayerRights.ADMINISTRATOR) {
				if (Misc.getRandom(0) == 7) {
					player.getPacketSender().sendMessage("Casket has been saved as a donator benefit");
				} else {
					player.getInventory().delete(7956, 1);
				}
			}
			if (player.getRights() == PlayerRights.EXECUTIVE_DONATOR || player.getRights() == PlayerRights.DEVELOPER || player.getRights() == PlayerRights.DIVINE_DONATOR) {
				if (Misc.getRandom(0) == 8) {
					player.getPacketSender().sendMessage("Casket has been saved as a donator benefit");
				} else {
					player.getInventory().delete(7956, 1);
				}
			}
			if (player.getRights() == PlayerRights.PLAYER || player.getRights() == PlayerRights.YOUTUBER) {
				player.getInventory().delete(7956, 1);
			}
			
			int[] rewards = 		{5022,5022,5022,5022,5022,5022,5022,5022};
			int[] rewardsAmount = 	{200,400,500,500,500,500,200,500,1000,1000,500,500,500,500,500,500,500,500,500,500,1000};
			int rewardPos = Misc.getRandom(rewards.length-1);
			player.getInventory().add(rewards[rewardPos], ((Misc.random(rewardsAmount[rewardPos])) + (Misc.getRandom(rewardsAmount[rewardPos]))));
			break;
			
			//Donation Box
		case 6183:
			DonationBox.open(player);
			break;
			case 15369:
			RUBoxes.openCommonBox(player);
			break;
		case 15370:
			RUBoxes.openUncommonBox(player);
			break;
		case 15371:
			RUBoxes.openRareBox(player);
			break;
		case 15372:
			RUBoxes.openExtremeBox(player);
			break;
		case 15373:
			RUBoxes.openLegendaryBox(player);
			break;
			//Clue Scroll
		case 2714:
			ClueScrolls.addClueRewards(player);
			break;
		case 621:
			player.getInventory().delete(621, 1);
			player.getPointsHandler().incrementTriviaPoints(10);
			player.getPacketSender().sendMessage("You have claimed 10 Trivia Points");
			break;
			
		case 13208:
			if (!player.getCorruptBandagesLoading().getContents().isEmpty()) {
				for (Entry<Integer> crystal : player.getCorruptBandagesLoading().getContents().entrySet()) {
					player.getCorruptBandagesLoading().getContents().remove(crystal.getElement());
					player.setConstitution(player.getConstitution() + 150);
					if(player.getConstitution() > 1190)
						player.setConstitution(1190);
					Sounds.sendSound(player, Sound.EAT_FOOD);
					player.getPacketSender().sendMessage("@red@The Corrupt bandage has healed you 15 Hitpoints");
					return;
				}
			} else {
				player.getPacketSender().sendMessage("@red@You have no crystals in your Bandage!");
			}
			break;
			
		
				
		case 15387:
			player.getInventory().delete(15387, 1);
			rewards = new int[] {1377, 1149, 7158, 3000, 219, 5016, 6293, 6889, 2205, 3051, 269, 329, 3779, 6371, 2442, 347, 247};
			player.getInventory().add(rewards[RandomUtility.getRandom(rewards.length-1)], 1);
			break;
		case 407:
			player.getInventory().delete(407, 1);
			if (RandomUtility.getRandom(3) < 3) {
				player.getInventory().add(409, 1);
			} else if(RandomUtility.getRandom(4) < 4) {
				player.getInventory().add(411, 1);
			} else 
				player.getInventory().add(413, 1);
			break;
		case 405:
			player.getInventory().delete(405, 1);
			if (RandomUtility.getRandom(1) < 1) {
				int coins = RandomUtility.getRandom(10);
				player.getInventory().add(995, coins);
				player.getPacketSender().sendMessage("The casket contained "+coins+" coins!");
			} else
				player.getPacketSender().sendMessage("The casket was empty.");
			break;
		case 15084:
			Gambling.rollDice(player);
			break;
		case 6307:
			player.setDialogueActionId(79);
			DialogueManager.start(player, 0);
			break;
		case 299:
			Gambling.plantSeed(player);
			break;
		case 4155:
			if(player.getSlayer().getSlayerTask() == SlayerTasks.NO_TASK) {
				player.getPacketSender().sendInterfaceRemoval();
				player.getPacketSender().sendMessage("Your Enchanted gem will only work if you have a Slayer task.");
				return;
			}
			DialogueManager.start(player, SlayerDialogues.dialogue(player));
			break;
		case 11858:
		case 11860:
		case 11862:
		case 11848:
		case 11856:
		case 11850:
		case 11854:
		case 11852:
		case 11846:
			if(!player.getClickDelay().elapsed(2000) || !player.getInventory().contains(itemId))
				return;
			if(player.busy()) {
				player.getPacketSender().sendMessage("You cannot open this right now.");
				return;
			}

			int[] items = itemId == 11858 ? new int[] {10350, 10348, 10346, 10352} : 
				itemId == 11860 ? new int[]{10334, 10330, 10332, 10336} : 
					itemId == 11862 ? new int[]{10342, 10338, 10340, 10344} : 
						itemId == 11848 ? new int[]{4716, 4720, 4722, 4718} : 
							itemId == 11856 ? new int[]{4753, 4757, 4759, 4755} : 
								itemId == 11850 ? new int[]{4724, 4728, 4730, 4726} : 
									itemId == 11854 ? new int[]{4745, 4749, 4751, 4747} : 
										itemId == 11852 ? new int[]{4732, 4734, 4736, 4738} : 
											itemId == 11846 ? new int[]{4708, 4712, 4714, 4710} :
												new int[]{itemId};

											if(player.getInventory().getFreeSlots() < items.length) {
												player.getPacketSender().sendMessage("You do not have enough space in your inventory.");
												return;
											}
											player.getInventory().delete(itemId, 1);
											for(int i : items) {
												player.getInventory().add(i, 1);
											}
											player.getPacketSender().sendMessage("You open the set and find items inside.");
											player.getClickDelay().reset();
											break;
		case 952:
			if(player.getInventory().getFreeSlots() < 1) {
				player.getPacketSender().sendMessage("You need at least 1 free inventory space to loot this.");
				return;
			}
			if(player.getLocation() != Location.WILDERNESS) {
				if(!player.getClickDelay().elapsed(1300)) {
					return;
				}
				if(!player.getClickDelay().elapsed(1300))
					return;
				Digging.dig(player);
				player.getAchievementTracker().progress(AchievementData.DIG_FOR_ARCHAEOLOGY, 1);
				player.getMovementQueue().reset();
				player.getPacketSender().sendMessage("You start digging in the mud...");
				player.performAnimation(new Animation(830));
				player.getSkillManager().addExperience(Skill.ARCHAEOLOGY, 1500 + Misc.getRandom(125));
				player.getClickDelay().reset();
				player.getInventory().add(5022, 100).refreshItems();


				player.getPacketSender().sendMessage("You find some scrolls...");
				if (Misc.getRandom(5) == 1) {
					//player.getInventory().add(1215, 1).refreshItems();
					player.getSkillManager().addExperience(Skill.ARCHAEOLOGY, 250 + Misc.getRandom(125));
					player.getPacketSender().sendMessage("@red@You found nothing but got some extra XP!");
				}
				if (Misc.getRandom(10) == 1) {
					player.getInventory().add(11559, 1).refreshItems();
					player.getSkillManager().addExperience(Skill.ARCHAEOLOGY, 250 + Misc.getRandom(125));

					player.getPacketSender().sendMessage("@red@You found a starter key, and some extra XP!");
				}
				if (Misc.getRandom(5) == 1) {
					player.getInventory().add(1969, 1).refreshItems();
					player.getPacketSender().sendMessage("@red@You found a spinach roll! Yuuck");
				}
				if (Misc.getRandom(13) == 1) {
					player.getInventory().add(1965, 1).refreshItems();
					player.getPacketSender().sendMessage("@red@You found a cabbage! Yuucky!");
				}
				if (Misc.getRandom(50) == 1) {
					player.getInventory().add(20900, 1).refreshItems();
					player.getPacketSender().sendMessage("@red@You found an insanity key!");
				}
				if (Misc.getRandom(300) == 1) {
					player.getInventory().add(2717, 1).refreshItems();
					player.getPacketSender().sendMessage("@red@You found an Insanity Chest");
				}
				if (Misc.getRandom(1000) == 1) {
					player.getInventory().add(5023, 1).refreshItems();
					player.getPacketSender().sendMessage("@red@You got super lucky while digging, and got a 1q scroll!");
				}
				if (Misc.getRandom(35) == 1) {
					player.getInventory().add(5022, 10000).refreshItems();
					player.getSkillManager().addExperience(Skill.ARCHAEOLOGY, 250 + Misc.getRandom(125));
					player.getPacketSender().sendMessage("@red@You found 10k 1B Scrolls while digging");
				}
				if (Misc.getRandom(2500) == 1) {
					player.getInventory().add(15356, 1).refreshItems();
					player.getSkillManager().addExperience(Skill.ARCHAEOLOGY, 250 + Misc.getRandom(125));
					World.sendMessage("<shad=0>@bla@[@cya@Archaeology@bla@] @cya@"+player.getUsername()+"@bla@ Has just received a @cya@ $10 Bond@bla@ from digging!");
				}
				return;
			}
			break;
		case 10006:
			// Hunter.getInstance().laySnare(client);
			Hunter.layTrap(player, new SnareTrap(new GameObject(19175, new Position(player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ())), TrapState.SET, 200, player));
			break;
		case 10008:			
			Hunter.layTrap(player, new BoxTrap(new GameObject(19187, new Position(player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ())), TrapState.SET, 200, player));
			break;
		case 5509:
		case 5510:
		case 5512:
			RunecraftingPouches.fill(player, RunecraftingPouch.forId(itemId));
			break;
		case 292:
			IngridientsBook.readBook(player, 0, false);
			break;
		case 600:
			TestBook.readBook(player, 0, false);
			break;
		case 6199:
			MysteryBox mysteryBox = new MysteryBox(player);
			mysteryBox.openInterface();
			player.setBox(6199);
			break;

			case 7100:
				int rewards3[][] = {
						{11995,11996,11997,12001,12002,12003,12004,2771,2771,19935,12005,12006,11990,11981,11979,2090,2091}, //Uncommon, 0
						{11991,11992,11993,11994,11989,11988,11987,11986,11985,11984,2759,2760,2761,2762,11983,11982,}, //Rare, 1
						{2104,2758,2095,2096,2097,2098}
				};
				double numGen3 = Math.random();
				/** Chances
				 *  50% chance of Common Items - cheap gear, high-end consumables
				 *  40% chance of Uncommon Items - various high-end coin-bought gear
				 *  10% chance of Rare Items - Highest-end coin-bought gear, some voting-point/pk-point equipment
				 */
				int rewardGrade3;
				int random3 = Misc.random(99)+1;
				if(random3 > 50) {
					rewardGrade3 = 0;
				} else if(random3 > 4) {
					rewardGrade3 = 1;
				} else {
					rewardGrade3 = 2;
				}


				rewardPos = RandomUtility.getRandom(rewards3[rewardGrade3].length-1);
				player.getInventory().delete(7100, 1);
				player.getInventory().add(rewards3[rewardGrade3][rewardPos], 1).refreshItems();
					World.sendMessage("<shad=0>@bla@[@cya@Pokeball@bla@] @cya@"+player.getUsername()+"@bla@ Has just received a @cya@ "+ItemDefinition.forId(rewards3[rewardGrade3][rewardPos]).getName()+" @bla@!");
				break;

				case 7102:
					YodaBox yodaBox = new YodaBox(player);
					yodaBox.openInterface();
					player.setBox(7102);
				break;

				case 7104:
					int[] common7 = new int[]{3886, 4279, 4280, 3892, 3891, 5079, 5080};
					int[] uncommon7 = new int[]{3890, 3889, 3888, 3881};
					int[] rare7 = new int[]{3884, 3883, 3887, 3885, 19691, 9676, 9677};
					player.getMysteryBoxOpener().setOpenBox(7104);
					player.getMysteryBoxOpener().display(7104, "Pokeball", common7, uncommon7, rare7);
				break;
			case 7124:
				int[] owner = new int[]{15354, 15355};
				int[] uncommonowner = new int[]{11423, 4770, 3084, 4061};
				int[] rareowner = new int[]{3689, 3687, 3688, 3690, 3691, 21003, 21031, 21032, 21033, 3279};
				player.getMysteryBoxOpener().setOpenBox(7124);
				player.getMysteryBoxOpener().display(7124, "Owner Box", owner, uncommonowner, rareowner);
				break;
			case 3230:
				int[] casketnormal = new int[]{15356, 15355};
				int[] casketuncommon = new int[]{14931, 3576, 3569, 7122};
				int[] casketrare = new int[]{898, 897, 2543, 3279, 3246, 5001, 5002, 1667, 15359, 7106};
				player.getMysteryBoxOpener().setOpenBox(3230);
				player.getMysteryBoxOpener().display(3230, "Launch Casket", casketnormal, casketuncommon, casketrare);
				break;

			case 3231:
				int[] icasket = new int[]{4770, 15355, 15359, 898, 897, 8898, 4772, 5202, 5228};
				int[] icasketuncommon = new int[]{14922, 14923,14924, 14919, 14920, 14921};
				int[] icasketrare = new int[]{3280, 3279, 5002, 3882,20550, 14922};
				player.getMysteryBoxOpener().setOpenBox(3231);
				player.getMysteryBoxOpener().display(3231, "Launch Casket (i)", icasket, icasketuncommon, icasketrare);
				break;

				case 7106:
					int[] common1 = new int[]{4765, 4767, 4766, 4769, 4768, 4771, 4772};
					int[] uncommon1 = new int[]{};
					int[] rare1 = new int[]{};
					player.getMysteryBoxOpener().setOpenBox(7106);
					player.getMysteryBoxOpener().display(7106, "kingdom Hearts Box", common1, uncommon1, rare1);
				break;

				case 7108:
					int[] common2 = new int[]{1038, 1040, 1042, 1044, 1046, 1048, 1044, 1046, 1048, 19017, 19053, 14052, 14051, 1050, 14050, 1053, 1055, 14050}; //Uncommon, 0
					int[] uncommon2 = new int[]{5092, 5093, 5090};
					int[] rare2 = new int[]{};
					player.getMysteryBoxOpener().setOpenBox(7108);
					player.getMysteryBoxOpener().display(7108, "Cosmetic Box", common2, uncommon2, rare2);
				break;

				case 7112:
					BandosBox bandosBox = new BandosBox(player);
					bandosBox.openInterface();
					player.setBox(7112);

				break;

            case 7114:
				int[] common = new int[]{11527, 11529, 11531};
				int[] uncommon = new int[]{3819, 3820, 3821, 3822, 3823, 3824, 3825};
				int[] rare = new int[]{11533, 11423, 4060, 15355, 15356};
				player.getMysteryBoxOpener().setOpenBox(7114);
				player.getMysteryBoxOpener().display(7114, "Super Donator Box", common, uncommon, rare);
				break;

            case 7122:
            	int[] common6 = new int[]{911, 910, 909, 913, 912, 4761};
				int[] uncommon6 = new int[]{11423, 4761, 903, 11355};
				int[] rare6 = new int[]{11536, 11537, 4770, 20054};
				player.getMysteryBoxOpener().setOpenBox(7122);
				player.getMysteryBoxOpener().display(7122, "Sponsor Donator Box", common6, uncommon6, rare6);
				break;

				case 7116:
					int[] common4 = new int[]{11423, 5083, 5084, 5085, 5086, 5087, 5088, 5089 };
					int[] uncommon4 = new int[]{20054, 5140, 5141, 5142, 5143, 5144, 5145, 5146, 5147, 5148
							, 15355, 15356, 15357};
					int[] rare4 = new int[]{5111, 5113, 5112, 5115, 5114, 5116, 4743, 4770, 5000, 15358, 1667};
					player.getMysteryBoxOpener().setOpenBox(7116);
					player.getMysteryBoxOpener().display(7116, "Executive Donator Box", common4, uncommon4, rare4);
				break;

				case 7120:
					int[] common5 = new int[]{3285, 3286, 3287, 20644, 20645};
					int[] uncommon5 = new int[]{4806, 4805, 4804, 925, 923, 924, 927, 926, 922, 919, 3666};
					int[] rare5 = new int[]{4761, 11423, 12423};
					player.getMysteryBoxOpener().setOpenBox(7120);
					player.getMysteryBoxOpener().display(7120, "Extreme Donator Box", common5, uncommon5, rare5);
//					if(player.getInventory().getFreeSlots() < 2) {
//						player.getPacketSeAzAzAzAznder().sendMessage("You don't have enough free inventory space.");
//						return;
//					}
//					 int neo[][] = {
//							{5022}, //Uncommon, 0
//							{20702,20701,20700,20705,20706,20703,20704}, //Rare, 1
//							{2104,2758,2095,2096,2097,2098}
//					};
//				 numGen3 = Math.random();
//
//				  randomYoda = Misc.random(275);
//
//					 drBoost = NPCDrops.getDroprate(player);
//					randomYoda = (int)randomYoda * ((100-drBoost)/100);
//				 amount = 1;
//				if(randomYoda == 0) {
//					rewardGrade3 = 1;
//					rewardPos = RandomUtility.getRandom(neo[rewardGrade3].length-1);
//					player.setAnimation(new Animation(6382));
//					player.setGraphic(new Graphic(127));
//					World.sendMessage("<shad=0>@bla@[@whi@Neo Cortex box@bla@] @whi@"+player.getUsername()+"@bla@ Has just received a @whi@ "+ItemDefinition.forId(neo[rewardGrade3][rewardPos]).getName()+"@bla@!");
//					double randomDouble = Misc.random(99);
//
//					double ddrBoost = NPCDrops.getDoubleDr(player);
//					randomDouble = (int)randomDouble * ((100-ddrBoost)/100);
//					if(randomDouble == 0) {
//						amount *= 2;
//					}
//				} else {
//					rewardGrade3 = 0;
//					rewardPos = RandomUtility.getRandom(neo[rewardGrade3].length-1);
//					double randomDouble = Misc.random(99);
//
//					double ddrBoost = NPCDrops.getDoubleDr(player);
//					randomDouble = (int)randomDouble * ((100-ddrBoost)/100);
//					amount = Misc.random(225);
//
//					if(randomDouble == 0) {
//						amount *= 2;
//					}
//				}
//				player.getInventory().delete(7120, 1);
//				player.getInventory().add(neo[rewardGrade3][rewardPos], amount).refreshItems();
				break;


			case 7118:
				int[] common3 = new int[]{2572, 11527, 3683, 3684, 3685, 20259}; //Uncommon, 0
				int[] uncommon3 = new int[]{11529, 11531, 3313, 3314, 3315, 3316, 3317};
				int[] rare3 = new int[]{11533, 11423, 3084};
				player.getMysteryBoxOpener().setOpenBox(7118);
				player.getMysteryBoxOpener().display(7118, "Donator Box", common3, uncommon3, rare3);
				break;
				
			case 14933:
				int[] common8 = new int[]{5023, 7118, 7114, 7120}; //Uncommon, 0
				int[] uncommon8 = new int[]{14925, 14926, 14927, 14928, 14929, 14930, 5140, 5141, 5142, 5143, 5144, 5146, 5147};
				int[] rare8 = new int[]{14910, 14911, 14912, 14913, 14914, 14915, 14916, 14917, 14918, 15355};
				player.getMysteryBoxOpener().setOpenBox(14933);
				player.getMysteryBoxOpener().display(14933, "@yel@Lucky Block", common8, uncommon8, rare8);
				break;


			case 21045:


				int rewards4[][] = {
						{894}, //Uncommon, 0
						{895, 700}, //Rare, 1
						{21044, 2867, 906, 907, 908,10865,12629}
				};
				 numGen3 = Math.random();
				/** Chances
				 *  50% chance of Common Items - cheap gear, high-end consumables
				 *  40% chance of Uncommon Items - various high-end coin-bought gear
				 *  10% chance of Rare Items - Highest-end coin-bought gear, some voting-point/pk-point equipment
				 */
				random3 = Misc.random(99)+1;
				if(random3 > 50) {
					rewardGrade3 = 0;
				} else if(random3 > 25) {
					rewardGrade3 = 1;
				} else {
					rewardGrade3 = 2;
				}


				rewardPos = RandomUtility.getRandom(rewards4[rewardGrade3].length-1);
				player.getInventory().delete(21045, 1);
				player.getInventory().add(rewards4[rewardGrade3][rewardPos], 1).refreshItems();
					World.sendMessage("<shad=0>@bla@[@cya@Weaponbox box@bla@] @cya@"+player.getUsername()+"@bla@ Has just received a @cya@ "+ItemDefinition.forId(rewards4[rewardGrade3][rewardPos]).getName()+" @bla@!");
				break;
		case 15501:
			int superiorRewards[][] = {
					{11995,11996,11997,12001,12002,12003,12004,2771,2771,19935,12005,12006,11990,11981,11979,2090,2091}, //Uncommon, 0
					{11991,11992,11993,11994,11989,11988,11987,11986,11985,11984,2759,2760,2761,2762,11983,11982,}, //Rare, 1
					{2104,2758,2095,2096,2097,2098} //Legendary, 3
			};
			double superiorNumGen = Math.random();
			/** Chances
			 *  40% chance of Uncommon Items - various high-end coin-bought gear
			 *  30% chance of Rare Items - Highest-end coin-bought gear, Some poor voting-point/pk-point equipment
			 *  20% chance of Epic Items -Better voting-point/pk-point equipment
			 *  10% chance of Legendary Items - Only top-notch voting-point/pk-point equipment
			 */
			int superiorRewardGrade = superiorNumGen >= 0.60 ? 0 : superiorNumGen >= 0.30 ? 1 : superiorNumGen >= 0.10 ? 2 : 3;
			int superiorRewardPos = RandomUtility.getRandom(superiorRewards[superiorRewardGrade].length-1);
			player.getInventory().delete(15501, 1);
			player.getInventory().add(superiorRewards[superiorRewardGrade][superiorRewardPos], 1).refreshItems();
			break;
		case 11882:
			player.getInventory().delete(11882, 1);
			player.getInventory().add(2595, 1).refreshItems();
			player.getInventory().add(2591, 1).refreshItems();
			player.getInventory().add(3473, 1).refreshItems();
			player.getInventory().add(2597, 1).refreshItems();
			break;
		case 11884:
			player.getInventory().delete(11884, 1);
			player.getInventory().add(2595, 1).refreshItems();
			player.getInventory().add(2591, 1).refreshItems();
			player.getInventory().add(2593, 1).refreshItems();
			player.getInventory().add(2597, 1).refreshItems();
			break;
		case 11906:
			player.getInventory().delete(11906, 1);
			player.getInventory().add(7394, 1).refreshItems();
			player.getInventory().add(7390, 1).refreshItems();
			player.getInventory().add(7386, 1).refreshItems();
			break;
		case 15262:
			if(!player.getClickDelay().elapsed(1000))
				return;
			player.getInventory().delete(15262, 1);
			player.getInventory().add(18016, 10000).refreshItems();
			player.getClickDelay().reset();
			break;
		case 6:
			DwarfMultiCannon.setupCannon(player);
			break;
		}
	}

	public static void secondAction(Player player, Packet packet) {
		int interfaceId = packet.readLEShortA();
		int slot = packet.readLEShort();
		int itemId = packet.readShortA();


		if(slot < 0 || slot > player.getInventory().capacity())
			return;
		if(player.getInventory().getItems()[slot].getId() != itemId)
			return;
		if (SummoningData.isPouch(player, itemId, 2))
			return;
//		if (new DissolveHandler(player).handleDissolving(itemId)) {
//			return;
//		}

	/*	if(itemId == 5022) {
			for(int i = 0; i < player.getInventory().getAmount(5023); i++) {
				player.getInventory().delete(5022,i);
				MoneyPouch.depositMoney(player,1000000000);

			}
		} */

		switch(itemId) {


		case 6500:
			if(player.getCombatBuilder().isAttacking() || player.getCombatBuilder().isBeingAttacked()) {
				player.getPacketSender().sendMessage("You cannot configure this right now.");
				return;
			}
			player.getPacketSender().sendInterfaceRemoval();
			DialogueManager.start(player, 101);
			player.setDialogueActionId(60);
			break;

		case 12926:
			player.getBlowpipeLoading().handleUnloadBlowpipe();
			break;
		case 3962:
			player.getDragonRageLoading().handleUnloadDragonrage();
			break;
		case 896:
			player.getMinigunLoading().handleUnloadMinigun();
			break;
		case 13208:
			player.getCorruptBandagesLoading().handleUnloadCorruptBandages();
			break;

			//25% DR SCROLL



			//50% DR SCROLL



		case 11724:
			if(player.getInventory().contains(6530)){
				player.getInventory().delete(6530, 250);
				player.getInventory().add(897, 1);
				World.sendMessage("<img=10> <col=008FB2>[Upgrade]"+player.getUsername()+" has just upgraded his @red@Bandos Chestplate@la@ to tier 1!");
				player.getPacketSender().sendMessage("Your Bandos Items Has been Upgraded");
				} else {
					player.getPacketSender().sendMessage("@red@You Need 250 Upgrade Tokens To Do This");
				}
				break;
		case 11726:
			if(player.getInventory().contains(6530)){
				player.getInventory().delete(6530, 250);
				player.getInventory().add(894, 1);
				World.sendMessage("<img=10> <col=008FB2>[Upgrade]"+player.getUsername()+" has just upgraded his @red@Bandos Tassets@la@ to tier 1!");
				player.getPacketSender().sendMessage("Your Bandos Items Has been Upgraded");
				} else {	
					player.getPacketSender().sendMessage("@red@You Need 250 Upgrade Tokens To Do This");
				}
				break;
		case 894:
			if(player.getInventory().contains(6530)){
				player.getInventory().delete(6530, 500);
				player.getInventory().add(895, 1);
				World.sendMessage("<img=10> <col=008FB2>[Upgrade]"+player.getUsername()+" has just upgraded his @red@Bandos Chestplate@la@ to tier 2!");
				player.getPacketSender().sendMessage("Your Bandos Items Has been Upgraded");
				} else {
					player.getPacketSender().sendMessage("@red@You Need 500 Upgrade Tokens To Do This");
				}
				break;
		case 895:
			if(player.getInventory().contains(6530)){
				player.getInventory().delete(6530, 1000);
				player.getInventory().add(896, 1);
				World.sendMessage("<img=10> <col=008FB2>[Upgrade]"+player.getUsername()+" has just upgraded his @red@Bandos Tassets@la@ to tier 3!");
				player.getPacketSender().sendMessage("Your Bandos Items Has been Upgraded");
				} else {
					player.getPacketSender().sendMessage("@red@You Need 1000 Upgrade Tokens To Do This");
				}
				break;
		case 897:
			if(player.getInventory().contains(6530)){
				player.getInventory().delete(6530, 500);
				player.getInventory().add(898, 1);
				World.sendMessage("<img=10> <col=008FB2>[Upgrade]"+player.getUsername()+" has just upgraded his @red@Bandos Chestplate@la@ to tier 2!");
				player.getPacketSender().sendMessage("Your Bandos Items Has been Upgraded");
				} else {
					player.getPacketSender().sendMessage("@red@You Need 500 Upgrade Tokens To Do This");
				}
				break;
		case 898:
			if(player.getInventory().contains(6530)){
			player.getInventory().delete(6530, 1000);
			player.getInventory().add(899, 1);
			World.sendMessage("<img=10> <col=008FB2>[Upgrade]"+player.getUsername()+" has just upgraded his @red@Bandos Chestplate@la@ to tier 3!");
			player.getPacketSender().sendMessage("Your Bandos Items Has been Upgraded");
			} else {
				player.getPacketSender().sendMessage("@red@You Need 1000 Upgrade Tokens To Do This");
			}
			break;
			
			
			
			
		case 1712:
		case 1710:
		case 1708:
		case 1706:
		case 11118:
		case 11120:
		case 11122:
		case 11124:
			JewelryTeleporting.rub(player, itemId);
			break;
		case 1704:
			player.getPacketSender().sendMessage("Your amulet has run out of charges.");
			break;
		case 11126:
			player.getPacketSender().sendMessage("Your bracelet has run out of charges.");
			break;
		case 13281:
		case 13282:
		case 13283:
		case 13284:
		case 13285:
		case 13286:
		case 13287:
		case 13288:
			player.getSlayer().handleSlayerRingTP(itemId);
			break;
		case 5509:
		case 5510:
		case 5512:
			RunecraftingPouches.check(player, RunecraftingPouch.forId(itemId));
			break;
		case 995:
			MoneyPouch.depositMoney(player, player.getInventory().getAmount(995));
			break;
			case 5022:
				int amount = 0;
			//	for(int i = 0; i < player.getInventory().getAmount(5022); i++) {
				//	amount++;
			//	}
				//player.getInventory().delete(5022,amount);
				//MoneyPouch.depositMoney(player, player.getInventory().getAmount(5022));
				player.setMoneyInPouch(player.getMoneyInPouch()+(long)1000000000*player.getInventory().getAmount(5022));
				player.getPacketSender().sendString(8135, ""+player.getMoneyInPouch());
				player.getInventory().delete(5022, player.getInventory().getAmount(5022));
				break;
		case 1438:
		case 1448:
		case 1440:
		case 1442:
		case 1444:
		case 1446:
		case 1454:
		case 1452:
		case 1462:
		case 1458:
		case 1456:
		case 1450:
			Runecrafting.handleTalisman(player, itemId);
			break;
		}
	}

	public void thirdClickAction(Player player, Packet packet) {
		int itemId = packet.readShortA();
		int slot = packet.readLEShortA();
		int interfaceId = packet.readLEShortA();
		if(slot < 0 || slot > player.getInventory().capacity())
			return;
		if(player.getInventory().getItems()[slot].getId() != itemId)
			return;
		if(JarData.forJar(itemId) != null) {
			PuroPuro.lootJar(player, new Item(itemId, 1), JarData.forJar(itemId));
			return;
		}
		if (SummoningData.isPouch(player, itemId, 3)) {
			return;
		}
		if(ItemBinding.isBindable(itemId)) {
			ItemBinding.bindItem(player, itemId);
			return;
		}
		if(itemId == 5022) {
			int amount = 0;
			for(int i = 0; i < player.getInventory().getAmount(5022); i++) {
				amount++;
			}
			player.getInventory().delete(5022,amount);
			player.setMoneyInPouch((long) 1000000000*amount);
		}
		switch(itemId) {
		case 14019:
			player.getPacketSender().sendInterface(60000);
			break;
		case 12926:
			player.getBlowpipeLoading().handleCheckBlowpipe();
			break;
		case 3962:
			player.getDragonRageLoading().handleCheckDragonrage();
			break;
		case 896:
			player.getMinigunLoading().handleCheckMinigun();
			break;
		case 13208:
			player.getCorruptBandagesLoading().handleCheckCorruptBandages();
			break;
			case 21007:
				if(player.getInventory().hasItem(new Item(21007,100))){
//					World.sendMessage("<shad=0>@bla@[@whi@Mythical@bla@] <shad=0>@whi@"+ player.getUsername()
//							+ "@bla@ has just forged a mythical blade part");
				} else {
					player.sendMessage("You need 100 shards to forge the mythical blade part");
				}
				break;
			case 21006:
				if(player.getInventory().hasItem(new Item(21006,100))){
//					World.sendMessage("<shad=0>@bla@[@whi@Mythical@bla@] <shad=0>@whi@"+ player.getUsername()
//							+ "@bla@ has just forged a mythical hilt part");

				} else {
					player.sendMessage("You need 100 shards to forge the mythical hilt part");
				}
				break;
		case 19670:
			if(player.busy()) {
				player.getPacketSender().sendMessage("You can not do this right now.");
				return;
			}
			player.setDialogueActionId(71);
			DialogueManager.start(player, player.getGameMode() == GameMode.NORMAL ? 108 : 109);
			break;
		case 6500:
			CharmingImp.sendConfig(player);
			break;
		case 4155:
			player.getPacketSender().sendInterfaceRemoval();
			DialogueManager.start(player, 103);
			player.setDialogueActionId(65);
			break;
		case 13281:
		case 13282:
		case 13283:
		case 13284:
		case 13285:
		case 13286:
		case 13287:
		case 13288:
			player.getPacketSender().sendInterfaceRemoval();
			player.getPacketSender().sendMessage(player.getSlayer().getSlayerTask() == SlayerTasks.NO_TASK ? ("You do not have a Slayer task.") : ("Your current task is to kill another "+(player.getSlayer().getAmountToSlay())+" "+Misc.formatText(player.getSlayer().getSlayerTask().toString().toLowerCase().replaceAll("_", " "))+"s."));
			break;
		case 6570:
			if(player.getInventory().contains(6570) && player.getInventory().getAmount(6529) >= 50000) {
				player.getInventory().delete(6570, 1).delete(6529, 50000).add(19111, 1);
				player.getPacketSender().sendMessage("You have upgraded your Fire cape into a TokHaar-Kal cape!");
			} else {
				player.getPacketSender().sendMessage("You need at least 50.000 Tokkul to upgrade your Fire Cape into a TokHaar-Kal cape.");
			}
			break;
		case 19111:
			if(player.getInventory().contains(19111) && player.getInventory().getAmount(19111) >= 3) {
				player.getInventory().delete(19111, 1).delete(19111, 3).add(6532, 1);
				player.getPacketSender().sendMessage("You have upgraded your TokHaar-Kal cape into a Kryptic TokHaar-Kal cape!");
			} else {
				player.getPacketSender().sendMessage("You need at least 3 TokHaar-Kal capes to upgrade your cape.");
			}
			break;
		case 6532:
			if(player.getInventory().contains(6532) && player.getInventory().getAmount(6532) >= 3) {
				player.getInventory().delete(6532, 1).delete(6532, 3).add(6934, 1);
				player.getPacketSender().sendMessage("You have upgraded your Kryptic TokHaar-Kal cape into a Brutal TokHaar-Kal cape!");
			} else {
				player.getPacketSender().sendMessage("You need at least 3 Kryptic TokHaar-Kal capes to upgrade your cape.");
			}
			break;
		case 6934:
			if(player.getInventory().contains(6934) && player.getInventory().getAmount(11316) >= 2500) {
				player.getInventory().delete(6934, 1).delete(11316, 2500).add(11318, 1);
				player.getPacketSender().sendMessage("You have upgraded your Brutal TokHaar-Kal cape into an Eternal cape!");
			} else {
				player.getPacketSender().sendMessage("You need a Brutal TokHaar-kal cape and 2500 Monster fragments to upgrade your cape.");
			}
			break;
		case 11318:
			if(player.getInventory().contains(11318) && player.getInventory().getAmount(11320) >= 5000) {
				player.getInventory().delete(11318, 1).delete(11320, 5000).add(11319, 1);
				player.getPacketSender().sendMessage("You have upgraded your Eternal cape into an Immortal cape!");
			} else {
				player.getPacketSender().sendMessage("You need an eternal cape and 5000 Boss fragments to upgrade your cape.");
			}
			break;
//			case 12421:
//				if(player.getRights() == PlayerRights.DONATOR ||player.getRights() == PlayerRights.SUPER_DONATOR || player.getRights() == PlayerRights.EXTREME_DONATOR || player.getRights() == PlayerRights.SPONSOR_DONATOR || player.getRights() == PlayerRights.EXECUTIVE_DONATOR ||player.getRights() == PlayerRights.DIVINE_DONATOR){
//					player.sendMessage("You already have a higher tier donator rank!");
//				} else {
//					player.setRights(PlayerRights.DONATOR);
//					player.getInventory().delete(12421,	1);
//					PlayerPanel.refreshPanel(player);
//					World.sendMessage("<shad=0>@bla@[@red@Donator@bla@] @red@"+player.getUsername()+" @bla@ Has just claimed his @red@donator rank@bla@!");
//				}
//				break;
//				case 12422:
//				if(player.getRights() == PlayerRights.EXTREME_DONATOR ||player.getRights() == PlayerRights.SUPER_DONATOR || player.getRights() == PlayerRights.SPONSOR_DONATOR ||player.getRights() == PlayerRights.EXECUTIVE_DONATOR){
//					player.sendMessage("You already have a higher tier donator rank!");
//				} else {
//					player.setRights(PlayerRights.SUPER_DONATOR);
//					player.getInventory().delete(12422,	1);
//					PlayerPanel.refreshPanel(player);
//					World.sendMessage("<shad=0>@bla@[@gre@Super Donator@bla@] @gre@"+player.getUsername()+" @bla@ Has just claimed his @gre@super donator rank@bla@!");
//				}
//				break;
//				case 12423:
//				if(player.getRights() == PlayerRights.EXTREME_DONATOR ||player.getRights() == PlayerRights.SPONSOR_DONATOR || player.getRights() == PlayerRights.EXECUTIVE_DONATOR || player.getRights() == PlayerRights.DIVINE_DONATOR){
//					player.sendMessage("You already have a higher tier donator rank!");
//				} else {
//					player.setRights(PlayerRights.EXTREME_DONATOR);
//					player.getInventory().delete(12423,	1);
//					PlayerPanel.refreshPanel(player);
//					World.sendMessage("<shad=0>@bla@[@blu@Extreme Donator@bla@] @blu@"+player.getUsername()+" @bla@ Has just claimed his @blu@extreme donator rank@bla@!");
//				}
//				break;
//				case 12424:
//				if(player.getRights() == PlayerRights.SPONSOR_DONATOR || player.getRights() == PlayerRights.EXECUTIVE_DONATOR || player.getRights() == PlayerRights.DIVINE_DONATOR){
//					player.sendMessage("You already have a higher tier donator rank!");
//				} else {
//					player.setRights(PlayerRights.SPONSOR_DONATOR);
//					player.getInventory().delete(12424,	1);
//					PlayerPanel.refreshPanel(player);
//					World.sendMessage("<shad=0>@bla@[@mag@Sponsor Donator@bla@] @mag@"+player.getUsername()+" @bla@ Has just claimed his @mag@Sponsor donator rank@bla@!");
//				}
//				break;
//
//				case 12425:
//				if(player.getRights() == PlayerRights.EXECUTIVE_DONATOR){
//					player.sendMessage("You already have a higher tier donator rank!");
//				} else {
//					player.setRights(PlayerRights.EXECUTIVE_DONATOR);
//					player.getInventory().delete(12425,	1);
//					player.getPacketSender().sendMessage("@red@ You now Have acces to ::Exzone!");
//					player.getInventory().add(3818, 1);
//					PlayerPanel.refreshPanel(player);
//					World.sendMessage("<shad=0>@bla@[@bla@Executive Donator@bla@] @bla@"+player.getUsername()+" @bla@ Has just claimed his @bla@Executive donator rank@bla@!");
//				}
//				break;case 12421:
//				if(player.getRights() == PlayerRights.DONATOR ||player.getRights() == PlayerRights.SUPER_DONATOR || player.getRights() == PlayerRights.EXTREME_DONATOR || player.getRights() == PlayerRights.SPONSOR_DONATOR || player.getRights() == PlayerRights.EXECUTIVE_DONATOR ||player.getRights() == PlayerRights.DIVINE_DONATOR){
//					player.sendMessage("You already have a higher tier donator rank!");
//				} else {
//					player.setRights(PlayerRights.DONATOR);
//					player.getInventory().delete(12421,	1);
//					PlayerPanel.refreshPanel(player);
//					World.sendMessage("<shad=0>@bla@[@red@Donator@bla@] @red@"+player.getUsername()+" @bla@ Has just claimed his @red@donator rank@bla@!");
//				}
//				break;
//				case 12422:
//				if(player.getRights() == PlayerRights.EXTREME_DONATOR ||player.getRights() == PlayerRights.SUPER_DONATOR || player.getRights() == PlayerRights.SPONSOR_DONATOR ||player.getRights() == PlayerRights.EXECUTIVE_DONATOR){
//					player.sendMessage("You already have a higher tier donator rank!");
//				} else {
//					player.setRights(PlayerRights.SUPER_DONATOR);
//					player.getInventory().delete(12422,	1);
//					PlayerPanel.refreshPanel(player);
//					World.sendMessage("<shad=0>@bla@[@gre@Super Donator@bla@] @gre@"+player.getUsername()+" @bla@ Has just claimed his @gre@super donator rank@bla@!");
//				}
//				break;
//				case 12423:
//				if(player.getRights() == PlayerRights.EXTREME_DONATOR ||player.getRights() == PlayerRights.SPONSOR_DONATOR || player.getRights() == PlayerRights.EXECUTIVE_DONATOR || player.getRights() == PlayerRights.DIVINE_DONATOR){
//					player.sendMessage("You already have a higher tier donator rank!");
//				} else {
//					player.setRights(PlayerRights.EXTREME_DONATOR);
//					player.getInventory().delete(12423,	1);
//					PlayerPanel.refreshPanel(player);
//					World.sendMessage("<shad=0>@bla@[@blu@Extreme Donator@bla@] @blu@"+player.getUsername()+" @bla@ Has just claimed his @blu@extreme donator rank@bla@!");
//				}
//				break;
//				case 12424:
//				if(player.getRights() == PlayerRights.SPONSOR_DONATOR || player.getRights() == PlayerRights.EXECUTIVE_DONATOR || player.getRights() == PlayerRights.DIVINE_DONATOR){
//					player.sendMessage("You already have a higher tier donator rank!");
//				} else {
//					player.setRights(PlayerRights.SPONSOR_DONATOR);
//					player.getInventory().delete(12424,	1);
//					PlayerPanel.refreshPanel(player);
//					World.sendMessage("<shad=0>@bla@[@mag@Sponsor Donator@bla@] @mag@"+player.getUsername()+" @bla@ Has just claimed his @mag@Sponsor donator rank@bla@!");
//				}
//				break;
//
//				case 12425:
//				if(player.getRights() == PlayerRights.EXECUTIVE_DONATOR){
//					player.sendMessage("You already have a higher tier donator rank!");
//				} else {
//					player.setRights(PlayerRights.EXECUTIVE_DONATOR);
//					player.getInventory().delete(12425,	1);
//					player.getPacketSender().sendMessage("@red@ You now Have acces to ::Exzone!");
//					player.getInventory().add(3818, 1);
//					PlayerPanel.refreshPanel(player);
//					World.sendMessage("<shad=0>@bla@[@bla@Executive Donator@bla@] @bla@"+player.getUsername()+" @bla@ Has just claimed his @bla@Executive donator rank@bla@!");
//				}
//				break;

			// SLAYER HELMET UPGRADES //
					case 13263: //NORMAL UPGRADE TO LIME
								if(player.getInventory().contains(13263) && player.getInventory().getAmount(11320) >= 1250 && player.getInventory().getAmount(11316) >= 2500) {
									player.getInventory().delete(13263, 1).delete(11320, 1250).delete(11316, 2500).add(11546, 1);
									player.getPacketSender().sendMessage("You have upgraded your Slayer helmet!");
								} else {
									player.getPacketSender().sendMessage("You need a Slayer helmet and");
									player.getPacketSender().sendMessage("2500 Monster fragments and 1250 Boss fragments to upgrade your Slayer helmet.");
								}
								break;
					case 11546: //LIME UPGRADE TO AQUA
								if(player.getInventory().contains(11546) && player.getInventory().getAmount(11320) >= 1250 && player.getInventory().getAmount(11316) >= 2500) {
									player.getInventory().delete(11546, 1).delete(11320, 1250).delete(11316, 2500).add(11547, 1);
									player.getPacketSender().sendMessage("You have upgraded your Slayer helmet!");
								} else {
									player.getPacketSender().sendMessage("You need a Lime Slayer helmet and");
									player.getPacketSender().sendMessage("2500 Monster fragments and 1250 Boss fragments to upgrade your Slayer helmet.");
								}
								break;
					case 11547: //AQUA UPGRADE TO CORRUPT
								if(player.getInventory().contains(11547) && player.getInventory().getAmount(11320) >= 1250 && player.getInventory().getAmount(11316) >= 2500) {
									player.getInventory().delete(11547, 1).delete(11320, 1250).delete(11316, 2500).add(11548, 1);
									player.getPacketSender().sendMessage("You have upgraded your Slayer helmet!");
								} else {
									player.getPacketSender().sendMessage("You need an Aqua Slayer helmet and");
									player.getPacketSender().sendMessage("2500 Monster fragments and 1250 Boss fragments to upgrade your Slayer helmet.");
								}
								break;
					case 11548: //CORRUPT UPGRADE TO BLESSED
								if(player.getInventory().contains(11548) && player.getInventory().getAmount(11320) >= 1250 && player.getInventory().getAmount(11316) >= 2500) {
									player.getInventory().delete(11548, 1).delete(11320, 3000).delete(11316, 5000).add(11550, 1);
									player.getPacketSender().sendMessage("You have upgraded your Slayer helmet!");
								} else {
									player.getPacketSender().sendMessage("You need a Corrupt Slayer helmet and");
									player.getPacketSender().sendMessage("5000 Monster fragments and 3000 Boss fragments to upgrade your Slayer helmet.");
								}
								break;
					case 11550: //CORRUPT UPGRADE TO BLESSED
								if(player.getInventory().contains(11550) && player.getInventory().getAmount(11320) >= 1250 && player.getInventory().getAmount(11316) >= 2500) {
									player.getInventory().delete(11550, 1).delete(11320, 5000).delete(11316, 10000).add(11549, 1);
									player.getPacketSender().sendMessage("You have upgraded your Slayer helmet!");
								} else {
									player.getPacketSender().sendMessage("You need a Blessed Slayer helmet and");
									player.getPacketSender().sendMessage("10000 Monster fragments and 5000 Boss fragments to upgrade your Slayer helmet.");
								}
								break;					
								//END//
								//AMULET OF INSANITY//
					case 19886: //DIRE WOLF UPGRADE TO INSANITY
						if(player.getInventory().contains(19886) && player.getInventory().getAmount(11320) >= 15000 && player.getInventory().getAmount(11316) >= 25000) {
							player.getInventory().delete(19886, 1).delete(11320, 1250).delete(11316, 2500).add(11422, 1);
							player.getPacketSender().sendMessage("You have upgraded your Dire Wolf necklace.");
						} else {
							player.getPacketSender().sendMessage("You need a Dire Wolf necklace and");
							player.getPacketSender().sendMessage("25000 Monster fragments and 15000 Boss fragments to upgrade your Barrows gloves.");
						}
						break;
								//END//
								//GLOVES UPGRADE//
					case 7462: //BARROWS UPGRADE TO BLESSED
						if(player.getInventory().contains(7462) && player.getInventory().getAmount(11320) >= 1250 && player.getInventory().getAmount(11316) >= 2500) {
							player.getInventory().delete(7462, 1).delete(11320, 1250).delete(11316, 2500).add(11552, 1);
							player.getPacketSender().sendMessage("You have upgraded your Barrows gloves");
						} else {
							player.getPacketSender().sendMessage("You need a pair of Barrows gloves and");
							player.getPacketSender().sendMessage("2500 Monster fragments and 1250 Boss fragments to upgrade your Barrows gloves.");
						}
						break;
			case 11552: //BLESSED UPGRADE TO DEATH
						if(player.getInventory().contains(11552) && player.getInventory().getAmount(11320) >= 1250 && player.getInventory().getAmount(11316) >= 2500) {
							player.getInventory().delete(11552, 1).delete(11320, 2200).delete(11316, 5000).add(11553, 1);
							player.getPacketSender().sendMessage("You have upgraded your Blessed gloves!");
						} else {
							player.getPacketSender().sendMessage("You need a pair of Blessed gloves and");
							player.getPacketSender().sendMessage("5000 Monster fragments and 2200 Boss fragments to upgrade your Blessed gloves.");
						}
						break;			
								//END//
						// RING UPGRADES//
			case 6737: //BERSERKER UPGRADE TO CHAOS
						if(player.getInventory().contains(6737) && player.getInventory().getAmount(11320) >= 1250 && player.getInventory().getAmount(11316) >= 2500) {
							player.getInventory().delete(6737, 1).delete(11320, 1250).delete(11316, 2500).add(11555, 1);
							player.getPacketSender().sendMessage("You have upgraded your Berserker Ring");
						} else {
							player.getPacketSender().sendMessage("You need a pair of Berserker Ring and");
							player.getPacketSender().sendMessage("2500 Monster fragments and 1250 Boss fragments to upgrade your Berserker Ring.");
						}
						break;
			case 11555: //CHAOS UPGRADE TO DEATH
						if(player.getInventory().contains(11555) && player.getInventory().getAmount(11320) >= 1250 && player.getInventory().getAmount(11316) >= 2500) {
							player.getInventory().delete(11555, 1).delete(11320, 1250).delete(11316, 2500).add(11556, 1);
							player.getPacketSender().sendMessage("You have upgraded your Ring of Chaos!");
						} else {
							player.getPacketSender().sendMessage("You need a Ring of Chaos and");
							player.getPacketSender().sendMessage("2500 Monster fragments and 1250 Boss fragments to upgrade your Ring of Chaos.");
						}
						break;
						//END//
		case 2572:
			if(player.getInventory().contains(2572) && player.getInventory().getAmount(11425) >= 1) {
				final int 
						row = 2572, 
						upgradeMaterial = 11425, 
						potentialResult = 11527;
				
				final int random = Misc.getRandom(2);
				
				player.getInventory()
						.delete(row, 1)
						.delete(upgradeMaterial, 1)
						.refreshItems();
				
				if (random == 2) {
					player.getInventory()
							.add(potentialResult, 1)
							.refreshItems();
					player.getPacketSender().sendMessage("You have upgraded your Ring of wealth!");
				} else
					player.getPacketSender().sendMessage("You have failed to upgrade your Ring of wealth!");
			} else {
				player.getPacketSender().sendMessage("You need a Ring of wealth and a Scroll of efficiency to upgrade your Ring of wealth!");
			}
			break;
		case 11527:
			if(player.getInventory().contains(11527) && player.getInventory().getAmount(11425) >= 1) {
				final int 
						row = 11527, 
						upgradeMaterial = 11425, 
						potentialResult = 11529;
				
				final int random = Misc.getRandom(2);
				
				player.getInventory()
						.delete(row, 1)
						.delete(upgradeMaterial, 1)
						.refreshItems();
				
				if (random == 2) {
					player.getInventory()
							.add(potentialResult, 1)
							.refreshItems();
					player.getPacketSender().sendMessage("You have upgraded your Ring of wealth [1]!");
				} else
					player.getPacketSender().sendMessage("You have failed to upgrade your Ring of wealth [1]!");
			} else {
				player.getPacketSender().sendMessage("You need a Ring of wealth [1] and a Scroll of efficiency to upgrade your Ring of wealth [1]!");
			}
			break;

		case 11529:
			if(player.getInventory().contains(11529) && player.getInventory().getAmount(11425) >= 1) {
				final int 
						row = 11529, 
						upgradeMaterial = 11425, 
						potentialResult = 11531;
				
				final int random = Misc.getRandom(2);
				
				player.getInventory()
						.delete(row, 1)
						.delete(upgradeMaterial, 1)
						.refreshItems();
				
				if (random == 2) {
					player.getInventory()
							.add(potentialResult, 1)
							.refreshItems();
					player.getPacketSender().sendMessage("You have upgraded your Ring of wealth [2]!");
				} else
					player.getPacketSender().sendMessage("You have failed to upgrade your Ring of wealth [2]!");
			} else {
				player.getPacketSender().sendMessage("You need a Ring of wealth [2] and a Scroll of efficiency to upgrade your Ring of wealth [2]!");
			}
			break;
		case 11531:
			if(player.getInventory().contains(11531) && player.getInventory().getAmount(11425) >= 1) {
				final int 
						row = 11531, 
						upgradeMaterial = 11425, 
						potentialResult = 11533;
				
				final int random = Misc.getRandom(2);
				
				player.getInventory()
						.delete(row, 1)
						.delete(upgradeMaterial, 1)
						.refreshItems();
				
				if (random == 2) {
					player.getInventory()
							.add(potentialResult, 1)
							.refreshItems();
					player.getPacketSender().sendMessage("You have upgraded your Ring of wealth [3]!");
				} else
					player.getPacketSender().sendMessage("You have failed to upgrade your Ring of wealth [3]!");
			} else {
				player.getPacketSender().sendMessage("You need a Ring of wealth [3] and a Scroll of efficiency to upgrade your Ring of wealth [3]!");
			}
			break;
		case 15262:
			if(!player.getClickDelay().elapsed(1300))
				return;
			int amt = player.getInventory().getAmount(15262);
			if(amt > 0)
				player.getInventory().delete(15262, amt).add(18016, 10000 * amt);
			player.getClickDelay().reset();
			break;
		case 5509:
		case 5510:
		case 5512:
			RunecraftingPouches.empty(player, RunecraftingPouch.forId(itemId));
			break;
		case 11283: //DFS
			player.getPacketSender().sendMessage("Your Dragonfire shield has "+player.getDfsCharges()+"/20 dragon-fire charges.");
			break;
		case 11613: //dkite
			player.getPacketSender().sendMessage("Your Dragonfire shield has "+player.getDfsCharges()+"/20 dragon-fire charges.");
			break;
		}
	}

	@Override
	public void handleMessage(Player player, Packet packet) {
		if (player.getConstitution() <= 0)
			return;
		switch (packet.getOpcode()) {
		case SECOND_ITEM_ACTION_OPCODE:
			secondAction(player, packet);
			break;
		case FIRST_ITEM_ACTION_OPCODE:
			firstAction(player, packet);
			break;
		case THIRD_ITEM_ACTION_OPCODE:
			thirdClickAction(player, packet);
			break;
		}
	}

	public static final int SECOND_ITEM_ACTION_OPCODE = 75;

	public static final int FIRST_ITEM_ACTION_OPCODE = 122;

	public static final int THIRD_ITEM_ACTION_OPCODE = 16;

}