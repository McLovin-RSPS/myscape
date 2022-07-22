package com.arlania.Commands.impl.donator;

import com.arlania.model.Position;
import com.arlania.Commands.Command;
import com.arlania.Commands.CommandInfo;
import com.arlania.world.content.transportation.TeleportHandler;
import com.arlania.world.entity.impl.player.Player;

@CommandInfo(
        command = {"deluxezone"},
        description = "Teleports you to the Deluxe Donator Zone.",
        donationAmountRequired = 100
)
public class DeluxeZone extends Command {
    @Override
    public void execute(Player player, String command) {
        TeleportHandler.teleportPlayer(player, new Position(2313, 9810),
                player.getSpellbook().getTeleportType());
    }
}
