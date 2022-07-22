package com.arlania.Commands.impl.player;

import com.arlania.model.Item;
import com.arlania.model.container.impl.Equipment;
import com.arlania.util.Misc;

import java.awt.Color;

import com.arlania.GameSettings;
import com.arlania.Commands.Command;
import com.arlania.Commands.CommandInfo;
import com.arlania.world.World;
import com.arlania.world.content.discordbot.DiscordBot;
import com.arlania.world.entity.impl.player.Player;

import net.dv8tion.jda.core.EmbedBuilder;

@CommandInfo(
        command = {"claim"},
        description = "to claim donation"

)
public class store3 extends Command{

    @Override
    public void execute(Player player, String command) {
			new Thread() {
				public void run() {
					try {
						System.out.println(player.getUsername());
						com.everythingrs.donate.Donation[] donations = com.everythingrs.donate.Donation.donations("4NPnqxwOzv226sTFD4StwGoJ4u6RlxA7PRdkwKDOvAahKOMvZVQ4UdroyDzBlVLMdYlSJ0T1", player.getUsername());
						if (donations.length == 0) {
							player.getPacketSender().sendMessage("You currently don't have any items waiting. You must donate first!");
							return;
						}
						if (donations[0].message != null) {
							player.getPacketSender().sendMessage(donations[0].message);
							return;
						}
						for (com.everythingrs.donate.Donation donate : donations) {
							int random = Misc.random(50);
							if (player.getEquipment().get(Equipment.RING_SLOT).getId() == 4743) {
								player.getInventory().add(new Item(donate.product_id, donate.product_amount*2));
								World.sendMessage("<img=10> <shad=0>@bla@[@cya@Donation@bla@]@bla@[@or3@Ring Effect@bla@]@cya@ "+player.getUsername()+" @bla@has just Donated for  @cya@"+donate.product_amount*2+"X @bla@ @cya@"+donate.product_name+" @bla@ becuase of x2 ring!");
								EmbedBuilder embed = new EmbedBuilder();
								embed.setTitle("ImaginePS Donations");
								embed.setColor(Color.CYAN);
								embed.addField(" [Donation]"+player.getUsername()+" has just Donated for "+donate.product_amount*2+"X "+donate.product_name+" Becuase of x2 Ring!", "", false);
								embed.addField("Thanks! For Donations ","", true);
								
								DiscordBot.jda.getTextChannelById(GameSettings.DONATION_KEY).sendMessage(embed.build()).queue();
							} else  if(random > 1 && random < 5) {
								player.getInventory().add(new Item(donate.product_id, donate.product_amount*2));
								World.sendMessage("<img=10> <shad=0>@bla@[@mag@Donation@bla@]@bla@[@or1@Doubled@bla@]@mag@ "+player.getUsername()+" @bla@has just Donated for  @mag@"+donate.product_amount*2+"X @bla@ @mag@"+donate.product_name+" @bla@!");
								EmbedBuilder embed = new EmbedBuilder();
								embed.setTitle("ImaginePS Donations");
								embed.setColor(Color.RED);
								embed.addField(" [Donation][DOUBLED] "+player.getUsername()+" has just Donated for "+donate.product_amount*2+"X "+donate.product_name+" !!", "", false);
								embed.addField("Thanks! For Donations ","", true);
								
								DiscordBot.jda.getTextChannelById(GameSettings.DONATION_KEY).sendMessage(embed.build()).queue();
							} else if(random > 5) {
								player.getInventory().add(new Item(donate.product_id, donate.product_amount));
								World.sendMessage("<img=10> <shad=0>@bla@[@red@Donation@bla@]@mag@ "+player.getUsername()+" @bla@has just Donated for a  @red@"+donate.product_amount+"X @bla@ @red@"+donate.product_name+" @bla@!");
								EmbedBuilder embed = new EmbedBuilder();
								embed.setTitle("ImaginePS Donations");
								embed.setColor(Color.YELLOW);
								embed.addField(" [Donation] "+player.getUsername()+" has just Donated for "+donate.product_amount+"X "+donate.product_name+" !", "", false);
								embed.addField("Thanks! For Donations ","", true);
								DiscordBot.jda.getTextChannelById(GameSettings.DONATION_KEY).sendMessage(embed.build()).queue();
							}

							
						}
						player.getPacketSender().sendMessage("Thank you for donating!");
					} catch (Exception e) {
						player.getPacketSender().sendMessage("Api Services are currently offline. Please check back shortly");
						e.printStackTrace();
					}
				}
					
			}.start();
		}
    	
    }
