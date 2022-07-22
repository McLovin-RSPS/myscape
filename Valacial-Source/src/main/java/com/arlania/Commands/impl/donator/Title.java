package com.arlania.Commands.impl.donator;

import com.arlania.GameSettings;
import com.arlania.model.Flag;
import com.arlania.Commands.Command;
import com.arlania.Commands.CommandInfo;
import com.arlania.util.NameUtils;
import com.arlania.world.entity.impl.player.Player;

import java.util.Arrays;

@CommandInfo(
        command = {"title", "settitle"},
        description = "Allows you to set a custom title.",
        donationAmountRequired = 25
)
public class Title extends Command {
    @Override
    public void execute(Player player, String command) {
        String title = command.split("-")[1];

        if (title == null || title.length() <= 2 || title.length() > 9 || !NameUtils.isValidName(title)) {
            player.getPacketSender().sendMessage("You can not set your title to that!");
            return;
        }
        // overriden permmited strings
        switch (player.getRights()) {
            case ADMINISTRATOR:
                for (String s : GameSettings.INVALID_NAMES) {
                    if (Arrays.asList(admin).contains(s.toLowerCase())) {
                        continue;
                    }
                    if (title.toLowerCase().contains(s.toLowerCase())) {
                        player.getPacketSender().sendMessage("Your title contains an invalid tag.");
                        return;
                    }
                }
                break;
            case MODERATOR:
                for (String s : GameSettings.INVALID_NAMES) {
                    if (Arrays.asList(mod).contains(s.toLowerCase())) {
                        continue;
                    }
                    if (title.toLowerCase().contains(s.toLowerCase())) {
                        player.getPacketSender().sendMessage("Your title contains an invalid tag.");
                        return;
                    }
                }
                break;
            // permitted to use whatever they'd like
            case OWNER:
            case DEVELOPER:
                break;
            default:
                for (String s : GameSettings.INVALID_NAMES) {
                    if (title.toLowerCase().contains(s.toLowerCase())) {
                        player.getPacketSender().sendMessage("Your title contains an invalid tag.");
                        return;
                    }
                }
                break;
        }
        player.setTitle("@or2@" + title);
        player.getUpdateFlag().flag(Flag.APPEARANCE);
    }

    private static final String[] admin = {"admin", "administrator", "a d m i n"};
    private static final String[] mod = {"mod", "moderator", "m o d"};
}
