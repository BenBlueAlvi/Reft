package com.ue.reft.tabs;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.ue.reft.BaseActor;
import com.ue.reft.Entity;
import com.ue.reft.ReftGame;

public abstract class Tab extends BaseActor{

	private ShapeRenderer background = new ShapeRenderer();
	public boolean isOpen;
	public Tab() {
		super("assets/blackground.png");
		background.setColor(Color.BLACK);
		// TODO Auto-generated constructor stub
		
	}
	
	
	/**
     * Draws a black background
     * 
     */
	public void drawBackground(){

		background.begin(ShapeType.Filled);
		background.rect(0, 0, ReftGame.getViewWidth(), ReftGame.getViewWidth());
		background.end();
		
	}
	@Override
	public void draw(Batch batch, float parentAlpha){
		super.draw(batch, parentAlpha);
	}
	
	
	/**
     * Displays the tab on screen and update its content. 
     * The tab will display on top of all other open tabs
     * @param p the player entity
     */
	public void open(Entity p){}
	
	
	/**
    * Stops displaying the tab on screen
    *	
     */
	public void close(){}

}
