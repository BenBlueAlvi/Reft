package com.ue.reft.commands;

import com.ue.reft.Entity;
import com.ue.reft.world.World;

public class Interact extends Command{

	public Interact() {
		super("Interact");
	
	}
	
	@Override
	public void run(Entity p, World w, int subIndex){
		w.getSector(w.x, w.y).features[subIndex].interact();
	}

}
