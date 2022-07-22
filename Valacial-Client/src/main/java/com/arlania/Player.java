package com.arlania;

import java.util.Arrays;

public final class Player extends Entity {
    static MemCache modelCache = new MemCache(260);
    public final int[] equipment;
    final int[] anIntArray1700;
    public int frontLight = 68;
    public int backLight = 820;
    public int rightLight = 0;
    public int middleLight = -1;
    public int leftLight = 0;
    public int[][] modifiedColors = new int[12][];
    public int constitution;
    public int maxConstitution;


    public int playerRights;


    public String playerTitle;
    public NPCDef desc;
    public int team;
    public int myGender;
    public String name;
    public int combatLevel;
    public int headIcon;
    public int bountyHunterIcon;
    public int hintIcon;
    public boolean skulled;
    public int summonLevel;
    public int SummonLevel;
    public int startTimeTransform;
    boolean aBoolean1699;
    int transformedTimer;
    int z;
    boolean visible;
    int resizeX;
    int resizeZ;
    int resizeY;
    Model tranformIntoModel;
    int extendedXMin;
    int extendedYMin;
    int extendedXMax;
    int extendedYMax;
    boolean updateColor = true;
    private int defaultParticleColor = 9883875;
    private int maxCapeParticleColor = this.defaultParticleColor;
    private long aLong1697;
    private int anInt1715;
    private long aLong1718;
    Player() {
        this.aLong1697 = -1L;
        this.aBoolean1699 = false;
        this.anIntArray1700 = new int[5];
        this.visible = false;
        this.anInt1715 = 9;
        this.equipment = new int[12];
    }

    public Model getRotatedModel() {
        if (!this.visible)
            return null;
        Model model = method452();
        if (model == null)
            return null;
        this.height = model.modelHeight;
        model.rendersWithinOneTile = true;
        if (this.aBoolean1699)
            return model;
        if ((this.anInt1520 != -1) && (this.currentAnim != -1)) {
            SpotAnim spotAnim = SpotAnim.cache[this.anInt1520];
            Model model_2 = spotAnim.getModel();
            if ((spotAnim.animation != null) &&
                    (FrameReader.animationlist[Integer.parseInt(Integer.toHexString(spotAnim.animation.frameIDs[0]).substring(0, Integer.toHexString(spotAnim.animation.frameIDs[0])
                            .length() - 4), 16)].length == 0)) {
                model_2 = null;
            }


            if (model_2 != null) {
                Model model_3 = new Model(true, FrameReader.isNullFrame(this.currentAnim), false, model_2);
                model_3.translate(0, -this.graphicHeight, 0);
                model_3.createBones();
                model_3.scaleT(132, 132, 132);
                model_3.applyTransform(spotAnim.animation.frameIDs[this.currentAnim]);
                model_3.triangleSkin = null;
                model_3.vertexSkin = null;
                if ((spotAnim.sizeXY != 128) || (spotAnim.sizeZ != 128))
                    model_3.scaleT(spotAnim.sizeXY, spotAnim.sizeXY, spotAnim.sizeZ);
                model_3.light(64 + spotAnim.shadow, 850 + spotAnim.lightness, -30, -50, -30, true);
                Model[] aclass30_sub2_sub4_sub6_1s = {model, model_3};
                model = new Model(aclass30_sub2_sub4_sub6_1s);
            }
        }
        if (this.tranformIntoModel != null) {
            if (Client.loopCycle >= this.transformedTimer)
                this.tranformIntoModel = null;
            if ((Client.loopCycle >= this.startTimeTransform) && (Client.loopCycle < this.transformedTimer)) {
                Model model_1 = this.tranformIntoModel;
                model_1.light(this.frontLight, this.backLight, this.rightLight, this.middleLight, this.leftLight, true);
                if (this.turnDirection == 512) {
                    model_1.rotateBy90();
                    model_1.rotateBy90();
                    model_1.rotateBy90();
                } else if (this.turnDirection == 1024) {
                    model_1.rotateBy90();
                    model_1.rotateBy90();
                } else if (this.turnDirection == 1536) {
                    model_1.rotateBy90();
                }
                Model[] models = {model, model_1};
                model = new Model(models);
                if (this.turnDirection == 512) {
                    model_1.rotateBy90();
                } else if (this.turnDirection == 1024) {
                    model_1.rotateBy90();
                    model_1.rotateBy90();
                } else if (this.turnDirection == 1536) {
                    model_1.rotateBy90();
                    model_1.rotateBy90();
                    model_1.rotateBy90();
                }
                model_1.translate(this.x - this.resizeX, this.z - this.resizeZ, this.y - this.resizeY);
            }
        }
        model.rendersWithinOneTile = true;
        return model;
    }

