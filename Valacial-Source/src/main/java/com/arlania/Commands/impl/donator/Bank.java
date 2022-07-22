package com.arlania.Commands.impl.donator;

import com.arlania.model.Locations;
import com.arlania.model.PlayerRights;
import com.arlania.Commands.Command;
import com.arlania.Commands.CommandInfo;
import com.arlania.world.entity.impl.player.Player;

@CommandInfo(
        command = {"bank", "openbank"},
        description = "Access your bank from nearly anywhere",
        donationAmountRequired = 50
)
public class Bank extends Command {
    @Override
    public void execute(Player player, String command) {
        if (player.getLocation() == Locations.Location.FIGHT_PITS
                || player.getLocation() == Locations.Location.FIGHT_CAVES || player.getLocation() == Locations.Location.DUEL_ARENA
                || player.getLocation() == Locations.Location.RECIPE_FOR_DISASTER
                || player.getLocation() == Locations.Location.WILDERNESS
                || player.getLocation() == Locations.Location.FREE_FOR_ALL_ARENA
                || player.inFFALobby || player.inFFA) {
            player.getPacketSender().sendMessage("You can not open your bank here!");
            return;
        }
        player.getBank(player.getCurrentBankTab()).open();
    }
}
