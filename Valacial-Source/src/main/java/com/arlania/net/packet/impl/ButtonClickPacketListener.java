package com.arlania.net.packet.impl;

import com.arlania.GameSettings;
import com.arlania.model.GameMode;
import com.arlania.model.Locations.Location;
import com.arlania.model.PlayerRights;
import com.arlania.model.Position;
import com.arlania.model.container.impl.Bank;
import com.arlania.model.container.impl.Bank.BankSearchAttributes;
import com.arlania.model.definitions.NPCDrops;
import com.arlania.model.definitions.NpcDefinition;
import com.arlania.model.definitions.WeaponInterfaces.WeaponInterface;
import com.arlania.model.input.impl.EnterClanChatToJoin;
import com.arlania.model.input.impl.EnterSyntaxToBankSearchFor;
import com.arlania.model.input.impl.ItemSearch;
import com.arlania.model.input.impl.PosInput;
import com.arlania.net.packet.Packet;
import com.arlania.net.packet.PacketListener;
import com.arlania.rewardslist.RewardsHandler;
import com.arlania.util.Misc;
import com.arlania.world.World;
import com.arlania.world.content.*;
import com.arlania.world.content.Sounds.Sound;
import com.arlania.world.content.achievements.AchievementInterface;
import com.arlania.world.content.clan.ClanChat;
import com.arlania.world.content.clan.ClanChatManager;
import com.arlania.world.content.collectionlog.CollectionLog;
import com.arlania.world.content.combat.magic.Autocasting;
import com.arlania.world.content.combat.magic.MagicSpells;
import com.arlania.world.content.combat.prayer.CurseHandler;
import com.arlania.world.content.combat.prayer.PrayerHandler;
import com.arlania.world.content.combat.weapon.CombatSpecial;
import com.arlania.world.content.combat.weapon.FightType;
import com.arlania.world.content.daily_reward.DailyReward;
import com.arlania.world.content.daily_reward.DailyRewardConstants;
import com.arlania.world.content.dialogue.DialogueManager;
import com.arlania.world.content.dialogue.DialogueOptions;
import com.arlania.world.content.droppreview.*;
import com.arlania.world.content.grandexchange.GrandExchange;
import com.arlania.world.content.minigames.impl.Dueling;
import com.arlania.world.content.minigames.impl.Nomad;
import com.arlania.world.content.minigames.impl.PestControl;
import com.arlania.world.content.minigames.impl.RecipeForDisaster;
import com.arlania.world.content.mysteryboxes.*;
import com.arlania.world.content.scratchcards.ScratchCard;
import com.arlania.world.content.skill.ChatboxInterfaceSkillAction;
import com.arlania.world.content.skill.impl.construction.Construction;
import com.arlania.world.content.skill.impl.crafting.LeatherMaking;
import com.arlania.world.content.skill.impl.crafting.Tanning;
import com.arlania.world.content.skill.impl.dungeoneering.Dungeoneering;
import com.arlania.world.content.skill.impl.dungeoneering.DungeoneeringParty;
import com.arlania.world.content.skill.impl.dungeoneering.ItemBinding;
import com.arlania.world.content.skill.impl.fletching.Fletching;
import com.arlania.world.content.skill.impl.herblore.IngridientsBook;
import com.arlania.world.content.skill.impl.slayer.Slayer;
import com.arlania.world.content.skill.impl.smithing.SmithingData;
import com.arlania.world.content.skill.impl.summoning.PouchMaking;
import com.arlania.world.content.skill.impl.summoning.SummoningTab;
import com.arlania.world.content.teleportation.Teleporting;
import com.arlania.world.content.transportation.TeleportHandler;
import com.arlania.world.content.transportation.TeleportType;
import com.arlania.world.content.upgrading.UpgradeData;
import com.arlania.world.content.upgrading.UpgradeListener;
import com.arlania.world.entity.impl.player.Player;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.arlania.world.content.TeleportHandlerNew.openInterface;

/**
 * This packet listener manages a button that the player has clicked upon.
 * 
 * @author Gabriel Hannason
 */

public class ButtonClickPacketListener implements PacketListener {

