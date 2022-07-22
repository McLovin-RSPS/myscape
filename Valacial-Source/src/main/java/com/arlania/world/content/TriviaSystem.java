package com.arlania.world.content;

import java.util.Random;

import com.arlania.world.World;
import com.arlania.world.entity.impl.player.Player;

public class TriviaSystem {

	enum TriviaData {
		QUESTION_1("donabab", "Abbadon"),

		QUESTION_2("kiln", "Link"),

		QUESTION_3("oetmhabp", "Baphomet"),

		QUESTION_4("Who is the boss based off Naruto?", "Uchiha Madara"),

		QUESTION_5("Which Marvel Character is a boss", "Thanos"),
		
		QUESTION_6("Where can you find more server information, such as rules?", "Discord"),

		QUESTION_7("What slayer level is required for boss slayer?", "92"),

		QUESTION_8("What is the highest tier of pet on ImaginePS?", "Dream"),

		QUESTION_9("How many different types of Infinity Stones are required to charge the Infinity Gauntlet?", "6"), 
		
		
		
		LAST_QUESTION("Who is a great Administrator", "The x file"); // kk
																														// those
																														// are
																														// good
																														// enough
																														// for
																														// testing

		TriviaData(String question, String answer) {
			this.question = question;
			this.answer = answer;
		}

		private String question, answer;

		public String getQuestion() {
			return question;
		}

		public String getAnswer() {
			return answer;
		}
	}

	private static int timer = 2000; // 20minutes
	private static boolean active = false;
	private static TriviaData currentQuestion = null;
	
	public static String getCurrentQuestion() {
		return currentQuestion == null ? "None" : currentQuestion.getQuestion().toUpperCase().substring(0, 1) + currentQuestion.getQuestion().toLowerCase().substring(1);
	}

	public static void tick() {

		if (!active) {
			if (timer < 1) {
				startTrivia();
				timer = 2000;
			} else {
				timer--;
			}
		}
	}

	private static final TriviaData[] TRIVIA_DATA = TriviaData.values();

	private static void startTrivia() {
		setAndAskQuestion();
	}

	private static void setAndAskQuestion() {
		active = true;
		currentQuestion = TRIVIA_DATA[new Random().nextInt(TRIVIA_DATA.length)];
		World.sendMessage("<shad=1><img=10>@red@[TRIVIA]<img=10> @red@" + currentQuestion.getQuestion() + "");
		World.getPlayers().forEach(PlayerPanel::refreshPanel);
	}
	
	public static void answer(Player player, String answer) {
		if(!active) {
			player.sendMessage("<shad=1>@red@There is no trivia going on at the moment");
			return;
		}
		if(answer.equalsIgnoreCase(currentQuestion.getAnswer())) {
			player.getInventory().add(8989, 1);
			active = false;
			World.sendMessage("<shad=1><img=10>@red@[TRIVIA]<img=10> @blu@" + player.getUsername() + "@bla@ has recieved a @red@Goodiebag @bla@from Trivia");
			World.sendMessage("<shad=1><img=10>@red@[TRIVIA]<img=10> @bla@ The answer for the trivia to the question was @red@" + currentQuestion.answer);
			currentQuestion = null;
			World.getPlayers().forEach(PlayerPanel::refreshPanel);//soz ok is there anything else u need or is that all
			player.sendMessage("@bla@congrats, you've guessed correctly and received a@blu@ Goodie bag@bla@!");
			
		} else {
			player.sendMessage("@bla@Incorrect answer - your answer was: @red@" + answer);
		}
	}

}
