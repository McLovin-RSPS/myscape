package com.arlania.world.content;

import com.arlania.model.GameMode;
import com.arlania.model.Item;
import com.arlania.net.packet.PacketSender;
import com.arlania.world.World;
import com.arlania.world.content.clan.ClanChatManager;
import com.arlania.world.entity.impl.player.Player;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class StartScreen {

    private static final int INTERFACE_ID = 23050;
    private static final int SCROLL_BAR_ID = 23109;
    private static final int CONFIRM_BUTTON = 23052;
    private static final int CYCLE_LEFT = 23056;
    private static final int CYCLE_RIGHT = 23059;

    public static Set<Integer> ignoreLockForButtons = new HashSet<>(Arrays.asList(CONFIRM_BUTTON, CYCLE_LEFT, CYCLE_RIGHT));

    private final Player player;
    private Selection selection = Selection.NORMAL;

    public StartScreen(Player player) {
        this.player = player;
    }

    public static void open(Player player) {
        if (player.getStartScreen() == null) {
            player.setStartScreen(new StartScreen(player));
        }
        player.getStartScreen().open();
    }

    public void open() {
        player.getPacketSender().sendInterface(INTERFACE_ID);
        refreshSelection();
    }

    public void refreshSelection() {
        PacketSender out = player.getPacketSender();
        Selection sel = selection.left();
        out.setScrollBar(SCROLL_BAR_ID, 0);
        for (int i = 0; i < Card.values.length; i++) {
            Card card = Card.values[i];
            out.sendString(card.title, sel.name);
            out.sendString(card.description, sel.description);
            out.sendString(card.dropDescription, sel.dropDescription);
            out.sendSpriteChange(card.sprite, sel.sprite);
            sel = sel.right();
        }
    }

    public boolean handleButton(int id) {
        if (id == CONFIRM_BUTTON) {
            handleConfirm();
            return true;
        }
        if (id == CYCLE_LEFT) {
            selection = selection.left();
            refreshSelection();
            return true;
        }
        if (id == CYCLE_RIGHT) {
            selection = selection.right();
            refreshSelection();
            return true;
        }
        return false;
    }

    private void handleConfirm() {
        if (!player.newPlayer()) {
            return;
        }
        if(!PlayerPunishment.hasRecieved1stStarter(player.getHostAddress())) {
            player.setReceivedStarter(true);
            for (Item item : selection.startItems) {
                player.getInventory().add(item);
            }
            PlayerPunishment.addIpToStarterList1(player.getHostAddress());
            PlayerPunishment.addIpToStarter1(player.getHostAddress());
        }
        else if(PlayerPunishment.hasRecieved1stStarter(player.getHostAddress()) && !PlayerPunishment.hasRecieved2ndStarter(player.getHostAddress())) {
            player.setReceivedStarter(true);
            for (Item item : selection.startItems) {
                player.getInventory().add(item);
            }
            PlayerPunishment.addIpToStarterList2(player.getHostAddress());
            PlayerPunishment.addIpToStarter2(player.getHostAddress());
        }
        else if(PlayerPunishment.hasRecieved1stStarter(player.getHostAddress()) && PlayerPunishment.hasRecieved2ndStarter(player.getHostAddress())) {
            player.getPacketSender().sendMessage("You've received to many starters.");
        }
        player.getPacketSender().sendInterfaceRemoval();
        GameMode.set(player, selection.linkedMode, false);
        ClanChatManager.join(player, "viper");
        player.setPlayerLocked(false);
        player.getPacketSender().sendInterface(3559);
        player.getAppearance().setCanChangeAppearance(true);
        player.setNewPlayer(false);
        World.sendMessage("<col=6600CC>[NEW PLAYER]: "+player.getUsername()+" has logged into ImaginePS for the first time!");
        player.setStartScreen(null);
    }

    enum Selection {
        NORMAL("Normal", GameMode.NORMAL, new Item[] {
                new Item(1856, 1),
                new Item(2441, 10),
                new Item(2437, 10),
                new Item(2443, 10),
                new Item(3025, 10),
                new Item(15272, 200),
                new Item(5022, 100),
                new Item(11537, 1),
                new Item(11535, 1),
                new Item(11536, 1),
                new Item(11540, 1),
                new Item(11541, 1),
                new Item(11543, 1),
                (new Item(11539,1)),
                (new Item (939, 1)),
                (new Item (938, 1))}, "As a normal player you will be able to play the game without any restrictions.",
                "2 Drop rate boost.", 713),
        IRONMAN("Ironman", GameMode.IRONMAN, new Item [] {
                new Item(1856, 1),
                new Item(2441, 10),
                new Item(2437, 10),
                new Item(2443, 10),
                new Item(3025, 10),
                new Item(15272, 200),
                new Item(995, 2000000),
                new Item(11537, 1),
                new Item(11535, 1),
                new Item(11536, 1),
                (new Item(11538,1)),
                (new Item(11539,1)),
                (new Item (11543, 1)),
                (new Item(11540,1)),
                (new Item(11541,1)),
                (new Item(11542,1)),
                (new Item(11544,1)),
                (new Item (841, 1)),
                (new Item (882, 500)),
                (new Item (579, 1)),
                (new Item (577, 1)),
                (new Item (1011, 1)),
                (new Item (1381, 1)),
                (new Item (558, 1000)),
                (new Item (557, 1000)),
                (new Item (555, 1000)),
                (new Item (554, 1000))}, "Play ImaginePS as an Iron man. You will be restricted from trading, staking and looting items from killed players. You will not get a npc drop if another player has done more damage. You will have to rely on your starter, skilling, pvming, and shops. This game mode is for players that love a challenge.",
                "10 drop rate boost.", 712),
        ULTIMATE_IRONMAN("Ultimate Ironman", GameMode.HARDCORE_IRONMAN, new Item[] {
                new Item(1856, 1),
                new Item(2441, 10),
                new Item(2437, 10),
                new Item(2443, 10),
                new Item(3025, 10),
                new Item(15272, 200),
                new Item(995, 2000000),
                new Item(11537, 1),
                new Item(11535, 1),
                new Item(11536, 1),
                (new Item(11538,1)),
                (new Item(11539,1)),
                (new Item (11543, 1)),
                (new Item(11540,1)),
                (new Item(11541,1)),
                (new Item(11542,1)),
                (new Item(11544,1)),
                (new Item (841, 1)),
                (new Item (882, 500)),
                (new Item (579, 1)),
                (new Item (577, 1)),
                (new Item (1011, 1)),
                (new Item (1381, 1)),
                (new Item (558, 1000)),
                (new Item (557, 1000)),
                (new Item (555, 1000)),
                (new Item (554, 1000))}, "Play ImaginePS as a Ultimate Ironman. In addiction to the iron man rules you cannot use banks. This gamemode is for the players that love the impossible.",
                "20 Drop rate boost.", 711),
        ;

        static final Selection[] values = Selection.values();

        final String name;
        final GameMode linkedMode;
        final Item[] startItems;
        final String description;
        final String dropDescription;
        final int sprite;

        Selection(String name, GameMode linkedMode, Item[] startItems, String description, String dropDescription, int sprite) {
            this.name = name;
            this.linkedMode = linkedMode;
            this.startItems = startItems;
            this.description = description;
            this.dropDescription = dropDescription;
            this.sprite = sprite;
        }

        Selection left() {
            return values[(ordinal() + values.length - 1) % values.length];
        }

        Selection right() {
            return values[(ordinal() + 1) % values.length];
        }
    }

    enum Card {
        LEFT(23104, 23112, 23107, 23116),
        CENTER(23103, 23110, 23106, 23115),
        RIGHT(23105, 23114, 23108, 23117),
        ;

        static final Card[] values = Card.values();

        final int title;
        final int description;
        final int dropDescription;
        final int sprite;

        Card(int title, int description, int dropDescription, int sprite) {
            this.title = title;
            this.description = description;
            this.dropDescription = dropDescription;
            this.sprite = sprite;
        }
    }
}
