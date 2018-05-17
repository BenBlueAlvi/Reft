package com.ue.reft.tabs;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.ue.reft.Entity;
import com.ue.reft.ReftGame;
import com.ue.reft.Slots;
import com.ue.reft.Text;
import com.ue.reft.items.Item;
import com.ue.reft.items.ItemEquipable;

public class InventoryTab extends Tab{
	
	private Text invenTitle;
	private Text equipTitle;
	private ShapeRenderer boxMaker = new ShapeRenderer();
	private Text[] equipTitles = new Text[Slots.values().length];
	private ArrayList<Container<Item>> equips =  new ArrayList<Container<Item>>();
	private ArrayList<Container<Item>> storedItems = new  ArrayList<Container<Item>>();
	public static int scrollOffset = 0;
	public static int equipScrollOffset = 0;
	private Container<Item> selectedItem = null;
	
	final static int boxHeight = ReftGame.getViewHeight() - 45;
	final static int maxItems = 27;
	
	private static Rectangle itemRect = new Rectangle(5, 5, ReftGame.getViewWidth()/2 - 5, ReftGame.getViewHeight() - 40);
	private static Rectangle equipRect = new Rectangle(ReftGame.getViewWidth()/2 + 5, 5, ReftGame.getViewWidth()/2 - 10, ReftGame.getViewHeight() - 40);
	
	public InventoryTab(){
		super();
		boxMaker.setColor(Color.WHITE);
		invenTitle = new Text("Inventory:");
		equipTitle = new Text("Equipment:");
		equipTitle.getLabel().setFontScale(3);
		invenTitle.getLabel().setFontScale(3);
		
		for (int i = 0; i < Slots.values().length; i ++){
			Text t = new Text(Slots.values()[i].toString() + ":");
			t.setPosition(ReftGame.getViewWidth()/2 + 10, ReftGame.getViewHeight()- 55 - (float) i * 16);
			equipTitles[i] = t;
			this.addActor(t);
			
			Container<Item> container = new Container<Item>(null, ReftGame.getViewWidth()/2 + 10 + 80, ReftGame.getViewHeight()- 47 - (float) i * 16);
			
			this.addActor(container);
			equips.add(container);
		}
		
		this.addActor(invenTitle);
		invenTitle.setPosition(5, ReftGame.getViewHeight()- 23);
		this.addActor(equipTitle);
		equipTitle.setPosition(ReftGame.getViewWidth()/2 + 5, ReftGame.getViewHeight()- 23);
		
		this.setVisible(false);
	}
	
	public void open(Entity p){
		
		if (!isOpen){
			selectedItem = null;
			
			this.storedItems = new  ArrayList<Container<Item>>();
			for (int i = 0; i < p.getInventory().size(); i++){
				Container<Item> newContainer = new Container<Item>(p.getInventory().get(i), 10f, ReftGame.getViewHeight()- 45 - (float) i * 16);
		
				this.storedItems.add(newContainer);
				
				
				this.addActor(newContainer);
				
			}
			this.setVisible(true);
			isOpen = true;
		}
		
	}
	
	public void close(){
		for (Container<Item> c : storedItems){
			c.remove();
		}
	
		this.setVisible(false);
		isOpen = false;
	}
	
	/**
     * Called every tick to update the tab.
     * @param p the player entity
     */
	
