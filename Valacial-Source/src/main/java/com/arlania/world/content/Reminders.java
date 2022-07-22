package com.arlania.world.content;

import com.arlania.util.Misc;
import com.arlania.util.Stopwatch;
import com.arlania.world.World;

/*
 * @author Bas
 * www.Arlania.com
 */

public class Reminders {
	
	
    private static final int TIME = 900000; //15 minutes
	private static Stopwatch timer = new Stopwatch().reset();
	public static String currentMessage;
	
	/*
	 * Random Message Data
	 *///@bla@[@or2@Beta tester@bla@] @or2@
	private static final String[][] MESSAGE_DATA = { 
			{"@red@[SERVER]@bla@ Join 'help' CC For Help/Tips!"},
			{"@red@[SERVER]@bla@ AFK area is for you to get ahead while AFK@or2@[::afk]@bla@"},
			{"@red@[SERVER]@bla@ Donate to help the server grow! @or2@[;;donate]@bla@"},
			{"@red@[SERVER]@bla@ Use the command @or2@[;;drop (npcname)]@bla@ for drop tables"},
			{"@red@[SERVER]@bla@ Use the @or2@[::help]@bla@ command to ask staff for help"},
			{"@red@[SERVER]@bla@ Remember to vote daily for rewards! @or2@[::vote]@bla@"},
			{"@red@[SERVER]@bla@ Remember to spread the word and invite your friends to play!"},
			{"@red@[SERVER]@bla@ Use @or2@[::commands]@bla@ to find a list of commands"},
			{"@red@[SERVER]@bla@ Toggle your client settings to your preference in the wrench tab!"},
			{"@red@[SERVER]@bla@ Join one of the many events we hold on our discrd! @or2@[;;Discord]@bla@"},
			{"@red@[SERVER]@bla@ Want to see something ingame? Suggest it on @or2@[;;discord]@bla@"},
			
		
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
			World.sendMessage(currentMessage);
			World.savePlayers();
					
				}
				
			World.savePlayers();
			}
		

          }

}