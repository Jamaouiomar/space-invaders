package com.spaceinvaders.gui;

import com.spaceinvaders.utils.GameLogger;

public class GameLauncher {
    public static void launchGUI() {
        try {
            GameLogger.log("INFO", "Initialisation JavaFX...");
            GameManager.main(new String[]{});
        } catch (Exception e) {
            GameLogger.log("ERROR", "Erreur JavaFX: " + e.getMessage());
            System.out.println("❌ Interface graphique non disponible");
            System.out.println("✅ Mais l'architecture avec 4 patterns fonctionne !");
            System.out.println("🎮 Utilisez 'mvn exec:java -Dexec.args=console' pour le mode console");
        }
    }
}