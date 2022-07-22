package com.arlania.Commands.impl.staff;

import com.arlania.GameServer;
import com.arlania.engine.task.Task;
import com.arlania.engine.task.TaskManager;
import com.arlania.Commands.Command;
import com.arlania.Commands.CommandInfo;
import com.arlania.world.World;
import com.arlania.world.content.WellOfGoodwill;
import com.arlania.world.content.WellOfWealth;
import com.arlania.world.content.clan.ClanChatManager;
import com.arlania.world.content.grandexchange.GrandExchangeOffers;
import com.arlania.world.entity.impl.player.Player;

import static com.arlania.model.PlayerRights.DEVELOPER;
import static com.arlania.model.PlayerRights.OWNER;

@CommandInfo(
        command = {"update"},
        description = "Put the server into update state.",
        rights = {OWNER, DEVELOPER}
)
public class Update extends Command {
    @Override
    public void execute(Player player, String command) {
        int time = Integer.parseInt(command.split(" ")[1]);
        if (time > 0) {
            GameServer.setUpdating(true);
            for (Player players : World.getPlayers()) {
                if (players == null) {
                    continue;
                }
                players.getPacketSender().sendSystemUpdate(time);
            }
            TaskManager.submit(new Task(time) {
                @Override
                protected void execute() {
                    for (Player player : World.getPlayers()) {
                        if (player != null) {
                            World.savePlayers();
                            World.deregister(player);
                        }
                    }
                    WellOfGoodwill.save();
                    WellOfWealth.save();
                    GrandExchangeOffers.save();
                    ClanChatManager.save();
                    GameServer.getLogger().info("Update task finished!");
                    stop();
                }
            });
        }
    }
}
