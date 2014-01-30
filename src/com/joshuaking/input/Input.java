package com.joshuaking.input;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import com.joshuaking.renderer.Render;
import com.joshuaking.util.LocationUtil;

public class Input {

	private static Input instance = null;
	private Input(){
	}
	public static Input getInstance(){
		if(instance == null){
			instance = new Input();
		}
		return instance;
	}
	
	public Vector3f getMouseVector(){
		return LocationUtil.getMousePositionIn3dCoords(Mouse.getX(), Mouse.getY());
	}
	public boolean isMoveUpKeyDown(){
		return Keyboard.isKeyDown(Keyboard.KEY_W);
	}
	public boolean isMoveDownKeyDown(){
		return Keyboard.isKeyDown(Keyboard.KEY_S);
	}
	public boolean isMoveLeftKeyDown(){
		return Keyboard.isKeyDown(Keyboard.KEY_A);
	}
	public boolean isMoveRightKeyDown(){
		return Keyboard.isKeyDown(Keyboard.KEY_D);
	}
	public boolean isActionKeyOneDown(){
		return Keyboard.isKeyDown(Keyboard.KEY_Q);
	}
	public boolean isActionKeyTwoDown(){
		return Keyboard.isKeyDown(Keyboard.KEY_E);
	}
	public boolean isMouseLeftClick(){
		return Mouse.isButtonDown(0);
	}
	public boolean isMouseRightClick(){
		return Mouse.isButtonDown(1);
	}
	public boolean isMenuKeyDown(){
		return Keyboard.isKeyDown(Keyboard.KEY_ESCAPE);
	}
	public boolean isConfirmKeyDown(){
		return Keyboard.isKeyDown(Keyboard.KEY_RETURN);
	}
	
	public int getMouseDirX(){
		return Mouse.getDX();
	}
	public int getMouseDirY(){
		return Mouse.getDY();
	}
	public int getMousePosX(){
		return Mouse.getX();
	}
	public int getMousePosY(){
		return Render.HEIGHT - Mouse.getY();
	}
	
}
