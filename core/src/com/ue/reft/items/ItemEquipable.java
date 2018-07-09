package com.ue.reft.items;

import com.ue.reft.Skills;
import com.ue.reft.Slots;
import com.ue.reft.Stats;


public class ItemEquipable extends Item{
	
	
	private int[] statBonuses = new int[Stats.values().length];
	private int[] skillBonuses = new int[Skills.values().length];
	public int mass;
	public int cost;
	public int style;
	public int damage;
	public boolean unwieldly;
	
	public Slots slot;
	
	//def
	public int durability;
	public float coverage;
	public float absorbsion;
	
	public ItemEquipable(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	public int getStat(Stats stat){
		return this.statBonuses[stat.ordinal()];
	}
	public void setStat(Stats stat, int num){
		this.statBonuses[stat.ordinal()] = num;
	}
	

}
