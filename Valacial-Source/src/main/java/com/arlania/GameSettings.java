package com.arlania;

import com.arlania.model.Position;
import com.arlania.net.security.ConnectionHandler;
import java.math.BigInteger;

public class GameSettings {
	public static final Position EDGEVILLE = new Position(3088, 3485, 0);



	public static boolean LIVE = false;

	public static final int LOCAL_BACKUP_MAX = 500;

	/**
	 * Dzone activation
	 */
	public static boolean DZONEON = false;
	
	/** 
	 * The maximum amount of itemIDs
	 */
	public static final int ITEM_LIMIT = 60000;
	
	/**
	 * The game port
	 */
	public static final int GAME_PORT = 43595;

	/**
	 * The game version
	 */
	public static final int GAME_VERSION = 13;

	/**
	 * The maximum amount of players that can be logged in on a single game
	 * sequence.
	 */
	public static final int LOGIN_THRESHOLD = 25;

	/**
	 * The maximum amount of players that can be logged in on a single game
	 * sequence.
	 */
	public static final int LOGOUT_THRESHOLD = 50;
	
	/**
	 * The maximum amount of players who can receive rewards on a single game
	 * sequence.
	 */
	public static final int VOTE_REWARDING_THRESHOLD = 15;

	/**
	 * The maximum amount of connections that can be active at a time, or in
	 * other words how many clients can be logged in at once per connection.
	 * (0 is counted too)
	 */
	public static final int CONNECTION_AMOUNT = 4;

	/**
	 * The throttle interval for incoming connections accepted by the
	 * {@link ConnectionHandler}.
	 */
	public static final long CONNECTION_INTERVAL = 1000;

	/**
	 * The number of seconds before a connection becomes idle.
	 */
	public static final int IDLE_TIME = 15;
	
	/**
	 * The keys used for encryption on login
	 */
	public static final BigInteger RSA_MODULUS = new BigInteger("141038977654242498796653256463581947707085475448374831324884224283104317501838296020488428503639086635001378639378416098546218003298341019473053164624088381038791532123008519201622098961063764779454144079550558844578144888226959180389428577531353862575582264379889305154355721898818709924743716570464556076517");
	public static final BigInteger RSA_EXPONENT = new BigInteger("73062137286746919055592688968652930781933135350600813639315492232042839604916461691801305334369089083392538639347196645339946918717345585106278208324882123479616835538558685007295922636282107847991405620139317939255760783182439157718323265977678194963487269741116519721120044892805050386167677836394617891073");

	/**
	 * The maximum amount of messages that can be decoded in one sequence.
	 */
	public static final int DECODE_LIMIT = 30;
	
	/** GAME **/

	/**
	 * Processing the engine
	 */
	public static final int ENGINE_PROCESSING_CYCLE_RATE = 600;
	public static final int GAME_PROCESSING_CYCLE_RATE = 600;

	/**
	 * Are the MYSQL services enabled?
	 */
	public static boolean MYSQL_ENABLED = false;

	/**
	 * Handles Examination interface.
	 */
	
	public static boolean Examniation = false;
	
	/**
	 * Is it currently bonus xp?
	 */
	public static boolean BONUS_EXP = false;//Misc.isWeekend();
	/**
	 * 
	 * The default position
	 */
	public static final Position DEFAULT_POSITION = new Position(3817, 3484);

	
	public static final int MAX_STARTERS_PER_IP = 2;
	
