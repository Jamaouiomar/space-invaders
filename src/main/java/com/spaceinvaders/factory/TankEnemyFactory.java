package com.spaceinvaders.factory;

import com.spaceinvaders.composite.Enemy;
import com.spaceinvaders.utils.GameLogger;

public class TankEnemyFactory implements EnemyFactory {
    @Override
    public Enemy createEnemy(String name) {
        GameLogger.log("FACTORY", "TankEnemyFactory created: " + name);
        return new Enemy(name, 25);
    }
}