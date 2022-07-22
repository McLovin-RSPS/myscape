package com.arlania.Commands.impl.staff;

import com.arlania.model.Position;
import com.arlania.Commands.Command;
import com.arlania.Commands.CommandInfo;
import com.arlania.world.content.transportation.TeleportHandler;
import com.arlania.world.content.transportation.TeleportType;
import com.arlania.world.entity.impl.player.Player;

import static com.arlania.model.PlayerRights.*;

@CommandInfo(
        command = {"sz", "staffzone"},
        description = "Teleports to the staff zone.",
        rights = {SUPPORT, MODERATOR,  ADMINISTRATOR, OWNER, DEVELOPER}
)
public class StaffZone extends Command {
    @Override
    public void execute(Player player, String command) {
        TeleportHandler.teleportPlayer(player, new Position(2846, 5147), TeleportType.NORMAL);
    }
}
