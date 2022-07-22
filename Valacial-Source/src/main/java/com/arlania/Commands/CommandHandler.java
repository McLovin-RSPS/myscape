package com.arlania.Commands;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.arlania.model.PlayerRights;
import com.arlania.net.packet.Packet;
import com.arlania.net.packet.PacketListener;
import com.arlania.util.Misc;
import com.arlania.world.entity.impl.player.Player;

public class CommandHandler implements PacketListener {
	    public static List<Class> commands = new ArrayList<>();
	    @Override
	    public void handleMessage(Player player, Packet packet) {

	        String command = Misc.readString(packet.getBuffer());
	        System.out.println(command);

	        if (command.contains("\r") || command.contains("\n")) {
	            return;
	        }
	        Command c;
	        try {
	            for(Class clazz : commands) {
	                if(clazz.isAnnotationPresent(CommandInfo.class)) {
	                    Annotation annotation = clazz.getAnnotation(CommandInfo.class);
	                    CommandInfo commandInfo = (CommandInfo) annotation;
	                    String[] realCommand;
	                    if(command.contains(" ") && command.contains("-")) {
	                        int spaceIndex = command.indexOf(" ");
	                        int dashIndex = command.indexOf("-");
	                        if(spaceIndex < dashIndex) {
	                            realCommand = command.split(" ");
	                        } else {
	                            realCommand = command.split("-");
	                        }
	                    } else if(command.contains(" ")) {
	                        realCommand = command.split(" ");
	                    } else if(command.contains("-")) {
	                        realCommand = command.split("-");
	                    } else {
	                        realCommand = new String[]{command};
	                    }
	                    List<String> possibleCommands = Arrays.asList(commandInfo.command());
	                    if(possibleCommands.contains(realCommand[0])) {
	                    	System.out.println("command found");
	                        c = (Command) clazz.newInstance();
	                        List<PlayerRights> rights = Arrays.asList(commandInfo.rights());
	                        if(rights.contains(PlayerRights.PLAYER) || player.getRights().shouldDebug() || rights.contains(player.getRights())) {
	                            if (player.getAmountDonated() >= ((CommandInfo) annotation).donationAmountRequired()) {
	                                c.execute(player, command);
	                                return;
	                        } else {
	                                player.sendMessage("You need a total donated value of $" + commandInfo.donationAmountRequired() + " to use this command.");
	                            }
	                        }
	                    }
	                }
	            }
	        } catch (Exception exception) {
	            exception.printStackTrace();
	                player.getPacketSender().sendMessage("Error executing that command.");
	        }
	    }
	}
