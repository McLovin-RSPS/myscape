package com.arlania.Commands;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.arlania.model.PlayerRights;

@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE) //on class level
	public @interface CommandInfo {
	    String[] command() default "";
	    String description() default "";
	    int donationAmountRequired() default 0;
	    PlayerRights[] rights() default PlayerRights.PLAYER;
	}//sec intellij opening kk
