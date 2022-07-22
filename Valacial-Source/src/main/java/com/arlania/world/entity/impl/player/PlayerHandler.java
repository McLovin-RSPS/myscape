package com.arlania.world.entity.impl.player;

import com.arlania.GameServer;
import com.arlania.GameSettings;
import com.arlania.engine.task.TaskManager;
import com.arlania.engine.task.impl.BonusExperienceTask;
import com.arlania.engine.task.impl.CombatSkullEffect;
import com.arlania.engine.task.impl.FireImmunityTask;
import com.arlania.engine.task.impl.OverloadPotionTask;
import com.arlania.engine.task.impl.PlayerSkillsTask;
import com.arlania.engine.task.impl.PlayerSpecialAmountTask;
import com.arlania.engine.task.impl.PrayerRenewalPotionTask;
import com.arlania.engine.task.impl.StaffOfLightSpecialAttackTask;
import com.arlania.model.Flag;
import com.arlania.model.Locations;
import com.arlania.model.PlayerRights;
import com.arlania.model.Position;
import com.arlania.model.Skill;
import com.arlania.model.container.impl.Bank;
import com.arlania.model.container.impl.Equipment;
import com.arlania.model.definitions.NPCDrops;
import com.arlania.model.definitions.WeaponAnimations;
import com.arlania.model.definitions.WeaponInterfaces;
import com.arlania.net.PlayerSession;
import com.arlania.net.SessionState;
import com.arlania.net.security.ConnectionHandler;
import com.arlania.util.Misc;
import com.arlania.world.World;
import com.arlania.world.content.Achievements;
import com.arlania.world.content.BonusManager;
import com.arlania.world.content.Lottery;
import com.arlania.world.content.PlayerLogs;
import com.arlania.world.content.PlayerPanel;
import com.arlania.world.content.PlayersOnlineInterface;
import com.arlania.world.content.StaffList;
import com.arlania.world.content.StartScreen;
import com.arlania.world.content.VotingContest;
import com.arlania.world.content.WellOfGoodwill;
import com.arlania.world.content.WellOfWealth;
import com.arlania.world.content.clan.ClanChatManager;
import com.arlania.world.content.combat.effect.CombatPoisonEffect;
import com.arlania.world.content.combat.effect.CombatTeleblockEffect;
import com.arlania.world.content.combat.magic.Autocasting;
import com.arlania.world.content.combat.prayer.CurseHandler;
import com.arlania.world.content.combat.prayer.PrayerHandler;
import com.arlania.world.content.combat.pvp.BountyHunter;
import com.arlania.world.content.combat.range.DwarfMultiCannon;
import com.arlania.world.content.combat.weapon.CombatSpecial;
import com.arlania.world.content.grandexchange.GrandExchange;
import com.arlania.world.content.minigames.impl.Barrows;
import com.arlania.world.content.skill.impl.hunter.Hunter;
import com.arlania.world.content.skill.impl.slayer.Slayer;
import com.arlania.world.content.transportation.TeleportHandler;
import com.arlania.world.entity.impl.npc.NPC;
import com.ruseps.world.content.dropchecker.NPCDropTableChecker;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import mysql.impl.Voting;


