package com.arlania.net.packet.impl;

import org.jboss.netty.buffer.ChannelBuffer;

import com.arlania.net.packet.*;
import com.arlania.util.Misc;
import com.arlania.world.content.PlayerPunishment;
import com.arlania.world.content.clan.ClanChatManager;
import com.arlania.world.entity.impl.player.Player;

/***
 * 
 * @author Coif
 *
 * Creation Date: Aug 31, 2018 - 2:12:52 AM
 */
public class SendClanChatMessagePacketListener implements PacketListener {

	@Override
	public void handleMessage(Player player, Packet packet) {
		/** Get method for the channel buffer. **/
		ChannelBuffer opcode = packet.getBuffer();
		/** Gets requested bytes from the buffer client > server **/
		int size = opcode.readableBytes();
		/** Check to flood **/
		if (size < 1 || size > 255) {
			System.err.println("blocked packet from sending from clan chat. Requested size="+size);
			return;
		}
		
		String clanMessage = packet.readString();
		/** Checks for null, invalid messages **/
		if(clanMessage == null || clanMessage.length() < 1 || clanMessage.length() > 255)
			return;
		
		if(PlayerPunishment.muted(player.getUsername()) || PlayerPunishment.IPMuted(player.getHostAddress())) {
			player.getPacketSender().sendMessage("You are muted and cannot chat.");
			return;
		}
		ClanChatManager.sendMessage(player, Misc.filterMessage(player, clanMessage));
	}

}