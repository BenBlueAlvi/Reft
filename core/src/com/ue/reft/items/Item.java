package com.ue.reft.items;

import com.ue.reft.Entity;
import com.ue.reft.Utils.DamageCurve;

public abstract class Item {
	
	public String name;
	
	public Entity owner;
	
	public int strBonus;
	public int strPerBonus;
	public int mass;
	public int cost;
	public int style;
	public DamageCurve damage;
	
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
	
}
