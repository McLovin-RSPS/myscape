package com.arlania.Commands.impl.player;

import com.arlania.Commands.Command;
import com.arlania.Commands.CommandInfo;
import com.arlania.world.content.minigames.impl.FreeForAll;
import com.arlania.world.entity.impl.player.Player;

@CommandInfo(
        command = {"ffa"},
        description = "Teleport you into a FFA lobby"
)
public class Ffa extends Command {



    @Override
    public void execute(Player player, String command) {
        if (player.getSummoned() != -1) {
            player.sendMessage("You cannot bring pets into the Free for All event!");
            return;
        } else {
            for (int i = 0; i < player.getPrayerbook().ordinal(); i++) {
                player.setPrayerActive(i, false);
            }
            FreeForAll.enterLobby(player);
        }
    }
}
