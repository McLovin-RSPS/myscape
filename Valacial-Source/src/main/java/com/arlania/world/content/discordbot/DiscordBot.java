package com.arlania.world.content.discordbot;


import java.awt.Color;

import javax.security.auth.login.LoginException;

import com.arlania.GameSettings;

import com.arlania.world.World;
import com.arlania.world.content.PlayerPunishment;
import com.arlania.world.entity.impl.player.Player;
import com.arlania.world.entity.impl.player.PlayerHandler;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Game.GameType;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;


public class DiscordBot extends ListenerAdapter {

	public static JDA jda;

	public void initialize() {
		JDABuilder builder = new JDABuilder(AccountType.BOT);
		builder.setStatus(OnlineStatus.ONLINE);

		builder.setToken("");
		builder.setGame(Game.of(GameType.WATCHING, "ImaginePS Server", "https://myscpae.org"));
		builder.addEventListener(this);
		try {
			jda = builder.build();
		} catch (LoginException e) {
			e.printStackTrace();
		}
	}

	private final String DO_COMMAND = "::";

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {

		String message = event.getMessage().getContentDisplay();

		String[] command = message.split(" ");

		if (command[0].equalsIgnoreCase(DO_COMMAND + "players")) {
			
			EmbedBuilder embed = new EmbedBuilder();
			embed.setTitle("ImaginePS");
			embed.setColor(Color.DARK_GRAY);

			embed.setDescription("");
			embed.addField("There are currently " + World.getPlayers().size() + " Playing ImaginePS!", "", false);
			event.getChannel().sendMessage(embed.build()).queue();
		}

		if (command[0].equalsIgnoreCase(DO_COMMAND + "vote")) {

		
			
			EmbedBuilder embed = new EmbedBuilder();
			embed.setTitle("ImaginePS Voting!");
			embed.setColor(Color.DARK_GRAY);
			embed.setDescription("");
			embed.addField("Do ::vote in-game", "", false);
			embed.addField("Vote on all the sites", "", false);
			embed.addField("Do ::vote 1 all", "", false);
			event.getChannel().sendMessage(embed.build()).queue();

		}

		if (command[0].equalsIgnoreCase(DO_COMMAND + "store")) {

			EmbedBuilder embed = new EmbedBuilder();
			embed.setTitle("ImaginePS Store");
			embed.setColor(Color.DARK_GRAY);
			embed.setDescription("");
			embed.addField("Do  :: onate in-game", "", false);
			embed.addField("Or head too https://ImaginePS.org/store", "", false);
			embed.addField("Purchase whatever you would like", "", false);
			embed.addField("Do ::claim in-game!", "", false);
			event.getChannel().sendMessage(embed.build()).queue();

		}

		if (command[0].equalsIgnoreCase(DO_COMMAND + "website")) {
			EmbedBuilder embed = new EmbedBuilder();
			embed.setTitle("ImaginePS Website");
			embed.setColor(Color.DARK_GRAY);
			embed.setDescription("");
			embed.addField("Our website is: ", "ImaginePS.org", false);
			event.getChannel().sendMessage(embed.build()).queue();

		}
		
		if (command[0].equalsIgnoreCase(DO_COMMAND + "commands")) {
			System.out.println("Command executed");
			EmbedBuilder embed = new EmbedBuilder();
			embed.setTitle("ImaginePS Bot Commands");
			embed.setColor(Color.RED);
			embed.setDescription("");
			embed.addField("::store - links you to our donation store.", "", false);
			embed.addField("::players - tells you how many players are in-game.", "", false);
			embed.addField("::vote - links you to our voting page", "", false);
			embed.addField("::website - links you to our website", "", false);
			embed.addField("::apply - displays information about applying to ImaginePS", "", false);
			event.getChannel().sendMessage(embed.build()).queue();

		}
		
		if (command[0].equalsIgnoreCase(DO_COMMAND + "apply")) {
			System.out.println("Command executed");
			EmbedBuilder embed = new EmbedBuilder();
			embed.setTitle("Applying for staff on ImaginePS");
			embed.setColor(Color.RED);
			embed.setDescription("");
			embed.addField("To apply please go to #staff-applications", "", false);
			embed.addField("Only applicants that follow the format will be considered.", "", false);
		
			event.getChannel().sendMessage(embed.build()).queue();

		}
		
		
		
		/*
		 *  DISCORD STAFF COMMANDS
		 */
		
		
		String player2 = message.substring(message.lastIndexOf(" ") + 1);
		
		
		
		
		Role Staff_Role = event.getJDA().getRoleById("726029582280556627"); // ok
		
		/*
		 * ROLE COMMAND
		 */
		if (command[0].equalsIgnoreCase(DO_COMMAND + "roles")) { //to get role Id's from discord
			DiscordBot.jda.getTextChannelById(GameSettings.INGAME_LOG).sendMessage("" + event.getMember().getRoles()).queue();
		}
		
		if (command[0].equalsIgnoreCase(DO_COMMAND + "roles")) { //to get role Id's from discord
			DiscordBot.jda.getTextChannelById(GameSettings.INGAME_LOG).sendMessage("" + event.getMember().getRoles()).queue();
		}
		
		/*if(event.getMember().getRoles().contains(Staff_Role)) {
			
			
			//if (command[0].equalsIgnoreCase(DO_COMMAND + "mute")) {
				
	        	//PlayerPunishment.mute(player2); 
	        	//World.sendStaffMessage(event.getMember().getAsMention() + " has Discord-Muted " + player2);
	        	//World.getPlayerByName(player2).sendMessage("You have been muted.");
	    		//DiscordBot.jda.getTextChannelById(GameSettings.INGAME_LOG).sendMessage(event.getMember().getAsMention() + " has muted " + player2).queue();
				
			}
			
			
			
			if (command[0].equalsIgnoreCase(DO_COMMAND + "unmute")) {
	        	PlayerPunishment.unmute(player2);
	        	World.sendStaffMessage(event.getMember().getAsMention() + " has Discord-UnMuted " + player2);
	    		DiscordBot.jda.getTextChannelById(GameSettings.INGAME_LOG).sendMessage(event.getMember().getAsMention() + " has unmuted " + player2).queue();
			}
			
			if (command[0].equalsIgnoreCase(DO_COMMAND + "ban")) {
	        	PlayerPunishment.ban(player2);
	        	 World.getPlayerByName(player2).logout();
	                PlayerHandler.handleLogout(World.getPlayerByName(player2));
	        	World.sendStaffMessage(event.getMember().getNickname() + " has Discord-Banned " + player2);
	    		DiscordBot.jda.getTextChannelById(GameSettings.INGAME_LOG).sendMessage(event.getMember().getAsMention() + " has Banned " + player2).queue();
			}
			
			if (command[0].equalsIgnoreCase(DO_COMMAND + "unban")) {
	        	PlayerPunishment.unban(player2);
	        	World.sendStaffMessage(event.getMember().getAsMention() + " has Discord-Unbanned " + player2);
	    		DiscordBot.jda.getTextChannelById(GameSettings.INGAME_LOG).sendMessage(event.getMember().getAsMention() + " has Unbanned " + player2).queue();
			}
			
			if (command[0].equalsIgnoreCase(DO_COMMAND + "kick")) {
	        	 World.getPlayerByName(player2).logout();
	                PlayerHandler.handleLogout(World.getPlayerByName(player2));
	                
	    		DiscordBot.jda.getTextChannelById(GameSettings.INGAME_LOG).sendMessage(event.getMember().getAsMention() + " has kicked " + player2).queue();
			}
			
			if (command[0].equalsIgnoreCase(DO_COMMAND + "movehome")) {
				World.getPlayerByName(player2).moveTo(GameSettings.DEFAULT_POSITION.copy());
	        	World.sendStaffMessage(event.getMember().getAsMention() + " has Discord-Unbanned " + player2);
	    		DiscordBot.jda.getTextChannelById(GameSettings.INGAME_LOG).sendMessage(event.getMember().getAsMention() + " has moved " + player2 + " home.").queue();
			}
		}
			*/

	
		
		
	}
	
	
	}
