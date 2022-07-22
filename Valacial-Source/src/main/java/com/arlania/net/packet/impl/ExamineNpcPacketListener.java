package com.arlania.net.packet.impl;

import com.arlania.model.PlayerRights;
import com.arlania.model.definitions.NpcDefinition;
import com.arlania.net.packet.Packet;
import com.arlania.net.packet.PacketListener;
import com.arlania.world.content.DropLookup;
import com.arlania.world.content.MonsterDrops;
import com.arlania.world.entity.impl.player.Player;
import com.ruseps.world.content.dropchecker.NPCDropTableChecker;

public class ExamineNpcPacketListener implements PacketListener {

	@Override
	public void handleMessage(Player player, Packet packet) {
		int npc = packet.readShort();
		if(npc <= 0) {
			return;
		}
		NpcDefinition npcDef = NpcDefinition.forId(npc);
		if(npcDef != null) {
			if(player.getRights() == PlayerRights.OWNER)
				player.sendMessage("This NPC has an ID off: " + npc);
			player.getPacketSender().sendMessage(npcDef.getExamine());
			//MonsterDrops.sendNpcDrop(player, npcDef.getId(), npcDef.getName().toLowerCase());
			DropLookup.openForNpc(player, npc);
			//NPCDropTableChecker.getSingleton().showNPCDropTable(player, npcDef.getId());
		}
	}

}
