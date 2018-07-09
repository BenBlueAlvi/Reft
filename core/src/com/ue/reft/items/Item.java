package com.ue.reft.items;

import com.ue.reft.Entity;
import com.ue.reft.Skills;
import com.ue.reft.Stats;


public abstract class Item {
	
	public String name;
	
	public Entity owner;
	
	public String[] desc = new String[12];
	
	public Item(String name){
		this.name = name;
	}
	
	@Override
	public String toString(){
		return name;
		
	}
	
	public void setDesc(String...desc) {
		for (int i = 0; i < this.desc.length; i++) {
			if (i < desc.length) {
				this.desc[i] = desc[i];
			} else {
				this.desc[i] = "";
			}
			
		}
		
	}
	
	//event stuff ----------------
	public void onObtain(Entity e) {
		
	}
	

}