	public void update(Entity p){
		for (int i = 0; i < storedItems.size(); i++){
			storedItems.get(i).setPosition(10f, ReftGame.getViewHeight()- 45 - (float) (i- scrollOffset) * 16);
			if (storedItems.get(i).getY() < 5 || storedItems.get(i).getY() > ReftGame.getViewHeight()- 45){
				storedItems.get(i).setVisible(false);
			} else {
				storedItems.get(i).setVisible(true);
			}
		}
		
		for (int i = 0; i < equipTitles.length; i++){
			equipTitles[i].setPosition(ReftGame.getViewWidth()/2 + 10, ReftGame.getViewHeight()- 55 - (float) (i- equipScrollOffset) * 16);
			
			if (equipTitles[i].getY() > 425 || equipTitles[i].getY() < 14 * 16 + 5){
				equipTitles[i].setVisible(false);
			} else {
				equipTitles[i].setVisible(true);
			}
		}
		
		for (int i = 0; i < equips.size(); i++){
			equips.get(i).setPosition(ReftGame.getViewWidth()/2 + 10 + 80, ReftGame.getViewHeight()- 47 - (float) (i- equipScrollOffset) * 16);
			if (equips.get(i).getY() < 5 || equips.get(i).getY() > ReftGame.getViewHeight()- 45){
				equips.get(i).setVisible(false);
			} else {
				equips.get(i).setVisible(true);
			}
		}
		
		
		
		
		
		
		for (Container<Item> c : storedItems){

		
			if (c.getBoundingPolygon().contains(Gdx.input.getX(),ReftGame.getViewHeight() - Gdx.input.getY())){
	
				if (Gdx.input.justTouched()){
					if (selectedItem == c){
			
						selectedItem = null;
					} else {
						selectedItem = c;
				
					}
				}
			
			
		
			}
			c.title.setColor(Color.WHITE);
		}
		
		
		if (selectedItem != null){
			selectedItem.title.setColor(Color.ROYAL);
		}
		
		
		
		for (int i= 0; i < equipTitles.length; i++){

			
			if (equipTitles[i].getBoundingPolygon().contains(Gdx.input.getX(),ReftGame.getViewHeight() - Gdx.input.getY()) && Gdx.input.justTouched()){

				if (selectedItem != null && selectedItem.getThing() instanceof ItemEquipable){
					equips.get(i).setThing(selectedItem.getThing());
					
					p.getEquips().put(Slots.values()[i], (ItemEquipable) selectedItem.getThing());
					storedItems.remove(selectedItem);
					p.getInventory().remove(selectedItem.getThing());
					this.removeActor(selectedItem);
					selectedItem = null;
				} else { 
				
					if (equips.get(i).getThing() != null){
						p.getEquips().put(Slots.values()[i], null);
						Container<Item> c = new Container<Item>(equips.get(i).getThing(), 10f, ReftGame.getViewHeight()- 45 - (float) storedItems.size() * 16);		
						storedItems.add(c);
						p.getInventory().add(c.getThing());
						this.addActor(c);
						equips.get(i).setThing(null);
					
						
					}
				}
				
			}
			
		}
		
		
		if (scrollOffset > 0){
			scrollOffset = 0;
		} else if (scrollOffset < storedItems.size() - maxItems && storedItems.size() > maxItems){
			scrollOffset = storedItems.size() - maxItems;
		} else if (storedItems.size() < maxItems){
			scrollOffset = 0;
		}
		
		if (equipScrollOffset < 0){
			equipScrollOffset = 0;
		} else if (equipScrollOffset > 13){
			equipScrollOffset = 13;
		}
		
		
		
		
		
		
	}
	
	/**
     * Increases the scroll value in the tab by amount.
     * Should only be used by {@link InputProcess}
     * @param amount the amount to be scrolled
     */
	public static void scroll(int amount){
		if (itemRect.contains(Gdx.input.getX(),ReftGame.getViewHeight() - Gdx.input.getY())){
			scrollOffset -= amount;
		} else if (equipRect.contains(Gdx.input.getX(),ReftGame.getViewHeight() - Gdx.input.getY())){
			equipScrollOffset -= amount;
		}
		
	}
	
	
	@Override
	public void draw(Batch batch, float parentAlpha){
		
	
		super.draw(batch, parentAlpha);
		
		boxMaker.begin(ShapeType.Line);
		boxMaker.rect(5, 5, ReftGame.getViewWidth()/2 - 5, ReftGame.getViewHeight() - 40);
		
		boxMaker.rect(ReftGame.getViewWidth()/2 + 5, 5, ReftGame.getViewWidth()/2 - 10, ReftGame.getViewHeight() - 40);
		for (int i = 0; i < maxItems - 1; i ++){
			boxMaker.line(5, ReftGame.getViewHeight()- 53 - (float) i * 16, ReftGame.getViewWidth()/2, ReftGame.getViewHeight()- 53 - (float) i * 16);
		}
		for (int i = 0; i < 13; i ++){
			boxMaker.line(ReftGame.getViewWidth()/2 + 5, ReftGame.getViewHeight()- 55 - (float) i * 16, ReftGame.getViewWidth()-5, ReftGame.getViewHeight()- 55 - (float) i * 16);
		}
		boxMaker.end();
		
		
		
		
	
		
	}
	
	
}
