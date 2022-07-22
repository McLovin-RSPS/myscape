package com.arlania.Commands.impl.staff;

import com.arlania.model.Item;
import com.arlania.model.container.impl.Bank;
import com.arlania.Commands.Command;
import com.arlania.Commands.CommandInfo;
import com.arlania.util.Misc;
import com.arlania.world.World;
import com.arlania.world.entity.impl.player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.arlania.model.PlayerRights.*;

@CommandInfo(
        command = {"checkbank"},
        description = "Check a player's bank.",
        rights = {ADMINISTRATOR, OWNER, DEVELOPER}
)
public class CheckBank extends Command {
    @Override
    public void execute(Player player, String command) {
        Player c = World.getPlayerByName(command.substring(10));
        if (c == null) {
            c = Misc.accessPlayer(command.substring(10));
        }
        List<Bank> tabs = Arrays.asList(c.getBanks());
        List<Item> items = new ArrayList<>();
        tabs.forEach(bankTab -> {
            bankTab.getValidItems().forEach((tabItem -> {
                items.add(tabItem);
            }));
        });
        for(int i = 0; i < items.size(); i++) {
            player.getPA().sendItemOnInterface(5382,items.get(i).getId(),i,items.get(i).getAmount());
        }
        player.getPA().sendInterface(5292);
    }
}
