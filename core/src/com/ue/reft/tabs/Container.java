package com.ue.reft.tabs;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.ue.reft.BaseActor;
import com.ue.reft.ReftGame;
import com.ue.reft.Text;

public class Container<T> extends BaseActor{
	
	private T thing;
	public Text title;
	public int listNum;
	
	
	/**
     * Used to store things in the ui and give them hitboxes
     * @param thing the thing to be stored in the container
     * @param x the x-pos of the container
     * @param y the y-pos of the container
     */
	public Container(T thing, float x, float y){
		super("assets/null.png");
		this.setPosition(x, y);
		title = new Text("");
		this.addActor(title);
		this.setThing(thing);
		this.listNum = 0;
	
	
	}
	
	
	/**
     * Returns what is stored in the container
     * @return the thing stored in the container
     */
	
	public T getThing(){
		return this.thing;
	}
	
	/**
     * stores a thing in a container
     * @param the thing to be stored in the container
     */
	public void setThing(T thing){
		this.thing = thing;
		if (thing != null){
			this.title.setText(this.thing.toString());
	
			
		} else {
			this.title.setText("");
		}
		updateHitbox();

		
	}
	
	
	private void updateHitbox(){
		float width = title.getLabel().getText().length * 10;
		float height = 16;
		float[] vertices = {0,0, width,0, width,height, 0,height};
		boundingPolygon = new Polygon(vertices);
		boundary.set(0,0, width,height);
	}

}
