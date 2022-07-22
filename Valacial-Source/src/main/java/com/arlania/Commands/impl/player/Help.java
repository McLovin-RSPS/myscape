package com.arlania.Commands.impl.player;

import com.arlania.Commands.Command;
import com.arlania.Commands.CommandInfo;
import com.arlania.world.World;
import com.arlania.world.entity.impl.player.Player;

@CommandInfo(
        command = {"help", "ticket"},
        description = "Request help from a staff member."
)
public class Help extends Command {
    @Override
    public void execute(Player player, String command) {
        if (player.getLastYell().elapsed(30000)) {
            World.sendStaffMessage("<col=FF0066><img=10> [TICKET SYSTEM]<col=6600FF> " + player.getUsername()
                    + " has requested help. Please help them!");
            player.getLastYell().reset();
            player.getPacketSender()
                    .sendMessage("<col=663300>Your help request has been received. Please be patient.");
        } else {
            player.getPacketSender().sendMessage("")
                    .sendMessage("<col=663300>You need to wait 30 seconds before using this again.").sendMessage(
                    "<col=663300>If it's an emergency, please private message a staff member directly instead.");
        }
    }
}
