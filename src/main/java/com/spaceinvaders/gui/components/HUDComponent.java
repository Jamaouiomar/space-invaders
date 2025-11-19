package com.spaceinvaders.gui.components;

import com.spaceinvaders.states.GameContext;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class HUDComponent {
    private VBox container;
    private Label scoreLabel;
    private Label stateLabel;
    private Label patternsLabel;
    private Label controlsLabel;
    
    private int score = 0;
    private GameContext gameContext;

    public HUDComponent(GameContext gameContext) {
        this.gameContext = gameContext;
        createComponent();
        updateDisplay();
    }

    private void createComponent() {
        container = new VBox(10);
        container.setPadding(new Insets(10));
        container.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7); -fx-border-color: #444; -fx-border-width: 2;");

        // Score
        scoreLabel = createStyledLabel("Score: 0", Color.GOLD, 16);
        
        // État du jeu (State Pattern)
        stateLabel = createStyledLabel("State: " + gameContext.getState().getStateName(), Color.CYAN, 14);
        
        // Patterns actifs
        patternsLabel = createStyledLabel("🎯 State 🎨 Decorator 🏗️ Composite 🏭 Factory", Color.LIGHTGREEN, 12);
        
        // Contrôles
        controlsLabel = createStyledLabel("🎮 P=Pause ESC=Menu SPACE=Action", Color.YELLOW, 12);

        container.getChildren().addAll(scoreLabel, stateLabel, patternsLabel, controlsLabel);
    }

    private Label createStyledLabel(String text, Color color, int fontSize) {
        Label label = new Label(text);
        label.setTextFill(color);
        label.setFont(Font.font("Arial", fontSize));
        label.setStyle("-fx-font-weight: bold;");
        return label;
    }

    public void updateDisplay() {
        // Met à jour l'état du jeu (State Pattern)
        stateLabel.setText("State: " + gameContext.getState().getStateName());
        
        // Met à jour le score
        scoreLabel.setText("Score: " + score);
        
        // Change la couleur basée sur l'état
        updateStateColors();
    }

    private void updateStateColors() {
        String state = gameContext.getState().getStateName();
        switch (state) {
            case "PLAYING":
                stateLabel.setTextFill(Color.LIGHTGREEN);
                break;
            case "PAUSE":
                stateLabel.setTextFill(Color.YELLOW);
                break;
            case "GAME_OVER":
                stateLabel.setTextFill(Color.RED);
                break;
            default:
                stateLabel.setTextFill(Color.CYAN);
        }
    }

    public void addScore(int points) {
        score += points;
        updateDisplay();
        
        // Effet visuel pour gain de points
        scoreLabel.setScaleX(1.2);
        scoreLabel.setScaleY(1.2);
        
        // Animation de retour
        javafx.animation.ScaleTransition scaleTransition = 
            new javafx.animation.ScaleTransition(javafx.util.Duration.millis(200), scoreLabel);
        scaleTransition.setToX(1.0);
        scaleTransition.setToY(1.0);
        scaleTransition.play();
    }

    public void setPosition(double x, double y) {
        container.setLayoutX(x);
        container.setLayoutY(y);
    }

    public VBox getComponent() {
        return container;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
        updateDisplay();
    }
}