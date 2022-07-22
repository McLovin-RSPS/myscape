/*    */ package com.arlania;
/*    */
/*    */ public class Texture {
	/*    */   public boolean opaque;
	/*    */   public boolean hasAlpha;
	/*    */   public final int width;
	/*    */
	/*    */   public Texture(int width, int height) {
		/*  9 */     this.width = width;
		/* 10 */     this.height = height;
		/* 11 */     this.opaque = true;
		/*    */   }
	/*    */
	/*    */   public int getPixel(int i)
	/*    */   {
		/* 16 */     return -1;
		/*    */   }
	/*    */
	/*    */
	/*    */   public static Texture get(int id)
	/*    */   {
		/* 22 */     if ((id < 0) || (id >= textures.length)) {
			/* 23 */       return null;
			/*    */     }
		/* 25 */     if (loaded[id])
			/* 26 */       return textures[id];
		/* 27 */     updateManager.requestFileData(5, id);
		/* 28 */     return null;
		/*    */   }
	/*    */
	/*    */   public static void init(int count, OnDemandFetcher updateManager_)
	/*    */   {
		/* 33 */     textures = new Texture[count];
		/* 34 */     loaded = new boolean[count];
		/* 35 */     updateManager = updateManager_;
		/*    */   }
	/*    */
	/*    */   public static void load(int id, byte[] data)
	/*    */   {
		/* 40 */     loaded[id] = true;
		/* 41 */     if ((data != null) && (data.length >= 5))
			/*    */     {
			/* 43 */       ByteBuffer buffer = new ByteBuffer(data);
			/* 44 */       int type = buffer.readUnsignedByte();
			/* 45 */       int width = buffer.readUnsignedWord();
			/* 46 */       int height = buffer.readUnsignedWord();
			/* 47 */       if (type == 0) {
				/* 48 */         textures[id] = new PalettedTexture(width, height, buffer, false);
				/* 49 */       } else if (type == 1) {
				/* 50 */         textures[id] = new RGBTexture(width, height, buffer);
				/* 51 */       } else if (type == 2) {
				/* 52 */         textures[id] = new AlphaPalettedTexture(width, height, buffer);
				/* 53 */       } else if (type == 3) {
				/* 54 */         textures[id] = new ARGBTexture(width, height, buffer);
				/*    */       }
			/*    */     }
		/*    */   }
	/*    */
	/*    */   public String toString()
	/*    */   {
		/* 61 */     return this.width + " X " + this.height + "\t" + (this.opaque ? "+opaque" : "-opaque") + "\t" + (this.hasAlpha ? "+alpha" : "-alpha");
		/*    */   }
	/*    */
	/*    */   public static void nullLoader()
	/*    */   {
		/* 66 */     loaded = null;
		/* 67 */     textures = null;
		/* 68 */     updateManager = null;
		/*    */   }
	/*    */
	/*    */   public static int getTotal()
	/*    */   {
		/* 73 */     return textures.length;
		/*    */   }
	/*    */
	/*    */
	/*    */   public final int height;
	/*    */
	/*    */   private static boolean[] loaded;
	/*    */
	/*    */   private static Texture[] textures;
	/*    */   static OnDemandFetcher updateManager;
	/* 83 */   public static Texture NULL_TEXTURE = new Texture(64, 64);
	/*    */ }

