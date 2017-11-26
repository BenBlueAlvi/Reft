package com.ue.reft.items;

import com.ue.reft.Entity;
import com.ue.reft.Skills;
import com.ue.reft.Stats;
import com.ue.reft.Utils.DamageCurve;

public abstract class Item {
	
	public String name;
	
	public Entity owner;
	
	private int[] statBonuses = new int[Stats.values().length];
	private int[] skillBonuses = new int[Skills.values().length];
	public int mass;
	public int cost;
	public int style;
	public DamageCurve damage;
	public boolean unwieldly;
	
	//def
	public int durability;
	public float coverage;
	public float absorbsion;
	
	public Item(String name){
		this.name = name;
	}
	
	@Override
	public String toString(){
		return name;
		
	}
	
	public int getStat(Stats stat){
		return this.statBonuses[stat.ordinal()];
	}
	public void setStat(Stats stat, int num){
		this.statBonuses[stat.ordinal()] = num;
	}
	
}
