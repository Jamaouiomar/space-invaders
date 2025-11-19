package com.spaceinvaders.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameLogger {
    private static final Logger logger = LogManager.getLogger(GameLogger.class);
    
    public static void log(String level, String message) {
        switch (level.toUpperCase()) {
            case "STATE":
            case "DECORATOR":
            case "COMPOSITE":
            case "FACTORY":
                logger.info("[" + level + "] " + message);
                break;
            default:
                logger.info(message);
        }
    }
}