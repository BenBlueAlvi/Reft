package com.ue.reft.tabs.battleTab;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polyline;
import com.badlogic.gdx.math.Vector2;
import com.ue.reft.BaseActor;
import com.ue.reft.Entity;
import com.ue.reft.Pos;
import com.ue.reft.Printer;
import com.ue.reft.ReftGame;
import com.ue.reft.Slots;
import com.ue.reft.Text;
import com.ue.reft.Utils;
import com.ue.reft.abilities.Ability;
import com.ue.reft.items.Item;
import com.ue.reft.tabs.Container;
import com.ue.reft.tabs.Tab;

public class BattleTab extends Tab{
	
	private Tile[][] map = null;
	private ShapeRenderer boxMaker = new ShapeRenderer();
	private Container<Ability> selectedAbil;
	private ArrayList<Container<Ability>> abilities = new ArrayList<Container<Ability>>();
	private Dot player = new Dot("assets/playerDot.png");
	private ArrayList<Dot> enms = new ArrayList<Dot>(); 
	private int scrollOffset;
	private int mapOffsetX;
	private int mapOffsetY;
	final int maxMapX = 25;
	final int maxMapY = 15;
	private int walkDist = 0;
	private ArrayList<Tile> tilesInRange = new ArrayList<Tile>();
	private int timePoints = 10;
	
	private Text timePointsDisp;
	private Text healthDisp;
	private Text manaDisp;
	private Text staminaDisp;
	
	private Text abilDesc;
	private Text abilCost;
	private Text abilTimeCost;
	
	private static Printer printer = new Printer(9, false);
	
	final static int maxAbils = 10;
	public BattleTab(){
		super();
		this.setVisible(false);
		this.timePointsDisp = new Text("Time Remaining: " + timePoints);
		this.timePointsDisp.setPosition(5, ReftGame.getViewHeight() - 15);
		this.addActor(timePointsDisp);
		
		this.healthDisp = new Text("Health: ", Color.RED);
		this.healthDisp.setPosition(225, ReftGame.getViewHeight() - 15);
		this.addActor(healthDisp);
		
		this.manaDisp = new Text("Mana: ", Color.SKY);
		this.manaDisp.setPosition(325, ReftGame.getViewHeight() - 15);
		this.addActor(manaDisp);
		
		this.staminaDisp = new Text("Stamina: ", Color.YELLOW);
		this.staminaDisp.setPosition(425, ReftGame.getViewHeight() - 15);
		this.addActor(staminaDisp);
		
		this.abilDesc = new Text("");
		this.abilDesc.setPosition(10, 96);
		this.addActor(abilDesc);
		
		this.abilTimeCost = new Text("");
		this.abilTimeCost.setPosition(10, 64);
		this.addActor(abilTimeCost);
		
		this.abilCost = new Text("");
		this.abilCost.setPosition(10, 32);
		this.addActor(abilCost);
		
		
		this.addActor(printer);
		printer.setPosition(ReftGame.getViewWidth()/3 + 12, 5);
	}
	
	/**
     * Sets the active map to the parameter
     * @param  map the {@code Tile[][]} to be displayed;
     * 
     */
	
	public void setMap(Tile[][] map){

		
		if (this.map != null){
			for (int row = 0; row < this.map.length; row++){
				for (int col = 0; col < this.map[0].length; col++){
					this.removeActor(this.map[row][col]);
				}
			}
		}
		
		this.map = map;
		for (int row = 0; row < map.length; row++){
			for (int col = 0; col < map[0].length; col++){
			
				map[row][col].setPosition(ReftGame.getViewWidth()/3 + 12 + col * 16,ReftGame.getViewHeight()- row * 16 - 55 + 13);
				this.addActor(map[row][col]);
			}
		}
	}
	 /**
     * Converts a character map into a tile map
     * @param  map the {@code Character[][]} to be converted
     * @return the corresponding  {@code Tile[][]};
     */
	public Tile[][] processMap(Character[][] map){
		Tile[][] newMap = new Tile[maxMapY][maxMapX];
		for (int row = 0; row < maxMapY; row++){
			for (int col = 0; col <maxMapX; col++){
				if (row < map.length  &&  col < map[0].length){
					switch(map[row][col]){
					case 'w':
						newMap[row][col] = new Wall();
						break;
					case 'f':
						newMap[row][col] = new Floor();
						break;
					default:
						newMap[row][col] = new Wall();
					}
				} else {
					newMap[row][col] = new Wall();
				}
				
			}
		}
		return newMap;
	}
	