    public void updatePlayerAppearance(Stream stream) {
        stream.currentOffset = 0;
        this.myGender = stream.readUnsignedByte();
        this.headIcon = stream.readUnsignedByte();
        this.bountyHunterIcon = stream.readUnsignedByte();
        this.skulled = (stream.readUnsignedWord() == 1);
        if ((this.bountyHunterIcon > 4) && (this.bountyHunterIcon != 255))
            this.bountyHunterIcon = 4;
        this.desc = null;
        this.team = 0;
        for (int partId = 0; partId < 12; partId++) {
            int firstByte = stream.readUnsignedByte();
            if (firstByte == 0) {
                this.equipment[partId] = 0;
            } else {
                int secondByte = stream.readUnsignedByte();
                this.equipment[partId] = ((firstByte << 8) + secondByte);
                if ((partId == 0) && (this.equipment[0] == 65535)) {
                    this.desc = NPCDef.forID(stream.readUnsignedWord());
                    break;
                }
                if (partId == 1) {
                    int length = stream.readUnsignedByte();
                    if (length > 0) {
                        int[] colors = new int[length];
                        for (int i = 0; i < length; i++) {
                            colors[i] = stream.readInt();
                        }

                        if (!Arrays.equals(modifiedColors[partId], colors)) {
                            modelCache.clear();
                        }
                        modifiedColors[partId] = colors;
                        this.maxCapeParticleColor = JagexColor.toRGB(colors[0]);
                    } else {
                        if (modifiedColors[partId] != null) {
                            modelCache.clear();
                        }
                        modifiedColors[partId] = null;
                        this.maxCapeParticleColor = defaultParticleColor;
                    }
                    if (this == Client.myPlayer) {
                        Client.getClient().updateMaxCapeColors(this.modifiedColors[partId]);
                    }
                }
                if (partId == 8)
                    Client.myHeadAndJaw[0] = (this.equipment[partId] - 256);
                if (partId == 11)
                    Client.myHeadAndJaw[1] = (this.equipment[partId] - 256);
                if ((this.equipment[partId] >= 512) && (this.equipment[partId] - 512 < ItemDef.totalItems)) {
                    int l1 = ItemDef.forID(this.equipment[partId] - 512).team;
                    if (l1 != 0)
                        this.team = l1;
                }
            }
        }
        for (int l = 0; l < 5; l++) {
            int j1 = stream.readUnsignedByte();
            if ((j1 < 0) || (j1 >= Client.anIntArrayArray1003[l].length))
                j1 = 0;
            this.anIntArray1700[l] = j1;
        }

        this.anInt1511 = stream.readUnsignedWord();
        if (this.anInt1511 == 65535)
            this.anInt1511 = -1;
        this.anInt1512 = stream.readUnsignedWord();
        if (this.anInt1512 == 65535)
            this.anInt1512 = -1;
        this.anInt1554 = stream.readUnsignedWord();
        if (this.anInt1554 == 65535)
            this.anInt1554 = -1;
        this.anInt1555 = stream.readUnsignedWord();
        if (this.anInt1555 == 65535)
            this.anInt1555 = -1;
        this.anInt1556 = stream.readUnsignedWord();
        if (this.anInt1556 == 65535)
            this.anInt1556 = -1;
        this.anInt1557 = stream.readUnsignedWord();
        if (this.anInt1557 == 65535)
            this.anInt1557 = -1;
        this.runAnimation = stream.readUnsignedWord();
        if (this.runAnimation == 65535) {
            this.runAnimation = -1;
        }
        this.name = TextClass.fixName(TextClass.nameForLong(stream.readQWord()));
        this.combatLevel = stream.readUnsignedByte();
        this.playerRights = stream.readUnsignedWord();
        this.playerTitle = stream.readString();

        this.visible = true;
        this.aLong1718 = 0L;

        if (this.desc != null) {
            this.combatLevel = this.desc.combatLevel;
            this.anInt1511 = this.desc.standAnim;
            this.anInt1512 = this.desc.standAnim;
            this.anInt1554 = this.desc.walkAnim;
            this.anInt1555 = this.desc.standAnim;
            this.anInt1556 = this.desc.walkAnim;
            this.anInt1557 = this.desc.walkAnim;
            this.runAnimation = this.desc.walkAnim;
        }


        for (int k1 = 0; k1 < 12; k1++) {
            this.aLong1718 <<= 4;
            if (this.equipment[k1] >= 256) {
                this.aLong1718 += this.equipment[k1] - 256;
            }
        }
        if (this.equipment[0] >= 256)
            this.aLong1718 += (this.equipment[0] - 256 >> 4);
        if (this.equipment[1] >= 256)
            this.aLong1718 += (this.equipment[1] - 256 >> 8);
        for (int i2 = 0; i2 < 5; i2++) {
            this.aLong1718 <<= 3;
            this.aLong1718 += this.anIntArray1700[i2];
        }

        this.aLong1718 <<= 1;
        this.aLong1718 += this.myGender;
    }

