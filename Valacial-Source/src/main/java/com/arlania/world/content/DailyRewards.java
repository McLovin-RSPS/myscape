package com.arlania.world.content;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

import javax.management.timer.Timer;

import com.arlania.engine.task.Task;
import com.arlania.engine.task.TaskManager;
import com.arlania.model.Item;
import com.arlania.world.content.dailyReward.RewardMontly.*;
import com.arlania.world.entity.impl.player.Player;

/**
 * @author Suic
 */

public class DailyRewards {

	LocalDateTime date;
	private static Player player;
	
	public DailyRewards(Player player) {
		this.player = player;
	}
	
	final int[] day1 = new int[] {1543, 7100};
	final int[] day2 = new int[] {7118, 7100};
	final int[] day3 = new int[] {2572, 11425};
	final int[] day4 = new int[] {21045, 7118};
	final int[] day5 = new int[] {11527, 7100};
	final int[] day6 = new int[] {1543, 7118};
	final int[] day7 = new int[] {1543, 1543, 1543, 1543, 1543,1543, 1543, 1543, 1543, 1543};

	
	public void resetData() {
		
		player.day1Claimed = false;
		player.day2Claimed = false;
		player.day3Claimed = false;
		player.day4Claimed = false;
		player.day5Claimed = false;
		player.day6Claimed = false;
		player.day7Claimed = false;
		
	}
	
	public static void resetTime() {
		long timer = 86400000;
		long time =  player.lastDailyClaim;
		time -= timer;
		
	}
	
	public void handleVote() {
		
		System.err.println("handling voting reward...");
		
		if(System.currentTimeMillis() + 86400000 > player.lastVoteTime && !player.day1Claimed) {
			player.getPacketSender().sendConfig(1811, 1);
			player.hasVotedToday = true;
			System.out.println("handling voting reward 1");
			boolean isGreater = System.currentTimeMillis() + 86400000 > player.lastVoteTime;
		} else if(System.currentTimeMillis() + 86400000 > player.lastVoteTime && !player.day2Claimed) {
			player.getPacketSender().sendConfig(1813, 1);
			player.hasVotedToday = true;
		} else if(System.currentTimeMillis() + 86400000 > player.lastVoteTime && !player.day3Claimed) {
			player.getPacketSender().sendConfig(1815, 1);
			player.hasVotedToday = true;
		} else if(System.currentTimeMillis() + 86400000 > player.lastVoteTime && !player.day4Claimed) {
			player.getPacketSender().sendConfig(1817, 1);
			player.hasVotedToday = true;
		} else if(System.currentTimeMillis() + 86400000 > player.lastVoteTime && !player.day5Claimed) {
			player.getPacketSender().sendConfig(1819, 1);
			player.hasVotedToday = true;
		} else if(System.currentTimeMillis() + 86400000 > player.lastVoteTime && !player.day6Claimed) {
			player.getPacketSender().sendConfig(1821, 1);
			player.hasVotedToday = true;
		}
	}
	
	public void handleDailyLogin() {
		if(System.currentTimeMillis() + 86400000 > player.lastLogin) {
			if(!player.day1Claimed) {
				player.getPacketSender().sendConfig(1810, 1);
			} else if(!player.day2Claimed) {
				player.getPacketSender().sendConfig(1812, 1);
			} else if(!player.day3Claimed) {
				player.getPacketSender().sendConfig(1814, 1);
			} else if(!player.day4Claimed) {
				player.getPacketSender().sendConfig(1816, 1);
			} else if(!player.day5Claimed) {
				player.getPacketSender().sendConfig(1818, 1);
			} else if(!player.day6Claimed) {
				player.getPacketSender().sendConfig(1820, 1);
			}
		}
	}
	
