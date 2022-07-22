package com.arlania.net.packet.impl;

import com.arlania.GameServer;
import com.arlania.GameSettings;
import com.arlania.engine.task.Task;
import com.arlania.engine.task.TaskManager;
import com.arlania.engine.task.impl.PlayerDeathTask;
import com.arlania.model.Animation;
import com.arlania.model.Direction;
import com.arlania.model.Flag;
import com.arlania.model.GameMode;
import com.arlania.model.GameObject;
import com.arlania.model.Graphic;
import com.arlania.model.Item;
import com.arlania.model.Locations.Location;
import com.arlania.model.PlayerRights;
import com.arlania.model.Position;
import com.arlania.model.Prayerbook;
import com.arlania.model.Skill;
import com.arlania.model.container.impl.Bank;
import com.arlania.model.container.impl.Equipment;
import com.arlania.model.container.impl.Shop.ShopManager;
import com.arlania.model.definitions.ItemDefinition;
import com.arlania.model.definitions.NPCDrops;
import com.arlania.model.definitions.NpcDefinition;
import com.arlania.model.definitions.WeaponAnimations;
import com.arlania.model.definitions.WeaponInterfaces;
import com.arlania.model.input.impl.EnterReferral;
import com.arlania.net.login.BetaMode;
import com.arlania.net.packet.Packet;
import com.arlania.net.packet.PacketListener;
import com.arlania.net.security.ConnectionHandler;
import com.arlania.rewardslist.RewardsHandler;
import com.arlania.util.CommandTypes;
import com.arlania.util.Misc;
import com.arlania.util.NameUtils;
import com.arlania.util.RandomUtility;
import com.arlania.util.StaffCommand;
import com.arlania.util.banning.BanHammer;
import com.arlania.util.banning.BanType;
import com.arlania.world.World;
import com.arlania.world.content.*;
import com.arlania.world.content.PlayerPunishment.Jail;

import static com.arlania.world.content.TeleportHandlerNew.openInterface;

import com.arlania.world.content.achievements.AchievementData;
import com.arlania.world.content.achievements.AchievementInterface;
import com.arlania.world.content.bossEvent.BossEventHandler;
import com.arlania.world.content.clan.ClanChatManager;
import com.arlania.world.content.combat.CombatFactory;
import com.arlania.world.content.combat.DesolaceFormulas;
import com.arlania.world.content.combat.prayer.CurseHandler;
import com.arlania.world.content.combat.prayer.PrayerHandler;
import com.arlania.world.content.combat.strategy.CombatStrategies;
import com.arlania.world.content.combat.weapon.CombatSpecial;
import com.arlania.world.content.droppreview.SLASHBASH;
import com.arlania.world.content.global.GlobalBoss;
import com.arlania.world.content.global.GlobalBossHandler;
import com.arlania.world.content.grandexchange.GrandExchange;
import com.arlania.world.content.grandexchange.GrandExchangeOffers;
import com.arlania.world.content.minigames.impl.FreeForAll;
import com.arlania.world.content.priceguide.PriceGuide;
import com.arlania.world.content.skill.SkillManager;
import com.arlania.world.content.skill.impl.herblore.Decanting;
import com.arlania.world.content.skill.impl.summoning.Summoning;
import com.arlania.world.content.transportation.TeleportHandler;
import com.arlania.world.content.transportation.TeleportType;
import com.arlania.world.content.upgrading.UpgradeData;
import com.arlania.world.entity.impl.npc.NPC;
import com.arlania.world.entity.impl.player.Player;
import com.arlania.world.entity.impl.player.PlayerHandler;
import com.motivoters.motivote.service.MotivoteRS;
import com.ruseps.world.content.dropchecker.NPCDropTableChecker;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import mysql.MySQLController;
import mysql.impl.Store;
import mysql.impl.Voting;

/**
 * This packet listener manages commands a player uses by using the command
 * console prompted by using the "`" char.
 *
 * @author Gabriel Hannason
 */

public class CommandPacketListener implements PacketListener {
	public static boolean zoneDisabled = false;
	public static int config;
	private static final File CONTESTERS_FILE_LOCATION = new File("./data/saves/voting/votes.txt");
	public static final List<String> CONTESTERS = new ArrayList<String>();
	@Override
	public void handleMessage(Player player, Packet packet) {
		String command = Misc.readString(packet.getBuffer());
		String[] parts = command.toLowerCase().split(" ");
		if (command.contains("\r") || command.contains("\n")) {
			return;
		}
		try {
			if(player.getGameMode() !=GameMode.NORMAL) {
			if (player.getAmountDonated() >= 1500) {
				playerCommands(player, parts, command);
				memberCommands(player, parts, command);
				uberDonator(player, parts, command);
				legendaryDonator(player, parts, command);
				extremeDonator(player, parts, command);
			}
			 if (player.getAmountDonated() >= 800 && player.getAmountDonated() < 1500) {
				playerCommands(player, parts, command);
				memberCommands(player, parts, command);
				uberDonator(player, parts, command);
				legendaryDonator(player, parts, command);
				extremeDonator(player, parts, command);
			}
			 if (player.getAmountDonated() >= 300&& player.getAmountDonated() < 800) {
				playerCommands(player, parts, command);
				memberCommands(player, parts, command);
				legendaryDonator(player, parts, command);
				extremeDonator(player, parts, command);
			}
			 if (player.getAmountDonated() >= 150 && player.getAmountDonated() < 300) {
				playerCommands(player, parts, command);
				memberCommands(player, parts, command);
				extremeDonator(player, parts, command);
			}

			 if (player.getAmountDonated() >= 50 && player.getAmountDonated() < 150) {
				playerCommands(player, parts, command);
				memberCommands(player, parts, command);
				superDonator(player, parts, command);
			}

			if (player.getAmountDonated() >= 25 && player.getAmountDonated() < 49) {
				playerCommands(player, parts, command);
				memberCommands(player, parts, command);
			}
			}
			switch (player.getRights()) {
			case VETERAN:
				playerCommands(player, parts, command);
				memberCommands(player, parts, command);
				break;
			case PLAYER:
				playerCommands(player, parts, command);
				break;
				case FORUM_DEVELOPER:
					playerCommands(player, parts, command);
					superDonator(player, parts, command);
					extremeDonator(player, parts, command);
					legendaryDonator(player, parts, command);
					uberDonator(player, parts, command);
					memberCommands(player, parts, command);
					helperCommands(player, parts, command);
					break;
			case MODERATOR:
				playerCommands(player, parts, command);
				superDonator(player, parts, command);
				extremeDonator(player, parts, command);
				legendaryDonator(player, parts, command);
				uberDonator(player, parts, command);
				memberCommands(player, parts, command);
				helperCommands(player, parts, command);
				moderatorCommands(player, parts, command);
				break;
			case ADMINISTRATOR:
				playerCommands(player, parts, command);
				superDonator(player, parts, command);
				extremeDonator(player, parts, command);
				legendaryDonator(player, parts, command);
				uberDonator(player, parts, command);
				memberCommands(player, parts, command);
				helperCommands(player, parts, command);
				moderatorCommands(player, parts, command);
				administratorCommands(player, parts, command);
				break;
			case OWNER:
				playerCommands(player, parts, command);
				superDonator(player, parts, command);
				extremeDonator(player, parts, command);
				legendaryDonator(player, parts, command);
				uberDonator(player, parts, command);
				memberCommands(player, parts, command);
				helperCommands(player, parts, command);
				moderatorCommands(player, parts, command);
				administratorCommands(player, parts, command);
				ownerCommands(player, parts, command);
				developerCommands(player, parts, command);
				break;
			case DEVELOPER:
				playerCommands(player, parts, command);
				superDonator(player, parts, command);
				extremeDonator(player, parts, command);
				legendaryDonator(player, parts, command);
				uberDonator(player, parts, command);
				memberCommands(player, parts, command);
				helperCommands(player, parts, command);
				moderatorCommands(player, parts, command);
				administratorCommands(player, parts, command);
				ownerCommands(player, parts, command);
				developerCommands(player, parts, command);
				break;
			case SUPPORT:
				playerCommands(player, parts, command);
				superDonator(player, parts, command);
				extremeDonator(player, parts, command);
				legendaryDonator(player, parts, command);
				uberDonator(player, parts, command);
				memberCommands(player, parts, command);
				helperCommands(player, parts, command);
				break;
			case YOUTUBER:
				playerCommands(player, parts, command);
				memberCommands(player, parts, command);
				youtuberCommands(player, parts, command);
				break;
			case DONATOR:
				playerCommands(player, parts, command);
				memberCommands(player, parts, command);
				break;
			case SUPER_DONATOR:
				playerCommands(player, parts, command);
				memberCommands(player, parts, command);
				superDonator(player, parts, command);
				break;
			case EXTREME_DONATOR:
				playerCommands(player, parts, command);
				memberCommands(player, parts, command);
				extremeDonator(player, parts, command);
				break;
			case SPONSOR_DONATOR:
				playerCommands(player, parts, command);
				memberCommands(player, parts, command);
				legendaryDonator(player, parts, command);
				extremeDonator(player, parts, command);
				break;
			case EXECUTIVE_DONATOR:
				playerCommands(player, parts, command);
				memberCommands(player, parts, command);
				uberDonator(player, parts, command);
				legendaryDonator(player, parts, command);
				extremeDonator(player, parts, command);
				break;
			case DIVINE_DONATOR:
				playerCommands(player, parts, command);
				memberCommands(player, parts, command);
				uberDonator(player, parts, command);
				legendaryDonator(player, parts, command);
				extremeDonator(player, parts, command);
				break;
			case PARTNER_DONATOR:
				playerCommands(player, parts, command);
				memberCommands(player, parts, command);
				uberDonator(player, parts, command);
				legendaryDonator(player, parts, command);
				extremeDonator(player, parts, command);
				break;
			case CONTRIBUTER:
				playerCommands(player, parts, command);
				memberCommands(player, parts, command);
				uberDonator(player, parts, command);
				legendaryDonator(player, parts, command);
				extremeDonator(player, parts, command);
				break;
			default:
				break;
			}
		} catch (Exception exception) {
			// exception.printStackTrace();

			if (player.getRights() == PlayerRights.DEVELOPER) {
				player.getPacketSender().sendConsoleMessage("Error executing that command.");
			} else {
				player.getPacketSender().sendMessage("Error executing that command.");
			}

		}
	}

	private final static MotivoteRS motivote = new MotivoteRS("", ""); // replace
																												// API
																												// key
																												// and
																												// subdomain
					static int VOTES = 0;																							// with
																												// yours.

