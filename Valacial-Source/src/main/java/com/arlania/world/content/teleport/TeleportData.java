package com.arlania.world.content.teleport;

import com.arlania.model.Item;
import com.arlania.model.Position;
import com.arlania.model.definitions.NPCDrops;
import org.apache.commons.lang3.text.WordUtils;

public enum TeleportData {

//    GIANT_MOLE(TeleportCategory.GLOBAL, new Position(1761, 5186, 0), 3340, 2000, new Item(5073), new Item(20558, 100)),
//    CORPOREAL_BEAST(TeleportCategory.SKILLING, new Position(3197, 3673, 0), itemsFromIds(13734, 13754, 13752, 13746, 13750, 13748, 12001)),


	YODA_YODA("BOMBY'S Minions", TeleportCategory.MONSTERS, new Position(2660, 3045, 0), 1265),
	PAC("Donut Homer", TeleportCategory.MONSTERS, new Position(1827, 4511, 0), 2437),
	ALIEN("Alien", TeleportCategory.MONSTERS, new Position(3561, 9948,0), 1677),
	TEEMO("Bowser", TeleportCategory.MONSTERS, new Position(3169, 2982, 0), 1880),
	TOXIC("Mewtwo", TeleportCategory.MONSTERS, new Position(2793, 2773, 0), 1459),
	MARIO("Donkey Kong", TeleportCategory.MONSTERS, new Position(1747, 5323, 0), 6102),
	PIKA("Bandicoot", TeleportCategory.MONSTERS, new Position(2704, 9756, 0), 4392),
	RICK("Sonic & Knuckles", TeleportCategory.MONSTERS, new Position(2089, 4455, 0), 9786),
	AKU("Spyro", TeleportCategory.MONSTERS, new Position(3860, 2831, 0), 7843),
	LUNAR("Sephiroth", TeleportCategory.MONSTERS, new Position(3127, 4373, 0), 9236),
	VOLDER("Voldemort", TeleportCategory.MONSTERS, new Position(1928, 5002, 0), 7643),

	//bosses
	ABBADON("Abbadon", TeleportCategory.BOSSES, new Position(2516, 5173, 0), 6303),
	Link("Link", TeleportCategory.BOSSES, new Position(1240, 1233, 0), 7567),
	BAPHOMET("Baphomet", TeleportCategory.BOSSES, new Position(2461, 10156, 0), 2236),
	Uchiha("Madara Uchiha", TeleportCategory.BOSSES, new Position(2892, 2731, 0), 7532),
	Assassin("Assassin", TeleportCategory.BOSSES, new Position(2596, 2588, 0), 1999),
	Dead_Bone("Dead Bone Guardian", TeleportCategory.BOSSES, new Position(2076, 4830, 0), 9201),
	Blood_lord("Blood Lord", TeleportCategory.BOSSES, new Position(3049, 2905, 0), 9325),
	Samurai("Samurai", TeleportCategory.BOSSES, new Position(1891, 4513, 0), 7563),
	thanos("Thanos", TeleportCategory.BOSSES, new Position(1632, 4702, 0), 9202),
	King_storm("King Storm Breaker", TeleportCategory.BOSSES, new Position(3281, 4311, 0), 8133),
	Barrelchest("Barrelchest", TeleportCategory.BOSSES, new Position(2975, 9515, 1), 5666),
	Chaos_ele("Chaos Elemental", TeleportCategory.BOSSES, new Position(2463, 4782, 0), 3200),
	Devil_Beast("Devil Beast", TeleportCategory.BOSSES, new Position(1752, 5224, 0), 6442),
	Blest_God("Blessed God", TeleportCategory.BOSSES, new Position(3038, 5346, 0), 8754),
	RoseEater("RoseEater", TeleportCategory.BOSSES, new Position(3282, 9836, 0), 6754),
	EternalMelee("Eternal Dragon(Melee)", TeleportCategory.BOSSES, new Position(3282, 9836, 0), 8),
	EternalRanged("Eternal Dragon(Range)", TeleportCategory.BOSSES, new Position(3282, 9836, 0), 11),
	EternalMagic("Eternal Dragon(Magic)", TeleportCategory.BOSSES, new Position(3282, 9836, 0), 10),


	//Minigames
	PZONE("Progression Island", TeleportCategory.MINIGAMES, new Position(3795, 3543,0), 1265),
	PIRATE("Pirate Zone", TeleportCategory.MINIGAMES, new Position(3808, 3479,0), 8239),
	AFK("Afking Spot", TeleportCategory.MINIGAMES, new Position(3694, 2967,0), 8002),
	COSMETIC("Cosmetic Zone", TeleportCategory.MINIGAMES, new Position(2969, 2579,0), 8733),
	DEADRAIDS("Deadly Raid", TeleportCategory.MINIGAMES, new Position(3666, 2976,0), 8548),

