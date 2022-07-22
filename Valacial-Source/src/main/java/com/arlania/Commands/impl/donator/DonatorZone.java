package com.arlania.Commands.impl.donator;

import com.arlania.model.Position;
import com.arlania.Commands.Command;
import com.arlania.Commands.CommandInfo;
import com.arlania.world.content.transportation.TeleportHandler;
import com.arlania.world.entity.impl.player.Player;

@CommandInfo(
        command = {"dzone", "donorzone", "donatorzone"},
        description = "Teleports you to the Donator Zone.",
        donationAmountRequired = 25
)
public class DonatorZone extends Command {
    @Override
    public void execute(Player player, String command) {
        TeleportHandler.teleportPlayer(player, new Position(3363, 9638),
                player.getSpellbook().getTeleportType());
    }
}
