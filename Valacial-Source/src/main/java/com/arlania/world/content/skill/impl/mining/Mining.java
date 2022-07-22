package com.arlania.world.content.skill.impl.mining;

import com.arlania.engine.task.Task;
import com.arlania.engine.task.TaskManager;
import com.arlania.model.Animation;
import com.arlania.model.GameObject;
import com.arlania.model.Locations;
import com.arlania.model.Skill;
import com.arlania.util.Misc;
import com.arlania.world.World;
import com.arlania.world.content.Achievements;
import com.arlania.world.content.CustomObjects;
import com.arlania.world.content.ShootingStar;
import com.arlania.world.content.Sounds;
import com.arlania.world.content.Sounds.Sound;
import com.arlania.world.content.achievements.AchievementData;
import com.arlania.world.content.skill.impl.mining.MiningData.Ores;
import com.arlania.world.entity.impl.player.Player;
import com.arlania.world.content.AfkOreSystem;

public class Mining {

	public static void startMining(final Player player, final GameObject oreObject) {
		player.getSkillManager().stopSkilling();
		player.getPacketSender().sendInterfaceRemoval();
		if(!Locations.goodDistance(player.getPosition().copy(), oreObject.getPosition(), 1) && oreObject.getId() != 24444  && oreObject.getId() != 24445 && oreObject.getId() != 38660)
			return;
		if(player.busy() || player.getCombatBuilder().isBeingAttacked() || player.getCombatBuilder().isAttacking()) {
			player.getPacketSender().sendMessage("You cannot do that right now.");
			return;
		}
		if(player.getInventory().getFreeSlots() == 0) {
			player.getPacketSender().sendMessage("You do not have any free inventory space left.");
			return;
		}
		player.setInteractingObject(oreObject);
		player.setPositionToFace(oreObject.getPosition());
		final Ores o = MiningData.forRock(oreObject.getId());
		final boolean giveGem = o != Ores.Rune_essence && o != Ores.Pure_essence;
		final boolean giveBond = o != Ores.Rune_essence && o != Ores.Pure_essence;

		final int reqCycle = o == Ores.Runite ? 6 + Misc.getRandom(2) : Misc.getRandom(o.getTicks() - 1);
		if (o != null) {
			final int pickaxe = MiningData.getPickaxe(player);
			final int miningLevel = player.getSkillManager().getCurrentLevel(Skill.MINING);
			if (pickaxe > 0) {
				if (miningLevel >= o.getLevelReq()) {
					final MiningData.Pickaxe p = MiningData.forPick(pickaxe);
					if (miningLevel >= p.getReq()) {
						player.performAnimation(new Animation(p.getAnim()));
						final int delay = o.getTicks() - MiningData.getReducedTimer(player, p);
						player.setCurrentTask(new Task(delay >= 2 ? delay : 1, player, false) {
							int cycle = 0;
							@Override
							public void execute() {
								if(player.getInteractingObject() == null || player.getInteractingObject().getId() != oreObject.getId()) {
									player.getSkillManager().stopSkilling();
									player.performAnimation(new Animation(65535));
									stop();
									return;
								}
								if(player.getInventory().getFreeSlots() == 0) {
									player.performAnimation(new Animation(65535));
									stop();
									player.getPacketSender().sendMessage("You do not have any free inventory space left.");
									return;
								}
								if (cycle != reqCycle) {
									cycle++;
									player.performAnimation(new Animation(p.getAnim()));
								}
								if(giveBond) {
									boolean golden = Misc.getRandom(o == Ores.AFK_ROCK ? 20000 : 5000) == 1;
									if (Misc.getRandom(o == Ores.AFK_ROCK ? 5000 : 5000) == 1) {
										int bondId = golden ? 1 : MiningData.RANDOM_BOND[(int) (MiningData.RANDOM_BOND.length * Math.random())];
										player.getInventory().add(bondId, 1);
										player.getPacketSender().sendMessage("You've found a $1 Bond!");
									}
								}
								if(giveGem) {
									boolean onyx = (o == Ores.Runite || o == Ores.CRASHED_STAR) && Misc.getRandom(o == Ores.CRASHED_STAR ? 20000 : 5000) == 1;
									if(onyx || Misc.getRandom(o == Ores.AFK_ROCK ? 100 : 100) == 1) {
										int gemId = onyx ? 1 : MiningData.RANDOM_GEMS[(int)(MiningData.RANDOM_GEMS.length * Math.random())];
										player.getInventory().add(gemId, 1);
										player.getPacketSender().sendMessage("You've found a Golden AFK rock!");
									}
								}
								if (cycle == reqCycle) {
									if(o == Ores.Iron) {
										player.getAchievementTracker().progress(com.arlania.world.content.achievements.AchievementData.MINE_SOME_IRON, 1);
									} else if(o == Ores.Runite) {
										player.getAchievementTracker().progress(com.arlania.world.content.achievements.AchievementData.MINE_25_RUNITE_ORES, 1);
										player.getAchievementTracker().progress(com.arlania.world.content.achievements.AchievementData.MINE_2000_RUNITE_ORES, 1);

									}
									if(o.getItemId() != -1) {
										player.getInventory().add(o.getItemId(), 1);
									}
									player.getSkillManager().addExperience(Skill.MINING, (int) (o.getXpAmount() * 1.4));
									if(o == Ores.CRASHED_STAR || o == Ores.AFK_ROCK) {
									//	AfkOreSystem.minedCount++;
										player.sendMessage("You chip a piece of the rock. "  + AfkOreSystem.getLeft() + " more for ::afkboss to spawn.") ;
										player.getAchievementTracker().progress(AchievementData.MINE_10000_TICKETS, 1);
										AfkOreSystem.spawnBoss();

									} else {
										player.getPacketSender().sendMessage("You mine some ore.");
										player.getInventory().add(20951,5);
									}
									Sounds.sendSound(player, Sound.MINE_ITEM);
									cycle = 0;
									this.stop();
									if(o.getRespawn() > 0) {
										player.performAnimation(new Animation(65535));
										oreRespawn(player, oreObject, o);
									} else {
										if(oreObject.getId() == 38660) {
											if(ShootingStar.CRASHED_STAR == null || ShootingStar.CRASHED_STAR.getStarObject().getPickAmount() >= ShootingStar.MAXIMUM_MINING_AMOUNT) {
												player.getPacketSender().sendClientRightClickRemoval();
												player.getSkillManager().stopSkilling();
												return;
											} else {
												ShootingStar.CRASHED_STAR.getStarObject().incrementPickAmount();
											}
										} else {
											player.performAnimation(new Animation(65535));
										}
										startMining(player, oreObject);
									}
								}
							}
						});
						TaskManager.submit(player.getCurrentTask());
					} else {
						player.getPacketSender().sendMessage("You need a Mining level of at least "+p.getReq()+" to use this pickaxe.");
					}
				} else {
					player.getPacketSender().sendMessage("You need a Mining level of at least "+o.getLevelReq()+" to mine this rock.");
				}
			} else {
				player.getPacketSender().sendMessage("You don't have a pickaxe to mine this rock with.");
			}
		}
	}

	public static void oreRespawn(final Player player, final GameObject oldOre, Ores o) {
		if(oldOre == null || oldOre.getPickAmount() >= 1)
			return;
		oldOre.setPickAmount(1);
		for(Player players : player.getLocalPlayers()) {
			if(players == null)
				continue;
			if(players.getInteractingObject() != null && players.getInteractingObject().getPosition().equals(player.getInteractingObject().getPosition().copy())) {
				players.getPacketSender().sendClientRightClickRemoval();
				players.getSkillManager().stopSkilling();
			}
		}
		player.getPacketSender().sendClientRightClickRemoval();
		player.getSkillManager().stopSkilling();
		CustomObjects.globalObjectRespawnTask(new GameObject(452, oldOre.getPosition().copy(), 10, 0), oldOre, o.getRespawn());
	}
}
