package com.arlania;

public final class NPCDef {

	public int rdc = 0;
	public int rdc2 = 0;
	public int rdc3 = 0;
	public int frontLight = 68;
	public int backLight = 820;
	public int rightLight = 0;
	public int middleLight = -1; // Cannot be 0
	public int leftLight = 0;
	public int colourRedefine = 0;
	public int colourRedefine2 = 0;
	public int colourRedefine3 = 0;
	public int colourRedefine4 = 0;
	private String[] actions2;
// one sec.
	public static void copyDef(NPCDef copyFrom, NPCDef copyTo) {
		copyTo.models = copyFrom.models;
		copyTo.runAnim = copyFrom.runAnim;
		copyTo.standAnim = copyFrom.standAnim;
		copyTo.walkAnim = copyFrom.walkAnim;
		copyTo.actions = copyFrom.actions;
		copyTo.name = copyFrom.name;
		copyTo.npcHeadModels = copyFrom.npcHeadModels;
	}

	public static void copyDef(int copyFromId, NPCDef copyTo) {
		copyDef(NPCDef.forID(copyFromId), copyTo);
	}
	
	
	public static NPCDef forID(int i) {
		for (int j = 0; j < 20; j++)
			if (cache[j].type == (long) i)
				return cache[j];
		cacheIndex = (cacheIndex + 1) % 20;
		NPCDef npc = cache[cacheIndex] = new NPCDef();
		if (i >= streamIndices.length)
			return null;
		stream.currentOffset = streamIndices[i];
		npc.type = i;
		npc.readValues(stream);
		if(npc.name != null && npc.name.toLowerCase().contains("bank")) {
			if(npc.actions != null) {
				for(int l = 0; l < npc.actions.length; l++) {
					if(npc.actions[l] != null && npc.actions[l].equalsIgnoreCase("Collect"))
						npc.actions[l] = null;
				}
			}
		}
		npc.id = i;
		switch (i) {



			/*
			 * Ultra bosses
			 */



			//Eternal Dragons

			case 8:
				npc.name = "Eternal Dragon(Melee)";
				npc.models = new int[1];
				npc.models[0] = 14002;
				npc.actions = new String[] {null, "@or2@Attack", null, null, null};

				npc.combatLevel = 100;
				npc.squaresNeeded = 1;
				npc.walkAnim = 5022;
				npc.standAnim = 5022;
				break;
			case 11:
				npc.name = "Eternal Dragon(Ranged)";
				npc.models = new int[1];
				npc.actions = new String[] {null, "@or2@Attack", null, null, null};

				npc.models[0] = 14003;
				npc.combatLevel = 100;
				npc.squaresNeeded = 1;
				npc.walkAnim = 5022;
				npc.standAnim = 5022;
				break;
			case 10:
				npc.name = "Eternal Dragon(Magic)";
				npc.actions = new String[] {null, "@or2@Attack", null, null, null};
				npc.models[0] = 14004;
				npc.combatLevel = 100;
				npc.squaresNeeded = 1;
				npc.walkAnim = 5022;
				npc.standAnim = 5022;
				break;
			case 16:
				npc.name = "Demonic Chest"; //make this a chest npc as object
				npc.combatLevel = 100;
				npc.squaresNeeded = 1;
				npc.walkAnim = 5022;
				npc.standAnim = 5022;
				npc.models = new int[1];
				npc.models[0] = 14005;
				npc.actions = new String[5];
				npc.actions = new String[] {null, "Attack", null, null, null};
				npc.sizeXZ = 120;
				npc.sizeY = 120;
				break;
		
		case 10126:
			npc.name = "Unholy";
			break;
		case 8133:
	        npc.name = "@gre@King Storm Breaker";
    		npc.combatLevel = 100;
            npc.squaresNeeded = 1;
            npc.walkAnim = 11975;
            npc.standAnim = 11973;
        	npc.models = new int[6];
			npc.models[0] = 90927; //HEAD
			npc.models[1] = 90938; //CHEST
			npc.models[2] = 90931; //CAPE
			npc.models[3] = 90935; //HAND
			npc.models[4] = 90933; //LEG
			npc.models[5] = 90929; //WEAPON
			npc.actions = new String[5];
			npc.actions = new String[] {null, "Attack", null, null, null};
		    npc.sizeXZ = 180;
            npc.sizeY = 180;
			break;
		case 5666:
			npc.combatLevel = 115;
			break;
		case 7952:
			npc.actions = new String[] {null, "Attack", null, null, null};
			npc.models = new int[1];
			npc.models[0] = 57513;
		    npc.combatLevel = 0;
			npc.name = "Mew";
			npc.description = "It's Mew.";
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.squaresNeeded = 1;
			npc.sizeXZ = 75;
			npc.sizeY = 75;
			break;
			case 7953:
				//npc.actions = new String[] { "Pick Up", null, null, null, null };
				npc.models = new int[1];
				npc.models[0] = 57513;
				npc.combatLevel = 0;
				npc.name = "Mew";
				npc.description = "It's Mew.";
				npc.standAnim = 11973;
				npc.walkAnim = 11975;
				npc.squaresNeeded = 1;
				npc.sizeXZ = 75;
				npc.sizeY = 75;
				break;
		case 8976:			
			//npc.actions = new String[] { "Pick Up", null, null, null, null };
			npc.models = new int[1];
			npc.models[0] =  57502;
			npc.combatLevel = 0;
			npc.name = "Squirtle";
			npc.description = "Squirtle";
			npc.walkAnim = 819;
			npc.standAnim = 808;
			npc.squaresNeeded = 1;
			npc.sizeXZ = 75;
			npc.sizeY = 75;
            break;
            
		case 7780:
		  npc.name = "Sumona @red@(Boss Tasks Only!!)";
		  npc.description = "@red@Dont Get Task From here if you didnt unlock first 4 bosses!."; 
		break;
		
		case 8387:
			//npc.actions = new String[] { "Pick Up", null, null, null, null };
			npc.models = new int[1];
			npc.models[0] = 96344;
		    npc.combatLevel = 0;
			npc.name = "Pet PikaChu";
			npc.description = "It's PikaChu.";
			npc.walkAnim = 819;
			npc.standAnim = 808;
			npc.squaresNeeded = 1;
			npc.sizeXZ = 50;
			npc.sizeY = 50;
			break;
			
		case 8503:
            npc.name = "Helicopter";
            npc.description = "Pet helicopter";
            npc.standAnim = 3129;
            npc.walkAnim = 3129;
            npc.models = new int[1];
            npc.models[0] = 91813;
            npc.actions = new String[5];
            //npc.actions[1] = "pick-up";
           npc.clickable = false;
            npc.combatLevel = 10000;
            npc.sizeXZ = 125;
            npc.sizeY = 125;
            break;
			
		case 8388:
			//npc.actions = new String[] { "Pick Up", null, null, null, null };
			npc.models = new int[1];
			npc.models[0] = 57504;
		    npc.combatLevel = 0;
			npc.name = "Mudkip";
			npc.description = "It's Mudkip.";
			npc.walkAnim = 819;
			npc.standAnim = 808;
			npc.squaresNeeded = 1;
			npc.sizeXZ = 75;
			npc.sizeY = 75;
			break;
			
		case 8389:
			//npc.actions = new String[] { "Pick Up", null, null, null, null };
			npc.models = new int[1];
			npc.models[0] = 96346;
		    npc.combatLevel = 0;
			npc.name = "Pet Mr.Krabs";
			npc.description = "It's Pet Mr Krabs.";
			npc.walkAnim = 819;
			npc.standAnim = 808;
			npc.squaresNeeded = 1;
			npc.sizeXZ = 50;
			npc.sizeY = 50;
			break;
			
		case 8390:
			//npc.actions = new String[] { "Pick Up", null, null, null, null };
			npc.models = new int[1];
			npc.models[0] = 96347;
		    npc.combatLevel = 0;
			npc.name = "Pet Sonic";
			npc.description = "It's Pet Sonic.";
			npc.walkAnim = 819;
			npc.standAnim = 808;
			npc.squaresNeeded = 1;
			npc.sizeXZ = 50;
			npc.sizeY = 50;
			break;
			
		case 9674:
			//npc.actions = new String[] { "Pick Up", null, null, null, null };
			npc.models = new int[1];
			npc.models[0] = 57512;
		    npc.combatLevel = 0;
			npc.name = "Lugia";
			npc.description = "It's Lugia.";
			npc.walkAnim = 5022;
			npc.standAnim = 5022;
			npc.squaresNeeded = 1;
			npc.sizeXZ = 75;
			npc.sizeY = 75;
			break;


			case 9676:
				//npc.actions = new String[] { "Pick Up", null, null, null, null };
				npc.models = new int[1];
				npc.models[0] = 57515;
				npc.combatLevel = 0;
				npc.name = "Raikou";
				npc.description = "It's Raikou.";
				npc.standAnim = 4484;
				npc.walkAnim = 4488;
				npc.squaresNeeded = 1;
				npc.sizeXZ = 75;
				npc.sizeY = 75;
				break;
			case 9677:
				//npc.actions = new String[] { "Pick Up", null, null, null, null };
				npc.models = new int[1];
				npc.models[0] = 57516;
				npc.combatLevel = 0;
				npc.name = "Suicune";
				npc.description = "It's Suicune.";
				npc.standAnim = 4484;
				npc.walkAnim = 4488;
				npc.squaresNeeded = 1;
				npc.sizeXZ = 75;
				npc.sizeY = 75;
				break;

		case 9845:
			//npc.actions = new String[] { "Pick Up", null, null, null, null };
			npc.models = new int[1];
			npc.models[0] = 57501;
		    npc.combatLevel = 0;
			npc.name = "Diglet";
			npc.description = "It's Diglet.";
			npc.walkAnim = 819;
			npc.standAnim = 808;
			npc.squaresNeeded = 1;
			npc.sizeXZ = 75;
			npc.sizeY = 75;
			break;
			
		case 8438:
			//npc.actions = new String[] { "Pick Up", null, null, null, null };
			npc.models = new int[1];
			npc.models[0] = 12345;
		    npc.combatLevel = 0;
			npc.name = "Charmander";
			npc.description = "It's Charmander.";
			npc.walkAnim = 819;
			npc.standAnim = 808;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 75;
			npc.sizeY = 75;
			break;
			
		case 8972:
			//npc.actions = new String[] { "Pick Up", null, null, null, null };
			npc.models = new int[1];
			npc.models[0] = 57503;
		    npc.combatLevel = 0;
			npc.name = "Bubbasaur";
			npc.description = "It's Bubbasaur.";
			npc.walkAnim = 819;
			npc.standAnim = 808;
			npc.squaresNeeded = 1;
			npc.sizeXZ = 75;
			npc.sizeY = 75;
			break;
			
		case 9347:
			//npc.actions = new String[] { "Pick Up", null, null, null, null };
			npc.models = new int[1];
			npc.models[0] = 57505;
		    npc.combatLevel = 0;
			npc.name = "Starmie";
			npc.description = "It's Starmie.";
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.squaresNeeded = 1;
			npc.sizeXZ = 75;
			npc.sizeY = 75;
			break;
			
		case 8734:
			//npc.actions = new String[] { "Pick Up", null, null, null, null };
			npc.models = new int[1];
			npc.models[0] = 57506;
		    npc.combatLevel = 0;
			npc.name = "Haunter";
			npc.description = "Haunter";
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.squaresNeeded = 1;
			npc.sizeXZ = 75;
			npc.sizeY = 75;
			break;
			
		case 9865:
			//npc.actions = new String[] { "Pick Up", null, null, null, null };
			npc.models = new int[1];
			npc.models[0] = 57508;
		    npc.combatLevel = 0;
			npc.name = "Voltorb";
			npc.description = "It's Voltorb.";
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.squaresNeeded = 1;
			npc.sizeXZ = 75;
			npc.sizeY = 75;
			break;
			case 8632:
				//npc.actions = new String[] { "Pick Up", null, null, null, null };
				npc.models = new int[1];
				npc.models[0] = 57500;
			    npc.combatLevel = 0;
				npc.name = "Togepi";
				npc.description = "Togepi.";
				npc.walkAnim = 819;
				npc.standAnim = 808;
				npc.squaresNeeded = 1;
				npc.sizeXZ = 75;
				npc.sizeY = 75;
				break;

			case 6871:
				//npc.actions = new String[] { "Pick Up", null, null, null, null };
				npc.models = new int[1];
			    npc.combatLevel = 0;
				npc.models[0] = 95093;
				npc.name = "Lucario";
				npc.description = "Lucario.";
				npc.walkAnim = 819;
				npc.standAnim = 808;
				npc.squaresNeeded = 1;
				npc.sizeXZ = 75;
				npc.sizeY = 75;
				break;	
				
			case 7823:
				//npc.actions = new String[] { "Pick Up", null, null, null, null };
				npc.models = new int[1];
			    npc.combatLevel = 0;
				npc.models[0] = 57507;
				npc.name = "Beedrill";
				npc.description = "Beedrill.";
				npc.walkAnim = 819;
				npc.standAnim = 808;
				npc.squaresNeeded = 1;
				npc.sizeXZ = 75;
				npc.sizeY = 75;
				break;
				
		case 6757:
			//npc.actions = new String[] { "Pick Up", null, null, null, null };
			npc.models = new int[1];
			npc.models[0] = 57511;
		    npc.combatLevel = 0;
			npc.name = "Blastoise";
			npc.description = "Blastoise.";
			npc.walkAnim = 819;
			npc.standAnim = 808;
			npc.squaresNeeded = 1;
			npc.sizeXZ = 75;
			npc.sizeY = 75;
			break;
			
		case 4902:
			npc.models = new int[] {40338, 40349, 28798, 40370, 28767, 28810, 48470};
			npc.name = "Expert Miner";
			npc.description = "It's a Monlum.";
			npc.squaresNeeded = 1;
			npc.standAnim = 3461;
			npc.walkAnim = 9678;
			npc.runAnim = 9678;
			npc.turn180AnimIndex = -1;
			npc.turn90CWAnimIndex = -1;
			npc.turn90CCWAnimIndex = -1;
			npc.actions = new String[] {"Talk to", null, null, null, null};
			npc.destColours = new int[] {41270, 41254, 41288, 6378, 6368, 6385};
			npc.originalColours =  new int[] {32798, 32790, 32803, 8398, 8390, 8406};
			npc.drawMinimapDot = true;
			npc.combatLevel = 0;
			npc.sizeXZ = 128;
			npc.sizeY = 120;
			npc.hasRenderPriority = true;
			npc.lightning = 0;
			npc.shadow = 0;
			npc.headIcon = -1;
			npc.elementalIcon = -1;
			npc.degreesToTurn = 32;
			npc.varbitId = -1;
			npc.varSettingsId = -1;
			npc.childrenIDs = null;
			npc.clickable = true;
			break;

			
		case 4999:
			copyDef(2262, npc);
			npc.actions = new String[] {null, "Attack", null, null, null};
			npc.name = "Earth magician";
			break;
			
			case 2236:
				npc.name = "Baphomet";
				npc.models = new int[]{94346};
				npc.combatLevel = 100;
				npc.walkAnim = 819;
				npc.standAnim = 808;
				npc.squaresNeeded = 2;
				npc.sizeXZ = 225;
				npc.sizeY = 225;
				npc.originalColours = new int[]{40,60,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72};
				npc.destColours = new int[] {56,56,56,56,56,56,56,56,56,56,56,56,56,56,56,56,56,56,56,56,56,56,56,56,56};
				break;
			case 2237:
				npc.name = "Pyramid Head";
				npc.models = new int[]{57518};
				npc.combatLevel = 100;
				npc.walkAnim = 819;
				npc.standAnim = 808;
				npc.squaresNeeded = 2;
				npc.sizeXZ = 225;
				npc.sizeY = 225;
				break;
			case 2238:
				npc.name = "Optimus Prime";
				npc.models = new int[]{57519};
				npc.combatLevel = 100;
				npc.walkAnim = 819;
				npc.standAnim = 808;
				npc.squaresNeeded = 2;
				npc.sizeXZ = 225;
				npc.sizeY = 225;
				break;
			case 2239:
				npc.name = "Dark Magician";
				npc.models = new int[]{57520};
				npc.combatLevel = 100;
				npc.walkAnim = 819;
				npc.standAnim = 808;
				npc.squaresNeeded = 2;
				npc.sizeXZ = 225;
				npc.sizeY = 225;
				break;
			case 2240:
				npc.name = "Sasuke";
				npc.models = new int[]{57521};
				npc.combatLevel = 100;
				npc.walkAnim = 819;
				npc.standAnim = 808;
				npc.squaresNeeded = 2;
				npc.sizeXZ = 225;
				npc.sizeY = 225;
				break;
			case 2241:
				npc.name = "Broly";
				npc.models = new int[]{57522};
				npc.combatLevel = 100;
				npc.walkAnim = 819;
				npc.standAnim = 808;
				npc.squaresNeeded = 2;
				npc.sizeXZ = 225;
				npc.sizeY = 225;
				break;
			case 7856:
				npc.models = new int[] {90882};
				npc.name = "Item Combiner";
				npc.actions = new String[] {"Combiner", null, null, null, null};
				npc.walkAnim = 819;
				npc.drawMinimapDot = false;
				npc.standAnim = 808;
				npc.squaresNeeded = 1;
				npc.sizeXZ = 145;
				npc.sizeY = 145;
				break;
				
			case 7843:
				npc.models = new int[7];
				npc.models[0] = 90585; //HEAD
				npc.name = "Spyro";
			    npc.combatLevel = 100;
			    npc.actions = new String[5];
				npc.actions = new String[] {null, "Attack", null, null, null};
				npc.standAnim = 90;
				npc.walkAnim = 4635;
			    npc.squaresNeeded = 3;
				break;
//-------------------------------------------Hallowean models------------------------------------------------//
			case 8544:
				npc.models = new int[7];
				npc.models[0] = 84184; //HEAD
				npc.models[1] = 84180; //CHEST
				npc.models[2] = 84186; //LEGS
				npc.models[4] = 84236; //WEAPON
				npc.models[5] = 84182; //gloves
				npc.models[6] = 84178; //Boots
				npc.name = "pennywise";
			    npc.combatLevel = 999;
			    npc.actions = new String[5];
				npc.actions = new String[] {null, "Attack", null, null, null};
		        npc.walkAnim = 11975;
		        npc.standAnim = 11973;
			    npc.squaresNeeded = 3;
		     	npc.squaresNeeded = 1;
				npc.sizeXZ = 250;
				npc.sizeY = 250;
				break;
				
			case 7535:
				npc.models = new int[7];
				npc.models[0] = 92093; //HEAD
				npc.models[1] = 92095; //CHEST
				npc.models[2] = 92097; //LEGS
				npc.models[4] = 92099; //WEAPON
				npc.models[5] = 92100; //gloves
				npc.models[6] = 92102; //Boots
				npc.name = "Mysterio";
			    npc.combatLevel = 999;
			    npc.actions = new String[5];
				npc.actions = new String[] {null, "Attack", null, null, null};
		        npc.walkAnim = 11975;
		        npc.standAnim = 11973;
			    npc.squaresNeeded = 3;
		     	npc.squaresNeeded = 1;
				npc.sizeXZ = 250;
				npc.sizeY = 250;
				break;
				
			case 8545:
				npc.models = new int[7];
				npc.models[0] = 84237; //HEAD
				npc.models[1] = 84238; //CHEST
				npc.models[2] = 84239; //LEGS
				npc.models[4] = 84240; //WEAPON
				npc.models[5] = 84241; //gloves
				npc.models[6] = 84242; //Boots
				npc.name = "Jason";
			    npc.combatLevel = 999;
			    npc.actions = new String[5];
				npc.actions = new String[] {null, "Attack", null, null, null};
		        npc.walkAnim = 11975;
		        npc.standAnim = 11973;
			    npc.squaresNeeded = 3;
		     	npc.squaresNeeded = 1;
				npc.sizeXZ = 250;
				npc.sizeY = 250;
				break;
				
			case 8546:
				npc.models = new int[7];
				npc.models[0] = 84243; //HEAD
				npc.models[1] = 84244; //CHEST
				npc.models[2] = 84245; //LEGS
				npc.models[4] = 84246; //WEAPON
				npc.models[5] = 84247; //gloves
				npc.models[6] = 84248; //Boots
				npc.name = "Freddy";
			    npc.combatLevel = 999;
			    npc.actions = new String[5];
				npc.actions = new String[] {null, "Attack", null, null, null};
		        npc.walkAnim = 11975;
		        npc.standAnim = 11973;
			    npc.squaresNeeded = 3;
		     	npc.squaresNeeded = 1;
				npc.sizeXZ = 250;
				npc.sizeY = 250;
				break;
				
			case 8547:
				npc.models = new int[7];
				npc.models[0] = 84249; //HEAD
				npc.models[1] = 84250; //CHEST
				npc.models[2] = 84251; //LEGS
				npc.models[4] = 84252; //WEAPON
				npc.models[5] = 84253; //gloves
				npc.models[6] = 84248; //Boots
				npc.name = "Michael Myers";
			    npc.combatLevel = 999;
			    npc.actions = new String[5];
				npc.actions = new String[] {null, "Attack", null, null, null};
		        npc.walkAnim = 11975;
		        npc.standAnim = 11973;
			    npc.squaresNeeded = 3;
		     	npc.squaresNeeded = 1;
				npc.sizeXZ = 250;
				npc.sizeY = 250;
				break;
				
			case 8548:
				npc.models = new int[] {84254};
				npc.name = "Killer Pumpkin";
			    npc.combatLevel = 4000;
				npc.actions = new String[5];
				npc.actions = new String[] {null, "Attack", null, null, null};
		        npc.walkAnim = 11975;
		        npc.standAnim = 11973;
	            npc.squaresNeeded = 3;
				npc.squaresNeeded = 1;
				npc.sizeXZ = 500;
				npc.sizeY = 500;
				break;
				
			case 8733:
				npc.models = new int[] {94314};
				npc.name = "Cosmetic Piniata";
			    npc.combatLevel = 1;
				npc.actions = new String[5];
				npc.actions = new String[] {null, "Attack", null, null, null};
				npc.walkAnim = 819;
				npc.standAnim = 808;
				npc.squaresNeeded = 1;
				npc.sizeXZ = 250;
				npc.sizeY = 250;
				break;
			case 8736:
				npc.models = new int[] {99543};
				npc.name = "Bomby";
				npc.combatLevel = 1;
				npc.actions = new String[5];
				npc.actions[0] = "Tutorial";
				npc.walkAnim = 819;
				npc.standAnim = 808;
				npc.squaresNeeded = 1;
				npc.sizeXZ = 150;
				npc.sizeY = 150;
				break;
			case 8735:
				npc.models = new int[] {99543};
				npc.name = "Bomby's minions";
				npc.combatLevel = 1;
				npc.actions = new String[5];
				npc.actions = new String[] {null, "Attack", null, null, null};
				npc.walkAnim = 819;
				npc.standAnim = 808;
				npc.squaresNeeded = 1;
				npc.sizeXZ = 85;
				npc.sizeY = 85;
				break;
			case 8766:
				npc.name = "@whi@Icy Vorago";
				npc.walkAnim = 1660;
				npc.standAnim = -1;
				npc.models = new int[]{90322};
				npc.combatLevel = 999;
				npc.standAnim = 12193;
				npc.walkAnim = 12203;
				npc.degreesToTurn = 32;
				npc.actions = new String[5];
				npc.actions[1] = "Attack";
				npc.sizeY = 250;
				npc.sizeXZ = 250;
				npc.squaresNeeded = 3;
				npc.drawMinimapDot = true;
				break;	
				
			case 6529:
				//npc.actions = new String[] { "Pick Up", null, null, null, null };
				npc.models = new int[1];
				npc.models[0] = 90322;
			    npc.combatLevel = 0;
				npc.name = "Pet Icy Vorago";
				npc.description = "Pet Icy Vorago.";
				npc.standAnim = 12193;
				npc.walkAnim = 12203;
				npc.degreesToTurn = 32;
				npc.squaresNeeded = 1;
				npc.sizeXZ = 80;
				npc.sizeY = 80;
				break;
//-------------------------------------------Hallowean models------------------------------------------------//
			case 8239:
				npc.models = new int[] {96164};
				npc.name = "Dead Pirate";
			    npc.combatLevel = 4000;
				npc.actions = new String[5];
				npc.actions = new String[] {null, "Attack", null, null, null};
	            npc.walkAnim = 11975;
	            npc.standAnim = 11973;
	            npc.squaresNeeded = 3;
				npc.squaresNeeded = 1;
				break;
				
			case 6442:
				npc.models = new int[] {9897};
				npc.name = "Devil Beast";
				npc.actions = new String[5];
				npc.actions = new String[] {null, "Attack", null, null, null};
				npc.combatLevel = 166;
				npc.standAnim = 12248;
				npc.walkAnim = 12246;
				npc.sizeY = 150;
				npc.sizeXZ = 150;
				npc.squaresNeeded = 2;
				break;
				
			case 7451:
				npc.models = new int[] {12142};
				npc.name = "@red@Hades";
				npc.actions = new String[5];
				npc.actions = new String[] {null, "Attack", null, null, null};
				npc.combatLevel = 166;
				npc.standAnim = 12248;
				npc.walkAnim = 12246;
				npc.sizeY = 150;
				npc.sizeXZ = 150;
				npc.squaresNeeded = 2;
				break;
				
			case 50:
				npc.name = "Undead Gold Dragon";
				npc.actions = new String[5];
			     npc.actions = new String[] {null, "Attack", null, null, null};
				npc.models = new int[] {91692};
				npc.combatLevel = 276;
				//npc.colourRedefine = 74441;
				npc.standAnim = 12248;
				npc.walkAnim = 12246;
				npc.sizeY = 140;
				npc.sizeXZ = 140;
				npc.squaresNeeded = 3;
				break;

		/*	case 1677:
			case 1676:
			case 1678:
			    NPCDef babydragon = NPCDef.forID(52);
				npc.name = "Skiadrum";
                npc.models = new int[1];
                npc.models[0] = babydragon.models[0];
                npc.colourRedefine2 = 74634;
				npc.walkAnim = babydragon.walkAnim;
				npc.standAnim = babydragon.standAnim;
				npc.squaresNeeded = babydragon.squaresNeeded;
				npc.elementalIcon = 6;
				break; */			
			case 1677:
            case 1676:
            case 1678:
            	NPCDef al = new NPCDef().forID(4419);
				npc.name = "ALIEN";
				npc.actions = new String[5];
				npc.actions[1] = "Attack";
				npc.sizeXZ = 200;
				npc.sizeY = 200;
				npc.standAnim = al.standAnim;
				npc.walkAnim = al.walkAnim;
				npc.models = al.models;
                break;

            case 8002:
				al = new NPCDef().forID(4419);
                npc.name = "Afk merchant";
                npc.actions = new String[] {"AFK Shop", null , "Golden AFK Shop(coming soon)", null, null};
				npc.sizeXZ = 200;
				npc.sizeY = 200;
				npc.standAnim = al.standAnim;
				npc.walkAnim = al.walkAnim;
				npc.models = al.models;
                break;

                
 //  * -----------------------Custom Boses by ValacialX----------------------------*//
            case 9200:
                npc.name = "@or1@Sora From Kingdom Hearts";
          		npc.combatLevel = 100;
                  npc.squaresNeeded = 1;
                  npc.walkAnim = 11975;
                  npc.standAnim = 11973;
              	npc.models = new int[7];
      			npc.models[0] = 75637; //HEAD
      			npc.models[1] = 75635; //CHEST
      			npc.models[2] = 75636; //CAPE
      			npc.models[3] = 75633; //HAND
      			npc.models[4] = 75634; //LEG
      			npc.models[5] = 75665; //WEAPON
      			npc.models[6] = 86544; //WEAPON
      			npc.actions = new String[5];
      			npc.actions = new String[] {null, "Attack", null, null, null};
      		    npc.sizeXZ = 180;
                  npc.sizeY = 180;
      			break;
                
        	case 17:
				npc.name = "Jenny (Teleports you to Upgrade Zone)";
				npc.actions = new String[5];
				npc.actions[2] = "Teleport";
				break;
                
            case 9202:
                npc.name = "@blu@Thanos";
        		npc.combatLevel = 65;
                npc.models = new int[]{94277};
                npc.actions = new String[] {null, "@Blu@Attack", null, null, null};
                npc.squaresNeeded = 1;
                npc.walkAnim = 11975;
                npc.standAnim = 11973;
                npc.walkAnim = 11975;
                npc.sizeXZ = 150;
                npc.sizeY = 150;
                break; 
                
    		case 7563:
    	        npc.name = "@gre@Samurai";
    		    		npc.combatLevel = 100;
    		            npc.squaresNeeded = 1;
    		            npc.walkAnim = 11975;
    		            npc.standAnim = 11973;
    		        	npc.models = new int[7];
    					npc.models[0] = 90707; //HEAD
    					npc.models[1] = 94130; //CHEST
    					npc.models[2] = 94132; //CAPE
    					npc.models[3] = 94134; //HAND
    					npc.models[4] = 92115; //LEG
    					npc.models[5] = 92114; //WEAPON
    					npc.models[6] = 94484; //WEAPON
    					npc.actions = new String[5];
    					npc.actions = new String[] {null, "Attack", null, null, null};
    				    npc.sizeXZ = 180;
    		            npc.sizeY = 180;
    					break;
                 
        	case 6754:
    			npc.name = "RoseEater";
    			npc.models = new int[] { 94433 };
    			npc.standAnim = 12790;
    			npc.walkAnim = 12790;
    			npc.combatLevel = 765;
    			npc.actions = new String[5];
    			npc.actions = new String[] {null, "Attack",  null, null, null};
    		    npc.originalColours = new int[]{55};
                npc.destColours = new int[] {57};
    			npc.sizeXZ = 100;
    			npc.sizeY = 100;
    		break;
    		
        	   case 7643:
                npc.name = "Voldemort";
           		npc.combatLevel = 100;
                npc.squaresNeeded = 1;
                npc.walkAnim = 11975;
                npc.standAnim = 11973;
               	npc.models = new int[7];
       			npc.models[0] = 57544; //HEAD
       			npc.models[1] = 57545; //CHEST
       			npc.models[2] = 57546; //CAPE
       			npc.models[3] = 57547; //HAND
       			npc.models[4] = 57548; //LEG
       			npc.models[5] = 57549; //LEG
       			npc.actions = new String[5];
       	        npc.actions = new String[] {null, "@red@Attack", null, null, null};
       		    npc.sizeXZ = 120;
                npc.sizeY = 120;
       			break;
                
            case 9201:
                npc.name = "@whi@Dead Bone Guardian";
        		npc.combatLevel = 100;
                npc.squaresNeeded = 1;
                npc.walkAnim = 11975;
                npc.standAnim = 11973;
            	npc.models = new int[8];
    			npc.models[0] = 96546; //HEAD
    			npc.models[1] = 96548; //CHEST
    			npc.models[2] = 96550; //CAPE
    			npc.models[3] = 96553; //HAND
    			npc.models[4] = 96552; //LEG
    			npc.models[5] = 96555; //LEG
    			npc.actions = new String[5];
    	        npc.actions = new String[] {null, "@red@Attack", null, null, null};
    		    npc.sizeXZ = 200;
                npc.sizeY = 200;
    			break;
    			
            case 7916:
                npc.name = "@or1@Bone Beast";
                npc.combatLevel = 666;
                npc.squaresNeeded = 1;
            	npc.models = new int[7];
    			npc.models[0] = 84259;
     			npc.actions = new String[5];
    	        npc.actions = new String[] {null, "@red@Attack", null, null, null};
    			npc.standAnim = 1198;
    			npc.walkAnim = 10055;
    			break;
    			
            case 9325:
                npc.name = "@blu@Blood Lord";
        		npc.combatLevel = 100;
                npc.squaresNeeded = 1;
                npc.walkAnim = 11975;
                npc.standAnim = 11973;
            	npc.models = new int[6];
    			npc.models[0] = 94535; //HEAD
    			npc.models[1] = 94531; //CHEST
    			npc.models[2] = 94533; //CAPE
    			npc.models[3] = 94538; //HAND
    			npc.models[4] = 94537; //LEG
    			npc.models[5] = 94529; //WEAPON
    			npc.actions = new String[5];
    			npc.actions = new String[] {null, "Attack", null, null, null};
    		    npc.sizeXZ = 180;
                npc.sizeY = 180;
    			break;
    			
        	case 8543:
    			npc.combatLevel = 999;
    			//npc.actions = new String[] { "Pick Up", null, null, null, null };
    			npc.actions = new String[5];
            	npc.models = new int[6];
        	    npc.models[0] = 98222; //HEAD
        		npc.models[1] = 98223; //CHEST
        		npc.models[2] = 98224; //CAPE
        		npc.models[3] = 98225; //HAND
        		npc.models[4] = 98226; //LEG
        		npc.models[5] = 98221; //WEAPON
    			npc.name = "Pet Sephiroth";
    			npc.description = "It's Sephiroth.";
    			npc.walkAnim = 11975;
                npc.standAnim = 11973;
    			npc.squaresNeeded = 1;
    		    npc.sizeXZ = 70;
                npc.sizeY =  70;
    			break;
    			
        	case 4638:
    			npc.combatLevel = 999;
    			npc.actions = new String[5];
				//npc.actions[0] = "pick-up";
    			npc.actions[2] = "Bank";
    			npc.name = "Pet World";
    			 npc.models = new int[]{94460};
    			npc.description = "It's Pet World.";
    			npc.walkAnim = 11975;
                npc.standAnim = 11973;
    			npc.squaresNeeded = 1;
    		    npc.sizeXZ = 150;
                npc.sizeY =  150;
    			break;
    			
            case 9326:
                npc.name = "@blu@Sephiroth's Minion";
                npc.combatLevel = 164;
                npc.squaresNeeded = 1;
                npc.walkAnim = 11975;
                npc.standAnim = 11973;
            	npc.models = new int[6];
    			npc.models[0] = 98222; //HEAD
    			npc.models[1] = 98223; //CHEST
    			npc.models[2] = 98224; //CAPE
    			npc.models[3] = 98225; //HAND
    			npc.models[4] = 98226; //LEG
    			npc.models[5] = 98221; //WEAPON
    			npc.actions = new String[5];
    			npc.actions = new String[] {null, "Attack", null, null, null};
    		    npc.sizeXZ = 150;
                npc.sizeY = 150;
    			break;
    			
            case 7845:
                npc.name = "@gre@Toxic Defender";
                npc.combatLevel = 164;
                npc.squaresNeeded = 1;
                npc.walkAnim = 11975;
                npc.standAnim = 11973;
            	npc.models = new int[8];
    			npc.models[0] = 94540; //HEAD
    			npc.models[1] = 94542; //CHEST
    			npc.models[2] = 94544; //CAPE
    			npc.models[3] = 94415; //HAND
    			npc.models[4] = 90787; //LEG
    			npc.models[5] = 90788; //WEAPON
    			npc.models[6] = 94432; //WEAPON
    			npc.models[7] = 94430; //WEAPON
    			npc.originalColours = new int[]{60,61,62,63,64,65,66,67,68,69,70,71,72,73};
				npc.destColours = new int[]  {66,66,66,66,66,66,66,66,66,66,66,66,66,66};
    			npc.actions = new String[5];
    			npc.actions = new String[] {null, "Attack", null, null, null};
    		    npc.sizeXZ = 125;
                npc.sizeY = 125;
    			break;
    			
            case 8721:
                npc.name = "@blu@Darth Vader";
                npc.combatLevel = 420;
                npc.actions = new String[] {null, "@or2@Attack", null, null, null};
                npc.combatLevel = 1420;
                npc.squaresNeeded = 1;
                npc.combatLevel = 653;
                npc.walkAnim = 11975;
                npc.standAnim = 11973;
                npc.walkAnim = 11975;
                npc.sizeXZ = 230;
                npc.sizeY = 230;
                npc.models = new int[]{99542};
    			break;
                
        	case 6102:
        		   npc.name = "@red@Donkey Kong";
                   npc.models = new int[]{94219};
                   npc.actions = new String[] {null, "@or2@Attack", null, null, null};
                   npc.squaresNeeded = 1;
                   npc.combatLevel = 80;
                   npc.walkAnim = 11975;
                   npc.standAnim = 11973;
                   npc.walkAnim = 11975;
                   npc.sizeXZ = 230;
                   npc.sizeY = 230;
                   break;  
                   
        	case 6542:
    			npc.name = "Entei";
				npc.models = new int[1];
				npc.models[0] = 57514;
				npc.combatLevel = 0;
				npc.name = "Entei";
				npc.description = "It's Entei.";
				npc.standAnim = 4484;
				npc.walkAnim = 4488;
				npc.squaresNeeded = 1;
				npc.sizeXZ = 100;
				npc.sizeY = 100;
    			break;
                   
                   
                	case 9356:
                		   npc.name = "@or3@Infinito";
                           npc.models = new int[]{94488};
                           npc.actions = new String[] {null, "@or2@Attack", null, null, null};
                           npc.squaresNeeded = 1;
                           npc.combatLevel = 653;
                           npc.walkAnim = 11975;
                           npc.standAnim = 11973;
                           npc.walkAnim = 11975;
                           npc.sizeXZ = 160;
                           npc.sizeY = 160;
                           break;  
                           
                   
          	case 9786:
     		   npc.name = "@blu@Sonic";
                npc.models = new int[]{94216};
                npc.actions = new String[] {null, "@or2@Attack", null, null, null};
                npc.squaresNeeded = 1;
                npc.combatLevel = 653;
                npc.walkAnim = 11975;
                npc.standAnim = 11973;
                npc.walkAnim = 11975;
                npc.sizeXZ = 110;
                npc.sizeY = 110;
                break;   
                
          	case 9236:
      		   npc.name = "Sephiroth";
				npc.combatLevel = 164;
				npc.squaresNeeded = 1;
				npc.walkAnim = 11975;
				npc.standAnim = 11973;
				npc.models = new int[6];
				npc.models[0] = 98222; //HEAD
				npc.models[1] = 98223; //CHEST
				npc.models[2] = 98224; //CAPE
				npc.models[3] = 98225; //HAND
				npc.models[4] = 98226; //LEG
				npc.models[5] = 98221; //WEAPON
				npc.actions = new String[5];
				npc.actions = new String[] {null, "Attack", null, null, null};
				npc.sizeXZ = 150;
				npc.sizeY = 150;
                 break;   
                
                
           	case 6654:
      		   npc.name = "@or1@Harry Potter #Potato";
                 npc.models = new int[]{130033};
                 npc.actions = new String[] {null, "@or3@Talk To", null, null, null};
                 npc.squaresNeeded = 1;
                 npc.combatLevel = 653;
                 npc.walkAnim = 11975;
                 npc.standAnim = 11973;
                 npc.walkAnim = 11975;
                 npc.sizeXZ = 180;
                 npc.sizeY = 180;
                 break;    
                
        	case 8146:
      		   npc.name = "@yel@Knuckles";
                 npc.models = new int[]{94217};
                 npc.actions = new String[] {null, "@or2@Attack", null, null, null};
                 npc.squaresNeeded = 1;
                 npc.combatLevel = 653;
                 npc.walkAnim = 11975;
                 npc.standAnim = 11973;
                 npc.walkAnim = 11975;
                 npc.sizeXZ = 120;
                 npc.sizeY = 120;
                 break;
                 
        	case 7532:
       		   npc.name = "@or3@Uchiha Madara";
                  npc.models = new int[]{1999};
                  npc.actions = new String[] {null, "@or2@Attack", null, null, null};
                  npc.squaresNeeded = 1;
          		  npc.combatLevel = 85;
                  npc.walkAnim = 11975;
                  npc.standAnim = 11973;
                  npc.walkAnim = 11975;
                  npc.sizeXZ = 150;
                  npc.sizeY = 150;
                  break;

			case 7536:
				npc.name = "@or3@Sasuke";
				npc.models = new int[]{153};
				npc.actions = new String[] {null, "@or2@Attack", null, null, null};
				npc.squaresNeeded = 1;
				npc.combatLevel = 85;
				npc.walkAnim = 11975;
				npc.standAnim = 11973;
				npc.walkAnim = 11975;
				npc.sizeXZ = 120;
				npc.sizeY = 120;
				break;

			case 7534:
				npc.name = "@or3@Orochimaru";
				npc.models = new int[]{130071};
				npc.actions = new String[] {null, "@or2@Attack", null, null, null};
				npc.squaresNeeded = 5;
				npc.combatLevel = 85;
				npc.walkAnim = 11975;
				npc.standAnim = 11973;
				npc.walkAnim = 11975;
				npc.sizeXZ = 60;
				npc.sizeY = 60;
				break;
			case 500:
				npc.name = "@or3@Owner Set(Melee)";
				npc.models = new int[]{1};
				npc.actions = new String[] {null, "@or2@Attack", null, null, null};
				npc.squaresNeeded = 1;
				npc.combatLevel = 85;
				npc.walkAnim = 11975;
				npc.standAnim = 11973;
				npc.sizeXZ = 100;
				npc.sizeY = 100;
				break;
			case 7538:
				npc.name = "@or3@Owner Set(Ranged)";
				npc.models = new int[]{130099};
				npc.actions = new String[] {null, "@or2@Attack", null, null, null};
				npc.squaresNeeded = 1;
				npc.combatLevel = 85;
				npc.walkAnim = 11975;
				npc.standAnim = 11973;
				npc.walkAnim = 11975;
				npc.sizeXZ = 100;
				npc.sizeY = 100;
				break;
			case 7539:
				npc.name = "@or3@Owner Set(Magic)";
				npc.models = new int[]{130087};
				npc.actions = new String[] {null, "@or2@Attack", null, null, null};
				npc.squaresNeeded = 1;
				npc.combatLevel = 85;
				npc.walkAnim = 11975;
				npc.standAnim = 11973;
				npc.walkAnim = 11975;
				npc.sizeXZ = 100;
				npc.sizeY = 100;
				break;
                
 //  * -----------------------Custom Boses by ValacialX Raid----------------------------*//
                
                
			case 1300:
				npc.name = "Godzilla pet";
				npc.models = new int[]{95122};
					npc.actions = new String[5];
					//npc.actions[0] = "pick-up";
					npc.squaresNeeded = 3;
				npc.combatLevel = 999;
				npc.standAnim = 11973;
				npc.originalColours = new int[]{40,60,51,52,53,54,55,56,57,58,59};
				npc.destColours = new int[] {55,55,55,55,55,55,55,55,55,55,55};
				npc.walkAnim = 11975;
				npc.sizeXZ = 100;
				npc.sizeY = 100;
				break;
		/*	case 6102:
				babydragon = NPCDef.forID(52);
				npc.name = "Aurelia";
				npc.models = new int[1];
				npc.models[0] = babydragon.models[0];
				npc.colourRedefine2 = 33250;
				npc.walkAnim = babydragon.walkAnim;
				npc.standAnim = babydragon.standAnim;
				npc.squaresNeeded = babydragon.squaresNeeded;
				npc.elementalIcon = 5;
				break; */
                
			case 9172:
				npc.elementalIcon = 2;
				break;
			case 1265:
                npc.name = "@red@BOMBY'S MINIONS";
                npc.models = new int[]{99543};
                npc.walkAnim = 11975;
                npc.standAnim = 11973;
                npc.squaresNeeded = 2;
                break;

            case 4325:
            case 2021:
                npc.actions = new String[5];
                //npc.actions[0] = "pick-up";
                break;
//            case 2316:
//            case 2239:
//                npc.actions = new String[5];
//                npc.name = "Pig pet";
//                //npc.actions[0] = "pick-up";
//                break;
			case 1266:
				npc.actions = new String[5];
			     //npc.actions[0] = "pick-up";
    			npc.actions[2] = "Bank";
    			npc.actions[3] = "Teleport Executive Zone";
				npc.name = "Yoda pet (Executive Pet)";
				npc.models = new int[]{46363};
				npc.walkAnim = 11975;
				npc.standAnim = 11973;
				npc.sizeXZ = 140;
				npc.sizeY = 140;
				npc.squaresNeeded = 1;
				npc.drawMinimapDot = false;
				break;
			case 7527:
				npc.actions = new String[5];
			     //npc.actions[0] = "pick-up";
				npc.name = "Scyther";
				npc.models = new int[]{57509};
				npc.walkAnim = 11975;
				npc.standAnim = 11973;
				npc.sizeXZ = 140;
				npc.sizeY = 140;
				npc.squaresNeeded = 1;
				npc.drawMinimapDot = false;
				break;
		/*	case 1459:
				babydragon = NPCDef.forID(52);
				npc.name = "Allan";
				npc.models = new int[1];
				npc.models[0] = babydragon.models[0];
				npc.colourRedefine2 = 95000;
				npc.walkAnim = babydragon.walkAnim;
				npc.standAnim = babydragon.standAnim;
				npc.squaresNeeded = babydragon.squaresNeeded;
				npc.elementalIcon = 3;
				break; */
				 case 1459:
		         npc.name = "Mewtwo";
		         npc.models = new int[]{95087};
		         npc.combatLevel = 60;
		         npc.actions = new String[] {null, "Attack", null, null, null};
		         npc.standAnim = 11973;
		         npc.walkAnim = 11975;
		         break;
			case 1302:
				npc.name = "Donkey Kong Pet";
				npc.actions = new String[5];
				//npc.actions[0] = "pick-up";
				npc.models = new int[]{94219};
				npc.combatLevel = 666;
				npc.standAnim = 11973;
				npc.walkAnim = 11975;
				npc.sizeXZ = 100;
				npc.sizeY = 100;
				break;

		/*	case 1880:
                babydragon = NPCDef.forID(3376);
                npc.name = "weisslogia";
                npc.models = new int[1];
                npc.models[0] = babydragon.models[0];
                npc.colourRedefine2 = 1038;
                npc.walkAnim = babydragon.walkAnim;
                npc.standAnim = babydragon.standAnim;
                npc.squaresNeeded = babydragon.squaresNeeded;
                npc.ementalIcon = 7;
				break; */
		
			case 1880:
                npc.name = "Bowser";
                npc.combatLevel = 36;
    			npc.models = new int[]{99000};
                npc.actions = new String[] {null, "Attack", null, null, null};
				npc.standAnim = 11973;
				npc.walkAnim = 11975;
                break;

			case 1301:
				npc.name = "Deadly Assassin Pet";
				npc.combatLevel = 435;
				npc.actions = new String[5];
				//npc.actions[0] = "pick-up";
				npc.models = new int[6];
				npc.models[0] = 80341;
				npc.models[1] = 80343;
				npc.models[2] = 94529; //wep
				npc.models[3] = 50;
				npc.models[4] = 52;
				npc.models[5] = 80345;
				npc.standAnim = 71;
				npc.walkAnim = 70;
				npc.sizeXZ = 100;
				npc.sizeY = 100;
				break;
		/*
		 * End of Edited NPC defs
		 */
		
		/*
		 * Custom Pets Added
		 */
		case 6357:
			npc.actions = new String[5];
			//npc.actions[0] = "pick-up";
			npc.models = new int[1];
			npc.models[0] = 95087;
			npc.name = "MewTwo";
			npc.combatLevel = 0;
			npc.description = "It's MewTwo.";
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.sizeXZ = 50;
			npc.sizeY = 75;
			npc.squaresNeeded = 1;
			break;
		case 6358:
			npc.actions = new String[5];
			//npc.actions[0] = "pick-up";			
			npc.models = new int[1];
			npc.models[0] = 95088;
			npc.name = "Charmeleon";
			npc.combatLevel = 0;
			npc.description = "It's Charmeleon.";
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.squaresNeeded = 2;
			npc.sizeXZ = 50;
			npc.sizeY = 75;
			break;
		case 6359:
			npc.actions = new String[5];
			//npc.actions[0] = "pick-up";
			npc.models = new int[1];
			npc.models[0] = 95089;
			npc.name = "Luigi";
			npc.combatLevel = 0;
			npc.description = "It's Luigi.";
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.squaresNeeded = 1;
			break;
			
		case 8622:
			npc.name = "Pet Vorago";
			npc.standAnim = -1;
			npc.walkAnim = 1661;
			npc.models = new int[]{60001};
			npc.combatLevel = 1337;
			npc.turn180AnimIndex = -1;
			npc.turn90CCWAnimIndex = -1;
			npc.turn90CWAnimIndex = -1;
			npc.degreesToTurn = 32;
			npc.actions = new String[5];
			//npc.actions[0] = "pick-up";
			npc.squaresNeeded = 3;
			npc.drawMinimapDot = true;
			break;
			
		case 7888:
			npc.models = new int[] {71096};
			npc.name = "Loot Chest";
			npc.actions = new String[] {"Open", null, null, null, null};
			npc.squaresNeeded = 1;
			npc.drawMinimapDot = false;
			break;
			

			
		case 872:
			npc.name = "Teleport Wizzard";
			npc.actions = new String[] {"Teleport", null, null, null, null};
			npc.squaresNeeded = 1;
			break;
			
		case 6755:
			npc.models = new int[] {96023};
			npc.name = "X-Arcade @red@(Raid)";
		    npc.combatLevel = 0;
			npc.actions = new String[5];
			npc.actions[2] = "Teleport";
			npc.squaresNeeded = 1;
			break;
			
		case 9872:
			npc.models = new int[] {96164};
			npc.name = "Dead Pirate";
		    npc.combatLevel = 4000;
			npc.actions = new String[5];
			npc.actions[0] = "Teleport";
			npc.actions[2] = "Shop";
            npc.walkAnim = 11975;
            npc.standAnim = 11973;
			npc.squaresNeeded = 1;
			break;
			
		case 6432:
            npc.name = "Cloud Strife";
    		npc.combatLevel = 100;
            npc.squaresNeeded = 1;
            npc.walkAnim = 11975;
            npc.standAnim = 11973;
        	npc.models = new int[7];
			npc.models[0] = 98227; //HEAD
			npc.models[1] = 78226; //CHEST
			npc.models[2] = 78227; //CAPE
			npc.models[3] = 90815; //HAND
			npc.models[4] = 90817; //LEG
			npc.models[5] = 90819; //WEAPON
			npc.models[6] = 90826; //WEAPON
			npc.actions = new String[5];
			npc.actions = new String[] {null, "Attack", null, null, null};
		    npc.sizeXZ = 180;
            npc.sizeY = 180;
			break;
            
		case 6433:
            npc.name = "Executive Dragon";
            npc.combatLevel = 666;
            NPCDef NPCDef2 = NPCDef.forID(4972);
            npc.standAnim = NPCDef2.standAnim;
            npc.walkAnim = NPCDef2.walkAnim;
            npc.turn180AnimIndex = -1;
            npc.turn90CCWAnimIndex = -1;
            npc.turn90CWAnimIndex = -1;
            npc.degreesToTurn = 32 ;
            npc.models = new int[1];
            npc.models[0] = 84228;
            npc.sizeY = 120;
            npc.sizeXZ = 120;
            npc.squaresNeeded = 1;
            npc.actions = new String[]{null, "Attack", null, null, null};
            break;
			

		case 6360:
			//npc.actions = new String[] { "Pick Up", null, null, null, null };
			npc.models = new int[1];
			npc.models[0] = 95093;
			npc.name = "Lucario";
			npc.description = "It's Lucario.";
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.squaresNeeded = 1;
			break;
		case 7542:
			//npc.actions = new String[] { "Pick Up", null, null, null, null };
			npc.models = new int[1];
			npc.models[0] = 84222;
			npc.name = "Puffer Fish Pet";
			npc.description = "It's Puffer Fish Pet.";
			npc.standAnim = 4484;
			npc.walkAnim = 4488;
			npc.squaresNeeded = 2;
			break;
		case 8727:
			//npc.actions = new String[] { "Pick Up", null, null, null, null };
			npc.models = new int[1];
			npc.models[0] = 76333;
			npc.name = "Lucifer's Dog";
			npc.description = "It's Lucifer's Dog.";
			npc.standAnim = 4484;
			npc.walkAnim = 4488;
			npc.squaresNeeded = 1;
			break;
		case 6361:
			npc.models = new int[1];
			npc.models[0] = 95091;
			npc.name = "Squirtle";
			npc.description = "It's squirtle.";
			//npc.actions = new String[] { "Pick Up", null, null, null, null };
			npc.combatLevel = 85;
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.squaresNeeded = 1;
			npc.sizeXZ = 50;
			npc.sizeY = 75;
			break;
		case 6317:
			npc.name = "Pet Magma Golem";
			//npc.actions = new String[] { "Pick Up", null, null, null, null };
			npc.models = new int[1];
			npc.models[0] = 58932;
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.squaresNeeded = 1;
			break;
		case 6318:
			npc.name = "Pet Golem (X)";
			//npc.actions = new String[] { "Pick Up", null, null, null, null };
			npc.models = new int[1];
			npc.models[0] = 58931;
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.squaresNeeded = 1;
			break;
		case 6362:
			//npc.actions = new String[] { "Pick Up", null, null, null, null };
			npc.models = new int[1];
			npc.models[0] = 95092;
			npc.name = "Pet Mr Krabs";
			npc.description = "It's Mr. Krabs.";
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.squaresNeeded = 3;
			npc.sizeXZ = 75;
			npc.sizeY = 100;
			break;
		case 8382:
			//npc.actions = new String[] { "Pick Up", null, null, null, null };
			npc.models = new int[1];
			npc.models[0] = 57510;
			npc.name = "Machamp";
			npc.description = "It's Machamp.";
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.squaresNeeded = 1;
			npc.sizeXZ = 100;
			npc.sizeY = 100;
			break;
		case 6363:
			npc.combatLevel = 0;
			//npc.actions = new String[] { "Pick Up", null, null, null, null };
			npc.models = new int[1];
			npc.models[0] = 95094;
			npc.name = "Pet Sonic";
			npc.description = "It's Sonic.";
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.squaresNeeded = 1;
			break;
		case 6364:
			//npc.actions = new String[] { "Pick Up", null, null, null, null };
			npc.models = new int[1];
			npc.models[0] = 95095;
			npc.name = "Pet Homer";
			npc.description = "It's Homer.";
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.squaresNeeded = 1;
			npc.sizeXZ = 50;
			npc.sizeY = 75;
			break;

		case 6366:
			//npc.actions = new String[] { "Pick Up", null, null, null, null };
			npc.models = new int[1];
			npc.models[0] = 95097;
			npc.name = "Pet Pikachu";
			npc.description = "It's a Pikachu.";
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			npc.squaresNeeded = 1;
			npc.sizeXZ = 50;
			npc.sizeY = 75;
			break;
			
			
		/*
		 * Start of GWD 2 NPCS
		 */
		case 6307:
			npc.name = "Iktomi The Trickster";
			npc.description = "One of the guiders of the mighty God Zamorak!";
			npc.sizeXZ = 800;
			npc.sizeY = 800;
			npc.walkAnim = 5325;
			npc.standAnim = 5326;
			npc.combatLevel = 420;
			npc.squaresNeeded = 3;
			npc.models = new int[] { 20309, 20312 };
			npc.actions = new String[5];
			npc.actions = new String[] {null, "Attack",  null, null, null};
			npc.originalColours = new int[6];
			npc.originalColours[0] = 960;
			npc.originalColours[1] = 33728;
			npc.originalColours[2] = 22443;
			npc.originalColours[3] = 11200;
			npc.originalColours[4] = 56256;
			npc.originalColours[5] = 50099;
			npc.destColours = new int[6];
			npc.destColours[0] = 6; // 2ndary spider top arms
			npc.destColours[1] = 665; // spider top arms
			npc.destColours[2] = 660; // spuder under legs
			npc.destColours[3] = 22425; // back of spiders teeth
			npc.destColours[4] = 6; // spiders trim
			npc.destColours[5] = 22425; //spiders joints (cant really notice)
			break;
		case 6308:
			npc.name = "Pet Iktomi The Trickster";
			npc.description = "One of the guiders of the mighty God Zamorak!";
			npc.walkAnim = 5325;
			npc.standAnim = 5326;
			npc.combatLevel = 420;
			npc.squaresNeeded = 3;
			npc.models = new int[] { 20309, 20312 };
			npc.actions = new String[5];
			//npc.actions[0] = "pick-up";
			npc.originalColours = new int[6];
			npc.originalColours[0] = 960;
			npc.originalColours[1] = 33728;
			npc.originalColours[2] = 22443;
			npc.originalColours[3] = 11200;
			npc.originalColours[4] = 56256;
			npc.originalColours[5] = 50099;
			npc.destColours = new int[6];
			npc.destColours[0] = 6; // 2ndary spider top arms
			npc.destColours[1] = 665; // spider top arms
			npc.destColours[2] = 660; // spider under legs
			npc.destColours[3] = 22425; // back of spiders teeth
			npc.destColours[4] = 6; // spiders trim
			npc.destColours[5] = 22425; //spiders joints (cant really notice)
			break;
		case 6309://CANT BE THIS, this is client sided but error is server sided
			npc.name = "Le'fosh The Brutal";
			npc.description = "Iktomi's brethren!";
			npc.sizeXZ = 350;
			npc.sizeY = 350;
			npc.walkAnim = 5325;
			npc.standAnim = 5326;
			npc.combatLevel = 164;
			npc.squaresNeeded = 3;
			npc.models = new int[] { 20309, 20312 };
			npc.actions = new String[5];
			npc.actions = new String[] {null, "Attack",  null, null, null};
			npc.originalColours = new int[6];
			npc.originalColours[0] = 960;
			npc.originalColours[1] = 33728;
			npc.originalColours[2] = 22443;
			npc.originalColours[3] = 11200;
			npc.originalColours[4] = 56256;
			npc.originalColours[5] = 50099;
			npc.destColours = new int[6];
			npc.destColours[0] = 6; // 2ndary spider top arms
			npc.destColours[1] = 54169; // spider top arms
			npc.destColours[2] = 54164; // spuder under legs
			npc.destColours[3] = 22425; // back of spiders teeth
			npc.destColours[4] = 6; // spiders trim
			npc.destColours[5] = 22425; //spiders joints (cant really notice)
			break;
		case 6310:
			npc.name = "Pet Le'fosh The Brutal";
			npc.description = "Iktomi's brethren!";
			npc.walkAnim = 5325;
			npc.standAnim = 5326;
			npc.combatLevel = 164;
			npc.squaresNeeded = 3;
			npc.models = new int[] { 20309, 20312 };
			npc.actions = new String[5];
			//npc.actions[0] = "pick-up";
			npc.originalColours = new int[6];
			npc.originalColours[0] = 960;
			npc.originalColours[1] = 33728;
			npc.originalColours[2] = 22443;
			npc.originalColours[3] = 11200;
			npc.originalColours[4] = 56256;
			npc.originalColours[5] = 50099;
			npc.destColours = new int[6];
			npc.destColours[0] = 6; // 2ndary spider top arms
			npc.destColours[1] = 54169; // spider top arms
			npc.destColours[2] = 54164; // spuder under legs
			npc.destColours[3] = 22425; // back of spiders teeth
			npc.destColours[4] = 6; // spiders trim
			npc.destColours[5] = 22425; //spiders joints (cant really notice)
			break;
		case 6311:
			npc.name = "Zamorakian warbeast";
			npc.description = "A warbeast of Zamorak!";
			npc.combatLevel = 173;
			npc.walkAnim = 1197;
			npc.standAnim = 1198;
			npc.models = new int[] { 3879 };
			npc.actions = new String[5];
			npc.actions = new String[] {null, "Attack",  null, null, null};
			npc.originalColours = new int[8];
			npc.originalColours[0] = 9129; //body
			npc.originalColours[1] = 10155; //hair
			npc.originalColours[2] = 127; //claws
			npc.originalColours[3] = 10126; //end of tail
			npc.originalColours[4] = 11177; //nothing
			npc.originalColours[5] = 4818; //ears
			npc.originalColours[6] = 12; //nose
			npc.originalColours[7] = 0; //eyes
			npc.destColours = new int[8];
			npc.destColours[0] = 660;
			npc.destColours[1] = 3;
			npc.destColours[2] = 3;
			npc.destColours[3] = 3;
			npc.destColours[4] = 3;
			npc.destColours[5] = 3;
			npc.destColours[6] = 995;
			npc.destColours[7] = 995;
			break;
		case 6312:
			npc.name = "Pet Zamorakian warbeast";
			npc.description = "A warbeast of Zamorak!";
			npc.sizeXZ = 70;
			npc.sizeY = 70;
			npc.combatLevel = 173;
			npc.walkAnim = 1197;
			npc.standAnim = 1198;
			npc.models = new int[] { 3879 };
			npc.actions = new String[5];
			//npc.actions[0] = "pick-up";
			npc.originalColours = new int[8];
			npc.originalColours[0] = 9129; //body
			npc.originalColours[1] = 10155; //hair
			npc.originalColours[2] = 127; //claws
			npc.originalColours[3] = 10126; //end of tail
			npc.originalColours[4] = 11177; //nothing
			npc.originalColours[5] = 4818; //ears
			npc.originalColours[6] = 12; //nose
			npc.originalColours[7] = 0; //eyes
			npc.destColours = new int[8];
			npc.destColours[0] = 660;
			npc.destColours[1] = 3;
			npc.destColours[2] = 3;
			npc.destColours[3] = 3;
			npc.destColours[4] = 3;
			npc.destColours[5] = 3;
			npc.destColours[6] = 995;
			npc.destColours[7] = 995;
			break;
		
		case 52:
				npc.name = "Livyathann";
				npc.colourRedefine2 = 100;
				npc.elementalIcon = 2;
				break;  
		case 3316:
			npc.name = "Chainsaw Levi";
			npc.sizeXZ = 350;
			npc.sizeY = 350;
			npc.walkAnim = 5325;
			npc.standAnim = 5326;
			npc.combatLevel = 164;
			npc.squaresNeeded = 3;
			npc.models = new int[] { 20309, 20312 };
			npc.actions = new String[5];
			npc.actions = new String[] {null, "Attack",  null, null, null};
			npc.originalColours = new int[6];
			npc.originalColours[0] = 960;
			npc.originalColours[1] = 33728;
			npc.originalColours[2] = 22443;
			npc.originalColours[3] = 11200;
			npc.originalColours[4] = 56256;
			npc.originalColours[5] = 50099;
			npc.destColours = new int[6];
			npc.destColours[0] = 6; // 2ndary spider top arms
			npc.destColours[1] = 54169; // spider top arms
			npc.destColours[2] = 54164; // spuder under legs
			npc.destColours[3] = 22425; // back of spiders teeth
			npc.destColours[4] = 6; // spiders trim
			npc.destColours[5] = 22425; //spiders joints (cant really notice)
			break;
		case 6313:
			npc.name = "Abyzou Heartwrencher";
			npc.sizeXZ = 160;
			npc.sizeY = 160;
			npc.combatLevel = 718;
			npc.walkAnim = 63;
			npc.standAnim = 66;
			npc.models = new int[] { 17375, 17391, 17384, 17399 };
			npc.actions = new String[5];
			npc.actions = new String[] {null, "Attack",  null, null, null};
			npc.originalColours = new int[9];
			npc.originalColours[0] = 5219; //teeth
			npc.originalColours[1] = 910; //inside of mouth. Ankles. End of tail
			npc.originalColours[2] = 1814; //around face. Start of tail
			npc.originalColours[3] = 1938; //around face and back of head. Top part of legs and end bit of tail.
			npc.originalColours[4] = 1690; //top of forehead
			npc.originalColours[5] = 921; //eyebrows
			npc.originalColours[6] = 0; //horns. Feet
			npc.originalColours[7] = 962; //eye colour
			npc.originalColours[8] = 912; // knees, dick, end of tail
			npc.destColours = new int[9];
			npc.destColours[0] = 5584;
			npc.destColours[1] = 5584;
			npc.destColours[2] = 43;
			npc.destColours[3] = 6550;
			npc.destColours[4] = 6550;
			npc.destColours[5] = 43;
			npc.destColours[6] = 5584;
			npc.destColours[7] = 9152;
			npc.destColours[8] = 42;
			//npc.anInt55:-1, npc.anInt57:-1, npc.anInt58:-1,  npc.anInt59:-1, npc.anInt75:-1, entity.anInt79:16, npc.anInt83:-1, npc.anInt85:0, npc.anInt86:128, npc.anInt91:128, entity.anInt92:0, entity.anInt56:13 
			break;
		case 6314:
			npc.name = "Pet Abyzou Heartwrencher";
			npc.combatLevel = 718;
			npc.walkAnim = 63;
			npc.standAnim = 66;
			npc.models = new int[] { 17375, 17391, 17384, 17399 };
			npc.actions = new String[5];
			//npc.actions[0] = "pick-up";
			npc.originalColours = new int[9];
			npc.originalColours[0] = 5219; //teeth
			npc.originalColours[1] = 910; //inside of mouth. Ankles. End of tail
			npc.originalColours[2] = 1814; //around face. Start of tail
			npc.originalColours[3] = 1938; //around face and back of head. Top part of legs and end bit of tail.
			npc.originalColours[4] = 1690; //top of forehead
			npc.originalColours[5] = 921; //eyebrows
			npc.originalColours[6] = 0; //horns. Feet
			npc.originalColours[7] = 962; //eye colour
			npc.originalColours[8] = 912; // knees, dick, end of tail
			npc.destColours = new int[9];
			npc.destColours[0] = 5584;
			npc.destColours[1] = 5584;
			npc.destColours[2] = 43;
			npc.destColours[3] = 6550;
			npc.destColours[4] = 6550;
			npc.destColours[5] = 43;
			npc.destColours[6] = 5584;
			npc.destColours[7] = 9152;
			npc.destColours[8] = 42;
			//npc.anInt55:-1, npc.anInt57:-1, npc.anInt58:-1,  npc.anInt59:-1, npc.anInt75:-1, entity.anInt79:16, npc.anInt83:-1, npc.anInt85:0, npc.anInt86:128, npc.anInt91:128, entity.anInt92:0, entity.anInt56:13 
			break;
			/*
			 * Rick & Morty
			 */
			
		case 6351:
			npc.name = "Rick";
			npc.description = "Rise above, focus on science.";
			npc.combatLevel = 256;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.models = new int[1];
			npc.models[0] = 95023; //HEAD
			npc.actions = new String[5];
			npc.actions[0] = "Attack";
			break;
		case 6353:
			npc.name = "Pet Morty";
			npc.combatLevel = 0;
			npc.description = "Get your shit together.";
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.models = new int[1];
			npc.models[0] = 95024; //HEAD
			npc.actions = new String[5];
			//npc.actions[0] = "Pickup";
			break;
		case 6354:
			npc.name = "Pet Chilli";
			npc.description = "Wow, you look hot";
			npc.combatLevel = 0;
			npc.models = new int[1];
			npc.models[0] = 95025;
			npc.actions = new String[5];
		//	//npc.actions[0] = "pick-up";
			npc.squaresNeeded = 2;
			npc.standAnim = 808;
			npc.walkAnim = 819;		
			break;
		case 6355:
			npc.name = "Pet Mayonaise";
			npc.description = "Mmmmmm, yummy";
			npc.combatLevel = 0;
			npc.models = new int[1];
			npc.models[0] = 95026;
			npc.actions = new String[5];
			////npc.actions[0] = "pick-up";
			npc.squaresNeeded = 2;
			npc.standAnim = 808;
			npc.walkAnim = 819;		
			break;
			/*
			 * End Of Rick & Morty
			 */
		case 6356:
			npc.name = "Pet Striped Leopard";
			npc.walkAnim = 5226;
			npc.standAnim = 5225;
			npc.combatLevel = 0;
			npc.models = new int[1];
			npc.models[0] = 95044;
			npc.actions = new String[5];
			////npc.actions[0] = "pick-up";
			//npc.anInt55:-1, npc.anInt57:-1, npc.anInt58:-1,  npc.anInt59:-1, npc.anInt75:-1, entity.anInt79:32, npc.anInt83:-1, npc.anInt85:0, npc.anInt86:128, npc.anInt91:128, entity.anInt92:0, entity.anInt56:4 
			break;
		case 3334:
			npc.name = "WildyWyrm";
			npc.models = new int[] { 63604 };
			//npc.boundDim = 1;
			npc.standAnim = 12790;
			npc.walkAnim = 12790;
			npc.combatLevel = 382;
			npc.actions = new String[5];
			npc.actions = new String[] {null, "Attack",  null, null, null};
			npc.sizeXZ = 225;
			npc.sizeY = 200;
			//npc.sizeXZ = 35;
			//npc.sizeY = 75;
		break;
			case 3336:
				npc.name = "Hydra";
				npc.models = new int[] { 63606 };
				//npc.boundDim = 1;
				npc.standAnim = 5070;
				npc.walkAnim = 5070;
				npc.combatLevel = 382;
				npc.actions = new String[5];
				npc.actions = new String[] {null, "Attack",  null, null, null};
				npc.sizeXZ = 350;
				npc.sizeY = 350;
				//npc.sizeXZ = 35;
				//npc.sizeY = 75;
				break;
		case 3335:
			npc.name = "Pet WildyWyrm";
			npc.models = new int[] { 63604 };
			//npc.boundDim = 1;
			npc.standAnim = 12790;
			npc.walkAnim = 12790;
			npc.combatLevel = 382;
			npc.actions = new String[5];
			//npc.actions[1] = "Pickup";
			npc.sizeXZ = 70;
			npc.sizeY = 70;
			//npc.sizeXZ = 35;
			//npc.sizeY = 75;
		break;
	/*	case 152:
			npc.name = "Custom Npc Boss";
			npc.models = new int[] { 75500 }; 
			//npc.boundDim = 1;
			NPCDef cnpc = forID(2633);
			npc.standAnim = cnpc.standAnim;
			npc.walkAnim = cnpc.walkAnim;
			npc.combatLevel = 885;
			npc.actions = new String[5];
			npc.actions = new String[] {null, "Attack",  null, null, null};
			npc.sizeXZ = 125;
			npc.sizeY = 100;
			//npc.sizeXZ = 35;
			//npc.sizeY = 75;
		break;*/
		case 1:
			npc.name = "Poison";
			npc.actions = new String[] {null, null, null, null, null};
			npc.sizeXZ = 1;
			npc.sizeY = 1;
			npc.drawMinimapDot = false;
			break;

		case 2002:
			npc.models = new int[2];
			npc.models[0] = 28294;
			npc.models[1] = 28295;
			npc.name = "Venenatis";
			npc.actions = new String[] {null, "Attack", null, null, null};
			npc.sizeXZ = 100;
			npc.sizeY = 100;
			NPCDef ven1 = forID(60);
			npc.standAnim = ven1.standAnim;
			npc.walkAnim = ven1.walkAnim;
			npc.combatLevel = 464;
			npc.squaresNeeded = 3;
			break;
		case 3192:
			npc.name = "ImaginePS Shop";
			npc.combatLevel = 999;
			npc.actions = new String[] {"Trade", null, null, null, null};
			break;
		case 457:
			npc.name = "Instance manager";
			npc.description = "A being from the underworld";
			npc.combatLevel = 3982;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.sizeXZ = 180;
			npc.sizeY = 180;
			npc.models = new int[6];
			npc.models[0] = 91321; //HEAD
			npc.models[1] = 91316; //CHEST
			npc.models[2] = 91274; //CAPE
			npc.models[3] = 91319; //HAND
			npc.models[4] = 91322; //LEG
			npc.models[5] = 91317; //BOOT
			npc.actions = new String[5];
			npc.actions[1] = "Talk to";
			npc.actions[2] = null;
			npc.actions[3] = null;
			npc.actions[4] = null;
			break;
		case 6306:
			npc.name = "Instance manager";
			npc.description = "A being from the underworld";
			npc.combatLevel = 3982;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.sizeXZ = 180;
			npc.sizeY = 180;
			npc.models = new int[6];
			npc.models[0] = 91321; //HEAD
			npc.models[1] = 91316; //CHEST
			npc.models[2] = 91274; //CAPE
			npc.models[3] = 91319; //HAND
			npc.models[4] = 91322; //LEG
			npc.models[5] = 91317; //BOOT
			npc.actions = new String[5];
			npc.actions[1] = "Talk to";
			npc.actions[2] = null;
			npc.actions[3] = null;
			npc.actions[4] = null;
			break;
		case 2436:
			npc.name = "ImaginePS Warriors";
			npc.walkAnim = 1660;
			npc.standAnim = 11973;
			npc.models = new int[9];
			npc.models[0] = 230; //HEAD
			npc.models[1] = 246; //JAW
			npc.models[2] = 49352; //CHEST
			npc.models[3] = 10313; //CAPE
			npc.models[4] = 49347; //ARM
			npc.models[5] = 49349; //HAND
			npc.models[6] = 5409; //WEP
			npc.models[7] = 49351; //LEG
			npc.models[8] = 49348; //BOOT
			npc.actions = new String[5];
			npc.actions[0] = "Examine";
			npc.actions[1] = "Attack";
			npc.actions[2] = null;
			npc.actions[3] = null;
			npc.actions[4] = null;

			break;
		case 2437:
			npc.name = "Donut Homer";
			npc.walkAnim = 1660;
			npc.models = new int[]{96345};
			npc.standAnim = 11973;
			npc.actions = new String[5];
			npc.actions[0] = "Examine";
			npc.actions[1] = "Attack";
			npc.actions[2] = null;
			npc.actions[3] = null;
			npc.actions[4] = null;
			break;
			case 2439:
			npc.name = "Artillery Boss";
			npc.walkAnim = 1660;
			npc.standAnim = 11973;
			npc.models = new int[8];
			npc.models[0] = 120002; //HEAD
			npc.models[1] = 120003; //HEAD
			npc.models[2] = 120004; //HEAD
			npc.models[3] = 94502; //HEAD
			npc.models[4] = 49349; //HAND
			npc.models[5] = 49348; //BOOT
			npc.models[6] = 94412; //BOOT
			npc.models[7] = 94413; //BOOT
			npc.actions = new String[5];
			npc.actions[0] = "Examine";
			npc.actions[1] = "Attack";
			npc.actions[2] = null;
			npc.actions[3] = null;
			npc.actions[4] = null;
			npc.originalColours = new int[]{40,60,51,52,53,54,55,56,57,58,59};
			npc.destColours = new int[] {71,71,71,71,71,71,71,71,71,71,71};
			break;
		case 9911:
			npc.standAnim = 14382;
			break;
		case 9910:
			npc.name = "Pet Har'Lakk the Riftsplitter";
			npc.actions = new String[5];
			////npc.actions[0] = "pick-up";
			npc.standAnim = 14382;
			npc.sizeXZ = 60;
			npc.sizeY = 60;
			break;
		case 10141:
			npc.name = "Magma Satanic Devil";
			npc.standAnim = 14382;
			npc.colourRedefine = 10;
			npc.colourRedefine2 = 1;
            npc.sizeXZ = 100;
            npc.sizeY = 100;
			break;
		case 10140:
			npc.name = "Pet Bal'lak The Pummeler";
			npc.actions = new String[5];
            npc.actions = new String[] {null, "@or2@Attack", null, null, null};
            npc.combatLevel = 1420;
            npc.squaresNeeded = 1;
            npc.combatLevel = 653;
            npc.walkAnim = 11975;
			npc.standAnim = 14382;
            npc.walkAnim = 11975;
            npc.sizeXZ = 230;
            npc.sizeY = 230;
			break;
		case 10039:
			npc.standAnim = 14382;
			break;
		case 10038:
			npc.name = "To'Kash the Bloodchiller";
			npc.actions = new String[5];
			////npc.actions[0] = "pick-up";
			npc.standAnim = 14382;
			npc.sizeXZ = 60;
			npc.sizeY = 60;
			break;
		case 6301:
			npc.name = "Pet Nutella";
			npc.description = "Mmmmmm chocolate :D";
			npc.models = new int[1];
			npc.models[0] = 91723;
			npc.actions = new String[5];
			////npc.actions[0] = "pick-up";
			npc.squaresNeeded = 2;
			npc.standAnim = 808;
			npc.walkAnim = 819;		
			break;
		case 2042://turgoise
			
			npc.name = "Zulrah";
			npc.actions = new String[] {null, "Attack", null, null, null};
			npc.models = new int[1];
			npc.models[0] = 14407;
			npc.standAnim = 5070;
			npc.walkAnim = 5070;
			npc.combatLevel = 725;
			npc.sizeXZ = 100;
			npc.sizeY = 100;
			break;
		case 2043://regular
			npc.name = "Zulrah";
			npc.actions = new String[] {null, "Attack", null, null, null};
			npc.models = new int[1];
			npc.models[0] = 14408;
			npc.standAnim = 5070;
			npc.walkAnim = 5070;
			npc.combatLevel = 725;
			npc.sizeXZ = 100;
			npc.sizeY = 100;
			break;
		case 2044://melee
			npc.name = "Zulrah";
			npc.actions = new String[] {null, "Attack", null, null, null};
			npc.models = new int[1];
			npc.models[0] = 14409;
			npc.standAnim = 5070;
			npc.walkAnim = 5070;
			npc.combatLevel = 725;
			npc.sizeXZ = 100;
			npc.sizeY = 100;
			break;
		case 2001:
			npc.models = new int[1];
			npc.models[0] = 28293;
			npc.name = "Scorpia";
			npc.actions = new String[] {null, "Attack", null, null, null};
			NPCDef scor = forID(107);
			npc.standAnim = scor.standAnim;
			npc.walkAnim = scor.walkAnim;
			npc.combatLevel = 464;
			npc.squaresNeeded = 3;
			break;
		case 8754:
            npc.name = "@or2@Blest God";
    		npc.combatLevel = 100;
            npc.squaresNeeded = 1;
            npc.walkAnim = 11975;
            npc.standAnim = 11973;
        	npc.models = new int[7];
			npc.models[0] = 99021; //HEAD
			npc.models[1] = 99022; //CHEST
			npc.models[2] = 99016; //CAPE
			npc.models[3] = 99017; //HAND
			npc.models[4] = 99018; //LEG
			npc.models[5] = 99019; //WEAPON
			npc.models[6] = 99020; //WEAPON
			npc.actions = new String[5];
			npc.actions = new String[] {null, "Attack", null, null, null};
		    npc.sizeXZ = 180;
            npc.sizeY = 180;
			break;
		case 6766:
			npc.name = "Doomsday";
			npc.description = "It's Doomsday.";
			npc.combatLevel = 150;
			npc.walkAnim = 11975;
			npc.standAnim = 11973;
			npc.actions = new String[5];
			npc.actions[1] = "Attack";
			npc.models = new int[1];
			npc.models[0] = 4039;
			npc.sizeXZ = 150;
			npc.sizeY = 150;
			npc.squaresNeeded = 3;
			break;
		case 5886:
			npc.name = "Abyssal Sire";
			npc.description = "It's an abyssal sire.";
			npc.combatLevel = 350;
			npc.walkAnim = 4534;
			npc.standAnim = 4533;
			npc.actions = new String[5];
			npc.actions[1] = "Attack";
			npc.models = new int[1];
			npc.models[0] = 29477;
			npc.sizeXZ = 108;
			npc.sizeY = 108;
			npc.squaresNeeded = 6;
			break;
		case 499:
			npc.name = "Thermonuclear smoke devil";
			npc.description = "It looks like its glowing";
			npc.combatLevel = 301;
			npc.walkAnim = 1828;
			npc.standAnim = 1829;
			npc.actions = new String[5];
			npc.actions[1] = "Attack";
			npc.models = new int[1];
			npc.models[0] = 28442;
			npc.sizeXZ = 240;
			npc.sizeY = 240;
			npc.squaresNeeded = 4;
			break;
		case 1999:
			npc.models = new int[2];
			npc.name = "Assassin";
			npc.models[1] = 96509;
			npc.combatLevel = 100;
            npc.walkAnim = 11975;
            npc.standAnim = 11973;
			npc.actions = new String[5];
			npc.actions = new String[] {null, "Attack", null, null, null};
			npc.sizeXZ = 200;
			npc.sizeY = 200;
		 break;
		case 2006:
			npc.models = new int[1];
			npc.models[0] = 28300;
			npc.name = "Vet'ion";
			npc.actions = new String[] {null, "Attack", null, null, null};
			NPCDef vet = forID(90);
			npc.standAnim = vet.standAnim;
			npc.walkAnim = vet.walkAnim;
			npc.combatLevel = 464;
			break;
		case 2003:
			npc.models = new int[1];
			npc.models[0] = 28281;
			npc.name = "Kraken";
            npc.actions = new String[] {null, "Attack", null, null, null};
            NPCDef eld = forID(3847);
            npc.models = new int[1];
            npc.models[0] = 28233;
            npc.combatLevel = 291;
            npc.standAnim = 3989;
            npc.walkAnim = eld.walkAnim;
            npc.sizeXZ = npc.sizeY = 84;
            break;
		case 2004:
			npc.models = new int[1];
			npc.models[0] = 28231;
			npc.name = "Cave kraken";
			npc.actions = new String[] {null, "Attack", null, null, null};
			NPCDef cave = forID(3847);
			npc.models = new int[1];
			npc.models[0] = 28233;
			npc.combatLevel = 127;
			npc.standAnim = 3989;
			npc.walkAnim = cave.walkAnim;
			npc.sizeXZ = npc.sizeY = 42;
			break;
		case 273:
			npc.name = "Boss Point Shop";
			npc.models[0] = 94394;
			npc.actions = new String[] {"Trade", null, null, null, null};
			break;
		case 865:
			npc.name = "Halloween Point Shop";
			npc.models[0] = 84175;
			npc.standAnim = 808;
			npc.walkAnim = 819;
		    npc.sizeXZ = 180;
            npc.sizeY = 180;
			npc.actions = new String[] {"Trade", null, null, null, null};
			break;
		case 263:
			npc.name = "Extreme Donator Shop";
			npc.actions = new String[] {"Trade", null, null, null, null};
			break;
		case 543:
			npc.name = "Decanter";
			break;
		case 5417:
			npc.combatLevel = 210;
			break;
		case 5418:
			npc.combatLevel = 90;
			break;
		case 6715:
			npc.combatLevel = 91;
			break;
		case 6716:
			npc.combatLevel = 128;
			break;
		case 6701:
			npc.combatLevel = 173;
			break;
		case 6725:
			npc.combatLevel = 224;
			break;
		case 6691:
			npc.squaresNeeded = 2;
			npc.combatLevel = 301;
			break;
		case 212:
			npc.name = "Donator Shop";
	 	 	npc.models = new int[]{90400};
			npc.actions = new String[] {"View Shop 1", null, "View Shop 2", null, null};
			break;
		case 741:
			npc.name = "Donator Shop 2";
			break;
		case 2998:
			npc.name = "Gambler";
			npc.actions = new String[] {"Trade", null , null, null, null};
			break;
		case 5588:
		    	npc.name = "Robot";
		   	 	npc.models = new int[]{90586};
		    	npc.actions = new String[5];
		    	npc.actions[1] = "Attack";
		    	npc.combatLevel = 256;
		            break;
		case 2633:
			npc.name = "Trivia Point Shop";
			break;
		case 602:
			npc.name = "Loyalty Points Shop";
			break;
		case 633:
			npc.name = "King Dragon Point Shop";
			npc.models[0] = 94394;
			break;
		case 8710:
		case 8707:
		case 8706:
		case 8705:
			npc.name = "Musician";
			npc.actions = new String[]{"Listen-to", null, null, null, null};
			break;
	
		case 947:
			npc.name = "Player Owned Shop Manager";
			npc.actions = new String[] {"Talk-to", null, "View Shops", "My Shop", "Claim Earnings"};
			break;
		case 9939:
			npc.combatLevel = 607;
			break;
		case 688:
			npc.name = "Archer";
			break;
		case 4540:
			npc.elementalIcon = 2;
			npc.combatLevel = 299;
			break;
		case 3101:
			npc.sizeY = npc.sizeXZ = 80;
			npc.squaresNeeded = 1;
			npc.actions = new String[]{"Talk-to", null, "Start", "Rewards", null};
			break;
		case 6222:
			npc.name = "Kree'arra";
			npc.squaresNeeded = 5;
			npc.standAnim = 6972;
			npc.walkAnim = 6973;
			npc.actions = new String[]{null, "Attack", null, null, null};
			npc.sizeY = npc.sizeXZ = 110;
			break;
		case 6203:
			npc.models = new int[] {27768, 27773, 27764, 27765, 27770};
			npc.name = "K'ril Tsutsaroth";
			npc.squaresNeeded = 5;
			npc.standAnim = 6943;
			npc.walkAnim = 6942;
			npc.actions = new String[]{null, "Attack", null, null, null};
			npc.sizeY = npc.sizeXZ = 110;
			break;
		case 1610:
		case 491:
		case 10216:
			npc.actions = new String[]{null, "Attack", null, null, null};
			break;
		case 7969:
			npc.actions = new String[]{"Talk-to", null, "Trade", null, null};
			break;
		case 1382:
			npc.name = "Glacor";
			npc.models = new int[]{58940};
			npc.squaresNeeded = 3;
			//	npc.anInt86 = 475;
			npc.sizeXZ = npc.sizeY = 180;
			npc.standAnim = 10869;
			npc.walkAnim = 10867;
			npc.actions = new String[]{null, "Attack", null, null, null};
			npc.combatLevel = 123;
			npc.drawMinimapDot = true;
			npc.combatLevel = 188;
			break;
		case 6305:
			npc.name = "@red@Demonic Mystery (Vote BOSS)@bla@";
			npc.actions = new String[]{null, "Attack", null, null, null};
			npc.combatLevel = 456;
			npc.squaresNeeded = 1;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.sizeXZ = 100;
			npc.sizeY = 100;
			npc.models = new int[] {41399};
			break;
		
		case 6502:
			npc.name = "Pet Rock Crab";
			npc.walkAnim = 1311;
			npc.standAnim = 1310;
			npc.models = new int[2];
			npc.models[0] = 4399;
			npc.models[1] = 4400;
			npc.actions = new String[5];
			////npc.actions[0] = "pick-up";
		break;
	
		case 7000:
			npc.models = new int[]{57958, 57960};
			npc.name = "Adamant dragon";
			npc.description = "It's a Adamant dragon.";
			npc.squaresNeeded = 4;
			npc.standAnim = 14251;
			npc.walkAnim = 14250;
			npc.runAnim = 14250;
			npc.actions = new String[]{null, "Attack", null, null, null};
			npc.originalColours = new int[65];
			npc.originalColours[0] = 404; //wings colour
			npc.originalColours[1] = 396; //wings colour
			npc.originalColours[2] = 9234; //inner claws
			npc.originalColours[3] = 9238; //outer claws
			npc.originalColours[4] = 50; // parts of the body
			npc.originalColours[5] = 41; // parts of body and wings
			npc.originalColours[6] = 43; // parts of body and wings
			npc.originalColours[7] = 39; // bit of feet and leg
			npc.originalColours[8] = 55; // bit of back 2 feet
			npc.originalColours[9] = 46; //tiny bit of back feet and back
			npc.originalColours[10] = 37; //inner back feet
			npc.originalColours[11] = 5289; //nothing
			npc.originalColours[12] = 5285; //tiny square under knees
			npc.originalColours[13] = 3115; // underneath knees
			npc.originalColours[14] = 3113; //trim over body
			npc.originalColours[15] = 3117; //2nd trim over body
			npc.originalColours[16] = 3133; //light part of body
			npc.originalColours[17] = 3109; //tiny bit of knee
			npc.originalColours[18] = 3111; //tiny bit of leg
			npc.originalColours[19] = 3127; //trim of tail and underbody
			npc.originalColours[20] = 3118; //parts of legs
			npc.originalColours[21] = 3121; //round face under legs etc
			npc.originalColours[22] = 3128; //round body
			npc.originalColours[23] = 3114; //back of leg tiny
			npc.originalColours[24] = 3120; //muscles
			npc.originalColours[25] = 48; //2 lines on top of back
			npc.originalColours[26] = 3134; //spikes
			npc.originalColours[27] = 3138; //spikes
			npc.originalColours[28] = 52; //wings and middle of back
			npc.originalColours[29] = 49; //wings
			npc.originalColours[30] = 3116; //trim
			npc.originalColours[31] = 3119; //trim
			npc.originalColours[32] = 3139; //trim
			npc.originalColours[33] = 3144; //trim
			npc.originalColours[34] = 3145; //trim
			npc.originalColours[35] = 44; //trim
			npc.originalColours[36] = 68; //2 lines on under belly
			npc.originalColours[37] = 3112; //little bit of under belly
			npc.originalColours[38] = 64; //whole under belly
			npc.originalColours[39] = 47; //bit near ass
			npc.originalColours[40] = 4129; //points on wings
			npc.originalColours[41] = 4137; //points on wings
			npc.originalColours[42] = 4145; //points on wings
			npc.originalColours[43] = 35903; //top teeth
			npc.originalColours[44] = 3124; //nose
			npc.originalColours[45] = 35898; //teeth
			npc.originalColours[46] = 35906; //teeth
			npc.originalColours[47] = 35902; //teeth
			npc.originalColours[48] = 35890; //teeth
			npc.originalColours[49] = 2096; //mouth
			npc.originalColours[50] = 3129; //face
			npc.originalColours[51] = 2094; //tounge
			npc.originalColours[52] = 2092; //tounge
			npc.originalColours[53] = 2090; //tounge
			npc.originalColours[54] = 3125; //face
			npc.originalColours[55] = 3123; //face
			npc.originalColours[56] = 2086; //back of mouth
			npc.originalColours[57] = 3130; //face
			npc.originalColours[58] = 3126; //face
			npc.originalColours[59] = 943; //eyes
			npc.originalColours[60] = 931; //eyes
			npc.originalColours[61] = 3137; //face
			npc.originalColours[62] = 3132; //face
			npc.originalColours[63] = 3131; //face
			npc.originalColours[64] = 24805; //idk
			npc.destColours = new int[65];
			npc.destColours[0] = 404; //wings colour
			npc.destColours[1] = 396; //wings colour
			npc.destColours[2] = 9234; //inner claws
			npc.destColours[3] = 9238; //outer claws
			npc.destColours[4] = 21660; // parts of the body
			npc.destColours[5] = 21656; // parts of body and wings
			npc.destColours[6] = 21657; // parts of body and wings
			npc.destColours[7] = 21662; // bit of feet and leg
			npc.destColours[8] = 21659; // bit of back 2 feet
			npc.destColours[9] = 21658; //tiny bit of back feet and back
			npc.destColours[10] = 21650; //inner back feet
			npc.destColours[11] = 21659; //nothing
			npc.destColours[12] = 21659; //tiny square under knees
			npc.destColours[13] = 21652; // underneath knees
			npc.destColours[14] = 21654; //trim over body
			npc.destColours[15] = 21654; //2nd trim over body
			npc.destColours[16] = 21654; //light part of body
			npc.destColours[17] = 21659; //tiny bit of knee
			npc.destColours[18] = 21659; //tiny bit of leg
			npc.destColours[19] = 21664; //trim of tail and underbody
			npc.destColours[20] = 21662; //parts of legs
			npc.destColours[21] = 21659; //round face under legs etc
			npc.destColours[22] = 21659; //round body
			npc.destColours[23] = 21658; //back of leg tiny
			npc.destColours[24] = 21665; //muscles
			npc.destColours[25] = 21664; //2 lines on top of back
			npc.destColours[26] = 21659; //spikes
			npc.destColours[27] = 21659; //spikes
			npc.destColours[28] = 21656; //wings and middle of back
			npc.destColours[29] = 21658; //wings
			npc.destColours[30] = 21664; //trim
			npc.destColours[31] = 21662; //trim
			npc.destColours[32] = 21663; //trim
			npc.destColours[33] = 21659; //trim
			npc.destColours[34] = 21664; //trim
			npc.destColours[35] = 21666; //trim
			npc.destColours[36] = 21659; //2 lines on under belly
			npc.destColours[37] = 21659; //little bit of under belly
			npc.destColours[38] = 21659; //whole under belly
			npc.destColours[39] = 21660; //bit near ass
			npc.destColours[40] = 21659; //points on wings
			npc.destColours[41] = 21659; //points on wings
			npc.destColours[42] = 21659; //points on wings
			npc.destColours[43] = 7; //top teeth
			npc.destColours[44] = 21659; //nose
			npc.destColours[45] = 7; //teeth
			npc.destColours[46] = 7; //teeth
			npc.destColours[47] = 7; //teeth
			npc.destColours[48] = 7; //teeth
			npc.destColours[49] = 21659; //mouth
			npc.destColours[50] = 21664; //face
			npc.destColours[51] = 21659; //tounge
			npc.destColours[52] = 21659; //tounge
			npc.destColours[53] = 21659; //tounge
			npc.destColours[54] = 21666; //face
			npc.destColours[55] = 21661; //face
			npc.destColours[56] = 21659; //back of mouth
			npc.destColours[57] = 21666; //face
			npc.destColours[58] = 21655; //face
			npc.destColours[59] = 29439; //eyes
			npc.destColours[60] = 29439; //eyes
			npc.destColours[61] = 21653; //face
			npc.destColours[62] = 21652; //face
			npc.destColours[63] = 21656; //face
			npc.destColours[64] = 21659; //idk
			npc.npcHeadModels = null;
			npc.drawMinimapDot = true;
			npc.combatLevel = 313;
			npc.sizeXZ = 138;
			npc.sizeY = 138;
			npc.hasRenderPriority = false;
			npc.lightning = 25;
			npc.shadow = 375;
			npc.headIcon = -1;
			npc.degreesToTurn = 32;
			npc.varbitId = -1;
			npc.varSettingsId = -1;
			npc.childrenIDs = null;
			npc.clickable = true;
			break;
			case 1231:
				npc.name = "Dark Groudon";
				npc.models = new int[] {94388};
				npc.walkAnim = 11975;
				npc.standAnim = 11973;
				npc.sizeXZ *= 1.6;
				npc.sizeY *= 1.6;
				npc.squaresNeeded = 2;
				npc.originalColours = new int[]{40,1,24};
				npc.destColours = new int[]{55,57,57};
				break;
			case 1232:
				npc.name = "Light Groudon";
				npc.models = new int[] {94388};
				npc.walkAnim = 11975;
				npc.standAnim = 11973;
				npc.squaresNeeded = 3;
				npc.sizeXZ *= 1.6;
				npc.sizeY *= 1.6;
				npc.originalColours = new int[]{40,1,24};
				npc.destColours = new int[]{56,68,68};
				break;
			case 1233:
				npc.name = "Water Groudon";
				npc.models = new int[] {94388};
				npc.walkAnim = 11975;
				npc.standAnim = 11973;
				npc.squaresNeeded = 3;
				npc.sizeXZ *= 1.6;
				npc.sizeY *= 1.6;
				npc.originalColours = new int[]{40,1,24};
				npc.destColours = new int[]{70,57,57};
				break;
			case 7567:
	              npc.name = "@gre@Link";
	        		npc.combatLevel = 100;
	                npc.squaresNeeded = 1;
	                npc.walkAnim = 11975;
	                npc.standAnim = 11973;
	            	npc.models = new int[6];
	    			npc.models[0] = 94224; //HEAD
	    			npc.models[1] = 94226; //CHEST
	    			npc.models[2] = 94228; //CAPE
	    			npc.models[3] = 94222; //HAND
	    			npc.models[4] = 13719; //LEG
	    			npc.models[5] = 92038; //WEAPON
	    			npc.actions = new String[5];
	    			npc.actions = new String[] {null, "Attack", null, null, null};
	    		    npc.sizeXZ = 180;
	                npc.sizeY = 180;
	    			break;
			case 6583:
	            npc.name = "@whi@StormTrooper";
	      		npc.combatLevel = 100;
	            npc.squaresNeeded = 1;
	            npc.walkAnim = 11975;
	            npc.standAnim = 11973;
	    		npc.drawMinimapDot = true;
	        	npc.models = new int[5];
				npc.models[0] = 71010; //HEAD
				npc.models[1] = 71012; //CHEST
				npc.models[2] = 71014; //CAPE
				npc.models[3] = 71016; //HAND
				npc.models[4] = 71018; //LEG
				npc.actions = new String[5];
				npc.actions = new String[] {null, "Attack", null, null, null};
			    npc.sizeXZ = 150;
	            npc.sizeY = 150;
				break;
		case 7001:
			npc.models = new int[]{57958, 57960};
			npc.name = "Runite dragon";
			npc.description = "It's a Runite dragon.";
			npc.squaresNeeded = 4;
			npc.standAnim = 14251;
			npc.walkAnim = 14250;
			npc.runAnim = 14250;
			npc.actions = new String[]{null, "Attack", null, null, null};
			npc.originalColours = new int[65];
			npc.originalColours[0] = 404; //wings colour
			npc.originalColours[1] = 396; //wings colour
			npc.originalColours[2] = 9234; //inner claws
			npc.originalColours[3] = 9238; //outer claws
			npc.originalColours[4] = 50; // parts of the body
			npc.originalColours[5] = 41; // parts of body and wings
			npc.originalColours[6] = 43; // parts of body and wings
			npc.originalColours[7] = 39; // bit of feet and leg
			npc.originalColours[8] = 55; // bit of back 2 feet
			npc.originalColours[9] = 46; //tiny bit of back feet and back
			npc.originalColours[10] = 37; //inner back feet
			npc.originalColours[11] = 5289; //nothing
			npc.originalColours[12] = 5285; //tiny square under knees
			npc.originalColours[13] = 3115; // underneath knees
			npc.originalColours[14] = 3113; //trim over body
			npc.originalColours[15] = 3117; //2nd trim over body
			npc.originalColours[16] = 3133; //light part of body
			npc.originalColours[17] = 3109; //tiny bit of knee
			npc.originalColours[18] = 3111; //tiny bit of leg
			npc.originalColours[19] = 3127; //trim of tail and underbody
			npc.originalColours[20] = 3118; //parts of legs
			npc.originalColours[21] = 3121; //round face under legs etc
			npc.originalColours[22] = 3128; //round body
			npc.originalColours[23] = 3114; //back of leg tiny
			npc.originalColours[24] = 3120; //muscles
			npc.originalColours[25] = 48; //2 lines on top of back
			npc.originalColours[26] = 3134; //spikes
			npc.originalColours[27] = 3138; //spikes
			npc.originalColours[28] = 52; //wings and middle of back
			npc.originalColours[29] = 49; //wings
			npc.originalColours[30] = 3116; //trim
			npc.originalColours[31] = 3119; //trim
			npc.originalColours[32] = 3139; //trim
			npc.originalColours[33] = 3144; //trim
			npc.originalColours[34] = 3145; //trim
			npc.originalColours[35] = 44; //trim
			npc.originalColours[36] = 68; //2 lines on under belly
			npc.originalColours[37] = 3112; //little bit of under belly
			npc.originalColours[38] = 64; //whole under belly
			npc.originalColours[39] = 47; //bit near ass
			npc.originalColours[40] = 4129; //points on wings
			npc.originalColours[41] = 4137; //points on wings
			npc.originalColours[42] = 4145; //points on wings
			npc.originalColours[43] = 35903; //top teeth
			npc.originalColours[44] = 3124; //nose
			npc.originalColours[45] = 35898; //teeth
			npc.originalColours[46] = 35906; //teeth
			npc.originalColours[47] = 35902; //teeth
			npc.originalColours[48] = 35890; //teeth
			npc.originalColours[49] = 2096; //mouth
			npc.originalColours[50] = 3129; //face
			npc.originalColours[51] = 2094; //tounge
			npc.originalColours[52] = 2092; //tounge
			npc.originalColours[53] = 2090; //tounge
			npc.originalColours[54] = 3125; //face
			npc.originalColours[55] = 3123; //face
			npc.originalColours[56] = 2086; //back of mouth
			npc.originalColours[57] = 3130; //face
			npc.originalColours[58] = 3126; //face
			npc.originalColours[59] = 943; //eyes
			npc.originalColours[60] = 931; //eyes
			npc.originalColours[61] = 3137; //face
			npc.originalColours[62] = 3132; //face
			npc.originalColours[63] = 3131; //face
			npc.originalColours[64] = 24805; //idk
			npc.destColours = new int[65];
			npc.destColours[0] = 32916; //wings colour
			npc.destColours[1] = 32908; //wings colour
			npc.destColours[2] = 9234; //inner claws
			npc.destColours[3] = 9238; //outer claws
			npc.destColours[4] = 36129; // parts of the body
			npc.destColours[5] = 36125; // parts of body and wings
			npc.destColours[6] = 36127; // parts of body and wings
			npc.destColours[7] = 36132; // bit of feet and leg
			npc.destColours[8] = 36129; // bit of back 2 feet
			npc.destColours[9] = 36128; //tiny bit of back feet and back
			npc.destColours[10] = 36120; //inner back feet
			npc.destColours[11] = 36129; //nothing
			npc.destColours[12] = 36129; //tiny square under knees
			npc.destColours[13] = 36132; // underneath knees
			npc.destColours[14] = 36124; //trim over body
			npc.destColours[15] = 36124; //2nd trim over body
			npc.destColours[16] = 36125; //light part of body
			npc.destColours[17] = 36129; //tiny bit of knee
			npc.destColours[18] = 36129; //tiny bit of leg
			npc.destColours[19] = 36134; //trim of tail and underbody
			npc.destColours[20] = 36132; //parts of legs
			npc.destColours[21] = 36129; //round face under legs etc
			npc.destColours[22] = 36129; //round body
			npc.destColours[23] = 36128; //back of leg tiny
			npc.destColours[24] = 36135; //muscles
			npc.destColours[25] = 36134; //2 lines on top of back
			npc.destColours[26] = 36129; //spikes
			npc.destColours[27] = 36129; //spikes
			npc.destColours[28] = 36126; //wings and middle of back
			npc.destColours[29] = 36128; //wings
			npc.destColours[30] = 36134; //trim
			npc.destColours[31] = 36132; //trim
			npc.destColours[32] = 36133; //trim
			npc.destColours[33] = 36129; //trim
			npc.destColours[34] = 36134; //trim
			npc.destColours[35] = 36136; //trim
			npc.destColours[36] = 36129; //2 lines on under belly
			npc.destColours[37] = 36129; //little bit of under belly
			npc.destColours[38] = 36129; //whole under belly
			npc.destColours[39] = 36130; //bit near ass
			npc.destColours[40] = 36129; //points on wings
			npc.destColours[41] = 36129; //points on wings
			npc.destColours[42] = 36129; //points on wings
			npc.destColours[43] = 7; //top teeth
			npc.destColours[44] = 36129; //nose
			npc.destColours[45] = 7; //teeth
			npc.destColours[46] = 7; //teeth
			npc.destColours[47] = 7; //teeth
			npc.destColours[48] = 7; //teeth
			npc.destColours[49] = 36129; //mouth
			npc.destColours[50] = 36134; //face
			npc.destColours[51] = 36129; //tounge
			npc.destColours[52] = 36129; //tounge
			npc.destColours[53] = 36129; //tounge
			npc.destColours[54] = 36136; //face
			npc.destColours[55] = 36131; //face
			npc.destColours[56] = 36129; //back of mouth
			npc.destColours[57] = 36136; //face
			npc.destColours[58] = 36125; //face
			npc.destColours[59] = 6730; //eyes
			npc.destColours[60] = 6730; //eyes
			npc.destColours[61] = 36133; //face
			npc.destColours[62] = 36132; //face
			npc.destColours[63] = 36136; //face
			npc.destColours[64] = 36129; //idk
			npc.npcHeadModels = null;
			npc.drawMinimapDot = true;
			npc.combatLevel = 341;
			npc.sizeXZ = 165;
			npc.sizeY = 165;
			npc.hasRenderPriority = false;
			npc.lightning = 25;
			npc.shadow = 375;
			npc.headIcon = -1;
			npc.degreesToTurn = 32;
			npc.varbitId = -1;
			npc.varSettingsId = -1;
			npc.childrenIDs = null;
			npc.clickable = true;
			break;
			
		case 6500:
			npc.models = new int[]{57958, 57960};
			npc.name = "Pet Adamant dragon";
			npc.description = "It's a Adamant dragon.";
			npc.sizeY = npc.sizeXZ = 70;
			npc.squaresNeeded = 1;
			npc.standAnim = 14251;
			npc.walkAnim = 14250;
			npc.runAnim = 14250;
			npc.actions = new String[5];
			////npc.actions[0] = "pick-up";
			npc.originalColours = new int[65];
			npc.originalColours[0] = 404; //wings colour
			npc.originalColours[1] = 396; //wings colour
			npc.originalColours[2] = 9234; //inner claws
			npc.originalColours[3] = 9238; //outer claws
			npc.originalColours[4] = 50; // parts of the body
			npc.originalColours[5] = 41; // parts of body and wings
			npc.originalColours[6] = 43; // parts of body and wings
			npc.originalColours[7] = 39; // bit of feet and leg
			npc.originalColours[8] = 55; // bit of back 2 feet
			npc.originalColours[9] = 46; //tiny bit of back feet and back
			npc.originalColours[10] = 37; //inner back feet
			npc.originalColours[11] = 5289; //nothing
			npc.originalColours[12] = 5285; //tiny square under knees
			npc.originalColours[13] = 3115; // underneath knees
			npc.originalColours[14] = 3113; //trim over body
			npc.originalColours[15] = 3117; //2nd trim over body
			npc.originalColours[16] = 3133; //light part of body
			npc.originalColours[17] = 3109; //tiny bit of knee
			npc.originalColours[18] = 3111; //tiny bit of leg
			npc.originalColours[19] = 3127; //trim of tail and underbody
			npc.originalColours[20] = 3118; //parts of legs
			npc.originalColours[21] = 3121; //round face under legs etc
			npc.originalColours[22] = 3128; //round body
			npc.originalColours[23] = 3114; //back of leg tiny
			npc.originalColours[24] = 3120; //muscles
			npc.originalColours[25] = 48; //2 lines on top of back
			npc.originalColours[26] = 3134; //spikes
			npc.originalColours[27] = 3138; //spikes
			npc.originalColours[28] = 52; //wings and middle of back
			npc.originalColours[29] = 49; //wings
			npc.originalColours[30] = 3116; //trim
			npc.originalColours[31] = 3119; //trim
			npc.originalColours[32] = 3139; //trim
			npc.originalColours[33] = 3144; //trim
			npc.originalColours[34] = 3145; //trim
			npc.originalColours[35] = 44; //trim
			npc.originalColours[36] = 68; //2 lines on under belly
			npc.originalColours[37] = 3112; //little bit of under belly
			npc.originalColours[38] = 64; //whole under belly
			npc.originalColours[39] = 47; //bit near ass
			npc.originalColours[40] = 4129; //points on wings
			npc.originalColours[41] = 4137; //points on wings
			npc.originalColours[42] = 4145; //points on wings
			npc.originalColours[43] = 35903; //top teeth
			npc.originalColours[44] = 3124; //nose
			npc.originalColours[45] = 35898; //teeth
			npc.originalColours[46] = 35906; //teeth
			npc.originalColours[47] = 35902; //teeth
			npc.originalColours[48] = 35890; //teeth
			npc.originalColours[49] = 2096; //mouth
			npc.originalColours[50] = 3129; //face
			npc.originalColours[51] = 2094; //tounge
			npc.originalColours[52] = 2092; //tounge
			npc.originalColours[53] = 2090; //tounge
			npc.originalColours[54] = 3125; //face
			npc.originalColours[55] = 3123; //face
			npc.originalColours[56] = 2086; //back of mouth
			npc.originalColours[57] = 3130; //face
			npc.originalColours[58] = 3126; //face
			npc.originalColours[59] = 943; //eyes
			npc.originalColours[60] = 931; //eyes
			npc.originalColours[61] = 3137; //face
			npc.originalColours[62] = 3132; //face
			npc.originalColours[63] = 3131; //face
			npc.originalColours[64] = 24805; //idk
			npc.destColours = new int[65];
			npc.destColours[0] = 404; //wings colour
			npc.destColours[1] = 396; //wings colour
			npc.destColours[2] = 9234; //inner claws
			npc.destColours[3] = 9238; //outer claws
			npc.destColours[4] = 21660; // parts of the body
			npc.destColours[5] = 21656; // parts of body and wings
			npc.destColours[6] = 21657; // parts of body and wings
			npc.destColours[7] = 21662; // bit of feet and leg
			npc.destColours[8] = 21659; // bit of back 2 feet
			npc.destColours[9] = 21658; //tiny bit of back feet and back
			npc.destColours[10] = 21650; //inner back feet
			npc.destColours[11] = 21659; //nothing
			npc.destColours[12] = 21659; //tiny square under knees
			npc.destColours[13] = 21652; // underneath knees
			npc.destColours[14] = 21654; //trim over body
			npc.destColours[15] = 21654; //2nd trim over body
			npc.destColours[16] = 21654; //light part of body
			npc.destColours[17] = 21659; //tiny bit of knee
			npc.destColours[18] = 21659; //tiny bit of leg
			npc.destColours[19] = 21664; //trim of tail and underbody
			npc.destColours[20] = 21662; //parts of legs
			npc.destColours[21] = 21659; //round face under legs etc
			npc.destColours[22] = 21659; //round body
			npc.destColours[23] = 21658; //back of leg tiny
			npc.destColours[24] = 21665; //muscles
			npc.destColours[25] = 21664; //2 lines on top of back
			npc.destColours[26] = 21659; //spikes
			npc.destColours[27] = 21659; //spikes
			npc.destColours[28] = 21656; //wings and middle of back
			npc.destColours[29] = 21658; //wings
			npc.destColours[30] = 21664; //trim
			npc.destColours[31] = 21662; //trim
			npc.destColours[32] = 21663; //trim
			npc.destColours[33] = 21659; //trim
			npc.destColours[34] = 21664; //trim
			npc.destColours[35] = 21666; //trim
			npc.destColours[36] = 21659; //2 lines on under belly
			npc.destColours[37] = 21659; //little bit of under belly
			npc.destColours[38] = 21659; //whole under belly
			npc.destColours[39] = 21660; //bit near ass
			npc.destColours[40] = 21659; //points on wings
			npc.destColours[41] = 21659; //points on wings
			npc.destColours[42] = 21659; //points on wings
			npc.destColours[43] = 7; //top teeth
			npc.destColours[44] = 21659; //nose
			npc.destColours[45] = 7; //teeth
			npc.destColours[46] = 7; //teeth
			npc.destColours[47] = 7; //teeth
			npc.destColours[48] = 7; //teeth
			npc.destColours[49] = 21659; //mouth
			npc.destColours[50] = 21664; //face
			npc.destColours[51] = 21659; //tounge
			npc.destColours[52] = 21659; //tounge
			npc.destColours[53] = 21659; //tounge
			npc.destColours[54] = 21666; //face
			npc.destColours[55] = 21661; //face
			npc.destColours[56] = 21659; //back of mouth
			npc.destColours[57] = 21666; //face
			npc.destColours[58] = 21655; //face
			npc.destColours[59] = 29439; //eyes
			npc.destColours[60] = 29439; //eyes
			npc.destColours[61] = 21653; //face
			npc.destColours[62] = 21652; //face
			npc.destColours[63] = 21656; //face
			npc.destColours[64] = 21659; //idk
			npc.npcHeadModels = null;
			npc.drawMinimapDot = true;
			npc.combatLevel = 313;
			npc.hasRenderPriority = false;
			npc.lightning = 25;
			npc.shadow = 375;
			npc.headIcon = -1;
			npc.degreesToTurn = 32;
			npc.varbitId = -1;
			npc.varSettingsId = -1;
			npc.childrenIDs = null;
			npc.clickable = true;
			break;
			
		case 7543:
			npc.name = "Pet Executive Dragon";
			npc.description = "It's a Executive Dragon.";
			npc.models = new int[]{98244};
			npc.sizeXZ = 140;
			npc.sizeY = 140;
			npc.squaresNeeded = 1;
			npc.drawMinimapDot = false;
			npc.standAnim = 14251;
			npc.walkAnim = 14250;
			npc.runAnim = 14250;
			npc.actions = new String[5];
			/////npc.actions[0] = "pick-up";
			npc.npcHeadModels = null;
			npc.combatLevel = 313;
			npc.hasRenderPriority = false;
			npc.lightning = 25;
			npc.shadow = 375;
			npc.headIcon = -1;
			npc.degreesToTurn = 32;
			npc.varbitId = -1;
			npc.varSettingsId = -1;
			npc.childrenIDs = null;
			npc.clickable = true;
			break;
			
		case 6501:
			npc.models = new int[]{57958, 57960};
			npc.name = "Pet Runite dragon";
			npc.description = "It's a Runite dragon.";
			npc.sizeY = npc.sizeXZ = 70;
			npc.squaresNeeded = 1;
			npc.standAnim = 14251;
			npc.walkAnim = 14250;
			npc.runAnim = 14250;
			npc.actions = new String[5];
			////npc.actions[0] = "pick-up";
			npc.originalColours = new int[65];
			npc.originalColours[0] = 404; //wings colour
			npc.originalColours[1] = 396; //wings colour
			npc.originalColours[2] = 9234; //inner claws
			npc.originalColours[3] = 9238; //outer claws
			npc.originalColours[4] = 50; // parts of the body
			npc.originalColours[5] = 41; // parts of body and wings
			npc.originalColours[6] = 43; // parts of body and wings
			npc.originalColours[7] = 39; // bit of feet and leg
			npc.originalColours[8] = 55; // bit of back 2 feet
			npc.originalColours[9] = 46; //tiny bit of back feet and back
			npc.originalColours[10] = 37; //inner back feet
			npc.originalColours[11] = 5289; //nothing
			npc.originalColours[12] = 5285; //tiny square under knees
			npc.originalColours[13] = 3115; // underneath knees
			npc.originalColours[14] = 3113; //trim over body
			npc.originalColours[15] = 3117; //2nd trim over body
			npc.originalColours[16] = 3133; //light part of body
			npc.originalColours[17] = 3109; //tiny bit of knee
			npc.originalColours[18] = 3111; //tiny bit of leg
			npc.originalColours[19] = 3127; //trim of tail and underbody
			npc.originalColours[20] = 3118; //parts of legs
			npc.originalColours[21] = 3121; //round face under legs etc
			npc.originalColours[22] = 3128; //round body
			npc.originalColours[23] = 3114; //back of leg tiny
			npc.originalColours[24] = 3120; //muscles
			npc.originalColours[25] = 48; //2 lines on top of back
			npc.originalColours[26] = 3134; //spikes
			npc.originalColours[27] = 3138; //spikes
			npc.originalColours[28] = 52; //wings and middle of back
			npc.originalColours[29] = 49; //wings
			npc.originalColours[30] = 3116; //trim
			npc.originalColours[31] = 3119; //trim
			npc.originalColours[32] = 3139; //trim
			npc.originalColours[33] = 3144; //trim
			npc.originalColours[34] = 3145; //trim
			npc.originalColours[35] = 44; //trim
			npc.originalColours[36] = 68; //2 lines on under belly
			npc.originalColours[37] = 3112; //little bit of under belly
			npc.originalColours[38] = 64; //whole under belly
			npc.originalColours[39] = 47; //bit near ass
			npc.originalColours[40] = 4129; //points on wings
			npc.originalColours[41] = 4137; //points on wings
			npc.originalColours[42] = 4145; //points on wings
			npc.originalColours[43] = 35903; //top teeth
			npc.originalColours[44] = 3124; //nose
			npc.originalColours[45] = 35898; //teeth
			npc.originalColours[46] = 35906; //teeth
			npc.originalColours[47] = 35902; //teeth
			npc.originalColours[48] = 35890; //teeth
			npc.originalColours[49] = 2096; //mouth
			npc.originalColours[50] = 3129; //face
			npc.originalColours[51] = 2094; //tounge
			npc.originalColours[52] = 2092; //tounge
			npc.originalColours[53] = 2090; //tounge
			npc.originalColours[54] = 3125; //face
			npc.originalColours[55] = 3123; //face
			npc.originalColours[56] = 2086; //back of mouth
			npc.originalColours[57] = 3130; //face
			npc.originalColours[58] = 3126; //face
			npc.originalColours[59] = 943; //eyes
			npc.originalColours[60] = 931; //eyes
			npc.originalColours[61] = 3137; //face
			npc.originalColours[62] = 3132; //face
			npc.originalColours[63] = 3131; //face
			npc.originalColours[64] = 24805; //idk
			npc.destColours = new int[65];
			npc.destColours[0] = 32916; //wings colour
			npc.destColours[1] = 32908; //wings colour
			npc.destColours[2] = 9234; //inner claws
			npc.destColours[3] = 9238; //outer claws
			npc.destColours[4] = 36129; // parts of the body
			npc.destColours[5] = 36125; // parts of body and wings
			npc.destColours[6] = 36127; // parts of body and wings
			npc.destColours[7] = 36132; // bit of feet and leg
			npc.destColours[8] = 36129; // bit of back 2 feet
			npc.destColours[9] = 36128; //tiny bit of back feet and back
			npc.destColours[10] = 36120; //inner back feet
			npc.destColours[11] = 36129; //nothing
			npc.destColours[12] = 36129; //tiny square under knees
			npc.destColours[13] = 36132; // underneath knees
			npc.destColours[14] = 36124; //trim over body
			npc.destColours[15] = 36124; //2nd trim over body
			npc.destColours[16] = 36125; //light part of body
			npc.destColours[17] = 36129; //tiny bit of knee
			npc.destColours[18] = 36129; //tiny bit of leg
			npc.destColours[19] = 36134; //trim of tail and underbody
			npc.destColours[20] = 36132; //parts of legs
			npc.destColours[21] = 36129; //round face under legs etc
			npc.destColours[22] = 36129; //round body
			npc.destColours[23] = 36128; //back of leg tiny
			npc.destColours[24] = 36135; //muscles
			npc.destColours[25] = 36134; //2 lines on top of back
			npc.destColours[26] = 36129; //spikes
			npc.destColours[27] = 36129; //spikes
			npc.destColours[28] = 36126; //wings and middle of back
			npc.destColours[29] = 36128; //wings
			npc.destColours[30] = 36134; //trim
			npc.destColours[31] = 36132; //trim
			npc.destColours[32] = 36133; //trim
			npc.destColours[33] = 36129; //trim
			npc.destColours[34] = 36134; //trim
			npc.destColours[35] = 36136; //trim
			npc.destColours[36] = 36129; //2 lines on under belly
			npc.destColours[37] = 36129; //little bit of under belly
			npc.destColours[38] = 36129; //whole under belly
			npc.destColours[39] = 36130; //bit near ass
			npc.destColours[40] = 36129; //points on wings
			npc.destColours[41] = 36129; //points on wings
			npc.destColours[42] = 36129; //points on wings
			npc.destColours[43] = 7; //top teeth
			npc.destColours[44] = 36129; //nose
			npc.destColours[45] = 7; //teeth
			npc.destColours[46] = 7; //teeth
			npc.destColours[47] = 7; //teeth
			npc.destColours[48] = 7; //teeth
			npc.destColours[49] = 36129; //mouth
			npc.destColours[50] = 36134; //face
			npc.destColours[51] = 36129; //tounge
			npc.destColours[52] = 36129; //tounge
			npc.destColours[53] = 36129; //tounge
			npc.destColours[54] = 36136; //face
			npc.destColours[55] = 36131; //face
			npc.destColours[56] = 36129; //back of mouth
			npc.destColours[57] = 36136; //face
			npc.destColours[58] = 36125; //face
			npc.destColours[59] = 6730; //eyes
			npc.destColours[60] = 6730; //eyes
			npc.destColours[61] = 36133; //face
			npc.destColours[62] = 36132; //face
			npc.destColours[63] = 36136; //face
			npc.destColours[64] = 36129; //idk
			npc.npcHeadModels = null;
			npc.drawMinimapDot = true;
			npc.combatLevel = 341;
			npc.hasRenderPriority = false;
			npc.lightning = 25;
			npc.shadow = 375;
			npc.headIcon = -1;
			npc.degreesToTurn = 32;
			npc.varbitId = -1;
			npc.varSettingsId = -1;
			npc.childrenIDs = null;
			npc.clickable = true;
			break;
		case 4249:
			npc.name = "Gambler";
			break;
		case 341:
			npc.name = "Hobo";
			npc.actions = new String[] {"pick-up", null, null, null, null};
			break;
		case 6970:
			npc.actions = new String[] {"Trade", null, "Exchange Shards", null, null};
			break;
		case 4657:
			npc.actions = new String[] {"Talk-to", null, "Check Total", "Teleport", null};
			break;
		case 364:
			npc.actions = new String[] {"Talk-to", null, "Vote Rewards", "Loyalty Titles", null};
			break;
		case 8591:
			npc.actions = new String[] {"Talk-to", null, "Trade", null, null};
			break;
		case 316:
		case 315:
		case 309:
		case 310:
		case 314:
		case 312:
		case 313:
			npc.sizeXZ = 30;
			break;
		case 318:
			npc.sizeXZ = 30;
			npc.actions = new String[] {"Net", null, "Lure", null, null};
			break;
		case 233:
			npc.sizeXZ = 30;
			npc.squaresNeeded = 1;
			npc.name = "Rainbow Fish Spot";
			npc.actions = new String[] {"Bait", null, "Lure", null, null};
			break;
		case 805:
			npc.actions = new String[] {"Trade", null, "Tan hide", null, null};
			break;
		case 461:
		case 844:
		case 650:
		case 5112:
		case 3789:
		case 802:
		case 520:
		case 521:
		case 11226:
			npc.actions = new String[] {"Trade", null, null, null, null};
			break;
		case 8022:
		case 8028:
			String color = i == 8022 ? "Luminous" : "Elder";
			npc.name = ""+color+" energy";
			npc.actions = new String[] {"Harvest", null, null, null, null};
			break;
		case 8444:
			npc.actions = new String[5];
			npc.actions[0] = "Trade";
			break;
		case 2579:
			npc.name = "Veteran";
			npc.description = "One of ImaginePS's veterans.";
			npc.combatLevel = 200;
			npc.actions = new String[5];
			npc.actions[0] = "Talk-to";
//			npc.actions[2] = "Trade";
			npc.models = new int[7];
			npc.models[0] = 65289;
			npc.models[1] = 62746;
			npc.models[2] = 62743;
			npc.models[3] = 65305;
			npc.models[4] = 13319;
			npc.models[5] = 27738;
			npc.models[6] = 20147;
			npc.standAnim = 808;
			npc.walkAnim = 819;
			npc.npcHeadModels = NPCDef.forID(517).npcHeadModels;
			break;
		case 6830:
		case 6841:
		case 6796:
		case 7331:
		case 6831:
		case 7361:
		case 6847:
		case 6872:
		case 7353:
		case 6835:
		case 6845:
		case 6808:
		case 7370:
		case 7333:
		case 7351:
		case 7367:
		case 6853:
		case 6855:
		case 6857:
		case 6859:
		case 6861:
		case 6863:
		case 9481:
		case 6827:
		case 6889:
		case 6813:
		case 6817:
		case 7372:
		case 6839:
		case 8575:
		case 7345:
		case 6799:
		case 7335:
		case 7347:
		case 6800:
		case 9488:
		case 6804:
		case 6822:
		case 6849:
		case 7355:
		case 7357:
		case 7359:
		case 7341:
		case 7329:
		case 7339:
		case 7349:
		case 7375:
		case 7343:
		case 6820:
		case 6865:
		case 6809:
		case 7363:
		case 7337:
		case 7365:
		case 6991:
		case 6992:
		case 6869:
		case 6818:
		case 6843:
		case 6823:
		case 7377:
		case 6887:
		case 6885:
		case 6883:
		case 6881:
		case 6879:
		case 6877:
		case 6875:
		case 6833:
		case 6851:
		case 5079:
		case 5080:
		case 6824:
			npc.actions = new String[] {null, null, null, null, null};
			break;
		case 6806: // thorny snail
		case 6807:
		case 6994: // spirit kalphite
		case 6995:
		case 6867: // bull ant
		case 6868:
		case 6794: // spirit terrorbird
		case 6795:
		case 6815: // war tortoise
		case 6816:
		case 6874:// pack yak
		case 6873: // pack yak
		case 3594: // yak
		case 3590: // war tortoise
		case 3596: // terrorbird
			npc.actions = new String[] {"Store", null, null, null, null};
			break;
		case 548:
			npc.actions = new String[] {"Trade", null, null, null, null};
			break;
		case 3299:
		case 437:
			npc.actions = new String[] {"Trade", null, null, null, null};
			break;
		case 1267:
		case 8459:
			npc.drawMinimapDot = true;
			break;
		case 961:
			npc.actions = new String[] {null, null, "Buy Consumables", "Restore Stats", null};
			npc.name = "Healer";
			break;
		case 705:
			npc.actions = new String[] {null, null, "Buy Armour", "Buy Weapons", "Buy Jewelries"};
			npc.name = "Warrior";
			break;
		case 1861:
			npc.actions = new String[] {null, null, "Buy Equipment", "Buy Ammunition", null};
			npc.name = "Archer";
			break;
		case 946:
			npc.actions = new String[] {null, null, "Buy Equipment", "Buy Runes", null};
			npc.name = "Mage";
			break;
		case 2253:
			npc.actions = new String[] {null, null, "Buy Skillcapes", "Buy Skillcapes (t)", "Buy Hoods"};
			break;
		case 2292:
			npc.actions = new String[] {"Trade", null, null, null, null};
			npc.name = "Merchant";
			break;
		case 2676:
			npc.actions = new String[] {"Makeover", null, null, null, null};
			npc.colourRedefine = 98761;
			break;
		case 494:
		case 1360:
			npc.actions = new String[] {"Talk-to", null, null, null, null};
			npc.standAnim = 11973;
			npc.walkAnim = 11975;
			break;

		case 659:
			npc.actions = new String[] {"Talk-to", null, null, null, null};
			break;
		case 1685:
			npc.name = "Pure";
			npc.actions = new String[] {"Trade", null, null, null, null};
			break;
		case 3030:
			npc.name = "King black dragon";
			npc.actions = new String[5];
			//npc.actions[0] = "pick-up";
			npc.models = new int[] {17414, 17415, 17429, 17422};
			npc.combatLevel = 276;
			npc.standAnim = 90;
			npc.walkAnim = 4635;
			npc.sizeY = 63;
			npc.sizeXZ = 63;
			npc.squaresNeeded = 3;
			break;

		case 7889:
			npc.models = new int[] {95030};
			npc.name = "Vote Chest";
			npc.actions = new String[] {"Open", null, null, null, null};
			npc.squaresNeeded = 1;
			break;
			
		case 3031:
			npc.name = "General graardor";
			npc.actions = new String[5];
			//npc.actions[0] = "pick-up";
			npc.models = new int[] {27785, 27789};
			npc.combatLevel = 624;
			npc.standAnim = 7059;
			npc.walkAnim = 7058;
			npc.sizeY = 40;
			npc.sizeXZ = 40;
			break;	

		case 3032:
			npc.name = "TzTok-Jad";
			npc.actions = new String[5];
			//npc.actions[0] = "pick-up";
			npc.models = new int[] {34131};
			npc.combatLevel = 702;
			npc.standAnim = 9274;
			npc.walkAnim = 9273;
			npc.sizeY = 45;
			npc.sizeXZ = 45;
			npc.squaresNeeded = 2;
			break;
			//raids
			case 6:
				npc.name = "Perfect Cell";
				npc.actions = new String[5];
				npc.actions = new String[] {null, "Attack", null, null, null};
				npc.models = new int[] {6};
				npc.combatLevel = 500;
				npc.walkAnim = 819;
				npc.standAnim = 808;
				npc.sizeY = 150;
				npc.sizeXZ = 150;
				npc.squaresNeeded = 2;
				break;
			case 7:
				npc.name = "Beerus";
				npc.actions = new String[5];
				npc.actions = new String[] {null, "Attack", null, null, null};
				npc.models = new int[] {7,8,9,10,11,12};
				npc.combatLevel = 500;
				npc.walkAnim = 819;
				npc.standAnim = 808;
				npc.sizeY = 150;
				npc.sizeXZ = 150;
				npc.squaresNeeded = 2;
				break;

		case 3200:
			npc.name = "Chaos elemental";
			npc.combatLevel = 115;
			break;

		case 3033:
			npc.name = "Chaos elemental";
			npc.actions = new String[5];
			//npc.actions[0] = "pick-up";
			npc.models = new int[] {11216};
			npc.combatLevel = 115;
			npc.standAnim = 3144;
			npc.walkAnim = 3145;
			npc.sizeY = 62;
			npc.sizeXZ = 62;
			break;

		case 3034:
			npc.name = "Corporeal beast";
			npc.actions = new String[5];
			//npc.actions[0] = "pick-up";
			npc.models = new int[] {40955};
			npc.combatLevel = 785;
			npc.standAnim = 10056;
			npc.walkAnim = 10055;
			npc.sizeY = 45;
			npc.sizeXZ = 45;
			npc.squaresNeeded = 2;
			break;

		case 3035:
			npc.name = "Kree'arra";
			npc.actions = new String[5];
			//npc.actions[0] = "pick-up";
			npc.models = new int[] {28003, 28004};
			npc.combatLevel = 580;
			npc.standAnim = 6972;
			npc.walkAnim = 6973;
			npc.sizeY = 43;
			npc.sizeXZ = 43;
			npc.squaresNeeded = 2;
			break;

		case 3036:
			npc.name = "K'ril tsutsaroth";
			npc.actions = new String[5];
			//npc.actions[0] = "pick-up";
			npc.models = new int[] {27768, 27773, 27764, 27765, 27770};
			npc.combatLevel = 650;
			npc.standAnim = 6943;
			npc.walkAnim = 6942;
			npc.sizeY = 43;
			npc.sizeXZ = 43;
			npc.squaresNeeded = 2;
			break;
		case 3037:
			npc.name = "Commander zilyana";
			npc.actions = new String[5];
			//npc.actions[0] = "pick-up";
			npc.models = new int[] {28057, 28071, 28078, 28056};
			npc.combatLevel = 596;
			npc.standAnim = 6963;
			npc.walkAnim = 6962;
			npc.sizeY = 103;
			npc.sizeXZ = 103;
			npc.squaresNeeded = 2;
			break;
		case 3038:
			npc.name = "Dagannoth supreme";
			npc.actions = new String[5];
			//npc.actions[0] = "pick-up";
			npc.models = new int[] {9941, 9943};
			npc.combatLevel = 303;
			npc.standAnim = 2850;
			npc.walkAnim = 2849;
			npc.sizeY = 105;
			npc.sizeXZ = 105;
			npc.squaresNeeded = 2;
			break;

		case 3039:
			npc.name = "Dagannoth prime"; //9940, 9943, 9942
			npc.actions = new String[5];
			//npc.actions[0] = "pick-up";
			npc.models = new int[] {9940, 9943, 9942};
			npc.originalColours = new int[]{11930, 27144, 16536, 16540};
			npc.destColours = new int[]{5931, 1688, 21530, 21534};
			npc.combatLevel = 303;
			npc.standAnim = 2850;
			npc.walkAnim = 2849;
			npc.sizeY = 105;
			npc.sizeXZ = 105;
			npc.squaresNeeded = 2;
			break;

		case 3040:
			npc.name = "Dagannoth rex";
			npc.actions = new String[5];
			//npc.actions[0] = "pick-up";
			npc.models = new int[] {9941};
			npc.originalColours = new int[]{16536, 16540, 27144, 2477};
			npc.destColours = new int[]{7322, 7326, 10403, 2595};
			npc.combatLevel = 303;
			npc.standAnim = 2850;
			npc.walkAnim = 2849;
			npc.sizeY = 105;
			npc.sizeXZ = 105;
			npc.squaresNeeded = 2;
			break;
		case 3047:
			npc.name = "Frost dragon";
			npc.combatLevel = 166;
			npc.standAnim = 13156;
			npc.walkAnim = 13157;
			npc.colourRedefine = 65432;
			npc.turn180AnimIndex = -1;
			npc.turn90CCWAnimIndex = -1;
			npc.turn90CWAnimIndex = -1;
			//npc.type = 51;
			npc.degreesToTurn = 32 ;
			npc.models = new int[] {56767, 55294};
			npc.actions = new String[5];
			//npc.actions[0] = "pick-up";
			npc.sizeY = 72;
			npc.sizeXZ = 72;
			npc.squaresNeeded = 2;
			break;
//		case 11:
//			npc.name = "Frost dragon";
//			npc.combatLevel = 166;
//			npc.standAnim = -1;
//			npc.walkAnim = -1;
//			npc.colourRedefine = 65432;
//			npc.models = new int[] {111100};
//			npc.actions = new String[5];
//			//npc.actions[0] = "pick-up";
//			npc.squaresNeeded = 2;
//			break;
		case 12:
			npc.name = "Gods dragon";
			npc.combatLevel = 166;
			npc.standAnim = -1;
			npc.walkAnim = -1;
			npc.models = new int[] {111333};
			npc.actions = new String[5];
			//npc.actions[0] = "pick-up";
			npc.squaresNeeded = 3;
			break;

		/*	case 4392:
				babydragon = NPCDef.forID(3588);
				npc.name = "Belerion";
				npc.models = new int[3];
				npc.models[0] = babydragon.models[0];
				npc.models[1] = babydragon.models[1];
				npc.models[2] = babydragon.models[2];
				npc.colourRedefine2 =25;
				npc.walkAnim = babydragon.walkAnim;
				npc.standAnim = babydragon.standAnim;
				npc.squaresNeeded = babydragon.squaresNeeded;
				npc.elementalIcon = 1;
				break;  */
		case 4392:
            npc.models = new int[2];
            npc.name = "Bandicoot";
            npc.models[1] = 94218;
            npc.combatLevel = 75;
            npc.standAnim = 7047;
            npc.walkAnim = 7046;
            npc.actions = new String[5];
            npc.actions = new String[] {null, "Attack", null, null, null};
            npc.sizeXZ = 175; npc.sizeY = 175;
            break;
			
		case 6303:
			npc.models = new int[]{91780};
			npc.name = "Abbadon";
			npc.combatLevel = 100;
			npc.standAnim = 10921;
			npc.walkAnim = 10920;
			npc.turn180AnimIndex = -1;
			npc.turn90CCWAnimIndex = -1;
			npc.turn90CWAnimIndex = -1;
		//	npc.type = 8349;
			npc.destColours = new int[]{52};
			npc.originalColours = new int[]{51};
			npc.degreesToTurn = 32;
			npc.actions = new String[5];
			npc.actions[1] = "Attack";
			npc.sizeY = 60;
			npc.sizeXZ = 60;
			npc.squaresNeeded = 2;
			break;
		case 6304:
			npc.models = new int[]{91780};
			npc.name = "Abbadon";
			npc.combatLevel = 100;
			npc.standAnim = 10921;
            npc.destColours = new int[]{52};
            npc.originalColours = new int[]{51};
			npc.walkAnim = 10920;
			npc.turn180AnimIndex = -1;
			npc.turn90CCWAnimIndex = -1;
			npc.turn90CWAnimIndex = -1;
		//	npc.type = 8349;
			npc.degreesToTurn = 32;
			npc.actions = new String[5];
			npc.actions[1] = "Attack";
			npc.actions = new String[5];
			//npc.actions[0] = "pick-up";
			npc.sizeY = 40;
			npc.sizeXZ = 40;
			npc.squaresNeeded = 2;
			break;
		case 3048:
			npc.models = new int[]{44733};
			npc.name = "Tormented demon";
			npc.combatLevel = 450;
			npc.standAnim = 10921;
			npc.walkAnim = 10920;
			npc.turn180AnimIndex = -1;
			npc.turn90CCWAnimIndex = -1;
			npc.turn90CWAnimIndex = -1;
		//	npc.type = 8349;
			npc.degreesToTurn = 32;
			npc.actions = new String[5];
			//npc.actions[0] = "pick-up";
			npc.sizeY = 60;
			npc.sizeXZ = 60;
			npc.squaresNeeded = 2;
			break;
		case 3050:
			npc.models = new int[] {24602, 24605, 24606};
			npc.name = "Kalphite queen";
			npc.actions = new String[5];
			//npc.actions[0] = "pick-up";
			npc.combatLevel = 333;
			npc.standAnim = 6236;
			npc.walkAnim = 6236;
			npc.sizeY = 70;
			npc.sizeXZ = 70;
			npc.squaresNeeded = 2;
			break;
		case 3051:
			npc.models = new int[] {46141};
			npc.name = "Slash bash";
			npc.actions = new String[5];
			//npc.actions[0] = "pick-up";
			npc.combatLevel = 111;
			npc.standAnim = 11460;
			npc.walkAnim = 11461;
			npc.sizeY = 65;
			npc.sizeXZ = 65;
			npc.squaresNeeded = 2;
			break;
		case 3052:
			npc.models = new int[] {45412};
			npc.name = "Phoenix";
			npc.actions = new String[5];
			//npc.actions[0] = "pick-up";
			npc.combatLevel = 235;
			npc.standAnim = 11074;
			npc.walkAnim = 11075;
			npc.sizeY = 70;
			npc.sizeXZ = 70;
			npc.squaresNeeded = 2;
			break;
		case 3053:
			npc.models = new int[] {46058, 46057};
			npc.name = "Bandos avatar";
			npc.actions = new String[5];
			//npc.actions[0] = "pick-up";
			npc.combatLevel = 299;
			npc.standAnim = 11242;
			npc.walkAnim = 11255;
			npc.sizeY = 70;
			npc.sizeXZ = 70;
			npc.squaresNeeded = 2;
			break;

            case 2500:
                npc.name = "Black Phanther";
                npc.models = new int[]{94252, 94253, 94254, 44758, 44752};
                npc.walkAnim = 819;
                npc.standAnim = 808;
                npc.squaresNeeded = 1;
                npc.sizeXZ *=2;
                npc.sizeY *=2;
                npc.actions = new String[] {null, "Attack", null, null, null};
                break;

            case 2501:
                npc.name = "ImaginePS the Destroyer";
                npc.models = new int[]{94256, 94257, 94258, 44758, 44752};
                npc.walkAnim = 819;
                npc.standAnim = 808;
                npc.sizeXZ *=2;
                npc.sizeY *=2;
                npc.squaresNeeded = 1;
                npc.actions = new String[] {null, "Attack", null, null, null};
                break;

            case 2502:
                npc.name = "Ant man";
                npc.models = new int[]{94274, 94275, 94276, 44758, 44752};
                npc.walkAnim = 819;
                npc.standAnim = 808;
                npc.sizeXZ *=2;
                npc.sizeY *=2;
                npc.squaresNeeded = 1;
                npc.actions = new String[] {null, "Attack", null, null, null};
                break;
            case 2511:
                npc.name = "Spiderman";
                npc.models = new int[]{94262, 94263, 94264, 44758, 44752};
                npc.walkAnim = 819;
                npc.standAnim = 808;
                npc.sizeXZ *=2;
                npc.sizeY *=2;
                npc.squaresNeeded = 1;
                npc.actions = new String[] {null, "Attack", null, null, null};
                break;
            case 2518:
                npc.name = "JOKER";
                npc.models = new int[]{64498, 64500, 64502, 64504, 64505, 64507};
                npc.walkAnim = 819;
                npc.standAnim = 808;
                npc.sizeXZ *=2;
                npc.sizeY *=2;
                npc.squaresNeeded = 1;
                npc.actions = new String[] {null, "Attack", null, null, null};
                break;
                
                case 7552:
                    npc.name = "Infinito";
                    npc.models = new int[]{94488};
                    npc.walkAnim = 819;
                    npc.standAnim = 808;
                    npc.sizeXZ *=2;
                    npc.sizeY *=2;
                    npc.squaresNeeded = 1;
                    npc.actions = new String[] {null, "Attack", null, null, null};
                    break;
			case 3779:

				npc.name = "Deadpool";
				npc.actions = new String[]{null, "Attack", null, null, null};
				npc.combatLevel = 456;
				npc.squaresNeeded =1;
				npc.standAnim = 808;
				npc.walkAnim = 819;
				npc.sizeXZ = 500;
				npc.sizeY = 500;
				npc.models = new int[] {94460};
break;
				//definition.rdc2 = 2352;//29925

			case 3782:

				npc.name = "@red@Ainz Ooal Gown (Donator BOSS)@bla@";
				npc.actions = new String[]{null, "Attack", null, null, null};
				npc.combatLevel = 456;
				npc.squaresNeeded = 1;
				npc.standAnim = 808;
				npc.walkAnim = 819;
				npc.sizeXZ = 100;
				npc.sizeY = 100;
				npc.models = new int[] {41151};
				break;
			case 3785:
				npc.name = "@red@Demonic Mystery (Vote BOSS)@bla@";
				npc.actions = new String[]{null, "Attack", null, null, null};
				npc.combatLevel = 456;
				npc.squaresNeeded = 1;
				npc.standAnim = 808;
				npc.walkAnim = 819;
				npc.sizeXZ = 100;
				npc.sizeY = 100;
				npc.models = new int[] {41399};
				break;
                case 8453:
                    npc.name = "Beelzebub";
        			npc.models = new int[] {70009};
					npc.standAnim = 6236;
					npc.walkAnim = 6236;
                    npc.sizeXZ *=1.5;
                    npc.sizeY *=1.5;
                    npc.squaresNeeded = 1;
                    npc.actions = new String[] {null, "Attack", null, null, null};
                    break;
                
                case 4538:
                    npc.name = "Mini Chief Keefs";
                    npc.models = new int[]{92033, 92035, 92037, 92038, 92114, 96006};
                    npc.walkAnim = 819;
                    npc.standAnim = 808;
                    npc.sizeXZ = 40;
                    npc.sizeY = 40;
                    npc.squaresNeeded = 1;
            	    npc.originalColours = new int[]{55,60,51,55,55,54,55,55,57,55,59};
                    npc.destColours = new int[] {62,62,62,62,62,62,62,62,62,62,62};
                    npc.actions = new String[] {null, "Attack", null, null, null};
                    break;
            case 2505:
                npc.name = "bubbles";
                npc.models = new int[]{96187};
                npc.walkAnim = 819;
                npc.sizeXZ *=2;
                npc.sizeY *=2;
                npc.standAnim = 808;
                npc.squaresNeeded = 1;
                npc.actions = new String[] {null, "Attack", null, null, null};
                break;
            case 2506:
                npc.name = "Captain America";
                npc.models = new int[]{94265, 94266, 94267, 90689, 44758, 44752};
                npc.walkAnim = 819;
                npc.standAnim = 808;
                npc.squaresNeeded = 1;
                npc.sizeXZ *=2;
                npc.sizeY *=2;
                npc.actions = new String[] {null, "Attack", null, null, null};
                break;
            case 2509:
                npc.name = "Blossom";
                npc.models = new int[]{96186};
                npc.walkAnim = 819;
                npc.standAnim = 808;
                npc.sizeXZ *=2;
                npc.sizeY *=2;
                npc.squaresNeeded = 1;
                npc.actions = new String[] {null, "Attack", null, null, null};
                break;

            case 2515:
                npc.name = "Buttercup";
                npc.models = new int[]{96188};
                npc.walkAnim = 819;
                npc.standAnim = 808;
                npc.sizeXZ *=2;
                npc.sizeY *=2;
                npc.squaresNeeded = 1;
                npc.actions = new String[] {null, "Attack", null, null, null};
                break;





            case 3054:
			npc.models = new int[] {62717};
			npc.name = "Nex";
			npc.actions = new String[5];
			//npc.actions[0] = "pick-up";
			npc.combatLevel = 565;
			npc.standAnim = 6320;
			npc.walkAnim = 6319;
			npc.sizeY = 95;
			npc.sizeXZ = 95;
			npc.squaresNeeded = 1;
			break;
		case 3055:
			npc.models = new int[] {51852, 51853};
			npc.name = "Jungle strykewyrm";
			npc.actions = new String[5];
			//npc.actions[0] = "pick-up";
			npc.combatLevel = 110;
			npc.standAnim = 12790;
			npc.walkAnim = 12790;
			npc.sizeY = 60;
			npc.sizeXZ = 60;
			npc.squaresNeeded = 1;
			break;
		case 3056:
			npc.models = new int[] {51848, 51850};
			npc.name = "Desert strykewyrm";
			npc.actions = new String[5];
			//npc.actions[0] = "pick-up";
			npc.combatLevel = 130;
			npc.standAnim = 12790;
			npc.walkAnim = 12790;
			npc.sizeY = 60;
			npc.sizeXZ = 60;
			npc.squaresNeeded = 1;
			break;
		case 3057:
			npc.models = new int[] {51847, 51849};
			npc.name = "Ice strykewyrm";
			npc.actions = new String[5];
			//npc.actions[0] = "pick-up";
			npc.combatLevel = 210;
			npc.standAnim = 12790;
			npc.walkAnim = 12790;
			npc.sizeY = 65;
			npc.sizeXZ = 65;
			npc.squaresNeeded = 1;
			break;
		
		case 3058:
			npc.models = new int[] {49142, 49144};
			npc.name = "Green dragon";
			npc.actions = new String[5];
			//npc.actions[0] = "pick-up";
			npc.combatLevel = 79;
			npc.standAnim = 12248;
			npc.walkAnim = 12246;
			npc.sizeY = 70;
			npc.sizeXZ = 70;
			npc.squaresNeeded = 2;
			break;
		case 3059:
			npc.models = new int[] {57937};
			npc.name = "Baby blue dragon";
			npc.actions = new String[5];
			//npc.actions[0] = "pick-up";
			npc.combatLevel = 48;
			npc.standAnim = 14267;
			npc.walkAnim = 14268;
			npc.sizeY = 85;
			npc.sizeXZ = 85;
			npc.squaresNeeded = 1;
			break;
		case 3060:
			npc.models = new int[] {49137, 49144};
			npc.name = "Blue dragon";
			npc.actions = new String[5];
			//npc.actions[0] = "pick-up";
			npc.combatLevel = 111;
			npc.standAnim = 12248;
			npc.walkAnim = 12246;
			npc.sizeY = 70;
			npc.sizeXZ = 70;
			npc.squaresNeeded = 2;
			break;
		case 3062:
			npc.models = new int[] {31653};
			npc.name = "Skotizo";
			npc.actions = new String[5];
			//npc.actions[0] = "pick-up";
			npc.combatLevel = 321;
			npc.standAnim = 66;
			npc.walkAnim = 63;
			npc.sizeY = 20;
			npc.sizeXZ = 20;
			npc.squaresNeeded = 2;
			break;
		case 3063:
			npc.models = new int[] {0, 29270};
			npc.name = "Cerberus";
			npc.actions = new String[5];
			//npc.actions[0] = "pick-up";
			npc.combatLevel = 318;
			npc.standAnim = 4484;
			npc.walkAnim = 4488;
			npc.sizeY = 20;
			npc.sizeXZ = 20;
			npc.squaresNeeded = 1;
			break;
		case 4415:
			npc.models = new int[] {29477};
			npc.name = "Abyssal Sire";
			npc.actions = new String[5];
			//npc.actions[0] = "pick-up";
			npc.combatLevel = 350;
			npc.standAnim = 4533;
			npc.walkAnim = 4534;
			npc.sizeY = 20;
			npc.sizeXZ = 20;
			npc.squaresNeeded = 1;
			break;
		case 4416:
			npc.name = "Thermy";
			npc.description = "It looks like its glowing";
			npc.walkAnim = 1828;
			npc.standAnim = 1829;
			npc.actions = new String[5];
			//npc.actions[0] = "pick-up";

			npc.models = new int[1];
			npc.models[0] = 28442;
			npc.sizeXZ = 80;
			npc.sizeY = 80;
			npc.squaresNeeded = 1;
			break;
		case 4417:
			npc.models = new int[1];
			npc.models[0] = 28293;
			npc.name = "Scorp";
			npc.actions = new String[5];
			//npc.actions[0] = "pick-up";
			NPCDef scor1 = forID(107);
			npc.standAnim = scor1.standAnim;
			npc.walkAnim = scor1.walkAnim;
			npc.sizeXZ = 80;
			npc.sizeY = 80;
			npc.squaresNeeded = 2;
			break;
		case 4419:
			npc.name = "ALIEN";
			npc.actions = new String[5];
			npc.actions[1] = "Attack";
			npc.sizeXZ = 200;
			npc.sizeY = 200;
			break;
            case 8275:
		npc.models = new int[9];
				npc.models[0] = 206;
				npc.models[1] = 252;
				npc.models[2] = 306;
				npc.models[3] = 152;
				npc.models[4] = 176;
				npc.models[5] = 268;
				npc.models[6] = 185;
				npc.models[7] = 549;
				npc.models[8] = 323;

				break;
		case 200:
			npc.name = "RAT";
			npc.actions = new String[5];
			npc.actions[1] = "Attack";
			npc.models = new int[1];
			npc.models[0] = 90054;
			npc.squaresNeeded = 2;
			npc.combatLevel = 624;
			npc.standAnim = 7059;
			npc.walkAnim = 7058;
			break;
		//case 20:
			//npc.name = "RAT";
			//npc.actions = new String[5];
			//npc.actions[1] = "Attack";
			//npc.models = new int[1];
			//npc.models[0] = 90054;
			//npc.squaresNeeded = 2;
			//npc.combatLevel = 624;
			//npc.standAnim = 7059;
			//npc.walkAnim = 7058;
			//break;
		case 138:
			npc.name = "Terrorbird";
			npc.actions = new String[5];
			npc.actions[1] = "Attack";
			npc.combatLevel = 220;
			npc.sizeXZ = 200;
			npc.sizeY = 200;
			npc.squaresNeeded = 2;
			break;
		case 839: // dire wolf
			npc.name = "Dire Wolf Pup";
			npc.combatLevel = 80;
			npc.actions = new String[5];
			npc.actions[1] = "Attack";
			break;
		case 4413: // dire wolf
			npc.name ="Dire Wolf";
			npc.combatLevel = 126;
			npc.actions = new String[5];
			npc.actions[1] = "Attack";
			npc.sizeXZ = 200;
			npc.sizeY = 200;
			break;
		case 141: // pet dire wolf
			npc.name ="Pet Dire Wolf";
			npc.actions = new String[5];
			//npc.actions[0] = "pick-up";
			npc.sizeXZ = 100;;
			npc.sizeY = 100;
			npc.squaresNeeded = 2;
			break;
		case 433: // CYRISUS
			npc.actions = new String[5];
			npc.actions[1] = "Attack";
			npc.sizeXZ = 200;
			npc.sizeY = 200;
			break;
		case 5896:
			npc.name = "Pet Cyrisus";
			npc.actions = new String[5];
			//npc.actions[0] = "pick-up";
			npc.sizeXZ = 95;
			npc.sizeY = 95;
			npc.squaresNeeded = 2;
			break;
		case 4438:
			npc.name = "Pet Centaur";
			npc.description = "It looks like its glowing";
			npc.actions = new String[5];
			//npc.actions[0] = "pick-up";
			npc.sizeXZ = 80;
			npc.sizeY = 80;
			npc.squaresNeeded = 2;
			break;
		case 923:
			npc.name = "Pet Hell Bat";
			npc.description = "It looks like its glowing";
			npc.models = new int[1];
			npc.models[0] = 90556;
			npc.actions = new String[5];
			//npc.actions[0] = "pick-up";
			npc.squaresNeeded = 2;
			npc.sizeXZ = 450;
			npc.sizeY = 450;
			npc.standAnim = 35;
			npc.walkAnim = 29;
			break;
		case 1453:
			npc.name = "Pet Monkey";
			npc.description = "It looks like its glowing";
			npc.actions = new String[5];
			//npc.actions[0] = "pick-up";
			npc.squaresNeeded = 2;
			break;
		case 6302:
			npc.name = "ImaginePS mare Beast";
			npc.models = new int[]{90557};
			npc.standAnim = 2729;
			npc.walkAnim = 2731;
			npc.actions = new String[]{null, "Attack", null, null, null};
			npc.combatLevel = 101;
			npc.sizeXZ = 80;
			npc.sizeY = 80;
			npc.drawMinimapDot = false;
			break;
		case 9001:
	        npc.name = "@mag@SexySophia";
    		npc.combatLevel = 100;
            npc.squaresNeeded = 1;
            npc.walkAnim = 11975;
            npc.standAnim = 11973;
        	npc.models = new int[6];
			npc.models[0] = 64318; //HEAD
			npc.models[1] = 64320; //BODY
			npc.models[2] = 64322; //LEGS
			npc.models[3] = 64324; //GLOVES
			npc.models[4] = 64325; //BOOTS
			npc.actions = new String[5];
			npc.actions = new String[] {null, "Attack", null, null, null};
		    npc.sizeXZ = 200;
            npc.sizeY = 200;
			npc.drawMinimapDot = true;
			break;
		case 9002:
	        npc.name = "@red@RedPurge";
    		npc.combatLevel = 100;
            npc.squaresNeeded = 1;
            npc.walkAnim = 11975;
            npc.standAnim = 11973;
        	npc.models = new int[6];
			npc.models[0] = 130010; //HEAD
			npc.models[1] = 130014; //BODY
			npc.models[2] = 130012; //LEGS
			npc.models[3] = 130017; //GLOVES
		    npc.models[4] = 130016; //BOOTS
			npc.actions = new String[5];
			npc.actions = new String[] {null, "Attack", null, null, null};
		    npc.sizeXZ = 200;
            npc.sizeY = 200;
			npc.drawMinimapDot = true;
			break;
		case 9003:
	        npc.name = "@whi@WhiteBeard";
			npc.models = new int[]{97300};
    		npc.combatLevel = 100;
            npc.squaresNeeded = 1;
            npc.walkAnim = 11975;
            npc.standAnim = 11973;
        	//npc.models = new int[6];
			npc.actions = new String[5];
			npc.actions = new String[] {null, "Attack", null, null, null};
		    npc.sizeXZ = 200;
            npc.sizeY = 200;
			npc.drawMinimapDot = true;
			break;
			
		//case 4425:
		//	npc.name = "Callisto";
	//		npc.models = new int[] { 28298 };
	//		npc.actions = new String[5];
	///		//npc.actions[0] = "pick-up";
	//		npc.combatLevel = 470;
	//		NPCDef callisto1 = forID(105);
	//		npc.standAnim = callisto1.standAnim;
	//		npc.walkAnim = callisto1.walkAnim;
	//		npc.actions = callisto1.actions;
	//		npc.sizeXZ = npc.sizeY = 80;
//			npc.squaresNeeded = 2;
  //          break;
		//case 4420:
		//	npc.models = new int[1];
		//	npc.models[0] = 28300;
		//	npc.name = "Vetion";
		//	npc.actions = new String[5];
		//	//npc.actions[0] = "pick-up";
		//	NPCDef vet1 = forID(90);
		//	npc.standAnim = vet1.standAnim;
		//	npc.walkAnim = vet1.walkAnim;
		//	npc.sizeXZ = 80;
		//	npc.sizeY = 80;
		//	npc.squaresNeeded = 2;
		//	break;
		}
		return npc;
	}

