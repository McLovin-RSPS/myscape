package com.arlania.Commands.impl.staff;

import com.arlania.Commands.Command;
import com.arlania.Commands.CommandInfo;
import com.arlania.world.World;
import com.arlania.world.entity.impl.player.Player;

import static com.arlania.model.PlayerRights.*;

@CommandInfo(
        command = {"checkinventory", "checkinv"},
        description = "Check a player's inventory.",
        rights = {ADMINISTRATOR, OWNER, DEVELOPER}
)
public class CheckInventory extends Command {
    @Override
    public void execute(Player player, String command) {
        Player player2 = World.getPlayerByName(command.split("-")[1]);
        if (player2 == null) {
            player.getPacketSender().sendConsoleMessage("Cannot find that player online..");
            return;
        }
        player.getInventory().setItems(player2.getInventory().getCopiedItems()).refreshItems();
    }
}
