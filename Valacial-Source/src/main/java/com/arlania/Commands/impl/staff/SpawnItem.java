package com.arlania.Commands.impl.staff;

import com.arlania.model.Item;
import com.arlania.Commands.Command;
import com.arlania.Commands.CommandInfo;
import com.arlania.world.entity.impl.player.Player;

import static com.arlania.model.PlayerRights.*;

@CommandInfo(
        command = {"item"},
        description = "Spawn an item.",
        rights = { OWNER, DEVELOPER}
)
public class SpawnItem extends Command {
    @Override
    public void execute(Player player, String command) {
        String[] string = command.split(" ");
        int id = Integer.parseInt(string[1]);
        int amount = (string.length == 2 ? 1
                : Integer.parseInt(string[2].trim().toLowerCase().replaceAll("k", "000").replaceAll("m", "000000")
                .replaceAll("b", "000000000")));
        if (amount > Integer.MAX_VALUE) {
            amount = Integer.MAX_VALUE;
        }
        Item item = new Item(id, amount);
        player.getInventory().add(item, true);

        player.getPacketSender().sendItemOnInterface(47052, 11694, 1);
    }
}
