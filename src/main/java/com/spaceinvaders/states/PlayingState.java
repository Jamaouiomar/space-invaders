package com.spaceinvaders.states;

public class PlayingState implements GameState {
    @Override
    public void handleInput(GameContext context, String input) {
        if ("PAUSE".equals(input)) {
            context.setState(new PauseState());
        } else if ("GAME_OVER".equals(input)) {
            context.setState(new GameOverState());
        }
    }
    
    @Override
    public void update(GameContext context) {}
    
    @Override
    public void render(GameContext context) {
        System.out.println("=== PLAYING ===");
        System.out.println("Enemies: 10");
        System.out.println("Score: 1500");
    }
    
    @Override
    public String getStateName() {
        return "PLAYING";
    }
}