	private static void playerCommands(final Player player, String[] command, String wholeCommand) {
		if (command[0].equalsIgnoreCase("teleport")) {
			player.getTeleportInterface().open();
			return;
		}
		if (GameSettings.betaMode && command[0].equalsIgnoreCase("resetskills")) {
			player.getSkillManager().newSkillManager();
			return;
		}
//		if (command[0].equalsIgnoreCase("achievements")) {
//			AchievementInterface.open(player);
//			return;
//		}
		if (command[0].equalsIgnoreCase("pos")) {
			if (player.getLocation() == Location.RAID || player.getLocation() == Location.DUEL_ARENA) {
			player.getPacketSender().sendMessage("You can't open the player shops right now!");
		} else {
				player.getPlayerOwnedShopManager().options();
				return;
			}
		}

		if (command[0].equalsIgnoreCase("refer") || command[0].equalsIgnoreCase("ref")) {
			if (player.hasReferral) {
				player.getPacketSender().sendMessage("You have already claimed a referral reward on this account!");
			} else {
				player.getPacketSender().sendEnterInputPrompt("Please type your refer code to receive a reward!");
				player.setInputHandling(new EnterReferral());
			}
		}
		if (command[0].equalsIgnoreCase("dismiss")) {
			if(player.getSummoning().getFamiliar() != null) {
				player.getSummoning().unsummon(true, true);
				player.getPacketSender().sendMessage("You've dismissed your familiar.");
			} else {
				player.getPacketSender().sendMessage("You don't have a familiar to dismiss.");
			}
		}
//		if (command[0].equalsIgnoreCase("deals")) {
//			player.getDonationDeals().displayReward();
//     		player.getDonationDeals().displayTime();
//		}
//		if (command[0].equalsIgnoreCase("donationdeal")) {
//			player.getDonationDeals().displayReward();
//			player.getDonationDeals().displayTime();
//		}

	/*	if(command[0].equalsIgnoreCase("events")) {
			new BossEventHandler().teleport(player);
		}*/
//		if (command[0].equalsIgnoreCase("progress")) {
//			player.getProgressionManager().open();
//		}
//		if (command[0].equalsIgnoreCase("startraids")) {
//			if (player.getLocation() == Location.RAID || player.getLocation() == Location.DUEL_ARENA || player.getLocation() == Location.WILDERNESS) {
//				player.getPacketSender().sendMessage("You can't open shops right now !");
//			} else {
//			Locations.Location.GODS_RAID.enter(player);
//			TeleportHandler.teleportPlayer(player, new Position(3052, 2724),
//					player.getSpellbook().getTeleportType());
//			return;
//			}
//		}
//		if (command[0].equalsIgnoreCase("leaveraids")) {
//			Locations.Location.GODS_RAID.leave(player);
//			TeleportHandler.teleportPlayer(player, new Position(3817, 3484), player.getSpellbook().getTeleportType());
//		}
		
		if (command[0].equalsIgnoreCase("rewardlist") || command[0].equalsIgnoreCase("rewardss")) {
			new RewardsHandler(player);
			RewardsHandler.open(player);
		}
		if (command[0].equalsIgnoreCase("discord")) {
			player.getAchievementTracker().progress(AchievementData.ENTER_THE_DISCORD, 1);
			player.createAccountPin(false);

		}
		if (command[0].equalsIgnoreCase("curses")) {
			player.performAnimation(new Animation(645));
			if (player.getPrayerbook() == Prayerbook.NORMAL) {
				player.getPacketSender().sendMessage("You sense a surge of power flow through your body!");
				player.setPrayerbook(Prayerbook.CURSES);
			} else {
				player.getPacketSender().sendMessage("You sense a surge of purity flow through your body!");
				player.setPrayerbook(Prayerbook.NORMAL);
			}
			player.getPacketSender().sendTabInterface(GameSettings.PRAYER_TAB, player.getPrayerbook().getInterfaceId());
			PrayerHandler.deactivateAll(player);
			CurseHandler.deactivateAll(player);
		}
		if (command[0].equalsIgnoreCase("invite")) {
			String name = wholeCommand.substring(7);
			Player target = World.getPlayerByName(name);
			if(!player.getInstance()) {
				player.getPacketSender().sendMessage("You need to be in a boss instance to invite players!");
				return;
			}
			if (target == null) {
				player.getPacketSender().sendMessage("Player is not online");
			} else {
			target.getPacketSender().sendMessage("<shad=0>@cya@"+player.getUsername()+" Has invited you for his boss instance!");
			target.getPacketSender().sendMessage("<shad=0>@cya@ use ::acceptinvite if you wish to join!");
			target.setInvited(true);
			int[] pos = new int[] {player.getPosition().getX(),player.getPosition().getY(),player.getPosition().getZ()};
			target.setInvitedCoords(pos);
			}
		}
		if (command[0].equalsIgnoreCase("acceptinvite")) {
			if (player.getInvited() == false) {
				player.getPacketSender().sendMessage("You have no instance invite!");
			} else {
				player.setInvited(false);
				TeleportHandler.teleportPlayer(player, new Position(player.getInvitedCoords()[0],player.getInvitedCoords()[1],player.getInvitedCoords()[2]), player.getSpellbook().getTeleportType());
			}
		}
		
//		if (command[0].equalsIgnoreCase("jacksz")) {
//
//			player.getDailyRewards().handleVote();//its on local host right? ye
//
//			player.lastVoteTime = System.currentTimeMillis();
//		}

		if (command[0].startsWith("reward")) {
		    if (command.length == 1) {
		        player.getPacketSender().sendMessage("Please use [::reward id], [::reward id amount], or [::reward id all].");
		        return;
		    }
		    final String playerName = player.getUsername();
		    final String id = command[1];
		    final String amount = command.length == 3 ? command[2] : "1";

		    com.everythingrs.vote.Vote.service.execute(new Runnable() {
		        @Override
		        public void run() {
		            try {
		                com.everythingrs.vote.Vote[] reward = com.everythingrs.vote.Vote.reward("DNRcqCeF7wFog1T17fakYOYwl6cBS8tuoRFINeYxTQxyh7vzA6XDZeUu4xK6fmsZtZpimfBz",
		                    playerName, id, amount);
		                if (reward[0].message != null) {
		                    player.getPacketSender().sendMessage(reward[0].message);
		                    return;
		                }
		                Voting.VOTES++;
						player.getPacketSender().sendString(50866, "Vote boss: @gre@" + Voting.VOTES + "/15");
						player.getInventory().add(reward[0].reward_id, reward[0].give_amount);
		                player.getPacketSender().sendMessage("Thank you for voting! You now have " + reward[0].vote_points + " vote points.");
		                World.sendMessage("<img=10><shad=0>@bla@[@mag@Vote@mag@]@bla@"+player.getUsername()+"@bla@ has just Voted@mag@@bla@!");
						player.getAchievementTracker().progress(com.arlania.world.content.achievements.AchievementData.VOTE, 1);

		            } catch (Exception e) {
		                player.getPacketSender().sendMessage("Api Services are currently offline. Please check back shortly");
		                e.printStackTrace();
		            }
		        }

		    });
		}
		if(command[0].equalsIgnoreCase("ffa")) {
			FreeForAll.enterLobby(player);
		}
		if (command[0].equalsIgnoreCase("npckills")) {
			player.getPacketSender().sendMessage("@red@You currently have " + KillsTracker.getTotalKills(player) + " NPC kills.");
		}
		if (command[0].equalsIgnoreCase("tdkills")) {
			player.getPacketSender().sendMessage("@red@You currently have " + KillsTracker.getTotalKillsForNpc(8349, player) + " Tormented Demon kills.");
		}
		if (command[0].equalsIgnoreCase("dustkills")) {
			player.getPacketSender().sendMessage("@blu@You currently have " + KillsTracker.getTotalKillsForNpc(1624, player) + " Dust Devil kills.");
		}
		if (command[0].equalsIgnoreCase("thermykills")) {
			player.getPacketSender().sendMessage("@red@You currently have " + KillsTracker.getTotalKillsForNpc(499, player) + " Thermy kills.");
		}
		if (command[0].equalsIgnoreCase("skotizokills")) {
			player.getPacketSender().sendMessage("@red@You currently have " + KillsTracker.getTotalKillsForNpc(8754, player) + " Skotizo kills.");
		}
        if (command[0].equals("mydr")) {
        player.getPacketSender().sendMessage("You currently have "+NPCDrops.getDroprate(player,true)+"% droprate and "+NPCDrops.getDoubleDr(player,true)+"% double droprate");
        }
		if (command[0].equalsIgnoreCase("kbdkills")) {
			player.getPacketSender().sendMessage("@red@You currently have " + KillsTracker.getTotalKillsForNpc(50, player) + " KBD kills.");
		}
		if (command[0].equalsIgnoreCase("kqkills")) {
			player.getPacketSender().sendMessage("@red@You currently have " + KillsTracker.getTotalKillsForNpc(1160, player) + " KQ kills.");
		}
		if (command[0].equalsIgnoreCase("slashkills")) {
			player.getPacketSender().sendMessage("@red@You currently have " + KillsTracker.getTotalKillsForNpc(2060, player) + " Slash Bash kills.");
		}
		if (command[0].equalsIgnoreCase("cerbkills")) {
			player.getPacketSender().sendMessage("@red@You currently have " + KillsTracker.getTotalKillsForNpc(1999, player) + " Cerberus kills.");
		}
		if (command[0].equalsIgnoreCase("phoenixkills")) {
			player.getPacketSender().sendMessage("@red@You currently have " + KillsTracker.getTotalKillsForNpc(8549, player) + " Phoenix kills.");
		}
		if (command[0].equalsIgnoreCase("mewkills")) {
			player.getPacketSender().sendMessage("@red@You currently have " + KillsTracker.getTotalKillsForNpc(6766, player) + " Mew kills.");
		}
		if (command[0].equalsIgnoreCase("dwolfkills")) {
			player.getPacketSender().sendMessage("@red@You currently have " + KillsTracker.getTotalKillsForNpc(4413, player) + " Dire Wolf kills.");
		}
		if (command[0].equalsIgnoreCase("glacorkills")) {
			player.getPacketSender().sendMessage("@red@You currently have " + KillsTracker.getTotalKillsForNpc(1382, player) + " Glacor kills.");
		}
		if (command[0].equalsIgnoreCase("yetikills")) {
			player.getPacketSender().sendMessage("@red@You currently have " + KillsTracker.getTotalKillsForNpc(130, player) + " Yeti kills.");
		}
		if (command[0].equalsIgnoreCase("cyrisuskills")) {
			player.getPacketSender().sendMessage("@red@You currently have " + KillsTracker.getTotalKillsForNpc(433, player) + " Cyrisus kills.");
		}
		if (command[0].equalsIgnoreCase("frostkills")) {
			player.getPacketSender().sendMessage("@red@You currently have " + KillsTracker.getTotalKillsForNpc(51, player) + " Frost Dragon kills.");
		}
		if (command[0].equalsIgnoreCase("nazakills")) {
			player.getPacketSender().sendMessage("@red@You currently have " + KillsTracker.getTotalKillsForNpc(509, player) + " Nazastarool kills.");
		}
		if (command[0].equalsIgnoreCase("abbadonkills")) {
			player.getPacketSender().sendMessage("@red@You currently have " + KillsTracker.getTotalKillsForNpc(6303, player) + " Abbadon kills.");
		}
		if (command[0].equalsIgnoreCase("voteboss")) {
			Voting.VOTES+= 15;
		}
//		if (command[0].equalsIgnoreCase("train") || command[0].equalsIgnoreCase("starter")
//				|| command[0].equalsIgnoreCase("start") || command[0].equalsIgnoreCase(
//				"training")) {/*
//		 * Position[] locations = new Position[] { new Position(2702, 4751, 0), new
//		 * Position(2708, 4752, 0), new Position(2734, 4767, 0), new Position(2728,
//		 * 4761, 0) }; Position teleportLocation =
//		 * locations[RandomUtility.exclusiveRandom(0, locations.length)];
//		 *
//		 * TeleportHandler.teleportPlayer(player, teleportLocation,
//		 * player.getSpellbook().getTeleportType()); // will work now, was from 0-length
//		 * + 1 before, but supposed to be 0-length // only.
//		 */
//			player.getPacketSender().sendInterface(48550);
//		}
//		if (command[0].equalsIgnoreCase("Donate")) {
//			new Thread() {
//				public void run() {
//					try {
//						System.out.println(player.getUsername());
//						com.everythingrs.donate.Donation[] donations = com.everythingrs.donate.Donation.donations("4NPnqxwOzv226sTFD4StwGoJ4u6RlxA7PRdkwKDOvAahKOMvZVQ4UdroyDzBlVLMdYlSJ0T1", player.getUsername());
//						if (donations.length == 0) {
//							player.getPacketSender().sendMessage("You currently don't have any items waiting. You must donate first!");
//							return;
//						}
//						if (donations[0].message != null) {
//							player.getPacketSender().sendMessage(donations[0].message);
//							return;
//						}
//						for (com.everythingrs.donate.Donation donate : donations) {
//							int random = Misc.random(50);
//
//							if(random == 0) {
//								player.getInventory().add(new Item(donate.product_id, donate.product_amount*3));
//								World.sendMessage("<img=10> <shad=0>@bla@[@mag@Donation@bla@]@bla@[@or3@Tripled@bla@]@mag@ "+player.getUsername()+" @bla@has just Donated for  @mag@"+donate.product_amount*3+"X @bla@ @mag@"+donate.product_name+" @bla@!");
//								EmbedBuilder embed = new EmbedBuilder();
//								embed.setTitle("ValacialX Donations");
//								embed.setColor(Color.ORANGE);
//								embed.addField(" [Donation][TRIPLED] "+player.getUsername()+" has just Donated for "+donate.product_amount*3+"X "+donate.product_name+" !", "", false);
//								embed.addField("Thanks!For Donations ","", true);
//
//								DiscordBot.jda.getTextChannelById(GameSettings.DONATION_KEY).sendMessage(embed.build()).queue();
//							}
//							if(random > 1 && random < 5) {
//								player.getInventory().add(new Item(donate.product_id, donate.product_amount*2));
//								World.sendMessage("<img=10> <shad=0>@bla@[@mag@Donation@bla@]@bla@[@or1@Doubled@bla@]@mag@ "+player.getUsername()+" @bla@has just Donated for  @mag@"+donate.product_amount*2+"X @bla@ @mag@"+donate.product_name+" @bla@!");
//								EmbedBuilder embed = new EmbedBuilder();
//								embed.setTitle("ValacialX Donations");
//								embed.setColor(Color.RED);
//								embed.addField(" [Donation][DOUBLED] "+player.getUsername()+" has just Donated for "+donate.product_amount*2+"X "+donate.product_name+" !!", "", false);
//								embed.addField("Thanks!For Donations ","", true);
//
//								DiscordBot.jda.getTextChannelById(GameSettings.DONATION_KEY).sendMessage(embed.build()).queue();
//							}
//							if(random > 5) {
//								player.getInventory().add(new Item(donate.product_id, donate.product_amount));
//								World.sendMessage("<img=10> <shad=0>@bla@[@red@Donation@bla@]@mag@ "+player.getUsername()+" @bla@has just Donated for a  @red@"+donate.product_amount+"X @bla@ @red@"+donate.product_name+" @bla@!");
//								EmbedBuilder embed = new EmbedBuilder();
//								embed.setTitle("ValacialX Donations");
//								embed.setColor(Color.YELLOW);
//								embed.addField(" [Donation] "+player.getUsername()+" has just Donated for "+donate.product_amount+"X "+donate.product_name+" !", "", false);
//								embed.addField("Thanks!For Donations ","", true);
//								DiscordBot.jda.getTextChannelById(GameSettings.DONATION_KEY).sendMessage(embed.build()).queue();
//							}
//
//						}
//						player.getPacketSender().sendMessage("Thank you for donating!");
//
//					} catch (Exception e) {
//						player.getPacketSender().sendMessage("Api Services are currently offline. Please check back shortly");
//						e.printStackTrace();
//					}
//				}
//			}.start();
//		}
	
		if (command[0].equals("fly")) {
			if (player.getRights().isStaff() || player.getRights() == PlayerRights.DIVINE_DONATOR
					|| player.getRights() == PlayerRights.PARTNER_DONATOR
					|| player.getRights() == PlayerRights.EXECUTIVE_DONATOR)
			 if (player.getCharacterAnimations().getStandingAnimation() != 1501) {
					player.performAnimation(new Animation(1500));
					player.getCharacterAnimations().setStandingAnimation(1501);
					player.getCharacterAnimations().setWalkingAnimation(1851);
					player.getCharacterAnimations().setRunningAnimation(1851);
					player.getUpdateFlag().flag(Flag.APPEARANCE);
					player.sendMessage("You turn Fly mode on.");
				} else {
					player.getCharacterAnimations().setStandingAnimation(0x328);
					player.getCharacterAnimations().setWalkingAnimation(0x333);
					player.getCharacterAnimations().setRunningAnimation(824);
					player.getUpdateFlag().flag(Flag.APPEARANCE);
					player.sendMessage("Flymode has been deactivated.");
				}
		}
	
				if(command[0].equals("players")) {
		      player.getPacketSender().sendMessage("There are currently " + World.getPlayers().size() + " players online!");
		}
		if (command[0].equals("ixwarrior") || command[0].equals("ixwarriors")) {
			TeleportHandler.teleportPlayer(player, new Position(2766, 2799),
					player.getSpellbook().getTeleportType());
		}
		if (command[0].equals("edgeville")) {
			TeleportHandler.teleportPlayer(player, new Position(3087, 3503),
					player.getSpellbook().getTeleportType());
		}
//		if (command[0].equals("mew")) {
//			TeleportHandler.teleportPlayer(player, new Position(2718, 9818),
//					player.getSpellbook().getTeleportType());
//		}
		if (command[0].equals("falador")) {
			TeleportHandler.teleportPlayer(player, new Position(2966, 3381),
					player.getSpellbook().getTeleportType());
		}
		if (command[0].equals("camelot")) {
			TeleportHandler.teleportPlayer(player, new Position(2731, 3485),
					player.getSpellbook().getTeleportType());
		}
		if (command[0].equals("lumbridge")) {
			TeleportHandler.teleportPlayer(player, new Position(3236, 3218),
					player.getSpellbook().getTeleportType());
		}
		if (command[0].equals("draynor")) {
			TeleportHandler.teleportPlayer(player, new Position(3094, 3248),
					player.getSpellbook().getTeleportType());
		}
		if (command[0].equals("portsarim")) {
			TeleportHandler.teleportPlayer(player, new Position(3041, 3252),
					player.getSpellbook().getTeleportType());
		}
		if (command[0].equals("rimmington")) {
			TeleportHandler.teleportPlayer(player, new Position(2957, 3215),
					player.getSpellbook().getTeleportType());
		}
		if (command[0].equals("alkharid")) {
			TeleportHandler.teleportPlayer(player, new Position(3292, 3185),
					player.getSpellbook().getTeleportType());
		}
		if (command[0].equals("ardougne")) {
			TeleportHandler.teleportPlayer(player, new Position(2662, 3305),
					player.getSpellbook().getTeleportType());
		}
		if (command[0].equals("yanille")) {
			TeleportHandler.teleportPlayer(player, new Position(2606, 3093),
					player.getSpellbook().getTeleportType());
		}
		if (command[0].equals("daily")) {
			RewardsHandler.open(player);
		}
		if (command[0].equals("direwolf")) {
			if(zoneDisabled) 
			{
				player.sendMessage("Zone is disabled for now.");
			return;
			}
			TeleportHandler.teleportPlayer(player, new Position(3110, 9518),
					player.getSpellbook().getTeleportType());
			
		}
		if (command[0].equals("direwolf2")) {
			if(zoneDisabled) 
			{
				player.sendMessage("Zone is disabled for now.");
			return;
			}
			TeleportHandler.teleportPlayer(player, new Position(3110, 9518, 4),
					player.getSpellbook().getTeleportType());
		}
		if (command[0].equals("direwolf3")) {
			if(zoneDisabled) 
			{
				player.sendMessage("Zone is disabled for now.");
			return;
			}
			TeleportHandler.teleportPlayer(player, new Position(3110, 9518, 8),
					player.getSpellbook().getTeleportType());
		}
		if (command[0].equals("triobros")) {
			if(zoneDisabled) 
			{
				player.sendMessage("Zone is disabled for now.");
			return;
			}
			TeleportHandler.teleportPlayer(player, new Position(2522, 3019, 0),
					player.getSpellbook().getTeleportType());
		}

		//if (command[0].equals("voteboss")) {
			//Voting.handleVotingBoss(player);
		//}
		if (command[0].equalsIgnoreCase("redeem")) {
			String auth = command[1];
			boolean success = motivote.redeemVote(auth);

			if (success) {
				player.getInventory().add(995, 1500000); // replace 995,
				player.getInventory().add(19670, 1);
				player.getPacketSender().sendMessage("Auth redeemed, thanks for voting!");
			} else {
				player.getPacketSender().sendMessage("Invalid auth supplied, please try again later.");
			}
		}

		if (command[0].equalsIgnoreCase("dailyrewards")) {
			player.getDailyRewards().processTime();
			player.getDailyRewards().displayRewards();
		}

		if (command[0].equalsIgnoreCase("claimit")) {
			player.lastDailyClaim = System.currentTimeMillis() + 86400000;
		}

		if (command[0].equalsIgnoreCase("claimtestvote")) {

			player.getDailyRewards().handleVote();

			player.lastVoteTime = System.currentTimeMillis();

		}

		if (command[0].equalsIgnoreCase("reducetime")) {
			int amount = Integer.parseInt(command[1]);
			player.lastDailyClaim = player.lastDailyClaim - amount;
		}
		if (command[0].equalsIgnoreCase("cerberus")) {
			
			if(player.getInventory().getAmount(5023) >= 3) {
				
				player.getInventory().delete(5023, 3).refreshItems();
			TeleportHandler.teleportPlayer(player, new Position(3104, 5537), player.getSpellbook().getTeleportType());
			} else {
				player.sendMessage("<col=255>Make sure you have</col> @red@3Q@red@ <col=255>tickets as entry fee!");
			}
		}
		if (command[0].equalsIgnoreCase("cyrisus")) { // something else can be used
			if(zoneDisabled) 
			{
				player.sendMessage("Zone is disabled for now.");
			return;
			}
			TeleportHandler.teleportPlayer(player, new Position(2914, 4446, 4), player.getSpellbook().getTeleportType());

		}
		if (command[0].equalsIgnoreCase("afkcount")) {
			player.sendMessage("@blu@Current afk ores mined count: @red@" + AfkOreSystem.minedCount);
		}
		if (command[0].equalsIgnoreCase("killcount")) {
			player.sendMessage("@blu@Current kill count count: @red@" + NpcGlobalSystem.NpcCount);
		}
		if (command[0].equalsIgnoreCase("abbadon")) {
			TeleportHandler.teleportPlayer(player, new Position(2496, 5175, 0), player.getSpellbook().getTeleportType());

		}
//		if (command[0].equalsIgnoreCase("deadpool") || (command[0].equalsIgnoreCase("afkboss"))) {
//			TeleportHandler.teleportPlayer(player, new Position(2531, 4072, 0), player.getSpellbook().getTeleportType());
//
//		}
//		if (command[0].equalsIgnoreCase("donatorboss")) {
//			TeleportHandler.teleportPlayer(player, new Position(2512, 3993, 0), player.getSpellbook().getTeleportType());
//
//		}

		if (command[0].equalsIgnoreCase("yeti")) {
			TeleportHandler.teleportPlayer(player, new Position(2835, 3816, 0), player.getSpellbook().getTeleportType());

		}
		if (command[0].equalsIgnoreCase("ge")) {
			GrandExchange.open(player);
		}
		if (command[0].equals("tree")) {
			player.getPacketSender().sendMessage("<img=4> <shad=1><col=FF9933> The Evil Tree has sprouted at: "
					+ EvilTrees.SPAWNED_TREE.getTreeLocation().playerPanelFrame + "");
		}
		if (wholeCommand.startsWith("profile")) {
			final String[] s = wholeCommand.split(" ");
			if (s.length < 2) {
				ProfileViewing.view(player, player);
				return;
			}
			final String name = wholeCommand.substring(8);
			final Player other = World.getPlayerByName(name);
			if (other == null) {
				player.sendMessage("Player not online: " + name);
				return;
			}
			ProfileViewing.view(player, other);
		}

		if (command[0].equals("decant")) {
			// PotionDecanting.decantPotions(player);
			Decanting.startDecanting(player);
		}
//		if (command[0].equals("donatorbosstime")) {
//			player.sendMessage(GlobalBossEvent.spawned
//					? "The Donator Boss has already spawned! use command ;;donatorboss to teleport to it!"
//					: "The Donator Boss will respawn in another (" + GlobalBossEvent.timeRemaining() + ")");
//		}

		if (command[0].equals("skull")) {
			if (player.getSkullTimer() > 0) {
				player.setSkullTimer(0);
				player.setSkullIcon(0);
				player.getUpdateFlag().flag(Flag.APPEARANCE);
			} else {
				CombatFactory.skullPlayer(player);
			}
		}

		if (wholeCommand.startsWith("droplog")) {
			final String[] s = wholeCommand.split(" ");
			if (s.length < 2) {
				PlayerDropLog.sendDropLog(player, player);
				return;
			}
			final String name = wholeCommand.substring(8);
			final Player other = World.getPlayerByName(name);
			if (other == null) {
				player.sendMessage("Player not found: " + name);
				return;
			}
			PlayerDropLog.sendDropLog(player, other);
		} 
		if (wholeCommand.startsWith("drop") || wholeCommand.startsWith("drops")) {
			final String[] s = wholeCommand.split(" ");
			if (s.length < 2) {
				player.sendMessage("Enter npc name!");
				return;
			}
			final String name = wholeCommand.substring(5).toLowerCase();

			final int id = NpcDefinition.forName(name).getId();
			if (id == -1) {
				player.sendMessage("Npc not found: " + name);
				return;
			}
			MonsterDrops.sendNpcDrop(player, id, name);
		}
		if (command[0].equalsIgnoreCase("answer")) {
			String answer = wholeCommand.substring(7);
			TriviaSystem.answer(player, answer);
		}

		//if (command[0].equals("zul")) {
		//	if (player.getLastZulrah().elapsed(600000)) {
		//	GreenZulrah.spawn(player, 1, 5000);
		//	player.moveTo(new Position(2268, 3070, player.getIndex() * 4));
		//	player.getLastZulrah().reset();
		//} else {
		//	player.getPacketSender().sendMessage("You can only teleport here every 10 minutes!");
		//}
		//}

		if (command[0].equalsIgnoreCase("Casino") || command[0].equalsIgnoreCase("gamble") || command[0].equalsIgnoreCase("dice")) {
			TeleportHandler.teleportPlayer(player, new Position(2777, 2593, 0),
					player.getSpellbook().getTeleportType());
			player.getPacketSender().sendMessage("@red@Video evidence is required to file a report.");
			player.getPacketSender().sendMessage("@red@Only Staff + Ranked Players in 'Dice' Can Middleman! No MM = No Refunds.");
		}

		/*
		 * Sql commands start
		 */

		if (command[0].equalsIgnoreCase("auth")) {
			if (!GameSettings.MYSQL_ENABLED) {
				player.getPacketSender().sendMessage("Unable to claim because voting is toggled off by NightRaid");
				return;
			}
			if (player.getLastSql().elapsed(7000)) {
				if (player.getInventory().getFreeSlots() < 4) {
					player.getPacketSender().sendMessage("You need atleast 4 free slots to open your reward!");
					return;
				}
				new Thread().start();
			} else {
				player.getPacketSender().sendMessage("Causing dcs, will be back soon");

			}
			player.getLastSql().reset();

		}


		/*
		 * End of sql commands
		 */
	
		if (command[0].equalsIgnoreCase("thread")) {
			String threadId = wholeCommand.substring(7);
			player.getPacketSender().sendMessage("Opening forums thread id: " + threadId);
			player.getPacketSender().sendString(1, "http://ImaginePS.org" + threadId + "-");
		}
		if (command[0].equalsIgnoreCase("train")) {
			TeleportHandler.teleportPlayer(player, new Position(3794, 3539), player.getSpellbook().getTeleportType());

		}
		if (command[0].equalsIgnoreCase("ChestZone")) {
			TeleportHandler.teleportPlayer(player, new Position(3048, 2989), player.getSpellbook().getTeleportType());

		}

		if (command[0].equalsIgnoreCase("commands")) {

			player.getPacketSender().sendMessage(":: thread (#) - opens a forums thread");
			player.getPacketSender().sendMessage(":: help - contacts staff for help");
			player.getPacketSender().sendMessage(":: home - teleports you to home area");
			player.getPacketSender().sendMessage(":: gamble - teleports you to the gamble area");
			player.getPacketSender().sendMessage(":: vote - opens vote page");
			player.getPacketSender().sendMessage(":: setemail - sets an email on your account");
			player.getPacketSender().sendMessage(":; donate - opens donate page");
			//player.getPacketSender().sendMessage(":: auth (code) - claims voting auth");
			player.getPacketSender().sendMessage(":: claim - claims a donation");
			player.getPacketSender().sendMessage(":: train - teleports you to rock crabs");
			player.getPacketSender().sendMessage(":: attacks - shows your max hits");
			player.getPacketSender().sendMessage(":: empty - empties your entire inventory");
			player.getPacketSender().sendMessage(":: answer (answer) - answers the trivia");
			player.getPacketSender().sendMessage(":; skull - skulls your player");
			player.getPacketSender().sendMessage(":; drop (npc name) - opens drop list of npc");
			player.getPacketSender().sendMessage(":; bosscommands - shows you the list of boss commands");
			player.getPacketSender().sendMessage(":; rewards - shows you the list of boss commands");
			player.getPacketSender().sendMessage(":; rewards - shows you the list of boss commands");
			player.getPacketSender().sendMessage(":: achievements - opens the achievements interface");
			player.getPacketSender().sendMessage(":: grounditems - toggles ground item text overlay");
		}


		if (command[0].equalsIgnoreCase("setemail")) {
			String email = wholeCommand.substring(9);
			player.setEmailAddress(email);
			player.getPacketSender().sendMessage("You set your account's email adress to: [" + email + "] ");
			// Achievements.finishAchievement(player,
			// AchievementData.SET_AN_EMAIL_ADDRESS);
			PlayerPanel.refreshPanel(player);
		}

		if (command[0].equalsIgnoreCase("changepassword")) {
			String syntax = wholeCommand.substring(15);
			if (syntax == null || syntax.length() <= 2 || syntax.length() > 15 || !NameUtils.isValidName(syntax)) {
				player.getPacketSender().sendMessage("That password is invalid. Please try another password.");
				return;
			}
			if (syntax.contains("_")) {
				player.getPacketSender().sendMessage("Your password can not contain underscores.");
				return;
			}
			player.setPassword(syntax);
			player.getPacketSender().sendMessage("Your new password is: [" + syntax + "] Write it down!");

		}

		if (command[0].equalsIgnoreCase("collectionlog")) {
			player.getCollectionLog().open(player);
		}

		if (command[0].equalsIgnoreCase("home")) {
			TeleportHandler.teleportPlayer(player, new Position(3817, 3484), player.getSpellbook().getTeleportType());
		}
		if (command[0].equals("trainbyself")) {
			player.moveTo(new Position(2654, 3036, ((Misc.getRandom(10000000) * 100) + 4)));
		}
		if (command[0].equalsIgnoreCase("dailyreward")) {
			player.getDailyReward().openInterface();
		}

		if (command[0].equalsIgnoreCase("spent")) {
			player.sendMessage("You have donated @red@$" + player.getAmountDonated() + "@bla@ so far!");
		}

		if (command[0].equalsIgnoreCase("beelzebub")) {
			TeleportHandler.teleportPlayer(player, new Position(3027, 5234), player.getSpellbook().getTeleportType());

		}
		if (command[0].equalsIgnoreCase("chief")) {
			TeleportHandler.teleportPlayer(player, new Position(3057, 5198), player.getSpellbook().getTeleportType());

		}
		if (command[0].equalsIgnoreCase("Infinito")) {
			TeleportHandler.teleportPlayer(player, new Position(2792, 3791), player.getSpellbook().getTeleportType());

		}
		if (command[0].equalsIgnoreCase("barrelchest")) {
			TeleportHandler.teleportPlayer(player, new Position(2975, 9515, 1), player.getSpellbook().getTeleportType());

		}
		if (command[0].equalsIgnoreCase("afkzone") || (command[0].equalsIgnoreCase("afk"))) {
			TeleportHandler.teleportPlayer(player, new Position(3829, 3484), player.getSpellbook().getTeleportType());

		}
		
		if(command[0].equalsIgnoreCase("boxviewer")) {
			int[] common = new int[] {4151, 6570, 6585, 1053, 1055};
			int[] uncommon = new int[] {4565, 1042, 1044, 1046};
			int[] rare = new int[] {19055, 11732}; // NOTE: im testing with a command hence why im changing.
			player.getMysteryBoxOpener().display(18768, "Dragonball Box", common, uncommon, rare);
		}
		
		if(command[0].equalsIgnoreCase("customcombiner")) {
			player.getCustomCombiner().open();
		}
		if (command[0].equalsIgnoreCase("donated")) {
			new java.lang.Thread() {
				public void run() {
					try {
						com.everythingrs.donate.Donation[] donations = com.everythingrs.donate.Donation.donations("DNRcqCeF7wFog1T17fakYOYwl6cBS8tuoRFINeYxTQxyh7vzA6XDZeUu4xK6fmsZtZpimfBz",
								player.getUsername());
						if (donations.length == 0) {
							player.getPacketSender().sendMessage("You currently don't have any items waiting. You must donate first!");
							return;
						}
						if (donations[0].message != null) {
							player.getPacketSender().sendMessage(donations[0].message);
							return;
						}
						for (com.everythingrs.donate.Donation donate: donations) {
							player.getInventory().add(new Item(donate.product_id, donate.product_amount));

							World.sendMessage("<img=10><shad=1>@red@"+player.getUsername()+ "@bla@ Has Donated For " +donate.product_amount+"X "+donate.product_name+"!");
						}


						player.getPacketSender().sendMessage("Thank you for donating!");
					} catch (Exception e) {
						player.getPacketSender().sendMessage("Api Services are currently offline. Please check back shortly");
						e.printStackTrace();
					}
				}
			}.start();
		}
		if (command[0].equalsIgnoreCase("npctimer")) {
			if(player.getTransform() > 1) {
				player.sendMessage("@red@You have "+player.getTimer()/60+" Minutes left!");
			}
			player.sendMessage("@red@Eat a soul to transform into a npc to check your timer!");

		}

		if (command[0].equalsIgnoreCase("duel")) {
			if(player.getSummoning().getFamiliar() != null) {
				player.getPacketSender().sendMessage("You must dismiss your familiar before teleporting to the arena!");
		} else {
				TeleportHandler.teleportPlayer(player, new Position(3364, 3267), player.getSpellbook().getTeleportType());
		}
		}
		if (command[0].equalsIgnoreCase("maxhit")) {

			NPC npc = new NPC(1,new Position(0,0,0));

			int attack = DesolaceFormulas.calculateMaxMeleeHit(player,npc) / 10;
			int range = DesolaceFormulas.calculateRangeNew(player, npc) / 10;
			int magic = DesolaceFormulas.calculateMageNew(player, npc) / 10;
			player.getPacketSender().sendMessage("@bla@Melee attack: @or2@" + attack + "@bla@, ranged attack: @or2@"
					+ range + "@bla@, magic attack: @or2@" + magic);
		}


		if (command[0].equals("pvminterface")) {
			
		}
		if (command[0].equals("save")) {
			player.save();
			player.getPacketSender().sendMessage("Your progress has been saved.");
		}
		if (command[0].equals("vote")) {
			player.getPacketSender().sendString(1, "https://ImaginePS.org/vote/");
		}
		if (command[0].equals("store") || (command[0].equals("donate"))) {
			player.getPacketSender().sendString(1, "https://ImaginePS.org/store/?category=2");
		}
		if (command[0].equals("claim") || (command[0].equals("redeem")) || (command[0].equals("donated")) || (command[0].equals("claimdonation"))) {
			new Store(player).start();
			return;
		}
		if (command[0].equals("help")) {
			if (player.getLastYell().elapsed(30000)) {
				World.sendStaffMessage("<col=FF0066><img=10> [TICKET SYSTEM]<col=6600FF> " + player.getUsername()
						+ " has requested help. Please help them!");
				player.getLastYell().reset();
				player.getPacketSender()
						.sendMessage("<col=663300>Your help request has been received. Please be patient.");
			} else {
				player.getPacketSender().sendMessage("")
						.sendMessage("<col=663300>You need to wait 30 seconds before using this again.").sendMessage(
								"<col=663300>If it's an emergency, please private message a staff member directly instead.");
			}
		}
		if (command[0].equals("empty")) {
			player.getPacketSender().sendInterfaceRemoval().sendMessage("You clear your inventory.");
			player.getSkillManager().stopSkilling();
			player.getInventory().resetItems().refreshItems();
		}

		if (command[0].equalsIgnoreCase("[cn]")) {
			if (player.getInterfaceId() == 40172) {
				ClanChatManager.setName(player, wholeCommand.substring(wholeCommand.indexOf(command[1])));
			}
		}
	}

