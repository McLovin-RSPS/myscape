package com.arlania.Commands.impl.player;

import com.arlania.Commands.Command;
import com.arlania.Commands.CommandInfo;
import com.arlania.world.content.PlayersOnlineInterface;
import com.arlania.world.entity.impl.player.Player;

@CommandInfo(
        command = {"player", "players", "online", "playeronline"},
        description = "Will show details on all players online."
)
public class Players extends Command {
    @Override
    public void execute(Player player, String command) {
        player.getPacketSender().sendInterfaceRemoval();
        PlayersOnlineInterface.showInterface(player);
    }
}
