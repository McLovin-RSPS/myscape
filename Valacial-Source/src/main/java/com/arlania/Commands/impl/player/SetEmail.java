package com.arlania.Commands.impl.player;

import com.arlania.Commands.Command;
import com.arlania.Commands.CommandInfo;
import com.arlania.world.content.PlayerPanel;
import com.arlania.world.entity.impl.player.Player;

@CommandInfo(
        command = {"setemail"},
        description = "Set an email for security."
)
public class SetEmail extends Command {
    @Override
    public void execute(Player player, String command) {
        String[] string = command.split(" ");
        String email = string[1];
        player.setEmailAddress(email);
        player.getPacketSender().sendMessage("You set your account's email adress to: [" + email + "] ");
        PlayerPanel.refreshPanel(player);
    }
}
