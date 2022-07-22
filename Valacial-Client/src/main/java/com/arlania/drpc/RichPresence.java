//package com.arlania.drpc;
//
//
//import club.minnced.discord.rpc.DiscordEventHandlers;
//import club.minnced.discord.rpc.DiscordRPC;
//import club.minnced.discord.rpc.DiscordRichPresence;
//
//public class RichPresence {
//
//	private final static String CLIENT_ID = "721984615459454996";
//
//    private static DiscordRPC lib;
//    private static DiscordRichPresence presence;
//    public static double gameversion = 1.0;
//
//    public static void initiate() {
//        lib = DiscordRPC.INSTANCE;
//        DiscordEventHandlers handlers = new DiscordEventHandlers();
//        lib.Discord_Initialize(CLIENT_ID, handlers, true, "");
//        presence = new DiscordRichPresence();
//        presence.startTimestamp = System.currentTimeMillis() / 1000;
//        presence.largeImageKey = "img";
//        presence.largeImageText = "Playing ImaginePS!!";
//        presence.smallImageText = "V1.0";
//        presence.smallImageKey = "img1";
//        presence.details = "In Loading Screen";
//        updatePresence();
//
//        new Thread(() -> {
//            while (!Thread.currentThread().isInterrupted()) {
//                lib.Discord_RunCallbacks();
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException ignored) {}
//            }
//        }, "RPC-Callback-Handler").start();
//    }
//
//    public boolean presenceIsNull() {
//        return presence == null;
//    }
//
//    public static void updateDetails(String details) {
//        presence.details = details;
//        updatePresence();
//    }
//
//    public void updateState(String state) {
//        presence.state = state;
//        updatePresence();
//    }
//
//    public void updateSmallImageKey(String key) {
//        presence.smallImageKey = key;
//        updatePresence();
//    }
//
//    private static void updatePresence() {
//        lib.Discord_UpdatePresence(presence);
//    }
//
//}