	public Model getHeadModel() {
		if (childrenIDs != null) {
			NPCDef altered = getAlteredNPCDef();
			if (altered == null)
				return null;
			else
				return altered.getHeadModel();
		}
		if (npcHeadModels == null)
			return null;
		boolean everyFetched = false;
		for (int i = 0; i < npcHeadModels.length; i++)
			if (!Model.modelIsFetched(npcHeadModels[i]))
				everyFetched = true;
		if (everyFetched)
			return null;
		Model parts[] = new Model[npcHeadModels.length];
		for (int j = 0; j < npcHeadModels.length; j++)
			parts[j] = Model.fetchModel(npcHeadModels[j]);
		Model completeModel;
		if (parts.length == 1)
			completeModel = parts[0];
		else
			completeModel = new Model(parts.length, parts);
		if (originalColours != null) {
			for (int k = 0; k < originalColours.length; k++)
				completeModel.recolour(originalColours[k], destColours[k]);
		}
		if (colourRedefine > 0)
			completeModel.method1337(colourRedefine);
		if (colourRedefine2 != 0)
			completeModel.method1338(colourRedefine2);
		if (colourRedefine3 != 0)
			completeModel.method1339(colourRedefine3);


		applyTexturing(completeModel, id);
		return completeModel;
	}

