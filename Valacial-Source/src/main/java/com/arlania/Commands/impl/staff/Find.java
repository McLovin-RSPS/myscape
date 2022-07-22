package com.arlania.Commands.impl.staff;

import com.arlania.model.definitions.ItemDefinition;
import com.arlania.Commands.Command;
import com.arlania.Commands.CommandInfo;
import com.arlania.world.entity.impl.player.Player;

import static com.arlania.model.PlayerRights.*;

@CommandInfo(
        command = {"find", "findid", "getitem", "getid"},
        description = "Get an item ID.",
        rights = {ADMINISTRATOR, OWNER, DEVELOPER}
)
public class Find extends Command {
    @Override
    public void execute(Player player, String command) {
        for (ItemDefinition def : ItemDefinition.getDefinitions()) {
            if (def != null) {
                if (def.getName() != null) {
                    if (def.getName().toLowerCase().contains(command.split(" ")[1].toLowerCase())) {
                        player.sendMessage("@red@Found: " + def.getName() + " - " + def.getId());
                    }
                }
            }
        }
    }
}
