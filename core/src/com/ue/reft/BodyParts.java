package com.ue.reft;

public enum BodyParts {
	head, torso, rightLeg, leftLeg, rightFoot, leftFoot, rightArm, leftArm, rightHand, leftHand, neck;
	
	
	public Slots slot;
	public static int[] humanoid = {10, 25, 15, 15, 3, 3, 11, 11, 3, 3, 1};
	
	static {
		head.slot = Slots.HEAD;
		torso.slot = Slots.CHEST;
		rightLeg.slot = Slots.LEGS;
		leftLeg.slot = Slots.LEGS;
		rightFoot.slot = Slots.FEET;
		leftFoot.slot = Slots.FEET;
		rightArm.slot = Slots.CHEST;
		leftArm.slot = Slots.CHEST;
		rightHand.slot = Slots.RIGHTHAND;
		leftHand.slot = Slots.LEFTHAND;
		neck.slot = null;
	}
}
