package com.spaceinvaders.factory;

import com.spaceinvaders.utils.GameLogger;

public class EnemyFactoryProducer {
    public static EnemyFactory getFactory(EnemyType type) {
        GameLogger.log("FACTORY", "Factory requested for type: " + type);
        
        switch (type) {
            case FAST:
                return new FastEnemyFactory();
            case TANK:
                return new TankEnemyFactory();
            case BASIC:
            default:
                return new BasicEnemyFactory();
        }
    }
}