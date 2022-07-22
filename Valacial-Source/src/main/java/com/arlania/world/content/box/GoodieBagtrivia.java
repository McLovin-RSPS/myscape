package com.arlania.world.content.box;


import com.arlania.model.definitions.ItemDefinition;
import com.arlania.world.World;
import com.arlania.world.entity.impl.player.Player;

import java.util.Random;

/**
 * @Author Suic
 */

public class GoodieBagtrivia {

    private Player player;

    public GoodieBagtrivia(Player player) {
        this.player = player;
    }

    public boolean claimed = false;
    

    public int boxId = 8989;

    public int[] rewards  = {7118, 79, 5023, 21060, 2717, 11425, 11527, 4632, 7104, 8989, 11531, 5157, 15355, 2717, 3317, 7114, 7114, 4056, 12422, 455};

    public void setRewards(int[] rewards) {
        this.rewards = rewards;
    }

    public void open() {
    	player.setLastgoodiebox(8989);
    	player.getPacketSender().sendString(49210, "20x\\\\nAmazing Prize's!");
    	player.getPacketSender().sendString(49202, "ImaginePS GoodieBox @or2@(trivia)");
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
              //  World.sendMessage("Alert##Goodiebags##" + player.getUsername() + " has Got " +ItemDefinition.forId(rewards[player.selectedGoodieBag]).getName() +" as reward!" +"##From Goodiebox(Trivia) ");
                String announcement = "@red@[Trivia GoodieBox]@bla@ " + player.getUsername() + "@red@ has @bla@" +ItemDefinition.forId(rewards[player.selectedGoodieBag]).getName() +"@red@ as reward!";
                World.sendMessage(announcement);
                claimed = true;
                boxId = 8989;
            } else {
                player.sendMessage("@red@You need a Trivia goodiebox to claim the reward");
            }
        } else {
            player.sendMessage("@red@You've already claimed the reward for this box");
        }
    }
}
