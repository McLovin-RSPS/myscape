package com.arlania.world;

import com.arlania.GameSettings;
import com.arlania.engine.task.Task;
import com.arlania.engine.task.TaskManager;
import com.arlania.model.Item;
import com.arlania.model.PlayerRights;
import com.arlania.model.definitions.NPCDrops;
import com.arlania.net.login.RemoteAddressFilter;
import com.arlania.util.Misc;
import com.arlania.world.content.*;
import com.arlania.world.content.minigames.MinigameHandler2;
import com.arlania.world.content.minigames.impl.FightPit;
import com.arlania.world.content.minigames.impl.FreeForAll;
import com.arlania.world.content.minigames.impl.PestControl;
import com.arlania.world.content.minigames.impl.weapon.WeaponGame;
import com.arlania.world.entity.Entity;
import com.arlania.world.entity.EntityHandler;
import com.arlania.world.entity.impl.CharacterList;
import com.arlania.world.entity.impl.npc.NPC;
import com.arlania.world.entity.impl.player.Player;
import com.arlania.world.entity.impl.player.PlayerBackup;
import com.arlania.world.entity.impl.player.PlayerHandler;
import com.arlania.world.entity.updating.NpcUpdateSequence;
import com.arlania.world.entity.updating.PlayerUpdateSequence;
import com.arlania.world.entity.updating.UpdateSequence;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.logging.Logger;

import static com.google.common.base.Preconditions.checkNotNull;

//import mysql.impl.Voting;

/**
 * @author Gabriel Hannason
 * Thanks to lare96 for help with parallel updating system
 */
public class World {
	@Getter
	@Setter
	public static RemoteAddressFilter remoteAddressFilter = new RemoteAddressFilter();



	private static final Logger logger = Logger.getLogger("ImaginePS");


	public static long[] bossSpawns = new long[4];



	/** All of the registered players. */
	private static CharacterList<Player> players = new CharacterList<>(1000);

	/** All of the registered NPCs. */
	private static CharacterList<NPC> npcs = new CharacterList<>(2300);

	/** Used to block the game thread until updating has completed. */
	private static Phaser synchronizer = new Phaser(1);

