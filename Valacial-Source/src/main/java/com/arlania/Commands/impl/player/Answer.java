package com.arlania.Commands.impl.player;

import com.arlania.Commands.Command;
import com.arlania.Commands.CommandInfo;
//import com.arlania.world.content.TriviaBot;
import com.arlania.world.entity.impl.player.Player;

@CommandInfo(
        command = {"answer", "trivia"},
        description = "Answer the trivia questions."
)
public class Answer extends Command {
    @Override
    public void execute(Player player, String command) {
        String[] string = command.split("-");
        String triviaAnswer = string[1];
        //if (TriviaBot.acceptingQuestion()) {
           // TriviaBot.attemptAnswer(player, triviaAnswer);
       // } else {
      //   //   player.sendMessage("There is currently no questions being asked.");
       // }
    }
}
