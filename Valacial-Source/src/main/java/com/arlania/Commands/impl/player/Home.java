package com.arlania.Commands.impl.player;

import com.arlania.GameSettings;
import com.arlania.Commands.Command;
import com.arlania.Commands.CommandInfo;
import com.arlania.world.content.transportation.TeleportHandler;
import com.arlania.world.entity.impl.player.Player;

@CommandInfo(
        command = {"home"},
        description = "Teleports you home"
       )
public class Home extends Command {
    @Override
    public void execute(Player player, String command) {
        TeleportHandler.teleportPlayer(player, GameSettings.DEFAULT_POSITION, player.getSpellbook().getTeleportType());
    }
}