	/**
     * Begins a battle process in the tab
     * doesn't open the tab
     * @param  map the {@code Character[][]} map, which will be converted into a {@code Tile[][]} map
     * @param  p the player Entity
     * @param  e an array of the enemies
     */
	public void beginBattle(Character[][] map, Entity p, Entity...e){
		setMap(processMap(map));
		player.setPosition(ReftGame.getViewWidth()/3 + 12 + p.battlePos[0] * 16,ReftGame.getViewHeight()- p.battlePos[1] * 16 - 55 + 13);
		player.entity = p;
		for (Entity enm : e){
			Dot d = new Dot("assets/enmDot.png");
			d.setPosition(ReftGame.getViewWidth()/3 + 12 + enm.battlePos[0] * 16,ReftGame.getViewHeight()- enm.battlePos[1] * 16 - 55 + 13);
			d.entity = enm;
			this.addActor(d);
			enms.add(d);
		}
		walkDist = p.abilities.get(1).range;
		this.addActor(player);
	}
	
	public void open(Entity p){
		super.open(p);
		if (!isOpen){
			selectedAbil = null;
			player.entity = p;
			this.abilities = new  ArrayList<Container<Ability>>();
			System.out.println("hi");
			for (int i = 0; i < p.abilities.size(); i++){
				Container<Ability> newContainer = new Container<Ability>(p.abilities.get(i), 10f, ReftGame.getViewHeight() -35 - (float) i * 16);
		
				this.abilities.add(newContainer);
				
				
				this.addActor(newContainer);
				
			}
				isOpen = true;
		
	
		}
		
	}
	
	public void close(){
		System.out.println("hi");
		for (Container<Ability> c : abilities){
			c.remove();
		}
	
		this.setVisible(false);
		isOpen = false;
	}
	
	public void update(Entity p){
		
		//Update dot posision
		this.map[(int) player.pos.y][(int) player.pos.x].dot = player;
		for (Dot d : enms){
			this.map[(int) d.pos.y][(int) d.pos.x].dot = d;
		}
		
		//updates text
		this.timePointsDisp.setText("Timepoints remaining: " + timePoints);
		this.healthDisp.setText("Health: " + player.entity.health);
		this.manaDisp.setText("Mana: " + player.entity.mana);
		this.staminaDisp.setText("Stamina: " + player.entity.stamina);
		
		//make sure abilities go invis due to scoll
		for (int i = 0; i < abilities.size(); i++){
			abilities.get(i).setPosition(10f, ReftGame.getViewHeight()- 35 - (float) (i- scrollOffset) * 16);
			if (abilities.get(i).getY() < 5 || abilities.get(i).getY() > ReftGame.getViewHeight()- 35){
				abilities.get(i).setVisible(false);
			} else {
				abilities.get(i).setVisible(true);
			}
			
			
		}
		
		//scrolling
		if (scrollOffset < 0){
			scrollOffset = 0;
		} else if (scrollOffset > abilities.size() - maxAbils && abilities.size() > maxAbils){
			scrollOffset = abilities.size() - maxAbils;
		}
		
		
		
		//map scrolling
		if (player.pos.x > maxMapX + mapOffsetX){
			mapOffsetX += 1;
		}
		else if (player.pos.x < 0 + mapOffsetX){
			mapOffsetX -= 1;
		}
		if (player.pos.y < 0 + mapOffsetY){
			mapOffsetY -= 1;
		}
		else if (player.pos.y > maxMapY + mapOffsetY){
			mapOffsetY += 1;
		}
		
		System.out.println(mapOffsetX);
		
		//map scrolling update
		for (int row = 0; row < map.length; row++){
			for (int col = 0; col < map[0].length; col++){	
		
				map[row][col].setPosition(ReftGame.getViewWidth()/3 + 12 + (col + mapOffsetX) * 16, ReftGame.getViewHeight()- (row + mapOffsetY) * 16 - 55 + 13);
				if (map[row][col].getX() < ReftGame.getViewWidth()/3 + 12 || map[row][col].getX() > ReftGame.getViewWidth()/3 + 12 + 16 * maxMapX || 
						map[row][col].getY() < ReftGame.getViewHeight() - 55 + 13 || map[row][col].getY() > ReftGame.getViewHeight()- (maxMapY) * 16 - 55 + 13){
					map[row][col].setVisible(true);
				} else {
					map[row][col].setVisible(false);
				}
			}
		}
		
		
		//hit detection for abils
		for (Container<Ability> c : abilities){
			if (c.getBoundingPolygon().contains(Gdx.input.getX(),ReftGame.getViewHeight() - Gdx.input.getY())){
	
				if (Gdx.input.justTouched()){
					if (selectedAbil == c ){
			
						selectedAbil = null;
					} else {
						selectedAbil = c;
				
					}
				}
			}
			c.title.setColor(Color.WHITE);
			if (c.getThing().timeCost > this.timePoints || c.getThing().cost > player.entity.getAmountOf(c.getThing().type)){
				c.title.setColor(Color.LIGHT_GRAY);
			}
		}
		
		
		//using abils
		if (timePoints > 0){
			playerTurn();
		} else {
			//enemy ai here
			timePoints = 10;
		}
		
		
		
		
		

	}

