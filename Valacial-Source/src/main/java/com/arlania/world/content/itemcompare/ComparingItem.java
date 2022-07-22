package com.arlania.world.content.itemcompare;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.arlania.model.Item;
import com.arlania.model.definitions.ItemDefinition;
import com.arlania.world.entity.impl.player.Player;

public class ComparingItem {

	private final int INTERFACE_ID = 0;

	protected Player player;

	public ComparingItem(Player player) {
		this.player = player;
	}

	public void open() {
		player.getPacketSender().sendInterface(INTERFACE_ID);
	}

	private double[] comparing1 = new double[18];
		
	private double[] comparing2 = new double[18];
	
	public static boolean allLess(double[] comparing1, double[] comparing2) {
	  //  boolean sameLength = (comparing1.length == comparing2.length);
	//    if (!sameLength)
	  //      return false;
	    boolean digitDifference = true;
	    for (int i = 0; i <= comparing1.length - 1 && digitDifference; i++) {
	        digitDifference = (comparing1[i] < comparing2[i]);
	    }
	    System.err.println("" + digitDifference);
	    return digitDifference;
	}
	
	public boolean handleComparing(int usedWithSlot, Item comparedAgaisnt) {
		ItemDefinition itemDef = ItemDefinition.forId(usedWithSlot);
		ItemDefinition itemDef_comared = ItemDefinition.forId(comparedAgaisnt.getId());

		if (comparedAgaisnt != null) {
			for (int i = 0; i < itemDef.getBonus().length; i++) {
				for (int z = 0; z < itemDef_comared.getBonus().length; z++) {
					itemDef.getBonus()[i] = comparing1[i];
					itemDef.getBonus()[z] = comparing2[z];
					allLess(comparing1, comparing2);
				}
			}
		}
		return false;
	}
}
