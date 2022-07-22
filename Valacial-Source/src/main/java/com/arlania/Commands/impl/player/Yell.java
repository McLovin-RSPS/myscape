package com.arlania.Commands.impl.player;

import com.arlania.model.GameMode;
import com.arlania.model.PlayerRights;
import com.arlania.Commands.Command;
import com.arlania.Commands.CommandInfo;
import com.arlania.world.World;
import com.arlania.world.content.PlayerPunishment;
import com.arlania.world.entity.impl.player.Player;

@CommandInfo(
        command = {"yell"},
        description = "Send a message to the entire world."
)
public class Yell extends Command {


    @Override
    public void execute(Player player, String command) {
        if (PlayerPunishment.muted(player.getUsername()) || PlayerPunishment.IPMuted(player.getHostAddress())) {
            player.getPacketSender().sendMessage("You are muted and cannot yell.");
            return;
        }
        int delay = player.getRights().getYellDelay();
        if (!player.getLastYell().elapsed((delay * 1000))) {
            player.getPacketSender().sendMessage(
                    "You must wait at least " + delay + " seconds between every yell-message you send.");
            return;
        }
        String yellMessage = command.substring(4, command.length());

        player.getLastYell().reset();
        if (player.getRights() == PlayerRights.MODERATOR && player.getUsername().equalsIgnoreCase("")) {
            World.sendMessage("" + player.getRights().getYellPrefix() + "<img=" + player.getRights().ordinal()
                    + "><col=006400> [Global Mod]</col> @bla@" + player.getUsername() + ":<col=5A8EBD>" + yellMessage);
            return;
        }
        if (player.getRights() == PlayerRights.ADMINISTRATOR && player.getUsername().equalsIgnoreCase("")) {
            World.sendMessage("" + player.getRights().getYellPrefix() + "<img=" + player.getRights().ordinal()
                    + "><col=006400> [Web Dev/Manager]</col> @bla@" + player.getUsername() + ":" + yellMessage);
            return;
        }
        if (player.getRights() == PlayerRights.OWNER) {
            World.sendMessage("" + player.getRights().getYellPrefix() + "<img=" + player.getRights().ordinal()
                    + ">@red@ [Owner] @bla@" + player.getUsername() + ":@bla@<shad=1>" + yellMessage);
            return;
        }			if (player.getRights() == PlayerRights.FORUM_DEVELOPER) {
            World.sendMessage("" + player.getRights().getYellPrefix() + "<img=10>@red@ [Forum Developer] @bla@" + player.getUsername() + ":@bla@<shad=1>" + yellMessage);
            return;
        }
        if (player.getRights() == PlayerRights.DEVELOPER) {
            World.sendMessage("" + player.getRights().getYellPrefix() + "<img=" + player.getRights().ordinal()
                    + ">@red@ [Developer] @bla@" + player.getUsername() + ":" + yellMessage);
            return;
        }
        if (player.getRights() == PlayerRights.SUPPORT) {
            World.sendMessage("" + player.getRights().getYellPrefix() + "<img=" + player.getRights().ordinal()
                    + ">@blu@ [Support] @bla@" + player.getUsername() + ":<col=00FF43><shad=1>" + yellMessage);
            return;
        }

        if (player.getRights() == PlayerRights.MODERATOR) {
            World.sendMessage("" + player.getRights().getYellPrefix() + "<img=" + player.getRights().ordinal()
                    + "><col=6600CC> [Moderator]</col> @bla@" + player.getUsername() + ":<col=F3C103><shad=1>" + yellMessage);
            return;
        }
        if (player.getRights() == PlayerRights.ADMINISTRATOR) {
            World.sendMessage("" + player.getRights().getYellPrefix() + "<img=" + player.getRights().ordinal()
                    + ">@or2@ [Administrator] @bla@" + player.getUsername() + ":<col=B8FF00><shad=1>" + yellMessage);
            return;
        }
        if (player.getRights() == PlayerRights.PARTNER_DONATOR) {
            World.sendMessage("" + player.getRights().getYellPrefix() + "<img=" + player.getRights().ordinal()
                    + "> @yel@<shad=1> [Partner]</shad> @bla@" + player.getUsername() + ":@yel@<shad=1>" + yellMessage);
            return;
        }
        if (player.getRights() == PlayerRights.CONTRIBUTER) {
            World.sendMessage("" + player.getRights().getYellPrefix() + "<img=" + player.getRights().ordinal()
                    + "> @dre@<shad=1> [Contributer]</shad> @bla@" + player.getUsername() + ":@dre@<shad=1>" + yellMessage);
            return;
        }
        if (player.getRights() == PlayerRights.EXECUTIVE_DONATOR) {
            World.sendMessage("" + player.getRights().getYellPrefix() + "<img=" + player.getRights().ordinal()
                    + "<shad=3> <img=9>[Executive]<shad=3> @bla@" + player.getUsername() + ":@bla@" + yellMessage);
            return;
        }// HERE
        if (player.getRights() == PlayerRights.VETERAN) {
            World.sendMessage("" + player.getRights().getYellPrefix() + "<img=" + player.getRights().ordinal()
                    + "> @whi@<shad=1> [Veteran]</shad> @bla@" + player.getUsername() + ":@whi@<shad=1>" + yellMessage);
            return;
        }
        if (player.getRights() == PlayerRights.DIVINE_DONATOR) {
            World.sendMessage("" + player.getRights().getYellPrefix() + "<img=" + player.getRights().ordinal()
                    + "@bla@ <img=10>[@yel@DIVINE@bla@]@yel@ @bla@" + player.getUsername() + ":@yel@"
                    + yellMessage);
            return;
        }
        if (player.getRights() == PlayerRights.SPONSOR_DONATOR) {
            World.sendMessage("" + player.getRights().getYellPrefix() + "<img=" + player.getRights().ordinal()
                    + "@mag@ <img=8>[Sponsor]@mag@ @bla@" + player.getUsername() + ":@mag@"
                    + yellMessage);
            return;
        }


        if (player.getRights() == PlayerRights.EXTREME_DONATOR) {
            World.sendMessage("" + player.getRights().getYellPrefix() + "<img=" + player.getRights().ordinal()
                    + "@blu@ <img=7>[Extreme]@blu@ @bla@" + player.getUsername() + ":@blu@"
                    + yellMessage);
            return;
        }
        if (player.getRights() == PlayerRights.SUPER_DONATOR) {
            World.sendMessage("" + player.getRights().getYellPrefix() + "<img=" + player.getRights().ordinal()
                    + "@or2@ <img=6>[Super]@or2@ @bla@" + player.getUsername() + ":@gre@"
                    + yellMessage);
            return;
        }
        if (player.getRights() == PlayerRights.DONATOR) {
            World.sendMessage("" + player.getRights().getYellPrefix() + "<img=" + player.getRights().ordinal()
                    + "@red@ <img=5>[Donator]@red@ @bla@" + player.getUsername() + ":@red@"
                    + yellMessage);
            return;
        }
        if (player.getRights() == PlayerRights.YOUTUBER) {
            World.sendMessage("" + player.getRights().getYellPrefix() + "<img=" + player.getRights().ordinal()
                    + ">@red@ [Youtuber] @bla@" + player.getUsername() + ":" + yellMessage);
            return;
        }
        if (player.getBetaTester()) {
            World.sendMessage("" + player.getRights().getYellPrefix() + "<img=" + player.getRights().ordinal()
                    + ">@or2@ [Beta tester] @or2@" + player.getUsername() + ":" + yellMessage);
            return;
        }
    }
}
