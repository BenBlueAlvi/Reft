package com.ue.reft;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.math.MathUtils;
import com.ue.reft.abilities.Ability;
import com.ue.reft.items.Item;
import com.ue.reft.races.Race;

public class Entity {
	
	
	public int health; // hp
	public int lifeForceCap; //max hp;
	public int mana; // mp
	public int manaCap; //max mana;
	public int stamina;
	public int maxStamina;
	
	public int strength;
	
	public int acc;
	public int dodge;
	
	public int vul;
	public int crit;
	
	public int will;
	
	public int sanity;
	
	public int reputation;
	
	public String name;
	public String title;
	public Race race;
	
	public int powerLevel;
	public int xp;
	public int lvl;

	public ArrayList<Ability> abilities = new ArrayList<Ability>();
	public ArrayList<Item> inventory = new ArrayList<Item>();
	public HashMap<Slots, Item> equipment = new HashMap<Slots, Item>();
	public int[] skills = new int[Skills.values().length];
	
	public int[] battlePos = {0,0};
	
	
	
	public Entity(){
		this.name = "No one";
		for (Slots s : Slots.values()){
			equipment.put(s, null);
		}
		
	}
	
	/**
	 * Calculates which body part was hit 
	 * @return the body part that was hit as a BodyParts
	 */
	
	public BodyParts calcHitBodyPart(){
		int hitNum = MathUtils.random(1, 100);
		int bodyPart = 0;
		if (hitNum < this.race.bodyPartHitChances[0]){
			bodyPart = 0;
		} else if (hitNum > this.race.bodyPartHitChances[0] && hitNum <= this.race.bodyPartHitChances[1] + this.race.bodyPartHitChances[0]){
			bodyPart = 1;
		} else if (hitNum > this.race.bodyPartHitChances[1] + this.race.bodyPartHitChances[0]  && hitNum <= this.race.bodyPartHitChances[2] + this.race.bodyPartHitChances[1] + this.race.bodyPartHitChances[0]){
			bodyPart = 1;
		}
		
		int lowerBound = 0;
		int upperBound = 0;
		for (int i = 0; i < this.race.bodyPartHitChances.length; i++){
			upperBound += this.race.bodyPartHitChances[i];
			if (hitNum > lowerBound && hitNum <= upperBound){
				bodyPart = i;
				break;
			}
			lowerBound += this.race.bodyPartHitChances[i];
		}
		
		return BodyParts.values()[bodyPart];
	}
	

	
	
}
