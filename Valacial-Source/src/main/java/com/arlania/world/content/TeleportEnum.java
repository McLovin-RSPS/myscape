package com.arlania.world.content;

import com.arlania.model.Position;
import com.arlania.model.Item;
import com.arlania.model.Position;

import java.util.ArrayList;

public enum TeleportEnum {
    //id 1 is zones
    //id 2 is bosses
    //id 3 is minigames
    //id 4 is global
    //id 5 is ultra

    //zones
    YODA_YODA(1265, 1, "BOMBY'S Minions", new Position(2660, 3045, 0), "tier: 1", "Melee", "All styles"),
    PAC(2437, 1, "Donut Homer", new Position(1827, 4511, 0), "tier: 2", "Melee", "All styles"),
    ALIEN(1677, 1, "Alien", new Position(3561, 9948,0), "tier: 3", "Melee", "All styles"),
    TEEMO(1880, 1, "Bowser", new Position(3169, 2982, 0), "tier: 4", "Melee", "All styles"),
    TOXIC(1459, 1, "Mewtwo", new Position(2793, 2773, 0), "tier: 5", "Melee", "All styles"),
    MARIO(6102, 1, "Donkey Kong", new Position(1747, 5323, 0), "tier: 6", "Melee", "All styles"),
    PIKA(4392, 1, "Bandicoot", new Position(2704, 9756, 0), "tier: 7", "Melee", "All styles"),
    RICK(9786, 1, "Sonic & Knuckles", new Position(2089, 4455, 0), "tier: 8", "Melee", "All styles"),
    AKU(7843, 1, "Spyro", new Position(3860, 2831, 0), "tier: 9", "Melee", "All styles"),
    LUNAR(9236, 1, "Sephiroth", new Position(3127, 4373, 0), "tier: 10", "Melee", "All styles"),
    VOLDER(7643, 1, "Voldemort", new Position(1928, 5002, 0), "tier: 11", "Melee", "All styles"),

    //bosses
    ABBADON(6303, 2, "Abbadon", new Position(2516, 5173, 0), "tier: 12", "Melee", "All styles"),
    Link(7567, 2, "Link", new Position(1240, 1233, 0), "tier: 13", "Melee", "All styles"),
    BAPHOMET(2236, 2, "Baphomet", new Position(2461, 10156, 0), "tier: 14", "Melee", "All styles"),
    Uchiha(7532, 2, "Madara Uchiha", new Position(2892, 2731, 0), "tier: 15", "Melee", "All styles"),
    Assassin(1999, 2, "Assassin", new Position(2596, 2588, 0), "tier: 16", "Melee", "All styles"),
    Dead_Bone(9201, 2, "Dead Bone Guardian", new Position(2076, 4830, 0), "tier: 17", "Melee", "All styles"),
    Blood_lord(9325, 2, "Blood Lord", new Position(3049, 2905, 0), "tier: 18", "Melee", "All styles"),
    Samurai(7563, 2, "Samurai", new Position(1891, 4513, 0), "tier: 19", "Melee", "All styles"),
    thanos(9202, 2, "Thanos", new Position(1632, 4702, 0), "tier: 20", "Melee", "All styles"),
    King_storm(8133, 2, "King Storm Breaker", new Position(3281, 4311, 0), "tier: 21", "Melee", "All styles"),
    Barrelchest(5666, 2, "Barrelchest", new Position(2975, 9515, 1), "tier: 22", "Melee", "All styles"),
    Chaos_ele(3200, 2, "Chaos Elemental", new Position(2463, 4782, 0), "tier: 23", "Melee", "All styles"),
    Devil_Beast(6442, 2, "Devil Beast", new Position(1752, 5224, 0), "tier: 24", "Melee", "All styles"),
    Blest_God(8754, 2, "Blessed God", new Position(3038, 5346, 0), "tier: 25", "Melee", "All styles"),
    RoseEater(6754, 2, "RoseEater", new Position(3282, 9836, 0), "tier: 26", "Melee", "All styles"),
//    EternalMelee(8, 2, "Eternal Dragon(Melee)", new Position(3282, 9836, 0), "tier: 26.5", "Magic", "All styles"),
//    EternalRanged(11, 2, "Eternal Dragon(Range)", new Position(3282, 9836, 0), "tier: 26.5", "Magic", "All styles"),
//    EternalMagic(10, 2, "Eternal Dragon(Magic)", new Position(3282, 9836, 0), "tier: 26.5", "Magic", "All styles"),


  //Minigames
    PZONE(1265, 3, "Progression Island", new Position(3795, 3543,0), "tier: 0", "Melee", "All styles"),
    PIRATE(8239, 3, "Pirate Zone", new Position(3808, 3479,0), "tier: 0", "Melee", "All styles"),
    AFK(8002, 3, "Afking Spot", new Position(3694, 2967,0), "tier: 0", "Melee", "All styles"),
    COSMETIC(8733, 3, "Cosmetic Zone", new Position(2969, 2579,0), "tier: 0", "Melee", "All styles"),
    DEADRAIDS(8548, 3, "Deadly Raid", new Position(3666, 2976,0), "tier: 0", "Melee", "All styles"),

