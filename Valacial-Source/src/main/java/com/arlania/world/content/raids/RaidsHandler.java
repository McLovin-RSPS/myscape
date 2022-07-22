package com.arlania.world.content.raids;

import java.util.ArrayList;

import com.arlania.world.World;
import com.arlania.world.entity.impl.player.Player;

public class RaidsHandler {

	public static ArrayList<Integer> playersInLobby = new ArrayList<>();

	private final static RaidsData information[] = RaidsData.values();

	public void lobby() {
		for (int i = 0; i < playersInLobby.size(); i++) {
			for (Player p : World.getPlayers()) {
				for (RaidsData data : information) {

				}
			}
		}
	}

	public void countdownTask() {

	}

	public void start() {

	}

	public void spawnWaves() {

	}

	public void finish() {

	}
}
