package com.spaceinvaders.states;

public class PauseState implements GameState {
    @Override
    public void handleInput(GameContext context, String input) {
        if ("RESUME".equals(input)) {
            context.setState(new PlayingState());
        }
    }
    
    @Override
    public void update(GameContext context) {}
    
    @Override
    public void render(GameContext context) {
        System.out.println("=== PAUSE ===");
        System.out.println("Press R to resume");
    }
    
    @Override
    public String getStateName() {
        return "PAUSE";
    }
}