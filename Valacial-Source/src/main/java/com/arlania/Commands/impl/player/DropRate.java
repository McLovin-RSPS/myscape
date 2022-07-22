package com.arlania.Commands.impl.player;

import com.arlania.model.definitions.NPCDrops;
import com.arlania.Commands.Command;
import com.arlania.Commands.CommandInfo;
import com.arlania.world.entity.impl.player.Player;

@CommandInfo(
        command = {"droprate", "dr", "drboost", "drinfo"},
        description = "Will give you information about your drop rate/double drop rate."
)
public class DropRate extends Command {
    @Override
    public void execute(Player player, String command) {
        player.getPacketSender().sendMessage("You currently have " + NPCDrops.getDroprate(player, false)
                + "% droprate and " + NPCDrops.getDoubleDr(player, false) + "% double droprate");
    }
}
