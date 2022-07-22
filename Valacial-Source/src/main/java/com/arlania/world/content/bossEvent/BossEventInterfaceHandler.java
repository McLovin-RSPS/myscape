package com.arlania.world.content.bossEvent;

import com.arlania.world.entity.impl.player.Player;

/**
 * 
 * @author Adam_#6723
 *
 */

public class BossEventInterfaceHandler {
	
	protected Player player;
	
	public BossEventInterfaceHandler(Player player) {
		this.player = player;
	}
	
	private final static BossEventData information[] = BossEventData.values();
	
	private final int ITEM_GROUP_ID = 37418;
	
	private final int INTERFACE_ID = 37400;
	
	public void open() {
		sendStrings();
		player.getPacketSender().sendInterface(INTERFACE_ID);
	}
	
	public void sendStrings() {
		for(BossEventData data : information) {
			if(player.getBossevent() != null) {
			player.getPA().sendFrame126("Task Name: " + data.getName(), 37409);
			player.getPA().sendFrame126("Task Combat level: " + data.getCombatLevel(), 37410);
			player.getPA().sendFrame126("Task Health: " + data.getHealth(), 37411);
			player.getPA().sendFrame126("Task Maxhit: " + data.getMaxhit(), 37412);
			player.getPA().sendFrame126("Task Weakness: " + data.getWeakness(), 37413);
			player.getPA().sendFrame126("Task Amount: " + data.getEndamount(), 37415);
			player.getPacketSender().sendItemOnInterface(ITEM_GROUP_ID, 607, 0, 1);
			} else {
				System.err.println("Shits null?");
			}
		}
	}
	
	public boolean button(int buttonid) {
		if(buttonid == 146045) {
			new BossEventHandler().teleport(player);
			return true;
		}
		return false;
	}

}
