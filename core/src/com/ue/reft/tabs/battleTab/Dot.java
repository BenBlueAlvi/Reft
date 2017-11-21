package com.ue.reft.tabs.battleTab;

import com.badlogic.gdx.math.Vector2;
import com.ue.reft.BaseActor;
import com.ue.reft.Entity;
import com.ue.reft.ReftGame;

public class Dot extends BaseActor{
	
	public Vector2 pos = new Vector2();
	public Entity entity;
	
	public Dot(String path) {
		super(path);
		
	}
	
	@Override
	public void act(float dt){
		super.act(dt);
		this.pos.x = (this.getX() - ReftGame.getViewWidth()/3 - 12)/16;
		this.pos.y = -((this.getY() + 55 - ReftGame.getViewHeight())/16);
	
	}
	
	public Tile[] getAdjTiles(Tile[][] map){
		
		Tile[] adjs = {
				map[(int) (this.pos.y)][(int) this.pos.x], 
				map[(int) (this.pos.y) + 1][(int) this.pos.x], 
				map[(int) (this.pos.y) - 1][(int) this.pos.x], 
				map[(int) (this.pos.y)][(int) this.pos.x + 1], 
				map[(int) (this.pos.y)][(int) this.pos.x - 1], 
				map[(int) (this.pos.y)+ 1][(int) this.pos.x + 1], 
				map[(int) (this.pos.y) - 1][(int) this.pos.x - 1], 
				map[(int) (this.pos.y) + 1][(int) this.pos.x - 1], 
				map[(int) (this.pos.y) - 1][(int) this.pos.x + 1], 
				
				};
		return adjs;
	}
		
		
		
	
	
	

}