	/** A thread pool that will update players in parallel. */
	private static ExecutorService updateExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors(), new ThreadFactoryBuilder().setNameFormat("UpdateThread").setPriority(Thread.MAX_PRIORITY).build());

	/** The queue of {@link Player}s waiting to be logged in. **/
    private static Queue<Player> logins = new ConcurrentLinkedQueue<>();

    /**The queue of {@link Player}s waiting to be logged out. **/
    private static Queue<Player> logouts = new ConcurrentLinkedQueue<>();
    
    /**The queue of {@link Player}s waiting to be given their vote reward. **/
    private static Queue<Player> voteRewards = new ConcurrentLinkedQueue<>();
    
    public static MinigameHandler2 minigameHandler;

    public static String dartVader = "Alive";
    public static String DeathReaper = "Alive";
    public static String IndiansProtec = "Alive";
    public static String ShadowLord = "Alive";
    public static String Vorage = "Alive";

	public static final int SUNDAY = 1, MONDAY = 2, TUESDAY = 3, WEDNESDAY = 4, THURSDAY = 5, FRIDAY = 6, SATURDAY = 7;

	public static int getGlobalDay() {
		Calendar calendar = new GregorianCalendar();
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	public static String getEventForDay() {
		switch (getGlobalDay()) {
			case MONDAY:
				return "+10% Server-Wide Drop Rate Boost";
			case TUESDAY:
				return "2x Boss Points";
			case WEDNESDAY:
				return "2x Slayer Points";
			case THURSDAY:
				return "2x Vote Rewards";
			case FRIDAY:
			case SATURDAY:
			case SUNDAY:
				return "Triple XP & +20% Server-Wide Drop Rate Boost";
			default:
				return "No Events!";
		}
	}

	public static boolean isEvent(int day) {
		return getGlobalDay() == day;
	}

	public static String getDay() {
		int dayId = getGlobalDay();
		if (dayId == 1)
			return "Sunday";
		else if (dayId == 2)
			return "Monday";
		else if (dayId == 3)
			return "Tuesday";
		else if (dayId == 4)
			return "Wednesday";
		else if (dayId == 5)
			return "Thursday";
		else if (dayId == 6)
			return "Friday";
		else
			return "Saturday";
	}

	public static boolean isWeekend() {
		return getGlobalDay() == 6 || getGlobalDay() == 7 || getGlobalDay() == 1;
	}


    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static Map<String, String> claimedCodeUsers = new HashMap<String, String>();

	public static String notifyName = null;

	public static Item notifyItem = null;

	public static int notifyLimit;

	public static int notifyUsed;
	
	
    
    public static void register(Entity entity) {
		EntityHandler.register(entity);
	}

	public static void deregister(Entity entity) {
		EntityHandler.deregister(entity);
	}

	public static Player getPlayerByName(String username) {
		Optional<Player> op = players.search(p -> p != null && p.getUsername().equals(Misc.formatText(username)));
		return op.isPresent() ? op.get() : null;
	}
	
	public static Player getPlayerByIndex(int username) {
		Optional<Player> op = players.search(p -> p != null && p.getIndex() == username);
		return op.isPresent() ? op.get() : null;
	}

	public static Player getPlayerByLong(long encodedName) {
		Optional<Player> op = players.search(p -> p != null && p.getLongUsername().equals(encodedName));
		return op.isPresent() ? op.get() : null;
	}

	public static void sendMessage(String message) {
		players.forEach(p -> p.getPacketSender().sendMessage(message));
	}
	
	public static void sendStaffMessage(String message) {
		players.stream().filter(p -> p != null && (p.getRights() == PlayerRights.OWNER | p.getRights() == PlayerRights.ADMINISTRATOR || p.getRights() == PlayerRights.MODERATOR || p.getRights() == PlayerRights.SUPPORT)).forEach(p -> p.getPacketSender().sendMessage(message));
	}
	
	public static void updateServerTime() {
		players.forEach(p-> p.getPacketSender().sendString(50862, "@or1@Server Time: @gre@"+Misc.getCurrentServerTime()));

	}

	public static void updatePlayersOnline() {
		
		//players.forEach(p-> p.getPacketSender().sendString(39173, PlayerPanel.LINE_START + "@or1@Players Online: @yel@"+players.size()));
		players.forEach(p -> p.getPacketSender().sendString(26608, "@or2@Players Online: @gre@"+(int)(players.size()  )+""));
		players.forEach(p -> p.getPacketSender().sendString(57003, "Players:  @gre@"+(int)(World.getPlayers().size()   )+""));
		players.forEach(p -> p.getPacketSender().sendString(50863, "Players online: @gre@" + World.getPlayers().size() + ""));
	}
		public static void equipmentinterface(Player player) {
		players.forEach(p -> p.getPacketSender().sendString(66100, "DropRate: @gre@"+NPCDrops.getDroprate(player,true)));
		updateStaffList();
	}


	public static void updateStaffList() {
		TaskManager.submit(new Task(false) {
			@Override
			protected void execute() {
				players.forEach(p -> StaffList.updateInterface(p));
				stop();
			}
		});
	}

	private static final Queue<Runnable> callbacks = new ArrayDeque<>(256);


	public static void callback(Runnable action) {
		checkNotNull(action, "action");

		synchronized (callbacks) {
			callbacks.add(action);
		}
	}

	public static void savePlayers() {
		players.forEach(p -> p.save());
	}

	public static CharacterList<Player> getPlayers() {
		return players;
	}

	public static CharacterList<NPC> getNpcs() {
		return npcs;
	}
	
	public static void sequence() {


        // Handle queued logouts.
        int amount2 = 0;
        Iterator<Player> $it = logouts.iterator();
        while ($it.hasNext()) {
            Player player = $it.next();
            if (player == null || amount2 >= GameSettings.LOGOUT_THRESHOLD)
                break;
            if (PlayerHandler.handleLogout(player)) {
                $it.remove();
                amount2++;
            }
        }
        
              // Handle queued vote rewards
        for(int i = 0; i < GameSettings.VOTE_REWARDING_THRESHOLD; i++) {
            Player player = voteRewards.poll();
            if (player == null)
                break;
            //Voting.handleQueuedReward(player);
        }

        try {

			//BotManager.process();
			GlobalBossEvent.process();
			FightPit.sequence();
			//Cows.sequence();
			//Cows.spawnMainNPCs();
			Reminders.sequence();
			PestControl.sequence();
			ShootingStar.sequence();
			WeaponGame.sequence();
			EvilTrees.sequence();
			TriviaSystem.tick();
			//ShopRestocking.sequence();
			FreeForAll.sequence();
			PlayerBackup.sequence();
			// First we construct the update sequences.
			UpdateSequence<Player> playerUpdate = new PlayerUpdateSequence(synchronizer, updateExecutor);
			UpdateSequence<NPC> npcUpdate = new NpcUpdateSequence();
			// Then we execute pre-updating code.
			players.forEach(playerUpdate::executePreUpdate);
			npcs.forEach(npcUpdate::executePreUpdate);
			// Then we execute parallelized updating code.
			synchronizer.bulkRegister(players.size());
			players.forEach(playerUpdate::executeUpdate);
			synchronizer.arriveAndAwaitAdvance();
			// Then we execute post-updating code.
			players.forEach(playerUpdate::executePostUpdate);
			npcs.forEach(npcUpdate::executePostUpdate);

			if (!callbacks.isEmpty()) {
				synchronized (callbacks) {
					int size = Math.min(100, callbacks.size());
					int failCount = 0;
					for (int i = 0; i < size; i++) {
						Runnable task = callbacks.poll();
						if (task == null) break;
						try {
							task.run();
						} catch (Throwable t) {
							failCount++;
							logger.warning("Uncaught exception in task: " + task);
							t.printStackTrace();
						}
					}
					if (failCount != 0)
						logger.info("Executed " + size + " callbacks (" + callbacks.size() + " remain.)");
					else
						logger.info("Executed " + size + " callbacks ({" + callbacks.size() + "} remain, {" + failCount + "} failed exceptionally)");

				}
			}
		}catch (Error e) {
        	System.out.println("FUCK!!!");
        	e.printStackTrace();
		}

		// Handle queued logins.
		for (int amount = 0; amount < GameSettings.LOGIN_THRESHOLD; amount++) {
			Player player = logins.poll();
			if (player == null)
				break;
			PlayerHandler.handleLogin(player);
		}
	}
	
	public static Queue<Player> getLoginQueue() {
		return logins;
	}
	
	public static Queue<Player> getLogoutQueue() {
		return logouts;
	}
	
	public static Queue<Player> getVoteRewardingQueue() {
		return voteRewards;
	}

	public static RemoteAddressFilter getRemoteAddressFilter() {
		// TODO Auto-generated method stub
		return null;
	}
}
