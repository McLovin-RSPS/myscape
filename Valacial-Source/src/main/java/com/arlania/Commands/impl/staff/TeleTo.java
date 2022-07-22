package com.arlania.Commands.impl.staff;

import com.arlania.model.PlayerRights;
import com.arlania.Commands.Command;
import com.arlania.Commands.CommandInfo;
import com.arlania.world.World;
import com.arlania.world.content.transportation.TeleportHandler;
import com.arlania.world.content.transportation.TeleportType;
import com.arlania.world.entity.impl.player.Player;

import static com.arlania.model.PlayerRights.*;

@CommandInfo(
        command = {"teleto"},
        description = "Teleports to a player.",
        rights = {SUPPORT, MODERATOR,  ADMINISTRATOR, OWNER, DEVELOPER}
)
public class TeleTo extends Command {
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
            if (player.getRights() == PlayerRights.SUPPORT || player.getRights() == PlayerRights.MODERATOR) {
                if (player2.getMinigameAttributes().getDungeoneeringAttributes().getParty() != null) {
                    player.sendMessage("you can't teleport to someone who is in dungeonnering");
                    return;
                }
            }

            if (canTele) {
                TeleportHandler.teleportPlayer(player, player2.getPosition().copy(), TeleportType.NORMAL);
                player.getPacketSender().sendConsoleMessage("Teleporting to player: " + player2.getUsername() + "");
            } else {
                player.getPacketSender()
                        .sendConsoleMessage("You can not teleport to this player at the moment. Minigame maybe?");
            }
        }
    }
}
