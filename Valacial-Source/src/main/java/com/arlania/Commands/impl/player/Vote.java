package com.arlania.Commands.impl.player;

import com.arlania.Commands.Command;
import com.arlania.Commands.CommandInfo;
import com.arlania.world.entity.impl.player.Player;

@CommandInfo(
        command = {"vote"},
        description = "Takes you too the voting page for ImaginePS"
)
public class Vote extends Command {


    @Override
    public void execute(Player player, String command) {
        player.getPacketSender().sendString(1, "http://ImaginePS.org/vote/");
    }
}
