package com.arlania.Commands.impl.staff;

import com.arlania.Commands.Command;
import com.arlania.Commands.CommandInfo;
import com.arlania.util.CommandTypes;
import com.arlania.util.StaffCommand;
import com.arlania.world.World;
import com.arlania.world.entity.impl.player.Player;

import static com.arlania.model.PlayerRights.*;
import static com.arlania.util.Misc.superiorCheck;

@CommandInfo(
        command = {"ban"},
        description = "Ban a player.",
        rights = {MODERATOR,  ADMINISTRATOR, OWNER, DEVELOPER}
)
public class Ban extends Command {
    @Override
    public void execute(Player player, String command) {

        Player target2 = World.getPlayerByName(command.split("-")[1]);
        if (target2 != null) {
            if (!superiorCheck(player, target2)) {
                return;
            }
        }
        StaffCommand.parse(CommandTypes.BAN, player, command);
    }
}
