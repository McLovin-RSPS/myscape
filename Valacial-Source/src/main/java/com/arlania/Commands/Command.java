package com.arlania.Commands;

import java.util.logging.Logger;

import com.arlania.world.entity.impl.player.Player;

public class Command {
    protected Logger logger = Logger.getLogger(super.getClass().getName());
    public void execute(Player player, String command) {};
}