	public NPCDef getAlteredNPCDef() {
		try {
			int j = -1;
			if (varbitId != -1) {
				VarBit varBit = VarBit.cache[varbitId];
				int k = varBit.configId;
				int l = varBit.leastSignificantBit;
				int i1 = varBit.mostSignificantBit;
				int j1 = Client.anIntArray1232[i1 - l];
				j = clientInstance.variousSettings[k] >> l & j1;
			} else if (varSettingsId != -1) {
				j = clientInstance.variousSettings[varSettingsId];
			}
			if (j < 0 || j >= childrenIDs.length || childrenIDs[j] == -1) {
				return null;
			} else {
				return forID(childrenIDs[j]);
			}
		} catch (Exception e) {
			return null;
		}
	}

	public static int NPCAMOUNT = 11599;

	public static void unpackConfig(CacheArchive streamLoader) {
		stream = new Stream(streamLoader.getDataForName("npc.dat"));
		Stream stream2 = new Stream(streamLoader.getDataForName("npc.idx"));
		int totalNPCs = stream2.readUnsignedWord();
		streamIndices = new int[totalNPCs];
		int i = 2;
		for (int j = 0; j < totalNPCs; j++) {
			streamIndices[j] = i;
			i += stream2.readUnsignedWord();
		}
		cache = new NPCDef[20];
		for (int k = 0; k < 20; k++)
			cache[k] = new NPCDef();
		//NPCDefThing2.initialize();
	}



