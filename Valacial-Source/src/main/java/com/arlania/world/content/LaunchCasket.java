package com.arlania.world.content;

import com.arlania.GameLoader;
import com.arlania.GameServer;
import com.arlania.engine.GameEngine;
import com.arlania.world.entity.impl.player.Player;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class LaunchCasket {
    private static final int ITEM_ID = 995; //change this after
    private static final Set<String> namesReceived = new HashSet<>();
    private static final Set<String> ipsReceived = new HashSet<>();
    private static final String IP_SAVES_FILE = "./data/saves/casketIps.txt";
    private static final String NAME_SAVES_FILE = "./data/saves/casketNames.txt";

    public static void tryGive(Player p) {
        if (namesReceived.contains(p.getUsername())) {
            return;
        }
        if (ipsReceived.contains(p.getHostAddress())) {
            return;
        }
        p.getInventory().add(ITEM_ID, 1);
        p.sendMessage("@red@You receive your launch casket!");
        namesReceived.add(p.getUsername());
        ipsReceived.add(p.getHostAddress());
        GameServer.getLoader().getEngine().submit(() -> {
            try {
                FileWriter fw = new FileWriter(IP_SAVES_FILE, true);
                fw.write(p.getHostAddress());
                fw.write(System.lineSeparator());
                fw.close();
                fw = new FileWriter(NAME_SAVES_FILE, true);
                fw.write(p.getUsername());
                fw.write(System.lineSeparator());
                fw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static void loadCasketsReceived() {
        try (BufferedReader in = new BufferedReader(new FileReader(new File(IP_SAVES_FILE)))) {
            ipsReceived.addAll(in.lines().collect(Collectors.toList()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader in = new BufferedReader(new FileReader(new File(NAME_SAVES_FILE)))) {
            namesReceived.addAll(in.lines().collect(Collectors.toList()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
