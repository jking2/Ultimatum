package com.joshuaking.battle;

import java.util.Stack;

import org.lwjgl.opengl.GL11;

import com.joshuaking.input.Input;
import com.joshuaking.renderer.Camera;
import com.joshuaking.renderer.Render;
import com.joshuaking.unit.Unit;

public class Battle {

	private Team playerTeam;
	private Team enemyTeam;
	private Map map;
	private UI ui;
	private Camera camera;
	private Stack<IEvent> events;
	
	private Unit selectedUnit;
	private Tile selectedTile;
	
	public Battle(Team playerTeam, Team enemyTeam, Map map){
		this.playerTeam=playerTeam;
		this.enemyTeam=enemyTeam;
		this.map=map;
		ui = new UI(this);
		camera = new Camera();
		events = new Stack<IEvent>();
		
		//TESTING
		ui.addUIElement(new UnitCommandElement(Render.WIDTH/10, Render.HEIGHT/10));
	}
	
	public void update(){
		handleMouseInput();
		if(!events.isEmpty()){
			events.peek().update();
		}else{
			map.update();
			
		}
	}
	public void render(){
		GL11.glPushMatrix();
		camera.pan();
		map.render();
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		ui.render();
		GL11.glPopMatrix();
	}
	
	private void handleMouseInput(){
		if(Input.getInstance().isMouseRightClick()){
			camera.addPan(Input.getInstance().getMouseDirX(),Input.getInstance().getMouseDirY());
		}else if(Input.getInstance().isMouseLeftClick()){
			if(!ui.checkClick(Input.getInstance().getMousePosX(),Input.getInstance().getMousePosY())){
				selectedUnit = map.checkClickForUnit(Input.getInstance().getMouseVector());
				selectedTile = map.checkClickForTile(Input.getInstance().getMouseVector());
			}
		}
	}
	
	public Team getEnemyTeam(){
		return enemyTeam;
	}
	public Team getPlayerTeam(){
		return playerTeam;
	}
	public Map getMap(){
		return map;
	}
}
