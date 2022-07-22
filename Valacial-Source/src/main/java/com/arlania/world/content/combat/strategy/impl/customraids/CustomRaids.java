package com.arlania.world.content.combat.strategy.impl.customraids;

import com.arlania.GameSettings;
import com.arlania.engine.task.Task;
import com.arlania.engine.task.TaskManager;
import com.arlania.model.GameObject;
import com.arlania.model.Position;
import com.arlania.util.Misc;
import com.arlania.world.World;
import com.arlania.world.content.dialogue.Dialogue;
import com.arlania.world.content.dialogue.DialogueExpression;
import com.arlania.world.content.dialogue.DialogueManager;
import com.arlania.world.content.dialogue.DialogueType;
import com.arlania.world.content.transportation.TeleportHandler;
import com.arlania.world.entity.impl.npc.NPC;
import com.arlania.world.entity.impl.player.Player;
import lombok.Getter;
import lombok.Setter;

/**
 * rune-server kotlincode on 1/5/2021
 **/
public class CustomRaids {

    private CustomRaidsParty party;

    private Player player;

    private static Position ENTRANCE_POSITION = new Position(3670, 3219, 0);
    @Getter @Setter
    private CustomRaidsFight fight = CustomRaidsFight.MADARA;


    public boolean madaraDead;
    public boolean susakeDead;
    public boolean oroDead;


    public CustomRaids(Player player) {
        this.player = player;
    }


    public boolean isObject(GameObject gameObject) {
        if(party == null)
            return false;

        switch(gameObject.getId()) {
            case 332_653:

                if(!party.isLeader(player)) {
                    if(party.getStatus() == CustomRaidsPartyStatus.COLLECTING_MEMBERS) {
                        player.sendMessage("The party leader has not yet started the raid.");
                        return true;
                    } else if(party.getStatus() == CustomRaidsPartyStatus.STARTED_MEMBERS_JOINING) {
                        DialogueManager.start(player, new Dialogue() {

                            @Override
                            public DialogueType type() {
                                return DialogueType.OPTION;
                            }

                            @Override
                            public DialogueExpression animation() {
                                return null;
                            }

                            @Override
                            public String[] dialogue() {
                                return new String[] {
                                        "Enter Theatre of Blood",
                                        "Cancel",
                                };
                            }

                            @Override
                            public boolean action(int option) {
                                player.getPA().closeDialogueOnly();
                                switch(option) {
                                    case 1:
                                        goToRoom(5);
                                        break;
                                }
                                return false;
                            }

                        });
                        return true;
                    }
                    return true;
                }

                DialogueManager.start(player, new Dialogue() {

                    @Override
                    public DialogueType type() {
                        return DialogueType.OPTION;
                    }

                    @Override
                    public DialogueExpression animation() {
                        return null;
                    }

                    @Override
                    public String[] dialogue() {
                        return new String[] {
                                "Start Theatre of Blood",
                                "Cancel",
                        };
                    }

                    @Override
                    public boolean action(int option) {
                        player.getPA().closeDialogueOnly();
                        switch(option) {
                            case 1:
                                openRaids();
                                break;
                        }
                        return false;
                    }

                });
                break;
        }
        return false;
    }
    public void openRaids() {
        if(party.getStatus() != CustomRaidsPartyStatus.COLLECTING_MEMBERS) {
            return;
        }
        if(party.getPartyMembers().size() > 1) {
            sendMessageToParty("The raids is now open to joining. You have 60 seconds to join before it starts.");
        }

        goToRoom(0);
        party.setStatus(CustomRaidsPartyStatus.STARTED_MEMBERS_JOINING);
        party.getJoiningTimer().reset();
    }
    public void attemptInvite(Player toInvite) {
        DialogueManager.start(player, new Dialogue() {

            @Override
            public DialogueType type() {
                return DialogueType.OPTION;
            }

            @Override
            public DialogueExpression animation() {
                return null;
            }

            @Override
            public String[] dialogue() {
                return new String[] {
                        "Invite "+toInvite.getUsername(),
                        "Cancel",
                };
            }

            @Override
            public boolean action(int option) {
                player.getPA().closeDialogueOnly();
                switch(option) {
                    case 1:
                        invite(toInvite);
                        break;
                }
                return false;
            }

        });
    }