    public boolean maxCapeIds(int itemId) {
        return itemId == 14019;
    }

    public Model method452() {
        if (this.desc != null) {
            int currentFrame = -1;
            int nextFrame = -1;
            int cycle1 = 0;
            int cycle2 = 0;
            if ((this.anim >= 0) && (this.animationDelay == 0)) {
                Animation animation = Animation.anims[this.anim];
                currentFrame = animation.frameIDs[this.currentAnimFrame];
                nextFrame = animation.frameIDs[this.nextAnimationFrame];
                cycle1 = animation.delays[this.currentAnimFrame];
                cycle2 = this.anInt1528;
            } else if (this.anInt1517 >= 0) {
                Animation animation = Animation.anims[this.anInt1517];
                currentFrame = animation.frameIDs[this.currentForcedAnimFrame];
                nextFrame = animation.frameIDs[this.nextIdleAnimationFrame];
                cycle1 = animation.delays[this.currentForcedAnimFrame];
                cycle2 = this.anInt1519;
            }
            Model model = this.desc.method164(-1, currentFrame, null, nextFrame, cycle1, cycle2);
            return model;
        }


        long l = this.aLong1718;
        int currentFrame = -1;
        int nextFrame = -1;
        int cycle1 = 0;
        int cycle2 = 0;
        int i1 = -1;
        int j1 = -1;
        int k1 = -1;
        if ((this.anim >= 0) && (this.animationDelay == 0)) {
            Animation animation = Animation.anims[this.anim];
            currentFrame = animation.frameIDs[this.currentAnimFrame];
            if (this.nextAnimationFrame < animation.frameIDs.length)
                nextFrame = animation.frameIDs[this.nextAnimationFrame];
            cycle1 = animation.delays[this.currentAnimFrame];
            cycle2 = this.anInt1528;
            if ((this.anInt1517 >= 0) && (this.anInt1517 != this.anInt1511))
                i1 = Animation.anims[this.anInt1517].frameIDs[this.currentForcedAnimFrame];
            if (animation.leftHandItem >= 0) {
                j1 = animation.leftHandItem;
                l += (j1 - this.equipment[5] << 40);
            }
            if (animation.rightHandItem >= 0) {
                k1 = animation.rightHandItem;
                l += (k1 - this.equipment[3] << 48);
            }
        } else if (this.anInt1517 >= 0) {
            Animation animation = Animation.anims[this.anInt1517];
            currentFrame = animation.frameIDs[this.currentForcedAnimFrame];
            nextFrame = animation.frameIDs[this.nextIdleAnimationFrame];
            cycle1 = animation.delays[this.currentForcedAnimFrame];
            cycle2 = this.anInt1519;
        }


        Model model_1 = (Model) modelCache.get(l);
        if (model_1 == null) {
            boolean fetchModels = false;
            for (int bodyPartId = 0; bodyPartId < 12; bodyPartId++) {
                int partId = this.equipment[bodyPartId];
                if ((k1 >= 0) && (bodyPartId == 3))
                    partId = k1;
                if ((j1 >= 0) && (bodyPartId == 5))
                    partId = j1;
                if (partId - 256 != 846) {
                    if ((partId >= 256) && (partId < 512) && (!IDK.cache[(partId - 256)].bodyModelIsFetched()))
                        fetchModels = true;
                    if ((partId >= 512) && (!ItemDef.forID(partId - 512).equipModelFetched(this.myGender))) {
                        fetchModels = true;
                    }
                }
            }

            if (fetchModels) {
                if (this.aLong1697 != -1L)
                    model_1 = (Model) modelCache.get(this.aLong1697);
                if (model_1 == null)
                    return null;
            }
        }
        boolean hasMaxCape = false;
        if (model_1 == null) {
            Model[] bodyPartModels = new Model[13];
            int j2 = 0;
            for (int currentPart = 0; currentPart < 12; currentPart++) {
                int i3 = this.equipment[currentPart];
                if ((k1 >= 0) && (currentPart == 3))
                    i3 = k1;
                if ((j1 >= 0) && (currentPart == 5))
                    i3 = j1;
                if ((i3 >= 256) && (i3 < 512)) {
                    Model model_3 = null;
                    if (i3 - 256 < IDK.cache.length)
                        model_3 = IDK.cache[(i3 - 256)].fetchBodyModel();
                    if (model_3 != null)
                        bodyPartModels[(j2++)] = model_3;
                }
                if (i3 >= 512) {
                    int itemId = i3 - 512;
                    ItemDef def = ItemDef.forID(i3 - 512);
                    Model model_4 = def.getEquipModel(this.myGender);
                    if (maxCapeIds(itemId)) {
                        hasMaxCape = true;
                    }
                    if (this.modifiedColors[currentPart] != null) {
                        for (int i11 = 0; i11 < def.editedModelColor.length; i11++)
                            model_4.recolour(def.editedModelColor[i11], this.modifiedColors[currentPart][i11]);
                    }
                    if (model_4 != null) {
                        bodyPartModels[(j2++)] = model_4;
                    }
                }
            }
            model_1 = new Model(j2, bodyPartModels);
            for (int j3 = 0; j3 < 5; j3++) {
                if (this.anIntArray1700[j3] != 0) {
                    model_1.recolour(Client.anIntArrayArray1003[j3][0], Client.anIntArrayArray1003[j3][this.anIntArray1700[j3]]);
                    if (j3 == 1)
                        model_1.recolour(Client.anIntArray1204[0], Client.anIntArray1204[this.anIntArray1700[j3]]);
                }
            }
            model_1.createBones();
            model_1.light(this.frontLight, this.backLight, this.rightLight, this.middleLight, this.leftLight, true);
            modelCache.put(model_1, l);
            this.aLong1697 = l;
        }
        if (this.aBoolean1699)
            return model_1;
        Model model_2 = Model.entityModelDesc;
        model_2.method464(model_1, FrameReader.isNullFrame(currentFrame) & FrameReader.isNullFrame(i1));
        if ((currentFrame != -1) && (i1 != -1)) {
            model_2.method471(Animation.anims[this.anim].animationFlowControl, i1, currentFrame);
        } else if ((currentFrame != -1) && (nextFrame != -1)) {
            model_2.applyTransform(currentFrame, nextFrame, cycle1, cycle2);
        } else
            model_2.applyTransform(currentFrame);
        model_2.calculateDiagonals();
        model_2.triangleSkin = null;
        model_2.vertexSkin = null;

        return model_2;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public Model getPlayerModel() {
        if (!this.visible)
            return null;
        if (this.desc != null)
            return this.desc.getHeadModel();
        boolean flag = false;
        for (int i = 0; i < 12; i++) {
            int j = this.equipment[i];
            try {
                if ((j >= 256) && (j < 512) && (!IDK.cache[(j - 256)].headModelFetched()))
                    flag = true;
                if ((j >= 512) && (!ItemDef.forID(j - 512).dialogueModelFetched(this.myGender))) {
                    flag = true;
                }
            } catch (Exception e) {
                flag = true;
            }
        }

        if (flag)
            return null;
        Model[] models = new Model[12];
        int k = 0;
        for (int l = 0; l < 12; l++) {
            int i1 = this.equipment[l];
            if ((i1 >= 256) && (i1 < 512)) {
                Model model_1 = IDK.cache[(i1 - 256)].fetchHeadModel();
                if (model_1 != null)
                    models[(k++)] = model_1;
            }
            if (i1 >= 512) {
                Model model_2 = ItemDef.forID(i1 - 512).getDialogueModel(this.myGender);
                if (model_2 != null) {
                    models[(k++)] = model_2;
                }
            }
        }
        Model model = new Model(k, models);
        for (int j1 = 0; j1 < 5; j1++) {
            if (this.anIntArray1700[j1] != 0) {
                model.recolour(Client.anIntArrayArray1003[j1][0], Client.anIntArrayArray1003[j1][this.anIntArray1700[j1]]);
                if (j1 == 1)
                    model.recolour(Client.anIntArray1204[0], Client.anIntArray1204[this.anIntArray1700[j1]]);
            }
        }
        return model;
    }
}
