package com.joshuaking.main;

import java.util.concurrent.atomic.AtomicBoolean;

import com.joshuaking.exceptions.JIllegalStateException;
import com.joshuaking.renderer.Render;
import com.joshuaking.statemachine.EmptyState;
import com.joshuaking.statemachine.StateMachine;
import com.joshuaking.statemachine.TestState;

public class MainGame extends Thread {

	public final static int GAME_SPEED = 1;
	// Whether or not the game should be running
	private AtomicBoolean running;
	// Sets the FPS of the game. The game will try to run at 60fps and no more
	private final int FPS = 60;
	private final double TIME_BETWEEN_RENDERS = 1000000000 / FPS;
	// How often updates will be called
	private final double HERTZ = 30.0;
	private final double TIME_BETWEEN_UPDATES = 1000000000 / HERTZ;
	// The max number of updates that can be backed up before a render is made
	private final double MAX_UPDATES = 5;

	private StateMachine stateMachine;

	private static MainGame instance = null;

	public static MainGame GetInstance() {
		if (instance == null) {
			instance = new MainGame();
		}
		return instance;
	}

	private MainGame() {
		super();
		Render.getInstance().createDisplay();
		stateMachine = new StateMachine();
		stateMachine.add("empty", new EmptyState());

		stateMachine.add("test", new TestState());
		

		changeState("test", "fucking fuck");
		
		running = new AtomicBoolean();
		running.set(true);
	}

	@Override
	public void run() {
		double lastUpdateTime = System.nanoTime();
		double lastRenderTime = System.nanoTime();

		int lastSecondTime = (int) (lastUpdateTime / 1000000000);
		running.set(true);
		
		int fps = 60;
		int frameCount = 0;
		
		while (running.get()) {
			double currentTime = System.nanoTime();
			int updateCount = 0;
			while (currentTime - lastUpdateTime > TIME_BETWEEN_UPDATES
					&& updateCount < MAX_UPDATES) {
				if (running.get()) {
					stateMachine.update();
				}
				lastUpdateTime += TIME_BETWEEN_UPDATES;
				updateCount++;
			}
			if (currentTime - lastUpdateTime > TIME_BETWEEN_UPDATES) {
				lastUpdateTime = currentTime - TIME_BETWEEN_UPDATES;
			}
			if (running.get()) {
				stateMachine.render();
				Render.getInstance().updateDisplay();
				frameCount++;
				lastRenderTime = currentTime;
			}
			int thisSecond = (int) (lastUpdateTime / 1000000000);
			if (thisSecond > lastSecondTime) {
				Render.getInstance().displayFPS(thisSecond,frameCount);
				fps = frameCount;
				frameCount = 0;
				lastSecondTime = thisSecond;
			}
			while (currentTime - lastRenderTime < TIME_BETWEEN_RENDERS
					&& currentTime - lastUpdateTime < TIME_BETWEEN_UPDATES) {
				Thread.yield();
				try {
					Thread.sleep(1);
				} catch (Exception e) {
				}
				currentTime = System.nanoTime();
			}

		}
	}

	public boolean changeState(String stateName, String condition){
		try{
			stateMachine.change(stateName, condition);
			return true;
		}catch(JIllegalStateException e){
			return false;
		}
	}
}
