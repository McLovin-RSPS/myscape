package com.arlania.model.input.impl;

import com.arlania.engine.task.Task;
import com.arlania.engine.task.TaskManager;
import com.arlania.model.input.Input;
import com.arlania.world.entity.impl.player.Player;

public class EnterAccountPin extends Input {

    @Override
    public void handleSyntax(Player player, String accountPin) {
        if (accountPin.equalsIgnoreCase(player.accountPin)) {
            player.enteredPin = true;
            player.lastIP = player.getHostAddress();
            player.sendMessage("Pin correctly entered");
            return;
        }
        TaskManager.submit(new Task(1, player, false) {
            @Override
            protected void execute() {
                player.promptPin(true);
                stop();
            }
        });

    }
}
