package com.ue.reft.world;

public class Sector {

	public static Sector[][] sectors;
	
	
	public Biome biome;
	public int encounterChance;
	public String desc;
	public Feature[] features;
	
	public Sector(String desc, Feature...features){
		this.desc = desc;
		this.features = features;
	}
	
	
	
	public String print(){
		String s = this.desc;
		for (Feature f : features){
			s += " " + f.desc;
		}
		return s;
	}
	
}
