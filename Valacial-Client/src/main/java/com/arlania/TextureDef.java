package com.arlania;


/*     */ public final class TextureDef { boolean aBoolean1223;
	/*     */   boolean aBoolean1204;
	/*     */   boolean aBoolean1205;
	/*     */   byte aByte1217;
	/*     */   byte aByte1225;
	/*     */   byte aByte1214;
	/*     */   byte aByte1213;
	/*     */   short aShort1221;
	/*     */   byte aByte1211;
	/*     */   byte aByte1203;
	/*     */
	/*  14 */   public static void unpackConfig(CacheArchive streamLoader) { byte[] data = streamLoader.getDataForName("textures.dat");
		/*  15 */     ByteBuffer buffer = new ByteBuffer(data);
		/*  16 */     int count = buffer.readUnsignedWord();
		System.out.println("textures.dat has " + count + " textures");
		/*  17 */     textures = new TextureDef[count];
		/*  18 */     for (int i = 0; i != count; i++) {
			/*  19 */       if (buffer.readUnsignedByte() == 1) {
				/*  20 */         textures[i] = new TextureDef();
				/*     */       }
			/*     */     }
		/*  23 */     for (int i = 0; i != count; i++) {
			/*  24 */       if (textures[i] != null) {
				/*  25 */         textures[i].aBoolean1223 = (buffer.readUnsignedByte() == 1);
				/*     */       }
			/*     */     }
		/*  28 */     for (int i = 0; i != count; i++) {
			/*  29 */       if (textures[i] != null) {
				/*  30 */         textures[i].aBoolean1204 = (buffer.readUnsignedByte() == 1);
				/*     */       }
			/*     */     }
		/*  33 */     for (int i = 0; i != count; i++) {
			/*  34 */       if (textures[i] != null) {
				/*  35 */         textures[i].aBoolean1205 = (buffer.readUnsignedByte() == 1);
				/*     */       }
			/*     */     }
		/*  38 */     for (int i = 0; i != count; i++) {
			/*  39 */       if (textures[i] != null) {
				/*  40 */         textures[i].aByte1217 = buffer.readSignedByte();
				/*     */       }
			/*     */     }
		/*  43 */     for (int i = 0; i != count; i++) {
			/*  44 */       if (textures[i] != null) {
				/*  45 */         textures[i].aByte1225 = buffer.readSignedByte();
				/*     */       }
			/*     */     }
		/*  48 */     for (int i = 0; i != count; i++) {
			/*  49 */       if (textures[i] != null) {
				/*  50 */         textures[i].aByte1214 = buffer.readSignedByte();
				/*     */       }
			/*     */     }
		/*  53 */     for (int i = 0; i != count; i++) {
			/*  54 */       if (textures[i] != null) {
				/*  55 */         textures[i].aByte1213 = buffer.readSignedByte();
				/*     */       }
			/*     */     }
		/*  58 */     for (int i = 0; i != count; i++) {
			/*  59 */       if (textures[i] != null) {
				/*  60 */         textures[i].aShort1221 = ((short)buffer.readUnsignedWord());
				/*     */       }
			/*     */     }
		/*  63 */     for (int i = 0; i != count; i++) {
			/*  64 */       if (textures[i] != null) {
				/*  65 */         textures[i].aByte1211 = buffer.readSignedByte();
				/*     */       }
			/*     */     }
		/*  68 */     for (int i = 0; i != count; i++) {
			/*  69 */       if (textures[i] != null) {
				/*  70 */         textures[i].aByte1203 = buffer.readSignedByte();
				/*     */       }
			/*     */     }
		/*  73 */     for (int i = 0; i != count; i++) {
			/*  74 */       if (textures[i] != null) {
				/*  75 */         textures[i].aBoolean1222 = (buffer.readUnsignedByte() == 1);
				/*     */       }
			/*     */     }
		/*  78 */     for (int i = 0; i != count; i++) {
			/*  79 */       if (textures[i] != null) {
				/*  80 */         textures[i].aBoolean1216 = (buffer.readUnsignedByte() == 1);
				/*     */       }
			/*     */     }
		/*  83 */     for (int i = 0; i != count; i++) {
			/*  84 */       if (textures[i] != null) {
				/*  85 */         textures[i].aByte1207 = buffer.readSignedByte();
				/*     */       }
			/*     */     }
		/*  88 */     for (int i = 0; i != count; i++) {
			/*  89 */       if (textures[i] != null) {
				/*  90 */         textures[i].aBoolean1212 = (buffer.readUnsignedByte() == 1);
				/*     */       }
			/*     */     }
		/*  93 */     for (int i = 0; i != count; i++) {
			/*  94 */       if (textures[i] != null) {
				/*  95 */         textures[i].aBoolean1210 = (buffer.readUnsignedByte() == 1);
				/*     */       }
			/*     */     }
		/*  98 */     for (int i = 0; i != count; i++) {
			/*  99 */       if (textures[i] != null) {
				/* 100 */         textures[i].aBoolean1215 = (buffer.readUnsignedByte() == 1);
				/*     */       }
			/*     */     }
		/* 103 */     for (int i = 0; i != count; i++) {
			/* 104 */       if (textures[i] != null) {
				/* 105 */         textures[i].anInt1202 = buffer.readUnsignedByte();
				/*     */       }
			/*     */     }
		/* 108 */     for (int i = 0; i != count; i++) {
			/* 109 */       if (textures[i] != null) {
				/* 110 */         textures[i].anInt1206 = buffer.readDWord();
				/*     */       }
			/*     */     }
		/* 113 */     for (int i = 0; i != count; i++)
			/* 114 */       if (textures[i] != null)
				/* 115 */         textures[i].anInt1226 = buffer.readUnsignedByte();
		/*     */   }
	/*     */
	/*     */   boolean aBoolean1222;
	/*     */   boolean aBoolean1216;
	/*     */
	/*     */   public static void nullLoader() {
		/* 122 */     textures = null;
		/*     */   }
	/*     */
	/*     */   byte aByte1207;
	/*     */   boolean aBoolean1212;
	/*     */   boolean aBoolean1210;
	/*     */   boolean aBoolean1215;
	/*     */   int anInt1202;
	/*     */   int anInt1206;
	/*     */   int anInt1226;
	/*     */   public static TextureDef[] textures;
	/*     */ }

