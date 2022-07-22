package com.arlania.Commands.impl.player;

import com.arlania.model.Flag;
import com.arlania.Commands.Command;
import com.arlania.Commands.CommandInfo;
import com.arlania.world.entity.impl.player.Player;

@CommandInfo(
        command = {"cleartitle", "removetitle"},
        description = "Will show details on all players online."
)
public class RemoveTitle extends Command {
    @Override
    public void execute(Player player, String command) {
        player.setTitle("");
        player.getUpdateFlag().flag(Flag.APPEARANCE);
    }
}
