package com.arlania.world.content;

import com.arlania.engine.task.Task;
import com.arlania.engine.task.TaskManager;
import com.arlania.model.Skill;
import com.arlania.world.entity.impl.player.Player;

public class GodMode {

	Player player;

	public GodMode(Player player) {
		this.player = player;
	}

	public boolean isActive() {
		return player.getTimeLeft() > 0;
	}
	

	public void handleLogin() {
		if(isActive()) {
			player.getPacketSender().sendWalkableInterface(48300, true);
			System.out.println("Login -->");
			
			System.out.println("Time left: " + player.getTimeLeft());
			run();
		}
	}

	public void init() {
		if (isActive()) {
			player.sendMessage("Godmode is still active");
			return;
		}
		player.setTimeLeft(14400);
		player.getPacketSender().sendWalkableInterface(48300, true);
		
		run();
		
	}
	
	private void run() {
		System.out.println("Time left: " + player.getTimeLeft());
		player.getPacketSender().sendString(48302, "Time left: " + (player.getTimeLeft() / 5000) + " minutes");	
		TaskManager.submit(new Task(100, true) {
			@Override
			protected void execute() {
				if (!isActive()) {
					player.getPacketSender().sendWalkableInterface(48300, false);
					for (Skill skill : Skill.values())
						player.getSkillManager().setCurrentLevel(skill, player.getSkillManager().getMaxLevel(skill));
					player.sendMessage("@red@ Godmode has run out!");
					stop();
					return;
				}
				
				player.getSkillManager().setCurrentLevel(Skill.PRAYER, 15000);
				player.getSkillManager().setCurrentLevel(Skill.ATTACK, 1500);
				player.getSkillManager().setCurrentLevel(Skill.STRENGTH, 1500);
				player.getSkillManager().setCurrentLevel(Skill.DEFENCE, 1500);
				player.getSkillManager().setCurrentLevel(Skill.RANGED, 1500);
				player.getSkillManager().setCurrentLevel(Skill.MAGIC, 1500);
				player.getSkillManager().setCurrentLevel(Skill.CONSTITUTION, 15000);
				player.getSkillManager().setCurrentLevel(Skill.SUMMONING, 1500);
				player.getPacketSender().sendString(48302, "" + (player.getTimeLeft() / 5000) + " minutes");													// too?no i dont want decrease
				player.setTimeLeft(player.getTimeLeft() - 5000);

			}
		});
	}

}