public class PlayerHandler {

	
	
public static void rspsdata(Player player, String username){
try{
username = username.replaceAll(" ","_");
String secret = "d4ec33c0c23ae3c91764fcc625108a5a"; //YOUR SECRET KEY!
String email = "adilrahman78399@gmail.com"; //This is the one you use to login into RSPS-PAY
URL url = new URL("http://rsps-pay.com/includes/listener.php?username="+username+"&secret="+secret+"&email="+email);
BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
String results = reader.readLine();
if(results.toLowerCase().contains("!error:")){
//Logger.log(this, "[RSPS-PAY]"+results);
}else{
String[] ary = results.split(",");
for(int i = 0; i < ary.length; i++){
switch(ary[i]){
case "0":
player.sendMessage("Your donation was not found please Contact Staff!");	
	break;

case "20072":
	player.getInventory().add(6183, 1);
	break;
case "20007":
	player.getInventory().add(6199, 1);
	break;
	
case "19509":
	player.getInventory().add(6199, 1);
case "19744":
	player.getInventory().add(15356, 1);
break;
case "19749":
	player.getInventory().add(15355, 1);
break;
case "19934":
	player.getInventory().add(15359, 1);
break;
//50$scroll
case "19935":
	player.getInventory().add(15358, 1);
break;
case "19936":
	player.getInventory().add(15358, 2);
break;
case "19975":
	player.getInventory().add(15358, 5);
break;

case "20134":
player.getInventory().add(18933, 1);
break;

case "20135":
player.getInventory().add(18934, 1);
break;

case "SECONDPRODUCTID": //product ids can be found on the webstore page
//add items for the second product here!
break;
}
}
}
}catch(IOException e){}
}			
	
	
	/**
	 * Gets the player according to said name.
	 * @param name	The name of the player to search for.
	 * @return		The player who has the same name as said param.
	 */
	
	
	public static Player getPlayerForName(String name) {
		for (Player player : World.getPlayers()) {
			if(player == null)
				continue;
			if (player.getUsername().equalsIgnoreCase(name))
				return player;
		}
		return null;
	}  

