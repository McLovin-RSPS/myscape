package com.arlania.world.content.newminigames.impl.stadium;

import com.arlania.world.content.newminigames.impl.stadium.impl.WingAttack;
import com.arlania.world.content.newminigames.impl.stadium.impl.DragonClaw;
import com.arlania.world.content.newminigames.impl.stadium.impl.FireBlast;
import com.arlania.world.content.newminigames.impl.stadium.impl.AirSlash;
import com.arlania.world.content.newminigames.impl.stadium.impl.Thunder;
import com.arlania.world.content.newminigames.impl.stadium.impl.Thundershock;
import com.arlania.world.content.newminigames.impl.stadium.impl.Thunderbolt;
import com.arlania.world.content.newminigames.impl.stadium.impl.VoltTackle;
import com.arlania.world.content.newminigames.impl.stadium.impl.IceBeam;
import com.arlania.world.content.newminigames.impl.stadium.impl.ToxicRain;
import com.arlania.world.content.newminigames.impl.stadium.impl.RockSmash;
import com.arlania.world.content.newminigames.impl.stadium.impl.Whirlpool;
import com.arlania.world.content.newminigames.impl.stadium.impl.DarkPulse;
import com.arlania.world.content.newminigames.impl.stadium.impl.PsychicBlast;
import com.arlania.world.content.newminigames.impl.stadium.impl.FireStorm;
import com.arlania.world.content.newminigames.impl.stadium.impl.Psychic;
import com.arlania.world.content.newminigames.impl.stadium.impl.Rockslide;
import com.arlania.world.content.newminigames.impl.stadium.impl.LavaPlume;
import com.arlania.world.content.newminigames.impl.stadium.impl.HyperBeam;
import com.arlania.world.content.newminigames.impl.stadium.impl.Incinerate;

public enum PokemonData {

	NULL(-1),
	CHARIZARD(18, new PokemonMove[] { new DragonClaw(), new FireBlast(), new AirSlash(), new WingAttack()  }),
	PIKACHU(23, new PokemonMove[] { new Thunder(), new Thundershock(), new Thunderbolt(), new VoltTackle() }),
	RAICHU(24, new PokemonMove[] { new Thunder(), new Thundershock(), new Thunderbolt(), new VoltTackle() }),
	SQUIRTLE(22, new PokemonMove[] { new IceBeam(), new ToxicRain(), new RockSmash(), new Whirlpool()  }),
	GROUDON(17, new PokemonMove[] { new Rockslide(), new LavaPlume(), new FireStorm(), new Incinerate()  }),
	LUCARIO(19, new PokemonMove[] { new DarkPulse() }),
	MEWTWO(21, new PokemonMove[] { new Psychic(), new PsychicBlast(), new DarkPulse(), new HyperBeam()  });

	PokemonData(int id) {
		this.id = id;
		this.moveArr = new PokemonMove[] { new WingAttack() };
	}

	PokemonData(int id, PokemonMove[] moveArr) {
		this.id = id;
		this.moveArr = moveArr;
	}

	private int id;
	private PokemonMove[] moveArr;

	public int getId() {
		return this.id;
	}

	public PokemonMove[] getMoves() {
		return this.moveArr;
	}

	public static PokemonData forId(int id) {
		for (PokemonData data : PokemonData.values()) {
			if (data.id == id)
				return data;
		}
		return NULL;
	}

}
