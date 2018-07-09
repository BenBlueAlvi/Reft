package com.ue.reft;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.ue.reft.abilities.Ability;
import com.ue.reft.items.Item;
import com.ue.reft.items.ItemEquipable;

public class Utils {
	
	
	public static int roll20(){
		return MathUtils.random(1, 20);
	}
	public static int roll100(){
		return MathUtils.random(0, 100);
	}
	public static Vector2 polarToRect(int r, double angle, Vector2 origin) {
		Vector2 v = new Vector2();
		v.x = (float) (r * Math.cos(Math.toRadians(angle))) + origin.x;
		v.y = (float) (r * Math.sin(Math.toRadians(angle))) + origin.y;

		return v;
	}
	
	
	public static int damageEquation(Entity offender, Entity defender, int cover, Ability abil){
		
		int damage = offender.getStat(Stats.strength);
		int totalPers = 0;
		if (offender.getEquips().get(Slots.MAINHAND) != null){
			damage += offender.getEquips().get(Slots.MAINHAND).damage;
			damage += offender.getEquips().get( Slots.MAINHAND).getStat(Stats.strength);
			
		}
		
		for (Slots s : Slots.values()){
			if (s != Slots.MAINHAND && s != Slots.OFFHAND){
				if (offender.getEquips().get(s) != null){
					damage += offender.getEquips().get(s).getStat(Stats.strength);
					
				}
				
			}
		}
		damage *= (totalPers / 100) + 1;
		
		//calc hit
		
		if (Roll.roll("1d20") + offender.getStat(Stats.acc) - (2 * cover) > Roll.roll("1d20") + defender.getStat(Stats.reaction)) {
			System.out.println("hit");
			//deal damage
			

			BodyParts bodyPartHit = defender.calcHitBodyPart();
			int armorDamage = 0;
			int entityDamage = 0;
			//check if armor is in slot


			if (defender.getEquips().get(bodyPartHit.slot) != null){
				ItemEquipable equipedItem = defender.getEquips().get(bodyPartHit.slot);

				
				//check if hit armor
				if (equipedItem.coverage < Utils.roll100()) {
					entityDamage = damage;
				} else {
					//damage armor based on absorption
					 armorDamage = (int) (damage * equipedItem.absorbsion);
					entityDamage = (int) (damage * (1-equipedItem.absorbsion));
					equipedItem.durability -= armorDamage;
				}
				
				
				
			} else {
				entityDamage = damage;
			}
			defender.health -= entityDamage;
			
		} else {
			System.out.println("miss!");
			damage = 0;
		}

		return damage;
		
	}
	

	 
	 public static ArrayList<Integer[]> circlePoints(int in){
		 ArrayList<Integer[]> output = new ArrayList<Integer[]>();
		 //test = new Integer[2];
		 double[] temp = new double[2];


		 for (int i = 1; i <= 90; i++){
			 temp = coord((Math.PI/180) * i);
			
			 Integer[] test = { (int) (temp[0] * in), (int) (temp[1] * in)};

		

			 if(testList(output, test)){
		
				 output.add(test);
			
			 }

		 }

		 //System.out.println(output.size());
		 return output;
		 }

		 public static double[] coord(double in){
		 double[] output = {Math.cos(in), Math.sin(in)};
		 return output;
		 }
		 
		 public static boolean testList(ArrayList<Integer[]> list, Integer[] test){

			 for (Integer[] i : list) {
				 if(test[0] == i[0] && test[1] == i[1]){
					 return false;
				 }
			 }
			return true;
		 }	


		
	 
	 
	public static void DrawList(int x, int y, int width, int hieght, ShapeRenderer sr){
		sr.begin(ShapeType.Line);
		sr.rect(x, y, width, hieght);
	
	
		for (int i = 0; i < hieght/16; i ++){
			sr.line(x, y + i * 16, x+width, y+ i * 16);
		}
		sr.end();
	}
	
	public static Color getCoverColor(int cover) {
	
	
		return new Color(1f, ((float)(cover) /4), ((float)(cover)/4), 1f);
	}
	
	public static Texture loadTexture(String path) {
		try {
			Texture t = new Texture(Gdx.files.internal("assets/" + path + ".png"));
			return t;

		} catch (Exception GdxRuntimeException) {
			System.out.println("Error: Could not find: " + path + " substituting...");
			Texture t = new Texture(Gdx.files.internal("assets/missingTex.png"));
			return t;
		}
	}
	
	public static Animation<TextureRegion> loadAnimation(String path, int cols, int rows){
		Texture sheet = Utils.loadTexture(path);
		TextureRegion[][] map = TextureRegion.split(sheet, sheet.getWidth()/cols, sheet.getHeight()/rows);
		TextureRegion[] frames = new TextureRegion[cols * rows];
		int index = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				frames[index++] = map[i][j];
			}
		}
		return new Animation<TextureRegion>(0.025f, frames);
	}

	
	
}