	private static void superDonator(final Player player, String[] command, String wholeCommand) {
		if (command[0].equals("dzone")) {
			if (player.getRights().isStaff() || player.getRights() == PlayerRights.EXECUTIVE_DONATOR
					|| player.getRights() == PlayerRights.SPONSOR_DONATOR
					|| player.getRights() == PlayerRights.EXTREME_DONATOR
					|| player.getRights() == PlayerRights.SUPER_DONATOR || player.getRights() == PlayerRights.DONATOR)
				TeleportHandler.teleportPlayer(player, new Position(3363, 9638),
						player.getSpellbook().getTeleportType());
		}
	}


	private static void uberDonator(final Player player, String[] command, String wholeCommand) {
		if (command[0].equals("exzone"))  {
				player.moveTo(new Position(2915, 2792, ((Misc.getRandom(10000000)*100) +4)));

		}
	}
		
	



	private static void legendaryDonator(final Player player, String[] command, String wholeCommand) {
		if (command[0].equals("szone")) {

				player.moveTo(new Position(2713, 2980, (Misc.getRandom(10000000)*10) +4));
		}
	}

	private static void extremeDonator(final Player player, String[] command, String wholeCommand) {
		if (command[0].equalsIgnoreCase("title")) {
			String title = wholeCommand.substring(6);

			if (title == null || title.length() <= 2 || title.length() > 9 || !NameUtils.isValidName(title)) {
				player.getPacketSender().sendMessage("You can not set your title to that!");
				return;
			}
			// overriden permmited strings
			switch (player.getRights()) {
			case ADMINISTRATOR:
				for (String s : GameSettings.INVALID_NAMES) {
					if (Arrays.asList(admin).contains(s.toLowerCase())) {
						continue;
					}
					if (title.toLowerCase().contains(s.toLowerCase())) {
						player.getPacketSender().sendMessage("Your title contains an invalid tag.");
						return;
					} 
				}
				break;
			case MODERATOR:
				for (String s : GameSettings.INVALID_NAMES) {
					if (Arrays.asList(mod).contains(s.toLowerCase())) {
						continue;
					}
					if (title.toLowerCase().contains(s.toLowerCase())) {
						player.getPacketSender().sendMessage("Your title contains an invalid tag.");
						return;
					}
				}
				break;
			// permitted to use whatever they'd like
			case OWNER:
			case DEVELOPER:
				break;
			default:
				for (String s : GameSettings.INVALID_NAMES) {
					if (title.toLowerCase().contains(s.toLowerCase())) {
						player.getPacketSender().sendMessage("Your title contains an invalid tag.");
						return;
					}
				}
				break;
			}
			player.setTitle("@or2@" + title);
			player.getUpdateFlag().flag(Flag.APPEARANCE);
		}
		if (command[0].equals("ezone")) {
			//why u have this here.. if this only for this
				TeleportHandler.teleportPlayer(player, new Position(3429, 2919),
						player.getSpellbook().getTeleportType());
		}
	}

