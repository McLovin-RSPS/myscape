package com.arlania.world.content.achievements;

import com.arlania.model.Item;
import com.arlania.model.Skill;
import org.apache.commons.lang3.text.WordUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum AchievementData {
    ENTER_THE_DISCORD(0, AchievementType.EASY, 1, "Join our discord. Type ::Discord",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    FILL_WELL_OF_GOODWILL_1M(1, AchievementType.EASY, 10, "Pour 1Q Into The Well.",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    CUT_AN_OAK_TREE(3, AchievementType.EASY, 1, "Cut An Oak Tree",
            new Item[] {new Item(5022, 100)},
            new ExpReward(Skill.WOODCUTTING, 10000),
            new BossPointReward(10)),
    BURN_AN_OAK_LOG(4, AchievementType.EASY, 1, "Burn An Oak Log",
            new Item[] {new Item(5022, 100)},
            new ExpReward(Skill.FIREMAKING, 10000),
            new BossPointReward(10)),
    FISH_A_SALMON(5, AchievementType.EASY, 1, "Fish A Salmon",
            new Item[] {new Item(5022, 100)},
            new ExpReward(Skill.FISHING, 10000),
            new BossPointReward(10)),
    COOK_A_SALMON(6, AchievementType.EASY, 1, "Cook A Salmon",
            new Item[] {new Item(5022, 100)},
            new ExpReward(Skill.COOKING, 100000),
            new BossPointReward(10)),
    EAT_A_ROCKTAIL(7, AchievementType.EASY, 1, "Eat A Rocktail",
            new Item[] {new Item(5022, 100)},
            new ExpReward(Skill.CONSTITUTION, 10000),
            new BossPointReward(10)),
    MINE_SOME_IRON(8, AchievementType.EASY, 1, "Mine Some Iron",
            new Item[] {new Item(5022, 100)},
            new ExpReward(Skill.MINING, 10000),
            new BossPointReward(10)),
    SMELT_AN_IRON_BAR(9, AchievementType.EASY, 1, "Smelt An Iron Bar",
            new Item[] {new Item(5022, 100)},
            new ExpReward(Skill.SMITHING, 10000),
            new BossPointReward(10)),
    HARVEST_A_CROP(10, AchievementType.EASY, 1, "Harvest A Crop",
            new Item[] {new Item(5022, 100)},
            new ExpReward(Skill.FARMING, 10000),
            new BossPointReward(10)),
    DIG_FOR_ARCHAEOLOGY(11, AchievementType.EASY, 1, "Dig once.",
            new Item[] {new Item(5022, 100)},
            new ExpReward(Skill.ARCHAEOLOGY, 10000),
            new BossPointReward(10)),
    CRAFT_A_PAIR_OF_LEATHER_BOOTS(12, AchievementType.EASY, 1, "Craft A Pair of Leather Boots",
            new Item[] {new Item(5022, 100)},
            new ExpReward(Skill.CRAFTING, 10000),
            new BossPointReward(10)),
    CLIMB_AN_AGILITY_OBSTACLE(13, AchievementType.EASY, 1, "Climb An Agility Obstacle",
            new Item[] {new Item(5022, 100)},
            new ExpReward(Skill.AGILITY, 10000),
            new BossPointReward(10)),
    FLETCH_SOME_ARROWS(14, AchievementType.EASY, 1, "Fletch Some Arrows",
            new Item[] {new Item(5022, 100)},
            new ExpReward(Skill.FLETCHING, 10000),
            new BossPointReward(10)),
    STEAL_A_RING(15, AchievementType.EASY, 1, "Steal A Ring",
            new Item[] {new Item(5022, 100)},
            new ExpReward(Skill.THIEVING, 10000),
            new BossPointReward(10)),
    MIX_A_POTION(16, AchievementType.EASY, 1, "Mix A Potion",
            new Item[] {new Item(5022, 100)},
            new ExpReward(Skill.HERBLORE, 10000),
            new BossPointReward(10)),
    USE_DIVINATION_SKILL(17, AchievementType.EASY, 1, "Use Divination Skill",
            new Item[] {new Item(5022, 100)},
            new ExpReward(Skill.DIVINATION, 100000),
            new BossPointReward(10)),
    BURY_A_BIG_BONE(18, AchievementType.EASY, 1, "Bury A Big Bone",
            new Item[] {new Item(5022, 100)},
            new ExpReward(Skill.PRAYER, 10000),
            new BossPointReward(10)),
    COMPLETE_A_SLAYER_TASK(19, AchievementType.EASY, 1, "Complete A Slayer Task",
            new Item[] {new Item(5022, 100)},
            new ExpReward(Skill.SLAYER, 10000),
            new BossPointReward(10)),
    KILL_A_MONSTER_USING_MELEE(20, AchievementType.EASY, 1, "Kill a Monster Using Melee",
            new Item[] {new Item(5022, 100)},
            new ExpReward(Skill.STRENGTH, 10000),
            new BossPointReward(10)),
    KILL_A_MONSTER_USING_RANGED(21, AchievementType.EASY, 1, "Kill a Monster Using Ranged",
            new Item[] {new Item(5022, 100)},
            new ExpReward(Skill.RANGED, 10000),
            new BossPointReward(10)),
    KILL_A_MONSTER_USING_MAGIC(22, AchievementType.EASY, 1, "Kill a Monster Using Magic",
            new Item[] {new Item(5022, 100)},
            new ExpReward(Skill.MAGIC, 10000),
            new BossPointReward(10)),
    DEAL_EASY_DAMAGE_USING_MELEE(23, AchievementType.EASY, 1, "Deal 1000 Melee Damage",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    DEAL_EASY_DAMAGE_USING_RANGED(24, AchievementType.EASY, 1, "Deal 1000 Ranged Damage",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    DEAL_EASY_DAMAGE_USING_MAGIC(25, AchievementType.EASY, 1, "Deal 1000 Magic Damage",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    PERFORM_A_SPECIAL_ATTACK(26, AchievementType.EASY, 1, "Perform a Special Attack",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),

    //Medium
    FILL_WELL_OF_GOODWILL_50M(27, AchievementType.MEDIUM, 1, "Pour 50Q Into The Well",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    CUT_100_MAGIC_LOGS(28, AchievementType.MEDIUM, 100, "Cut 100 Magic Logs",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    BURN_100_MAGIC_LOGS(29, AchievementType.MEDIUM, 100, "Burn 100 Magic Logs",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    FISH_25_ROCKTAILS(30, AchievementType.MEDIUM, 25, "Fish 25 Rocktails",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    COOK_25_ROCKTAILS(31, AchievementType.MEDIUM, 25, "Cook 25 Rocktails",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    MINE_25_RUNITE_ORES(32, AchievementType.MEDIUM, 25, "Mine 25 Runite Ores",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    SMELT_25_RUNE_BARS(33, AchievementType.MEDIUM, 25, "Smelt 25 Rune Bars",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    HARVEST_10_TORSTOLS(34, AchievementType.MEDIUM, 10, "Harvest 10 Torstols",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    CATCH_5_KINGLY_IMPLINGS(35, AchievementType.MEDIUM, 5, "Catch 5 Kingly Implings",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    COMPLETE_A_HARD_SLAYER_TASK(36, AchievementType.MEDIUM, 1, "Complete A Hard Slayer Task",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    CRAFT_20_BLACK_DHIDE_BODIES(37, AchievementType.MEDIUM, 20, "Craft 20 Black D'hide Bodies",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    FLETCH_450_RUNE_ARROWS(38, AchievementType.MEDIUM, 450, "Fletch 450 Rune Arrows",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    STEAL_140_SCIMITARS(39, AchievementType.MEDIUM, 140, "Steal 140 Scimitars",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    MIX_AN_OVERLOAD_POTION(40, AchievementType.MEDIUM, 1, "Mix An Overload Potion",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    CLIMB_50_AGILITY_OBSTACLES(41, AchievementType.MEDIUM, 50, "Climb 50 Agility Obstacles",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    RUNECRAFT_500_BLOOD_RUNES(42, AchievementType.MEDIUM, 500, "Runecraft 500 Blood Runes",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    DEAL_MEDIUM_DAMAGE_USING_MELEE(43, AchievementType.MEDIUM, 100000, "Deal 100K Melee Damage",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    DEAL_MEDIUM_DAMAGE_USING_RANGED(44, AchievementType.MEDIUM, 100000, "Deal 100K Ranged Damage",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    DEAL_MEDIUM_DAMAGE_USING_MAGIC(45, AchievementType.MEDIUM, 100000, "Deal 100K Magic Damage",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    DEFEAT_THE_JOKER(46, AchievementType.MEDIUM, 1, "Defeat The Joker",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    DEFEAT_DARTH_VADER(47, AchievementType.MEDIUM, 1, "Defeat Darth Vader",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    DEFEAT_CLOUD_STRIFE(48, AchievementType.MEDIUM, 1, "Defeat Cloud Strife",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    DEFEAT_SORA(1000, AchievementType.MEDIUM, 1, "Defeat Sora", //id is 1000
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    DEFEAT_MYSTERIO(49, AchievementType.MEDIUM, 1, "Defeat Mysterio",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    DEFEAT_WHITEBEARD(50, AchievementType.MEDIUM, 1, "Defeat White Beard",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
//hard
FILL_WELL_OF_GOODWILL_250M(51, AchievementType.HARD, 250, "Pour 250Q Into The Well",
        new Item[] {new Item(5022, 100)},
        new BossPointReward(10)),
    CUT_5000_MAGIC_LOGS(52, AchievementType.HARD, 5000, "Cut 5000 Magic Logs",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    BURN_2500_MAGIC_LOGS(53, AchievementType.HARD, 2500, "Burn 2500 Magic Logs",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    FISH_2000_ROCKTAILS(54, AchievementType.HARD, 2000, "Fish 2000 Rocktails",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    COOK_1000_ROCKTAILS(55, AchievementType.HARD, 1000, "Cook 1000 Rocktails",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    MINE_2000_RUNITE_ORES(56, AchievementType.HARD, 2000, "Mine 2000 Runite Ores",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    SMELT_1000_RUNE_BARS(57, AchievementType.HARD, 1000, "Smelt 1000 Rune Bars",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    HARVEST_1000_TORSTOLS(58, AchievementType.HARD, 1000, "Harvest 1000 Torstols",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    CATCH_100_KINGLY_IMPLINGS(59, AchievementType.HARD, 100, "Catch 100 Kingly Implings",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    FLETCH_5000_RUNE_ARROWS(60, AchievementType.HARD, 5000, "Fletch 5000 Rune Arrows",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    STEAL_5000_SCIMITARS(61, AchievementType.HARD, 5000, "Steal 5000 Scimitars",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    DIVINATION_8000(62, AchievementType.HARD, 8000, "Collect 8,000 Elder energy.",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    MIX_100_OVERLOAD_POTIONS(63, AchievementType.HARD, 100, "Mix 100 Overlord Potions",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    COMPLETE_AN_ELITE_SLAYER_TASK(64, AchievementType.HARD, 1, "Complete Elite Slayer Task",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    DEAL_HARD_DAMAGE_USING_MELEE(65, AchievementType.HARD, 100_000, "Deal 100K Melee Damage",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    DEAL_HARD_DAMAGE_USING_RANGED(66, AchievementType.HARD, 100_000, "Deal 100K Melee Damage",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    DEAL_HARD_DAMAGE_USING_MAGIC(67, AchievementType.HARD, 100_000, "Deal 100K Melee Damage",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    DEFEAT_10000_JOKERS(68, AchievementType.HARD, 10_000, "Defeat 10,000 Jokers",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    DEFEAT_10000_DARTH_VADERS(69, AchievementType.HARD, 10_000, "Defeat 10,000 Darth Vader",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    DEFEAT_10000_CLOUD_STRIFE(70, AchievementType.HARD, 10_000, "Defeat 10,000 Cloud Strife",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    DEFEAT_10000_SORA(71, AchievementType.HARD, 10_000, "Defeat 10,000 Sora",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    DEFEAT_10000_MYSTERIO(72, AchievementType.HARD, 10_000, "Defeat 10,000 Mysterio",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    DEFEAT_10000_WHITEBEARD(73, AchievementType.HARD, 10_000, "Defeat 10,000 White Beard",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
//daily

    VOTE(74, AchievementType.DAILY, 1, "Vote today",
            new Item[] {new Item(19670, 3)},
            new BossPointReward(10)),
    KILL_1000_BOSSES(75, AchievementType.DAILY, 1, "Kill 1,000 Bosses",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
   OPEN_10_BOXES(76, AchievementType.DAILY, 1, "Open 10x of any Mystery Box",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    COMPLETE_10_SLAYER(77, AchievementType.DAILY, 1, "Complete 10 Slayer Tasks",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    KILL_VOTEBOSS(78, AchievementType.DAILY, 1, "Kill Demonic Mystery [::voteboss]",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    KILL_DONATORBOSS(79, AchievementType.DAILY, 1, "Kill Ainz Ooal Gown [::donatorboss]",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    KILL_AFKBOSS(80, AchievementType.DAILY, 1, "Kill Deadpool [::afkboss] ",
            new Item[] {new Item(5022, 100)},
            new BossPointReward(10)),
    MINE_10000_TICKETS(81, AchievementType.DAILY, 1, "Mine 10,000 afk rocks [::afk] ",
            new Item[] {new Item(693, 10_000)},
            new BossPointReward(10)),


    ;

    public static final AchievementData[] values = AchievementData.values();
    public static final AchievementData[][] arraysByType = new AchievementData[AchievementType.values().length][];

    public static void checkDuplicateIds() {
        Set<Integer> ids = new HashSet<>();
        for (AchievementData achievement : values) {
            if (ids.contains(achievement.id)) {
                System.err.println("AchievementData sharing the same id!!! Shutting Down. Each achievement must have a unique id.");
                for (AchievementData data : values) {
                    if (data.id == achievement.id) {
                        System.out.println(data.name() + " id: " + data.id);
                    }
                }
                System.exit(0);
            }
            ids.add(achievement.id);
        }
    }

    final int id;
    final AchievementType type;
    final String description;
    final int progressAmount;
    final Item[] itemRewards;
    final NonItemReward[] nonItemRewards;

    AchievementData(int id, AchievementType type, int progressAmount, String description, Item[] itemRewards, NonItemReward... nonItemRewards) {
        this.id = id;
        this.type = type;
        this.progressAmount = progressAmount;
        this.description = description;
        this.itemRewards = itemRewards;
        this.nonItemRewards = nonItemRewards;
    }

    @Override
    public String toString() {
        return WordUtils.capitalize(this.name().toLowerCase().replaceAll("_", " "));
    }

    public static AchievementData[] getAchievementsOfType(AchievementType type){
        int index = type.ordinal();
        if (arraysByType[index] != null) {
            return arraysByType[index];
        }
        arraysByType[index] = Arrays.stream(AchievementData.values).filter(a -> a.type.equals(type)).toArray(AchievementData[]::new);
        return arraysByType[index];
    }
}
