package com.arlania.Commands.impl.staff;

import com.arlania.Commands.Command;
import com.arlania.Commands.CommandInfo;
import com.arlania.world.World;
import com.arlania.world.content.PlayerLogs;
import com.arlania.world.content.PlayerPunishment;
import com.arlania.world.entity.impl.player.Player;

import static com.arlania.model.PlayerRights.*;

@CommandInfo(
        command = {"unjail"},
        description = "Release a player.",
        rights = {MODERATOR,  ADMINISTRATOR, OWNER, DEVELOPER}
)
public class UnJail extends Command {
    @Override
    public void execute(Player player, String command) {
        Player player2 = World.getPlayerByName(command.substring(10));
        if (player2 != null) {
            PlayerPunishment.Jail.unjail(player2);
            PlayerLogs.log(player.getUsername(),
                    "" + player.getUsername() + " just unjailed " + player2.getUsername() + "!");
            player.getPacketSender().sendMessage("Unjailed player: " + player2.getUsername() + "");
            player2.getPacketSender().sendMessage("You have been unjailed by " + player.getUsername() + ".");
        } else {
            player.getPacketSender().sendConsoleMessage("Could not find that player online.");
        }
    }
}
