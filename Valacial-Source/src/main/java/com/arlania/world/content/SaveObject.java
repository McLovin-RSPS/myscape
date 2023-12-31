package com.arlania.world.content;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import com.arlania.world.entity.impl.player.Player;


/**
 * @author Arsen Maxyutov.
 */
public abstract class SaveObject {

	/**
	 * The name identifier of the SaveObject.
	 */
	private String name;

	/**
	 * Constructs a new SaveObject with the specified name.
	 *
	 * @param name
	 */
	public SaveObject(String name) {
		this.name = name;
	}

	/**
	 * Gets the name of the SaveObject.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * Saves the SaveObject to the Player's file.
	 *
	 * @param player
	 * @param writer
	 * @throws java.io.IOException
	 */
	public abstract boolean save(Player player, BufferedWriter writer) throws IOException;

	/**
	 * Loads a SaveObject from the Player's file.
	 *
	 * @param player
	 * @param values
	 * @param reader
	 * @throws java.io.IOException
	 */
	public abstract void load(Player player, String values, BufferedReader reader)
			throws IOException;

}