    public static void printDefinitionsForId(int mobId) {
        /*Print out Grain*/
        NPCDef dump = NPCDef.forID(mobId);
        System.out.println("degreesToTurn: "+dump.standAnim);
        System.out.println("degreesToTurn: "+dump.walkAnim);
    }

	public static void nullLoader() {
		modelCache = null;
		streamIndices = null;
		cache = null;
		stream = null;
	}

	public Model getModel() {
		if (childrenIDs != null) {
			NPCDef npc = getAlteredNPCDef();
			if (npc == null)
				return null;
			else
				return npc.getModel();
		}
		Model completedModel = (Model) modelCache.get(type);
		if (completedModel == null) {
			boolean everyModelFetched = false;
			for (int ptr = 0; ptr < models.length; ptr++)
				if (!Model.modelIsFetched(models[ptr]))
					everyModelFetched = true;

			if (everyModelFetched)
				return null;
			Model parts[] = new Model[models.length];
			for (int j1 = 0; j1 < models.length; j1++)
				parts[j1] = Model.fetchModel(models[j1]);
			if (parts.length == 1)
				completedModel = parts[0];
			else
				completedModel = new Model(parts.length, parts);
			if (originalColours != null) {
				for (int k1 = 0; k1 < originalColours.length; k1++)
					completedModel.recolour(originalColours[k1], destColours[k1]);
			}
			if (colourRedefine > 0)
				completedModel.method1337(colourRedefine);
			if (colourRedefine2 != 0)
				completedModel.method1338(colourRedefine2);
			if (colourRedefine3 != 0)
				completedModel.method1339(colourRedefine3);
			if(id == 13700) {
				completedModel.method1337(9600);
			}

			applyTexturing(completedModel, id);
			// give me paste rights nub
			 if (rdc > 0)
					completedModel.method1337(rdc);
	    			if (rdc2 != 0)
	    				completedModel.method1338(rdc2);
	    			if (rdc3 != 0)
	    				completedModel.method1339(rdc3);
			completedModel.createBones();
			completedModel.light(frontLight, backLight, rightLight, middleLight, leftLight, true);
			modelCache.put(completedModel, type);
		}
		return completedModel;
	}

