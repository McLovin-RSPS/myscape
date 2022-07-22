package com.arlania.engine.task.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.arlania.engine.task.Task;
import com.arlania.engine.task.TaskManager;
import com.arlania.model.Animation;
import com.arlania.model.Locations.Location;
import com.arlania.model.definitions.NPCDrops;
import com.arlania.model.definitions.NpcDefinition;
import com.arlania.util.Misc;
import com.arlania.world.World;
import com.arlania.world.content.*;
import com.arlania.world.content.Achievements.AchievementData;
import com.arlania.world.content.bossEvent.BossEventHandler;
import com.arlania.world.content.combat.strategy.impl.KalphiteQueen;
import com.arlania.world.content.combat.strategy.impl.Nex;
import com.arlania.world.content.global.GlobalBoss;
import com.arlania.world.content.global.GlobalBossHandler;
import com.arlania.world.entity.impl.npc.NPC;
import com.arlania.world.entity.impl.player.Player;

import mysql.impl.Voting;

/**
 * Represents an npc's death task, which handles everything
 * an npc does before and after their death animation (including it), 
 * such as dropping their drop table items.
 * 
 * @author relex lawl
 */

public class NPCDeathTask extends Task {
	
	/*
	 * The array which handles what bosses will give a player points
	 * after death
	 */
	private Set<Integer> BOSSES = new HashSet<>(Arrays.asList( // Add here for npc death count
			1999, 2882, 2881, 2883, 9200, 6432, 2518, 7134, 6754, 6305, 9201, 6303, 6303, 7567, 2236, 7532, 1999, 9325, 7563, 9202, 5666, 6442, 8754, 3200, 8133, 6306, 6766, 5666, 8754, 4413, 6222, 433, 6260, 6247, 6203, 8349, 50, 2001, 11558, 1160, 8133, 3200, 13447, 8549, 3851, 1382, 8133, 13447 ,2000 ,2009, 2006, 499

	        )); //use this array of npcs to change the npcs you want to give boss points
	
	/**
	 * The NPCDeathTask constructor.
	 * @param npc	The npc being killed.
	 */
	public NPCDeathTask(NPC npc) {
		super(2);
		this.npc = npc;
		this.ticks = 2;
	}

	/**
	 * The npc setting off the death task.
	 */
	private final NPC npc;

	/**
	 * The amount of ticks on the task.
	 */
	private int ticks = 2;

	/**
	 * The player who killed the NPC
	 */
	private Player killer = null;

