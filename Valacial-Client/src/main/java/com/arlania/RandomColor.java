/*     */ package com.arlania;
/*     */

/*     */ public class RandomColor
        /*     */ {
    /*  12 */   private static int currentLoadingColor = -1;
    /*  13 */   private static int nextLoadingColor = -1;
    /*  14 */   private static long startTime = 0L;
    /*  15 */   private static long colorStart = 0L;
    /*     */   public static int currentColour;
    /*     */
    /*     */   public static void process() {
        /*  19 */     currentColour = processRandomColour();
        /*     */   }
    /*     */
    /*     */   private static int randomColor() {
        /*  23 */     int red = (int)(Math.random() * 17.0D);
        /*  24 */     int green = (int)(Math.random() * 17.0D);
        /*  25 */     int blue = (int)(Math.random() * 17.0D);
        /*  26 */     int type = (int)(Math.random() * 8.0D);
        /*  27 */     if (type == 0) {
            /*  28 */       type |= 1 << (int)(Math.random() * 3.0D);
            /*     */     }
        /*  30 */     else if (type == 7) {
            /*  31 */       type &= (1 << (int)(Math.random() * 3.0D) ^ 0xFFFFFFFF);
            /*     */     }
        /*  33 */     if ((type & 0x1) != 0) {
            /*  34 */       red += 196 + (int)(Math.random() * 33.0D);
            /*     */     }
        /*  36 */     if ((type & 0x2) != 0) {
            /*  37 */       green += 196 + (int)(Math.random() * 33.0D);
            /*     */     }
        /*  39 */     if ((type & 0x4) != 0) {
            /*  40 */       blue += 196 + (int)(Math.random() * 33.0D);
            /*     */     }
        /*  42 */     return red << 16 | green << 8 | blue;
        /*     */   }
    /*     */
    /*     */   private static int blend(int dst, int src, int a1) {
        /*  46 */     if (a1 <= 0) {
            /*  47 */       return dst;
            /*     */     }
        /*  49 */     if (a1 >= 255) {
            /*  50 */       return src;
            /*     */     }
        /*  52 */     int a2 = 255 - a1;
        /*  53 */     return ((0xFF00FF00 & (0xFF00FF & src) * a1 | 0xFF0000 & (src & 0xFF00) * a1) >>> 8) + ((0xFF0000 & a2 * (dst & 0xFF00) | a2 * (dst & 0xFF00FF) & 0xFF00FF00) >>> 8);
        /*     */   }
    /*     */
    /*     */   private static int processRandomColour() {
        /*  57 */     long time = System.currentTimeMillis();
        /*  58 */     if (startTime == 0L) {
            /*  59 */       startTime = time;
            /*     */     }
        /*  61 */     int n = -1;
        /*  62 */     long elapse = (time - startTime) / 30L;
        /*  63 */     if (n == -1) {
            /*  64 */       n = (int)(elapse % 2000L);
            /*  65 */       if (n > 1000) {
                /*  66 */         n = 2000 - n;
                /*     */       }
            /*     */     }
        /*  69 */     if (n < 0) {
            /*  70 */       n = 0;
            /*  71 */     } else if (n > 1000) {
            /*  72 */       n = 1000;
            /*     */     }
        /*  74 */     if ((colorStart == 0L) || (nextLoadingColor == -1)) {
            /*  75 */       colorStart = time;
            /*  76 */       nextLoadingColor = randomColor();
            /*     */     }
        /*  78 */     if (currentLoadingColor == -1) {
            /*  79 */       currentLoadingColor = 3329330;
            /*     */     }
        /*  81 */     long colorElapse = (time - colorStart) / 25L;
        /*  82 */     int alpha = (int)(colorElapse * 255L / 200L);
        /*  83 */     int color = blend(currentLoadingColor, nextLoadingColor, alpha);
        /*  84 */     if (alpha >= 255) {
            /*  85 */       currentLoadingColor = nextLoadingColor;
            /*  86 */       nextLoadingColor = -1;
            /*     */     }
        /*  88 */     return rgbToHSL(color);
        /*     */   }
    /*     */
    /*     */   public static int rgbToHSL(int color) {
        /*  92 */     double r = (color >> 16 & 0xFF) / 256.0D;
        /*  93 */     double g = (color >> 8 & 0xFF) / 256.0D;
        /*  94 */     double b = (color & 0xFF) / 256.0D;
        /*  95 */     double red_val1 = r;
        /*  96 */     if (g < red_val1) {
            /*  97 */       red_val1 = g;
            /*     */     }
        /*  99 */     if (b < red_val1) {
            /* 100 */       red_val1 = b;
            /*     */     }
        /* 102 */     double red_val2 = r;
        /* 103 */     if (g > red_val2) {
            /* 104 */       red_val2 = g;
            /*     */     }
        /* 106 */     if (b > red_val2) {
            /* 107 */       red_val2 = b;
            /*     */     }
        /* 109 */     double hueCalc = 0.0D;
        /* 110 */     double satCalc = 0.0D;
        /* 111 */     double lightCalc = (red_val1 + red_val2) / 2.0D;
        /* 112 */     if (red_val1 != red_val2) {
            /* 113 */       if (lightCalc < 0.5D) {
                /* 114 */         satCalc = (red_val2 - red_val1) / (red_val2 + red_val1);
                /*     */       }
            /* 116 */       if (lightCalc >= 0.5D) {
                /* 117 */         satCalc = (red_val2 - red_val1) / (2.0D - red_val2 - red_val1);
                /*     */       }
            /* 119 */       if (r == red_val2) {
                /* 120 */         hueCalc = (g - b) / (red_val2 - red_val1);
                /*     */       }
            /* 122 */       else if (g == red_val2) {
                /* 123 */         hueCalc = 2.0D + (b - r) / (red_val2 - red_val1);
                /*     */       }
            /* 125 */       else if (b == red_val2) {
                /* 126 */         hueCalc = 4.0D + (r - g) / (red_val2 - red_val1);
                /*     */       }
            /*     */     }
        /* 129 */     hueCalc /= 6.0D;
        /* 130 */     int saturation = (int)(satCalc * 256.0D);
        /* 131 */     int lightness = (int)(lightCalc * 256.0D);
        /* 132 */     if (saturation < 0) {
            /* 133 */       saturation = 0;
            /*     */     }
        /* 135 */     else if (saturation > 255) {
            /* 136 */       saturation = 255;
            /*     */     }
        /* 138 */     if (lightness < 0) {
            /* 139 */       lightness = 0;
            /*     */     }
        /* 141 */     else if (lightness > 255) {
            /* 142 */       lightness = 255;
            /*     */     }
        /* 144 */     int divisor = 1;
        /* 145 */     if (lightCalc > 0.5D) {
            /* 146 */       divisor = (int)((1.0D - lightCalc) * satCalc * 512.0D);
            /*     */     }
        /*     */     else {
            /* 149 */       divisor = (int)(lightCalc * satCalc * 512.0D);
            /*     */     }
        /* 151 */     if (divisor < 1) {
            /* 152 */       divisor = 1;
            /*     */     }
        /* 154 */     int hueOffset = (int)(hueCalc * divisor);
        /* 155 */     int saturationOffset = saturation;
        /* 156 */     int lightnessOffset = lightness;
        /* 157 */     return getHSLValue(hueOffset, saturationOffset, lightnessOffset);
        /*     */   }
    /*     */
    /*     */   private static int getHSLValue(int hue, int saturation, int lightness) {
        /* 161 */     if (lightness > 179) {
            /* 162 */       saturation /= 2;
            /*     */     }
        /* 164 */     if (lightness > 192) {
            /* 165 */       saturation /= 2;
            /*     */     }
        /* 167 */     if (lightness > 217) {
            /* 168 */       saturation /= 2;
            /*     */     }
        /* 170 */     if (lightness > 243) {
            /* 171 */       saturation /= 2;
            /*     */     }
        /* 173 */     return (hue / 4 << 10) + (saturation / 32 << 7) + lightness / 2;
        /*     */   }
    /*     */ }
