package com.arlania;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class InterfaceNPC
{
  private final int[] modelArray;
  private final int modelArrayLength;
  private final int primaryModel;
  private final int secondaryModel;
  private final String name;
  private final String description;
  private static int animation;
  private static int animationDelay;
  public static int animationFrame;
  
  public InterfaceNPC(NPCDef entity)
  {
    this.modelArray = entity.models;
    this.modelArrayLength = entity.models.length;
    this.primaryModel = entity.models[0];
    if (entity.models.length > 1) {
      this.secondaryModel = entity.models[1];
    } else {
      this.secondaryModel = entity.models[0];
    }
    this.name = entity.name;
    this.description = entity.description;
    animation = entity.standAnim;
    if (animationFrame >= Animation.anims[animation].delays.length - 1) {
      animationFrame = 0;
    }
    animationDelay = Animation.anims[animation].delays[animationFrame];
  }
  
  public static void petAnimationStep()
  {
    if (updatePetAnimations) {
      return;
    }
    animationFrame += 1;
    if (animationFrame >= Animation.anims[animation].frameIDs.length) {
      animationFrame = 0;
    }
  }
  
  public static void updateAnimations()
  {
    ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    executorService.scheduleAtFixedRate(new Runnable()
    {
      public void run()
      {
        InterfaceNPC.isPetAnimationRunning = true;
        InterfaceNPC.petAnimationStep();
      }
    }, 0L, animationDelay == 0 ? 30 : animationDelay * 30, TimeUnit.MILLISECONDS);
  }

  
  public int getAnimationDelay()
  {
    return animationDelay;
  }
  
  public int getPrimaryModel()
  {
    return this.primaryModel;
  }
  
  public int getAnimation()
  {
    return animation;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public int getAnimationFrame()
  {
    return animationFrame;
  }
  
  public String getDescription()
  {
    return this.description;
  }
  
  public int getSecondaryModel()
  {
    return this.secondaryModel;
  }
  
  public int getModelArrayLength()
  {
    return this.modelArrayLength;
  }
  
  public int[] getModelArray()
  {
    return this.modelArray;
  }
  
  public int getPetSelected()
  {
    return npcSelected;
  }
  
  public void setPetSelected(int petID)
  {
    npcSelected = petID;
  }
  
  public static boolean updatePetAnimations = false;
  public static boolean isPetAnimationRunning = false;
  public static int npcSelected = 0;
}
