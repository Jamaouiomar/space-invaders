package com.spaceinvaders.entities;

public abstract class PlayerDecorator implements Player {
    protected Player decoratedPlayer;
    
    public PlayerDecorator(Player player) {
        this.decoratedPlayer = player;
    }
    
    @Override
    public String getDescription() {
        return decoratedPlayer.getDescription();
    }
    
    @Override
    public int getSpeed() {
        return decoratedPlayer.getSpeed();
    }
    
    @Override
    public int getFireRate() {
        return decoratedPlayer.getFireRate();
    }
    
    @Override
    public int getShield() {
        return decoratedPlayer.getShield();
    }
}