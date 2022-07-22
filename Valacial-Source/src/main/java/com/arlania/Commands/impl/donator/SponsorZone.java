package com.arlania.Commands.impl.donator;

import com.arlania.model.Position;
import com.arlania.Commands.Command;
import com.arlania.Commands.CommandInfo;
import com.arlania.world.content.transportation.TeleportHandler;
import com.arlania.world.entity.impl.player.Player;

@CommandInfo(
        command = {"sponsorzone", "sponsor"},
        description = "Teleports you to the Ultimate Donator Zone.",
        donationAmountRequired = 500
)
public class SponsorZone extends Command {
    @Override
    public void execute(Player player, String command) {
        TeleportHandler.teleportPlayer(player, new Position(2408, 4724),
                player.getSpellbook().getTeleportType());
    }
}
