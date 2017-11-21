package com.ue.reft.tabs.battleTab;

public class Wall extends Tile{

	public Wall() {
		super("assets/wall.png");
		isStandable = false;
		isSolid = true;
		id = 'w';
	}

}
