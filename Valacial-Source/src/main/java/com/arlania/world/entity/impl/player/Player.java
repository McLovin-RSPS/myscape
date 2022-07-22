package com.arlania.world.entity.impl.player;

import com.arlania.GameSettings;
import com.arlania.engine.task.Task;
import com.arlania.engine.task.TaskManager;
import com.arlania.engine.task.impl.PlayerDeathTask;
import com.arlania.engine.task.impl.WalkToTask;
import com.arlania.model.Animation;
import com.arlania.model.Appearance;
import com.arlania.model.CharacterAnimations;
import com.arlania.model.ChatMessage;
import com.arlania.model.Direction;
import com.arlania.model.DwarfCannon;
import com.arlania.model.Flag;
import com.arlania.model.GameMode;
import com.arlania.model.GameObject;
import com.arlania.model.Gender;
import com.arlania.model.Item;
import com.arlania.model.Locations;
import com.arlania.model.MagicSpellbook;
import com.arlania.model.PlayerInteractingOption;
import com.arlania.model.PlayerRelations;
import com.arlania.model.PlayerRights;
import com.arlania.model.Position;
import com.arlania.model.Prayerbook;
import com.arlania.model.Skill;
import com.arlania.model.UpdateFlag;
import com.arlania.model.container.impl.Bank;
import com.arlania.model.container.impl.Bank.BankSearchAttributes;
import com.arlania.model.container.impl.Equipment;
import com.arlania.model.container.impl.GambleOfferItemContainer;
import com.arlania.model.container.impl.Inventory;
import com.arlania.model.container.impl.PriceChecker;
import com.arlania.model.container.impl.Shop;
import com.arlania.model.definitions.ItemDefinition;
import com.arlania.model.definitions.WeaponAnimations;
import com.arlania.model.definitions.WeaponInterfaces;
import com.arlania.model.definitions.WeaponInterfaces.WeaponInterface;
import com.arlania.model.input.Input;
import com.arlania.model.input.impl.EnterAccountPin;
import com.arlania.model.input.impl.EnterCreatePin;
import com.arlania.model.movement.MovementQueue;
import com.arlania.net.PlayerSession;
import com.arlania.net.SessionState;
import com.arlania.net.packet.ByteOrder;
import com.arlania.net.packet.Packet;
import com.arlania.net.packet.PacketBuilder;
import com.arlania.net.packet.PacketSender;
import com.arlania.net.packet.ValueType;
import com.arlania.util.FrameUpdater;
import com.arlania.util.Stopwatch;
import com.arlania.world.World;
import com.arlania.world.content.Achievements.AchievementAttributes;
import com.arlania.world.content.BankPin.BankPinAttributes;
import com.arlania.world.content.BonusManager;
import com.arlania.world.content.DailyRewards;
import com.arlania.world.content.DonationDeals;
import com.arlania.world.content.DropLog.DropLogEntry;
import com.arlania.world.content.GodMode;
import com.arlania.world.content.KillsTracker.KillsEntry;
import com.arlania.world.content.LoyaltyProgramme.LoyaltyTitles;
import com.arlania.world.content.MysteryBoxOpener;
import com.arlania.world.content.NpcSpawner;
import com.arlania.world.content.PVMRanking;
import com.arlania.world.content.PlayerDropLog;
import com.arlania.world.content.PointsHandler;
import com.arlania.world.content.ProfileViewing;
import com.arlania.world.content.ProgressionManager;
import com.arlania.world.content.StartScreen;
import com.arlania.world.content.TeleportEnum;
import com.arlania.world.content.Trading;
import com.arlania.world.content.achievements.AchievementInterface;
import com.arlania.world.content.achievements.AchievementTracker;
import com.arlania.world.content.bossEvent.BossEventData;
import com.arlania.world.content.box.GoodieBagSponsor;
import com.arlania.world.content.box.GoodieBagbfg9000;
import com.arlania.world.content.box.GoodieBagtrivia;
import com.arlania.world.content.box.GoodiebagEternal;
import com.arlania.world.content.clan.ClanChat;
import com.arlania.world.content.collectionlog.CollectionLog;
import com.arlania.world.content.collectionlog.CollectionLogData;
import com.arlania.world.content.collectionlog.CollectionLogPage;
import com.arlania.world.content.combat.CombatFactory;
import com.arlania.world.content.combat.CombatType;
import com.arlania.world.content.combat.effect.CombatPoisonEffect.CombatPoisonData;
import com.arlania.world.content.combat.magic.CombatSpell;
import com.arlania.world.content.combat.magic.CustomMagicStaff;
import com.arlania.world.content.combat.prayer.CurseHandler;
import com.arlania.world.content.combat.prayer.PrayerHandler;
import com.arlania.world.content.combat.pvp.PlayerKillingAttributes;
import com.arlania.world.content.combat.range.CombatRangedAmmo.RangedWeaponData;
import com.arlania.world.content.combat.strategy.CombatStrategies;
import com.arlania.world.content.combat.strategy.CombatStrategy;
import com.arlania.world.content.combat.strategy.impl.customraids.CustomRaids;
import com.arlania.world.content.combat.weapon.CombatSpecial;
import com.arlania.world.content.combat.weapon.FightType;
import com.arlania.world.content.daily_reward.DailyReward;
import com.arlania.world.content.dialogue.Dialogue;
import com.arlania.world.content.gambling.GamblingManager;
import com.arlania.world.content.gamblinginterface.GamblingInterface;
import com.arlania.world.content.grandexchange.GrandExchangeSlot;
import com.arlania.world.content.itemloading.BlowpipeLoading;
import com.arlania.world.content.itemloading.CorruptBandagesLoading;
import com.arlania.world.content.itemloading.DragonRageLoading;
import com.arlania.world.content.itemloading.MinigunLoading;
import com.arlania.world.content.minigames.Minigame;
import com.arlania.world.content.minigames.MinigameAttributes;
import com.arlania.world.content.minigames.impl.Dueling;
import com.arlania.world.content.minigames.impl.weapon.WeaponGame;
import com.arlania.world.content.mysteryboxes.MysteryBox;
import com.arlania.world.content.newminigames.impl.stadium.StadiumController;
import com.arlania.world.content.pos.PlayerOwnedShopManager;
import com.arlania.world.content.skill.CustomCombiner;
import com.arlania.world.content.skill.SkillManager;
import com.arlania.world.content.skill.impl.construction.ConstructionData.HouseLocation;
import com.arlania.world.content.skill.impl.construction.ConstructionData.HouseTheme;
import com.arlania.world.content.skill.impl.construction.HouseFurniture;
import com.arlania.world.content.skill.impl.construction.Portal;
import com.arlania.world.content.skill.impl.construction.Room;
import com.arlania.world.content.skill.impl.farming.Farming;
import com.arlania.world.content.skill.impl.slayer.Slayer;
import com.arlania.world.content.skill.impl.summoning.BossPets.BossPet;
import com.arlania.world.content.skill.impl.summoning.Pouch;
import com.arlania.world.content.skill.impl.summoning.Summoning;
import com.arlania.world.content.starterzone.StarterZone;
import com.arlania.world.content.teleport.TeleportInterface;
import com.arlania.world.content.transportation.TeleportData1;
import com.arlania.world.content.transportation.TeleportType1;
import com.arlania.world.content.tutorial.TutorialStages;
import com.arlania.world.entity.Entity;
import com.arlania.world.entity.impl.Character;
import com.arlania.world.entity.impl.bot.BotPlayer;
import com.arlania.world.entity.impl.npc.NPC;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import lombok.Getter;
import lombok.Setter;

public class Player extends Character {

	private final TeleportInterface teleportInterface = new TeleportInterface(this);

	public TeleportInterface getTeleportInterface() {
		return teleportInterface;
	}

	public final StarterZone starterZone = new StarterZone(this);

	/*
	 * Collection log variables
	 */
	public CollectionLogPage collectionLogPageOpen = null;
	public CollectionLogData collectionLogView = null;

	public CollectionLog collectionLog = new CollectionLog();
	public CollectionLog getCollectionLog() {
		return this.collectionLog;
	}


	private boolean placeholders = false;

	public boolean isPlaceholders() {
		return placeholders;
	}

	public void setPlaceholders(boolean placeholders) {
		this.placeholders = placeholders;
	}

	public List<Integer> npcDrops = new ArrayList<>();
	/*
	* <Potion, currentDoses>
	 */
	public int damage = 0;

	@Getter
	@Setter
	public CustomRaids customRaids = new CustomRaids(this);
	public transient int pinAttempts = 0;
	/**
	 * Better account pins
	 */
	public boolean enteredPin;

	public long incorrectPinTimeOut = 0;
	public String accountPin = "", lastIP = "";

	public boolean hasAccountPin() {
		return accountPin.length() > 0;
	}

	public boolean requiresPin() {
		if (!hasAccountPin() || lastIP.isEmpty() || this.newPlayer())
			return false;
		return !this.lastIP.equalsIgnoreCase(this.getHostAddress());
	}

	public void promptPin(boolean retry) {
		if (retry) {
			if (++pinAttempts > 3) {
				incorrectPinTimeOut = System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(10);
				//LOG HERE
				World.sendStaffMessage("<col=ff0000>(Security): "+this.getUsername()+" has been account locked for 10Minutes! reason: incorrect pin "+pinAttempts+"x times!");
				this.save();
				World.deregister(this);
				return;
			}
			this.sendMessage("Incorrect pin, try again.");
		}
		System.out.println("sending shit for pin..");
		this.setInputHandling(new EnterAccountPin());
		this.getPacketSender().sendEnterInputPrompt("Enter your account pin:");
	}

	public void createAccountPin(boolean retry) {
		if (this.hasAccountPin()) {
			this.sendMessage("You already have an account pin!");
			return;
		}
		this.setInputHandling(new EnterCreatePin());
		this.getPacketSender().sendEnterInputPrompt(retry ? "Enter a pin that is 4 characters long:" : "Thanks for joining discord. Enter an account pin you would like to use:");

}
	public int DamageDealth = 0;

	/*
	 * Daily reward variables
	 */
	private DailyReward dailyReward = new DailyReward(this);

	public DailyReward getDailyReward() {
		return dailyReward;
	}

	private boolean claimedTodays = false;
	public transient int dissolveId, dissolveOrbAmount, dissolveXP;
	public boolean getClaimedTodays() {
		return claimedTodays;
	}

	public void setClaimedTodays(boolean claimedTodays) {
		this.claimedTodays = claimedTodays;
	}

	public static String getTimeLeft(Long millis) {
		long days = TimeUnit.MILLISECONDS.toDays(millis);
		millis -= TimeUnit.DAYS.toMillis(days);
		long hours = TimeUnit.MILLISECONDS.toHours(millis);
		millis -= TimeUnit.HOURS.toMillis(hours);
		long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
		millis -= TimeUnit.MINUTES.toMillis(minutes);
		long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);
		if (millis < 1) {
			return ": @gre@Alive";
		}
		if (minutes > 0) {
			return ":@red@ " + minutes + ":" + seconds + " minutes";
		} else {
			return ":@red@ " + seconds + " seconds";
		}
	}

	public static int questTabInterfaceId = 0;

	public boolean hasReferral;

	public boolean isBotPlayer() {
		return this instanceof BotPlayer;
	}//iswear ur way dumber than ithought why tf would u copy a command to player,java idfk faggot

	public Timer timer2;


	public static void exe(Player player){
//player.timer2 = new Timer();
//		player.timer2.scheduleAtFixedRate(new TimerTask()
//		{
//			int count = 1;
//
//			public void run()
//			{
//				try {
//					if (!player.getCombatBuilder().isAttacking()) {
//						count = 0;
//						player.damage = 0;
//						player.getPacketSender().sendString(53322, "0 Dps");
//						player.timer2.cancel();
//						player.timer2.purge();
//						return;
//					}
//
//					if (player.damage > 0) {
//							player.getPacketSender().sendString(53322, (player.damage/count)+" Dps");
//						player.getPacketSender().sendMessage("test"+player.damage);
//						//player.getPacketSender().sendMessage("Dps: "+player.getCombatBuilder().getVictim().getCombatBuilder().getDamageMap().get(player).getDamage() / count[0]);
//
//					} else {
//						player.getPacketSender().sendString(53322, "0 Dps");
//						//	player.getPacketSender().sendMessage("Testing 0");
//						player.getPacketSender().sendMessage("test"+player.damage);
//
//					}
//				} catch(NullPointerException e) {
//					System.out.println(e);
//				}
//
//				count++;
//
//			}
//		}, 0, 1000);
	}

    private int amountDonatedToday;

    public int getAmountDonatedToday() {
        return amountDonatedToday;
    }
	private MysteryBoxOpener mysteryBoxOpener = new MysteryBoxOpener(this);
	
	public MysteryBoxOpener getMysteryBoxOpener() {
		return mysteryBoxOpener;
	}
    public int xPosDailyTask;

	public int getxPosDailyTask() {
		return xPosDailyTask;
	}
	
    private CustomCombiner customCombiner = new CustomCombiner(this);

    public CustomCombiner getCustomCombiner() {
        return customCombiner;
    }

	public void setxPosDailyTask(int xPosDailyTask) {
		this.xPosDailyTask = xPosDailyTask;
	}

	public boolean hasPlayerCompletedBossTask = false;

	public boolean isHasPlayerCompletedBossTask() {
		return hasPlayerCompletedBossTask;
	}
	
    int dailyClaimed = Calendar.DAY_OF_YEAR;

	public int getDailyClaimed() {
		return dailyClaimed;
	}

	public void setDailyClaimed(int daily){
		this.dailyClaimed = daily;
	}

	public void setHasPlayerCompletedBossTask(boolean hasPlayerCompletedBossTask) {
		this.hasPlayerCompletedBossTask = hasPlayerCompletedBossTask;
	}

	public int teleportIndexPanel = 0;

	public String currentDailyTask;

	public  int tier;
	public  int teleportIndex;


	public ArrayList<TeleportEnum> teleports;


	public String getCurrentDailyTask() {
		return currentDailyTask;
	}

	public void setCurrentDailyTask(String currentDailyTask) {
		this.currentDailyTask = currentDailyTask;
	}

	public int getCurrentDailyTaskAmount() {
		return currentDailyTaskAmount;
	}

	public void setCurrentDailyTaskAmount(int currentDailyTaskAmount) {
		this.currentDailyTaskAmount = currentDailyTaskAmount;
	}

	public int currentDailyTaskAmount;
    
	private int timeLeft;
	
	public int getTimeLeft() {
		return timeLeft;
	}
	
	public void setTimeLeft(int time) {
		this.timeLeft = time;
	}

    public long lastDonation;

    public void setAmountDonatedToday(int amount) {
        this.amountDonatedToday = amount;
    }
    private DonationDeals donationDeals = new DonationDeals(this);

    public DonationDeals getDonationDeals() {
        return donationDeals;
    }

    public void incrementAmountDonatedToday(int amount) {
        this.amountDonatedToday += amount;
    }

	private GodMode godMode = new GodMode(this);
	
	public GodMode getGodMode() {
		return godMode;
	}
    
