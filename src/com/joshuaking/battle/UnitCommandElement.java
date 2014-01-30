package com.joshuaking.battle;

import org.lwjgl.input.Mouse;

import com.joshuaking.input.Input;
import com.joshuaking.renderer.Sprite;

public class UnitCommandElement extends UIElement{
	
	private Sprite background;

	public UnitCommandElement(int posX, int posY) {
		super(posX, posY);
		background = new Sprite("Assets/UIBackground.png");
	}

	@Override
	public boolean checkClick(int mousePosX, int mousePosY) {
		
		if(background.isWithinBB(Input.getInstance().getMouseVector().getX(), Input.getInstance().getMouseVector().getY())){
			System.out.println("CLICKED");
			return true;
		}
		
		return false;
	}

	@Override
	public void render() {
		background.renderAbsolute(this.posX, this.posY, 0);
	}

}
