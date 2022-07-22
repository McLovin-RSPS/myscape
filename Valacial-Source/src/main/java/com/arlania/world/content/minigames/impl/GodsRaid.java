package com.arlania.world.content.minigames.impl;

import com.arlania.model.GameObject;
import com.arlania.model.Graphic;
import com.arlania.model.Item;
import com.arlania.model.Locations.Location;
import com.arlania.model.Position;
import com.arlania.model.Skill;
import com.arlania.util.Misc;
import com.arlania.util.RandomUtility;
import com.arlania.world.World;
import com.arlania.world.content.skill.impl.dungeoneering.Dungeoneering;
import com.arlania.world.content.skill.impl.construction.Construction;
import com.arlania.world.content.skill.impl.dungeoneering.ItemBinding;
import com.arlania.world.content.skill.impl.herblore.Herblore;
import com.arlania.world.content.skill.impl.herblore.IngridientsBook;
import com.arlania.world.content.skill.impl.hunter.BoxTrap;
import com.arlania.world.content.skill.impl.hunter.Hunter;
import com.arlania.world.content.skill.impl.hunter.JarData;
import com.arlania.world.content.skill.impl.hunter.PuroPuro;
import com.arlania.world.content.skill.impl.hunter.SnareTrap;
import com.arlania.world.content.skill.impl.hunter.Trap.TrapState;
import com.arlania.world.content.skill.impl.prayer.Prayer;
import com.arlania.world.content.skill.impl.runecrafting.Runecrafting;
import com.arlania.world.content.skill.impl.runecrafting.RunecraftingPouches;
import com.arlania.world.content.skill.impl.runecrafting.RunecraftingPouches.RunecraftingPouch;
import com.arlania.world.content.skill.impl.slayer.SlayerDialogues;
import com.arlania.world.content.skill.impl.slayer.SlayerTasks;
import com.arlania.world.content.skill.impl.summoning.CharmingImp;
import com.arlania.world.content.skill.impl.summoning.SummoningData;
import com.arlania.world.content.skill.impl.woodcutting.BirdNests;
import com.arlania.world.content.CustomObjects;
import com.arlania.world.content.PlayerPanel;
import com.arlania.world.entity.impl.npc.NPC;
import com.arlania.world.entity.impl.npc.NPC;

public class GodsRaid extends Raid {

    public static boolean started;

    public static boolean getStarted(){
        return started;
    }

    public static void setStarted(boolean started) {
        GodsRaid.started = started;
    }

    public GodsRaid(int maxPlayers) {
        super(maxPlayers, Location.GODS_RAID);
        setName("Deadly Raid");
        init();
    }

    @Override
    protected void spawnCurrentStageNpcs() {
        Stage stage = stages[currentStage];
        int npcCount = stage.countNpcs();
        NPC npc;
        for (int i = 0; i < npcCount; ++i) {
            npc = stage.getNpc(i);
            npc.getDefinition().setMulti(true);
            npc.getDefinition().setRespawnTime(-1);
            World.register(stage.getNpc(i));
        }
    }

    @Override
    protected void giveRewards() {
        int[] rare = {11425, 4646, 4648, 4647, 4650, 4649, 4651, 4652}; // 1:333 chance
        int[] ultraRare = {4281, 14933}; // 1:1000 chance - should put pets here
        int[][] common = {{11559}, {21045}, {20204,15}, {20205,15}, {3260,10}, {4062,10}, {4064,10}, {4063,10}, {20206,15}, {}}; // item and amount
        int abba[][] = {
                {5022,10000}, {5022,12500}, {5022,13500}, {5022,15000}, {5022,16500}, {5022,17000}, {5022,17500}, {5022,18500}, {5022,20000}
        };

        for (int i = 0; i < playerIndex; ++i) {

                int random = Misc.random(999);
                Item reward = new Item(19904, 75);
                int randomCommon = RandomUtility.getRandom(abba.length - 1);
                reward = new Item(abba[randomCommon][0]);
                int amount = abba[randomCommon][1];

                if (random == 0) {
                    amount = 1;
                    int randomUltra = Misc.random(ultraRare.length - 1);
                    reward = new Item(ultraRare[randomUltra]);
                    String itemName = reward.getDefinition().getName();
                    String announcement = "@red@[Deadly Raids][Ultra Rare] @Bla@" + players[i].getUsername() + " has got " + itemName;
                    World.sendMessage(announcement);
                } else if (random >= 1 && random <= 3) {
                    amount = 1;
                    int randomRare = Misc.random(rare.length - 1);
                    reward = new Item(ultraRare[randomRare]);
                    String itemName = reward.getDefinition().getName();
                    String announcement = "@red@[Deadly Raids][Rare] @Bla@" + players[i].getUsername() + " has got " + itemName;
                    World.sendMessage(announcement);
                }
                reward.setAmount(amount);
                players[i].getSkillManager().addExperience(Skill.RAID, 30000);
                players[i].sendMessage("<shad=1>@gre@You receive 30k Raids xp from doing this raid!");
                players[i].sendMessage("You got " + amount + "x " + reward.getDefinition().getName() + " as a reward");
                if (reward.getDefinition().isStackable() || players[i].getInventory().getFreeSlots() > amount)
                    players[i].getInventory().add(reward);
                else {
                    players[i].sendMessage("reward was added to your bank because you have no inventory spaces");
                    players[i].getBank(players[i].getCurrentBankTab()).add(reward);
                }
        }
    }

