package com.arlania.Commands;

import java.lang.reflect.Modifier;

import com.arlania.GameServer;

import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;

public class LoadCommands {
    public static void run() {
        new FastClasspathScanner().matchSubclassesOf(Command.class, clazz -> {
            try {
                if (!Modifier.isAbstract(clazz.getModifiers())) {
                    synchronized (GameServer.class) {
                        CommandHandler.commands.add(clazz);
                    }
                }
            }catch(Exception e) {
               e.printStackTrace();
            }
        }).scan();
        System.out.println(CommandHandler.commands.size() + " commands loaded");
    }
}