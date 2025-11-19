package com.spaceinvaders.entities;

import com.spaceinvaders.utils.GameLogger;

public class ShieldDecorator extends PlayerDecorator {
    public ShieldDecorator(Player player) {
        super(player);
        GameLogger.log("DECORATOR", "Shield applied to " + player.getDescription());
    }
    
    @Override
    public String getDescription() {
        return decoratedPlayer.getDescription() + " with Shield";
    }
    
    @Override
    public int getShield() {
        return decoratedPlayer.getShield() + 50;
    }
}