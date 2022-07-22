package com.arlania.world.content.combat.strategy.impl.customraids;



import com.arlania.util.Stopwatch;
import com.arlania.world.entity.impl.player.Player;
import lombok.Getter;

import java.util.ArrayList;

/**
 * rune-server kotlincode on 1/5/2021
 **/
public class CustomRaidsParty {

    private ArrayList<Player> partyMembers;

    @Getter
    public Player partyLeader;

    private CustomRaidsPartyStatus status;

    private Stopwatch joiningTimer;

    private int floor;

    public CustomRaidsParty(Player partyLeader) {
        this.partyLeader = partyLeader;
        this.partyMembers = new ArrayList<>(5);
        this.partyMembers.add(partyLeader);
        this.status = CustomRaidsPartyStatus.COLLECTING_MEMBERS;
        this.joiningTimer = new Stopwatch();
        this.floor = partyLeader.getIndex() * 4;
    }

    public Player getPartyLeader() {
        if (partyMembers.size() == 0) {
            return null;
        }
        return partyMembers.get(0);
    }


    public void addPlayer(Player toAdd) {
        partyMembers.add(toAdd);
    }

    public void setPartyLeader(Player partyLeader) {
        this.partyLeader = partyLeader;
    }

    public ArrayList<Player> getPartyMembers() {
        return partyMembers;
    }

    public CustomRaidsPartyStatus getStatus() {
        return status;
    }

    public void setStatus(CustomRaidsPartyStatus status) {
        this.status = status;
    }

    public boolean isLeader(Player player) {
        return player.getUsername().equalsIgnoreCase(partyLeader.getUsername());
    }

    public Stopwatch getJoiningTimer() {
        return joiningTimer;
    }

    public int getFloor() {
        return floor;
    }
}