private final DailyRewards dailyRewards = new DailyRewards(this);
	
	public DailyRewards getDailyRewards() {
		return dailyRewards;
	}
	
	public boolean day1Claimed = false;
	public boolean day2Claimed = false;
	public boolean day3Claimed = false;
	public boolean day4Claimed = false;
	public boolean day5Claimed = false;
	public boolean day6Claimed = false;
	public boolean day7Claimed = false;
	
	public long lastLogin;
	public long lastDailyClaim;
	
	public boolean hasFirstTimeTimerSet;
	
	public long lastVoteTime;
	
	public boolean hasVotedToday;

    public boolean claimedFirst;
    public boolean claimedSecond;
    public boolean claimedThird;
	private boolean indung;


	public int npcViewing;
	
	public long npcDropTableDelay = 0;

	public NpcSpawner spawner = new NpcSpawner(this);

	public NpcSpawner getNpcSpawner() {
		return spawner;
	}

	public int getLastgoodiebox() { 
		return lastgoodiebox;
	}
	
	public int currentBossTask;
	public boolean isInDung() {
		return indung;
	}
	public void setInDung(boolean indung) {
		this.indung = indung;
	}
	public int currentBossTaskAmount;
	
	private int lastgoodiebox;
	
	private GoodieBagSponsor goodiebagsponsor = new GoodieBagSponsor(this);
	
	public GoodieBagSponsor getGoodieBagSponsor() {
		return goodiebagsponsor;
	}
	
	private GoodieBagbfg9000 goodiebagbfg9000 = new GoodieBagbfg9000(this);
	
	public GoodieBagbfg9000 getGoodieBagbfg9000 () {
		return goodiebagbfg9000;
	}

	private GoodieBagtrivia goodiebagtrivia = new GoodieBagtrivia(this);
	
	public GoodieBagtrivia getGoodieBagtrivia () {
		return goodiebagtrivia;
	}
	public int selectedGoodieBag1 = -1;
	private GoodiebagEternal goodiebagEternal = new GoodiebagEternal(this);

	public GoodiebagEternal getGoodiebagEternal () {
		return goodiebagEternal;
	}
	public int selectedGoodieBag = -1;



	public Map<Integer, Integer> getNpcKillCount() {
		return npcKillCountMap;
	}

	public void setNpcKillCount(Map<Integer, Integer> dataMap) {
		this.npcKillCountMap = dataMap;
	}

	public void addNpcKillCount(int npcId) {
		npcKillCountMap.merge(npcId, 1, Integer::sum);
	}



	private Map<Integer, Integer> npcKillCountMap = new HashMap<>();

	public int getNpcKillCount(int npcId) { // + already exists yeah but not in playersaving/loading
		return npcKillCountMap.get(npcId) == null ? 0 : npcKillCountMap.get(npcId);
	}
	public int getCurrentBossTaskAmount() {
		return currentBossTaskAmount;
	}

	public void setCurrentBossTaskAmount(int currentBossTaskAmount) {
		this.currentBossTaskAmount = currentBossTaskAmount;
	}

	public String getName() {
        return getUsername();
    }
	
	 private Item raidsLoot;
	    private Item raidsLootSecond;
	    private Item raidsLootThird;

	    public Item getRaidsLoot() {
	        return raidsLoot;
	    }

	    public void setRaidsLoot(Item raidsLoot) {
	        this.raidsLoot = raidsLoot;
	    }

	    public Item getRaidsLootSecond() {
	        return raidsLootSecond;
	    }

	    public void setRaidsLootSecond(Item raidsLootSecond) {
	        this.raidsLootSecond = raidsLootSecond;
	    }
	    
	    public Item getRaidsLootThird() {
	        return raidsLootThird;
	    }

	    public void setRaidsLootThird(Item raidsLootThird) {
	        this.raidsLootThird = raidsLootThird;
	    }
	
	/**
	 * Killcount
	 */
	
	private int killcount;
	
	public int getKillcount() {
		return killcount;
	}
	
	public void setKillCount(int amount) {
		killcount = amount;
	}
	
	public void incrementKillCount(int amount) {
		this.killcount += amount;
	}
	
	
	

	public int getCurrentBossTask() {
		return currentBossTask;
	}

	public void setCurrentBossTask(int currentBossTask) {
		this.currentBossTask = currentBossTask;
	}


	
	public BossEventData bossevent;
	
	
	
	public BossEventData getBossevent() {
		return bossevent;
	}

	public void setBossevent(BossEventData bossevent) {
		this.bossevent = bossevent;
	}

	private int[] maxCapeColors = { 65214, 65200, 65186, 62995 };
    private PVMRanking pvmRanking = new PVMRanking(this);

    public int[] getMaxCapeColors() {
		return maxCapeColors;
	}

	public void setMaxCapeColors(int[] maxCapeColors) {
		this.maxCapeColors = maxCapeColors;
	}
	
	public BossPet bosspets;

	public BossPet getBosspets() {
		return bosspets;
	}

	public void setBosspets(BossPet bosspets) {
		this.bosspets = bosspets;
	}

	private String title = "";

    private int winstreak;


    private int transform;

    private int itemToUpgrade;

    public int getItemToUpgrade(){
        return itemToUpgrade;
    }
    public void setItemToUpgrade(int itemId){
        this.itemToUpgrade = itemId;
    }

    public int getTransform(){
        return transform;
    }
    public void setTransform(int npcId){
        this.transform = npcId;
    }

    public int getWinStreak(){
        return winstreak;
    }
    public void setWinStreak(int npcId){
        this.winstreak = npcId;
    }
    
	/**
 * Teleport Attributes
 */

public TeleportData1 currentTeleport;

public TeleportData1 getCurrentTeleport1() {
	return currentTeleport;
}

public void setCurrentTeleport1(TeleportData1 currentTeleport) {
	this.currentTeleport = currentTeleport;
}

public TeleportType1 teleportType1 = TeleportType1.MONSTERS;


public TeleportType1 getTeleportType1() {
	return teleportType1;
}

public void setTeleportType1(TeleportType1 teleportType) {
	this.teleportType1 = teleportType;
}

    private  boolean instance = false;
	private final PlayerOwnedShopManager playerOwnedShopManager = new PlayerOwnedShopManager(this);

	private boolean active;

	private boolean Invited = false;
	private int[] InvitedCoords = new int[] {0,0,0};
	private boolean shopUpdated;

	public PlayerOwnedShopManager getPlayerOwnedShopManager() {
		return playerOwnedShopManager;
	}
	public boolean getInvited() {
		return Invited;
	}
	public int[] getInvitedCoords() {
		return InvitedCoords;
	}
	public boolean getInstance() {
	    return instance;
    }
    public void setInstance(boolean instance2) {
	    this.instance = instance2;
    }

    public void setSpinning(boolean Spinning) {
        this.Spinning = Spinning;
    }
    public static boolean Spinning = false;
    private MysteryBox mysteryBox = new MysteryBox(this);

    public MysteryBox getMysteryBox() {
        return mysteryBox;
    }


    private int mbox;

    public int getmbox() {
        return mbox;
    }

    public Player setmbox(int mbox) {
        this.mbox = mbox;
        return this;
    }

    public static int[] guns = {20643,21082,5202, 13733,937,14923,5138,940,4640,939,898,20695,21077,20645,21079,894,21044,700,701,895,896,2867,21080,4786,18971};

    public static int[]  getGuns(){
        return guns;
    }

	public void setInvited(boolean i) {
	    this.Invited = i;
    }

	public void setInvitedCoords(int[] i) {
	    this.InvitedCoords = i;
    }

    public int box = 0;

    public void setBox(int box1) {
        this.box = box1;
    }
    public int getBox(){
        return box;
    }

    public Player(PlayerSession playerIO) {
        super(GameSettings.DEFAULT_POSITION.copy());
        this.session = playerIO;
    }
    /**
	 * The gambling manager
	 */
	private final GamblingManager gamblingManager = new GamblingManager();
	/**
	 * The gambling offer container
	 */
	private final GambleOfferItemContainer gambleOffer = new GambleOfferItemContainer(this);


private Map<String, Object> attributes = new HashMap<>();

	@SuppressWarnings("unchecked")
	public <T> T getAttribute(String key) {
		return (T) attributes.get(key);
	}

	private Minigame minigame = null;

	@SuppressWarnings("unchecked")
	public <T> T getAttribute(String key, T fail) {
		Object object = attributes.get(key);
		return object == null ? fail : (T) object;
	}

	public boolean hasAttribute(String key) {
		return attributes.containsKey(key);
	}

	public void removeAttribute(String key) {
		attributes.remove(key);
	}
    private int hardwareNumber;
    public int getHardwareNumber() {
        return hardwareNumber;
    }

    public Player setHardwareNumber(int hardwareNumber) {
        this.hardwareNumber = hardwareNumber;
        return this;
    }

	public void setAttribute(String key, Object value) {
		attributes.put(key, value);
	}

    @Override
    public void appendDeath() {
        if (!isDying) {
            isDying = true;
            TaskManager.submit(new PlayerDeathTask(this));
        }
    }
    private int NightRaidPoints;

   	public int getNightRaidPoints() {
   		return NightRaidPoints;
   	}

   	public void setNightRaidPoints(int NightRaidPoints) {
   		this.NightRaidPoints = NightRaidPoints;
   	}
   	public void incrementNightRaidPoints(double amount) {
		this.NightRaidPoints -= amount;
   	}

   	private boolean betaTester;

    private int bossPoints;
    private int weaponGamePoints;


	private int DeadPoints;
	
   	public int getDeadPoints() {
   		return DeadPoints;
   	}

	public int getBossPoints() {
		return bossPoints;
	}

	public void setBossPoints(int bossPoints) {
		this.bossPoints = bossPoints;
	}
	
	public void setDeadPoints(int DeadPoints) {
		this.DeadPoints = DeadPoints;
	}
	