	@SuppressWarnings("incomplete-switch")
	@Override
	public void execute() {
		try {
			npc.setEntityInteraction(null);
			switch (ticks) {
			case 2:
				npc.getMovementQueue().setLockMovement(true).reset();
				killer = npc.getCombatBuilder().getKiller(npc.clearDamageMapOnDeath());
				
				if(!(npc.getId() >= 6142 && npc.getId() <= 6145) && !(npc.getId() > 5070 && npc.getId() < 5081))
					npc.performAnimation(new Animation(npc.getDefinition().getDeathAnimation()));

				/** CUSTOM NPC DEATHS **/
				if(npc.getId() == 13447) {
					Nex.handleDeath();
				}

				break;
			case 0:
				if(killer != null) {
					//killer.setNpcKills(killer.getNpcKills() + 1);
					//killer.sendMessage("<img=0>You now have @red@" + killer.getNpcKills() + " NPC Points!");
					boolean boss = (npc.getDefaultConstitution() > 2000);
					if(!Nex.nexMinion(npc.getId()) && npc.getId() != 1158 && !(npc.getId() >= 3493 && npc.getId() <= 3497)) {
						KillsTracker.submitById(killer, npc.getId(), true, boss);
						KillsTracker.submitById(killer, npc.getId(), false, boss);
						//NpcGlobalSystem.NpcCount++;

						if(boss) {
							Achievements.doProgress(killer, AchievementData.DEFEAT_500_BOSSES);
						}
					}
                    killer.addNpcKillCount(npc.getId());
                    String npcName = Misc.formatText(npc.getDefinition().getName());
                    killer.sendMessage("<shad=1>@mag@You currently have " + killer.getNpcKillCount(npc.getId())
                            + " kills of <shad=1>@bla@" + npcName);
					killer.getPvMRanking().check();
					if (BOSSES.contains(npc.getId())) {
						killer.setBossPoints(killer.getBossPoints() + 1);
						killer.sendMessage("<img=0>You now have @red@" + killer.getBossPoints() + " Boss Points!");
					}
					if(npc.getId() == 2436) {
						killer.setNightRaidPoints(killer.getNightRaidPoints() + 2);
						killer.sendMessage("<img=0>You now have @red@" + killer.getNightRaidPoints() + " NightRaid Points!");
					}
//					if(npc.getId() == 8239) {
//						killer.setDeadPoints(killer.getDeadPoints() + 1);
//						killer.sendMessage("<img=0>You now have @red@" + killer.getDeadPoints() + " Dead Points!");
//					}
					if(npc.getId() == 6305) { //<-- CHANGE THIS.
						//Voting.VOTES = 0;
						Voting.handleKilledVotingBoss(killer);
						killer.getAchievementTracker().progress(com.arlania.world.content.achievements.AchievementData.KILL_VOTEBOSS, 1);

					}
					if (BOSSES.contains(npc.getId())) {
						killer.getAchievementTracker().progress(com.arlania.world.content.achievements.AchievementData.KILL_1000_BOSSES, 1);
					}
					if(npc.getId() == 2518) {
						killer.getAchievementTracker().progress(com.arlania.world.content.achievements.AchievementData.DEFEAT_THE_JOKER, 1);
						killer.getAchievementTracker().progress(com.arlania.world.content.achievements.AchievementData.DEFEAT_10000_JOKERS, 1);
					} else if(npc.getId() == 8721) {
						killer.getAchievementTracker().progress(com.arlania.world.content.achievements.AchievementData.DEFEAT_10000_DARTH_VADERS, 1);
						killer.getAchievementTracker().progress(com.arlania.world.content.achievements.AchievementData.DEFEAT_DARTH_VADER, 1);

					} else if(npc.getId() == 6432) {
						killer.getAchievementTracker().progress(com.arlania.world.content.achievements.AchievementData.DEFEAT_10000_CLOUD_STRIFE, 1);
						killer.getAchievementTracker().progress(com.arlania.world.content.achievements.AchievementData.DEFEAT_CLOUD_STRIFE, 1);
					} else if(npc.getId() == 7535) {
						killer.getAchievementTracker().progress(com.arlania.world.content.achievements.AchievementData.DEFEAT_10000_MYSTERIO, 1);
						killer.getAchievementTracker().progress(com.arlania.world.content.achievements.AchievementData.DEFEAT_MYSTERIO, 1);
					} else if(npc.getId() == 9003) {
						killer.getAchievementTracker().progress(com.arlania.world.content.achievements.AchievementData.DEFEAT_WHITEBEARD, 1);
						killer.getAchievementTracker().progress(com.arlania.world.content.achievements.AchievementData.DEFEAT_10000_WHITEBEARD, 1);
					} else if(npc.getId() == 9200) {
						killer.getAchievementTracker().progress(com.arlania.world.content.achievements.AchievementData.DEFEAT_10000_SORA, 1);
						killer.getAchievementTracker().progress(com.arlania.world.content.achievements.AchievementData.DEFEAT_SORA, 1);
					} else if(npc.getId() == 3782) {
						killer.getAchievementTracker().progress(com.arlania.world.content.achievements.AchievementData.KILL_DONATORBOSS, 1);
					} else if(npc.getId() == 3779) {
						killer.getAchievementTracker().progress(com.arlania.world.content.achievements.AchievementData.KILL_AFKBOSS, 1);
					} else if(npc.getId() == 6247) {
						Achievements.finishAchievement(killer, AchievementData.DEFEAT_COMMANDER_ZILYANA);
						killer.getAchievementAttributes().setGodKilled(2, true);
					} else if(npc.getId() == 6203) {
						Achievements.finishAchievement(killer, AchievementData.DEFEAT_KRIL_TSUTSAROTH);
						killer.getAchievementAttributes().setGodKilled(3, true);
					} else if(npc.getId() == 8133) {
						Achievements.finishAchievement(killer, AchievementData.DEFEAT_THE_CORPOREAL_BEAST);
					} else if(npc.getId() == 13447) {
						Achievements.finishAchievement(killer, AchievementData.DEFEAT_NEX);
						killer.getAchievementAttributes().setGodKilled(4, true);
					}
					/** ACHIEVEMENTS **/
					switch(killer.getLastCombatType()) {
					case MAGIC:
						killer.getAchievementTracker().progress(com.arlania.world.content.achievements.AchievementData.KILL_A_MONSTER_USING_MAGIC, 1);

						break;
					case MELEE:

						killer.getAchievementTracker().progress(com.arlania.world.content.achievements.AchievementData.KILL_A_MONSTER_USING_MELEE, 1);

						break;
					case RANGED:
						killer.getAchievementTracker().progress(com.arlania.world.content.achievements.AchievementData.KILL_A_MONSTER_USING_RANGED, 1);
						break;
					}

					/** LOCATION KILLS **/
					if(npc.getLocation().handleKilledNPC(killer, npc)) {
						stop();
						return;
					}



					/*
					 * Halloween event dropping
					 */

					if(npc.getId() == 1973) {
						TrioBosses.handleSkeleton(killer, npc.getPosition());
					}
					if(npc.getId() == 75) {
						TrioBosses.handleZombie(killer, npc.getPosition());
					}
					if(npc.getId() == 103) {
						TrioBosses.handleGhost(killer, npc.getPosition());
					}

					if(npc instanceof GlobalBoss)
						GlobalBossHandler.onDeath((GlobalBoss)npc);

					/*
					 * End Halloween event dropping
					 */
					/**
					 * Keys Event Monsters
									**/
									if(npc.getId() == 8754) {
										KeysEvent.handleSkotizo(killer, npc.getPosition());
									}
									if(npc.getId() == 8549) {
										KeysEvent.handlePhoenix(killer, npc.getPosition());
									}
									if(npc.getId() == 499) {
										KeysEvent.handleThermo(killer, npc.getPosition());
									}
									if(npc.getId() == 2060) {
										KeysEvent.handleSlashBash(killer, npc.getPosition());
									}
									if(npc.getId() == 2642) {
										KeysEvent.handleKBD(killer, npc.getPosition());
									}
									if(npc.getId() == 1999) {
										KeysEvent.handleCerb(killer, npc.getPosition());
									}
									if(npc.getId() == 7134) {
										KeysEvent.handleBork(killer, npc.getPosition());
									}
									if(npc.getId() == 1382) {
										KeysEvent.handleGlacor(killer, npc.getPosition());
									}
									if(npc.getId() == 6766) {
										KeysEvent.handleShaman(killer, npc.getPosition());
									}
									if(npc.getId() == 941) {
										KeysEvent.handleGreenDragon(killer, npc.getPosition());
									}
									if(npc.getId() == 55) {
										KeysEvent.handleBlueDragon(killer, npc.getPosition());
									}
									if(npc.getId() == 1615) {
										KeysEvent.handleAbbyDemon(killer, npc.getPosition());
									}

					if (World.minigameHandler.handleNpcDeath(npc)) {
						stop();
						return;
					}

					if(ZonesEnum.getZonesEnumByKillCountNpc(npc.getId()) != null) {
						ZonesEnum zonesEnum = ZonesEnum.getZonesEnumByKillCountNpc(npc.getId());
						if(killer.getNpcKillCount(npc.getId()) == zonesEnum.getKill_count()) {
							killer.getPacketSender().sendMessage("@or3@you have unlocked "+ NpcDefinition.forId(zonesEnum.getNpcId()).getName()+"!");
						}

					}
					if (GlobalBossEvent.spawned && npc.getId() == GlobalBossEvent.ID) {
						GlobalBossEvent.handleDeath(npc);
						stop();
						return;
					}
					if (npc.getId() == 3779) {
						AfkBossDrop.handleDrop(npc);
					}
						/** PARSE DROPS **/
					NPCDrops.dropItems(killer, npc);

					/** SLAYER **/
					killer.getSlayer().killedNpc(npc);
					
					/** BOSS EVENT **/
					new BossEventHandler().death(killer, npc, npc.getDefinition().getName());
				}
				stop();
				break;
			}
			ticks--;
		} catch(Exception e) {
			e.printStackTrace();
			stop();
		}
	}

	@Override
	public void stop() {
		setEventRunning(false);

		npc.setDying(false);

		//respawn
		if(npc.getDefinition().getRespawnTime() > 0 && npc.getLocation() != Location.GRAVEYARD && npc.getLocation() != Location.RAID) {
			TaskManager.submit(new NPCRespawnTask(npc, npc.getDefinition().getRespawnTime()));
		}

		World.deregister(npc);

		if(npc.getId() == 1158 || npc.getId() == 1160) {
			KalphiteQueen.death(npc.getId(), npc.getPosition());
		}
		if(Nex.nexMob(npc.getId())) {
			Nex.death(npc.getId());
		}
	}
}
