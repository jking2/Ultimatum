package com.joshuaking.battle;

import org.newdawn.slick.geom.Rectangle;

import com.joshuaking.renderer.Render;


public abstract class UIElement {


	
	protected int posX;
	protected int posY;
	
	protected boolean hidden;
	
	
	
	public UIElement(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}

	abstract public boolean checkClick(int mousePosX, int mousePosY);

	abstract public void render();
	
	

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

}
