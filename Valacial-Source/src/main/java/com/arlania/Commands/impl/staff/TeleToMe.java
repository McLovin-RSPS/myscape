package com.arlania.Commands.impl.staff;

import com.arlania.Commands.Command;
import com.arlania.Commands.CommandInfo;
import com.arlania.world.World;
import com.arlania.world.content.transportation.TeleportHandler;
import com.arlania.world.content.transportation.TeleportType;
import com.arlania.world.entity.impl.player.Player;

import static com.arlania.model.PlayerRights.*;

@CommandInfo(
        command = {"teletome"},
        description = "Teleports a player to you.",
        rights = {SUPPORT, MODERATOR,  ADMINISTRATOR, OWNER, DEVELOPER}
)
public class TeleToMe extends Command {
    @Override
    public void execute(Player player, String command) {
        String[] string = command.split("-");

        String playerToTele = string[1];
        Player player2 = World.getPlayerByName(playerToTele);
        if (player2 == null) {
            player.getPacketSender().sendConsoleMessage("Cannot find that player online..");
            return;
        } else {
            boolean canTele = TeleportHandler.checkReqs(player, player2.getPosition().copy())
                    && player.getRegionInstance() == null && player2.getRegionInstance() == null;
            if (canTele) {
                TeleportHandler.teleportPlayer(player2, player.getPosition().copy(), TeleportType.NORMAL);
                player.getPacketSender()
                        .sendConsoleMessage("Teleporting player to you: " + player2.getUsername() + "");
                player2.getPacketSender().sendMessage("You're being teleported to " + player.getUsername() + "...");
            } else {
                player.getPacketSender().sendConsoleMessage(
                        "You can not teleport that player at the moment. Maybe you or they are in a minigame?");
            }
        }
    }
}
