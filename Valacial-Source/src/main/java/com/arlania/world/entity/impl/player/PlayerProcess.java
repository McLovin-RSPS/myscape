package com.arlania.world.entity.impl.player;

import com.arlania.GameSettings;
import com.arlania.model.RegionInstance.RegionInstanceType;
import com.arlania.util.Misc;
import com.arlania.world.World;
import com.arlania.world.content.AfkOreSystem;
import com.arlania.world.content.BublleGum;
import com.arlania.world.content.GlobalBossEvent;
import com.arlania.world.content.LoyaltyProgramme;
import com.arlania.world.content.combat.pvp.BountyHunter;
import com.arlania.world.content.global.GlobalBoss;
import com.arlania.world.content.global.GlobalBossHandler;
import com.arlania.world.content.skill.impl.construction.House;
import com.arlania.world.entity.impl.GroundItemManager;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class PlayerProcess {

	/*
	 * The player (owner) of this instance
	 */
	private Player player;


	public int secondsOver = 0;
	int delay = 1000; // delay for 5 sec.
	int period = 1000; // repeat every sec.
	int combatTick = 0;
	/*
	 * The loyalty tick, once this reaches 6, the player
	 * will be given loyalty points.
	 * 6 equals 3.6 seconds.
	 */
	private int loyaltyTick;

	int test = 0;


	/*
	 * The timer tick, once this reaches 2, the player's
	 * total play time will be updated.
	 * 2 equals 1.2 seconds.
	 */
	private int timerTick;
private int tick;
	/*
	 * Makes sure ground items are spawned on height change
	 */
	private int previousHeight;

	public PlayerProcess(Player player) {
		this.player = player;
		this.previousHeight = player.getPosition().getZ();
	}

	public void sequence() {

		/** SKILLS **/
		if(player.shouldProcessFarming()) {
			player.getFarming().sequence();
		}
        if (player.inFFA) {
      	  player.getPacketSender().sendInteractionOption("Attack", 2, true);
        }    
               if (player.getCombatBuilder().isAttacking()) {
                    
                } else {
                    if (player.walkableInterfaceList.contains(41020)) {

						player.sendParallellInterfaceVisibility(41020, false);
                    }
                } 

               if(!player.getCombatBuilder().isOutOfCombat()) {
				   if(!player.getCombayDelay().elapsed(1000)) {
				   	return;
				   }
				   player.getCombayDelay().reset();
				   player.secondsOver++;


				   if(player.secondsOver > 2) {
					 player.getPacketSender().sendString(53322, (player.damage/player.secondsOver)/10+" Dps");

				   } else {
					   player.getPacketSender().sendString(53322, (player.damage/10)+" Dps");
				   }
			   } else {
               	if(player.secondsOver != 0 || player.damage != 0 && player.getCombayDelay().elapsed(2000)) {
					player.getCombayDelay().reset();
					player.secondsOver = 0;
               		player.damage = 0;
					player.getPacketSender().sendString(53322, "0 Dps");
				}
			   }

//		combatTick++;
//if(combatTick >= 1) {
//	combatTick = 0;
//}


		if(timerTick >= 1) {

			Calendar c = Calendar.getInstance();
			c.add(Calendar.DAY_OF_MONTH, 1);
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			c.set(Calendar.MILLISECOND, 0);
			long millis = (c.getTimeInMillis() - System.currentTimeMillis());

			long days = TimeUnit.MILLISECONDS.toDays(millis);
			millis -= TimeUnit.DAYS.toMillis(days);
			long hours = TimeUnit.MILLISECONDS.toHours(millis);
			millis -= TimeUnit.HOURS.toMillis(hours);
			long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
			millis -= TimeUnit.MINUTES.toMillis(minutes);
			long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);


			if (player.getInterfaceId() == 57210) {
				if (hours > 0) {
					player.getPacketSender().sendString(57235, "Time remaining: " + hours + "h " + minutes + "min " + seconds + "sec ");
				} else if (minutes > 0) {
					player.getPacketSender().sendString(57235, "Time remaining: " + minutes + "min " + seconds + "sec");
				} else {
					player.getPacketSender().sendString(57235, "Time remaining: " + seconds + "Seconds"); //check discord

				}
			}
			//questtab 1 playerPanel
			if (Player.questTabInterfaceId == 26600) {
				player.getPacketSender().sendString(37471, "@gre@" + Misc.getTimePlayed((player.getTotalPlayTime() + player.getRecordedLogin().elapsed())));
				player.getPacketSender().sendString("@gre@"+player.getSkillManager().getTotalGainedExp(),37466);


			}
			//questtab 2 Server panel


			if (Player.questTabInterfaceId == 50850) {


				System.out.println(World.bossSpawns[0]);
				//System.out.println(System.currentTimeMillis() + " System");
				player.getPacketSender().sendString(50867, "Undead Gold Dragon" + Player.getTimeLeft((World.bossSpawns[1] - System.currentTimeMillis())));
				player.getPacketSender().sendString(50868, "Infinito" + Player.getTimeLeft((World.bossSpawns[3] - System.currentTimeMillis())));
				player.getPacketSender().sendString(50869, "Beelzebub" + Player.getTimeLeft((World.bossSpawns[0] - System.currentTimeMillis())));
				player.getPacketSender().sendString(50870, "Icy Vorago" + Player.getTimeLeft((World.bossSpawns[2] - System.currentTimeMillis())));
				//layer.getPacketSender().sendString(50871, "Ainz Ooal Gown " +  GlobalBossEvent.timeRemaining());

				//player.getPacketSender().sendString(50871, "The World(AFK Boss)" + AfkOreSystem.minedCount


//			AtomicInteger id = new AtomicInteger(50867);
//			AtomicInteger id2 = new AtomicInteger(0);
//
//			for(GlobalBoss boss : GlobalBossHandler.getBosses()) {
//				 millis = World.bossSpawns[id2.get()];
//				 System.out.println(millis);
//
//
//				 days = TimeUnit.MILLISECONDS.toDays(millis);
//				millis -= TimeUnit.DAYS.toMillis(days);
//				 hours = TimeUnit.MILLISECONDS.toHours(millis);
//				millis -= TimeUnit.HOURS.toMillis(hours);
//				 minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
//				millis -= TimeUnit.MINUTES.toMillis(minutes);
//				 seconds = TimeUnit.MILLISECONDS.toSeconds(millis);
//
//				if(millis < 0) {
//					player.getPacketSender().sendString(id.getAndIncrement(), "" + boss.getDefinition().getName() + ": @gre@Alive");
//				} else if(seconds > 1){
//					player.getPacketSender().sendString(id.getAndIncrement(), "" + boss.getDefinition().getName() + ": " + minutes+ ": "+seconds+" mins");
//				} else  {
//					player.getPacketSender().sendString(id.getAndIncrement(), "" + boss.getDefinition().getName() + ": "+seconds+" seconds");
//
//				}
//				id2.getAndIncrement();
//			}

//			id.set(50867);
//			id2.set(0);
			}
			//questtab 3 Staff
			if (Player.questTabInterfaceId == 50650) {

//			if(World.getPlayerByName("Gref")!= null){
//				player.getPacketSender().sendString("@or1@Gref: @gre@Online",50664);
//			} else {
//				player.getPacketSender().sendString("@or1@Gref: @red@Offline",50664);
//			}
//			if(World.getPlayerByName("Gandalf")!= null){
//				player.getPacketSender().sendString("@or1@Gandalf: @gre@Online",50665);
//			} else {
//				player.getPacketSender().sendString("@or1@Gandalf: @red@Offline",50665);
//			}
//			player.getPacketSender().sendString("@yel@Admins",50666);
//			if(World.getPlayerByName("Buu")!= null){
//				player.getPacketSender().sendString("@or1@Buu: @gre@Online",50667);
//			} else {
//				player.getPacketSender().sendString("@or1@Buu: @red@Offline",50667);
//			}
//			player.getPacketSender().sendString("@red@N/A",50668);
//			player.getPacketSender().sendString("@blu@Mods",50669);
//			if(World.getPlayerByName("Legacy")!= null){
//				player.getPacketSender().sendString("@or1@Legacy: @gre@Online",50670);
//			} else {
//				player.getPacketSender().sendString("@or1@Legacy: @red@Offline",50670);
//			}
//			player.getPacketSender().sendString("@red@N/A",50671);
//			player.getPacketSender().sendString("@cya@Supporters",50672);
//			if(World.getPlayerByName("Nayosum")!= null){
//				player.getPacketSender().sendString("@or1@Nayosum: @gre@Online",50673);
//			} else {
//				player.getPacketSender().sendString("@or1@Nayosum: @red@Offline",50673);
//			}
//			player.getPacketSender().sendString("@red@N/A",50674);
//		}

				timerTick = 0;
			}
		}
		timerTick++;

		if(previousHeight != player.getPosition().getZ()) {
			GroundItemManager.handleRegionChange(player);
			previousHeight = player.getPosition().getZ();
		}

		if(!player.isInActive()) {
			if(loyaltyTick >= 6) {
				LoyaltyProgramme.incrementPoints(player);
				loyaltyTick = 0;
			}
			loyaltyTick++;
		}
		
		if(timerTick >= 1) {
//			player.getPacketSender().sendString(39166, "@or2@Time played:  @yel@"+Misc.getTimePlayed((player.getTotalPlayTime() + player.getRecordedLogin().elapsed())));
			timerTick = 0;
		}
		timerTick++;
		
		BountyHunter.sequence(player);
		BublleGum.sequence(player);

        if(player.getRegionInstance() != null && (player.getRegionInstance().getType() == RegionInstanceType.CONSTRUCTION_HOUSE || player.getRegionInstance().getType() == RegionInstanceType.CONSTRUCTION_DUNGEON)) {
            ((House)player.getRegionInstance()).process();
        }
    }
}