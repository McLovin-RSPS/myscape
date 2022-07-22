package com.arlania;

public class TextureLoader317 {
   public static int textureammount = 104;
   private static int[][] texturePalettes;
   public static int loadedTextureCount;
   public static Background[] textureImages;
   public static boolean[] textureIsTransparent;
   private static int[] averageTextureColour;
   private static int[][] texelCache;
   public static int[] textureLastUsed;
   private static int textureTexelPoolPointer;
   private static int[][] texelArrayPool;
   public static int textureGetCount;

   static {
      texturePalettes = new int[textureammount][];
      textureImages = new Background[textureammount];
      textureIsTransparent = new boolean[textureammount];
      averageTextureColour = new int[textureammount];
      texelCache = new int[textureammount][];
      textureLastUsed = new int[textureammount];
   }

   public static void clear() {
      textureImages = null;
      textureIsTransparent = null;
      averageTextureColour = null;
      texelArrayPool = null;
      texelCache = null;
      textureLastUsed = null;
      texturePalettes = null;
   }

   public static void calculateTexturePalette(double brightness) {
      int l;
      for(l = 0; l < 104; ++l) {
         if (textureImages[l] != null) {
            int[] ai = textureImages[l].palette;
            texturePalettes[l] = new int[ai.length];

            for(int j1 = 0; j1 < ai.length; ++j1) {
               texturePalettes[l][j1] = Rasterizer.adjustBrightness(ai[j1], brightness);
               if ((texturePalettes[l][j1] & 16316671) == 0 && j1 != 0) {
                  texturePalettes[l][j1] = 1;
               }
            }
         }
      }

      for(l = 0; l < 104; ++l) {
         resetTexture(l);
      }

   }

   public static void clearTextureCache() {
      texelArrayPool = null;

      for(int j = 0; j < 104; ++j) {
         texelCache[j] = null;
      }

   }

   public static void resetTextures() {
      if (Client.getOption("hd_tex")) {
         TextureLoader667.resetTextures();
      }
      if (texelArrayPool == null) {
         textureTexelPoolPointer = 20;
         if (Rasterizer.lowMem) {
            texelArrayPool = new int[textureTexelPoolPointer][16384];
         } else {
            texelArrayPool = new int[textureTexelPoolPointer][65536];
         }

         for(int k = 0; k < textureammount; ++k) {
            texelCache[k] = null;
         }
      }
   }


   public static void unpackTextures(CacheArchive streamLoader) {
      loadedTextureCount = 0;

      for(int j = 0; j < 74; ++j) {
         try {
               textureImages[j] = new Background(streamLoader, String.valueOf(j), 0);
               if (Rasterizer.lowMem && textureImages[j].libWidth == 128) {
                  textureImages[j].reduceSetOffset();
               } else {
                  textureImages[j].setOffset();
               }
               Rasterizer.customTexIds.add(j);

            ++loadedTextureCount;
         } catch (Exception var3) {
         }
      }

   }

   public static int getAverageTextureColour(int textureId) {
      if (averageTextureColour[textureId] != 0) {
         return averageTextureColour[textureId];
      } else {
         int red = 0;
         int green = 0;
         int blue = 0;
         int colourCount = texturePalettes[textureId].length;

         int rgb;
         for(rgb = 0; rgb < colourCount; ++rgb) {
            red += texturePalettes[textureId][rgb] >> 16 & 255;
            green += texturePalettes[textureId][rgb] >> 8 & 255;
            blue += texturePalettes[textureId][rgb] & 255;
         }

         rgb = (red / colourCount << 16) + (green / colourCount << 8) + blue / colourCount;
         rgb = Rasterizer.adjustBrightness(rgb, 1.4D);
         if (textureId != 1 && textureId != 24) {
            rgb = Rasterizer.adjustBrightness(rgb, 1.4D);
         } else {
            rgb = adjustBrightnessLinear(rgb, 679);
            rgb = Rasterizer.adjustBrightness(rgb, 4.800000190734863D);
         }

         if (rgb == 0) {
            rgb = 1;
         }

         averageTextureColour[textureId] = rgb;
         return rgb;
      }
   }

   private static int adjustBrightnessLinear(int rgb, int factor) {
      return ((rgb >>> 16) * factor & '\uff00') << 8 | (rgb >>> 8 & 255) * factor & '\uff00' | (rgb & 255) * factor >> 8;
   }

   public static void resetTexture(int textureId) {
      if (texelCache[textureId] != null) {
         texelArrayPool[textureTexelPoolPointer++] = texelCache[textureId];
         texelCache[textureId] = null;
      }
   }

   public static int[] getTexturePixels(int textureId) {
      if (textureId == 1) {
         textureId = 24;
      }
      textureLastUsed[textureId] = textureGetCount++;
      if (texelCache[textureId] != null) {
         return texelCache[textureId];
      } else {
         int[] ai;
         int l1;
         if (textureTexelPoolPointer > 0) {
            ai = texelArrayPool[--textureTexelPoolPointer];
            texelArrayPool[textureTexelPoolPointer] = null;
         } else {
            int j = 0;
            int k = -1;

            for(l1 = 0; l1 < loadedTextureCount; ++l1) {
               if (texelCache[l1] != null && (textureLastUsed[l1] < j || k == -1)) {
                  j = textureLastUsed[l1];
                  k = l1;
               }
            }

            ai = texelCache[k];
            texelCache[k] = null;
         }

         texelCache[textureId] = ai;
         Background background = textureImages[textureId];
         int[] ai1 = texturePalettes[textureId];
         int k2;
         if (Rasterizer.lowMem) {
            textureIsTransparent[textureId] = false;

            for(l1 = 0; l1 < 4096; ++l1) {
               k2 = ai[l1] = ai1[background.imgPixels[l1]] & 16316671;
               if (k2 == 0) {
                  textureIsTransparent[textureId] = true;
               }

               ai[4096 + l1] = k2 - (k2 >>> 3) & 16316671;
               ai[8192 + l1] = k2 - (k2 >>> 2) & 16316671;
               ai[12288 + l1] = k2 - (k2 >>> 2) - (k2 >>> 3) & 16316671;
            }
         } else {
            if (background.imgWidth != 64) {
               for(l1 = 0; l1 < 16384; ++l1) {
                  ai[l1] = ai1[background.imgPixels[l1]];
               }
            } else {
               for(l1 = 0; l1 < 128; ++l1) {
                  for(k2 = 0; k2 < 128; ++k2) {
                     ai[k2 + (l1 << 7)] = ai1[background.imgPixels[(k2 >> 1) + (l1 >> 1 << 6)]];
                  }
               }
            }
//what the fuck have u done man
            textureIsTransparent[textureId] = false;

            for(l1 = 0; l1 < 16384; ++l1) {
               ai[l1] &= 16316671;
               k2 = ai[l1];
               if (k2 == 0) {
                  textureIsTransparent[textureId] = true;
               }

               ai[16384 + l1] = k2 - (k2 >>> 3) & 16316671;
               ai[32768 + l1] = k2 - (k2 >>> 2) & 16316671;
               ai[49152 + l1] = k2 - (k2 >>> 2) - (k2 >>> 3) & 16316671;
            }
         }

         return ai;
      }
   }
}
