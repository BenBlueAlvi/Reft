package com.ue.reft.tabs.battleTab;

import com.badlogic.gdx.graphics.Color;
import com.ue.reft.BaseActor;

public abstract class Tile extends BaseActor{
	

	public boolean isAttackable;
	public boolean isMoveable;
	public boolean isStandable;
	public boolean isSolid;
	public Dot dot;
	public char id;
	private Color walkHighlight = new Color(0xadccff);
	private Color moveHighlight = new Color(0xbeeeff);
	private Color attackHighlight = Color.RED;

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
			
		}
		
		this.isMoveable = ye;
		
	}
	
	/**
     * Sets the if the player can attack a dot on this tile
     * @param  attackAble the boolean value for if the tile is attackable
     */
	public void setAttackable(boolean attackAble){
		if (attackAble){
			this.setColor(attackHighlight);
		} else {
			if (!isMoveable){
				this.setColor(Color.WHITE);
			}
			
		}
		
		this.isAttackable = attackAble;
	}
	


}