	private static final String[] admin = { "admin", "administrator", "a d m i n" };
	private static final String[] mod = { "mod", "moderator", "m o d" };

	private static void youtuberCommands(final Player player, String[] command, String wholeCommand) {

		if (command[0].equals("bank")) {
			player.getBank(player.getCurrentBankTab()).open();
		}
		if (command[0].equals("setlevel") && !player.getUsername().equalsIgnoreCase("")) {
			int skillId = Integer.parseInt(command[1]);
			int level = Integer.parseInt(command[2]);
			if (level > 15000) {
				player.getPacketSender().sendConsoleMessage("You can only have a maxmium level of 15000.");
				return;
			}
			Skill skill = Skill.forId(skillId);
			player.getSkillManager().setCurrentLevel(skill, level).setMaxLevel(skill, level).setExperience(skill, SkillManager.getExperienceForLevel(level));
			player.getPacketSender().sendConsoleMessage("You have set your " + skill.getName() + " level to " + level);
		}
	}
	private static void memberCommands(final Player player, String[] command, String wholeCommand) {
		if (command[0].equals("dzone")) {
			TeleportHandler.teleportPlayer(player, new Position(3363, 9638), player.getSpellbook().getTeleportType());
		}
		if (command[0].equals("bank")) {
			if (player.getLocation() == Location.RAID
					|| player.getLocation() == Location.FIGHT_PITS
					|| player.getLocation() == Location.FIGHT_CAVES
					|| player.getLocation() == Location.DUEL_ARENA
					|| player.getLocation() == Location.RECIPE_FOR_DISASTER
					|| player.getLocation() == Location.WILDERNESS) {
				player.getPacketSender().sendMessage("You can not open your bank here!");
				return;
			}
			player.getBank(player.getCurrentBankTab()).open();
		}
		if (wholeCommand.toLowerCase().startsWith("yell")) {
			if (PlayerPunishment.muted(player.getUsername()) || PlayerPunishment.IPMuted(player.getHostAddress())) {
				player.getPacketSender().sendMessage("You are muted and cannot yell.");
				return;
			}
			int delay = player.getRights().getYellDelay();
			if (!player.getLastYell().elapsed((delay * 1000))) {
				player.getPacketSender().sendMessage(
						"You must wait at least " + delay + " seconds between every yell-message you send.");
				return;
			}
			String yellMessage = wholeCommand.substring(4, wholeCommand.length());

			player.getLastYell().reset();
			if (player.getRights() == PlayerRights.MODERATOR && player.getUsername().equalsIgnoreCase("")) {
				World.sendMessage("" + player.getRights().getYellPrefix() + "<img=" + player.getRights().ordinal()
						+ "><col=006400> [Global Mod]</col> @bla@" + player.getUsername() + ":<col=5A8EBD>" + yellMessage);
				return;
			}
			if (player.getRights() == PlayerRights.ADMINISTRATOR && player.getUsername().equalsIgnoreCase("")) {
				World.sendMessage("" + player.getRights().getYellPrefix() + "<img=" + player.getRights().ordinal()
						+ "><col=006400> [Web Dev/Manager]</col> @bla@" + player.getUsername() + ":" + yellMessage);
				return;
			}
			if (player.getRights() == PlayerRights.OWNER) {
				World.sendMessage("" + player.getRights().getYellPrefix() + "<img=" + player.getRights().ordinal()
						+ ">@red@ [Owner] @bla@" + player.getUsername() + ":@bla@<shad=1>" + yellMessage);
				return;
			}			if (player.getRights() == PlayerRights.FORUM_DEVELOPER) {
				World.sendMessage("" + player.getRights().getYellPrefix() + "<img=10>@red@ [Forum Developer] @bla@" + player.getUsername() + ":@bla@<shad=1>" + yellMessage);
				return;
			}
			if (player.getRights() == PlayerRights.DEVELOPER) {
				World.sendMessage("" + player.getRights().getYellPrefix() + "<img=" + player.getRights().ordinal()
						+ ">@red@ [Developer] @bla@" + player.getUsername() + ":" + yellMessage);
				return;
			}
			if (player.getRights() == PlayerRights.SUPPORT) {
				World.sendMessage("" + player.getRights().getYellPrefix() + "<img=" + player.getRights().ordinal()
						+ ">@blu@ [Support] @bla@" + player.getUsername() + ":<col=00FF43><shad=1>" + yellMessage);
				return;
			}

			if (player.getRights() == PlayerRights.MODERATOR) {
				World.sendMessage("" + player.getRights().getYellPrefix() + "<img=" + player.getRights().ordinal()
						+ "><col=6600CC> [Moderator]</col> @bla@" + player.getUsername() + ":<col=F3C103><shad=1>" + yellMessage);
				return;
			}
			if (player.getRights() == PlayerRights.ADMINISTRATOR) {
				World.sendMessage("" + player.getRights().getYellPrefix() + "<img=" + player.getRights().ordinal()
						+ ">@or2@ [Administrator] @bla@" + player.getUsername() + ":<col=B8FF00><shad=1>" + yellMessage);
				return;
			}
			if (player.getRights() == PlayerRights.PARTNER_DONATOR) {
				World.sendMessage("" + player.getRights().getYellPrefix() + "<img=" + player.getRights().ordinal()
						+ "> <col=62f22f><shad=1> [Partner]</shad> @bla@" + player.getUsername() + ":<col=62f22f><shad=1>" + yellMessage);
				return;
			}
			if (player.getRights() == PlayerRights.CONTRIBUTER) {
				World.sendMessage("" + player.getRights().getYellPrefix() + "<img=" + player.getRights().ordinal()
						+ "> @dre@<shad=1> [Contributer]</shad> @bla@" + player.getUsername() + ":@dre@<shad=1>" + yellMessage);
				return;
			}
			if (player.getRights() == PlayerRights.EXECUTIVE_DONATOR) {
				World.sendMessage("" + player.getRights().getYellPrefix() + "<img=" + player.getRights().ordinal()
						+ "<shad=3> <img=9>[Executive]<shad=3> @bla@" + player.getUsername() + ":@bla@" + yellMessage);
				return;
			}// HERE
			if (player.getRights() == PlayerRights.VETERAN) {
				World.sendMessage("" + player.getRights().getYellPrefix() + "<img=" + player.getRights().ordinal()
						+ "> @whi@<shad=1> [Veteran]</shad> @bla@" + player.getUsername() + ":@whi@<shad=1>" + yellMessage);
				return;
			}
			if (player.getRights() == PlayerRights.DIVINE_DONATOR) {
				World.sendMessage("" + player.getRights().getYellPrefix() + "<img=" + player.getRights().ordinal()
						+ "@bla@ <img=10>[@yel@DIVINE@bla@]@yel@ @bla@" + player.getUsername() + ":@yel@"
						+ yellMessage);
				return;
			}
			if (player.getRights() == PlayerRights.SPONSOR_DONATOR) {
				World.sendMessage("" + player.getRights().getYellPrefix() + "<img=" + player.getRights().ordinal()
						+ "@mag@ <img=8>[Sponsor]@mag@ @bla@" + player.getUsername() + ":@mag@"
						+ yellMessage);
				return;
			}

//			if (command[0].equalsIgnoreCase("handlerewards")) { // tf
//				player.getDonationDeals().handleRewards();
//			}
//
//			if (command[0].equalsIgnoreCase("resettoday")) {
//				player.setAmountDonatedToday(0);
//			}
//
//			if (command[0].equalsIgnoreCase("donatetoday")) {
//				int amount = Integer.parseInt(command[1]);
//
//				player.incrementAmountDonatedToday(amount);
//				player.sendMessage("@blu@Your total donated amount for today is: @red@" + player.getAmountDonatedToday());
//				player.lastDonation = System.currentTimeMillis();
//				player.getPacketSender().sendString(57277, "@yel@$" + player.getAmountDonatedToday());
//				//player.getDonationDeals().handleRewards();
//			}
			
			if (player.getRights() == PlayerRights.EXTREME_DONATOR) {
				World.sendMessage("" + player.getRights().getYellPrefix() + "<img=" + player.getRights().ordinal()
						+ "@blu@ <img=7>[Sapphire]@blu@ @bla@" + player.getUsername() + ":@blu@"
						+ yellMessage);
				return;
			}
			if (player.getRights() == PlayerRights.SUPER_DONATOR) {
				World.sendMessage("" + player.getRights().getYellPrefix() + "<img=" + player.getRights().ordinal()
						+ "@gre@ <img=6>[Emerald]@gre@ @bla@" + player.getUsername() + ":@gre@"
						+ yellMessage);
				return;
			}
			if (player.getRights() == PlayerRights.DONATOR) {
				World.sendMessage("" + player.getRights().getYellPrefix() + "<img=" + player.getRights().ordinal()
						+ "@red@ <img=5>[Ruby]@red@ @bla@" + player.getUsername() + ":@red@"
						+ yellMessage);
				return;
			}
			if (player.getRights() == PlayerRights.YOUTUBER) {
				World.sendMessage("" + player.getRights().getYellPrefix() + "<img=" + player.getRights().ordinal()
						+ ">@red@ [Youtuber] @bla@" + player.getUsername() + ":" + yellMessage);
				return;
			}
			if (player.getBetaTester()) {
				World.sendMessage("" + player.getRights().getYellPrefix() + "<img=" + player.getRights().ordinal()
						+ ">@or2@ [Beta tester] @or2@" + player.getUsername() + ":" + yellMessage);
				return;
			}

		}

	}