	public void claimDay7() {
		
		if(player.day1Claimed && player.day2Claimed && player.day3Claimed && player.day4Claimed && player.day5Claimed && player.day6Claimed
				&& player.day7Claimed) {
			player.getInventory().add(7100, 1).add(7118, 1).add(21044, 1).add(11425, 1).add(11529, 1);
			resetData();
		} else {
			player.sendMessage("@red@Claim day 1-6 first before u can claim day 7");
			return;
		}
	}
	
	public void setDataOnLogin() {
		if(player.day1Claimed) {
			player.getPacketSender().sendConfig(1810, 1);
			player.getPacketSender().sendConfig(1811, 1);
		}
		if(player.day2Claimed) {
			player.getPacketSender().sendConfig(1812, 1);
			player.getPacketSender().sendConfig(1813, 1);
		}
		if(player.day3Claimed) {
			player.getPacketSender().sendConfig(1814, 1);
			player.getPacketSender().sendConfig(1815, 1);
		}
		if(player.day4Claimed) {
			player.getPacketSender().sendConfig(1816, 1);
			player.getPacketSender().sendConfig(1817, 1);
		}
		if(player.day5Claimed) {
			player.getPacketSender().sendConfig(1818, 1);
			player.getPacketSender().sendConfig(1819, 1);
		}	
		if(player.day6Claimed) {
			player.getPacketSender().sendConfig(1820, 1);
			player.getPacketSender().sendConfig(1821, 1);
		}
		
	}
	
	public static boolean handleRewards(int buttonId) { //Sec agen :D

		System.out.println("buttonId="+buttonId);
		if (buttonId >= -8307 && buttonId <= 8312) {
			System.out.println("blocked buttonId="+buttonId);
			return true;
		}
		long timer = 86400000;
		long time =  player.lastDailyClaim;//current 0 because of test..
		int index = buttonId;
		
		switch(index) {
		case -8312:
			// also votes -> true
			if(time > 1 && !player.day1Claimed && player.hasVotedToday) { // true
				player.day1Claimed = true;
				player.lastDailyClaim = System.currentTimeMillis() + timer;
				player.getInventory().add(1543, 1).add(7100, 1);
				player.hasVotedToday = false;
				resetTime();
				return true;
			} else {
				String msg = null;
				if (player.lastDailyClaim > System.currentTimeMillis()) {
					msg = "You have already claimed a daily reward within the last 24hours.";
				} else
					msg = "You need to vote before claiming this reward.";
				
				if (msg != null)
					player.sendMessage(msg);
				return false;
			}
		case -8311:
			if(player.day1Claimed && !player.day2Claimed && player.hasVotedToday) { // can claim if day 1 true, day 2 not sorry for that give me 1 min go
				if(time < 1) {
					player.day2Claimed = true;
					player.lastDailyClaim = System.currentTimeMillis() + 86400000;
					player.getInventory().add(7118, 1).add(7100, 1);
					player.hasVotedToday = false;
				return true;
			} else {
				return false;
			}
			}
			break;
			
		case -8310:
			if (player.day1Claimed && player.day2Claimed && !player.day3Claimed && player.hasVotedToday) {
				if(time < 1) {
					player.day3Claimed = true;
					player.lastDailyClaim = System.currentTimeMillis() + 86400000;
					player.getInventory().add(2572, 1).add(11425, 1);
					player.hasVotedToday = false;
				return true;
			} else {
				return false;
			}
			
			}
			break;
			
		case -8309:
			if (player.day1Claimed && player.day2Claimed && player.day3Claimed && !player.day4Claimed && player.hasVotedToday) {
				if(time < 1) {
					player.day4Claimed = true;
					player.lastDailyClaim = System.currentTimeMillis() + 86400000;
					player.getInventory().add(7118, 1).add(21045, 1);
					player.hasVotedToday = false;
				return true;
			} else {
				return false;
			}
			}
			break;
			
		case -8308:
			if (player.day1Claimed && player.day2Claimed && player.day3Claimed && player.day4Claimed && !player.day5Claimed && player.hasVotedToday) {
				if(time < 1) {
					player.day5Claimed = true;
					player.lastDailyClaim = System.currentTimeMillis() + 86400000;
					player.getInventory().add(11527, 1).add(7100, 1);
					player.hasVotedToday = false;
				return true;
			} else {
				return false;
			}
			}
			break;
			
		case -8307:
			if (player.day1Claimed && player.day2Claimed && player.day3Claimed && player.day4Claimed && player.day5Claimed && !player.day6Claimed && player.hasVotedToday) {
				if(time < 1) {
					player.day6Claimed = true;
					player.lastDailyClaim = System.currentTimeMillis() + 86400000;
					player.getInventory().add(1543, 1).add(7118, 1);
					player.hasVotedToday = false;
				return true;
			} else {
				return false;
			}
			}
			break;
		}
		return false;
	}
	

	
	
