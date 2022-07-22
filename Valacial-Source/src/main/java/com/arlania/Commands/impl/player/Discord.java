package com.arlania.Commands.impl.player;

import java.awt.Color;

import com.arlania.GameSettings;
import com.arlania.Commands.Command;
import com.arlania.Commands.CommandInfo;
import com.arlania.world.World;
import com.arlania.world.content.discordbot.DiscordBot;
import com.arlania.world.entity.impl.player.Player;

import net.dv8tion.jda.core.EmbedBuilder;

@CommandInfo(
        command = {"discord"},
        description = "Allows you to join our discord server"
)
public class Discord extends Command {

    @Override
    public void execute(Player player, String command) {
        player.getPacketSender().sendString(1, "https://discord.gg/evcd6dM");
    }
}
