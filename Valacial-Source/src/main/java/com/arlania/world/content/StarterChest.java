package com.arlania.world.content;

import com.arlania.model.GroundItem;
import com.arlania.model.Item;
import com.arlania.model.definitions.ItemDefinition;
import com.arlania.util.Misc;
import com.arlania.world.World;
import com.arlania.world.entity.impl.GroundItemManager;
import com.arlania.world.entity.impl.player.Player;

import java.security.SecureRandom;

public final class StarterChest {

    private static final SecureRandom random = new SecureRandom();

    public static final int CHEST = 172;

    public static final int KEY = 11559;

    private static final Item[] RARE_TABLE = {
            new Item(5022, 25000),
            new Item(1153),
            new Item(1115),
            new Item(1067),
            new Item(20251),
            new Item(21011),
            new Item(21012),
            new Item(21004),
            new Item(21030)
    };

    private static final Item[] SUPER_RARE_TABLE = {
            new Item(3313),
            new Item(3314),
            new Item(3315),
            new Item(3316),
            new Item(3317),
            new Item(1044),
            new Item(1048),
            new Item(1046),
            new Item(1038),
            new Item(1042),
    };

    private static final Item[] LEGENDARY_TABLE = {
            new Item(15355)

    };

    public static void openChest(Player player) {
        if (!player.getInventory().contains(KEY)) {
            player.sendMessage("@red@You need a Starter key to open this chest..");
            return;
        }

        int roll = Misc.random(1000);

        Item item;
        boolean rare = false;
        if (roll == 1) {
            int length = LEGENDARY_TABLE.length;
            item = LEGENDARY_TABLE[random.nextInt(length)];
            rare = true;
        } else if (roll <= 50) {
            int length = SUPER_RARE_TABLE.length;
            item = SUPER_RARE_TABLE[random.nextInt(length)];
            rare = true;
        } else if (roll <= 150) {
            int length = RARE_TABLE.length;
            item = RARE_TABLE[random.nextInt(length)];
            rare = true;
        } else {
            item = new Item(5022, Misc.random(1_000, 3_000));
        }

        ItemDefinition definition = item.getDefinition();
        if (definition == null) {
            player.sendMessage("@red@Error encountered while opening chest, please report this to staff.");
            return;
        }

        if (rare) {
            World.sendMessage("<shad=0>@bla@[@red@Starter Chest@bla@] @bla@" + player.getUsername() + "@bla@ Has just received @gre@ " + item.getAmount() + "x " + definition.getName() + "@bla@!");
        }

        player.getInventory().delete(KEY, 1);

        if (player.getInventory().isFull()) {
            GroundItem groundItem = new GroundItem(item, player.getPosition().copy(), player.getUsername(), false, 150, true, 200);
            GroundItemManager.spawnGroundItem(player, groundItem);

            player.sendMessage("@red@Your reward dropped beneath your feet.");
        } else {
            player.getInventory().add(item);
        }

    }

}
