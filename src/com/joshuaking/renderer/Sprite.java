package com.joshuaking.renderer;

import java.io.IOException;

import org.lwjgl.opengl.GL11;

public class Sprite{

	private Texture texture;
	private int width;
	private int height;
	private double renderedPosX;
	private double renderedPosY;
	
	public Sprite(String fileLocation){
		renderedPosX=0;
		renderedPosY=0;
		try{
			texture = Render.getInstance().getTextureLoader().getTexture(fileLocation);
			width = texture.getImageWidth();
			height = texture.getImageHeight();
		} catch(IOException e){
			System.out.println("Failed to load a texture for this sprite: "+this.toString());
			System.out.println("We tried to load at location: "+fileLocation);
			e.printStackTrace();
			System.exit(0);
		}
	}
	public int getWidth(){
		return texture.getImageWidth();
	}
	public int getHeight(){
		return texture.getImageHeight();
	}
	public void render(float xPos, float yPos, float rotation){
		renderedPosX = xPos+Camera.totalPanX;
		renderedPosY = yPos-Camera.totalPanY;
		GL11.glPushMatrix();
		texture.bind();
		GL11.glTranslated(xPos, yPos, 0);
		rotate(rotation);
		GL11.glBegin(GL11.GL_QUADS);
		{
			GL11.glTexCoord2f(0, 0);
			GL11.glVertex2d(xPos, yPos);

			GL11.glTexCoord2f(0, texture.getHeight());
			GL11.glVertex2d(xPos, texture.getImageHeight()+yPos);

			GL11.glTexCoord2f(texture.getWidth(), texture.getHeight());
			GL11.glVertex2d(texture.getImageWidth()+xPos, texture.getImageHeight()+yPos);

			GL11.glTexCoord2f(texture.getWidth(), 0);
			GL11.glVertex2d(texture.getImageWidth()+xPos, yPos);
		}
		GL11.glEnd();
		GL11.glPopMatrix();
	}
	public boolean isWithinBB(double mouseX, double mouseY){
		if(mouseX>renderedPosX && mouseX < renderedPosX+width){
			if(mouseY > renderedPosY && mouseY < renderedPosY+height){
				return true;
			}
		}
		return false;
	}
	
	private void rotate(double rotation){
		GL11.glRotated(rotation, 0.0f, 0.0f, 1.0f);
	}
	
	public void renderAbsolute(float xPos, float yPos, float rotation){
		renderedPosX = (-Render.WIDTH+1)+xPos;
		renderedPosY = (-Render.HEIGHT+1)+yPos;
		GL11.glPushMatrix();
		GL11.glTranslated(-Render.WIDTH+1, -Render.HEIGHT+1, 0);
		texture.bind();
		rotate(rotation);
		GL11.glBegin(GL11.GL_QUADS);
		{
			GL11.glTexCoord2f(0, 0);
			GL11.glVertex2d(xPos, yPos);

			GL11.glTexCoord2f(0, texture.getHeight());
			GL11.glVertex2d(xPos, texture.getImageHeight()+yPos);

			GL11.glTexCoord2f(texture.getWidth(), texture.getHeight());
			GL11.glVertex2d(texture.getImageWidth()+xPos, texture.getImageHeight()+yPos);

			GL11.glTexCoord2f(texture.getWidth(), 0);
			GL11.glVertex2d(texture.getImageWidth()+xPos, yPos);
		}
		GL11.glEnd();
		GL11.glPopMatrix();
	}
	
}
