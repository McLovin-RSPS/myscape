package com.arlania.world.content.combat.strategy.impl;

import com.arlania.model.Graphic;
import com.arlania.model.Position;

public class saveLocGraphic {

    Position loc;
    Graphic graphic;

    public saveLocGraphic(Position pos, Graphic graphic){
        this.loc = pos;
        this.graphic = graphic;
    }

    public Graphic getGraphic() {
        return graphic;
    }

    public Position getLoc() {
        return loc;
    }
}
