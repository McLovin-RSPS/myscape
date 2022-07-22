package com.arlania.net.packet.impl;

import com.arlania.GameSettings;
import com.arlania.model.Item;
import com.arlania.model.definitions.ItemDefinition;
import com.arlania.net.packet.Packet;
import com.arlania.net.packet.PacketListener;
import com.arlania.util.Misc;
import com.arlania.world.content.priceguide.PriceGuide;
import com.arlania.world.entity.impl.player.Player;

public class PriceGuideListener implements PacketListener {
	private final static int MAXIMUM_ITEM_ID = 22694;

	public void processPacket(Player c, Packet packet) {
	int interfaceId = packet.readUnsignedShort();//packet.readUnsignedShort(); // short has a
							      // size of 2 bytes
	int itemSlot = packet.readUnsignedByte();//packet.readUnsignedByte(); // byte has a size of
							   // 1 byte
	int itemId = packet.readUnsignedShort();//packet.readUnsignedShort(); // short has a size of
							 // 2 bytes

	// this packet has a size of 5 if you add those up

	// cheat protection from pricing an invalid item

	int maximumSlots = c.isBanking ? 352 : 28;
	
	if (itemSlot < 0 || itemSlot > maximumSlots || itemId < 0 || itemId > MAXIMUM_ITEM_ID) {
	    c.getInventory().resetItems().refreshItems();
	    return;
	}

	try {
	    if (Item.tradeable(itemId)) {
		c.sendMessage("<col=255>This item is not priced because it is untradable.");
		return;
	    }
	    if (PriceGuide.getPrice(c, itemId) != 0) {
		c.sendMessage("<col=255>According to the price guide, "
			+ Misc.formatPlayerName(ItemDefinition.getItemName(itemId).replaceAll("_", " ")) + " costs @red@"
			+ Misc.format(PriceGuide.getPrice(c, itemId)) + " <col=255>1B Scrolls<col=255>"
			+ (c.getInventory().getAmount(itemId) > 1 ? " (@red@"
				+ Misc.format(PriceGuide.getPrice(c, itemId) * c.getInventory().getAmount(itemId))
				+ "<col=255>B Total)" : "")
			+ ".");
	    } else {
		c.sendMessage("<col=255>This item does not exist on the price guide.");
	    }
	} catch (Exception e) {
	    c.sendMessage("<col=255> Error, report to Staff.");
	}

    }

	@Override
	public void handleMessage(Player player, Packet packet) {
		// TODO Auto-generated method stub
		
	}

}