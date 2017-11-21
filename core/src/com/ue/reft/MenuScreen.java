package com.ue.reft;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class MenuScreen implements Screen{
	
	public Game game;
	private BaseActor menu;
	private Stage menuStage;
	
	public MenuScreen(Game g){
		game = g;
		create();
	}
	
	public void create(){
		menuStage = new Stage();
		
		//needs texture, pos, and added to the stage
		menu = new BaseActor("assets/menu.png");
		
		menu.setPosition(0, 0);
		menuStage.addActor(menu);
		
	}
	
	
	
	
	
	public void render(float delta) {
		
		if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) game.setScreen(new GameplayScreen(game));
			
		menuStage.act();
		Gdx.gl.glClearColor(0.8f, 0.8f, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		menuStage.draw();
		
		
		
	}

	@Override
	public void show() {
		
		
	}


	@Override
	public void resize(int width, int height) {
	
		
	}

	@Override
	public void pause() {
		
		
	}

	@Override
	public void resume() {
	
		
	}

	@Override
	public void hide() {
		
		
	}

	@Override
	public void dispose() {
		
		
	}
	

}
