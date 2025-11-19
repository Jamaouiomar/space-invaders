package com.spaceinvaders.factory;

import com.spaceinvaders.composite.Enemy;
import com.spaceinvaders.utils.GameLogger;

public class FastEnemyFactory implements EnemyFactory {
    @Override
    public Enemy createEnemy(String name) {
        GameLogger.log("FACTORY", "FastEnemyFactory created: " + name);
        return new Enemy(name, 5);
    }
}