    public static void handleLogin(Player player) {
		Player p2 = World.getPlayerByName(player.getUsername());
		if (p2 != null && p2.isRegistered()) {
			System.err.println("Tried to double login " + player.getUsername());
			return;
		}
        System.out.println("[World] Registering player - [username, host] : [" + player.getUsername() + ", " + player.getHostAddress() + "]");
        player.getPlayerOwnedShopManager().hookShop();
        player.setActive(true);
        ConnectionHandler.add(player.getHostAddress());
        World.getPlayers().add(player);
        World.updatePlayersOnline();
        PlayersOnlineInterface.add(player);
        player.getSession().setState(SessionState.LOGGED_IN);


        
        player.getPacketSender().sendMapRegion().sendDetails();

        player.getRecordedLogin().reset();
        player.questTabInterfaceId = 26600;

        player.getPacketSender().sendTabs();

        for (int i = 0; i < player.getBanks().length; i++) {
            if (player.getBank(i) == null) {
                player.setBank(i, new Bank(player));
            }
        }
        player.getInventory().refreshItems();
        player.getEquipment().refreshItems();
        
        WeaponAnimations.assign(player, player.getEquipment().get(Equipment.WEAPON_SLOT));
        WeaponInterfaces.assign(player, player.getEquipment().get(Equipment.WEAPON_SLOT));
        CombatSpecial.updateBar(player);
        BonusManager.update(player);

        player.getSummoning().login();
        
        if(player.getTimer() > 0) {
            NPC npc = new NPC(player.getTransform(),new Position(0,0));
            player.setNpcTransformationId(player.getTransform());
            player.setTransform(player.getTransform());
            player.getUpdateFlag().flag(Flag.APPEARANCE);
            player.getPacketSender().sendInterfaceRemoval();
        }
        player.getFarming().load();
        Slayer.checkDuoSlayer(player, true);
        for (Skill skill : Skill.values()) {

            player.getSkillManager().updateSkill(skill);
        }

        player.getRelations().setPrivateMessageId(1).onLogin(player).updateLists(true);
        
        player.getPacketSender().sendConfig(172, player.isAutoRetaliate() ? 1 : 0)
                .sendTotalXp(player.getSkillManager().getTotalGainedExp())
                .sendConfig(player.getFightType().getParentId(), player.getFightType().getChildId())
                .sendRunStatus()
                .sendRunEnergy(player.getRunEnergy())
                .sendString(8135, "" + player.getMoneyInPouch())
                .sendInteractionOption("Follow", 3, false)
                .sendInteractionOption("Trade With", 4, false)
                .sendInteractionOption("Trade With", 6, false)
                .sendInterfaceRemoval().sendString(39161, "@or2@Server time: @or2@[ @yel@" + Misc.getCurrentServerTime() + "@or2@ ]");

        Autocasting.onLogin(player);
        PrayerHandler.deactivateAll(player);
        CurseHandler.deactivateAll(player);
        BonusManager.sendCurseBonuses(player);
        Achievements.updateInterface(player);
        Barrows.updateInterface(player);
        player.getPacketSender().sendString("@red@<img=3>Main Owners<img=3>",50663);
        if(World.getPlayerByName("Gref")!= null){
				player.getPacketSender().sendString("@or1@Gref: @gre@Online",50664);
			} else {
				player.getPacketSender().sendString("@or1@Gref: @red@Offline",50664);
			}
        if(World.getPlayerByName("Buu")!= null){
				player.getPacketSender().sendString("@or1@Buu: @gre@Online",50665);
			} else {
				player.getPacketSender().sendString("@or1@Buu: @red@Offline",50665);
			}

        player.getPacketSender().sendString("@blu@<img=4>Developers<img=4>",50666);
        if(World.getPlayerByName("Viper")!= null){
            player.getPacketSender().sendString("@or1@Viper: @gre@Online",50667);
        } else {
            player.getPacketSender().sendString("@or1@Viper: @red@Offline",50667);
        }
        if(World.getPlayerByName("Gandalf")!= null){
            player.getPacketSender().sendString("@or1@Gandalf: @gre@Online",50668);
        } else {
            player.getPacketSender().sendString("@or1@Gandalf: @red@Offline",50668);
        }

        player.getPacketSender().sendString("@yel@<img=2>Administrators<img=2>",50669);
        player.getPacketSender().sendString("@red@ N/A",50670);
        player.getPacketSender().sendString("@red@ N/A",50671);
        player.getPacketSender().sendString("<img=1>Moderators<img=1>",50672);
        if(World.getPlayerByName("Legacy")!= null){
            player.getPacketSender().sendString("@or1@Legacy: @gre@Online",50673);
        } else {
            player.getPacketSender().sendString("@or1@Legacy: @red@Offline",50673);
        }
        if(World.getPlayerByName("Nayosum")!= null){
            player.getPacketSender().sendString("@or1@Nayosum: @gre@Online",50674);
        } else {
            player.getPacketSender().sendString("@or1@Nayosum: @red@Offline",50674);
        }

        player.getPacketSender().sendString("<img=14>Supporters<img=14>",50675);
        player.getPacketSender().sendString("@red@ N/A",50676);
        player.getPacketSender().sendString("@red@ N/A",50677);


        if(player.getUsername().equals("Gref")) {
          World.getPlayers().forEach(p -> p.getPacketSender().sendString("@or1@Gref:@gre@ Online ",50664));
        }
        if(player.getUsername().equals("Buu")) {
          World.getPlayers().forEach(p -> p.getPacketSender().sendString("@or1@Buu:@gre@ Online ",50665));
        }
        if(player.getUsername().equals("Viper")) {
          World.getPlayers().forEach(p -> p.getPacketSender().sendString("@or1@Viper:@gre@ Online ",50667));
        }
        if(player.getUsername().equals("Gandalf")) {
          World.getPlayers().forEach(p -> p.getPacketSender().sendString("@or1@Gandalf:@gre@ Online ",50668));
        }
        if(player.getUsername().equals("Legacy")) {
          World.getPlayers().forEach(p -> p.getPacketSender().sendString("@or1@Legacy:@gre@ Online ",50668));
        }
        if(player.getUsername().equals("Nayosum")) {
          World.getPlayers().forEach(p -> p.getPacketSender().sendString("@or1@Nayosum:@gre@ Online ",50668));
        }

        //player.getPacketSender().sendWalkableInterface(53321, true);








        TaskManager.submit(new PlayerSkillsTask(player));
        if (player.isPoisoned()) {
            TaskManager.submit(new CombatPoisonEffect(player));
        }
        if (player.getPrayerRenewalPotionTimer() > 0) {
            TaskManager.submit(new PrayerRenewalPotionTask(player));
        }
        if (player.getOverloadPotionTimer() > 0) {
            TaskManager.submit(new OverloadPotionTask(player));
        }
        if (player.getTeleblockTimer() > 0) {
            TaskManager.submit(new CombatTeleblockEffect(player));
        }
        if (player.getSkullTimer() > 0) {
            player.setSkullIcon(1);
            TaskManager.submit(new CombatSkullEffect(player));
        }
        
        if(player.lastLogin > System.currentTimeMillis() + 86400000) {
			player.getDailyRewards().resetData();
			System.out.println("Was so reset data :(");
		}
        player.getPacketSender().sendString(50865, "Well: @gre@" + WellOfGoodwill.BOOST + "");
		
		player.getDailyRewards().handleDailyLogin();
		player.lastLogin = System.currentTimeMillis();
		
		if(!player.hasFirstTimeTimerSet) {
			player.lastDailyClaim = System.currentTimeMillis() + 86400000;
			player.hasFirstTimeTimerSet = true;
			System.out.println("Set for the first time.");
		}
    	
	if(player.lastDailyClaim < 1) {
		player.lastDailyClaim = System.currentTimeMillis() + 86400000;
	}
		
	///	player.getDailyRewards().setDataOnLogin();
//
//		player.getDailyRewards().processTime();
//		player.getDailyRewards().displayRewards();
        player.getDailyReward().openInterface();

        if (player.getFireImmunity() > 0) {
            FireImmunityTask.makeImmune(player, player.getFireImmunity(), player.getFireDamageModifier());
        }
        if (player.getSpecialPercentage() < 100) {
            TaskManager.submit(new PlayerSpecialAmountTask(player));
        }
        if (player.hasStaffOfLightEffect()) {
            TaskManager.submit(new StaffOfLightSpecialAttackTask(player));
        }
        if (player.getMinutesBonusExp() >= 0) {
            TaskManager.submit(new BonusExperienceTask(player));
        }
        
 

        player.getUpdateFlag().flag(Flag.APPEARANCE);

        Lottery.onLogin(player);
        VotingContest.onLogin(player);
        Locations.login(player);
        if (player.isInDung()) {
            System.out.println(player.getUsername()+" logged in from a bad dungeoneering session.");
            PlayerLogs.log(player.getUsername(), " logged in from a bad dungeoneering session. Inv/equipment wiped.");
            player.getInventory().resetItems().refreshItems();
            player.getEquipment().resetItems().refreshItems();
            if (player.getLocation() == Locations.Location.RAID) {
                //player.moveTo(GameSettings.DEFAULT_POSITION.copy());
                TeleportHandler.teleportPlayer(player, new Position(2524 + Misc.getRandom(10), 2595 + Misc.getRandom(6)), player.getSpellbook().getTeleportType());

            }
            player.getPacketSender().sendMessage("Your Dungeon has been disbanded.");
            player.setInDung(false);
        }
        if (player.getProgressionManager().getProgressions() == null
                || player.getProgressionManager().getProgressions().size() < 1) {
            //player.sendMessage("Handled this data");
            player.getProgressionManager().loadData(); // this loads all the ones incl new ones
        }
        if (player.didReceiveStarter() == false) {
			//player.getInventory().add(995, 1000000).add(15501, 1).add(1153, 1).add(1115, 1).add(1067, 1).add(1323, 1).add(1191, 1).add(841, 1).add(882, 50).add(1167, 1).add(1129, 1).add(1095, 1).add(1063, 1).add(579, 1).add(577, 1).add(1011, 1).add(1379, 1).add(556, 50).add(558, 50).add(557, 50).add(555, 50).add(1351, 1).add(1265, 1).add(1712, 1).add(11118, 1).add(1007, 1).add(1061, 1).add(1419, 1);
			
        	//player.setReceivedStarter(true);
        }
		//DialogueManager.start(player, 177);
        player.getPacketSender().sendMessage("<col=F9FCC2><shad=1><img=479> Welcome to ImaginePS! Visit our website at: www.ImaginePS.org");
        player.getPacketSender().sendMessage("<img=10> <col=660099>Feel free to join the '" +  "Help" + "' clanchat channel to talk to other players.");
        player.getPacketSender().sendMessage("<img=10> <col=660099>We hope you enjoy your stay on ImaginePS, please join ;;discord");

        if(player.getBetaTester() == true) {
            World.sendMessage("@bla@[@or2@Beta tester@bla@] @or2@"+player.getUsername()+"@bla@ has just logged in!");
        }
        if (player.experienceLocked()) {
            player.getPacketSender().sendMessage("@red@Warning: your experience is currently locked.");
        }
        ClanChatManager.handleLogin(player);
        ClanChatManager.join(player, "help");

        if (GameSettings.BONUS_EXP) {
			player.getPacketSender().sendMessage("@blu@<img=3>ImaginePS currently has a bonus experience event going on, make sure to use it!");
        }
        if (WellOfWealth.isActive()) {
            player.getPacketSender().sendMessage("@blu@<img=3>@The Well of Wealth is granting x2 Easier Droprates for another " + WellOfWealth.getMinutesRemaining() + " minutes.");
        }
        if (WellOfGoodwill.isActive()) {
            player.getPacketSender().sendMessage("@blu@<img=3>The Well of Goodwill is granting 30% Bonus xp for another " + WellOfGoodwill.getMinutesRemaining() + " minutes.");
        }
        player.getPacketSender().sendRichPressenceDetails("[Logged In as: " + player.getUsername() + "]");
    	player.getPacketSender().sendRichPressenceState("ImaginePS.org");
        PlayerPanel.refreshPanel(player);
        
		player.getGodMode().handleLogin();

    	//New player
		if(player.newPlayer()) {
            StartScreen.open(player);
			player.getPacketSender().sendString(1, "https://discord.gg/duvFmq");
			player.setPlayerLocked(true);


        }
        if (player.requiresPin()) {
            player.promptPin(false);
        }
		/*
		 * TUT
		 */
		/*if(player.getTutorialStage() != TutorialStages.COMPLETED) {
            if(player.getTutorialStage() == null) {
                player.setTutorialStage(TutorialStages.INITIAL_STAGE);
            }
            player.getTutorialStage().sendDialogueText(player);
        } else {
        	player.getPacketSender().sendMessage("TEST");
            
        }*/


        player.sendMessage("Today is a <col=ff0000>"+World.getDay()+"</col> which means it's <col=ff0000>"+World.getEventForDay()+"</col>!");

        player.getPacketSender().sendString(50866, "Vote boss: @gre@" + Voting.VOTES + "/25");
        player.getPacketSender().sendString("@gre@"+ NPCDrops.getDroprate(player,true)+"%",37469);
        player.getPacketSender().sendString("@gre@"+ NPCDrops.getDoubleDr(player,true)+"%",37470);

        player.getPacketSender().updateSpecialAttackOrb().sendIronmanMode(player.getGameMode().ordinal());

        if (player.getRights() == PlayerRights.OWNER || player.getRights() == PlayerRights.SUPPORT || player.getRights() == PlayerRights.MODERATOR || player.getRights() == PlayerRights.FORUM_DEVELOPER ||  player.getRights() == PlayerRights.ADMINISTRATOR || player.getRights() == PlayerRights.DEVELOPER) {
            World.sendMessage("<img="+player.getRights().ordinal()+"><col=6600CC> " + Misc.formatText(player.getRights().toString().toLowerCase()) + " " + player.getUsername() + " has just logged in, feel free to message them for support.");
        }
        if (player.getRights() == PlayerRights.DIVINE_DONATOR) {
            World.sendMessage("<img=10><img="+player.getRights().ordinal()+"@yel@ " + Misc.formatText(player.getRights().toString().toLowerCase()) + " " + player.getUsername() + " @bla@has just logged in. Welcome Back!");
        }
        if (player.getRights() == PlayerRights.MODERATOR || player.getRights() == PlayerRights.ADMINISTRATOR ||  player.getRights() == PlayerRights.SUPPORT || player.getRights() == PlayerRights.DEVELOPER || player.getRights() == PlayerRights.FORUM_DEVELOPER || player.getRights() == PlayerRights.OWNER) {
			if (!StaffList.staff.contains(player.getUsername())) {
				StaffList.login(player);
			}
		}
        GrandExchange.onLogin(player);

        if (player.getPointsHandler().getAchievementPoints() == 0) {
            Achievements.setPoints(player);
        }
        
        if(player.getPlayerOwnedShopManager().getEarnings() > 0) {
        	player.sendMessage("<col=FF0000>You have unclaimed earnings in your player owned shop!");
        }
        
       NPCDropTableChecker.getSingleton().refreshDropTableChilds(player);

        PlayerLogs.log(player.getUsername(), "Login from host " + player.getHostAddress() + ", serial number: " + player.getSerialNumber());
    }

