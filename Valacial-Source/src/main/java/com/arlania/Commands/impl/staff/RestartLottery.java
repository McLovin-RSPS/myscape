package com.arlania.Commands.impl.staff;

import com.arlania.Commands.Command;
import com.arlania.Commands.CommandInfo;
import com.arlania.world.content.Lottery;
import com.arlania.world.entity.impl.player.Player;

import static com.arlania.model.PlayerRights.DEVELOPER;
import static com.arlania.model.PlayerRights.OWNER;

@CommandInfo(
        command = {"restartlottery", "resetlottery"},
        description = "Restarts the lottery.",
        rights = {OWNER, DEVELOPER}
)
public class RestartLottery extends Command {
    @Override
    public void execute(Player player, String command) {
        Lottery.restartLottery();
    }
}
