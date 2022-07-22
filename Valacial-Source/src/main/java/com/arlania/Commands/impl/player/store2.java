package com.arlania.Commands.impl.player;



import java.awt.Color;

import com.arlania.GameSettings;
import com.arlania.Commands.Command;
import com.arlania.Commands.CommandInfo;
import com.arlania.world.World;
import com.arlania.world.content.discordbot.DiscordBot;
import com.arlania.world.entity.impl.player.Player;

import net.dv8tion.jda.core.EmbedBuilder;

@CommandInfo(
        command = {"reward"},
        description = "to claim vote"
)
public class store2 extends Command{

    @Override
    public void execute(Player player, String command) {
    	
    	if (command.length() == 1) {
    		player.getPacketSender().sendMessage("Please use [::reward id], [::reward id amount], or [::reward id all].");
    		return;
    	}
    	final String playerName = player.getUsername();
    	final String id = command.split(" ")[1];
;
    	final String amount = command.length() == 3 ? command.split(" ")[2] : "1";

    	com.everythingrs.vote.Vote.service.execute(new Runnable() {
    		@Override
    		public void run() {
    			try {
    				com.everythingrs.vote.Vote[] reward = com.everythingrs.vote.Vote.reward("4NPnqxwOzv226sTFD4StwGoJ4u6RlxA7PRdkwKDOvAahKOMvZVQ4UdroyDzBlVLMdYlSJ0T1",
    						playerName, id, amount);
    				if (reward[0].message != null) {
    					player.getPacketSender().sendMessage(reward[0].message);
    					return;
    				}
    				player.getInventory().add(reward[0].reward_id, reward[0].give_amount);
    				EmbedBuilder embed = new EmbedBuilder();
    				embed.setTitle("Thanks for Voting");
    				embed.setColor(Color.GREEN);
    				embed.addField(player.getUsername() + " voted and claimed His Reward - There is now " + reward[0].vote_points + "  Vote Points Left! ", "", false);
    				
    				DiscordBot.jda.getTextChannelById(GameSettings.VOTE_KEY).sendMessage(embed.build()).queue();
    				
    				World.sendMessage("<img=10><col=cc0000><shad=cc0000> Thanks " + player.getUsername() + " for Voting <img=10>");
    				player.getPacketSender().sendMessage("Thank you for voting! You now have " + reward[0].vote_points + " vote points.");
    				player.getDailyRewards().handleVote();
    				player.lastVoteTime = System.currentTimeMillis();
    			} catch (Exception e) {
    				player.getPacketSender().sendMessage("Api Services are currently offline. Please check back shortly");
    				e.printStackTrace();
    			}
    		}

    	});
    	}
    	
}