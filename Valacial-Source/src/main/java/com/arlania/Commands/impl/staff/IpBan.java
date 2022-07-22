package com.arlania.Commands.impl.staff;

import com.arlania.Commands.Command;
import com.arlania.Commands.CommandInfo;
import com.arlania.util.Misc;
import com.arlania.util.banning.BanHammer;
import com.arlania.util.banning.BanType;
import com.arlania.world.World;
import com.arlania.world.content.PlayerLogs;
import com.arlania.world.content.PlayerPunishment;
import com.arlania.world.entity.impl.player.Player;
import com.arlania.world.entity.impl.player.PlayerHandler;

import java.util.Objects;

import static com.arlania.model.PlayerRights.*;
import static com.arlania.util.Misc.superiorCheck;

@CommandInfo(
        command = {"ipban"},
        description = "Ban a player by their IP Address.",
        rights = { ADMINISTRATOR, OWNER, DEVELOPER}
)
public class IpBan extends Command {
    @Override
    public void execute(Player player, String command) {
        Player target2 = World.getPlayerByName(command.split("-")[1]);
        if (target2 != null) {
            if (!superiorCheck(player, target2)) {
                return;
            }
        }
        final String bannedUserName = Misc.formatPlayerName(command.substring(command.split("-")[0].length() + 1));

        player.sendMessage("Attempting to ban " + bannedUserName);
        Player player2 = PlayerHandler.getPlayerForName(bannedUserName);
        if (player2 == null) {
            player.getPacketSender()
                    .sendConsoleMessage("Could not find that player online, started searching offline files.");
            BanHammer.ban(BanType.IP, player, bannedUserName);
        } else {
            if (PlayerPunishment.IPBanned(player2.getHostAddress())) {
                player.getPacketSender().sendConsoleMessage(
                        "Player " + player2.getUsername() + "'s IP is already banned. Command logs written.");
                return;
            }
            final String bannedIP = player2.getHostAddress();
            PlayerPunishment.addBannedIP(bannedIP);
            player.getPacketSender().sendConsoleMessage(
                    "Player " + player2.getUsername() + "'s IP was successfully banned. Command logs written.");
            World.sendStaffMessage("<col=FF0066><img=10> [PUNISHMENTS]<col=6600FF> " + player.getUsername()
                    + " has IPbanned " + player2.getUsername() + ". Don't forget to post logs!");
            for (Player playersToBan : World.getPlayers()) {
                if (playersToBan == null) {
                    continue;
                }
                if (Objects.equals(playersToBan.getHostAddress(), bannedIP)) {
                    PlayerLogs.log(player.getUsername(),
                            "" + player.getUsername() + " just IPBanned " + playersToBan.getUsername() + "!");
                    World.deregister(playersToBan);
                    if (!Objects.equals(player2.getUsername(), playersToBan.getUsername())) {
                        player.getPacketSender().sendConsoleMessage("Player " + playersToBan.getUsername()
                                + " was successfully IPBanned. Command logs written.");
                    }
                }
            }
        }
    }
}
