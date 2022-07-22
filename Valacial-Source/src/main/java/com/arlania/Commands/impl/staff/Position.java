package com.arlania.Commands.impl.staff;

import com.arlania.Commands.Command;
import com.arlania.Commands.CommandInfo;
import com.arlania.world.entity.impl.player.Player;

import static com.arlania.model.PlayerRights.*;

@CommandInfo(
        command = {"mypos", "coords", "position"},
        description = "Get your players position.",
        rights = {OWNER, DEVELOPER}
)
public class Position extends Command {
    @Override
    public void execute(Player player, String command) {
        player.getPacketSender().sendMessage(player.getPosition().toString());
    }
}
