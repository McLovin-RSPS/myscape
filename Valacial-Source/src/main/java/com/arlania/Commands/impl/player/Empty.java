package com.arlania.Commands.impl.player;

import com.arlania.Commands.Command;
import com.arlania.Commands.CommandInfo;
import com.arlania.world.entity.impl.player.Player;

@CommandInfo(
        command = {"empty", "clearinv"},
        description = "Delete all items from your inventory.."
)
public class Empty extends Command {
    @Override
    public void execute(Player player, String command) {
        player.getPacketSender().sendInterfaceRemoval().sendMessage("You clear your inventory.");
        player.getSkillManager().stopSkilling();
        player.getInventory().resetItems().refreshItems();
    }
}
