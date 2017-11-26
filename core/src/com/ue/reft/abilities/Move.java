package com.ue.reft.abilities;

import com.ue.reft.Entity;
import com.ue.reft.Sources;

public class Move extends Ability{

	public Move(Entity owner) {
		super(owner, Sources.Lifeforce);
		this.range = 5;
		this.name = "Move";
		this.timeCost = 1;
	}

}
