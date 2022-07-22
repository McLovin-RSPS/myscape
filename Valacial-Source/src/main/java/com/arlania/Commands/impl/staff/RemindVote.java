package com.arlania.Commands.impl.staff;

import com.arlania.Commands.Command;
import com.arlania.Commands.CommandInfo;
import com.arlania.world.World;
import com.arlania.world.entity.impl.player.Player;

import static com.arlania.model.PlayerRights.*;

@CommandInfo(
        command = {"remindvote"},
        description = "Remind the server too vote.",
        rights = {SUPPORT, MODERATOR,  ADMINISTRATOR, OWNER, DEVELOPER}
)
public class RemindVote extends Command {
    @Override
    public void execute(Player player, String command) {
        World.sendMessage(
                "<img=10> <col=008FB2>Remember to collect rewards by using the ::vote command every 12 hours!");
    }
}
