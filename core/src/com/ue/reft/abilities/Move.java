package com.ue.reft.abilities;

import com.ue.reft.Entity;

public class Move extends Ability{

	public Move(Entity owner) {
		super(owner);
		this.range = 5;
		this.name = "Move";
		this.timeCost = 1;
	}

}
