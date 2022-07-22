/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arlania.world.content.upgrading;

import com.arlania.model.Item;

/**
 *
 * @author Infinity 28/11/2019
 * Copyright Megascape.net
 */
public enum UpgradeData {
    /*
    Starting item   Rewarded Item    Chance x/100     Coins Required
    */
    SLAYER1(new Item(11550, 1), new Item(11549, 1), 50, 1500000),
    SLAYER2(new Item(11549, 1), new Item(11546, 1), 50, 1700000),
    SLAYER3(new Item(11546, 1), new Item(11547, 1), 65, 2000000),
    SLAYER4(new Item(11547, 1), new Item(11548, 1), 85, 2200000),
    KEY(new Item(11559, 1), new Item(20900, 1), 50, 5000),
    AMMY(new Item(11423, 1), new Item(4770, 1), 25, 10000000),
    RING4(new Item(20054, 1), new Item(4743, 1), 25, 10000000),
    BOW(new Item(937, 1), new Item(20643, 1), 25, 1000000),
    HELM(new Item(5140, 1), new Item(3645, 1), 25, 5000000),
    BODY(new Item(5142, 1), new Item(3643, 1), 25, 5000000),
    LEGS(new Item(5141, 1), new Item(3644, 1), 25, 5000000),
    GLOVES(new Item(5143, 1), new Item(3648, 1), 25, 5000000),
    BOOTS(new Item(5144, 1), new Item(3647, 1), 25, 5000000),
    WEP(new Item(5146, 1), new Item(20051, 1), 25, 5000000),
    OFF_HAND(new Item(5147, 1), new Item(3642, 1), 25, 5000000),
    CAPE(new Item(4764, 1), new Item(5179, 1), 25, 5000000),
    CAPE1(new Item(4762, 1), new Item(5178, 1), 25, 5000000),
    CAPE2(new Item(5148, 1), new Item(5180, 1), 25, 5000000),
    PET(new Item(3887, 1), new Item(1667, 1), 25, 5000000),
    RING5(new Item(4743, 1), new Item(20550, 1), 5, 5000000),
    CASKET(new Item(2714, 1), new Item(2717, 1), 50, 5000);
	 
	private Item required, reward;
	private int chance, coinsRequired;
	
	UpgradeData(Item required, Item reward, int chance, int coinsRequired) {
		this.required = required;
		this.reward = reward;
		this.chance = chance;
		this.coinsRequired = coinsRequired;
	}
	
	public static int[] itemList = { 2714, 11549, 11546, 11547, 11559, 11550, 11423, 20054, 937, 5140, 5141, 5142, 5143, 5144, 5146, 5147, 4762, 4764, 5148, 3887, 4743 };
    
	public Item getRequired() {
		return required;
	}

	public Item getReward() {
		return reward;
	}

	public int getChance() {
		return chance;
	}
	
	public int getCoinsRequired() {
		return coinsRequired;
	}
	
}