	@Override
	public void handleMessage(Player player, Packet packet) {

		int id = packet.readShort();
		if (player.requiresPin())
			return;
		if (player.getRights() == PlayerRights.DEVELOPER || player.getRights() == PlayerRights.OWNER) {
			player.getPacketSender().sendMessage("Clicked button: " + id);
		}

		if (checkHandlers(player, id))
			return;

		if (id >= 32623 && id <= 32722) {
            player.getPlayerOwnedShopManager().handleButton(id);
        }
		
		if (player.getGoodieBagSponsor().handleClick(id))
		return;
		
		if (player.getGoodieBagbfg9000().handleClick(id))
			return;
		if (player.getGoodiebagEternal().handleClick(id))
			return;
		if (player.getGoodieBagtrivia().handleClick(id))
			return;

		if (id >= 32410 && id <= 32460) {
			StaffList.handleButton(player, id);
		}

		if (CollectionLog.clickButton(player, id)) {
			return;
		}

		if (id == DailyRewardConstants.CLAIM_BUTTON) {
			player.getDailyReward().claimReward();
			return;
		}

		if(id >= -19635 && id <= -19606){
			int test = id+19635;
			
			if((test+1) > player.teleports.size()) {
				player.getPacketSender().sendMessage("This teleport is empty!");
			} else {
				TeleportHandlerNew.buildInterface(test, player,player.tier);
			}
		}

		if(new RewardsHandler(player).button(id)) {
			return;
		}
        if( id >= -27885 && id <= -27719) {

            int base_button = -27885;
            int modified_button = (base_button - id);
            int index = Math.abs(modified_button);

            if(!player.getClickDelay().elapsed(600)) {
                return;
            }
            player.getClickDelay().reset();
            DropLookup.display(player, NPCDrops.forId(player.npcDrops.get(index)));
            return;
        }
		switch (id) {
		
//			case -8254:
//				player.getPacketSender().sendString(1, "www.venexps.org/store");
//				player.getPacketSender().sendMessage("Attempting to open the store");
//				break;

			case -28054:
				PlayersOnlineInterface.showInterface(player);
				PlayersOnlineInterface.updateInterface(player,PlayersOnlineInterface.getPlayer(player));
				break;

			case -8384:
				if (System.currentTimeMillis() - player.getGambling().lastAction <= 300)
					return;
				player.getGambling().lastAction = System.currentTimeMillis();
				if (player.getGambling().inGamble() && player.getGambling().getGamblingMode() != null) {
					player.getGambling().acceptGamble(1);
				} else {
					player.sendMessage("@red@Game mode not set, set 1 to play");
				}
//                System.out.println("In gamble: " + player.getGambling().inGamble());
				break;
			case -8383:
				if (player.getGambling().inGamble())
					player.getGambling().declineGamble(true);
				break;
			case -28051:
				player.getPacketSender().sendInterface(62200);
				for (int i = 0; i < UpgradeData.itemList.length; i++)
					player.getPacketSender().sendItemOnInterface(62209, UpgradeData.itemList[i], i, 1);
				break;
			case -28060:
				player.getPacketSender().sendString(1, "https://valacal317.everythingrs.com/services/store");
				player.getPacketSender().sendMessage("Attempting to open: Donator Store!");
				break;

			case -28057:
				if(player.getSlayer().getSlayerTask().getNpcId() == -1) {
					player.sendMessage("<shad=0>@red@You dont have a slayer task!");
					return;
				}
				player.sendMessage("<shad=0>@or1@You have @red@"+player.getSlayer().getAmountToSlay()+" "+ NpcDefinition.forId(player.getSlayer().getSlayerTask().getNpcId()).getName()+"'s @or1@left to kill!");
				break;
			case -19703:
				int amount1 = player.getInventory().getAmount(6199);
				if (amount1 >= 1) {
					player.getPacketSender().sendMessage("You cannot do this at the moment.");
					return;
				}
				if(player.getLocation() != null && player.getLocation() == Location.WILDERNESS) {
					player.getPacketSender().sendMessage("You cannot do this at the moment.");
					return;
				}
				player.getPacketSender().sendInterfaceRemoval();

				Position position = player.teleports.get(player.teleportIndex).getPosition();
				TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);
				player.getPacketSender().sendMessage("Teleporting!");
				break;

			case 20228:

				player.getPlayerOwnedShopManager().options();
				break;
			case 20232:
			case -16956:
//				player.getDonationDeals().displayReward();
//				player.getDonationDeals().displayTime();
				player.getPacketSender().sendMessage("Deals are located on ::store");

				break;
			case -16952:
				player.getPacketSender().sendString(1, "https://ImaginePS.org/pvm-guide.html");
				player.getPacketSender().sendMessage("Attempting to open: Starter Guide!");
				break;
			case -16948:
				player.getPacketSender().sendString(1, "https://ImaginePS.org/price-guide.html");
				player.getPacketSender().sendMessage("Attempting to open: Starter Guide!");
				break;
			case -16955:
					player.getPacketSender().sendString(1, "https://ImaginePS.org/store/?category=2");
					player.getPacketSender().sendMessage("Attempting to open: Donation Shop!");
					break;
			case -17492:
				player.getPA().sendInterfaceRemoval();
				break;
		      case -27931:
	                player.setInputHandling(new ItemSearch());
	                player.getPacketSender().sendEnterInputPrompt("Enter an Item name, or part of it.");
	                break;
	        case -27928:
                player.setInputHandling(new NpcSearch());
                player.getPacketSender().sendEnterInputPrompt("Enter an Npc name, or part of it.");
                break;
			case -17500:
				//lol kk sec
				if (player.getInventory().contains(player.getMysteryBoxOpener().getOpenBox())) { // example for mbox with random data.
					player.getMysteryBoxOpener().open(player.getMysteryBoxOpener().getOpenBox());
				}
				
			case -17497: // example for mbox with random data. - open all
				if (player.getGameMode() == GameMode.IRONMAN ) {
					player.sendMessage("@red@As an Ultimate ironman you can't do this.");
					return;
				}
				if (player.getInventory().contains(player.getMysteryBoxOpener().getOpenBox())) {
					player.sendMessage("@blu@Your rewards have been added to your bank."); // done. ok i gtg, pm me if u need more work done, btw u still need that toolbelt?

					player.getMysteryBoxOpener().openAll(player.getMysteryBoxOpener().getOpenBox());
				}
				
				break;

		/** UPGRADING **/
		 case -3334://ooh you have 2 same interfaces i think lemme check
		        /*** LIVE ***/
		        	if (!player.getClickDelay().elapsed(3000)) {
		        		player.sendMessage("@red@Please wait a few secounds before trying to upgrade again.");
		        		return;
		        		}
		        		new UpgradeListener(player).upgrade();
		        		player.getClickDelay().reset();
		        /*** DISABLE ***/
		        	/*player.getPA().sendMessage("@red@Upgrading is currently disabled while all of the servers,");
		    		player.getPA().sendMessage("@red@items are added to the Machine. Thank you for your paitence.");
		        	player.getPacketSender().sendInterfaceRemoval();*/
		        	break;
		        case -3306:
				case -8255:
		        	player.getPacketSender().sendInterfaceRemoval();
		        	break;//dont control please
				case -16263:
			        for (int i2 = 1; i2 <= 20; i2++) {
						if(player.getLastgoodiebox() == 3576) {
					        player.getPacketSender().resetItemsOnInterface(49270, 20);
					        player.getGoodieBagSponsor().shuffle(player.getGoodieBagSponsor().rewards);
					        player.getGoodieBagSponsor().claimed = false;
					        player.selectedGoodieBag = -1;
				            player.getPacketSender().sendString(49232 + i2, String.valueOf(i2));
						}
						if(player.getLastgoodiebox() == 3569) {
								player.getPacketSender().resetItemsOnInterface(49270, 20);
								player.getGoodieBagbfg9000().shuffle(player.getGoodieBagbfg9000().rewards);
								player.getGoodieBagbfg9000().claimed = false;
								player.selectedGoodieBag = -1;
								player.getPacketSender().sendString(49232 + i2, String.valueOf(i2));
						}
						if(player.getLastgoodiebox() == 3570) {
							player.getPacketSender().resetItemsOnInterface(49270, 20);
							player.getGoodiebagEternal().shuffle(player.getGoodiebagEternal().rewards);
							player.getGoodiebagEternal().claimed = false;
							player.selectedGoodieBag = -1;
							player.getPacketSender().sendString(49232 + i2, String.valueOf(i2));
						}
						if(player.getLastgoodiebox() == 8989) {
					        player.getPacketSender().resetItemsOnInterface(49270, 20);
					        player.getGoodieBagtrivia().shuffle(player.getGoodieBagtrivia().rewards);
					        player.getGoodieBagtrivia().claimed = false;
					        player.selectedGoodieBag = -1;
				            player.getPacketSender().sendString(49232 + i2, String.valueOf(i2));
							}
			        }
			            break;
				case -16332:
					if(player.getLastgoodiebox() == 3576) {
						player.getGoodieBagSponsor().claim();
						}
						if(player.getLastgoodiebox() == 3569) {
						player.getGoodieBagbfg9000().claim();
						}
					if(player.getLastgoodiebox() == 3570) {
						player.getGoodiebagEternal().claim();
					}
						if(player.getLastgoodiebox() == 8989) {
						player.getGoodieBagtrivia().claim();
						}
					break;
		        case -3530:
		            player.getPacketSender().sendInterfaceRemoval();
		            player.getPlayerOwnedShopManager().open();
		            player.getPacketSender().sendMessage("You have refreshed all shops.");
		            break;
				case -3536:
		            player.getPlayerOwnedShopManager().claimEarnings();
		            break;
				case -31929:
					player.getPacketSender().sendInterfaceRemoval();
					break;
			    case -534:
		            player.getPlayerOwnedShopManager().openEditor();
		            break;
		        case -530:
		            player.getPlayerOwnedShopManager().claimEarnings();
		            break;
		        case 32602:
					player.setInputHandling(new PosInput());
					player.getPacketSender().sendEnterInputPrompt("What/Who would you like to search for?");
					break;
		        case 32606:
		        	player.getPacketSender().sendInterfaceRemoval();
					break;
		        case -3533:
		            //player.getPacketSender().sendInterfaceRemoval();
		            player.getPlayerOwnedShopManager().openEditor();
		            break;
		        case -31925://wizzard
		            //player.getPacketSender().sendInterfaceRemoval();
		            player.getPlayerOwnedShopManager().open();
		            break;
		case -26218:
		case -26215:
		case -26209:
		case -26212:
			new ScratchCard(player).reveal(id);
			break;


			case -18529:

				if(player.getBox() == 6199) {
					int amount = player.getInventory().getAmount(6199);
					if (amount >= 1) {
						MysteryBox mysteryBox = new MysteryBox(player);
						mysteryBox.process();
					}
				}
					if(player.getBox() == 7102) {
					int amount = player.getInventory().getAmount(7102);
					if(amount >= 1) {
						YodaBox mysteryBox = new YodaBox(player);
						mysteryBox.process();
					}
				}
					if(player.getBox() == 7104) {
					int amount = player.getInventory().getAmount(7104);
					if(amount >= 1) {
						GodzillaBox mysteryBox = new GodzillaBox(player);
						mysteryBox.process();
					}
				}
				if(player.getBox() == 7124) {
					int amount = player.getInventory().getAmount(7124);
					if(amount >= 1) {
						OwnerBox mysteryBox = new OwnerBox(player);
						mysteryBox.process();
					}
				}
				if(player.getBox() == 3230) {
					int amount = player.getInventory().getAmount(3230);
					if(amount >= 1) {
						OwnerBox mysteryBox = new OwnerBox(player);
						mysteryBox.process();
					}
				}
					if(player.getBox() == 7106) {
					int amount = player.getInventory().getAmount(7106);
					if(amount >= 1) {
						AssasinBox mysteryBox = new AssasinBox(player);
						mysteryBox.process();
					}
				}
					if(player.getBox() == 7108) {
					int amount = player.getInventory().getAmount(7108);
					if(amount >= 1) {
						DonkeyBox mysteryBox = new DonkeyBox(player);
						mysteryBox.process();
					}
				}
					if(player.getBox() == 7110) {
					int amount = player.getInventory().getAmount(7110);
					if(amount >= 1) {
						OblivionBox mysteryBox = new OblivionBox(player);
						mysteryBox.process();
					}
				}
					if(player.getBox() == 7112) {
					int amount = player.getInventory().getAmount(7112);
					if(amount >= 1) {
						BandosBox mysteryBox = new BandosBox(player);
						mysteryBox.process();
					}
				}
					if(player.getBox() == 7114) {
					int amount = player.getInventory().getAmount(7114);
					if(amount >= 1) {
						AbbadonBox mysteryBox = new AbbadonBox(player);
						mysteryBox.process();
					}
				}
					if(player.getBox() == 7116) {
					int amount = player.getInventory().getAmount(7116);
					if(amount >= 1) {
						InfernalGroudonBox mysteryBox = new InfernalGroudonBox(player);
						mysteryBox.process();
					}
				}
					if(player.getBox() == 7118) {
					int amount = player.getInventory().getAmount(7118);
					if(amount >= 1) {
						DonatorBox mysteryBox = new DonatorBox(player);
						mysteryBox.process();
					}
				}
					if(player.getBox() == 7120) {
					int amount = player.getInventory().getAmount(7120);
					if(amount >= 1) {
						NeocortexBox mysteryBox = new NeocortexBox(player);
						mysteryBox.process();
					}
				}
//				if(player.getBox() == 3230) {
//					int amount = player.getInventory().getAmount(3230);
//					if(amount >= 1) {
//						Launch mysteryBox = new NeocortexBox(player);
//						mysteryBox.process();
//					}
//				}
				if(player.getBox() == 7124) {
					int amount = player.getInventory().getAmount(7124);
					if(amount >= 1) {
					}

				}if(player.getBox() == 7122) {
					int amount = player.getInventory().getAmount(7122);
					if(amount >= 1) {
						spiderBox mysteryBox = new spiderBox(player);
						mysteryBox.process();
					}
				}
				break;


				//all    !!@#$@$@
				case -18524:
				if(player.getBox() == 6199) {
					int amount = player.getInventory().getAmount(6199);
					for(int i = 0; i < amount; i++) {
						if(player.getInventory().getFreeSlots() == 0) {
							player.getPacketSender().sendMessage("You need more inventory spaces!");
							return;
						}
						MysteryBox mysteryBox = new MysteryBox(player);
						mysteryBox.process();
					}

				}
				if(player.getBox() == 7102) {
					int amount = player.getInventory().getAmount(7102);
					for(int i = 0; i < amount; i++) {
						if(player.getInventory().getFreeSlots() == 0) {
							player.getPacketSender().sendMessage("You need more inventory spaces!");
							return;
						}
						YodaBox mysteryBox = new YodaBox(player);
						mysteryBox.process();
					}

				}if(player.getBox() == 7104) {
					int amount = player.getInventory().getAmount(7104);
					for(int i = 0; i < amount; i++) {
						if(player.getInventory().getFreeSlots() == 0) {
							player.getPacketSender().sendMessage("You need more inventory spaces!");
							return;
						}
						GodzillaBox mysteryBox = new GodzillaBox(player);
						mysteryBox.process();
					}
				}if(player.getBox() == 7124) {
					int amount = player.getInventory().getAmount(7124);
					for(int i = 0; i < amount; i++) {
						if(player.getInventory().getFreeSlots() == 0) {
							player.getPacketSender().sendMessage("You need more inventory spaces!");
							return;
						}
						OwnerBox mysteryBox = new OwnerBox(player);
						mysteryBox.process();
					}
				}if(player.getBox() == 7106) {
					int amount = player.getInventory().getAmount(7106);
					for(int i = 0; i < amount; i++) {
						if(player.getInventory().getFreeSlots() == 0) {
							player.getPacketSender().sendMessage("You need more inventory spaces!");
							return;
						}
						AssasinBox mysteryBox = new AssasinBox(player);
						mysteryBox.process();
					}
				}if(player.getBox() == 7108) {
					int amount = player.getInventory().getAmount(7108);
					for(int i = 0; i < amount; i++) {
						if(player.getInventory().getFreeSlots() == 0) {
							player.getPacketSender().sendMessage("You need more inventory spaces!");
							return;
						}
						DonkeyBox mysteryBox = new DonkeyBox(player);
						mysteryBox.process();
					}
				}if(player.getBox() == 7110) {
					int amount = player.getInventory().getAmount(7110);
					for (int i = 0; i < amount; i++) {
						if (player.getInventory().getFreeSlots() == 0) {
							player.getPacketSender().sendMessage("You need more inventory spaces!");
							return;
						}
						OblivionBox mysteryBox = new OblivionBox(player);
						mysteryBox.process();
					}
				}
				if(player.getBox() == 7122) {

					int amount = player.getInventory().getAmount(7122);
					for(int i = 0; i < amount; i++) {
						if (player.getInventory().getFreeSlots() == 0) {
							player.getPacketSender().sendMessage("You need more inventory spaces!");
							return;
						}
						spiderBox mysteryBox = new spiderBox(player);
						mysteryBox.process();
					}
				}if(player.getBox() == 7120) {
					int amount = player.getInventory().getAmount(7120);
					for(int i = 0; i < amount; i++) {
						if(player.getInventory().getFreeSlots() == 0) {
							player.getPacketSender().sendMessage("You need more inventory spaces!");
							return;
						}
						NeocortexBox mysterybox = new NeocortexBox(player);
						mysterybox.process();
					}
				}if(player.getBox() == 7112) {
					int amount = player.getInventory().getAmount(7112);
					for(int i = 0; i < amount; i++) {
						if(player.getInventory().getFreeSlots() == 0) {
							player.getPacketSender().sendMessage("You need more inventory spaces!");
							return;
						}
						BandosBox mysteryBox = new BandosBox(player);
						mysteryBox.process();
					}
				}if(player.getBox() == 7114) {
					int amount = player.getInventory().getAmount(7114);
					for(int i = 0; i < amount; i++) {
						if(player.getInventory().getFreeSlots() == 0) {
							player.getPacketSender().sendMessage("You need more inventory spaces!");
							return;
						}
						AbbadonBox mysteryBox = new AbbadonBox(player);
						mysteryBox.process();
					}
				}if(player.getBox() == 7116) {
					int amount = player.getInventory().getAmount(7116);
					for(int i = 0; i < amount; i++) {
						if(player.getInventory().getFreeSlots() == 0) {
							player.getPacketSender().sendMessage("You need more inventory spaces!");
							return;
						}
						InfernalGroudonBox mysteryBox = new InfernalGroudonBox(player);
						mysteryBox.process();
					}
				}if(player.getBox() == 7118) {
					int amount = player.getInventory().getAmount(7118);
					for(int i = 0; i < amount; i++) {
						if(player.getInventory().getFreeSlots() == 0) {
							player.getPacketSender().sendMessage("You need more inventory spaces!");
							return;
						}
						DonatorBox mysteryBox = new DonatorBox(player);
						mysteryBox.process();
					}
				}if(player.getBox() == 7124) {
					int amount = player.getInventory().getAmount(7124);
					for(int i = 0; i < amount; i++) {
						if(player.getInventory().getFreeSlots() == 0) {
							player.getPacketSender().sendMessage("You need more inventory spaces!");
							return;
						}
					}
				}

				break;
		case 26113:
			player.dropLogOrder = !player.dropLogOrder;
			if (player.dropLogOrder) {
				player.getPA().sendFrame126(26113, "Oldest to Newest");
			} else {
				player.getPA().sendFrame126(26113, "Newest to Oldest");
			}
			break;
		case -8305:
			player.getPacketSender().sendInterfaceRemoval();
			break;
		case -29031:
			ProfileViewing.rate(player, true);
			break;
		case -29028:
			ProfileViewing.rate(player, false);

        case -17611:
        case -17613:
            player.sendMessage("@red@Coming soon!");
            break;
		case -27454:
		case -27534:
            case -18519:
            case -17606:
		case 5384:
		case 12729:
			player.getPacketSender().sendInterfaceRemoval();
			break;
		case -17631:
			KBD.closeInterface(player);
			break;

		case -17629:
			if (player.getLocation() == Location.KING_BLACK_DRAGON) {
				KBD.nextItem(player);
			}
			if (player.getLocation() == Location.SLASH_BASH) {
				SLASHBASH.nextItem(player);
			}
			if (player.getLocation() == Location.TORM_DEMONS) {
				TDS.nextItem(player);
			}
			if (player.getLocation() == Location.CORPOREAL_BEAST) {
				CORP.nextItem(player);
			}
			if (player.getLocation() == Location.DAGANNOTH_DUNGEON) {
				DAGS.nextItem(player);
			}
			if (player.getLocation() == Location.BANDOS_AVATAR) {
				AVATAR.nextItem(player);
			}
			if (player.getLocation() == Location.KALPHITE_QUEEN) {
				KALPH.nextItem(player);
			}
			if (player.getLocation() == Location.PHOENIX) {
				PHEON.nextItem(player);
			}
			if (player.getLocation() == Location.GLACORS) {
				GLAC.nextItem(player);
			}
			if (player.getLocation() == Location.SKOTIZO) {
				SKOT.nextItem(player);
			}
			if (player.getLocation() == Location.CERBERUS) {
				CERB.nextItem(player);
			}
			if (player.getLocation() == Location.NEX) {
				NEXX.nextItem(player);
			}
			if (player.getLocation() == Location.GODWARS_DUNGEON) {
				GWD.nextItem(player);
			}
			if (player.getLocation() == Location.BORK) {
				BORKS.nextItem(player);
			}
			if (player.getLocation() == Location.LIZARDMAN) {
				LIZARD.nextItem(player);
			}
			if (player.getLocation() == Location.BARRELCHESTS) {
				BARRELS.nextItem(player);
			}
			break;

		case -17630:
			if (player.getLocation() == Location.KING_BLACK_DRAGON) {
				KBD.previousItem(player);
			}
			if (player.getLocation() == Location.SLASH_BASH) {
				SLASHBASH.previousItem(player);
			}
			if (player.getLocation() == Location.TORM_DEMONS) {
				TDS.previousItem(player);
			}
			if (player.getLocation() == Location.CORPOREAL_BEAST) {
				CORP.previousItem(player);
			}
			if (player.getLocation() == Location.DAGANNOTH_DUNGEON) {
				DAGS.previousItem(player);
			}
			if (player.getLocation() == Location.BANDOS_AVATAR) {
				AVATAR.previousItem(player);
			}
			if (player.getLocation() == Location.KALPHITE_QUEEN) {
				KALPH.previousItem(player);
			}
			if (player.getLocation() == Location.PHOENIX) {
				PHEON.previousItem(player);
			}
			if (player.getLocation() == Location.GLACORS) {
				GLAC.previousItem(player);
			}
			if (player.getLocation() == Location.SKOTIZO) {
				SKOT.previousItem(player);
			}
			if (player.getLocation() == Location.CERBERUS) {
				CERB.previousItem(player);
			}
			if (player.getLocation() == Location.NEX) {
				NEXX.previousItem(player);
			}
			if (player.getLocation() == Location.GODWARS_DUNGEON) {
				GWD.previousItem(player);
			}
			if (player.getLocation() == Location.BORK) {
				BORKS.previousItem(player);
			}
			if (player.getLocation() == Location.LIZARDMAN) {
				LIZARD.previousItem(player);
			}
			if (player.getLocation() == Location.BARRELCHESTS) {
				BARRELS.previousItem(player);
			}
			break;

//		case -26373:
//			DropLog.open(player);
//			break;
		case 26614:
			DropLog.open(player);
			break;
		case 26601:
			player.getPacketSender().sendTabInterface(GameSettings.QUESTS_TAB, 46343);
			StaffList.updateInterface(player);
			break;
		case 1036:
			EnergyHandler.rest(player);
			break;
//		case -26376:
			// PlayersOnlineInterface.showInterface(player);
//			break;
		case -26372:
			player.getPacketSender().sendTabInterface(GameSettings.QUESTS_TAB, 46343);
			StaffList.updateInterface(player);
			break;
	       case 32388:
               player.getPacketSender().sendTabInterface(GameSettings.QUESTS_TAB, 26600);
               break;
		case -26359:
			player.setExperienceLocked(!player.experienceLocked());
			player.sendMessage("Your experience is now: "+(player.experienceLocked() ? "locked" : "unlocked"));
			break;
        case 50855:
            player.getPacketSender().sendTabInterface(GameSettings.QUESTS_TAB, 26600)
                    .sendTab(GameSettings.QUESTS_TAB);
            break;
        case 50652:
            player.getPacketSender().sendTabInterface(GameSettings.QUESTS_TAB, 26600)
                    .sendTab(GameSettings.QUESTS_TAB);
            break;
		case 27229:
			DungeoneeringParty.create(player);
			break;
		case 3229:
			player.sendMessage("Common Costs 50 NightRaid Points.");
			break;
		case 3218:
			player.sendMessage("Uncommon Package Costs 100 NightRaid Points.");
			break;
		case 3215:
			player.sendMessage("Extreme Package Costs 200 NightRaid Points.");
			break;
		case 3221:
			player.sendMessage("Rare Package Costs 150 NightRaid Points.");
			break;
		case 3235:
			player.sendMessage("Legendary Package Costs 250 NightRaid Points.");
			break;
		case 3204:
			if(player.getNightRaidPoints() >= 150) {
				 player.getInventory().add(15371, 1);
				 player.incrementNightRaidPoints(150);
				 PlayerPanel.refreshPanel(player);
			}
			break;
		case 3206:
			if(player.getNightRaidPoints() >= 200) {
				 player.getInventory().add(15372, 1);
				 player.incrementNightRaidPoints(200);
				 PlayerPanel.refreshPanel(player);
			} 
			break;
		case 3260:
			player.getPacketSender().sendString(1, "www.venexps.org");
		     player.getPacketSender().sendMessage("Attempting to open: www.venexps.org");
			break;
		case 3208:
			if(player.getNightRaidPoints() >= 100) {
				 player.getInventory().add(15370, 1);
				 player.incrementNightRaidPoints(100);
				 PlayerPanel.refreshPanel(player);
			} 
			break;
		case 3225:
			if(player.getNightRaidPoints() >= 50) {
				 player.getInventory().add(15369, 1);
				 player.incrementNightRaidPoints(50);
				 PlayerPanel.refreshPanel(player);
			} 
			break;
		case 3240:
			if(player.getNightRaidPoints() >= 250) {
				 player.getInventory().add(15373, 1);
				 player.incrementNightRaidPoints(250);
				 PlayerPanel.refreshPanel(player);
			} 
			break;
		case 26226:
		case 26229:
			if (Dungeoneering.doingDungeoneering(player)) {
				DialogueManager.start(player, 114);
				player.setDialogueActionId(71);
			} else {
				System.out.println("70");
				Dungeoneering.leave(player, false, true);
			}
			break;
		case 26244:
		case 26247:
			if (player.getMinigameAttributes().getDungeoneeringAttributes().getParty() != null) {
				if (player.getMinigameAttributes().getDungeoneeringAttributes().getParty().getOwner().getUsername()
						.equals(player.getUsername())) {
					DialogueManager.start(player, id == 26247 ? 106 : 105);
					player.setDialogueActionId(id == 26247 ? 68 : 67);
				} else {
					player.getPacketSender().sendMessage("Only the party owner can change this setting.");
				}
			}
			break;
		case 28180:
			TeleportHandler.teleportPlayer(player, new Position(3666, 2976), player.getSpellbook().getTeleportType());
			break;
		case 14176:
			player.setUntradeableDropItem(null);
			player.getPacketSender().sendInterfaceRemoval();
			break;
		case 14175:
			player.getPacketSender().sendInterfaceRemoval();
			if (player.getUntradeableDropItem() != null
					&& player.getInventory().contains(player.getUntradeableDropItem().getId())) {
				ItemBinding.unbindItem(player, player.getUntradeableDropItem().getId());
				player.getInventory().delete(player.getUntradeableDropItem());
				player.getPacketSender().sendMessage("Your item vanishes as it hits the floor.");
				Sounds.sendSound(player, Sound.DROP_ITEM);
			}
			player.setUntradeableDropItem(null);
			break;
		case 1013:
			player.getSkillManager().setTotalGainedExp(0);
			break;
		case 391:
			if (WellOfGoodwill.isActive()) {
				player.getPacketSender().sendMessage(
						"<img=10> <col=008FB2>The Well of Goodwill is granting 30% bonus experience for another "
								+ WellOfGoodwill.getMinutesRemaining() + " minutes.");
			} else {
				player.getPacketSender()
						.sendMessage("<img=10> <col=008FB2>The Well of Goodwill needs another "
								+ Misc.insertCommasToNumber("" + WellOfGoodwill.getMissingAmount())
								+ " coins before becoming full.");
			}
			break;
		case -26368:
			
			break;
		case -26370:
			KillsTracker.open(player);
			break;
		case 26604:
			KillsTracker.open(player);
			break;
		case -26369:
			DropLog.open(player);
			break;

		case -30281:
			player.getPacketSender().sendInterfaceRemoval();
			break;

		case 28177:
			if (!TeleportHandler.checkReqs(player, null)) {
				player.getSkillManager().stopSkilling();
				return;
			}
			if (!player.getClickDelay().elapsed(4500) || player.getMovementQueue().isLockMovement()) {
				player.getSkillManager().stopSkilling();
				return;
			}
			if (player.getLocation() == Location.CONSTRUCTION) {
				player.getSkillManager().stopSkilling();
				return;
			}
			Construction.newHouse(player);
			Construction.enterHouse(player, player, true, true);
			player.getSkillManager().stopSkilling();
			break;
		case -30282:
			KillsTracker.openBoss(player);
			break;
		case -10283:
			KillsTracker.open(player);
			break;
			   /*
	         * UPGRADE
	         */
	    case 62230:
	    	player.getPacketSender().sendInterfaceRemoval();
	    	break;
		case 11014:
			player.getTeleportInterface().open();
			//Teleporting.openTab(player, -4928);
			break;
//		case -26333:
//			player.getPacketSender().sendString(1, "www.runeunity.org/forum");
//			player.getPacketSender().sendMessage("Attempting to open: www.runeunity.org/forum");
//			break;

			//quest   26600
			case 26616:
			case -14681:
			case -14884:
				player.questTabInterfaceId = 26600;

				player.getPacketSender().sendTabInterface(GameSettings.QUESTS_TAB, 26600)
						.sendTab(GameSettings.QUESTS_TAB);
				break;


			//quest 2  50850
       		 case 26610:
			case -14678:
			case -14881:
				player.questTabInterfaceId = 50850;
            player.getPacketSender().sendTabInterface(GameSettings.QUESTS_TAB, 50850)
                    .sendTab(GameSettings.QUESTS_TAB);
            break;


            //quest 3  50650

			case -14878:
				player.questTabInterfaceId = 50650;
				player.getPacketSender().sendTabInterface(GameSettings.QUESTS_TAB, 50650)
						.sendTab(GameSettings.QUESTS_TAB);
				break;
			case 26613:
			case -14684:
				//AchievementInterface.open(player);
				player.getPacketSender().sendMessage("Achievements will be released February 28th, 2021 at 2PM EST!");

				return;

			case -8299:
				 if(!player.getClickDelay().elapsed(600)) {
		                return;
		            }
		            player.getClickDelay().reset();
		            
				if(PlayerPunishment.hasDaily(player.getHostAddress())){
					player.getPacketSender().sendMessage("You have already claimed the daily today!");
					return;

				}

				LocalDateTime date = LocalDateTime.of(
						LocalDate.now().plusDays(0),
						LocalTime.of(12, 0)
				);
				PlayerPunishment.addDailyIp(player.getHostAddress());

				player.getInventory().add(DailyRewards.getRewardByMonth(date.getMonth().toString().toLowerCase(),date.getDayOfMonth()));
				break;

        case -10531:
            if (player.isKillsTrackerOpen()) {
                player.setKillsTrackerOpen(false);
                player.getPacketSender().sendTabInterface(GameSettings.QUESTS_TAB, 639);
                PlayerPanel.refreshPanel(player);
            }
            break;
		case -26330:
			player.getPacketSender().sendString(1, "www.venexps.org");
			player.getPacketSender().sendMessage("Attempting to open: www.venexps.org");
			break;
		case -26329:
			player.getPacketSender().sendString(1, "www.venexps.org");
			player.getPacketSender().sendMessage("Attempting to open: www.venexps.org");
			break;
			case -26327:
				player.setTimer(0);
			break;
		case -26328:
			player.getPacketSender().sendString(1, "www.venexps.org");
			player.getPacketSender().sendMessage("Attempting to open: www.venexps.org");
			break;
		case -26331:
			RecipeForDisaster.openQuestLog(player);
			break;
		case -26332:
			Nomad.openQuestLog(player);
			break;
		case -26371:
			ProfileViewing.view(player, player);
			break;
		case 26611:
			ProfileViewing.view(player, player);
			break;
		case 350:
			player.getPacketSender()
					.sendMessage("To autocast a spell, please right-click it and choose the autocast option.")
					.sendTab(GameSettings.MAGIC_TAB).sendConfig(108, player.getAutocastSpell() == null ? 3 : 1);
			break;
		case 12162:
			DialogueManager.start(player, 61);
			player.setDialogueActionId(28);
			break;
		case 29335:
			if (player.getInterfaceId() > 0) {
				player.getPacketSender()
						.sendMessage("Please close the interface you have open before opening another one.");
				return;
			}
			DialogueManager.start(player, 60);
			player.setDialogueActionId(27);
			break;
		case 29455:
			if (player.getInterfaceId() > 0) {
				player.getPacketSender()
						.sendMessage("Please close the interface you have open before opening another one.");
				return;
			}
			ClanChatManager.toggleLootShare(player);
			break;
		case 8658:
			DialogueManager.start(player, 55);
			player.setDialogueActionId(26);
			break;
		case 11001:
		 TeleportHandler.teleportPlayer(player, new Position(3817, 3484), player.getSpellbook().getTeleportType());
			break;
		case 8667:
			TeleportHandler.teleportPlayer(player, new Position(2742, 3443), player.getSpellbook().getTeleportType());
			break;
		case 8672:
			TeleportHandler.teleportPlayer(player, new Position(2595, 4772), player.getSpellbook().getTeleportType());
			player.getPacketSender().sendMessage(
					"<img=10> To get started with Divination, click on the green 'Elder Energy'.");
			break;
		case 8861:
			TeleportHandler.teleportPlayer(player, new Position(2914, 3450), player.getSpellbook().getTeleportType());
			break;
		case 8656:
			player.setDialogueActionId(47);
			DialogueManager.start(player, 86);
			break;
		case 8659:
			TeleportHandler.teleportPlayer(player, new Position(3024, 9741), player.getSpellbook().getTeleportType());
			break;
		case 8664:
			TeleportHandler.teleportPlayer(player, new Position(3094, 3501), player.getSpellbook().getTeleportType());
			break;
		case 8666:
			TeleportHandler.teleportPlayer(player, new Position(3826, 3497), player.getSpellbook().getTeleportType());
			break;
			

		/*
		 * Teleporting Called Below
		 */

		case -4914:
		case -4911:
		case -4908:
		case -4905:
		case -4899:
		case -4896:
		case -4893:
		case -4890:
		case -4845:
		case -4839:
		case -4842:
			Teleporting.teleport(player, id);
			break;
		case -4902:
			if(player.getSummoning().getFamiliar() != null) {
				player.getPacketSender().sendMessage("You must dismiss your familiar before teleporting to the arena!");
		} else {
			Teleporting.teleport(player, id);
		}
		break;


			case -19725:
				openInterface(player,1);
				player.teleportIndexPanel = 1;
			break;
			case -19722:
				openInterface(player,2);
				player.teleportIndexPanel = 2;

				break;
			case -19719:
				openInterface(player,3);
				player.teleportIndexPanel = 3;

				break;
			case -19716:
				openInterface(player,4);
				player.teleportIndexPanel = 4;
				break;
			case -19713:
				openInterface(player,5);
				player.teleportIndexPanel = 4;
				break;

		case 10003:
			player.getTeleportInterface().open();

			break;
		case -13390:
			player.getPacketSender().closeAllWindows();
			break;
			case -16938:
				player.getPacketSender().closeAllWindows();
				break;
			case 20222:
			case -16968:
			player.getPacketSender().sendInterface(37600);
			break;
			
		case 20224:
			case -16964:
			player.getPacketSender().sendInterface(62200);
			for (int i = 0; i < UpgradeData.itemList.length; i++)
				player.getPacketSender().sendItemOnInterface(62209, UpgradeData.itemList[i], i, 1);
		break;
			case -16960:
				if (player.getLocation() == Location.RAID || player.getLocation() == Location.DUEL_ARENA) {
					player.getPacketSender().sendMessage("You can't open the player shops right now!");
				} else {
					player.getPlayerOwnedShopManager().options();
					return;
				}
				break;
		case 20230:
			PlayersOnlineInterface.showInterface(player);
			break;
			
		case 20234:
			player.getDailyReward().openInterface();
			break;
		
		case 20226:
			player.getPacketSender().sendString(1, "https://ImaginePS.org/store/?category=2");
		    player.getPacketSender().sendMessage("Attempting to open: Donator Store!");
			break;
			
		case -4934:
			Teleporting.openTab(player, -4934);
			break;
		case -4931:
			Teleporting.openTab(player, -4931);
			break;
		case -4928:
			Teleporting.openTab(player, -4928);
			break;
		case -4925:
			Teleporting.openTab(player, -4925);
			break;
		case -4922:
			Teleporting.openTab(player, -4922);
			break;
		case -4919:
			Teleporting.openTab(player, -4919);
			break;
			case -16972:
				TeleportHandler.teleportPlayer(player, new Position(3691, 2978), player.getSpellbook().getTeleportType());

				break;
		/*
		 * End Teleporting
		 */
			
		/*case -4845:
			WeaponGame.addToLobby(player);
			player.getPacketSender().sendInterfaceRemoval();
			player.getPacketSender().sendMessage("Weapon Game is currently under development!");

			break;*/
			case -16976:
				TeleportHandler.teleportPlayer(player, new Position(3795, 3534), player.getSpellbook().getTeleportType());
				break;
		case 39318:
			System.err.println("ok");
			break;

		case 8671:
			player.setDialogueActionId(56);
			DialogueManager.start(player, 89);
			break;
		case 8670:
			TeleportHandler.teleportPlayer(player, new Position(2717, 3499), player.getSpellbook().getTeleportType());
			break;
		case 20220:
			player.getCollectionLog().open(player);
			break;
		case 8668:
			TeleportHandler.teleportPlayer(player, new Position(2717, 3499), player.getSpellbook().getTeleportType());
			break;
		case 8665:
			TeleportHandler.teleportPlayer(player, new Position(3079, 3495), player.getSpellbook().getTeleportType());
			break;
		case 8662:
			TeleportHandler.teleportPlayer(player, new Position(2345, 3698), player.getSpellbook().getTeleportType());
			break;
		case 13928:
			TeleportHandler.teleportPlayer(player, new Position(3052, 3304), player.getSpellbook().getTeleportType());
			break;
		case 28179:
			player.getPacketSender().sendMessage("Invention coming soon!");
			break;
		case 28178:
			player.getPacketSender().sendMessage("You have received a spade, dig around!");
			player.getInventory().add(952, 1);
			break;
		case 1159: // Bones to Bananas
		case 15877:// Bones to peaches
		case 30306:
			MagicSpells.handleMagicSpells(player, id);
			break;
		case 10001:
			if (player.getInterfaceId() == -1) {
				Consumables.handleHealAction(player);
			} else {
				player.getPacketSender().sendMessage("You cannot heal yourself right now.");
			}
			break;
		case 18025:
			if (PrayerHandler.isActivated(player, PrayerHandler.AUGURY)) {
				PrayerHandler.deactivatePrayer(player, PrayerHandler.AUGURY);
			} else {
				PrayerHandler.activatePrayer(player, PrayerHandler.AUGURY);
			}
			break;
		case 18018:
			if (PrayerHandler.isActivated(player, PrayerHandler.RIGOUR)) {
				PrayerHandler.deactivatePrayer(player, PrayerHandler.RIGOUR);
			} else {
				PrayerHandler.activatePrayer(player, PrayerHandler.RIGOUR);
			}
			break;
		case 10000:
		case 950:
			if (player.getInterfaceId() < 0)
				player.getPacketSender().sendInterface(40030);
			else
				player.getPacketSender().sendMessage("Please close the interface you have open before doing this.");
			break;
		case 3546:
		case 3420:
			if (System.currentTimeMillis() - player.getTrading().lastAction <= 300)
				return;
			player.getTrading().lastAction = System.currentTimeMillis();
			if (player.getTrading().inTrade()) {
				player.getTrading().acceptTrade(id == 3546 ? 2 : 1);
			} else {
				player.getPacketSender().sendInterfaceRemoval();
			}
			break;
		case 10162:
		case -18269:
		case 11729:
			player.getPacketSender().sendInterfaceRemoval();
			break;
		case 841:
			IngridientsBook.readBook(player, player.getCurrentBookPage() + 2, true);
			//TestBook.readBook(player, player.getCurrentBookPage() + 2, true);
			break;
		case 839:
			IngridientsBook.readBook(player, player.getCurrentBookPage() - 2, true);
			//TestBook.readBook(player, player.getCurrentBookPage() - 2, true);
			break;
		case 14922:
			player.getPacketSender().sendClientRightClickRemoval().sendInterfaceRemoval();
			break;
		case 14921:
			player.getPacketSender().sendMessage("Please visit the forums and ask for help in the support section.");
			break;
		case 5294:
			player.getPacketSender().sendClientRightClickRemoval().sendInterfaceRemoval();
			player.setDialogueActionId(player.getBankPinAttributes().hasBankPin() ? 8 : 7);
			DialogueManager.start(player,
					DialogueManager.getDialogues().get(player.getBankPinAttributes().hasBankPin() ? 12 : 9));
			break;
		case 27653:
			if (!player.busy() && !player.getCombatBuilder().isBeingAttacked()
					&& !Dungeoneering.doingDungeoneering(player)) {
				player.getSkillManager().stopSkilling();
				player.getPriceChecker().open();
			} else {
				player.getPacketSender().sendMessage("You cannot open this right now.");
			}
			break;
		case 2735:
		case 1511:
			if (player.getSummoning().getBeastOfBurden() != null) {
				player.getSummoning().toInventory();
				player.getPacketSender().sendInterfaceRemoval();
			} else {
				player.getPacketSender().sendMessage("You do not have a familiar who can hold items.");
			}
			break;
		case -11501:
		case -11504:
		case -11498:
		case -11507:
		case 1020:
		case 1021:
		case 1019:
		case 1018:
			if (id == 1020 || id == -11504)
				SummoningTab.renewFamiliar(player);
			else if (id == 1019 || id == -11501)
				SummoningTab.callFollower(player);
			else if (id == 1021 || id == -11498)
				SummoningTab.handleDismiss(player, false);
			else if (id == -11507)
				player.getSummoning().store();
			else if (id == 1018)
				player.getSummoning().toInventory();
			break;
		case 11004:
			player.getPacketSender().sendTab(GameSettings.SKILLS_TAB);
			DialogueManager.sendStatement(player, "Simply press on the skill you want to train in the skills tab.");
			player.setDialogueActionId(-1);
			break;
		case 8654:
		case 8657:
		case 8655:
		case 8663:
		case 8669:
		case 8660:
		case 11008:
			player.getTeleportInterface().open();
			break;
		case 11017:
			player.getTeleportInterface().open();
			break;
		case 11011:
			player.getTeleportInterface().open();
			break;
		case 11020:
			player.getTeleportInterface().open();
			break;
		case 2799:
		case 2798:
		case 1747:
		case 1748:
		case 8890:
		case 8886:
		case 8875:
		case 8871:
		case 8894:
			ChatboxInterfaceSkillAction.handleChatboxInterfaceButtons(player, id);
			break;
		case 14873:
		case 14874:
		case 14875:
		case 14876:
		case 14877:
		case 14878:
		case 14879:
		case 14880:
		case 14881:
		case 14882:
			BankPin.clickedButton(player, id);
			break;
		case 27005:
		case 22012:
			if (!player.isBanking() || player.getInterfaceId() != 5292)
				return;
			Bank.depositItems(player, id == 27005 ? player.getEquipment() : player.getInventory(), false);
			break;
		case 27023:
			if (!player.isBanking() || player.getInterfaceId() != 5292)
				return;
			if (player.getSummoning().getBeastOfBurden() == null) {
				player.getPacketSender().sendMessage("You do not have a familiar which can hold items.");
				return;
			}
			Bank.depositItems(player, player.getSummoning().getBeastOfBurden(), false);
			break;
		case 22008:
			if (!player.isBanking() || player.getInterfaceId() != 5292)
				return;
			player.setNoteWithdrawal(!player.withdrawAsNote());
			break;
		case 27026:
			if(!player.isBanking() || player.getInterfaceId() != 5292)
				return;
			player.setPlaceholders(!player.isPlaceholders());
			break;
		case 21000:
			if (!player.isBanking() || player.getInterfaceId() != 5292)
				return;
			player.setSwapMode(false);
			player.getPacketSender().sendConfig(304, 0).sendMessage("This feature is coming soon!");
			// player.setSwapMode(!player.swapMode());
			break;
		case 27009:
			MoneyPouch.toBank(player);
			break;
		case 27014:
		case 27015:
		case 27016:
		case 27017:
		case 27018:
		case 27019:
		case 27020:
		case 27021:
		case 27022:
			if (!player.isBanking())
				return;
			if (player.getBankSearchingAttribtues().isSearchingBank())
				BankSearchAttributes.stopSearch(player, true);
			int bankId = id - 27014;
			boolean empty = bankId > 0 ? Bank.isEmpty(player.getBank(bankId)) : false;
			if (!empty || bankId == 0) {
				player.setCurrentBankTab(bankId);
				player.getPacketSender().sendString(5385, "scrollreset");
				player.getPacketSender().sendString(27002, Integer.toString(player.getCurrentBankTab()));
				player.getPacketSender().sendString(27000, "1");
				player.getBank(bankId).open();
			} else
				player.getPacketSender().sendMessage("To reincarnate a new tab, please drag an item here.");
			break;
		case 22004:
			if (!player.isBanking())
				return;
			if (!player.getBankSearchingAttribtues().isSearchingBank()) {
				player.getBankSearchingAttribtues().setSearchingBank(true);
				player.setInputHandling(new EnterSyntaxToBankSearchFor());
				player.getPacketSender().sendEnterInputPrompt("What would you like to search for?");
			} else {
				BankSearchAttributes.stopSearch(player, true);
			}
			break;
		case 22845:
		case 24115:
		case 24010:
		case 24041:
		case 150:
			player.setAutoRetaliate(!player.isAutoRetaliate());
			break;
		case 29332:
			ClanChat clan = player.getCurrentClanChat();
			if (clan == null) {
				player.getPacketSender().sendMessage("You are not in a clanchat channel.");
				return;
			}
			ClanChatManager.leave(player, false);
			player.setClanChatName(null);
			break;
		case 29329:
			if (player.getInterfaceId() > 0) {
				player.getPacketSender()
						.sendMessage("Please close the interface you have open before opening another one.");
				return;
			}
			player.setInputHandling(new EnterClanChatToJoin());
			player.getPacketSender().sendEnterInputPrompt("Enter the name of the clanchat channel you wish to join:");
			break;
		case 19158:
		case 152:
			if (player.getRunEnergy() <= 1) {
				player.getPacketSender().sendMessage("You do not have enough energy to do this.");
				player.setRunning(false);
			} else
				player.setRunning(!player.isRunning());
			player.getPacketSender().sendRunStatus();
			break;

		case -282:
			DropLog.openRare(player);
			break;
		case -26355:
			player.setExperienceLocked(!player.experienceLocked());
			String type = player.experienceLocked() ? "locked" : "unlocked";
			player.getPacketSender().sendMessage("Your experience is now " + type + ".");
			PlayerPanel.refreshPanel(player);
			break;
		case 27651:
		case 21341:
			if (player.getInterfaceId() == -1) {
				player.getSkillManager().stopSkilling();
				BonusManager.update(player);
				player.getPacketSender().sendInterface(21172);
			} else
				player.getPacketSender().sendMessage("Please close the interface you have open before doing this.");
			break;
		case 27654:
			if (player.getInterfaceId() > 0) {
				player.getPacketSender()
						.sendMessage("Please close the interface you have open before opening another one.");
				return;
			}
			player.getSkillManager().stopSkilling();
			ItemsKeptOnDeath.sendInterface(player);
			break;
		case 2458: // Logout
			if (player.logout()) {
				if(player.getSummoned() > 1) {
					SummoningTab.handleDismiss(player,true);
					player.setSummoned(-1);
				}
				World.getPlayers().remove(player);
			}
			break;
		case 29138:
		case 29038:
		case 29063:
		case 29113:
		case 29163:
		case 29188:
		case 29213:
		case 29238:
		case 30007:
		case 48023:
		case 33033:
		case 30108:
		case 7473:
		case 7562:
		case 7487:
		case 7788:
		case 8481:
		case 7612:
		case 7587:
		case 7662:
		case 7462:
		case 7548:
		case 7687:
		case 7537:
		case 12322:
		case 7637:
		case 12311:
		case -24530:
			CombatSpecial.activate(player);
			break;
			
			
		case 1772: // shortbow & longbow
			if (player.getWeapon() == WeaponInterface.SHORTBOW) {
				player.setFightType(FightType.SHORTBOW_ACCURATE);
			} else if (player.getWeapon() == WeaponInterface.LONGBOW) {
				player.setFightType(FightType.LONGBOW_ACCURATE);
			} else if (player.getWeapon() == WeaponInterface.CROSSBOW) {
				player.setFightType(FightType.CROSSBOW_ACCURATE);
			}
			break;
		case 1771:
			if (player.getWeapon() == WeaponInterface.SHORTBOW) {
				player.setFightType(FightType.SHORTBOW_RAPID);
			} else if (player.getWeapon() == WeaponInterface.LONGBOW) {
				player.setFightType(FightType.LONGBOW_RAPID);
			} else if (player.getWeapon() == WeaponInterface.CROSSBOW) {
				player.setFightType(FightType.CROSSBOW_RAPID);
			}
			break;
		case 1770:
			if (player.getWeapon() == WeaponInterface.SHORTBOW) {
				player.setFightType(FightType.SHORTBOW_LONGRANGE);
			} else if (player.getWeapon() == WeaponInterface.LONGBOW) {
				player.setFightType(FightType.LONGBOW_LONGRANGE);
			} else if (player.getWeapon() == WeaponInterface.CROSSBOW) {
				player.setFightType(FightType.CROSSBOW_LONGRANGE);
			}
			break;
		case 2282: // dagger & sword
			if (player.getWeapon() == WeaponInterface.DAGGER) {
				player.setFightType(FightType.DAGGER_STAB);
			} else if (player.getWeapon() == WeaponInterface.SWORD) {
				player.setFightType(FightType.SWORD_STAB);
			}
			break;
		case 2285:
			if (player.getWeapon() == WeaponInterface.DAGGER) {
				player.setFightType(FightType.DAGGER_LUNGE);
			} else if (player.getWeapon() == WeaponInterface.SWORD) {
				player.setFightType(FightType.SWORD_LUNGE);
			}
			break;
		case 2284:
			if (player.getWeapon() == WeaponInterface.DAGGER) {
				player.setFightType(FightType.DAGGER_SLASH);
			} else if (player.getWeapon() == WeaponInterface.SWORD) {
				player.setFightType(FightType.SWORD_SLASH);
			}
			break;
		case 2283:
			if (player.getWeapon() == WeaponInterface.DAGGER) {
				player.setFightType(FightType.DAGGER_BLOCK);
			} else if (player.getWeapon() == WeaponInterface.SWORD) {
				player.setFightType(FightType.SWORD_BLOCK);
			}
			break;
		case 2429: // scimitar & longsword
			if (player.getWeapon() == WeaponInterface.SCIMITAR) {
				player.setFightType(FightType.SCIMITAR_CHOP);
			} else if (player.getWeapon() == WeaponInterface.LONGSWORD) {
				player.setFightType(FightType.LONGSWORD_CHOP);
			}
			break;
		case 2432:
			if (player.getWeapon() == WeaponInterface.SCIMITAR) {
				player.setFightType(FightType.SCIMITAR_SLASH);
			} else if (player.getWeapon() == WeaponInterface.LONGSWORD) {
				player.setFightType(FightType.LONGSWORD_SLASH);
			}
			break;
		case 2431:
			if (player.getWeapon() == WeaponInterface.SCIMITAR) {
				player.setFightType(FightType.SCIMITAR_LUNGE);
			} else if (player.getWeapon() == WeaponInterface.LONGSWORD) {
				player.setFightType(FightType.LONGSWORD_LUNGE);
			}
			break;
		case 2430:
			if (player.getWeapon() == WeaponInterface.SCIMITAR) {
				player.setFightType(FightType.SCIMITAR_BLOCK);
			} else if (player.getWeapon() == WeaponInterface.LONGSWORD) {
				player.setFightType(FightType.LONGSWORD_BLOCK);
			}
			break;
		case 3802: // mace
			player.setFightType(FightType.MACE_POUND);
			break;
		case 3805:
			player.setFightType(FightType.MACE_PUMMEL);
			break;
		case 3804:
			player.setFightType(FightType.MACE_SPIKE);
			break;
		case 3803:
			player.setFightType(FightType.MACE_BLOCK);
			break;
		case 4454: // knife, thrownaxe, dart & javelin
			if (player.getWeapon() == WeaponInterface.KNIFE) {
				player.setFightType(FightType.KNIFE_ACCURATE);
			} else if (player.getWeapon() == WeaponInterface.THROWNAXE) {
				player.setFightType(FightType.THROWNAXE_ACCURATE);
			} else if (player.getWeapon() == WeaponInterface.DART) {
				player.setFightType(FightType.DART_ACCURATE);
			} else if (player.getWeapon() == WeaponInterface.JAVELIN) {
				player.setFightType(FightType.JAVELIN_ACCURATE);
			}
			break;
		case 4453:
			if (player.getWeapon() == WeaponInterface.KNIFE) {
				player.setFightType(FightType.KNIFE_RAPID);
			} else if (player.getWeapon() == WeaponInterface.THROWNAXE) {
				player.setFightType(FightType.THROWNAXE_RAPID);
			} else if (player.getWeapon() == WeaponInterface.DART) {
				player.setFightType(FightType.DART_RAPID);
			} else if (player.getWeapon() == WeaponInterface.JAVELIN) {
				player.setFightType(FightType.JAVELIN_RAPID);
			}
			break;
		case 4452:
			if (player.getWeapon() == WeaponInterface.KNIFE) {
				player.setFightType(FightType.KNIFE_LONGRANGE);
			} else if (player.getWeapon() == WeaponInterface.THROWNAXE) {
				player.setFightType(FightType.THROWNAXE_LONGRANGE);
			} else if (player.getWeapon() == WeaponInterface.DART) {
				player.setFightType(FightType.DART_LONGRANGE);
			} else if (player.getWeapon() == WeaponInterface.JAVELIN) {
				player.setFightType(FightType.JAVELIN_LONGRANGE);
			}
			break;
		case 4685: // spear
	    case 5146:
			player.setFightType(FightType.SPEAR_LUNGE);
			break;
		case 4688:
			player.setFightType(FightType.SPEAR_SWIPE);
			break;
		case 4687:
			player.setFightType(FightType.SPEAR_POUND);
			break;
		case 4686:
			player.setFightType(FightType.SPEAR_BLOCK);
			break;
		case 4711: // 2h sword
			player.setFightType(FightType.TWOHANDEDSWORD_CHOP);
			break;
		case 4714:
			player.setFightType(FightType.TWOHANDEDSWORD_SLASH);
			break;
		case 4713:
			player.setFightType(FightType.TWOHANDEDSWORD_SMASH);
			break;
		case 4712:
			player.setFightType(FightType.TWOHANDEDSWORD_BLOCK);
			break;
		case 5576: // pickaxe
			player.setFightType(FightType.PICKAXE_SPIKE);
			break;
		case 5579:
			player.setFightType(FightType.PICKAXE_IMPALE);
			break;
		case 5578:
			player.setFightType(FightType.PICKAXE_SMASH);
			break;
		case 5577:
			player.setFightType(FightType.PICKAXE_BLOCK);
			break;
		case 7768: // claws
			player.setFightType(FightType.CLAWS_CHOP);
			break;
		case 7771:
			player.setFightType(FightType.CLAWS_SLASH);
			break;
		case 7770:
			player.setFightType(FightType.CLAWS_LUNGE);
			break;
		case 7769:
			player.setFightType(FightType.CLAWS_BLOCK);
			break;
		case 8466: // halberd
			player.setFightType(FightType.HALBERD_JAB);
			break;
		case 8468:
			player.setFightType(FightType.HALBERD_SWIPE);
			break;
		case 8467:
			player.setFightType(FightType.HALBERD_FEND);
			break;
		case 5862: // unarmed
			player.setFightType(FightType.UNARMED_PUNCH);
			break;
		case 5861:
			player.setFightType(FightType.UNARMED_KICK);
			break;
		case 5860:
			player.setFightType(FightType.UNARMED_BLOCK);
			break;
		case 12298: // whip
			player.setFightType(FightType.WHIP_FLICK);
			break;
		case 3318:
			player.setFightType(FightType.WHIP_FLICK);
			break;
		case 12297:
			player.setFightType(FightType.WHIP_LASH);
			break;
		case 12296:
			player.setFightType(FightType.WHIP_DEFLECT);
			break;
		case 336: // staff
			player.setFightType(FightType.STAFF_BASH);
			break;
		case 335:
			player.setFightType(FightType.STAFF_POUND);
			break;
		case 334:
			player.setFightType(FightType.STAFF_FOCUS);
			break;
		case 433: // warhammer
			player.setFightType(FightType.WARHAMMER_POUND);
			break;
		case 432:
			player.setFightType(FightType.WARHAMMER_PUMMEL);
			break;
		case 431:
			player.setFightType(FightType.WARHAMMER_BLOCK);
			break;
		case 782: // scythe
			player.setFightType(FightType.SCYTHE_REAP);
			break;
		case 784:
			player.setFightType(FightType.SCYTHE_CHOP);
			break;
		case 785:
			player.setFightType(FightType.SCYTHE_JAB);
			break;
		case 783:
			player.setFightType(FightType.SCYTHE_BLOCK);
			break;
		case 1704: // battle axe
			player.setFightType(FightType.BATTLEAXE_CHOP);
			break;
		case 1707:
			player.setFightType(FightType.BATTLEAXE_HACK);
			break;
		case 1706:
			player.setFightType(FightType.BATTLEAXE_SMASH);
			break;
		case 1705:
			player.setFightType(FightType.BATTLEAXE_BLOCK);
			break;
		}
	}

	private boolean checkHandlers(Player player, int id) {
		if(player.getCustomCombiner().handleButton(id))
			return true;
		if (Construction.handleButtonClick(id, player)) {
			return true;
		}
		switch (id) {
		case 2494:
		case 2495:
		case 2496:
		case 2497:
		case 2498:
		case 2471:
		case 2472:
		case 2473:
		case 2461:
		case 2462:
		case 2482:
		case 2483:
		case 2484:
		case 2485:
			DialogueOptions.handle(player, id);
			return true;
		case -8312:
		case -8311:
		case -8310:
		case -8309:
		case -8308:
		case -8307:
			player.getDailyReward().openInterface();
			return true;
				
		}
		if (player.isPlayerLocked() && id != 2458 && !StartScreen.ignoreLockForButtons.contains(id)) {
			return true;
		}
		if (player.getTeleportInterface().handleButton(id)) {
			return true;
		}
		if (player.getAchievementInterface() != null && player.getAchievementInterface().handleButton(id)) {
			return true;
		}
		if (Achievements.handleButton(player, id)) {
			return true;
		}
		if (Sounds.handleButton(player, id)) {
			return true;
		}
		if (PrayerHandler.isButton(id)) {
			PrayerHandler.togglePrayerWithActionButton(player, id);
			return true;
		}
		if (CurseHandler.isButton(player, id)) {
			return true;
		}
		if (player.getStartScreen() != null && player.getStartScreen().handleButton(id)) {
			return true;
		}
		if (Autocasting.handleAutocast(player, id)) {
			return true;
		}
		if (SmithingData.handleButtons(player, id)) {
			return true;
		}
		if (PouchMaking.pouchInterface(player, id)) {
			return true;
		}
		if (LoyaltyProgramme.handleButton(player, id)) {
			return true;
		}
		if (Fletching.fletchingButton(player, id)) {
			return true;
		}
		if (LeatherMaking.handleButton(player, id) || Tanning.handleButton(player, id)) {
			return true;
		}
		if (Emotes.doEmote(player, id)) {
			return true;
		}
		if (PestControl.handleInterface(player, id)) {
			return true;
		}
		if (player.getLocation() == Location.DUEL_ARENA && Dueling.handleDuelingButtons(player, id)) {
			return true;
		}
		if (Slayer.handleRewardsInterface(player, id)) {
			return true;
		}
		if (ExperienceLamps.handleButton(player, id)) {
			return true;
		}
		if (PlayersOnlineInterface.handleButton(player, id)) {
			return true;
		}
		if (GrandExchange.handleButton(player, id)) {
			return true;
		}
		if (ClanChatManager.handleClanChatSetupButton(player, id)) {
			return true;
		}
		return false;
	}

	public static final int OPCODE = 185;
}
