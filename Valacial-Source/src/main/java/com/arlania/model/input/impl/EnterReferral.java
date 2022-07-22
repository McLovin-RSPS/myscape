package com.arlania.model.input.impl;

import com.arlania.model.input.Input;
import com.arlania.world.entity.impl.player.Player;

import java.io.*;
import java.util.Scanner;


import java.util.Arrays;

public class EnterReferral extends Input {

    public static final String[] refs = {"bomby"}; // codes here

    @Override
    public void handleSyntax(Player player, String syntax) {
        if (checkIps(player.getHostAddress())) {
            player.getPacketSender().sendMessage("You have already received a referral reward!");
            return;
        }
        if(player.getSkillManager().getTotalLevel() < 200) {
            player.sendMessage("You must have a total level of atleast 200 to claim a refferal code.");
            return;
        }
            referralResponse(player, syntax);
        try {
            BufferedWriter w = new BufferedWriter(new FileWriter("data/referral_data.txt", true));
            w.write(player.getHostAddress());
            w.newLine();
            w.flush();
            w.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void referralResponse(Player player, String username) {
            player.getInventory().add(12421, 1);
            player.hasReferral = true;
            player.sendMessage("@red@Congrats! Because you used the code " + username + " You have gotten a reward!");
        }

    public static boolean checkIps(String ip) {
        File file = new File("data/referral_data.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String content = null;
        while(scanner.hasNext()) {
            content = scanner.nextLine();
            if (ip.equalsIgnoreCase(content)) {
                return true;
            }
        }
        return false;
    }
}