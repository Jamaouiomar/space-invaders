package com.spaceinvaders.states;

import com.spaceinvaders.utils.GameLogger;

public class GameContext {
    private GameState currentState;
    
    public GameContext() {
        this.currentState = new MenuState();
        GameLogger.log("STATE", "Game initialized with state: " + currentState.getStateName());
    }
    
    public void setState(GameState state) {
        GameLogger.log("STATE", "Game: " + this.currentState.getStateName() + " -> " + state.getStateName());
        this.currentState = state;
    }
    
    public GameState getState() {
        return currentState;
    }
    
    public void handleInput(String input) {
        currentState.handleInput(this, input);
    }
    
    public void update() {
        currentState.update(this);
    }
    
    public void render() {
        currentState.render(this);
    }
}