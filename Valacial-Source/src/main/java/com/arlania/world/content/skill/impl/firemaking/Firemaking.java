package com.arlania.world.content.skill.impl.firemaking;

import com.arlania.engine.task.Task;
import com.arlania.engine.task.TaskManager;
import com.arlania.model.Animation;
import com.arlania.model.GameObject;
import com.arlania.model.Skill;
import com.arlania.model.movement.MovementQueue;
import com.arlania.util.Misc;
import com.arlania.world.content.Achievements;
import com.arlania.world.content.CustomObjects;
import com.arlania.world.content.Sounds;
import com.arlania.world.content.Achievements.AchievementData;
import com.arlania.world.content.Sounds.Sound;
import com.arlania.world.content.skill.impl.dungeoneering.Dungeoneering;
import com.arlania.world.entity.impl.player.Player;

/**
 * The Firemaking skill
 * @author Gabriel Hannason
 */

public class Firemaking {

	public static void lightFire(final Player player, int log, final boolean addingToFire, final int amount) {
		if (!player.getClickDelay().elapsed(2000) || player.getMovementQueue().isLockMovement())
			return;
		if(!player.getLocation().isFiremakingAllowed()) {
			player.getPacketSender().sendMessage("You can not light a fire in this area.");
			return;
		}
		boolean objectExists = CustomObjects.objectExists(player.getPosition().copy());
		if(!Dungeoneering.doingDungeoneering(player)) {
			if(objectExists && !addingToFire || player.getPosition().getZ() > 0 || !player.getMovementQueue().canWalk(1, 0) && !player.getMovementQueue().canWalk(-1, 0) && !player.getMovementQueue().canWalk(0, 1) && !player.getMovementQueue().canWalk(0, -1)) {
				player.getPacketSender().sendMessage("You can not light a fire here.");
				return;
			}
		}
		final Logdata.logData logData = Logdata.getLogData(player, log);
		if(logData == null)
			return;
		player.getMovementQueue().reset();
		if(objectExists && addingToFire)
			MovementQueue.stepAway(player);
		player.getPacketSender().sendInterfaceRemoval();
		player.setEntityInteraction(null);
		player.getSkillManager().stopSkilling();
		int cycle = 2 + Misc.getRandom(3);
		if (player.getSkillManager().getMaxLevel(Skill.FIREMAKING) < logData.getLevel()) {
			player.getPacketSender().sendMessage("You need a Firemaking level of atleast "+logData.getLevel()+" to light this.");
			return;
		}
		if(!addingToFire) {
			player.getPacketSender().sendMessage("You attempt to light a fire..");
			player.performAnimation(new Animation(71));
			player.getMovementQueue().setLockMovement(true);
		}
		player.setCurrentTask(new Task(addingToFire ? 2 : cycle, player, addingToFire ? true : false) {
			int added = 0;
			@Override
			public void execute() {
				player.getPacketSender().sendInterfaceRemoval();
				if(addingToFire && player.getInteractingObject() == null) { //fire has died
					player.getSkillManager().stopSkilling();
					player.getPacketSender().sendMessage("The fire has died out.");
					return;
				}
				player.getInventory().delete(logData.getLogId(), 1);
				if(addingToFire) {
					player.performAnimation(new Animation(827));
					player.getPacketSender().sendMessage("You add some logs to the fire..");
				} else {
					if(!player.getMovementQueue().isMoving()) {
						player.getMovementQueue().setLockMovement(false);
						player.performAnimation(new Animation(65535));
						MovementQueue.stepAway(player);
					}
					CustomObjects.globalFiremakingTask(new GameObject(2732, player.getPosition().copy()), player, logData.getBurnTime());
					player.getPacketSender().sendMessage("The fire catches and the logs begin to burn.");
					stop();
				}
				if(logData == Logdata.logData.OAK) {
					player.getAchievementTracker().progress(com.arlania.world.content.achievements.AchievementData.BURN_AN_OAK_LOG, 1);

				} else if(logData == Logdata.logData.MAGIC) {
					player.getAchievementTracker().progress(com.arlania.world.content.achievements.AchievementData.BURN_100_MAGIC_LOGS, 1);
					player.getAchievementTracker().progress(com.arlania.world.content.achievements.AchievementData.BURN_2500_MAGIC_LOGS, 1);

				}
				Sounds.sendSound(player, Sound.LIGHT_FIRE);
				player.getSkillManager().addExperience(Skill.FIREMAKING, logData.getXp());
				added++;
				if(added >= amount || !player.getInventory().contains(logData.getLogId())) {
					stop();
					if(added < amount && addingToFire && Logdata.getLogData(player, -1) != null && Logdata.getLogData(player, -1).getLogId() != log) {
						player.getClickDelay().reset(0);
						Firemaking.lightFire(player, -1, true, (amount-added));
					}
					return;
				}
			}

			@Override
			public void stop() {
				setEventRunning(false);
				player.performAnimation(new Animation(65535));
				player.getMovementQueue().setLockMovement(false);
			}
		});
		TaskManager.submit(player.getCurrentTask());
		player.getClickDelay().reset(System.currentTimeMillis() + 500);
	}

}