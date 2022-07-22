package com.arlania.Commands.impl.donator;

import com.arlania.model.Item;
import com.arlania.model.container.impl.Bank;
import com.arlania.Commands.Command;
import com.arlania.Commands.CommandInfo;
import com.arlania.world.entity.impl.player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@CommandInfo(
        command = {"sortbank", "organisebank"},
        description = "Will sort your bank into 1 tab, items are sorted from A-Z",
        donationAmountRequired = 500
)
public class SortBank extends Command {

    @Override
    public void execute(Player player, String command) {
        List<Bank> tabs = Arrays.asList(player.getBanks());
        List<Item> items = new ArrayList<>();
        tabs.forEach(bankTab -> {
            bankTab.getValidItems().forEach((tabItem -> {
                items.add(tabItem);
            }));
        });
        items.sort((o1, o2) -> o1.getDefinition().getName().compareTo(o2.getDefinition().getName()));
        for(int i = 0; i < 9; i++) {
            player.setBank(i, new Bank(player));
        }
        items.forEach(item -> {
            player.getBank(0).add(item);
        });
    }
}
