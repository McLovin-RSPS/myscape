/*     */ package com.arlania;
/*     */
/*     */ import java.io.PrintStream;
/*     */
/*     */ public final class Background extends DrawingArea { public byte[] imgPixels;
	/*     */   public final int[] palette;
	/*     */
	public Background(int width, int height, int[] palette, byte[] pixels) {
		this.imgWidth = libWidth = width;
		this.imgHeight = libHeight = height;
		this.palette = palette;
		this.imgPixels = pixels;
	}
	/*   8 */   public Background(CacheArchive streamLoader, String s, int i) { Stream stream = new Stream(streamLoader.getDataForName(s + ".dat"));
		/*   9 */     Stream stream_1 = new Stream(streamLoader.getDataForName("index.dat"));
		/*  10 */     stream_1.currentOffset = stream.readUnsignedWord();
		/*  11 */     this.libWidth = stream_1.readUnsignedWord();
		/*  12 */     this.libHeight = stream_1.readUnsignedWord();
		/*  13 */     int j = stream_1.readUnsignedByte();
		/*  14 */     this.palette = new int[j];
		/*  15 */     for (int k = 0; k < j - 1; k++) {
			/*  16 */       this.palette[(k + 1)] = stream_1.read3Bytes();
			/*     */     }
		/*  18 */     for (int l = 0; l < i; l++)
			/*     */     {
			/*  20 */       stream_1.currentOffset += 2;
			/*  21 */       stream.currentOffset += stream_1.readUnsignedWord() * stream_1.readUnsignedWord();
			/*  22 */       stream_1.currentOffset += 1;
			/*     */     }
		/*     */
		/*  25 */     this.xDrawOffset = stream_1.readUnsignedByte();
		/*  26 */     this.yDrawOffset = stream_1.readUnsignedByte();
		/*  27 */     this.imgWidth = stream_1.readUnsignedWord();
		/*  28 */     this.imgHeight = stream_1.readUnsignedWord();
		/*  29 */     int type = stream_1.readUnsignedByte();
		/*  30 */     int j1 = this.imgWidth * this.imgHeight;
		/*  31 */     this.imgPixels = new byte[j1];
		/*  32 */     if (type == 0)
			/*     */     {
			/*  34 */       for (int k1 = 0; k1 < j1; k1++) {
				/*  35 */         this.imgPixels[k1] = stream.readSignedByte();
				/*     */       }
			/*  37 */       return;
			/*     */     }
		/*  39 */     if (type == 1)
			/*     */     {
			/*  41 */       for (int l1 = 0; l1 < this.imgWidth; l1++)
				/*     */       {
				/*  43 */         for (int i2 = 0; i2 < this.imgHeight; i2++)
					/*  44 */           this.imgPixels[(l1 + i2 * this.imgWidth)] = stream.readSignedByte(); } }
		/*     */   }
	/*     */
	/*     */   public int imgWidth;
	/*     */   public int imgHeight;
	/*     */   public int xDrawOffset;
	/*     */   public int yDrawOffset;
	/*     */   public int libWidth;
	/*     */   private int libHeight;
	/*  53 */   public void reduceSetOffset() { this.libWidth /= 2;
		/*  54 */     this.libHeight /= 2;
		/*  55 */     byte[] pixelBuffer = new byte[this.libWidth * this.libHeight];
		/*  56 */     int i = 0;
		/*  57 */     for (int j = 0; j < this.imgHeight; j++)
			/*     */     {
			/*  59 */       for (int k = 0; k < this.imgWidth; k++) {
				/*  60 */         pixelBuffer[((k + this.xDrawOffset >> 1) + (j + this.yDrawOffset >> 1) * this.libWidth)] = this.imgPixels[(i++)];
				/*     */       }
			/*     */     }
		/*     */
		/*  64 */     this.imgPixels = pixelBuffer;
		/*  65 */     this.imgWidth = this.libWidth;
		/*  66 */     this.imgHeight = this.libHeight;
		/*  67 */     this.xDrawOffset = 0;
		/*  68 */     this.yDrawOffset = 0;
		/*     */   }
	/*     */
	/*     */   public void setOffset()
	/*     */   {
		/*  73 */     if ((this.imgWidth == this.libWidth) && (this.imgHeight == this.libHeight))
			/*  74 */       return;
		/*  75 */     byte[] pixelBuffer = new byte[this.libWidth * this.libHeight];
		/*  76 */     int i = 0;
		/*  77 */     for (int y = 0; y < this.imgHeight; y++)
			/*     */     {
			/*  79 */       for (int x = 0; x < this.imgWidth; x++) {
				/*  80 */         pixelBuffer[(x + this.xDrawOffset + (y + this.yDrawOffset) * this.libWidth)] = this.imgPixels[(i++)];
				/*     */       }
			/*     */     }
		/*     */
		/*  84 */     this.imgPixels = pixelBuffer;
		/*  85 */     this.imgWidth = this.libWidth;
		/*  86 */     this.imgHeight = this.libHeight;
		/*  87 */     this.xDrawOffset = 0;
		/*  88 */     this.yDrawOffset = 0;
		/*     */   }
	/*     */
	/*     */   public void spriteClip(int x, int y, int stretchWidth, int stretchHeight)
	/*     */   {
		/*     */     try
			/*     */     {
			/*  95 */       int width = this.imgWidth;
			/*  96 */       int height = this.imgHeight;
			/*  97 */       int k1 = 0;
			/*  98 */       int l1 = 0;
			/*  99 */       int i2 = (width << 16) / stretchWidth;
			/* 100 */       int j2 = (height << 16) / stretchHeight;
			/* 101 */       int k2 = this.libWidth;
			/* 102 */       int l2 = this.libHeight;
			/* 103 */       i2 = (k2 << 16) / stretchWidth;
			/* 104 */       j2 = (l2 << 16) / stretchHeight;
			/* 105 */       x += (this.xDrawOffset * stretchWidth + k2 - 1) / k2;
			/* 106 */       y += (this.yDrawOffset * stretchHeight + l2 - 1) / l2;
			/* 107 */       if (this.xDrawOffset * stretchWidth % k2 != 0)
				/* 108 */         k1 = (k2 - this.xDrawOffset * stretchWidth % k2 << 16) / stretchWidth;
			/* 109 */       if (this.yDrawOffset * stretchHeight % l2 != 0)
				/* 110 */         l1 = (l2 - this.yDrawOffset * stretchHeight % l2 << 16) / stretchHeight;
			/* 111 */       stretchWidth = stretchWidth * (this.imgWidth - (k1 >> 16)) / k2;
			/* 112 */       stretchHeight = stretchHeight * (this.imgHeight - (l1 >> 16)) / l2;
			/* 113 */       int i3 = x + y * DrawingArea.width;
			/* 114 */       int j3 = DrawingArea.width - stretchWidth;
			/* 115 */       if (y < DrawingArea.topY)
				/*     */       {
				/* 117 */         int k3 = DrawingArea.topY - y;
				/* 118 */         stretchHeight -= k3;
				/* 119 */         y = 0;
				/* 120 */         i3 += k3 * DrawingArea.width;
				/* 121 */         l1 += j2 * k3;
				/*     */       }
			/* 123 */       if (y + stretchHeight > DrawingArea.bottomX)
				/* 124 */         stretchHeight -= y + stretchHeight - DrawingArea.bottomX;
			/* 125 */       if (x < DrawingArea.topX)
				/*     */       {
				/* 127 */         int width2 = DrawingArea.topX - x;
				/* 128 */         stretchWidth -= width2;
				/* 129 */         x = 0;
				/* 130 */         i3 += width2;
				/* 131 */         k1 += i2 * width2;
				/* 132 */         j3 += width2;
				/*     */       }
			/* 134 */       if (x + stretchWidth > DrawingArea.bottomY)
				/*     */       {
				/* 136 */         int i4 = x + stretchWidth - DrawingArea.bottomY;
				/* 137 */         stretchWidth -= i4;
				/* 138 */         j3 += i4;
				/*     */       }
			/* 140 */       plotScale(DrawingArea.pixels, this.imgPixels, this.palette, k1, l1, i3, j3, stretchWidth, stretchHeight, i2, j2, width);
			/*     */     }
		/*     */     catch (Exception exception)
			/*     */     {
			/* 144 */       System.out.println("error in sprite clipping routine");
			/*     */     }
		/*     */   }
	/*     */
	/*     */
	/*     */   public void plotScale(int[] pixels, byte[] imgPixels, int[] palette, int i, int j, int k, int l, int stretchWidth, int stretchHeight, int k1, int l1, int width)
	/*     */   {
		/*     */     try
			/*     */     {
			/* 153 */       int j2 = i;
			/* 154 */       for (int k2 = -stretchHeight; k2 < 0; k2++)
				/*     */       {
				/* 156 */         int l2 = (j >> 16) * width;
				/* 157 */         for (int i3 = -stretchWidth; i3 < 0; i3++)
					/*     */         {
					/* 159 */           byte byte0 = imgPixels[((i >> 16) + l2)];
					/* 160 */           if (byte0 != 0) {
						/* 161 */             pixels[(k++)] = palette[(byte0 & 0xFF)];
						/*     */           } else
						/* 163 */             k++;
					/* 164 */           i += k1;
					/*     */         }
				/*     */
				/* 167 */         j += l1;
				/* 168 */         i = j2;
				/* 169 */         k += l;
				/*     */       }
			/*     */
			/*     */     }
		/*     */     catch (Exception exception)
			/*     */     {
			/* 175 */       System.out.println("error in plot_scale");
			/*     */     }
		/*     */   }
	/*     */
	/*     */   public void decodePalette(int r, int g, int b)
	/*     */   {
		/* 181 */     for (int i1 = 0; i1 < this.palette.length; i1++)
			/*     */     {
			/* 183 */       int j1 = this.palette[i1] >> 16 & 0xFF;
			/* 184 */       j1 += r;
			/* 185 */       if (j1 < 0) {
				/* 186 */         j1 = 0;
				/*     */       }
			/* 188 */       else if (j1 > 255)
				/* 189 */         j1 = 255;
			/* 190 */       int k1 = this.palette[i1] >> 8 & 0xFF;
			/* 191 */       k1 += g;
			/* 192 */       if (k1 < 0) {
				/* 193 */         k1 = 0;
				/*     */       }
			/* 195 */       else if (k1 > 255)
				/* 196 */         k1 = 255;
			/* 197 */       int l1 = this.palette[i1] & 0xFF;
			/* 198 */       l1 += b;
			/* 199 */       if (l1 < 0) {
				/* 200 */         l1 = 0;
				/*     */       }
			/* 202 */       else if (l1 > 255)
				/* 203 */         l1 = 255;
			/* 204 */       this.palette[i1] = ((j1 << 16) + (k1 << 8) + l1);
			/*     */     }
		/*     */   }
	/*     */
	/*     */   public void drawBackground(int i, int k)
	/*     */   {
		/* 210 */     i += this.xDrawOffset;
		/* 211 */     k += this.yDrawOffset;
		/* 212 */     int l = i + k * DrawingArea.width;
		/* 213 */     int i1 = 0;
		/* 214 */     int j1 = this.imgHeight;
		/* 215 */     int k1 = this.imgWidth;
		/* 216 */     int l1 = DrawingArea.width - k1;
		/* 217 */     int i2 = 0;
		/* 218 */     if (k < DrawingArea.topY)
			/*     */     {
			/* 220 */       int j2 = DrawingArea.topY - k;
			/* 221 */       j1 -= j2;
			/* 222 */       k = DrawingArea.topY;
			/* 223 */       i1 += j2 * k1;
			/* 224 */       l += j2 * DrawingArea.width;
			/*     */     }
		/* 226 */     if (k + j1 > DrawingArea.bottomY)
			/* 227 */       j1 -= k + j1 - DrawingArea.bottomY;
		/* 228 */     if (i < DrawingArea.topX)
			/*     */     {
			/* 230 */       int k2 = DrawingArea.topX - i;
			/* 231 */       k1 -= k2;
			/* 232 */       i = DrawingArea.topX;
			/* 233 */       i1 += k2;
			/* 234 */       l += k2;
			/* 235 */       i2 += k2;
			/* 236 */       l1 += k2;
			/*     */     }
		/* 238 */     if (i + k1 > DrawingArea.bottomX)
			/*     */     {
			/* 240 */       int l2 = i + k1 - DrawingArea.bottomX;
			/* 241 */       k1 -= l2;
			/* 242 */       i2 += l2;
			/* 243 */       l1 += l2;
			/*     */     }
		/* 245 */     if ((k1 > 0) && (j1 > 0))
			/*     */     {
			/* 247 */       drawPixels(j1, DrawingArea.pixels, this.imgPixels, l1, l, k1, i1, this.palette, i2);
			/*     */     }
		/*     */   }
	/*     */
	/*     */
	/*     */   private void drawPixels(int i, int[] ai, byte[] abyte0, int j, int k, int l, int i1, int[] ai1, int j1)
	/*     */   {
		/* 254 */     int k1 = -(l >> 2);
		/* 255 */     l = -(l & 0x3);
		/* 256 */     for (int l1 = -i; l1 < 0; l1++)
			/*     */     {
			/* 258 */       for (int i2 = k1; i2 < 0; i2++)
				/*     */       {
				/* 260 */         byte byte1 = abyte0[(i1++)];
				/* 261 */         if (byte1 != 0) {
					/* 262 */           ai[(k++)] = ai1[(byte1 & 0xFF)];
					/*     */         } else
					/* 264 */           k++;
				/* 265 */         byte1 = abyte0[(i1++)];
				/* 266 */         if (byte1 != 0) {
					/* 267 */           ai[(k++)] = ai1[(byte1 & 0xFF)];
					/*     */         } else
					/* 269 */           k++;
				/* 270 */         byte1 = abyte0[(i1++)];
				/* 271 */         if (byte1 != 0) {
					/* 272 */           ai[(k++)] = ai1[(byte1 & 0xFF)];
					/*     */         } else
					/* 274 */           k++;
				/* 275 */         byte1 = abyte0[(i1++)];
				/* 276 */         if (byte1 != 0) {
					/* 277 */           ai[(k++)] = ai1[(byte1 & 0xFF)];
					/*     */         } else {
					/* 279 */           k++;
					/*     */         }
				/*     */       }
			/* 282 */       for (int j2 = l; j2 < 0; j2++)
				/*     */       {
				/* 284 */         byte byte2 = abyte0[(i1++)];
				/* 285 */         if (byte2 != 0) {
					/* 286 */           ai[(k++)] = ai1[(byte2 & 0xFF)];
					/*     */         } else {
					/* 288 */           k++;
					/*     */         }
				/*     */       }
			/* 291 */       k += j;
			/* 292 */       i1 += j1;
			/*     */     }
		/*     */   }
	/*     */ }
