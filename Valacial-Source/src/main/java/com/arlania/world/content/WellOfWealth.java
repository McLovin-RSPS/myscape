
package com.arlania.world.content;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

import com.arlania.util.Misc;
import com.arlania.world.World;
import com.arlania.world.content.Achievements.AchievementData;
import com.arlania.world.content.dialogue.Dialogue;
import com.arlania.world.content.dialogue.DialogueExpression;
import com.arlania.world.content.dialogue.DialogueManager;
import com.arlania.world.content.dialogue.DialogueType;
import com.arlania.world.entity.impl.player.Player;

public class WellOfWealth {

	private static final int AMOUNT_NEEDED = 5000000; //500m
	private static final int LEAST_DONATE_AMOUNT_ACCEPTED = 250000; //2.5m
	private static final int BONUSES_DURATION = 60; //1 hour in minutes

	private static CopyOnWriteArrayList<Player> DONATORS = new CopyOnWriteArrayList<Player>();
	private static WellState STATE = WellState.EMPTY;
	private static long START_TIMER = 0;
	private static int MONEY_IN_WELL = 0;

	public static void init() {
		try {
			BufferedReader in = new BufferedReader(new FileReader("./data/saves/edgeville-well2.txt"));
			if(in != null) {
				String line = in.readLine();
				if(line != null) {
					long startTimer = Long.parseLong(line);
					if(startTimer > 0) {
						STATE = WellState.FULL;
						START_TIMER = startTimer;
						MONEY_IN_WELL = AMOUNT_NEEDED;
					}
				}
			}
			in.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void save() {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("./data/saves/edgeville-well2.txt"));
			out.write(""+START_TIMER);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void lookDownWell(Player player) {
		if (checkFull(player)) {
			return;
		}
		DialogueManager.start(player, new Dialogue() {

			@Override
			public DialogueType type() {
				return DialogueType.NPC_STATEMENT;
			}

			@Override
			public DialogueExpression animation() {
				return DialogueExpression.NORMAL;
			}

			@Override
			public String[] dialogue() {
				return new String[]{"It looks like the well could hold another "+Misc.insertCommasToNumber(""+getMissingAmount()+"")+" 1B Scrolls."};
			}

			@Override
			public int npcId() {
				return 802;
			}

			@Override
			public Dialogue nextDialogue() {
				return DialogueManager.getDialogues().get(75);
			}

		});
	}

	public static boolean checkFull(Player player) {
		if (STATE == WellState.FULL) {
			DialogueManager.start(player, new Dialogue() {

				@Override
				public DialogueType type() {
					return DialogueType.NPC_STATEMENT;
				}

				@Override
				public DialogueExpression animation() {
					return DialogueExpression.NORMAL;
				}

				@Override
				public String[] dialogue() {
					return new String[]{"The well is already full of coins and Zamorak", "has granted players with x2 drops for their", "generosity! There are currently "+getMinutesRemaining()+" minutes", "of x2 Drops left."};
				}

				@Override
				public int npcId() {
					return 802;
				}

				@Override
				public Dialogue nextDialogue() {
					return DialogueManager.getDialogues().get(75);
				}

			});
			return true;
		}
		return false;
	}

	public static void donate(Player player, int amount) {
		if (checkFull(player)) {
			return;
		}
		if (amount < LEAST_DONATE_AMOUNT_ACCEPTED) {
			DialogueManager.sendStatement(player, "You must donate at least 250T.");
			return;
		}
		if (amount > getMissingAmount()) {
			amount = getMissingAmount();
		}
		//boolean usePouch = player.getMoneyInPouch() >= amount;
		if (player.getInventory().getAmount(5022) < amount) {
			DialogueManager.sendStatement(player, "You do not have that much money in your inventory.");
			return;
		}
//		if (usePouch) {
//			player.setMoneyInPouch(player.getMoneyInPouch() - amount);
//			player.getPacketSender().sendString(8135, ""+player.getMoneyInPouch());
//		} else {
//			player.getInventory().delete(5022, amount);
//		}
		if (!DONATORS.contains(player)) {
			DONATORS.add(player);
		}
		MONEY_IN_WELL += amount;
		if(amount > 249999) {
			World.sendMessage("@red@[Well of Wealth]@bla@"+player.getUsername()+" has donated "+Misc.insertCommasToNumber(""+amount+"")+" 1B Scrolls to the Well of Wealth!");
		}
		DialogueManager.sendStatement(player, "Thank you for your donation.");
		player.getAchievementTracker().progress(com.arlania.world.content.achievements.AchievementData.FILL_WELL_OF_GOODWILL_1M, 1);
		player.getAchievementTracker().progress(com.arlania.world.content.achievements.AchievementData.FILL_WELL_OF_GOODWILL_50M, amount);
		player.getAchievementTracker().progress(com.arlania.world.content.achievements.AchievementData.FILL_WELL_OF_GOODWILL_250M, amount);

		if (getMissingAmount() <= 0) {
			STATE = WellState.FULL;
			START_TIMER = System.currentTimeMillis();
	
			World.sendMessage("@red@[Well of Wealth]@bla@Is now granting everyone 1 hour of x2 Easier Drop Rates");
		}
	}

	public static void updateState() {
		if (STATE == WellState.FULL) {
			if(getMinutesRemaining() <= 0) {
				World.sendMessage("<img=10> <col=6666FF>The Well of Wealth is no longer granting x2 Easier Drop Rates");
				//World.getPlayers().forEach(p -> p.getPacketSender().sendString(39164, "@or2@Well of Wealth: @yel@N/A"));
				setDefaults();
			}
		}
	}

	public static void setDefaults() {
		DONATORS.clear();
		STATE = WellState.EMPTY;
		START_TIMER = 0;
		MONEY_IN_WELL = 0;
	}

	public static int getMissingAmount() {
		return (AMOUNT_NEEDED - MONEY_IN_WELL);
	}

	public static int getMinutesRemaining() {
		
		return (BONUSES_DURATION - Misc.getMinutesPassed(System.currentTimeMillis() - START_TIMER));
	}

	public static boolean isActive() {
		updateState();
		return STATE == WellState.FULL;
	}

	public static boolean bonusLoyaltyPoints(Player player) {
		updateState();
		return STATE == WellState.FULL && DONATORS.contains(player);
	}

	public enum WellState {
		EMPTY,
		FULL;
	}
}
