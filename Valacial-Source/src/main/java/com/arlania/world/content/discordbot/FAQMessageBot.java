package com.arlania.world.content.discordbot;

import com.arlania.GameSettings;
import com.arlania.util.Misc;
import com.arlania.util.Stopwatch;
import com.arlania.world.World;

public class FAQMessageBot {
	
	
	
    private static final int TIME = 60; //4 HOURS
	private static Stopwatch timer = new Stopwatch().reset();
	public static String currentMessage;
	
	/*
	 * Random Message Data
	 */
	private static final String[][] MESSAGE_DATA = {
			{"Donate to help the server grow!"}, 
			{"Use the !commands command to view different Discord commands!"},
			{"Remember to vote daily for rewards!"},
			
			{"Remember to spread the word and invite your friends to play!"},
			{"Have suggestion/idea? Let us know in #suggestions"},
			{"When dealing with a donation ONLY use the webstore"},
			
			{"Want to see something ingame? Suggest it on ::discord"},
			{"Check out #polls to take an active part in community based updates"},
	};

	/*
	 * Sequence called in world.java
	 * Handles the main method
	 * Grabs random message and announces it
	 */
	public static void sequence() {
		if(timer.elapsed(TIME)) {
			timer.reset();
			{
			currentMessage = MESSAGE_DATA[Misc.getRandom(MESSAGE_DATA.length - 1)][0];
			DiscordBot.jda.getTextChannelById(GameSettings.INGAME_LOG).sendMessage("```" + currentMessage + "```").queue();
				}
			}
		

          }
	
	

}