    //global
    GOLD_KBD(50, 4, "Undead Dragon", new Position(3057, 5198, 0), "tier: 10", "Melee", "All styles"),
    MUSKETIR(7552, 4, "Infinito", new Position(2792, 3791, 0), "tier: 10", "Melee", "All styles"),
    DEATH(8453, 4, "Beelzebub", new Position(3027, 5234, 0), "tier: 10", "Mage", "All styles"),
    VORAGO(8766, 4, "Icy Vorago", new Position(2863, 3723, 0), "tier: 10", "Melee", "All styles"),
    VOTE(6305, 4, "Demonic Mystery", new Position(3702, 2971,0), "tier: 0", "Melee", "All styles"),
    AFKBOSS(3779, 4, "Deadpool", new Position(2531, 4072,0), "tier: 0", "Melee", "All styles"),
    Mew(7953, 4, "Mew", new Position(2531, 4072,0), "tier: 0", "Melee", "All styles"),
    DONATORBOSS(3782, 4, "Ainz Ooal Gown", new Position(2512, 3993,0), "tier: 0", "Melee", "All styles"),

    //ultra
    JOKER(2518, 5, "JOKER(Range)", new Position(2139, 5100, 0), "tier: 27", "Melee", "All styles"),
    DARTHVAIDER(8721, 5, "Darth Vader(Mage)", new Position(1886, 5462, 0), "tier: 28", "Melee", "All styles"),
    LUGIA(6432, 5, "Cloud Strife(Melee)", new Position(2036, 4497, 0), "tier: 29", "Melee", "All styles"),
    BROlY(9200, 5, "Sora(Melee)", new Position(2402, 3488, 0), "tier: 30", "Melee", "All styles"),
    MYSTERIO(7535, 5, "Mysterio(Range)", new Position(2721, 4897, 0), "tier: 31", "Melee", "All styles"),
    WHITE_BEARD(9003, 5, "Whitebeard(Mage)", new Position(2449, 2848, 0), "tier: 32", "Melee", "All styles"),
    PYRAMID_HEAD(9003, 5, "Pyramid Head(Melee)", new Position(2449, 2848, 0), "tier: 32", "Melee", "All styles"),
    OPTIMUS_PRIME(9003, 5, "Optimus Prime(Ranged)", new Position(2449, 2848, 0), "tier: 33", "Melee", "All styles"),
    DARK_MAGICIAN(9003, 5, "Dark Magician(Mage)", new Position(2449, 2848, 0), "tier: 34", "Melee", "All styles"),
    DOOMSDAY(9003, 5, "Doomsday(Melee)", new Position(2449, 2848, 0), "tier: 35", "Melee", "All styles"),
    SASUKE(9003, 5, "Sasuke(Ranged)", new Position(2449, 2848, 0), "tier: 36", "Melee", "All styles"),
    BROLY(9003, 5, "Broly(Mage)", new Position(2449, 2848, 0), "tier: 37", "Melee", "All styles");
	//MAGMA(10141, 5, "Magma Satanic Devil(Melee)", new Position(2529, 5850, 0), "tier: 33", "Melee", "All styles");









    private TeleportEnum(int npcId, int id, String teleportName, Position position, String difficulty){
        this.npcId = npcId;
        this.id = id;
        this.teleportName = teleportName;
        this.position = position;
        this.difficulty = difficulty;
}

    private TeleportEnum(int npcId, int id, String teleportName, Position position, String difficulty, String attackWith, String weakness){
        this.npcId = npcId;
        this.id = id;
        this.teleportName = teleportName;
        this.position = position;
        this.difficulty = difficulty;
        this.attackWith = attackWith;
        this.weakness = weakness;
}

    private String attackWith;
    private String weakness;
    private int npcId;
    private String teleportName;
    private Position position;
    private int id;
    private String difficulty;
    private Item[] drops;

    public static ArrayList<TeleportEnum> dataByTier(int tier){
        ArrayList<TeleportEnum> teleports = new ArrayList<>();
        for(TeleportEnum data : TeleportEnum.values()) {
            if(data.id == tier){
                teleports.add(data);
            }
        }
        return teleports;
    }


    public Item[] getDrops() {
        return drops;
    }

    public String getAttackWith() {
        return attackWith;
    }

    public String getWeakness() {
        return weakness;
    }

    public int getNpcId() {
        return npcId;
    }

    public int getId() {
        return id;
    }

    public Position getPosition() {
        return position;
    }

    public String getTeleportName() {
        return teleportName;
    }

    public String getDifficulty() {
        return difficulty;
    }
}