	public Model method164(int j, int frame, int ai[], int nextFrame, int idk, int idk2) {
		if (childrenIDs != null) {
			NPCDef npc = getAlteredNPCDef();
			if (npc == null)
				return null;
			else
				return npc.method164(j, frame, ai, nextFrame, idk, idk2);
		}
		Model completedModel = (Model) modelCache.get(type);
		if (completedModel == null) {
			boolean everyModelFetched = false;
			for (int ptr = 0; ptr < models.length; ptr++)
				if (!Model.modelIsFetched(models[ptr]))
					everyModelFetched = true;

			if (everyModelFetched)
				return null;
			Model parts[] = new Model[models.length];
			for (int j1 = 0; j1 < models.length; j1++)
				parts[j1] = Model.fetchModel(models[j1]);
			if (parts.length == 1)
				completedModel = parts[0];
			else
				completedModel = new Model(parts.length, parts);
			if (originalColours != null) {
				for (int k1 = 0; k1 < originalColours.length; k1++)
					completedModel.recolour(originalColours[k1], destColours[k1]);
			}
			if (colourRedefine > 0)
				completedModel.method1337(colourRedefine);
			if (colourRedefine2 != 0)
				completedModel.method1338(colourRedefine2);
			if (colourRedefine3 != 0)
				completedModel.method1339(colourRedefine3);
			if(id == 13700) {
				completedModel.method1337(9600);
			}

			applyTexturing(completedModel, id);
			 if (rdc > 0)
					completedModel.method1337(rdc);
	    			if (rdc2 != 0)
	    				completedModel.method1338(rdc2);
	    			if (rdc3 != 0)
	    				completedModel.method1339(rdc3);
			completedModel.createBones();
			completedModel.light(frontLight, backLight, rightLight, middleLight, leftLight, true);
			modelCache.put(completedModel, type);
		}
		Model animatedModel = Model.entityModelDesc;
		animatedModel.method464(completedModel, FrameReader.isNullFrame(frame) & FrameReader.isNullFrame(j));

		if (frame != -1 && j != -1)
			animatedModel.method471(ai, j, frame);
		else if (frame != -1 && nextFrame != -1)
			animatedModel.applyTransform(frame, nextFrame, idk, idk2);
		else if (frame != -1)
			animatedModel.applyTransform(frame);
		if (sizeXZ != 128 || sizeY != 128)
			animatedModel.scaleT(sizeXZ, sizeXZ, sizeY);
		animatedModel.calculateDiagonals();
		animatedModel.triangleSkin = null;
		animatedModel.vertexSkin = null;
		if (squaresNeeded == 1)
			animatedModel.rendersWithinOneTile = true;
		return animatedModel;
	}

