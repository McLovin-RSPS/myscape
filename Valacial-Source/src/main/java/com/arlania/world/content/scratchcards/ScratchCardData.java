package com.arlania.world.content.scratchcards;

public enum ScratchCardData {

	IndanHelmet(4636),IndanBody(4637),IndanLegs(4638),IndanBoots(4639),IndanBow(4640),staff(3246),muske(4641),muske1(4643)
	,muske2(4642),muske3(4644),muske4(4645);
	private int displayId;


	ScratchCardData(int displayId) {
		this.displayId = displayId;
	}

	public int getDisplayId() {
		return displayId;
	}

	public void setDisplayId(int displayId) {
		this.displayId = displayId;
	}

}