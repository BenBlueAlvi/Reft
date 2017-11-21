package com.ue.reft.abilities;

import com.ue.reft.Entity;
import com.ue.reft.Skills;
import com.ue.reft.Sources;
import com.ue.reft.Utils.DamageCurve;

public abstract class Ability {
	
	public Skills score;
	public Entity owner;
	public String name;
	public Ability[] prerequisites;
	public int range;
	public int AOE;
	public int cost;
	public Sources type;
	public DamageCurve damage;
	public int xp;
	public int timeCost;
	
	public Ability(Entity owner){
		this.owner = owner;
		this.name = "";
	}
	
	/**
     *uses the ability
     *@param e the entity that the ability will be used on
     */
	
	public void useOn(Entity e){
		
	}
	
	@Override
	public String toString(){
		return this.name;
	}
	
}
