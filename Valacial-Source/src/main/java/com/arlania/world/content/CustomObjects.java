package com.arlania.world.content;

import java.util.concurrent.CopyOnWriteArrayList;

import com.arlania.engine.task.Task;
import com.arlania.engine.task.TaskManager;
import com.arlania.model.GameObject;
import com.arlania.model.GroundItem;
import com.arlania.model.Item;
import com.arlania.model.Locations;
import com.arlania.model.Position;
import com.arlania.model.Locations.Location;
import com.arlania.world.World;
import com.arlania.world.clip.region.RegionClipping;
import com.arlania.world.entity.impl.GroundItemManager;
import com.arlania.world.entity.impl.player.Player;

/**
 * Handles customly spawned objects (mostly global but also privately for players)
 * @author Gabriel Hannason
 */
public class CustomObjects {
	
	private static final int DISTANCE_SPAWN = 70; //Spawn if within 70 squares of distance
	public static final CopyOnWriteArrayList<GameObject> CUSTOM_OBJECTS = new CopyOnWriteArrayList<GameObject>();

	public static void init() {
		for(int i = 0; i < CLIENT_OBJECTS.length; i++) {
			int id = CLIENT_OBJECTS[i][0];
			int x = CLIENT_OBJECTS[i][1];
			int y = CLIENT_OBJECTS[i][2];
			int z = CLIENT_OBJECTS[i][3];
			int face = CLIENT_OBJECTS[i][4];
			GameObject object = new GameObject(id, new Position(x, y, z));
			object.setFace(face);
			RegionClipping.addObject(object);
		}
		for(int i = 0; i < CUSTOM_OBJECTS_SPAWNS.length; i++) {
			int id = CUSTOM_OBJECTS_SPAWNS[i][0];
			int x = CUSTOM_OBJECTS_SPAWNS[i][1];
			int y = CUSTOM_OBJECTS_SPAWNS[i][2];
			int z = CUSTOM_OBJECTS_SPAWNS[i][3];
			int face = CUSTOM_OBJECTS_SPAWNS[i][4];
			GameObject object = new GameObject(id, new Position(x, y, z));
			object.setFace(face);
			CUSTOM_OBJECTS.add(object);
			World.register(object);
		}
	}
	
	private static void handleList(GameObject object, String handleType) {
		switch(handleType.toUpperCase()) {
		case "DELETE":
			for(GameObject objects : CUSTOM_OBJECTS) {
				if(objects.getId() == object.getId() && object.getPosition().equals(objects.getPosition())) {
					CUSTOM_OBJECTS.remove(objects);
				}
			}
			break;
		case "ADD":
			if(!CUSTOM_OBJECTS.contains(object)) {
				CUSTOM_OBJECTS.add(object);
			}
			break;
		case "EMPTY":
			CUSTOM_OBJECTS.clear();
			break;
		}
	}

	public static void spawnObject(Player p, GameObject object) {
		if(object.getId() != -1) {
			p.getPacketSender().sendObject(object);
			if(!RegionClipping.objectExists(object)) {
				RegionClipping.addObject(object);
			}
		} else {
			deleteObject(p, object);
		}
	}
	
	public static void deleteObject(Player p, GameObject object) {
		p.getPacketSender().sendObjectRemoval(object);
		if(RegionClipping.objectExists(object)) {
			RegionClipping.removeObject(object);
		}
	}
	
	public static void deleteGlobalObject(GameObject object) {
		handleList(object, "delete");
		World.deregister(object);
	}

	public static void spawnGlobalObject(GameObject object) {
		handleList(object, "add");
		World.register(object);
	}
	
	public static void spawnGlobalObjectWithinDistance(GameObject object) {
		for(Player player : World.getPlayers()) {
			if(player == null)
				continue;
			if(object.getPosition().isWithinDistance(player.getPosition(), DISTANCE_SPAWN)) {
				spawnObject(player, object);
			}
		}
	}
	
	public static void deleteGlobalObjectWithinDistance(GameObject object) {
		for(Player player : World.getPlayers()) {
			if(player == null)
				continue;
			if(object.getPosition().isWithinDistance(player.getPosition(), DISTANCE_SPAWN)) {
				deleteObject(player, object);
			}
		}
	}
	
		public static boolean objectExists(Position pos) {
			return getGameObject(pos) != null;
		}

		public static GameObject getGameObject(Position pos) {
			for(GameObject objects : CUSTOM_OBJECTS) {
				if(objects != null && objects.getPosition().equals(pos)) {
					return objects;
				}
			}
			return null;
		}

		public static void handleRegionChange(Player p) {
			for(GameObject object: CUSTOM_OBJECTS) {
				if(object == null)
					continue;
				if(object.getPosition().isWithinDistance(p.getPosition(), DISTANCE_SPAWN)) {
					spawnObject(p, object);
				}
			}
		}
	
		public static void objectRespawnTask(Player p, final GameObject tempObject, final GameObject mainObject, final int cycles) {
			deleteObject(p, mainObject);
			spawnObject(p, tempObject);
			TaskManager.submit(new Task(cycles) {
				@Override
				public void execute() {
					deleteObject(p, tempObject);
					spawnObject(p, mainObject);
					this.stop();
				}
			});
		}
		
		public static void globalObjectRespawnTask(final GameObject tempObject, final GameObject mainObject, final int cycles) {
			deleteGlobalObject(mainObject);
			spawnGlobalObject(tempObject);
			TaskManager.submit(new Task(cycles) {
				@Override
				public void execute() {
					deleteGlobalObject(tempObject);
					spawnGlobalObject(mainObject);
					this.stop();
				}
			});
		}

		public static void globalObjectRemovalTask(final GameObject object, final int cycles) {
			spawnGlobalObject(object);
			TaskManager.submit(new Task(cycles) {
				@Override
				public void execute() {
					deleteGlobalObject(object);
					this.stop();
				}
			});
		}

	public static void globalFiremakingTask(final GameObject fire, final Player player, final int cycles) {
		spawnGlobalObject(fire);
		TaskManager.submit(new Task(cycles) {
			@Override
			public void execute() {
				deleteGlobalObject(fire);
				if(player.getInteractingObject() != null && player.getInteractingObject().getId() == 2732) {
					player.setInteractingObject(null);
				}
				this.stop();
			}
			@Override
			public void stop() {
				setEventRunning(false);
				GroundItemManager.spawnGroundItem(player, new GroundItem(new Item(592), fire.getPosition(), player.getUsername(), false, 150, true, 100));
				GroundItemManager.spawnGroundItem(player, new GroundItem(new Item(20952,5), fire.getPosition(), player.getUsername(), false, 150, true, 100));
			}
		});
	}
	
	/**
	 * Contains
	 * @param ObjectId - The object ID to spawn
	 * @param absX - The X position of the object to spawn
	 * @param absY - The Y position of the object to spawn
	 * @param Z - The Z position of the object to spawn
	 * @param face - The position the object will face
	 */
	
