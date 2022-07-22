package com.arlania.Commands.impl.staff;

import com.arlania.Commands.Command;
import com.arlania.Commands.CommandInfo;
import com.arlania.world.World;
import com.arlania.world.entity.impl.player.Player;

import static com.arlania.model.PlayerRights.DEVELOPER;
import static com.arlania.model.PlayerRights.OWNER;

@CommandInfo(
        command = {"giveall"},
        description = "Give every online player an item.",
        rights = {OWNER, DEVELOPER}
)
public class GiveAll extends Command {
    @Override
    public void execute(Player player, String command) {
        int item = Integer.parseInt(command.split(" ")[1]);
        int amount = Integer.parseInt(command.split(" ")[2]);

        for (Player target : World.getPlayers()) {
            if (target == null) {
                continue;
            }
            player.getPacketSender().sendConsoleMessage("Gave player gold.");
            target.getInventory().add(item, amount);
        }
    }
}
