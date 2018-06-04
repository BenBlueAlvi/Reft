package com.ue.reft.tabs.battleTab;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.ue.reft.BaseActor;
import com.ue.reft.Utils;

public abstract class Tile extends BaseActor{
	

	public boolean isAttackable;
	public boolean isMoveable;
	public boolean isStandable;
	public boolean isSolid;
	public boolean isHit;
	public Dot dot;
	public char id;
	public int cover;
	
	public ArrayList<TileObject> tileObjects = new ArrayList<TileObject>();
	private Color walkHighlight = new Color(0xadccff);
	private Color moveHighlight = new Color(0xbeeeff);
	private Color hitHighlight = Color.ORANGE;
	private Color attackHighlight = Color.RED;
	private float lighting = 0;

	public Tile(String path) {
		super(path);
		walkHighlight.a = 10f;
		attackHighlight.a = 10f;
		
	}
	
	/**
     * Sets the moveHighlight for this tile
     * @param  ye the boolean value for if the tile should be highlighted
     */
	
	public void setMoveHighlight(boolean ye){
		if (ye){
			this.setColor(moveHighlight);
		
		} else {
			if (!isAttackable){
				this.setColor(Color.WHITE);
			
			}
			if (!isHit){
				this.setColor(Color.WHITE);
			
			}
			
		}
		
		this.isMoveable = ye;
		
	}
	
	/**
     * Sets the if the player can attack a dot on this tile
     * @param  attackAble the boolean value for if the tile is attackable
     */
	public void setAttackable(boolean attackAble, int cover){
		if (attackAble){
			this.setColor(Utils.getCoverColor(cover));
			this.cover = cover;
			updateLighting();
		} else {
			if (!isMoveable){
				this.setColor(Color.WHITE);
				updateLighting();
			}
			if (!isHit){
				this.setColor(Color.WHITE);
				updateLighting();
			}
			this.cover = 4;
			
		}
		
		this.isAttackable = attackAble;
	}
	
	public void setTelegraph(boolean ye){
		if (ye){
			this.setColor(hitHighlight);
			updateLighting();
		} else {
			if (!isMoveable){
				this.setColor(Color.WHITE);
				updateLighting();
				
			}
			if (!isAttackable){
				this.setColor(Color.WHITE);
				updateLighting();
			}
			
		}
		this.isHit = ye;
	}
	
	public void setLighting(float lighting) {
		// 0 is no light, 1 is lots of light
		this.lighting = lighting;
	
	}
	public void addLighting(float lighting) {
		// 0 is no light, 1 is lots of light
		this.lighting += lighting;
		
	}
	
	public void updateLighting() {
		
		
		
		this.getColor().a = lighting;
		
	}
	
	public void resetLighting() {
		this.lighting = 0;
	}
	


}
