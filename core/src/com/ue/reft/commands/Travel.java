package com.ue.reft.commands;

import com.ue.reft.Entity;
import com.ue.reft.world.World;

public class Travel extends Command{

	public Travel() {
		super("Travel");
		this.addSubCommands(new Command("North"), new Command("South"), new Command("East"), new Command("West"));
		
	}
	
	@Override
	public void run(Entity p, World w, int subIndex){
		
		switch (subIndex){
		
		case 0:
			w.y -= 1;
			break;
		case 1:
			w.y += 1;
			break;
		case 2:
			w.x += 1;
			break;
		case 3:
			w.x -=1;
			break;
		}
		
		
		
		w.print();
	}
	
}
