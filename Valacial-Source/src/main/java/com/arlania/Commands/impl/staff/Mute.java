package com.arlania.Commands.impl.staff;

import com.arlania.Commands.Command;
import com.arlania.Commands.CommandInfo;
import com.arlania.util.CommandTypes;
import com.arlania.util.StaffCommand;
import com.arlania.world.entity.impl.player.Player;

import static com.arlania.model.PlayerRights.*;

@CommandInfo(
        command = {"mute"},
        description = "Mute a player.",
        rights = {SUPPORT, MODERATOR,  ADMINISTRATOR, OWNER, DEVELOPER}
)
public class Mute extends Command {
    @Override
    public void execute(Player player, String command) {
        StaffCommand.parse(CommandTypes.MUTE, player, command);
    }
}
