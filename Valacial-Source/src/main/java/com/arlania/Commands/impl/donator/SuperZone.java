package com.arlania.Commands.impl.donator;

import com.arlania.model.Position;
import com.arlania.Commands.Command;
import com.arlania.Commands.CommandInfo;
import com.arlania.world.content.transportation.TeleportHandler;
import com.arlania.world.entity.impl.player.Player;

@CommandInfo(
        command = {"szone", "superzone"},
        description = "Teleports you to the Super Donator Zone.",
        donationAmountRequired = 50
)
public class SuperZone extends Command {
    @Override
    public void execute(Player player, String command) {
        TeleportHandler.teleportPlayer(player, new Position(3429, 2919),
                player.getSpellbook().getTeleportType());
    }
}
