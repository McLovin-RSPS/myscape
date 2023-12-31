package com.arlania.world.content.skill.impl.dungeoneering;

import java.util.concurrent.CopyOnWriteArrayList;

import com.arlania.GameSettings;
import com.arlania.model.Flag;
import com.arlania.model.GroundItem;
import com.arlania.model.PlayerRights;
import com.arlania.model.Locations.Location;
import com.arlania.model.Position;
import com.arlania.model.Skill;
import com.arlania.util.Misc;
import com.arlania.world.World;
import com.arlania.world.content.PlayerPanel;
import com.arlania.world.content.dialogue.DialogueManager;
import com.arlania.world.content.dialogue.impl.DungPartyInvitation;
import com.arlania.world.entity.impl.GroundItemManager;
import com.arlania.world.entity.impl.npc.NPC;
import com.arlania.world.entity.impl.player.Player;

/**
 * @author Gabriel Hannason
 */
public class DungeoneeringParty {

	public DungeoneeringParty(Player owner) {
		this.owner = owner;
		player_members = new CopyOnWriteArrayList<Player>();
		player_members.add(owner);
	}

	private Player owner;
	private DungeoneeringFloor floor;
	private int complexity = -1;
	private CopyOnWriteArrayList<Player> player_members;
	private CopyOnWriteArrayList<NPC> npc_members = new CopyOnWriteArrayList<NPC>();
	private CopyOnWriteArrayList<GroundItem> ground_items = new CopyOnWriteArrayList<GroundItem>();
	private Position gatestonePosition;
	private boolean hasEnteredDungeon;
	private int kills, deaths;
	private boolean killedBoss;

	public void invite(Player p) {
		if(getOwner() == null || p == getOwner())
			return;
		if(hasEnteredDungeon) {
			getOwner().getPacketSender().sendMessage("You cannot attemptInvite anyone right now.");
			return;
		}
		if(player_members.size() >= 5) {
			getOwner().getPacketSender().sendMessage("Your party is full.");
			return;
		}
		if(p.getLocation() != Location.RAID || p.isTeleporting()) {
			getOwner().getPacketSender().sendMessage("That player is not in Deamonheim.");
			return;
		}
		if(player_members.contains(p)) {
			getOwner().getPacketSender().sendMessage("That player is already in your party.");
			return;
		}
		if(p.getMinigameAttributes().getDungeoneeringAttributes().getParty() != null) {
			getOwner().getPacketSender().sendMessage("That player is currently in another party.");
			return;
		}
		if(p.getRights() != PlayerRights.DEVELOPER && System.currentTimeMillis() - getOwner().getMinigameAttributes().getDungeoneeringAttributes().getLastInvitation() < 2000) {
			getOwner().getPacketSender().sendMessage("You must wait 2 seconds between each party invitation.");
			return;
		}
		if(p.busy()) {
			getOwner().getPacketSender().sendMessage("That player is currently busy.");
			return;
		}
		getOwner().getMinigameAttributes().getDungeoneeringAttributes().setLastInvitation(System.currentTimeMillis());
		DialogueManager.start(p, new DungPartyInvitation(getOwner(), p));
		getOwner().getPacketSender().sendMessage("An invitation has been sent to "+p.getUsername()+".");
	}

	public void add(Player p) {
		if(player_members.size() >= 5) {
			p.getPacketSender().sendMessage("That party is already full.");
			return;
		}
		if(hasEnteredDungeon) {
			p.getPacketSender().sendMessage("This party has already entered a dungeon.");
			return;
		}
		if(p.getLocation() != Location.RAID || p.isTeleporting()) {
			return;
		}
		sendMessage(""+p.getUsername()+" has joined the party.");
		p.getPacketSender().sendMessage("You've joined "+getOwner().getUsername()+"'s party.");
		player_members.add(p);
		p.getMinigameAttributes().getDungeoneeringAttributes().setParty(owner.getMinigameAttributes().getDungeoneeringAttributes().getParty());
		p.getPacketSender().sendTabInterface(GameSettings.QUESTS_TAB, Dungeoneering.PARTY_INTERFACE);
		p.getPacketSender().sendDungeoneeringTabIcon(true);
		p.getPacketSender().sendTab(GameSettings.QUESTS_TAB);
		refreshInterface();
	}

