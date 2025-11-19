package com.spaceinvaders.composite;

import java.util.ArrayList;
import java.util.List;
import com.spaceinvaders.utils.GameLogger;

public class EnemyGroup implements GameComponent {
    private String groupName;
    private List<GameComponent> components = new ArrayList<>();
    
    public EnemyGroup(String groupName) {
        this.groupName = groupName;
        GameLogger.log("COMPOSITE", "EnemyGroup created: " + groupName);
    }
    
    @Override
    public void update() {
        GameLogger.log("COMPOSITE", "Updating EnemyGroup: " + groupName + " with " + components.size() + " components");
        for (GameComponent component : components) {
            component.update();
        }
    }
    
    @Override
    public void render() {
        System.out.println("=== ENEMY GROUP: " + groupName + " ===");
        for (GameComponent component : components) {
            component.render();
        }
    }
    
    @Override
    public void add(GameComponent component) {
        components.add(component);
        GameLogger.log("COMPOSITE", "Added " + component.getName() + " to group " + groupName);
    }
    
    @Override
    public void remove(GameComponent component) {
        components.remove(component);
        GameLogger.log("COMPOSITE", "Removed " + component.getName() + " from group " + groupName);
    }
    
    @Override
    public String getName() {
        return groupName;
    }
}