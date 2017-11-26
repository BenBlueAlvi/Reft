package com.ue.reft;

public enum Sources {
	Lifeforce, Soul;
	
	public String useAbv;
	
	static{
		Lifeforce.useAbv = "stamina";
		Soul.useAbv = "mana";
	}
	
}