	public static long displayTimeLeft(long timeInMs) {
		
		timeInMs = timeInMs - System.currentTimeMillis();
		
		long seconds = (int) Math.ceil((timeInMs / 1000));
		
		if(seconds < 0) {
			return 0;
		}
		
		LocalTime timeOfDay = LocalTime.ofSecondOfDay(seconds);
		String time = timeOfDay.toString();
		
	    player.getPacketSender().sendString(57222, time);
	    
	    return seconds;
		
	}
	
	public void processTime() {
		
		TaskManager.submit(new Task(1, player, false) {
			@Override
			protected void execute() {
		    	displayTimeLeft(player.lastDailyClaim);
			}
		});
	}
	




	int[] february = new int[29];

	
	

	public static Item getRewardByMonth(String month,int day){
		switch (month) {
			case"january":
				return January.reward[day-1];
				case"february":
				return February.reward[day-1];
				case"march":
				return March.reward[day-1];
				case"april":
				return April.reward[day-1];
				case"may":
				return May.reward[day-1];
				case"june":
				return June.reward[day-1];
				case"july":
				return July.reward[day-1];
				case"august":
				return August.reward[day-1];
				case"september":
				return September.reward[day-1];
				case"october":
				return October.reward[day-1];
				case"november":
				return November.reward[day-1];
				case"december":
				return December.reward[day-1];


		}
		return null;
	}
	
	public void displayRewards() {

		int interfaceId = 57224;

		Calendar rightNow = Calendar.getInstance();


		for(int i = 0; i < 10; i++) {
			date = LocalDateTime.of(
					LocalDate.now().plusDays(i),
					LocalTime.of(12, 0)
			);
			String month = ""+date.getMonth().toString().toLowerCase();
			Integer maxSize = 6;

			String newMonth = "";
			if(month.length() > maxSize ){
				newMonth = month.substring(0, maxSize);
				newMonth = newMonth+"...";
			} else {
				newMonth = month;
			}


				if(i == 0) {
					player.getPacketSender().sendString((interfaceId+(i)),"Today");
				} else if (i == 1) {
					player.getPacketSender().sendString((interfaceId+(i)),"Tomorrow");
				}	else {
					player.getPacketSender().sendString((interfaceId+(i)),""+date.getDayOfMonth()+" "+newMonth+" ");
				}
				player.getPacketSender().sendItemOnInterface( 57223,getRewardByMonth(date.getMonth().toString().toLowerCase(),date.getDayOfMonth()).getId(), i, getRewardByMonth(date.getMonth().toString().toLowerCase(),date.getDayOfMonth()).getAmount());
			//player.getPacketSender().sendItemOnInterface(57223,getRewardByMonth(date.getMonth().toString().toLowerCase(),date.getDayOfMonth()).getId(),getRewardByMonth(date.getMonth().toString().toLowerCase(),date.getDayOfMonth()).getAmount());
		}
		//its gonna be a ong day for u lmao

		player.getPacketSender().sendInterface(57210);


	}

	public static void showInterface(Player player2) {
		// TODO Auto-generated method stub
		
	}
}