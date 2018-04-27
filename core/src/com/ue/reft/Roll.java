package com.ue.reft;

import com.badlogic.gdx.math.MathUtils;

public class Roll {
	

	private int count;
	private int rand;
	
	public Roll(String roll){
		this.count = Integer.parseInt(roll.split("d")[0]);
		this.rand = Integer.parseInt(roll.split("d")[1]);
	}
	
	public int roll(){
		int total = 0;
		for (int i = 1; i <= count; i ++){
			total += MathUtils.random(1, rand);
		}
		return total;
			
	}
	
	public static int roll(String roll) {
		int count = Integer.parseInt(roll.split("d")[0]);
		int rand = Integer.parseInt(roll.split("d")[1]);
		int total = 0;
		for (int i = 1; i <= count; i ++){
			total += MathUtils.random(1, rand);
		}
		return total;
	}
}
