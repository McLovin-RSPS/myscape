package com.arlania.world.content;

import com.arlania.world.content.SaveInteger;
import com.arlania.world.content.tutorial.TutorialStages;
import com.arlania.world.entity.impl.player.Player;

public class SaveTutorialStage extends SaveInteger {

    public SaveTutorialStage(String name) {
        super(name);
    }

    @Override
    public void setValue(Player player, int value) {
        player.setTutorialStage(TutorialStages.values()[value]);
    }

    @Override
    public Integer getValue(Player player) {
        return player.getTutorialStage().ordinal();
    }

    @Override
    public int getDefaultValue() {
        return TutorialStages.INITIAL_STAGE.ordinal();
    }
}
