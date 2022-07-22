package com.arlania.Commands.impl.player;

import com.arlania.Commands.Command;
import com.arlania.Commands.CommandInfo;
import com.arlania.world.content.KillsTracker;
import com.arlania.world.entity.impl.player.Player;

@CommandInfo(
        command = {"npckills", "kills"},
        description = "See how many total NPC kills you currently have."
)
public class NpcKills extends Command {


    @Override
    public void execute(Player player, String command) {
        player.getPacketSender()
                .sendMessage("@red@You currently have " + KillsTracker.getTotalKills(player) + " NPC kills.");
    }
}
