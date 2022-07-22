package com.arlania;

import static com.arlania.RSInterface.*;

public class StarterZone
{
	private static final int ORANGE = 0xFF8624;

	public static void unpack(TextDrawingArea[] tda)
	{
		int id = 23400;
		int frame = 0;
		RSInterface rs = addInterface(id++);
		rs.totalChildren(11);

		int x = 135;
		int y = 273;
		int width = 242;
		int height = 60;
		addRectangle(id, 0, 0xFFFFFF, false, width, height);
		rs.child(frame++, id++, x, y);
		addRectangle(id, 215, 0xFFFFFF, true, width - 2, height - 2);
		rs.child(frame++, id++, x+1, y+1);

		addText(id, "", fonts, 2, ORANGE, true, true);
		rs.child(frame++, id++, 197, 276);

		addText(id, "Next Rewards", fonts, 2, ORANGE, true, true);
		rs.child(frame++, id++, 315, 276);


		//progress bar
		addRectangle(id, 0, 0x000000, false, 102, 20);
		rs.child(frame++, id++, 146, 300);
		createProgressBar(23395);
		rs.child(frame++, 23395, 147, 301);

		//progress text
		addText(id, "", fonts, 2, 0xFFFFFF, true, true);
		rs.child(frame++, id++, 197, 303);
		interfaceCache[id - 1].doubleShadow = true;

		//items
		addContainer(id, 0, 3, 1, 6, 10, false, new String[] {null, null, null, null, null});
		rs.child(frame++, id++, 258, 294);

		for (int i = 0; i < 3; i++) {
			addRectangle(id, 0, 0x000000, false, 34, 34);
			rs.child(frame++, id++, 258 + i * 37, 293);
		}
	}


	private static void createProgressBar(int id) {
		int frame = 0;
		RSInterface rs = addInterface(id++);
		rs.totalChildren(1);
		rs.width = 100;
		rs.height = 18;

		addRectangle(id, 0, 0xFF0000, true, 100, 18);
		rs.child(frame++, id++, 0, 0);
	}
}