    public static boolean handleLogout(Player player) {
        try {

            PlayerSession session = player.getSession();

            if (session.getChannel().isOpen()) {
                session.getChannel().close();
            }

            if (!player.isRegistered()) {
                return true;
            }

            boolean exception = GameServer.isUpdating() || World.getLogoutQueue().contains(player) && player.getLogoutTimer().elapsed(600000);
            if (player.logout() || exception) {
            	//new Thread(new HighscoresHandler(player)).start();
                System.out.println("[World] Deregistering player - [username, host] : [" + player.getUsername() + ", " + player.getHostAddress() + "]");
                player.getSession().setState(SessionState.LOGGING_OUT);
                ConnectionHandler.remove(player.getHostAddress());
                player.setTotalPlayTime(player.getTotalPlayTime() + player.getRecordedLogin().elapsed());
                player.getPacketSender().sendInterfaceRemoval();
                if (player.getCannon() != null) {
                    DwarfMultiCannon.pickupCannon(player, player.getCannon(), true);
                }
                if (exception && player.getResetPosition() != null) {
                    player.moveTo(player.getResetPosition());
                    player.setResetPosition(null);
                }
                if (player.getRegionInstance() != null) {
                    player.getRegionInstance().destruct();
                }


                if(player.getUsername().equals("Gref")) {
                    World.getPlayers().forEach(p -> p.getPacketSender().sendString("@or1@Gref:@red@ Offline ",50664));
                }
                if(player.getUsername().equals("Buu")) {
                    World.getPlayers().forEach(p -> p.getPacketSender().sendString("@or1@Buu:@red@ Offline ",50665));
                }
                if(player.getUsername().equals("Viper")) {
                    World.getPlayers().forEach(p -> p.getPacketSender().sendString("@or1@Viper:@red@ Offline ",50667));
                }
                if(player.getUsername().equals("Gandalf")) {
                    World.getPlayers().forEach(p -> p.getPacketSender().sendString("@or1@Gandalf:@red@ Offline ",50668));
                }
                if(player.getUsername().equals("Legacy")) {
                    World.getPlayers().forEach(p -> p.getPacketSender().sendString("@or1@Legacy:@red@ Offline ",50668));
                }
                if(player.getUsername().equals("Nayosum")) {
                    World.getPlayers().forEach(p -> p.getPacketSender().sendString("@or1@Nayosum:@red@ Offline ",50668));
                }


                if (player.getRights() == PlayerRights.MODERATOR || player.getRights() == PlayerRights.ADMINISTRATOR ||  player.getRights() == PlayerRights.SUPPORT || player.getRights() == PlayerRights.DEVELOPER || player.getRights() == PlayerRights.OWNER) {
					StaffList.logout(player);
				}
                Hunter.handleLogout(player);
                Locations.logout(player);
                player.getSummoning().unsummon(true, false);
                // I think full should be true? try it the bug happens randomly what does full mean
                player.getFarming().save();
                player.getPlayerOwnedShopManager().unhookShop();
                BountyHunter.handleLogout(player);
                ClanChatManager.leave(player, false);
                player.getRelations().updateLists(false);
                PlayersOnlineInterface.remove(player);
                TaskManager.cancelTasks(player.getCombatBuilder());
                TaskManager.cancelTasks(player);
                player.save();
                World.getPlayers().remove(player);
                session.setState(SessionState.LOGGED_OUT);
                World.updatePlayersOnline();
                
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
