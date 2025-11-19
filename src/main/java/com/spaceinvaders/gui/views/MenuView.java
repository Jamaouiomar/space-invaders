package com.spaceinvaders.gui.views;

import com.spaceinvaders.gui.GameManager;
import com.spaceinvaders.states.GameContext;
import com.spaceinvaders.utils.GameLogger;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MenuView {
    private VBox view;
    private GameManager gameManager;
    private GameContext gameContext;

    public MenuView(GameManager gameManager, GameContext gameContext) {
        this.gameManager = gameManager;
        this.gameContext = gameContext;
        createView();
    }

    private void createView() {
        Label title = new Label("SPACE INVADERS");
        title.setFont(Font.font("Arial", 36));
        title.setTextFill(Color.LIGHTBLUE);

        Label subtitle = new Label("Design Patterns Edition");
        subtitle.setFont(Font.font("Arial", 18));
        subtitle.setTextFill(Color.WHITE);

        Label patternsInfo = new Label("✅ 4 Patterns Implémentés: State, Decorator, Composite, Factory");
        patternsInfo.setFont(Font.font("Arial", 14));
        patternsInfo.setTextFill(Color.LIGHTGREEN);

        Button startButton = createButton("DÉMARRER", Color.LIGHTGREEN);
        Button consoleButton = createButton("MODE CONSOLE", Color.YELLOW);
        Button quitButton = createButton("QUITTER", Color.LIGHTCORAL);

        startButton.setOnAction(e -> {
            GameLogger.log("STATE", "GUI: Menu -> Starting Game");
            gameContext.handleInput("START");
            gameManager.showGameView();
        });

        consoleButton.setOnAction(e -> {
            GameLogger.log("INFO", "GUI: Switching to console mode");
            showConsoleInfo();
        });

        quitButton.setOnAction(e -> {
            GameLogger.log("INFO", "GUI: Application quitting");
            System.exit(0);
        });

        view = new VBox(20, title, subtitle, patternsInfo, startButton, consoleButton, quitButton);
        view.setAlignment(Pos.CENTER);
        view.setStyle("-fx-background-color: #000000; -fx-padding: 40px;");
    }

    private Button createButton(String text, Color color) {
        Button button = new Button(text);
        button.setFont(Font.font("Arial", 16));
        button.setTextFill(color);
        button.setStyle("-fx-background-color: transparent; -fx-border-color: " + toHex(color) + "; -fx-border-width: 2px;");
        button.setMinWidth(200);
        return button;
    }

    private String toHex(Color color) {
        return String.format("#%02X%02X%02X",
            (int)(color.getRed() * 255),
            (int)(color.getGreen() * 255),
            (int)(color.getBlue() * 255));
    }

    private void showConsoleInfo() {
        System.out.println("\n=== MODE CONSOLE ACTIVÉ ===");
        System.out.println("Les 4 patterns sont fonctionnels:");
        System.out.println("- State Pattern: Gestion des états du jeu");
        System.out.println("- Decorator Pattern: Système de power-ups");
        System.out.println("- Composite Pattern: Structure des niveaux");
        System.out.println("- Factory Pattern: Création d'ennemis");
        System.out.println("Voir les logs dans logs/game.log");
    }

    public VBox getView() {
        return view;
    }
}