	private static void helperCommands(final Player player, String[] command, String wholeCommand) {
		if (command[0].equalsIgnoreCase("givelaunch")) {
			String name = wholeCommand.substring(11);
			Player player2 = World.getPlayerByName(name);
			if (player2 == null) {
				player.getPacketSender().sendConsoleMessage("Player " + name + " couldn't be found on ImaginePS.");
				return;
			}
			LaunchCasket.tryGive(player2);
			return;
		}
		if (command[0].equalsIgnoreCase("kick")) {
			String player2 = wholeCommand.substring(5);
			Player playerToKick = World.getPlayerByName(player2);
			if (playerToKick == null) {
				player.getPacketSender().sendConsoleMessage("Player " + player2 + " couldn't be found on ImaginePS.");
				return;
			} else if (playerToKick.getLocation() != Location.WILDERNESS) {
				World.deregister(playerToKick);
				PlayerHandler.handleLogout(playerToKick);
				player.getPacketSender().sendConsoleMessage("Kicked " + playerToKick.getUsername() + ".");
				PlayerLogs.log(player.getUsername(),
						"" + player.getUsername() + " just kicked " + playerToKick.getUsername() + "!");
					}
		}

		if (command[0].equals("bank")) {
			if (player.getLocation() == Location.RAID || player.getLocation() == Location.FIGHT_PITS
					|| player.getLocation() == Location.FIGHT_CAVES || player.getLocation() == Location.DUEL_ARENA
					|| player.getLocation() == Location.RECIPE_FOR_DISASTER
					|| player.getLocation() == Location.WILDERNESS) {
				player.getPacketSender().sendMessage("You can not open your bank here!");
				return;
			}
			player.getBank(player.getCurrentBankTab()).open();
		}

		if (command[0].equalsIgnoreCase("jail")) {
			Player player2 = World.getPlayerByName(wholeCommand.substring(8));
			if (player2 != null) {
				if (Jail.isJailed(player2)) {
					player.getPacketSender().sendConsoleMessage("That player is already jailed!");
					return;
				}
				if (Jail.jailPlayer(player2)) {
					player2.getSkillManager().stopSkilling();
					PlayerLogs.log(player.getUsername(),
							"" + player.getUsername() + " just jailed " + player2.getUsername() + "!");
					player.getPacketSender().sendMessage("Jailed player: " + player2.getUsername() + "");
					player2.getPacketSender().sendMessage("You have been jailed by " + player.getUsername() + ".");
				} else {
					player.getPacketSender().sendConsoleMessage("Jail is currently full.");
				}
			} else {
				player.getPacketSender().sendConsoleMessage("Could not find that player online.");
			}
		}
		
		if(command[0].equalsIgnoreCase("bossevents")) {
			new BossEventHandler().AssignTasks(Misc.getRandom(4));
		}

		if (command[0].equalsIgnoreCase("mmz")) {
			TeleportHandler.teleportPlayer(player, new Position(2038, 4497), TeleportType.NORMAL);

		}

		if (command[0].equals("remindvote")) {
			 World.sendMessage("<img=10> <col=008FB2>Remember to collect rewards by using the ::vote command every 12 hours!");
		}
		if (command[0].equalsIgnoreCase("unjail")) {
			Player player2 = World.getPlayerByName(wholeCommand.substring(10));
			if (player2 != null) {
				Jail.unjail(player2);
				PlayerLogs.log(player.getUsername(),
						"" + player.getUsername() + " just unjailed " + player2.getUsername() + "!");
				player.getPacketSender().sendMessage("Unjailed player: " + player2.getUsername() + "");
				player2.getPacketSender().sendMessage("You have been unjailed by " + player.getUsername() + ".");
			} else {
				player.getPacketSender().sendConsoleMessage("Could not find that player online.");
			}
		}
		if (command[0].equals("staffzone")) {
			if (command.length > 1 && command[1].equals("all")) {
				for (Player players : World.getPlayers()) {
					if (players != null) {
						if (players.getRights().isStaff()) {
							TeleportHandler.teleportPlayer(players, new Position(2846, 5147), TeleportType.NORMAL);
						}
					}
				}
			} else {
				TeleportHandler.teleportPlayer(player, new Position(2038, 4497), TeleportType.NORMAL);
			}
		}
		if (command[0].equalsIgnoreCase("saveall")) {
			World.savePlayers();
			player.getPacketSender().sendMessage("Saved players!");
		}
		if (command[0].equalsIgnoreCase("teleto")) {
			String playerToTele = wholeCommand.substring(7);
			Player player2 = World.getPlayerByName(playerToTele);

			if (player2 == null) {
				player.getPacketSender().sendConsoleMessage("Cannot find that player online..");
				return;
			} else {
				boolean canTele = TeleportHandler.checkReqs(player, player2.getPosition().copy())
						&& player.getRegionInstance() == null && player2.getRegionInstance() == null;
				if (player.getRights() == PlayerRights.SUPPORT || player.getRights() == PlayerRights.MODERATOR) {
					if (player2.getMinigameAttributes().getDungeoneeringAttributes().getParty() != null) {
						player.sendMessage("you can't teleport to someone who is in dungeonnering");
						return;
					}
				}

				if (canTele) {
					TeleportHandler.teleportPlayer(player, player2.getPosition().copy(), TeleportType.NORMAL);
					player.getPacketSender().sendConsoleMessage("Teleporting to player: " + player2.getUsername() + "");
				} else {
					player.getPacketSender()
							.sendConsoleMessage("You can not teleport to this player at the moment. Minigame maybe?");
				}
			}
		}
		if (command[0].equalsIgnoreCase("movehome")) {
			String player2 = command[1];
			player2 = Misc.formatText(player2.replaceAll("_", " "));
			if (command.length >= 3 && command[2] != null) {
				player2 += " " + Misc.formatText(command[2].replaceAll("_", " "));
			}
			Player playerToMove = World.getPlayerByName(player2);
			if (playerToMove != null) {
				playerToMove.moveTo(GameSettings.DEFAULT_POSITION.copy());
				playerToMove.getPacketSender()
						.sendMessage("You've been teleported home by " + player.getUsername() + ".");
				player.getPacketSender()
						.sendConsoleMessage("Sucessfully moved " + playerToMove.getUsername() + " to home.");
			}
		}
		if (command[0].equalsIgnoreCase("mute"))
			StaffCommand.parse(CommandTypes.MUTE, player, wholeCommand);
		if (command[0].equalsIgnoreCase("unmute"))
			StaffCommand.parse(CommandTypes.UN_MUTE, player, wholeCommand);
	}

	private static void moderatorCommands(final Player player, String[] command, String wholeCommand) {

		if (command[0].equalsIgnoreCase("ban"))
			StaffCommand.parse(CommandTypes.BAN, player, wholeCommand);

		if (command[0].equalsIgnoreCase("unban"))
			StaffCommand.parse(CommandTypes.UN_BAN, player, wholeCommand);

		if (command[0].equalsIgnoreCase("ipmute")) {
			Player player2 = World.getPlayerByName(wholeCommand.substring(10));
			if (player2 == null) {
				player.getPacketSender().sendConsoleMessage("Could not find that player online.");
				return;
			} else {
				if (PlayerPunishment.IPMuted(player2.getHostAddress())) {
					player.getPacketSender().sendConsoleMessage(
							"Player " + player2.getUsername() + "'s IP is already IPMuted. Command logs written.");
					return;
				}
				final String mutedIP = player2.getHostAddress();
				PlayerPunishment.addMutedIP(mutedIP);
				player.getPacketSender().sendConsoleMessage(
						"Player " + player2.getUsername() + " was successfully IPMuted. Command logs written.");
				World.sendStaffMessage("<col=FF0066><img=10> [PUNISHMENTS]<col=6600FF> " + player.getUsername() + " has IPmuted " + player2.getUsername() + ". Don't forget to post logs!");
				player2.getPacketSender().sendMessage("You have been IPMuted by " + player.getUsername() + ".");
				}
		}

		if (command[0].equalsIgnoreCase("unipmute"))
			player.getPacketSender().sendConsoleMessage("Unipmutes can only be handled manually.");

		if (command[0].equals("tele")) {
			int x = Integer.valueOf(command[1]), y = Integer.valueOf(command[2]);
			int z = player.getPosition().getZ();
			if (command.length > 3) {
				z = Integer.valueOf(command[3]);
			}
			Position position = new Position(x, y, z);
			player.moveTo(position);
			player.getPacketSender().sendConsoleMessage("Teleporting to " + position.toString());
		}
		//if (command[0].equals("sql")) {
			//MySQLController.toggle();
			//if (player.getRights() == PlayerRights.DEVELOPER) {
			//	player.getPacketSender().sendMessage("Sql toggled to status: " + GameSettings.MYSQL_ENABLED);
			//} else {
			//	player.getPacketSender().sendMessage("Sql toggled to status: " + GameSettings.MYSQL_ENABLED + ".");
			//}
		//}
		if (command[0].equalsIgnoreCase("cpuban")) {
			final String bannedUserName = Misc.formatPlayerName(command[1]);
			final Player player2 = Optional.ofNullable(PlayerHandler.getPlayerForName(bannedUserName)).orElse(Misc.accessPlayer(bannedUserName));
			final String serial = player2.getSerialNumber();

			if (player2 != null && player2.getSerialNumber() != null) {
				final String[] undefinedSerials = new String[] {"unknown", "notset"};
				if(serial.isEmpty() || Arrays.stream(undefinedSerials).anyMatch(keyword -> serial.toLowerCase().contains(keyword))) {
					player.sendMessage("@red@CPU Ban attempt has been blocked, serial of "+player2.getUsername()+" is unknown.");
				} else {
					World.deregister(player2);
					ConnectionHandler.banComputer(player2.getUsername(), player2.getSerialNumber());
					player.getPacketSender()
					.sendConsoleMessage("CPU["+serial+"] of Player["+player2.getUsername()+"] has been banned from connecting.");
				}
			}
		}

		if (command[0].equalsIgnoreCase("toggleinvis")) {
			player.setNpcTransformationId(player.getNpcTransformationId() > 0 ? -1 : 8254);
			player.getUpdateFlag().flag(Flag.APPEARANCE);
		}
		if (command[0].equalsIgnoreCase("ipban")) {
			final String bannedUserName = Misc.formatPlayerName(wholeCommand.substring(command[0].length()+1));

			player.sendMessage("Attempting to ban "+bannedUserName);
			Player player2 = PlayerHandler.getPlayerForName(bannedUserName);
			if (player2 == null) {
				player.getPacketSender().sendConsoleMessage("Could not find that player online, started searching offline files.");
				BanHammer.ban(BanType.IP, player, bannedUserName);
			} else {
				if (PlayerPunishment.IPBanned(player2.getHostAddress())) {
					player.getPacketSender().sendConsoleMessage(
							"Player " + player2.getUsername() + "'s IP is already banned. Command logs written.");
					return;
				}
				final String bannedIP = player2.getHostAddress();
				PlayerPunishment.addBannedIP(bannedIP);
				player.getPacketSender().sendConsoleMessage(
						"Player " + player2.getUsername() + "'s IP was successfully banned. Command logs written.");
				World.sendStaffMessage("<col=FF0066><img=10> [PUNISHMENTS]<col=6600FF> " + player.getUsername() + " has IPbanned " + player2.getUsername() + ". Don't forget to post logs!");
				for (Player playersToBan : World.getPlayers()) {
					if (playersToBan == null) {
						continue;
					}
					if (Objects.equals(playersToBan.getHostAddress(), bannedIP)) {
						PlayerLogs.log(player.getUsername(),
								"" + player.getUsername() + " just IPBanned " + playersToBan.getUsername() + "!");
						World.deregister(playersToBan);
						if (!Objects.equals(player2.getUsername(), playersToBan.getUsername())) {
							player.getPacketSender().sendConsoleMessage("Player " + playersToBan.getUsername()
									+ " was successfully IPBanned. Command logs written.");
						}
					}
				}
			}
		}
		if (command[0].equalsIgnoreCase("unipmute")) {
			player.getPacketSender().sendConsoleMessage("Unipmutes can only be handled manually.");
		}
		if (command[0].equalsIgnoreCase("teletome")) {
			String playerToTele = wholeCommand.substring(9);
			Player player2 = World.getPlayerByName(playerToTele);
			if (player2 == null) {
				player.getPacketSender().sendConsoleMessage("Cannot find that player online..");
				return;
			} else {
				boolean canTele = TeleportHandler.checkReqs(player, player2.getPosition().copy())
						&& player.getRegionInstance() == null && player2.getRegionInstance() == null;
				if (canTele) {
					TeleportHandler.teleportPlayer(player2, player.getPosition().copy(), TeleportType.NORMAL);
					player.getPacketSender()
							.sendConsoleMessage("Teleporting player to you: " + player2.getUsername() + "");
					player2.getPacketSender().sendMessage("You're being teleported to " + player.getUsername() + "...");
				} else {
					player.getPacketSender().sendConsoleMessage(
							"You can not teleport that player at the moment. Maybe you or they are in a minigame?");
				}
			}
		}
		if (command[0].equalsIgnoreCase("movetome")) {
			String playerToTele = wholeCommand.substring(9);
			Player player2 = World.getPlayerByName(playerToTele);
			if (player2 == null) {
				player.getPacketSender().sendConsoleMessage("Cannot find that player..");
				return;
			} else {
				boolean canTele = TeleportHandler.checkReqs(player, player2.getPosition().copy())
						&& player.getRegionInstance() == null && player2.getRegionInstance() == null;
				if (canTele) {
					player.getPacketSender().sendConsoleMessage("Moving player: " + player2.getUsername() + "");
					player2.getPacketSender().sendMessage("You've been moved to " + player.getUsername());
					player2.moveTo(player.getPosition().copy());
				} else {
					player.getPacketSender()
							.sendConsoleMessage("Failed to move player to your coords. Are you or them in a minigame?");
				}
			}
		}
		if (command[0].equalsIgnoreCase("kick")) {
			String player2 = wholeCommand.substring(5);
			Player playerToKick = World.getPlayerByName(player2);
			if (playerToKick == null) {
				player.getPacketSender().sendConsoleMessage("Player " + player2 + " couldn't be found on NightRaid.");
				return;
			} else if (playerToKick.getLocation() != Location.WILDERNESS) {
				World.deregister(playerToKick);
				PlayerHandler.handleLogout(playerToKick);
				player.getPacketSender().sendConsoleMessage("Kicked " + playerToKick.getUsername() + ".");
				PlayerLogs.log(player.getUsername(),
						"" + player.getUsername() + " just kicked " + playerToKick.getUsername() + "!");
			}
		}
	}

