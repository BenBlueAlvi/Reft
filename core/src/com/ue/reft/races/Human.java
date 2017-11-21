package com.ue.reft.races;

import com.ue.reft.Skills;

public class Human extends Race{
	
	
	
	
	
	
	
	public Human(){
		super();
		this.name = "Human";
		this.speed = 5;
		
		
		this.desc = "The adapitble human, good at almost anything. You're sure to acomplish great things with this race";
		//30 + 30 = 60 + 30 = 90
		this.setBodyPartHitChances(10, 25, 15, 15, 3, 3, 11, 11, 3, 3, 1);
		
	}
	
	
	
}
