package com.arlania.rewardslist;

import com.arlania.model.definitions.ItemDefinition;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Adam_#6723
 * Enum which holds attributes for the reward interface.
 *
 */

public enum RewardsData {
	
	DONATOR_BOX(0, new int[] {2572, 11527, 3683, 3684, 3685, 20259, 11529, 11531, 3313, 3314, 3315, 3316, 3317, 11533, 11423, 3084} , "Donator Box"),
	SUPER_DONATOR_BOX(1, new int[] {11527, 11529, 11531, 3819, 3820, 3821, 3822, 3823, 3824, 3825, 11533, 11423, 4060, 15355, 15356 }, "Super Donator Box"),
	EXTREME_DONATOR_BOX(2, new int[] {3285, 3286, 3287, 20644, 20645}, "Extreme Donator Box"),
	SPONSOR_DONATOR_BOX(3, new int[] {21077, 21078, 21082,   2096, 2097, 2098, 11533}, "Sponsor Donator Box"),
	EXECUTIVE_DONATOR_BOX(4, new int[] {5022, 21060, 13196, 13197, 13198, 20254,13206, 13207, 13199 }, "Executive Donator Box"),
	LUCKY_BLOCK(5, new int[] {5022, 21013,20086,20087,20088,20252,21020,21021, 2104,2758,2095,2096,2097,2098 }, "Lucky Block"),
    STARTER_KEY(6, new int[] {5022, 21082,21077,21078,11529,20500,20258,20501,5016, 20502,20503, 2104,2758,2095,2096,2097,2098}, "Starter Key"),
	INSANITY_BOX(7, new int[] {3959,3952,19066,19045,19043,19042,18927,19041,19040,19021,19020,19019,18986,18951,18929, 18929,18926,18922,18920,18892,18888, 3959,18929,18926,18922,18920,18892,18888}, "Insanity Box"),
	PET_MYSTERY_BOX(8, new int[] {2090, 2103, 7582, 7583, 2771, 2772, 3620, 3621, 3622, 19935, 19936, 19937, 2758, 2759, 2760, 2761, 2762}
, "Pet MBox"),
	BFG_GOODIEBOX(9, new int[] {5022, 20702,20701,20700,20706,20703,20704,20695, 2104,
			2758, 2095, 2096, 2097, 2098},"BFG 9000 Goodiebox"),
	EXECUTIVE_GOODIEBOX(10, new int[] {5022, 20702,20701,20700,20706,20703,20704,20695, 2104,
			2758, 2095, 2096, 2097, 2098},"Executive Rank Goodiebox"),
	LAUNCH_CASKET(11, new int[] {5022, 20702,20701,20700,20706,20703,20704,20695, 2104,
								2758, 2095, 2096, 2097, 2098},"Launch Casket"),
	LAUNCH_CASLET(12, new int[] {5022, 11425,20095,20096,20256,20256,20256,20256,5020,5020,5020,5020,
					20097,20098,20099,20100,11425,20095,20096,20097,20098,20099,20100,20095,20096,20097,20098,20099,20100,
					20095,20096,20097,20098,20099,20100,20095,20096,20097,20098,20099,20100,20095,20096,20097,20098,20099,
					20100,20095,20096,20097,20098,20099,20100,20603,20604,20605, 2104, 2758, 2095, 2096, 2097, 2098}, "Launch Casket(i)"),
	
	;		
	private int index;
	private String RewardName;
	private int item[];
	private int amount;
	
	public int getIndex() {
		return index;
	}

	public int[] getItemID() {
		return item;
	}

	public String getText() {
		return RewardName;
	}


	public int getAmount() {
		return amount;
	}

	public static void main(String[] args) {
		ItemDefinition.init();
		try (BufferedWriter out = new BufferedWriter(new FileWriter(new File("./rewards.txt"))))
		{
			for (RewardsData rewardsData : values()) {
				out.write(rewardsData.RewardName + "\n");
				for (int i = 0; i < rewardsData.item.length; i++) {
					out.write(ItemDefinition.getItemName(rewardsData.item[i]) + "\n");
				}
				out.write("\n");
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	RewardsData(int index, int[] item, String RewardName) {
		this.index = (index);
		this.item = (item);
		this.RewardName = (RewardName);
	}
	
	static final Map<Integer, RewardsData> byId = new HashMap<Integer, RewardsData>();
	
	static {
		for (RewardsData e : RewardsData.values()) {
			if (byId.put(e.getIndex(), e) != null) {
				  throw new IllegalArgumentException("duplicate id: " + e.getIndex());
			}
		}
	}
	
	public static RewardsData getById(int id) {
		if(byId.get(id) == null) {
			return byId.get(0);
		}
	    return byId.get(id);
	}

	
}