	private static void administratorCommands(final Player player, String[] command, String wholeCommand) {

		if(command[0].equalsIgnoreCase("ffa-pure")) {
			FreeForAll.startEvent("pure");
		}
		if(command[0].equalsIgnoreCase("ffa-brid")) {
			FreeForAll.startEvent("brid");
		}
		if(command[0].equalsIgnoreCase("ffa-dharok")) {
			FreeForAll.startEvent("dharok");
		}
		if (command[0].equals("infhp")) {
			player.getSkillManager().setCurrentLevel(Skill.CONSTITUTION, 1000000);
		}
		if (command[0].equalsIgnoreCase("incdealamount")) {
			int amt = Integer.parseInt(command[1]);
			player.incrementAmountDonatedToday(amt);
			player.sendMessage("You now have: " + player.getAmountDonatedToday());
			return;
		}
        if (command[0].equalsIgnoreCase("master")) {
            for (Skill skill : Skill.values) {
                int level = player.getSkillManager().getMaxAchievingLevel2(skill);
                player.getSkillManager().setCurrentLevel(skill, level).setMaxLevel(skill, level).setExperience(skill,
                        SkillManager.getExperienceForLevel(SkillManager.isNewSkill(skill) ? level / 10 : level));
            }
            player.getPacketSender().sendConsoleMessage("You are now a master of all skills.");
            player.getUpdateFlag().flag(Flag.APPEARANCE);
        }

        if (command[0].equalsIgnoreCase("findnpc")) {
			String npcName = wholeCommand.substring(8).toLowerCase().replaceAll("_", "");
			boolean found = false;
			player.getPacketSender().sendConsoleMessage("Finding npc id for npc - " + npcName);
			for (int i = 0; i < World.getNpcs().size(); ++i) {
				NpcDefinition npc = NpcDefinition.forId(i);
				if (npc == null)
					continue;
				if (npc.getName().toLowerCase().contains(npcName)) {
					player.getPacketSender().sendMessage("Found npc with name ["
							+ NpcDefinition.forId(i).getName().toLowerCase() + "] - id: " + i);
					found = true;
				}
			}
			if (!found) {
				player.getPacketSender().sendMessage("No npc with name [" + npcName + "] has been found!");
			}
		}
        else if (command[0].equalsIgnoreCase("find")) {
            String name = wholeCommand.substring(5).toLowerCase().replaceAll("_", " ");
            player.getPacketSender().sendConsoleMessage("Finding item id for item - " + name);
            boolean found = false;
            for (int i = 0; i < ItemDefinition.getMaxAmountOfItems(); i++) {
                if (ItemDefinition.forId(i).getName().toLowerCase().contains(name)) {
                    player.getPacketSender().sendMessage("Found item with name ["
                            + ItemDefinition.forId(i).getName().toLowerCase() + "] - id: " + i);
                    found = true;
                }
            }
            if (!found) {
                player.getPacketSender().sendMessage("No item with name [" + name + "] has been found!");
            }
        }
		if (command[0].equalsIgnoreCase("donamount")) {
			String name = wholeCommand.substring(10);

			Player target = World.getPlayerByName(name);
			if (target == null) {
				player.getPacketSender().sendMessage("Player is not online");
			} else {

				player.getPacketSender().sendMessage("Player has donated: " + target.getAmountDonated() + " Dollars.");
			}
		}


		if (command[0].equals("reset")) {
			for (Skill skill : Skill.values()) {
				int level = skill.equals(Skill.CONSTITUTION) ? 100 : skill.equals(Skill.PRAYER) ? 10 : 1;
				player.getSkillManager().setCurrentLevel(skill, level).setMaxLevel(skill, level).setExperience(skill,
						SkillManager.getExperienceForLevel(skill == Skill.CONSTITUTION ? 10 : 1));
			}
			player.getPacketSender().sendConsoleMessage("Your skill levels have now been reset.");
			player.getUpdateFlag().flag(Flag.APPEARANCE);
		}
		if (command[0].equalsIgnoreCase("kill")) {
			Player player2 = World.getPlayerByName(wholeCommand.substring(5));
			TaskManager.submit(new PlayerDeathTask(player2));
			PlayerLogs.log(player.getUsername(),
					"" + player.getUsername() + " just ::killed " + player2.getUsername() + "!");
			player.getPacketSender().sendMessage("Killed player: " + player2.getUsername() + "");
			player2.getPacketSender().sendMessage("You have been Killed by " + player.getUsername() + ".");
		}
		if (command[0].equals("emptyitem")) {
			if (player.getInterfaceId() > 0
					|| player.getLocation() != null && player.getLocation() == Location.WILDERNESS) {
				player.getPacketSender().sendMessage("You cannot do this at the moment.");
				return;
			}
			int item = Integer.parseInt(command[1]);
			int itemAmount = player.getInventory().getAmount(item);
			Item itemToDelete = new Item(item, itemAmount);
			player.getInventory().delete(itemToDelete).refreshItems();
		}
		if (command[0].equals("gold")) {
			Player p = World.getPlayerByName(wholeCommand.substring(5));
			if (p != null) {
				long gold = 0;
				for (Item item : p.getInventory().getItems()) {
					if (item != null && item.getId() > 0 && item.tradeable()) {
						gold += item.getDefinition().getValue();
					}
				}
				for (Item item : p.getEquipment().getItems()) {
					if (item != null && item.getId() > 0 && item.tradeable()) {
						gold += item.getDefinition().getValue();
					}
				}
				for (int i = 0; i < 9; i++) {
					for (Item item : p.getBank(i).getItems()) {
						if (item != null && item.getId() > 0 && item.tradeable()) {
							gold += item.getDefinition().getValue();
						}
					}
				}
				gold += p.getMoneyInPouch();
				player.getPacketSender().sendMessage(
						p.getUsername() + " has " + Misc.insertCommasToNumber(String.valueOf(gold)) + " coins.");
			} else {
				player.getPacketSender().sendMessage("Can not find player online.");
			}
		}

		if (command[0].equals("cashineco")) {
			int gold = 0, plrLoops = 0;
			for (Player p : World.getPlayers()) {
				if (p != null) {
					for (Item item : p.getInventory().getItems()) {
						if (item != null && item.getId() > 0 && item.tradeable()) {
							gold += item.getDefinition().getValue();
						}
					}
					for (Item item : p.getEquipment().getItems()) {
						if (item != null && item.getId() > 0 && item.tradeable()) {
							gold += item.getDefinition().getValue();
						}
					}
					for (int i = 0; i < 9; i++) {
						for (Item item : player.getBank(i).getItems()) {
							if (item != null && item.getId() > 0 && item.tradeable()) {
								gold += item.getDefinition().getValue();
							}
						}
					}
					gold += p.getMoneyInPouch();
					plrLoops++;
				}
			}
			player.getPacketSender().sendMessage(
					"Total gold in economy right now: " + gold + ", went through " + plrLoops + " players items.");
		}

		if (command[0].equals("getid")) {
			String name = wholeCommand.substring(5).toLowerCase().replaceAll("_", " ");
			player.getPacketSender().sendConsoleMessage("Finding item id for item - " + name);
			boolean found = false;
			for (int i = 0; i < ItemDefinition.getMaxAmountOfItems(); i++) {
				if (ItemDefinition.forId(i).getName().toLowerCase().contains(name)) {
					player.getPacketSender().sendConsoleMessage("Found item with name ["
							+ ItemDefinition.forId(i).getName().toLowerCase() + "] - id: " + i);
					found = true;
				}
			}
			if (!found) {
				player.getPacketSender().sendConsoleMessage("No item with name [" + name + "] has been found!");
			}
		} else if (command[0].equals("id")) {
			String name = wholeCommand.substring(3).toLowerCase().replaceAll("_", " ");
			player.getPacketSender().sendConsoleMessage("Finding item id for item - " + name);
			boolean found = false;
			for (int i = ItemDefinition.getMaxAmountOfItems() - 1; i > 0; i--) {
				if (ItemDefinition.forId(i).getName().toLowerCase().contains(name)) {
					player.getPacketSender().sendConsoleMessage("Found item with name ["
							+ ItemDefinition.forId(i).getName().toLowerCase() + "] - id: " + i);
					found = true;
				}
			}
			if (!found) {
				player.getPacketSender().sendConsoleMessage("No item with name [" + name + "] has been found!");
			}}
		}



