package com.spaceinvaders.gui.components;

import com.spaceinvaders.composite.Enemy;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class EnemyComponent {
    private Pane container;
    private Circle enemyShape;
    private Text enemyInfo;
    private Enemy enemy;

    public EnemyComponent(Enemy enemy) {
        this.enemy = enemy;
        createComponent();
        updateDisplay();
    }

    private void createComponent() {
        container = new Pane();
        
        // Forme de l'ennemi (dépend de son type via Factory Pattern)
        enemyShape = new Circle(15);
        setEnemyAppearanceBasedOnHealth();
        
        // Informations de l'ennemi
        enemyInfo = new Text();
        enemyInfo.setFill(Color.WHITE);
        enemyInfo.setStyle("-fx-font: 10px Arial;");
        
        container.getChildren().addAll(enemyShape, enemyInfo);
    }

    private void setEnemyAppearanceBasedOnHealth() {
        int health = getHealthFromEnemyName();
        
        if (health <= 5) {
            enemyShape.setFill(Color.RED);    // Faible santé = rouge
        } else if (health <= 10) {
            enemyShape.setFill(Color.ORANGE); // Santé moyenne = orange
        } else {
            enemyShape.setFill(Color.GREEN);  // Bonne santé = vert
        }
        
        enemyShape.setStroke(Color.WHITE);
        enemyShape.setStrokeWidth(1);
    }

    private int getHealthFromEnemyName() {
        String name = enemy.getName().toLowerCase();
        if (name.contains("fast")) return 5;
        if (name.contains("tank")) return 25;
        return 10; // Basic
    }

    public void updateDisplay() {
        updateInfo();
        
        // Animation de dégâts
        if (enemy.getName().contains("damaged")) {
            enemyShape.setFill(Color.DARKRED);
        }
    }

    private void updateInfo() {
        StringBuilder info = new StringBuilder();
        info.append(enemy.getName()).append("\n");
        info.append("Health: ").append(getHealthFromEnemyName());
        
        enemyInfo.setText(info.toString());
    }

    public void setPosition(double x, double y) {
        enemyShape.setCenterX(x);
        enemyShape.setCenterY(y);
        enemyInfo.setX(x - 20);
        enemyInfo.setY(y - 20);
    }

    public void takeDamage() {
        // Effet visuel de dégâts
        enemyShape.setFill(Color.DARKRED);
        enemyShape.setStroke(Color.RED);
    }

    public Pane getComponent() {
        return container;
    }

    public Enemy getEnemy() {
        return enemy;
    }
}