package com.spaceinvaders;

import com.spaceinvaders.gui.GameLauncher;
import com.spaceinvaders.utils.GameLogger;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0 && "console".equals(args[0])) {
            runConsoleDemo();
        } else {
            GameLogger.log("INFO", "🚀 Lancement de Space Invaders - Design Patterns");
            GameLauncher.launchGUI();
        }
    }
    
    private static void runConsoleDemo() {
        GameLogger.log("INFO", "=== SPACE INVADERS - MODE CONSOLE ===");
        
        // Test State Pattern
        GameLogger.log("INFO", "\n--- TEST STATE PATTERN ---");
        testStatePattern();
        
        // Test Decorator Pattern  
        GameLogger.log("INFO", "\n--- TEST DECORATOR PATTERN ---");
        testDecoratorPattern();
        
        // Test Composite Pattern
        GameLogger.log("INFO", "\n--- TEST COMPOSITE PATTERN ---");
        testCompositePattern();
        
        // Test Factory Pattern
        GameLogger.log("INFO", "\n--- TEST FACTORY PATTERN ---");
        testFactoryPattern();
        
        GameLogger.log("INFO", "=== DÉMONSTRATION TERMINÉE AVEC SUCCÈS ===");
    }
    
    private static void testStatePattern() {
        com.spaceinvaders.states.GameContext game = new com.spaceinvaders.states.GameContext();
        game.render();
        game.handleInput("START");
        game.render();
        game.handleInput("PAUSE");
        game.render();
        game.handleInput("RESUME");
        game.render();
        game.handleInput("GAME_OVER");
        game.render();
    }
    
    private static void testDecoratorPattern() {
        com.spaceinvaders.entities.Player player = new com.spaceinvaders.entities.BasicPlayer();
        System.out.println("\nPlayer: " + player.getDescription());
        System.out.println("Speed: " + player.getSpeed());
        System.out.println("Shield: " + player.getShield());
        
        player = new com.spaceinvaders.entities.SpeedBoostDecorator(player);
        player = new com.spaceinvaders.entities.ShieldDecorator(player);
        
        System.out.println("\nAfter power-ups: " + player.getDescription());
        System.out.println("Speed: " + player.getSpeed());
        System.out.println("Shield: " + player.getShield());
    }
    
    private static void testCompositePattern() {
        com.spaceinvaders.composite.Level level1 = new com.spaceinvaders.composite.Level(1);
        com.spaceinvaders.composite.EnemyGroup wave1 = new com.spaceinvaders.composite.EnemyGroup("Wave 1");
        com.spaceinvaders.composite.EnemyGroup wave2 = new com.spaceinvaders.composite.EnemyGroup("Wave 2");
        
        wave1.add(new com.spaceinvaders.composite.Enemy("Alien1", 10));
        wave1.add(new com.spaceinvaders.composite.Enemy("Alien2", 10));
        wave1.add(new com.spaceinvaders.composite.Enemy("Alien3", 10));
        
        wave2.add(new com.spaceinvaders.composite.Enemy("Alien4", 15));
        wave2.add(new com.spaceinvaders.composite.Enemy("Alien5", 15));
        
        level1.add(wave1);
        level1.add(wave2);
        
        level1.render();
        
        System.out.println("\n--- Updating Level 1 ---");
        level1.update();
        
        System.out.println("\n--- Testing Damage ---");
        com.spaceinvaders.composite.Enemy testEnemy = new com.spaceinvaders.composite.Enemy("TestEnemy", 10);
        testEnemy.takeDamage(5);
        testEnemy.takeDamage(5);
    }
    
    private static void testFactoryPattern() {
        System.out.println("\n--- Enemy Factory Demo ---");
        
        com.spaceinvaders.factory.EnemyFactory basicFactory = 
            com.spaceinvaders.factory.EnemyFactoryProducer.getFactory(com.spaceinvaders.factory.EnemyType.BASIC);
        com.spaceinvaders.factory.EnemyFactory fastFactory = 
            com.spaceinvaders.factory.EnemyFactoryProducer.getFactory(com.spaceinvaders.factory.EnemyType.FAST);
        com.spaceinvaders.factory.EnemyFactory tankFactory = 
            com.spaceinvaders.factory.EnemyFactoryProducer.getFactory(com.spaceinvaders.factory.EnemyType.TANK);
        
        com.spaceinvaders.composite.Enemy basicEnemy = basicFactory.createEnemy("Basic Alien");
        com.spaceinvaders.composite.Enemy fastEnemy = fastFactory.createEnemy("Fast Alien");
        com.spaceinvaders.composite.Enemy tankEnemy = tankFactory.createEnemy("Tank Alien");
        
        System.out.println("\n--- Testing Created Enemies ---");
        basicEnemy.render();
        fastEnemy.render();
        tankEnemy.render();
        
        System.out.println("\n--- Testing Enemy Durability ---");
        basicEnemy.takeDamage(5);
        fastEnemy.takeDamage(5);
        tankEnemy.takeDamage(5);
    }
}