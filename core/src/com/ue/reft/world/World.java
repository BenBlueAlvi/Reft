package com.ue.reft.world;

import com.badlogic.gdx.math.MathUtils;
import com.ue.reft.GameplayScreen;
import com.ue.reft.Printer;

public class World {
	
	private Sector[][] map;
	public int x = 0;
	public int y = 0;
	public int xSize;
	public int ySize;
	
	public World(){
		
		
		Sector n = new Sector("You stand on the northern end of an island.");
		Sector ne = new Sector("You stand on the northeastern end of an island.");
		Sector nw = new Sector("You stand on the northwestern end of an island.");
		Sector w = new Sector("You stand on the western end of an island.");
		Sector c = new Sector("You stand in the center of an island.");
		Sector e = new Sector("You stand on the eastern end of an island.");
		Sector s = new Sector("You stand on the southern end of an island.");
		Sector sw = new Sector("You stand on the southwestern end of an island.");
		Sector se = new Sector("You stand on the southeastern end of an island.");
		
		Sector[][] sectorList = {{nw, n, ne},{w,c,e},{sw,s,se}};
		map = sectorList;
		xSize = map[0].length - 1;
		ySize = map.length - 1;
	}
	
	
	public Sector getSector(int x, int y){
		return map[x][y];
	}
	
	/**
     *prints the description of the current map sector
     */
	public void print(){
		x = MathUtils.clamp(x, 0, xSize);
		y = MathUtils.clamp(y, 0, ySize);

		GameplayScreen.printer.print(map[y][x].desc);
	}
	
	
	
	
}
