package com.arlania.net.packet.impl;

public enum ZonesEnum {

    Link(7567,100,6303),
    Bapho(2236,100,7567),
    Assassin(1999,150,2236),
    DeadBone(9201,150,1999),
    Blood(9325,150,9201),
    Samurai(7563,200,9325),
    Thanos(9202,200,7563),
    Storm(8133,200,9202),
    Barrelchest(5666,250,8133),
    Chaos(3200,250,5666),
    Devil(6442,250,3200),
    Blast(8754,300,6442),
    RoseEater(6754,300,8754),
    ETERNAL_MELEE(8,300,6754),
    ETERNAL_RANGED(11,300,6754),
    ETERNAL_MAGIC(10,300,6754),

    joker(2518,350,6754),
    darth(8721,350,6583),
    cloud(6432,350,8721),
    sora(9200,400,6432),
    Stormtrooper(6583,400,2518),
    mysterio(7535,400,9200),
    WhiteBeard(9003,400,7535),
    MagmasatanicDevil(10141,500,9003);
    //Madara(7532,500,2236);

    //npc u gonna attack
    int npcId;
    //npc acount u need to attack it
    int kill_count;
    //npc count amount of x npc u need
    int kill_count_npcId;

    private ZonesEnum(int npcId, int kill_count, int kill_count_npcId){
       this.npcId = npcId;
       this.kill_count = kill_count ;
       this.kill_count_npcId = kill_count_npcId;
    }


   public static ZonesEnum getZonesEnumByNpcId(int npcId){
               for(ZonesEnum zone : ZonesEnum.values()) {
                   if(zone.npcId == npcId) {
                       return zone;
                   }
               }
       return null;

    }

    public static ZonesEnum getZonesEnumByKillCountNpc(int npcId){
        for(ZonesEnum zone : ZonesEnum.values()) {
            if(zone.kill_count_npcId == npcId) {
                return zone;
            }
        }
        return null;

    }

    public int getKill_count() {
        return kill_count;
    }

    public int getKill_count_npcId() {
        return kill_count_npcId;
    }

    public int getNpcId() {
        return npcId;
    }
}