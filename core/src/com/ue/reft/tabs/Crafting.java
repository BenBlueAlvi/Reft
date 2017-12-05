package com.ue.reft.tabs;

import com.ue.reft.Entity;

public class Crafting extends Tab{
	
	public Crafting () {
		super();
	}
	
	public void open(Entity p){
		this.setVisible(true);
	}
	
	public void close(){
		//put stuff back in inven
		this.setVisible(false);
	}
	
	public void update(){
		
	}
}