	private void playerTurn(){
		if (selectedAbil != null){
			
			this.abilCost.setText("Cost: " + selectedAbil.getThing().cost + " " + selectedAbil.getThing().type.useAbv);
			this.abilTimeCost.setText("Time Cost: " + selectedAbil.getThing().timeCost);
			this.abilDesc.setText(selectedAbil.getThing().name);
			
			selectedAbil.title.setColor(Color.ROYAL);
			
			//calculate tiles in range
			if (selectedAbil.getThing().name.equals("Move")){
				tilesInRange = this.getTilesInRange(player.center.x, player.center.y, walkDist * 16);
				System.out.println(walkDist);
			} else {
				tilesInRange = this.getTilesInRange(player.center.x, player.center.y, selectedAbil.getThing().range * 16);
			
			}
			
			//set tile highlights
			
			for (int row = 0; row < map.length; row++){
				for (int col = 0; col < map[0].length; col++){	
			
					map[row][col].setMoveHighlight(false);
					map[row][col].setAttackable(false);
					
				}
			}
			
			//highlight tiles
			
			for (Tile t : tilesInRange){
				
				if (this.calculateCoverFrom(map[(int) player.pos.y][(int) player.pos.x].getX(), map[(int) player.pos.y][(int) player.pos.x].getY(), t.getX(),t.getY()) < 4){
					if (selectedAbil.getThing().name == "Move"){
						
				
					} else {
						t.setAttackable(true);
					}
				} else {
				
				}
			}
			
			
			//Mark adjs moveable
			if (selectedAbil.getThing().name == "Move"){
				
				for (int row = 0; row < map.length; row++){
					for (int col = 0; col < map[0].length; col++){	
						map[row][col].setMoveHighlight(false);
						
					}
				}
				for (Tile t : player.getAdjTiles(map)){
					if (this.isTileVisableFrom(t.center.x, t.center.y, player.center.x, player.center.y)){
						t.setMoveHighlight(true);
					}
					
				}
			}
			
			//remove other highlights
			
			if (selectedAbil.getThing().name == "Move"){
				for (int row = 0; row < map.length; row++){
					for (int col = 0; col < map[0].length; col++){	
						map[row][col].setAttackable(false);
						
					}
				}
			} else {
				
				for (int row = 0; row < map.length; row++){
					for (int col = 0; col < map[0].length; col++){	
			
						map[row][col].setMoveHighlight(false);
						
					}
				}
			}
		
				
			
		
			//ability calculations
			for (int row = 0; row < map.length; row++){
				for (int col = 0; col < map[0].length; col++){	
					if (map[row][col].getBoundingPolygon().contains(Gdx.input.getX(),ReftGame.getViewHeight() - Gdx.input.getY())){
						
						Pos[] poses = selectedAbil.getThing().telegraph.getHitPositions(col, row);
						for (int i = 0; i < poses.length; i++){
							map[(int) poses[i].y][(int) poses[i].x].setTelegraph(true);
						}
						
						if (Gdx.input.justTouched()){
							if (selectedAbil.getThing().name == "Move" && map[row][col].isMoveable && map[row][col].isStandable){
								player.setPosition(ReftGame.getViewWidth()/3 + 12 + col * 16,ReftGame.getViewHeight()- row * 16 - 55 + 13);
								walkDist -= 1;
								timePoints -= selectedAbil.getThing().timeCost;
								
							} else {
								if (map[row][col].isAttackable && map[row][col].dot != null){
									int dam = Utils.damageEquation(player.entity, map[row][col].dot.entity, calculateCoverFrom(map[(int) player.pos.y][(int) player.pos.y].getX(), map[(int) player.pos.y][(int) player.pos.y].getY(), map[row][col].getX(), map[row][col].getY()), selectedAbil.getThing());
								
									printer.print(map[row][col].dot.entity.name + " takes " + dam + " damage!");
									timePoints -= selectedAbil.getThing().timeCost;
								}
							}
						}
						
					}
				}
			}
			
			
		} else {
			for (int row = 0; row < map.length; row++){
				for (int col = 0; col < map[0].length; col++){	
			
					map[row][col].setAttackable(false);
					map[row][col].setMoveHighlight(false);
					
				}
			}
			
			this.abilCost.setText("");
			this.abilDesc.setText("");
			this.abilTimeCost.setText("");
		}
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha){
		super.draw(batch, parentAlpha);
		
		/*(boxMaker.begin(ShapeType.Line);
		boxMaker.rect(5, 5, ReftGame.getViewWidth()/3 - 5, ReftGame.getViewHeight() - 40);
		boxMaker.rect(ReftGame.getViewWidth()/3 + 5, 5, ReftGame.getViewWidth()/3 * 2 - 10, ReftGame.getViewHeight() - 40);
	
		for (int i = 0; i < maxAbils - 1; i ++){
			boxMaker.line(5, ReftGame.getViewHeight()- 53 - (float) i * 16, ReftGame.getViewWidth()/3, ReftGame.getViewHeight()- 53 - (float) i * 16);
		}
		
		
		

		boxMaker.end();*/
		Utils.DrawList(5, ReftGame.getViewHeight()- (maxMapY - 1) * 16 - 55 + 13, ReftGame.getViewWidth()/3 - 5, maxMapY * 16, boxMaker);
		boxMaker.begin(ShapeType.Line);
		boxMaker.rect(ReftGame.getViewWidth()/3 + 12 - 1, ReftGame.getViewHeight() - 55 + 13 + 15 + 2, maxMapX * 16 + 3, -maxMapY * 16  - 3);
		boxMaker.rect(5, ReftGame.getViewHeight()- (maxMapY - 1) * 16 - 55 + 13, ReftGame.getViewWidth()/3 - 5, -200);
		
	
		
		boxMaker.end();
	}
	
