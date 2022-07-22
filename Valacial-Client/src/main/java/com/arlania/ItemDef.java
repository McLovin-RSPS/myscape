package com.arlania;

import java.awt.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public final class ItemDef {

    private static int[] prices;
    public static Color clorDefcode;
    public int[] clorDefcodeTo = {0};
	public int colourRedefine = 0;
	public int colourRedefine2 = 0;
	public int colourRedefine3 = 0;
    public String groundName;

    /** Easier way to pack custom models -- Adam. **/
	
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

    private static List<Integer> untradeableItems = new ArrayList<Integer>();


	public static void applyTexturing(Model model, int id) {
		switch (id) {

			case 21013:
				model.setTexture(103,1000,5000,84);
				break;
			case 21020:
			case 14876:
				model.setTexture(84);
				break;
			case 20104:
			case 20105:
			case 20107:
			case 20103:
				model.setTexture(93,0,2500,84);
				break;
			case 20106:
				model.setTexture(84);
				break;
			case 20102:
				model.setTexture(84,0,75,93);
				break;

			case 21021:
				model.setTexture(84,0,5000,103);
				break;

			case 20086:
				model.setTexture(84,1000,5000,103);
				break;
			case 20087:
			case 20088:
				model.setTexture(84,1000,10000,103);
				break;
		case 20501:
		case 20502:
		case 20500:

			model.setTexture(55,4018);
			model.setTexture(55,3761);
			model.setTexture(55,3756);
			model.setTexture(55,3503);
			model.setTexture(55,3994);
			model.setTexture(55,4000);
			model.setTexture(55,4008);
			break;
			
		case 979:
		case 980:
		case 2548:
        //    model.setTexture(54, 1, 1, 16);
			break;
			
		case 20503:
			model.setTexture(66, 1, 5000, 16);
			break;

//			case 21013:
//				model.setTexture(55,4018);
//				model.setTexture(55,3761);
//				model.setTexture(55,3756);
//				model.setTexture(55,3503);
//				model.setTexture(55,3994);
//				model.setTexture(55,4000);
//				model.setTexture(55,4008);
//				break;
				//item id
			case 1017:
				model.setTexture(52);
				break;
			case 20079:
				model.setTexture(59,3500,4318);
				break;
	case 20084:
	case 20085:
		model.setTexture(59);
		break;

			case 4636:
				model.setTexture(88,0,100);
				break;
			case 4637:
			case 4638:
				model.setTexture(88,0,200);
				break;
			case 4639:
			case 4640:
				model.setTexture(88,0,10000);
				break;


				case 5163:
				case 5165:
				case 5166:
				case 5167:
				case 5168:
				case 5164:
				model.setTexture(85,18367);
			break;



	case 4281:
	case 14934:
	case 14935:
		model.setTexture(94,100,200);
		break;
			case 20081:
			case 20082:
			case 20083:
			case 6798:
				model.setTexture(59,654645);
				break;
			case 8839:
                model.setTexture(65,31,100);
                break;
			case 8840:
                model.setTexture(65,31,100);
                break;
			case 19787:
			case 19788:
				model.setTexture(53,31,150);
				model.setTexture(53,500,10000);
				break;
			case 13196:
			case 13197:
			case 13198:
				//model.setTexture(95,30,75,84);
				model.setTexture(95);
				break;
			case 20259:
			case 3684:
			case 3685:
			case 3683:
			case 1544:
				model.setTexture(91);
				break;
			case 21063:
				model.setTexture(16,1,10000,66);
				break;
			case 21064:
			case 21065:
			case 21066:
				model.setTexture(66,800,935,16);
				break;


			case 11665:
			case 11663:
			case 11664:
                model.setTexture(65,31,10000000);
                break;

                case 21056:
                case 21057:
                case 21058:
                model.setTexture(53,31,10000000);
                break;
			case 21059:
			case 20551:
				model.setTexture(53);
				break;

                case 20550:
                model.setTexture(65,31,10000000);
                break;
			case 8842:
				model.setTexture(65);
				break;
			case 20453:
			case 4632:
				model.setTexture(55);
				break;

//			case 3682:
//			case 20251:
//			case 20252:
//			case 20253:
//			case 20254:
//			case 20255:
//			case 20256:
//			case 20257:
//			case 20258:
//				//model.setTexture();
//				break;

			case 10907:
				model.setTexture(94,50,100);
				break;
			case 20114:
			case 20113:
			case 20112:
			case 20451:
				model.setTexture(52,0,10000000);
				break;
			 case 20452:
			 case 4634:
				model.setTexture(60);
				break;
				case 20012:
					model.setTexture(57,10,100);
				break;
			case 20010:
			case 20011:
				model.setTexture(57,30,55);
				break;
				case 20020:
			case 20019:
				model.setTexture(57);
				break;
			case 20700:
			case 20701:
			case 20702:
			case 20706:
			case 20703:
			case 20704:
			case 4631:
			case 694:
				model.setTexture(98);
				break;

			case 19903:
				model.setTexture(69);
				break;

			case 19904:
				model.setTexture(60);
				break;
				
				
				
				
				
				
				//|------------------ Ranks --------------|\\
			case 12421:
			    break;
			case 12422:
			    model.setTexture(66);
			    break;
			case 12423:
			    model.setTexture(53);
			    break;
			case 12424:
			    model.setTexture(54);
			    break;
			case 12425:
			    model.setTexture(57);
			    break;
			case 12426:
			    model.setTexture(56);
			    break;
			  //|------------------ End --------------|\\
			    
			    
			    
			case 1580:
			case 628:
			    model.setTexture(52);
			    
			    
			case 19905:
				model.setTexture(52);
				break;
			case 20095:
			case 20096:
			case 20097:
			case 20098:
            case 20450:
			case 20099:
			case 20100:
			case 20603:
			case 4630:
			case 20101:
				model.setTexture(88);
				break;
			case 10865:
			case 12629:
				model.setTexture(83);
				break;
			case 17849:
				model.setTexture(55);
			break;

			case 2867:
			case 21044:
					model.setTexture(83,new int[]{83,89,97,103});
				break;

			case 13655:
				model.setTexture(70,97000);
				break;
			case 4629:
				model.setTexture(70);
				break;
			case 4626:
				model.setTexture(70);
				break;
			case 6102:
				model.setTexture(55,50,34000);
				break;
			case 2543:
			case 4799:
			case 4800:
			case 4801:
			case 4802:
				model.setTexture(59,50,35000);
				break;
			case 5111:
			case 5112:
			case 5113:
			case 5114:
			case 5115:
			case 5116:
			case 3818:
				model.setTexture(55,50,35000);
				break;
			case 4774:
			case 4775:
			case 4776:
			case 4777:
			case 4779:
				model.setTexture(86,60,45345);
				break;
			case 5000:
				model.setTexture(59,64,90000);
				break;
			case 13206:
			case 13207:
			case 13199:
			case 21060:
			case 4633:
				model.setTexture(95);
				break;
		}
	}

	 public static void nullLoader() {
	        modelCache = null;
	        spriteCache = null;
	        streamIndices = null;
	        cache = null;
	        stream = null;
	    }

		int[] originalModelColor;
	    

	    public boolean dialogueModelFetched(int j) {
	        int k = maleDialogue;
	        int l = maleDialogueModel;
	        if (j == 1) {
	            k = femaleDialogue;
	            l = femaleDialogueModel;
	        }
	        if (k == -1) {
	            return true;
	        }
	        boolean flag = true;
	        if (!Model.modelIsFetched(k)) {
	            flag = false;
	        }
	        if (l != -1 && !Model.modelIsFetched(l)) {
	            flag = false;
	        }
	        return flag;
	    }

	    public Model getDialogueModel(int gender) {
	        int k = maleDialogue;
	        int l = maleDialogueModel;
	        if (gender == 1) {
	            k = femaleDialogue;
	            l = femaleDialogueModel;
	        }
	        if (k == -1) {
	            return null;
	        }
	        Model model = Model.fetchModel(k);
	        if (l != -1) {
	            Model model_1 = Model.fetchModel(l);
	            Model models[] = {model, model_1};
	            model = new Model(2, models);
	        }
	        if (editedModelColor != null) {
	            for (int i1 = 0; i1 < editedModelColor.length; i1++) {
	                model.recolour(editedModelColor[i1], newModelColor[i1]);
	            }
	        }

	        if (colourRedefine > 0)
	    		model.method1337(colourRedefine);
	    		if (colourRedefine2 != 0)
	    		model.method1338(colourRedefine2);
	    		if (colourRedefine3 != 0)
	    		model.method1339(colourRedefine3);

			applyTexturing(model, id);


			return model;
	    }

	    public boolean equipModelFetched(int gender) {
	        int fistModel = maleWearId;
	        int secondModel = maleEquip2;
	        int thirdModel = maleEquip3;
	        if (gender == 1) {
	            fistModel = femaleWearId;
	            secondModel = femaleEquip2;
	            thirdModel = femaleEquip3;
	        }
	        if (fistModel == -1) {
	            return true;
	        }
	        boolean flag = true;
	        if (!Model.modelIsFetched(fistModel)) {
	            flag = false;
	        }
	        if (secondModel != -1 && !Model.modelIsFetched(secondModel)) {
	            flag = false;
	        }
	        if (thirdModel != -1 && !Model.modelIsFetched(thirdModel)) {
	            flag = false;
	        }
	        return flag;
	    }

	    public Model getEquipModel(int gender) {
	        int j = maleWearId;
	        int k = maleEquip2;
	        int l = maleEquip3;
	        if (gender == 1) {
	            j = femaleWearId;
	            k = femaleEquip2;
	            l = femaleEquip3;
	        }
	        if (j == -1) {
	            return null;
	        }
	        Model model = Model.fetchModel(j);
	        if (k != -1) {
	            if (l != -1) {
	                Model model_1 = Model.fetchModel(k);
	                Model model_3 = Model.fetchModel(l);
	                Model model_1s[] = {model, model_1, model_3};
	                model = new Model(3, model_1s);
	            } else {
	                Model model_2 = Model.fetchModel(k);
	                Model models[] = {model, model_2};
	                model = new Model(2, models);
	            }
	        }
	        //if (j == 62367)
	        //model.translate(68, 7, -8);
	        if (gender == 0 && maleWieldY != 0) {
	            model.translate(0, maleWieldY, 0);
	        } else if (gender == 1 && femaleWieldY != 0) {
	            model.translate(0, femaleWieldY, 0);
	        }
	        if (editedModelColor != null) {
	            for (int i1 = 0; i1 < editedModelColor.length; i1++) {
	                model.recolour(editedModelColor[i1], newModelColor[i1]);
	            }
	        }

	        if (colourRedefine > 0)
	    		model.method1337(colourRedefine);
	    		if (colourRedefine2 != 0)
	    		model.method1338(colourRedefine2);
	    		if (colourRedefine3 != 0)
	    		model.method1339(colourRedefine3);

			applyTexturing(model, id);
			
			return model;
	    }

	    public void setDefaults() {
	        untradeable = false;
	        modelID = 0;
	        name = null;
	        groundName = null;
	        description = null;
	        editedModelColor = null;
	        newModelColor = null;
	        clorDefcode = null;
	        clorDefcodeTo = null;
	        modelZoom = 2000;
	        modelRotationY = 0;
	        modelRotationX = 0;
	        modelOffsetX = 0;
	        modelOffset1 = 0;
	        modelOffsetY = 0;
	        stackable = false;
	        value = 0;
	        membersObject = false;
	        groundActions = null;
	        actions = null;
	        maleWearId = -1;
	        maleEquip2 = -1;
	        maleWieldY = 0;
	        maleXOffset = 0;
	        femaleWearId = -1;
	        femaleEquip2 = -1;
	        femaleWieldY = 0;
	        maleEquip3 = -1;
	        femaleEquip3 = -1;
	        maleDialogue = -1;
	        maleDialogueModel = -1;
	        femaleDialogue = -1;
	        femaleDialogueModel = -1;
	        stackIDs = null;
	        stackAmounts = null;
	        certID = -1;
	        certTemplateID = -1;
	        sizeX = 128;
	        sizeY = 128;
	        sizeZ = 128;
	        shadow = 0;
	        lightness = 0;
	        team = 0;
	        lendID = -1;
	        lentItemID = -1;
	        colourRedefine = 0;
	        colourRedefine2 = 0;
	        colourRedefine3 = 0;
	    }

	    public static void unpackConfig(CacheArchive streamLoader) {
	        /*
			 * stream = new Stream(FileOperations.ReadFile("./Cache/obj.dat"));
			 * Stream stream = new
			 * Stream(FileOperations.ReadFile("./Cache/obj.idx"));
	         */
	        stream = new Stream(streamLoader.getDataForName("obj.dat"));
	        Stream stream = new Stream(streamLoader.getDataForName("obj.idx"));
	        totalItems = stream.readUnsignedWord();
	        streamIndices = new int[totalItems + 1000];
	        int i = 2;
	        for (int j = 0; j < totalItems; j++) {
	            streamIndices[j] = i;
	            i += stream.readUnsignedWord();
	        }
	        cache = new ItemDef[10];
	        for (int k = 0; k < 10; k++) {
	            cache[k] = new ItemDef();
	        }
	        setSettings();
	    }

		public static ItemDef copyRotations(ItemDef itemDef, int id) {
			ItemDef itemDef2 = ItemDef.forID(id);
			itemDef.modelOffsetY = itemDef2.modelOffsetY;
			itemDef.modelOffsetX = itemDef2.modelOffsetX;
			itemDef.modelOffsetY = itemDef2.modelOffsetY;
			itemDef.modelOffset1 = itemDef2.modelOffset1;
			itemDef.modelZoom = itemDef2.modelZoom;
			itemDef.modelRotationX = itemDef2.modelRotationX;
			itemDef.modelRotationY = itemDef2.modelRotationY;
			return itemDef;
		}

		public enum CustomItems {
			RED_WHOOPASS_WINGS(19709, 48334, 48334, 48334, false), // 18888 cape, shield
			ELITE_CROSSBOW(9185, 48353, 48354, 48354, true), // 18889
			ELITE_MAGE_BOOK(3842, 48361, 48362, 48362, false), // 18890
			ELITE_MAGE_WAND(6914, 48363, 48364, 48364, true), // 18891
			LIGHT_BLUE_WINGS(19709, 48375, 48375, 48375, false), // 18892
			GODSTONE_SHIELD(8714, 13086, 13087, 13087, false), // 18893
			BLOODY_DREADFUL_SHIELD(8714, 13701, 13700, 13700, false), // 18894
			JUGGERNAUT_RING(18907, 47762, -1, false), // 18895
			ANCIENT_AXES(4747, 95150, 95151, 95151, false), // 18896
			QUEST_CAPE(19709, 27657, 27656, 27656, false), // 18897
			GEMSTONE_SWORD(18351, 82966, 82967, 82967, false), // 18898
			MALEVOLENT_SHIELD(8714, 48394, 48395, 48395, false), // 18899
			NINJA_THROWING_KNIFE(5698, 48396, 48397, 48397, false), // 18900
			RAINBOW_SPIRIT_SHIELD(5698, 80010, 80011, 80011, false), // 18901
			STRIPED_WHIP(18351, 80028, 80029, 80029, true), // 18902
			BLUE_DREADFUL_SHIELD(8714, 80031, 80030, 80030, false), // 18903
			IRON_PARTYHAT(1038, new int[] { 926, 0, 0, 0 }, new int[] { 33, 0, 0, 0 }), // 18904
			DARK_BLUE_DREADFUL_SHIELD(8714, 16065, false), // 18905
			BLADED_SHIELD(8714, 19550, true), // 18906
			RING_OF_RICHES(18907, 68824, -1, false), // 18907
			TENACIOUS_RING(6737, 68825, -1, false), // 18908
			BLUE_OBBY_SHIELD(8714, 30001, 30000, false), // 18909
			GREEN_OBBY_SHIELD(8714, 30003, 30002, false), // 18910
			CREATURE_WHIP(4151, 4185, 4186, true),
			GOLD_DREADFUL_SHIELD(8714, 55028, 55027, false), // 18912
			MACHINED_CLAWS(14484, 62937, 62936, false), // 18913
			PURPLE_SPIRIT_SHIELD(5698, 87654, 87653, false), // 18914
			RELIGIOUS_SWORD(18351, 86044, true), // 18915
			ANGELIC_PLATELEGS(14010, 86065, 86062, false), // 18916
			ANGELIC_PLATEBODY(14009, 86067, 86063, false), // 18917
			ILLUMINNATION_HELM(14008, 86066, 86064, false), // 18918
			MINIGUN(5698, 74119, true), // 18919
			RAINBOW_WINGS(19709, 23231, 23232, false), // 18920
			GOBLIN_MASK(14010, 28666, 28553, false), // 18921
			WHITE_WINGS(19709, 45000, false), // 18922
			ROYAL_THROWING_AXE(1377, 72044, true), // 18923
			RAY_GUN(5698, 74114, false), // 18924
			GHOSTLY_SWORDS(18351, 75058, 75059, true), // 18925
			PINK_WINGS(19709, 75860, 75859, false), // 18926
			DARK_RED_WINGS(19709, 75861, 75862, false), // 18927
			BLACK_WINGS(19709, 75863, 75864, false), // 18928
			FIERY_WINGS(19709, 75865, 75866, false), // 18929
			RAINBOW_WHIP(18351, 78101, true), // 18930
			ICY_CROSSBOW(9185, 79411, true), // 18931
			CHILLER_SWORD(18351, 79413, true), // 18932
			RED_SPIRIT_SHIELD(8714, 80004, false), // 18933
			GOLD_SPIRIT_SHIELD(8714, 80007, false), // 18934
			TOY_CROSSBOW(9185, 90009, 90009, true), // 18935
			AMERICAN_PARTYHAT(1048, 74006, 74006, 74007, false), // 18936
			TECHTONIC_ROBE_BOTTOM(18937, 90025, 90023, false), // 18937
			TECHTONIC_ROBE_TOP(18938, 90020, 90022, false), // 18938
			TECHTONIC_HELM(18939, 90019, 90017, false), // 18939
			SIRENIC_PLATELEGS(18940, 90032, 90034, false), // 18940
			SIRENIC_PLATEBODY(18941, 90029, 90031, false), // 18941
			SIRENIC_FULL_HELM(18942, 90028, 90026, false), // 18942
			MALEVOLENT_PLATELEGS(18943, 90043, 90041, false), // 18943
			MALEVOLENT_PLATEBODY(18944, 90040, 90038, false), // 18944
			MALEVOLENT_HELM(18945, 90037, 90035, false), // 18945
			DRAGONBONE_KITESHIELD(8714, 90045, 90044, 90044, false), // 18946
			ARMADYL_CROSSBOW(18357, 90047, 90046, 90046, true), // 18947
			DRAGON_BOOTS(11732, 90049, 90049, 90049, false), // 18948 start
			SKY_TORVA_HELM(14008, 100, 100, 101, false), // 18949
			PAPER_SACK(1048, 10031, 10031, 10031, false), // 18950
			COLORFUL_WINGS(19709, 10094, 10094, 10093, false), // 18951
			ROYAL_CAPE(19709, 1015, 1015, 1017, false), // 18952
			SKY_TORVA_PLATEBODY(14009, 102, 102, 103, false), // 18953
			SKY_TORVA_WINGS(19709, 104, 104, 105, false), // 18954
			SKY_TORVA_BOOTS(11732, 95133, 95133, 95144, false), // 18955
			SKY_TORVA_GLOVES(1059, 95131, 95131, 95132, false), // 18956
			SKY_BRUTAL_WHIP(18351, 95129, 95129, 95130, true), // 18957
			GOLDEN_DEATH_CAPE(19709, 11112, 11112, 11112, false), // 18958
			ONE_GLOVE(1059, 11114, 11114, 11114, false), // 18959
			ONE_LEGS(14010, 11115, 11115, 11115, false), // 18960
			ONE_CAPE(19709, 11118, 11118, 11118, false), // 18961
			SKY_TORVA_PLATELEGS(14010, 95127, 95127, 95128, false), // 18962
			ANIMAL_HELM(14008, 12276, 12276, 15002, false), // 18963
			ANIMAL_BODY(14009, 12278, 12278, 15004, false), // 18964
			ANIMAL_BOW(861, 12323, 12316, 12316, true), // 18965
			HUGE_BIRD_WINGS(19709, 12542, 12542, 12542, false), // 18966
			SPARTA_BOOTS(11732, 13565, 13565, 13565, false), // 18967
			BLUE_DONATOR_CAPE(19709, 13955, 13955, 13956, false), // 18968
			RED_DONATOR_CAPE(19709, 13957, 13957, 13958, false), // 18969
			DOUBLE_ENDED_DILDO(18351, 14053, 14053, 14052, true), // 18970
			GUNDAM_BOW(861, 14680, 14680, 14679, true), // 18971
			ANIMAL_LEGS(14010, 15005, 15005, 15005, false), // 18972
			ORNAMENTAL_PLATE(14009, 15042, 15042, 82947, false), // 18973
			ORNAMENTAL_PLATELEGS(14010, 15044, 15044, 15043, false), // 18974
			ORNAMENTAL_BOOTS(11732, 15046, 15046, 15045, false), // 18975
			ORNAMENTAL_GLOVES(1059, 15049, 15049, 15047, false), // 18976
			GREEN_DONATOR_CAPE(19709, 16020, 16020, 16021, false), // 18977
			DONATOR_SHIELD(8714, 16034, 16034, 16035, false), // 18978
			COLORFUL_SCARF(1725, 16829, 16829, 16830, false), // 18979
			COLORFUL_HELM(18945, 19993, 19993, 19993, false), // 18980
			COLORFUL_PLATE(14009, 19995, 19995, 19995, false), // 18981
			COLORFUL_PLATELEGS(14010, 19996, 19996, 19996, false), // 18982
			PINK_DILDO(18351, 20, 20, 20, true), // 18983
			ORNAMENTAL_HELM(14008, 2035, 2035, 2036, false), // 18984
			INFERNAL_SWORD(18351, 95125, 95125, 95125, true), // 18985
			PURPLE_GLASS_WINGS(19709, 23060, 23060, 23061, false), // 18986
			RAINBOW_CAPE(19709, 23234, 23234, 23235, false), // 18987
			STACKED_AXE(18351, 24092, 24092, 24091, true), // 18988
			DOOM_BLADE(18351, 95126, 95126, 95126, true), // 18989
			POOL_CUE(18351, 26096, 26096, 26097, true), // 18990
			GREEN_CROWN(1048, 27222, 27222, 27221, false), // 18991
			RED_CROWN(1048, 27232, 27232, 27231, false), // 18992
			PURPLE_CROWN(1048, 27242, 27242, 27241, false), // 18993
			COLORFUL_LANCE(18351, 27274, 27274, 27275, true), // 18994
			WINGED_DONATOR_CAPE(19709, 27830, 27830, 27831, false), // 18995
			DEVIL_TAIL(14010, 27864, 27864, 27863, false), // 18996
			DEVIL_HORNS(1048, 27874, 27874, 27873, false), // 18997
			PINK_TOP_HAT(1048, 27888, 27888, 27887, false), // 18998
			RED_WINGED_CAPE(19709, 27896, 27896, 27897, false), // 18999
			GUITAR(18351, 49026, 49026, 49026, true), // 19000
			JESTER_SWORD(18351, 49101, 49101, 49102, true), // 19001
			NOOB_GODSWORD(18351, 5000, 5000, 5001, true), // 19002
			FORGOTTEN_LONGSWORD(18351, 5002, 5002, 5003, true), // 19003
			ROYAL_BLADE(18351, 50065, 50065, 50066, true), // 19004
			AKIMBO_DAGGERS(4747, 50134, 50134, 50135, true), // 19005
			FLESH_LONGSWORD(18351, 50224, 50224, 50223, true), // 19006
			ARMADYL_LONGSWORD(18351, 50227, 50227, 50226, true), // 19007
			DRAGONBONE_SCIMITAR(18351, 50228, 50228, 50228, true), // 19008
			TEST_SCIMITAR(18351, 50941, 50941, 50942, true), // 19009
			DRAGONBONE_CAPE(19709, 50947, 50947, 50946, false), // 19010
			BLAZED_DEATH_CAPE(19709, 5167, 5167, 5166, false), // 19011
			BLUE_DEATH_CAPE(19709, 5168, 5169, 5169, false), // 19012
			BUMBLE_PLATEBODY(18944, 54171, 54171, 56539, false), // 19013
			BUMBLE_PLATELEGS(18943, 54012, 54012, 55818, false), // 19014
			BUMBLE_SHIELD(8714, 54445, 54445, 56975, false), // 19015
			STRIPED_PARTYHAT(1048, 58001, 58001, 58000, false), // 19016
			MULTICOLORED_PARTYHAT(1048, 58005, 58005, 58004, false), // 19017
			COLORFUL_SANTA_HAT(1050, 58013, 58013, 58012, false), // 19018
			GOLD_TRIMMED_WINGS(19709, 58025, 58025, 58024, false), // 19019
			PURPLE_WINGS(19709, 58028, 58028, 58027, false), // 19020
			RASTA_WINGS(19709, 58034, 58034, 58033, false), // 19021
			COLORFUL_WHIP(18351, 58051, 58051, 58050, true), // 19022
			GOLDEN_CLAWS(14484, 62000, 62000, 62001, true), // 19023
			SPARTA_PLATEBODY(18944, 62767, 62767, 90052, false), // 19024
			SPARTA_HELM(18945, 62771, 62771, 62770, false), // 19025
			SPARTA_PLATELEGS(18943, 62773, 62773, 62772, false), // 19026
			SPARTA_SHIELD(8714, 62775, 62775, 62774, false), // 19027
			TALIS_SWORD(18351, 62769, 62769, 62788, true), // 19028
			GRIM_PLATEBODY(18944, 62832, 62832, 62831, false), // 19029
			GRIM_PLATELEGS(18943, 62833, 62833, 62930, false), // 19030
			GRIM_HELM(18945, 62835, 62835, 62834, false), // 19031
			GRIM_BOOTS(11732, 62837, 62837, 62836, false), // 19032
			GRIM_GLOVES(1059, 62839, 62839, 62838, false), // 19033
			METALIC_PLATEBODY(18944, 62847, 62847, 62846, false), // 19034
			METALIC_PLATELEGS(18943, 62841, 62841, 62840, false), // 19035
			METALIC_HELM(18945, 62849, 62849, 62848, false), // 19036
			CARVED_PLATEBODY(18944, 62853, 62853, 62852, false), // 19037
			CARVED_PLATELEGS(18943, 62855, 62855, 62854, false), // 19038
			CARVED_HELM(18945, 62857, 62857, 62856, false), // 19039
			BLUE_GLASS_WINGS(19709, 64057, 64057, 64058, false), // 19040
			GREEN_GLASS_WINGS(19709, 64059, 64059, 64060, false), // 19041
			RED_GLASS_WINGS(19709, 64061, 64061, 64062, false), // 19042
			YELLOW_GLASS_WINGS(19709, 64063, 64063, 64064, false), // 19043
			BLACK_GLASS_WINGS(19709, 64065, 64065, 64066, false), // 19044
			GODLY_WINGS(19709, 665, 665, 666, false), // 109045
			BLING_CHAIN(19709, 68989, 68989, 68990, false), // 19046
			SUPERMAN_LEGS(18943, 71959, 71959, 71958, false), // 19047
			SUPERMAN_BODY(18944, 71962, 71962, 71960, false), // 19048
			SUPERMAN_BOOTS(11732, 71961, 71961, 71961, false), // 19049
			AMERICAN_PARTYHAT_V2(1048, 74006, 74006, 74007, false), // 19050
			RETRO_PARTYHAT(1048, 74009, 74009, 74008, false), // 19051
			STRIPED_PARTYHAT_V2(1048, 74011, 74011, 74010, false), // 19052
			ZOMBIE_PARTYHAT(1048, 74014, 74014, 74015, false), // 19053
			SUPERMAN_CAPE(19709, 75072, 75072, 75072, false), // 19054
			ICY_KATANA(18351, 75500, 75500, 75500, true), // 19055
			PISTOL(9185, 75501, 75501, 75502, true), // 19056
			GOOGLE_CHROME_SWORD(18351, 75878, 75878, 75878, true), // 19057
			COLORFUL_SHIELD(8714, 7772, 7772, 7772, false), // 19058
			WHOOPASS_CAPE(19709, 81, 81, 80, false), // 19059
			ONE_HELM(19709, 82948, 82948, 82948, false), // 19060
			ONE_PLATEBODY(18944, 82949, 82949, 82949, false), // 19061
			TRON_SHIELD(8714, 82990, 82990, 82991, false), // 19062
			LEGACY_SHIELD(8714, 82992, 82992, 82993, false), // 19063
			GRIM_SHIELD(8714, 82994, 82994, 82995, false), // 19064
			STRIPED_WINGS(19709, 83010, 83010, 83011, false), // 19065
			ANGELIC_WINGS(19709, 83012, 83012, 83013, false), // 19066
			ENERGY_SWORD(18351, 8393, 8393, 8393, true), // 19067
			COLORFUL_PARTYHAT(1048, 9001, 9001, 9000, false), // 19068
			ZARYTE_BOW(861, 62692, 62750, 62750, true),  // 19069
			BRONZE_PARTYHAT(1038, new int[] { 926, 0, 0, 0 }, new int[] { 6028, 0, 0, 0 }),  // 19070
			STEEL_PARTYHAT(1038, new int[] { 926, 0, 0, 0 }, new int[] { 61, 0, 0, 0 }),  // 19071
			BLACK_PARTYHAT(1038, new int[] { 926, 0, 0, 0 }, new int[] { 3, 0, 0, 0 }),  // 19072
			MITHRIL_PARTYHAT(1038, new int[] { 926, 0, 0, 0 }, new int[] { 43297, 0, 0, 0 }),  // 19073
			ADAMANT_PARTYHAT(1038, new int[] { 926, 0, 0, 0 }, new int[] { 21662, 0, 0, 0 }),  // 19074
			RUNE_PARTYHAT(1038, new int[] { 926, 0, 0, 0 }, new int[] { 36252, 0, 0, 0 }),  // 19075
			BARROWS_PARTYHAT(1038, new int[] { 926, 0, 0, 0 }, new int[] { 10388, 0, 0, 0 }),  // 19076
			LIME_PARTYHAT(1038, new int[] { 926, 0, 0, 0 }, new int[] { 17350, 0, 0, 0 }),  // 19077
			LAVA_PARTYHAT(1038, new int[] { 926, 0, 0, 0 }, new int[] { 6073, 0, 0, 0 }),  // 19078
			
			
			;

			private int copy;
			private int inventory;
			private int female;
			private int male;
			private boolean weapon;
			private int[] editedModelColor;
			private int[] originalModelColor;
			private boolean copyDef;
			
			CustomItems(int copy, int model, boolean weapon) {
				this.setCopy(copy);
				this.setInventory(model);
				this.setFemale(model);
				this.setMale(model);
				this.setWeapon(weapon);
			}
			
			CustomItems(int copy, int inventory, int wield, boolean weapon) {
				this.setCopy(copy);
				this.setInventory(inventory);
				this.setFemale(wield);
				this.setMale(wield);
				this.setWeapon(weapon);
			}

			CustomItems(int copy, int inventory, int female, int male, boolean weapon) {
				this.setCopy(copy);
				this.setInventory(inventory);
				this.setFemale(female);
				this.setMale(male);
				this.setWeapon(weapon);
			}
			
			CustomItems(int copy, int[] editedModelColor, int[] originalModelColor) {
				setCopyDef(true);
				this.setCopy(copy);
				this.editedModelColor = editedModelColor;
				this.originalModelColor = originalModelColor;
			}

			public int getCopy() {
				return copy;
			}

			public void setCopy(int copy) {
				this.copy = copy;
			}

			public int getInventory() {
				return inventory;
			}

			public void setInventory(int inventory) {
				this.inventory = inventory;
			}

			public int getFemale() {
				return female;
			}

			public void setFemale(int female) {
				this.female = female;
			}

			public int getMale() {
				return male;
			}

			public void setMale(int male) {
				this.male = male;
			}

			public boolean isWeapon() {
				return weapon;
			}

			public void setWeapon(boolean weapon) {
				this.weapon = weapon;
			}

			public int[] getEditedModelColor() {
				return editedModelColor;
			}

			public void setEditedModelColor(int[] editedModelColor) {
				this.editedModelColor = editedModelColor;
			}

			public int[] getOriginalModelColor() {
				return originalModelColor;
			}

			public void setOriginalModelColor(int[] originalModelColor) {
				this.originalModelColor = originalModelColor;
			}

			public boolean isCopyDef() {
				return copyDef;
			}

			public void setCopyDef(boolean copyDef) {
				this.copyDef = copyDef;
			}
		}
		public static String ucFirst(String str) {
			str = str.toLowerCase().replaceAll("_", " ");
			if (str.length() > 1) {
				str = str.substring(0, 1).toUpperCase() + str.substring(1);
			} else {
				return str.toUpperCase();
			}
			return str;
		}
	    
	    public static ItemDef forID(int i) {
	        for (int j = 0; j < 10; j++) {
	            if (cache[j].id == i) {
	                return cache[j];
	            }
	        }
	        cacheIndex = (cacheIndex + 1) % 10;
	        ItemDef itemDef = cache[cacheIndex];
	        if (i >= streamIndices.length) {
	            itemDef.id = 1;
	            itemDef.setDefaults();
	            return itemDef;
	        }
	        stream.currentOffset = streamIndices[i];
	        itemDef.id = i;
	        itemDef.setDefaults();
	        itemDef.readValues(stream);
	        if (itemDef.certTemplateID != -1) {
	            itemDef.toNote();
	        }
	        if (itemDef.lentItemID != -1) {
	            itemDef.toLend();
	        }
	        if (itemDef.id == i && itemDef.editedModelColor == null) {
	            itemDef.editedModelColor = new int[1];
	            itemDef.newModelColor = new int[1];
	            itemDef.editedModelColor[0] = 0;
	            itemDef.newModelColor[0] = 1;
	        }
	        if (untradeableItems.contains(itemDef.id)) {
	            itemDef.untradeable = true;
	        }
	        itemDef.value = prices[itemDef.id];
	        int custom_start = 18888;
			//System.out.println("Custom items: "+CustomItems.values().length);	
			for (CustomItems custom : CustomItems.values()) {
				if (i == custom_start + custom.ordinal()) {
					itemDef = copyRotations(itemDef, custom.getCopy());
					itemDef.name = ucFirst(custom.name());
					if(custom.isCopyDef()) {
						ItemDef def2 = ItemDef.forID(custom.getCopy());
						itemDef.modelID = def2.modelID;
						itemDef.maleWearId = def2.maleWearId;
						itemDef.femaleWearId = def2.femaleWearId;
						itemDef.editedModelColor = custom.editedModelColor;
						itemDef.newModelColor = custom.originalModelColor;
					} else {
						itemDef.modelID = custom.getInventory();
						itemDef.maleWearId = custom.getMale();
						itemDef.femaleWearId = custom.getFemale();
					}
	                    itemDef.actions = new String[5];
	                    itemDef.actions[1] = custom.isWeapon() ? "Wield" : "Wear";
				}
			}

			itemDef = ItemDef_Jarrod_1.forDef(itemDef, i);
			itemDef.createGroundName();
	        return itemDef;
	    }

	private void createGroundName() {
	    	if (name == null)
	    		return;
	    	if (!name.contains("@"))
	    		return;
	    	groundName = name.replaceAll("@.{3}@", "");
	}

	private void readValues(Stream stream) {
	        do {
	            int i = stream.readUnsignedByte();
	            if (i == 0) {
	                return;
	            }
	            if (i == 1) {
	                modelID = stream.readUnsignedWord();
	            } else if (i == 2) {
	                name = stream.readString();
	            } else if (i == 3) {
	                description = stream.readString();
	            } else if (i == 4) {
	                modelZoom = stream.readUnsignedWord();
	            } else if (i == 5) {
					modelRotationY = stream.readUnsignedWord();
	            } else if (i == 6) {
					modelRotationX = stream.readUnsignedWord();
	            } else if (i == 7) {
	                modelOffset1 = stream.readUnsignedWord();
	                if (modelOffset1 > 32767) {
	                    modelOffset1 -= 0x10000;
	                }
	            } else if (i == 8) {
	                modelOffsetY = stream.readUnsignedWord();
	                if (modelOffsetY > 32767) {
	                    modelOffsetY -= 0x10000;
	                }
	            } else if (i == 10) {
	                stream.readUnsignedWord();
	            } else if (i == 11) {
	                stackable = true;
	            } else if (i == 12) {
	                value = stream.readUnsignedWord();
	            } else if (i == 16) {
	                membersObject = true;
	            } else if (i == 23) {
					maleWearId = stream.readUnsignedWord();
					maleWieldY = stream.readSignedByte();
	            } else if (i == 24) {
	                maleEquip2 = stream.readUnsignedWord();
	            } else if (i == 25) {
					femaleWearId = stream.readUnsignedWord();
					femaleWieldY = stream.readSignedByte();
	            } else if (i == 26) {
	                femaleEquip2 = stream.readUnsignedWord();
	            } else if (i >= 30 && i < 35) {
	                if (groundActions == null) {
	                    groundActions = new String[5];
	                }
	                groundActions[i - 30] = stream.readString();
	                if (groundActions[i - 30].equalsIgnoreCase("hidden")) {
	                    groundActions[i - 30] = null;
	                }
	            } else if (i >= 35 && i < 40) {
	                if (actions == null) {
	                    actions = new String[5];
	                }
	                actions[i - 35] = stream.readString();
	                if (actions[i - 35].equalsIgnoreCase("null")) {
	                    actions[i - 35] = null;
	                }
	            } else if (i == 40) {
	                int j = stream.readUnsignedByte();
	                editedModelColor = new int[j];
	                newModelColor = new int[j];
	                for (int k = 0; k < j; k++) {
	                    editedModelColor[k] = stream.readUnsignedWord();
	                    newModelColor[k] = stream.readUnsignedWord();
	                }
	            } else if (i == 78) {
	                maleEquip3 = stream.readUnsignedWord();
	            } else if (i == 79) {
	                femaleEquip3 = stream.readUnsignedWord();
	            } else if (i == 90) {
	                maleDialogue = stream.readUnsignedWord();
	            } else if (i == 91) {
	                femaleDialogue = stream.readUnsignedWord();
	            } else if (i == 92) {
	                maleDialogueModel = stream.readUnsignedWord();
	            } else if (i == 93) {
	                femaleDialogueModel = stream.readUnsignedWord();
	            } else if (i == 95) {
	                modelOffsetX = stream.readUnsignedWord();
	            } else if (i == 97) {
	                certID = stream.readUnsignedWord();
	            } else if (i == 98) {
	                certTemplateID = stream.readUnsignedWord();
	            } else if (i >= 100 && i < 110) {
	                if (stackIDs == null) {
	                    stackIDs = new int[10];
	                    stackAmounts = new int[10];
	                }
	                stackIDs[i - 100] = stream.readUnsignedWord();
	                stackAmounts[i - 100] = stream.readUnsignedWord();
	            } else if (i == 110) {
	                sizeX = stream.readUnsignedWord();
	            } else if (i == 111) {
	                sizeY = stream.readUnsignedWord();
	            } else if (i == 112) {
	                sizeZ = stream.readUnsignedWord();
	            } else if (i == 113) {
	                shadow = stream.readSignedByte();
	            } else if (i == 114) {
	                lightness = stream.readSignedByte() * 5;
	            } else if (i == 115) {
	                team = stream.readUnsignedByte();
	            } else if (i == 116) {
	                lendID = stream.readUnsignedWord();
	            } else if (i == 117) {
	                lentItemID = stream.readUnsignedWord();
	            }
	        } while (true);
	    }

	    public static void setSettings() {
	        try {
	            prices = new int[22694];
	            int index = 0;
	            for (String line : Files.readAllLines(Paths.get(signlink.findcachedir() + "data/data.txt"), Charset.defaultCharset())) {
	                prices[index] = Integer.parseInt(line);
	                index++;
	            }
	            for (int i : UNTRADEABLE_ITEMS) {
	                untradeableItems.add(i);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    public void toNote() {
	        ItemDef itemDef = forID(certTemplateID);
	        modelID = itemDef.modelID;
	        modelZoom = itemDef.modelZoom;
	        modelRotationY = itemDef.modelRotationY;
	        modelRotationX = itemDef.modelRotationX;
	        modelOffsetX = itemDef.modelOffsetX;
	        modelOffset1 = itemDef.modelOffset1;
	        modelOffsetY = itemDef.modelOffsetY;
	        editedModelColor = itemDef.editedModelColor;
	        newModelColor = itemDef.newModelColor;
	        ItemDef itemDef_1 = forID(certID);
	        name = itemDef_1.name;
	        membersObject = itemDef_1.membersObject;
	        value = itemDef_1.value;
	        String s = "a";
	        char c = itemDef_1.name.charAt(0);
	        if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
	            s = "an";
	        }
	        description = ("Swap this note at any bank for " + s + " " + itemDef_1.name + ".");
	        stackable = true;
	    }

	    private void toLend() {
	        ItemDef itemDef = forID(lentItemID);
	        actions = new String[5];
	        modelID = itemDef.modelID;
	        modelOffset1 = itemDef.modelOffset1;
	        modelRotationX = itemDef.modelRotationX;
	        modelOffsetY = itemDef.modelOffsetY;
	        modelZoom = itemDef.modelZoom;
	        modelRotationY = itemDef.modelRotationY;
	        modelOffsetX = itemDef.modelOffsetX;
	        value = 0;
	        ItemDef itemDef_1 = forID(lendID);
	        maleDialogueModel = itemDef_1.maleDialogueModel;
	        editedModelColor = itemDef_1.editedModelColor;
	        clorDefcode= itemDef_1.clorDefcode;
	        clorDefcodeTo= itemDef_1.clorDefcodeTo;
	        maleEquip3 = itemDef_1.maleEquip3;
	        maleEquip2 = itemDef_1.maleEquip2;
	        femaleDialogueModel = itemDef_1.femaleDialogueModel;
	        maleDialogue = itemDef_1.maleDialogue;
	        groundActions = itemDef_1.groundActions;
	        maleWearId = itemDef_1.maleWearId;
	        name = itemDef_1.name;
	        femaleWearId = itemDef_1.femaleWearId;
	        membersObject = itemDef_1.membersObject;
	        femaleDialogue = itemDef_1.femaleDialogue;
	        femaleEquip2 = itemDef_1.femaleEquip2;
	        femaleEquip3 = itemDef_1.femaleEquip3;
	        newModelColor = itemDef_1.newModelColor;
	        team = itemDef_1.team;
	        if (itemDef_1.actions != null) {

	                for (int i_33_ = 0; i_33_ < 4; i_33_++) {
	                    actions[i_33_] = itemDef_1.actions[i_33_];
	            }
	        }
	        actions[4] = "Discard";
	    }

	    public static Sprite getSprite(int i, int j, int k, int zoom) {
	        if (k == 0 && zoom != -1) {
	            Sprite sprite = (Sprite) spriteCache.get(i);
	            if (sprite != null && sprite.maxHeight != j && sprite.maxHeight != -1) {
	                sprite.unlink();
	                sprite = null;
	            }
	            if (sprite != null) {
	                return sprite;
	            }
	        }
	        ItemDef itemDef = forID(i);
	        if (itemDef.stackIDs == null) {
	            j = -1;
	        }
	        if (j > 1) {
	            int i1 = -1;
	            for (int j1 = 0; j1 < 10; j1++) {
	                if (j >= itemDef.stackAmounts[j1] && itemDef.stackAmounts[j1] != 0) {
	                    i1 = itemDef.stackIDs[j1];
	                }
	            }

	            if (i1 != -1) {
	                itemDef = forID(i1);
	            }
	        }
	        Model model = itemDef.getItemModelFinalised(1);
	        if (model == null) {
	            return null;
	        }
	        Sprite sprite = null;
	        if (itemDef.certTemplateID != -1) {
	            sprite = getSprite(itemDef.certID, 10, -1);
	            if (sprite == null) {
	                return null;
	            }
	        }
	        if (itemDef.lendID != -1) {
	            sprite = getSprite(itemDef.lendID, 50, 0);
	            if (sprite == null) {
	                return null;
	            }
	        }
	        Sprite sprite2 = new Sprite(32, 32);
	        int k1 = Rasterizer.center_x;
	        int l1 = Rasterizer.center_y;
	        int ai[] = Rasterizer.lineOffsets;
	        int ai1[] = DrawingArea.pixels;
	        int i2 = DrawingArea.width;
	        int j2 = DrawingArea.height;
	        int k2 = DrawingArea.topX;
	        int l2 = DrawingArea.bottomX;
	        int i3 = DrawingArea.topY;
	        int j3 = DrawingArea.bottomY;
	        Rasterizer.notTextured = false;
	        DrawingArea.initDrawingArea(32, 32, sprite2.myPixels);
	        DrawingArea.drawPixels(32, 0, 0, 0, 32);
	        Rasterizer.setDefaultBounds();
	        int k3 = itemDef.modelZoom;
	        if (zoom != -1 && zoom != 0) {
	            k3 = (itemDef.modelZoom * 100) / zoom;
	        }
	        if (k == -1) {
	            k3 = (int) ((double) k3 * 1.5D);
	        }
	        if (k > 0) {
	            k3 = (int) ((double) k3 * 1.04D);
	        }
	        int l3 = Rasterizer.SINE[itemDef.modelRotationY] * k3 >> 16;
	        int i4 = Rasterizer.COSINE[itemDef.modelRotationY] * k3 >> 16;
	        model.renderSingle(itemDef.modelRotationX, itemDef.modelOffsetX, itemDef.modelRotationY, itemDef.modelOffset1, l3 + model.modelHeight / 2 + itemDef.modelOffsetY, i4 + itemDef.modelOffsetY);
	        for (int i5 = 31; i5 >= 0; i5--) {
	            for (int j4 = 31; j4 >= 0; j4--) {
	                if (sprite2.myPixels[i5 + j4 * 32] != 0) {
	                    continue;
	                }
	                if (i5 > 0 && sprite2.myPixels[(i5 - 1) + j4 * 32] > 1) {
	                    sprite2.myPixels[i5 + j4 * 32] = 1;
	                    continue;
	                }
	                if (j4 > 0 && sprite2.myPixels[i5 + (j4 - 1) * 32] > 1) {
	                    sprite2.myPixels[i5 + j4 * 32] = 1;
	                    continue;
	                }
	                if (i5 < 31 && sprite2.myPixels[i5 + 1 + j4 * 32] > 1) {
	                    sprite2.myPixels[i5 + j4 * 32] = 1;
	                    continue;
	                }
	                if (j4 < 31 && sprite2.myPixels[i5 + (j4 + 1) * 32] > 1) {
	                    sprite2.myPixels[i5 + j4 * 32] = 1;
	                }
	            }

	        }

	        if (k > 0) {
	            for (int j5 = 31; j5 >= 0; j5--) {
	                for (int k4 = 31; k4 >= 0; k4--) {
	                    if (sprite2.myPixels[j5 + k4 * 32] != 0) {
	                        continue;
	                    }
	                    if (j5 > 0 && sprite2.myPixels[(j5 - 1) + k4 * 32] == 1) {
	                        sprite2.myPixels[j5 + k4 * 32] = k;
	                        continue;
	                    }
	                    if (k4 > 0 && sprite2.myPixels[j5 + (k4 - 1) * 32] == 1) {
	                        sprite2.myPixels[j5 + k4 * 32] = k;
	                        continue;
	                    }
	                    if (j5 < 31 && sprite2.myPixels[j5 + 1 + k4 * 32] == 1) {
	                        sprite2.myPixels[j5 + k4 * 32] = k;
	                        continue;
	                    }
	                    if (k4 < 31 && sprite2.myPixels[j5 + (k4 + 1) * 32] == 1) {
	                        sprite2.myPixels[j5 + k4 * 32] = k;
	                    }
	                }

	            }

	        } else if (k == 0) {
	            for (int k5 = 31; k5 >= 0; k5--) {
	                for (int l4 = 31; l4 >= 0; l4--) {
	                    if (sprite2.myPixels[k5 + l4 * 32] == 0 && k5 > 0 && l4 > 0 && sprite2.myPixels[(k5 - 1) + (l4 - 1) * 32] > 0) {
	                        sprite2.myPixels[k5 + l4 * 32] = 0x302020;
	                    }
	                }

	            }

	        }
	        if (itemDef.certTemplateID != -1) {
	            int l5 = sprite.maxWidth;
	            int j6 = sprite.maxHeight;
	            sprite.maxWidth = 32;
	            sprite.maxHeight = 32;
	            sprite.drawSprite(0, 0);
	            sprite.maxWidth = l5;
	            sprite.maxHeight = j6;
	        }
	        if (itemDef.lendID != -1) {
	            int l5 = sprite.maxWidth;
	            int j6 = sprite.maxHeight;
	            sprite.maxWidth = 32;
	            sprite.maxHeight = 32;
	            sprite.drawSprite(0, 0);
	            sprite.maxWidth = l5;
	            sprite.maxHeight = j6;
	        }
	        if (k == 0) {
	            spriteCache.put(sprite2, i);
	        }
	        DrawingArea.initDrawingArea(j2, i2, ai1);
	        DrawingArea.setDrawingArea(j3, k2, l2, i3);
	        Rasterizer.center_x = k1;
	        Rasterizer.center_y = l1;
	        Rasterizer.lineOffsets = ai;
	        Rasterizer.notTextured = true;
	        sprite2.maxWidth = itemDef.stackable ? 33 : 32;
	        sprite2.maxHeight = j;
	        return sprite2;
	    }

	    public static void printItem(int mobId) {
	        /*Print out Grain*/
	        ItemDef dump = ItemDef.forID(mobId);
	        if (dump.name != null) {
	            System.out.println("Dumping: "+dump.name);
	        } else {
	            System.out.println("MobDefinition.get("+mobId+").name == null");
	        }
	                System.out.println("Male Model1: " + dump.maleWearId);
	                System.out.println("Male Model2: " + dump.maleEquip2);
	                System.out.println("Male Model3: " + dump.maleEquip3);
	                System.out.println("Female Model1: " + dump.femaleWearId);
	                System.out.println("Female Model2: " + dump.femaleEquip2);
	                System.out.println("Female Model3: " + dump.femaleEquip3);
	    }

	    public static Sprite getSprite(int i, int j, int k) {

	        ItemDef itemDef = forID(i);
	        if (itemDef.stackIDs == null) {
	            j = -1;
	        }
	        if (j > 1) {
	            int i1 = -1;
	            for (int j1 = 0; j1 < 10; j1++) {
	                if (j >= itemDef.stackAmounts[j1] && itemDef.stackAmounts[j1] != 0) {
	                    i1 = itemDef.stackIDs[j1];
	                }
	            }
	            if (i1 != -1) {
	                itemDef = forID(i1);
	            }
	        }
	        Model model = itemDef.getItemModelFinalised(1);
	        if (model == null) {
	            return null;
	        }
	        Sprite sprite = null;
	        if (itemDef.certTemplateID != -1) {
	            sprite = getSprite(itemDef.certID, 10, -1);
	            if (sprite == null) {
	                return null;
	            }
	        }
	        if (itemDef.lentItemID != -1) {
	            sprite = getSprite(itemDef.lendID, 50, 0);
	            if (sprite == null) {
	                return null;
	            }
	        }
	        Sprite sprite2 = new Sprite(32, 32);
	        int k1 = Rasterizer.center_x;
	        int l1 = Rasterizer.center_y;
	        int ai[] = Rasterizer.lineOffsets;
	        int ai1[] = DrawingArea.pixels;
	        int i2 = DrawingArea.width;
	        int j2 = DrawingArea.height;
	        int k2 = DrawingArea.topX;
	        int l2 = DrawingArea.bottomX;
	        int i3 = DrawingArea.topY;
	        int j3 = DrawingArea.bottomY;
	        Rasterizer.notTextured = false;
	        DrawingArea.initDrawingArea(32, 32, sprite2.myPixels);
	        DrawingArea.drawPixels(32, 0, 0, 0, 32);
	        Rasterizer.setDefaultBounds();
	        int k3 = itemDef.modelZoom;
	        if (k == -1) {
	            k3 = (int) ((double) k3 * 1.5D);
	        }
	        if (k > 0) {
	            k3 = (int) ((double) k3 * 1.04D);
	        }
	        int l3 = Rasterizer.SINE[itemDef.modelRotationY] * k3 >> 16;
	        int i4 = Rasterizer.COSINE[itemDef.modelRotationY] * k3 >> 16;
	        model.renderSingle(itemDef.modelRotationX, itemDef.modelOffsetX, itemDef.modelRotationY, itemDef.modelOffset1, l3 + model.modelHeight / 2 + itemDef.modelOffsetY, i4 + itemDef.modelOffsetY);
	        for (int i5 = 31; i5 >= 0; i5--) {
	            for (int j4 = 31; j4 >= 0; j4--) {
	                if (sprite2.myPixels[i5 + j4 * 32] == 0) {
	                    if (i5 > 0 && sprite2.myPixels[(i5 - 1) + j4 * 32] > 1) {
	                        sprite2.myPixels[i5 + j4 * 32] = 1;
	                    } else if (j4 > 0 && sprite2.myPixels[i5 + (j4 - 1) * 32] > 1) {
	                        sprite2.myPixels[i5 + j4 * 32] = 1;
	                    } else if (i5 < 31 && sprite2.myPixels[i5 + 1 + j4 * 32] > 1) {
	                        sprite2.myPixels[i5 + j4 * 32] = 1;
	                    } else if (j4 < 31 && sprite2.myPixels[i5 + (j4 + 1) * 32] > 1) {
	                        sprite2.myPixels[i5 + j4 * 32] = 1;
	                    }
	                }
	            }
	        }
	        if (k > 0) {
	            for (int j5 = 31; j5 >= 0; j5--) {
	                for (int k4 = 31; k4 >= 0; k4--) {
	                    if (sprite2.myPixels[j5 + k4 * 32] == 0) {
	                        if (j5 > 0 && sprite2.myPixels[(j5 - 1) + k4 * 32] == 1) {
	                            sprite2.myPixels[j5 + k4 * 32] = k;
	                        } else if (k4 > 0 && sprite2.myPixels[j5 + (k4 - 1) * 32] == 1) {
	                            sprite2.myPixels[j5 + k4 * 32] = k;
	                        } else if (j5 < 31 && sprite2.myPixels[j5 + 1 + k4 * 32] == 1) {
	                            sprite2.myPixels[j5 + k4 * 32] = k;
	                        } else if (k4 < 31 && sprite2.myPixels[j5 + (k4 + 1) * 32] == 1) {
	                            sprite2.myPixels[j5 + k4 * 32] = k;
	                        }
	                    }
	                }
	            }
	        } else if (k == 0) {
	            for (int k5 = 31; k5 >= 0; k5--) {
	                for (int l4 = 31; l4 >= 0; l4--) {
	                    if (sprite2.myPixels[k5 + l4 * 32] == 0 && k5 > 0 && l4 > 0 && sprite2.myPixels[(k5 - 1) + (l4 - 1) * 32] > 0) {
	                        sprite2.myPixels[k5 + l4 * 32] = 0x302020;
	                    }
	                }
	            }
	        }
	        if (itemDef.certTemplateID != -1) {
	            int l5 = sprite.maxWidth;
	            int j6 = sprite.maxHeight;
	            sprite.maxWidth = 32;
	            sprite.maxHeight = 32;
	            sprite.drawSprite(0, 0);
	            sprite.maxWidth = l5;
	            sprite.maxHeight = j6;
	        }
	        if (itemDef.lentItemID != -1) {
	            int l5 = sprite.maxWidth;
	            int j6 = sprite.maxHeight;
	            sprite.maxWidth = 32;
	            sprite.maxHeight = 32;
	            sprite.drawSprite(0, 0);
	            sprite.maxWidth = l5;
	            sprite.maxHeight = j6;
	        }
	        if (k == 0) {
	            spriteCache.put(sprite2, i);
	        }
	        DrawingArea.initDrawingArea(j2, i2, ai1);
	        DrawingArea.setDrawingArea(j3, k2, l2, i3);
	        Rasterizer.center_x = k1;
	        Rasterizer.center_y = l1;
	        Rasterizer.lineOffsets = ai;
	        Rasterizer.notTextured = true;
	        if (itemDef.stackable) {
	            sprite2.maxWidth = 33;
	        } else {
	            sprite2.maxWidth = 32;
	        }
	        sprite2.maxHeight = j;
	        return sprite2;
	    }

	    public Model getItemModelFinalised(int amount) {
	        if (stackIDs != null && amount > 1) {
	            int stackId = -1;
	            for (int k = 0; k < 10; k++) {
	                if (amount >= stackAmounts[k] && stackAmounts[k] != 0) {
	                    stackId = stackIDs[k];
	                }
	            }
	            if (stackId != -1) {
	                return forID(stackId).getItemModelFinalised(1);
	            }
	            
	        }
	        Model model = (Model) modelCache.get(id);
	        if (model != null) {
	            return model;
	        }
	        model = Model.fetchModel(modelID);
	        if (model == null) {
	            return null;
	        }
	        if (sizeX != 128 || sizeY != 128 || sizeZ != 128) {
	            model.scaleT(sizeX, sizeZ, sizeY);
	        }
	        if (editedModelColor != null) {
				for (int l = 0; l < editedModelColor.length; l++) {
					model.recolour(editedModelColor[l], newModelColor[l]);
				}
			}

	        if (colourRedefine > 0)
	    		model.method1337(colourRedefine);
	    		if (colourRedefine2 != 0)
	    		model.method1338(colourRedefine2);
	    		if (colourRedefine3 != 0)
	    		model.method1339(colourRedefine3);
	    		modelCache.put(model, id);

			applyTexturing(model, id);
			
			model.light(64 + shadow, 768 + lightness, -50, -10, -50, true);
	        model.rendersWithinOneTile = true;
	        modelCache.put(model, id);
	        return model;
	    }
	    
	    public static String itemModels(int itemID) {
	    	int inv = forID(itemID).modelID;
	    	int male = forID(itemID).maleWearId;
	    	int female = forID(itemID).femaleWearId;
	    	String name = forID(itemID).name;
	    	return "<col=225>"+name+"</col> (<col=800000000>"+itemID+"</col>) - [inv: <col=800000000>"+inv+"</col>] - [male: <col=800000000>"+male+"</col>] - [female: <col=800000000>"+female+"</col>]";
	    }

	    public Model getItemModel(int i) {
	        if (stackIDs != null && i > 1) {
	            int j = -1;
	            for (int k = 0; k < 10; k++) {
	                if (i >= stackAmounts[k] && stackAmounts[k] != 0) {
	                    j = stackIDs[k];
	                }
	            }
	            if (j != -1) {
	                return forID(j).getItemModel(1);
	            }
	        }
	        Model model = Model.fetchModel(modelID);
	        if (model == null) {
	            return null;
	        }
	        if (editedModelColor != null) {
	            for (int l = 0; l < editedModelColor.length; l++) {
	                model.recolour(editedModelColor[l], newModelColor[l]);
	            }
	        }

	        if (colourRedefine > 0)
	    		model.method1337(colourRedefine);
	    		if (colourRedefine2 != 0)
	    		model.method1338(colourRedefine2);
	    		if (colourRedefine3 != 0)
	    		model.method1339(colourRedefine3);
			applyTexturing(model, id);
			return model;
	    }

	    public static final int[] UNTRADEABLE_ITEMS
	            = {13661, 13262,
	                6529, 6950, 1464, 2996, 2677, 2678, 2679, 2680, 2682,
	                2683, 2684, 2685, 2686, 2687, 2688, 2689, 2690,
	                6570, 12158, 12159, 12160, 12163, 12161, 12162,
	                19143, 19149, 19146, 19157, 19162, 19152, 4155,
	                8850, 10551, 8839, 8840, 8842, 11663, 11664,
	                11665, 3842, 3844, 3840, 8844, 8845, 8846, 8847,
	                8848, 8849, 8850, 10551, 7462, 7461, 7460,
	                7459, 7458, 7457, 7456, 7455, 7454, 7453, 8839,
	                8840, 8842, 11663, 11664, 11665, 10499, 9748,
	                9754, 9751, 9769, 9757, 9760, 9763, 9802, 9808,
	                9784, 9799, 9805, 9781, 9796, 9793, 9775, 9772,
	                9778, 9787, 9811, 9766, 9749, 9755, 9752, 9770,
	                9758, 9761, 9764, 9803, 9809, 9785, 9800, 9806,
	                9782, 9797, 9794, 9776, 9773, 9779, 9788, 9812,
	                9767, 9747, 9753, 9750, 9768, 9756, 9759, 9762,
	                9801, 9807, 9783, 9798, 9804, 9780, 9795, 9792,
	                9774, 9771, 9777, 9786, 9810, 9765, 9948, 9949,
	                9950, 12169, 12170, 12171, 20671, 14641, 14642,
	                6188, 10954, 10956, 10958,
	                3057, 3058, 3059, 3060, 3061,
	                7594, 7592, 7593, 7595, 7596,
	                14076, 14077, 14081,
	                10840, 10836, 6858, 6859, 10837, 10838, 10839,
	                9925, 9924, 9923, 9922, 9921,
	                4084, 4565, 20046, 20044, 20045,
	                1050, 14595, 14603, 14602, 14605, 11789,
	                19708, 19706, 19707,
	                4860, 4866, 4872, 4878, 4884, 4896, 4890, 4896, 4902,
	                4932, 4938, 4944, 4950, 4908, 4914, 4920, 4926, 4956,
	                4926, 4968, 4994, 4980, 4986, 4992, 4998,
	                18778, 18779, 18780, 18781,
	                13450, 13444, 13405, 15502,
	                10548, 10549, 10550, 10551, 10555, 10552, 10553, 2412, 2413, 2414,
	                20747,
	                18365, 18373, 18371, 15246, 12964, 12971, 12978, 14017,
	                757, 8851, 3818,
	                13855, 13848, 13857, 13856, 13854, 13853, 13852, 13851, 13850, 5509, 13653, 14021, 14020, 19111, 14019, 14022,
	                19785, 19786, 18782, 18351, 18349, 18353, 18357, 18355, 18359, 18335
	            };
	    public ItemDef() {
	        id = -1;
	    }

	    public byte femaleWieldY;
	    public int value;
	    public int[] editedModelColor;
	    public int id;
	    public static MemCache spriteCache = new MemCache(100);
	    public static MemCache modelCache = new MemCache(50);
	    public int[] newModelColor;
	    public boolean membersObject;
	    public int femaleEquip3;
	    public int certTemplateID;
	    public int femaleEquip2;
	    public int maleWearId;
	    public int maleDialogueModel;
	    public int sizeX;
	    public String groundActions[];
	    public int modelOffset1;
	    public String name;
	    public static ItemDef[] cache;
	    public int femaleDialogueModel;
	    public int modelID;
	    public int maleDialogue;
	    public boolean stackable;
	    public String description;
	    public int certID;
	    public static int cacheIndex;
	    public int modelZoom;
	    public static Stream stream;
	    public int lightness;
	    public int maleEquip3;
	    public int maleEquip2;
	    public String actions[];
	  //  public int modelRotationY;
	    public int sizeZ;
	    public int sizeY;
	    public int[] stackIDs;
	    public int modelOffsetY;
	    public static int[] streamIndices;
	    public int shadow;
	    public int femaleDialogue;
	    public int modelRotationX;
	    public int femaleWearId;
	    public int[] stackAmounts;
	    public int team;
	    public static int totalItems;
	    public int modelOffsetX;
	    public byte maleWieldY;
	    public byte maleXOffset;
	    public int lendID;
	    public int lentItemID;
	    public boolean untradeable;
		public int modelOffset2;
		public int modelRotation1;
		public int modelRotation2;
		public int modelRotationY;
		public int rotationX;
		public int rotationY;
		public void setNonNoteable() {
			// TODO Auto-generated method stub
			
		}

		public void setNonStackable() {
			// TODO Auto-generated method stub
			
		}
	}