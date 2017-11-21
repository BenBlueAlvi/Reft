package com.ue.reft.commands;

public class CommandList {
	
	public Command[] commands;
	
	public CommandList(Command...c){
		this.commands = c;
	}
	
	

	public static CommandList worldCommands = new CommandList(new Travel());
	
}