	public void remove(Player p, boolean resetTab, boolean fromParty) {
		if(fromParty) {
			player_members.remove(p);
			if(resetTab) {
				p.getPacketSender().sendTabInterface(GameSettings.QUESTS_TAB,26600);
				p.getPacketSender().sendDungeoneeringTabIcon(false);
				p.getPacketSender().sendTab(GameSettings.QUESTS_TAB);
			} else {
				p.getPacketSender().sendTabInterface(GameSettings.QUESTS_TAB, Dungeoneering.FORM_PARTY_INTERFACE);
				p.getPacketSender().sendDungeoneeringTabIcon(true);
				p.getPacketSender().sendTab(GameSettings.QUESTS_TAB);
			}
		}
		p.getPacketSender().sendInterfaceRemoval();
		if(p == owner) {
			for(Player member : player_members) {
				if(member != null && member.getMinigameAttributes().getDungeoneeringAttributes().getParty() != null && member.getMinigameAttributes().getDungeoneeringAttributes().getParty() == this) {
					if(member == owner)
						continue;
					if(fromParty) {
						member.getPacketSender().sendMessage("Your party has been deleted by the party's leader.");
						remove(member, false, true);
					} else {
						remove(member, false, false);
					}
				}
			}
			if(hasEnteredDungeon) {
				for(NPC npc : p.getMinigameAttributes().getDungeoneeringAttributes().getParty().getNpcs()) {
					if(npc != null && npc.getPosition().getZ() == p.getPosition().getZ())
						World.deregister(npc);
				}
				for(GroundItem groundItem : p.getMinigameAttributes().getDungeoneeringAttributes().getParty().getGroundItems()) {
					if(groundItem != null)
						GroundItemManager.remove(groundItem, true);
				}
			}
		} else {
			if(fromParty) {
				sendMessage(p.getUsername()+" has left the party.");
				if(hasEnteredDungeon) {
					if(p.getInventory().contains(Dungeoneering.DUNGEONEERING_GATESTONE_ID)) {
						p.getInventory().delete(Dungeoneering.DUNGEONEERING_GATESTONE_ID, 1);
						getOwner().getInventory().add(Dungeoneering.DUNGEONEERING_GATESTONE_ID, 1, "Dungeoneering gatestone");
					}
				}
			}
		}
		if(hasEnteredDungeon) {
			p.getEquipment().resetItems().refreshItems();
			p.getInventory().resetItems().refreshItems();
			p.restart();
			p.getUpdateFlag().flag(Flag.APPEARANCE);
			p.moveTo(new Position(3450, 3715));
			int damage = p.getMinigameAttributes().getDungeoneeringAttributes().getDamageDealt();
			int deaths = p.getMinigameAttributes().getDungeoneeringAttributes().getDeaths();
			int exp = (int) (damage > 0 ? (p.getMinigameAttributes().getDungeoneeringAttributes().getDamageDealt() * 2.6) - (deaths * 2800 + Misc.getRandom(400)) : 0);
			int tokens = (int) (damage > 0 ? (p.getMinigameAttributes().getDungeoneeringAttributes().getDamageDealt() / 2.9) - (deaths * 80 + Misc.getRandom(25)) : 0);
			if(killedBoss) {
				exp += 13000 + Misc.getRandom(2000);
				tokens += Misc.getRandom(500) + 60;
			}
			if(exp > 0 && tokens > 0) {
				exp += (3000 + Misc.getRandom(1000)) * complexity;
				if(player_members.size() == 1) {
					exp = (int) (exp * 0.85);
					tokens = (int) (tokens * 1);
				}
				if(player_members.size() >= 2) {
					exp = (int) (exp * 1.15);
					tokens = (int) (tokens * 1.5);			
				p.getPacketSender().sendMessage("<img=10> <col=660000>You've received bonus xp & dung points for a multi player party! ");

				}
				p.getSkillManager().addExperience(Skill.RAID, exp);
				p.getPointsHandler().setDungeoneeringTokens(tokens, true);
				
				p.getPacketSender().sendMessage("<img=10> <col=660000>You've received some Dungeoneering experience and "+tokens+" Dungeoneering tokens.");
				PlayerPanel.refreshPanel(p);
			}
			if(p == owner) {
				hasEnteredDungeon = killedBoss = false;
				kills = deaths = 0;
				setGatestonePosition(null);
			}
		}
		if(fromParty) {
			p.getMinigameAttributes().getDungeoneeringAttributes().setParty(null);
			refreshInterface();
		}
		p.getPacketSender().sendInterfaceRemoval();
	}

