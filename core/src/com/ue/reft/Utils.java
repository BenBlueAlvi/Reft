package com.ue.reft;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.ue.reft.abilities.Ability;
import com.ue.reft.items.Item;

public class Utils {
	
	
	public static int roll20(){
		return MathUtils.random(1, 20);
	}
	public static int roll100(){
		return MathUtils.random(0, 100);
	}
	
	
	public static int damageEquation(Entity offender, Entity defender, int cover, Ability abil){
		
		int damage = offender.getStat(Stats.strength);
		int totalPers = 0;
		if (offender.equipment.get(Slots.MAINHAND) != null){
			damage += offender.equipment.get(Slots.MAINHAND).damage.getPoint(Utils.roll100());
			damage += offender.equipment.get( Slots.MAINHAND).getStat(Stats.strength);
			
		}
		
		for (Slots s : Slots.values()){
			if (s != Slots.MAINHAND && s != Slots.OFFHAND){
				if (offender.equipment.get(s) != null){
					damage += offender.equipment.get(s).getStat(Stats.strength);
					
				}
				
			}
		}
		damage *= (totalPers / 100) + 1;
		
		//calc hit
		
		if (Roll.roll("1d20") + offender.getStat(Stats.acc) - (2 * cover) > Roll.roll("1d20") + defender.getStat(Stats.reaction)) {
			System.out.println("hit");
			//deal damage
			BodyParts bodyPartHit = defender.calcHitBodyPart();
			
			if (defender.equipment.get(bodyPartHit.slot) != null){
				Item equipedItem = defender.equipment.get(bodyPartHit.slot);
				
				int armorDamage = (int) (damage * equipedItem.absorbsion);
				int entityDamage = (int) (damage * (1-equipedItem.absorbsion));
				
				defender.health -= entityDamage;
				equipedItem.durability -= armorDamage;
			}
		} else {
			System.out.println("miss!");
			damage = 0;
		}
		
	
		
	
		
		
		return damage;
		
	}
	

	 public class DamageCurve {

		 double rangeTop = 2, rangeBottom = 0;
		 int pos;

		 public DamageCurve(double rangeTop, double rangeBottom) {
			 this.rangeTop = rangeTop;
			 this.rangeBottom = rangeBottom;
			
		 }


		 public double getPoint(int pos){
			 double output = 0.0, last = 0.0, current = 0.0;

			 for (int i = 0; i < pos; i++){
				 current = .5 * ((Math.exp(-(2/625)*Math.pow(i-50, 2))) * (.319254 - .00638308 * i));
				 output += .5 * (current + last);
				 last = current;
			 }

			 output = (output * (rangeTop - rangeBottom)) + rangeBottom;
	
			 return output;
		 }

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
	
	
		return new Color(1f, ((float)(cover) /4), ((float)(cover)/4), 0.5f);
	}
	
	
}