private int npcKills;

	public int getNpcKills() {
		return npcKills;
	}
    private int npcTransformationId;

    public Character setNpcTransformationId(int npcTransformationId) {
        this.npcTransformationId = npcTransformationId;
        return this;
    }
    public int getNpcTransformationId() {
        return npcTransformationId;
    }

    private final Appearance appearance = new Appearance(this);



    public void setBetaTester(boolean beta) {
	    this.betaTester = beta;
    }
    public boolean getBetaTester(){
	    return betaTester;
    }

	public void setNpcKills(int npcKills) {
		this.npcKills = npcKills;
	}

	/*
	 * Variables for DropTable & Player Profiling
	 *@author Levi Patton
	 *@www.rune-server.org/members/auguryps
	 */
	public Player dropLogPlayer;
	public boolean dropLogOrder;
	private PlayerDropLog playerDropLog = new PlayerDropLog();
	private ProfileViewing profile = new ProfileViewing();
	/*
	 * Variables for the DropLog
	 * @author Levi Patton
	 */
	public PacketSender getPA() {
		return getPacketSender();
	}
	public PlayerDropLog getPlayerDropLog() {
		return playerDropLog;
	}

	public ProfileViewing getProfile() {
		return profile;
	}
	
	public Stopwatch getDropTableTimer() {
		return dropTimer;
	}

	public void setProfile(ProfileViewing profile) {
		this.profile = profile;
	}

	public void setPlayerDropLog(PlayerDropLog playerDropLog) {
		this.playerDropLog = playerDropLog;
	}

    @Override
    public int getConstitution() {
        return getSkillManager().getCurrentLevel(Skill.CONSTITUTION);
    }

    @Override
    public Character setConstitution(int constitution) {
        if (isDying) {
            return this;
        }
        skillManager.setCurrentLevel(Skill.CONSTITUTION, constitution);
        packetSender.sendSkill(Skill.CONSTITUTION);
        if (getConstitution() <= 0 && !isDying) {
            appendDeath();
        }
        return this;
    }

    @Override
    public void heal(int amount) {
        int level = skillManager.getMaxLevel(Skill.CONSTITUTION);
        if ((skillManager.getCurrentLevel(Skill.CONSTITUTION) + amount) >= level) {
            setConstitution(level);
        } else {
            setConstitution(skillManager.getCurrentLevel(Skill.CONSTITUTION) + amount);
        }
    }

    @Override
	public int getBaseAttack(CombatType type) {
		if (type == CombatType.RANGED)
			return skillManager.getCurrentLevel(Skill.RANGED);
		else if (type == CombatType.MAGIC)
			return skillManager.getCurrentLevel(Skill.MAGIC);
		return skillManager.getCurrentLevel(Skill.ATTACK);
	}

	@Override
	public int getBaseDefence(CombatType type) {
		if (type == CombatType.MAGIC)
			return skillManager.getCurrentLevel(Skill.MAGIC);
		return skillManager.getCurrentLevel(Skill.DEFENCE);
	}


    /**
     * The max amount of players that can be added per cycle.
     */
    private static final int MAX_NEW_PLAYERS_PER_CYCLE = 25;

    /**
     * Loops through the associated player's {@code localPlayer} list and updates them.
     * @return	The PlayerUpdating instance.
     */

    public static void update(final Player player) {
        PacketBuilder update = new PacketBuilder();
        PacketBuilder packet = new PacketBuilder(81, Packet.PacketType.SHORT);
        packet.initializeAccess(PacketBuilder.AccessType.BIT);
        updateMovement(player, packet);
        appendUpdates(player, update, player, false, true);
        packet.putBits(8, player.getLocalPlayers().size());
        for (Iterator<Player> playerIterator = player.getLocalPlayers().iterator(); playerIterator.hasNext();) {
            Player otherPlayer = playerIterator.next();
            if (World.getPlayers().get(otherPlayer.getIndex()) != null && otherPlayer.isVisible() && otherPlayer.getPosition().isWithinDistance(player.getPosition()) && !otherPlayer.isNeedsPlacement()) {
                updateOtherPlayerMovement(packet, otherPlayer);
                if (otherPlayer.getUpdateFlag().isUpdateRequired()) {
                    appendUpdates(player, update, otherPlayer, false, false);
                }
            } else {
                playerIterator.remove();
                packet.putBits(1, 1);
                packet.putBits(2, 3);
            }
        }
        int playersAdded = 0;

        for(Player otherPlayer : World.getPlayers()) {
            if (player.getLocalPlayers().size() >= 79 || playersAdded > MAX_NEW_PLAYERS_PER_CYCLE)
                break;
            if (otherPlayer == null || otherPlayer == player || !player.isVisible() || player.getLocalPlayers().contains(otherPlayer) || !otherPlayer.getPosition().isWithinDistance(player.getPosition()))
                continue;
            player.getLocalPlayers().add(otherPlayer);
            addPlayer(player, otherPlayer, packet);
            appendUpdates(player, update, otherPlayer, true, false);
            playersAdded++;
        }

        if (update.buffer().writerIndex() > 0) {
            packet.putBits(11, 2047);
            packet.initializeAccess(PacketBuilder.AccessType.BYTE);
            packet.putBytes(update.buffer());
        } else {
            packet.initializeAccess(PacketBuilder.AccessType.BYTE);
        }
        player.getSession().queueMessage(packet);
    }

    /**
     * Adds a new player to the associated player's client.
     * @param target	The player to add to the other player's client.
     * @param builder	The packet builder to write information on.
     * @return			The PlayerUpdating instance.
     */
    private static void addPlayer(Player player, Player target, PacketBuilder builder) {
        builder.putBits(11, target.getIndex());
        builder.putBits(1, 1);
        builder.putBits(1, 1);
        int yDiff = target.getPosition().getY() - player.getPosition().getY();
        int xDiff = target.getPosition().getX() - player.getPosition().getX();
        builder.putBits(5, yDiff);
        builder.putBits(5, xDiff);
    }

    /**
     * Updates the associated player's movement queue.
     * @param builder	The packet builder to write information on.
     * @return			The PlayerUpdating instance.
     */
    private static void updateMovement(Player player, PacketBuilder builder) {
        /*
         * Check if the player is teleporting.
         */
        if (player.isNeedsPlacement() || player.isChangingRegion()) {
            /*
             * They are, so an update is required.
             */
            builder.putBits(1, 1);

            /*
             * This value indicates the player teleported.
             */
            builder.putBits(2, 3);

            /*
             * This is the new player height.
             */
            builder.putBits(2, player.getPosition().getZ());

            /*
             * This indicates that the client should discard the walking queue.
             */
            builder.putBits(1, player.isResetMovementQueue() ? 1 : 0);

            /*
             * This flag indicates if an update block is appended.
             */
            builder.putBits(1, player.getUpdateFlag().isUpdateRequired() ? 1 : 0);

            /*
             * These are the positions.
             */
            builder.putBits(7,
                    player.getPosition().getLocalY(player.getLastKnownRegion()));
            builder.putBits(7,
                    player.getPosition().getLocalX(player.getLastKnownRegion()));
        } else /*
         * Otherwise, check if the player moved.
         */
            if (player.getPrimaryDirection().toInteger() == -1) {
                /*
                 * The player didn't move. Check if an update is required.
                 */
                if (player.getUpdateFlag().isUpdateRequired()) {
                    /*
                     * Signifies an update is required.
                     */
                    builder.putBits(1, 1);

                    /*
                     * But signifies that we didn't move.
                     */
                    builder.putBits(2, 0);
                } else
                    /*
                     * Signifies that nothing changed.
                     */
                    builder.putBits(1, 0);
            } else /*
             * Check if the player was running.
             */
                if (player.getSecondaryDirection().toInteger() == -1) {

                    /*
                     * The player walked, an update is required.
                     */
                    builder.putBits(1, 1);

                    /*
                     * This indicates the player only walked.
                     */
                    builder.putBits(2, 1);

                    /*
                     * This is the player's walking direction.
                     */

                    builder.putBits(3, player.getPrimaryDirection().toInteger());

                    /*
                     * This flag indicates an update block is appended.
                     */
                    builder.putBits(1, player.getUpdateFlag().isUpdateRequired() ? 1 : 0);
                } else {

                    /*
                     * The player ran, so an update is required.
                     */
                    builder.putBits(1, 1);

                    /*
                     * This indicates the player ran.
                     */
                    builder.putBits(2, 2);

                    /*
                     * This is the walking direction.
                     */
                    builder.putBits(3, player.getPrimaryDirection().toInteger());

                    /*
                     * And this is the running direction.
                     */
                    builder.putBits(3, player.getSecondaryDirection().toInteger());

                    /*
                     * And this flag indicates an update block is appended.
                     */
                    builder.putBits(1, player.getUpdateFlag().isUpdateRequired() ? 1 : 0);
                }
    }

    /**
     * Updates another player's movement queue.
     * @param builder			The packet builder to write information on.
     * @param target			The player to update movement for.
     * @return					The PlayerUpdating instance.
     */
    private static void updateOtherPlayerMovement(PacketBuilder builder, Player target) {
        /*
         * Check which type of movement took place.
         */
        if (target.getPrimaryDirection().toInteger() == -1) {
            /*
             * If no movement did, check if an update is required.
             */
            if (target.getUpdateFlag().isUpdateRequired()) {
                /*
                 * Signify that an update happened.
                 */
                builder.putBits(1, 1);

                /*
                 * Signify that there was no movement.
                 */
                builder.putBits(2, 0);
            } else
                /*
                 * Signify that nothing changed.
                 */
                builder.putBits(1, 0);
        } else if (target.getSecondaryDirection().toInteger() == -1) {
            /*
             * The player moved but didn't run. Signify that an update is
             * required.
             */
            builder.putBits(1, 1);

            /*
             * Signify we moved one tile.
             */
            builder.putBits(2, 1);

            /*
             * Write the primary sprite (i.e. walk direction).
             */
            builder.putBits(3, target.getPrimaryDirection().toInteger());

            /*
             * Write a flag indicating if a block update happened.
             */
            builder.putBits(1, target.getUpdateFlag().isUpdateRequired() ? 1 : 0);
        } else {
            /*
             * The player ran. Signify that an update happened.
             */
            builder.putBits(1, 1);

            /*
             * Signify that we moved two tiles.
             */
            builder.putBits(2, 2);

            /*
             * Write the primary sprite (i.e. walk direction).
             */
            builder.putBits(3, target.getPrimaryDirection().toInteger());

            /*
             * Write the secondary sprite (i.e. run direction).
             */
            builder.putBits(3, target.getSecondaryDirection().toInteger());

            /*
             * Write a flag indicating if a block update happened.
             */
            builder.putBits(1, target.getUpdateFlag().isUpdateRequired() ? 1 : 0);
        }
    }

    /**
     * Appends a player's update mask blocks.
     * @param builder				The packet builder to write information on.
     * @param target				The player to update masks for.
     * @param updateAppearance		Update the player's appearance without the flag being set?
     * @param noChat				Do not allow player to chat?
     * @return						The PlayerUpdating instance.
     */
    private static void appendUpdates(Player player, PacketBuilder builder, Player target, boolean updateAppearance, boolean noChat) {
        if (!target.getUpdateFlag().isUpdateRequired() && !updateAppearance)
            return;
	/*	if (player.getCachedUpdateBlock() != null && !player.equals(target) && !updateAppearance && !noChat) {
			builder.putBytes(player.getCachedUpdateBlock());
			return;
		}*/
        //	synchronized (target) {
        final UpdateFlag flag = target.getUpdateFlag();
        int mask = 0;
        if (flag.flagged(Flag.GRAPHIC) && target.getGraphic() != null) {
            mask |= 0x100;
        }
        if (flag.flagged(Flag.ANIMATION) && target.getAnimation() != null) {
            mask |= 0x8;
        }
        if (flag.flagged(Flag.FORCED_CHAT) && target.getForcedChat().length() > 0) {
            mask |= 0x4;
        }
        if (flag.flagged(Flag.CHAT) && !noChat && !player.getRelations().getIgnoreList().contains(target.getLongUsername())) {
            mask |= 0x80;
        }
        if (flag.flagged(Flag.ENTITY_INTERACTION)) {
            mask |= 0x1;
        }
        if (flag.flagged(Flag.APPEARANCE) || updateAppearance) {
            mask |= 0x10;
        }
        if (flag.flagged(Flag.FACE_POSITION)) {
            mask |= 0x2;
        }
        if (flag.flagged(Flag.SINGLE_HIT)) {
            mask |= 0x20;
        }
        if (flag.flagged(Flag.DOUBLE_HIT)) {
            mask |= 0x200;
        }
        if (flag.flagged(Flag.FORCED_MOVEMENT) && target.getForceMovement() != null) {
            mask |= 0x400;
        }
        if (mask >= 0x100) {
            mask |= 0x40;
            builder.put((mask & 0xFF));
            builder.put((mask >> 8));
        } else {
            builder.put(mask);
        }
        if (flag.flagged(Flag.FORCED_MOVEMENT) && target.getForceMovement() != null) {
            updateForcedMovement(player, builder, target);
        }
        if (flag.flagged(Flag.GRAPHIC) && target.getGraphic() != null) {
            updateGraphics(builder, target);
        }
        if (flag.flagged(Flag.ANIMATION) && target.getAnimation() != null) {
            updateAnimation(builder, target);
        }
        if (flag.flagged(Flag.FORCED_CHAT) && target.getForcedChat().length() > 0) {
            updateForcedChat(builder, target);
        }
        if (flag.flagged(Flag.CHAT) && !noChat && !player.getRelations().getIgnoreList().contains(target.getLongUsername())) {
            updateChat(builder, target);
        }
        if (flag.flagged(Flag.ENTITY_INTERACTION)) {
            updateEntityInteraction(builder, target);
        }
        if (flag.flagged(Flag.APPEARANCE) || updateAppearance) {
            Player.updateAppearance(player, builder, target);
        }
        if (flag.flagged(Flag.FACE_POSITION)) {
            updateFacingPosition(builder, target);
        }
        if (flag.flagged(Flag.SINGLE_HIT)) {
            updateSingleHit(builder, target);
        }
        if (flag.flagged(Flag.DOUBLE_HIT)) {
            updateDoubleHit(builder, target);
        }
			/*if (!player.equals(target) && !updateAppearance && !noChat) {
				player.setCachedUpdateBlock(cachedBuffer.buffer());
			}*/
        //	}
    }

    /**
     * This update block is used to update player chat.
     * @param builder	The packet builder to write information on.
     * @param target	The player to update chat for.
     * @return			The PlayerUpdating instance.
     */
    private static void updateChat(PacketBuilder builder, Player target) {
        ChatMessage.Message message = target.getChatMessages().get();
        byte[] bytes = message.getText();
        builder.putShort(((message.getColour() & 0xff) << 8) | (message.getEffects() & 0xff), ByteOrder.LITTLE);
        builder.put(target.getRights().ordinal());
        builder.put(target.getGameMode().ordinal());
        builder.put(bytes.length, ValueType.C);
        builder.putBytesReverse(bytes);
    }

    /**
     * This update block is used to update forced player chat.
     * @param builder	The packet builder to write information on.
     * @param target	The player to update forced chat for.
     * @return			The PlayerUpdating instance.
     */
    private static void updateForcedChat(PacketBuilder builder, Player target) {
        builder.putString(target.getForcedChat());
    }

    /**
     * This update block is used to update forced player movement.
     * @param builder	The packet builder to write information on.
     * @param target	The player to update forced movement for.
     * @return			The PlayerUpdating instance.
     */
    private static void updateForcedMovement(Player player, PacketBuilder builder, Player target) {
        Position position = target.getPosition();
        Position myPosition = player.getLastKnownRegion();
        builder.put((position.getLocalX(myPosition) + target.getForceMovement()[MovementQueue.FIRST_MOVEMENT_X]), ValueType.S);
        builder.put((position.getLocalY(myPosition) + target.getForceMovement()[MovementQueue.FIRST_MOVEMENT_Y]), ValueType.S);
        builder.put((position.getLocalX(myPosition) + target.getForceMovement()[MovementQueue.SECOND_MOVEMENT_X]), ValueType.S);
        builder.put((position.getLocalY(myPosition) + target.getForceMovement()[MovementQueue.SECOND_MOVEMENT_Y]), ValueType.S);
        builder.putShort(target.getForceMovement()[MovementQueue.MOVEMENT_SPEED], ValueType.A, ByteOrder.LITTLE);
        builder.putShort(target.getForceMovement()[MovementQueue.MOVEMENT_REVERSE_SPEED], ValueType.A);
        builder.put(target.getForceMovement()[MovementQueue.MOVEMENT_DIRECTION], ValueType.S);
    }

    /**
     * This update block is used to update a player's animation.
     * @param builder	The packet builder to write information on.
     * @param target	The player to update animations for.
     * @return			The PlayerUpdating instance.
     */
    private static void updateAnimation(PacketBuilder builder, Player target) {
        builder.putShort(target.getAnimation().getId(), ByteOrder.LITTLE);
        builder.put(target.getAnimation().getDelay(), ValueType.C);
    }

    /**
     * This update block is used to update a player's graphics.
     * @param builder	The packet builder to write information on.
     * @param target	The player to update graphics for.
     * @return			The PlayerUpdating instance.
     */
    private static void updateGraphics(PacketBuilder builder, Player target) {
        builder.putShort(target.getGraphic().getId(), ByteOrder.LITTLE);
        builder.putInt(((target.getGraphic().getHeight().ordinal() * 50) << 16) + (target.getGraphic().getDelay() & 0xffff));
    }

    /**
     * This update block is used to update a player's single hit.
     * @param builder	The packet builder used to write information on.
     * @param target	The player to update the single hit for.
     * @return			The PlayerUpdating instance.
     */
    private static void updateSingleHit(PacketBuilder builder, Player target) {
        builder.putInt(target.getPrimaryHit().getDamage());
        builder.put(target.getPrimaryHit().getHitmask().ordinal());
        builder.put(target.getPrimaryHit().getCombatIcon().ordinal() - 1);
        builder.putInt(target.getPrimaryHit().getAbsorb());
        builder.putInt(target.getConstitution());
        builder.putInt(target.getSkillManager().getMaxLevel(Skill.CONSTITUTION));
    }

    /**
     * This update block is used to update a player's double hit.
     * @param builder	The packet builder used to write information on.
     * @param target	The player to update the double hit for.
     * @return			The PlayerUpdating instance.
     */
    private static void updateDoubleHit(PacketBuilder builder, Player target) {
        builder.putInt(target.getSecondaryHit().getDamage());
        builder.put(target.getSecondaryHit().getHitmask().ordinal());
        builder.put(target.getSecondaryHit().getCombatIcon().ordinal() - 1);
        builder.putInt(target.getPrimaryHit().getAbsorb());
        builder.putInt(target.getConstitution());
        builder.putInt(target.getSkillManager().getMaxLevel(Skill.CONSTITUTION));
    }

    /**
     * This update block is used to update a player's face position.
     * @param builder	The packet builder to write information on.
     * @param target	The player to update face position for.
     * @return			The PlayerUpdating instance.
     */
    private static void updateFacingPosition(PacketBuilder builder, Player target) {
        final Position position = target.getPositionToFace();
        int x = position == null ? 0 : position.getX();
        int y = position == null ? 0 : position.getY();
        builder.putShort(x * 2 + 1, ValueType.A, ByteOrder.LITTLE);
        builder.putShort(y * 2 + 1, ByteOrder.LITTLE);
    }

    /**
     * This update block is used to update a player's entity interaction.
     * @param builder	The packet builder to write information on.
     * @param target	The player to update entity interaction for.
     * @return			The PlayerUpdating instance.
     */
    private static void updateEntityInteraction(PacketBuilder builder, Player target) {
        Entity entity = target.getInteractingEntity();
        if (entity != null) {
            int index = entity.getIndex();
            if (entity instanceof Player)
                index += + 32768;
            builder.putShort(index, ByteOrder.LITTLE);
        } else {
            builder.putShort(-1, ByteOrder.LITTLE);
        }
    }

	public Player removeInterface(Player player) {
		if (player.isBanking()) {
			packetSender.sendClientRightClickRemoval();
			player.setBanking(false);
		}
		if (player.isShopping()) {
			packetSender.sendClientRightClickRemoval().sendItemsOnInterface(Shop.INTERFACE_ID, new Item[] { new Item(-1) });
			player.setShopping(false);
		}
		if (player.getPriceChecker().isOpen()) {
			player.getPriceChecker().exit();
		}
		if (player.isResting()) {
			player.setResting(false);
			player.performAnimation(new Animation(11788));
		}
		player.setDialogueActionId(-1);
		player.setInterfaceId(-1);
		player.getAppearance().setCanChangeAppearance(false);
		player.getSession().queueMessage(new PacketBuilder(219));
		return this;
	}
    /**
     * Resets the associated player's flags that will need to be removed
     * upon completion of a single update.
     * @return	The PlayerUpdating instance.
     */
    public static void resetFlags(Player player) {
        player.getUpdateFlag().reset();
        player.setRegionChange(false);
        player.setTeleporting(false).setForcedChat("");
        player.setResetMovementQueue(false);
        player.setNeedsPlacement(false);
        player.setPrimaryDirection(Direction.NONE);
        player.setSecondaryDirection(Direction.NONE);
    }




    /**
     * This update block is used to update a player's appearance, this includes
     * their equipment, clothing, combat level, gender, head icons, user name and animations.
     * @param target	The player to update appearance for.
     * @return			The PlayerUpdating instance.
     */
    public static void updateAppearance(Player player, PacketBuilder out, Player target) {
        for(Player p : World.getPlayers()) {

        }
        Appearance appearance = target.getAppearance();
        Equipment equipment = target.getEquipment();
        PacketBuilder properties = new PacketBuilder();
        properties.put(appearance.getGender().ordinal());
        properties.put(appearance.getHeadHint());
        properties.put(target.getLocation() == Locations.Location.WILDERNESS ? appearance.getBountyHunterSkull() : -1);
        properties.putShort(target.getSkullIcon());

        if (target.getNpcTransformationId() > 1) {
            properties.putShort(-1);
            properties.putShort(target.getNpcTransformationId());
        }else {
            int[] equip = new int[equipment.capacity()];
            for (int i = 0; i < equipment.capacity(); i++) {
                equip[i] = equipment.getItems()[i].getId();
            }
            if (equip[Equipment.HEAD_SLOT] > -1) {
                properties.putShort(0x200 + equip[Equipment.HEAD_SLOT]);
            } else {
                properties.put(0);
            }
            if (equip[Equipment.CAPE_SLOT] > -1) {
                properties.putShort(0x200 + equip[Equipment.CAPE_SLOT]);
                if(equip[Equipment.CAPE_SLOT] == 14019) {
					/*int[] modelColors = new int[] { 65214, 65200, 65186, 62995 };
					if(target.getUsername().equalsIgnoreCase("apache ah64")) {
						modelColors[0] = 926;//cape
						modelColors[1] = 350770;//cape
						modelColors[2] = 928;//outline
						modelColors[3] = 130770;//cape
					} else if(target.getUsername().equalsIgnoreCase("apache ah66")) {
						modelColors[0] = 926;//cape
						modelColors[1] = 302770;//cape
						modelColors[2] = 928;//outline
						modelColors[3] = 302770;//cape
					}*/
                    int[] modelColors = target.getMaxCapeColors();
                    //System.out.println("Updating: "+Arrays.toString(modelColors));
                    if(modelColors != null) {
                        properties.put(modelColors.length);
                        for(int i = 0; i < modelColors.length; i++) {
                            properties.putInt(modelColors[i]);
                        }
                    } else {
                        properties.put(0);
                    }
                } else {
                    properties.put(0);
                }
            } else {
                properties.put(0);
            }
            if (equip[Equipment.AMULET_SLOT] > -1) {
                properties.putShort(0x200 + equip[Equipment.AMULET_SLOT]);
            } else {
                properties.put(0);
            }
            if (equip[Equipment.WEAPON_SLOT] > -1) {
                properties.putShort(0x200 + equip[Equipment.WEAPON_SLOT]);
            } else {
                properties.put(0);
            }
            if (equip[Equipment.BODY_SLOT] > -1) {
                properties.putShort(0x200 + equip[Equipment.BODY_SLOT]);
            } else {
                properties.putShort(0x100 + appearance.getLook()[Appearance.CHEST]);
            }
            if (equip[Equipment.SHIELD_SLOT] > -1) {
                properties.putShort(0x200 + equip[Equipment.SHIELD_SLOT]);
            } else {
                properties.put(0);
            }

            if (ItemDefinition.forId(equip[Equipment.BODY_SLOT]).isFullBody()) {
                properties.put(0);
            } else {
                properties.putShort(0x100 + appearance.getLook()[Appearance.ARMS]);
            }

            if (equip[Equipment.LEG_SLOT] > -1) {
                properties.putShort(0x200 + equip[Equipment.LEG_SLOT]);
            } else {
                properties.putShort(0x100 + appearance.getLook()[Appearance.LEGS]);
            }

            if (ItemDefinition.forId(equip[Equipment.HEAD_SLOT]).isFullHelm()) {
                properties.put(0);
            } else {
                properties.putShort(0x100 + appearance.getLook()[Appearance.HEAD]);
            }
            if (equip[Equipment.HANDS_SLOT] > -1) {
                properties.putShort(0x200 + equip[Equipment.HANDS_SLOT]);
            } else {
                properties.putShort(0x100 + appearance.getLook()[Appearance.HANDS]);
            }
            if (equip[Equipment.FEET_SLOT] > -1) {
                properties.putShort(0x200 + equip[Equipment.FEET_SLOT]);
            } else {
                properties.putShort(0x100 + appearance.getLook()[Appearance.FEET]);
            }
            if (appearance.getLook()[Appearance.BEARD] <= 0 || appearance.getGender().equals(Gender.FEMALE)) {
                properties.put(0);
            } else if (ItemDefinition.forId(equip[Equipment.HEAD_SLOT]).isFullHelm()) {
                properties.put(0);
                
            } else {
                properties.putShort(0x100 + appearance.getLook()[Appearance.BEARD]);
            }
        }
        properties.put(appearance.getLook()[Appearance.HAIR_COLOUR]);
        properties.put(appearance.getLook()[Appearance.TORSO_COLOUR]);
        properties.put(appearance.getLook()[Appearance.LEG_COLOUR]);
        properties.put(appearance.getLook()[Appearance.FEET_COLOUR]);
        properties.put(appearance.getLook()[Appearance.SKIN_COLOUR]);

        int skillAnim = target.getSkillAnimation();
        if(skillAnim > 0) {
            for(int i = 0; i < 7; i++)
                properties.putShort(skillAnim);
        } else {
            properties.putShort(target.getCharacterAnimations().getStandingAnimation());
            properties.putShort(0x337);
            properties.putShort(target.getCharacterAnimations().getWalkingAnimation());
            properties.putShort(0x334);
            properties.putShort(0x335);
            properties.putShort(0x336);
            properties.putShort(target.getCharacterAnimations().getRunningAnimation());
        }

        properties.putLong(target.getLongUsername());
        properties.put(target.getSkillManager().getCombatLevel());
        properties.putShort(target.getRights().ordinal());
        properties.putString(target.getTitle());
        //System.out.println("test - "+target.getTitle());
        //properties.putShort(target.getLoyaltyTitle().ordinal());

        out.put(properties.buffer().writerIndex(), ValueType.C);
        out.putBytes(properties.buffer());
    }


    @Override
    public int getAttackSpeed() {
        int speed = weapon.getSpeed();
        String weapon = equipment.get(Equipment.WEAPON_SLOT).getDefinition().getName();

		int weaponId = equipment.get(Equipment.WEAPON_SLOT).getId();

		switch(weaponId){
			case 898:
				return 1;
			case 11539:
			case 939:
			case 938:
			case 21002:
			case 21030:
			case 21004:
			case 894:
			case 895:
			case 700:
			case 21044:
			case 2867:
			case 21013:
				return 4;

            case 20259:
            case 21060:
            case 20695:
            case 20603:
            case 20645:
            case 20510:
			case 5157:
			case 3316:
			case 3321:
			case 4056:
			case 3084:
			case 3294:
			case 3825:

				case 5138:
				case 3298:
				case 3666:
			case 4060:
			case 4061:
			case 937:
			case 3814:
			case 4777:
			case 903:
			case 5146:
			case 5083:
			case 20643:
			case 20051:
			case 3246:
                return 3;

			case 13733:
			case 13265:
			case 5202:
			case 8898:
			case 14934:
			case 4640:
			case 4645:
			case 3067:
			case 5168:
			case 3088:
			case 21003:
				return 2;


			default:
				return 5;
		}
//
//
//		if (getCurrentlyCasting() != null) {
//            if (getCurrentlyCasting() == CombatSpells.BLOOD_BLITZ.getSpell() || getCurrentlyCasting() == CombatSpells.SHADOW_BLITZ.getSpell() || getCurrentlyCasting() == CombatSpells.SMOKE_BLITZ.getSpell() || getCurrentlyCasting() == CombatSpells.ICE_BLITZ.getSpell()) {
//                return 5;
//            } else if(getCurrentlyCasting() == CombatSpells.MADARA.getSpell()) {
//                return 1;
//            } else if(getCurrentlyCasting() == CombatSpells.MADARAX.getSpell()) {
//                return 1;
//            } else if(getCurrentlyCasting() == CombatSpells.INFIN1.getSpell()) {
//                return 1;
//            } else if(getCurrentlyCasting() == CombatSpells.INFIN2.getSpell()) {
//                return 1;
//            } else if(getCurrentlyCasting() == CombatSpells.INVIC.getSpell()) {
//                return 1;
//            } else if(getCurrentlyCasting() == CombatSpells.DEXION.getSpell()) {
//                return 1;
//            } else if(getCurrentlyCasting() == CombatSpells.WINTER4EVER.getSpell()) {
//                return 1;
//            } else if(getCurrentlyCasting() == CombatSpells.VOLDEMORT.getSpell()) {
//                return 1;
//            } else if(getCurrentlyCasting() == CombatSpells.INSANE_POWER.getSpell()) {
//                return 1;
//            } else if(getCurrentlyCasting() == CombatSpells.DEATH.getSpell()) {
//                return 1;
//            } else if(getCurrentlyCasting() == CombatSpells.MYSTERIO.getSpell()) {
//                return 1;
//            } else {
//                return 6;
//            }
//        }
//
//         weaponId = equipment.get(Equipment.WEAPON_SLOT).getId();
//
//
//
//        if (weaponId == 1419) {
//            speed -= 2;
//        }
//
//        int weaponId1 = equipment.get(Equipment.WEAPON_SLOT).getId();
//        if (weaponId1 == 903) {
//            speed = 1;
//        }
//
//        int weaponId4 = equipment.get(Equipment.WEAPON_SLOT).getId();
//        if (weaponId4 == 5202) {
//            speed = 1;
//        }
//
//        int weaponId2 = equipment.get(Equipment.WEAPON_SLOT).getId();
//        if (weaponId2 == 4056) {
//            speed = 1;
//        }
//
//        int weaponId3 = equipment.get(Equipment.WEAPON_SLOT).getId();
//        if (weaponId3 == 3084) {
//            speed = 1;
//        }
//
//
//
//       if (weaponId == 894 || weaponId ==21044 || weaponId ==700 ||weaponId ==701 ||weaponId ==895 ||weaponId == 2867) {
//            speed = 3;
//        }
//        if (weaponId == 21080 || weaponId == 898 || weaponId == 20605) {
//            speed = 1;
//        }
//        if (weaponId == 17847 ) {
//            speed = 2;
//        }
//        if(weaponId == 20504) {
//            speed = 1;
//        }
//        if (weaponId == 21079) {
//            speed = 2;
//        } if (weaponId == 20259) {
//            speed = 2;
//        } if (weaponId == 3298) {
//            speed = 2;
//        } if (weaponId == 3088) {
//            speed = 2;
//        } if (weaponId == 4802) {
//            speed = 2;
//        } if (weaponId == 3070) {
//            speed = 2;
//        } if (weaponId == 5116) {
//            speed = 1;
//        } if (weaponId == 3666) {
//            speed = 1;
//        } if (weaponId == 4761) {
//            speed = 1;
//        } if (weaponId == 937) {
//            speed = 1;
//        }  if (weaponId == 18971) {
//            speed = 1;
//        } if (weaponId == 5138) {
//            speed = 1;
//        } if (weaponId == 940) {
//            speed = 1;
//        } if (weaponId == 4640) {
//            speed = 1;
//        } if (weaponId == 4786) {
//            speed = 1;
//        } if (weaponId == 3076) {
//            speed = 1;
//        } if (weaponId == 19001) {
//            speed = 1;
//        }if (weaponId == 20051) {
//            speed = 1;
//        }if (weaponId == 4645) {
//            speed = 1;
//        } if (weaponId == 5083) {
//            speed = 2;
//        } if (weaponId == 5146) {
//            speed = 1;
//        } if (weaponId == 14922) {
//            speed = 1;
//        } if (weaponId == 14923) {
//            speed = 1;
//        } if (weaponId == 14924) {
//            speed = 1;
//        } if (weaponId == 5168) {
//            speed = 1;
//        } if (weaponId == 3321) {
//            speed = 1;
//        } if (weaponId == 4777) {
//            speed = 2;
//        } if (weaponId == 4772) {
//            speed = 1;
//        } if (weaponId == 3814) {
//			speed = 1;
//		} if (weaponId == 21009) {
//        	speed = 2;
//        } if (weaponId == 2549) {
//            speed = 1;
//        } if (weaponId == 20603) {
//            speed = 2;
//        } if (weaponId == 3816) {
//            speed = 1;
//        } if (weaponId == 20695) {
//            speed = 2;
//        } if (weaponId == 21082) {
//            speed = 2;
//        } if (weaponId == 13733) {
//            speed = 1;
//        } if (weaponId == 20645) {
//            speed = 2;
//        } if (weaponId == 3316) {
//            speed = 1;
//        } if (weaponId == 14934) {
//            speed = 1;
//        } if (weaponId == 20510) {
//            speed = 1;
//        } if (weaponId == 3825) {
//            speed = 1;
//        }
//        if (weaponId == 20643 || weaponId == 11584 || weaponId == 21077) {
//            speed = 1;
//        }
//
//        if (weaponId == 21002 || weaponId == 20695 || weaponId == 21060 || weaponId == 21063 || weaponId == 21013 || weaponId == 20079|| weaponId == 20102 || weaponId == 20607|| weaponId == 21003 || weaponId == 21030 || weaponId == 21031 || weaponId == 21032 || weaponId == 21033 || weaponId == 21004)  {
//            speed = 2;
//        }
//        if (fightType == FightType.CROSSBOW_RAPID || fightType == FightType.LONGBOW_RAPID || weaponId == 6522 && fightType == FightType.KNIFE_RAPID || weapon.contains("rapier")) {
//            if (weaponId != 11235) {
//                speed--;
//            }
//
//
//        } else if (weaponId != 6522 && weaponId != 15241 && (fightType == FightType.SHORTBOW_RAPID || fightType == FightType.DART_RAPID || fightType == FightType.KNIFE_RAPID || fightType == FightType.THROWNAXE_RAPID || fightType == FightType.JAVELIN_RAPID) || weaponId == 11730) {
//            speed -= 2;
//        }
//        if(weaponId ==896) {
//            return 3;
//        }
        //return speed;
        //	return DesolaceFormulas.getAttackDelay(this);
    }

    public boolean sendElementalMessage = true;
    public int clue1Amount;
    public int clue2Amount;
    public int clue3Amount;
    public int clueLevel;
    public double doubleDropRate = 0;
    public double droprate = 0;
    public Item[] puzzleStoredItems;
    public int sextantGlobalPiece;
    public double sextantBarDegree;
    public int rotationFactor;
    public int sextantLandScapeCoords;
    public int sextantSunCoords;

   // private Channel channel;

  //  public Player write(Packet packet) {
    //    if (channel.isConnected()) {
    //        channel.write(packet);
    //    }
    //    return this;
  //  }

   /// public Channel getChannel() {
   //     return channel;
   // }

    private Bank bank = new Bank(this);

    public Bank getBank() {
        return bank;
    }

    public int summoned = -1;

    public int getSummoned(){
        return summoned;
    }
    public void setSummoned(int sum){
        this.summoned = sum;
    }
    public boolean isSendElementalMessage() {
    return sendElementalMessage;
    }
    public void setSendElementalMessage(boolean elemental) {
        this.sendElementalMessage = elemental;
    }

    @Override
    public boolean isPlayer() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Player)) {
            return false;
        }

        Player p = (Player) o;
        return p.getIndex() == getIndex() || p.getUsername().equals(username);
    }



    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public void poisonVictim(Character victim, CombatType type) {
        if (type == CombatType.MELEE || weapon == WeaponInterface.DART || weapon == WeaponInterface.KNIFE || weapon == WeaponInterface.THROWNAXE || weapon == WeaponInterface.JAVELIN) {
            CombatFactory.poisonEntity(victim, CombatPoisonData.getPoisonType(equipment.get(Equipment.WEAPON_SLOT)));
        } else if (type == CombatType.RANGED) {
            CombatFactory.poisonEntity(victim, CombatPoisonData.getPoisonType(equipment.get(Equipment.AMMUNITION_SLOT)));
        }
    }

	@Override
	public CombatStrategy determineStrategy() {
		if (specialActivated && castSpell == null) {

			if (combatSpecial.getCombatType() == CombatType.MELEE) {
				return CombatStrategies.getDefaultMeleeStrategy();
			} else if (combatSpecial.getCombatType() == CombatType.RANGED) {
				setRangedWeaponData(RangedWeaponData.getData(this));
				return CombatStrategies.getDefaultRangedStrategy();
			} else if (combatSpecial.getCombatType() == CombatType.MAGIC) {
				return CombatStrategies.getDefaultMagicStrategy();
			}
		}

        if (CustomMagicStaff.checkCustomStaff(this)) {
            CustomMagicStaff.handleCustomStaff(this);
            this.setCastSpell(CustomMagicStaff.CustomStaff.getSpellForWeapon(this.getEquipment().getItems()[Equipment.WEAPON_SLOT].getId()));
            return CombatStrategies.getDefaultMagicStrategy();
        }

        if (castSpell != null || autocastSpell != null) {
            return CombatStrategies.getDefaultMagicStrategy();
        }

        RangedWeaponData data = RangedWeaponData.getData(this);
        if (data != null) {
            setRangedWeaponData(data);
            return CombatStrategies.getDefaultRangedStrategy();
        }

        return CombatStrategies.getDefaultMeleeStrategy();
    }

    public void process() {
        process.sequence();
    }

    public void dispose() {
        save();
        packetSender.sendLogout();
    }

    public void save() {
        if (session.getState() != SessionState.LOGGED_IN && session.getState() != SessionState.LOGGING_OUT) {
            return;
        }
        PlayerSaving.save(this);
    }

    public boolean logout() {
    	boolean debugMessage = false;
		long[] playerXP = new long[Skill.values().length];
		for (int i = 0; i <  Skill.values().length; i++) {
			playerXP[i] = this.getSkillManager().getExperience(Skill.forId(i));
		}
        if (getCombatBuilder().isBeingAttacked()) {
            getPacketSender().sendMessage("You must wait a few seconds after being out of combat before doing this.");
            return false;
        }
        if (getConstitution() <= 0 || isDying || settingUpCannon || crossingObstacle) {
            getPacketSender().sendMessage("You cannot log out at the moment.");
            return false;
        }
    	getPacketSender().sendRichPressenceDetails("In Loading Screen");
    	getPacketSender().sendRichPressenceState("Logged Out");
        return true;
    }

    public void restart() {
        setFreezeDelay(0);
        setOverloadPotionTimer(0);
        setPrayerRenewalPotionTimer(0);
        setSpecialPercentage(100);
        setSpecialActivated(false);
        CombatSpecial.updateBar(this);
        setHasVengeance(false);
        setSkullTimer(0);
        setSkullIcon(0);
        setTeleblockTimer(0);
        setPoisonDamage(0);
        setStaffOfLightEffect(0);
        performAnimation(new Animation(65535));
        WeaponInterfaces.assign(this, getEquipment().get(Equipment.WEAPON_SLOT));
        WeaponAnimations.assign(this, getEquipment().get(Equipment.WEAPON_SLOT));
        PrayerHandler.deactivateAll(this);
        CurseHandler.deactivateAll(this);
        getEquipment().refreshItems();
        getInventory().refreshItems();
		if(!this.getGodMode().isActive()) {
		for (Skill skill : Skill.values())
			getSkillManager().setCurrentLevel(skill, getSkillManager().getMaxLevel(skill));
		} else {
			this.getSkillManager().setCurrentLevel(Skill.CONSTITUTION, this.getSkillManager().getMaxLevel(Skill.CONSTITUTION));//i know but this makes them have 15k hp after dead is that 
			//this.getSkillManager().setCurrentLevel(CONSTITUTION, getSkillManager().getMaxLevel(CONSTITUTION));
		}
		setRunEnergy(100);
		setDying(false);
		getMovementQueue().setLockMovement(false).reset();
		
		getUpdateFlag().flag(Flag.APPEARANCE);
	}
    /**
     * @author Stan van der Bend
     *
     * This method will ensure this {@link Player} will receive the item(s) defined by the params.
     *  1): first try inventory
     *  2): then try bank
     *  3): then try refund box
     *  4): otherwise send error message
     *
     * @param itemId
     * @param itemAmount
     */
    public void giveItem(int itemId, int itemAmount) {

        final ItemDefinition definition = ItemDefinition.forId(itemId);

        if(definition == null){
            sendMessage("@red@[Error]: Could not find definition ("+itemId+"-"+itemAmount+")");
            sendMessage("@red@Please take a screenshot and post it on the forums.");
            return;
        }

        final int occupiedSlots = definition.isNoted() || definition.isStackable() ? 1 : itemAmount;

        if(inventory.getFreeSlots() >= occupiedSlots) {
        	inventory.add(itemId, itemAmount).refreshItems();
        } else if(bank.getFreeSlots() >= occupiedSlots) {
        	boolean added = false;
        	for(Bank bank: getBanks()) {
        		if(!added && !Bank.isEmpty(bank)) {
        			bank.add(itemId, itemAmount).refreshItems();
        			added = true;
        		}
        	}
        } else {
            sendMessage("@red@[Error]: Could not give ("+itemId+"-"+itemAmount+")");
            sendMessage("@red@Please take a screenshot and post it on the forums.");
            World.sendStaffMessage("@red@[Error]: Could not give ("+itemId+"-"+itemAmount+") to "+username);
        }
    }
    public boolean busy() {
        return interfaceId > 0 || isBanking || shopping || trading.inTrade() || dueling.inDuelScreen || isResting;
    }

    public int secondsOver = 0;

    /*
     * Fields
     */
    /**
     * * STRINGS **
     */
    private String username;
    private String password;
    private String serial_number;
    private String emailAddress;
    private String hostAddress;
    private String clanChatName;

	private HouseLocation houseLocation;

	private HouseTheme houseTheme;

    /**
     * * LONGS *
     */
    private Long longUsername;
    private long moneyInPouch;
    private long totalPlayTime;
    //Timers (Stopwatches)
    private final Stopwatch sqlTimer = new Stopwatch();
    private final Stopwatch foodTimer = new Stopwatch();
    private final Stopwatch potionTimer = new Stopwatch();
    private final Stopwatch lastRunRecovery = new Stopwatch();
    private final Stopwatch clickDelay = new Stopwatch();
    private final Stopwatch combatDelay = new Stopwatch();
    private final Stopwatch lastItemPickup = new Stopwatch();
    private final Stopwatch lastYell = new Stopwatch();
    private final Stopwatch lastZulrah = new Stopwatch();
    private final Stopwatch lastSql = new Stopwatch();

    private final Stopwatch lastVengeance = new Stopwatch();
    private final Stopwatch emoteDelay = new Stopwatch();
    private final Stopwatch specialRestoreTimer = new Stopwatch();
    private final Stopwatch lastSummon = new Stopwatch();
    private final Stopwatch recordedLogin = new Stopwatch();
	private final Stopwatch dropTimer = new Stopwatch();
    private final Stopwatch creationDate = new Stopwatch();
    private final Stopwatch tolerance = new Stopwatch();
    private final Stopwatch lougoutTimer = new Stopwatch();

    /**
     * * INSTANCES **
     */
    private final CopyOnWriteArrayList<KillsEntry> killsTracker = new CopyOnWriteArrayList<KillsEntry>();
    private final CopyOnWriteArrayList<DropLogEntry> dropLog = new CopyOnWriteArrayList<DropLogEntry>();
    private ArrayList<HouseFurniture> houseFurniture = new ArrayList<HouseFurniture>();
    private ArrayList<Portal> housePortals = new ArrayList<>();
    private final List<Player> localPlayers = new LinkedList<Player>();
    private final List<NPC> localNpcs = new LinkedList<NPC>();
    @Getter @Setter
    public PlayerSession session;

    private final PlayerProcess process = new PlayerProcess(this);
    private final PlayerKillingAttributes playerKillingAttributes = new PlayerKillingAttributes(this);
    private final MinigameAttributes minigameAttributes = new MinigameAttributes();
    private final BankPinAttributes bankPinAttributes = new BankPinAttributes();
    private final BankSearchAttributes bankSearchAttributes = new BankSearchAttributes();
    private final AchievementAttributes achievementAttributes = new AchievementAttributes();
    private CharacterAnimations characterAnimations = new CharacterAnimations();
    private final BonusManager bonusManager = new BonusManager();
    private final PointsHandler pointsHandler = new PointsHandler(this);


    private final PacketSender packetSender = new PacketSender(this);
    private final FrameUpdater frameUpdater = new FrameUpdater();
    private PlayerRights rights = PlayerRights.PLAYER;
    private SkillManager skillManager = new SkillManager(this);
    private PlayerRelations relations = new PlayerRelations(this);
    private ChatMessage chatMessages = new ChatMessage();
    private Inventory inventory = new Inventory(this);
    private Equipment equipment = new Equipment(this);
    private PriceChecker priceChecker = new PriceChecker(this);
    private Trading trading = new Trading(this);
    private Dueling dueling = new Dueling(this);
    private Slayer slayer = new Slayer(this);

    private Farming farming = new Farming(this);
    private Summoning summoning = new Summoning(this);
    private Bank[] bankTabs = new Bank[9];
    private Room[][][] houseRooms = new Room[5][13][13];
    private PlayerInteractingOption playerInteractingOption = PlayerInteractingOption.NONE;
    private GameMode gameMode = GameMode.NORMAL;
    private CombatType lastCombatType = CombatType.MELEE;
    private FightType fightType = FightType.UNARMED_PUNCH;
    private Prayerbook prayerbook = Prayerbook.NORMAL;
    private MagicSpellbook spellbook = MagicSpellbook.NORMAL;
    private LoyaltyTitles loyaltyTitle = LoyaltyTitles.NONE;

    private ClanChat currentClanChat;
    private Input inputHandling;
    private WalkToTask walkToTask;
    private Shop shop;
    private GameObject interactingObject;
    private Item interactingItem;
    private Dialogue dialogue;
    private DwarfCannon cannon;
    private CombatSpell autocastSpell, castSpell, previousCastSpell;
    private RangedWeaponData rangedWeaponData;
    private CombatSpecial combatSpecial;
    private WeaponInterface weapon;
    private Item untradeableDropItem;
    private Object[] usableObject;
    private GrandExchangeSlot[] grandExchangeSlots = new GrandExchangeSlot[6];
    private Task currentTask;
    private Position resetPosition;
    private Pouch selectedPouch;
    private BlowpipeLoading blowpipeLoading = new BlowpipeLoading(this);
    private DragonRageLoading dragonrageLoading = new DragonRageLoading(this);
    private CorruptBandagesLoading CorruptBandagesLoading = new CorruptBandagesLoading(this);
    private MinigunLoading MinigunLoading = new MinigunLoading(this);

    private int timer;

    public int getTimer(){
        return timer;
    }

    public void setTimer(int time){
        this.timer = time;
    }
    /**
     * * INTS **
     */
    public int destination = 0;
	public int lastClickedTab = 0;

    private int[] brawlerCharges = new int[9];
    private int[] forceMovement = new int[7];
    private int[] leechedBonuses = new int[7];
    private int[] ores = new int[2];
    private int[] constructionCoords;
    private int recoilCharges;
    private int runEnergy = 100;
    private int currentBankTab;
    private int interfaceId, walkableInterfaceId, multiIcon;
    private int dialogueActionId;
    private int overloadPotionTimer, prayerRenewalPotionTimer;
    private int furiousPotionTimer;
    private int fireImmunity, fireDamageModifier;
    private int amountDonated;
    private int wildernessLevel;
    private int fireAmmo;
    private int specialPercentage = 100;
    private int skullIcon = -1, skullTimer;
    private int teleblockTimer;
    private int dragonFireImmunity;
    private int poisonImmunity;
    private int shadowState;
    private int effigy;
    private int dfsCharges;
    private String XmasEvent;
    private int playerViewingIndex;
    private int staffOfLightEffect;
    private int minutesBonusExp = -1;
    private int selectedGeSlot = -1;
    private int selectedGeItem = -1;
    private int geQuantity;
    private int gePricePerItem;
    private int selectedSkillingItem;
    private int currentBookPage;
    private int storedRuneEssence, storedPureEssence;
    private int trapsLaid;
    private int skillAnimation;
    private int houseServant;
    private int houseServantCharges;
    private int servantItemFetch;
    private int portalSelected;
    private int constructionInterface;
    private int buildFurnitureId;
    private int buildFurnitureX;
    private int buildFurnitureY;
    private int combatRingType;

    /**
     * * BOOLEANS **
     */
    private boolean unlockedLoyaltyTitles[] = new boolean[12];
    private boolean[] crossedObstacles = new boolean[7];
    private boolean processFarming;
    private boolean crossingObstacle;
    private boolean targeted;
    public boolean isBanking;


	private boolean noteWithdrawal;


	private boolean swapMode;
    private boolean regionChange, allowRegionChangePacket;
    private boolean isDying;
    private boolean isRunning = true, isResting;
    private boolean experienceLocked;
    private boolean clientExitTaskActive;
    private boolean drainingPrayer;
    private boolean shopping;
    private boolean settingUpCannon;
    private boolean hasVengeance;
    private boolean killsTrackerOpen;
    private boolean acceptingAid;
    private boolean autoRetaliate;
    private boolean autocast;
    private boolean specialActivated;
    private boolean isCoughing;
    private boolean playerLocked;
    private boolean recoveringSpecialAttack;
    private boolean soundsActive, musicActive;
    private boolean newPlayer;
    private boolean openBank;
    private boolean inActive;
	public int timeOnline;
    private boolean inConstructionDungeon;
    private boolean isBuildingMode;
    private boolean voteMessageSent;
    private boolean receivedStarter;

    public void incrementAmountDonated2(float price) {
        this.amountDonated += price;
    }
    /*
     * Getters & Setters
     */
    public PlayerSession getSession() {
        return session;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public PriceChecker getPriceChecker() {
        return priceChecker;
    }

    /*
     * Getters and setters
     */
    public String getUsername() {
        return username;
    }

    public Player setUsername(String username) {
        this.username = username;
        return this;
    }

    public Long getLongUsername() {
        return longUsername;
    }

    public Player setLongUsername(Long longUsername) {
        this.longUsername = longUsername;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public void setEmailAddress(String address) {
        this.emailAddress = address;
    }

    public Player setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getHostAddress() {
        return hostAddress;
    }

    public Player setHostAddress(String hostAddress) {
        this.hostAddress = hostAddress;
        return this;
    }

    public String getSerialNumber() {
        return serial_number;
    }

    public Player setSerialNumber(String serial_number) {
        this.serial_number = serial_number;
        return this;
    }

    public FrameUpdater getFrameUpdater() {
        return this.frameUpdater;
    }

    public PlayerRights getRights() {
        return rights;
    }
    public Player setRights(PlayerRights rights) {
        this.rights = rights;
        return this;
    }

    public ChatMessage getChatMessages() {
        return chatMessages;
    }

    public PacketSender getPacketSender() {
        return packetSender;
    }

    public SkillManager getSkillManager() {
        return skillManager;
    }

    public Appearance getAppearance() {
        return appearance;
    }


    public PlayerRelations getRelations() {
        return relations;
    }

    public PlayerKillingAttributes getPlayerKillingAttributes() {
        return playerKillingAttributes;
    }

    public PointsHandler getPointsHandler() {
        return pointsHandler;
    }


    public boolean isImmuneToDragonFire() {
        return dragonFireImmunity > 0;
    }

    public int getDragonFireImmunity() {
        return dragonFireImmunity;
    }

    public void setDragonFireImmunity(int dragonFireImmunity) {
        this.dragonFireImmunity = dragonFireImmunity;
    }

    public void setDroprate(double droprate) {
        this.droprate = droprate;
    }
    public void setDoubleDropRate(double doubleDropRate) {
        this.doubleDropRate = doubleDropRate;
    }
    public double getDropRate() {
        return droprate;
    }
    public double getDoubleDropRate() {
      return doubleDropRate;
    }

    public void incrementDragonFireImmunity(int amount) {
        dragonFireImmunity += amount;
    }

    public void decrementDragonFireImmunity(int amount) {
        dragonFireImmunity -= amount;
    }


    public int getPoisonImmunity() {
        return poisonImmunity;
    }

    public void setPoisonImmunity(int poisonImmunity) {
        this.poisonImmunity = poisonImmunity;
    }

    public void incrementPoisonImmunity(int amount) {
        poisonImmunity += amount;
    }

    public void decrementPoisonImmunity(int amount) {
        poisonImmunity -= amount;
    }

    public boolean isAutoRetaliate() {
        return autoRetaliate;
    }

    public void setAutoRetaliate(boolean autoRetaliate) {
        this.autoRetaliate = autoRetaliate;
    }

    /**
     * @return the castSpell
     */
    public CombatSpell getCastSpell() {
        return castSpell;
    }

    /**
     * @param castSpell the castSpell to set
     */
    public void setCastSpell(CombatSpell castSpell) {
        this.castSpell = castSpell;
    }

    public CombatSpell getPreviousCastSpell() {
        return previousCastSpell;
    }

    public void setPreviousCastSpell(CombatSpell previousCastSpell) {
        this.previousCastSpell = previousCastSpell;
    }

    /**
     * @return the autocast
     */
    public boolean isAutocast() {
        return autocast;
    }

    /**
     * @param autocast the autocast to set
     */
    public void setAutocast(boolean autocast) {
        this.autocast = autocast;
    }

    /**
     * @return the skullTimer
     */
    public int getSkullTimer() {
        return skullTimer;
    }

    /**
     * @param skullTimer the skullTimer to set
     */
    public void setSkullTimer(int skullTimer) {
        this.skullTimer = skullTimer;
    }

    public void decrementSkullTimer() {
        skullTimer -= 50;
    }

    /**
     * @return the skullIcon
     */
    public int getSkullIcon() {
        return skullIcon;
    }

    /**
     * @param skullIcon the skullIcon to set
     */
    public void setSkullIcon(int skullIcon) {
        this.skullIcon = skullIcon;
    }

    /**
     * @return the teleblockTimer
     */
    public int getTeleblockTimer() {
        return teleblockTimer;
    }

    /**
     * @param teleblockTimer the teleblockTimer to set
     */
    public void setTeleblockTimer(int teleblockTimer) {
        this.teleblockTimer = teleblockTimer;
    }

    public void decrementTeleblockTimer() {
        teleblockTimer--;
    }

    /**
     * @return the autocastSpell
     */
    public CombatSpell getAutocastSpell() {
        return autocastSpell;
    }

    /**
     * @param autocastSpell the autocastSpell to set
     */
    public void setAutocastSpell(CombatSpell autocastSpell) {
        this.autocastSpell = autocastSpell;
    }

    /**
     * @return the specialPercentage
     */
    public int getSpecialPercentage() {
        return specialPercentage;
    }

    /**
     * @param specialPercentage the specialPercentage to set
     */
    public void setSpecialPercentage(int specialPercentage) {
        this.specialPercentage = specialPercentage;
    }

    /**
     * @return the fireAmmo
     */
    public int getFireAmmo() {
        return fireAmmo;
    }

    /**
     * @param fireAmmo the fireAmmo to set
     */
    public void setFireAmmo(int fireAmmo) {
        this.fireAmmo = fireAmmo;
    }

    public int getWildernessLevel() {
        return wildernessLevel;
    }

    public void setWildernessLevel(int wildernessLevel) {
        this.wildernessLevel = wildernessLevel;
    }

    /**
     * @return the combatSpecial
     */
    public CombatSpecial getCombatSpecial() {
        return combatSpecial;
    }

    /**
     * @param combatSpecial the combatSpecial to set
     */
    public void setCombatSpecial(CombatSpecial combatSpecial) {
        this.combatSpecial = combatSpecial;
    }

    /**
     * @return the specialActivated
     */
    public boolean isSpecialActivated() {
        return specialActivated;
    }

    /**
     * @param specialActivated the specialActivated to set
     */
    public void setSpecialActivated(boolean specialActivated) {
        this.specialActivated = specialActivated;
    }

    public void decrementSpecialPercentage(int drainAmount) {
        this.specialPercentage -= drainAmount;

        if (specialPercentage < 0) {
            specialPercentage = 0;
        }
    }

    public void incrementSpecialPercentage(int gainAmount) {
        this.specialPercentage += gainAmount;

        if (specialPercentage > 100) {
            specialPercentage = 100;
        }
    }

    /**
     * @return the rangedAmmo
     */
    public RangedWeaponData getRangedWeaponData() {
        return rangedWeaponData;
    }

    /**
     * @param rangedAmmo the rangedAmmo to set
     */
    public void setRangedWeaponData(RangedWeaponData rangedWeaponData) {
        this.rangedWeaponData = rangedWeaponData;
    }

    /**
     * @return the weapon.
     */
    public WeaponInterface getWeapon() {
        return weapon;
    }

    public int raidDmg;

    public int getRaidDmg() {
        return raidDmg;
    }
    public void setRaidDmg(int i) {
        this.raidDmg = i;
    }
    public ArrayList<Integer> walkableInterfaceList = new ArrayList<>();
	public long lastHelpRequest;
	public long lastAuthClaimed;
	//public GameModes selectedGameMode;
	private boolean areCloudsSpawned;

	public boolean inFFA;
	public boolean inFFALobby;
    public int[] oldSkillLevels = new int[25];
    public long[] oldSkillXP = new long[25];
    public int[] oldSkillMaxLevels = new int[25];
    public void resetInterfaces() {
        walkableInterfaceList.stream().filter((i) -> !(i == 41005 || i == 41000)).forEach((i) -> {
            getPacketSender().sendWalkableInterface(i, false);
        });

        walkableInterfaceList.clear();
    }

    public void sendParallellInterfaceVisibility(int interfaceId, boolean visible) {
        if (this != null && this.getPacketSender() != null) {
            if (visible) {
                if (walkableInterfaceList.contains(interfaceId)) {
                    return;
                } else {
                    walkableInterfaceList.add(interfaceId);
                }
            } else {
                if (!walkableInterfaceList.contains(interfaceId)) {
                    return;
                } else {
                    walkableInterfaceList.remove( (Object) interfaceId);
                }
            }

            getPacketSender().sendWalkableInterface(interfaceId, visible);
        }
    }

    /**
     * @param weapon the weapon to set.
     */
    public void setWeapon(WeaponInterface weapon) {
        this.weapon = weapon;
    }

    /**
     * @return the fightType
     */
    public FightType getFightType() {
        return fightType;
    }

    /**
     * @param fightType the fightType to set
     */
    public void setFightType(FightType fightType) {
        this.fightType = fightType;
    }

    public Bank[] getBanks() {
        return bankTabs;
    }

    public Bank getBank(int index) {
        return bankTabs[index];
    }

    public Player setBank(int index, Bank bank) {
        this.bankTabs[index] = bank;
        return this;
    }

    public boolean isAcceptAid() {
        return acceptingAid;
    }

    public void setAcceptAid(boolean acceptingAid) {
        this.acceptingAid = acceptingAid;
    }

    public Trading getTrading() {
        return trading;
    }

    public Dueling getDueling() {
        return dueling;
    }

    public CopyOnWriteArrayList<KillsEntry> getKillsTracker() {
        return killsTracker;
    }

    public CopyOnWriteArrayList<DropLogEntry> getDropLog() {
        return dropLog;
    }

    public void setWalkToTask(WalkToTask walkToTask) {
        this.walkToTask = walkToTask;
    }

    public WalkToTask getWalkToTask() {
        return walkToTask;
    }

    public Player setSpellbook(MagicSpellbook spellbook) {
        this.spellbook = spellbook;
        return this;
    }

    public MagicSpellbook getSpellbook() {
        return spellbook;
    }

    public Player setPrayerbook(Prayerbook prayerbook) {
        this.prayerbook = prayerbook;
        return this;
    }

    public Prayerbook getPrayerbook() {
        return prayerbook;
    }

    /**
     * The player's local players list.
     */
    public List<Player> getLocalPlayers() {
        return localPlayers;
    }

    /**
     * The player's local npcs list getter
     */
    public List<NPC> getLocalNpcs() {
        return localNpcs;
    }

    public Player setInterfaceId(int interfaceId) {
        this.interfaceId = interfaceId;
        return this;
    }

    public int getInterfaceId() {
        return this.interfaceId;
    }

    public boolean isDying() {
        return isDying;
    }

    public void setDying(boolean isDying) {
        this.isDying = isDying;
    }

    public int[] getForceMovement() {
        return forceMovement;
    }

    public Player setForceMovement(int[] forceMovement) {
        this.forceMovement = forceMovement;
        return this;
    }

    /**
     * @return the equipmentAnimation
     */
    public CharacterAnimations getCharacterAnimations() {
        return characterAnimations;
    }

    /**
     * @return the equipmentAnimation
     */
    public void setCharacterAnimations(CharacterAnimations equipmentAnimation) {
        this.characterAnimations = equipmentAnimation.clone();
    }

    public LoyaltyTitles getLoyaltyTitle() {
        return loyaltyTitle;
    }

    public void setLoyaltyTitle(LoyaltyTitles loyaltyTitle) {
        this.loyaltyTitle = loyaltyTitle;
    }

    public void setWalkableInterfaceId(int interfaceId2) {
        this.walkableInterfaceId = interfaceId2;
    }

    public PlayerInteractingOption getPlayerInteractingOption() {
        return playerInteractingOption;
    }

    public Player setPlayerInteractingOption(PlayerInteractingOption playerInteractingOption) {
        this.playerInteractingOption = playerInteractingOption;
        return this;
    }

    public int getMultiIcon() {
        return multiIcon;
    }

    public Player setMultiIcon(int multiIcon) {
        this.multiIcon = multiIcon;
        return this;
    }

    public int getWalkableInterfaceId() {
        return walkableInterfaceId;
    }

    public boolean soundsActive() {
        return soundsActive;
    }

    public void setSoundsActive(boolean soundsActive) {
        this.soundsActive = soundsActive;
    }

    public boolean musicActive() {
        return musicActive;
    }

    public void setMusicActive(boolean musicActive) {
        this.musicActive = musicActive;
    }

    public BonusManager getBonusManager() {
        return bonusManager;
    }

    public int getRunEnergy() {
        return runEnergy;
    }

    public Player setRunEnergy(int runEnergy) {
        this.runEnergy = runEnergy;
        return this;
    }

    public Stopwatch getLastRunRecovery() {
        return lastRunRecovery;
    }

    public Player setRunning(boolean isRunning) {
        this.isRunning = isRunning;
        return this;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public Player setResting(boolean isResting) {
        this.isResting = isResting;
        return this;
    }

    public boolean isResting() {
        return isResting;
    }

    public void setMoneyInPouch(long moneyInPouch) {
        this.moneyInPouch = moneyInPouch;
    }

    public long getMoneyInPouch() {
        return moneyInPouch;
    }

    public int getMoneyInPouchAsInt() {
        return moneyInPouch > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) moneyInPouch;
    }

    public boolean experienceLocked() {
        return experienceLocked;
    }

    public void setExperienceLocked(boolean experienceLocked) {
        this.experienceLocked = experienceLocked;
    }

    public void setClientExitTaskActive(boolean clientExitTaskActive) {
        this.clientExitTaskActive = clientExitTaskActive;
    }

    public boolean isClientExitTaskActive() {
        return clientExitTaskActive;
    }

    public Player setCurrentClanChat(ClanChat clanChat) {
        this.currentClanChat = clanChat;
        return this;
    }

    public ClanChat getCurrentClanChat() {
        return currentClanChat;
    }

    public String getClanChatName() {
        return clanChatName;
    }

    public Player setClanChatName(String clanChatName) {
        this.clanChatName = clanChatName;
        return this;
    }

    public void setInputHandling(Input inputHandling) {
        this.inputHandling = inputHandling;
    }

    public Input getInputHandling() {
        return inputHandling;
    }

    public boolean isDrainingPrayer() {
        return drainingPrayer;
    }

    public void setDrainingPrayer(boolean drainingPrayer) {
        this.drainingPrayer = drainingPrayer;
    }

    public Stopwatch getClickDelay() {
        return clickDelay;
    }
    public Stopwatch getCombayDelay() {
        return combatDelay;
    }

    public int[] getLeechedBonuses() {
        return leechedBonuses;
    }

    public Stopwatch getLastItemPickup() {
        return lastItemPickup;
    }

    public Stopwatch getLastSummon() {
        return lastSummon;
    }

    public BankSearchAttributes getBankSearchingAttribtues() {
        return bankSearchAttributes;
    }

    public AchievementAttributes getAchievementAttributes() {
        return achievementAttributes;
    }

    public BankPinAttributes getBankPinAttributes() {
        return bankPinAttributes;
    }

    public int getCurrentBankTab() {
        return currentBankTab;
    }

    public Player setCurrentBankTab(int tab) {
        this.currentBankTab = tab;
        return this;
    }

    public boolean isBanking() {
        return isBanking;
    }

    public Player setBanking(boolean isBanking) {
        this.isBanking = isBanking;
        return this;
    }

    public void setNoteWithdrawal(boolean noteWithdrawal) {
        this.noteWithdrawal = noteWithdrawal;
    }

    public boolean withdrawAsNote() {
        return noteWithdrawal;
    }

	public GamblingManager getGambling1() {
		return gamblingManager;
	}

	private GamblingInterface gambling = new GamblingInterface(this);

	public GamblingInterface getGambling() {
		return gambling;
	}

	public GambleOfferItemContainer getGambleOffer() {
		return gambleOffer;
	}

    public void setSwapMode(boolean swapMode) {
        this.swapMode = swapMode;
    }

    public boolean swapMode() {
        return swapMode;
    }

    public boolean isShopping() {
        return shopping;
    }

    public void setShopping(boolean shopping) {
        this.shopping = shopping;
    }

    public Shop getShop() {
        return shop;
    }

    public Player setShop(Shop shop) {
        this.shop = shop;
        return this;
    }

    public GameObject getInteractingObject() {
        return interactingObject;
    }

    public Player setInteractingObject(GameObject interactingObject) {
        this.interactingObject = interactingObject;
        return this;
    }

    public Item getInteractingItem() {
        return interactingItem;
    }

    public void setInteractingItem(Item interactingItem) {
        this.interactingItem = interactingItem;
    }

    public Dialogue getDialogue() {
        return this.dialogue;
    }

    public void setDialogue(Dialogue dialogue) {
        this.dialogue = dialogue;
    }

    public int getDialogueActionId() {
        return dialogueActionId;
    }

    public void setDialogueActionId(int dialogueActionId) {
        this.dialogueActionId = dialogueActionId;
    }

    public void setSettingUpCannon(boolean settingUpCannon) {
        this.settingUpCannon = settingUpCannon;
    }

    public boolean isSettingUpCannon() {
        return settingUpCannon;
    }

    public Player setCannon(DwarfCannon cannon) {
        this.cannon = cannon;
        return this;
    }

    public DwarfCannon getCannon() {
        return cannon;
    }

    public int getOverloadPotionTimer() {
        return overloadPotionTimer;
    }

    public void setOverloadPotionTimer(int overloadPotionTimer) {
        this.overloadPotionTimer = overloadPotionTimer;
    }

    public int getFuriousPotionTimer() {
        return furiousPotionTimer;
    }

    public void setFuriousPotionTimer(int furiousPotionTimer) {
        this.furiousPotionTimer = furiousPotionTimer;
    }
    public int getPrayerRenewalPotionTimer() {
        return prayerRenewalPotionTimer;
    }

    public void setPrayerRenewalPotionTimer(int prayerRenewalPotionTimer) {
        this.prayerRenewalPotionTimer = prayerRenewalPotionTimer;
    }

    public Stopwatch getSpecialRestoreTimer() {
        return specialRestoreTimer;
    }

    public boolean[] getUnlockedLoyaltyTitles() {
        return unlockedLoyaltyTitles;
    }

    public void setUnlockedLoyaltyTitles(boolean[] unlockedLoyaltyTitles) {
        this.unlockedLoyaltyTitles = unlockedLoyaltyTitles;
    }

    public void setUnlockedLoyaltyTitle(int index) {
        unlockedLoyaltyTitles[index] = true;
    }

    public Stopwatch getEmoteDelay() {
        return emoteDelay;
    }

    public MinigameAttributes getMinigameAttributes() {
        return minigameAttributes;
    }

    public Minigame getMinigame() {
    	return minigame;
    }

	public void setMinigame(Minigame minigame) {
		this.minigame = minigame;
	}

    public int getFireImmunity() {
        return fireImmunity;
    }

    public Player setFireImmunity(int fireImmunity) {
        this.fireImmunity = fireImmunity;
        return this;
    }

    public int getFireDamageModifier() {
        return fireDamageModifier;
    }

    public Player setFireDamageModifier(int fireDamageModifier) {
        this.fireDamageModifier = fireDamageModifier;
        return this;
    }

    public boolean hasVengeance() {
        return hasVengeance;
    }

    public void setHasVengeance(boolean hasVengeance) {
        this.hasVengeance = hasVengeance;
    }

    public Stopwatch getLastVengeance() {
        return lastVengeance;
    }


	public void setHouseRooms(Room[][][] houseRooms) {
		this.houseRooms = houseRooms;
	}


	public void setHousePortals(ArrayList<Portal> housePortals) {
		this.housePortals = housePortals;
	}

	/*
	 * Construction instancing Arlania
	 */
	public boolean isVisible() {
		if(getLocation() == Locations.Location.CONSTRUCTION) {
			return false;
		}
		return true;
	}


	public void setHouseFurtinture(ArrayList<HouseFurniture> houseFurniture) {
		this.houseFurniture = houseFurniture;
	}


    public Stopwatch getTolerance() {
        return tolerance;
    }

    public boolean isTargeted() {
        return targeted;
    }

    public void setTargeted(boolean targeted) {
        this.targeted = targeted;
    }

    public Stopwatch getLastYell() {
        return lastYell;
    }

    public Stopwatch getLastZulrah() {
        return lastZulrah;
    }

    public Stopwatch getLastSql() {
        return lastSql;
    }

    public int getAmountDonated() {
        return amountDonated;
    }

    public void incrementAmountDonated(int amountDonated) {
        this.amountDonated += amountDonated;
    }

	private ProgressionManager progressionManager = new ProgressionManager(this);

	public ProgressionManager getProgressionManager() {
		return progressionManager;
	}
    public long getTotalPlayTime() {
        return totalPlayTime;
    }

    public void setTotalPlayTime(long amount) {
        this.totalPlayTime = amount;
    }

    public Stopwatch getRecordedLogin() {
        return recordedLogin;
    }

    public Player setRegionChange(boolean regionChange) {
        this.regionChange = regionChange;
        return this;
    }

    public boolean isChangingRegion() {
        return this.regionChange;
    }

    public void setAllowRegionChangePacket(boolean allowRegionChangePacket) {
        this.allowRegionChangePacket = allowRegionChangePacket;
    }

    public boolean isAllowRegionChangePacket() {
        return allowRegionChangePacket;
    }

    public boolean isKillsTrackerOpen() {
        return killsTrackerOpen;
    }

    public void setKillsTrackerOpen(boolean killsTrackerOpen) {
        this.killsTrackerOpen = killsTrackerOpen;
    }

    public boolean isCoughing() {
        return isCoughing;
    }

    public void setCoughing(boolean isCoughing) {
        this.isCoughing = isCoughing;
    }

    public int getShadowState() {
        return shadowState;
    }

    public void setShadowState(int shadow) {
        this.shadowState = shadow;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    public boolean isPlayerLocked() {
        return playerLocked;
    }

    public Player setPlayerLocked(boolean playerLocked) {
        this.playerLocked = playerLocked;
        return this;
    }

    public Stopwatch getSqlTimer() {
        return sqlTimer;
    }

    public Stopwatch getFoodTimer() {
        return foodTimer;
    }

    public Stopwatch getPotionTimer() {
        return potionTimer;
    }

    public Item getUntradeableDropItem() {
        return untradeableDropItem;
    }

    public void setUntradeableDropItem(Item untradeableDropItem) {
        this.untradeableDropItem = untradeableDropItem;
    }

    public boolean isRecoveringSpecialAttack() {
        return recoveringSpecialAttack;
    }

    public void setRecoveringSpecialAttack(boolean recoveringSpecialAttack) {
        this.recoveringSpecialAttack = recoveringSpecialAttack;
    }

    public CombatType getLastCombatType() {
        return lastCombatType;
    }

    public void setLastCombatType(CombatType lastCombatType) {
        this.lastCombatType = lastCombatType;
    }

    public int getEffigy() {
        return this.effigy;
    }

    public void setEffigy(int effigy) {
        this.effigy = effigy;
    }

    public int getDfsCharges() {
        return dfsCharges;
    }

    public void incrementDfsCharges(int amount) {
        this.dfsCharges += amount;
    }

    public void setNewPlayer(boolean newPlayer) {
        this.newPlayer = newPlayer;
    }

    public boolean newPlayer() {
        return newPlayer;
    }

    public Stopwatch getLogoutTimer() {
        return lougoutTimer;
    }

    public Player setUsableObject(Object[] usableObject) {
        this.usableObject = usableObject;
        return this;
    }

    public Player setUsableObject(int index, Object usableObject) {
        this.usableObject[index] = usableObject;
        return this;
    }

    public Object[] getUsableObject() {
        return usableObject;
    }

    public int getPlayerViewingIndex() {
        return playerViewingIndex;
    }

    public void setPlayerViewingIndex(int playerViewingIndex) {
        this.playerViewingIndex = playerViewingIndex;
    }

    public boolean hasStaffOfLightEffect() {
        return staffOfLightEffect > 0;
    }

    public int getStaffOfLightEffect() {
        return staffOfLightEffect;
    }

    public void setStaffOfLightEffect(int staffOfLightEffect) {
        this.staffOfLightEffect = staffOfLightEffect;
    }

    public void decrementStaffOfLightEffect() {
        this.staffOfLightEffect--;
    }

    public boolean openBank() {
        return openBank;
    }

    public void setOpenBank(boolean openBank) {
        this.openBank = openBank;
    }

    public int getMinutesBonusExp() {
        return minutesBonusExp;
    }

    public void setMinutesBonusExp(int minutesBonusExp, boolean add) {
        this.minutesBonusExp = (add ? this.minutesBonusExp + minutesBonusExp : minutesBonusExp);
    }

    public void setInactive(boolean inActive) {
        this.inActive = inActive;
    }

    public boolean isInActive() {
        return inActive;
    }

    public int getSelectedGeItem() {
        return selectedGeItem;
    }

    public void setSelectedGeItem(int selectedGeItem) {
        this.selectedGeItem = selectedGeItem;
    }

    public int getGeQuantity() {
        return geQuantity;
    }

    public void setGeQuantity(int geQuantity) {
        this.geQuantity = geQuantity;
    }

    public int getGePricePerItem() {
        return gePricePerItem;
    }

    public void setGePricePerItem(int gePricePerItem) {
        this.gePricePerItem = gePricePerItem;
    }

    public GrandExchangeSlot[] getGrandExchangeSlots() {
        return grandExchangeSlots;
    }

    public void setGrandExchangeSlots(GrandExchangeSlot[] GrandExchangeSlots) {
        this.grandExchangeSlots = GrandExchangeSlots;
    }

    public void setGrandExchangeSlot(int index, GrandExchangeSlot state) {
        this.grandExchangeSlots[index] = state;
    }

    public void setSelectedGeSlot(int slot) {
        this.selectedGeSlot = slot;
    }

    public int getSelectedGeSlot() {
        return selectedGeSlot;
    }

    public Task getCurrentTask() {
        return currentTask;
    }

    public void setCurrentTask(Task currentTask) {
        this.currentTask = currentTask;
    }

    public int getSelectedSkillingItem() {
        return selectedSkillingItem;
    }

    public void setSelectedSkillingItem(int selectedItem) {
        this.selectedSkillingItem = selectedItem;
    }

    public boolean shouldProcessFarming() {
        return processFarming;
    }

    public void setProcessFarming(boolean processFarming) {
        this.processFarming = processFarming;
    }

    public Pouch getSelectedPouch() {
        return selectedPouch;
    }

    public void setSelectedPouch(Pouch selectedPouch) {
        this.selectedPouch = selectedPouch;
    }

    public int getCurrentBookPage() {
        return currentBookPage;
    }

    public void setCurrentBookPage(int currentBookPage) {
        this.currentBookPage = currentBookPage;
    }

    public int getStoredRuneEssence() {
        return storedRuneEssence;
    }

    public void setStoredRuneEssence(int storedRuneEssence) {
        this.storedRuneEssence = storedRuneEssence;
    }

    public int getStoredPureEssence() {
        return storedPureEssence;
    }

    public void setStoredPureEssence(int storedPureEssence) {
        this.storedPureEssence = storedPureEssence;
    }

    public int getTrapsLaid() {
        return trapsLaid;
    }

    public void setTrapsLaid(int trapsLaid) {
        this.trapsLaid = trapsLaid;
    }

    public boolean isCrossingObstacle() {
        return crossingObstacle;
    }

    public Player setCrossingObstacle(boolean crossingObstacle) {
        this.crossingObstacle = crossingObstacle;
        return this;
    }

    public boolean[] getCrossedObstacles() {
        return crossedObstacles;
    }

    public boolean getCrossedObstacle(int i) {
        return crossedObstacles[i];
    }

    public Player setCrossedObstacle(int i, boolean completed) {
        crossedObstacles[i] = completed;
        return this;
    }

    public void setCrossedObstacles(boolean[] crossedObstacles) {
        this.crossedObstacles = crossedObstacles;
    }

    public int getSkillAnimation() {
        return skillAnimation;
    }

    public Player setSkillAnimation(int animation) {
        this.skillAnimation = animation;
        return this;
    }

    public int[] getOres() {
        return ores;
    }

    public void setOres(int[] ores) {
        this.ores = ores;
    }

    public void setResetPosition(Position resetPosition) {
        this.resetPosition = resetPosition;
    }

    public Position getResetPosition() {
        return resetPosition;
    }

    public Slayer getSlayer() {
        return slayer;
    }

    public Summoning getSummoning() {
        return summoning;
    }

    public Farming getFarming() {
        return farming;
    }

    public boolean inConstructionDungeon() {
        return inConstructionDungeon;
    }

    public void setInConstructionDungeon(boolean inConstructionDungeon) {
        this.inConstructionDungeon = inConstructionDungeon;
    }

    public int getHouseServant() {
        return houseServant;
    }
    public HouseLocation getHouseLocation() {
		return houseLocation;
	}
	public HouseTheme getHouseTheme() {
		return houseTheme;
	}

	public void setHouseTheme(HouseTheme houseTheme) {
		this.houseTheme = houseTheme;
	}
	public void setHouseLocation(HouseLocation houseLocation) {
		this.houseLocation = houseLocation;
	}

    public void setHouseServant(int houseServant) {
        this.houseServant = houseServant;
    }

    public int getHouseServantCharges() {
        return this.houseServantCharges;
    }

    public void setHouseServantCharges(int houseServantCharges) {
        this.houseServantCharges = houseServantCharges;
    }

    public void incrementHouseServantCharges() {
        this.houseServantCharges++;
    }

    public int getServantItemFetch() {
        return servantItemFetch;
    }

    public void setServantItemFetch(int servantItemFetch) {
        this.servantItemFetch = servantItemFetch;
    }

    public int getPortalSelected() {
        return portalSelected;
    }

    public void setPortalSelected(int portalSelected) {
        this.portalSelected = portalSelected;
    }

    public boolean isBuildingMode() {
        return this.isBuildingMode;
    }

    public void setIsBuildingMode(boolean isBuildingMode) {
        this.isBuildingMode = isBuildingMode;
    }

    public int[] getConstructionCoords() {
        return constructionCoords;
    }

    public void setConstructionCoords(int[] constructionCoords) {
        this.constructionCoords = constructionCoords;
    }

    public int getBuildFurnitureId() {
        return this.buildFurnitureId;
    }

    public void setBuildFuritureId(int buildFuritureId) {
        this.buildFurnitureId = buildFuritureId;
    }

    public int getBuildFurnitureX() {
        return this.buildFurnitureX;
    }

    public void setBuildFurnitureX(int buildFurnitureX) {
        this.buildFurnitureX = buildFurnitureX;
    }

    public int getBuildFurnitureY() {
        return this.buildFurnitureY;
    }

    public void setBuildFurnitureY(int buildFurnitureY) {
        this.buildFurnitureY = buildFurnitureY;
    }

    public int getCombatRingType() {
        return this.combatRingType;
    }

    public void setCombatRingType(int combatRingType) {
        this.combatRingType = combatRingType;
    }

    public Room[][][] getHouseRooms() {
        return houseRooms;
    }

    public ArrayList<Portal> getHousePortals() {
        return housePortals;
    }

    public ArrayList<HouseFurniture> getHouseFurniture() {
        return houseFurniture;
    }

    public int getConstructionInterface() {
        return this.constructionInterface;
    }

    public void setConstructionInterface(int constructionInterface) {
        this.constructionInterface = constructionInterface;
    }

    public int[] getBrawlerChargers() {
        return this.brawlerCharges;
    }

    public void setBrawlerCharges(int[] brawlerCharges) {
        this.brawlerCharges = brawlerCharges;
    }


    public int getRecoilCharges() {
        return this.recoilCharges;
    }

    public int setRecoilCharges(int recoilCharges) {
        return this.recoilCharges = recoilCharges;
    }

    public boolean voteMessageSent() {
        return this.voteMessageSent;
    }

    public void setVoteMessageSent(boolean voteMessageSent) {
        this.voteMessageSent = voteMessageSent;
    }

    public boolean didReceiveStarter() {
        return receivedStarter;
    }

	public void sendMessage(String string) {
		packetSender.sendMessage(string);
	}

    public void setReceivedStarter(boolean receivedStarter) {
        this.receivedStarter = receivedStarter;
    }

    public BlowpipeLoading getBlowpipeLoading() {
    	return blowpipeLoading;
    }
    public DragonRageLoading getDragonRageLoading() {
    	return dragonrageLoading;
    }
    public CorruptBandagesLoading getCorruptBandagesLoading() {
    	return CorruptBandagesLoading;
    }
    public MinigunLoading getMinigunLoading() {
    	return MinigunLoading;
    }
	public boolean cloudsSpawned() {
		return areCloudsSpawned;
	}

	public void setCloudsSpawned(boolean cloudsSpawned) {
		this.areCloudsSpawned = cloudsSpawned;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isShopUpdated() {
		return shopUpdated;
	}

	public void setShopUpdated(boolean shopUpdated) {
		this.shopUpdated = shopUpdated;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void write(Packet packet) {
		// TODO Auto-generated method stub

	}

	public void datarsps(Player player, String username2) {
		// TODO Auto-generated method stub

	}

    public PVMRanking getPvMRanking() {
        return pvmRanking;
    }


	public int getWeaponGamePoints() {
		return weaponGamePoints;
	}

	/**
	 * Sets the amount of {@link WeaponGame}
	 * points a {@link Player} has.
	 *
	 * @param weaponGamePoints
	 * 		The amount of points to set.
	 */
	public void setWeaponGamePoints(int weaponGamePoints) {
		this.weaponGamePoints = weaponGamePoints;

	}
    public boolean hasRights(PlayerRights... hasRights) {
        for (int i = 0; i < hasRights.length; i++) {
            if (rights == hasRights[i]) {
                return true;
            }
        }
        return false;
    }

    public void updateMoneyPouch(){
        getPacketSender().sendString(8135, "" + getMoneyInPouch() + "");
    }
    private ItemDefinition ItemDefiniton = new ItemDefinition();


	public long lastOpPotion;


	public boolean inCustomFFA;

    public ItemDefinition getItems() {
		return ItemDefiniton;
	}
    /*
     * UPGRADE
     */
    public Item upgradeSelection;

	public Item getUpgradeSelection() {
		return upgradeSelection;
	}

	public void setUpgradeSelection(Item upgradeSelection) {
		this.upgradeSelection = upgradeSelection;
	}

	 private TutorialStages tutorialStage;

	private int yPostDailyTask;
	
	private int zPosDailyTask;
	
	private int rewardDailyTask;

	public int getRewardDailyTask() {
		return rewardDailyTask;
	}
	
	public void setRewardDailyTask(int rewardDailyTask) {
		this.rewardDailyTask = rewardDailyTask;
	}

	public TutorialStages getTutorialStage() {
	    return tutorialStage;
    }

    public void setTutorialStage(TutorialStages stage){
	    this.tutorialStage = stage;
    }

	public Player setLastgoodiebox(int lastgoodiebox ) {
		this.lastgoodiebox = lastgoodiebox;
		return this;
	}

	public void setController(StadiumController stadiumController) {
		// TODO Auto-generated method stub
		
	}

	public void setzPosDailyTask(int zPosDailyTask) {
		this.zPosDailyTask = zPosDailyTask;
	}

	public void setyPostDailyTask(int yPostDailyTask) {
		this.yPostDailyTask = yPostDailyTask;
	}

	public Object getController() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getzPosDailyTask() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getyPostDailyTask() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setDissolveData(int id, int amountOfOrbs, int dissolveXP) {
		this.dissolveId = id;
		this.dissolveOrbAmount = amountOfOrbs;
		this.dissolveXP = dissolveXP;
	}

	public void removeDissolveData() {
		this.dissolveId = 0;
		this.dissolveOrbAmount = 0;
		this.dissolveXP = 0;
	}

	private StartScreen startScreen = null;

	public StartScreen getStartScreen() {
		return startScreen;
	}

	public void setStartScreen(StartScreen startScreen) {
		this.startScreen = startScreen;
	}
	private AchievementInterface achievementInterface;

	public void setAchievementInterface(AchievementInterface achievementInterface) {
		this.achievementInterface = achievementInterface;
	}

	public AchievementInterface getAchievementInterface() {
		return this.achievementInterface;
	}

	private AchievementTracker achievementTracker = new AchievementTracker(this);

	public AchievementTracker getAchievementTracker() {
		return this.achievementTracker;
	}

}