	//Only adds clips to these objects, they are spawned clientsided
	//NOTE: You must add to the client's customobjects array to make them spawn, this is just clipping!
	private static final int[][] CLIENT_OBJECTS = {
	/*** Kourend home area oof ***/
			{411, 3556, 3087, 0, 0}, //delete
			{409, 3549, 3087, 0, 0},
			{409, 3550, 3087, 0, 0},
		//	{11356, 2654, 3046, 0, 0}, //delete

			{-1, 1615, 3662, 0, 0}, //delete
			{-1, 1611, 3671, 0, 0}, //delete
			{-1, 1611, 3668, 0, 0}, //delete
			{-1, 1611, 3675, 0, 0}, //delete
			{-1, 1611, 3679, 0, 0}, //delete
			{-1, 1648, 3683, 0, 0}, //delete
			{409,2741, 2865,0,1},
			{411,2741, 2865,0,1},
			{3422, 3812, 3502, 0, 0}, //delete
			{-1, 1633, 3685,  0, 0}, //delete
			{-1, 1635, 3685, 0, 0}, //delete
			{-1, 1639, 3685, 0, 0}, //delete
			{-1, 1640, 3685, 0, 0}, //delete
			{-1, 1637, 3684, 0, 0}, //delete
			{-1, 1637, 3683, 0, 0}, //delete
			{-1, 1634, 3683, 0, 0}, //delete
			{-1, 1638, 3683, 0, 0}, //delete
			{-1, 1645, 3669, 0, 0}, //delete
			{-1, 1626, 3681, 0, 0}, //delete
			{409, 2741, 2865, 0, 1}, //pray altar
			{411, 2741, 2862, 0, 1}, //turmoil
			{3422, 3812, 3502, 0, 0}, //delete
			
			
						
			{2213, 1635, 3670, 0, 2}, //bank
			{2213, 1636, 3670, 0, 2}, //bank
			{2213, 1637, 3670, 0, 2}, //bank
			
			{2213, 3344, 3134, 0, 2}, //bank
			{2213, 3345, 3134, 0, 2}, //bank
			{2213, 3346, 3134, 0, 2}, //bank
			{2213, 3347, 3134, 0, 2}, //bank
			{2213, 3348, 3134, 0, 2}, //bank
			{2213, 3349, 3134, 0, 2}, //bank
			{2213, 3350, 3134, 0, 2}, //bank
			{2213, 3351, 3134, 0, 2}, //bank
			{2213, 3352, 3134, 0, 2}, //bank
			{2213, 3353, 3134, 0, 2}, //bank
			{2213, 3354, 3134, 0, 2}, //bank
			{2213, 3355, 3134, 0, 2}, //bank
			{2213, 3356, 3134, 0, 2}, //bank
			{2213, 3357, 3134, 0, 2}, //bank
			{2213, 3358, 3134, 0, 2}, //bank
			{2213, 3359, 3134, 0, 2}, //bank
			{2213, 3360, 3134, 0, 2}, //bank
			{2213, 3361, 3134, 0, 2}, //bank
			{2213, 3362, 3134, 0, 2}, //bank
			
			{2111, 3833, 3485, 0, 2}, //afk ore
			{2111, 3836, 3482, 0, 2}, //afk ore
			{2111, 3830, 3477, 0, 2}, //afk ore
			{9222, 3692, 2965, 0, 2}, //afk tree
			{9222, 3694, 2965, 0, 2}, //afk tree
			{9222, 3696, 2965, 0, 2}, //afk tree

			{2213, 3681, 2981, 0, 3},  //newHome Bank
			{2213, 3681, 2982, 0, 3},  //newHome Bank
			{2213, 3681, 2983, 0, 3},  //newHome Bank
			{409, 3678, 2990, 0, 3},  //newHome Bank
			{411, 3674, 2990, 0, 1},  //newHome Bank



			{2213, 1633, 3674, 0, 1}, //bank

			{2213, 1608, 3667, 0, 1}, //bank
			{2213, 1608, 3668, 0, 1}, //bank
			{2213, 1608, 3669, 0, 1}, //bank
			{2213, 1608, 3670, 0, 1}, //bank
			
			{2213, 1608, 3676, 0, 1}, //bank
			{2213, 1608, 3677, 0, 1}, //bank
			{2213, 1608, 3678, 0, 1}, //bank
			{2213, 1608, 3679, 0, 1}, //bank
			{884,3540,3101,0,1},
			
			{818, 1608, 3674, 0, 3}, //blockade of armour 
			{818, 1608, 3673, 0, 3}, //blockade of armour 
			{818, 1608, 3672, 0, 3}, //blockade of armour 
			

			{4875, 1646, 3683, 0, 1}, //thieving
			{4874, 1647, 3683, 0, 1}, //thieving
			{4876, 1648, 3683, 0, 1}, //thieving
			{4877, 1649, 3683, 0, 1}, //thieving
			{4878, 1650, 3683, 0, 1}, //thieving
			
			
			//NEW HOME BANK
			{2213,3085,2978,0,1},
			{2213,3085,2979,0,1},
			{2213,3085,2980,0,1},
			{2213,3085,2981,0,1},
			{2213,3085,2982,0,1},
			{2213,3085,2983,0,1},
			
			//ALTARS NEW HOME
			{6552,3106,2965,0,0},
			{411,3099,2963,0,1},
			{409,3101,2965,0,0},
			
			
			{4845,3681,2965,0,0},
			
			//upgrader
			{3422, 3812, 3502, 0, 0}, //delete
			
			//Scoreboard
			{3192,3085,2997,0,1},
			
			//Thievingstalls
			{4875,3091,2987,0,1},
			{4874,3090,2987,0,1},
			{4876,3089,2987,0,1},
			{4877,3088,2987,0,1},
			{4878,3087,2987,0,1},
			
			//burnt chest
			//well of goodwill
			{884,3540,3101,0,1},
			
			//Crystal chest
			{172,3093,2985,0,1},
			
			
			{172, 1625, 3680, 0, 1}, //chest
			{10620, 1625, 3681, 0, 1}, //chest
			
			/*** Bas Extreme Donator zone ***/
			
			{47180, 3413, 2919, 0, 0}, //Frost drags teleport
			
			{4875, 3451, 2881, 0, 0}, //Thief stalls
			{4874, 3450, 2881, 0, 0}, //Thief stalls
			{4876, 3449, 2881, 0, 0}, //Thief stalls
			{4877, 3448, 2881, 0, 0}, //Thief stalls
			{4878, 3447, 2881, 0, 0}, //Thief stalls
			{13493, 3446, 2881, 0, 0}, //Thief stalls
			{4875, 3200, 3431, 0, 0}, //theif stalls
			{4874, 3200, 3432, 0, 0}, //theif stalls
			{4876, 3200, 3433, 0, 0}, //theif stalls
			{4877, 3200, 3434, 0, 0}, //theif stalls
			{4878, 3200, 3435, 0, 0}, //theif stalls
			//theif stalls
			
			{10620, 3200, 3434, 0, 0}, //WildyWyrm chest
			
			//DRAPHT ZONE//
			{2213, 3425, 2930, 0, 0}, //Banks
			//END//
			/*
			* Remove Uber Zone Objects
			*/
			{-1, 2425, 4714, 0, 0},
			{-1, 2420, 4716, 0, 0},
			{-1, 2426, 4726, 0, 0},
			{-1, 2420, 4709, 0, 0},
			{-1, 2419, 4698, 0, 0},
			{-1, 2420, 4700, 0, 0},
			{-1, 2399, 4721, 0, 0},
			{-1, 2398, 4721, 0, 0},
			{-1, 2399, 4720, 0, 0},
			{-1, 2395, 4722, 0, 0},
			{-1, 2398, 4717, 0, 0},
			{-1, 2396, 4717, 0, 0},
			{-1, 2396, 4718, 0, 0},
			{-1, 2396, 4719, 0, 0},
			{-1, 2395, 4718, 0, 0},
			{-1, 2394, 4711, 0, 0},
			{-1, 2396, 4711, 0, 0},
			{-1, 2397, 4711, 0, 0},
			{-1, 2397, 4713, 0, 0},
			{-1, 2399, 4713, 0, 0},
			{-1, 2402, 4726, 0, 0},
			{-1, 2407, 4728, 0, 0},
			{-1, 2405, 4724, 0, 0},
			{-1, 2409, 4705, 0, 0},
			{-1, 2410, 4704, 0, 0},
			{-1, 2407, 4702, 0, 0},
			{-1, 2407, 4701, 0, 0},
			{-1, 2408, 4701, 0, 0},
			{-1, 2412, 4701, 0, 0},
			{-1, 2413, 4701, 0, 0},
			{-1, 2414, 4703, 0, 0},
			{-1, 2416, 4714, 0, 0},
			{-1, 2412, 4732, 0, 0},
			{-1, 2413, 4729, 0, 0},
			{-1, 2414, 4733, 0, 0},
			{-1, 2415, 4730, 0, 0},
			{-1, 2416, 4730, 0, 0},
			{-1, 2416, 4731, 0, 0},
			{-1, 2419, 4731, 0, 0},
			{-1, 2420, 4731, 0, 0},
			{-1, 2420, 4732, 0, 0},
			{-1, 2415, 4725, 0, 0},
			{-1, 2417, 4729, 0, 0},
			{-1, 2418, 4727, 0, 0},
			{-1, 2418, 4723, 0, 0},
			{-1, 2419, 4722, 0, 0},
			{-1, 2420, 4726, 0, 0},
			{-1, 2415, 4725, 0, 0},
			{-1, 2417, 4729, 0, 0},
			{-1, 2418, 4727, 0, 0},
			{-1, 2418, 4723, 0, 0},
			{-1, 2419, 4722, 0, 0},
			{-1, 2420, 4726, 0, 0},
			
			//{13405, 3439, 2906, 0, 1}, //House teleport
			
			{4306, 3431, 2872, 0, 2}, //Anvil
			{6189, 3433, 2871, 0, 3}, //Furnace
			
			{10091, 3452, 2871, 0, 0}, //Rocktail fishing 
			{10091, 3449, 2867, 0, 0}, //Rocktail fishing 
			
			{14859, 3439, 2872, 0, 0}, //Rune ore 
			{14859, 3442, 2871, 0, 0}, //Rune ore 
			{14859, 3444, 2870, 0, 0}, //Rune ore 
			{14859, 3445, 2868, 0, 0}, //Rune ore 
			{14859, 3443, 2869, 0, 0}, //Rune ore 
			{14859, 3441, 2869, 0, 0}, //Rune ore 
			{14859, 3439, 2870, 0, 0}, //Rune ore 
			{14859, 3437, 2872, 0, 0}, //Rune ore 
			
			{1306, 3422, 2870, 0, 0}, //Magic tree's 
			{1306, 3422, 2872, 0, 0}, //Magic tree's 
			{1306, 3422, 2874, 0, 0}, //Magic tree's 
			{1306, 3422, 2876, 0, 0}, //Magic tree's 
			{1306, 3422, 2878, 0, 0}, //Magic tree's 
			{1306, 3422, 2880, 0, 0}, //Magic tree's 
			{3422,3680,2986,0,0},
			{1306, 3422, 2882, 0, 0}, //Magic tree's 
			
			{3192, 3434, 2922, 0, 2}, //Scoreboard
			
			{409, 3443, 2918, 0, 2}, //Altar 
			{6552, 3439, 2918, 0, 2}, //Altar 
			{8749, 3445, 2913, 0, 3}, //Altar 
			{411, 3441, 2910, 0, 0}, //Altar 
			{13179, 3439, 2912, 0, 1}, //Altar 

			{2213, 3425, 2930, 0, 0}, //Banks
			{2213, 3426, 2930, 0, 0}, //Banks
			{2213, 3427, 2930, 0, 0}, //Banks
			{2213, 3428, 2930, 0, 0}, //Banks
			
			{2213, 3426, 2894, 0, 1}, //Banks
			{2213, 3426, 2893, 0, 1}, //Banks
			{2213, 3426, 2892, 0, 1}, //Banks
			{2213, 3426, 2891, 0, 1}, //Banks
			{2213, 3426, 2890, 0, 1}, //Banks
			{2213, 3426, 2889, 0, 1}, //Banks
			
			{10503, 3456, 2876, 0, 0}, //rocks to fix random wall
			{10503, 3456, 2877, 0, 0}, //rocks to fix random wall
			{10503, 3456, 2878, 0, 0}, //rocks to fix random wall
			{10503, 3456, 2879, 0, 0}, //rocks to fix random wall		
			{10503, 3446, 2889, 0, 0}, //rocks to fix random wall
			{10503, 3438, 2904, 0, 0}, //rocks to fix random wall
			{10503, 3415, 2917, 0, 0}, //rocks to fix random wall
			{10503, 3411, 2925, 0, 0}, //rocks to fix random wall
			
			/*** Bas Extreme Donator zone end ***/
			
			
			{10091, 2337, 3703, 0, 0}, //fishing rocktail @ fishing location
			
			/*** Bas Gambling area ***/
			{2213, 2729, 3467, 0, 3}, //bank
			{2213, 2729, 3468, 0, 3}, //bank
			{2213, 2729, 3469, 0, 3}, //bank
			{2213, 2729, 3470, 0, 3}, //bank
			{2213, 2729, 3471, 0, 3}, //bank
		
			
			/*** Bas Varrock home ***/

			{172, 3208, 3438, 0, 2}, //crystal key chest
			
			
			{13179, 3226, 3433, 0, 3}, //veng
			{6552, 3226, 3435, 0, 3}, //ancient
			{409, 3205, 3434, 0, 1}, //pray altar
			{411, 3205, 3436, 0, 1}, //turmoil
			{884, 3195, 3436, 0, 1}, //well
			
			{3192, 3220, 3435, 0, 2}, //scoreboard
			
			{2213, 3211, 3438, 0, 2}, //bank
			{2213, 3212, 3438, 0, 2}, //bank
			{2213, 3213, 3438, 0, 2}, //bank
			{2213, 3214, 3438, 0, 2}, //bank
			{1746, 3215, 3438, 0, 0}, //ladder
			
			{-1, 3217, 3436, 0, 0}, //remove stall
			{-1, 3219, 3436, 0, 0}, //remove stall
			{-1, 3220, 3431, 0, 0}, //remove stall
			{-1, 3220, 3425, 0, 0}, //remove stall
			{-1, 3223, 3434, 0, 0}, //remove oak
			{-1, 3226, 3431, 0, 0}, //remove plant
			
			/*** Bas Varrock home end ***/
			
			


			
			/*** Bas UBER Donator zone ***/
			
			{8749, 2307, 9806, 0, 1}, //special attack altar 
			
//			{411, 2307, 9807, 0, 1}, //pray altar
//			{409, 2307, 9805, 0, 1}, //pray switch altar

			{2213, 2317, 9798, 0, 0}, //bank
			{2213, 2316, 9798, 0, 0}, //bank
			{2213, 2315, 9798, 0, 0}, //bank
			{2213, 2314, 9798, 0, 0}, //bank
			{2213, 2313, 9798, 0, 0}, //bank
			{2213, 2312, 9798, 0, 0}, //bank
			{2213, 2311, 9798, 0, 0}, //bank
			{2213, 2310, 9798, 0, 0}, //bank
			{2213, 2309, 9798, 0, 0}, //bank

			{-1, 2325, 9798, 0, 0}, //remove box
			{-1, 2324, 9798, 0, 0}, //remove barrel
			{-1, 2324, 9799, 0, 0}, //remove boxes
			{-1, 2320, 9798, 0, 0}, //remove chair
			{-1, 2319, 9799, 0, 0}, //remove workspace
			{-1, 2319, 9798, 0, 0}, //remove workspace
			{-1, 2318, 9798, 0, 0}, //remove workspace
			{-1, 2309, 9799, 0, 0}, //remove workspace
			{-1, 2321, 9800, 0, 0}, //remove workspace
			{-1, 2309, 9806, 0, 0}, //remove workspace
			{-1, 2318, 9801, 0, 0}, //remove workspace
			{-1, 2327, 9800, 0, 0}, //remove workspace
			{-1, 2327, 9799, 0, 0}, //remove workspace
			{-1, 2327, 9798, 0, 0}, //remove workspace
			{-1, 2326, 9798, 0, 0}, //remove workspace

			{14859, 2330, 9795, 0, 0}, //rune ore's
			{14859, 2329, 9794, 0, 0}, //rune ore's
			{14859, 2328, 9793, 0, 0}, //rune ore's
			{14859, 2327, 9793, 0, 0}, //rune ore's

			{6189, 2324, 9794, 0, 3}, //Smith bars
			{4306, 2331, 9798, 0, 1}, //Anvil
			{4306, 2331, 9800, 0, 1}, //Anvil

			{13493, 2317, 9801, 0, 2}, //thief stall
			{13493, 2309, 9802, 0, 2}, //thief stall

			{8702, 2323, 9799, 0, 0}, //fish spot

			{2732, 2316, 9809, 0, 0}, //fire

			{1306, 2318, 9807, 0, 0}, //magic trees
			{1306, 2320, 9807, 0, 0}, //magic trees
			{1306, 2322, 9807, 0, 0}, //magic trees

			
			/*** Bas UBER Donator zone end ***/
			
			/*** Bas Donator zone ***/
			
			{11933, 3353, 9622, 0, 0},//Tin Ore
			{11933, 3351, 9620, 0, 0},//Tin Ore
			{11936, 3349, 9622, 0, 0},//Copper Ore
			{11936, 3347, 9620, 0, 0},//Copper Ore
			{11954, 3344, 9620, 0, 0},//Iron Ore
			{11954, 3345, 9622, 0, 0},//Iron Ore
			{11954, 3343, 9623, 0, 0},//Iron Ore
			{11963, 3345, 9625, 0, 0},//Coal Ore
			{11963, 3344, 9627, 0, 0},//Coal Ore
			{11963, 3345, 9629, 0, 0},//Coal Ore
			{11963, 3346, 9631, 0, 0},//Coal Ore
			{11951, 3347, 9628, 0, 0},//Gold Ore
			{11951, 3347, 9628, 0, 0},//Gold Ore
			{11951, 3347, 9624, 0, 0},//Gold Ore
			{11947, 3351, 9623, 0, 0},//Mithril Ore
			{11947, 3350, 9626, 0, 0},//Mithril Ore
			{11947, 3349, 9628, 0, 0},//Mithril Ore
			
			{11941, 3373, 9622, 0, 0},//Adamant Ore
			{11941, 3376, 9621, 0, 0},//Adamant Ore
			{11941, 3379, 9622, 0, 0},//Adamant Ore
			{11941, 3383, 9623, 0, 0},//Adamant Ore
			{11941, 3382, 9626, 0, 0},//Adamant Ore
			{11941, 3381, 9629, 0, 0},//Adamant Ore
			{14859, 3378, 9627, 0, 0},//Rune Ore
			{14859, 3375, 9624, 0, 0},//Rune Ore
			
			{1307, 3382, 9651, 0, 0},//Tree's
			{1307, 3381, 9648, 0, 0},//Tree's
			{1307, 3383, 9655, 0, 0},//Tree's
			{1309, 3382, 9657, 0, 0},//Tree's
			{1309, 3378, 9658, 0, 0},//Tree's
			{1309, 3375, 9658, 0, 0},//Tree's
			{1309, 3371, 9657, 0, 0},//Tree's
			
			{1308, 3355, 9657, 0, 0},//Tree's
			{1308, 3351, 9659, 0, 0},//Tree's
			{1281, 3346, 9658, 0, 0},//Tree's
			{1281, 3344, 9656, 0, 0},//Tree's
			{1278, 3344, 9652, 0, 0},//Tree's
			{1278, 3345, 9648, 0, 0},//Tree's
			
			//{28716, 3376, 9632, 0, 1},//Obelisk
			
			//{13405, 3376, 9646, 0, 1},//Portal construction
			
			{4875, 3350, 9648, 0, 1},//Thief
			{4874, 3350, 9647, 0, 1},//Thief
			{4876, 3350, 9646, 0, 1},//Thief
			{4877, 3350, 9645, 0, 1},//Thief
			{4878, 3350, 9644, 0, 1},//Thief
			{13493, 3350, 9643, 0, 3},//Thief Donor
			
			{8702, 3350, 9636, 0, 0},//Fish barrel
			{12269, 3350, 9634, 0, 0},//Cook
			
			{6189, 3350, 9630, 0, 0},//Furnace
			{4306, 3350, 9632, 0, 1},//Anvil
			
			{14859, 3372, 9626, 0, 0},//Rune Ore
			{14859, 3371, 9626, 0, 0},//Rune Ore
			{14859, 3370, 9626, 0, 0},//Rune Ore
			{14859, 3369, 9626, 0, 0},//Rune Ore
			{14859, 3368, 9626, 0, 0},//Rune Ore
			{14859, 3367, 9626, 0, 0},//Rune Ore
			{14859, 3366, 9626, 0, 0},//Rune Ore
			{14859, 3365, 9626, 0, 0},//Rune Ore
			{14859, 3361, 9626, 0, 0},//Rune Ore
			{14859, 3360, 9626, 0, 0},//Rune Ore
			{14859, 3359, 9626, 0, 0},//Rune Ore
			{14859, 3358, 9626, 0, 0},//Rune Ore
			{14859, 3357, 9626, 0, 0},//Rune Ore
			{14859, 3356, 9626, 0, 0},//Rune Ore
			{14859, 3355, 9626, 0, 0},//Rune Ore
			{14859, 3354, 9626, 0, 0},//Rune Ore

			{210, 3361, 9642, 0, 0},//Ice Light
			{210, 3365, 9642, 0, 0},//Ice Light
			{210, 3361, 9638, 0, 0},//Ice Light
			{210, 3365, 9638, 0, 0},//Ice Light

			{586, 3363, 9640, 0, 2},//Statue

			{4483, 3363, 9652, 0, 0},//Bank North
			{4483, 3376, 9640, 0, 1},//Bank East
			{4483, 3363, 9627, 0, 4},//Bank South
			{4483, 3351, 9640, 0, 3},//Bank West

			{1306, 3355, 9652, 0, 0},//Magic trees
			{1306, 3357, 9652, 0, 0},//Magic trees
			{1306, 3359, 9652, 0, 0},//Magic trees

			{1306, 3370, 9652, 0, 0},//Magic trees
			{1306, 3368, 9652, 0, 0},//Magic trees
			{1306, 3366, 9652, 0, 0},//Magic trees
			
			/* REMOVE PORTALS */
			{-1, 3353, 9640, 0, 0}, //Delete Portals
			{-1, 3363, 9629, 0, 0}, //Delete Portals
			{-1, 3374, 9640, 0, 0}, //Delete Portals
			{-1, 3363, 9650, 0, 0}, //Delete Portals
			
			/* NORTH EAST REMOVE PILE'S */
			{-1, 3371, 9658, 0, 0}, //Delete Pile's corners
			{-1, 3375, 9657, 0, 0}, //Delete Pile's corners
			{-1, 3377, 9653, 0, 0}, //Delete Pile's corners
			{-1, 3379, 9655, 0, 0}, //Delete Pile's corners
			{-1, 3381, 9657, 0, 0}, //Delete Pile's corners
			{-1, 3381, 9653, 0, 0}, //Delete Pile's corners
			{-1, 3381, 9650, 0, 0}, //Delete Pile's corners
			
			/* NORTH WEST REMOVE PILE'S */
			{-1, 3346, 9651, 0, 0}, //Delete Pile's corners
			{-1, 3348, 9652, 0, 0}, //Delete Pile's corners
			{-1, 3345, 9654, 0, 0}, //Delete Pile's corners
			{-1, 3348, 9655, 0, 0}, //Delete Pile's corners
			{-1, 3352, 9654, 0, 0}, //Delete Pile's corners
			{-1, 3345, 9657, 0, 0}, //Delete Pile's corners
			{-1, 3350, 9657, 0, 0}, //Delete Pile's corners
			{-1, 3354, 9658, 0, 0}, //Delete Pile's corners
			{-1, 3356, 9657, 0, 0}, //Delete Pile's corners
			
			/* SOUTH EAST REMOVE PILE'S */
			{-1, 3381, 9727, 0, 0}, //Delete Pile's corners
			{-1, 3378, 9625, 0, 0}, //Delete Pile's corners
			{-1, 3376, 9624, 0, 0}, //Delete Pile's corners
			{-1, 3381, 9623, 0, 0}, //Delete Pile's corners
			{-1, 3379, 9621, 0, 0}, //Delete Pile's corners
			{-1, 3374, 9621, 0, 0}, //Delete Pile's corners
			{-1, 3370, 9621, 0, 0}, //Delete Pile's corners
			{-1, 3381, 9627, 0, 0}, //Delete Pile's corners
			
			/* SOUTH WEST REMOVE PILE'S */
			{-1, 3355, 9621, 0, 0}, //Delete Pile's corners
			{-1, 3352, 9622, 0, 0}, //Delete Pile's corners
			{-1, 3350, 9621, 0, 0}, //Delete Pile's corners
			{-1, 3348, 9625, 0, 0}, //Delete Pile's corners
			{-1, 3347, 9620, 0, 0}, //Delete Pile's corners
			{-1, 3345, 9623, 0, 0}, //Delete Pile's corners
			{-1, 3347, 9628, 0, 0}, //Delete Pile's corners
			{-1, 3345, 9628, 0, 0}, //Delete Pile's corners

			/*** Bas Donator zone end ***/
			
			//H'ween even chest
			{2079, 2576, 9876, 0, 0},
			/**Grand EXCHANGE**/
			{8799, 3091, 3495, 0, 3},
			
		//lumby cows gate
		{2344, 3253, 3266, 0, 0},
		{3192, 3084, 3485, 0, 4},
		{-1, 3084, 3487, 0, 2},
		
		
		/* ZULRAH */
		
		{1, 3038, 3415, 0, 0},
		{357, 3034, 3422, 0, 0},
		{ 25136, 2278, 3070, 0, 0 },
		{ 25136, 2278, 3065, 0, 0 },
		{ 25138, 2273, 3066, 0, 0 },
		{ 25136, 2272, 3065, 0, 0 },
		{ 25139, 2267, 3065, 0, 0 },
		{ 25136, 2260, 3081, 0, 0 },
		{401, 3503, 3567, 0, 0},
		{401, 3504, 3567, 0, 0},
		{1309, 2715, 3465, 0, 0},
		{1309, 2709, 3465, 0, 0},
		{1309, 2709, 3458, 0, 0},
		{1306, 2705, 3465, 0, 0},
		{1306, 2705, 3458, 0, 0},
		{-1, 2715, 3466, 0, 0},
		{-1, 2712, 3466, 0, 0},
		{-1, 2713, 3464, 0, 0},
		{-1, 2711, 3467, 0, 0},
		{-1, 2710, 3465, 0, 0},
		{-1, 2709, 3464, 0, 0},
		{-1, 2708, 3466, 0, 0},
		{-1, 2707, 3467, 0, 0},
		{-1, 2704, 3465, 0, 0},
		{-1, 2714, 3466, 0, 0},
		{-1, 2705, 3457, 0, 0},
		{-1, 2709, 3461, 0, 0},
		{-1, 2709, 3459, 0, 0},
		{-1, 2708, 3458, 0, 0},
		{-1, 2710, 3457, 0, 0},
		{-1, 2711, 3461, 0, 0},
		{-1, 2713, 3461, 0, 0},
		{-1, 2712, 3459, 0, 0},
		{-1, 2712, 3457, 0, 0},
		{-1, 2714, 3458, 0, 0},
		{-1, 2705, 3459, 0, 0},
		{-1, 2705, 3464, 0, 0},
		{2274, 2912, 5300, 2, 0},
		{2274, 2914, 5300, 1, 0},
		{2274, 2919, 5276, 1, 0},
		{2274, 2918, 5274, 0, 0},
		{2274, 3001, 3931, 0, 0},
		{-1, 2884, 9797, 0, 2},
		{4483, 2909, 4832, 0, 3},
		{4483, 2901, 5201, 0, 2},
		{4483, 2902, 5201, 0, 2},
		{9326, 3001, 3960, 0, 0},
		{1662, 3112, 9677, 0, 2},
		{1661, 3114, 9677, 0, 2},
		{1661, 3122, 9664, 0, 1},
		{1662, 3123, 9664, 0, 2},
		{1661, 3124, 9664, 0, 3},
		{4483, 2918, 2885, 0, 3},
		{12356, 3081, 3500, 0, 0},
		{2182, 3081, 3497, 0, 0},
		{1746, 3090, 3492, 0, 1},
		{2644, 2737, 3444, 0, 0},
		{-1, 2608, 4777, 0, 0},
		{-1, 2601, 4774, 0, 0},
		{-1, 2611, 4776, 0, 0},
		
		
		
		
		/**Oude ruse Member Zone*/
		
//		{2344, 3421, 2908, 0, 0}, //Rock blocking
//        {2345, 3438, 2909, 0, 0},
//        {2344, 3435, 2909, 0, 0},
//        {2344, 3432, 2909, 0, 0},
//        {2345, 3431, 2909, 0, 0},
//        {2344, 3428, 2921, 0, 1},
//        {2344, 3428, 2918, 0, 1},
//        {2344, 3428, 2915, 0, 1},
//        {2344, 3428, 2912, 0, 1},
//        {2345, 3428, 2911, 0, 1},
//        {2344, 3417, 2913, 0, 1},
//        {2344, 3417, 2916, 0, 1},
//        {2344, 3417, 2919, 0, 1},
//        {2344, 3417, 2922, 0, 1},
//        {2345, 3417, 2912, 0, 0},
//        {2346, 3418, 2925, 0, 0},
//        {10378, 3426, 2907, 0, 0},
//        {8749, 3426, 2923, 0, 2}, //Altar
//        {-1, 3420, 2909, 0, 10}, //Remove crate by mining
//        {-1, 3420, 2923, 0, 10}, //Remove Rockslide by Woodcutting
//        {14859, 3421, 2909, 0, 0}, //Mining
//        {14859, 3419, 2909, 0, 0},
//        {14859, 3418, 2910, 0, 0},
//        {14859, 3418, 2911, 0, 0},
//        {14859, 3422, 2909, 0, 0},
//        {1306, 3418, 2921, 0, 0}, //Woodcutting
//        {1306, 3421, 2924, 0, 0},
//        {1306, 3420, 2924, 0, 0},
//        {1306, 3419, 2923, 0, 0},
//        {1306, 3418, 2922, 0, 0},
//		{-1, 3430, 2912, 0, 2}, 
//		{13493, 3424, 2916, 0, 1},//Armour  stall
		
        /**Oude ruse Member Zone end*/
		
		
		{-1, 3098, 3496, 0, 1},
		{-1, 3095, 3498, 0, 1},
		{-1, 3088, 3509, 0, 1},
		{-1, 3095, 3499, 0, 1},
		{-1, 3085, 3506, 0, 1},
		{30205, 3085, 3509, 0, 3},
		{-1, 3206, 3263, 0, 0},
		{-1, 2794, 2773, 0, 0},
		{2, 2692, 3712, 0, 3},
		{2, 2688, 3712, 0, 1},
		{4483, 3081, 3496, 0, 1},
		{4483, 3081, 3495, 0, 1},
		{4875, 3094, 3500, 0, 0},
		{4874, 3095, 3500, 0, 0},
		{4876, 3096, 3500, 0, 0},
		{4877, 3097, 3500, 0, 0},
		{4878, 3098, 3500, 0, 0},
		{ 11758, 3019, 9740, 0, 0},
		{ 11758, 3020, 9739, 0, 1},
		{ 11758, 3019, 9738, 0, 2},
		{ 11758, 3018, 9739, 0, 3},
		{ 11933, 3028, 9739, 0, 1},
		{ 11933, 3032, 9737, 0, 2},
		{ 11933, 3032, 9735, 0, 0},
		{ 11933, 3035, 9742, 0, 3},
		{ 11933, 3034, 9739, 0, 0},
		{ 11936, 3028, 9737, 0, 1},
		{ 11936, 3029, 9734, 0, 2},
		{ 11936, 3031, 9739, 0, 0},
		{ 11936, 3032, 9741, 0, 3},
		{ 11936, 3035, 9734, 0, 0},
		{ 11954, 3037, 9739, 0, 1},
		{ 11954, 3037, 9735, 0, 2},
		{ 11954, 3037, 9733, 0, 0},
		{ 11954, 3039, 9741, 0, 3},
		{ 11954, 3039, 9738, 0, 0},
		{ 11963, 3039, 9733, 0, 1},
		{ 11964, 3040, 9732, 0, 2},
		{ 11965, 3042, 9734, 0, 0},
		{ 11965, 3044, 9737, 0, 3},
		{ 11963, 3042, 9739, 0, 0},
		{ 11963, 3045, 9740, 0, 1},
		{ 11965, 3043, 9742, 0, 2},
		{ 11964, 3045, 9744, 0, 0},
		{ 11965, 3048, 9747, 0, 3},
		{ 11951, 3048, 9743, 0, 0},
		{ 11951, 3049, 9740, 0, 1},
		{ 11951, 3047, 9737, 0, 2},
		{ 11951, 3050, 9738, 0, 0},
		{ 11951, 3052, 9739, 0, 3},
		{ 11951, 3051, 9735, 0, 0},
		{ 11947, 3049, 9735, 0, 1},
		{ 11947, 3049, 9734, 0, 2},
		{ 11947, 3047, 9733, 0, 0},
		{ 11947, 3046, 9733, 0, 3},
		{ 11947, 3046, 9735, 0, 0},
		{ 11941, 3053, 9737, 0, 1},
		{ 11939, 3054, 9739, 0, 2},
		{ 11941, 3053, 9742, 0, 0},
		{ 14859, 3038, 9748, 0, 3},
		{ 14859, 3044, 9753, 0, 0},
		{ 14859, 3048, 9754, 0, 1},
		{ 14859, 3054, 9746, 0, 2},
		{ 4306, 3026, 9741, 0, 0},
		{ 6189, 3022, 9742, 0, 1},
		{ 75 , 2914, 3452, 0, 2},
		{ 10091 , 2352, 3703, 0, 2},
		{ 11758, 3449, 3722, 0, 0},
		{ 11758, 3450, 3722, 0, 0},
		{ 50547, 3445, 3717, 0, 3},
		{2465, 3085, 3512, 0, 0},
		{ -1, 3090, 3496, 0, 0},
		{ -1, 3090, 3494, 0, 0},
		{ -1, 3092, 3496, 0, 0},
		
		{ -1, 3659, 3508, 0, 0},
		{ 4053, 3660, 3508, 0, 0},
		{ 4051, 3659, 3508, 0, 0},
		{ 1, 3649, 3506, 0, 0},
		{ 1, 3650, 3506, 0, 0},
		{ 1, 3651, 3506, 0, 0},
		{ 1, 3652, 3506, 0, 0},
		{ -1, 2860, 9734, 0, 1},
		{ -1, 2857, 9736, 0, 1},
		{ 664, 2859, 9742, 0, 1},
		{ 1167, 2860, 9742, 0, 1},
		{ 5277, 3691, 3465, 0, 2},
		{ 5277, 3690, 3465, 0, 2},
		{ 5277, 3688, 3465, 0, 2},
		{ 5277, 3687, 3465, 0, 2},
		{ 114, 3093, 3933, 0, 0},
		
	};
	
	
	/**
	 * Contains
	 * @param ObjectId - The object ID to spawn
	 * @param absX - The X position of the object to spawn
	 * @param absY - The Y position of the object to spawn
	 * @param Z - The Z position of the object to spawn
	 * @param face - The position the object will face
	 */
	
