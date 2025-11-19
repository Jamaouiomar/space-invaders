package com.spaceinvaders.states;

public class GameOverState implements GameState {
    @Override
    public void handleInput(GameContext context, String input) {
        if ("RESTART".equals(input)) {
            context.setState(new MenuState());
        }
    }
    
    @Override
    public void update(GameContext context) {}
    
    @Override
    public void render(GameContext context) {
        System.out.println("=== GAME OVER ===");
        System.out.println("Final Score: 1500");
        System.out.println("Press R to restart");
    }
    
    @Override
    public String getStateName() {
        return "GAME_OVER";
    }
}