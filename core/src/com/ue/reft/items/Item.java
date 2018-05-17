package com.ue.reft.items;

import com.ue.reft.Entity;
import com.ue.reft.Skills;
import com.ue.reft.Stats;
import com.ue.reft.Utils.DamageCurve;

public abstract class Item {
	
	public String name;
	
	public Entity owner;
	
	public String[] desc;
	
	public Item(String name){
		this.name = name;
	}
	
	@Override
	public String toString(){
		return name;
		
	}
	
	public void setDesc(String...desc) {
		this.desc = desc;
	}
	
	//event stuff ----------------
	public void onObtain(Entity e) {
		
	}
	

}
