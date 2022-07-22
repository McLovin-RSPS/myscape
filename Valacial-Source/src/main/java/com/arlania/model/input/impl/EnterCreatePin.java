package com.arlania.model.input.impl;
import com.arlania.model.input.Input;
import com.arlania.world.entity.impl.player.Player;

public class EnterCreatePin extends Input {

    @Override
    public void handleSyntax(Player player, String pin) {
        if (player.hasAccountPin() || pin == null) {
            player.sendMessage(pin == null ? "Try using a different pin! this one is invalid!" : "you already have an account pin!");
            return;
        }
        if (pin.length() != 4) {
            player.sendMessage("Your pin must be 4 characters long.");
            player.createAccountPin(true);
            return;
        }
        player.accountPin = pin;
        player.enteredPin = true;
        player.sendMessage("Success! your account pin is now: <col=ff0000>"+pin+"</col> make sure you remember it!");

    }
}

