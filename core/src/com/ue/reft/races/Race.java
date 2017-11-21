package com.ue.reft.races;

import java.util.ArrayList;
import java.util.HashMap;

import com.ue.reft.BodyParts;
import com.ue.reft.Entity;
import com.ue.reft.Skills;
import com.ue.reft.abilities.Ability;

public abstract class Race {
	
	protected HashMap<Skills, Integer> scoreBonuses;
	protected Ability[] abilityBonuses;
	
	public String name;
	
	public int speed;
	
	public static ArrayList<Race> allRaces = new ArrayList<Race>();
	
	public String desc;
	
	public int[] bodyPartHitChances = new int[BodyParts.values().length];
	
	
	public Race(){
		this.name = "";
		this.desc = "";
		scoreBonuses = new HashMap<Skills, Integer>();
		abilityBonuses = new Ability[2];
		Race.allRaces.add(this);
	}
	
	public void apply(Entity e){
		for (Ability a : abilityBonuses){
			e.abilities.add(a);
		}
		
	
		
	}
	/*head, torso, rightLeg, leftLeg, rightFoot, leftFoot, rightArm, leftArm, rightHand, leftHand, neck;*/
	
	/**
	 * sets the hit chances for each body part
	 * @param head int % chance of being hit
	 * @param torso int % chance of being hit
	 * @param rightLeg int % chance of being hit
	 * @param leftLeg int % chance of being hit
	 * @param rightFoot int % chance of being hit
	 * @param leftFoot int % chance of being hit
	 * @param rightArm int % chance of being hit
	 * @param leftArm int % chance of being hit
	 * @param rightHand int % chance of being hit
	 * @param leftHand int % chance of being hit
	 * @param neck int % chance of being hit
	 */
	
	protected void setBodyPartHitChances(int head, int torso, int rightLeg, int leftLeg, int rightFoot, int leftFoot, int rightArm, int leftArm, int rightHand, int leftHand, int neck){
		this.bodyPartHitChances[0] = head;
		this.bodyPartHitChances[1] = torso;
		this.bodyPartHitChances[2] = rightLeg;
		this.bodyPartHitChances[3] = leftLeg;
		this.bodyPartHitChances[4] = rightFoot;
		this.bodyPartHitChances[5] = leftFoot;
		this.bodyPartHitChances[6] = rightArm;
		this.bodyPartHitChances[7] = leftArm;
		this.bodyPartHitChances[8] = rightHand;
		this.bodyPartHitChances[9] = leftHand;
		this.bodyPartHitChances[10] = neck;
	}
	
}
