package com.cg.blog.application.entities;

public enum AwardType {

	SILVER(200), GOLD(500), PLATINUM(700), REGULAR(100);

	private int value;

	AwardType(int i) {
		this.value = i;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
