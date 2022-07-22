package com.arlania;

import static com.arlania.RSInterface.*;

public class AccountCreation {

    private static final int ORANGE = 0xFF8624;
    private static final int ORANGE_FADED = 0xA15517;
    private static final int GREY = 0x858585;
    private static final int GREY_FADED = 0x545454;

    public static void unpack(TextDrawingArea[] tda) {
        int id = 23050;
        int frame = 0;
        RSInterface rs = addInterface(id++);
        rs.totalChildren(9);

        //background
        addSpriteLoader(id, 1352);
        rs.child(frame++, id++, 80, 18);

        //confirm top right button
        addHoverButtonWSpriteLoader(id, 1355, 16, 16, "Confirm Selection", -1, id+1, 1);
        addHoveredImageWSpriteLoader(id+1, 1356, 16, 16, id+2);
        rs.child(frame++, id, 406, 27);
        rs.child(frame++, id + 1, 406, 27);
        id += 3;

        //title
        addText(id, "Character Creation", fonts, 2, ORANGE, true, true);
        rs.child(frame++, id++, 259, 28);

        //cycle left button
        addHoverButtonWSpriteLoader(id, 1360, 13, 254, "Cycle Left", -1, id+1, 1);
        addHoveredImageWSpriteLoader(id+1, 1359, 13, 254, id+2);
        rs.child(frame++, id, 90, 56);
        rs.child(frame++, id + 1, 90, 56);
        id += 3;

        //cycle right button
        addHoverButtonWSpriteLoader(id, 1358, 13, 254, "Cycle Right", -1, id+1, 1);
        addHoveredImageWSpriteLoader(id+1, 1357, 13, 254, id+2);
        rs.child(frame++, id, 408, 56);
        rs.child(frame++, id + 1, 408, 56);
        id += 3;

        createAccountCard(tda);

        rs.child(frame++, 23100, 106, 58);
    }

    private static void createAccountCard(TextDrawingArea[] tda) {
        int id = 23100;
        int frame = 0;
        RSInterface rs = addInterface(id++);
        rs.width = 299;
        rs.height = 250;
        rs.totalChildren(18);

        int xOffset = 157;

        addSpriteLoader(id, 1354);
        rs.child(frame++, id++, 73, 4);

        addSpriteLoader(id, 1361);
        rs.child(frame++, id, 73 - xOffset, 4);
        rs.child(frame++, id++, 73 + xOffset, 4);


        addText(id, "Ironman", fonts, 0, ORANGE, true, true);
        rs.child(frame++, id++, 150, 12);

        addText(id, "Ironman", fonts, 0, ORANGE_FADED, true, true);
        rs.child(frame++, id++, 150 - xOffset, 12);

        addText(id, "Ironman", fonts, 0, ORANGE_FADED, true, true);
        rs.child(frame++, id++, 150 + xOffset, 12);


        //drop information
        addWrappingText(id, "Drop information a a a a a a a a a a aa  a . . . . . " +
                "Drop information", tda, 1, GREY, false, true, 141, 12);
        rs.child(frame++, id++, 80, 195);

        addWrappingText(id, "Drop information a a a a a a a a a a aa  a . . . . . " +
                "Drop information", tda, 1, GREY_FADED, false, true, 141, 12);

        rs.child(frame++, id++, 80 - xOffset, 195);
        addWrappingText(id, "Drop information a a a a a a a a a a aa  a . . . . . " +
                "Drop information", tda, 1, GREY_FADED, false, true, 141, 12);
        rs.child(frame++, id++, 80 + xOffset, 195);

        RSInterface textScroll = addInterface(id++);
        textScroll.scrollMax = 250;
        textScroll.width = 129;
        textScroll.height = 161;
        setChildren(1, textScroll);

        addWrappingText(id, "- Information sfsd f d f . . . s d f fd fd fd d d d", tda, 1, GREY, false, false, 127, 14);
        textScroll.child(0, id, 2, 2);

        rs.child(frame++, id++ - 1, 79, 30);
        RSInterface leftDesc = addInterface(id++);
        leftDesc.width = 129 - 78;
        leftDesc.height = 161;
        setChildren(1, leftDesc);

        addWrappingText(id, "- Information sfsd f d f . . . s d f fd fd fd d d left left left left left left", tda, 1, GREY_FADED, false, false, 127, 14);
        leftDesc.child(0, id, 2 - 78, 2);

        rs.child(frame++, id++ - 1, 79 - xOffset + 78, 33);

        RSInterface rightDesc = addInterface(id++);
        rightDesc.width = 129 - 78;
        rightDesc.height = 161;
        setChildren(1, rightDesc);

        addWrappingText(id, "- Information sfsd f d f . . . s d f fd fd fd d d right right right right right right", tda, 1, GREY_FADED, false, false, 127, 14);
        rightDesc.child(0, id, 2, 2);

        rs.child(frame++, id++ - 1, 79 + xOffset, 33);


        addSpriteLoader(id, 712);
        rs.child(frame++, id, 85, 11);
        rs.child(frame++, id++, 202, 11);

        addSpriteLoader(id, 711);
        rs.child(frame++, id, 85 - xOffset, 11);
        rs.child(frame++, id++, 202 - xOffset, 11);

        addSpriteLoader(id, 713);
        rs.child(frame++, id, 85 + xOffset, 11);
        rs.child(frame++, id++, 202 + xOffset, 11);
    }
}
