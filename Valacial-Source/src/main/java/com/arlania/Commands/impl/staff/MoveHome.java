package com.arlania.Commands.impl.staff;

import com.arlania.GameSettings;
import com.arlania.Commands.Command;
import com.arlania.Commands.CommandInfo;
import com.arlania.world.World;
import com.arlania.world.entity.impl.player.Player;

import static com.arlania.model.PlayerRights.*;

@CommandInfo(
        command = {"telehome", "forcehome", "movehome"},
        description = "Teleport a player home.",
        rights = {MODERATOR,  ADMINISTRATOR, OWNER, DEVELOPER}
)
public class MoveHome extends Command {
    @Override
    public void execute(Player player, String command) {
        Player target = World.getPlayerByName(command.split("-")[1]);
        if (target != null) {
            target.moveTo(GameSettings.DEFAULT_POSITION);
            player.sendMessage("You moved " + target.getUsername() + " to home.");
        }
    }
}
