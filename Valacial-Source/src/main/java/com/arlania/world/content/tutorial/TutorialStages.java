package com.arlania.world.content.tutorial;

import com.arlania.GameSettings;
import com.arlania.model.Position;
import com.arlania.world.content.BankPin;
import com.arlania.world.content.StartScreen;
import com.arlania.world.content.dialogue.Dialogue;
import com.arlania.world.content.dialogue.DialogueExpression;
import com.arlania.world.content.dialogue.DialogueManager;
import com.arlania.world.content.dialogue.DialogueType;
import com.arlania.world.entity.impl.player.Player;

/**
 * The tutorial stages for the tutorial intro.
 *
 * @author Gabriel || Wolfsdarker
 */
public enum TutorialStages {


    INITIAL_STAGE(null, "Welcome to ImaginePS!", "We have a small tutorial that will", "teach you some things about the server.", "Would you like to see it?"),

    
    SKIP_STAGE(null) {
        @Override
        public Dialogue getDialogue() {
            return new Dialogue() {

                @Override
                public DialogueType type() {
                    return DialogueType.OPTION;
                }

                @Override
                public DialogueExpression animation() {
                    return DialogueExpression.NORMAL;
                }

                public String[] dialogue() {
                    return new String[]{"Yes, show me the tutorial", "No, I would like to skip it"};
                }

                @Override
                public void specialAction(Player player) {
                	if (TutorialStages.values()[ordinal()] == null) {
//                		StartScreen.open(player);
                       // StartScreen.open(player);
                		player.getPacketSender().sendString(1, "https://discord.gg/XxN48Tq");
                		return;
                	}
                    player.setDialogueActionId(280);
                    player.setTutorialStage(TutorialStages.values()[ordinal()]);
                    movePlayer(player);
                }
            };
        }

        @Override
        public void sendDialogueText(Player player) {
            player.setDialogueActionId(280);
            super.sendDialogueText(player);
        }

        @Override
        public void stageAction(Player player) {
            player.setDialogueActionId(280);
        }
    },

    AFK_PLACE_START(new Position(3095, 3511), "First, there is the AFK zones, which are", "easy ways to make money. All you have to do is", "Click on the AFK spots."),
    AFK_PLACE_SECOND(new Position(3089, 3471), "There is a limit of 1 account per AFK zone.", "So you can have a maximum of 3 accounts, one", "in each zone."),
    AFK_PLACE_END(new Position(2394, 3488), "This one of the easiest money makers in the game."),

    BOSS_EVENTS(new Position(3055, 5200), "Moving on, we now have the boss events", "which can be seen using the command ::events."),
    BOSS_EVENTS_2(new Position(3108, 4348), "These events happen every few hours and the", "::events command will let you know when its", "active or on cooldown."),

    SHOPS(new Position(3080, 3509), "Here we have the shops that require 1b tickets or Coins", "as their currency."),
    POINT_SHOPS(new Position(3096, 3497), "And these shops have different currencies and they", "can be obtained in different ways.", "Either through minigames, donation, bossing", "and more."),
    PLAYER_OWNED_SHOP(new Position(3091, 3495), "And the final shop is the player owned shop,", "which are owned by other players and have", "their prices set by the owner of the item."),

    SKILL_TELEPORT(null, "Now to train skills, it is as simple as clicking", "on them and it will teleport you to one of", "the training locations.") {
        @Override
        public void stageAction(Player player) {
            player.getPacketSender().sendTab(GameSettings.SKILLS_TAB);
        }
    },

    QUEST_TAB(null, "Also, within your quest tab, you can see your", "player statistics, which will display how many", "points you have in each currency.") {
        @Override
        public void stageAction(Player player) {
            player.getPacketSender().sendTab(GameSettings.QUESTS_TAB);
        }
    },

    ACHIEVEMENT_TAB(null, "Within the achievements tab, you can see everything", "you must complete to earn the completionist cape.") {
        @Override
        public void stageAction(Player player) {
            player.getPacketSender().sendTab(GameSettings.ACHIEVEMENT_TAB);
        }
    },

    COMMANDS(null, "If you ever need help, remember you have the", "command to check all available commands to you.", "Just simply type '::commands'."),

    DROPS_COMMAND(null, "When you don't know which boss to kill, you can", "use the '::drops' command and search for the boss", "that best suits your needs."),

    SETTINGS_TAB(null, "And if you need to change any settings, you can go", "in the settings tab and change the client's configuration.") {
        @Override
        public void stageAction(Player player) {
            player.getPacketSender().sendTab(GameSettings.OPTIONS_TAB);
        }
    },

    COMPLETED(GameSettings.EDGEVILLE, "The tutorial is over! You are now free to play as", "you wish!") {
        @Override
        public void stageAction(Player player) {
            if (player.newPlayer()) {
                BankPin.init(player, false);
            }
            player.setPlayerLocked(false);
//            StartScreen.open(player);
            //StartScreen.open(player);
            player.getPacketSender().sendString(1, "https://discord.gg/XxN48Tq");
        }
    },
    ;


    /**
     * The location the player will be teleported once the stage is active.
     */
    private final Position teleportPosition;

    /**
     * The dialogue text.
     */
    private final String[] dialogueText;

    TutorialStages(Position teleportPosition, String... dialogueText) {
        this.teleportPosition = teleportPosition;
        this.dialogueText = dialogueText;
    }

    public String[] getDialogueText() {
        return dialogueText;
    }

    /**
     * Returns the dialogue.
     *
     * @param player
     */
    public void movePlayer(Player player) {
        if (teleportPosition != null) {
            player.moveTo(teleportPosition);
        }
    }

    /**
     * Returns the dialogue for the stage.
     *
     * @return the dialogue
     */
    public Dialogue getDialogue() {
        return new Dialogue() {

            @Override
            public DialogueType type() {
                return DialogueType.NPC_STATEMENT;
            }

            @Override
            public DialogueExpression animation() {
                return DialogueExpression.LISTEN_LAUGH;
            }

            @Override
            public String[] dialogue() {
                return dialogueText;
            }

            @Override
            public int npcId() {
                return 660;
            }
        	

            @Override
            public Dialogue nextDialogue() {

                if (TutorialStages.values()[ordinal()] == COMPLETED) {
                    return null;
                }

                TutorialStages next = TutorialStages.values()[ordinal() + 1];

                if (next != null && next.dialogueText != null) {
                    return next.getDialogue();
                }
              
                return null;
            }

            @Override
            public void specialAction(Player player) {
                player.setTutorialStage(TutorialStages.values()[ordinal()]);
                movePlayer(player);
                stageAction(player);
            }
        };
    }

    /**
     * Sends the dialogue to the player.
     *
     * @param player
     */
    public void sendDialogueText(Player player) {
        DialogueManager.start(player, getDialogue());
        player.setDialogueActionId(280);
        stageAction(player);
    }

    /**
     * The special action for the player.
     *
     * @param player
     */
    public void stageAction(Player player) {
    }
}