	public void readValues(Stream stream) {
		do {
			int i = stream.readUnsignedByte();
			if (i == 0)
				return;
			if (i == 1) {
				int j = stream.readUnsignedByte();
				models = new int[j];
				for (int j1 = 0; j1 < j; j1++)
					models[j1] = stream.readUnsignedWord();
			} else if (i == 2)
				name = stream.readNewString();
			else if (i == 3) {
				description = stream.readNewString();
			} else if (i == 12)
				squaresNeeded = stream.readSignedByte();
			else if (i == 13)
				standAnim = stream.readUnsignedWord();
			else if (i == 14) {
				walkAnim = stream.readUnsignedWord();
				runAnim = walkAnim;
			} else if (i == 17) {
				walkAnim = stream.readUnsignedWord();
				turn180AnimIndex = stream.readUnsignedWord();
				turn90CWAnimIndex = stream.readUnsignedWord();
				turn90CCWAnimIndex = stream.readUnsignedWord();
				if (walkAnim == 65535)
					walkAnim = -1;
				if (turn180AnimIndex == 65535)
					turn180AnimIndex = -1;
				if (turn90CWAnimIndex == 65535)
					turn90CWAnimIndex = -1;
				if (turn90CCWAnimIndex == 65535)
					turn90CCWAnimIndex = -1;
			} else if (i >= 30 && i < 40) {
				if (actions == null)
					actions = new String[5];
				actions[i - 30] = stream.readNewString();
				if (actions[i - 30].equalsIgnoreCase("hidden"))
					actions[i - 30] = null;
			} else if (i == 40) {
				int k = stream.readUnsignedByte();
				destColours = new int[k];
				originalColours = new int[k];
				for (int k1 = 0; k1 < k; k1++) {
					originalColours[k1] = stream.readUnsignedWord();
					destColours[k1] = stream.readUnsignedWord();
				}
			} else if (i == 60) {
				int l = stream.readUnsignedByte();
				npcHeadModels = new int[l];
				for (int l1 = 0; l1 < l; l1++)
					npcHeadModels[l1] = stream.readUnsignedWord();
			} else if (i == 90)
				stream.readUnsignedWord();
			else if (i == 91)
				stream.readUnsignedWord();
			else if (i == 92)
				stream.readUnsignedWord();
			else if (i == 93)
				drawMinimapDot = false;
			else if (i == 95)
				combatLevel = stream.readUnsignedWord();
			else if (i == 97)
				sizeXZ = stream.readUnsignedWord();
			else if (i == 98)
				sizeY = stream.readUnsignedWord();
			else if (i == 99)
				hasRenderPriority = true;
			else if (i == 100)
				lightning = stream.readSignedByte();
			else if (i == 101)
				shadow = stream.readSignedByte() * 5;
			else if (i == 102)
				headIcon = stream.readUnsignedWord();
			 if (i == 103)
				degreesToTurn = stream.readUnsignedWord();
			else if (i == 106) {
				varbitId = stream.readUnsignedWord();
				if (varbitId == 65535)
					varbitId = -1;
				varSettingsId = stream.readUnsignedWord();
				if (varSettingsId == 65535)
					varSettingsId = -1;
				int i1 = stream.readUnsignedByte();
				childrenIDs = new int[i1 + 1];
				for (int i2 = 0; i2 <= i1; i2++) {
					childrenIDs[i2] = stream.readUnsignedWord();
					if (childrenIDs[i2] == 65535)
						childrenIDs[i2] = -1;
				}
			} else if (i == 107)
				clickable = false;
		} while (true);
	}

