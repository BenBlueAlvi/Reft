package com.ue.reft.races;

import com.ue.reft.BodyParts;
import com.ue.reft.Skills;
import com.ue.reft.Stats;

public class Elf extends Race{
	
	
	
	
	
	
	
	public Elf(){
		super();
		this.name = "Elf";
		this.speed = 6;
		this.statBonuses.put(Stats.alterness, 2);
		this.statBonuses.put(Stats.coordination, 2);
		this.statBonuses.put(Stats.presence, 1);
		this.statBonuses.put(Stats.will, 3);
		this.statBonuses.put(Stats.vul, 1);
		
		this.skillBonuses.put(Skills.herbology, 5);
		this.skillBonuses.put(Skills.arcana, 5);
		this.skillBonuses.put(Skills.reading, 5);
	
		
		this.desc = "";
		//30 + 30 = 60 + 30 = 90
		this.setBodyPartHitChances(BodyParts.humanoid);
		
	}
	
	
	
}

