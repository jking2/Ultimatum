package com.joshuaking.battle;

import org.lwjgl.util.vector.Vector3f;

import com.joshuaking.renderer.Sprite;
import com.joshuaking.unit.Unit;

public class Map {
	
	private Sprite sprite = new Sprite("Assets/Map.png");

	public void update(){
		
	}

	public void render() {
		
		sprite.render(0, 0, 0);
		
	}

	public Unit checkClickForUnit(Vector3f mouseVector) {
		if(sprite.isWithinBB(mouseVector.getX(), mouseVector.getY())){
			System.out.println("CLICKED THE BIG FUCING MAP");
		}
		return null;
	}

	public Tile checkClickForTile(Vector3f mouseVector) {
		// TODO Auto-generated method stub
		return null;
	}
}
