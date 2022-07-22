package com.arlania.net.packet.impl;

/**
 * @author Devilspawn
 */

import com.arlania.model.PlayerRights;
import com.arlania.model.Position;
import com.arlania.model.definitions.NpcDefinition;
import com.arlania.net.packet.Packet;
import com.arlania.net.packet.PacketListener;
import com.arlania.util.Misc;
import com.arlania.world.content.transportation.TeleportHandler;
import com.arlania.world.entity.impl.player.Player;

public class PvmInterfaceHandler implements PacketListener {

    public enum PvmData {
        // monsters
        YODA_YODA(1265, new int[] {21002, 21004, 21030, 21030, 1067, 1115, 1153, 21011, 21012, 2572, 20200, 1543},
                new Position(2660, 3045)),
        ARTILLERY(2437, new int[] {6199, 21045},
                new Position(1827, 4511, 0)),
        GODZILLA(1678, new int[] {21021 ,21013, 20087, 20088, 20086, 21020, 20252, 20201},
                new Position(3561, 9948)),
        DEADLY_ASSASSIN(1880, new int[] {20105, 20103, 20104, 20106, 20107, 20102, 20253, 20202},
                new Position(3169, 2982)),
        DONKEY_KONG(1459, new int[] {21060, 13196, 13198, 13197, 13207, 13206, 20254, 2098, 20203},
                new Position(2793, 2773)),
        OBLIVION(6102, new int[] {20259, 20225, 3684, 3685, 3683, 20204},
                new Position(1747, 5323)),
        NEO_CORTEX(4392, new int[] {20695, 20700, 20701, 20703, 20704, 20702, 20706, 20208},
                new Position(2704, 9756)),
        RICK_MORTHY(9786, new int[] {12421, 20096, 20097, 20095, 20100, 20098, 20099, 20256, 20603, 20604, 20605, 20205, 11425},
                new Position(2089, 4455)),
        TOXIC(7843, new int[] {3717, 3321, 3322, 3323, 4636, 4637},
                new Position(3860, 2831)),
        GODZILA(9236, new int[] {3651, 3652, 3653, 3654, 3655, 20510},
                new Position(3127, 4373)),
        VOLDEMORT(7643, new int[] {5152, 5153, 5154, 5155, 5156, 5157},
                new Position(1928, 5002)),
        // bosses
        ABBADON(6303, new int[] {3313, 3314, 3315, 3316, 3317},
                new Position(2516, 5173)),
        INFERNAL_GROUDON(7567, new int[] {3318, 3319, 3320, 3321, 3322},
                new Position(1240, 1233)),
        BAPHOMET(2236, new int[] {3819, 3820, 3821, 3822, 3823, 3824, 3825},
                new Position(2461, 10156)),
        ZORO(7552, new int[] {4641, 4643, 4642, 4644, 4645}, //muskeetit
                new Position(2792, 3791)),
        DEATH1(8453, new int[] {},
                new Position(3027, 5234)),
        VORAGO(8766, new int[] {5163, 5164, 5165, 5166, 5167},
                new Position(2863, 3723)),
        GOLD_KBD(50, new int[] {},
                new Position(3057, 5198)),
        COSMETIC(8733, new int[] {7108},
                new Position(2969, 2579)),
        DARTHVAIDER(8721, new int[] {},
                new Position(1886, 5462)),
        MYSTERIO(7535, new int[] {},
                new Position(2721, 4897)),
        ANGELIC(9201, new int[] {5131, 5132, 5133, 5135, 5136, 5137, 5138, 2717},
                new Position(2076, 4830)),
        FLUFFY(1999, new int[] {3076, 3077, 3289, 3290, 3291, 3292, 3293, 3294},
                new Position(2596, 2588)),
        MANDARA(7532, new int[] {4056},
                new Position(2892, 2731)),
        SEPHIROTH(9325, new int[] {3809, 3810, 3811, 3812, 3813, 3814},
                new Position(3049, 2905)),
        INFINITY(7563, new int[] {925, 923, 924, 927, 926, 922},
                new Position(1891, 4513)),
        SKOTIZO(8754, new int[] {5083, 5085, 5086, 5087, 5088, 5089, 5084},
                new Position(3038, 5346)),
        SHADOW(6754, new int[] {},
                new Position(3282, 9836)),
        THANOS(9202, new int[] {4060, 4629, 4630, 4631, 4632, 4633, 4634},
                new Position(1632, 4702)),
        CHAOS(3200, new int[] {911, 910, 909, 912, 913, 903},
                new Position(2463, 4782)),
        BARREL(5666, new int[] {4057, 4058, 4059, 937},
                new Position(2975, 9515, 1)),
        CORP(8133, new int[] {931, 932, 933, 934, 929},
                new Position(3281, 4311)),
        KRT(6442, new int[] {4774, 4775, 4776, 4777, 4779},
                new Position(1752, 5224)),
        JOKER(2518, new int[] {},
                new Position(2139, 5100)),
        LUGIA(6432, new int[] {},
                new Position(2036, 4497)),
        BRODY(9200, new int[] {},
                new Position(2402, 3488)),
        SEXYSOPHIA(6432, new int[] {},
        		new Position(2402, 3488)),
        REDPURGE(9200, new int[] {},
        		new Position(2402, 3488)),
        SKYMASTER(9003, new int[] {},
                new Position(2449, 2848)),
        
        
        
