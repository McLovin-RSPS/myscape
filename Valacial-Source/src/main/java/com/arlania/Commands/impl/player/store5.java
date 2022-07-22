package com.arlania.Commands.impl.player;

import com.arlania.model.GameMode;
import com.arlania.model.PlayerRights;
import com.arlania.Commands.Command;
import com.arlania.Commands.CommandInfo;
import com.arlania.world.World;
import com.arlania.world.content.PlayerPunishment;
import com.arlania.world.entity.impl.player.Player;

@CommandInfo(
        command = {"test"},
        description = "placeholder command",
        donationAmountRequired = 1337
)
public class store5 extends Command{

    @Override
    public void execute(Player player, String command) {
    	player.sendMessage("Attempting to open store ");
    	
    }

}