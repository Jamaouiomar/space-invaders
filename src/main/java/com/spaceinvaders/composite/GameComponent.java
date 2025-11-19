package com.spaceinvaders.composite;

public interface GameComponent {
    void update();
    void render();
    void add(GameComponent component);
    void remove(GameComponent component);
    String getName();
}