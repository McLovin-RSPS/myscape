package com.arlania.Commands.impl.player;

import com.arlania.Commands.Command;
import com.arlania.Commands.CommandInfo;
import com.arlania.util.NameUtils;
import com.arlania.world.entity.impl.player.Player;

@CommandInfo(
        command = {"changepassword", "setpassword", "changepw", "setpw"},
        description = "Set up a new password."
)
public class ChangePassword extends Command {
    @Override
    public void execute(Player player, String command) {
        String syntax = command.split(" ")[1];
        if (syntax == null || syntax.length() <= 2 || syntax.length() > 15 || !NameUtils.isValidName(syntax)) {
            player.getPacketSender().sendMessage("That password is invalid. Please try another password.");
            return;
        }
        if (syntax.contains("_")) {
            player.getPacketSender().sendMessage("Your password can not contain underscores.");
            return;
        }
        player.setPassword(syntax);
        player.getPacketSender().sendMessage("Your new password is: [" + syntax + "] Write it down!");

    }
}
