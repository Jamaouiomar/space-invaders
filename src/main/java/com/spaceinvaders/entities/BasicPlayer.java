package com.spaceinvaders.entities;

public class BasicPlayer implements Player {
    @Override
    public String getDescription() {
        return "Basic Player";
    }
    
    @Override
    public int getSpeed() {
        return 5;
    }
    
    @Override
    public int getFireRate() {
        return 1;
    }
    
    @Override
    public int getShield() {
        return 0;
    }
}