package com.ue.reft;

import java.util.HashMap;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Cursor.SystemCursor;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.ue.reft.abilities.BasicAttack;
import com.ue.reft.abilities.Move;
import com.ue.reft.items.Item;
import com.ue.reft.races.Human;
import com.ue.reft.tabs.InventoryTab;
import com.ue.reft.tabs.battleTab.BattleTab;
import com.ue.reft.tabs.battleTab.Floor;
import com.ue.reft.tabs.battleTab.Tile;
import com.ue.reft.tabs.battleTab.Wall;
import com.ue.reft.world.World;

public class GameplayScreen implements Screen{
	
	public Stage mainStage;
	public Stage uiStage;

	public static Printer printer = new Printer(25, true);
	public static InputProcess inputProcess;
	private Entity Player;
	private Entity testEnm;

			
			
	
	public Game game;
	private ShapeRenderer shapeRender;
	private Entity testEnm2;
	private Entity testEnm3;
	
	private InventoryTab inventoryTab;
	private BattleTab battleTab;
	private char f = new Floor().id;
	private char w = new Wall().id;
	private Character[][] map = {
			{w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w, w,w,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,w},
			{w,w,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,w, w,w,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,w},
			{w,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,w, w,w,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,w},
			{w,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,w, w,w,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,w},
			{w,f,f,f,f,w,f,f,f,f,f,f,f,f,f,f,f,f,w, w,w,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,w},
			{w,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,w, w,w,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,w},
			{w,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,w, w,w,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,w},
			{w,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,w, w,w,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,w},
			{w,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,w, w,w,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,w},
			{w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w, w,w,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,w}
	
	};
	
	private World theWorld;
	
	public GameplayScreen(Game g){
		game = g;
		create();
	}
	
	public void create() {
		
		
		mainStage = new Stage();
		uiStage = new Stage();

		mainStage.addActor(printer);

		inputProcess = new InputProcess();
		
		shapeRender = new ShapeRenderer();

		
		Human h = new Human();
		Gdx.input.setInputProcessor(inputProcess);
	
		
		
		Player = new Entity(new Human());
		Player.abilities.add(new BasicAttack(Player));
		Player.abilities.add(new Move(Player));
		Player.race = new Human();
		
	
		testEnm = new Entity(new Human());
		testEnm.name = "colton";
		testEnm.battlePos[0] = 3;
		testEnm.battlePos[1] = 3;
		testEnm2 = new Entity(new Human());
		testEnm2.name = "zakiah";
		inventoryTab = new InventoryTab();
		mainStage.addActor(inventoryTab);
		battleTab = new BattleTab();
		mainStage.addActor(battleTab);
	
		
		
		Player.battlePos[0] = 4;
		Player.battlePos[1] = 4;
		battleTab.beginBattle(map, Player, testEnm, testEnm2);
		
	
		
		
		theWorld = new World();
		theWorld.print();
		for (int i = 0; i < 40; i++){
			printer.print(Integer.toString(i));
		}
	

	}
	
	public void render(float dt){
		
		
		
		mainStage.act(dt);
		uiStage.act();
	
		
		printer.getWorldInput(Player, theWorld);
		
		
		
		Gdx.gl.glClearColor(0.0F, 0.0F, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		inventoryTab.update(Player);
		battleTab.update(Player);
		
		if (Gdx.input.isKeyJustPressed(Keys.I)){
			if (inventoryTab.isOpen){
				inventoryTab.close();
			} else {
				inventoryTab.open(Player);
			}
			
		}
		if (Gdx.input.isKeyJustPressed(Keys.B)){
		
			if (battleTab.isOpen){
				battleTab.close();
			} else {
				
				battleTab.open(Player);
			}
			
		}
		if (Gdx.input.isKeyJustPressed(Keys.T)){
			long l = ReftGame.timerthread.centiSeconds;
			String out = (l / 100.0) + " seconds running!";
			System.out.println(out);
		}
		
		//camera
	
	
		
		mainStage.draw();
	
		uiStage.draw();
		
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
