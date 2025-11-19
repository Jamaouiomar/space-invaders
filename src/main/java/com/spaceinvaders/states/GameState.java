package com.spaceinvaders.states;

public interface GameState {
    void handleInput(GameContext context, String input);
    void update(GameContext context);
    void render(GameContext context);
    String getStateName();
}