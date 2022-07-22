package com.arlania;

/**
 * Created by Stan van der Bend on 23/12/2017.
 * project: immortal client
 * package: com.arlania
 */
public class ItemDef_Jarrod_1 {
	public static void defaultConfig(ItemDef itemDef, int groundModel, int maleModel, String name) {
		defaultConfig(itemDef, groundModel, maleModel, maleModel, name);
	}

	public static void defaultConfig(ItemDef itemDef, int groundModel, int maleModel, int femaleModel, String name) {
		itemDef.modelID = groundModel;
		itemDef.maleWearId = maleModel;
		itemDef.femaleEquip2 = femaleModel;
		itemDef.name = name;
		itemDef.description = ("It's an " + itemDef.name);


		itemDef.modelZoom = 1500;
		itemDef.modelOffset1 = 0;
		itemDef.modelRotationY = 900;
		itemDef.modelRotationX = 1200;
		itemDef.groundActions = new String[5];
		itemDef.groundActions[2] = "Take";

		itemDef.stackable = false;
	}
    public static ItemDef forDef(ItemDef itemDef, int ID) {

        ItemDef itemDef2;
		switch (ID){

		case 20427:
			itemDef.modelID = 68;
			itemDef.name = "Sesmic Staff";
			itemDef.description = "Sesmicy staff";
			itemDef.modelZoom = 1874;
			itemDef.modelOffset1 = -3;
			itemDef.modelRotationX = 1499;
			itemDef.modelRotationY = 292;
			itemDef.maleWearId = 50962;
			itemDef.femaleWearId = 50962;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.stackable = false;
			break;
			
		case 20260: 
			defaultConfig(itemDef, 50959, 50960, 50960, "Sesmic scythe");
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.actions = new String[5];
			itemDef.actions[1] = "Wear";
			itemDef.actions[4] = "Drop";
			itemDef.description = "WOW! Its on fire!";
			itemDef.modelOffset1 = 0;
			
			break;



            case 21011:
                itemDef.modelID = 91318;
                itemDef.name = "Bomby gloves";
                itemDef.description = "I can almost feel the Dark lord's soul within";
                itemDef.modelZoom = 648;
                itemDef.modelRotationY = 618;
                itemDef.modelRotationX = 1143;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -5;
                itemDef.maleWearId = 91319;
                itemDef.colourRedefine3 = 150000;

                itemDef.femaleWearId = 91319;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;

            case 21012:
                itemDef.modelID = 91317;
                itemDef.name = "Bomby boots";
                itemDef.description = "I can almost feel the Dark lord's soul within";
                itemDef.modelZoom = 825;
                itemDef.modelOffset1 = -4;
                itemDef.modelOffsetY = 0;
                itemDef.modelRotationY = 400;
                itemDef.maleWearId = 91317;
                itemDef.colourRedefine3 = 150000;
                itemDef.femaleWearId = 91317;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.stackable = false;
                break;

            case 21056:
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.name = "Elite void melee helm";
                ItemDef melee = ItemDef.forID(11665);
                itemDef.modelID = melee.modelID;
                itemDef.maleWearId = melee.maleWearId;
                itemDef.femaleWearId = melee.femaleWearId;
                itemDef.modelRotationX = melee.modelRotationX;
                itemDef.modelOffsetX = melee.modelOffsetX;
                itemDef.modelOffset1 = melee.modelOffset1;
                itemDef.modelOffsetY = melee.modelOffsetY;
                itemDef.modelRotationY = melee.modelRotationY;
                itemDef.modelZoom = melee.modelZoom;
                break;
            case 21057:
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.name = "Elite void ranger helm";
                 melee = ItemDef.forID(11664);
                itemDef.modelID = melee.modelID;
                itemDef.maleWearId = melee.maleWearId;
                itemDef.maleEquip2 = melee.maleEquip2;
                itemDef.maleEquip3 = melee.maleEquip3;
                itemDef.femaleWearId = melee.femaleWearId;
                itemDef.femaleEquip2 = melee.femaleEquip2;
                itemDef.femaleEquip3 = melee.femaleEquip3;
                itemDef.modelRotationX = melee.modelRotationX;
                itemDef.modelOffsetX = melee.modelOffsetX;
                itemDef.modelOffset1 = melee.modelOffset1;
                itemDef.modelOffsetY = melee.modelOffsetY;
                itemDef.modelRotationY = melee.modelRotationY;
                itemDef.modelZoom = melee.modelZoom;
                break;
            case 21058:
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.name = "Elite void mage helm";
                 melee = ItemDef.forID(11663);
                itemDef.modelID = melee.modelID;
                itemDef.maleWearId = melee.maleWearId;
                itemDef.maleEquip2 = melee.maleEquip2;
                itemDef.maleEquip3 = melee.maleEquip3;
                itemDef.femaleWearId = melee.femaleWearId;
                itemDef.femaleEquip2 = melee.femaleEquip2;
                itemDef.femaleEquip3 = melee.femaleEquip3;
                itemDef.modelRotationX = melee.modelRotationX;
                itemDef.modelOffsetX = melee.modelOffsetX;
                itemDef.modelOffset1 = melee.modelOffset1;
                itemDef.modelOffsetY = melee.modelOffsetY;
                itemDef.modelRotationY = melee.modelRotationY;
                itemDef.modelZoom = melee.modelZoom;
                break;
            case 21059:
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.name = "Elite void gloves";
                 melee = ItemDef.forID(8842);
                itemDef.modelID = melee.modelID;
                itemDef.maleWearId = melee.maleWearId;
                itemDef.femaleWearId = melee.femaleWearId;
                itemDef.modelRotationX = melee.modelRotationX;
                itemDef.modelOffsetX = melee.modelOffsetX;
                itemDef.modelOffset1 = melee.modelOffset1;
                itemDef.modelOffsetY = melee.modelOffsetY;
                itemDef.modelRotationY = melee.modelRotationY;
                itemDef.modelZoom = melee.modelZoom;
                break;

            case 21020:
                itemDef.modelID = 91318;
                itemDef.name = "Dark Invisus gloves";
                itemDef.description = "I can almost feel the Dark lord's soul within";
                itemDef.modelZoom = 648;
                itemDef.modelRotationY = 618;
                itemDef.modelRotationX = 1143;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -5;
                itemDef.maleWearId = 90138;
                itemDef.femaleWearId = 90138;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;

            case 21021:
                itemDef.modelID = 91317;
                itemDef.name = "Dark Invisus boots";
                itemDef.description = "I can almost feel the Dark lord's soul within";
                itemDef.modelZoom = 825;
                itemDef.modelOffset1 = -4;
                itemDef.modelOffsetY = 0;
                itemDef.modelRotationY = 400;
                itemDef.maleWearId = 90085;
                itemDef.femaleWearId = 90085;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.stackable = false;
                break;
            case 20411:
                itemDef.modelID = 83331;
                itemDef.name = "Dark Predator boots";
                itemDef.description = "I can almost feel the Dark lord's soul within";
                itemDef.modelZoom = 825;
                itemDef.modelOffset1 = -4;
                itemDef.modelOffsetY = 0;
                itemDef.modelRotationY = 400;
                itemDef.maleWearId = 83330;
                itemDef.femaleWearId = 83330;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.stackable = false;
                break;

            case 20500:
                itemDef.modelID = 130000;
                itemDef.name = "infernal boots";
                itemDef.maleWearId = 130000;
                itemDef.femaleWearId = 130000;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.stackable = false;
                break;
            case 20501:
                itemDef.modelID = 130001;
                itemDef.name = "infernal full helm";
                itemDef.maleWearId = 130002;
                itemDef.femaleWearId = 130003;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.stackable = false;
                break;
            case 20502:
                itemDef.modelID = 130004;
                itemDef.name = "infernal plate body";
                itemDef.maleWearId = 130005;
                itemDef.femaleWearId = 130005;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.stackable = false;
                break;
            case 20600:
                itemDef.modelID = 96127;
                itemDef.name = "infernal plate body";
                itemDef.maleWearId = 96128;
                itemDef.femaleWearId = 96128;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.stackable = false;
                break;
            case 20601:
                itemDef.modelID = 96113;
                itemDef.name = "Infernal Blade";
                itemDef.maleWearId = 96114;
                itemDef.femaleWearId = 96114;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.stackable = false;
                break;
            case 20602:
                itemDef.modelID = 96054;
                itemDef.name = "staff of Infernal";
                itemDef.maleWearId = 96055;
                itemDef.femaleWearId = 96055;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.stackable = false;
                itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
                itemDef.newModelColor = new int[]    {60,60,60,60,60,60,60,60,60,60,60,60,60,60};
                break;
            case 20603:
                itemDef.modelID = 91218;
                itemDef.name = "Sonic Katana";
                itemDef.maleWearId = ItemDef.forID(3666).maleWearId;
                itemDef.femaleWearId = ItemDef.forID(3666).femaleWearId;;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.stackable = false;
                 break;

            case 20510:
                itemDef.modelID = 96104;
                itemDef.name = "Sephiroth Sword";
                itemDef.maleWearId = 96105;
                itemDef.femaleWearId = 96105;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.stackable = false;
                break;
                //npc.models[0] = 98222; //HEAD
            //				npc.models[1] = 98223; //CHEST
            //				npc.models[2] = 98224; //CAPE
            //				npc.models[3] = 98225; //HAND
            //				npc.models[4] = 98226; //LEG
            //				npc.models[5] = 98221; //WEAPON
                case 20607:
                itemDef.modelID = 96113;
                itemDef.name = "Shadow Dragonslayer Blade";
                itemDef.maleWearId = 96114;
                itemDef.femaleWearId = 96114;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.stackable = false;
                itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
                itemDef.newModelColor = new int[]    {57,57,57,57,57,57,57,57,57,57,57,57,57,57};
                break;
            case 20604:
                itemDef.modelID = 96054;
                itemDef.name = "staff of Water";
                itemDef.maleWearId = 96055;
                itemDef.femaleWearId = 96055;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.stackable = false;
                itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
                itemDef.newModelColor = new int[]    {70,70,70,70,70,70,70,70,70,70,70,70,70,70};
                break;
            case 20605:
                itemDef.modelID = 95141;
                itemDef.name = "Water bow";
                itemDef.description = "A pernix shortbow.";
                itemDef.modelZoom = 1447;
                itemDef.modelRotationY = 296;
                itemDef.modelRotationX = 377;
                itemDef.modelOffset1 = 5;
                itemDef.modelOffsetY = -33;
                itemDef.membersObject = true;
                itemDef.maleWearId = 95142;
                itemDef.femaleWearId = 95142;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.stackable = false;
                itemDef.editedModelColor = new int[] {40,31,62,63,64,65,66,67,68,69,70,71,72,73};
                itemDef.newModelColor = new int[]    {70,70,70,70,70,70,70,70,70,70,70,70,70,70};
                break;
            case 20503:
                itemDef.modelID = 130006;
                itemDef.name = "infernal plate legs";
                itemDef.maleWearId = 130007;
                itemDef.femaleWearId = 130007;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.stackable = false;
                break;

            case 20504:
                itemDef.modelID = 130008;
                itemDef.name = "infernal spear";
                itemDef.maleWearId = 130009;
                itemDef.femaleWearId = 130009;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.stackable = false;
                break;

            case 21013:
                itemDef.name = "@bla@ Dark Invisus's Scythe";
                itemDef.modelID = 94458;
                itemDef.maleWearId = 94459;
                itemDef.femaleWearId = 94459;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.stackable = false;
                break;


            case 21008:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.name = "@whi@Mythical sword tier1";
                itemDef.modelID = 91015;
                itemDef.maleWearId = 54355;
                itemDef.femaleWearId = 54355;
                break;

            case 21004:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.name = "@gre@Starter Scythe";
                itemDef.colourRedefine3 = 150000;
                itemDef.modelID = 91526;
                itemDef.maleWearId = 91527;
                itemDef.femaleWearId = 91527;
                break;

            case 21060:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.name = "@gre@Mewtwo sword";
                itemDef.modelID = 90504;
                itemDef.maleWearId = 90505;
                itemDef.femaleWearId = 90505;
                break;
            case 21064:
                itemDef.name = "Forest defender";
                itemDef2 = ItemDef.forID(20072);
                itemDef.modelID = itemDef2.modelID;
                itemDef.maleWearId = itemDef2.maleWearId;
                itemDef.femaleWearId = itemDef2.femaleWearId;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelRotationY = itemDef2.modelRotationY;
                itemDef.modelRotationX = itemDef2.modelRotationX;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.actions = itemDef2.actions;
                break;
            case 21065:
                itemDef.name = "Forest boots";
                itemDef2 = ItemDef.forID(11732);
                itemDef.modelID = itemDef2.modelID;
                itemDef.maleWearId = itemDef2.maleWearId;
                itemDef.femaleWearId = itemDef2.femaleWearId;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelRotationY = itemDef2.modelRotationY;
                itemDef.modelRotationX = itemDef2.modelRotationX;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.actions = itemDef2.actions;
                break;
            case 21066:
                itemDef.name = "Forest gloves";
                itemDef2 = ItemDef.forID(7462);
                itemDef.modelID = itemDef2.modelID;
                itemDef.maleWearId = itemDef2.maleWearId;
                itemDef.femaleWearId = itemDef2.femaleWearId;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelRotationY = itemDef2.modelRotationY;
                itemDef.modelRotationX = itemDef2.modelRotationX;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.actions = itemDef2.actions;
                break;
            case 21063:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.name = "@gre@ Forest sword";
                itemDef.modelID = 120001;
                itemDef.maleWearId = 120000;
                itemDef.femaleWearId = 120000;
                break;
            case 21061:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.name = "@gre@ Oblivion scythe";
                itemDef.modelID = 91674;
                itemDef.maleWearId = 91675;
                itemDef.femaleWearId = 91675;
                break;
            case 21062:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.name = "@gre@ Lava minigun";
                itemDef.modelID = 90674;
                itemDef.maleWearId = 90675;
                itemDef.femaleWearId = 90675;
                break;

            case 21003:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.name = "@red@Owner Sword";
                itemDef.modelID = 64327;
                itemDef.maleWearId = 64327;
                itemDef.femaleWearId = 64327;
                break;

            case 21030:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.name = "@gre@Starter Sword";
                itemDef.colourRedefine3 = 150000;
                itemDef.modelID = 94140;
                itemDef.maleWearId = 94141;
                itemDef.femaleWearId = 94141;
                break;

            case 21031:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.name = "@red@Owner Shield";
                itemDef.modelID = 64326;
                itemDef.maleWearId = 64326;
                itemDef.femaleWearId = 64326;
                break;
            case 3692:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.name = "@red@Owner Wings";
                itemDef.modelID = 64325;
                itemDef.maleWearId = 64325;
                itemDef.femaleWearId = 64325;
                break;

            case 21033:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.name = "@red@Owner Aura";
                itemDef.modelID = 64315;
                itemDef.maleWearId = 64315;
                itemDef.femaleWearId = 64315;
                break;

            case 21002:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.name = "@gre@Double Starter Daggers";
                itemDef.colourRedefine3 = 150000;
                itemDef.modelID = 91452;
                itemDef.maleWearId = 91453;
                itemDef.femaleWearId = 91453;
                break;

            case 21007:
                ItemDef shard = ItemDef.forID(13727);
                itemDef.name = "@whi@Mythical edge shard";
                itemDef.modelID = shard.modelID;
                itemDef.colourRedefine = 96000;
                itemDef.actions = new String[5];
                itemDef.stackable = true;
                itemDef.actions[2] = "Combine";
                itemDef.actions[4] = "Drop";
                break;
            case 21006:
                ItemDef shard2 = ItemDef.forID(13727);
                itemDef.name = "@whi@Mythical hilt shard";
                itemDef.modelID = shard2.modelID;
                itemDef.colourRedefine = 902;
                itemDef.actions = new String[5];
                itemDef.actions[2] = "Combine";
                itemDef.stackable = true;
                itemDef.actions[4] = "Drop";
                break;


            case 21009:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.name = "@whi@Mythical sword tier2";
                itemDef.modelID = 91015;
                itemDef.maleWearId = 100001;
                itemDef.femaleWearId = 100001;
                break;
            case 21010:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.name = "@whi@Mythical sword tier3";
                itemDef.modelID = 91015;
                itemDef.maleWearId = 100002;
                itemDef.femaleWearId = 100002;
                break;


              case 11546:
                 itemDef.name = "Lime Slayer helmet[3]";
                  itemDef.modelID = 56474;
                  itemDef.description = " full helm";
                  itemDef.maleWearId = 56473;
                  itemDef.femaleWearId = 56473;
                  itemDef2 = ItemDef.forID(13263);
                  itemDef.modelOffset1 = itemDef2.modelOffset1;
                  itemDef.modelOffsetX = itemDef2.modelOffsetX;
                  itemDef.modelOffsetY = itemDef2.modelOffsetY;
                  itemDef.modelRotationY = itemDef2.modelRotationY;
                  itemDef.modelRotationX = itemDef2.modelRotationX;
                  itemDef.modelZoom = itemDef2.modelZoom;
                  itemDef.actions = itemDef2.actions;
                  itemDef.editedModelColor = new int[] {60};
                  itemDef.newModelColor = new int[]    {66};
                    break;
              case 11547:
                  itemDef.name = "Aqua Slayer helmet[4]";
                  itemDef.modelID = 56474;
                  itemDef.description = " full helm";
                  itemDef.maleWearId = 56473;
                  itemDef.femaleWearId = 56473;
                  itemDef2 = ItemDef.forID(13263);
                  itemDef.modelOffset1 = itemDef2.modelOffset1;
                  itemDef.modelOffsetX = itemDef2.modelOffsetX;
                  itemDef.modelOffsetY = itemDef2.modelOffsetY;
                  itemDef.modelRotationY = itemDef2.modelRotationY;
                  itemDef.modelRotationX = itemDef2.modelRotationX;
                  itemDef.modelZoom = itemDef2.modelZoom;
                  itemDef.actions = itemDef2.actions;
                  itemDef.editedModelColor = new int[] {60};
                  itemDef.newModelColor = new int[]    {70};
                     break;


            /*  case 20120:
                  itemDef.name = "Invision potion (3)";
                  itemDef.colourRedefine2 = 123123;
                  itemDef.modelID = 2697;
                  itemDef.modelZoom = 550;
                  itemDef.rotationY = 84;
                  itemDef.rotationX = 1996;
                  itemDef.modelOffset1 = 0;
                  itemDef.modelOffsetY = -1;
                  itemDef.stackable = false;
                  itemDef.membersObject = true;
                  itemDef.actions = new String[5];
                  itemDef.actions[0] = "Drink";
                  itemDef.actions[3] = "Empty";
                     break;
              case 20119:
                  itemDef.name = "Invision potion (3)";
                  itemDef.colourRedefine2 = 123123;
                  itemDef.modelID = 2697;
                  itemDef.modelZoom = 550;
                  itemDef.rotationY = 84;
                  itemDef.rotationX = 1996;
                  itemDef.modelOffset1 = 0;
                  itemDef.modelOffsetY = -1;
                  itemDef.stackable = false;
                  itemDef.membersObject = true;
                  itemDef.actions = new String[5];
                  itemDef.actions[0] = "Drink";
                  itemDef.actions[3] = "Empty";
                  itemDef.certTemplateID = 799;
                  itemDef.certID = 20120;
                     break; */

              case 11548:
                  itemDef.name = "Corrupt Slayer helmet[5]";
                  itemDef.modelID = 56474;
                  itemDef.description = " full helm";
                  itemDef.maleWearId = 56473;
                  itemDef.femaleWearId = 56473;
                  itemDef2 = ItemDef.forID(13263);
                  itemDef.modelOffset1 = itemDef2.modelOffset1;
                  itemDef.modelOffsetX = itemDef2.modelOffsetX;
                  itemDef.modelOffsetY = itemDef2.modelOffsetY;
                  itemDef.modelRotationY = itemDef2.modelRotationY;
                  itemDef.modelRotationX = itemDef2.modelRotationX;
                  itemDef.modelZoom = itemDef2.modelZoom;
                  itemDef.actions = itemDef2.actions;
                  itemDef.editedModelColor = new int[] {60};
                  itemDef.newModelColor = new int[]    {71};
                     break;
              case 11550:
                  itemDef.name = "Lava Slayer helmet[1]";
                  itemDef.modelID = 56474;
                  itemDef.description = " full helm";
                  itemDef.maleWearId = 56473;
                  itemDef.femaleWearId = 56473;
                  itemDef2 = ItemDef.forID(13263);
                  itemDef.modelOffset1 = itemDef2.modelOffset1;
                  itemDef.modelOffsetX = itemDef2.modelOffsetX;
                  itemDef.modelOffsetY = itemDef2.modelOffsetY;
                  itemDef.modelRotationY = itemDef2.modelRotationY;
                  itemDef.modelRotationX = itemDef2.modelRotationX;
                  itemDef.modelZoom = itemDef2.modelZoom;
                  itemDef.actions = itemDef2.actions;
                  itemDef.editedModelColor = new int[] {60};
                  itemDef.newModelColor = new int[]    {40};
                     break;
              case 11549:
                  itemDef.name = "Golden Slayer helmet[2]";
                  itemDef.modelID = 56474;
                  itemDef.description = " full helm";
                  itemDef.maleWearId = 56473;
                  itemDef.femaleWearId = 56473;
                  itemDef2 = ItemDef.forID(13263);
                  itemDef.modelOffset1 = itemDef2.modelOffset1;
                  itemDef.modelOffsetX = itemDef2.modelOffsetX;
                  itemDef.modelOffsetY = itemDef2.modelOffsetY;
                  itemDef.modelRotationY = itemDef2.modelRotationY;
                  itemDef.modelRotationX = itemDef2.modelRotationX;
                  itemDef.modelZoom = itemDef2.modelZoom;
                  itemDef.actions = itemDef2.actions;
                  itemDef.editedModelColor = new int[] {60};
                  itemDef.newModelColor = new int[]    {59};
                     break;
              case 11552:
                  itemDef.name = "Blessed Gloves";
                  itemDef.colourRedefine2 = 326;
                  itemDef2 = ItemDef.forID(7462);
                  itemDef.modelID = itemDef2.modelID;
                  itemDef.maleWearId = itemDef2.maleWearId;
                    itemDef.femaleWearId = itemDef2.femaleWearId;
                   itemDef.modelOffset1 = itemDef2.modelOffset1;
                    itemDef.modelOffsetX = itemDef2.modelOffsetX;
                    itemDef.modelOffsetY = itemDef2.modelOffsetY;
                     itemDef.modelRotationY = itemDef2.modelRotationY;
                      itemDef.modelRotationX = itemDef2.modelRotationX;
                     itemDef.modelZoom = itemDef2.modelZoom;
                     itemDef.actions = new String[5];
                     itemDef.actions[1] = "Wear";
                     itemDef.actions[4] = "Drop";
                     itemDef.actions[2] = "Upgrade";
                     break;
              case 11553:
                  itemDef.name = "Gloves of Death";
                  itemDef.colourRedefine2 = 32326;
                  itemDef2 = ItemDef.forID(7462);
                  itemDef.modelID = itemDef2.modelID;
                  itemDef.maleWearId = itemDef2.maleWearId;
                    itemDef.femaleWearId = itemDef2.femaleWearId;
                   itemDef.modelOffset1 = itemDef2.modelOffset1;
                    itemDef.modelOffsetX = itemDef2.modelOffsetX;
                    itemDef.modelOffsetY = itemDef2.modelOffsetY;
                     itemDef.modelRotationY = itemDef2.modelRotationY;
                      itemDef.modelRotationX = itemDef2.modelRotationX;
                     itemDef.modelZoom = itemDef2.modelZoom;
                     itemDef.actions = new String[5];
                     itemDef.actions[1] = "Wear";
                     itemDef.actions[4] = "Drop";
                     break;
              case 11555:
                  itemDef.name = "Ring of Chaos";
                  itemDef.colourRedefine2 = 32326;
                  itemDef2 = ItemDef.forID(6737);
                  itemDef.modelID = itemDef2.modelID;
                  itemDef.maleWearId = itemDef2.maleWearId;
                    itemDef.femaleWearId = itemDef2.femaleWearId;
                   itemDef.modelOffset1 = itemDef2.modelOffset1;
                    itemDef.modelOffsetX = itemDef2.modelOffsetX;
                    itemDef.modelOffsetY = itemDef2.modelOffsetY;
                     itemDef.modelRotationY = itemDef2.modelRotationY;
                      itemDef.modelRotationX = itemDef2.modelRotationX;
                     itemDef.modelZoom = itemDef2.modelZoom;
                     itemDef.actions = new String[5];
                     itemDef.actions[1] = "Wear";
                     itemDef.actions[4] = "Drop";
                     itemDef.actions[2] = "Upgrade";
                     break;
              case 11556:
                  itemDef.name = "Ring of Death";
                  itemDef.colourRedefine2 = 213131;
                  itemDef2 = ItemDef.forID(6737);
                  itemDef.modelID = itemDef2.modelID;
                  itemDef.maleWearId = itemDef2.maleWearId;
                    itemDef.femaleWearId = itemDef2.femaleWearId;
                   itemDef.modelOffset1 = itemDef2.modelOffset1;
                    itemDef.modelOffsetX = itemDef2.modelOffsetX;
                    itemDef.modelOffsetY = itemDef2.modelOffsetY;
                     itemDef.modelRotationY = itemDef2.modelRotationY;
                      itemDef.modelRotationX = itemDef2.modelRotationX;
                     itemDef.modelZoom = itemDef2.modelZoom;
                     itemDef.actions = new String[5];
                     itemDef.actions[1] = "Wear";
                     itemDef.actions[4] = "Drop";
                     break;
              case 6737://BERSERKER RING
                  itemDef.actions = new String[5];
                  itemDef.actions[1] = "Wear";
                  itemDef.actions[4] = "Drop";
                  itemDef.actions[2] = "Upgrade";
                  break;
              case 7462://BARROWS GLOVES
                  itemDef.actions = new String[5];
                  itemDef.actions[1] = "Wear";
                  itemDef.actions[4] = "Drop";
                  itemDef.actions[2] = "Upgrade";
                  break;
              case 620:
                  itemDef.name = "10 Boss points";
                  itemDef.actions = new String[5];
                  itemDef.actions[4] = "Drop";
                  itemDef.actions[0] = "Claim";
                  break;
            case 17847:
                itemDef.modelID = 90587;
                itemDef.name = "Blood godsword";
                itemDef.description = "The lava flows freely through the blade";
                itemDef.modelZoom = 1500;
                itemDef.modelRotationY = 228;
                itemDef.modelRotationX = 1985;
                itemDef.modelOffset1 = 5;
                itemDef.modelOffsetY = -55;
                itemDef.stackable = false;
                itemDef.membersObject = true;
                itemDef.maleWearId = 90588;
                itemDef.femaleWearId = 90588;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.editedModelColor = new int[4];
                itemDef.newModelColor = new int[4];
                itemDef.editedModelColor[0] = 40;
                itemDef.newModelColor[0] = 52;
                itemDef.editedModelColor[1] = 31;
                itemDef.newModelColor[1] = 52;
                itemDef.editedModelColor[2] = 1;
                itemDef.newModelColor[2] = 57;
                itemDef.editedModelColor[3] = 24;
                itemDef.newModelColor[3] = 57;
                break;
            case 19938:
                itemDef.modelID = 95027;
                itemDef.name = "AK47";
                itemDef.description = "This weapon shouldnt be in the game :o";
                itemDef.modelZoom = 1500;
                itemDef.modelRotationY = 228;
                itemDef.modelRotationX = 1985;
                itemDef.modelOffset1 = 5;
                itemDef.modelOffsetY = -55;
                itemDef.stackable = false;
                itemDef.membersObject = true;
                itemDef.maleWearId = 95028;
                itemDef.femaleWearId = 95028;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                break;
            case 20091:
                itemDef.modelID = 95065;
                itemDef.name = "AK47 @red@(Lava)";
                itemDef.description = "This weapon shouldnt be in the game :o";
                itemDef.modelZoom = 1500;
                itemDef.modelRotationY = 228;
                itemDef.modelRotationX = 1985;
                itemDef.modelOffset1 = 5;
                itemDef.modelOffsetY = -55;
                itemDef.stackable = false;
                itemDef.membersObject = true;
                itemDef.maleWearId = 95066;
                itemDef.femaleWearId = 95066;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                break;
            case 19939:
                itemDef.modelID = 95029;
                itemDef.name = "Flamethrower";
                itemDef.description = "This weapon shouldnt be in the game :o";
                itemDef.modelZoom = 1500;
                itemDef.modelRotationY = 228;
                itemDef.modelRotationX = 1985;
                itemDef.modelOffset1 = 5;
                itemDef.modelOffsetY = -55;
                itemDef.stackable = false;
                itemDef.membersObject = true;
                itemDef.maleWearId = 95030;
                itemDef.femaleWearId = 95030;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                break;

            case 569:
                itemDef.name = "Fuel";
                itemDef.description = "Fuel for the flamethrower";
                itemDef.stackable = true;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                break;

            case 17848:
                itemDef.modelID = 90589;
                itemDef.name = "Flaming whip";
                itemDef.description = "Ouch!";
                itemDef.modelZoom = 1000;
                itemDef.modelRotationY = 601;
                itemDef.modelRotationX = 1000;
                itemDef.modelOffset1 = 4;
                itemDef.modelOffsetY = 8;
                itemDef.membersObject = true;
                itemDef.maleWearId = 90590;
                itemDef.femaleWearId = 90590;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                break;

            case 17849:
                itemDef.modelID = 90591;
                itemDef.name = "Razor whip";
                itemDef.description = "Ouch!";
                itemDef.modelZoom = 1000;
                itemDef.modelRotationY = 601;
                itemDef.modelRotationX = 1000;
                itemDef.modelOffset1 = 4;
                itemDef.modelOffsetY = 8;
                itemDef.membersObject = true;
                itemDef.maleWearId = 90592;
                itemDef.femaleWearId = 90592;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                break;

            case 3947:
                itemDef.modelID = 93273;
                itemDef.name = "Baphomet Torva full helm";
                itemDef.description = "a full helm imbued with lava";
                itemDef.modelZoom = 672;
                itemDef.modelRotationY = 85;
                itemDef.modelRotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -3;
                itemDef.maleWearId = 93274;
                itemDef.femaleWearId = 93274;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.editedModelColor = new int[] {40,1,24};
                itemDef.newModelColor = new int[]    {70,55,55};
                break;
            case 3948:
                itemDef.modelID = 93278;
                itemDef.name = "Baphomet Torva platebody";
                itemDef.description = "Torva platebody imbued with lava";
                itemDef.modelZoom = 1506;
                itemDef.modelRotationY = 473;
                itemDef.modelRotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleWearId = 93277;
                itemDef.femaleWearId = 93277;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.editedModelColor = new int[] {40,1,24};
                itemDef.newModelColor = new int[]    {70,55,55};
                break;
            case 3949:
                itemDef.modelID = 93275;
                itemDef.name = "Baphomet Torva platelegs ";
                itemDef.description = "Torva platelegs imbued with lava";
                itemDef.modelZoom = 1740;
                itemDef.modelRotationY = 474;
                itemDef.modelRotationX = 2045;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -5;
                itemDef.maleWearId = 93276;
                itemDef.femaleWearId = 93276;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                itemDef.editedModelColor = new int[] {40,1,24};
                itemDef.newModelColor = new int[]    {70,55,55};
                break;
            case 3950:
                itemDef.modelID = 95079;
                itemDef.name = "Drygore Boots";
                itemDef.description = "It's a pair of drygore boots.";
                itemDef.modelZoom = 855;
                itemDef.modelRotationY = 215;
                itemDef.modelRotationX = 94;
                itemDef.modelOffset1 = 4;
                itemDef.modelOffsetY = -32;
                itemDef.maleWearId = 95080;
                itemDef.femaleWearId = 95080;
                itemDef.groundActions = new String[] { null, null, "Take", null, null };
                itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
                break;
            case 3951:
                itemDef.modelID = 95081;
                itemDef.name = "Drygore Gauntlets";
                itemDef.description = "It's a pair of Drygore Gauntlets.";
                itemDef.modelZoom = 592;
                itemDef.modelRotationY = 323;
                itemDef.modelRotationX = 1710;
                itemDef.modelOffset1 = 3;
                itemDef.modelOffsetY = 5;
                itemDef.maleWearId = 95082;
                itemDef.femaleWearId = 95082;
                itemDef.groundActions = new String[] { null, null, "Take", null, null };
                itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
                break;
            case 21081:
                itemDef.name = "Voldemort's top";
                itemDef.modelID = 95035;
                itemDef.femaleWearId = 95036;
                itemDef.maleWearId = 95036;
                itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
                break;
            case 21082:
                itemDef.name = "Infernal Minigun";
                itemDef.modelID = 95031;
                itemDef.femaleWearId = 95032;
                itemDef.maleWearId = 95032;
                itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
                break;
            case 20643:
                itemDef.name = "Shadow Minigun";
                itemDef.modelID = 95031;
                itemDef.femaleWearId = 95032;
                itemDef.maleWearId = 95032;
                itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
                itemDef.editedModelColor = new int[] {40,31,62,63,64,65,66,67,60,68,69,70,71,72,73};
                itemDef.newModelColor = new int[]    {53,53,53,53,53,53,53,53,53,53,53,53,53,53,53};
                break;
            case 21083:
                itemDef.name = "Purple Minigun";
                itemDef.modelID = 95033;
                itemDef.femaleWearId = 95034;
                itemDef.maleWearId = 95034;
                itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
                break;
            case 21080:
                itemDef.name = "@red@Blood Duel Beretta";
                itemDef.modelID = 94059;
                itemDef.femaleWearId = 94060;
                itemDef.maleWearId = 94060;
                itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
                itemDef.editedModelColor = new int[4];
                itemDef.newModelColor = new int[4];
                itemDef.editedModelColor[0] = 40;
                itemDef.newModelColor[0] = 52;
                itemDef.editedModelColor[1] = 31;
                itemDef.newModelColor[1] = 52;
                itemDef.editedModelColor[2] = 1;
                itemDef.newModelColor[2] = 57;
                itemDef.editedModelColor[3] = 24;
                itemDef.newModelColor[3] = 57;
                break;
            case 21075:
                itemDef.name = "@red@Blood phat";
                itemDef.modelID = 94332;
                itemDef.femaleWearId = 94333;
                itemDef.maleWearId = 94333;
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 51;
                itemDef.newModelColor[0] = 52;

                itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
                break;

            case 20950:
                itemDef.modelID = ItemDef.forID(9018).modelID;
                itemDef.name = "gun powder";
                itemDef.stackable = true;
                itemDef.colourRedefine = 25;
                itemDef.modelZoom = ItemDef.forID(9018).modelZoom;
                itemDef.actions = new String[] { null, null, null, null, "Drop" };
                break;

            case 20951:
                itemDef.modelID = ItemDef.forID(9018).modelID;
                itemDef.name = "sulfur";
                itemDef.stackable = true;
                itemDef.colourRedefine = 8128;
                itemDef.modelZoom = ItemDef.forID(9018).modelZoom;
                itemDef.actions = new String[] { null, null, null, null, "Drop" };
                break;

            case 20952:
                itemDef.modelID = ItemDef.forID(9018).modelID;
                itemDef.name = "Charcoal";
                itemDef.stackable = true;
                itemDef.colourRedefine = 96000;
                itemDef.modelZoom = ItemDef.forID(9018).modelZoom;
                itemDef.actions = new String[] { null, null, null, null, "Drop" };
                break;
                
                //|------------------------------------------Start of Ranks----------------------------------|
            case 12421:
                itemDef.name = "@red@Donator Scroll";
                itemDef.actions = new String[] { "Info", null, "@red@Activate", null, "Drop" };
                itemDef.modelID = 90001;
                break;
            case 12422:
                itemDef.name = "@gre@Super Donator Scroll";
                itemDef.actions = new String[] { "Info", null, "@gre@Activate", null, "Drop" };
                itemDef.modelID = 90001;
                break;
            case 12423:
                itemDef.name = "@blu@Extreme Donator Scroll";
                itemDef.actions = new String[] { "Info", null, "@blu@Activate", null, "Drop" };
                itemDef.modelID = 90001;
                break;
            case 12424:
                itemDef.name = "@mag@Sponsor Donator Rank";
                itemDef.actions = new String[] { "Info", null, "@mag@Activate", null, "Drop" };
                itemDef.modelID = 90001;
                break;
            case 12425:
                itemDef.name = "@bla@Executive Donator Scroll";
                itemDef.actions = new String[] { "Info", null, "@bla@Activate", null, "Drop" };
                itemDef.modelID = 90001;
                break;
            case 12426:
                itemDef.name = "@yel@Divine Donator Scroll";
                itemDef.actions = new String[] { "Info", null, "@yel@Activate", null, "Drop" };
                itemDef.modelID = 90001;
                break;              
               //|------------------------------------------End of Ranks----------------------------------|
                
            case 21077:
                itemDef.name = "Infernal M16 mh";
                itemDef.modelID = 94411;
                itemDef.femaleWearId = 94412;
                itemDef.maleWearId = 94412;
                itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
                break;
            case 21076:
                itemDef.name = "Nude M16 mh";
                itemDef.modelID = 94411;
                itemDef.femaleWearId = 94412;
                itemDef.maleWearId = 94412;
                itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
                break;
            case 21078:
                itemDef.name = "Infernal M16 oh";
                itemDef.modelID = 94411;
                itemDef.femaleWearId = 94413;
                itemDef.maleWearId = 94413;
                itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
                break;
                case 21079:
                itemDef.name = "@red@Blood sniper";
                itemDef.modelID = 91811;
                itemDef.femaleWearId = 91812;
                    itemDef.editedModelColor = new int[4];
                    itemDef.newModelColor = new int[4];
                    itemDef.editedModelColor[0] = 40;
                    itemDef.newModelColor[0] = 52;
                    itemDef.editedModelColor[1] = 50;
                    itemDef.newModelColor[1] = 52;
                    itemDef.editedModelColor[2] = 51;
                    itemDef.newModelColor[2] = 52;
                    itemDef.editedModelColor[3] = 24;
                    itemDef.newModelColor[3] = 52;
                itemDef.maleWearId = 91812;
                itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
                break;

                case 21069:
                itemDef.name = "Seers boots";
                itemDef.modelID = 94293;
                itemDef.femaleWearId = 94293;
                itemDef.maleWearId = 94293;
                itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
                break;
                case 21068:
                itemDef.name = "Archers boots";
                itemDef.modelID = 94294;
                itemDef.femaleWearId = 94294;
                itemDef.maleWearId = 94294;
                itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
                break;
                case 21067:
                itemDef.name = "Berserker boots";
                itemDef.modelID = 94292;
                itemDef.femaleWearId = 94292;
                itemDef.maleWearId = 94292;
                itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
                break;

            case 3952:
                itemDef.modelID = 95083;
                itemDef.name = "Drygore Wings";
                itemDef.description = "It's a pair of drygore wings.";
               // itemDef.colourRedefine2 = 356987;
                itemDef.modelZoom = 1519;
                itemDef.modelRotationY = 595;
                itemDef.modelRotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleWearId = 95084;
                itemDef.femaleWearId = 95084;
                itemDef.groundActions = new String[] { null, null, "Take", null, null };
                itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
                break;
            case 3969: 
                itemDef.modelID = 95010;
                itemDef.name = "Ramlord wings";
                itemDef.description = "A pair of Ramlord wings";
                itemDef.colourRedefine3 = 35698;
                itemDef.modelZoom = 825;
                itemDef.modelOffset1 = -4;
                itemDef.modelOffsetY = 0;
                itemDef.modelRotationX = 400;
                itemDef.maleWearId = 95011;
                
                itemDef.femaleWearId = 95011;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "drop";
                itemDef.stackable = false;
                break;
            case 3953:
                itemDef.modelID = 95085;
                itemDef.name = "Drygore Godsword";
                itemDef.description = "It's a drygore godsword.";
                itemDef.modelZoom = 1711;
                itemDef.modelRotationX = 1513;
                itemDef.modelRotationY = 354;
                itemDef.maleWearId = 95086;
                itemDef.femaleWearId = 95086;
                itemDef.actions = new String[] { null, null, "Take", null, null };
                itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
                break;
            case 20095:
            	itemDef.colourRedefine2 = 51;
                itemDef.modelID = 90694;
                itemDef.name = "Sonic full helmet";
                itemDef.description = "It's a purgatory full helmet.";
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 100;
                itemDef.modelRotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleWearId = 90695;
                itemDef.femaleWearId = 90695;
                itemDef.groundActions = new String[] { null, null, "Take", null, null };
                itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
                break;
            case 20096:
            	itemDef.colourRedefine2 = 51;
                itemDef.modelID = 90696;
                itemDef.name = "Sonic platebody";
                itemDef.description = "It's a purgatory platebody.";
                itemDef.modelZoom = 1400;
                itemDef.modelRotationY = 595;
                itemDef.modelRotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleWearId = 90697;
                itemDef.femaleWearId = 90697;
                itemDef.groundActions = new String[] { null, null, "Take", null, null };
                itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
                break;
            case 20097:
            	itemDef.colourRedefine2 = 51;
                itemDef.modelID = 90698;
                itemDef.name = "Sonic platelegs";
                itemDef.description = "It's a pair of Sonic platelegs.";
                itemDef.modelZoom = 1500;
                itemDef.modelRotationY = 595;
                itemDef.modelRotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleWearId = 90699;
                itemDef.femaleWearId = 90699;
                itemDef.groundActions = new String[] { null, null, "Take", null, null };
                itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
                break;
            case 20098:
            	itemDef.colourRedefine2 = 51;
                itemDef.modelID = 91133;
                itemDef.name = "Sonic boots";
                itemDef.description = "It's a pair of Sonic boots.";
                itemDef.modelZoom = 855;
                itemDef.modelRotationY = 215;
                itemDef.modelRotationX = 94;
                itemDef.modelOffset1 = 4;
                itemDef.modelOffsetY = -32;
                itemDef.maleWearId = 91133;
                itemDef.femaleWearId = 91133;
                itemDef.groundActions = new String[] { null, null, "Take", null, null };
                itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
                break;
            case 20099:
            	itemDef.colourRedefine2 = 51;
                itemDef.modelID = 91134;
                itemDef.name = "Sonic gauntlets";
                itemDef.description = "It's a pair of Sonic gauntlets.";
                itemDef.modelZoom = 592;
                itemDef.modelRotationY = 323;
                itemDef.modelRotationX = 1710;
                itemDef.modelOffset1 = 3;
                itemDef.modelOffsetY = 5;
                itemDef.maleWearId = 91135;
                itemDef.femaleWearId = 91135;
                itemDef.groundActions = new String[] { null, null, "Take", null, null };
                itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
                break;
            case 20100:
            	itemDef.colourRedefine2 = 51;
                itemDef.modelID = 90728;
                itemDef.name = "Sonic wings";
                itemDef.description = "It's a pair of Sonic wings.";
                itemDef.modelZoom = 1519;
                itemDef.modelRotationY = 595;
                itemDef.modelRotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleWearId = 90729;
                itemDef.femaleWearId = 90729;
                itemDef.groundActions = new String[] { null, null, "Take", null, null };
                itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
                break;
            case 20101:
            	itemDef.colourRedefine2 = 51;
                itemDef.modelID = 95110;
                itemDef.name = "Purgatory longsword";
                itemDef.description = "It's a purgatory longsword.";
                itemDef.modelZoom = 1519;
                itemDef.modelRotationY = 595;
                itemDef.modelRotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleWearId = 95111;
                itemDef.femaleWearId = 95111;
                itemDef.groundActions = new String[] { null, null, "Take", null, null };
                itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
                break;

            case 20102:
                itemDef.modelID = 94528;
                itemDef.name = "@gre@Bowser blade";
                itemDef.description = "It's a Camouflage Bowser.";
                itemDef.modelZoom = 900;
                itemDef.modelRotationY = 324;
                itemDef.modelRotationX = 1808;
                itemDef.modelOffset1 = -2;
                itemDef.modelOffsetY = 3;
                itemDef.maleWearId = 94529;
                itemDef.femaleWearId = 94529;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 20103:
                itemDef.modelID = 94381;
                itemDef.name = "@gre@Bowser platelegs";
                itemDef.description = "It's a set of camouflage torva platelegs.";
                itemDef.modelZoom = 1740;
                itemDef.modelRotationY = 474;
                itemDef.modelRotationX = 2045;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -5;
                itemDef.maleWearId = 94382;
                itemDef.femaleWearId = 94382;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 20104:
                itemDef.modelID = 94379;
                itemDef.name = "@gre@Bowser fullhelm";
                itemDef.description = "It's a camouflage torva fullhelm.";
                itemDef.modelZoom = 676;
                itemDef.modelRotationY = 0;
                itemDef.modelRotationX = 0;
                itemDef.modelOffset1 = 3;
                itemDef.modelOffsetY = -14;
                itemDef.maleWearId = 94380;
                itemDef.femaleWearId = 94380;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 20105:
                itemDef.modelID = 94383;
                itemDef.name = "@gre@Bowser platebody";
                itemDef.description = "It's a camouflage torva platebody.";
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -8;
                itemDef.modelZoom = 1513;
                itemDef.modelRotationY = 566;
                itemDef.modelRotationX = 0;
                itemDef.maleWearId = 94384;
                itemDef.femaleWearId = 94384;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;

            case 20106:
                itemDef.name = "@gre@Bowser gloves";
                itemDef.description = "It's a pair of camouflage gloves.";
                itemDef.modelZoom = 592;
                //itemDef.colourRedefine3 = 17350;
                itemDef.modelRotationY = 323;
                itemDef.modelRotationX = 1710;
                itemDef.modelOffset1 = 3;
                itemDef.modelOffsetY = 5;
                itemDef.modelID = 49;
                itemDef.maleWearId = 50;
                itemDef.femaleWearId = 50;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 20107:
                itemDef.name = "@gre@Bowser boots";
                itemDef.description = "It's a pair of camouflage boots.";
                itemDef.modelID = 51;
                itemDef.modelZoom = 855;
                itemDef.modelRotationY = 215;
                itemDef.modelRotationX = 94;
                itemDef.modelOffset1 = 4;
                itemDef.modelOffsetY = -32;
                itemDef.maleWearId = 52;
                itemDef.femaleWearId = 52;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.description = "Some boots.";
                break;


            case 19906:
                itemDef.modelID = 95098;
                itemDef.name = "Bullets";
                itemDef.description = "Bullets";
                itemDef.modelZoom = 1500;
                itemDef.modelOffsetY = 5;
                itemDef.modelOffset1 = 3;
                itemDef.modelRotationX = 117;
                itemDef.modelRotationY = 477;
                itemDef.groundActions = new String[] { null, null, "Take", null, null };
                itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
                itemDef.stackable = true;
                break;

                case 19904:
                itemDef.modelID = 95098;
                itemDef.name = "Infernal Bullets";
                itemDef.description = "Bullets";
                itemDef.modelZoom = 1500;
                itemDef.modelOffsetY = 5;
                itemDef.modelOffset1 = 3;
                itemDef.modelRotationX = 117;
                itemDef.modelRotationY = 477;
                itemDef.groundActions = new String[] { null, null, "Take", null, null };
                itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
                itemDef.stackable = true;
                break;
                case 19903:
                itemDef.modelID = 95098;
                itemDef.name = "Ice Bullets";
                itemDef.description = "Bullets";
                itemDef.modelZoom = 1500;
                itemDef.modelOffsetY = 5;
                itemDef.modelOffset1 = 3;
                itemDef.modelRotationX = 117;
                itemDef.modelRotationY = 477;
                itemDef.groundActions = new String[] { null, null, "Take", null, null };
                itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
                itemDef.stackable = true;
                break;
            case 996:
            case 997:
            case 998:
            case 999:
            case 1000:
            case 1001:
            case 1002:
            case 1003:
            case 1004:
                itemDef.name = "Coins";
                itemDef.newModelColor = new int[] { 54194 };
                itemDef.editedModelColor = new int[] { 8128 };
                break;
            case 2381:
                itemDef.name = "Equilibrium Essence";
                itemDef.description = "A strange essence from the wilderness";
                itemDef.stackable = true;
                break;
            // Tkaar armor
            case 15662:
                itemDef.modelID = 90571;
                itemDef.name = "Tok-Tkzar platebody";
                itemDef.description = "Carved from the exo-skeleton of Jad itself";
                itemDef.modelZoom = 1828;
                itemDef.modelRotationY = 539;
                itemDef.modelRotationX = 0;
                itemDef.modelOffset1 = -1;
                itemDef.modelOffsetY = 0;
                itemDef.maleWearId = 90572;
                itemDef.femaleWearId = 90572;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 15663:
                itemDef.modelID = 90573;
                itemDef.name = "Tok-Tkzar platelegs";
                itemDef.description = "Carved from the exo-skeleton of Jad itself";
                itemDef.modelZoom = 1828;
                itemDef.modelRotationY = 539;
                itemDef.modelRotationX = 0;
                itemDef.modelOffset1 = -1;
                itemDef.modelOffsetY = 0;
                itemDef.maleWearId = 90574;
                itemDef.femaleWearId = 90574;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 15664:
                itemDef.modelID = 90575;
                itemDef.name = "Tok-Tkzar cloak";
                itemDef.description = "Carved from the exo-skeleton of Jad itself";
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 1;
                itemDef.stackable = false;
                itemDef.modelZoom = 2128;
                itemDef.modelRotationY = 504;
                itemDef.modelRotationX = 0;
                itemDef.maleWearId = 90576;
                itemDef.femaleWearId = 90576;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                break;
            case 15665:
                itemDef.modelID = 90577;
                itemDef.name = "Tok-Tkzar boots";
                itemDef.description = "Carved from the exo-skeleton of Jad itself";
                itemDef.modelZoom = 676;
                itemDef.modelRotationY = 63;
                itemDef.modelRotationX = 106;
                itemDef.modelOffset1 = 5;
                itemDef.modelOffsetY = -1;
                itemDef.maleWearId = 90578;
                itemDef.femaleWearId = 90578;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;

            case 15666:
                itemDef.modelID = 90579;
                itemDef.name = "Tok-Tkzar gloves";
                itemDef.description = "Carved from the exo-skeleton of Jad itself";
                itemDef.modelZoom = 648;
                itemDef.modelRotationY = 618;
                itemDef.modelRotationX = 1143;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -5;
                itemDef.maleWearId = 90580;
                itemDef.femaleWearId = 90580;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;

            case 15667:
                itemDef.modelID = 90581;
                itemDef.name = "Tok-Tkzar helm";
                itemDef.description = "Carved from the exo-skeleton of Jad itself";
                itemDef.modelZoom = 672;
                itemDef.modelRotationY = 85;
                itemDef.modelRotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -3;
                itemDef.maleWearId = 90582;
                itemDef.femaleWearId = 90582;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Morph";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 15668:
                itemDef.modelID = 90583;
                itemDef.name = "Tok-Tkzar mace";
                itemDef.description = "Carved from the exo-skeleton of Jad itself";
                itemDef.modelZoom = 1672;
                itemDef.modelRotationY = 285;
                itemDef.modelRotationX = 607;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -3;
                itemDef.maleWearId = 90584;
                itemDef.femaleWearId = 90584;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Morph";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            //End tkarr armor

            case 3642:
                itemDef.modelID = 54253;
                itemDef.name = "RamLord kite Shield";
                itemDef.description = "A custom item.";
                itemDef.modelZoom = 2434;
                itemDef.modelRotationY = 458;
                itemDef.modelRotationX = 0;
                itemDef.colourRedefine2 = 356782;
                itemDef.modelOffset1 = -3;
                itemDef.modelOffsetY = 9;
                itemDef.membersObject = true;
                itemDef.maleWearId = 55991;
                itemDef.femaleWearId = 55991;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.stackable = false;
                break;
            case 3643:
                itemDef.modelID = 54126;
                itemDef.name = "RamLord platebody";
                itemDef.description = "A custom item.";
                itemDef.modelZoom = 1447;
                itemDef.modelRotationY = 485;
                itemDef.modelRotationX = 0;
                itemDef.colourRedefine2 = 356782;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 13;
                itemDef.membersObject = true;
                itemDef.maleWearId = 55851;
                itemDef.femaleWearId = 55851;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.stackable = false;
                break;

            case 3644:
                itemDef.modelID = 54175;
                itemDef.name = "RamLord platelegs";
                itemDef.description = "A custom item.";
                itemDef.modelZoom = 1730;
                itemDef.modelRotationY = 525;
                itemDef.modelRotationX = 0;
                itemDef.colourRedefine2 = 356782;
                itemDef.modelOffset1 = 7;
                itemDef.modelOffsetY = 3;
                itemDef.membersObject = true;
                itemDef.maleWearId = 55815;
                itemDef.femaleWearId = 56478;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.stackable = false;
                break;

            case 3645:
                itemDef.modelID = 54164;
                itemDef.name = "RamLord full helm";
                itemDef.description = "A custom item.";
                itemDef.modelZoom = 921;
                itemDef.modelRotationY = 121;
                itemDef.modelRotationX = 0;
                itemDef.colourRedefine2 = 356782;//256782
                //cool primal color = 277782, 286782
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -4;
                itemDef.membersObject = true;
                itemDef.maleWearId = 55770;
                itemDef.femaleWearId = 56434;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.stackable = false;
                break;
            case 3646:
                itemDef.modelID = 54055;
                itemDef.name = "RamLord chainbody";
                itemDef.description = "A custom item.";
                itemDef.modelZoom = 1447;
                itemDef.modelRotationY = 485;
                itemDef.modelRotationX = 0;
                itemDef.colourRedefine2 = 356782;
                //cool primal color = 277782, 286782
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 8;
                itemDef.membersObject = true;
                itemDef.maleWearId = 55830;
                itemDef.femaleWearId = 56518;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.stackable = false;
                break;

            case 3647:
                itemDef.modelID = 54062;
                itemDef.name = "RamLord boots";
                itemDef.description = "A custom item.";
                itemDef.modelZoom = 789;
                itemDef.modelRotationY = 164;
                itemDef.modelRotationX = 156;
                itemDef.colourRedefine2 = 356782;
                //cool primal color = 277782, 286782
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -8;
                itemDef.membersObject = true;
                itemDef.maleWearId = 55673;
                itemDef.femaleWearId = 56353;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.stackable = false;
                break;

            case 3648:
                itemDef.modelID = 54037;
                itemDef.name = "RamLord gloves";
                itemDef.description = "A custom item.";
                itemDef.modelZoom = 930;
                itemDef.modelRotationY = 420;
                itemDef.modelRotationX = 828;
                itemDef.colourRedefine2 = 356782;
                //cool primal color = 277782, 286782
                itemDef.modelOffset1 = 3;
                itemDef.modelOffsetY = -7;
                itemDef.membersObject = true;
                itemDef.maleWearId = 55728;
                itemDef.femaleWearId = 56417;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.stackable = false;
                break;
            case 20051: //21063
                //itemDef.colourRedefine2 = 1;
                itemDef2 = ItemDef.forID(21063);
                itemDef.name = "RamLord longsword";
                itemDef.description = "A custom item.";
                itemDef.modelID = itemDef2.modelID;
                itemDef.maleWearId = itemDef2.maleWearId;
                itemDef.femaleWearId = itemDef2.femaleWearId;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelRotationY = itemDef2.modelRotationY;
                itemDef.modelRotationX = itemDef2.modelRotationX;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.actions = itemDef2.actions;
                itemDef.colourRedefine = 361782;
                break;


            case 3650:
                itemDef.modelID = 54425;
                itemDef.name = "Draconian shortbow";
                itemDef.description = "A custom item.";
                itemDef.modelZoom = 1447;
                itemDef.modelRotationY = 296;
                itemDef.modelRotationX = 377;
                itemDef.modelOffset1 = 5;
                itemDef.modelOffsetY = -33;
                itemDef.colourRedefine2 = 277782;
                //rapier : sky : 62111,
                //itemDef.aByte154 = -14;
                //cool primal color = 277782, 286782
                itemDef.membersObject = true;
                itemDef.maleWearId = 56287;
                itemDef.femaleWearId = 56287;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.stackable = false;
               break;

            case 3651:
                itemDef.modelID = 96095;
                itemDef.name = "Sephiroth helm";
                itemDef.description = "Sephiroth helm";
                itemDef.modelZoom = 1000;
                itemDef.modelRotationY = 100;
                itemDef.modelRotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -3;
                itemDef.maleWearId = 96096;
                itemDef.femaleWearId = 96096;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
            case 3652:
                itemDef.modelID = 96097;
                itemDef.name = "Sephiroth Body";
                itemDef.description = "Sephiroth Body";
                itemDef.modelZoom = 1506;
                itemDef.modelRotationY = 473;
                itemDef.modelRotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleWearId = 96098;
                itemDef.femaleWearId = 96098;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;

            case 3653:
                itemDef.modelID = 96099;
                itemDef.name = "Sephiroth legs";
                itemDef.description = "Sephiroth legs";
                itemDef.modelZoom = 1740;
                itemDef.modelRotationY = 474;
                itemDef.modelRotationX = 2045;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -5;
                itemDef.maleWearId = 96100;
                itemDef.femaleWearId = 96100;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;

            case 20511:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.name = "Magma protector";
                itemDef.description = "An item imbued with infernal essence.";
                itemDef.modelID = 95042;
                itemDef.modelZoom = 1560;
                itemDef.modelRotationY = 344;
                itemDef.modelRotationX = 1104;
                itemDef.modelOffsetX = 0;
                itemDef.modelOffset1 = -6;
                itemDef.modelOffsetY = -14;
                itemDef.maleWearId = 95043;
                itemDef.femaleWearId = 95043;
                itemDef.groundActions = new String[] { null, null, "Take", null, null };
                break;
            case 3654:
                itemDef.modelID = 96103;
                itemDef.name = "Sephiroth boots";
                itemDef.description = "Sephiroth boots";
                itemDef.modelZoom = 825;
                itemDef.modelOffset1 = -4;
                itemDef.modelOffsetY = 0;
                itemDef.modelRotationY = 400;
                itemDef.maleWearId = 96103;
                itemDef.femaleWearId = 96103;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.stackable = false;
                break;

            case 3655:
                itemDef.modelID = 96101;
                itemDef.name = "Sephiroth gloves";
                itemDef.description = "Sephiroth gloves";
                itemDef.modelZoom = 648;
                itemDef.modelRotationY = 618;
                itemDef.modelRotationX = 1143;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -5;
                itemDef.maleWearId = 96102;
                itemDef.femaleWearId = 96102;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;

            case 3656:
                itemDef.modelID = 54093;
                itemDef.name = "Draconian hood";
                itemDef.description = "A custom item.";
                itemDef.modelZoom = 1025;
                itemDef.modelRotationY = 215;
                itemDef.modelRotationX = 162;
                itemDef.modelOffset1 = 3;
                itemDef.modelOffsetY = -43;
                itemDef.colourRedefine2 = 62111;//298782
                //rapier : sky : 62111,
                //itemDef.aByte154 = -14;
                //cool primal color = 277782, 286782
                itemDef.membersObject = true;
                itemDef.maleWearId = 55746;
                itemDef.femaleWearId = 56453;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.stackable = false;
                break;
            case 3657:
                itemDef.modelID = 54097;
                itemDef.name = "Draconian robe top";
                itemDef.description = "A custom item.";
                itemDef.modelZoom = 1447;
                itemDef.modelRotationY = 485;
                itemDef.modelRotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 13;
                itemDef.colourRedefine2 = 62111;
                //rapier : sky : 62111,
                //itemDef.aByte154 = -14;
                //cool primal color = 277782, 286782
                itemDef.membersObject = true;
                itemDef.maleWearId = 55659;
                itemDef.femaleWearId = 56327;
                itemDef.femaleEquip2 = 56530;
                itemDef.maleEquip2 = 55893;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.stackable = false;
                break;
            case 3658:
                itemDef.modelID = 54009;
                itemDef.name = "Draconian robe bottom";
                itemDef.description = "A custom item.";
                itemDef.modelZoom = 1974;
                itemDef.modelRotationY = 377;
                itemDef.modelRotationX = 0;
                itemDef.modelOffset1 = 3;
                itemDef.modelOffsetY = 16;
                itemDef.colourRedefine2 = 62111;
                //rapier : sky : 62111,
                //itemDef.aByte154 = -14;
                //cool primal color = 277782, 286782
                itemDef.membersObject = true;
                itemDef.maleWearId = 55785;
                itemDef.femaleWearId = 56466;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.stackable = false;
                break;
            case 3659:
                itemDef.modelID = 54177;
                itemDef.name = "Draconian shoes";
                itemDef.description = "A custom item.";
                itemDef.modelZoom = 770;
                itemDef.modelRotationY = 189;
                itemDef.modelRotationX = 1988;
                itemDef.modelOffset1 = -1;
                itemDef.modelOffsetY = -22;
                itemDef.colourRedefine2 = 62111;
                //rapier : sky : 62111,
                //itemDef.aByte154 = -14;
                //cool primal color = 277782, 286782
                itemDef.membersObject = true;
                itemDef.maleWearId = 55681;
                itemDef.femaleWearId = 56367;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.stackable = false;
                break;
            case 3660:
                itemDef.modelID = 54121;
                itemDef.name = "Draconian mage gloves";
                itemDef.description = "A custom item.";
                itemDef.modelZoom = 830;
                itemDef.modelRotationY = 536;
                itemDef.modelRotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 3;
                itemDef.colourRedefine2 = 62111;
                //rapier : sky : 62111,
                //itemDef.aByte154 = -14;
                //cool primal color = 277782, 286782
                itemDef.membersObject = true;
                itemDef.maleWearId = 55740;
                itemDef.femaleWearId = 56408;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.stackable = false;
                break;
            case 3661:
                itemDef.modelID = 54391;
                itemDef.name = "Draconian staff";
                itemDef.description = "A custom item.";
                itemDef.modelZoom = 1650;
                itemDef.modelRotationY = 391;
                itemDef.modelRotationX = 391;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -5;
                itemDef.colourRedefine2 = 62111;
                //rapier : sky : 62111,
                //itemDef.aByte154 = -14;
                //cool primal color = 277782, 286782
                itemDef.membersObject = true;
                itemDef.maleWearId = 55909;
                itemDef.femaleWearId = 55909;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.stackable = false;
                break;

            case 3625:
                itemDef.modelID = 54425;
                itemDef.name = "Kryptic bow";
                itemDef.description = "A bow from the deepest dungeon";
                itemDef.modelZoom = 1447;
                itemDef.modelRotationY = 296;
                itemDef.modelRotationX = 377;
                itemDef.colourRedefine2 = 158211;
                itemDef.modelOffset1 = 5;
                itemDef.modelOffsetY = -33;
                itemDef.membersObject = true;
                itemDef.maleWearId = 56287;
                itemDef.femaleWearId = 56287;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.stackable = false;
                break;
            
            case 3626:
                itemDef.modelID = 57653;
                itemDef.name = "Prestigious full helm";
                itemDef.description = "A custom item.";
                itemDef.modelZoom = 640;
                itemDef.modelRotationY = 108;
                itemDef.modelRotationX = 156;
                itemDef.colourRedefine2 = 298782;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -1;
                itemDef.membersObject = true;
                itemDef.maleWearId = 4425;
                itemDef.femaleWearId = 28930;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.stackable = false;
                break;

            case 3627:
                itemDef.modelID = 57622;
                itemDef.name = "Prestigious platebody";
                itemDef.description = "A custom item.";
                itemDef.modelZoom = 1378;
                itemDef.modelRotationY = 527;
                itemDef.modelRotationX = 0;
                itemDef.colourRedefine2 = 298782;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -1;
                itemDef.membersObject = true;
                itemDef.maleWearId = 4435;
                itemDef.femaleWearId = 38208;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.stackable = false;
                break;

            case 3628:
                itemDef.modelID = 57633;
                itemDef.name = "Prestigious platelegs";
                itemDef.description = "A custom item.";
                itemDef.modelZoom = 1740;
                itemDef.modelRotationY = 516;
                itemDef.modelRotationX = 0;
                itemDef.colourRedefine2 = 298782;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.membersObject = true;
                itemDef.maleWearId = 4428;
                itemDef.femaleWearId = 38192;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.stackable = false;
                break;

            case 3629:
                itemDef.modelID = 57625;
                itemDef.name = "Prestigious kiteshield";
                itemDef.description = "A custom item.";
                itemDef.modelZoom = 1432;
                itemDef.modelRotationY = 281;
                itemDef.modelRotationX = 1959;
                itemDef.colourRedefine2 = 298782;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -1;
                itemDef.membersObject = true;
                itemDef.maleWearId = 57673;
                itemDef.femaleWearId = 57673;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.stackable = false;
                break;

            case 3631:
                itemDef.modelID = 57638;
                itemDef.name = "Prestigious cape";
                itemDef.description = "A custom item.";
                itemDef.modelZoom = 2012;
                itemDef.modelRotationY = 307;
                itemDef.modelRotationX = 978;
                itemDef.colourRedefine2 = 298782;
                itemDef.modelOffset1 = -1;
                itemDef.modelOffsetY = 0;
                itemDef.membersObject = true;
                itemDef.maleWearId = 4437;
                itemDef.femaleWearId = 38259;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.stackable = false;
                break;

            case 3632:
                itemDef.modelID = 57632;
                itemDef.name = "Prestigious cape";
                itemDef.description = "A custom item.";
                itemDef.modelZoom = 2012;
                itemDef.modelRotationY = 307;
                itemDef.modelRotationX = 978;
                itemDef.colourRedefine2 = 298782;
                itemDef.modelOffset1 = -1;
                itemDef.modelOffsetY = 0;
                itemDef.membersObject = true;
                itemDef.maleWearId = 4441;
                itemDef.femaleWearId = 38256;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.stackable = false;
                break;

            case 3633:
                itemDef.modelID = 16015;
                itemDef.name = "Prestigious boots";
                itemDef.description = "A custom item.";
                itemDef.modelZoom = 784;
                itemDef.modelRotationY = 147;
                itemDef.modelRotationX = 1841;
                itemDef.colourRedefine2 = 298782;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -4;
                itemDef.membersObject = true;
                itemDef.maleWearId = 16010;
                itemDef.femaleWearId = 16012;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.stackable = false;
                break;

            case 3630:
                itemDef.modelID = 33165;
                itemDef.name = "Prestigious gloves";
                itemDef.description = "A custom item.";
                itemDef.modelZoom = 930;
                itemDef.modelRotationY = 420;
                itemDef.modelRotationX = 828;
                itemDef.colourRedefine2 = 298782;
                itemDef.modelOffset1 = -7;
                itemDef.modelOffsetY = 1;
                itemDef.membersObject = true;
                itemDef.maleWearId = 33207;
                itemDef.femaleWearId = 33259;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.stackable = false;
                break;

		/*case 7809:
			itemDef.name = "Prestigious spear";
			itemDef.modifiedModelColors = new int[7];
    		itemDef.originalModelColors = new int[7];
    		itemDef.modifiedModelColors[0] = 920; // Signe
    		itemDef.originalModelColors[0] = 1;
			break;

		case 7808:
			itemDef.name = "Prestigious mace";
			itemDef.modifiedModelColors = new int[7];
    		itemDef.originalModelColors = new int[7];
    		itemDef.modifiedModelColors[0] = 920; // Signe
    		itemDef.originalModelColors[0] = 1;
			break;
		case 7807:
			itemDef.name = "Prestigious battleaxe";
			itemDef.modifiedModelColors = new int[7];
    		itemDef.originalModelColors = new int[7];
    		itemDef.modifiedModelColors[0] = 920; // Signe
    		itemDef.originalModelColors[0] = 1;
			break;
		case 7806:
			itemDef.name = "Prestigious sword";
    		itemDef.modifiedModelColors = new int[7];
    		itemDef.originalModelColors = new int[7];
    		itemDef.modifiedModelColors[0] = 920; // Signe
    		itemDef.originalModelColors[0] = 1;
			break;*/


            case 3634:
                itemDef.modelID = 4934;
                itemDef.name = "Prestigious amulet";
                itemDef.description = "A custom item.";
                itemDef.modelZoom = 440;
                itemDef.modelRotationY = 172;
                itemDef.modelRotationX = 72;
                itemDef.colourRedefine2 = 298782;
                itemDef.modelOffset1 = 2;
                itemDef.modelOffsetY = -18;
                itemDef.membersObject = true;
                itemDef.maleWearId = 20858;
                itemDef.femaleWearId = 20864;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.stackable = false;
                break;
            case 3682:
                itemDef.modelID = 90999;
                itemDef.name = "Lava necklace";
                itemDef.description = "A necklace imbued with lava.";
                itemDef.modelZoom = 440;
                itemDef.modelRotationY = 172;
                itemDef.modelRotationX = 72;
                itemDef.modelOffset1 = 2;
                itemDef.modelOffsetY = -18;
                itemDef.membersObject = true;
                itemDef.maleWearId = 90998;
                itemDef.femaleWearId = 90998;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.stackable = false;
                break;
            case 20251:
                itemDef.modelID = 90999;
                itemDef.name = "BOMBY'S necklace";
                itemDef.description = "A BOMBY'S imbued with lava.";
                itemDef.modelZoom = 440;
                itemDef.modelRotationY = 172;
                itemDef.modelRotationX = 72;
                itemDef.modelOffset1 = 2;
                itemDef.modelOffsetY = -18;
                itemDef.membersObject = true;
                itemDef.maleWearId = 90998;
                itemDef.femaleWearId = 90998;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.stackable = false;
                itemDef.editedModelColor = new int[] {40,31};
                itemDef.newModelColor = new int[]    {57,57};
                break;
            case 20252:
                itemDef.modelID = 90999;
                itemDef.name = "Alien necklace";
                itemDef.description = "A necklace imbued with lava.";
                itemDef.modelZoom = 440;
                itemDef.modelRotationY = 172;
                itemDef.modelRotationX = 72;
                itemDef.modelOffset1 = 2;
                itemDef.modelOffsetY = -18;
                itemDef.membersObject = true;
                itemDef.maleWearId = 90998;
                itemDef.femaleWearId = 90998;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.stackable = false;
                itemDef.editedModelColor = new int[] {40,31};
                itemDef.newModelColor = new int[]    {55,55};
                break;
            case 20253:
                itemDef.modelID = 90999;
                itemDef.name = "bowser necklace";
                itemDef.description = "A necklace imbued with lava.";
                itemDef.modelZoom = 440;
                itemDef.modelRotationY = 172;
                itemDef.modelRotationX = 72;
                itemDef.modelOffset1 = 2;
                itemDef.modelOffsetY = -18;
                itemDef.membersObject = true;
                itemDef.maleWearId = 90998;
                itemDef.femaleWearId = 90998;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.stackable = false;
                itemDef.editedModelColor = new int[] {40,31};
                itemDef.newModelColor = new int[]    {66,66};
                break;
            case 20254:
                itemDef.modelID = 90999;
                itemDef.name = "Mewtwo necklace";
                itemDef.description = "A necklace imbued with lava.";
                itemDef.modelZoom = 440;
                itemDef.modelRotationY = 172;
                itemDef.modelRotationX = 72;
                itemDef.modelOffset1 = 2;
                itemDef.modelOffsetY = -18;
                itemDef.membersObject = true;
                itemDef.maleWearId = 90998;
                itemDef.femaleWearId = 90998;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.stackable = false;
                itemDef.editedModelColor = new int[] {40,31};
                itemDef.newModelColor = new int[]    {66,66};
                break;
            case 20255:
                itemDef.modelID = 90999;
                itemDef.name = "Oblivion necklace";
                itemDef.description = "A necklace imbued with lava.";
                itemDef.modelZoom = 440;
                itemDef.modelRotationY = 172;
                itemDef.modelRotationX = 72;
                itemDef.modelOffset1 = 2;
                itemDef.modelOffsetY = -18;
                itemDef.membersObject = true;
                itemDef.maleWearId = 90998;
                itemDef.femaleWearId = 90998;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.stackable = false;
                itemDef.editedModelColor = new int[] {40,31};
                itemDef.newModelColor = new int[]    {89,89};
                break;
            case 20256:
                itemDef.modelID = 90999;
                itemDef.name = "Sonic necklace";
                itemDef.description = "A necklace imbued with lava.";
                itemDef.modelZoom = 440;
                itemDef.modelRotationY = 172;
                itemDef.modelRotationX = 72;
                itemDef.modelOffset1 = 2;
                itemDef.modelOffsetY = -18;
                itemDef.membersObject = true;
                itemDef.maleWearId = 90998;
                itemDef.femaleWearId = 90998;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.stackable = false;
                itemDef.editedModelColor = new int[] {40,31};
                itemDef.newModelColor = new int[]    {98,98};
                break;
            case 20257:
                itemDef.modelID = 90999;
                itemDef.name = "Blood necklace";
                itemDef.description = "A necklace imbued with lava.";
                itemDef.modelZoom = 440;
                itemDef.modelRotationY = 172;
                itemDef.modelRotationX = 72;
                itemDef.modelOffset1 = 2;
                itemDef.modelOffsetY = -18;
                itemDef.membersObject = true;
                itemDef.maleWearId = 90998;
                itemDef.femaleWearId = 90998;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.stackable = false;
                itemDef.editedModelColor = new int[] {40,31};
                itemDef.newModelColor = new int[]    {52,52};
                break;
            case 20258:
                itemDef.modelID = 90999;
                itemDef.name = "Infernal necklace";
                itemDef.description = "A necklace imbued with lava.";
                itemDef.modelZoom = 440;
                itemDef.modelRotationY = 172;
                itemDef.modelRotationX = 72;
                itemDef.modelOffset1 = 2;
                itemDef.modelOffsetY = -18;
                itemDef.membersObject = true;
                itemDef.maleWearId = 90998;
                itemDef.femaleWearId = 90998;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.stackable = false;
                itemDef.editedModelColor = new int[] {40,31};
                itemDef.newModelColor = new int[]    {60,60};
                break;
            /** OSRS && NEW ITEMS **/
            case 18880:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.modelID = 90558;
                itemDef.name = "Ancestral hat";
                itemDef.modelZoom = 1236;
                itemDef.modelRotationY = 118;
                itemDef.modelRotationX = 10;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -12;
                itemDef.femaleWearId = 90559;
                itemDef.maleWearId = 90559;
                itemDef.description = "The hat of a powerful sorceress from a bygone era.";
                break;
            case 18881:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.modelID = 90561;
                itemDef.name = "Ancestral robe top";
                itemDef.modelZoom = 1358;
                itemDef.modelRotationY = 514;
                itemDef.modelRotationX = 2041;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -3;
                itemDef.femaleWearId = 90562;
                itemDef.maleWearId = 90563;
                itemDef.maleEquip2 = 90564; // male arms
                itemDef.femaleEquip2 = 90565; // female arms
                itemDef.description = "The robe top of a powerful sorceress from a bygone era.";
                break;
            case 18882:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.modelID = 90566;
                itemDef.name = "Ancestral robe bottom";
                itemDef.modelZoom = 1690;
                itemDef.modelRotationY = 435;
                itemDef.modelRotationX = 9;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = 7;
                itemDef.femaleWearId = 90567;
                itemDef.maleWearId = 90568;
                itemDef.description = "The robe bottom of a powerful sorceress from a bygone era.";
                break;
            case 20061:
                itemDef.modelID = 10247;
                itemDef.name = "Abyssal vine whip";
                itemDef.description = "Abyssal vine whip";
                itemDef.modelZoom = 848;
                itemDef.modelRotationY = 324;
                itemDef.modelRotationX = 1808;
                itemDef.modelOffset1 = 5;
                itemDef.modelOffsetY = 38;
                itemDef.maleWearId = 10253;
                itemDef.femaleWearId = 10253;
                itemDef.groundActions = new String[] { null, null, "Take", null, null };
                itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
                break;
            case 20010:
                itemDef.modelID = 80342;
                itemDef.name = "Shadow Dragonslayer platebody";
                itemDef.description = "It's a camouflage torva platebody.";
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = -8;
                itemDef.modelZoom = 1513;
                itemDef.modelRotationY = 566;
                itemDef.modelRotationX = 0;
                itemDef.maleWearId = 80343;
                itemDef.femaleWearId = 80343;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 20011:
                itemDef.modelID = 80344;
                itemDef.name = "Shadow Dragonslayer platelegs";
                itemDef.description = "It's a set of camouflage torva platelegs.";
                itemDef.modelZoom = 1740;
                itemDef.modelRotationY = 474;
                itemDef.modelRotationX = 2045;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -5;
                itemDef.maleWearId = 80345;
                itemDef.femaleWearId = 80345;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 20012:
                itemDef.modelID = 80340;
                itemDef.name = "Shadow Dragonslayer fullhelm";
                itemDef.description = "It's a camouflage torva fullhelm.";
                itemDef.modelZoom = 676;
                itemDef.modelRotationY = 0;
                itemDef.modelRotationX = 0;
                itemDef.modelOffset1 = 3;
                itemDef.modelOffsetY = -14;
                itemDef.maleWearId = 80341;
                itemDef.femaleWearId = 80341;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
    		case 11581:
    			itemDef.modelID = 62714;
    			itemDef.name = "Sea blue Torva full helm";
    			itemDef.description = "Torva full helm.";
               // itemDef.modelID = 93273;
                itemDef.modelZoom = 672;
                itemDef.modelRotationY = 85;
                itemDef.modelRotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -3;
                itemDef.maleWearId = 62738;
                itemDef.femaleWearId = 62754;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
    			itemDef.colourRedefine2 = 298782;
    			
    			break;

    		case 11582:
    			itemDef.modelID = 62701;
    			itemDef.name = "Sea blue Torva platelegs";
    			itemDef.description = "Torva platelegs.";
                itemDef.modelZoom = 672;
                itemDef.modelRotationY = 85;
                itemDef.modelRotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -3;
    			itemDef.colourRedefine2 = 298782;
    			itemDef.maleWearId = 62743;
    			itemDef.femaleWearId = 62760;
    			itemDef.groundActions = new String[5];
    			itemDef.groundActions[2] = "Take";
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wear";
    			itemDef.actions[2] = "Check-charges";
    			itemDef.actions[4] = "Drop";
    			break;
    		case 11583:
    			itemDef.modelID = 62699;
    			itemDef.name = "Sea blue Torva platebody";
    			itemDef.description = "Torva Platebody.";
                itemDef.modelZoom = 672;
                itemDef.modelRotationY = 85;
                itemDef.modelRotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -3;
    			itemDef.colourRedefine2 = 298782;
    			itemDef.maleWearId = 62746;
    			itemDef.femaleWearId = 62762;
    			itemDef.groundActions = new String[5];
    			itemDef.groundActions[2] = "Take";
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wear";
    			itemDef.actions[2] = "Check-charges";
    			itemDef.actions[4] = "Drop";
    			break;
            case 20013:
                itemDef.modelID = 44633;
                itemDef.name = "Vanguard helm";
                itemDef.modelZoom = 855;
                itemDef.modelRotationY = 1966;
                itemDef.modelRotationX = 5;
                itemDef.modelOffsetY = 4;
                itemDef.modelOffset1 = -1;
                itemDef.groundActions = new String[] { null, null, "Take", null, null };
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.maleWearId = 44769;
                itemDef.femaleWearId = 44769;
                break;
            case 11584://83
 			itemDef.name = "Sea blue Whip";
    			
                itemDef.colourRedefine3 = 298782;
                itemDef2 = ItemDef.forID(83);
                itemDef.modelID = itemDef2.modelID;
                itemDef.maleWearId = itemDef2.maleWearId;
                itemDef.femaleWearId = itemDef2.femaleWearId;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelRotationY = itemDef2.modelRotationY;
                itemDef.modelRotationX = itemDef2.modelRotationX;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.actions = itemDef2.actions;
                itemDef.editedModelColor = new int[1];
                itemDef.originalModelColor = new int[1];
                itemDef.editedModelColor[0] = 8334;
                itemDef.originalModelColor[0] = 298782;
            	break;
    		case 11592:
    			itemDef.name = "Sea blue Torva gloves";
    			
                itemDef.colourRedefine2 = 298782;
                itemDef2 = ItemDef.forID(21011);
                itemDef.modelID = itemDef2.modelID;
                itemDef.maleWearId = itemDef2.maleWearId;
                itemDef.femaleWearId = itemDef2.femaleWearId;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelRotationY = itemDef2.modelRotationY;
                itemDef.modelRotationX = itemDef2.modelRotationX;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.actions = itemDef2.actions;
    			break;
    		case 11593: //
    			itemDef.name = "Sea blue Torva boots";
                itemDef.colourRedefine2 = 298782;
                itemDef2 = ItemDef.forID(21012);
                itemDef.modelID = itemDef2.modelID;
                itemDef.maleWearId = itemDef2.maleWearId;
                itemDef.femaleWearId = itemDef2.femaleWearId;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelZoom = 825;
                itemDef.modelOffset1 = -4;
                itemDef.modelOffsetY = 0;
                itemDef.modelRotationY = 400;
                itemDef.actions = itemDef2.actions;
    			break;
            case 20014:
                itemDef.modelID = 44627;
                itemDef.name = "Vanguard body";
                itemDef.modelZoom = 1513;
                itemDef.modelRotationX = 2041;
                itemDef.modelRotationY = 593;
                itemDef.modelOffset1 = 3;
                itemDef.modelOffsetY = -11;
                itemDef.groundActions = new String[] { null, null, "Take", null, null };
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.maleWearId = 44812;
                itemDef.femaleWearId = 44812;
                break;
            case 14062:
                itemDef.modelID = 50011;
                itemDef.name = "Vanguard legs";
                itemDef.modelZoom = 1711;
                itemDef.modelRotationX = 0;
                itemDef.modelRotationY = 360;
                itemDef.modelOffset1 = 3;
                itemDef.modelOffsetY = -11;
                itemDef.groundActions = new String[] { null, null, "Take", null, null };
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.maleWearId = 50010;
                itemDef.femaleWearId = 44771;
                break;
            case 20016:
                itemDef.modelID = 44704;
                itemDef.name = "Battle-mage helm";
                itemDef.modelZoom = 658;
                itemDef.modelRotationX = 1898;
                itemDef.modelRotationY = 2;
                itemDef.modelOffset1 = 12;
                itemDef.modelOffsetY = 3;
                itemDef.groundActions = new String[] { null, null, "Take", null, null };
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.maleWearId = 44767;
                itemDef.femaleWearId = 44767;
                break;
            case 20017:
                itemDef.modelID = 44631;
                itemDef.name = "Battle-mage robe";
                itemDef.modelZoom = 1382;
                itemDef.modelRotationX = 3;
                itemDef.modelRotationY = 488;
                itemDef.modelOffset1 = 1;
                itemDef.modelOffsetY = 0;
                itemDef.groundActions = new String[] { null, null, "Take", null, null };
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.maleWearId = 44818;
                itemDef.femaleWearId = 44818;
                break;
            case 6307:
                ItemDef itemDef23 = ItemDef.forID(299);
                itemDef.modelID = itemDef23.modelID;
                itemDef.name = "Rg Seeds";
                itemDef.modelZoom = itemDef23.modelZoom;
                itemDef.modelRotationX = itemDef23.modelRotationX;
                itemDef.modelRotationY = itemDef23.modelRotationY;
                itemDef.modelOffset1 = itemDef23.modelOffset1;
                itemDef.modelOffsetY = itemDef23.modelOffsetY;
                itemDef.groundActions = new String[] { null, null, "Take", null, null };
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Plant";
                break;
            case 20018:
                itemDef.modelID = 44672;
                itemDef.name = "Battle-mage robe legs";
                itemDef.modelZoom = 1842;
                itemDef.modelRotationX = 1024;
                itemDef.modelRotationY = 498;
                itemDef.modelOffset1 = 4;
                itemDef.modelOffsetY = -1;
                itemDef.groundActions = new String[] { null, null, "Take", null, null };
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.maleWearId = 44775;
                itemDef.femaleWearId = 44775;
                break;
            // case 1685:
            //  itemDef.modelID = 83126;
            //  itemDef.name = "Dragon boots [1]";
            //  itemDef.modelZoom = 848;
            //   itemDef.rotationY = 141;
            //    itemDef.rotationX = 141;
            //     itemDef.modelOffset1 = -9;
            //     itemDef.modelOffsetY = 0;
            //      itemDef.groundActions = new String[] { null, null, "Take", null, null };
            //     itemDef.actions = new String[5];
            //      itemDef.actions[1] = "Wear";
            //      itemDef.maleEquip1 = 83126;
            //      itemDef.femaleEquip1 = 83126;
            //      break;
            case 12861:
                itemDef.name = "Dire Wolf gloves";
                break;
            case 20073:
                itemDef.modelID = 72078;
                itemDef.name = "Zamorakian scimitar";
                itemDef.description = "Imbued with the Demon himself";
                itemDef.modelZoom = 1488;
                itemDef.modelRotationY = 525;
                itemDef.modelRotationX = 1075;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.membersObject = true;
                itemDef.maleWearId = 72077;
                itemDef.femaleWearId = 70077;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                break;
            case 20019:
                itemDef.name = "Shadow Dragonslayer boots";
                itemDef.description = "It's a pair of camouflage boots.";
                itemDef.modelID = 51;
                itemDef.modelZoom = 855;
                itemDef.modelRotationY = 215;
                itemDef.modelRotationX = 94;
                itemDef.modelOffset1 = 4;
                itemDef.modelOffsetY = -32;
                itemDef.maleWearId = 52;
                itemDef.femaleWearId = 52;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.description = "Some boots.";
                break;
            case 20020:
                itemDef.name = "Shadow Dragonslayer gloves";
                itemDef.description = "It's a pair of camouflage gloves.";
                itemDef.modelZoom = 592;
                itemDef.modelRotationY = 323;
                itemDef.modelRotationX = 1710;
                itemDef.modelOffset1 = 3;
                itemDef.modelOffsetY = 5;
                itemDef.modelID = 49;
                itemDef.maleWearId = 50;
                itemDef.femaleWearId = 50;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 20021:
                itemDef.modelID = 44662;
                itemDef.name = "Battle-mage boots";
                itemDef.modelZoom = 987;
                itemDef.modelRotationX = 1988;
                itemDef.modelRotationY = 188;
                itemDef.modelOffset1 = -8;
                itemDef.modelOffsetY = 5;
                itemDef.groundActions = new String[] { null, null, "Take", null, null };
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.maleWearId = 44755;
                itemDef.femaleWearId = 44755;
                break;
            case 20022:
                itemDef.modelID = 44573;
                itemDef.name = "Battle-mage gloves";
                itemDef.modelZoom = 1053;
                itemDef.modelRotationX = 0;
                itemDef.modelRotationY = 536;
                itemDef.modelOffset1 = 3;
                itemDef.modelOffsetY = 0;
                itemDef.groundActions = new String[] { null, null, "Take", null, null };
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.maleWearId = 44762;
                itemDef.femaleWearId = 44762;
                break;
            case 11554:
                itemDef.name = "Abyssal tentacle";
                itemDef.modelZoom = 840;
                itemDef.modelRotationY = 280;
                itemDef.modelRotationX = 121;
                itemDef.modelOffsetY = 56;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.modelID = 28439;
                itemDef.maleWearId = 45006;
                itemDef.femaleWearId = 43500;
                break;
            case 11926:
                itemDef.name = "Odium ward";
                itemDef.modelZoom = 1200;
                itemDef.modelRotationY = 568;
                itemDef.modelRotationX = 1836;
                itemDef.modelOffsetX = 2;
                itemDef.modelOffsetY = 3;
                itemDef.newModelColor = new int[] { 15252 };
                itemDef.editedModelColor = new int[] { 908 };
                itemDef.modelID = 9354;
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.maleWearId = 9347;
                itemDef.femaleWearId = 9347;
                break;
            case 20690:
                ItemDef dfs = ItemDef.forID(11283);
                itemDef.name = "Water DragonFire Shield";
                itemDef.modelZoom = dfs.modelZoom;
                itemDef.modelRotationY = dfs.modelRotationY;
                itemDef.modelRotationX = dfs.modelRotationX;
                itemDef.modelOffsetX = dfs.modelOffsetX;
                itemDef.modelOffsetY = dfs.modelOffsetY;
                itemDef.modelID = dfs.modelID;
                itemDef.maleWearId = dfs.maleWearId;
                itemDef.femaleWearId = dfs.femaleWearId;
                itemDef.colourRedefine2 = 34543;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[2] = "Check";
                break;
            case 20691:
                 dfs = ItemDef.forID(11283);
                itemDef.name = "Grass DragonFire Shield";
                itemDef.modelZoom = dfs.modelZoom;
                itemDef.modelRotationY = dfs.modelRotationY;
                itemDef.modelRotationX = dfs.modelRotationX;
                itemDef.modelOffsetX = dfs.modelOffsetX;
                itemDef.modelOffsetY = dfs.modelOffsetY;
                itemDef.modelID = dfs.modelID;
                itemDef.maleWearId = dfs.maleWearId;
                itemDef.femaleWearId = dfs.femaleWearId;
                itemDef.colourRedefine2 = 14500;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[2] = "Check";
                break;
                case 20693:
                 dfs = ItemDef.forID(11283);
                itemDef.name = "Dark DragonFire Shield";
                itemDef.modelZoom = dfs.modelZoom;
                itemDef.modelRotationY = dfs.modelRotationY;
                itemDef.modelRotationX = dfs.modelRotationX;
                itemDef.modelOffsetX = dfs.modelOffsetX;
                itemDef.modelOffsetY = dfs.modelOffsetY;
                itemDef.modelID = dfs.modelID;
                itemDef.maleWearId = dfs.maleWearId;
                itemDef.femaleWearId = dfs.femaleWearId;
                itemDef.colourRedefine2 = 907;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[2] = "Check";
                break;

                case 20694:
                 dfs = ItemDef.forID(11283);
                itemDef.name = "Earth DragonFire Shield";
                itemDef.modelZoom = dfs.modelZoom;
                itemDef.modelRotationY = dfs.modelRotationY;
                itemDef.modelRotationX = dfs.modelRotationX;
                itemDef.modelOffsetX = dfs.modelOffsetX;
                itemDef.modelOffsetY = dfs.modelOffsetY;
                itemDef.modelID = dfs.modelID;
                itemDef.maleWearId = dfs.maleWearId;
                itemDef.femaleWearId = dfs.femaleWearId;
                itemDef.colourRedefine2 = 74800;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[2] = "Check";
                break;

            case 20675:
                 dfs = ItemDef.forID(11283);
                itemDef.name = "Light DragonFire Shield";
                itemDef.modelZoom = dfs.modelZoom;
                itemDef.modelRotationY = dfs.modelRotationY;
                itemDef.modelRotationX = dfs.modelRotationX;
                itemDef.modelOffsetX = dfs.modelOffsetX;
                itemDef.modelOffsetY = dfs.modelOffsetY;
                itemDef.modelID = dfs.modelID;
                itemDef.maleWearId = dfs.maleWearId;
                itemDef.femaleWearId = dfs.femaleWearId;
                itemDef.colourRedefine2 = 74300;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[2] = "Check";
                break;
                case 20696:
                 dfs = ItemDef.forID(11283);
                itemDef.name = "Mythical DragonFire Shield";
                itemDef.modelZoom = dfs.modelZoom;
                itemDef.modelRotationY = dfs.modelRotationY;
                itemDef.modelRotationX = dfs.modelRotationX;
                itemDef.modelOffsetX = dfs.modelOffsetX;
                itemDef.modelOffsetY = dfs.modelOffsetY;
                itemDef.modelID = dfs.modelID;
                itemDef.maleWearId = dfs.maleWearId;
                itemDef.femaleWearId = dfs.femaleWearId;
                itemDef.colourRedefine2 = 4200;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[2] = "Check";
                break;

                case 20390:
                 dfs = ItemDef.forID(1540);
                itemDef.name = "Water Dragon-anti Shield";
                itemDef.modelZoom = dfs.modelZoom;
                itemDef.modelRotationY = dfs.modelRotationY;
                itemDef.modelRotationX = dfs.modelRotationX;
                itemDef.modelOffsetX = dfs.modelOffsetX;
                itemDef.modelOffsetY = dfs.modelOffsetY;
                itemDef.modelID = dfs.modelID;
                itemDef.maleWearId = dfs.maleWearId;
                itemDef.femaleWearId = dfs.femaleWearId;
                itemDef.colourRedefine2 = 34543;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[2] = "Check";
                break;
            case 20391:
                 dfs = ItemDef.forID(1540);
                itemDef.name = "Grass Dragon-anti Shield";
                itemDef.modelZoom = dfs.modelZoom;
                itemDef.modelRotationY = dfs.modelRotationY;
                itemDef.modelRotationX = dfs.modelRotationX;
                itemDef.modelOffsetX = dfs.modelOffsetX;
                itemDef.modelOffsetY = dfs.modelOffsetY;
                itemDef.modelID = dfs.modelID;
                itemDef.maleWearId = dfs.maleWearId;
                itemDef.femaleWearId = dfs.femaleWearId;
                itemDef.colourRedefine2 = 14500;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[2] = "Check";
                break;

            case 11288:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 926;
                itemDef.newModelColor[0] = 196608;
                itemDef.modelID = 2438;
                itemDef.modelZoom = 730;
                itemDef.modelRotationY = 516;
                itemDef.modelRotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -10;
                itemDef.maleWearId = 3188;
                itemDef.femaleWearId = 3192;
                itemDef.name = "Black h'ween Mask";
                itemDef.description = "Aaaarrrghhh... I'm a monster.";
                break;
            case 11924:
                itemDef.name = "Malediction ward";
                itemDef.modelZoom = 1200;
                itemDef.modelRotationY = 568;
                itemDef.modelRotationX = 1836;
                itemDef.modelOffsetX = 2;
                itemDef.modelOffsetY = 3;
                itemDef.newModelColor = new int[] { -21608 };
                itemDef.editedModelColor = new int[] { 908 };
                itemDef.modelID = 9354;
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.maleWearId = 9347;
                itemDef.femaleWearId = 9347;
                break;
            case 12282:
                itemDef.name = "Serpentine helm";
                itemDef.modelID = 19220;
                itemDef.modelZoom = 700;
                itemDef.modelRotationX = 1724;
                itemDef.modelRotationY = 215;
                itemDef.modelOffsetX = 17;
                itemDef.femaleWearId = 14398;
                itemDef.maleWearId = 14395;
                itemDef.groundActions = new String[] { null, null, "Take", null, null };
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 12279:
                itemDef.name = "Magma helm";
                itemDef.modelID = 29205;
                itemDef.modelZoom = 700;
                itemDef.modelRotationX = 1724;
                itemDef.modelRotationY = 215;
                itemDef.modelOffsetX = 17;
                itemDef.groundActions = new String[] { null, null, "Take", null, null };
                itemDef.femaleWearId = 14426;
                itemDef.maleWearId = 14424;
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;

            case 20074:
                itemDef.name = "infernal mg";
                itemDef.modelID = 95031;
                itemDef.modelZoom = 700;
                itemDef.modelRotationX = 1724;
                itemDef.modelRotationY = 215;
                itemDef.modelOffsetX = 17;
                itemDef.groundActions = new String[] { null, null, "Take", null, null };
                itemDef.femaleWearId = 95032;
                itemDef.maleWearId = 95032;
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 12278:
                itemDef.name = "Tanzanite helm";
                itemDef.modelID = 29213;
                itemDef.modelZoom = 700;
                itemDef.modelRotationX = 1724;
                itemDef.modelRotationY = 215;
                itemDef.modelOffsetX = 17;
                itemDef.groundActions = new String[] { null, null, "Take", null, null };
                itemDef.femaleWearId = 23994;
                itemDef.maleWearId = 14421;
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 13239:
                itemDef.name = "Primordial boots";
                itemDef.modelID = 29397;
                itemDef.modelZoom = 976;
                itemDef.modelRotationY = 147;
                itemDef.modelRotationX = 279;
                itemDef.modelOffsetX = 5;
                itemDef.modelOffsetY = 5;
                itemDef.maleWearId = 29250;
                itemDef.femaleWearId = 29255;
                itemDef.groundActions = new String[] { null, null, "Take", null, null };
                itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
                break;
            case 12708:
                itemDef.name = "Pegasian boots";
                itemDef.modelID = 29396;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.groundActions = new String[5];
                itemDef.groundActions[1] = "Take";
                itemDef.modelZoom = 900;
                itemDef.modelRotationY = 165;
                itemDef.modelRotationX = 279;
                itemDef.modelOffsetX = 3;
                itemDef.modelOffsetY =-7;
                itemDef.maleWearId = 29252;
                itemDef.femaleWearId = 29253;
                break;
            case 16137:
                itemDef.name = "Thok's Sword";
                break;
            case 1543:
                itemDef.name = "Boss Key";
                break;
            case 6821:
                itemDef.name = "Eternal Orb";
                break;
            case 5001:
                itemDef.name = "Cosmetic Aura";
                itemDef.actions[1] = "Wear";
                itemDef.modelID = 1;
                itemDef.modelZoom = 3000;
                itemDef.modelRotationY = 130;
                itemDef.modelRotationX = 252;
                itemDef.modelOffset1 = -4;
                itemDef.modelOffsetY = 12;
                itemDef.maleWearId = 1;
                itemDef.femaleWearId = 1;
                break;
            case 5002:
                itemDef.name = "Bankai Aura";
                itemDef.actions[1] = "Wear";
                itemDef.modelID = 2;
                itemDef.modelZoom = 2300;
                itemDef.modelRotationY = 165;
                itemDef.modelRotationX = 930;
                itemDef.modelOffset1 = -9;
                itemDef.modelOffsetY = -9;
                itemDef.maleWearId = 2;
                itemDef.femaleWearId = 2;
                break;
            case 5003:
                itemDef.name = "Overlord Aura";
                itemDef.modelID = 3;
                itemDef.actions[1] = "Wear";
                itemDef.modelZoom = 5200;
                itemDef.modelRotationY = 183;
                itemDef.modelRotationX = 461;
                itemDef.modelOffset1 = -4;
                itemDef.modelOffsetY = 12;
                itemDef.maleWearId = 3;
                itemDef.femaleWearId = 3;
                break;

            case 15354:
                itemDef.name = "@red@$1 Bond";
                itemDef.colourRedefine2 = 10000;
                itemDef.description = "A " + itemDef.name + " Claim 1$ with this item. ";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Drop";
                itemDef.actions[0] = "Claim";
                itemDef.modelID = 29210;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelZoom = 2012;
                break;
            case 15355:
                itemDef.name = "@red@$5 Bond";
                itemDef.colourRedefine2 = 10000;
                itemDef.description = "A " + itemDef.name + " Claim 5$ with this item. ";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Drop";
                itemDef.actions[0] = "Claim";
                itemDef.modelID = 29210;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelZoom = 2012;
                break;
            case 15356:
                itemDef.name = "@red@$10 Bond";
                itemDef.colourRedefine2 = 20000;
                itemDef.description = "A " + itemDef.name + " Claim 10$ with this item. ";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Drop";
                itemDef.actions[0] = "Claim";
                itemDef.modelID = 29210;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelZoom = 2012;
                break;
            case 15357:
                itemDef.name = "@red@$25 Bond";
                itemDef.colourRedefine2 = 30000;
                itemDef.description = "A " + itemDef.name + " Claim 25$ with this item. ";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Drop";
                itemDef.actions[0] = "Claim";
                itemDef.modelID = 29210;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelZoom = 2050;
                break;
            case 15358:
                itemDef.name = "@red@$50 Bond";
                itemDef.colourRedefine2 = 30000;
                itemDef.description = "A " + itemDef.name + " Claim 50$ with this item. ";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Drop";
                itemDef.actions[0] = "Claim";
                itemDef.modelID = 29210;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelZoom = 2012;
                break;
            case 15359:
                itemDef.name = "@red@$100 Bond";
                itemDef.colourRedefine2 = 40000;
                itemDef.description = "A " + itemDef.name + " Claim 100$ with this item. ";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Drop";
                itemDef.actions[0] = "Claim";
                itemDef.modelID = 29210;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelZoom = 2012;
                break;
            case 15360:
                itemDef.name = "@red@$1000 Bond";
                itemDef.colourRedefine2 = 50000;
                itemDef.description = "A " + itemDef.name + " Claim 1000$ with this item. ";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Drop";
                itemDef.actions[0] = "Claim";
                itemDef.modelID = 29210;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelZoom = 2012;
                break;
            case 15361:
                itemDef.name = "@red@$2000 Bond";
                itemDef.colourRedefine2 = 60000;
                itemDef.description = "A " + itemDef.name + " Claim 2000$ with this item. ";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Drop";
                itemDef.actions[0] = "Claim";
                itemDef.modelID = 29210;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelZoom = 2012;
                break;
            case 15362:
                itemDef.name = "@red@$3000 Bond";
                itemDef.colourRedefine2 = 70000;
                itemDef.description = "A " + itemDef.name + " Claim 3000$ with this item. ";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Drop";
                itemDef.actions[0] = "Claim";
                itemDef.modelID = 29210;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.modelZoom = 2012;              
                break;
            case 13235:
                itemDef.name = "Eternal boots";
                itemDef.modelID = 29394;
                itemDef.modelZoom = 976;
                itemDef.modelRotationY = 147;
                itemDef.modelRotationX = 279;
                itemDef.modelOffsetX = 5;
                itemDef.modelOffsetY = 5;
                itemDef.maleWearId = 29249;
                itemDef.femaleWearId = 29254;
                itemDef.groundActions = new String[] { null, null, "Take", null, null };
                itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
                break;
            case 7614:
                itemDef.modelID = 95152;
                itemDef.name = "Zilyana shield";
                itemDef.description = "Worn by the mighty Commander of Saradomin.";
                itemDef.modelZoom = 1616;
                itemDef.modelRotationY = 396;
                itemDef.modelRotationX = 1050;
                itemDef.modelOffsetY = -3;
                itemDef.modelOffset1 = 4;
                itemDef.maleWearId = 95153;
                itemDef.femaleWearId = 95153;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Check-charges";
                itemDef.actions[4] = "Drop";
                break;
            case 20059:
                itemDef.name = "Drygore rapier";
                itemDef.modelZoom = 1145;
                itemDef.modelRotationX = 2047;
                itemDef.modelRotationY = 254;
                itemDef.modelOffset1 = -3;
                itemDef.modelOffsetY = -45;
                itemDef.groundActions = new String[] { null, null, "Take", null, null };
                itemDef.actions = new String[] { null, "Wield", "Check-charges", null, "Drop" };
                itemDef.modelID = 14000;
                itemDef.maleWearId = 14001;
                itemDef.femaleWearId = 14001;
                break;
            case 20057:
                itemDef.name = "Drygore longsword";
                itemDef.modelZoom = 1645;
                itemDef.modelRotationX = 377;
                itemDef.modelRotationY = 444;
                itemDef.modelOffset1 = 3;
                itemDef.modelOffsetY = 0;
                itemDef.groundActions = new String[] { null, null, "Take", null, null };
                itemDef.actions = new String[] { null, "Wield", "Check-charges", null, "Drop" };
                itemDef.modelID = 14022;
                itemDef.maleWearId = 14023;
                itemDef.femaleWearId = 14023;
                break;
            case 20058:
                itemDef.name = "Drygore mace";
                itemDef.modelZoom = 1118;
                itemDef.modelRotationX = 28;
                itemDef.modelRotationY = 235;
                itemDef.modelOffset1 = -1;
                itemDef.modelOffsetY = -47;
                itemDef.groundActions = new String[] { null, null, "Take", null, null };
                itemDef.actions = new String[] { null, "Wield", "Check-charges", null, "Drop" };
                itemDef.modelID = 14024;
                itemDef.maleWearId = 14025;
                itemDef.femaleWearId = 14025;
                break;
            /**END OF OSRS ITEMS**/
            case 1686:
                itemDef.name = "Drygore longsword";
                itemDef.modelZoom = 1645;
                itemDef.modelRotationX = 377;
                itemDef.modelRotationY = 444;
                itemDef.modelOffset1 = 3;
                itemDef.modelOffsetY = 0;
                itemDef.groundActions = new String[] { null, null, "Take", null, null };
                itemDef.actions = new String[] { null, "Wield", "Check-charges", null, "Drop" };
                itemDef.modelID = 94465;
                itemDef.maleWearId = 14150;
                itemDef.femaleWearId = 14150;
                break;
            case 19670:
                itemDef.name = "Vote scroll";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Drop";
                itemDef.actions[0] = "Claim";
                itemDef.actions[2] = "Claim-All";
                break;
            case 10034:
            case 10033:
                itemDef.actions = new String[]{null, null, null, null, "Drop"};
                break;
            case 13727:
                itemDef.actions = new String[]{null, null, null, null, "Drop"};
                break;
            case 6500:
                itemDef.modelID = 9123;
                itemDef.name = "Charming imp";
                //	itemDef.modelZoom = 672;
                //	itemDef.rotationY = 85;
                //	itemDef.rotationX = 1867;
                itemDef.actions = new String[]{null, null, "Check", "Config", "Drop"};
                break;
            case 13045:
                itemDef.name = "Abyssal bludgeon";
                itemDef.modelZoom = 2611;
                itemDef.modelRotationY = 552;
                itemDef.modelRotationX = 1508;
                itemDef.modelOffsetY = 3;
                itemDef.modelOffset1 = -17;
                itemDef.groundActions = new String[] { null, null, "Take", null, null };
                itemDef.actions = new String[] { null, "Wield", "Check", "Uncharge", "Drop" };
                itemDef.modelID = 29597;
                itemDef.maleWearId = 29424;
                itemDef.femaleWearId = 29424;
                break;
            case 13047:
                itemDef.name = "Abyssal dagger";
                itemDef.modelZoom = 1347;
                itemDef.modelRotationY = 1589;
                itemDef.modelRotationX = 781;
                itemDef.modelOffsetY = 3;
                itemDef.modelOffset1 = -5;
                itemDef.groundActions = new String[] { null, null, "Take", null, null };
                itemDef.actions = new String[] { null, "Wield", "Check", "Uncharge", "Drop" };
                itemDef.modelID = 29598;
                itemDef.maleWearId = 29425;
                itemDef.femaleWearId = 29425;
                break;
            case 500:
                itemDef.modelID = 2635;
                itemDef.name = "Black partyhat";
                itemDef.description = "Black partyhat";
                itemDef.modelZoom = 440;
                itemDef.modelRotationX = 1845;
                itemDef.modelRotationY = 121;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 1;
                itemDef.stackable = false;
                itemDef.value = 1;
                itemDef.membersObject = true;
                itemDef.maleWearId = 94498;
                itemDef.femaleWearId = 94498;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor = new int[] { 926 };
                break;
            case 13655:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.name = "Baphomet Drygore Offhand";
                itemDef.description = "A rare, protective kiteshield.";
                itemDef.modelID = 83379;
                itemDef.modelZoom = 1645;
                itemDef.modelRotationX = 377;
                itemDef.modelRotationY = 444;
                itemDef.modelOffset1 = 3;
                itemDef.modelOffsetY = 0;
                itemDef.maleWearId = 83380;
                itemDef.femaleWearId = 83380;
                itemDef.groundActions = new String[] { null, null, "Take", null, null };
                //itemDef.anInt188 = -1;
                //itemDef.anInt164 = -1;
                itemDef.maleDialogue = -1;
                itemDef.femaleDialogue = -1;
                itemDef.editedModelColor = new int[] {16,11,4};
                itemDef.newModelColor = new int[]    {97000,97000,97000};
                break;

            case 7108:
                itemDef.stackable = true;
                itemDef.name = "Ammunition"; // Item Name
                itemDef.description = "Ammunition for various firearms."; // Item
                break;
            case 9706:
                itemDef.stackable = true;
                itemDef.name = "Armadylian arrow"; // Item Name
                itemDef.description = "Ammunition for the Armadylian machine."; // Item
                break;
            case 12284:
                itemDef.name = "Toxic staff of the dead";
                itemDef.modelID = 19224;
                itemDef.modelZoom = 2150;
                itemDef.modelRotationX = 1010;
                itemDef.modelRotationY = 512;
                itemDef.femaleWearId = 14402;
                itemDef.maleWearId = 49001;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[2] = "Check";
                itemDef.actions[4] = "Uncharge";
                itemDef.groundActions = new String[] { null, null, "Take", null, null };
                itemDef.editedModelColor = new int[1];
                itemDef.editedModelColor[0] = 17467;
                itemDef.newModelColor = new int[1];
                itemDef.newModelColor[0] = 21947;
                break;
            case 12605:
                itemDef.name = "Treasonous ring";
                itemDef.modelZoom = 750;
                itemDef.modelRotationY = 342;
                itemDef.modelRotationX = 252;
                itemDef.modelOffset1 = -3;
                itemDef.modelOffsetY = -12;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wear", null, null, "Drop"};
                itemDef.modelID = 43900;
                break;

            /** clue scrolls **/
            case 2714:
                itemDef.name = "Casket";
               break;
            case 6183:
                itemDef.name = "@red@Donation Box";
                break;
            case 2677:
            case 2678:
            case 2679:
            case 2680:
            case 2681:
            case 2682:
            case 2683:
            case 2684:
            case 2685:
                itemDef.name = "Clue Scroll";
                break;



            case 13051:
                itemDef.name = "Armadyl crossbow";
                itemDef.modelZoom = 1325;
                itemDef.modelRotationY = 240;
                itemDef.modelRotationX = 110;
                itemDef.modelOffset1 = -6;
                itemDef.modelOffsetY = -40;
                itemDef.newModelColor = new int[]{115, 107, 10167, 10171};
                itemDef.editedModelColor = new int[]{5409, 5404, 6449, 7390};
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wield", null, null, "Drop"};
                itemDef.modelID = 19967;
                itemDef.maleWearId = 19839;
                itemDef.femaleWearId = 19839;
                break;
            case 20339:
                itemDef.name = "Imbued heart";
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 1800;
                itemDef.actions = new String[] { "Draw power", null, null, null, "Drop" };
                itemDef.modelID = 32298;
                itemDef.modelOffset1 = 4;
                itemDef.modelOffsetY = -4;
                break;
            case 4454:
                itemDef.name = "Kodai wand";
                itemDef.modelZoom = 1417;
                itemDef.modelRotationY = 552;
                itemDef.modelRotationX = 1006;
                itemDef.modelOffsetY = 1;
                itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
                itemDef.modelID = 32789;
                itemDef.maleWearId = 32669;
                itemDef.femaleWearId = 32669;
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 0;
                itemDef.newModelColor[0] = 4;
                break;
            case 4453:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 32797; // Drop/Inv Model
                itemDef.maleWearId = 32668; // Male Wield Model
                itemDef.femaleWearId = 32668; // Female Wield
                itemDef.modelZoom = 1230;
                itemDef.modelRotationY = 236;
                itemDef.modelRotationX = 236;
                itemDef.modelOffset1 = -5;
                itemDef.modelOffsetY = -36;
                itemDef.stackable = false;
                itemDef.name = "Dragon hunter crossbow"; // Item Name
                itemDef.description = "A crossbow specialising in hunting dragons."; // Item
                break;
            case 4452:
                itemDef.name = "Twisted buckler";
                itemDef.modelZoom = 920;
                itemDef.modelRotationY = 291;
                itemDef.modelRotationX = 2031;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -3;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.modelID = 32793;
                itemDef.maleWearId = 32667;
                itemDef.femaleWearId = 32667;
                break;
            case 4448:
                itemDef.name = "Dinh's bulwark";
                itemDef.modelZoom = 1620;
                itemDef.modelRotationY = 291;
                itemDef.modelRotationX = 1224;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -3;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.modelID = 32801;
                itemDef.maleWearId = 32671;
                itemDef.femaleWearId = 32671;
                break;
            case 15369:
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Claim Prize";
                itemDef.name = "@gre@Common Package"; //Name
                itemDef.description = "A Party @gre@Common"; //Description
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 22464;
                itemDef.newModelColor[0] = 22410;
                itemDef.modelID = 2426;
                itemDef.modelZoom = 1180;
                itemDef.modelRotationY = 160;
                itemDef.modelRotationX = 172;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffset1 = 0;
                break;
            case 15370:
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Claim Prize";
                itemDef.name = "@blu@Uncommon Package"; //Name
                itemDef.description = "A @blu@Uncommon package"; //Description
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 1010;
                itemDef.newModelColor[0] = 22410;
                itemDef.modelID = 2426;
                itemDef.modelZoom = 1180;
                itemDef.modelRotationY = 160;
                itemDef.modelRotationX = 172;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffset1 = 0;
                break;
            case 15371:
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Claim Prize";
                itemDef.name = "@ora@Rare Package"; //Name
                itemDef.description = "A @ora@Rare Package"; //Description
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 23009;
                itemDef.newModelColor[0] = 22410;
                itemDef.modelID = 2426;
                itemDef.modelZoom = 1180;
                itemDef.modelRotationY = 160;
                itemDef.modelRotationX = 172;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffset1 = 0;
                break;
            case 15372:
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Claim Prize";
                itemDef.name = "@red@Extreme Package"; //Name
                itemDef.description = "A Extreme Package"; //Description
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 8464;
                itemDef.newModelColor[0] = 22410;
                itemDef.modelID = 2426;
                itemDef.modelZoom = 1180;
                itemDef.modelRotationY = 160;
                itemDef.modelRotationX = 172;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffset1 = 0;
                break;
            case 15373:
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Claim Prize";
                itemDef.name = "@bla@Legendary Package"; //Name
                itemDef.description = "A @bla@Legendary Package"; //Description
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 0;
                itemDef.newModelColor[0] = 22410;
                itemDef.modelID = 2426;
                itemDef.modelZoom = 1180;
                itemDef.modelRotationY = 160;
                itemDef.modelRotationX = 172;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffset1 = 0;
                break;
     /*       case 4449:
            	itemDef.name = "Dragon sword";
            	itemDef.modelZoom = 1200;
            	itemDef.rotationY = 255;
            	itemDef.rotationX = 337;
            	itemDef.modelOffset1 = 7;
            	itemDef.actions = new String[] { null, "Wield", "null", null, "Destroy" };
            	itemDef.modelID = 32791;
            	itemDef.maleEquip1 = 32676;
            	itemDef.femaleEquip1 = 32676;
            	break; */
            case 4450:
                itemDef.name = "Elder maul";
                itemDef.modelZoom = 1800;
                itemDef.modelRotationY = 308;
                itemDef.modelRotationX = 36;
                itemDef.modelOffset1 = 7;
                itemDef.actions = new String[] { null, "Wield", "null", null, "Destroy" };
                itemDef.modelID = 32792;
                itemDef.maleWearId = 32678;
                itemDef.femaleWearId = 32678;
                itemDef.editedModelColor = new int[1];
                itemDef.newModelColor = new int[1];
                itemDef.editedModelColor[0] = 0;
                itemDef.newModelColor[0] = 4;
                break;
            case 3954:
                itemDef.name = "Bandos maul";
                itemDef.description = "One of General Graardors many weapons.";
                itemDef.modelZoom = 2300;
                itemDef.modelRotationY = 308;
                itemDef.modelRotationX = 36;
                itemDef.modelOffset1 = 7;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.modelID = 95001;
                itemDef.maleWearId = 95000;
                itemDef.femaleWearId = 95000;
                break;
            case 3964:
                itemDef.name = "Saradomin maul";
                itemDef.description = "One of Commander Zilyana's many weapons.";
                itemDef.modelZoom = 2300;
                itemDef.modelRotationY = 308;
                itemDef.modelRotationX = 36;
                itemDef.modelOffset1 = 7;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.modelID = 95019;
                itemDef.maleWearId = 95020;
                itemDef.femaleWearId = 95020;
                break;
            case 4451:
                itemDef.name = "Dragon thrownaxe";
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.modelID = 32788;
                itemDef.modelZoom = 980;
                itemDef.modelRotationY = 415;
                itemDef.modelRotationX = 112;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 2;
                itemDef.femaleWearId = 32672;
                itemDef.maleWearId = 32672; // male equip
                break;
            case 12927:
                itemDef.name = "Magma blowpipe";
                itemDef.modelZoom = 1158;
                itemDef.modelRotationY = 768;
                itemDef.modelRotationX = 189;
                itemDef.modelOffset1 = -7;
                itemDef.modelOffsetY = 4;
                itemDef.groundActions = new String[] { null, null, "Take", null, null };
                itemDef.actions = new String[] { null, "Wield", "Check", "Unload", "Uncharge" };
                itemDef.newModelColor = new int[] { 8134, 5058, 926, 957, 3008, 1321, 86, 41, 49, 7110, 3008, 1317 };
                itemDef.editedModelColor = new int[] { 48045, 49069, 48055, 49083, 50114, 33668, 29656, 29603, 33674, 33690, 33680, 33692 };
                itemDef.modelID = 19219;
                itemDef.maleWearId = 14403;
                itemDef.femaleWearId = 14403;
                break;
            case 12926:
                itemDef.modelID = 25000;
                itemDef.name = "Toxic blowpipe";
                itemDef.description = "It's a Toxic blowpipe";
                itemDef.modelZoom = 1158;
                itemDef.modelRotationY = 768;
                itemDef.modelRotationX = 189;
                itemDef.modelOffset1 = -7;
                itemDef.modelOffsetY = 4;
                itemDef.maleWearId = 14403;
                itemDef.femaleWearId = 14403;
                itemDef.actions = new String[]{null, "Wield", "Check", "Unload", "Drop"};
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                break;
            case 3962:
                itemDef.modelID = 95016;
                itemDef.name = "Dragonrage blowpipe";
                itemDef.description = "It's a Dragonrage blowpipe";
                itemDef.modelZoom = 1158;
                itemDef.modelRotationY = 768;
                itemDef.modelRotationX = 189;
                itemDef.modelOffset1 = -7;
                itemDef.modelOffsetY = 4;
                itemDef.maleWearId = 95017;
                itemDef.femaleWearId = 95017;
                itemDef.actions = new String[]{null, "Wield", "Check", "Unload", "Drop"};
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                break;
            case 13058:
                itemDef.name = "Trident of the seas";
                itemDef.description = "A weapon from the deep.";
                itemDef.femaleWearId = 1052;
                itemDef.maleWearId = 1052;
                itemDef.modelID = 1051;
                itemDef.modelRotationY = 420;
                itemDef.modelRotationX = 1130;
                itemDef.modelZoom = 2755;
                itemDef.modelOffsetY = 0;
                itemDef.modelOffset1 = 0;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[3] = "Check";
                break;
            case 12601:
                itemDef.name = "Ring of the gods";
                itemDef.modelZoom = 900;
                itemDef.modelRotationY = 393;
                itemDef.modelRotationX = 1589;
                itemDef.modelOffset1 = -9;
                itemDef.modelOffsetY = -12;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wear", null, null, "Drop"};
                itemDef.modelID = 94354;
                break;
            case 12603:
                itemDef.name = "Tyrannical ring";
                itemDef.modelZoom = 592;
                itemDef.modelRotationY = 285;
                itemDef.modelRotationX = 1163;
                itemDef.groundActions = new String[]{null, null, "Take", null, null};
                itemDef.actions = new String[]{null, "Wear", null, null, "Drop"};
                itemDef.modelID = 28823;
                break;
            case 20555:
                itemDef.name = "Dragon warhammer";
                itemDef.modelID = 4041;
                itemDef.modelRotationY = 1450;
                itemDef.modelRotationX = 1900;
                itemDef.modelZoom = 1605;
                itemDef.groundActions = new String[] { null, null, "Take", null, null };
                itemDef.actions = new String[] { null, "Wield", null, null, "Drop" };
                itemDef.maleWearId = 4037;
                itemDef.femaleWearId = 4038;
                break;
            case 11613:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 13701;
                itemDef.modelZoom = 1560;
                itemDef.modelRotationY = 344;
                itemDef.modelRotationX = 1104;
                itemDef.modelOffsetY = -14;
                itemDef.modelOffsetX = 0;
                itemDef.maleWearId = 13700;
                itemDef.femaleWearId = 13700;
                itemDef.name = "Dragon Kiteshield";
                itemDef.description = "A Dragon Kiteshield!";
                break;
            case 11614:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 3288;
                itemDef.modelZoom = 2000;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 3287;
                itemDef.femaleWearId = 3287;
                itemDef.name = "Death Cape";
                break;
                
            case 3071:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 96312;
                itemDef.modelZoom = 2000;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 96313;
                itemDef.femaleWearId = 96313;
                itemDef.name = "StoneBraker Full Helmet";
                break;
                
            case 3072:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 96314;
                itemDef.modelZoom = 2000;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 96315;
                itemDef.femaleWearId = 96315;
                itemDef.name = "StoneBraker Platebody";
                break;
                
            case 3073:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 96316;
                itemDef.modelZoom = 2000;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 96317;
                itemDef.femaleWearId = 96317;
                itemDef.name = "StoneBraker Legs";
                break;
                
            case 3074:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 96320;
                itemDef.modelZoom = 2000;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 96320;
                itemDef.femaleWearId = 96320;
                itemDef.name = "StoneBraker Boots";
                break;
                
            case 3075:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 96318;
                itemDef.modelZoom = 2000;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 96319;
                itemDef.femaleWearId = 96319;
                itemDef.name = "StoneBraker Gloves";
                break;
                
                //*------------CUSTOM ITEMS 2019------------*//
                
            case 13265:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 90814;
                itemDef.modelZoom = 1100;
                itemDef.modelRotationY = 100;
                itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
                itemDef.maleWearId = 90815;
                itemDef.femaleWearId = 90815;
                itemDef.name = "Cloud strife Full Helmet";
                break;
                
            case 13266:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 90816;
                itemDef.modelZoom = 1400;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
                itemDef.maleWearId = 90817;
                itemDef.femaleWearId = 90817;
                itemDef.name = "Cloud strife PlateBody";
                break;
                
                
            case 13268:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 90818;
                itemDef.modelZoom = 1500;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
                itemDef.maleWearId = 90819;
                itemDef.femaleWearId = 90819;
                itemDef.name = "Cloud strife Legs";
                break;
                
                
            case 13269:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 90826;
      		    itemDef.modelZoom = 1200;
                itemDef.modelRotationY = 500;
        	    itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
                itemDef.maleWearId = 90826;
                itemDef.femaleWearId = 90826;
                itemDef.name = "Cloud strife Boots";
                break;
                
                
            case 13270:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 90824;
      		    itemDef.modelZoom = 1200;
                itemDef.modelRotationY = 500;
        	    itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
                itemDef.maleWearId = 90825;
                itemDef.femaleWearId = 90825;
                itemDef.name = "Cloud strife Gloves";
                break;
                
            case 13271:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 90822;
      		    itemDef.modelZoom = 1200;
                itemDef.modelRotationY = 500;
        	    itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
                itemDef.maleWearId = 90823;
                itemDef.femaleWearId = 90823;
                itemDef.name = "Cloud strife Cape";
                break;
                
            case 20259:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 91313;
                //itemDef.colourRedefine3 = 675;
      		    itemDef.modelZoom = 1200;
                itemDef.modelRotationY = 500;
        	    itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
                itemDef.maleWearId = 91314;
                itemDef.femaleWearId = 91314;
                itemDef.name = "Donkey Kong Sword";
                break;
                
            case 3078:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 71023;
                itemDef.modelZoom = 900;
                itemDef.modelRotationY = 100;
       			itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
                itemDef.maleWearId = 71022;
                itemDef.femaleWearId = 71022;
                itemDef.name = "Darth Vader Full Helmet";
                break;
                
            case 3079:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 71020;
      		    itemDef.modelZoom = 1200;
                itemDef.modelRotationY = 500;
        	    itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
                itemDef.maleWearId = 71019;
                itemDef.femaleWearId = 71019;
                itemDef.name = "Darth Vader PlateBody";
                break;
                
            case 3080:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 73461;
      		    itemDef.modelZoom = 1200;
                itemDef.modelRotationY = 500;
        	    itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
                itemDef.maleWearId = 71021;
                itemDef.femaleWearId = 71021;
                itemDef.name = "Darth Vader Legs";
                break;
                
            case 3081:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 71028;
      		    itemDef.modelZoom = 1200;
                itemDef.modelRotationY = 500;
        	    itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
                itemDef.maleWearId = 71028;
                itemDef.femaleWearId = 71028;
                itemDef.name = "Darth Vader Boots";
                break;     
                
            case 3086:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 71025;
      		    itemDef.modelZoom = 1200;
                itemDef.modelRotationY = 500;
        	    itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
                itemDef.maleWearId = 71024;
                itemDef.femaleWearId = 71024;
                itemDef.name = "Darth Vader Gloves";
                break;        
                
            case 3087:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 71027;
      		    itemDef.modelZoom = 1200;
                itemDef.modelRotationY = 500;
        	    itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
                itemDef.maleWearId = 71026;
                itemDef.femaleWearId = 71026;
                itemDef.name = "Darth Vader Cape";
                break; 
                
            case 3088:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 35780;
      		    itemDef.modelZoom = 1200;
                itemDef.modelRotationY = 500;
        	    itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
                itemDef.maleWearId = 35781;
                itemDef.femaleWearId = 35781;
                itemDef.name = "Executive Thumper";
                break; 
                
            case 3274:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 90405;
      		    itemDef.modelZoom = 1200;
                itemDef.modelRotationY = 500;
        	    itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
                itemDef.maleWearId = 90406;
                itemDef.femaleWearId = 90406;
                itemDef.name = "@mag@Shadow Glaive";
                break;
            case 3275:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 94000;
      		    itemDef.modelZoom = 1200;
                itemDef.modelRotationY = 500;
        	    itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
                itemDef.maleWearId = 94001;
                itemDef.femaleWearId = 94001;
                itemDef.name = "@mag@Weedo OwnerCape";
                break; 
                
            case 3279:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 91726;
                itemDef.modelZoom = 1500;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 91727;
                itemDef.femaleWearId = 91727;
                itemDef.name = "@whi@Ownercape";
                break; 
                
            case 3280:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 91413;
                itemDef.modelZoom = 1500;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 91413;
                itemDef.femaleWearId = 91413;
                itemDef.name = "@yel@ELDRITCH's Cape";
                break; 
                
            case 3281:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 94422;
                itemDef.modelZoom = 1500;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 94423;
                itemDef.femaleWearId = 94423;
                itemDef.name = "@whi@Supreme T-Shirt";
                break;
                
            case 3282:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 91767;
                itemDef.modelZoom = 1500;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 91768;
                itemDef.femaleWearId = 91768;
                itemDef.name = "@whi@KillerPumpkin Full Helmet";
                break;
                
            case 3283:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 91769;
                itemDef.modelZoom = 1500;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 91770;
                itemDef.femaleWearId = 91770;
                itemDef.name = "@whi@KillerPumpkin PlateBody";
                break;
                
            case 3284:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 91771;
                itemDef.modelZoom = 1500;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 91772;
                itemDef.femaleWearId = 91772;
                itemDef.name = "@whi@KillerPumpkin Legs";
                break;
                
            case 3276:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 91690;
                itemDef.modelZoom = 1500;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 91691;
                itemDef.femaleWearId = 91691;
                itemDef.name = "@red@Love Glaive";
                break;    
                
            case 3300:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 91596;
                itemDef.modelZoom = 1500;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 91596;
                itemDef.femaleWearId = 91596;
                itemDef.name = "@mag@Batman Glaive";
                break; 
                
            case 3301:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 94107;
                itemDef.modelZoom = 1500;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 94107;
                itemDef.femaleWearId = 94107;
                itemDef.name = "@mag@Cpt. merica Glaive";
                break; 
                
            case 3302:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 91096;
                itemDef.modelZoom = 1500;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 91096;
                itemDef.femaleWearId = 91096;
                itemDef.name = "@mag@Metal Glaive";
                break; 
                
            case 3303:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 91240;
                itemDef.modelZoom = 1500;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 91240;
                itemDef.femaleWearId = 91240;
                itemDef.name = "@mag@ Pink Panther Glaive";
                break;
                
            case 3304:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 91624;
                itemDef.modelZoom = 1500;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 91624;
                itemDef.femaleWearId = 91624;
                itemDef.name = "@mag@Superman Glaive";
                break;
                
            case 3305:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 90307;
                itemDef.modelZoom = 1500;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 90307;
                itemDef.femaleWearId = 90307;
                itemDef.name = "@mag@Ice Glaive";
                break;
                
            case 3306:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 90616;
                itemDef.modelZoom = 1500;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 90616;
                itemDef.femaleWearId = 90616;
                itemDef.name = "@mag@Death Glaive";
                break;
                
            case 3307:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 91121;
                itemDef.modelZoom = 1500;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 91121;
                itemDef.femaleWearId = 91121;
                itemDef.name = "@mag@Ghost Slime Glaive";
                break;
                
            case 3308:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 91653;
                itemDef.modelZoom = 1500;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 91653;
                itemDef.femaleWearId = 91653;
                itemDef.name = "@mag@Custom example";
                break;
                
            case 3309:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 94466;
                itemDef.modelZoom = 1500;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 94466;
                itemDef.femaleWearId = 94466;
                itemDef.name = "@mag@Silver Surfer";
                break;
           
            case 3076:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 96505;
                itemDef.modelZoom = 1500;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 96506;
                itemDef.femaleWearId = 96506;
                itemDef.editedModelColor = new int[] {40};
                itemDef.newModelColor = new int[]  {51};
                itemDef.name = "@red@Assassin Sword";
                break; 
                                
            case 3077:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 96505;
                itemDef.modelZoom = 1500;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 96507;
                itemDef.femaleWearId = 96507;
                itemDef.editedModelColor = new int[] {40};
                itemDef.newModelColor = new int[]  {51};
                itemDef.name = "@red@Assassin Sword Off-Hand";
                break; 
                
            case 3289:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 96496;
                itemDef.modelZoom = 1000;
                itemDef.modelRotationY = 100;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 96497;
                itemDef.femaleWearId = 96497;
                itemDef.name = "@red@Assassin Hood";
                break; 
                
            case 3290:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 96498;
                itemDef.modelZoom = 1500;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 96499;
                itemDef.femaleWearId = 96499;
                itemDef.name = "@red@Assassin PlateBody";
                break; 
                
            case 3291:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 96500;
                itemDef.modelZoom = 1500;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 96501;
                itemDef.femaleWearId = 96501;
                itemDef.name = "@red@Assassin Legs";
                break; 
                
            case 3292:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 96504;
                itemDef.modelZoom = 1500;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 96504;
                itemDef.femaleWearId = 96504;
                itemDef.name = "@red@Assassin Boots";
                break; 
                
            case 3293:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 96502;
                itemDef.modelZoom = 1500;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 96503;
                itemDef.femaleWearId = 96503;
                itemDef.name = "@red@Assassin Gloves";
                break; 
                
            case 3294:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 96505;
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 800;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 96508;
                itemDef.femaleWearId = 96508;
                itemDef.name = "@red@Assassin Back Sword";
                break;
                
            case 3809:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 94534;
                itemDef.modelZoom = 1000;
                itemDef.modelRotationY = 100;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 94535;
                itemDef.femaleWearId = 94535;
                itemDef.name = "@blu@Blood Lord Helmet";
                break; 
                
            case 3810:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 94530;
                itemDef.modelZoom = 1500;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 94531;
                itemDef.femaleWearId = 94531;
                itemDef.name = "@blu@Blood Lord PlateBody";
                break; 
                
            case 3811:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 94532;
                itemDef.modelZoom = 1500;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 94533;
                itemDef.femaleWearId = 94533;
                itemDef.name = "@blu@Blood Lord Legs";
                break; 
                
            case 3812:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 94538;
                itemDef.modelZoom = 1500;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 94538;
                itemDef.femaleWearId = 94538;
                itemDef.name = "@blu@Blood Lord Boots";
                break; 
                
            case 3813:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 94536;
                itemDef.modelZoom = 1500;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 94537;
                itemDef.femaleWearId = 94537;
                itemDef.name = "@blu@Blood Lord Gloves";
                break; 
                
            case 3814:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 94528;
                itemDef.modelZoom = 1500;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 94529;
                itemDef.femaleWearId = 94529;
                itemDef.name = "@blu@Blood Lord Sword";
                break;  
                
            case 3068:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 78433;
                itemDef.modelZoom = 1500;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 78227;
                itemDef.femaleWearId = 78227;
                itemDef.name = "@or2@cloud strife Gloves";
                break;
                
            case 3069:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 78437;
                itemDef.modelZoom = 1500;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 78226;
                itemDef.femaleWearId = 78226;
                itemDef.name = "@or2@cloud strife Cape";
                break;
                
            case 3070:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 98227;
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 630;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 98227;
                itemDef.femaleWearId = 98227;
                itemDef.name = "@or2@cloud strife Sword";
                break;
                
            case 3816:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 91693;
                itemDef.modelZoom = 1500;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 91694;
                itemDef.femaleWearId = 91694;
                itemDef.name = "@red@Shadow Glaive";
                break;
                
            case 3817:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 91728;
                itemDef.modelZoom = 2000;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 91729;
                itemDef.femaleWearId = 91729;
                itemDef.name = "@bla@Shadow Aura (LVL 1)";
                break;
                
            case 3818:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 86742;
                itemDef.modelZoom = 2000;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 86743;
                itemDef.femaleWearId = 86743;
                itemDef.name = "executive Cape";
                break;
                
            case 2544:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 96113;
                itemDef.modelZoom = 2000;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 96114;
                itemDef.femaleWearId = 96114;
                itemDef.name = "Staff of OP";
                break;
                
            case 2545:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 94519;
                itemDef.modelZoom = 2000;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 94520;
                itemDef.femaleWearId = 94520;
                itemDef.name = "Gambler's Chain";
                break;
                
            case 2546:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 94517;
                itemDef.modelZoom = 2000;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 94518;
                itemDef.femaleWearId = 94518;
                itemDef.name = "Gambler's Cape";
                break;
                
            case 2547:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 94074;
                itemDef.modelZoom = 2000;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 94075;
                itemDef.femaleWearId = 94075;
                itemDef.name = "Send Nudes Icon";
                break;
                
            case 2549:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 130062;
                itemDef.modelZoom = 2000;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 130061;
                itemDef.femaleWearId = 130061;
                itemDef.name = "Devil Glaive";
                break;
                
            case 897:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 95119;
                itemDef.modelZoom = 2000;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 95119;
                itemDef.femaleWearId = 95119;
                itemDef.name = "BFG-9000 Off Hand";
                itemDef.editedModelColor = new int[] {60};
                itemDef.newModelColor = new int[]  {63};
                break;
                
            case 898:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 95120;
                itemDef.modelZoom = 2000;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 95120;
                itemDef.femaleWearId = 95120;
                itemDef.name = "BFG-9000";
                itemDef.editedModelColor = new int[] {60};
                itemDef.newModelColor = new int[]  {65};
                break;
                
            case 900:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 13957;
                itemDef.modelZoom = 2000;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 13958;
                itemDef.femaleWearId = 13958;
                itemDef.name = "Melee Cape";
                break;
                
            case 901:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 13955;
                itemDef.modelZoom = 2000;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 13956;
                itemDef.femaleWearId = 13956;
                itemDef.name = "Range Cape";
                break;
                
            case 902:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 16831;
                itemDef.modelZoom = 2000;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 16831;
                itemDef.femaleWearId = 16831;
                itemDef.name = "RainBow Fighter Boots";
                break;
                
            case 903:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 91151;
                itemDef.modelZoom = 2200;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 91152;
                itemDef.femaleWearId = 91152;
                itemDef.name = "Staff Of Destuction";
                itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
                itemDef.newModelColor = new int[]  {90,90,90,90,90,90,90,90,90,90,90,90,90,90};
                break;
                
            case 3246:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 95113;
                itemDef.modelZoom = 2200;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 95114;
                itemDef.femaleWearId = 95114;
                itemDef.name = "Destuction Khione's Staff";
                itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
                itemDef.newModelColor = new int[]  {90,90,90,90,90,90,90,90,90,90,90,90,90,90};
                break;
                
            case 910:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 91144;
                itemDef.modelZoom = 1400;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 91145;
                itemDef.femaleWearId = 91145;
                itemDef.name = "Destuction Body";
                itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
                itemDef.newModelColor = new int[]  {90,90,90,90,90,90,90,90,90,90,90,90,90,90};
                break;
                
            case 911:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 91142;
                itemDef.modelZoom = 1100;
                itemDef.modelRotationY = 100;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 91143;
                itemDef.femaleWearId = 91143;
                itemDef.name = "Destuction Helm";
                itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
                itemDef.newModelColor = new int[]  {90,90,90,90,90,90,90,90,90,90,90,90,90,90};
                break;
                
            case 909:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 91146;
                itemDef.modelZoom = 1400;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 91147;
                itemDef.femaleWearId = 91147;
                itemDef.name = "Destuction Legs";
                itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
                itemDef.newModelColor = new int[]  {90,90,90,90,90,90,90,90,90,90,90,90,90,90};
                break;
                 
            case 912:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 91150;
                itemDef.modelZoom = 1200;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 91150;
                itemDef.femaleWearId = 91150;
                itemDef.name = "Destuction Boots";
                itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
                itemDef.newModelColor = new int[]  {90,90,90,90,90,90,90,90,90,90,90,90,90,90};

                break;
                
            case 913:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 91148;
                itemDef.modelZoom = 1200;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 91149;
                itemDef.femaleWearId = 91149;
                itemDef.name = "Destuction gloves";
                itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
                itemDef.newModelColor = new int[]  {90,90,90,90,90,90,90,90,90,90,90,90,90,90};
                break;
                
            case 915:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 130082; //add drop gloves
                itemDef.modelZoom = 496;
                itemDef.modelRotationY = 1261;
                itemDef.modelRotationX = 1574;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 130077;
                itemDef.femaleWearId = 130077;
                itemDef.name = "Ainz Hands";
                break;
                
            case 916:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 130083;
                itemDef.modelZoom = 530;
                itemDef.modelRotationY = 148;
                itemDef.modelRotationX = 96;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 130078;
                itemDef.femaleWearId = 130078;
                itemDef.name = "Ainz Head";
                break;
            case 917:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 130076;
                itemDef.modelZoom = 3000;
                itemDef.modelRotationY = 61;
                itemDef.modelRotationX = 1800;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 130076;
                itemDef.femaleWearId = 130076;
                itemDef.name = "Ainz Cape";
                break;
            case 918:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 130079;
                itemDef.modelZoom = 2443;
                itemDef.modelRotationY = 183;
                itemDef.modelRotationX = 270;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 130079;
                itemDef.femaleWearId = 130079;
                itemDef.name = "Ainz Legs";
                break;
            case 919:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 130086;
                itemDef.modelZoom = 600;
                itemDef.modelRotationY = 287;
                itemDef.modelRotationX = 391;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 130086;
                itemDef.femaleWearId = 130086;
                itemDef.name = "Death Tome";
                break;
            case 920:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 130084;
                itemDef.modelZoom = 1313;
                itemDef.modelRotationY = 461;
                itemDef.modelRotationX = 1991;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 130080;
                itemDef.femaleWearId = 130080;
                itemDef.name = "Ainz Body";
                break;
                
            case 921:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 130081;
                itemDef.modelZoom = 2496;
                itemDef.modelRotationY = 896;
                itemDef.modelRotationX = 652;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 130081;
                itemDef.femaleWearId = 130081;
                itemDef.name = "Staff of Ainz Ooal Gown";
                break;
                
            case 922:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 90706;
                itemDef.modelZoom = 1800;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 90707;
                itemDef.femaleWearId = 90707;
                itemDef.name = "Samurai Cape";
                itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
                itemDef.newModelColor = new int[]  {56,56,56,56,56,56,56,56,56,56,56,56,56,56};
                break;
                
            case 923:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 94129;
                itemDef.modelZoom = 1400;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 94130;
                itemDef.femaleWearId = 94130;
                itemDef.name = "Samurai Body";
                itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
                itemDef.newModelColor = new int[]  {56,56,56,56,56,56,56,56,56,56,56,56,56,56};
                break;
                
            case 924:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 94131;
                itemDef.modelZoom = 1500;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 94132;
                itemDef.femaleWearId = 94132;
                itemDef.name = "Samurai Legs";
                itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
                itemDef.newModelColor = new int[]  {56,56,56,56,56,56,56,56,56,56,56,56,56,56};
                break;
                
            case 925:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 94133;
                itemDef.modelZoom = 1100;
                itemDef.modelRotationY = 100;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 94134;
                itemDef.femaleWearId = 94134;
                itemDef.name = "Samurai Helm";
                itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
                itemDef.newModelColor = new int[]  {56,56,56,56,56,56,56,56,56,56,56,56,56,56};
                break;
                
            case 926:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 92115;
                itemDef.modelZoom = 1200;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 92115;
                itemDef.femaleWearId = 92115;
                itemDef.name = "Samurai Boots";
                itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
                itemDef.newModelColor = new int[]  {56,56,56,56,56,56,56,56,56,56,56,56,56,56};
                break;
                
            case 927:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 92114;
                itemDef.modelZoom = 1200;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 92114;
                itemDef.femaleWearId = 92114;
                itemDef.name = "Samurai Gloves";
                itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
                itemDef.newModelColor = new int[]  {56,56,56,56,56,56,56,56,56,56,56,56,56,56};
                break;

            case 929:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 90927;
                itemDef.modelZoom = 1200;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 90927;
                itemDef.femaleWearId = 90927;
                itemDef.name = "Storm Breaker Boots";
                break;
                
    		case 3298:
     			itemDef.modelID = 90936;
     			itemDef.name = "Storm Breaker Maul";
    			itemDef.description = "Toxidian Maul";
                itemDef.modelZoom = 2000;
                itemDef.modelRotationY = 500;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 90938;
     			itemDef.femaleWearId = 90938;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     			break;
                
            case 931:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 90930;
                itemDef.modelZoom = 1000;
                itemDef.modelRotationY = 100;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 90931;
                itemDef.femaleWearId = 90931;
                itemDef.name = "Storm Breaker Helm";
                break;
                
            case 932:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 90934;
                itemDef.modelZoom = 1600;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 90935;
                itemDef.femaleWearId = 90935;
                itemDef.name = "Storm Breaker Body";
                break;
                
            case 933:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 90932;
                itemDef.modelZoom = 1600;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 90933;
                itemDef.femaleWearId = 90933;
                itemDef.name = "Storm Breaker Legs";
                break;
                
            case 934:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 90928;
                itemDef.modelZoom = 1600;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 90929;
                itemDef.femaleWearId = 90929;
                itemDef.name = "Storm Breaker Gloves";
                break;
                
            case 935:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 80201;
                itemDef.modelZoom = 1600;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 80201;
                itemDef.femaleWearId = 80201;
                itemDef.name = "Weed Chain";
                break;
                
            case 937:
                itemDef.modelID = 95146;
                itemDef.name = "Aqantium bow";
                itemDef.description = "Aqantium bow.";
                itemDef.modelZoom = 1700;
                itemDef.modelRotationY = 296;
                itemDef.modelRotationX = 377;
                itemDef.modelOffset1 = 5;
                itemDef.modelOffsetY = -33;
                itemDef.membersObject = true;
                itemDef.maleWearId = 95145;
                itemDef.femaleWearId = 95145;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.stackable = false;
                break;
                
            case 3310:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 91835;
                itemDef.modelZoom = 1000;
                itemDef.modelRotationY = 100;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 91836;
                itemDef.femaleWearId = 91836;
                itemDef.name = "Toxic shadow Helm";
                break;
                
            case 3311:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 91837;
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 91838;
                itemDef.femaleWearId = 91838;
                itemDef.name = "Toxic shadow Body";
                break;
                
            case 3312:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 91839;
                itemDef.modelZoom = 1200;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffsetY = -6;
                itemDef.modelOffsetX = 1;
                itemDef.maleWearId = 91840;
                itemDef.femaleWearId = 91840;
                itemDef.name = "Toxic shadow Legs";
                break;
                
            case 4774:
    			itemDef.modelID = 92021;
    			itemDef.name = "Grndmaster Helmet";
    			itemDef.description = "Grndmaster set";
                itemDef.modelZoom = 1000;
                itemDef.modelRotationY = 100;
    			itemDef.modelRotationX = 0;
    			itemDef.modelOffset1 = -1;
    			itemDef.modelOffsetY = -1;
    			itemDef.maleWearId = 92022;
    			itemDef.femaleWearId = 92022;
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wear";
    			itemDef.actions[4] = "Drop";
    			break;
    			
    		case 4775:
    			itemDef.modelID = 92023;
    			itemDef.name = "Grndmaster Platebody";
    			itemDef.description = "Grndmaster set";
    			itemDef.modelZoom = 1600;
    			itemDef.modelRotationY = 600;
    			itemDef.modelRotationX = 0;
    			itemDef.modelOffset1 = -1;
    			itemDef.modelOffsetY = -1;
    			itemDef.maleWearId = 92024;
    			itemDef.femaleWearId = 92024;
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wear";
    			itemDef.actions[4] = "Drop";
    			break;
    			
    		case 4776:
    			itemDef.modelID = 92025;
    			itemDef.name = "Grndmaster Legs";
    			itemDef.description = "Grndmaster set";
    			itemDef.modelZoom = 1500;
    			itemDef.modelRotationY = 500;
    			itemDef.modelRotationX = 0;
    			itemDef.modelOffset1 = -1;
    			itemDef.modelOffsetY = -1;
    			itemDef.maleWearId = 92026;
    			itemDef.femaleWearId = 92026;
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wear";
    			itemDef.actions[4] = "Drop";
    			break;
    			
    		case 4777:
    			itemDef.modelID = 92027;
    			itemDef.name = "Grndmaster Sword";
    			itemDef.description = "Grndmaster Sword";
    			itemDef.modelZoom = 1500;
    			itemDef.modelRotationY = 500;
    			itemDef.modelRotationX = 0;
    			itemDef.modelOffset1 = -1;
    			itemDef.modelOffsetY = -1;
    			itemDef.maleWearId = 92028;
    			itemDef.femaleWearId = 92028;
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wear";
    			itemDef.actions[4] = "Drop";
    			break;
    			
    		case 4779:
    			itemDef.modelID = 92027;
    			itemDef.name = "Grndmaster Sword Off-Hand";
    			itemDef.description = "Grndmaster Sword Off-Hand";
    			itemDef.modelZoom = 1700;
    			itemDef.modelRotationY = 600;
    			itemDef.modelRotationX = 0;
    			itemDef.modelOffset1 = -1;
    			itemDef.modelOffsetY = -1;
    			itemDef.maleWearId = 92029;
    			itemDef.femaleWearId = 92029;
    			itemDef.actions = new String[5];
    			itemDef.actions[1] = "Wear";
    			itemDef.actions[4] = "Drop";
    			break;
    			
    		 case 4780:
                 itemDef.actions = new String[5];
                 itemDef.actions[1] = "Wield";
                 itemDef.modelID = 96165;
                 itemDef.modelZoom = 1000;
                 itemDef.modelRotationY = 100;
                 itemDef.modelRotationX = 0;
                 itemDef.modelOffsetY = -6;
                 itemDef.modelOffsetX = 1;
                 itemDef.maleWearId = 96166;
                 itemDef.femaleWearId = 96166;
                 itemDef.name = "Dead Pirate Helm";
                 break;
                 
             case 4781:
                 itemDef.actions = new String[5];
                 itemDef.actions[1] = "Wield";
                 itemDef.modelID = 96167;
                 itemDef.modelZoom = 1300;
                 itemDef.modelRotationY = 500;
                 itemDef.modelRotationX = 0;
                 itemDef.modelOffsetY = -6;
                 itemDef.modelOffsetX = 1;
                 itemDef.maleWearId = 96168;
                 itemDef.femaleWearId = 96168;
                 itemDef.name = "Dead Pirate Body";
                 break;
                 
             case 4782:
                 itemDef.actions = new String[5];
                 itemDef.actions[1] = "Wield";
                 itemDef.modelID = 96169;
                 itemDef.modelZoom = 1300;
                 itemDef.modelRotationY = 500;
                 itemDef.modelRotationX = 0;
                 itemDef.modelOffsetY = -6;
                 itemDef.modelOffsetX = 1;
                 itemDef.maleWearId = 96170;
                 itemDef.femaleWearId = 96170;
                 itemDef.name = "Dead Pirate Legs";
                 break;
                 
             case 4784:
                 itemDef.actions = new String[5];
                 itemDef.actions[1] = "Wield";
                 itemDef.modelID = 96171;
                 itemDef.modelZoom = 1200;
                 itemDef.modelRotationY = 500;
                 itemDef.modelRotationX = 0;
                 itemDef.modelOffsetY = -6;
                 itemDef.modelOffsetX = 1;
                 itemDef.maleWearId = 96172;
                 itemDef.femaleWearId = 96172;
                 itemDef.name = "Dead Pirate Gloves";
                 break;
                 
             case 4785:
                 itemDef.actions = new String[5];
                 itemDef.actions[1] = "Wield";
                 itemDef.modelID = 96173;
                 itemDef.modelZoom = 1200;
                 itemDef.modelRotationY = 600;
                 itemDef.modelRotationX = 0;
                 itemDef.modelOffsetY = -6;
                 itemDef.modelOffsetX = 1;
                 itemDef.maleWearId = 96173;
                 itemDef.femaleWearId = 96173;
                 itemDef.name = "Dead Pirate Boots";
                 break;
                 
             case 4786:
                 itemDef.actions = new String[5];
                 itemDef.actions[1] = "Wield";
                 itemDef.modelID = 96249;
                 itemDef.modelZoom = 1200;
                 itemDef.modelRotationY = 200;
                 itemDef.modelRotationX = 0;
                 itemDef.modelOffsetY = -6;
                 itemDef.modelOffsetX = 1;
                 itemDef.maleWearId = 96250;
                 itemDef.femaleWearId = 96250;
                 itemDef.name = "Flintlock Pistol";
                 break;
                 
             case 4057:
                 itemDef.modelID = 91034;
                 itemDef.name = "Aqantium Helmet";
                 itemDef.description = "Aqantium Helmet.";
                 itemDef.modelZoom = 1000;
                 itemDef.modelRotationY = 100;
                 itemDef.modelRotationX = 0;
                 itemDef.membersObject = true;
                 itemDef.maleWearId = 91035;
                 itemDef.femaleWearId = 91035;
                 itemDef.actions = new String[5];
                 itemDef.actions[1] = "Wield";
                 itemDef.actions[4] = "Drop";
                 itemDef.stackable = false;
                 break;
                 
             case 4058:
                 itemDef.modelID = 91038;
                 itemDef.name = "Aqantium Body";
                 itemDef.description = "Aqantium Body.";
                 itemDef.modelZoom = 1300;
                 itemDef.modelRotationY = 500;
                 itemDef.modelRotationX = 0;
                 itemDef.membersObject = true;
                 itemDef.maleWearId = 91039;
                 itemDef.femaleWearId = 91039;
                 itemDef.actions = new String[5];
                 itemDef.actions[1] = "Wield";
                 itemDef.actions[4] = "Drop";
                 itemDef.stackable = false;
                 break;
                 
             case 4059:
                 itemDef.modelID = 91036;
                 itemDef.name = "Aqantium Legs";
                 itemDef.description = "Aqantium Legs.";
                 itemDef.modelZoom = 1300;
                 itemDef.modelRotationY = 500;
                 itemDef.modelRotationX = 0;
                 itemDef.membersObject = true;
                 itemDef.maleWearId = 91037;
                 itemDef.femaleWearId = 91037;
                 itemDef.actions = new String[5];
                 itemDef.actions[1] = "Wield";
                 itemDef.actions[4] = "Drop";
                 itemDef.stackable = false;
                 break;
                 
             case 4553:
                 itemDef.modelID = 95072;
                 itemDef.name = "Aqantium Boxing Gloves";
                 itemDef.description = "Aqantium Boxing Gloves.";
                 itemDef.modelZoom = 900;
                 itemDef.modelRotationY = 500;
                 itemDef.modelRotationX = 0;
                 itemDef.membersObject = true;
                 itemDef.maleWearId = 95073;
                 itemDef.femaleWearId = 95073;
                 itemDef.actions = new String[5];
                 itemDef.actions[1] = "Wield";
                 itemDef.actions[4] = "Drop";
                 itemDef.stackable = false;
                 itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
                 itemDef.newModelColor = new int[]  {70,70,70,70,70,70,70,70,70,70,70,70,70,70};
                 break;
                 
             case 4554:
                 itemDef.modelID = 95064;
                 itemDef.name = "Aqantium Wing Boots";
                 itemDef.description = "Aqantium Wing Boots.";
                 itemDef.modelZoom = 900;
                 itemDef.modelRotationY = 100;
                 itemDef.modelRotationX = 50;
                 itemDef.membersObject = true;
                 itemDef.maleWearId = 95064;
                 itemDef.femaleWearId = 95064;
                 itemDef.actions = new String[5];
                 itemDef.actions[1] = "Wield";
                 itemDef.actions[4] = "Drop";
                 itemDef.stackable = false;
                 itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
                 itemDef.newModelColor = new int[]  {70,70,70,70,70,70,70,70,70,70,70,70,70,70};
                 break;
                 
             case 4555:
                 itemDef.modelID = 95072;
                 itemDef.name = "Grndmaster Boxing Gloves";
                 itemDef.description = "Grndmaster Boxing Gloves.";
                 itemDef.modelZoom = 900;
                 itemDef.modelRotationY = 500;
                 itemDef.modelRotationX = 0;
                 itemDef.membersObject = true;
                 itemDef.maleWearId = 95073;
                 itemDef.femaleWearId = 95073;
                 itemDef.actions = new String[5];
                 itemDef.actions[1] = "Wield";
                 itemDef.actions[4] = "Drop";
                 itemDef.stackable = false;
                 itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
                 itemDef.newModelColor = new int[]  {86,86,86,86,86,86,86,86,86,86,86,86,86,86};
                 break;
                 
             case 4556:
                 itemDef.modelID = 95064;
                 itemDef.name = "Grndmaster Wing Boots";
                 itemDef.description = "Grndmaster Wing Boots.";
                 itemDef.modelZoom = 900;
                 itemDef.modelRotationY = 100;
                 itemDef.modelRotationX = 50;
                 itemDef.membersObject = true;
                 itemDef.maleWearId = 95064;
                 itemDef.femaleWearId = 95064;
                 itemDef.actions = new String[5];
                 itemDef.actions[1] = "Wield";
                 itemDef.actions[4] = "Drop";
                 itemDef.stackable = false;
                 itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
                 itemDef.newModelColor = new int[]  {86,86,86,86,86,86,86,86,86,86,86,86,86,86};
                 break;
                 
             case 4060:
                 itemDef.modelID = 94268;
                 itemDef.name = "infinity gauntlet";
                 itemDef.description = "infinity gauntlet.";
                 itemDef.modelZoom = 1300;
                 itemDef.modelRotationY = 500;
                 itemDef.modelRotationX = 0;
                 itemDef.membersObject = true;
                 itemDef.maleWearId = 94269;
                 itemDef.femaleWearId = 94269;
                 itemDef.actions = new String[5];
                 itemDef.actions[1] = "Wield";
                 itemDef.actions[4] = "Drop";
                 itemDef.stackable = false;
                 break;
                 
             case 4061:
                 itemDef.modelID = 94983;
                 itemDef.name = "infinity gauntlet @or2@(Charged)";
                 itemDef.description = "infinity gauntlet.";
                 itemDef.modelZoom = 1300;
                 itemDef.modelRotationY = 500;
                 itemDef.modelRotationX = 0;
                 itemDef.membersObject = true;
                 itemDef.maleWearId = 94984;
                 itemDef.femaleWearId = 94984;
                 itemDef.actions = new String[5];
                 itemDef.actions[1] = "Wield";
                 itemDef.actions[4] = "Drop";
                 itemDef.stackable = false;
                 break;
                 
             case 4629:
                 itemDef.modelID = 91621;
                 itemDef.name = "Space Stone";
                 itemDef.description = "Space Stone.";
                 itemDef.modelZoom = 1300;
                 itemDef.modelRotationY = 500;
                 itemDef.modelRotationX = 0;
               break;
               
             case 4630:
                 itemDef.modelID = 91621;
                 itemDef.name = "Mind Stone";
                 itemDef.description = "Mind Stone.";
                 itemDef.modelZoom = 1300;
                 itemDef.modelRotationY = 500;
                 itemDef.modelRotationX = 0;
               break;
               
             case 4631:
                 itemDef.modelID = 91621;
                 itemDef.name = "Reality Stone";
                 itemDef.description = "Reality Stone.";
                 itemDef.modelZoom = 1300;
                 itemDef.modelRotationY = 500;
                 itemDef.modelRotationX = 0;
               break;
               
             case 4632:
                 itemDef.modelID = 91621;
                 itemDef.name = "Power Stone";
                 itemDef.description = "Power Stone.";
                 itemDef.modelZoom = 1300;
                 itemDef.modelRotationY = 500;
                 itemDef.modelRotationX = 0;
               break;
               
             case 4633:
                 itemDef.modelID = 91621;
                 itemDef.name = "Time Stone";
                 itemDef.description = "Time Stone.";
                 itemDef.modelZoom = 1300;
                 itemDef.modelRotationY = 500;
                 itemDef.modelRotationX = 0;
               break;
               
             case 4634:
                 itemDef.modelID = 91621;
                 itemDef.name = "Soul Stone";
                 itemDef.description = "Soul Stone.";
                 itemDef.modelZoom = 1300;
                 itemDef.modelRotationY = 500;
                 itemDef.modelRotationX = 0;
               break;
            case 4000:
                itemDef.modelID = 14006;
                itemDef.name = "Eteneral Shard Piece 1";
                itemDef.description = "Eteneral Shard Piece 1";
                itemDef.modelZoom = 1052;
                itemDef.modelRotationY = 320;
                itemDef.modelRotationX = 436;
                break;
            case 4001:
                itemDef.modelID = 14007;
                itemDef.name = "Eteneral Shard Piece 2";
                itemDef.description = "Eteneral Shard Piece 2";
                itemDef.modelZoom = 1052;
                itemDef.modelRotationY = 320;
                itemDef.modelRotationX = 436;
                break;
            case 4002:
                itemDef.modelID = 14008;
                itemDef.name = "Eteneral Shard Piece 3";
                itemDef.description = "Eteneral Shard Piece 3";
                itemDef.modelZoom = 1052;
                itemDef.modelRotationY = 320;
                itemDef.modelRotationX = 436;
                break;
             case 5083:
     			itemDef.modelID = 99032;
     			itemDef.name = "Blessed Sword";
     			itemDef.description = "Blessed Sword";
    			itemDef.modelZoom = 1500;
    			itemDef.modelRotationY = 500;
    			itemDef.modelRotationX = 0;
    			itemDef.modelOffset1 = -1;
    			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 99021;
     			itemDef.femaleWearId = 99021;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     			break;
     			
             case 5084:
      			itemDef.modelID = 99032;
      			itemDef.name = "Blessed Sword Off-Hand";
      			itemDef.description = "Blessed Sword Off-Hand";
    			itemDef.modelZoom = 1500;
    			itemDef.modelRotationY = 500;
    			itemDef.modelRotationX = 0;
    			itemDef.modelOffset1 = -1;
    			itemDef.modelOffsetY = -1;
      			itemDef.maleWearId = 99022;
      			itemDef.femaleWearId = 99022;
      			itemDef.actions = new String[5];
      			itemDef.actions[1] = "Wear";
      			itemDef.actions[4] = "Drop";
      			break;
     			
     		case 5085:
     			itemDef.modelID = 99028;
     			itemDef.name = "Blessed Helmet";
     			itemDef.description = "Blessed set";
                itemDef.modelZoom = 1000;
                itemDef.modelRotationY = 100;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 99016;
     			itemDef.femaleWearId = 99016;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     			break;
     			
     		case 5086:
     			itemDef.modelID = 99029;
     			itemDef.name = "Blessed Body";
     			itemDef.description = "Blessed set";
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 500;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 99017;
     			itemDef.femaleWearId = 99017;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     			break;
     			
     		case 5087:
     			itemDef.modelID = 99030;
     			itemDef.name = "Blessed Legs";
     			itemDef.description = "Blessed set";
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 500;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 99018;
     			itemDef.femaleWearId = 99018;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     			break;
               
     		case 5088:
     			itemDef.modelID = 99031;
     			itemDef.name = "Blessed Gloves";
     			itemDef.description = "Blessed set";
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 500;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 99019;
     			itemDef.femaleWearId = 99019;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     			break;
     			
     		case 5089:
     			itemDef.modelID = 99020;
     			itemDef.name = "Blessed Boots";
     			itemDef.description = "Blessed set";
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 500;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 99020;
     			itemDef.femaleWearId = 99020;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     			break;
     			
     		case 3285:
     			itemDef.modelID = 65433;
     			itemDef.name = "Spyro Helmet";
     			itemDef.description = "Spyro set";
                itemDef.modelZoom = 1000;
                itemDef.modelRotationY = 100;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 65434;
     			itemDef.femaleWearId = 65434;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     			break;
     			
     		case 3286:
     			itemDef.modelID = 65435;
     			itemDef.name = "Spyro Body";
     			itemDef.description = "Spyro set";
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 500;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 65436;
     			itemDef.femaleWearId = 65436;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     			break;
     			
     		case 3287:
     			itemDef.modelID = 65437;
     			itemDef.name = "Spyro Legs";
     			itemDef.description = "Spyro set";
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 500;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 65438;
     			itemDef.femaleWearId = 65438;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     			break;
     			
            case 20645:
                itemDef.name = "Spyro M4a1 mh";
                itemDef.modelID = 94411;
                itemDef.femaleWearId = 94412;
                itemDef.maleWearId = 94412;
                itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
                itemDef.editedModelColor = new int[] {40,31,62,63,64,65,66,67,60,68,69,70,71,72,73};
                itemDef.newModelColor = new int[]    {70,70,70,70,70,70,70,70,70,70,70,70,70,70,70};
                break;
            case 20644:
                itemDef.name = "Spyro M4a1 oh";
                itemDef.modelID = 94411;
                itemDef.femaleWearId = 94413;
                itemDef.maleWearId = 94413;
                itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
                itemDef.editedModelColor = new int[] {40,31,62,63,64,65,66,67,60,68,69,70,71,72,73};
                itemDef.newModelColor = new int[]    {70,70,70,70,70,70,70,70,70,70,70,70,70,70,70};
                break;
                
    		case 3313:
     			itemDef.modelID = 96076;
     			itemDef.name = "Zeus Helmet";
     			itemDef.description = "Zeus set";
                itemDef.modelZoom = 1000;
                itemDef.modelRotationY = 100;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 96077;
     			itemDef.femaleWearId = 96077;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
                itemDef.editedModelColor = new int[] {40};
                itemDef.newModelColor = new int[]     {52};
     			break;
     			
     		case 3314:
     			itemDef.modelID = 96078;
     			itemDef.name = "Zeus Body";
    			itemDef.description = "Zeus set";
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 500;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 96079;
     			itemDef.femaleWearId = 96079;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
                itemDef.editedModelColor = new int[] {40};
                itemDef.newModelColor = new int[]     {52};
     			break;
     			
     		case 3315:
     			itemDef.modelID = 96080;
     			itemDef.name = "Zeus Legs";
    			itemDef.description = "Zeus set";
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 500;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 96081;
     			itemDef.femaleWearId = 96081;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
                itemDef.editedModelColor = new int[] {40};
                itemDef.newModelColor = new int[]     {52};
     			break;
               
     		case 3316:
     			itemDef.modelID = 96082;
     			itemDef.name = "Zeus Hammer";
    			itemDef.description = "Zeus set";
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 500;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 96083;
     			itemDef.femaleWearId = 96083;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
                itemDef.editedModelColor = new int[] {40};
                itemDef.newModelColor = new int[]     {52};
     			break;
     			
     		case 3317:
     			itemDef.modelID = 96084;
     			itemDef.name = "Zeus Shield";
    			itemDef.description = "Zeus set";
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 500;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 96085;
     			itemDef.femaleWearId = 96085;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
                itemDef.editedModelColor = new int[] {40};
                itemDef.newModelColor = new int[]     {52};
     			break;
     			
     		case 4056:
     			itemDef.modelID = 65444;
     			itemDef.name = "madara fan";
    			itemDef.description = "madara fan";
                itemDef.modelZoom = 2000;
                itemDef.modelRotationY = 500;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 65443;
     			itemDef.femaleWearId = 65443;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     			break;
     			
     		case 3318:
     			itemDef.modelID = 94223;
     			itemDef.name = "Link Helmet";
     			itemDef.description = "Link set";
                itemDef.modelZoom = 1000;
                itemDef.modelRotationY = 100;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 94224;
     			itemDef.femaleWearId = 94224;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     	         itemDef.editedModelColor = new int[] {40};
     	        itemDef.newModelColor = new int[]    {61};
     			break;
     			
     		case 3319:
     			itemDef.modelID = 94225;
     			itemDef.name = "Link Body";
    			itemDef.description = "Link set";
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 500;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 94226;
     			itemDef.femaleWearId = 94226;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     		    itemDef.editedModelColor = new int[] {40};
     	        itemDef.newModelColor = new int[]    {61};
     			break;
     			
     		case 3320:
     			itemDef.modelID = 94227;
     			itemDef.name = "Link Legs";
    			itemDef.description = "Link set";
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 500;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 94228;
     			itemDef.femaleWearId = 94228;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     		    itemDef.editedModelColor = new int[] {40};
     	        itemDef.newModelColor = new int[]    {61};
     			break;
     			
     		case 3321:
     			itemDef.modelID = 94221;
     			itemDef.name = "Link Sword";
    			itemDef.description = "Link Sword";
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 500;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 94222;
     			itemDef.femaleWearId = 94222;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     	        itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
                itemDef.newModelColor = new int[]  {60,60,60,60,60,60,60,60,60,60,60,60,60,60};
     			break;
     			
     		case 3322:
     			itemDef.modelID = 13717;
     			itemDef.name = "Link Shield";
    			itemDef.description = "Link set";
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 500;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 13719;
     			itemDef.femaleWearId = 13719;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     	        itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
                itemDef.newModelColor = new int[]  {60,60,60,60,60,60,60,60,60,60,60,60,60,60};
     			break;
     			
     		case 3819:
     			itemDef.modelID = 90884;
     			itemDef.name = "Baphont Helmet";
     			itemDef.description = "Baphont set";
                itemDef.modelZoom = 1000;
                itemDef.modelRotationY = 100;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 90885;
     			itemDef.femaleWearId = 90885;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     	         itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
     	        itemDef.newModelColor = new int[]    {70,70,70,70,70,70,70,70,70,70,70,70,70,70};
     			break;
     			
     		case 3820:
     			itemDef.modelID = 90886;
     			itemDef.name = "Baphont Body";
    			itemDef.description = "Baphont set";
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 500;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 90887;
     			itemDef.femaleWearId = 90887;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     	         itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
     	        itemDef.newModelColor = new int[]    {70,70,70,70,70,70,70,70,70,70,70,70,70,70};
     			break;
     			
     		case 3821:
     			itemDef.modelID = 90888;
     			itemDef.name = "Baphont Legs";
    			itemDef.description = "Baphont set";
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 500;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 90889;
     			itemDef.femaleWearId = 90889;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     	         itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
     	        itemDef.newModelColor = new int[]    {70,70,70,70,70,70,70,70,70,70,70,70,70,70};
     			break;
     			
     		case 3822:
     			itemDef.modelID = 90892;
     			itemDef.name = "Baphont Boots";
    			itemDef.description = "Infery set";
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 500;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 90892;
     			itemDef.femaleWearId = 90892;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     	         itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
     	        itemDef.newModelColor = new int[]    {70,70,70,70,70,70,70,70,70,70,70,70,70,70};
     			break;
     			
     		case 3823:
     			itemDef.modelID = 90890;
     			itemDef.name = "Baphont Gloves";
    			itemDef.description = "Baphont set";
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 500;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 90891;
     			itemDef.femaleWearId = 90891;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     	         itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
     	        itemDef.newModelColor = new int[]    {70,70,70,70,70,70,70,70,70,70,70,70,70,70};
     			break;
     			
    		case 3824:
     			itemDef.modelID = 90893;
     			itemDef.name = "Baphont Cape";
    			itemDef.description = "Baphont set";
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 500;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 90894;
     			itemDef.femaleWearId = 90894;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     	         itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
     	        itemDef.newModelColor = new int[]    {70,70,70,70,70,70,70,70,70,70,70,70,70,70};
     			break;
     			
    		case 3825:
     			itemDef.modelID = 90913;
     			itemDef.name = "Baphont Sword";
    			itemDef.description = "Baphont set";
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 500;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 90914;
     			itemDef.femaleWearId = 90914;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     	         itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
     	        itemDef.newModelColor = new int[]    {56,56,56,56,56,56,56,56,56,56,56,56,56,56};
     			break;
     			
            case 3085:
                itemDef.modelID = 91098;
                itemDef.name = "Mandara (X) Stone";
                itemDef.description = "Mandara (X) Stone.";
                itemDef.modelZoom = 1600;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
              break;
              
     		case 3084:
     			itemDef.modelID = 48932;
     			itemDef.name = "Madara Fan (x)";
    			itemDef.description = "Madara Fan (x)";
                itemDef.modelZoom = 2000;
                itemDef.modelRotationY = 500;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 48933;
     			itemDef.femaleWearId = 48933;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     			break;
     			
     		case 3089:
     			itemDef.modelID = 95139;
     			itemDef.name = "Toxidian Helmet";
     			itemDef.description = "Toxidian set";
                itemDef.modelZoom = 1000;
                itemDef.modelRotationY = 100;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 95138;
     			itemDef.femaleWearId = 95138;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     			break;
     			
     		case 3090:
     			itemDef.modelID = 95141;
     			itemDef.name = "Toxidian Legs";
    			itemDef.description = "Toxidian set";
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 500;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 95140;
     			itemDef.femaleWearId = 95140;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     			break;
     			
     		case 3091:
     			itemDef.modelID = 95143;
     			itemDef.name = "Toxidian Body";
    			itemDef.description = "Toxidian set";
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 500;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 95142;
     			itemDef.femaleWearId = 95142;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     			break;
     			
     		case 3295:
     			itemDef.modelID = 86535;
     			itemDef.name = "Toxidian Boots";
    			itemDef.description = "Toxidian set";
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 500;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 86535;
     			itemDef.femaleWearId = 86535;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     			break;
     			
     		case 3296:
     			itemDef.modelID = 86533;
     			itemDef.name = "Toxidian Gloves";
    			itemDef.description = "Toxidian set";
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 500;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 86534;
     			itemDef.femaleWearId = 86534;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     			break;
     			
     		case 3297:
     			itemDef.modelID = 86537;
     			itemDef.name = "Toxidian Cape";
    			itemDef.description = "Toxidian set";
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 500;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 86536;
     			itemDef.femaleWearId = 86536;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     			break;
     			
     		case 5091:
     			itemDef.modelID = 96178;
     			itemDef.name = "Toxidian h'ween mask";
    			itemDef.description = "Toxidian h'ween mask";
                itemDef.modelZoom = 1100;
                itemDef.modelRotationY = 100;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 96179;
     			itemDef.femaleWearId = 96179;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
                itemDef.editedModelColor = new int[] {40,1,24};
                itemDef.newModelColor = new int[]    {70,55,55};
     			break;
     			
     		case 5090:
     			itemDef.modelID = 91913;
     			itemDef.name = "Black h'ween mask";
    			itemDef.description = "Black h'ween mask";
                itemDef.modelZoom = 1100;
                itemDef.modelRotationY = 100;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 91903;
     			itemDef.femaleWearId = 91903;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     			break;
     			
     		case 5092:
     			itemDef.modelID = 91916;
     			itemDef.name = "Yellow h'ween mask";
    			itemDef.description = "Yellow h'ween mask";
                itemDef.modelZoom = 1100;
                itemDef.modelRotationY = 100;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 91906;
     			itemDef.femaleWearId = 91906;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     			break;
     			
     		case 5093:
     			itemDef.modelID = 91920;
     			itemDef.name = "Pink h'ween mask";
    			itemDef.description = "Pink h'ween mask";
                itemDef.modelZoom = 1100;
                itemDef.modelRotationY = 100;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 91910;
     			itemDef.femaleWearId = 91910;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     			break;
     			
//--------------------------------multi boss sets--------------------------//
     			
     		case 4636:
     			itemDef.modelID = 92032;
     			itemDef.name = "Indian Helmet";
     			itemDef.description = "Indian set";
                itemDef.modelZoom = 1000;
                itemDef.modelRotationY = 100;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 92033;
     			itemDef.femaleWearId = 92033;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     			break;
     			
     		case 4637:
     			itemDef.modelID = 92036;
     			itemDef.name = "Indian Legs";
    			itemDef.description = "Indian set";
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 500;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 92037;
     			itemDef.femaleWearId = 92037;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     			break;
     			
     		case 4638:
     			itemDef.modelID = 92034;
     			itemDef.name = "Indian Body";
    			itemDef.description = "Indian set";
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 500;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 92035;
     			itemDef.femaleWearId = 92035;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     			break;
     			
     		case 4639:
     			itemDef.modelID = 92038;
     			itemDef.name = "Indian Boots";
    			itemDef.description = "Indian set";
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 500;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 92038;
     			itemDef.femaleWearId = 92038;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     			break;
     			
     		case 4640:
     			itemDef.modelID = 96007;
     			itemDef.name = "Indian Bow";
    			itemDef.description = "Indian set";
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 500;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 96006;
     			itemDef.femaleWearId = 96006;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     			break;
     			
     			
//--------------------------------multi 2 boss sets--------------------------//
     			
     		case 4641:
     			itemDef.modelID = 92079;
     			itemDef.name = "Musketeer Helmet";
     			itemDef.description = "Musketeer set";
                itemDef.modelZoom = 1000;
                itemDef.modelRotationY = 100;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 92080;
     			itemDef.femaleWearId = 92080;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     			break;
     			
     		case 4642:
     			itemDef.modelID = 92083;
     			itemDef.name = "Musketeer Legs";
    			itemDef.description = "Musketeer set";
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 500;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 92084;
     			itemDef.femaleWearId = 92084;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     			break;
     			
     		case 4643:
     			itemDef.modelID = 92081;
     			itemDef.name = "Musketeer Body";
    			itemDef.description = "Musketeer set";
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 500;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 92082;
     			itemDef.femaleWearId = 92082;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     			break;
     			
     		case 4644:
     			itemDef.modelID = 92085;
     			itemDef.name = "Musketeer Boots";
    			itemDef.description = "Musketeer set";
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 500;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 92085;
     			itemDef.femaleWearId = 92085;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     			break;
     			
     		case 4645:
     			itemDef.modelID = 96013;
     			itemDef.name = "Musketeer Spear";
    			itemDef.description = "Musketeer set";
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 500;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 96012;
     			itemDef.femaleWearId = 96012;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     			break;
//--------------------------------multi 3 boss sets--------------------------//	
     			
     		case 3064:
     			itemDef.modelID = 94548;
     			itemDef.name = "Death Reaper Helmet";
     			itemDef.description = "Death Reaper set";
                itemDef.modelZoom = 1000;
                itemDef.modelRotationY = 100;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 94549;
     			itemDef.femaleWearId = 94549;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     			break;
     			
     		case 3065:
     			itemDef.modelID = 94552;
     			itemDef.name = "Death Reaper Legs";
    			itemDef.description = "Death Reaper set";
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 500;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 94553;
     			itemDef.femaleWearId = 94553;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     			break;
     			
     		case 3066:
     			itemDef.modelID = 94550;
     			itemDef.name = "Death Reaper Body";
    			itemDef.description = "Death Reaper set";
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 500;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 94551;
     			itemDef.femaleWearId = 94551;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     			break;
     			
    		case 3067:
     			itemDef.modelID = 98553;
     			itemDef.name = "Death Reaper Scythe";
    			itemDef.description = "Death Reaper set";
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 500;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 98554;
     			itemDef.femaleWearId = 98554;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     			break;
     			
    		case 4804:
     			itemDef.modelID = 94579;
     			itemDef.name = "Storm Breaker Spirit shield";
    			itemDef.description = "Storm Breaker Spirit shield";
    	          itemDef.modelZoom = 1616;
                  itemDef.modelRotationY = 396;
                  itemDef.modelRotationX = 1050;
                  itemDef.modelOffsetY = -3;
                  itemDef.modelOffsetX = 4;
     			itemDef.maleWearId = 94578;
     			itemDef.femaleWearId = 94578;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     			  itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
                  itemDef.newModelColor = new int[]  {53,53,53,53,53,53,53,53,53,53,53,53,53,53};
     			break;
     			
    		case 4805:
     			itemDef.modelID = 94579;
     			itemDef.name = "Aquanium Spirit shield";
    			itemDef.description = "Aquanium Spirit shield";
                itemDef.modelZoom = 1616;
                itemDef.modelRotationY = 396;
                itemDef.modelRotationX = 1050;
                itemDef.modelOffsetY = -3;
                itemDef.modelOffsetX = 4;
     			itemDef.maleWearId = 94578;
     			itemDef.femaleWearId = 94578;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     		     itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
                itemDef.newModelColor = new int[]  {90,90,90,90,90,90,90,90,90,90,90,90,90,90};
                break;
     			
    		case 4806:
     			itemDef.modelID = 94579;
     			itemDef.name = "Destuction Spirit shield";
    			itemDef.description = "Destuction Spirit shield";
                itemDef.modelZoom = 1616;
                itemDef.modelRotationY = 396;
                itemDef.modelRotationX = 1050;
                itemDef.modelOffsetY = -3;
                itemDef.modelOffsetX = 4;
     			itemDef.maleWearId = 94578;
     			itemDef.femaleWearId = 94578;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     	         itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
                 itemDef.newModelColor = new int[]  {90,90,90,90,90,90,90,90,90,90,90,90,90,90};
     			break;
     			
    		case 455:
     			itemDef.name = "ImaginePS Scratch Card";
    			break;

            case 14876:
                itemDef.name = "Mysterious Statuette";
                itemDef.description = "I better rub it quick!";
               // itemDef.modelZoom = 1616;
//                itemDef.rotationY = 396;
//                itemDef.rotationX = 1050;
                itemDef.modelOffsetY = -3;
                itemDef.modelOffsetX = 4;
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Rub";
                itemDef.actions[4] = "Drop";
                itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
                itemDef.newModelColor = new int[]  {90,90,90,90,90,90,90,90,90,90,90,90,90,90};
                break;
//--------------------------------Raids sets--------------------------//
     			
     		case 4646:
     			itemDef.modelID = 84183;
     			itemDef.name = "pennywise Helmet";
     			itemDef.description = "pennywise set";
                itemDef.modelZoom = 900;
                itemDef.modelRotationY = 100;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 84184;
     			itemDef.femaleWearId = 84184;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     			break;
     			
     		case 4647:
     			itemDef.modelID = 84185;
     			itemDef.name = "pennywise Legs";
     			itemDef.description = "pennywise set";
                itemDef.modelZoom = 1800;
                itemDef.modelRotationY = 500;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 84186;
     			itemDef.femaleWearId = 84186;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     			break;
     			
     		case 4648:
     			itemDef.modelID = 84179;
     			itemDef.name = "pennywise Body";
     			itemDef.description = "pennywise set";
     		     itemDef.modelZoom = 1500;
                itemDef.modelRotationY = 500;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 84180;
     			itemDef.femaleWearId = 84180;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     			break;
     			
    		case 4649:
     			itemDef.modelID = 84181;
     			itemDef.name = "pennywise Gloves";
     			itemDef.description = "pennywise set";
    		     itemDef.modelZoom = 1000;
                 itemDef.modelRotationY = 500;
      			itemDef.modelRotationX = 0;
      			itemDef.modelOffset1 = -1;
      			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 84182;
     			itemDef.femaleWearId = 84182;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     			break;
     			
    		case 4650:
     			itemDef.modelID = 84177;
     			itemDef.name = "pennywise Boots";
     			itemDef.description = "pennywise set";
     		     itemDef.modelZoom = 1000;
                 itemDef.modelRotationY = 500;
      			itemDef.modelRotationX = 0;
      			itemDef.modelOffset1 = -1;
      			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 84178;
     			itemDef.femaleWearId = 84178;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";   
     			break;
     			
    		case 4652:
     			itemDef.modelID = 86643;
     			itemDef.name = "Red balloon";
     			itemDef.description = "Red balloon";
     		     itemDef.modelZoom = 1700;
                 itemDef.modelRotationY = 500;
      			itemDef.modelRotationX = 0;
      			itemDef.modelOffset1 = -1;
      			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 86643;
     			itemDef.femaleWearId = 86643;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";   
     			break;
     			
    		case 4651:
     			itemDef.modelID = 56746;
     			itemDef.name = "chucky doll";
     			itemDef.description = "chucky doll";
     		     itemDef.modelZoom = 1000;
                 itemDef.modelRotationY = 500;
      			itemDef.modelRotationX = 0;
      			itemDef.modelOffset1 = -1;
      			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 84176;
     			itemDef.femaleWearId = 84176;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";   
     			break;
     			
     		case 4281:
     			itemDef.modelID = 99144;
     			itemDef.name = "Infernal Cape";
     			itemDef.description = "Infernal Cape";
     		     itemDef.modelZoom = 1800;
                 itemDef.modelRotationY = 450;
      			itemDef.modelRotationX = 0;
      			itemDef.modelOffset1 = -1;
      			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 99111;
     			itemDef.femaleWearId = 99111;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";   
     			break;
     			
    		case 4743:
     			itemDef.modelID = 94301;
     			itemDef.name = "Spirity Ring";
     			itemDef.description = "Spirity Ring";
     		     itemDef.modelZoom = 820;
                 itemDef.modelRotationY = 450;
      			itemDef.modelRotationX = 0;
      			itemDef.modelOffset1 = -1;
      			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 94301;
     			itemDef.femaleWearId = 94301;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";   
     			break;
     			
    		case 4744:
     			itemDef.modelID = 96736;
     			itemDef.name = "Spirity Neckles";
     			itemDef.description = "Spirity Neckles";
     		     itemDef.modelZoom = 650;
                 itemDef.modelRotationY = 500;
      			itemDef.modelRotationX = 0;
      			itemDef.modelOffset1 = -1;
      			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 96735;
     			itemDef.femaleWearId = 96735;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";   
     			break;
     			
     		case 4761:
     			itemDef.modelID = 94605;
     			itemDef.name = "Heaven Sword";
     			itemDef.description = "Heaven Sword";
     		     itemDef.modelZoom = 1450;
                 itemDef.modelRotationY = 500;
      			itemDef.modelRotationX = 0;
      			itemDef.modelOffset1 = -1;
      			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 94606;
     			itemDef.femaleWearId = 94606;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";   
     			  itemDef.editedModelColor = new int[] {60,61,62,63,64,65,66,67,68,69,70,71,72,73};
                  itemDef.newModelColor = new int[]  {56,56,56,56,56,56,56,56,56,56,56,56,56,56};
     			break;   
     			
 	//----------------------------------------Donator set---------------------------------------//
     			
     		case 5111:
     			itemDef.modelID = 97844;
     			itemDef.name = "Executive Beast Helmet";
     			itemDef.description = "Executive Beast set";
                itemDef.modelZoom = 900;
                itemDef.modelRotationY = 100;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 97843;
     			itemDef.femaleWearId = 97843;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     			break;
     			
     		case 5112:
     			itemDef.modelID = 97848;
     			itemDef.name = "Executive Beast Legs";
     			itemDef.description = "Executive Beast set";
                itemDef.modelZoom = 1800;
                itemDef.modelRotationY = 500;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 97847;
     			itemDef.femaleWearId = 97847;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     			break;
     			
     		case 5113:
     			itemDef.modelID = 97846;
     			itemDef.name = "Executive Beast Body";
     			itemDef.description = "Executive Beast set";
     		     itemDef.modelZoom = 1500;
                itemDef.modelRotationY = 500;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 97845;
     			itemDef.femaleWearId = 97845;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     			break;
     			
    		case 5114:
     			itemDef.modelID = 97852;
     			itemDef.name = "Executive Beast Gloves";
     			itemDef.description = "Executive Beast set";
    		     itemDef.modelZoom = 1000;
                 itemDef.modelRotationY = 500;
      			itemDef.modelRotationX = 0;
      			itemDef.modelOffset1 = -1;
      			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 97851;
     			itemDef.femaleWearId = 97851;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     			break;
     			
    		case 5115:
     			itemDef.modelID = 97853;
     			itemDef.name = "Executive Beast Boots";
     			itemDef.description = "Executive Beast set";
     		     itemDef.modelZoom = 1000;
                 itemDef.modelRotationY = 500;
      			itemDef.modelRotationX = 0;
      			itemDef.modelOffset1 = -1;
      			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 97853;
     			itemDef.femaleWearId = 97853;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";   
     			break;
     			
      		case 5116:
     			itemDef.modelID = 97850;
     			itemDef.name = "Executive Beast Axe";
     			itemDef.description = "Executive Beast set";
     		     itemDef.modelZoom = 1000;
                 itemDef.modelRotationY = 500;
      			itemDef.modelRotationX = 0;
      			itemDef.modelOffset1 = -1;
      			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 97849;
     			itemDef.femaleWearId = 97849;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";   
     			break;
     			
    			
      		case 3850:
     			itemDef.modelID = 86755;
     			itemDef.name = "Executive Glaive";
     			itemDef.description = "Executive Glaive";
     		     itemDef.modelZoom = 1900;
                 itemDef.modelRotationY = 500;
      			itemDef.modelRotationX = 0;
      			itemDef.modelOffset1 = -1;
      			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 86754;
     			itemDef.femaleWearId = 86754;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";   
     			break;
     			
       		case 3851:
     			itemDef.modelID = 86755;
     			itemDef.name = "Executive Glaive Off-Hand";
     			itemDef.description = "Executive Glaive Off-Hand";
     		     itemDef.modelZoom = 1900;
                 itemDef.modelRotationY = 500;
      			itemDef.modelRotationX = 0;
      			itemDef.modelOffset1 = -1;
      			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 86756;
     			itemDef.femaleWearId = 86756;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";   
     			break;
     			
       		case 4765:
     			itemDef.modelID = 75638;
     			itemDef.name = "Sora Helmet";
     			itemDef.description = "Sora set";
                itemDef.modelZoom = 900;
                itemDef.modelRotationY = 100;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 75637;
     			itemDef.femaleWearId = 75637;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     			break;
     			
     		case 4766:
     			itemDef.modelID = 75690;
     			itemDef.name = "Sora Legs";
     			itemDef.description = "Sora set";
                itemDef.modelZoom = 1800;
                itemDef.modelRotationY = 500;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 75635;
     			itemDef.femaleWearId = 75635;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     			break;
     			
     		case 4767:
     			itemDef.modelID = 75689;
     			itemDef.name = "Sora Body";
     			itemDef.description = "Sora set";
     		     itemDef.modelZoom = 1500;
                itemDef.modelRotationY = 500;
     			itemDef.modelRotationX = 0;
     			itemDef.modelOffset1 = -1;
     			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 75636;
     			itemDef.femaleWearId = 75636;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     			break;
     			
    		case 4768:
     			itemDef.modelID = 75686;
     			itemDef.name = "Sora Gloves";
     			itemDef.description = "Sora set";
    		     itemDef.modelZoom = 1000;
                 itemDef.modelRotationY = 500;
      			itemDef.modelRotationX = 0;
      			itemDef.modelOffset1 = -1;
      			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 75633;
     			itemDef.femaleWearId = 75633;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";
     			break;
     			
    		case 4769:
     			itemDef.modelID = 75634;
     			itemDef.name = "Sora Boots";
     			itemDef.description = "Sora set";
     		     itemDef.modelZoom = 1000;
                 itemDef.modelRotationY = 500;
      			itemDef.modelRotationX = 0;
      			itemDef.modelOffset1 = -1;
      			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 75634;
     			itemDef.femaleWearId = 75634;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";   
     			break;
     			
    		case 4771:
     			itemDef.modelID = 75687;
     			itemDef.name = "Sora Neckles";
     			itemDef.description = "Sora Neckles";
     		     itemDef.modelZoom = 450;
                 itemDef.modelRotationY = 500;
      			itemDef.modelRotationX = 0;
      			itemDef.modelOffset1 = -1;
      			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 75665;
     			itemDef.femaleWearId = 75665;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";   
     			break;
     			
    		case 4772:
     			itemDef.modelID = 86545;
     			itemDef.name = "Key Blade";
     			itemDef.description = "Key Blade";
     		     itemDef.modelZoom = 1500;
                 itemDef.modelRotationY = 400;
      			itemDef.modelRotationX = 0;
      			itemDef.modelOffset1 = -1;
      			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 86544;
     			itemDef.femaleWearId = 86544;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";   
     			break;
     			
    		case 4770:
     			itemDef.modelID = 42076;
     			itemDef.name = "Sharingan Aura";
     			itemDef.description = "Sharingan Aura";
     		     itemDef.modelZoom = 1600;
                 itemDef.modelRotationY = 500;
      			itemDef.modelRotationX = 0;
      			itemDef.modelOffset1 = -1;
      			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 42076;
     			itemDef.femaleWearId = 42076;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";   
     			break;
     			
    		case 5228:
     			itemDef.modelID = 42077;
     			itemDef.name = "Sharingan Aura (X)";
     			itemDef.description = "Sharingan Aura (X)";
     		     itemDef.modelZoom = 1600;
                 itemDef.modelRotationY = 500;
      			itemDef.modelRotationX = 0;
      			itemDef.modelOffset1 = -1;
      			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 42077;
     			itemDef.femaleWearId = 42077;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";   
     			break;
     			
    		case 5120:
     			itemDef.modelID = 91871;
     			itemDef.name = "Slayer Master Helmet";
     			itemDef.description = "Slayer Master Helmet";
                itemDef.modelZoom = 900;
                itemDef.modelRotationY = 100;
     			itemDef.modelRotationX = 0;
      			itemDef.modelOffset1 = -1;
      			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 91872;
     			itemDef.femaleWearId = 91872;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";   
     			break;
     			
    		case 5121:
     			itemDef.modelID = 91875;
     			itemDef.name = "Slayer Master Body";
     			itemDef.description = "Slayer Master Body";
    		     itemDef.modelZoom = 1500;
                 itemDef.modelRotationY = 500;
      			itemDef.modelRotationX = 0;
      			itemDef.modelOffset1 = -1;
      			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 91876;
     			itemDef.femaleWearId = 91876;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";   
     			break;
     			
    		case 5122:
     			itemDef.modelID = 91873;
     			itemDef.name = "Slayer Master Legs";
     			itemDef.description = "Slayer Master Legs";
    		     itemDef.modelZoom = 1500;
                 itemDef.modelRotationY = 500;
      			itemDef.modelRotationX = 0;
      			itemDef.modelOffset1 = -1;
      			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 91874;
     			itemDef.femaleWearId = 91874;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";   
     			break;
     			
    		case 5123:
     			itemDef.modelID = 91869;
     			itemDef.name = "Slayer Master Cape";
     			itemDef.description = "Slayer Master Cape";
     		     itemDef.modelZoom = 1600;
                 itemDef.modelRotationY = 500;
      			itemDef.modelRotationX = 0;
      			itemDef.modelOffset1 = -1;
      			itemDef.modelOffsetY = -1;
     			itemDef.maleWearId = 91870;
     			itemDef.femaleWearId = 91870;
     			itemDef.actions = new String[5];
     			itemDef.actions[1] = "Wear";
     			itemDef.actions[4] = "Drop";   
     			break;
     			
            case 4762:
                itemDef.modelID = 94416;
                itemDef.name = "Aqantium Cape";
                itemDef.description = "Aqantium Cape.";
                itemDef.modelZoom = 900;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.membersObject = true;
                itemDef.maleWearId = 94417;
                itemDef.femaleWearId = 94417;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.stackable = false;
                itemDef.editedModelColor = new int[] {54};
                itemDef.newModelColor = new int[]    {70};
                break;
                
            case 4763:
                itemDef.modelID = 94416;
                itemDef.name = "Grndmaster Cape";
                itemDef.description = "Grndmaster Cape.";
                itemDef.modelZoom = 900;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.membersObject = true;
                itemDef.maleWearId = 94417;
                itemDef.femaleWearId = 94417;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.stackable = false;
                itemDef.editedModelColor = new int[] {54};
                itemDef.newModelColor = new int[]    {86};
                break;
                
            case 4764:
                itemDef.modelID = 94416;
                itemDef.name = "Destuction Cape";
                itemDef.description = "Destuction Cape.";
                itemDef.modelZoom = 900;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.membersObject = true;
                itemDef.maleWearId = 94417;
                itemDef.femaleWearId = 94417;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.stackable = false;
                itemDef.editedModelColor = new int[] {54};
                itemDef.newModelColor = new int[]    {90};
                break;
                
            case 4787:
                itemDef.modelID = 94416;
                itemDef.name = "Space Cape";
                itemDef.description = "Space Cape.";
                itemDef.modelZoom = 900;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.membersObject = true;
                itemDef.maleWearId = 94417;
                itemDef.femaleWearId = 94417;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.actions[4] = "Drop";
                itemDef.stackable = false;
                itemDef.editedModelColor = new int[] {54};
                itemDef.newModelColor = new int[]    {55};
                break;
                
            case 18782:
                itemDef.modelID = 94378;
                itemDef.name = "Energy Blast";
                itemDef.description = "Energy Blast.";
                itemDef.modelZoom = 1000;
                itemDef.modelRotationY = 10;
                itemDef.modelRotationX = 0;
                break;
       			
    		case 5152:
       			itemDef.modelID = 57550;
       			itemDef.name = "voldemort Head";
       			itemDef.description = "voldemort set";
                itemDef.modelZoom = 900;
                itemDef.modelRotationY = 100;
       			itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
       			itemDef.maleWearId = 57544;
       			itemDef.femaleWearId = 57544;
       			itemDef.actions = new String[5];
       			itemDef.actions[1] = "Wear";
       			itemDef.actions[4] = "Drop"; 
                itemDef.editedModelColor = new int[] {54};
                itemDef.newModelColor = new int[]    {57};
       			break;
       			
       		case 5153:
       			itemDef.modelID = 57551;
       			itemDef.name = "voldemort Body";
       			itemDef.description = "voldemort set";
       		    itemDef.modelZoom = 1500;
                itemDef.modelRotationY = 500;
       			itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
       			itemDef.maleWearId = 57545;
       			itemDef.femaleWearId = 57545;
       			itemDef.actions = new String[5];
       			itemDef.actions[1] = "Wear";
       			itemDef.actions[4] = "Drop"; 
                itemDef.editedModelColor = new int[] {54};
                itemDef.newModelColor = new int[]    {57};
       			break;
       			
       		case 5154:
       			itemDef.modelID = 57552;
       			itemDef.name = "voldemort Legs";
       			itemDef.description = "voldemort set";
       		    itemDef.modelZoom = 1800;
                itemDef.modelRotationY = 500;
       			itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
       			itemDef.maleWearId = 57546;
       			itemDef.femaleWearId = 57546;
       			itemDef.actions = new String[5];
       			itemDef.actions[1] = "Wear";
       			itemDef.actions[4] = "Drop"; 
                itemDef.editedModelColor = new int[] {54};
                itemDef.newModelColor = new int[]    {57};
       			break;
       			
       		case 5155:
       			itemDef.modelID = 57553;
       			itemDef.name = "voldemort Gloves";
       			itemDef.description = "voldemort set";
       		    itemDef.modelZoom = 1000;
                itemDef.modelRotationY = 500;
        	    itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
       			itemDef.maleWearId = 57547;
       			itemDef.femaleWearId = 57547;
       			itemDef.actions = new String[5];
       			itemDef.actions[1] = "Wear";
       			itemDef.actions[4] = "Drop"; 
                itemDef.editedModelColor = new int[] {54};
                itemDef.newModelColor = new int[]    {57};
       			break;
       			
       		case 5156:
       			itemDef.modelID = 57548;
       			itemDef.name = "voldemort Boots";
       			itemDef.description = "voldemort set";
       		    itemDef.modelZoom = 1000;
                itemDef.modelRotationY = 500;
        	    itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
       			itemDef.maleWearId = 57548;
       			itemDef.femaleWearId = 57548;
       			itemDef.actions = new String[5];
       			itemDef.actions[1] = "Wear";
       			itemDef.actions[4] = "Drop"; 
                itemDef.editedModelColor = new int[] {54};
                itemDef.newModelColor = new int[]    {57};
       			break;
       			
       		case 5157:
       			itemDef.modelID = 57554;
       			itemDef.name = "voldemort Staff";
       			itemDef.description = "voldemort set";
       		    itemDef.modelZoom = 1000;
                itemDef.modelRotationY = 500;
        	    itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
       			itemDef.maleWearId = 57549;
       			itemDef.femaleWearId = 57549;
       			itemDef.actions = new String[5];
       			itemDef.actions[1] = "Wear";
       			itemDef.actions[4] = "Drop"; 
                itemDef.editedModelColor = new int[] {54};
                itemDef.newModelColor = new int[]    {57};
       			break;
       			
      		case 3576:
       			itemDef.name = "@mag@Sponsor Ticket GoodieBox";
       			break;
       			
      		case 3569:
       			itemDef.name = "@red@Bfg-9000 GoodieBox";
       			break;
            case 3570:
                itemDef.name = "@cya@Eternal GoodieBox";
                itemDef2 = ItemDef.forID(3569);
                itemDef.modelID = itemDef2.modelID;
                break;
//            case 6199:
//                itemDef.name = "@cyan@Money Casket";
//                itemDef.modelID = 130069;
//                break;

            case 5140:
       			itemDef.modelID = 95619;
       			itemDef.name = "Hell Malevolent Head";
       			itemDef.description = "Hell Malevolent set";
                itemDef.modelZoom = 900;
                itemDef.modelRotationY = 100;
       			itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
       			itemDef.maleWearId = 95620;
       			itemDef.femaleWearId = 95620;
       			itemDef.actions = new String[5];
       			itemDef.actions[1] = "Wear";
       			itemDef.actions[4] = "Drop"; 
                itemDef.editedModelColor = new int[] {40};
                itemDef.newModelColor = new int[]  {51};
       			break;
       			
       		case 5142:
       			itemDef.modelID = 96521;
       			itemDef.name = "Hell Malevolent Body";
       			itemDef.description = "Hell Malevolent set";
       		    itemDef.modelZoom = 1500;
                itemDef.modelRotationY = 500;
       			itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
       			itemDef.maleWearId = 96522;
       			itemDef.femaleWearId = 96522;
       			itemDef.actions = new String[5];
       			itemDef.actions[1] = "Wear";
       			itemDef.actions[4] = "Drop"; 
                itemDef.editedModelColor = new int[] {40};
                itemDef.newModelColor = new int[]  {51};
       			break;
       			
       		case 5141:
       			itemDef.modelID = 95623;
       			itemDef.name = "Hell Malevolent Legs";
       			itemDef.description = "Hell Malevolent set";
       		    itemDef.modelZoom = 1800;
                itemDef.modelRotationY = 500;
       			itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
       			itemDef.maleWearId = 95624;
       			itemDef.femaleWearId = 95624;
       			itemDef.actions = new String[5];
       			itemDef.actions[1] = "Wear";
       			itemDef.actions[4] = "Drop"; 
                itemDef.editedModelColor = new int[] {40};
                itemDef.newModelColor = new int[]  {51};
       			break;
       			
       		case 5143:
       			itemDef.modelID = 95625;
       			itemDef.name = "Hell Malevolent Gloves";
       			itemDef.description = "Hell Malevolent set";
       		    itemDef.modelZoom = 1000;
                itemDef.modelRotationY = 500;
        	    itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
       			itemDef.maleWearId = 95626;
       			itemDef.femaleWearId = 95626;
       			itemDef.actions = new String[5];
       			itemDef.actions[1] = "Wear";
       			itemDef.actions[4] = "Drop"; 
                itemDef.editedModelColor = new int[] {40};
                itemDef.newModelColor = new int[]  {51};
       			break;
       			
       		case 5144:
       			itemDef.modelID = 95627;
       			itemDef.name = "Hell Malevolent Boots";
       			itemDef.description = "Hell Malevolent set";
       		    itemDef.modelZoom = 1000;
                itemDef.modelRotationY = 500;
        	    itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
       			itemDef.maleWearId = 95627;
       			itemDef.femaleWearId = 95627;
       			itemDef.actions = new String[5];
       			itemDef.actions[1] = "Wear";
       			itemDef.actions[4] = "Drop"; 
                itemDef.editedModelColor = new int[] {40};
                itemDef.newModelColor = new int[]  {51};
       			break;		
       			
    		case 5146:
       			itemDef.modelID = 96526;
       			itemDef.name = "Hell Malevolent Sword";
       			itemDef.description = "Hell Malevolent set";
       		    itemDef.modelZoom = 1000;
                itemDef.modelRotationY = 500;
        	    itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
       			itemDef.maleWearId = 96527;
       			itemDef.femaleWearId = 96527;
       			itemDef.actions = new String[5];
       			itemDef.actions[1] = "Wear";
       			itemDef.actions[4] = "Drop"; 
                itemDef.editedModelColor = new int[] {40};
                itemDef.newModelColor = new int[]  {51};
       			break;	
       			
    		case 5147:
       			itemDef.modelID = 96526;
       			itemDef.name = "Hell Malevolent Sword Off-Hand";
       			itemDef.description = "Hell Malevolent set";
       		    itemDef.modelZoom = 1000;
                itemDef.modelRotationY = 500;
        	    itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
       			itemDef.maleWearId = 96528;
       			itemDef.femaleWearId = 96528;
       			itemDef.actions = new String[5];
       			itemDef.actions[1] = "Wear";
       			itemDef.actions[4] = "Drop"; 
                itemDef.editedModelColor = new int[] {40};
                itemDef.newModelColor = new int[]  {51};
       			break;	
       			
    		case 5148:
       			itemDef.modelID = 94189;
       			itemDef.name = "Hell Malevolent Cape";
       			itemDef.description = "Hell Malevolent set";
       		    itemDef.modelZoom = 1900;
                itemDef.modelRotationY = 500;
        	    itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
       			itemDef.maleWearId = 94190;
       			itemDef.femaleWearId = 94190;
       			itemDef.actions = new String[5];
       			itemDef.actions[1] = "Wear";
       			itemDef.actions[4] = "Drop"; 
                itemDef.editedModelColor = new int[] {57};
                itemDef.newModelColor = new int[]  {51};
       			break;	
//---------------------ICY GOLEM-------------------------//
       			
       		case 5163:
       			itemDef.modelID = 96510;
       			itemDef.name = "Icy Golem Head";
       			itemDef.description = "Icy Golem set";
                itemDef.modelZoom = 900;
                itemDef.modelRotationY = 100;
       			itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
       			itemDef.maleWearId = 96511;
       			itemDef.femaleWearId = 96511;
       			itemDef.actions = new String[5];
       			itemDef.actions[1] = "Wear";
       			itemDef.actions[4] = "Drop"; 
                itemDef.editedModelColor = new int[] {40};
                itemDef.newModelColor = new int[]  {51};
       			break;
       			
       		case 5164:
       			itemDef.modelID = 96512;
       			itemDef.name = "Icy Golem Body";
       			itemDef.description = "Icy Golem set";
       		    itemDef.modelZoom = 1500;
                itemDef.modelRotationY = 500;
       			itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
       			itemDef.maleWearId = 96513;
       			itemDef.femaleWearId = 96513;
       			itemDef.actions = new String[5];
       			itemDef.actions[1] = "Wear";
       			itemDef.actions[4] = "Drop"; 
                itemDef.editedModelColor = new int[] {40};
                itemDef.newModelColor = new int[]  {51};
       			break;
       			
       		case 5165:
       			itemDef.modelID = 96514;
       			itemDef.name = "Icy Golem Legs";
       			itemDef.description = "Icy Golem set";
       		    itemDef.modelZoom = 1800;
                itemDef.modelRotationY = 500;
       			itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
       			itemDef.maleWearId = 96515;
       			itemDef.femaleWearId = 96515;
       			itemDef.actions = new String[5];
       			itemDef.actions[1] = "Wear";
       			itemDef.actions[4] = "Drop"; 
                itemDef.editedModelColor = new int[] {40};
                itemDef.newModelColor = new int[]  {51};
       			break;
       			
       		case 5166:
       			itemDef.modelID = 96516;
       			itemDef.name = "Icy Golem Gloves";
       			itemDef.description = "Icy Golem set";
       		    itemDef.modelZoom = 1000;
                itemDef.modelRotationY = 500;
        	    itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
       			itemDef.maleWearId = 96517;
       			itemDef.femaleWearId = 96517;
       			itemDef.actions = new String[5];
       			itemDef.actions[1] = "Wear";
       			itemDef.actions[4] = "Drop"; 
                itemDef.editedModelColor = new int[] {40};
                itemDef.newModelColor = new int[]  {51};
       			break;
       			
       		case 5167:
       			itemDef.modelID = 96518;
       			itemDef.name = "Icy Golem Boots";
       			itemDef.description = "Icy Golem set";
       		    itemDef.modelZoom = 1000;
                itemDef.modelRotationY = 500;
        	    itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
       			itemDef.maleWearId = 96518;
       			itemDef.femaleWearId = 96518;
       			itemDef.actions = new String[5];
       			itemDef.actions[1] = "Wear";
       			itemDef.actions[4] = "Drop"; 
                itemDef.editedModelColor = new int[] {40};
                itemDef.newModelColor = new int[]  {51};
       			break;	
       			
       		case 5168:
       			itemDef.modelID = 90311;
       			itemDef.name = "Icy Vorago Claws";
       			itemDef.description = "Icy Golem set";
       		    itemDef.modelZoom = 1200;
                itemDef.modelRotationY = 500;
        	    itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
       			itemDef.maleWearId = 90310;
       			itemDef.femaleWearId = 90310;
       			itemDef.actions = new String[5];
       			itemDef.actions[1] = "Wear";
       			itemDef.actions[4] = "Drop"; 
                itemDef.editedModelColor = new int[] {40};
                itemDef.newModelColor = new int[]  {51};
       			break;	
       			
       		case 2543:
       			itemDef.modelID = 86742;
       			itemDef.name = "Divine Cape";
       			itemDef.description = "Divine Cape";
       		    itemDef.modelZoom = 1900;
                itemDef.modelRotationY = 500;
        	    itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
       			itemDef.maleWearId = 86743;
       			itemDef.femaleWearId = 86743;
       			itemDef.actions = new String[5];
       			itemDef.actions[1] = "Wear";
       			itemDef.actions[4] = "Drop"; 
                itemDef.editedModelColor = new int[] {57};
                itemDef.newModelColor = new int[]  {51};
       			break;	
       			
       		case 4799:
       			itemDef.modelID = 91865;
       			itemDef.name = "Divine Angel Helmet";
       			itemDef.description = "Divine set";
                itemDef.modelZoom = 900;
                itemDef.modelRotationY = 100;
       			itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
       			itemDef.maleWearId = 91864;
       			itemDef.femaleWearId = 91864;
       			itemDef.actions = new String[5];
       			itemDef.actions[1] = "Wear";
       			itemDef.actions[4] = "Drop"; 
                itemDef.editedModelColor = new int[] {57};
                itemDef.newModelColor = new int[]  {51};
       			break;	
       			
       		case 4800:
       			itemDef.modelID = 91869;
       			itemDef.name = "Divine Angel Body";
       			itemDef.description = "Divine set";
       		    itemDef.modelZoom = 1500;
                itemDef.modelRotationY = 500;
       			itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
       			itemDef.maleWearId = 91868;
       			itemDef.femaleWearId = 91868;
       			itemDef.actions = new String[5];
       			itemDef.actions[1] = "Wear";
       			itemDef.actions[4] = "Drop"; 
                itemDef.editedModelColor = new int[] {57};
                itemDef.newModelColor = new int[]  {51};
       			break;	
       			
       		case 4801:
       			itemDef.modelID = 91867;
       			itemDef.name = "Divine Angel Legs";
       			itemDef.description = "Divine set";
       		    itemDef.modelZoom = 1700;
                itemDef.modelRotationY = 500;
        	    itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
       			itemDef.maleWearId = 91866;
       			itemDef.femaleWearId = 91866;
       			itemDef.actions = new String[5];
       			itemDef.actions[1] = "Wear";
       			itemDef.actions[4] = "Drop"; 
                itemDef.editedModelColor = new int[] {57};
                itemDef.newModelColor = new int[]  {51};
       			break;	
       			
     		case 4802:
       			itemDef.modelID = 73487;
       			itemDef.name = "Divine Thumper";
       			itemDef.description = "Divine set";
       		    itemDef.modelZoom = 1500;
                itemDef.modelRotationY = 500;
       			itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
       			itemDef.maleWearId = 73488;
       			itemDef.femaleWearId = 73488;
       			itemDef.actions = new String[5];
       			itemDef.actions[1] = "Wear";
       			itemDef.actions[4] = "Drop"; 
                itemDef.editedModelColor = new int[] {57};
                itemDef.newModelColor = new int[]  {51};
       			break;	
       			
      		case 5000:
       			itemDef.modelID = 65467;
       			itemDef.name = "@yel@Divine Top Donator Icon";
       			itemDef.description = "Divine set";
       		    itemDef.modelZoom = 1950;
                itemDef.modelRotationY = 500;
        	    itemDef.modelRotationX = 1500;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
       			itemDef.maleWearId = 65468;
       			itemDef.femaleWearId = 65468;
       			itemDef.actions = new String[5];
       			itemDef.actions[1] = "Wear";
       			itemDef.actions[4] = "Drop"; 
                itemDef.editedModelColor = new int[] {57};
                itemDef.newModelColor = new int[]  {51};
       			break;	
       			
//--------------------------------//Upgrade Boots//----------------------------------------------//
   
     		case 12540:
       			itemDef.modelID = 1;
       			itemDef.name = "Berserker Boots (Melee)";
       			itemDef.description = "Berserker Boots";
                itemDef.modelZoom = 900;
                itemDef.modelRotationY = 165;
                itemDef.modelRotationX = 99;
                itemDef.modelOffset1 = 3;
                itemDef.modelOffsetY = -7;
       			itemDef.maleWearId = 1;
       			itemDef.femaleWearId = 1;
       			itemDef.actions = new String[5];
       			itemDef.actions[1] = "Wear";
       			itemDef.actions[4] = "Drop";
       			break;	
       			
     		case 12541:
       			itemDef.modelID = 94622;
       			itemDef.name = "Seers Boots (Mage)";
       			itemDef.description = "Seers Boots";
                itemDef.modelZoom = 900;
                itemDef.modelRotationY = 165;
                itemDef.modelRotationX = 99;
                itemDef.modelOffset1 = 3;
                itemDef.modelOffsetY = -7;
       			itemDef.maleWearId = 94622;
       			itemDef.femaleWearId = 94622;
       			itemDef.actions = new String[5];
       			itemDef.actions[1] = "Wear";
       			itemDef.actions[4] = "Drop"; 
                itemDef.editedModelColor = new int[] {60};
                itemDef.newModelColor = new int[]  {63};
       			break;	
       			
     		case 12542:
       			itemDef.modelID = 94622;
       			itemDef.name = "Archer Boots (Range)";
       			itemDef.description = "Archer Boots";
                itemDef.modelZoom = 900;
                itemDef.modelRotationY = 165;
                itemDef.modelRotationX = 99;
                itemDef.modelOffset1 = 3;
                itemDef.modelOffsetY = -7;
       			itemDef.maleWearId = 94622;
       			itemDef.femaleWearId = 94622;
       			itemDef.actions = new String[5];
       			itemDef.actions[1] = "Wear";
       			itemDef.actions[4] = "Drop"; 
                itemDef.editedModelColor = new int[] {60};
                itemDef.newModelColor = new int[]  {66};
       			break;	
       			
       		  //--------------------------JOKER--------------------------------------//
     		case 13728:
       			itemDef.modelID = 64497;
       			itemDef.name = "Joker Helmet";
       			itemDef.description = "Joker set";
                itemDef.modelZoom = 900;
                itemDef.modelRotationY = 100;
       			itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
       			itemDef.maleWearId = 64498;
       			itemDef.femaleWearId = 64498;
       			itemDef.actions = new String[5];
       			itemDef.actions[1] = "Wear";
       			itemDef.actions[4] = "Drop"; 
       			break;	
       			
       		case 13729:
       			itemDef.modelID = 64499;
       			itemDef.name = "Joker Body";
       			itemDef.description = "Joker set";
       		    itemDef.modelZoom = 1500;
                itemDef.modelRotationY = 500;
       			itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
       			itemDef.maleWearId = 64500;
       			itemDef.femaleWearId = 64500;
       			itemDef.actions = new String[5];
       			itemDef.actions[1] = "Wear";
       			itemDef.actions[4] = "Drop"; 
       			break;	
       			
       		case 13730:
       			itemDef.modelID = 64501;
       			itemDef.name = "Joker Legs";
       			itemDef.description = "Joker set";
       		    itemDef.modelZoom = 1700;
                itemDef.modelRotationY = 500;
        	    itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
       			itemDef.maleWearId = 64502;
       			itemDef.femaleWearId = 64502;
       			itemDef.actions = new String[5];
       			itemDef.actions[1] = "Wear";
       			itemDef.actions[4] = "Drop"; 
       			break;	
       			
      		case 13731:
       			itemDef.modelID = 64503;
       			itemDef.name = "Joker Gloves";
       			itemDef.description = "Joker set";
       		    itemDef.modelZoom = 1700;
                itemDef.modelRotationY = 500;
        	    itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
       			itemDef.maleWearId = 64504;
       			itemDef.femaleWearId = 64504;
       			itemDef.actions = new String[5];
       			itemDef.actions[1] = "Wear";
       			itemDef.actions[4] = "Drop"; 
       			break;	
       			
      		case 13732:
       			itemDef.modelID = 64505;
       			itemDef.name = "Joker Boots";
       			itemDef.description = "Joker set";
       		    itemDef.modelZoom = 1700;
                itemDef.modelRotationY = 500;
        	    itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
       			itemDef.maleWearId = 64505;
       			itemDef.femaleWearId = 64505;
       			itemDef.actions = new String[5];
       			itemDef.actions[1] = "Wear";
       			itemDef.actions[4] = "Drop"; 
       			break;	
       			
     		case 13733:
       			itemDef.modelID = 64506;
       			itemDef.name = "Rocket launcher";
       			itemDef.description = "Rocket launcher";
       		    itemDef.modelZoom = 1700;
                itemDef.modelRotationY = 500;
        	    itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
       			itemDef.maleWearId = 64507;
       			itemDef.femaleWearId = 64507;
       			itemDef.actions = new String[5];
       			itemDef.actions[1] = "Wear";
       			itemDef.actions[4] = "Drop"; 
       			break;	
       			
       	     //--------------------------GARFIELD--------------------------------------//
     		case 5131:
       			itemDef.modelID = 96556;
       			itemDef.name = "Dead Bone Helmet";
       			itemDef.description = "Dead Bone Helmet";
                itemDef.modelZoom = 900;
                itemDef.modelRotationY = 100;
       			itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
       			itemDef.maleWearId = 96546;
       			itemDef.femaleWearId = 96546;
       			itemDef.actions = new String[5];
       			itemDef.actions[1] = "Wear";
       			itemDef.actions[4] = "Drop"; 
       			break;	
       			
       		case 5133:
       			itemDef.modelID = 96547;
       			itemDef.name = "Dead Bone Body";
       			itemDef.description = "Dead Bone Body";
                itemDef.modelZoom = 1500;
                itemDef.modelRotationY = 500;
       			itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
       			itemDef.maleWearId = 96548;
       			itemDef.femaleWearId = 96548;
       			itemDef.actions = new String[5];
       			itemDef.actions[1] = "Wear";
       			itemDef.actions[4] = "Drop"; 
       			break;	
       			
     		case 5132:
       			itemDef.modelID = 96549;
       			itemDef.name = "Dead Bone Legs";
       			itemDef.description = "Dead Bone Legs";
     		    itemDef.modelZoom = 1700;
                itemDef.modelRotationY = 500;
        	    itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
       			itemDef.maleWearId = 96550;
       			itemDef.femaleWearId = 96550;
       			itemDef.actions = new String[5];
       			itemDef.actions[1] = "Wear";
       			itemDef.actions[4] = "Drop"; 
       			break;	
       			
     		case 5135:
       			itemDef.modelID = 96553;
       			itemDef.name = "Dead Bone Boots";
       			itemDef.description = "Dead Bone Boots";
                itemDef.modelZoom = 900;
                itemDef.modelRotationY = 100;
       			itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
       			itemDef.maleWearId = 96553;
       			itemDef.femaleWearId = 96553;
       			itemDef.actions = new String[5];
       			itemDef.actions[1] = "Wear";
       			itemDef.actions[4] = "Drop"; 
       			break;	
       			
     		case 5136:
       			itemDef.modelID = 96551;
       			itemDef.name = "Dead Bone Gloves";
       			itemDef.description = "Dead Bone Gloves";
                itemDef.modelZoom = 900;
                itemDef.modelRotationY = 100;
       			itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
       			itemDef.maleWearId = 96552;
       			itemDef.femaleWearId = 96552;
       			itemDef.actions = new String[5];
       			itemDef.actions[1] = "Wear";
       			itemDef.actions[4] = "Drop"; 
       			break;	
       			
    		case 5138:
       			itemDef.modelID = 96554;
       			itemDef.name = "Dead Bone Bow";
       			itemDef.description = "Dead Bone Bow";
                itemDef.modelZoom = 1700;
                itemDef.modelRotationY = 296;
                itemDef.modelRotationX = 377;
                itemDef.modelOffset1 = 5;
                itemDef.modelOffsetY = -33;
       			itemDef.maleWearId = 96555;
       			itemDef.femaleWearId = 96555;
       			itemDef.actions = new String[5];
       			itemDef.actions[1] = "Wear";
       			itemDef.actions[4] = "Drop"; 
       			break;	
       			
     		case 5178:
       			itemDef.modelID = 64393;
       			itemDef.name = "Cape Of Archeress";
       			itemDef.description = "Cape Of Archeress";
                itemDef.modelZoom = 1800;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 850;
                itemDef.modelOffset1 = 3;
                itemDef.modelOffsetY = -7;
       			itemDef.maleWearId = 64394;
       			itemDef.femaleWearId = 64394;
       			itemDef.actions = new String[5];
       			itemDef.actions[1] = "Wear";
       			itemDef.actions[4] = "Drop"; 
                itemDef.editedModelColor = new int[] {40};
                itemDef.newModelColor = new int[]  {66};
       			break;	
       			
     		case 5179:
       			itemDef.modelID = 64393;
       			itemDef.name = "Cape Of Magicians";
       			itemDef.description = "Cape Of Magicians";
                itemDef.modelZoom = 1800;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 850;
                itemDef.modelOffset1 = 3;
                itemDef.modelOffsetY = -7;
       			itemDef.maleWearId = 64394;
       			itemDef.femaleWearId = 64394;
       			itemDef.actions = new String[5];
       			itemDef.actions[1] = "Wear";
       			itemDef.actions[4] = "Drop"; 
                itemDef.editedModelColor = new int[] {40};
                itemDef.newModelColor = new int[]  {63};
       			break;
       			
     		case 5180:
       			itemDef.modelID = 64393;
       			itemDef.name = "Cape Of Warriors";
       			itemDef.description = "Cape Of Warriors";
                itemDef.modelZoom = 1800;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 850;
                itemDef.modelOffset1 = 3;
                itemDef.modelOffsetY = -7;
       			itemDef.maleWearId = 64394;
       			itemDef.femaleWearId = 64394;
       			itemDef.actions = new String[5];
       			itemDef.actions[1] = "Wear";
       			itemDef.actions[4] = "Drop"; 
                itemDef.editedModelColor = new int[] {40};
                itemDef.newModelColor = new int[]  {65};
       			break;
       			
            case 3689:
                itemDef.modelID = 64320;
                itemDef.name = "Owner full helm";
                itemDef.description = "I can almost feel the owner's soul within";
                itemDef.modelZoom = 672;
                itemDef.modelRotationY = 85;
                itemDef.modelRotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -3;
                itemDef.maleWearId = 64320;
                itemDef.femaleWearId = 64320;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 3687:
                itemDef.modelID = 64316;
                itemDef.name = "Owner platebody";
                itemDef.description = "I can almost feel the Dark lord's soul within";
                itemDef.modelZoom = 1506;
                itemDef.modelRotationY = 473;
                itemDef.modelRotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleWearId = 64316;
                itemDef.femaleWearId = 64316;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;

            case 22000:
                break;
            case 3688:
                itemDef.modelID = 64323;
                itemDef.name = "Owner platelegs";
                itemDef.description = "I can almost feel the Dark lord's soul within";
                itemDef.modelZoom = 1740;
                itemDef.modelRotationY = 474;
                itemDef.modelRotationX = 2045;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -5;
                itemDef.maleWearId = 64323;
                itemDef.femaleWearId = 64323;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 3690:
                itemDef.modelID = 64319;
                itemDef.name = "Owner gloves";
                itemDef.description = "I can almost feel the Dark lord's soul within";
                itemDef.modelZoom = 648;
                itemDef.modelRotationY = 618;
                itemDef.modelRotationX = 1143;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -5;
                itemDef.maleWearId = 64319;
                itemDef.femaleWearId = 64319;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 3691:
                itemDef.modelID = 64317;
                itemDef.name = "Owner boots";
                itemDef.description = "I can almost feel the Dark lord's soul within";
                itemDef.modelZoom = 825;
                itemDef.modelOffset1 = -4;
                itemDef.modelOffsetY = 0;
                itemDef.modelRotationY = 400;
                itemDef.maleWearId = 64317;
                itemDef.femaleWearId = 64317;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.stackable = false;
                break;
                
            case 5199:
                itemDef.modelID = 92092;
                itemDef.name = "Mysterio full helm";
                itemDef.description = "Mysterio full helm";
                itemDef.modelZoom = 900;
                itemDef.modelRotationY = 100;
       			itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
                itemDef.maleWearId = 92093;
                itemDef.femaleWearId = 92093;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
                
            case 5200:
                itemDef.modelID = 92094;
                itemDef.name = "Mysterio Body";
                itemDef.description = "Mysterio Body";
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -1;
                itemDef.maleWearId = 92095;
                itemDef.femaleWearId = 92095;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
                
            case 5201:
                itemDef.modelID = 92096;
                itemDef.name = "Mysterio Legs";
                itemDef.description = "Mysterio Legs";
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -1;
                itemDef.maleWearId = 92097;
                itemDef.femaleWearId = 92097;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
                
            case 5202:
                itemDef.modelID = 92098;
                itemDef.name = "Mysterio Gloves (Weapon)";
                itemDef.description = "Mysterio Gloves (Weapon)";
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -1;
                itemDef.maleWearId = 92099;
                itemDef.femaleWearId = 92099;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
                
            case 5203:
                itemDef.modelID = 92100;
                itemDef.name = "Mysterio Boots";
                itemDef.description = "Mysterio Boots";
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -1;
                itemDef.maleWearId = 92100;
                itemDef.femaleWearId = 92100;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
                
            case 5206:
                itemDef.modelID = 92101;
                itemDef.name = "Mysterio Cape";
                itemDef.description = "Mysterio Cape";
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -1;
                itemDef.maleWearId = 92102;
                itemDef.femaleWearId = 92102;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
                
                
            case 4626:
                itemDef.modelID = 96269;
                itemDef.name = "hearth of the sea";
                itemDef.description = "In the hearths of the sea is the power!.";
                itemDef.modelZoom = 1400;
                itemDef.modelRotationY = 473;
                itemDef.modelRotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.groundActions[2] = "Take";
                break;
                
            case 14910:
                itemDef.modelID = 95045;
                itemDef.name = "@cya@Helmet Pice (1)";
                itemDef.description = "@cya@Helmet Pice (1).";
                itemDef.modelZoom = 750;
                itemDef.modelRotationY = 473;
                itemDef.modelRotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.groundActions[2] = "Take";
                break;
                
            case 14911:
                itemDef.modelID = 95046;
                itemDef.name = "@cya@Helmet Pice (2)";
                itemDef.description = "@cya@Helmet Pice (2).";
                itemDef.modelZoom = 750;
                itemDef.modelRotationY = 473;
                itemDef.modelRotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.groundActions[2] = "Take";
                break;
                
            case 14912:
                itemDef.modelID = 95047;
                itemDef.name = "@cya@Helmet Pice (3)";
                itemDef.description = "@cya@Helmet Pice (3).";
                itemDef.modelZoom = 750;
                itemDef.modelRotationY = 473;
                itemDef.modelRotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.groundActions[2] = "Take";
                break;
                
            case 14913:
                itemDef.modelID = 95048;
                itemDef.name = "@cya@Legs Pice (1)";
                itemDef.description = "@cya@Legs Pice (1).";
                itemDef.modelZoom = 750;
                itemDef.modelRotationY = 473;
                itemDef.modelRotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.groundActions[2] = "Take";
                break;
                
            case 14914:
                itemDef.modelID = 95049;
                itemDef.name = "@cya@Legs Pice (2)";
                itemDef.description = "@cya@Legs Pice (2).";
                itemDef.modelZoom = 750;
                itemDef.modelRotationY = 473;
                itemDef.modelRotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.groundActions[2] = "Take";
                break;
                
            case 14915:
                itemDef.modelID = 95050;
                itemDef.name = "@cya@Legs Pice (3)";
                itemDef.description = "@cya@Legs Pice (3).";
                itemDef.modelZoom = 750;
                itemDef.modelRotationY = 473;
                itemDef.modelRotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.groundActions[2] = "Take";
                break;
                
            case 14916:
                itemDef.modelID = 95051;
                itemDef.name = "@cya@PlateBody Pice (1)";
                itemDef.description = "@cya@PlateBody Pice (1).";
                itemDef.modelZoom = 750;
                itemDef.modelRotationY = 473;
                itemDef.modelRotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.groundActions[2] = "Take";
                break;
                
            case 14917:
                itemDef.modelID = 95052;
                itemDef.name = "@cya@PlateBody Pice (2)";
                itemDef.description = "@cya@PlateBody Pice (2).";
                itemDef.modelZoom = 750;
                itemDef.modelRotationY = 473;
                itemDef.modelRotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.groundActions[2] = "Take";
                break;
                
            case 14918:
                itemDef.modelID = 95053;
                itemDef.name = "@cya@PlateBody Pice (3)";
                itemDef.description = "@cya@PlateBody Pice (3).";
                itemDef.modelZoom = 750;
                itemDef.modelRotationY = 473;
                itemDef.modelRotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.groundActions[2] = "Take";
                break;
                
            case 14919:
                itemDef.modelID = 95058;
                itemDef.name = "Invictus full helm";
                itemDef.description = "Invictus full helm";
                itemDef.modelZoom = 900;
                itemDef.modelRotationY = 100;
       			itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
                itemDef.maleWearId = 95059;
                itemDef.femaleWearId = 95059;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                itemDef.editedModelColor = new int[] {69};
                itemDef.newModelColor = new int[]    {68};
                break;
                
            case 14920:
                itemDef.modelID = 95062;
                itemDef.name = "Invictus Body";
                itemDef.description = "Invictus Body";
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -1;
                itemDef.maleWearId = 95063;
                itemDef.femaleWearId = 95063;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                itemDef.editedModelColor = new int[] {69};
                itemDef.newModelColor = new int[]    {68};
                break;
                
            case 14921:
                itemDef.modelID = 95060;
                itemDef.name = "Invictus Legs";
                itemDef.description = "Dreamed Legs";
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -1;
                itemDef.maleWearId = 95061;
                itemDef.femaleWearId = 95061;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                itemDef.editedModelColor = new int[] {69};
                itemDef.newModelColor = new int[]    {68};
                break;
                
            case 14922:
                itemDef.modelID = 91284;
                itemDef.name = "Invictus sword";
                itemDef.description = "Invictus sword";
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -1;
                itemDef.maleWearId = 91285;
                itemDef.femaleWearId = 91285;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
                
            case 14923:
                itemDef.modelID = 91280;
                itemDef.name = "Invictus Bow";
                itemDef.description = "Invictus Bow";
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -1;
                itemDef.maleWearId = 91281;
                itemDef.femaleWearId = 91281;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
                
            case 14933:
                itemDef.name = "@yel@Lucky Block";
                itemDef.modelID = 97539;
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                itemDef.stackable = true;
                itemDef.modelZoom = 1500;
                itemDef.modelRotationY = 200;
                itemDef.modelRotationX = 250;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -1;
                itemDef.maleWearId = 91283;
                itemDef.femaleWearId = 91283;
                break;
                
            case 14924:
                itemDef.modelID = 91282;
                itemDef.name = "Invictus Staff";
                itemDef.description = "Invictus Staff";
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -1;
                itemDef.maleWearId = 91283;
                itemDef.femaleWearId = 91283;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.maleDialogue = 62729;
                itemDef.femaleDialogue = 62729;
                break;
                
            case 14925:
                itemDef.modelID = 91286;
                itemDef.name = "Invictus Bow Part (1)";
                itemDef.description = "Invictus Bow Pice (1).";
                itemDef.modelZoom = 1500;
                itemDef.modelRotationY = 473;
                itemDef.modelRotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.groundActions[2] = "Take";
                break;
                
            case 14926:
                itemDef.modelID = 91287;
                itemDef.name = "Invictus Bow part (2)";
                itemDef.description = "Invictus Bow part (2).";
                itemDef.modelZoom = 750;
                itemDef.modelRotationY = 473;
                itemDef.modelRotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.groundActions[2] = "Take";
                break;
                
            case 14927:
                itemDef.modelID = 91288;
                itemDef.name = "Invictus Staff Part (1)";
                itemDef.description = "Invictus Staff Pice (1).";
                itemDef.modelZoom = 1650;
                itemDef.modelRotationY = 473;
                itemDef.modelRotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.groundActions[2] = "Take";
                break;
                
            case 14928:
                itemDef.modelID = 91289;
                itemDef.name = "Invictus Staff part (2)";
                itemDef.description = "Invictus Staff part (2).";
                itemDef.modelZoom = 1750;
                itemDef.modelRotationY = 473;
                itemDef.modelRotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.groundActions[2] = "Take";
                break;
                
            case 14929:
                itemDef.modelID = 91290;
                itemDef.name = "Invictus Sword Part (1)";
                itemDef.description = "Invictus Sword Pice (1).";
                itemDef.modelZoom = 750;
                itemDef.modelRotationY = 473;
                itemDef.modelRotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.groundActions[2] = "Take";
                break;
                
            case 14930:
                itemDef.modelID = 91291;
                itemDef.name = "Invictus Sword part (2)";
                itemDef.description = "Invictus Sword part (2).";
                itemDef.modelZoom = 1400;
                itemDef.modelRotationY = 473;
                itemDef.modelRotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.groundActions[2] = "Take";
                break;
                
        	case 14934:
       			itemDef.modelID = 90507;
       			itemDef.name = "Magma Devil Hammer";
       			itemDef.description = "Magma Devil Hammer";
       		    itemDef.modelZoom = 1000;
                itemDef.modelRotationY = 500;
        	    itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
       			itemDef.maleWearId = 90508;
       			itemDef.femaleWearId = 90508;
       			itemDef.actions = new String[5];
       			itemDef.actions[1] = "Wear";
       			itemDef.actions[4] = "Drop"; 
       			break;	
       			
    		case 14935:
       			itemDef.modelID = 90507;
       			itemDef.name = "Magma Devil Hammer Off-Hand";
       			itemDef.description = "Magma Devil Hammer Off-Hand";
       		    itemDef.modelZoom = 1000;
                itemDef.modelRotationY = 500;
        	    itemDef.modelRotationX = 0;
        	    itemDef.modelOffset1 = -1;
        		itemDef.modelOffsetY = -1;
       			itemDef.maleWearId = 90509;
       			itemDef.femaleWearId = 90509;
       			itemDef.actions = new String[5];
       			itemDef.actions[1] = "Wear";
       			itemDef.actions[4] = "Drop"; 
       			break;	
       			
    		  case 10905:
                  itemDef.modelID = 95137;
                  itemDef.name = "Magma Devil full helm";
                  itemDef.description = "Magma Devil full helm";
                  itemDef.modelZoom = 900;
                  itemDef.modelRotationY = 100;
         			itemDef.modelRotationX = 0;
          	    itemDef.modelOffset1 = -1;
          		itemDef.modelOffsetY = -1;
                  itemDef.maleWearId = 95138;
                  itemDef.femaleWearId = 95138;
                  itemDef.groundActions = new String[5];
                  itemDef.groundActions[2] = "Take";
                  itemDef.actions = new String[5];
                  itemDef.actions[1] = "Wear";
                  itemDef.actions[4] = "Drop";
                  itemDef.maleDialogue = 62729;
                  itemDef.femaleDialogue = 62729;
                  itemDef.editedModelColor = new int[] {50,51,60,61};
                  itemDef.newModelColor = new int[]    {94,94,94,94};
                  break;
                  
              case 10906:
                  itemDef.modelID = 95141;
                  itemDef.name = "Magma Devil Body";
                  itemDef.description = "Magma Devil Body";
                  itemDef.modelZoom = 1300;
                  itemDef.modelRotationY = 500;
                  itemDef.modelRotationX = 0;
                  itemDef.modelOffset1 = 0;
                  itemDef.modelOffsetY = -1;
                  itemDef.maleWearId = 95142;
                  itemDef.femaleWearId = 95142;
                  itemDef.groundActions = new String[5];
                  itemDef.groundActions[2] = "Take";
                  itemDef.actions = new String[5];
                  itemDef.actions[1] = "Wear";
                  itemDef.actions[4] = "Drop";
                  itemDef.maleDialogue = 62729;
                  itemDef.femaleDialogue = 62729;
                  itemDef.editedModelColor = new int[] {50,51,60,61};
                  itemDef.newModelColor = new int[]    {94,94,94,94};
                  break;
                  
              case 10907:
                  itemDef.modelID = 95139;
                  itemDef.name = "Magma Devil Legs";
                  itemDef.description = "Magma Devil Legs";
                  itemDef.modelZoom = 1300;
                  itemDef.modelRotationY = 500;
                  itemDef.modelRotationX = 0;
                  itemDef.modelOffset1 = 0;
                  itemDef.modelOffsetY = -1;
                  itemDef.maleWearId = 95140;
                  itemDef.femaleWearId = 95140;
                  itemDef.groundActions = new String[5];
                  itemDef.groundActions[2] = "Take";
                  itemDef.actions = new String[5];
                  itemDef.actions[1] = "Wear";
                  itemDef.actions[4] = "Drop";
                  itemDef.maleDialogue = 62729;
                  itemDef.femaleDialogue = 62729;
                  itemDef.editedModelColor = new int[] {50,51,60,61};
                  itemDef.newModelColor = new int[]    {94,94,94,94};
                  break;
                
               //--------------------Maestro set------------------------//      			
        }

        return ItemDef_Null.forDef(itemDef, ID);
    }

}