	//Objects that are handled by the server on regionchange
	private static final int[][] CUSTOM_OBJECTS_SPAWNS = {
			
	/*** Kourend home area oof ***/
			
			{-1, 1615, 3662, 0, 0}, //delete
			{-1, 1611, 3671, 0, 0}, //delete
			{-1, 1611, 3668, 0, 0}, //delete
			{-1, 1611, 3675, 0, 0}, //delete
			{-1, 1611, 3679, 0, 0}, //delete
			{-1, 1648, 3683, 0, 0}, //delete
			
			{-1, 1633, 3685, 0, 0}, //delete
			{-1, 1635, 3685, 0, 0}, //delete
			{-1, 1639, 3685, 0, 0}, //delete
			{-1, 1640, 3685, 0, 0}, //delete
			{-1, 1637, 3684, 0, 0}, //delete
			{-1, 1637, 3683, 0, 0}, //delete
			{-1, 1634, 3683, 0, 0}, //delete
			{-1, 1638, 3683, 0, 0}, //delete
			{-1, 1645, 3669, 0, 0}, //delete
			{-1, 1626, 3681, 0, 0}, //delete
			
			{2213, 1635, 3670, 0, 2}, //bank
			{2213, 1636, 3670, 0, 2}, //bank
			{2213, 1637, 3670, 0, 2}, //bank
			
			{2213, 1635, 3676, 0, 2}, //bank
			{2213, 1636, 3676, 0, 2}, //bank
			{2213, 1637, 3676, 0, 2}, //bank
			
			{2213, 1639, 3672, 0, 1}, //bank
			{2213, 1639, 3673, 0, 1}, //bank


			{2213, 2524, 2499, 0, 0}, //bank
			{2213, 2523, 2499, 0, 0}, //bank
			{2213, 2522, 2499, 0, 0}, //bank
			{2213, 2521, 2499, 0, 0}, //bank
			{2213, 2520, 2499, 0, 0}, //bank
			{2213, 2519, 2499, 0, 0}, //bank
			{2213, 2518, 2499, 0, 0}, //bank
			{2213, 2517, 2499, 0, 0}, //bank
			{2213, 2516, 2499, 0, 0}, //bank
			{2213, 2515, 2499, 0, 0}, //bank
			{2213, 2514, 2499, 0, 0}, //bank
			{2213, 2513, 2499, 0, 0}, //bank
			{2213, 2512, 2499, 0, 0}, //bank
			{2213, 2511, 2499, 0, 0}, //bank
			{2213, 2510, 2499, 0, 0}, //bank
			{2213, 2509, 2499, 0, 0}, //bank
			{2213, 2508, 2499, 0, 0}, //bank
			{2213, 2507, 2499, 0, 0}, //bank
			{2213, 2506, 2499, 0, 0}, //bank
			{2213, 2505, 2499, 0, 0}, //bank
			{2213, 2504, 2499, 0, 0}, //bank


			{2213, 1639, 3674, 0, 1}, //bank
			
			{2213, 1633, 3672, 0, 1}, //bank
			{2213, 1633, 3673, 0, 1}, //bank
			{2213, 1633, 3674, 0, 1}, //bank
			
			{2213, 1608, 3667, 0, 1}, //bank
			{2213, 1608, 3668, 0, 1}, //bank
			{2213, 1608, 3669, 0, 1}, //bank
			{2213, 1608, 3670, 0, 1}, //bank
			
			{2213, 1608, 3676, 0, 1}, //bank
			{2213, 1608, 3677, 0, 1}, //bank
			{2213, 1608, 3678, 0, 1}, //bank
			{2213, 1608, 3679, 0, 1}, //bank
			
			
			{818, 1608, 3674, 0, 3}, //blockade of armour 
			{818, 1608, 3673, 0, 3}, //blockade of armour 
			{818, 1608, 3672, 0, 3}, //blockade of armour 
			
			{409, 1611, 3668, 0, 1}, //altar
			{411, 3832, 3501, 0, 1}, //altar
			{6552, 1611, 3675, 0, 1}, //altar
			{13179, 1611, 3678, 0, 1}, //altar
			
			{4875, 1646, 3683, 0, 1}, //thieving
			{4874, 1647, 3683, 0, 1}, //thieving
			{4876, 1648, 3683, 0, 1}, //thieving
			{4877, 1649, 3683, 0, 1}, //thieving
			{4878, 1650, 3683, 0, 1}, //thieving
			
			{4875, 3557, 3127, 0, 1}, //thieving
			{4874, 3558, 3127, 0, 1}, //thieving
			{4876, 3559, 3127, 0, 1}, //thieving
			{4877, 3560, 3127, 0, 1}, //thieving
			{4878, 3561, 3127, 0, 1}, //thieving
			
			{172, 1625, 3680, 0, 1}, //chest
			{10620, 1625, 3681, 0, 1}, //chest
			
			{2213, 3333, 3333, 0, 1}, //bank test 3333 3333
			{2213, 2270, 5351, 0, 1}, //bank test 3333 3333
			
			{2079, 2576, 9876, 0, 0},		
			{10620, 3200, 3434, 0, 3}, //WildyWyrm chest
			/*
			 * Wilderness resource area
			 */
			{-1, 3181, 3935, 0, 0},
			{-1, 3174, 3933, 0, 0},
			{-1, 3194, 3929, 0, 0},
			{-1, 3193, 3938, 0, 0},
			
			{-1, 3175, 3927, 0, 0},
			{-1, 3177, 3927, 0, 0},
			{-1, 3176, 3926, 0, 0},
			{-1, 3175, 3925, 0, 0},
			{-1, 3177, 3925, 0, 0},
			
			{-1, 3190, 3938, 0, 0},
			
			{-1, 3192, 3927, 0, 0},
			{-1, 3194, 3927, 0, 0},
			{-1, 3193, 3926, 0, 0},
			{-1, 3192, 3925, 0, 0},
			{-1, 3194, 3925, 0, 0},
			
			{-1, 3193, 3933, 0, 0},
			{-1, 3193, 3935, 0, 0},
			{-1, 3194, 3934, 0, 0},
			{-1, 3195, 3933, 0, 0},
			{-1, 3195, 3935, 0, 0},
			
			{-1, 3192, 3941, 0, 0},
			{-1, 3190, 3941, 0, 0},
			{-1, 3191, 3942, 0, 0},
			{-1, 3192, 3943, 0, 0},
			{-1, 3199, 3943, 0, 0},
			
			{-1, 3184, 3941, 0, 0},
			{-1, 3183, 3940, 0, 0},
			{-1, 3182, 3941, 0, 0},
			
			{-1, 3177, 3941, 0, 0},
			{-1, 3177, 3939, 0, 0},
			{-1, 3176, 3940, 0, 0},
			{-1, 3175, 3941, 0, 0},
			{-1, 3175, 3939, 0, 0},
			
			//^^^^^^^^^^^^
			/*
			 * Object deletions
			 */
			
			{1306, 3181, 3935, 0, 0}, //Magic tree
			{1306, 3193, 3938, 0, 0},
			
			{1309, 3174, 3933, 0, 0}, //Yew tree
			{1309, 3194, 3929, 0, 0},
			
			{4306, 3190, 3938, 0, 0}, //anvil
			
			{2732, 3186, 3934, 0, 0}, //Fire
			
			{14859, 3175, 3927, 0, 0}, //rune ores
			{14859, 3177, 3927, 0, 0},
			{14859, 3176, 3926, 0, 0},
			{14859, 3175, 3925, 0, 0},
			{14859, 3177, 3925, 0, 0}, //rune ores
			
			{11939, 3192, 3927, 0, 0}, //addy ores
			{11941, 3194, 3927, 0, 0},
			{11941, 3193, 3926, 0, 0},
			{11939, 3192, 3925, 0, 0},
			{11939, 3194, 3925, 0, 0},
			
			{11947, 3193, 3933, 0, 0}, //mith ores
			{11947, 3193, 3935, 0, 0},
			{11947, 3194, 3934, 0, 0},
			{11963, 3195, 3933, 0, 0}, //coal ores
			{11963, 3195, 3935, 0, 0},
			
			{11963, 3192, 3941, 0, 0}, //coal ores
			{11963, 3190, 3941, 0, 0},
			{11963, 3191, 3942, 0, 0},
			{11963, 3192, 3943, 0, 0},
			{11963, 3190, 3943, 0, 0},
			
			{4876, 3184, 3941, 0, 0}, //thieving stalls
			{4877, 3183, 3940, 0, 0}, //thieving stalls
			{4878, 3182, 3941, 0, 0}, //thieving stalls
			
			{10091, 3182, 3926, 0, 0}, //fishing spot
			{10091, 3186, 3926, 0, 0}, //fishing spot
			
			{9390, 3189, 3929, 0, 0}, //Forge
			
			{4926, 3174, 3930, 0, 0}, //crystals
			{4927, 3174, 3931, 0, 0}, //crystals
			{4928, 3176, 3931, 0, 0}, //crystals
			
			/*
			 * End of wilderness resource area
			 */
			
			/*
			 * Xmas objects at home
			 */
			{47748, 3223, 3439, 0, 3}, //Xmas tree
			/*
			 * End of Xmas Objects at home
			 */
			
			/**
			 * ZULRAH
			 */
			{ 25136, 2278, 3070, 0, 0 },
			{ 25136, 2278, 3065, 0, 0 },
			{ 25138, 2273, 3066, 0, 0 },
			{ 25136, 2272, 3065, 0, 0 },
			{ 25139, 2267, 3065, 0, 0 },
			{ 25136, 2260, 3081, 0, 0 },
			{1, 3038, 3415, 0, 0},
			{357, 3034, 3422, 0, 0},
			{ -1, 2265, 3071, 0, 0 },
			{ -1, 2271, 3071, 0, 0 },
			
	
			
			{-1, 3084, 3487, 0, 2},
			
			{ 2274, 3652, 3488, 0, 0 },
			{ 2274, 3039, 9555, 0, 0 },
			{ 2274, 3039, 9554, 0, 0 },
			
			
			
			
		
			/*
			* Remove Uber Zone Objects
			*/
			{-1, 2425, 4714, 0, 0},
			{-1, 2420, 4716, 0, 0},
			{-1, 2426, 4726, 0, 0},
			{-1, 2420, 4709, 0, 0},
			{-1, 2419, 4698, 0, 0},
			{-1, 2420, 4700, 0, 0},
			{-1, 2399, 4721, 0, 0},
			{-1, 2398, 4721, 0, 0},
			{-1, 2399, 4720, 0, 0},
			{-1, 2395, 4722, 0, 0},
			{-1, 2398, 4717, 0, 0},
			{-1, 2396, 4717, 0, 0},
			{-1, 2396, 4718, 0, 0},
			{-1, 2396, 4719, 0, 0},
			{-1, 2395, 4718, 0, 0},
			{-1, 2394, 4711, 0, 0},
			{-1, 2396, 4711, 0, 0},
			{-1, 2397, 4711, 0, 0},
			{-1, 2397, 4713, 0, 0},
			{-1, 2399, 4713, 0, 0},
			{-1, 2402, 4726, 0, 0},
			{-1, 2407, 4728, 0, 0},
			{-1, 2405, 4724, 0, 0},
			{-1, 2409, 4705, 0, 0},
			{-1, 2410, 4704, 0, 0},
			{-1, 2407, 4702, 0, 0},
			{-1, 2407, 4701, 0, 0},
			{-1, 2408, 4701, 0, 0},
			{-1, 2412, 4701, 0, 0},
			{-1, 2413, 4701, 0, 0},
			{-1, 2414, 4703, 0, 0},
			{-1, 2416, 4714, 0, 0},
			{-1, 2412, 4732, 0, 0},
			{-1, 2413, 4729, 0, 0},
			{-1, 2414, 4733, 0, 0},
			{-1, 2415, 4730, 0, 0},
			{-1, 2416, 4730, 0, 0},
			{-1, 2416, 4731, 0, 0},
			{-1, 2419, 4731, 0, 0},
			{-1, 2420, 4731, 0, 0},
			{-1, 2420, 4732, 0, 0},
			{-1, 2415, 4725, 0, 0},
			{-1, 2417, 4729, 0, 0},
			{-1, 2418, 4727, 0, 0},
			{-1, 2418, 4723, 0, 0},
			{-1, 2419, 4722, 0, 0},
			{-1, 2420, 4726, 0, 0},
			{-1, 2415, 4725, 0, 0},
			{-1, 2417, 4729, 0, 0},
			{-1, 2418, 4727, 0, 0},
			{-1, 2418, 4723, 0, 0},
			{-1, 2419, 4722, 0, 0},
			{-1, 2420, 4726, 0, 0},
			
			
		//lumby cows gate
		{2344, 3253, 3266, 0, 1},
		
		//gamble zone
		{2213, 2842, 5143, 0, 0},
		{2213, 2843, 5143, 0, 0},
		{2213, 2844, 5143, 0, 0},
		{2213, 2845, 5143, 0, 0},
		{2213, 2846, 5143, 0, 0},
		{2213, 2847, 5143, 0, 0},
		{2213, 2848, 5143, 0, 0},
		{2213, 2849, 5143, 0, 0},
		{2213, 2850, 5143, 0, 0},
		{2213, 2851, 5143, 0, 0},
		
		
		{2274, 3652, 3488, 0, 0},
		/**Jail Start*/
		{ 12269, 3093, 3933, 0, 0},
		{ 1864, 3093, 3932, 0, 1},//Cell 1
		{ 1864, 3094, 3932, 0, 1},
		{ 1864, 3095, 3932, 0, 1},
		{ 1864, 3096, 3932, 0, 1},
		{ 1864, 3097, 3932, 0, 1},
		{ 1864, 3097, 3931, 0, 2},
		{ 1864, 3097, 3930, 0, 2},
		{ 1864, 3097, 3929, 0, 2},
		{ 1864, 3097, 3928, 0, 3},
		{ 1864, 3096, 3928, 0, 3},
		{ 1864, 3095, 3928, 0, 3},
		{ 1864, 3094, 3928, 0, 3},
		{ 1864, 3093, 3928, 0, 3},
		{ 1864, 3093, 3929, 0, 4},
		{ 1864, 3093, 3930, 0, 4},
		{ 1864, 3093, 3931, 0, 4},
		{ 1864, 3098, 3932, 0, 1},//Cell 2
		{ 1864, 3099, 3932, 0, 1},
		{ 1864, 3100, 3932, 0, 1},
		{ 1864, 3101, 3932, 0, 1},
		{ 1864, 3102, 3932, 0, 1},
		{ 1864, 3102, 3931, 0, 2},
		{ 1864, 3102, 3930, 0, 2},
		{ 1864, 3102, 3929, 0, 2},
		{ 1864, 3102, 3928, 0, 3},
		{ 1864, 3101, 3928, 0, 3},
		{ 1864, 3100, 3928, 0, 3},
		{ 1864, 3099, 3928, 0, 3},
		{ 1864, 3098, 3928, 0, 3},
		{ 1864, 3098, 3929, 0, 4},
		{ 1864, 3098, 3930, 0, 4},
		{ 1864, 3098, 3931, 0, 4},
		{ 1864, 3093, 3939, 0, 1},//Cell 3
		{ 1864, 3094, 3939, 0, 1},
		{ 1864, 3095, 3939, 0, 1},
		{ 1864, 3096, 3939, 0, 1},
		{ 1864, 3097, 3939, 0, 1},
		{ 1864, 3097, 3938, 0, 2},
		{ 1864, 3097, 3937, 0, 2},
		{ 1864, 3097, 3936, 0, 2},
		{ 1864, 3097, 3935, 0, 3},
		{ 1864, 3096, 3935, 0, 3},
		{ 1864, 3095, 3935, 0, 3},
		{ 1864, 3094, 3935, 0, 3},
		{ 1864, 3093, 3935, 0, 3},
		{ 1864, 3093, 3936, 0, 4},
		{ 1864, 3093, 3937, 0, 4},
		{ 1864, 3093, 3938, 0, 4},
		{ 1864, 3098, 3939, 0, 1},//Cell 4
		{ 1864, 3099, 3939, 0, 1},
		{ 1864, 3100, 3939, 0, 1},
		{ 1864, 3101, 3939, 0, 1},
		{ 1864, 3102, 3939, 0, 1},
		{ 1864, 3102, 3938, 0, 2},
		{ 1864, 3102, 3937, 0, 2},
		{ 1864, 3102, 3936, 0, 2},
		{ 1864, 3102, 3935, 0, 3},
		{ 1864, 3101, 3935, 0, 3},
		{ 1864, 3100, 3935, 0, 3},
		{ 1864, 3099, 3935, 0, 3},
		{ 1864, 3098, 3935, 0, 3},
		{ 1864, 3098, 3936, 0, 4},
		{ 1864, 3098, 3937, 0, 4},
		{ 1864, 3098, 3938, 0, 4},
		{ 1864, 3103, 3932, 0, 1},//Cell 5
		{ 1864, 3104, 3932, 0, 1},
		{ 1864, 3105, 3932, 0, 1},
		{ 1864, 3106, 3932, 0, 1},
		{ 1864, 3107, 3932, 0, 1},
		{ 1864, 3107, 3931, 0, 2},
		{ 1864, 3107, 3930, 0, 2},
		{ 1864, 3107, 3929, 0, 2},
		{ 1864, 3107, 3928, 0, 3},
		{ 1864, 3106, 3928, 0, 3},
		{ 1864, 3105, 3928, 0, 3},
		{ 1864, 3104, 3928, 0, 3},
		{ 1864, 3103, 3928, 0, 3},
		{ 1864, 3103, 3929, 0, 4},
		{ 1864, 3103, 3930, 0, 4},
		{ 1864, 3103, 3931, 0, 4},
		{ 1864, 3108, 3932, 0, 1},//Cell 6
		{ 1864, 3109, 3932, 0, 1},
		{ 1864, 3110, 3932, 0, 1},
		{ 1864, 3111, 3932, 0, 1},
		{ 1864, 3112, 3932, 0, 1},
		{ 1864, 3112, 3931, 0, 2},
		{ 1864, 3112, 3930, 0, 2},
		{ 1864, 3112, 3929, 0, 2},
		{ 1864, 3112, 3928, 0, 3},
		{ 1864, 3111, 3928, 0, 3},
		{ 1864, 3110, 3928, 0, 3},
		{ 1864, 3109, 3928, 0, 3},
		{ 1864, 3108, 3928, 0, 3},
		{ 1864, 3108, 3929, 0, 4},
		{ 1864, 3108, 3930, 0, 4},
		{ 1864, 3108, 3931, 0, 4},
		{ 1864, 3103, 3939, 0, 1},//Cell 7
		{ 1864, 3104, 3939, 0, 1},
		{ 1864, 3105, 3939, 0, 1},
		{ 1864, 3106, 3939, 0, 1},
		{ 1864, 3107, 3939, 0, 1},
		{ 1864, 3107, 3938, 0, 2},
		{ 1864, 3107, 3937, 0, 2},
		{ 1864, 3107, 3936, 0, 2},
		{ 1864, 3107, 3935, 0, 3},
		{ 1864, 3106, 3935, 0, 3},
		{ 1864, 3105, 3935, 0, 3},
		{ 1864, 3104, 3935, 0, 3},
		{ 1864, 3103, 3935, 0, 3},
		{ 1864, 3103, 3936, 0, 4},
		{ 1864, 3103, 3937, 0, 4},
		{ 1864, 3103, 3938, 0, 4},
		{ 1864, 3108, 3939, 0, 1},//Cell 8
		{ 1864, 3109, 3939, 0, 1},
		{ 1864, 3110, 3939, 0, 1},
		{ 1864, 3111, 3939, 0, 1},
		{ 1864, 3112, 3939, 0, 1},
		{ 1864, 3112, 3938, 0, 2},
		{ 1864, 3112, 3937, 0, 2},
		{ 1864, 3112, 3936, 0, 2},
		{ 1864, 3112, 3935, 0, 3},
		{ 1864, 3111, 3935, 0, 3},
		{ 1864, 3110, 3935, 0, 3},
		{ 1864, 3109, 3935, 0, 3},
		{ 1864, 3108, 3935, 0, 3},
		{ 1864, 3108, 3936, 0, 4},
		{ 1864, 3108, 3937, 0, 4},
		{ 1864, 3108, 3938, 0, 4},
		{ 1864, 3113, 3932, 0, 1},//Cell 9
		{ 1864, 3114, 3932, 0, 1},
		{ 1864, 3115, 3932, 0, 1},
		{ 1864, 3116, 3932, 0, 1},
		{ 1864, 3117, 3932, 0, 1},
		{ 1864, 3117, 3931, 0, 2},
		{ 1864, 3117, 3930, 0, 2},
		{ 1864, 3117, 3929, 0, 2},
		{ 1864, 3117, 3928, 0, 3},
		{ 1864, 3116, 3928, 0, 3},
		{ 1864, 3115, 3928, 0, 3},
		{ 1864, 3114, 3928, 0, 3},
		{ 1864, 3113, 3928, 0, 3},
		{ 1864, 3113, 3929, 0, 4},
		{ 1864, 3113, 3930, 0, 4},
		{ 1864, 3113, 3931, 0, 4},
		{ 1864, 3113, 3939, 0, 1},//Cell 10
		{ 1864, 3114, 3939, 0, 1},
		{ 1864, 3115, 3939, 0, 1},
		{ 1864, 3116, 3939, 0, 1},
		{ 1864, 3117, 3939, 0, 1},
		{ 1864, 3117, 3938, 0, 2},
		{ 1864, 3117, 3937, 0, 2},
		{ 1864, 3117, 3936, 0, 2},
		{ 1864, 3117, 3935, 0, 3},
		{ 1864, 3116, 3935, 0, 3},
		{ 1864, 3115, 3935, 0, 3},
		{ 1864, 3114, 3935, 0, 3},
		{ 1864, 3113, 3935, 0, 3},
		{ 1864, 3113, 3936, 0, 4},
		{ 1864, 3113, 3937, 0, 4},
		{ 1864, 3113, 3938, 0, 4},
		
		{2213, 3544, 3134, 0, 2}, //bank
		{2213, 3545, 3134, 0, 2}, //bank
		{2213, 3546, 3134, 0, 2}, //bank
		{2213, 3555, 3134, 0, 2}, //bank
		{2213, 3547, 3134, 0, 2}, //bank
		{2213, 3548, 3134, 0, 2}, //bank
		{2213, 3549, 3134, 0, 2}, //bank
		{2213, 3550, 3134, 0, 2}, //bank
		{2213, 3551, 3134, 0, 2}, //bank
		{2213, 3552, 3134, 0, 2}, //bank
		{2213, 3553, 3134, 0, 2}, //bank
		{2213, 3554, 3134, 0, 2}, //bank
		{2213, 3355, 3134, 0, 2}, //bank
		{2213, 3556, 3134, 0, 2}, //bank
		{2213, 3557, 3134, 0, 2}, //bank
		{2213, 3558, 3134, 0, 2}, //bank
		{2213, 3559, 3134, 0, 2}, //bank
		{2213, 3560, 3134, 0, 2}, //bank
		{2213, 3561, 3134, 0, 2}, //bank
		{2213, 3562, 3134, 0, 2}, //bank
	};
	public static boolean cloudExists(Location loc) {
		return getCloudObject(loc);
	}

	
	
	public static boolean getCloudObject(Location loc) {
		for (GameObject objects : CUSTOM_OBJECTS) {
			System.out.println(loc);
			if (objects.inLocation(objects.getPosition().getX(), objects.getPosition().getY(), Locations.Location.ZULRAH_CLOUD_FIVE)) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	
	public static void zulrahToxicClouds(final GameObject cloud, final Player player, final int cycles) {
		player.setInteractingObject(cloud);
		spawnGlobalObject(cloud);
		TaskManager.submit(new Task(cycles) {
			@Override
			public void execute() {
				deleteGlobalObject(cloud);
				player.setCloudsSpawned(false);
				if (player.getInteractingObject() != null
						&& player.getInteractingObject().getId() == 11700) {
					player.setInteractingObject(null);
				}
				this.stop();
			}

			@Override
			public void stop() {
				setEventRunning(false);
			}
		});

	}
}
