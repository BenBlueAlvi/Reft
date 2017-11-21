package com.ue.reft;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class Option extends BaseActor{

	private Label text;
	public int id;
	
	public Option() {
		super("assets/option.png");
		id = 0;
		BitmapFont font = new BitmapFont();
		LabelStyle normStyle = new LabelStyle(font, Color.WHITE);
		text = new Label("", normStyle);
		text.setPosition(2, 9);
		this.addActor(text);
	}
	
	public void setText(String newText){
		this.text.setText(newText);
	}

}