	private boolean isTileVisableFrom(float x, float y, float x2, float y2){
		Vector2 p1 = new Vector2(x,y);
		Vector2 p2 = new Vector2(x2,y2);
		for (int row = 0; row < map.length; row++){
			for (int col = 0; col < map[0].length; col++){	
				if (map[row][col].isSolid && Intersector.intersectSegmentPolygon(p1, p2, map[row][col].getBoundingPolygon())){
					return false;
				}
			}
		
			
		}
		
		return true;
		
		
	}
	
	/**
     * Calculates cover from an origin tile to a target tile
     * @param  x the lower left x value of the origin tile
     * @param  y the lower left y value of the origin tile
     * @param  x2 the lower left x value of the target tile
     * @param  y2 the lower left y value of the target tile
     * @return cover as an integer, with 4 being full cover and 0 being no cover
     */
	private int calculateCoverFrom(float x, float y, float x2, float y2){
		int cover = 0;
		//lower left
		if (!isTileVisableFrom(x + 1,y + 1,x2 + 1,y2 + 1)){
			cover += 1;
		}
		//lower right
		if (!isTileVisableFrom(x + 15,y,x2+15,y2+1)){
			cover += 1;
		}
		//upper right
		if (!isTileVisableFrom(x + 15,y+15,x2+15,y2+15)){
			cover += 1;
		}
		//upper left
		if (!isTileVisableFrom(x+1,y+15,x2+1,y2+15)){
			cover += 1;
		}
		
		
		
		return cover;
	}
	
	private ArrayList<Tile> getTilesInRange(float x, float y, int range){
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		
		Circle rangeCircle = new Circle(x, y, selectedAbil.getThing().range * 16);
		
		for (int row = 0; row < map.length; row++){
			for (int col = 0; col < map[0].length; col++){	
				if (rangeCircle.contains(map[row][col].center)){
					tiles.add(map[row][col]);
				}
			}
		}
		
		return tiles;
	}
	
	public static void scoll(int amount){
		printer.scrolled += amount;
	}
	
}
