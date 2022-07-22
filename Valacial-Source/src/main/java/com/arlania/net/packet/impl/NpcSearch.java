package com.arlania.net.packet.impl;


import com.arlania.model.input.Input;
import com.arlania.world.content.DropLookup;
import com.arlania.world.entity.impl.player.Player;

public class NpcSearch extends Input {

    @Override
    public void handleSyntax(Player player, final String syntax) {
        if(syntax.length() < 3) {
            player.sendMessage("You must type atleast 3 letters");
            return;
        }
        if(!player.getClickDelay().elapsed(600)) {
            return;
        }
        player.getClickDelay().reset();
        DropLookup.search(player, syntax, DropLookup.SearchPolicy.NPC);
    }
}
