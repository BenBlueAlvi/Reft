package com.ue.reft;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor.SystemCursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class ReftGame extends Game {
	final static int viewWidth = 640;
	private final static int viewHeight = 480;
	public static LabelStyle font;
	static timerThread timerthread = new timerThread("timer");
	
	@Override
	public void create() {
		
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("assets/AnonymousPro-Regular.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 12;
		BitmapFont theFont = generator.generateFont(parameter); // font size 12 pixels
		generator.dispose(); // don't forget to dispose to avoid memory leaks!
	
	
		font = new LabelStyle(theFont, Color.WHITE);
		
		MenuScreen ms = new MenuScreen(this);
		
		setScreen(ms);
	}

	public void startTimer(){
		timerthread.start();
	}
	
	public static int getViewHeight() {
		return viewHeight;
	}

	public static int getViewWidth() {
		
		return viewWidth;
	}
	
}
