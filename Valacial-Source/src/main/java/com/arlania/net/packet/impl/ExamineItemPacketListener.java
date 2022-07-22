package com.arlania.net.packet.impl;

import com.arlania.GameSettings;
import com.arlania.model.Item;
import com.arlania.model.Skill;
import com.arlania.model.definitions.ItemDefinition;
import com.arlania.net.packet.Packet;
import com.arlania.net.packet.PacketListener;
import com.arlania.util.Misc;
import com.arlania.world.content.priceguide.PriceGuide;
import com.arlania.world.entity.impl.player.Player;

public class ExamineItemPacketListener implements PacketListener {
	public static boolean Examniation = false;
	@Override 
	public void handleMessage(Player player, Packet packet) {
		int item = packet.readShort();
		if(item == 995 || item == 18201) {
			player.getPacketSender().sendMessage("Mhmm... Shining coins...");
			return;
		}
		ItemDefinition itemDef = ItemDefinition.forId(item);
		if(itemDef != null) {
			player.getPacketSender().sendMessage(itemDef.getDescription());
			for (Skill skill : Skill.values()) {
				if (itemDef.getRequirement()[skill.ordinal()] > player.getSkillManager().getMaxLevel(skill)) {
					player.getPacketSender().sendMessage("@red@WARNING: You need " + new StringBuilder().append(skill.getName().startsWith("a") || skill.getName().startsWith("e") || skill.getName().startsWith("i") || skill.getName().startsWith("o") || skill.getName().startsWith("u") ? "an " : "a ").toString() + Misc.formatText(skill.getName()) + " level of at least " + itemDef.getRequirement()[skill.ordinal()] + " to wear this.");
				}
			}
			if(Examniation) {
			handleExaminationInterface(player, item);
			handlePriceCheck(player, item);
			} else {
				handlePriceCheck(player,item);
			}
		}
	}

	
	public void handlePriceCheck(Player player, int itemID) {
		try {
		    if (!Item.tradeable(itemID)) {
			player.sendMessage("<col=255>This item is not priced because it is untradable.");
			return;
		    }
		    if (PriceGuide.getPrice(player, itemID) != 0) {
			player.sendMessage("<col=255>According to the price guide, "
				+ Misc.formatPlayerName(ItemDefinition.getItemName(itemID).replaceAll("_", " ")) + " costs @red@"
				+ Misc.format(PriceGuide.getPrice(player, itemID)) + " <col=255>1B Tickets<col=255>"
				+ (player.getInventory().getAmount(itemID) > 1 ? " (@red@"
					+ Misc.format(PriceGuide.getPrice(player, itemID) * player.getInventory().getAmount(itemID))
					+ "<col=255>B Total)" : "")
				+ ".");
		    } else {
			player.sendMessage("<col=255>This item does not exist on the price guide.");
		    }
		} catch (Exception e) {
		    player.sendMessage("<col=255> Error, report to Staff.");
		}
	}
	
	public void handleExaminationInterface(Player player, int itemId) {
		int count = 52103;
		ItemDefinition itemDef = ItemDefinition.forId(itemId);
		player.getPacketSender().sendItemOnInterface(52102,  itemId, 1);
		player.getPacketSender().sendString(52113, itemDef.getName());
		
		player.getPacketSender().sendString( count++, "Equipement: " + itemDef.getEquipmentType());
		player.getPacketSender().sendString( count++, "Stackable: " + itemDef.isStackable());
		player.getPacketSender().sendString( count++, "Noted: " + itemDef.isNoted());
		player.getPacketSender().sendString( count++, "High Alch: " +  PriceGuide.getPrice(player, itemId / 2));
		player.getPacketSender().sendString( count++, "Low Alch: " +  PriceGuide.getPrice(player, itemId / 4));
		player.getPacketSender().sendString( count++, "Weapon: " + itemDef.isWeapon());
		player.getPacketSender().sendString( count++, "Two Handed: " + itemDef.isTwoHanded());

		player.getPacketSender().sendString(52143, "Value: " + PriceGuide.getPrice(player, itemId) + "B");

		String[] text = { "Stab", "Slash", "Crush", "Magic", "Range" };
		for (int i = 0; i < 5; i++) {
			player.getPacketSender().sendString(52131 + i, text[i] + ": " + String.valueOf(itemDef.getBonus()[0 + i]));
			player.getPacketSender().sendString(52138 + i,text[i] + ": " + String.valueOf(itemDef.getBonus()[5 + i]));
		}

		player.getPacketSender().sendString(52144, "Strength: " + String.valueOf(itemDef.getBonus()[10]));
		player.getPacketSender().sendString(52145, "Prayer: " + String.valueOf(itemDef.getBonus()[11]));
		player.getPacketSender().sendInterface(52100);
	}
	
	public static String formatCoins(int amount) {
		if (amount > 9999 && amount <= 9999999) {
			return (amount / 1000) + "K";
		} else if (amount > 9999999 && amount <= 999999999) {
			return (amount / 1000000) + "M";
		} else if (amount > 999999999) {
			return (amount / 1000000000) + "B";
		}
		return String.valueOf(amount);
	}
}
