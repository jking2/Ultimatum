package com.joshuaking.statemachine;

import java.util.ArrayList;

import com.joshuaking.battle.Battle;
import com.joshuaking.battle.Map;
import com.joshuaking.battle.Team;
import com.joshuaking.input.Input;
import com.joshuaking.main.MainGame;
import com.joshuaking.renderer.Sprite;

public class TestState implements IState {

	private Battle battle = new Battle(new Team(), new Team(), new Map());

	@Override
	public void update() {
		battle.update();
	}

	@Override
	public void render() {
		battle.render();
	}

	@Override
	public void enter(String condition) {
		
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub

	}

}
