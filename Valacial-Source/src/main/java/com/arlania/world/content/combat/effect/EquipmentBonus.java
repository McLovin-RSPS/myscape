package com.arlania.world.content.combat.effect;

import com.arlania.model.container.impl.Equipment;
import com.arlania.world.content.combat.CombatType;
import com.arlania.world.entity.impl.player.Player;

public class EquipmentBonus {

	public static boolean wearingVoid(Player player, CombatType attackType) {
		int correctEquipment = 0;
		int helmet = attackType == CombatType.MAGIC ? MAGE_VOID_HELM :
						attackType == CombatType.RANGED ? RANGED_VOID_HELM : MELEE_VOID_HELM;
		for (int armour[] : VOID_ARMOUR) {
			if (player.getEquipment().getItems()[armour[0]].getId() == armour[1] ||
					player.getEquipment().getItems()[armour[0]].getId() == ELITE_VOID_ARMOUR[0]) {
				correctEquipment++;
			}
		}
		return correctEquipment >= 2 && player.getEquipment().getItems()[Equipment.HEAD_SLOT].getId() == helmet;
	}

	public static boolean wearingVoid2(Player player, CombatType attackType) {
		int correctEquipment = 0;
		int helmet = attackType == CombatType.MAGIC ? MAGE_VOID_HELM2 :
						attackType == CombatType.RANGED ? RANGED_VOID_HELM2 : MELEE_VOID_HELM2;
		for (int armour[] : VOID_ARMOUR2) {
			if (player.getEquipment().getItems()[armour[0]].getId() == armour[1] ||
					player.getEquipment().getItems()[armour[0]].getId() == ELITE_VOID_ARMOUR2[0]) {
				correctEquipment++;
			}
		}
		return correctEquipment >= 2 && player.getEquipment().getItems()[Equipment.HEAD_SLOT].getId() == helmet;
	}
		
	private static final int MAGE_VOID_HELM = 11663;
	
	private static final int RANGED_VOID_HELM = 11664;
	
	private static final int MELEE_VOID_HELM = 11665;
	

	public static final int[][] VOID_ARMOUR = {
		{Equipment.BODY_SLOT, 8839},
		{Equipment.LEG_SLOT, 8840},
		{Equipment.HANDS_SLOT, 8842}
	};
	
	public static final int[] ELITE_VOID_ARMOUR = {
		8839,
		8840,
		8842
	};

	private static final int MAGE_VOID_HELM2 = 21058;

	private static final int RANGED_VOID_HELM2 = 21057;

	private static final int MELEE_VOID_HELM2 = 21056;


	public static final int[][] VOID_ARMOUR2 = {
		{Equipment.BODY_SLOT, 19787},
		{Equipment.LEG_SLOT, 19788},
		{Equipment.HANDS_SLOT, 21059}
	};

	public static final int[] ELITE_VOID_ARMOUR2 = {
			19787,
			19788,
			21059
	};
}
