package com.spaceinvaders.gui.components;

import com.spaceinvaders.entities.Player;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class PlayerComponent {
    private Pane container;
    private Rectangle playerShape;
    private Text playerInfo;
    private Player player;

    public PlayerComponent(Player player) {
        this.player = player;
        createComponent();
        updateDisplay();
    }

    private void createComponent() {
        container = new Pane();
        
        // Forme du joueur
        playerShape = new Rectangle(40, 20);
        playerShape.setFill(Color.BLUE);
        playerShape.setStroke(Color.WHITE);
        playerShape.setStrokeWidth(2);
        
        // Informations du joueur (Decorator Pattern visible)
        playerInfo = new Text();
        playerInfo.setFill(Color.WHITE);
        playerInfo.setStyle("-fx-font: 12px Arial;");
        
        container.getChildren().addAll(playerShape, playerInfo);
    }

    public void updateDisplay() {
        // Met à jour l'apparence basée sur les Decorators
        updateAppearance();
        updateInfo();
    }

    private void updateAppearance() {
        // Change l'apparence basée sur les power-ups actifs
        String description = player.getDescription().toLowerCase();
        
        if (description.contains("speed")) {
            playerShape.setFill(Color.CYAN); // Speed boost = cyan
        }
        if (description.contains("shield")) {
            playerShape.setStroke(Color.GOLD); // Shield = bordure dorée
            playerShape.setStrokeWidth(3);
        }
        if (description.contains("speed") && description.contains("shield")) {
            playerShape.setFill(Color.PURPLE); // Les deux = violet
        }
    }

    private void updateInfo() {
        StringBuilder info = new StringBuilder();
        info.append("Player: ").append(player.getDescription()).append("\n");
        info.append("Speed: ").append(player.getSpeed()).append("\n");
        info.append("Shield: ").append(player.getShield());
        
        playerInfo.setText(info.toString());
    }

    public void setPosition(double x, double y) {
        playerShape.setX(x);
        playerShape.setY(y);
        playerInfo.setX(x);
        playerInfo.setY(y - 10);
    }

    public Pane getComponent() {
        return container;
    }

    public Player getPlayer() {
        return player;
    }
}