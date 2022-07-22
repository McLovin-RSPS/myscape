package com.arlania.Commands.impl.player;

import com.arlania.Commands.Command;
import com.arlania.Commands.CommandInfo;
import com.arlania.world.entity.impl.player.Player;

@CommandInfo(
        command = {"test"},
        description = "placeholder command",
        donationAmountRequired = 1337
)
public class Test extends Command{

    @Override
    public void execute(Player player, String command) {
    	player.sendMessage("Command used: " + command);
    	player.getPacketSender().sendString(1, "https://ImaginePS.org/store/");
    }

}