        //NECROMANCER(499, new int[] {18939, 18938, 18937}, true,
               //new Position(2204, 4841)),
      // PUMMELER(10141, new int[] {18891},
             //  new Position(1, 1)),

        // raids
        PEST_CONTROLL(3789, new int[] {},
        		new Position(2657, 2649)),
        POWERRAIDS(8548, new int[] {},
        		new Position(3666, 2976, 0)),
        AFK(8002, new int[] {},
        		new Position(3694, 2967, 0)),
        VOTE(6305, new int[] {},
        		new Position(3702, 2971, 0)),
        HALLOWEEN(8239, new int[] {},
        		new Position(3808, 3479)),
        NARUTO(1, new int[] {},
        		new Position(2402, 3488)),
        SLAYER_TOWER(2787, new int[] {},
        		new Position(3429, 3538)),
        
        ;

        private Position[] positions; // pick a random location from this array
        private int npcId;
        private int[] drops;
        private PlayerRights requiredRights = PlayerRights.PLAYER;
        private int pvmPoints;

        PvmData(int npcId, int[] drops, Position... pos) {
            this.npcId = npcId;
            this.drops = drops;
            this.positions = pos;
            this.pvmPoints = this.ordinal();
        }
        PvmData(int npcId, int[] drops , PlayerRights requiredRights, Position... positions) {
            this(npcId, drops, positions);
            this.requiredRights = requiredRights;
        }
        PvmData(int npcId, int[] drops , boolean multi, Position... positions) {
            this(npcId, drops, positions);
            NpcDefinition.forId(npcId).setMulti(multi);
        }

        public int getNpcId() {
            return npcId;
        }

        public Position getPosition() {
            return positions[Misc.randomMinusOne(positions.length)];
        }

        public int[] getDrops() { return this.drops; }

        public static PvmData forNpcId(int id) {
            PvmData[] teleData = values();
            for (PvmData data : teleData) {
                if (data.getNpcId() == id)
                    return data;
            }
            return null;
        }

        public PlayerRights getRequiredRights() {
            return requiredRights;
        }

        public int getPvmPoints() {
            return pvmPoints;
        }
    }

    @Override
    public void handleMessage(Player player, Packet packet) {
        int npcId = packet.readShort();
        PvmData data = PvmData.forNpcId(npcId);
        Position telePos = data.getPosition();
        if (!player.getRights().isAboveOrEqual(data.getRequiredRights())) {
            player.getPacketSender().sendMessage("this NPC requires status: @red@" + data.getRequiredRights().getYellPrefix() +
                    data.getRequiredRights() + "");
        } else {
            if (telePos == null) {
                System.out.println(player.getUsername() + " has requested a null teleport position from the interface?!");
                player.getPacketSender().sendMessage("coming soon, please be patient..");
            } else {
                TeleportHandler.spellBookTeleport(player, telePos);
            }
        }
    }

    public static void handleNpcKill(Player player, int npcId) {
        PvmData data = PvmData.forNpcId(npcId);
        if (data != null) {
            int receivedPoints = Misc.random(data.getPvmPoints());
            player.getPointsHandler().setPvmPoints(player.getPointsHandler().getPvmPoints() + receivedPoints);
            if (receivedPoints > 0)
                player.getPacketSender().sendMessage("You received @red@" + receivedPoints + " Pvm points");
        }
    }

    public static int OPCODE = 170;
}