	public void refreshInterface() {
		for(Player member : getPlayers()) {
			if(member != null) {
				for(int s = 26236; s < 26240; s++)
					member.getPacketSender().sendString(s, "");
				member.getPacketSender().sendString(26235, owner.getUsername()+"'s Party");
				member.getPacketSender().sendString(26240, floor == null ? "-" : ""+(floor.ordinal()+1));
				member.getPacketSender().sendString(26241, complexity == -1 ? "-" : ""+complexity+"");
				for(int i = 0; i < getPlayers().size(); i++) {
					Player p = getPlayers().get(i);
					if(p != null) {
						if(p == getOwner())
							continue;
						member.getPacketSender().sendString(26235+i, p.getUsername());
					}
				}
			}
		}
	}

	public void sendMessage(String message) {
		for(Player member : getPlayers()) {
			if(member != null) {
				member.getPacketSender().sendMessage("<img=10> <col=660000>"+message);
			}
		}
	}

	public void sendFrame(int frame, String string) {
		for(Player member : getPlayers()) {
			if(member != null) {
				member.getPacketSender().sendString(frame, string);
			}
		}
	}

	public static void create(Player p) {
		if(p.getLocation() != Location.RAID) {
			p.getPacketSender().sendMessage("You must be in Daemonheim to create a party.");
			return;
		}
		if(p.getMinigameAttributes().getDungeoneeringAttributes().getParty() != null) {
			p.getPacketSender().sendMessage("You are already in a Dungeoneering party.");
			return;
		}
		if(p.getMinigameAttributes().getDungeoneeringAttributes().getParty() == null)
			p.getMinigameAttributes().getDungeoneeringAttributes().setParty(new DungeoneeringParty(p));
		p.getMinigameAttributes().getDungeoneeringAttributes().getParty().setDungeoneeringFloor(DungeoneeringFloor.FIRST_FLOOR);
		p.getMinigameAttributes().getDungeoneeringAttributes().getParty().setComplexity(1);
		p.getPacketSender().sendMessage("<img=10> <col=660000>You've created a Dungeoneering party. Perhaps you should attemptInvite a few players?");
		p.getMinigameAttributes().getDungeoneeringAttributes().getParty().refreshInterface();
		p.getPacketSender().sendTabInterface(GameSettings.QUESTS_TAB, Dungeoneering.PARTY_INTERFACE);
		p.getPacketSender().sendDungeoneeringTabIcon(true);
		p.getPacketSender().sendTab(GameSettings.QUESTS_TAB).sendInterfaceRemoval();
	}

	public DungeoneeringFloor getDungeoneeringFloor() {
		return floor;
	}

	public void setDungeoneeringFloor(DungeoneeringFloor floor) {
		this.floor = floor;
	}

	public Player getOwner() {
		return owner;
	}

	public CopyOnWriteArrayList<Player> getPlayers() {
		return player_members;
	}

	public boolean hasEnteredDungeon() {
		return hasEnteredDungeon;
	}

	public void enteredDungeon(boolean hasEnteredDungeon) {
		this.hasEnteredDungeon = hasEnteredDungeon;
	}

	public int getKills() {
		return kills;
	}

	public void setKills(int kills) {
		this.kills = kills;
	}

	public int getDeaths() {
		return deaths;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	public CopyOnWriteArrayList<NPC> getNpcs() {
		return npc_members;
	}

	public CopyOnWriteArrayList<GroundItem> getGroundItems() {
		return ground_items;
	}

	public Position getGatestonePosition() {
		return gatestonePosition;
	}

	public void setGatestonePosition(Position gatestonePosition) {
		this.gatestonePosition = gatestonePosition;
	}

	public int getComplexity() {
		return complexity;
	}

	public void setComplexity(int complexity) {
		this.complexity = complexity;
	}

	public void setKilledBoss(boolean killedBoss) {
		this.killedBoss = killedBoss;
	}
}
