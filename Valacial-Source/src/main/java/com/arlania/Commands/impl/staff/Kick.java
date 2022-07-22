package com.arlania.Commands.impl.staff;

import com.arlania.model.Locations;
import com.arlania.Commands.Command;
import com.arlania.Commands.CommandInfo;
import com.arlania.world.World;
import com.arlania.world.content.PlayerLogs;
import com.arlania.world.entity.impl.player.Player;
import com.arlania.world.entity.impl.player.PlayerHandler;

import static com.arlania.model.PlayerRights.*;
import static com.arlania.util.Misc.superiorCheck;

@CommandInfo(
        command = {"kick", "boot"},
        description = "Force a player to log out.",
        rights = {MODERATOR,  ADMINISTRATOR, OWNER, DEVELOPER}
)
public class Kick extends Command {
    @Override
    public void execute(Player player, String command) {
        String player2 = command.split("-")[1];
        Player target = World.getPlayerByName(command.split("-")[1]);
        if (target != null) {
            if (!superiorCheck(player, target)) {
                return;
            }
        }
        Player playerToKick = World.getPlayerByName(player2);
        if (playerToKick == null) {
            player.getPacketSender().sendConsoleMessage("Player " + player2 + " couldn't be found on Catalyst.");
            return;
        } else if (playerToKick.getLocation() != Locations.Location.WILDERNESS) {
            World.deregister(playerToKick);
            PlayerHandler.handleLogout(playerToKick);
            player.getPacketSender().sendConsoleMessage("Kicked " + playerToKick.getUsername() + ".");
            PlayerLogs.log(player.getUsername(),
                    "" + player.getUsername() + " just kicked " + playerToKick.getUsername() + "!");
        }
    }
}