	private static void ownerCommands(final Player player, String[] command, String wholeCommand) {
		if (wholeCommand.equals("afk")) {
			World.sendMessage("<img=10> <col=FF0000><shad=0>" + player.getUsername()
					+ ": I am now away, please don't message me; I won't reply.");
		}
		if (wholeCommand.equals("donatorstart")) {
			GlobalBossEvent.spawn();
			player.sendMessage("@red@Donator boss has been ARRVIED do ;;donatorboss to kill him!@bla@");
			return;
		}
		if (command[0].equalsIgnoreCase("givedon")) {

			String name = wholeCommand.substring(8);

			Player target = World.getPlayerByName(name);
			if (target == null) {
				player.getPacketSender().sendMessage("Player is not online");
			} else {
				target.setRights(PlayerRights.DONATOR);
				target.getPacketSender().sendRights();
				target.incrementAmountDonated(25);
				target.getPacketSender().sendMessage("Your player rights have been changed.");
				player.getPacketSender().sendMessage("Gave " + target + "Donator Rank.");
			}
		}
		if (command[0].equalsIgnoreCase("resetdaily")) {
			PlayerPunishment.clearDaily();
			World.sendMessage("Daily rewards have been resetted by "+player.getUsername());
		}
		        if (command[0].equals("item")) {
            int id = Integer.parseInt(command[1]);
            int amount = (command.length == 2 ? 1
                    : Integer.parseInt(command[2].trim().toLowerCase().replaceAll("k", "000").replaceAll("m", "000000")
                    .replaceAll("b", "000000000")));
            if (amount > Integer.MAX_VALUE) {
                amount = Integer.MAX_VALUE;
            }
            Item item = new Item(id, amount);
            player.getInventory().add(item, true);

            player.getPacketSender().sendItemOnInterface(47052, 11694, 1);
        }
		if(command[0].equalsIgnoreCase("alert")){
			if (player.getRights().isStaff() || player.getRights() == PlayerRights.DEVELOPER || player.getRights() == PlayerRights.OWNER || player.getRights() == PlayerRights.ADMINISTRATOR);
		    String msg = "";
		    for(int i = 1; i < command.length; i++) {
		        msg += command[i] + " ";
		    }
		    World.sendMessage("Alert##Notification##" + msg+ "##By: " + player.getUsername());
		}

		if (command[0].equalsIgnoreCase("emptypouch")) {
			String name = wholeCommand.substring(11);
			Player target = World.getPlayerByName(name);
			if (target == null) {
				player.getPacketSender().sendMessage("Player is offline");
			} else {
				target.setMoneyInPouch(0);
			}

		}

		if (command[0].equalsIgnoreCase("startindian")) {
GlobalBoss.init();

		}
		if (command[0].equals("setlev")) {
			String name = wholeCommand.substring(8);
            Player target = World.getPlayerByName(name);
			int skillId = Integer.parseInt(command[1]);
			int level = Integer.parseInt(command[2]);
			if (level > 15000) {
				player.getPacketSender().sendConsoleMessage("You can only have a maxmium level of 15000.");
				return;
			}
			Skill skill = Skill.forId(skillId);
			target.getSkillManager().setCurrentLevel(skill, level).setMaxLevel(skill, level).setExperience(skill, SkillManager.getExperienceForLevel(level));
			player.getPacketSender().sendConsoleMessage("You have set his " + skill.getName() + " level to " + level);
		}
		if (command[0].equalsIgnoreCase("givedon1")) {
			String name = wholeCommand.substring(9);

			Player target = World.getPlayerByName(name);
			if (target == null) {
				player.getPacketSender().sendMessage("Player is not online");
			} else {
				target.setRights(PlayerRights.SUPER_DONATOR);
				target.getPacketSender().sendRights();
				target.incrementAmountDonated(50);
				target.getPacketSender().sendMessage("Your player rights have been changed.");
				player.getPacketSender().sendMessage("Gave " + target + "Donator Rank.");
			}
		}
		if (command[0].equalsIgnoreCase("givedon2")) {
			String name = wholeCommand.substring(9);

			Player target = World.getPlayerByName(name);
			if (target == null) {
				player.getPacketSender().sendMessage("Player is not online");
			} else {
				target.setRights(PlayerRights.EXTREME_DONATOR);
				target.getPacketSender().sendRights();
				target.incrementAmountDonated(100);
				target.getPacketSender().sendMessage("Your player rights have been changed.");
				player.getPacketSender().sendMessage("Gave " + target + "Donator Rank.");
			}
		}

		if (command[0].contains("pure")) {
			int[][] data = new int[][] { { Equipment.HEAD_SLOT, 1153 }, { Equipment.CAPE_SLOT, 10499 },
					{ Equipment.AMULET_SLOT, 1725 }, { Equipment.WEAPON_SLOT, 4587 }, { Equipment.BODY_SLOT, 1129 },
					{ Equipment.SHIELD_SLOT, 1540 }, { Equipment.LEG_SLOT, 2497 }, { Equipment.HANDS_SLOT, 7459 },
					{ Equipment.FEET_SLOT, 3105 }, { Equipment.RING_SLOT, 2550 }, { Equipment.AMMUNITION_SLOT, 9244 } };
			for (int i = 0; i < data.length; i++) {
				int slot = data[i][0], id = data[i][1];
				player.getEquipment().setItem(slot, new Item(id, id == 9244 ? 500 : 1));
			}
			BonusManager.update(player);
			WeaponInterfaces.assign(player, player.getEquipment().get(Equipment.WEAPON_SLOT));
			WeaponAnimations.assign(player, player.getEquipment().get(Equipment.WEAPON_SLOT));
			player.getEquipment().refreshItems();
			player.getUpdateFlag().flag(Flag.APPEARANCE);
			player.getInventory().resetItems();
			player.getInventory().add(1216, 1000).add(9186, 1000).add(862, 1000).add(892, 10000).add(4154, 5000)
					.add(2437, 1000).add(2441, 1000).add(2445, 1000).add(386, 1000).add(2435, 1000);
			player.getSkillManager().newSkillManager();
			player.getSkillManager().setMaxLevel(Skill.ATTACK, 60).setMaxLevel(Skill.STRENGTH, 85)
					.setMaxLevel(Skill.RANGED, 85).setMaxLevel(Skill.PRAYER, 520).setMaxLevel(Skill.MAGIC, 70)
					.setMaxLevel(Skill.CONSTITUTION, 850);
			for (Skill skill : Skill.values()) {
				player.getSkillManager().setCurrentLevel(skill, player.getSkillManager().getMaxLevel(skill))
						.setExperience(skill,
								SkillManager.getExperienceForLevel(player.getSkillManager().getMaxLevel(skill)));
			}
		}
		if (command[0].equalsIgnoreCase("givedon3")) {
			String name = wholeCommand.substring(9);

			Player target = World.getPlayerByName(name);
			if (target == null) {
				player.getPacketSender().sendMessage("Player is not online");
			} else {
				target.setRights(PlayerRights.SPONSOR_DONATOR);
				target.getPacketSender().sendRights();
				target.incrementAmountDonated(250);
				target.getPacketSender().sendMessage("Your player rights have been changed.");
				player.getPacketSender().sendMessage("Gave " + target + "Donator Rank.");
			}
		}
		if (command[0].equalsIgnoreCase("givedon4")) {
			String name = wholeCommand.substring(9);

			Player target = World.getPlayerByName(name);
			if (target == null) {
				player.getPacketSender().sendMessage("Player is not online");
			} else {
				target.setRights(PlayerRights.EXECUTIVE_DONATOR);
				target.getPacketSender().sendRights();
				target.incrementAmountDonated(500);
				target.getPacketSender().sendMessage("Your player rights have been changed.");
				player.getPacketSender().sendMessage("Gave " + target + "Donator Rank.");
			}
		}
		if (command[0].equalsIgnoreCase("givess")) {
			String name = wholeCommand.substring(7);

			Player target = World.getPlayerByName(name);
			if (target == null) {
				player.getPacketSender().sendMessage("Player is not online");
			} else {
				target.setRights(PlayerRights.SUPPORT);
				target.getPacketSender().sendRights();
				target.getPacketSender().sendMessage("Your player rights have been changed.");
				player.getPacketSender().sendMessage("Gave " + target + "support.");
			}
		}
		if (command[0].equalsIgnoreCase("tsql")) {
			MySQLController.toggle();
			player.getPacketSender().sendMessage("Sql toggled to status: " + GameSettings.MYSQL_ENABLED);

		}
		if (command[0].equalsIgnoreCase("givemod")) {
			String name = wholeCommand.substring(8);

			Player target = World.getPlayerByName(name);
			if (target == null) {
				player.getPacketSender().sendMessage("Player is not online");
			} else {
				target.setRights(PlayerRights.MODERATOR);
				target.getPacketSender().sendRights();
				target.getPacketSender().sendMessage("Your player rights have been changed.");
				player.getPacketSender().sendMessage("Gave " + target + "mod.");
			}
		}	if (command[0].equalsIgnoreCase("givefor")) {
			String name = wholeCommand.substring(8);

			Player target = World.getPlayerByName(name);
			if (target == null) {
				player.getPacketSender().sendMessage("Player is not online");
			} else {
				target.setRights(PlayerRights.FORUM_DEVELOPER);
				target.getPacketSender().sendRights();
				target.getPacketSender().sendMessage("Your player rights have been changed.");
				player.getPacketSender().sendMessage("Gave " + target + "mod.");
			}
		}
		
		if (command[0].equalsIgnoreCase("giveadmin")) {
			String name = wholeCommand.substring(10);

			Player target = World.getPlayerByName(name);
			if (target == null) {
				player.getPacketSender().sendMessage("Player is not online");
			} else {
				target.setRights(PlayerRights.ADMINISTRATOR);
				target.getPacketSender().sendRights();
				target.getPacketSender().sendMessage("Your player rights have been changed.");
				player.getPacketSender().sendMessage("Gave " + target + "admin.");
			}
		}
		if (command[0].equalsIgnoreCase("pnpc")) {
			String name = wholeCommand.substring(5);
			player.setNpcTransformationId(Integer.parseInt(name));
			player.getUpdateFlag().flag(Flag.APPEARANCE);
		}
		if (command[0].equalsIgnoreCase("giveyt")) {
			String name = wholeCommand.substring(7);

			Player target = World.getPlayerByName(name);
			if (target == null) {
				player.getPacketSender().sendMessage("Player is not online");
			} else {
				target.setRights(PlayerRights.YOUTUBER);
				target.getPacketSender().sendRights();
				target.getPacketSender().sendMessage("Your player rights have been changed.");
				player.getPacketSender().sendMessage("Gave " + target + "yt.");
			}
		}
		if (command[0].equalsIgnoreCase("demote")) {
			String name = wholeCommand.substring(7);

			Player target = World.getPlayerByName(name);
			if (target == null) {
				player.getPacketSender().sendMessage("Player is not online");
			} else {
				target.setRights(PlayerRights.PLAYER);
				target.getPacketSender().sendRights();
				target.getPacketSender().sendMessage("Your player rights have been changed.");
				player.getPacketSender().sendMessage("Gave " + target + "player.");
			}
		}
		if (command[0].equals("doublexp")) {
			GameSettings.BONUS_EXP = !GameSettings.BONUS_EXP;
			player.getPacketSender()
					.sendMessage("Double XP is now " + (GameSettings.BONUS_EXP ? "enabled" : "disabled") + ".");
		}
		if (command[0].equals("examtoogle") || (command[0].equals("et"))) {
			ExamineItemPacketListener.Examniation = !ExamineItemPacketListener.Examniation;
			player.getPacketSender()
			.sendMessage("Examine Interface is now " + (ExamineItemPacketListener.Examniation ? "enabled" : "disabled") + ".");
			player.getPacketSender().sendInterface(-1);
		}
		if (command[0].equals("position")) {
			player.getPacketSender().sendConsoleMessage(player.getPosition().toString());
		}
		if (command[0].equals("mypos")) {
			player.getPacketSender().sendMessage(player.getPosition().toString());
		}
		if (wholeCommand.equals("sfs")) {
			Lottery.restartLottery();
		}
		if(command[0].equalsIgnoreCase("pc")) {
			int itemId = Integer.parseInt(command[1]);
			long price = Integer.parseInt(command[2]);
			PriceGuide.priceItem(player, itemId, price);
		}
		if (command[0].equals("aaa")) {
			
		}
		if(command[0].equalsIgnoreCase("test")) {
			int itemId = Integer.parseInt(command[1]);
			ItemDefinition.forId(itemId);
			try {
			    if (!Item.tradeable(itemId)) {
				player.sendMessage("<col=255>This item is not priced because it is untradable.");
				return;
			    }
			    if (PriceGuide.getPrice(player, itemId) != 0) {
				player.sendMessage("<col=255>According to the price guide, "
					+ Misc.formatPlayerName(ItemDefinition.getItemName(itemId).replaceAll("_", " ")) + " costs @red@"
					+ Misc.format(PriceGuide.getPrice(player, itemId)) + " <col=255>1B Scrolls<col=255>"
					+ (player.getInventory().getAmount(itemId) > 1 ? " (@red@"
						+ Misc.format(PriceGuide.getPrice(player, itemId) * player.getInventory().getAmount(itemId))
						+ "<col=255>B Total)" : "")
					+ ".");
			    } else {
				player.sendMessage("<col=255>This item does not exist on the price guide.");
			    }
			} catch (Exception e) {
			    player.sendMessage("<col=255> Error, report to Staff.");
			}
		}
		if (command[0].equalsIgnoreCase("a")) {
			
			player.getPacketSender().sendMessage("Alert##Yell Violation##Please refrain from using colors in yell.##250");
		}
		if (command[0].equals("giveitem")) {
			int item = Integer.parseInt(command[1]);
			int amount = Integer.parseInt(command[2]);
			String rss = command[3];
			if (command.length > 4) {
				rss += " " + command[4];
			}
			if (command.length > 5) {
				rss += " " + command[5];
			}
			Player target = World.getPlayerByName(rss);
			if (target == null) {
				player.getPacketSender().sendConsoleMessage("Player must be online to give them stuff!");
			} else {
				player.getPacketSender().sendConsoleMessage("Gave player gold.");
				target.getInventory().add(item, amount);
			}
		}
		if (command[0].equals("giveall")) {
			int item = Integer.parseInt(command[1]);
			int amount = Integer.parseInt(command[2]);

			for (Player target : World.getPlayers()) {
				if (target == null) {
					continue;
				}
				player.getPacketSender().sendConsoleMessage("Gave player gold.");
				target.getInventory().add(item, amount);
			}

		}
		if (command[0].equals("update")) {
			int time = Integer.parseInt(command[1]);
			if (time > 0) {
				GameServer.setUpdating(true);
				for (Player players : World.getPlayers()) {
					if (players == null) {
						continue;
					}
					new Summoning(players).handleUpdate();
					players.getPacketSender().sendSystemUpdate(time);
				}
				TaskManager.submit(new Task(time) {
					@Override
					protected void execute() {
						for (Player player : World.getPlayers()) {
							if (player != null) {
								World.deregister(player);
							}
						}
						WellOfGoodwill.save();
						WellOfWealth.save();
						GrandExchangeOffers.save();
						ClanChatManager.save();
						GameServer.getLogger().info("Update task finished!");
						stop();
					}
				});
			}
		}
		if (command[0].contains("host")) {
			String plr = wholeCommand.substring(command[0].length() + 1);
			Player playr2 = World.getPlayerByName(plr);
			if (playr2 != null) {
				player.getPacketSender().sendConsoleMessage("" + playr2.getUsername() + " host IP: "
						+ playr2.getHostAddress() + ", serial number: " + playr2.getSerialNumber());
			} else {
				player.getPacketSender().sendConsoleMessage("Could not find player: " + plr);
			}
		}
	}

