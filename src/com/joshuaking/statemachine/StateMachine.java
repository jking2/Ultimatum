package com.joshuaking.statemachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.joshuaking.exceptions.JIllegalStateException;


public class StateMachine {

	private Map<String, IState> states = new HashMap<String,IState>();
	private IState currentState;
	
	public StateMachine(){
		currentState = new EmptyState();
	}
	
	public void update(){
		currentState.update();
	}
	public void render(){
		currentState.render();
	}
	public void change(String stateName, String condition) throws JIllegalStateException{
		if(states.containsKey(stateName)){
			currentState.exit();
			currentState = states.get(stateName);
			currentState.enter(condition);
		}else{
			throw new JIllegalStateException();
		}
	}
	public void add(String state_name, IState state){
		states.put(state_name, state);
	}
	public IState getState(){
		return this.currentState;
	}
}
