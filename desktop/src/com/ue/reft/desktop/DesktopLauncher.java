package com.ue.reft.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ue.reft.ReftGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		ReftGame theGame = new ReftGame();
		
		LwjglApplication launcher = new LwjglApplication(theGame);
		theGame.startTimer();
	}
}