    public void invite(Player toInvite) {
        if(toInvite == null) {
            player.sendMessage("You have attempted to invite an invalid player.");
            return;
        }


        DialogueManager.start(toInvite, new Dialogue() {

            @Override
            public DialogueType type() {
                return DialogueType.OPTION;
            }

            @Override
            public DialogueExpression animation() {
                return null;
            }

            @Override
            public String[] dialogue() {
                return new String[] {
                        "Join "+player.getUsername()+"'s Raiding Party",
                        "Cancel",
                };
            }

            @Override
            public boolean action(int option) {
                toInvite.getPA().closeDialogueOnly();
                switch(option) {
                    case 1:
                        if(player == null) {
                            toInvite.sendMessage("The party leader that invited you has now logged out.");
                            return false;
                        }

                        toInvite.getCustomRaids().joinParty(party);

                        break;
                }
                return false;
            }

        });
    }
    public void handleNextDoor(){
        Player partyLeader =getParty().getPartyLeader();
        switch (partyLeader.getCustomRaids().getFight()) {
            case MADARA:
                if (partyLeader.getCustomRaids().madaraDead) {
                    goToRoom(1);
                }
                break;
            case SASUKE:
                if (partyLeader.getCustomRaids().susakeDead) {
                    goToRoom(2);
                }
                break;
            case ORO:
                if (partyLeader.getCustomRaids().oroDead) {
                    goToRoom(3);
                }
                break;
        }
    }


    public void goToRoom(int bossLevel) {

        this.fight = CustomRaidsFight.values()[bossLevel];

        if (bossLevel == 0) {//MAIDEN
            party.setStatus(CustomRaidsPartyStatus.STARTED_NO_MORE_JOINING);

        }

        player.moveTo(new Position(CustomRaidsConstants.THEATRE_PATH[bossLevel].getX(),CustomRaidsConstants.THEATRE_PATH[bossLevel].getY(),CustomRaidsConstants.THEATRE_PATH[bossLevel].getZ()+party.getFloor()));

        player.sendMessage("Welcome to the room of " + Misc.formatPlayerName(fight.toString()) + ".");



        TaskManager.submit(new Task(5, player, true) {

            @Override
            protected void execute() {
                if(party == null) {
                    forceEnd();
                    stop();
                    return;
                }

                if(!getParty().isLeader(player)){
                    stop();
                    return;
                }
                NPC npc = null;

                fight.getSpawnPosition().setZ(party.getFloor());

                npc = new NPC(fight.getNpcId(),fight.getSpawnPosition());


                World.register(npc);

                stop();
            }

        });
    }
    public void joinParty(CustomRaidsParty party) {
        this.party = party;

        if(!this.party.getPartyMembers().contains(player)) {
            this.party.getPartyMembers().add(player);
        }

        refreshAll();
    }


    public CustomRaidsParty getParty() {
        return this.party;
    }

    public void refreshAll() {
        if(this.party != null) {
            for(Player partyMember : party.getPartyMembers()) {
                if(partyMember != null) {
                    partyMember.getCustomRaids().displayMembers();
                }
            }
        }
    }

    public void displayParty() {
        player.getPacketSender().sendTabInterface(GameSettings.QUESTS_TAB, 65000).sendTab(GameSettings.QUESTS_TAB);
        player.getPacketSender().sendTab(GameSettings.QUESTS_TAB);
        if(player.getCustomRaids().getParty()!=null) {
            displayMembers();
        }else{
            player.getPacketSender().sendString(65009, "Create");
            int id = 65016;
            for (int i = 65016; i < 65064; i++) {
                id++;
                player.getPacketSender().sendString(id++, "");
                player.getPacketSender().sendString(id++, "");
                player.getPacketSender().sendString(id++, "");
            }
            player.getPacketSender().sendString(65002, "Raiding Party: @whi@0");
        }
    }

    public void forceEnd() {
        sendMessageToParty("The party has ended due to the raid leader logging out.");

        for(Player partyMember : party.getPartyMembers()) {
            if(partyMember == null)
                continue;
            teleportToEntrance();
        }
    }

    public void sendMessageToParty(String message) {
        for(Player partyMember : party.getPartyMembers()) {
            if(partyMember == null)
                continue;
            partyMember.sendMessage("<col=830303>"+message);
        }
    }


    public void teleportToEntrance() {
        TeleportHandler.teleportPlayer(player, ENTRANCE_POSITION, player.getSpellbook().getTeleportType());
    }

    public void displayMembers() {

        player.getPacketSender().sendTabInterface(GameSettings.QUESTS_TAB, 65000).sendTab(GameSettings.QUESTS_TAB);
        for (Player member : party.getPartyMembers()) {
            if (member != null) {
                member.getPacketSender().sendString(65009, "Invite");

                int start = 65016;
                for (int i = 0; i < party.getPartyMembers().size(); i++) {
                    start++;
                    member.getPacketSender().sendString(start++, "" + party.getPartyMembers().get(i).getUsername());
                    member.getPacketSender().sendString(start++,
                            "" + party.getPartyMembers().get(i).getSkillManager().getTotalLevel());
                    member.getPacketSender().sendString(start++,
                            "" + party.getPartyMembers().get(i).getSkillManager().getCombatLevel());
                }

                for (int i = start; i < 65064; i++) {
                    start++;
                    member.getPacketSender().sendString(start++, "");
                    member.getPacketSender().sendString(start++, "");
                    member.getPacketSender().sendString(start++, "");
                }

                member.getPacketSender().sendString(65002, "Raiding Party: @whi@" + party.getPartyMembers().size());
            }
        }
        player.getPacketSender().sendInteractionOption("Invite", 2, true);
    }
}
