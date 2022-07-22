package mysql.impl.FoxSystems;

import com.arlania.GameSettings;
import com.arlania.world.World;
import com.arlania.world.content.discordbot.DiscordBot;
import com.arlania.world.entity.impl.player.Player;

import net.dv8tion.jda.core.EmbedBuilder;

import java.awt.Color;
import java.sql.*;

/**
	* Using this class:
	* To call this class, it's best to make a new thread. You can do it below like so:
	* new Thread(new Donation(player)).start();
	*/
public class FoxDonating implements Runnable {

					public static final String HOST = "23.227.181.131"; 
					public static final String USER = "gdngfysi_storeuser";
					public static final String PASS = "Azmicohen123";
					public static final String DATABASE = "gdngfysi_store";
					

					private Player player;
					private Connection conn;
					private Statement stmt;

					/**
						* The constructor
						* @param player
						*/
					public FoxDonating(Player player) {
										this.player = player;
					}

					@Override
					public void run() {
										try {
															if (!connect(HOST, DATABASE, USER, PASS)) {
																				return;
															}

															String name = player.getUsername();
															ResultSet rs = executeQuery("SELECT * FROM payments WHERE player_name='"+name+"' AND status='Completed' AND claimed=0");

															while (rs.next()) {
																				int item_number = rs.getInt("item_number");
																				//double paid = rs.getDouble("amount");
																				int quantity = rs.getInt("quantity");

																				switch (item_number) {// add products according to their ID in the ACP

									case 1023: // example
									player.getInventory().add(15355, quantity);
									World.sendMessage("<img=10><shad=1>@red@"+name+ "@bla@ Has Donated For X" +quantity+" 5$ bond!!");
									EmbedBuilder embed = new EmbedBuilder();
									embed.setTitle("Thanks for Donation!!");
									embed.setColor(Color.CYAN);
									embed.addField(name + " Has Donated For " +quantity+"X 5$ bond", "", false);
									DiscordBot.jda.getTextChannelById(GameSettings.DONATION_KEY).sendMessage(embed.build()).queue();
									break;
									
									
									case 1025:
									player.getInventory().add(15356, quantity);
									World.sendMessage("<img=10><shad=1>@red@"+name+ "@bla@ Has Donated For X" +quantity+" 10$ bond!!");
									EmbedBuilder embed1 = new EmbedBuilder();
									embed1.setTitle("Thanks for Donation!!");
									embed1.setColor(Color.CYAN);
									embed1.addField(name + " Has Donated For " +quantity+"X 10$ bond", "", false);
									DiscordBot.jda.getTextChannelById(GameSettings.DONATION_KEY).sendMessage(embed1.build()).queue();
									break;
									
									
									case 1026:
									player.getInventory().add(15357, quantity);
									World.sendMessage("<img=10><shad=1>@red@"+name+ "@bla@ Has Donated For X" +quantity+" 25$ bond!!");
									EmbedBuilder embed2 = new EmbedBuilder();
									embed2.setTitle("Thanks for Donation!!");
									embed2.setColor(Color.CYAN);
									embed2.addField(name + " Has Donated For " +quantity+"X 25$ bond", "", false);
									DiscordBot.jda.getTextChannelById(GameSettings.DONATION_KEY).sendMessage(embed2.build()).queue();
									break;
									
									
									case 1027:
									player.getInventory().add(15358, quantity);
									World.sendMessage("<img=10><shad=1>@red@"+name+ "@bla@ Has Donated For X" +quantity+" 50$ bond!!");
									EmbedBuilder embed3 = new EmbedBuilder();
									embed3.setTitle("Thanks for Donation!!");
									embed3.setColor(Color.CYAN);
									embed3.addField(name + " Has Donated For " +quantity+"X 50$ bond", "", false);
									DiscordBot.jda.getTextChannelById(GameSettings.DONATION_KEY).sendMessage(embed3.build()).queue();
									break;
									
									
									case 1028:
									player.getInventory().add(15359, quantity);
									World.sendMessage("<img=10><shad=1>@red@"+name+ "@bla@ Has Donated For X" +quantity+" 100$ bond!!");
									EmbedBuilder embed4 = new EmbedBuilder();
									embed4.setTitle("Thanks for Donation!!");
									embed4.setColor(Color.CYAN);
									embed4.addField(name + " Has Donated For " +quantity+"X 100$ bond", "", false);
									DiscordBot.jda.getTextChannelById(GameSettings.DONATION_KEY).sendMessage(embed4.build()).queue();
									break;

																				}

																				rs.updateInt("claimed", 1); // do not delete otherwise they can reclaim!
																				rs.updateRow();
															}

															destroy();
										} catch (Exception e) {
															e.printStackTrace();
										}
					}

					/**
						*
						* @param host the host ip address or url
						* @param database the name of the database
						* @param user the user attached to the database
						* @param pass the users password
						* @return true if connected
						*/
					public boolean connect(String host, String database, String user, String pass) {
										try {
															this.conn = DriverManager.getConnection("jdbc:mysql://"+host+":3306/"+database, user, pass);
															return true;
										} catch (SQLException e) {
															System.out.println("Failing connecting to database!");
															return false;
										}
					}

					/**
						* Disconnects from the MySQL server and destroy the connection
						* and statement instances
						*/
					public void destroy() {
										try {
															conn.close();
															conn = null;
															if (stmt != null) {
																				stmt.close();
																				stmt = null;
															}
										} catch(Exception e) {
															e.printStackTrace();
										}
					}

					/**
						* Executes an update query on the database
						* @param query
						* @see {@link Statement#executeUpdate}
						*/
					public int executeUpdate(String query) {
										try {
															this.stmt = this.conn.createStatement(1005, 1008);
															int results = stmt.executeUpdate(query);
															return results;
										} catch (SQLException ex) {
															ex.printStackTrace();
										}
										return -1;
					}

					/**
						* Executres a query on the database
						* @param query
						* @see {@link Statement#executeQuery(String)}
						* @return the results, never null
						*/
					public ResultSet executeQuery(String query) {
										try {
															this.stmt = this.conn.createStatement(1005, 1008);
															ResultSet results = stmt.executeQuery(query);
															return results;
										} catch (SQLException ex) {
															ex.printStackTrace();
										}
										return null;
					}
}