	/**
	 * Untradeable items//sec
	 * Items which cannot be traded or staked
	 */
	public static final int[] UNTRADEABLE_ITEMS = 
		{13661, 13262, 13727, 20079, 6500, 20692,
		7509, 7510, //ROCK CAKES, Ice gloves
		22053, //ecumenical keys
		19748, //ardy cape 4
		1561,
		896,
		11599,//pet return
		
		/* EVENT ITEMS */
		7329, 7330, 7331, 10326, 10327, 7404, 10329, 7406, 7405, 10328, 2946, // Firelighters, colored logs, gold tinderbox

		//easter 2017
		22051, 4565, 1037,

				//dice bag
				15084,

		/* xmas event 2016 */
		15420,
		13101,
		14595,
		14603,
		22043,
		14602,
		12425,
		14605,
		/* end xmas event 2016 */
		
		 9013, 13150, //friday the 13th items (may 2016)
		 9922, 9921, 22036, 22037, 22038, 22039, 22040, //hween 2k16
		 /* DONE EVENT ITEMS */
		 
		2724, //clue casket
		15707, //ring of kinship
		22014, 22015, 22016, 22017, 22018, 22019, 22020, 22021, 22022, 22023, 22024, 22025, 22026, 22027, 22028, 22029, 22030, 22031, 22032, //skilling pets
		14130, 14131, 14140, 14141, //sacred clay tools
		6797, //unlimited watering can
		16691, 9704, 17239, 16669, 6068, 9703, // Iron Man Items
		773, //perfect ring
		6529, 16127, 2996, 2677, 2678, 2679, 2680, 2682, 20081,
		2683, 2684, 2685, 2686, 2687, 2688, 2689, 11180, 
		6570, 12158, 12159, 12160, 12163, 12161, 12162,
		19143, 19149, 19146, 4155,
		8850, 10551, 8839, 8840, 8842, 11663, 11664, 19712,
		11665, 8844, 8845, 8846, 8847, 
		8848, 8849, 8850, 7462, 7461, 7460, 
		7459, 7458, 7457, 7456, 7455, 7454, 7453, 11665, 10499, 9748, 
		9754, 9751, 9769, 9757, 9760, 9763, 9802, 9808,
		9784, 9799, 9805, 9781, 9796, 9793, 9775, 9772,
		9778, 9787, 9811, 9766, 9749, 9755, 9752, 9770, 
		9758, 9761, 9764, 9803, 9809, 9785, 9800, 9806, 20072,
		9782, 9797, 9794, 9776, 9773, 9779, 9788, 9812, 
		9767, 9747, 9753, 9750, 9768, 9756, 9759, 9762,
		9801, 9807, 9783, 9798, 9804, 9780, 9795, 9792, 
		9774, 9771, 9777, 9786, 9810, 9765, 9948, 9949,
		9950, 12169, 12170, 12171, 14641, 14642,
		6188, 10954, 10956, 10958,
		3057,
		5512, 5509, 5514, 5510, //rc pouches
		14076, 14077, 14081,
		9925, 9924, 9923, 9922, 9921,
		4565,
		14595, 14603, 14602, 14605, 	11789,
		19708, 19706, 19707,
		4860, 4866, 4872, 4878, 4884, 4896, 4890, 4896, 4902,
		4932, 4938, 4944, 4950, 4908, 4914, 4920, 4926, 4956,
		4926, 4968, 4994, 4980, 4986, 4992, 4998,
		18778, 18779, 18780, 18781,
		13450, 13444, 13405, 15502, 
		10548, 10549, 10550, 10555, 10552, 10553,
		20747, 
		18365, 18373, 18371, 12964, 12971, 12978, 14017,
		8851,
		13855, 13848, 13849, 13857, 13856, 13854, 13853, 13852, 13851, 13850, 5509, 13653, 14020, 19111, 14019, 14022,
		19785, 19786, 18782, 18351, 18349, 18353, 18357, 18355, 18359, 18335, 11977, 11978, 11979, 11980, 11981, 11982, 11983, 11984, 11985, 11986, 11987, 11988, 11990, 11991, 11992, 11993, 11994, 11995, 11996, 11997, 11999, 12001, 12002, 12003, 12004, 12005, 15103, 15104, 15106, 
		15105,
		13613, 13619, 13622, 13623, 13616, 13614, 13617, 13618, 13626, 13624, 13627, 13628, //runecrafting shit //member cape
		22033, 22049, 22050, //zulrah pets
				//begin (deg) ancient armour
		13898, 13886, 13892, 13904,
		13889, 13895, 13901, 13907,
		13866, 13860, 13863, 13869,
		13878, 13872, 13875
	};
	/**
	 * Unsellable items
	 * Items which cannot be sold to shops
	 */
	public static int UNSELLABLE_ITEMS[] = new int[] {
		18349, 18351, 773, 18353, 995, 18349, 18351, 12425, 18353, 13262, 19634, 19635, 19642, 19643, 19644, 19711, 19712, 19785, 19786, 19787, 19788, 19789, 19790, 19803, 19804, 6570, 14019, 20747, 19785, 19786, 19787, 19788, 19789, 19790, 19803, 19804, 8839, 8840, 8841, 8842, 19711, 19712
	};

	public static final String[] INVALID_NAMES = { "mod", "moderator", "admin", "administrator", "owner", "developer",
			"supporter", "dev", "developer", "nigga", "0wn3r", "4dm1n", "m0d", "adm1n", "a d m i n", "m o d",
			"o w n e r" };

	public static final int 
	ATTACK_TAB = 0, 
	SKILLS_TAB = 1, 
	QUESTS_TAB = 2, 
	ACHIEVEMENT_TAB = 14,
	INVENTORY_TAB = 3, 
	EQUIPMENT_TAB = 4, 
	PRAYER_TAB = 5, 
	MAGIC_TAB = 6,

	SUMMONING_TAB = 13, 
	FRIEND_TAB = 8, 
	IGNORE_TAB = 9, 
	CLAN_CHAT_TAB = 7,
	LOGOUT = 10,
	OPTIONS_TAB = 11,
	EMOTES_TAB = 12;
	
	
	public static final String API_KEY = "lJNdfgLyOdpMI1o0FKIV7v8jDkjLmGgGQUaCXDZz9He5XlZ4TXyaRqzBF3nIxJecEpZpViYW";

	public static final String DONATION_KEY = "722706207021727786";
	
	public static final String EVENT_KEY = "722710231213146135";
	
	public static final String VOTE_KEY = "722709722305921074";

	public static final String RARE_LOG = "722547519825117232";
	
	public static final String INGAME_LOG = "722547520470909091";

	public static boolean betaMode = false;
}

