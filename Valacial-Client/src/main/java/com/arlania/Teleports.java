package com.arlania;


import static com.arlania.RSInterface.*;

public class Teleports {

    private static final int ORANGE = 0xFF8624;

    public static void unpack(TextDrawingArea[] tda) {
        int parentId = 23500;
        int id = parentId;
        RSInterface rs = addInterface(id++);
        int frame = 0;
        rs.totalChildren(80);

        //background sprite
        addSpriteLoader(id, 1372); //1048
        rs.child(frame++, id++, 15, 20);

        //close button
        addHoverButtonWSpriteLoader(id, 737, 16, 16, "Close Window", 0, id+1, 1);
        addHoveredImageWSpriteLoader(id+1, 738, 16, 16, id+2);
        rs.child(frame++, id, 470, 30);
        rs.child(frame++, id + 1, 470, 30);
        id += 3;

        //title
        addText(id, "Teleport Menu", tda, 2, ORANGE, true, true);
        rs.child(frame++, id++, 256, 30);

        //teleport button
        addHoverButtonWSpriteLoader(id, 1373/*1049*/, 75, 25, "Teleport", 0, id+1, 1);
        addHoveredImageWSpriteLoader(id+1, 1374/*1050*/, 75, 25, id+2);
        rs.child(frame++, id, 241, 275);
        rs.child(frame++, id + 1, 241, 275);
        id += 3;

        addText(id, "Teleport!", tda, 1, ORANGE, true, true);
        rs.child(frame++, id++, 278, 280);

        addText(id, "Teleports", tda, 2, ORANGE, true, true);
        rs.child(frame++, id++, 100, 83);

        addText(id, "Favourite", tda, 2, ORANGE, true, true);
        rs.child(frame++, id++, 433, 84);

        addNpc(id, 0);
        interfaceCache[id].height = 85; // cant overlap with the tele button or it breaks hover
        rs.child(frame++, id++, 210, 190);

        int maxFavourites = 10;
        for (int i = 0; i < maxFavourites; i++) {
            addConfigButtonWSpriteLoaderWithHover(id, parentId, i % 2 == 0 ? 1389/*1067*/ : 1390/*1066*/, i % 2 == 1 ? 1375/*1054*/ : 1376/*1053*/, 101, 20, "Select Favourite", i, 5, 1094, id + maxFavourites);
            rs.child(frame++, id++, 383, 101 + i * 20);
        }
        for (int i = 0; i < maxFavourites; i++) {
            addSingleHoverSprite(id, i % 2 == 1 ? 1375/*1054*/ : 1376/*1053*/, 101, 20);
            rs.child(frame++, id++, 383, 101 + i * 20);
        }
        for (int i = 0; i < maxFavourites; i++) {
            addText(id, "", tda, 0, ORANGE, false, true);
            rs.child(frame++, id++, 387, 105 + i * 20);
        }
        for (int i = 0; i < maxFavourites; i++) {
            addButtonWithHover(id, 1377/*1061*/, "Remove", 15, 14, id + maxFavourites);
            rs.child(frame++, id++, 472, 106 + i * 20);
        }
        for (int i = 0; i < maxFavourites; i++) {
            addSingleHoverSprite(id, 1378/*1062*/, 15, 14);
            rs.child(frame++, id++, 472, 106 + i * 20);
        }

        //Items
        rs.child(frame++, id, 180, 90);
        RSInterface.itemGroupAutoScroll(id, id+1, 20, 1, 8, 4, 5);
        interfaceCache[id].width = 196;
        interfaceCache[id].height = 42;
        id += 2;

        //category buttons
        String[] categoryNames = new String[] {"Monsters", "Bosses", "Minigames", "Global", "Ultra Bosses", "Skilling"};
        for (int i = 0; i < categoryNames.length; i++) {
            addConfigButtonWSpriteLoaderWithHover(id, parentId, 1379/*1051*/, 1380/*1065*/, 76, 20, "View " + categoryNames[i], i, 5, 1096, id + categoryNames.length);
            rs.child(frame++, id++, 25 + i * 77, 58);
        }
        for (int i = 0; i < categoryNames.length; i++) {
            addSingleHoverSprite(id, 1380/*1065*/, 76, 20);
            rs.child(frame++, id++, 25 + i * 77, 58);
        }
        for (int i = 0; i < categoryNames.length; i++) {
            addText(id, categoryNames[i], tda, 0, ORANGE, true, true);
            rs.child(frame++, id++, 63 + i * 77, 63);
        }

        teleScroll(tda, id);
        rs.child(frame++, id++, 27, 101);
    }

    private static void teleScroll(TextDrawingArea[] tda, int id) {
        int numOfTeles = 50;
        int frame = 0;
        int parent = id++;
        RSInterface rs = addInterface(parent);
        rs.width = 130;
        rs.height = 200;
        int heightSeparation = 20;
        rs.scrollMax = numOfTeles * heightSeparation;
        rs.totalChildren(numOfTeles * 5);

        for (int i = 0; i < numOfTeles; i++) {
            addConfigButtonWSpriteLoaderWithHover(id, parent, i % 2 == 0 ? 1381/*1055*/ : 1382/*1056*/, i % 2 == 0 ? 1383/*1063*/ : 1384/*1064*/, 146, 20, "Select", i, 5, 1095, id+numOfTeles);
            rs.child(frame++, id++, 0, i * heightSeparation);
        }
        for (int i = 0; i < numOfTeles; i++) {
            addSingleHoverSprite(id, i % 2 == 0 ? 1383/*1063*/ : 1384/*1064*/, 146, 20);
            rs.child(frame++, id++, 0, i * heightSeparation);
        }
        for (int i = 0; i < numOfTeles; i++) {
            addText(id, ""+id, tda, 0, ORANGE, false, true);
            rs.child(frame++, id++, 4, 5 + i * heightSeparation);
        }
        for (int i = 0; i < numOfTeles; i++) {
            addSpriteToggleButton(id, parent, 1385/*1059*/, 1386/*1060*/, 15, 14, "Toggle Favourite", 4, 1097+i, id + 50);
            rs.child(frame++, id++, 112, 3 + i * heightSeparation);
        }
        for (int i = 0; i < numOfTeles; i++) {
            addHoverImage2(id, 1387/*1058*/, 1388/*1057*/, 15, 14, 1097+i);
            rs.child(frame++, id++, 112, 3 + i * heightSeparation);
        }
    }
}