	//global
	VOTE("Demonic Mystery", TeleportCategory.GLOBAL, new Position(3702, 2971,0), 6305),
//	AFKBOSS("Deadpool", TeleportCategory.GLOBAL, new Position(3702, 2971,0), 3779),
//	Mew("Mew", TeleportCategory.GLOBAL, new Position(3702, 2971,0), 7953),
//	DONATORBOSS("Ainz Ooal Gown", TeleportCategory.GLOBAL, new Position(2512, 3993,0), 3782),
	GOLD_KBD("Undead Dragon", TeleportCategory.GLOBAL, new Position(3057, 5198, 0), 50),
	MUSKETIR("Infinito", TeleportCategory.GLOBAL, new Position(2792, 3791, 0), 7552),
	DEATH("Beelzebub", TeleportCategory.GLOBAL, new Position(3027, 5234, 0), 8453),
	VORAGO("Icy Vorago", TeleportCategory.GLOBAL, new Position(2863, 3723, 0), 8766),

	//ultra
	JOKER("JOKER(Range)", TeleportCategory.ULTRA_BOSSES, new Position(2139, 5100, 0), 2518),
	DARTHVAIDER("Darth Vader(Mage)", TeleportCategory.ULTRA_BOSSES, new Position(1886, 5462, 0), 8721),
	LUGIA("Cloud Strife(Melee)", TeleportCategory.ULTRA_BOSSES, new Position(2036, 4497, 0), 6432),
	BROlY("Sora(Melee)", TeleportCategory.ULTRA_BOSSES, new Position(2402, 3488, 0), 9200),
	MYSTERIO("Mysterio(Range)", TeleportCategory.ULTRA_BOSSES, new Position(2721, 4897, 0), 7535),
	WHITE_BEARD("Whitebeard(Mage)", TeleportCategory.ULTRA_BOSSES, new Position(2449, 2848, 0), 9003),
//	PYRAMID_HEAD("Pyramid Head(Melee)", TeleportCategory.ULTRA_BOSSES, new Position(2449, 2848, 0), 9003),
//	OPTIMUS_PRIME("Optimus Prime(Ranged)", TeleportCategory.ULTRA_BOSSES, new Position(2449, 2848, 0), 9003),
//	DARK_MAGICIAN("Dark Magician(Mage)", TeleportCategory.ULTRA_BOSSES, new Position(2449, 2848, 0), 9003),
//	DOOMSDAY("Doomsday(Melee)", TeleportCategory.ULTRA_BOSSES, new Position(2449, 2848, 0), 9003),
//	SASUKE("Sasuke(Ranged)", TeleportCategory.ULTRA_BOSSES, new Position(2449, 2848, 0), 9003),
//	BROLY("Broly(Mage)", TeleportCategory.ULTRA_BOSSES, new Position(2449, 2848, 0), 9003),
////	MAGMA("Magma Satanic Devil(Melee)", TeleportCategory.ULTRA_BOSSES, new Position(2529, 5850, 0), 10141),
    ;

    public final static TeleportData[] values = TeleportData.values();

    private final String name;
    private final TeleportCategory category;
    private final int npcId;
    private final int npcZoom;
    private final Position position;
	private Item[] items;

	TeleportData(String name, TeleportCategory category, Position position, int npcId, int npcZoom) {
        this.name = name == null ? WordUtils.capitalize(this.name().toLowerCase().replaceAll("_", " ")) : name;
        this.category = category;
        this.npcId = npcId;
        this.npcZoom = npcZoom;
        this.position = position;
    }

    TeleportData(String name, TeleportCategory category, Position position) {
        this(name, category, position, -1, 1400);
    }

    TeleportData(TeleportCategory category, Position position) {
        this(null, category, position);
    }

    TeleportData(TeleportCategory category, Position position, int npcId) {
        this(null, category, position, npcId, 1400);
    }

	TeleportData(String name, TeleportCategory category, Position position, int npcId) {
		this(name, category, position, npcId, 1400);
	}

    TeleportData(TeleportCategory category, Position position, int npcId, int npcZoom) {
        this(null, category, position, npcId, npcZoom);
    }

	public static void main(String[] args) {
		for(TeleportData data : values) {
			System.out.println("list.add(" + data.getPosition().getRegionId() + ");");
		}
	}

	public static void loadDrops()
	{
		for (TeleportData data : values) {
			NPCDrops npcDrops = NPCDrops.forId(data.npcId);
			if (npcDrops == null)
				continue;
			NPCDrops.NpcDropItem[] dropItems = npcDrops.getDropList();
			if (dropItems == null || dropItems.length == 0)
				continue;
			Item[] items = new Item[dropItems.length];
			for (int i = 0; i < dropItems.length; i++) {
				items[i] = new Item(dropItems[i].getId());
			}
			data.items = items;
		}
	}

	public Position getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public TeleportCategory getCategory() {
        return category;
    }

    public int getNpcId() {
        return npcId;
    }

    public int getNpcZoom() {
        return npcZoom;
    }

    public Item[] getItems() {
        return items;
    }

    private static Item[] itemsFromIds(int... ids) {
        if (ids.length == 0)
            return null;
        Item[] items = new Item[ids.length];
        for (int i = 0; i < ids.length; i++) {
            items[i] = new Item(ids[i]);
        }
        return items;
    }
}