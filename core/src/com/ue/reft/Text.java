package com.ue.reft;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Text extends BaseActor{
	private Label text;
	public Text(CharSequence text) {
		super("assets/null.png");
		this.text = new Label(text, ReftGame.font);
		this.text.setColor(Color.WHITE);
		updateHitbox();
		this.addActor(this.text);
	}
	
	public Text(CharSequence text, Color c) {
		super("assets/null.png");
		this.text = new Label(text, ReftGame.font);
		this.text.setColor(c);
		updateHitbox();
		this.addActor(this.text);
	}
	
	public Label getLabel(){
		return this.text;
	}
	public void setText(String text){
		this.text.setText(text);
		updateHitbox();
	}
	public void setColor(Color c){
		this.text.setColor(c);
	}
	
	private void updateHitbox(){
		float width = text.getText().length * 10;
		float height = 16;
		float[] vertices = {0,0, width,0, width,height, 0,height};
		boundingPolygon = new Polygon(vertices);
		boundary.set(0,0, width,height);
	}

}
