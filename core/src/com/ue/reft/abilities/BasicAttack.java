package com.ue.reft.abilities;

import com.ue.reft.Entity;
import com.ue.reft.Pos;
import com.ue.reft.Skills;
import com.ue.reft.Slots;
import com.ue.reft.Sources;
import com.ue.reft.Telegraph;
import com.ue.reft.Utils;
import com.ue.reft.items.Item;


public class BasicAttack extends Ability{

	
	
	public BasicAttack(Entity owner) {
		super(owner, Sources.Lifeforce);
	
		this.range = 100;
		this.AOE = 0;
		this.name = "Attack";
		this.timeCost = 1;
		this.cost = 0;
		this.telegraph = new Telegraph(new Pos(1,1));
	}
	
	@Override
	public void useOn(Entity target){
		Item usedWeapon = owner.getEquips().get(Slots.MAINHAND);
	
		
	
	}

}
