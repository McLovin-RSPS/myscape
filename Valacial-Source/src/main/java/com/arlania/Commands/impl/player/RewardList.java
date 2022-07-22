package com.arlania.Commands.impl.player;

import com.arlania.Commands.Command;
import com.arlania.Commands.CommandInfo;
import com.arlania.rewardslist.RewardsHandler;
import com.arlania.world.entity.impl.player.Player;

@CommandInfo(
        command = {"rewardlist"},
        description = "Will open an interface to display rewards"
)
public class RewardList extends Command {


    @Override
    public void execute(Player player, String command) {
        new RewardsHandler(player).open(player);
    }
}
