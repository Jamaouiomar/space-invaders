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

public class PauseView {
    private VBox view;
    private GameManager gameManager;
    private GameContext gameContext;

    public PauseView(GameManager gameManager, GameContext gameContext) {
        this.gameManager = gameManager;
        this.gameContext = gameContext;
        createView();
    }

    private void createView() {
        Label title = new Label("JEU EN PAUSE");
        title.setFont(Font.font("Arial", 32));
        title.setTextFill(Color.YELLOW);

        Label info = new Label("L'architecture Design Patterns est active");
        info.setFont(Font.font("Arial", 14));
        info.setTextFill(Color.WHITE);

        Button resumeButton = createButton("REPRENDRE", Color.LIGHTGREEN);
        Button menuButton = createButton("MENU PRINCIPAL", Color.LIGHTCORAL);

        resumeButton.setOnAction(e -> {
            GameLogger.log("STATE", "GUI: Pause -> Game");
            gameManager.showGameView();
        });

        menuButton.setOnAction(e -> {
            GameLogger.log("STATE", "GUI: Pause -> Menu");
            gameManager.showMenuView();
        });

        view = new VBox(20, title, info, resumeButton, menuButton);
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

    public VBox getView() {
        return view;
    }
}