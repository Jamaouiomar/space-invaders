package com.spaceinvaders.entities;

import com.spaceinvaders.utils.GameLogger;

public class SpeedBoostDecorator extends PlayerDecorator {
    public SpeedBoostDecorator(Player player) {
        super(player);
        GameLogger.log("DECORATOR", "SpeedBoost applied to " + player.getDescription());
    }
    
    @Override
    public String getDescription() {
        return decoratedPlayer.getDescription() + " with Speed Boost";
    }
    
    @Override
    public int getSpeed() {
        return decoratedPlayer.getSpeed() * 2;
    }
}