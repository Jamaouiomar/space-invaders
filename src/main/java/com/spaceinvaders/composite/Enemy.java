package com.spaceinvaders.composite;

import com.spaceinvaders.utils.GameLogger;

public class Enemy implements GameComponent {
    private String name;
    private int health;
    
    public Enemy(String name, int health) {
        this.name = name;
        this.health = health;
        GameLogger.log("COMPOSITE", "Enemy created: " + name);
    }
    
    @Override
    public void update() {
        System.out.println("Enemy " + name + " updating...");
    }
    
    @Override
    public void render() {
        System.out.println("Rendering Enemy: " + name + " (Health: " + health + ")");
    }
    
    @Override
    public void add(GameComponent component) {
        throw new UnsupportedOperationException("Cannot add to an Enemy");
    }
    
    @Override
    public void remove(GameComponent component) {
        throw new UnsupportedOperationException("Cannot remove from an Enemy");
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    public void takeDamage(int damage) {
        this.health -= damage;
        GameLogger.log("COMPOSITE", "Enemy " + name + " took " + damage + " damage. Health: " + health);
        if (health <= 0) {
            GameLogger.log("COMPOSITE", "Enemy " + name + " destroyed!");
        }
    }
}