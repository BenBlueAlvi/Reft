package com.ue.reft.abilities;

import com.ue.reft.Entity;
import com.ue.reft.Skills;
import com.ue.reft.Slots;
import com.ue.reft.Sources;
import com.ue.reft.Utils;
import com.ue.reft.items.Item;


public class BasicAttack extends Ability{

	
	
	public BasicAttack(Entity owner) {
		super(owner, Sources.Lifeforce);
	
		this.range = 100;
		this.AOE = 0;
		this.name = "Attack";
		this.timeCost = 1;
		this.cost = 5;
	}
	
	@Override
	public void useOn(Entity target){
		Item usedWeapon = owner.equipment.get(Slots.MAINHAND);
		if (owner.stamina > usedWeapon.cost){
		
		} else {
			
		}
		
	
	}

}
