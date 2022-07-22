package com.arlania.Commands.impl.staff;

import com.arlania.Commands.Command;
import com.arlania.Commands.CommandInfo;
import com.arlania.world.World;
import com.arlania.world.content.PlayerLogs;
import com.arlania.world.content.PlayerPunishment;
import com.arlania.world.entity.impl.player.Player;

import static com.arlania.model.PlayerRights.*;
import static com.arlania.util.Misc.superiorCheck;

@CommandInfo(
        command = {"jail"},
        description = "Lock up a player.",
        rights = {MODERATOR,  ADMINISTRATOR, OWNER, DEVELOPER}
)

public class Jail extends Command {
    @Override
    public void execute(Player player, String command) {
        Player target = World.getPlayerByName(command.split("-")[1]);
        if (target != null) {
            if (!superiorCheck(player, target)) {
                return;
            }
        }
        Player player2 = World.getPlayerByName(command.substring(8));
        if (player2 != null) {
            if (PlayerPunishment.Jail.isJailed(player2)) {
                player.getPacketSender().sendConsoleMessage("That player is already jailed!");
                return;
            }
            if (PlayerPunishment.Jail.jailPlayer(player2)) {
                player2.getSkillManager().stopSkilling();
                PlayerLogs.log(player.getUsername(),
                        "" + player.getUsername() + " just jailed " + player2.getUsername() + "!");
                player.getPacketSender().sendMessage("Jailed player: " + player2.getUsername() + "");
                player2.getPacketSender().sendMessage("You have been jailed by " + player.getUsername() + ".");
            } else {
                player.getPacketSender().sendConsoleMessage("Jail is currently full.");
            }
        } else {
            player.getPacketSender().sendConsoleMessage("Could not find that player online.");
        }
    }
}
