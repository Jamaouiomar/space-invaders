package com.spaceinvaders.gui;

import com.spaceinvaders.states.GameContext;
import com.spaceinvaders.utils.GameLogger;
import com.spaceinvaders.gui.views.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import java.util.Optional;

public class GameManager extends Application {
    private static GameContext gameContext;
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        
        GameLogger.log("INFO", "🎮 Space Invaders - Design Patterns - Starting...");
        
        try {
            // Initialise le contexte de jeu
            gameContext = new GameContext();
            
            // Configure la fenêtre principale
            configureStage();
            
            // Démarre avec l'écran menu
            showMenuView();
            
            primaryStage.show();
            
            // Focus initial
            Platform.runLater(() -> {
                primaryStage.requestFocus();
                System.out.println("✅ Application started successfully!");
            });
            
            GameLogger.log("INFO", "JavaFX Application started successfully");
            
        } catch (Exception e) {
            GameLogger.log("ERROR", "Critical error during startup: " + e.getMessage());
            showErrorDialog("Erreur de démarrage", "Impossible de démarrer l'application: " + e.getMessage());
        }
    }
    
    private void configureStage() {
        primaryStage.setTitle("🚀 Space Invaders - Design Patterns Edition");
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        
        // Gestion de la fermeture
        primaryStage.setOnCloseRequest(e -> {
            e.consume(); // Empêcher la fermeture immédiate
            confirmExit();
        });
    }
    
    private void confirmExit() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Quitter Space Invaders");
        alert.setContentText("Êtes-vous sûr de vouloir quitter le jeu?");
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            GameLogger.log("INFO", "User confirmed exit");
            exitGame();
        }
    }
    
    private void showErrorDialog(String title, String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(title);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }
    
    public void showMenuView() {
        Platform.runLater(() -> {
            try {
                MenuView menuView = new MenuView(this, gameContext);
                Scene scene = new Scene(menuView.getView(), 800, 600);
                primaryStage.setScene(scene);
                
                // Forcer le focus
                menuView.getView().requestFocus();
                
                GameLogger.log("STATE", "GUI: Showing Menu View");
                
            } catch (Exception e) {
                GameLogger.log("ERROR", "Failed to show MenuView: " + e.getMessage());
                showErrorDialog("Erreur Menu", "Impossible de charger le menu: " + e.getMessage());
            }
        });
    }
    
    public void showGameView() {
        Platform.runLater(() -> {
            try {
                GameView gameView = new GameView(this, gameContext);
                Scene scene = new Scene(gameView.getView(), 800, 600);
                primaryStage.setScene(scene);
                
                // Forcer le focus avec un délai pour être sûr
                new Thread(() -> {
                    try {
                        Thread.sleep(100); // Petit délai
                        Platform.runLater(() -> {
                            gameView.getView().requestFocus();
                            System.out.println("🎮 GameView focus activated!");
                            System.out.println("   → Flèches/ZQSD: Déplacer vaisseau");
                            System.out.println("   → ESPACE: Tirer");
                            System.out.println("   → P: Pause");
                            System.out.println("   → ESC: Menu");
                        });
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                }).start();
                
                GameLogger.log("STATE", "GUI: Showing Game View");
                
            } catch (Exception e) {
                GameLogger.log("ERROR", "Failed to show GameView: " + e.getMessage());
                showErrorDialog("Erreur Jeu", "Impossible de charger le jeu: " + e.getMessage());
                showMenuView(); // Retour au menu
            }
        });
    }
    
    public void showPauseView() {
        Platform.runLater(() -> {
            try {
                PauseView pauseView = new PauseView(this, gameContext);
                Scene scene = new Scene(pauseView.getView(), 800, 600);
                primaryStage.setScene(scene);
                
                // Forcer le focus
                pauseView.getView().requestFocus();
                
                GameLogger.log("STATE", "GUI: Showing Pause View");
                
            } catch (Exception e) {
                GameLogger.log("ERROR", "Failed to show PauseView: " + e.getMessage());
                showGameView(); // Retour au jeu en cas d'erreur
            }
        });
    }
    
    public void showGameOverView() {
        Platform.runLater(() -> {
            try {
                GameOverView gameOverView = new GameOverView(this, gameContext);
                Scene scene = new Scene(gameOverView.getView(), 800, 600);
                primaryStage.setScene(scene);
                
                // Forcer le focus
                gameOverView.getView().requestFocus();
                
                GameLogger.log("STATE", "GUI: Showing Game Over View");
                
            } catch (Exception e) {
                GameLogger.log("ERROR", "Failed to show GameOverView: " + e.getMessage());
                showMenuView(); // Retour au menu en cas d'erreur
            }
        });
    }
    
    public GameContext getGameContext() {
        return gameContext;
    }
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    public void restartGame() {
        GameLogger.log("INFO", "🔄 Restarting game...");
        gameContext = new GameContext();
        showGameView();
    }
    
    public void exitGame() {
        GameLogger.log("INFO", "👋 Exiting game...");
        Platform.exit();
        System.exit(0);
    }

    public static void main(String[] args) {
        try {
            GameLogger.log("INFO", "🎯 Launching Space Invaders - Design Patterns...");
            System.out.println("🚀 Starting Space Invaders...");
            launch(args);
        } catch (Exception e) {
            GameLogger.log("ERROR", "❌ Failed to launch: " + e.getMessage());
            System.out.println("❌ JavaFX failed: " + e.getMessage());
            System.out.println("🔄 Falling back to console mode...");
            
            // Fallback vers le mode console
            com.spaceinvaders.Main.main(new String[]{"console"});
        }
    }
}