package com.ue.reft;

public class Telegraph {
	
	
	private Pos[] positions;
	
	public Telegraph(Pos...positions){
		this.positions = positions;
	}
	
	
	public Pos[] getHitPositions(float x, float y){
		Pos[] finalPos = new Pos[this.positions.length];
		for (int i = 0; i < finalPos.length; i ++){
			finalPos[i] = new Pos(0,0);
		}
		
		for (int i = 0; i < this.positions.length; i ++){
			finalPos[i].x += positions[i].x;
			finalPos[i].y += positions[i].y;
		}
		
		
		return finalPos;
		
		
	}
	
}