	public static void applyTexturing(Model model, int id) {
		switch (id) {
		
		case 6433:
			model.setTexture(55,50,35000);
			break;
			
		case 1677:
		case 1678:
		case 1679:
			case 8002:
			model.setTexture(103,29000,29800,29900,30000,84);
			break;

		case 6442:
			model.setTexture(86,50,35000);
			break;
			
		case 3200:
			model.setTexture(90);
			break;
			case 50:
				model.setTexture(88,0,100000);
				break;

			case 8766:
				model.setTexture(85,1000,100000);

				break;
		case 10141:
			model.setTexture(94,57,765000);
			break;
		}
	}

	
	
	
	public NPCDef() {
		turn90CCWAnimIndex = -1;
		varbitId = -1;
		turn180AnimIndex = -1;
		varSettingsId = -1;
		combatLevel = -1;
		walkAnim = -1;
		squaresNeeded = 1;
		headIcon = -1;
		standAnim = -1;
		type = -1L;
		degreesToTurn = 32;
		turn90CWAnimIndex = -1;
		clickable = true;
		sizeY = 128;
		drawMinimapDot = true;
		sizeXZ = 128;
		hasRenderPriority = false;
		rdc = 0;
        rdc2 = 0;
        rdc3 = 0;
	}

	public int turn90CCWAnimIndex;
	public static int cacheIndex;
	public int varbitId;
	public int turn180AnimIndex;
	public int varSettingsId;
	public static Stream stream;
	public int combatLevel;
	public String name;
	public String actions[];
	public int walkAnim;
	public int runAnim;
	public byte squaresNeeded;
	public int[] destColours;
	public static int[] streamIndices;
	public int[] npcHeadModels;
	public int headIcon;
	public int elementalIcon;
	public int[] originalColours;
	public int standAnim;
	public long type;
	public int degreesToTurn;
	public static NPCDef[] cache;
	public static Client clientInstance;
	public int turn90CWAnimIndex;
	public boolean clickable;
	public int lightning;
	public int sizeY;
	public boolean drawMinimapDot;
	public int childrenIDs[];
	public String description;
	public int sizeXZ;
	public int shadow;
	public boolean hasRenderPriority;
	public int[] models;
	public static MemCache modelCache = new MemCache(30);
	public int id;
}