package com.ue.reft.commands;

import java.util.ArrayList;

import com.ue.reft.Entity;
import com.ue.reft.world.World;

public class Command {
	
	public String name = "";
	public ArrayList<Command> subCommands = new ArrayList<Command>();
	public int index;
	
	public Command(String name){
		this.name = name;
	}
	
	/**
     *runs the command
     *@param p the player entity
     *@param e another entity
     *@param subIndex the selected subcommand index
     */
	public void run(Entity p, Entity e, int subIndex){
		
	}
	
	/**
     *runs the command
     *@param p the player entity
     *@param w the world
     *@param subIndex the selected subcommand index
     */
	public void run(Entity p, World w, int subIndex){
		
	}
	
	public Command getSubCommand(){
		return this.subCommands.get(index);
	}
	
	
	public Command getSubCommand(int index){
		return this.subCommands.get(index);
	}
	
	/**
     *sets all of the subcommands of this command to the parameter
     *@param s the array of commands to be sub commands
     */
	public void setSubCommands(Command...s){
		for (int i = 0; i < s.length; i ++){
			this.subCommands.set(i, s[i]);
		}
	}
	
	/**
     *adds a list of subcommands
     *@param s the array of commands to be added
     */
	public void addSubCommands(Command...s){
		for (Command ss : s){
			this.subCommands.add(ss);
		}
		
	}
}
