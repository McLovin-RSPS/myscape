package com.arlania.world.content.skill.impl.summoning;

import com.arlania.world.content.skill.impl.summoning.BossPets.BossPet;
import com.arlania.world.entity.impl.npc.NPC;
import com.arlania.world.entity.impl.player.Player;

public class BossPets {

	public enum BossPet {
		
		PET_CHAOS_ELEMENTAL(3200, 3033, 11995),
		PET_KING_BLACK_DRAGON(50, 3030, 11996),
		PET_GENERAL_GRAARDOR(6260, 3031, 11997),
		PET_TZTOK_JAD(2745, 3032, 11978),
		PET_CORPOREAL_BEAST(8133, 3034, 12001),
		PET_KREE_ARRA(6222, 3035, 12002),
		PET_KRIL_TSUTSAROTH(6203, 3036, 12003),
		PET_COMMANDER_ZILYANA(6247, 3037, 12004),
		PET_DAGANNOTH_SUPREME(2881, 3038, 12005),
		PET_DAGANNOTH_PRIME(2882, 3039, 12006),
		PET_DAGANNOTH_REX(2883, 3040, 11990),
		PET_FROST_DRAGON(51, 3047, 11991),
		PET_TORMENTED_DEMON(8349, 3048, 11992),
		PET_KALPHITE_QUEEN(1158, 3050, 11993),
		PET_SLASH_BASH(2060, 3051, 11994),
		PET_PHOENIX(8549, 3052, 11989),
		PET_BANDOS_AVATAR(3053, 3053, 11988),
		PET_NEX(13447, 3054, 11987),
		PET_JUNGLE_STRYKEWYRM(9467, 3055, 11986),
		PET_DESERT_STRYKEWYRM(9465, 3056, 11985),
		PET_ICE_STRYKEWYRM(9463, 3057, 11984),
		PET_GREEN_DRAGON(941, 3058, 11983),
		PET_BABY_BLUE_DRAGON(52, 3059, 11982),
		PET_BLUE_DRAGON(55, 3060, 11981),
		PET_BLACK_DRAGON(54, 3061, 11979),
		PET_SKOTIZO(7286, 3062, 2100),
		//PET_CERBERUS(1999, 3063, 2101),
		PET_ABYSSAL_SIRE(5886, 4415, 2102),
		PET_THERMY(499, 4416, 2099),
		PET_ASSASIN(1301, 1301, 2097),
		PET_GODZILLA(1300, 1300, 2096),
		PET_HELLBAT(923, 923, 2091),
		PET_MONKEY(1453, 1453, 2090),
		PET_CENTAUR(4438, 4438, 2092),
		PET_CYRISUS(433, 5896, 2094),
		PET_YODA(1266, 1266, 5189),
		PET_DIRE_WOLF(141, 141, 2093),
		PET_NUTELLA(6301, 6301, 2103),
		PET_ABBADON(6304, 6304, 2104),
		PET_ADAMANT_DRAGON(6500, 6500, 2105),
		PET_ROCK_CRAB(6502, 6502, 2107),
		PET_RUNITE_DRAGON(6501, 6501, 2106),
		PET_RIFTSPLITTER(9910, 9910, 2108),
		PET_PUMMELER(10140, 10140, 2109),
		PET_BLOODCHILLER(10038, 10038, 2110),
		PET_LIGHTCREATURE(4325, 4325, 2771),
		PET_ROOSTER(2312, 2312, 2772),
		PET_PIG(2316, 2316, 3620),
		PET_DOG(98, 98, 3621),
		PET_HOBO(341, 341, 3622),
		PET_HELLKITTEN(3505, 3505, 7583),
		PET_MORTY(6353, 6353, 19935),
		PET_CHILLI(6354, 6354, 19936),
		PET_LUIGI(6359, 6359, 2758),
		PET_MRKRABS(6362, 6362, 2759),
		PET_SONIC(6363, 6363, 2760),
		PET_HOMER(6364, 6364, 2761),
		PET_PIKACHU(6366, 6366, 2762),
		PET_MAYONAISE(6355, 6355, 19937),
		PET_SEPER(8543, 8543, 8172),
		PET_WORLD(4638, 4638, 8651),
		PET_LUCIFER(8727, 8727, 6117),
		PET_MAGMA(6317, 6317, 9362),
		PET_MAGMA1(6318, 6318, 9363),
		PET_PUFFER(7542, 7542, 7841),
		PET_HELLCAT2(9864, 9864, 5123),
		PET_DRAGON(7543, 7543, 3989),
		PET_VORAGO(8622, 8622, 9382),
		PET_STAR(9865, 9865, 3881),
		THANOS(8382, 8382, 3883),
		PHOENIX(8632, 8632, 3886),
		MINIYODA(6871, 6871, 3887),
		SKELE(7823, 7823, 3888),
		ANT(8734, 8734, 3889),
		SCARRY(9347, 9347, 3890),
		FIREHYD(8972, 8972, 3891),
		CHARMAND(8438, 8438, 3892),
		BOB(9845, 9845, 4279),
		CALL(8976, 8976, 4280),
		PIKA(8387, 8387, 5079),
		DONAT(8388, 8388, 5080),
		//KRAB(8389, 8389, 5081),
		//SONIC(8390, 8390, 5082),
		TREX(9674, 9674, 1667),
		PET_HELLCAT(7953, 7953, 3882),
		KANGOO(6757, 6757, 3885),
		VORAGO_ICY(6529, 6529, 8673),
		PET_PANDA(7527, 7527, 3884),
		//legendary beast

		ENTEI(6542, 6542, 19691),
		RAIKOU(9676, 9676, 9676),
		SUICUNE(9677, 9677, 9677);

		private final int npcId; 
		private final int spawnNpcId; 
		private final int itemId;
		
		BossPet(int npcId, int spawnNpcId, int itemId) {
			this.npcId = npcId;
			this.spawnNpcId = spawnNpcId;
			this.itemId = itemId;
		}
		
		public static BossPet forId(int itemId) {
			for(BossPet p : BossPet.values()) {
				if(p != null && p.getItemId() == itemId) {
					return p;
				}
			}
			return null;
		}
		
		public static BossPet forSpawnId(int spawnNpcId) {
			for(BossPet p : BossPet.values()) {
				if(p != null && p.getSpawnNpcId() == spawnNpcId) {
					return p;
				}
			}
			return null;
		}

		public int getSpawnNpcId() {
			return spawnNpcId;
		}

		public int getItemId() {
			return itemId;
		}
	}
	
	public static boolean pickup(Player player, NPC npc) {
		BossPet pet = BossPet.forSpawnId(npc.getId());
		if(pet != null) {
			if(player.getSummoning().getFamiliar() != null && player.getSummoning().getFamiliar().getSummonNpc() != null && player.getSummoning().getFamiliar().getSummonNpc().isRegistered()) {
				if(player.getSummoning().getFamiliar().getSummonNpc().getId() == pet.getSpawnNpcId() && player.getSummoning().getFamiliar().getSummonNpc().getIndex() == npc.getIndex()) {
					if(player.getInventory().getFreeSlots() <= 0) {
						player.sendMessage("Please first clear up some space in your inventory :");
						return false;
					}
					player.getSummoning().unsummon(true, true);
					player.getPacketSender().sendMessage("You attempt to pick up your pet...");
					return true;
				} else {
					player.getPacketSender().sendMessage("This is not your pet to pick up.");
				}
			} else {
				player.getPacketSender().sendMessage("This is not your pet to pick up.");
			}
		}
		return false;
	}
	
}
