package com.arlania.Commands.impl.staff;

import com.arlania.Commands.Command;
import com.arlania.Commands.CommandInfo;
import com.arlania.world.World;
import com.arlania.world.content.PlayerPunishment;
import com.arlania.world.entity.impl.player.Player;

import static com.arlania.model.PlayerRights.*;
import static com.arlania.util.Misc.superiorCheck;

@CommandInfo(
        command = {"ipmute"},
        description = "Mute a player by their IP Address.",
        rights = { ADMINISTRATOR, OWNER, DEVELOPER}
)
public class IpMute extends Command {
    @Override
    public void execute(Player player, String command) {
        Player target2 = World.getPlayerByName(command.split("-")[1]);
        if (target2 != null) {
            if (!superiorCheck(player, target2)) {
                return;
            }
        }
        Player player2 = World.getPlayerByName(command.substring(10));
        if (player2 == null) {
            player.getPacketSender().sendConsoleMessage("Could not find that player online.");
            return;
        } else {
            if (PlayerPunishment.IPMuted(player2.getHostAddress())) {
                player.getPacketSender().sendConsoleMessage(
                        "Player " + player2.getUsername() + "'s IP is already IPMuted. Command logs written.");
                return;
            }
            final String mutedIP = player2.getHostAddress();
            PlayerPunishment.addMutedIP(mutedIP);
            player.getPacketSender().sendConsoleMessage(
                    "Player " + player2.getUsername() + " was successfully IPMuted. Command logs written.");
            World.sendStaffMessage("<col=FF0066><img=10> [PUNISHMENTS]<col=6600FF> " + player.getUsername()
                    + " has IPmuted " + player2.getUsername() + ". Don't forget to post logs!");
            player2.getPacketSender().sendMessage("You have been IPMuted by " + player.getUsername() + ".");
        }
    }
}
