package com.arlania.Commands.impl.staff;

import com.arlania.Commands.Command;
import com.arlania.Commands.CommandInfo;
import com.arlania.util.Misc;
import com.arlania.world.World;
import com.arlania.world.entity.impl.player.Player;
import com.arlania.world.entity.impl.player.PlayerSaving;

import static com.arlania.model.PlayerRights.*;

@CommandInfo(
        command = {"giveitem"},
        description = "Gives a player an item.",
        rights = {ADMINISTRATOR, OWNER, DEVELOPER}
)
public class GiveItem extends Command {
    @Override
    public void execute(Player player, String command) {
        int item = Integer.parseInt(command.split("-")[1]);
        int amount = Integer.parseInt(command.split("-")[2]);
        String rss = command.split("-")[3];
        Player target = World.getPlayerByName(rss);
        if (target == null) {
            Player offlinePlayer = Misc.accessPlayer(rss);
            offlinePlayer.getInventory().add(item, amount);
            PlayerSaving.save(offlinePlayer);
        } else {
            target.getInventory().add(item, amount);
        }
    }
}
