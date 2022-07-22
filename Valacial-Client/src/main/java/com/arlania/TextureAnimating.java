package com.arlania;

public class TextureAnimating {

	private static final int[][] Animated_Textures = {new int[] {90,4},{91,4},{89,4},{95,4},{93,4},{103,4},{84,4},{99,4},{98,4},{83,4},{85,2},{88,4},{86,4},{96,4}, {94,4}, 	new int[] {51,0},new int[] {52,0},new int[] {53,0},new int[] {54,0},new int[] {55,0},new int[] {56,0},new int[] {57,0},new int[] {58,0},new int[] {59,0},new int[] {60,0},new int[] {61,0},new int[] {62,0},new int[] {63,0},new int[] {64,0},new int[] {65,0},new int[] {66,0},new int[] {67,0},new int[] {68,0},new int[] {69,0},new int[] {70,0}, new int[] {71,0},new int[] {24,0},  new int[] {34,0}, new int[] {40,0}, new int[] {1,0}};
	private static byte[] pixels = new byte[16384];
	private static int[] hdPixels = new int[16384];

	/**
	 * Animates on screen textures with a certain id.
	 */
	public static void animateTexture() {
		try {
		if (!Rasterizer.lowMem) {
			for(int[] tex: Animated_Textures ) {
				if(Client.getOption("hd_tex")) {
					Texture t = Texture.get(tex[0]);
					if(t instanceof RGBTexture) {
						RGBTexture rgbT = (RGBTexture)t;
						int indexes = rgbT.width * rgbT.height - 1;
						int noise = rgbT.width * Client.instance.cycleTimer * 2*tex[1];
						int current[] = rgbT.pixels;
						int next[] = hdPixels;
						for (int i2 = 0; i2 <= indexes; i2++)
							next[i2] = current[i2 - noise & indexes];

						rgbT.setPixels(next);
						hdPixels = current;
						TextureLoader667.resetTexture(tex[0]);
					} else
					if(t instanceof ARGBTexture) {
						ARGBTexture rgbT = (ARGBTexture)t;
						int indexes = rgbT.width * rgbT.height - 1;
						int noise = rgbT.width * Client.instance.cycleTimer * 2*tex[1];
						int current[] = rgbT.pixels;
						int next[] = hdPixels;
						for (int i2 = 0; i2 <= indexes; i2++)
							next[i2] = current[i2 - noise & indexes];

						rgbT.setPixels(next);
						hdPixels = current;
						TextureLoader667.resetTexture(tex[0]);
					} else
					if(t instanceof PalettedTexture || t instanceof AlphaPalettedTexture) {
						PalettedTexture rgbT = (PalettedTexture)t;
						int indexes = rgbT.width * rgbT.height - 1;
						int noise = rgbT.width * Client.instance.cycleTimer * 2*tex[1];
						byte current[] = rgbT.getIndices();
						byte next[] = pixels;
						for (int i2 = 0; i2 <= indexes; i2++)
							next[i2] = current[i2 - noise & indexes];

						rgbT.setIndices(next);
						pixels = current;
						TextureLoader667.resetTexture(tex[0]);
					}
				} else {
					Background background = TextureLoader317.textureImages[tex[0]];
					int indexes = background.imgWidth * background.imgHeight - 1;
					int noise = background.imgWidth * Client.instance.cycleTimer * 1*tex[1];
					byte current[] = background.imgPixels;
					byte next[] = pixels;
					for (int i2 = 0; i2 <= indexes; i2++)
						next[i2] = current[i2 - noise & indexes];

					background.imgPixels = next;
					pixels = current;
					TextureLoader317.resetTexture(tex[0]);
				}
			}
		}
		} catch(Exception e) { }
	}
}
