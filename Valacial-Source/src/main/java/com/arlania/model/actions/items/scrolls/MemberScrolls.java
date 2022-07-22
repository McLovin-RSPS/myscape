package com.arlania.model.actions.items.scrolls;

import com.arlania.model.GameMode;
import com.arlania.model.PlayerRights;
import com.arlania.util.Misc;
import com.arlania.world.content.PlayerPanel;
import com.arlania.world.entity.impl.player.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public enum MemberScrolls {
    $1(15354, 1),
    $5(15355, 5),
    $10(15356, 10),
    $25(15357, 25),
    $50(15358, 50),
    $100(15359, 100),
    $1000(15360, 1000),
    $2000(15361, 2000),
    $3000(15362, 3000);


    int itemId, scrollAmount;

    MemberScrolls(int itemId, int scrollAmount) {
        this.itemId = itemId;
        this.scrollAmount = scrollAmount;
    }

    static Map<Integer, MemberScrolls> scrolls = new HashMap<>();

    static {
        for (MemberScrolls scroll : MemberScrolls.values()) {
            scrolls.put(scroll.getItemId(), scroll);
        }
    }

    public int getItemId() {
        return itemId;
    }

    public int getScrollAmount() {
        return scrollAmount;
    }

    public static boolean claimScroll(Player player, int itemId) {

        MemberScrolls scroll = scrolls.get(itemId);

        if (Objects.isNull(scroll)) {
            return false;
        }

        String name = scroll.name().toLowerCase().replace("_", " ");

        //Checking if the player is doing something.
        if (player.busy()) {
            player.getPacketSender().sendMessage("You cannot perform this action right now. " +
                    "Please finish what you are doing first.");
            return false;
        } else {//If the player is not doing anything then this code will be read.
            player.getInventory().delete(scroll.getItemId(), 1);
            int points = scroll.getScrollAmount();
            //Updating points.
            player.incrementAmountDonated(points);//Updating the total donated all in all.
            //Setting the points based on the scroll.
            player.getPointsHandler().setDonationPoints(points, true);
            //Sending a messaged with the scroll name and points claimed.
            player.getPacketSender().sendMessage("You claim your "
                    + name + ". Your total funds have been updated.");
            //Checking if the player has enough points to move on to the next rank.
            checkForRank(player);
            //Refreshing the player panel ("Quest tab") to update the points gained.
            PlayerPanel.refreshPanel(player);
            return true;
        }
    }

    public static void checkForRank(Player player) {
        //Fetching the total amount a person has donated and storing the information to a new variable.
        int amountDonated = player.getAmountDonated();
        //Storing string as no reason to duplicate.
        String noRankUpdate = "Your rank did not update due to being ";
        //Setting the rights to null so we can perform our rank check.
        PlayerRights rights = null;
        //Setting the players rank based on the amount donated.
        if (amountDonated >= 25) {
            rights = PlayerRights.DONATOR;
        }
        if (amountDonated >= 50) {
            rights = PlayerRights.SUPER_DONATOR;
        }
        if (amountDonated >= 150) {
            rights = PlayerRights.EXTREME_DONATOR;
        }
        if (amountDonated >= 300) {
            rights = PlayerRights.SPONSOR_DONATOR;
        }
        if (amountDonated >= 800) {
            rights = PlayerRights.EXECUTIVE_DONATOR;
        }
        if (amountDonated >= 1500) {
            rights = PlayerRights.DIVINE_DONATOR;
        }
        //Checking if the player has the right already or they are a game mode that the rank cannot be changed.
        if (rights != null && rights != player.getRights() && !player.getRights().isStaff()
                && player.getGameMode() != GameMode.IRONMAN
                && player.getGameMode() != GameMode.HARDCORE_IRONMAN) {
            //Sending a message to the player if they are eligible for the rank.
            player.getPacketSender().sendMessage("You've become a "
                    + Misc.formatText(rights.toString().toLowerCase()) + "! Congratulations!");
            //Setting the players new rank
            player.setRights(rights);
            //Updating the players rank once the rank is set.
            player.getPacketSender().sendRights();
            //Checking if the players game mode is anything other than a normal player.
        } else if (player.getGameMode() == GameMode.IRONMAN
                || player.getGameMode() == GameMode.HARDCORE_IRONMAN) {
            //If the player is not a normal player then they cannot have a rank update.
            player.getPacketSender().sendMessage(noRankUpdate
                    + player.getGameMode().name().toLowerCase().replace("_", " ") + ".");
        } else {//Checking to see if the member is staff because that is the only rank left to check.
            player.getPacketSender().sendMessage(noRankUpdate + player.getRights().name().toLowerCase() + ".");
            //If the player is indeed a staff member then they will not have a rank update.
        }
    }
}