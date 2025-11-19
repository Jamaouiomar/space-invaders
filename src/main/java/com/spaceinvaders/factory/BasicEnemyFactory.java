package com.spaceinvaders.factory;

import com.spaceinvaders.composite.Enemy;
import com.spaceinvaders.utils.GameLogger;

public class BasicEnemyFactory implements EnemyFactory {
    @Override
    public Enemy createEnemy(String name) {
        GameLogger.log("FACTORY", "BasicEnemyFactory created: " + name);
        return new Enemy(name, 10);
    }
}