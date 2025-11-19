package com.spaceinvaders.composite;

import java.util.ArrayList;
import java.util.List;
import com.spaceinvaders.utils.GameLogger;

public class Level implements GameComponent {
    private int levelNumber;
    private List<GameComponent> components = new ArrayList<>();
    
    public Level(int levelNumber) {
        this.levelNumber = levelNumber;
        GameLogger.log("COMPOSITE", "Level " + levelNumber + " created");
    }
    
    @Override
    public void update() {
        GameLogger.log("COMPOSITE", "Updating Level " + levelNumber + " with " + components.size() + " components");
        for (GameComponent component : components) {
            component.update();
        }
    }
    
    @Override
    public void render() {
        System.out.println("\n=== LEVEL " + levelNumber + " ===");
        for (GameComponent component : components) {
            component.render();
        }
    }
    
    @Override
    public void add(GameComponent component) {
        components.add(component);
        GameLogger.log("COMPOSITE", "Added " + component.getName() + " to Level " + levelNumber);
    }
    
    @Override
    public void remove(GameComponent component) {
        components.remove(component);
        GameLogger.log("COMPOSITE", "Removed " + component.getName() + " from Level " + levelNumber);
    }
    
    @Override
    public String getName() {
        return "Level " + levelNumber;
    }
}