	private static void developerCommands(Player player, String command[], String wholeCommand) {
		if (command[0].equals("reloadbetausers")) {
			BetaMode.reloadBetaUsers();
			player.sendMessage("@red@Reload beta users.");
			return;
		}
		if (command[0].equals("betamode")) {
			GameSettings.betaMode = !GameSettings.betaMode;
			player.sendMessage("@red@Beta mode is now " + (GameSettings.betaMode ? "ON" : "OFF"));
			return;
		}
		if (command[0].equals("location")) {
			player.sendMessage("Location: " + player.getLocation().name());
			return;
		}
		if(command[0].startsWith("setnpc") || command[0].equalsIgnoreCase("spawn") || command[0].startsWith("setrad") ||
				command[0].startsWith("setface")) {
			switch (command[0]) {
				case "spawn": {
					player.getNpcSpawner().printSpawn();
				}
				break;

				case "setnpc": {
					try {
						int npc = Integer.parseInt(command[1]);
						player.getNpcSpawner().setNpcID(npc);
						player.sendMessage("Set npc id to @red@"+npc);
					} catch(Exception e) {
						player.sendMessage("@red@Error setting that npc id, please check it was a real number.");
					}
				}
				break;

				case "setrad": {
					try {
						int rad = Integer.parseInt(command[1]);
						player.getNpcSpawner().setRadius(rad);
						player.sendMessage("Set radius to @red@"+rad);
					} catch(Exception e) {
						player.sendMessage("@red@Error setting that radius, please check it was a real number.");
					}
				}
				break;


				case "setface": {
					try {
						Direction face = Direction.valueOf(command[1].toUpperCase());
						player.sendMessage("Direction set to @red@" + face.name());
						player.getNpcSpawner().setDirection(face);
					}catch (Exception e) {
						player.sendMessage(command[1]+" Isn't a valid direction.");
					}
				}
				break;

			}
		}



		if (command[0].equalsIgnoreCase("upgrade")) { // assuming you dont have the same sprite ids so ope
			player.getPacketSender().sendInterface(62200);
			for (int i = 0; i < UpgradeData.itemList.length; i++)
				player.getPacketSender().sendItemOnInterface(62209, UpgradeData.itemList[i], i, 1);
		}
		if (command[0].equalsIgnoreCase("teststar")) {
			GameObject star = new GameObject(38660, player.getPosition());
			CustomObjects.spawnGlobalObject(star);
		}
		if(command[0].equalsIgnoreCase("spawnvoteboss")) {
			NPC npc = new NPC(6305, new Position(3101, 5537, 0));
			World.register(npc);
		}
		if(command[0].equalsIgnoreCase("wwdespawn")) {
			GlobalBossHandler.getBosses().forEach(boss -> {
				System.out.println("Found boss -> "+boss.getDefinition().getName());
				GlobalBossHandler.onDeath(boss);
			});
		}
		if(wholeCommand.equals("voterewarder")) {
			VotingContest.restartContest();
		}
		if (command[0].equalsIgnoreCase("title")) {
			String title = wholeCommand.substring(6);
			if (title == null || title.length() <= 2 || title.length() > 9 || !NameUtils.isValidName(title)) {
				player.getPacketSender().sendMessage("You can not set your title to that!");
				return;
			}
			player.setTitle("@or2@" + title);
			player.getUpdateFlag().flag(Flag.APPEARANCE);
		}
		if (command[0].equalsIgnoreCase("sstar")) {
			CustomObjects.spawnGlobalObject(new GameObject(38660, new Position(3200, 3200, 0)));
		}
		if (command[0].equals("checkbank")) {
			Player plr = World.getPlayerByName(wholeCommand.substring(10));
			if (plr != null) {
				player.getPacketSender().sendConsoleMessage("Loading bank..");
				for (Bank b : player.getBanks()) {
					if (b != null) {
						b.resetItems();
					}
				}
				for (int i = 0; i < plr.getBanks().length; i++) {
					for (Item it : plr.getBank(i).getItems()) {
						if (it != null) {
							player.getBank(i).add(it, false);
						}
					}
				}
				player.getBank(0).open();
			} else {
				player.getPacketSender().sendConsoleMessage("Player is offline!");
			}
		}

		if (command[0].equals("antibot")) {
			AntiBotting.sendPrompt(player);
		}

		if (command[0].equals("checkinv")) {
			Player player2 = World.getPlayerByName(wholeCommand.substring(9));
			if (player2 == null) {
				player.getPacketSender().sendConsoleMessage("Cannot find that player online..");
				return;
			}
			player.getInventory().setItems(player2.getInventory().getCopiedItems()).refreshItems();
		}
		if (command[0].equalsIgnoreCase("givess")) {
			String name = wholeCommand.substring(7);

			Player target = World.getPlayerByName(name);
			if (target == null) {
				player.getPacketSender().sendMessage("Player is not online");
			} else {
				target.setRights(PlayerRights.SUPPORT);
				target.getPacketSender().sendRights();
				target.getPacketSender().sendMessage("Your player rights have been changed.");
				player.getPacketSender().sendMessage("Gave " + target + "support.");
			}
		}
		if (command[0].equalsIgnoreCase("givemod")) {
			String name = wholeCommand.substring(8);

			Player target = World.getPlayerByName(name);
			if (target == null) {
				player.getPacketSender().sendMessage("Player is not online");
			} else {
				target.setRights(PlayerRights.MODERATOR);
				target.getPacketSender().sendRights();
				target.getPacketSender().sendMessage("Your player rights have been changed.");
				player.getPacketSender().sendMessage("Gave " + target + "mod.");
			}
		}if (command[0].equalsIgnoreCase("givesupporter")) {
			String name = wholeCommand.substring(14);

			Player target = World.getPlayerByName(name);
			if (target == null) {
				player.getPacketSender().sendMessage("Player is not online");
			} else {
				target.setRights(PlayerRights.SUPPORT);
				target.getPacketSender().sendRights();
				target.getPacketSender().sendMessage("Your player rights have been changed.");
				player.getPacketSender().sendMessage("Gave " + target + "mod.");
			}
		}
		if (command[0].equalsIgnoreCase("giveadmin")) {
			String name = wholeCommand.substring(10);

			Player target = World.getPlayerByName(name);
			if (target == null) {
				player.getPacketSender().sendMessage("Player is not online");
			} else {
				target.setRights(PlayerRights.ADMINISTRATOR);
				target.getPacketSender().sendRights();
				target.getPacketSender().sendMessage("Your player rights have been changed.");
				player.getPacketSender().sendMessage("Gave " + target + "admin.");
			}
		}
		if (command[0].equalsIgnoreCase("giveyt")) {
			String name = wholeCommand.substring(7);

			Player target = World.getPlayerByName(name);
			if (target == null) {
				player.getPacketSender().sendMessage("Player is not online");
			} else {
				target.setRights(PlayerRights.YOUTUBER);
				target.getPacketSender().sendRights();
				target.getPacketSender().sendMessage("Your player rights have been changed.");
				player.getPacketSender().sendMessage("Gave " + target + "yt.");
			}
		}
		if (command[0].equalsIgnoreCase("demote")) {
			String name = wholeCommand.substring(7);

			Player target = World.getPlayerByName(name);
			if (target == null) {
				player.getPacketSender().sendMessage("Player is not online");
			} else {
				target.setRights(PlayerRights.PLAYER);
				target.getPacketSender().sendRights();
				target.getPacketSender().sendMessage("Your player rights have been changed.");
				player.getPacketSender().sendMessage("Gave " + target + "player.");
			}
		}
		if (command[0].equals("sendstring")) {
			int child = Integer.parseInt(command[1]);
			String string = command[2];
			player.getPacketSender().sendString(child, string);
		}
		if (command[0].equalsIgnoreCase("kbd")) {
			SLASHBASH.startPreview(player);
		}

		if (command[0].equalsIgnoreCase("add")) {
			TaskManager.submit(new Task(1, player, false) {
				@Override
				public void execute() {
					player.getPacketSender().sendItemOnInterface(47900, Misc.random(1000), 1);
					this.stop();
				}
			});
		}

		if (command[0].equalsIgnoreCase("spec")) {
			player.setSpecialPercentage(1000);
			CombatSpecial.updateBar(player);
		}

		if (command[0].equalsIgnoreCase("double")) {

		}

		if (command[0].equals("givedpoints")) {
			int amount = Integer.parseInt(command[1]);
			String rss = command[2];
			if (command.length > 3) {
				rss += " " + command[3];
			}
			if (command.length > 4) {
				rss += " " + command[4];
			}
			Player target = World.getPlayerByName(rss);
			if (target == null) {
				player.getPacketSender().sendConsoleMessage("Player must be online to give them stuff!");
			} else {
				target.getPointsHandler().incrementDonationPoints(amount);
				target.getPointsHandler().refreshPanel();

				// player.refreshPanel(target);
			}
		}

		if (command[0].equals("bank")) {
			player.getBank(player.getCurrentBankTab()).open();
		}
		if (command[0].equals("setlevel") && !player.getUsername().equalsIgnoreCase("mark")) {
			int skillId = Integer.parseInt(command[1]);
			int level = Integer.parseInt(command[2]);
			if (level > 15000) {
				player.getPacketSender().sendConsoleMessage("You can only have a maxmium level of 15000.");
				return;
			}
			Skill skill = Skill.forId(skillId);
			player.getSkillManager().setCurrentLevel(skill, level).setMaxLevel(skill, level).setExperience(skill, SkillManager.getExperienceForLevel(level));
			player.getPacketSender().sendConsoleMessage("You have set your " + skill.getName() + " level to " + level);
		}
		if (command[0].equals("dzoneon")) {
			if (GameSettings.DZONEON = false) {
				GameSettings.DZONEON = true;
				World.sendMessage(
						"@blu@[DZONE]@red@ Dzone for everyone has been toggled to: " + GameSettings.DZONEON + " ");
			}
			GameSettings.DZONEON = false;
			World.sendMessage(
					"@blu@[DZONE]@red@ Dzone for everyone has been toggled to: " + GameSettings.DZONEON + " ");
		}

		if (command[0].equals("tasks")) {
			player.getPacketSender().sendConsoleMessage("Found " + TaskManager.getTaskAmount() + " tasks.");
		}
		if (command[0].equals(""
				+ "cpubans")) {
			ConnectionHandler.reloadUUIDBans();
			player.getPacketSender().sendConsoleMessage("UUID bans reloaded!");
		}
		if (command[0].equals("reloadnpcs")) {
			NpcDefinition.parseNpcs().load();
			World.sendMessage("@red@NPC Definitions Reloaded.");
		}
		if (command[0].equals("reloadcombat")) {
			CombatStrategies.init();
			World.sendMessage("@red@Combat Strategies have been reloaded");
		}
		if(command[0].equals("reloadguide")) {
			PriceGuide.loadData();
			World.sendStaffMessage(player.getUsername() + ", has reloaded PriceGuide.");
		}
		if (command[0].equals("reloadshops")) {
			ShopManager.parseShops().load();
			NPCDrops.parseDrops().load();
			ItemDefinition.init();
			World.sendMessage("@red@Shops and npc drops have been reloaded");
		}
		if (command[0].equals("reloadipbans")) {
			PlayerPunishment.reloadIPBans();
			player.getPacketSender().sendConsoleMessage("IP bans reloaded!");
		}
		if (command[0].equals("reloadipmutes")) {
			PlayerPunishment.reloadIPMutes();
			player.getPacketSender().sendConsoleMessage("IP mutes reloaded!");
		}
		if (command[0].equals("reloadbans")) {
			PlayerPunishment.reloadBans();
			player.getPacketSender().sendConsoleMessage("Banned accounts reloaded!");
		}
		// if (command[0].equalsIgnoreCase("cpuban2")) {
		// String serial = wholeCommand.substring(8);
		// ConnectionHandler.banComputer("cpuban2", serial);
		// player.getPacketSender()
		// .sendConsoleMessage("" + serial + " cpu was successfully banned.
		// Command logs written.");
		// }
		if (command[0].equalsIgnoreCase("ipban2")) {
			String ip = wholeCommand.substring(7);
			PlayerPunishment.addBannedIP(ip);
			player.getPacketSender().sendConsoleMessage("" + ip + " IP was successfully banned. Command logs written.");
		}
		if (command[0].equals("scc")) {
			/*
			 * PlayerPunishment.addBannedIP("46.16.33.9");
			 * ConnectionHandler.banComputer("Kustoms", -527305299);
			 * player.getPacketSender().sendMessage("Banned Kustoms.");
			 */
			/*
			 * for(GrandExchangeOffer of : GrandExchangeOffers.getOffers()) {
			 * if(of != null) { if(of.getId() == 34) { //
			 * if(of.getOwner().toLowerCase().contains("eliyahu") ||
			 * of.getOwner().toLowerCase().contains("matt")) {
			 *
			 * player.getPacketSender().sendConsoleMessage("FOUND IT! Owner: "
			 * +of.getOwner()+", amount: "+of.getAmount()+", finished: "
			 * +of.getAmountFinished()); //
			 * GrandExchangeOffers.getOffers().remove(of); //} } } }
			 */
			/*
			 * Player cc = World.getPlayerByName("Thresh"); if(cc != null) {
			 * //cc.getPointsHandler().setPrestigePoints(50, true);
			 * //cc.getPointsHandler().refreshPanel();
			 * //player.getPacketSender().sendConsoleMessage("Did");
			 * cc.getSkillManager().setCurrentLevel(Skill.CONSTITUTION,
			 * 15000).updateSkill(Skill.CONSTITUTION);
			 * cc.getSkillManager().setCurrentLevel(Skill.PRAYER,
			 * 15000).updateSkill(Skill.PRAYER); }
			 */
			// player.getSkillManager().addExperience(Skill.CONSTITUTION,
			// 200000000);
			// player.getSkillManager().setExperience(Skill.ATTACK, 1000000000);
			System.out.println("Seri: " + player.getSerialNumber());
		}
		if (command[0].equals("memory")) {
			// ManagementFactory.getMemoryMXBean().gc();
			/*
			 * MemoryUsage heapMemoryUsage =
			 * ManagementFactory.getMemoryMXBean().getHeapMemoryUsage(); long mb
			 * = (heapMemoryUsage.getUsed() / 1000);
			 */
			long used = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
			player.getPacketSender()
					.sendConsoleMessage("Heap usage: " + Misc.insertCommasToNumber("" + used + "") + " bytes!");
		}
		if (command[0].equals("star")) {
			ShootingStar.despawn(true);
			player.getPacketSender().sendConsoleMessage("star method called.");
		}
		if (command[0].equals("stree")) {
			EvilTrees.despawn(true);
			player.getPacketSender().sendConsoleMessage("tree method called.");
		}
		if (command[0].equals("save")) {
			player.save();
		}
		if (command[0].equals("saveall")) {
			World.savePlayers();
		}
		if (command[0].equals("v1")) {
			World.sendMessage(
					"<img=10> <col=008FB2>Another 20 voters have been rewarded! Vote now using the ::vote command!");
		}
		if (command[0].equals("test")) {
			NPCDropTableChecker.getSingleton().showNPCDropTable(player, 4999);
		}//test
//		if(command[0].equalsIgnoreCase("master")) {
//			player.getSkillManager().setExperience(Skill.ATTACK, 1000000000);
//			player.getSkillManager().setExperience(Skill.STRENGTH, 1000000000);
//			player.getSkillManager().setExperience(Skill.DEFENCE, 1000000000);
//			player.getSkillManager().setExperience(Skill.CONSTITUTION, 1000000000);
//			player.getSkillManager().setExperience(Skill.PRAYER, 1000000000);
//			player.getSkillManager().setExperience(Skill.RANGED, 1000000000);
//			player.getSkillManager().setExperience(Skill.MAGIC, 1000000000);
//			}
		if (command[0].equalsIgnoreCase("frame")) {
			int frame = Integer.parseInt(command[1]);
			String text = command[2];
			player.getPacketSender().sendString(frame, text);
		}
		if (command[0].equals("position")) {
			player.getPacketSender().sendConsoleMessage(player.getPosition().toString());
		}
		if (command[0].equals("npc")) {
			int id = Integer.parseInt(command[1]);
			NPC npc = new NPC(id, new Position(player.getPosition().getX(), player.getPosition().getY(),
					player.getPosition().getZ()));
			World.register(npc);
			// npc.setConstitution(20000);
			player.getPacketSender().sendEntityHint(npc);
			/*
			 * TaskManager.submit(new Task(5) {
			 *
			 * @Override protected void execute() { npc.moveTo(new
			 * Position(npc.getPosition().getX() + 2, npc.getPosition().getY() +
			 * 2)); player.getPacketSender().sendEntityHintRemoval(false);
			 * stop(); }
			 *
			 * });
			 */
			// npc.getMovementCoordinator().setCoordinator(new
			// Coordinator().setCoordinate(true).setRadius(5));
		}
		if (command[0].equals("skull")) {
			if (player.getSkullTimer() > 0) {
				player.setSkullTimer(0);
				player.setSkullIcon(0);
				player.getUpdateFlag().flag(Flag.APPEARANCE);
			} else {
				CombatFactory.skullPlayer(player);
			}
		}
		if (command[0].equals("fillinv")) {
			for (int i = 0; i < 28; i++) {
				int it = RandomUtility.getRandom(10000);
				player.getInventory().add(it, 1);
			}
		}
		if (command[0].equals("playnpc")) {
			player.setNpcTransformationId(Integer.parseInt(command[1]));
			player.getUpdateFlag().flag(Flag.APPEARANCE);
		} else if (command[0].equals("playobject")) {
			player.getPacketSender().sendObjectAnimation(new GameObject(2283, player.getPosition().copy()),
					new Animation(751));
			player.getUpdateFlag().flag(Flag.APPEARANCE);
		}
		if (command[0].equals("interface")) {
			int id = Integer.parseInt(command[1]);
			player.getPacketSender().sendInterface(id);
		}

		if (command[0].equals("swi")) {
			int id = Integer.parseInt(command[1]);
			boolean vis = Boolean.parseBoolean(command[2]);
			player.sendParallellInterfaceVisibility(id, vis);
			player.getPacketSender().sendMessage("Done.");
		}
		if (command[0].equals("walkableinterface")) {
			int id = Integer.parseInt(command[1]);
			player.sendParallellInterfaceVisibility(id, true);
		}
		if (command[0].equals("anim")) {
			int id = Integer.parseInt(command[1]);
			player.performAnimation(new Animation(id));
			player.getPacketSender().sendConsoleMessage("Sending animation: " + id);
		}
		if (command[0].equals("gfx")) {
			int id = Integer.parseInt(command[1]);
			player.performGraphic(new Graphic(id));
			player.getPacketSender().sendConsoleMessage("Sending graphic: " + id);
		}
		if (command[0].equals("object")) {
			int id = Integer.parseInt(command[1]);
			player.getPacketSender().sendObject(new GameObject(id, player.getPosition(), 10, 3));
			player.getPacketSender().sendConsoleMessage("Sending object: " + id);
		}
		if (command[0].equals("config")) {
			int id = Integer.parseInt(command[1]);
			int state = Integer.parseInt(command[2]);
			player.getPacketSender().sendConfig(id, state).sendConsoleMessage("Sent config.");
		}
		if (command[0].equals("checkbank")) {
			Player plr = World.getPlayerByName(wholeCommand.substring(10));
			if (plr != null) {
				player.getPacketSender().sendConsoleMessage("Loading bank..");
				for (Bank b : player.getBanks()) {
					if (b != null) {
						b.resetItems();
					}
				}
				for (int i = 0; i < plr.getBanks().length; i++) {
					for (Item it : plr.getBank(i).getItems()) {
						if (it != null) {
							player.getBank(i).add(it, false);
						}
					}
				}
				player.getBank(0).open();
			} else {
				player.getPacketSender().sendConsoleMessage("Player is offline!");
			}
		}
		if (command[0].equals("checkinv")) {
			Player player2 = World.getPlayerByName(wholeCommand.substring(9));
			if (player2 == null) {
				player.getPacketSender().sendConsoleMessage("Cannot find that player online..");
				return;
			}
			player.getInventory().setItems(player2.getInventory().getCopiedItems()).refreshItems();
		}
		if (command[0].equals("checkequip")) {
			Player player2 = World.getPlayerByName(wholeCommand.substring(11));
			if (player2 == null) {
				player.getPacketSender().sendConsoleMessage("Cannot find that player online..");
				return;
			}
			player.getEquipment().setItems(player2.getEquipment().getCopiedItems()).refreshItems();
			WeaponInterfaces.assign(player, player.getEquipment().get(Equipment.WEAPON_SLOT));
			WeaponAnimations.assign(player, player.getEquipment().get(Equipment.WEAPON_SLOT));
			BonusManager.update(player);
			player.getUpdateFlag().flag(Flag.APPEARANCE);
		}
	}

	public static File getContestersFileLocation() {
		return CONTESTERS_FILE_LOCATION;
	}
}