    @Override
    protected void initStages() {
        Stage[] stages = new Stage[4];
        NPC[] stage0npcs = new NPC[11];
        stage0npcs[0] = new NPC(8544, new Position(3055, 2723, 0));
        stage0npcs[1] = new NPC(8545, new Position(3051, 2720, 0));
        stage0npcs[2] = new NPC(8544, new Position(3051, 2726, 0));
        stage0npcs[3] = new NPC(8545, new Position(3045, 2727, 0));
        stage0npcs[4] = new NPC(8545, new Position(3043, 2723, 0));
        stage0npcs[5] = new NPC(8544, new Position(3046, 2715, 0));
        stage0npcs[6] = new NPC(8545, new Position(3061, 2718, 0));
        stage0npcs[7] = new NPC(8544, new Position(3054, 2718, 0));
        stage0npcs[8] = new NPC(8544, new Position(3059, 2734, 0));
        stage0npcs[9] = new NPC(8545, new Position(3056, 2728, 0));
        stage0npcs[10] = new NPC(8544, new Position(3057, 2722, 0));
        stages[0] = new Stage(stage0npcs);


        NPC[] stage1npcs = new NPC[11];
        stage1npcs[0] = new NPC(8546, new Position(3055, 2723, 0));
        stage1npcs[1] = new NPC(8545, new Position(3051, 2720, 0));
        stage1npcs[2] = new NPC(8545, new Position(3051, 2726, 0));
        stage1npcs[3] = new NPC(8546, new Position(3045, 2727, 0));
        stage1npcs[4] = new NPC(8545, new Position(3043, 2723, 0));
        stage1npcs[5] = new NPC(8546, new Position(3046, 2715, 0));
        stage1npcs[6] = new NPC(8545, new Position(3061, 2718, 0));
        stage1npcs[7] = new NPC(8546, new Position(3054, 2718, 0));
        stage1npcs[8] = new NPC(8545, new Position(3059, 2734, 0));
        stage1npcs[9] = new NPC(8546, new Position(3056, 2728, 0));
        stage1npcs[10] = new NPC(8545, new Position(3057, 2722, 0));
        stages[1] = new Stage(stage1npcs);


        NPC[] stage2npcs = new NPC[11];
        stage2npcs[0] = new NPC(8547, new Position(3055, 2723, 0));
        stage2npcs[1] = new NPC(8546, new Position(3051, 2720, 0));
        stage2npcs[2] = new NPC(8547, new Position(3051, 2726, 0));
        stage2npcs[3] = new NPC(8546, new Position(3045, 2727, 0));
        stage2npcs[4] = new NPC(8547, new Position(3043, 2723, 0));
        stage2npcs[5] = new NPC(8546, new Position(3046, 2715, 0));
        stage2npcs[6] = new NPC(8547, new Position(3061, 2718, 0));
        stage2npcs[7] = new NPC(8546, new Position(3054, 2718, 0));
        stage2npcs[8] = new NPC(8547, new Position(3059, 2734, 0));
        stage2npcs[9] = new NPC(8546, new Position(3056, 2728, 0));
        stage2npcs[10] = new NPC(8547, new Position(3057, 2722, 0));
        stages[2] = new Stage(stage2npcs);

        stages[3] = new Stage(new NPC[] {
                new NPC(8548, new Position(3052, 2724, 0))
        });
        this.stages = stages;
    }

    @Override
    protected void startNextStage() {
        super.startNextStage();
    }

    @Override
    public void init() {
        super.init();
        initStages();
    }



}
