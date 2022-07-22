package com.arlania.model;

import com.arlania.util.Misc;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;


/**
 * Represents a player's privilege rights.
 * @author Gabriel Hannason
 */

public enum PlayerRights {

	/*
	 * A regular member of the server.
	 */
	PLAYER(-1, null, 1, 1),
	/*
	 * A moderator who has more privilege than other regular members and donators.
	 */
	MODERATOR(-1, "<col=20B2AA>", 1, 1.5),

	/*
	 * The second-highest-privileged member of the server.
	 */
	ADMINISTRATOR(-1, "<col=FFFF64>", 1, 1.5),

	/*
	 * The highest-privileged member of the server
	 */
	OWNER(-1, "<col=B40404>", 1, 1.5),

	/*
	 * The Developer of the server, has same rights as the owner.
	 */
	DEVELOPER(-1, "<col=fa0505>", 1, 1.5),


	/*
	 * A member who has donated to the server or is a veteran. 
	 */
	DONATOR(-1, "<shad=FF7F00>", 1.5, 1.1),
	SUPER_DONATOR(-1, "<col=787878>", 1.5, 1.2),
 	EXTREME_DONATOR(-1, "<col=D9D919>", 2, 1.3),
	SPONSOR_DONATOR(-1, "<shad=697998>", 2.5, 1.4),
	EXECUTIVE_DONATOR(-1, "<col=0EBFE9>", 3, 1.5),
	DIVINE_DONATOR(-1, "<shad=FF7F00>", 3.5, 1.6),
	VETERAN(-1, "@whi@", 3, 1.5),// Testing / Colour code may be off
	PARTNER_DONATOR(-1, "@yel@", 4, 1.8 ),// Sponsor will get 4 loyalty bonus, 1.8 experience bonus. -- The hex code might not work so we might have to use a used one
	CONTRIBUTER(-1, "@dre@", 5, 1.7),// Contributer will get 5 loyalty bonus, 1.7 experience bonus.

	/*
	 * A member who has the ability to help people better.
	 */
	SUPPORT(-1, "<col=0EBFE9>", 1, 1.5),

	/*
	 * A member who has been with the server for a long time.
	 */
	YOUTUBER(-1, "<col=CD661D>", 1, 1.1),
	FORUM_DEVELOPER(-1, "<col=fa0505>", 1, 1.1);

	PlayerRights(int yellDelaySeconds, String yellHexColorPrefix, double loyaltyPointsGainModifier, double experienceGainModifier) {
		this.yellDelay = yellDelaySeconds;
		this.yellHexColorPrefix = yellHexColorPrefix;
		this.loyaltyPointsGainModifier = loyaltyPointsGainModifier;
		this.experienceGainModifier = experienceGainModifier;
	}
	
	private static final ImmutableSet<PlayerRights> STAFF = Sets.immutableEnumSet(SUPPORT, MODERATOR, ADMINISTRATOR, OWNER, DEVELOPER,FORUM_DEVELOPER);
	private static final ImmutableSet<PlayerRights> MEMBERS = Sets.immutableEnumSet(DONATOR, SUPER_DONATOR, EXTREME_DONATOR, SPONSOR_DONATOR, DIVINE_DONATOR, EXECUTIVE_DONATOR, PARTNER_DONATOR, CONTRIBUTER);
	
	/*
	 * The yell delay for the rank
	 * The amount of seconds the player with the specified rank must wait before sending another yell message.
	 */
	private int yellDelay;
	private String yellHexColorPrefix;
	private double loyaltyPointsGainModifier;
	private double experienceGainModifier;
	
	public int getYellDelay() {
		return yellDelay;
	}
	
	/*
	 * The player's yell message prefix.
	 * Color and shadowing.
	 */
	
	public String getYellPrefix() {
		return yellHexColorPrefix;
	}
	
	/**
	 * The amount of loyalty points the rank gain per 4 seconds
	 */
	public double getLoyaltyPointsGainModifier() {
		return loyaltyPointsGainModifier;
	}
	
	public double getExperienceGainModifier() {
		return experienceGainModifier;
	}
	
	public boolean isStaff() {
		return STAFF.contains(this);
	}
	
	public boolean isMember() {
		return MEMBERS.contains(this);
	}
	
	/**
	 * Gets the rank for a certain id.
	 * 
	 * @param id	The id (ordinal()) of the rank.
	 * @return		rights.
	 */
	public static PlayerRights forId(int id) {
		for (PlayerRights rights : PlayerRights.values()) {
			if (rights.ordinal() == id) {
				return rights;
			}
		}
		return null;
	}
	@Override
	public String toString() {
		return Misc.ucFirst(name().replaceAll("_", " "));
	}

	public boolean isAboveOrEqual(PlayerRights rights) {
		return this.ordinal() >= rights.ordinal();
	}

	public boolean shouldDebug() {
		// TODO Auto-generated method stub
		return false;
	}

}
