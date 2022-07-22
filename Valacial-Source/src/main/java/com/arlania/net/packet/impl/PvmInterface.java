package com.arlania.net.packet.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.arlania.util.Stopwatch;
import com.arlania.world.World;//ididnt know it bans players no icant ionly can type
import com.arlania.world.entity.impl.player.Player;

public class PvmInterface {
	public static void reset(Player player) {
		player.resetInterfaces();
	
	}//keep this its a gift 

	public static void showInterface(Player player) {
		player.getPacketSender().sendString(57003, "Players:  @gre@"+(int)(World.getPlayers().size() )+"").sendInterface(45800);
	
	}
}
