package mysql.impl;

import com.arlania.model.Item;
import com.arlania.world.World;
import com.arlania.world.content.GlobalBossEvent;
import com.arlania.world.entity.impl.player.Player;

import java.sql.*;
import java.util.Random;

import static com.arlania.model.actions.items.scrolls.MemberScrolls.checkForRank;


/**
 * do it below like so: new Thread(new Store(player)).start();
 */
public class Store extends Thread { //

	public static final String HOST = "142.44.142.157"; // dcan u just run git n the vps? make it lcalhost if u gonna run on
	// vps yeah yeah
	public static final String USER = "root";
	public static final String PASS = "#4&`NKR7y:xbv^";
	public static final String DATABASE = "ImaginePS";//go to command

	private Player player;
	private Connection conn;
	private Statement stmt;


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
				System.out.println("The connection to the store database has failed!");
				return;
			}

			System.out.println("reached2!");
			String name = player.getUsername().toLowerCase();
			ResultSet rs = executeQuery(
					"SELECT * FROM payment_history WHERE LOWER(username)='" + name + "' AND claimed=0");
			boolean filled = false;
			while (rs.next()) {
				filled = true;
				double price = rs.getDouble("pricePaid");
				int itemId = rs.getInt("item_id");
				int quantity = rs.getInt("qty");
				rewardPlayer(itemId, quantity, price, true);
				rs.updateInt("claimed", 1);
				rs.updateRow();
			}

			if (!filled)
				player.getPacketSender()
						.sendMessage("You currently don't have any items waiting. You must donate first!");
			else {
				Random rnd = new Random();
				int number = rnd.nextInt(9999);

				// this will convert any number sequence into 6 character.
				String code = player.getUsername().substring(0,2)+String.format("%04d", number);
				executeUpdate(
						"INSERT INTO discount (name, discount, uses) VALUES('"+code+"', 10, 1)");
				player.sendMessage("For a 10% discount on your next purchase, use the code @red@"+code+"@bla@ at checkout!"); //this'll work on that?
			}

			destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("reached!"); // try
	}

	/**
	 * @param itemId
	 * @param quantity
	 * @param price
	 * @param b
	 */
	private void rewardPlayer(int itemId, int quantity, double price, boolean reward) {
		if (!reward)
			return;


		Item toClaim = new Item(itemId, quantity);



		boolean space = player.getInventory().getFreeSlots() >= (toClaim.getDefinition().isStackable() ? 1 : quantity);

		if (space)
			player.getInventory().add(toClaim);
		else
			player.getBank(0).add(toClaim);

		player.incrementAmountDonated((int) price);
		player.setAmountDonatedToday((int) (player.getAmountDonatedToday() + price));
		player.getDonationDeals().handleRewards();
		player.sendMessage("@red@Thank you for supporting ImaginePS!@bla@ \n - @red@Your total donated is now@bla@: $"
				+ player.getAmountDonated() + ".\n - @reYour total donated today is now: $"
				+ player.getAmountDonatedToday() + ". \n Your reward of (" + toClaim.getAmount() + "x "
				+ toClaim.getDefinition().getName() + ") has been added to your " + (space ? "inventory" : "bank"));
		//World.sendMessage("<img=10><shad=0>@bla@[@mag@Donated@mag@]@bla@"+player.getUsername()+"@bla@ has just Donated@mag@@bla@!");
		GlobalBossEvent.handleDonationTime((int) price);
		checkForRank(player);

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
