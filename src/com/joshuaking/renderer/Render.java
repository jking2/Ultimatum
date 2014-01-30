package com.joshuaking.renderer;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Render {

	public static final int WIDTH = 800; //Default 800
	public static final int HEIGHT = 600; //Default 600
	private TextureLoader textureLoader;
	private static Render instance = null;
	
	private Sprite loadingScreen = null;
	
	private Render(){
		textureLoader = new TextureLoader();
	}
	public static Render getInstance(){
		if(instance == null){
			instance = new Render();
		}
		return instance;
	}
	public TextureLoader getTextureLoader(){
		return textureLoader;
	}
	public void createDisplay(){
		try{

			Display.setDisplayMode(new DisplayMode(WIDTH,HEIGHT));
			Display.create();
			
			setMainRenderMode();
	        
			loadingScreen = new Sprite("Assets/LoadingScreen.png");
	        
		}catch(Exception e){
			System.out.println("Could not create the display and/or set it to a displayMode");
			e.printStackTrace();
			System.exit(0);
		}
	}
	public void setMainRenderMode(){
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(-WIDTH, WIDTH, HEIGHT, -HEIGHT, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        
        //Zooms in the camera in. (x,y,z) ---> (2,2,1) == double zoom
        GL11.glScalef(1, 1, 1);
	}
	public void updateDisplay(){
		if(Display.isCloseRequested()){
			Display.destroy();
			System.exit(0);
		}else{
			Display.update();
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		}
	}
	public void displayFPS(int currentSecond, int FPS){
		String title = "Current Second: "+currentSecond+"   FPS: "+FPS;
		Display.setTitle(title);
	}
	public void renderLoadingScreen(){
		loadingScreen.render(0, 0, 0);
		updateDisplay();
	}
	
}
