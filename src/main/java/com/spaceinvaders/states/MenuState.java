package com.spaceinvaders.states;

import com.spaceinvaders.utils.GameLogger;

public class MenuState implements GameState {
    @Override
    public void handleInput(GameContext context, String input) {
        if ("START".equals(input)) {
            context.setState(new PlayingState());
        } else if ("QUIT".equals(input)) {
            GameLogger.log("INFO", "Game quitting from menu");
        }
    }
    
    @Override
    public void update(GameContext context) {}
    
    @Override
    public void render(GameContext context) {
        System.out.println("=== MENU ===");
        System.out.println("1. START");
        System.out.println("2. QUIT");
    }
    
    @Override
    public String getStateName() {
        return "MENU";
    }
}