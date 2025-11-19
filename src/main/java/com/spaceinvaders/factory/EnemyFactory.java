package com.spaceinvaders.factory;

import com.spaceinvaders.composite.Enemy;

public interface EnemyFactory {
    Enemy createEnemy(String name);
}