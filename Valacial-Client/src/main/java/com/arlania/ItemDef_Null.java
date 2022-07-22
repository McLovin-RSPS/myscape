package com.arlania;

public class ItemDef_Null {

    public static ItemDef forDef(ItemDef itemDef, int ID) {

        ItemDef itemDef2;
        switch (ID) {

            case 19467:
                itemDef.name = "Armadylian Machine";
                itemDef.description = "A strange Armadyl contraption.";
                itemDef.modelZoom = 1284;
                itemDef.modelRotationY = 189;
                itemDef.modelRotationX = 148;
                itemDef.modelOffset1 = 8;
                itemDef.modelOffsetY = -18;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.modelID = 95021;
                itemDef.maleWearId = 95022;
                itemDef.femaleWearId = 95022;
                itemDef.stackable = true;
                break;
            case 13263:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Upgrade";
                itemDef.actions[4] = "Drop";
                break;

            case 17546:
                itemDef.name = "@yel@God Potion @red@(5m)";
                itemDef.modelZoom = 600;
                itemDef.actions = new String[] { "Drink", null, null, null, "Destroy" };
                break;

            case 1153:
                itemDef.name = "Bomby  fullhelm";
                itemDef.modelZoom = 672;
                itemDef.modelRotationY = 85;
                itemDef.modelRotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -3;
                itemDef.maleWearId = 90412;
                itemDef.colourRedefine3 = 150000;
                itemDef.femaleWearId = 90412;
                itemDef.modelID = 90411;

                break;

            case 1115:
                itemDef.name = "Bomby platebody";
                itemDef.description = "I can almost feel the Dark lord's soul within";
                itemDef.modelZoom = 1506;
                itemDef.modelID = 98743;
                itemDef.modelRotationY = 473;
                itemDef.modelRotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleWearId = 90413;
                itemDef.femaleWearId = 90413;
                itemDef.colourRedefine3 = 150000;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;

            case 1067:
                itemDef.modelID = 90419;
                itemDef.name = "Bomby platelegs";
                itemDef.description = "I can almost feel the Dark lord's soul within";
                itemDef.modelZoom = 1740;
                itemDef.modelRotationY = 474;
                itemDef.modelRotationX = 2045;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -5;
                itemDef.maleWearId = 90420;
                itemDef.colourRedefine3 = 150000;
                itemDef.femaleWearId = 90420;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;


            case 21050:
                itemDef.name = "Light Darklord fullhelm";
                itemDef.modelZoom = 672;
                itemDef.modelRotationY = 85;
                itemDef.modelRotationX = 1867;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -3;
                itemDef.colourRedefine2 = 78475;
                itemDef.maleWearId = 91321;
                itemDef.femaleWearId = 91321;
                itemDef.modelID = 91320;
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;

            case 21051:
                itemDef.name = "Light Darklord platebody";
                itemDef.description = "I can almost feel the Dark lord's soul within";
                itemDef.modelZoom = 1506;
                itemDef.modelRotationY = 473;
                itemDef.modelRotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.colourRedefine2 = 78475;

                itemDef.modelID = 91315;
                itemDef.maleWearId = 91316;
                itemDef.femaleWearId = 91316;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;

            case 21052:
                itemDef.modelID = 91323;
                itemDef.name = "Light Darklord platelegs";
                itemDef.description = "I can almost feel the Dark lord's soul within";
                itemDef.modelZoom = 1740;
                itemDef.modelRotationY = 474;
                itemDef.modelRotationX = 2045;
                itemDef.modelID = 91323;
                itemDef.colourRedefine2 = 78475;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -5;
                itemDef.maleWearId = 91322;
                itemDef.femaleWearId = 91322;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 21053:
                itemDef.modelID = 91318;
                itemDef.name = "Light Darklord gloves";
                itemDef.description = "I can almost feel the Dark lord's soul within";
                itemDef.modelZoom = 648;
                itemDef.modelRotationY = 618;
                itemDef.modelRotationX = 1143;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = -5;
                itemDef.maleWearId = 91319;
                itemDef.femaleWearId = 91319;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                itemDef.colourRedefine2 = 78475;
                break;

            case 21045:
                itemDef.name = "Artillery box";
                itemDef.modelID = 91355;
                /* 21314 */       itemDef.modelZoom = 1100;
                itemDef.modelRotationX = ItemDef.forID(7956).modelRotationX;
                itemDef.modelRotationY = ItemDef.forID(7956).modelRotationY;
                itemDef.modelOffsetX = ItemDef.forID(7956).modelOffsetX;
                itemDef.modelOffsetY = ItemDef.forID(7956).modelOffsetY;
                itemDef.actions = new String[5];
                itemDef.actions[0] = "Open";
                itemDef.actions[4] = "Drop";
                break;
            case 21054:
                itemDef.modelID = 91317;
                itemDef.name = "Light Darklord boots";
                itemDef.description = "I can almost feel the Dark lord's soul within";
                itemDef.modelZoom = 825;
                itemDef.modelOffset1 = -4;
                itemDef.modelOffsetY = 0;
                itemDef.modelRotationY = 400;
                itemDef.maleWearId = 91317;
                itemDef.femaleWearId = 91317;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.stackable = false;
                itemDef.colourRedefine2 = 78475;

                break;

            case 8888:
            itemDef.modelID = 130011;
            itemDef.name = "@red@Redpurge Head";
            itemDef.description = "I can almost feel the dark power coming";
            itemDef.modelZoom = 1306;
            itemDef.modelRotationY = 473;
            itemDef.modelRotationX = 2042;
            itemDef.modelOffset1 = 0;
            itemDef.modelOffsetY = 0;
            itemDef.maleWearId = 130010;
            itemDef.femaleWearId = 130010;
            itemDef.groundActions = new String[5];
            itemDef.groundActions[2] = "Take";
            itemDef.actions = new String[5];
            itemDef.actions[1] = "Wear";
            itemDef.actions[4] = "Drop";
            break;
            
        case 8889:
            itemDef.modelID = 130015;
            itemDef.name = "@red@Redpurge Body";
            itemDef.description = "I can almost feel the dark power coming";
            itemDef.modelZoom = 1306;
            itemDef.modelRotationY = 473;
            itemDef.modelRotationX = 2042;
            itemDef.modelOffset1 = 0;
            itemDef.modelOffsetY = 0;
            itemDef.maleWearId = 130014;
            itemDef.femaleWearId = 130014;
            itemDef.groundActions = new String[5];
            itemDef.groundActions[2] = "Take";
            itemDef.actions = new String[5];
            itemDef.actions[1] = "Wear";
            itemDef.actions[4] = "Drop";
            break;
            
        case 8890:
            itemDef.modelID = 130013;
            itemDef.name = "@red@Redpurge Legs";
            itemDef.description = "I can almost feel the dark power coming";
            itemDef.modelZoom = 1306;
            itemDef.modelRotationY = 473;
            itemDef.modelRotationX = 2042;
            itemDef.modelOffset1 = 0;
            itemDef.modelOffsetY = 0;
            itemDef.maleWearId = 130012;
            itemDef.femaleWearId = 130012;
            itemDef.groundActions = new String[5];
            itemDef.groundActions[2] = "Take";
            itemDef.actions = new String[5];
            itemDef.actions[1] = "Wear";
            itemDef.actions[4] = "Drop";
            break;
               
        case 8891:
            itemDef.name = "RedPurge Gloves";
            itemDef.actions = new String[5];
            itemDef.actions[1] = "Wield";
            itemDef.modelID = 130018;
  		    itemDef.modelZoom = 1200;
            itemDef.modelRotationY = 500;
    	    itemDef.modelRotationX = 0;
    	    itemDef.modelOffset1 = -1;
    		itemDef.modelOffsetY = -1;
            itemDef.maleWearId = 130017;
            itemDef.femaleWearId = 130017;
            break;     

        case 8892:
            itemDef.name = "RedPurge Boots";
            itemDef.actions = new String[5];
            itemDef.actions[1] = "Wield";
            itemDef.modelID = 130016;
  		    itemDef.modelZoom = 1200;
            itemDef.modelRotationY = 500;
    	    itemDef.modelRotationX = 0;
    	    itemDef.modelOffset1 = -1;
    		itemDef.modelOffsetY = -1;
            itemDef.maleWearId = 130016;
            itemDef.femaleWearId = 130016;
            break;    
        
        case 8883:
            itemDef.modelID = 64317;
            itemDef.name = "@mag@SexySophia Head";
            itemDef.description = "I can almost feel the sex power coming";
            itemDef.modelZoom = 1506;
            itemDef.modelRotationY = 473;
            itemDef.modelRotationX = 2042;
            itemDef.modelOffset1 = 0;
            itemDef.modelOffsetY = 0;
            itemDef.maleWearId = 64318;
            itemDef.femaleWearId = 64318;
            itemDef.groundActions = new String[5];
            itemDef.groundActions[2] = "Take";
            itemDef.actions = new String[5];
            itemDef.actions[1] = "Wear";
            itemDef.actions[4] = "Drop";
            break;
            
        case 8884:
            itemDef.modelID = 64319;
            itemDef.name = "@mag@SexySophia body";
            itemDef.description = "I can almost feel the sex power coming";
            itemDef.modelZoom = 1506;
            itemDef.modelRotationY = 473;
            itemDef.modelRotationX = 2042;
            itemDef.modelOffset1 = 0;
            itemDef.modelOffsetY = 0;
            itemDef.maleWearId = 64320;
            itemDef.femaleWearId = 64320;
            itemDef.groundActions = new String[5];
            itemDef.groundActions[2] = "Take";
            itemDef.actions = new String[5];
            itemDef.actions[1] = "Wear";
            itemDef.actions[4] = "Drop";
            break;
            
        case 8885:
            itemDef.modelID = 64321;
            itemDef.name = "@mag@SexySophia Legs";
            itemDef.description = "I can almost feel the sex power coming";
            itemDef.modelZoom = 1506;
            itemDef.modelRotationY = 473;
            itemDef.modelRotationX = 2042;
            itemDef.modelOffset1 = 0;
            itemDef.modelOffsetY = 0;
            itemDef.maleWearId = 64322;
            itemDef.femaleWearId = 64322;
            itemDef.groundActions = new String[5];
            itemDef.groundActions[2] = "Take";
            itemDef.actions = new String[5];
            itemDef.actions[1] = "Wear";
            itemDef.actions[4] = "Drop";
            break;
        
        case 8886:
            itemDef.modelID = 64323;
            itemDef.name = "@mag@SexySophia Gloves";
            itemDef.description = "I can almost feel the sex power coming";
            itemDef.modelZoom = 1506;
            itemDef.modelRotationY = 473;
            itemDef.modelRotationX = 2042;
            itemDef.modelOffset1 = 0;
            itemDef.modelOffsetY = 0;
            itemDef.maleWearId = 64324;
            itemDef.femaleWearId = 64324;
            itemDef.groundActions = new String[5];
            itemDef.groundActions[2] = "Take";
            itemDef.actions = new String[5];
            itemDef.actions[1] = "Wear";
            itemDef.actions[4] = "Drop";
            break;
        
        case 8887:
            itemDef.modelID = 64325;
            itemDef.name = "@mag@SexySophia boots";
            itemDef.description = "I can almost feel the sex power coming";
            itemDef.modelZoom = 1506;
            itemDef.modelRotationY = 473;
            itemDef.modelRotationX = 2042;
            itemDef.modelOffset1 = 0;
            itemDef.modelOffsetY = 0;
            itemDef.maleWearId = 64325;
            itemDef.femaleWearId = 64325;
            itemDef.groundActions = new String[5];
            itemDef.groundActions[2] = "Take";
            itemDef.actions = new String[5];
            itemDef.actions[1] = "Wear";
            itemDef.actions[4] = "Drop";
            break;
        
        
        
        case 8893:
            itemDef.modelID = 64513;
            itemDef.name = "@whi@whitebeard Helm";
            itemDef.description = "I can almost feel the dark power coming";
            itemDef.modelZoom = 900;
            itemDef.modelRotationY = 100;
   			itemDef.modelRotationX = 0;
    	    itemDef.modelOffset1 = -1;
    		itemDef.modelOffsetY = -1;
            itemDef.maleWearId = 64514;
            itemDef.femaleWearId = 64514;
            itemDef.groundActions = new String[5];
            itemDef.groundActions[2] = "Take";
            itemDef.actions = new String[5];
            itemDef.actions[1] = "Wear";
            itemDef.actions[4] = "Drop";
            break;
            
        case 8894:
            itemDef.modelID = 64515;
            itemDef.name = "@whi@whitebeard Body";
            itemDef.description = "I can almost feel the dark power coming";
            itemDef.modelZoom = 1306;
            itemDef.modelRotationY = 473;
            itemDef.modelRotationX = 2042;
            itemDef.modelOffset1 = 0;
            itemDef.modelOffsetY = 0;
            itemDef.maleWearId = 64516;
            itemDef.femaleWearId = 64516;
            itemDef.groundActions = new String[5];
            itemDef.groundActions[2] = "Take";
            itemDef.actions = new String[5];
            itemDef.actions[1] = "Wear";
            itemDef.actions[4] = "Drop";
            break;
            
        case 8895:
            itemDef.modelID = 64517;
            itemDef.name = "@whi@whitebeard Legs";
            itemDef.description = "I can almost feel the dark power coming";
            itemDef.modelZoom = 1306;
            itemDef.modelRotationY = 473;
            itemDef.modelRotationX = 2042;
            itemDef.modelOffset1 = 0;
            itemDef.modelOffsetY = 0;
            itemDef.maleWearId = 64518;
            itemDef.femaleWearId = 64518;
            itemDef.groundActions = new String[5];
            itemDef.groundActions[2] = "Take";
            itemDef.actions = new String[5];
            itemDef.actions[1] = "Wear";
            itemDef.actions[4] = "Drop";
            break;
               
        case 8896:
            itemDef.name = "@whi@whitebeard Gloves";
            itemDef.actions = new String[5];
            itemDef.actions[1] = "Wield";
            itemDef.modelID = 64519;
  		    itemDef.modelZoom = 1200;
            itemDef.modelRotationY = 500;
    	    itemDef.modelRotationX = 0;
    	    itemDef.modelOffset1 = -1;
    		itemDef.modelOffsetY = -1;
            itemDef.maleWearId = 64520;
            itemDef.femaleWearId = 64520;
            break;     

        case 8897:
            itemDef.name = "@whi@whitebeard Boots";
            itemDef.actions = new String[5];
            itemDef.actions[1] = "Wield";
            itemDef.modelID = 64521;
  		    itemDef.modelZoom = 1200;
            itemDef.modelRotationY = 500;
    	    itemDef.modelRotationX = 0;
    	    itemDef.modelOffset1 = -1;
    		itemDef.modelOffsetY = -1;
            itemDef.maleWearId = 64521;
            itemDef.femaleWearId = 64521;
            break; 
            
        case 8898:
            itemDef.name = "@whi@whitebeard Staff";
            itemDef.actions = new String[5];
            itemDef.actions[1] = "Wield";
            itemDef.modelID = 64522;
  		    itemDef.modelZoom = 1200;
            itemDef.modelRotationY = 500;
    	    itemDef.modelRotationX = 0;
    	    itemDef.modelOffset1 = -1;
    		itemDef.modelOffsetY = -1;
            itemDef.maleWearId = 64523;
            itemDef.femaleWearId = 64523;
            break; 
            
        case 8899:
            itemDef.name = "@whi@whitebeard Cape";
            itemDef.actions = new String[5];
            itemDef.actions[1] = "Wield";
            itemDef.modelID = 64524;
  		    itemDef.modelZoom = 1200;
            itemDef.modelRotationY = 500;
    	    itemDef.modelRotationX = 0;
    	    itemDef.modelOffset1 = -1;
    		itemDef.modelOffsetY = -1;
            itemDef.maleWearId = 64525;
            itemDef.femaleWearId = 64525;
            break;
            case 8900:
                itemDef.modelID = 130070;
                itemDef.name = "@red@Horror Helm";
                itemDef.description = "I can almost feel the dark power coming";
                itemDef.modelZoom = 900;
                itemDef.modelRotationY = 100;
                itemDef.modelRotationX = 0;
                itemDef.modelOffset1 = -1;
                itemDef.modelOffsetY = -1;
                itemDef.maleWearId = 130071;
                itemDef.femaleWearId = 130071;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;

            case 8901:
                itemDef.modelID = 130072;
                itemDef.name = "@red@Horror Body";
                itemDef.description = "I can almost feel the dark power coming";
                itemDef.modelZoom = 1306;
                itemDef.modelRotationY = 473;
                itemDef.modelRotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleWearId = 130073;
                itemDef.femaleWearId = 130073;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;

            case 8902:
                itemDef.modelID = 130074;
                itemDef.name = "@red@Horror Legs";
                itemDef.stackable = false;
                itemDef.description = "I can almost feel the dark power coming";
                itemDef.modelZoom = 1306;
                itemDef.modelRotationY = 473;
                itemDef.modelRotationX = 2042;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleWearId = 130075;
                itemDef.femaleWearId = 130075;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;

            case 8903:
                itemDef.name = "@red@Horror Gloves";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 65528;
                itemDef.modelZoom = 1200;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffset1 = -1;
                itemDef.modelOffsetY = -1;
                itemDef.maleWearId = 65529;
                itemDef.femaleWearId = 65529;
                break;

            case 8904:
                itemDef.name = "@red@Horror Boots";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.stackable = false;
                itemDef.modelID = 65521;
                itemDef.modelZoom = 1200;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffset1 = -1;
                itemDef.modelOffsetY = -1;
                itemDef.maleWearId = 65521;
                itemDef.femaleWearId = 65521;
                break;

            case 8905:
                itemDef.name = "@red@Horror Dual Sword";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.modelID = 65531;
                itemDef.modelZoom = 1200;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffset1 = -1;
                itemDef.modelOffsetY = -1;
                itemDef.maleWearId = 65530;
                itemDef.femaleWearId = 65530;
                break;






            case 693:
                itemDef.name = "Afk ticket";
                itemDef.actions = new String[5]; // String array of 5 actions
                //itemDef.actions[0] = "Scratch";
                itemDef.editedModelColor = new int[]{17825, 17944, 17814, 18062};
                itemDef.newModelColor = new int[]{54245, 54245, 54245, 54245};
                break;
            case 694:
                itemDef.name = "Golden Afk Rock";
                itemDef.actions = new String[5]; // String array of 5 actions
              //  itemDef.actions[0] = "Scratch";
                itemDef.editedModelColor = new int[]{17825, 17944, 17814, 18062};
                itemDef.newModelColor = new int[]{54245, 54245, 54245, 54245};
                break;

            case 2869:
                itemDef.modelID = 40920;
                itemDef.name = "Blood Spirit Shield";
                itemDef.description = "It's a spirit shield";
                itemDef.editedModelColor = new int[13];
                itemDef.originalModelColor = new int[13];
                itemDef.editedModelColor[0] = 44635;
                itemDef.originalModelColor[0] = 924;
                itemDef.editedModelColor[1] = 44612;
                itemDef.originalModelColor[1] = 924;
                itemDef.editedModelColor[2] = 44606;
                itemDef.originalModelColor[2] = 924;
                itemDef.editedModelColor[3] = 44615;
                itemDef.originalModelColor[3] = 924;
                itemDef.editedModelColor[4] = 44641;
                itemDef.originalModelColor[4] = 924;
                itemDef.editedModelColor[5] = 44564;
                itemDef.originalModelColor[5] = 924;
                itemDef.editedModelColor[6] = 44575;
                itemDef.originalModelColor[6] = 924;
                itemDef.editedModelColor[7] = 44618;
                itemDef.originalModelColor[7] = 924;
                itemDef.editedModelColor[8] = 105;
                itemDef.originalModelColor[8] = 105;
                itemDef.editedModelColor[9] = 44603;
                itemDef.originalModelColor[9] = 924;
                itemDef.editedModelColor[10] = 44570;
                itemDef.originalModelColor[10] = 924;
                itemDef.editedModelColor[11] = 4500;
                itemDef.originalModelColor[11] = 924;
                itemDef.modelZoom = 1616;
                itemDef.modelRotationY = 396;
                itemDef.modelRotationX = 1050;
                itemDef.modelOffsetY = -3;
                itemDef.modelOffsetX = 4;
                itemDef.maleWearId = 80004;
                itemDef.femaleWearId = 80004;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 2870:
                itemDef.modelID = 40920;
                itemDef.name = "Icy Spirit Shield";
                itemDef.description = "It's a spirit shield";
                itemDef.editedModelColor = new int[13];
                itemDef.originalModelColor = new int[13];
                itemDef.editedModelColor[0] = 44635;
                itemDef.originalModelColor[0] = 105;
                itemDef.editedModelColor[1] = 44612;
                itemDef.originalModelColor[1] = 105;
                itemDef.editedModelColor[2] = 44606;
                itemDef.originalModelColor[2] = 105;
                itemDef.editedModelColor[3] = 44615;
                itemDef.originalModelColor[3] = 105;
                itemDef.editedModelColor[4] = 44641;
                itemDef.originalModelColor[4] = 105;
                itemDef.editedModelColor[5] = 44564;
                itemDef.originalModelColor[5] = 105;
                itemDef.editedModelColor[6] = 44575;
                itemDef.originalModelColor[6] = 105;
                itemDef.editedModelColor[7] = 44618;
                itemDef.originalModelColor[7] = 105;
                itemDef.editedModelColor[8] = 105;
                itemDef.originalModelColor[8] = 105;
                itemDef.editedModelColor[9] = 44603;
                itemDef.originalModelColor[9] = 105;
                itemDef.editedModelColor[10] = 44570;
                itemDef.originalModelColor[10] = 105;
                itemDef.editedModelColor[11] = 4500;
                itemDef.originalModelColor[11] = 105;
                itemDef.modelZoom = 1616;
                itemDef.modelRotationY = 396;
                itemDef.modelRotationX = 1050;
                itemDef.modelOffsetY = -3;
                itemDef.modelOffset1 = 4;
                itemDef.maleWearId = 80005;
                itemDef.femaleWearId = 80005;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 2880:
                itemDef.modelID = 40920;
                itemDef.name = "Lava spirit shield";
                itemDef.description = "It's a spirit shield";
                itemDef.editedModelColor = new int[13];
                itemDef.originalModelColor = new int[13];
                itemDef.editedModelColor[0] = 44635;
                itemDef.originalModelColor[0] = 6073;
                itemDef.editedModelColor[1] = 44612;
                itemDef.originalModelColor[1] = 6073;
                itemDef.editedModelColor[2] = 44606;
                itemDef.originalModelColor[2] = 6073;
                itemDef.editedModelColor[3] = 44615;
                itemDef.originalModelColor[3] = 6073;
                itemDef.editedModelColor[4] = 44641;
                itemDef.originalModelColor[4] = 6073;
                itemDef.editedModelColor[5] = 44564;
                itemDef.originalModelColor[5] = 6073;
                itemDef.editedModelColor[6] = 44575;
                itemDef.originalModelColor[6] = 6073;
                itemDef.editedModelColor[7] = 44618;
                itemDef.originalModelColor[7] = 6073;
                itemDef.editedModelColor[8] = 105;
                itemDef.originalModelColor[8] = 105;
                itemDef.editedModelColor[9] = 44603;
                itemDef.originalModelColor[9] = 6073;
                itemDef.editedModelColor[10] = 44570;
                itemDef.originalModelColor[10] = 6073;
                itemDef.editedModelColor[11] = 4500;
                itemDef.originalModelColor[11] = 6073;
                itemDef.modelZoom = 1616;
                itemDef.modelRotationY = 396;
                itemDef.modelRotationX = 1050;
                itemDef.modelOffsetY = -3;
                itemDef.modelOffset1 = 4;
                itemDef.maleWearId = 80007;
                itemDef.femaleWearId = 80007;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[2] = "Inspect";
                itemDef.actions[4] = "Drop";
                break;
            case 2881:
                itemDef.modelID = 40920;
                itemDef.name = "Dragonbone spirit shield";
                itemDef.description = "It's a spirit shield";
                itemDef.editedModelColor = new int[13];
                itemDef.originalModelColor = new int[13];
                itemDef.editedModelColor[0] = 44635;
                itemDef.originalModelColor[0] = 49950;
                itemDef.editedModelColor[1] = 44612;
                itemDef.originalModelColor[1] = 49950;
                itemDef.editedModelColor[2] = 44606;
                itemDef.originalModelColor[2] = 49950;
                itemDef.editedModelColor[3] = 44615;
                itemDef.originalModelColor[3] = 49950;
                itemDef.editedModelColor[4] = 44641;
                itemDef.originalModelColor[4] = 49950;
                itemDef.editedModelColor[5] = 44564;
                itemDef.originalModelColor[5] = 49950;
                itemDef.editedModelColor[6] = 44575;
                itemDef.originalModelColor[6] = 49950;
                itemDef.editedModelColor[7] = 44618;
                itemDef.originalModelColor[7] = 49950;
                itemDef.editedModelColor[8] = 105;
                itemDef.originalModelColor[8] = 105;
                itemDef.editedModelColor[9] = 44603;
                itemDef.originalModelColor[9] = 49950;
                itemDef.editedModelColor[10] = 44570;
                itemDef.originalModelColor[10] = 49950;
                itemDef.editedModelColor[11] = 4500;
                itemDef.originalModelColor[11] = 49950;
                itemDef.modelZoom = 1616;
                itemDef.modelRotationY = 396;
                itemDef.modelRotationX = 1050;
                itemDef.modelOffsetY = -3;
                itemDef.modelOffset1 = 4;
                itemDef.maleWearId = 80006;
                itemDef.femaleWearId = 80006;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 2884:
                itemDef.modelID = 40920;
                itemDef.name = "Aqua spirit shield";
                itemDef.description = "It's a spirit shield";
                itemDef.editedModelColor = new int[13];
                itemDef.originalModelColor = new int[13];
                itemDef.editedModelColor[0] = 44635;
                itemDef.originalModelColor[0] = 34503;
                itemDef.editedModelColor[1] = 44612;
                itemDef.originalModelColor[1] = 34503;
                itemDef.editedModelColor[2] = 44606;
                itemDef.originalModelColor[2] = 34503;
                itemDef.editedModelColor[3] = 44615;
                itemDef.originalModelColor[3] = 34503;
                itemDef.editedModelColor[4] = 44641;
                itemDef.originalModelColor[4] = 34503;
                itemDef.editedModelColor[5] = 44564;
                itemDef.originalModelColor[5] = 34503;
                itemDef.editedModelColor[6] = 44575;
                itemDef.originalModelColor[6] = 34503;
                itemDef.editedModelColor[7] = 44618;
                itemDef.originalModelColor[7] = 34503;
                itemDef.editedModelColor[8] = 105;
                itemDef.originalModelColor[8] = 105;
                itemDef.editedModelColor[9] = 44603;
                itemDef.originalModelColor[9] = 34503;
                itemDef.editedModelColor[10] = 44570;
                itemDef.originalModelColor[10] = 34503;
                itemDef.editedModelColor[11] = 4500;
                itemDef.originalModelColor[11] = 34503;
                itemDef.modelZoom = 1616;
                itemDef.modelRotationY = 396;
                itemDef.modelRotationX = 1050;
                itemDef.modelOffsetY = -3;
                itemDef.modelOffset1 = 4;
                itemDef.maleWearId = 80008;
                itemDef.femaleWearId = 80008;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;
            case 2885:
                itemDef.modelID = 40920;
                itemDef.name = "Darkly spirit shield";
                itemDef.description = "It's a spirit shield";
                itemDef.editedModelColor = new int[12];
                itemDef.originalModelColor = new int[12];
                itemDef.editedModelColor[0] = 44635;
                itemDef.originalModelColor[0] = 0;
                itemDef.editedModelColor[1] = 44612;
                itemDef.originalModelColor[1] = 0;
                itemDef.editedModelColor[2] = 44606;
                itemDef.originalModelColor[2] = 0;
                itemDef.editedModelColor[3] = 44615;
                itemDef.originalModelColor[3] = 0;
                itemDef.editedModelColor[4] = 44641;
                itemDef.originalModelColor[4] = 0;
                itemDef.editedModelColor[5] = 44564;
                itemDef.originalModelColor[5] = 0;
                itemDef.editedModelColor[6] = 44575;
                itemDef.originalModelColor[6] = 0;
                itemDef.editedModelColor[7] = 44618;
                itemDef.originalModelColor[7] = 0;
                itemDef.editedModelColor[8] = 105;
                itemDef.originalModelColor[8] = 105;
                itemDef.editedModelColor[9] = 44603;
                itemDef.originalModelColor[9] = 0;
                itemDef.editedModelColor[10] = 44570;
                itemDef.originalModelColor[10] = 0;
                itemDef.editedModelColor[11] = 4500;
                itemDef.originalModelColor[11] = 0;
                itemDef.modelZoom = 1616;
                itemDef.modelRotationY = 396;
                itemDef.modelRotationX = 1050;
                itemDef.modelOffsetY = -3;
                itemDef.modelOffset1 = 4;
                itemDef.maleWearId = 80016;
                itemDef.femaleWearId = 80016;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.actions[4] = "Drop";
                break;



            case 906:
                itemDef.modelID = 94495;
                itemDef.name = "Depature mask";
                itemDef.description = "Prime torva.";
                itemDef.modelZoom = 1100;
                itemDef.modelOffset1 = 16;
                itemDef.modelOffsetY = 0;
                itemDef.modelRotationY = 533;
                itemDef.modelRotationX = 333;
                itemDef.maleWearId = 94496;
                itemDef.femaleWearId = 94496;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                break;
            case 907:
                itemDef.modelID = 94497;
                itemDef.name = "Depature body";
                itemDef.description = "Torva in its prime.";
                itemDef.modelZoom = 1579;
                itemDef.modelOffset1 = -4;
                itemDef.modelOffsetY = 0;
                itemDef.modelRotationY = 533;
                itemDef.modelRotationX = 333;
                itemDef.maleWearId = 94498;
                itemDef.femaleWearId = 94498;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                break;
            case 10865:
                itemDef.name = "Depature boots";
                break;
                case 12629:
                itemDef.name = "Depature gloves";
                break;
            case 908:
                itemDef.modelID = 94499;
                itemDef.name = "Depature legs";
                itemDef.description = "Torva in its prime.";
                itemDef.modelZoom = 1579;
                itemDef.modelOffset1 = -4;
                itemDef.modelOffsetY = 0;
                itemDef.modelRotationY = 533;
                itemDef.modelRotationX = 333;
                itemDef.maleWearId = 94500;
                itemDef.femaleWearId = 94500;

                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                break;
            case 79:
                itemDef.modelID = 91096;
                itemDef.name = "Sponser Glaive";
                itemDef.description = ("It's a " + itemDef.name + ".");
                itemDef.modelZoom = 1579;
                itemDef.modelOffset1 = -4;
                itemDef.modelOffsetY = 0;
                itemDef.modelRotationY = 533;
                itemDef.modelRotationX = 333;
                itemDef.maleWearId = 32543;
                itemDef.femaleWearId = 32543;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                break;


            //// TRANSFER FROM HERE 25/10/2016
            case 943:
                itemDef.modelID = 32542;
                itemDef.name = "Purple glaive";
                itemDef.description = ("It's a " + itemDef.name + ".");
                itemDef.modelZoom = 1579;
                itemDef.modelOffset1 = -4;
                itemDef.modelOffsetY = 0;
                itemDef.modelRotationY = 533;
                itemDef.modelRotationX = 333;
                itemDef.maleWearId = 32543;
                itemDef.femaleWearId = 32543;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                break;
            case 19:
                itemDef.modelID = 4186;
                itemDef.name = "yellow Vine ";
                itemDef.description = "This shit hurts.";
                itemDef.modelZoom = 1579;
                itemDef.modelOffset1 = -4;
                itemDef.modelOffsetY = 0;
                itemDef.modelRotationX = 533;
                itemDef.modelRotationY = 333;
                itemDef.maleWearId = 4186;
                itemDef.femaleWearId = 4186;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                break;
            case 75:
                itemDef.modelID = 28333;
                itemDef.name = "Shadow helm";
                itemDef.description = "It's a shadow helm";
                itemDef.modelZoom = 850;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 16;
                itemDef.modelRotationX = 533;
                itemDef.modelRotationY = 333;
                itemDef.maleWearId = 28334;
                itemDef.femaleWearId = 28334;//-_
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                break;

            case 80:
                itemDef.modelID = 34867;
                itemDef.name = "Shadow Brutal Whip";
                itemDef.description = ("It's a Shadow Whip");
                itemDef.modelZoom = 1579;
                itemDef.modelOffset1 = -4;
                itemDef.modelOffsetY = 0;
                itemDef.modelRotationX = 333;
                itemDef.modelRotationY = 73;
                itemDef.maleWearId = 34868;
                itemDef.femaleWearId = 34868;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                break;
            case 20079:
                itemDef.name = "@yel@ Gold Predator's Scythe";
                itemDef.modelID = 94458;
                itemDef.maleWearId = 94459;
                itemDef.femaleWearId = 94459;
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.stackable = false;
                break;

            case 20080:
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wield";
                itemDef.name = "Infernal protector";
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
            case 20089:
                itemDef.modelID = 95061;
                itemDef.name = "@red@B@blu@r@gre@u@yel@t@red@a@blu@l @gre@w@yel@h@red@i@blu@p";
                itemDef.description = ("A funky looking rainbow brutal whip");
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 100;
                itemDef.maleWearId = 95062;
                itemDef.femaleWearId = 95062;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                break;

            case 20702:
                itemDef.modelID = 90732;
                itemDef.name = "Bandicoot mask";
                itemDef.description = "Bandicoot mask.";
                itemDef.modelZoom = 1000;
                itemDef.modelRotationY = 100;
                itemDef.modelRotationX = 0;
                itemDef.maleWearId = 90733;
                itemDef.femaleWearId = 90733;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                break;
            case 20701:
                itemDef.modelID = 90734;
                itemDef.name = "Bandicoot body";
                itemDef.description = "Bandicoot body.";
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.maleWearId = 90735;
                itemDef.femaleWearId = 90735;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.colourRedefine = 16000;

                break;
            case 20700:
                itemDef.modelID = 90736;
                itemDef.name = "Bandicoot legs";
                itemDef.description = "Bandicoot legs.";
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 500;
                itemDef.maleWearId = 90737;
                itemDef.femaleWearId = 90737;
                itemDef.modelRotationX = 0;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.colourRedefine = 16000;
                break;
            case 20706:
                itemDef.modelID = 90738;
                itemDef.name = "Bandicoot cape";
                itemDef.description = "It's a pair of Bandicoot wings.";
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffset1 = 0;
                itemDef.modelOffsetY = 0;
                itemDef.maleWearId = 90739;
                itemDef.femaleWearId = 90739;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                break;
            case 20703:
                itemDef.colourRedefine2 = 51;
                itemDef.modelID = 90746;
                itemDef.name = "Bandicoot boots";
                itemDef.description = "It's a pair of Bandicoot boots.";
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffset1 = 4;
                itemDef.modelOffsetY = -32;
                itemDef.maleWearId = 90747;
                itemDef.femaleWearId = 90747;
                itemDef.groundActions = new String[] { null, null, "Take", null, null };
                itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
                break;
            case 20704:
                itemDef.colourRedefine2 = 51;
                itemDef.modelID = 90748;
                itemDef.name = "Bandicoot Gloves";
                itemDef.description = "It's a pair of Bandicoot Gloves.";
                itemDef.modelZoom = 1300;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.modelOffset1 = 3;
                itemDef.modelOffsetY = 5;
                itemDef.maleWearId = 90749;
                itemDef.femaleWearId = 90749;
                itemDef.groundActions = new String[] { null, null, "Take", null, null };
                itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
                break;
            case 20695:
                itemDef.name = "Bandicoot minigun";
                itemDef.modelID = 95031;
                itemDef.femaleWearId = 95032;
                itemDef.maleWearId = 95032;
                itemDef.actions = new String[] { null, "Wear", null, null, "Drop" };
                itemDef.editedModelColor = new int[] {51,60};
                itemDef.newModelColor = new int[]    {98,98};
                break;
            case 20450:
                itemDef.name = "Doomsday Avatar Soul";
                itemDef.modelID = 91365;
                itemDef.actions = new String[] { "eat", null, null, null, "Drop" };
                break;
            case 20451:
                itemDef.name = "Sasuke Soul";
                itemDef.modelID = 91365;
                itemDef.actions = new String[] { "eat", null, null, null, "Drop" };
                break;
            case 20452:
                itemDef.name = "Broly Soul";
                itemDef.modelID = 91365;
                itemDef.actions = new String[] { "eat", null, null, null, "Drop" };
                break;
            case 20453:
                itemDef.name = "Mew Soul";
                itemDef.modelID = 91365;
                itemDef.actions = new String[] { "eat", null, null, null, "Drop" };
                break;

            case 81:
                itemDef.modelID = 34869;
                itemDef.name = "Shadow boots";
                itemDef.description = ("It's a " + itemDef.name + ".");
                itemDef.modelZoom = 825;
                itemDef.modelOffset1 = -4;
                itemDef.modelOffsetY = 0;
                itemDef.modelRotationX = 400;
                itemDef.maleWearId = 34870;
                itemDef.femaleWearId = 34870;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.stackable = false;
                itemDef.editedModelColor = new int[1];
                itemDef.originalModelColor = new int[1];
                itemDef.editedModelColor[0] = 8334;
                itemDef.originalModelColor[0] = 32703;
                break;
            case 82:
                itemDef.modelID = 34871;
                itemDef.name = "Shadow wings";
                itemDef.description = ("It's a " + itemDef.name + ".");
                itemDef.modelZoom = 825;
                itemDef.modelOffset1 = -4;
                itemDef.modelOffsetY = 0;
                itemDef.modelRotationX = 400;
                itemDef.maleWearId = 34872;
                itemDef.femaleWearId = 34872;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.stackable = false;
                itemDef.editedModelColor = new int[1];
                itemDef.originalModelColor = new int[1];
                itemDef.editedModelColor[0] = 8334;
                itemDef.originalModelColor[0] = 32703;
                break;
            case 298: // we will leave this for now
                itemDef.modelID = 77567;
                itemDef.name = "Shadow kiteshield";
                itemDef.description = ("It's a " + itemDef.name + ".");
                itemDef.modelZoom = 1579;
                itemDef.modelOffset1 = -4;
                itemDef.modelOffsetY = 0;
                itemDef.modelRotationX = 533;
                itemDef.modelRotationY = 333;
                itemDef.modelRotationY = 333;
                itemDef.maleWearId = 77568;
                itemDef.femaleWearId = 77568;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.stackable = false;
                break;
            case 5020:
                itemDef.name = "Bandos Avatar Instance";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Drop";
                itemDef.actions[0] = "Teleport";
                itemDef2 = ItemDef.forID(761);
                itemDef.modelID = 46058;
                itemDef.modelOffsetY = 5;
                itemDef.modelOffset1 = -3;
                itemDef.modelRotationX = 0;
                itemDef.modelRotationY = 320;
                itemDef.modelZoom = 3000;

                break;
            case 5018:
                itemDef.name = "Abbadon Instance";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Drop";
                itemDef.actions[0] = "Teleport";
                itemDef2 = ItemDef.forID(761);
                itemDef.modelID = 91780;
                itemDef.modelZoom = 3500;
                itemDef.modelOffsetY = 5;
                itemDef.modelOffset1 = -3;
                itemDef.modelRotationX = 0;
                itemDef.modelRotationY = 320;
                itemDef.editedModelColor = new int[] {60,51,40};
                itemDef.newModelColor = new int[]    {52,52,52};
                break;
            case 5016:
                itemDef.name = "Infernal Groudon Instance";
                itemDef.actions = new String[5];
                itemDef.actions[4] = "Drop";
                itemDef.actions[0] = "Teleport";
                itemDef2 = ItemDef.forID(761);
                itemDef.modelID = 94388;
                itemDef.modelOffsetY = 5;
                itemDef.modelOffset1 = -3;
                itemDef.modelRotationX = 0;
                itemDef.modelRotationY = 320;
                itemDef.modelZoom = 3000;
                itemDef.editedModelColor = new int[] {40,1,24};
                itemDef.newModelColor = new int[]    {60,40,40};
                break;
            case 83:
                itemDef.modelID = 77561;
                itemDef.name = "Shadow brutal whip";
                itemDef.description = ("It's a " + itemDef.name + ".");
                itemDef.modelZoom = 840;
                itemDef.modelOffset1 = -2;
                itemDef.modelOffsetY = 56;
                itemDef.modelRotationX = 0;
                itemDef.modelRotationY = 280;
                itemDef.maleWearId = 77560;
                itemDef.femaleWearId = 77560;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.actions = new String[5];
                itemDef.actions[1] = "Wear";
                itemDef.stackable = false;
                itemDef.editedModelColor = new int[1];
                itemDef.originalModelColor = new int[1];
                itemDef.editedModelColor[0] = 8334;
                itemDef.originalModelColor[0] = 32703;
                break;

            case 11559:
                itemDef.name = "Starter key";
                itemDef2 = ItemDef.forID(989);
                itemDef.modelID = 67575;
                itemDef.maleWearId = itemDef2.maleWearId;
                itemDef.femaleWearId = itemDef2.femaleWearId;
                itemDef.modelOffset1 = itemDef2.modelOffset1;
                itemDef.modelOffsetX = itemDef2.modelOffsetX;
                itemDef.modelOffsetY = itemDef2.modelOffsetY;
                itemDef.modelRotationY = itemDef2.modelRotationY;
                itemDef.modelRotationX = itemDef2.modelRotationX;
                itemDef.modelZoom = itemDef2.modelZoom;
                itemDef.editedModelColor = new int[] {60};
                itemDef.newModelColor = new int[]    {52};
                break;
                
                
                
                
                
                
        }
        return ItemDef_Lewis_1.forDef(itemDef, ID);
    }
}
