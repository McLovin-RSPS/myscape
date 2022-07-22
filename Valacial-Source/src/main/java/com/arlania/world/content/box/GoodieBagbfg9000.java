package com.arlania.world.content.box;


import com.arlania.model.definitions.ItemDefinition;
import com.arlania.world.World;
import com.arlania.world.entity.impl.player.Player;

import java.util.Random;

/**
 * @Author Suic
 */

public class GoodieBagbfg9000 {

    private Player player;

    public GoodieBagbfg9000(Player player) {
        this.player = player;
    }

    public boolean claimed = false;


    public int boxId = 3569;//3578

    public int[] rewards = {898, 15356, 15356, 15356, 15356, 15356, 15356, 15356, 15356, 15356, 15356, 15356, 15356, 15356, 15356, 15357, 15357, 15357, 15357, 15357};

    public void setRewards(int[] rewards) {
        this.rewards = rewards;
    }

    public void open() {
        player.setLastgoodiebox(3569);
        player.getPacketSender().sendString(49210, "14x $10 Bonds\\\\n5x $25 Bonds\\\\n1x Jackpot");
        player.getPacketSender().sendString(49202, "ImaginePS GoodieBox @red@(Bfg9000)");
        player.getPacketSender().sendInterface(49200);
        player.getPacketSender().resetItemsOnInterface(49270, 20);
        shuffle(rewards);
        claimed = false;
        player.selectedGoodieBag = -1;
        for (int i = 1; i <= 20; i++) {
            player.getPacketSender().sendString(49232 + i, String.valueOf(i));
        }
    }

    public void shuffle(int[] array) {
        Random rnd = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            int a = array[index];
            array[index] = array[i];
            array[i] = a;
        }
    }

    private void showRewards() {

        for (int i = 1; i <= 20; i++) {
            player.getPacketSender().sendString(49232 + i, "");
        }

        for (int i = 0; i < rewards.length; i++) {
            player.getPacketSender().sendItemOnInterface(49270, rewards[i], i, 1);
        }
    }

    public boolean handleClick(int buttonId) {
        if (!(buttonId >= -16325 && buttonId <= -16306)) {
            return false;
        }

        if (claimed) {
            return false;
        }

        int index = -1;

        if (buttonId >= -16325) {
            index = 16325 + buttonId;
        }
        player.getPacketSender().sendString(49232 + player.selectedGoodieBag + 1,
                String.valueOf(player.selectedGoodieBag + 1));
        player.selectedGoodieBag = index;
        player.getPacketSender().sendString(49232 + index + 1, " Pick");

        return true;
    }
    // btw this code is also easy to use, u see how clean it is

    public void claim() {
        if (player.selectedGoodieBag == -1) {
            player.sendMessage("@red@You haven't picked a number yet");
            return;
        }

        if (boxId == -1) {
            player.sendMessage("You don't have the required box for that goodiebag");
            return;
        }
        if (!claimed) {
            if (player.getInventory().contains(boxId)) { // gg this is guaranteed to work
                showRewards();
                player.getInventory().delete(boxId, 1);
                player.getInventory().add(rewards[player.selectedGoodieBag], 1);
                // World.sendMessage("Alert##Goodiebags##" + player.getUsername() + " has Got " +ItemDefinition.forId(rewards[player.selectedGoodieBag]).getName() +" as reward!" +"##From Goodiebox(bfg-9000) ");
                String announcement = "@red@[Bfg-9000 GoodieBox]@bla@ " + player.getUsername() + "@red@ has @bla@" +ItemDefinition.forId(rewards[player.selectedGoodieBag]).getName() +"@red@ as reward!";
                World.sendMessage(announcement);
                claimed = true;
                boxId = 3569;
            } else {
                player.sendMessage("@red@You need a Bfg-9000 goodiebox to claim the reward");
            }
        } else {
            player.sendMessage("@red@You've already claimed the reward for this box");
        }
    }
}
