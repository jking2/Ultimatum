package com.joshuaking.renderer;

import org.lwjgl.opengl.GL11;

public class Camera {

	public final static double PAN_SPEED = 3.0;
	
	public static double totalPanX;
	public static double totalPanY;
	
	public Camera(){
		resetPan();
	}
	
	private void resetPan(){
		totalPanX = 0;
		totalPanY = 0;
	}
	
	public void addPan(double addX, double addY){
		totalPanX += addX*PAN_SPEED;
		totalPanY += addY*PAN_SPEED;
	}
	
	public void pan(){
		GL11.glTranslated(totalPanX, -totalPanY,0);
	}

}
