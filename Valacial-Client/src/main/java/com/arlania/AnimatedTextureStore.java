package com.arlania;

/*    */
/*    */ public enum AnimatedTextureStore
        /*    */ {
    /*  5 */   WATER_DROPLETS(17, 4),
    /*  6 */   WATER(1, 1),
    /*  7 */   WATFER(24, 1),
    /*  8 */   MAGIC_TREE_STARS(34, 1),
    /*  9 */   FIRECAPE(55, 1),
    /* 10 */   CUSTOM(51, 1),
    /* 11 */   PROCUSTOM(52, 1),
    /* 12 */   NOOBCUSTOM(53, 1),
    /* 13 */   NEWBCUSTOM(54, 1),
    /* 14 */   XD(55, 1),
    /* 15 */   XDHEH(56, 1),
    /* 16 */   DADWR(57, 1),
    /* 17 */   RANDOMNOOB(12, 1),
    /* 18 */   RANDOMOK(19, 1),
    /* 19 */   SEC(58, 2),
    /* 20 */   INFERNAL(60, 1),
    /* 21 */   SECMEN(59, 1),
    /* 22 */   WEW(61, 1),
    /* 23 */   SUICISNUMBERONE(62, 1),
    /* 24 */   WEWXD(63, 2),
    /* 25 */   WEXDWXD(64, 1),
    /* 26 */   WEXDDWXD(65, 1),
    /* 27 */   RANDOMBRO(25, 1);
    /*    */
    /*    */   private final int material_id;
    /*    */   private final int speed;
    /*    */
    /* 32 */   private AnimatedTextureStore(int material_id, int speed) { this.material_id = material_id;
        /* 33 */     this.speed = speed;
        /*    */   }
    /*    */
    /*    */   public int getId()
    /*    */   {
        /* 38 */     return this.material_id;
        /*    */   }
    /*    */
    /*    */   public int getSpeed() {
        /* 42 */     return this.speed;
        /*    */   }
    /*    */ }

