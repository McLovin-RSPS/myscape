package com.arlania;

import com.arlania.model.Item;
import com.arlania.world.entity.impl.player.Player;

import java.sql.*;


/**
 * do it below like so: new Thread(new Store(player)).start();
 */
public class Store extends Thread { //

	public static final String HOST = "142.44.142.157"; // dcan u just run it n the vps? make it lcalhost if u gonna run on
													// vps yeah yeah
	public static final String USER = "root";
	public static final String PASS = "u`aMbI?(Z*XE5kJ";
	public static final String DATABASE = "venex_store";//go to command

	private Player player;
	private Connection conn;
	private Statement stmt;

	//public boolean doubleReward() {
	//	return canDoubleChance();
	//}

	/**
	 * The constructor
	 * 
	 * @param player
	 */
	public Store(Player player) {
		this.player = player;
	}

	@Override
	public void run() {
		System.out.println("reached!1");
		try {
			if (!connect(HOST, DATABASE, USER, PASS)) {
				System.err.println("The connection to the store database has failed!");
				// World.sendStaffMessage("<img=4> <shad=0>@red@[MAJOR] @bla@Connection to the
				// store database has failed!");
				player.sendMessage("@red@Sorry there has been an error! We will resolve this");
				return;
			}

			System.out.println("reached2!");
			String name = player.getUsername().toLowerCase();
			ResultSet rs = executeQuery(
					"SELECT * FROM payment_history WHERE LOWER(username)='" + name + "' AND claimed=0");
			boolean filled = false; // try
			while (rs.next()) {
				filled = true;
				double price = rs.getDouble("pricePaid");
				int itemId = rs.getInt("item_id");
				int quantity = rs.getInt("qty");
				//player.getDonationDeals().handleRewards();
				rewardPlayer(itemId, quantity, price, true);
				rs.updateInt("claimed", 1); // do not delete otherwise they can reclaim!
				rs.updateRow(); // try
			}



			destroy();
			if (!filled) {
				player.getPacketSender().sendMessage("You currently don't have any items waiting. You must donate first!");
				return;
			}
//			player.getDonationDeals().displayReward();
//			player.getDonationDeals().displayTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("reached!"); // try
	}
	
	//public boolean canDoubleChance() {
	//	if (player.doubleDonationChances <= 0)
	//		return false;
	//	player.doubleDonationChances--;
	//	return Math.random() <= 0.50;
	//}

	/**
	 * @param itemId
	 * @param quantity
	 * @param price
	 */
	private void rewardPlayer(int itemId, int quantity, double price, boolean reward) {
		if (!reward)
			return;

		boolean doubled = false;//doubleReward();

		Item toClaim = new Item(itemId, doubled ? (quantity * 2) : quantity);

		boolean space = player.getInventory().getFreeSlots() >= (toClaim.getDefinition().isStackable() ? 1 : quantity);

		if (space) 
			player.getInventory().add(toClaim);
		 else
			player.getBank(0).add(toClaim);

		//player.incrementAmountDonated((int) price);
		player.setAmountDonatedToday((int) (player.getAmountDonatedToday() + price));
		//player.getDonationDeals().handleRewards();


		player.sendMessage("Thank you for supporting venex! \n - Your total donated today is now: $"
				+ player.getAmountDonatedToday() + ". \n Your reward of (" + toClaim.getAmount() + "x "
				+ toClaim.getDefinition().getName() + ") has been added to your " + (space ? "inventory" : "bank"));
//		//World.sendMessage("<img=10><col=00ff00><shad=0> " + player.getUsername() + " has just Donated for " +  toClaim.getDefinition().getName() +"! "
//				+ (doubleReward() ? "and got got a lucky DOUBLE DONATION!" : ""));
//		GlobalBossEvent.handleDonationTime((int) price);

	}

	/**
	 *
	 * @param host     the host ip address or url
	 * @param database the name of the database
	 * @param user     the user attached to the database
	 * @param pass     the users password
	 * @return true if connected
	 */
	public boolean connect(String host, String database, String user, String pass) {
		try {
			this.conn = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + database, user, pass);
			// try now, worked i think god, send me the files, rezip and export plz,ok how i
			return true;
		} catch (SQLException e) {
			System.out.println("Failing connecting to database!");
			return false;
		}
	}

	/**
	 * Disconnects from the MySQL server and destroy the connection and statement
	 * instances
	 */
	public void destroy() {
		try {
			conn.close();
			conn = null;
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** //copy this file and make a duplicat
	 * Executes an update query on the database
	 * 
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
	 * 
	 * @param query
	 * @see {@link Statement#executeQuery(String)}
	 * @return the results, never null
	 */
	public ResultSet executeQuery(String query) {
		try {
			this.stmt = this.conn.createStatement(1005, 1008);
			ResultSet results = stmt.executeQuery(query);
			System.out.println("jah");
			return results;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
