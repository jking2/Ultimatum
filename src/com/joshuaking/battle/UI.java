package com.joshuaking.battle;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

public class UI {

	private Battle battle;
	private ArrayList<UIElement> elements;
	
	public UI(Battle battle) {
		this.battle=battle;
		elements = new ArrayList<UIElement>();
	}

	public void render() {
		for(UIElement element : elements){
			if(!element.isHidden()){
				element.render();
			}
		}
		
	}

	public boolean checkClick(int mousePosX, int mousePosY) {
		
		for(UIElement element : elements){
			if(element.checkClick(mousePosX,mousePosY)){
				return true;
			}
		}
		return false;
	}
	public void addUIElement(UIElement element){
		elements.add(element);
	}

}
