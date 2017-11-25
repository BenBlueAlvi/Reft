package com.ue.reft;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.ue.reft.abilities.Ability;
import com.ue.reft.commands.Command;
import com.ue.reft.commands.CommandList;
import com.ue.reft.world.World;

public class Printer extends BaseActor{
	
	private BitmapFont font;

	
	
	private static int maxCharsPerLine = 79;
	//private static int maxLines = 25;
	private int maxLines;
	private static int maxSavedLines = 50;
	
	public ArrayList<Label> lines;
	public Label inputLine;
	public ArrayList<Option> options;
	
	public ArrayList<String> outputs;

	public int scrolled;
	
	private int commandDepth = 1;
	private int commandIndex;
	private int subCommandIndex;
	
	private ShapeRenderer boxMaker = new ShapeRenderer();
	

	
	
	public Printer(int maxLines, boolean showInputLine) {
		super("assets/null.png");
		this.maxLines = maxLines;
		
		
		

		
	
		lines = new ArrayList<Label>();
		options = new ArrayList<Option>();
		outputs = new ArrayList<String>();
		inputLine = new Label(">>> ", ReftGame.font);
		inputLine.setPosition(5, 32);
		this.addActor(inputLine);
		
		for (int i = 0; i < maxLines; i ++){
			Label l = new Label("", ReftGame.font);
			l.setPosition(5 , l.getHeight() + i*16 + 16 + 32 + 5);
		
			this.addActor(l);
			lines.add(l);
			outputs.add("");
			
		}
		
		for (int y = 0; y < 7; y ++){
			for (int x = 0; x < 7; x ++){
				Option o = new Option();
				o.setPosition(20 + x * 85, ReftGame.getViewHeight()-(13*16) - y*23);
				o.setVisible(false);
				o.id = x + y;
				this.addActor(o);
				options.add(o);
			}
		}
		
		
		inputLine.setVisible(showInputLine);
		
		
	}
	
	/**
     *prints a line to the output feed
     *Printing "" will cause the game to crash
     *@param newText the string to be output
     */
	
	public void print(String newText){
		
	
		
		
		// Strip leading & trailing spaces

		// Split on 1 or more spaces
		String[] w = newText.split(" ");
	
		ArrayList<String> words = new ArrayList<String>();
		for (String s : w){
			words.add(s);
			
		}
		
		
		
		while(words.size() > 0) {
			String output = "";
			int strCount = 0;
			int index = 0;
			
			for(String s: words) {
				strCount += s.length() + 1;
				if(strCount < maxCharsPerLine) {
					index++;
				}
				else
					break;
			}
		
			for (int i = 0; i < index; i ++){
			
				output  += words.get(i) + " ";
				
			}
			outputs.add(output);
		
			for (String s : output.split(" ")){
				words.remove(s);
			}
		
			
			
		}
		scrolled += 1;
		
		
		
	}
	
	public void dispOptions(String[] op){
		for (int i = 0; i < op.length; i++){
			options.get(i).setText(op[i]);
			options.get(i).setVisible(true);
		}
	}
	
	
	
	@Override
	public void act(float dt){
		super.act(dt);
		scrolled = MathUtils.clamp(scrolled, 1, outputs.size() - maxLines);
	
		for (int i = 0; i < maxLines; i ++){
			if (i + scrolled < outputs.size() && i + scrolled > 0){
				lines.get(lines.size() -1 - i).setText(outputs.get(i + scrolled));
			}
			
		}
		
		if (outputs.size() > maxSavedLines){
			outputs.remove(0);
		}
		
	
		
		
	}
	
	/**
     *sets the text of the input line
     *@param s the String[] of words to displayed in the input line
     */
	
	public void setInputLine(String...s){
		String input = "";
		for (String ss : s){
			input += " " + ss;
		}
		inputLine.setText(input);
	}
	
	
	/**
     * processes commands
     * @param p the player entity
     * @param w the world
     */
	public void getWorldInput(Entity p, World w){
		CommandList c = CommandList.worldCommands;
		
		
		if (Gdx.input.isKeyJustPressed(Keys.SPACE)){
			if (commandDepth < 2){
				commandDepth += 1;	
			}
			
		}
		if (Gdx.input.isKeyJustPressed(Keys.BACKSPACE)){
			if (commandDepth > 1){
				commandDepth -= 1;	
			}
			
		}
		
		if (Gdx.input.isKeyJustPressed(Keys.TAB)){
			if (commandDepth == 1){
				commandIndex += 1;
			} else if (commandDepth == 2) {
				subCommandIndex += 1;
			}
			
			
			
		}
		
		if (commandIndex < 0){
			commandIndex = c.commands.length-1;
		} else if (commandIndex > c.commands.length-1){
			commandIndex = 0;
		}
		

		
		if (subCommandIndex < 0){
			subCommandIndex = c.commands[commandIndex].subCommands.size()-1;
		} else if (subCommandIndex > c.commands[commandIndex].subCommands.size()-1){
			subCommandIndex = 0;
		}
		
		
		
		
		
		
		
		
	
		setInputLine(">>> " + c.commands[commandIndex].name + " ", c.commands[commandIndex].subCommands.get(subCommandIndex).name);

		
		
		if (Gdx.input.isKeyJustPressed(Keys.ENTER)){	
		
		
			c.commands[commandIndex].run(p, w, subCommandIndex);
				
			
			setInputLine(">>>");
			
		}
		
		
	}
	
	/*@Override
	public void draw(Batch batch, float parentAlpha){
		super.draw(batch, parentAlpha);
	
		
		boxMaker.begin(ShapeType.Line);
		
		if (commandDepth == 1){
			boxMaker.rect(37,32, (CommandList.worldCommands.commands[commandIndex].name.length()-1)*9, 14);
			
		} else if (commandDepth == 2){
			boxMaker.rect(37 + (CommandList.worldCommands.commands[commandIndex].name.length())*9,32, CommandList.worldCommands.commands[commandIndex].subCommands.get(subCommandIndex).name.length()*9, 14);
			
		}
		
		boxMaker.end();
	
	
	}*/
	
	
	
	
}
